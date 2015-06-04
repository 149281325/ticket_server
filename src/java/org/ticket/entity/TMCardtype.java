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
@Table(name = "t_m_cardtype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TMCardtype.findAll", query = "SELECT t FROM TMCardtype t"),
    @NamedQuery(name = "TMCardtype.findByTypeId", query = "SELECT t FROM TMCardtype t WHERE t.typeId = :typeId"),
    @NamedQuery(name = "TMCardtype.findByTypeDesc", query = "SELECT t FROM TMCardtype t WHERE t.typeDesc = :typeDesc")})
public class TMCardtype implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public static final int VALID_DATE = 1; 
    public static final int BALANCE = 2;
    public static final int REMAIN_TIMES = 4;
    public static final int LIMIT_8HOURS = 8; //card will be invalid in 8 hours after entering, for one-day-card in a multi-days activity
    public static final int STAFF = 16;
    
    public static final int VALID_DATE_BALANCE = VALID_DATE | BALANCE; //3
    public static final int VALID_DATE_REMAIN_TIMES = VALID_DATE | REMAIN_TIMES; //5
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TYPE_ID")
    private Integer typeId;
    @Size(max = 100)
    @Column(name = "TYPE_DESC")
    private String typeDesc;

    public TMCardtype() {
    }

    public TMCardtype(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typeId != null ? typeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TMCardtype)) {
            return false;
        }
        TMCardtype other = (TMCardtype) object;
        if ((this.typeId == null && other.typeId != null) || (this.typeId != null && !this.typeId.equals(other.typeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ticket.entity.TMCardtype[ typeId=" + typeId + " ]";
    }
    
}
