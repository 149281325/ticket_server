/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ticket.utils;

import org.codehaus.jettison.json.*;
import org.ticket.entity.TCard;
import org.ticket.entity.TMCardtype;

/**
 * The convertion between entities and JSON strings.
 * @author Administrator
 */
public class JSONUtil {

    public static TCard asCard(String jsonStr) throws JSONException {
        TCard entity = new TCard();
        JSONObject json = new JSONObject(jsonStr);
        entity.setId(Long.parseLong(json.getString("uid"), 16));
        int type = 0;
        if(json.has("count")) {
            type += TMCardtype.REMAIN_TIMES;
            entity.setRemainTimes(json.getInt("count"));
        }
        if(json.has("balance")) {
            type += TMCardtype.BALANCE;
            entity.setBalance(json.getDouble("balance"));
        }
        if(json.has("start_time")) {
            type += TMCardtype.VALID_DATE;
            String dateStr = json.getString("start_time");
            if(dateStr.length()==8) dateStr = dateStr + "000000";
            entity.setValidFrom(DateUtil.strToDate(dateStr));
        }
        if(json.has("end_time")) {
            if((type & 1) == 0) {
                type += TMCardtype.VALID_DATE;
            }
            String dateStr = json.getString("end_time");
            if(dateStr.length()==8) dateStr = dateStr + "235959";
            entity.setValidTo(DateUtil.strToDate(dateStr));
        }
        if(json.has("single_day")) {
            if(json.getBoolean("single_day"))
                type += TMCardtype.LIMIT_8HOURS;
        }
        if(json.has("staff")) {
            if(json.getBoolean("staff"))
                type += TMCardtype.STAFF;
        }
        entity.setCardType(type);
        if(json.has("state")) {
            entity.setCardState(json.getInt("state"));
        }
        return entity;
    }
    
    private static void inspectCard(TCard c) {
        System.out.println("--- CARD " + Long.toHexString(c.getId()) + "---");
        System.out.println("type: " + c.getCardType());
        System.out.println("state: " + c.getCardState());
        System.out.println("balance: " + c.getBalance());
        System.out.println("points: " + c.getRemainTimes());
        System.out.println("from: " + c.getValidFrom());
        System.out.println("to: " + c.getValidTo());
    }

    private static void testCard() throws Exception {
        String s1 = "{\"type\":\"add\",\"uid\":\"FFFFFFFF\",\"count\":10}";
        String s2 = "{\"type\":\"add\",\"uid\":\"FFFFFFFF\",\"balance\":1.00}";
        String s3 = "{\"type\":\"add\",\"uid\":\"FFFFFFFF\",\"start_time\":20150801,\"end_time\":20150802,\"count\":1}";
        String s4 = "{\"type\":\"add\",\"uid\":\"FFFFFFFF\",\"start_time\":20150801,\"end_time\":20150802,\"balance\":1.02}";
        inspectCard(asCard(s1));
        inspectCard(asCard(s2));
        inspectCard(asCard(s3));
        inspectCard(asCard(s4));
    }
    
    public static void main(String args[]) throws Exception {
        //System.out.println("hao!");
        System.out.println(Long.parseLong("ffffffff", 16));
        System.out.println(Integer.MAX_VALUE);
        //System.out.println(Integer.toHexString(mx));
        //testCard();
    }
}
