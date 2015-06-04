/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ticket.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TOrder.findAll", query = "SELECT t FROM TOrder t"),
    @NamedQuery(name = "TOrder.findById", query = "SELECT t FROM TOrder t WHERE t.id = :id"),
    @NamedQuery(name = "TOrder.findByUserId", query = "SELECT t FROM TOrder t WHERE t.userId = :userId"),
    @NamedQuery(name = "TOrder.findByCardId", query = "SELECT t FROM TOrder t WHERE t.cardId = :cardId"),
    @NamedQuery(name = "TOrder.findByActivityId", query = "SELECT t FROM TOrder t WHERE t.activityId = :activityId"),
    @NamedQuery(name = "TOrder.findByPrice", query = "SELECT t FROM TOrder t WHERE t.price = :price"),
    @NamedQuery(name = "TOrder.findByPay", query = "SELECT t FROM TOrder t WHERE t.pay = :pay"),
    @NamedQuery(name = "TOrder.findByPayType", query = "SELECT t FROM TOrder t WHERE t.payType = :payType"),
    @NamedQuery(name = "TOrder.findByCouponCode", query = "SELECT t FROM TOrder t WHERE t.couponCode = :couponCode"),
    @NamedQuery(name = "TOrder.findByDiscount", query = "SELECT t FROM TOrder t WHERE t.discount = :discount"),
    @NamedQuery(name = "TOrder.findByDiscountReason", query = "SELECT t FROM TOrder t WHERE t.discountReason = :discountReason"),
    @NamedQuery(name = "TOrder.findByOrderState", query = "SELECT t FROM TOrder t WHERE t.orderState = :orderState"),
    @NamedQuery(name = "TOrder.findByExtProps", query = "SELECT t FROM TOrder t WHERE t.extProps = :extProps"),
    @NamedQuery(name = "TOrder.findByCardAndActivity", query = "SELECT t FROM TOrder t WHERE t.cardId = :cardId AND t.activityId = :activityId"),
    @NamedQuery(name = "TOrder.findByNote", query = "SELECT t FROM TOrder t WHERE t.note = :note")})
public class TOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "USER_ID")
    private Integer userId;
    @Column(name = "CARD_ID")
    private Integer cardId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACTIVITY_ID")
    private int activityId;
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "PAY")
    private Double pay;
    @Column(name = "PAY_TYPE")
    private Integer payType;
    @Size(max = 30)
    @Column(name = "COUPON_CODE")
    private String couponCode;
    @Size(max = 10)
    @Column(name = "DISCOUNT")
    private String discount;
    @Size(max = 30)
    @Column(name = "DISCOUNT_REASON")
    private String discountReason;
    @Column(name = "ORDER_STATE")
    private Integer orderState;
    @Size(max = 512)
    @Column(name = "EXT_PROPS")
    private String extProps;
    @Size(max = 512)
    @Column(name = "NOTE")
    private String note;

    public TOrder() {
    }

    public TOrder(Integer id) {
        this.id = id;
    }

    public TOrder(Integer id, int activityId) {
        this.id = id;
        this.activityId = activityId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPay() {
        return pay;
    }

    public void setPay(Double pay) {
        this.pay = pay;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscountReason() {
        return discountReason;
    }

    public void setDiscountReason(String discountReason) {
        this.discountReason = discountReason;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public String getExtProps() {
        return extProps;
    }

    public void setExtProps(String extProps) {
        this.extProps = extProps;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TOrder)) {
            return false;
        }
        TOrder other = (TOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ticket.entity.TOrder[ id=" + id + " ]";
    }
    
}
