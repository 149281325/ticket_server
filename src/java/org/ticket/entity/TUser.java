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
@Table(name = "t_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TUser.findAll", query = "SELECT t FROM TUser t"),
    @NamedQuery(name = "TUser.findById", query = "SELECT t FROM TUser t WHERE t.id = :id"),
    @NamedQuery(name = "TUser.findByName", query = "SELECT t FROM TUser t WHERE t.name = :name"),
    @NamedQuery(name = "TUser.findByPassword", query = "SELECT t FROM TUser t WHERE t.password = :password"),
    @NamedQuery(name = "TUser.findByNick", query = "SELECT t FROM TUser t WHERE t.nick = :nick"),
    @NamedQuery(name = "TUser.findByUserType", query = "SELECT t FROM TUser t WHERE t.userType = :userType"),
    @NamedQuery(name = "TUser.findByUserState", query = "SELECT t FROM TUser t WHERE t.userState = :userState"),
    @NamedQuery(name = "TUser.findByBalance", query = "SELECT t FROM TUser t WHERE t.balance = :balance"),
    @NamedQuery(name = "TUser.findByExtProps", query = "SELECT t FROM TUser t WHERE t.extProps = :extProps"),
    @NamedQuery(name = "TUser.findByNote", query = "SELECT t FROM TUser t WHERE t.note = :note")})
public class TUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "PASSWORD")
    private String password;
    @Size(max = 30)
    @Column(name = "NICK")
    private String nick;
    @Column(name = "USER_TYPE")
    private Integer userType;
    @Column(name = "USER_STATE")
    private Integer userState;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "BALANCE")
    private Double balance;
    @Size(max = 512)
    @Column(name = "EXT_PROPS")
    private String extProps;
    @Size(max = 512)
    @Column(name = "NOTE")
    private String note;

    public TUser() {
    }

    public TUser(Integer id) {
        this.id = id;
    }

    public TUser(Integer id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
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
        if (!(object instanceof TUser)) {
            return false;
        }
        TUser other = (TUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ticket.entity.TUser[ id=" + id + " ]";
    }
    
}
