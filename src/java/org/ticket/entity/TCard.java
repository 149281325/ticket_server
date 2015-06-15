/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ticket.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_card")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TCard.findAll", query = "SELECT t FROM TCard t"),
    @NamedQuery(name = "TCard.findById", query = "SELECT t FROM TCard t WHERE t.id = :id"),
    @NamedQuery(name = "TCard.findByCardType", query = "SELECT t FROM TCard t WHERE t.cardType = :cardType"),
    @NamedQuery(name = "TCard.findByCardState", query = "SELECT t FROM TCard t WHERE t.cardState = :cardState"),
    @NamedQuery(name = "TCard.findByBalance", query = "SELECT t FROM TCard t WHERE t.balance = :balance"),
    @NamedQuery(name = "TCard.findByValidFrom", query = "SELECT t FROM TCard t WHERE t.validFrom = :validFrom"),
    @NamedQuery(name = "TCard.findByValidTo", query = "SELECT t FROM TCard t WHERE t.validTo = :validTo"),
    @NamedQuery(name = "TCard.findByRemainTimes", query = "SELECT t FROM TCard t WHERE t.remainTimes = :remainTimes"),
    @NamedQuery(name = "TCard.findByExtProps", query = "SELECT t FROM TCard t WHERE t.extProps = :extProps")})
public class TCard implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CARD_TYPE")
    private int cardType;
    @Column(name = "CARD_STATE")
    private Integer cardState;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "BALANCE")
    private Double balance;
    @Column(name = "VALID_FROM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validFrom;
    @Column(name = "VALID_TO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validTo;
    @Column(name = "REMAIN_TIMES")
    private Integer remainTimes;
    @Size(max = 512)
    @Column(name = "EXT_PROPS")
    private String extProps;

    public TCard() {
    }

    public TCard(Long id) {
        this.id = id;
    }

    public TCard(Long id, int cardType) {
        this.id = id;
        this.cardType = cardType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public Integer getCardState() {
        return cardState;
    }

    public void setCardState(Integer cardState) {
        this.cardState = cardState;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public Integer getRemainTimes() {
        return remainTimes;
    }

    public void setRemainTimes(Integer remainTimes) {
        this.remainTimes = remainTimes;
    }

    public String getExtProps() {
        return extProps;
    }

    public void setExtProps(String extProps) {
        this.extProps = extProps;
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
        if (!(object instanceof TCard)) {
            return false;
        }
        TCard other = (TCard) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ticket.entity.TCard[ id=" + id + " ]";
    }
    
}
