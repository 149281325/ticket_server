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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "t_activity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TActivity.findAll", query = "SELECT t FROM TActivity t"),
    @NamedQuery(name = "TActivity.findById", query = "SELECT t FROM TActivity t WHERE t.id = :id"),
    @NamedQuery(name = "TActivity.findByName", query = "SELECT t FROM TActivity t WHERE t.name = :name"),
    @NamedQuery(name = "TActivity.findByActivityState", query = "SELECT t FROM TActivity t WHERE t.activityState = :activityState"),
    @NamedQuery(name = "TActivity.findByAddress", query = "SELECT t FROM TActivity t WHERE t.address = :address"),
    @NamedQuery(name = "TActivity.findByTimeFrom", query = "SELECT t FROM TActivity t WHERE t.timeFrom = :timeFrom"),
    @NamedQuery(name = "TActivity.findByTimeTo", query = "SELECT t FROM TActivity t WHERE t.timeTo = :timeTo"),
    @NamedQuery(name = "TActivity.findByOwner", query = "SELECT t FROM TActivity t WHERE t.owner = :owner"),
    @NamedQuery(name = "TActivity.findByExtProps", query = "SELECT t FROM TActivity t WHERE t.extProps = :extProps"),
    @NamedQuery(name = "TActivity.findByNote", query = "SELECT t FROM TActivity t WHERE t.note = :note")})
public class TActivity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NAME")
    private String name;
    @Column(name = "ACTIVITY_STATE")
    private Integer activityState;
    @Size(max = 300)
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "TIME_FROM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeFrom;
    @Column(name = "TIME_TO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeTo;
    @Size(max = 300)
    @Column(name = "OWNER")
    private String owner;
    @Size(max = 512)
    @Column(name = "EXT_PROPS")
    private String extProps;
    @Size(max = 512)
    @Column(name = "NOTE")
    private String note;

    public TActivity() {
    }

    public TActivity(Integer id) {
        this.id = id;
    }

    public TActivity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getActivityState() {
        return activityState;
    }

    public void setActivityState(Integer activityState) {
        this.activityState = activityState;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Date timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Date getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Date timeTo) {
        this.timeTo = timeTo;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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
        if (!(object instanceof TActivity)) {
            return false;
        }
        TActivity other = (TActivity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ticket.entity.TActivity[ id=" + id + " ]";
    }
    
}
