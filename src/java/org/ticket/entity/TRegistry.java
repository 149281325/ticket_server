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
@Table(name = "t_registry")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TRegistry.findAll", query = "SELECT t FROM TRegistry t"),
    @NamedQuery(name = "TRegistry.findById", query = "SELECT t FROM TRegistry t WHERE t.id = :id"),
    @NamedQuery(name = "TRegistry.findByUserId", query = "SELECT t FROM TRegistry t WHERE t.userId = :userId"),
    @NamedQuery(name = "TRegistry.findByCardId", query = "SELECT t FROM TRegistry t WHERE t.cardId = :cardId"),
    @NamedQuery(name = "TRegistry.findByActivityId", query = "SELECT t FROM TRegistry t WHERE t.activityId = :activityId"),
    @NamedQuery(name = "TRegistry.findByExtProps", query = "SELECT t FROM TRegistry t WHERE t.extProps = :extProps")})
public class TRegistry implements Serializable {
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
    @Size(max = 512)
    @Column(name = "EXT_PROPS")
    private String extProps;

    public TRegistry() {
    }

    public TRegistry(Integer id) {
        this.id = id;
    }

    public TRegistry(Integer id, int activityId) {
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
        if (!(object instanceof TRegistry)) {
            return false;
        }
        TRegistry other = (TRegistry) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ticket.entity.TRegistry[ id=" + id + " ]";
    }
    
}
