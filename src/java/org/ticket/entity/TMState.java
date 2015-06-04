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
@Table(name = "t_m_state")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TMState.findAll", query = "SELECT t FROM TMState t"),
    @NamedQuery(name = "TMState.findByStateId", query = "SELECT t FROM TMState t WHERE t.stateId = :stateId"),
    @NamedQuery(name = "TMState.findByStateDesc", query = "SELECT t FROM TMState t WHERE t.stateDesc = :stateDesc")})
public class TMState implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public static final int NORMAL = 1;
    public static final int SUSPENDED = 9;
    public static final int DELETED = -1;
    public static final int NOT_PAIED = 101;
    public static final int PAIED = 102;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATE_ID")
    private Integer stateId;
    @Size(max = 100)
    @Column(name = "STATE_DESC")
    private String stateDesc;

    public TMState() {
    }

    public TMState(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getStateDesc() {
        return stateDesc;
    }

    public void setStateDesc(String stateDesc) {
        this.stateDesc = stateDesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stateId != null ? stateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TMState)) {
            return false;
        }
        TMState other = (TMState) object;
        if ((this.stateId == null && other.stateId != null) || (this.stateId != null && !this.stateId.equals(other.stateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ticket.entity.TMState[ stateId=" + stateId + " ]";
    }
    
}
