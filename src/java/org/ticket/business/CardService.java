/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ticket.business;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
//import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.ticket.entity.TCard;
import org.ticket.entity.TMCardtype;
import org.ticket.entity.TMPaytype;
import org.ticket.entity.TMState;
import org.ticket.entity.TOrder;
import org.ticket.entity.service.TCardFacadeREST;
import org.ticket.entity.service.TOrderFacadeREST;
import org.ticket.utils.DateUtil;
import org.ticket.utils.JSONUtil;
import org.ticket.utils.SysConfig;

/**
 *
 * @author Administrator
 */
@Path("card")
@Stateless
@LocalBean
public class CardService {

    @EJB
    TCardFacadeREST cardBean;
    
    @EJB
    TOrderFacadeREST orderBean;
    
    int contextActivityId = SysConfig.getActivityId();
    
    double contextPrice = SysConfig.getDefaultPrice();
    
    public static final String SIMPLE_RESULT = "{\"Result\":%d,\"Remark\":\"%s\"}";
    
    String ret(String remark) {
        if(remark == null) {
            return String.format(SIMPLE_RESULT, 1, "ok.");
        } else if(remark.startsWith("ok")) {
            return String.format(SIMPLE_RESULT, 1, remark);
        } else {
            return String.format(SIMPLE_RESULT, 0, remark);
        }
    }
    
    /**
     * Add a card record to database.
     * @param json
     *      JSON string like:
     *      {"type":"add","uid":"FFFFFFFF","count":"10"}
     *      {"type":"add","uid":"FFFFFFFF","balance":"1.00"}
     *      {"type":"add","uid":"FFFFFFFF","start_time":"20150801","end_time":"20150802","count":"10"}
     *      {"type":"add","uid":"FFFFFFFF","start_time":"20150801","end_time":"20150802","balance":"1.00"}
     * @return 
     */
    @PUT
    @Consumes({"application/json"})
    @Produces({"text/plain"})
    public String addCard(String json) {
        TCard card = null;
        try {
            card = JSONUtil.asCard(json);
            cardBean.create(card);
        } catch(Exception e) {
            e.printStackTrace(System.out);
            return ret(e.getMessage());
        }
        return ret(null);
    }
    
    /**
     * Check card, update the card info, generate an order
     * @param json
     *      JSON string like:
     *      {"type":"check","uid":"FFFFFFFF","count":1}
     *      {"type":"check","uid":"FFFFFFFF","count":1,"valid_check":"true"}
     *      {"type":"check","uid":"FFFFFFFF","balance":233.00,"valid_check":"true"}
     * @return 
     */
    @POST
    @Consumes({"application/json"})
    @Produces({"text/plain"})
    public String checkCard(String json) throws JSONException {
        JSONObject j = new JSONObject(json);
        int id = Integer.parseInt(j.getString("uid"), 16);
        TCard card = cardBean.find(id);
        if(card == null) {
            return ret("Card not found: " + Integer.toHexString(id));
        }
        int cardType = card.getCardType();
        if((cardType & TMCardtype.STAFF) > 0) {
            return ret("ok. Staff");
        }
        
        int currActivityId = contextActivityId;
        if(j.has("activity_id")) currActivityId = j.getInt("activity_id"); 
        
        //re-enter?
        try {
            if(reEnter(card, currActivityId)) return ret("ok. Re-enter");
        } catch(Exception exc) {
            return ret(exc.getMessage());
        }
        
        //update the card
        int count = 0;
        double balance = 0;
        if(j.has("count")) {
            if(j.has("valid_check")) {
                if("true".equals(j.getString("valid_check"))) {
                    try {
                        validateDate(card, j);
                    } catch(Exception e) {
                        return ret(e.getMessage());
                    }
                }
            } 
            count = j.getInt("count");
            if(count <= 0) {
                return ret("count should larger than 0!");
            }
            int points = card.getRemainTimes();
            if(points < count) {
                return ret("Remain times not enough! Remain " + points + " times but need " + count);
            }
            card.setRemainTimes(points - count);
        } else if(j.has("balance")) {
            if(j.has("valid_check")) {
                if("true".equals(j.getString("valid_check"))) {
                    try {
                        validateDate(card, j);
                    } catch(Exception e) {
                        return ret(e.getMessage());
                    }
                }
            } 
            balance = j.getDouble("balance");
            if(balance <= 0) {
                return ret("balance should larger than 0!");
            }
            double currBalance = card.getBalance();
            if(currBalance < balance) {
                return ret("Balance not enough! Remain " + currBalance + " but need " + balance );
            }
            card.setBalance(currBalance - balance);
        }
        cardBean.edit(card);
        
        //generate an order
        TOrder order = new TOrder();
        order.setCardId(id);
        order.setActivityId(currActivityId);
        
        double currPrice = contextPrice;
        if(j.has("price")) currPrice = j.getDouble("price"); 
        order.setPrice(currPrice);
        
        order.setPay(balance==0 ? currPrice : balance);
        order.setPayType(balance==0 ? TMPaytype.CARD_REMAIN_TIMES : TMPaytype.CARD_BALANCE);
        order.setOrderState(TMState.PAIED);
        order.setExtProps("checkin:" + DateUtil.dateToStr(new Date(),"yyyyMMddHHmmss") + ",");
        orderBean.create(order);
        return ret(null);
    }
    
    void validateDate(TCard c, JSONObject j) throws JSONException {
        Date begin = c.getValidFrom();
        Date end = c.getValidTo();
        if(begin == null && end == null) {
            if(j.has("nodate_valid") && "true".equals(j.getString("nodate_valid"))) {
                    return;
            }
            throw new IllegalStateException("Card without valid date not allowed");
        }
        if(!DateUtil.atRange(new Date(), begin, end)) {
            throw new IllegalStateException("Invalid date: " + DateUtil.dateToStr(begin) + " - " + DateUtil.dateToStr(end));
        }
    }

    private boolean reEnter(TCard card, int actId) {
        List<TOrder> checkins = orderBean.findByCardAndActivity(card.getId(), actId);
        if(checkins.isEmpty()) return false;
        
        //re-enter, if its a single-day card and 8 hour has past, fail the checkin, otherwise log the checkin time
        TOrder o = checkins.get(0);
        if((card.getCardType() & TMCardtype.LIMIT_8HOURS) > 0) {
            String extProp = o.getExtProps();
            int i = extProp.lastIndexOf("checkin:");
            if(i == -1) {
                //no checkin time?
                System.out.println(">> Warning: no checkin time! something must be wrong." );
                logCheckin(o);
                return true; //allow
            }
            String lastCheckin = extProp.substring(i+7, i+7+14);
            System.out.println(">> Last checkin: " + lastCheckin);
            Date lastChk = DateUtil.strToDate(lastCheckin);
            if(DateUtil.plus8Hours(lastChk).before(new Date())) {
                throw new IllegalStateException("This is a single day card and was used");
            }
        }
        logCheckin(o);
        return true;
    }
    
    private void logCheckin(TOrder o) {
        String orig = o.getExtProps() == null ? "" : o.getExtProps();
        o.setExtProps(orig + "checkin:" + DateUtil.dateToStr(new Date(),"yyyyMMddHHmmss") + ",");
        orderBean.edit(o);
    }
    
}
