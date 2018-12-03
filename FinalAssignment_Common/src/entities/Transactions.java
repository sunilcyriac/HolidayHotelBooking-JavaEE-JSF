/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Batman
 */
@Entity
@Table(name = "TRANSACTIONS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transactions.findAll", query = "SELECT t FROM Transactions t")
    , @NamedQuery(name = "Transactions.findByTransactionid", query = "SELECT t FROM Transactions t WHERE t.transactionid = :transactionid")
    , @NamedQuery(name = "Transactions.findByPaymenttype", query = "SELECT t FROM Transactions t WHERE t.paymenttype = :paymenttype")
    , @NamedQuery(name = "Transactions.findByPrice", query = "SELECT t FROM Transactions t WHERE t.price = :price")
    , @NamedQuery(name = "Transactions.findByCardholder", query = "SELECT t FROM Transactions t WHERE t.cardholder = :cardholder")
    , @NamedQuery(name = "Transactions.findByTransactionname", query = "SELECT t FROM Transactions t WHERE t.transactionname = :transactionname")
    , @NamedQuery(name = "Transactions.findByCardno", query = "SELECT t FROM Transactions t WHERE t.cardno = :cardno")
    , @NamedQuery(name = "Transactions.findByExpirydate", query = "SELECT t FROM Transactions t WHERE t.expirydate = :expirydate")})
public class Transactions implements Serializable {

    private static final long serialVersionUID = 1L;
    public static String findAll;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TRANSACTIONID")
    private Integer transactionid;
    @Basic(optional = false)
    @Column(name = "PAYMENTTYPE")
    private String paymenttype;
    @Basic(optional = false)
    @Column(name = "PRICE")
    private double price;
    @Column(name = "CARDHOLDER")
    private String cardholder;
    @Column(name = "TRANSACTIONNAME")
    private String transactionname;
    @Column(name = "CARDNO")
    private String cardno;
    @Column(name = "EXPIRYDATE")
    private String expirydate;
    @JoinColumn(name = "CUSTOMERID", referencedColumnName = "CUSTOMERID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Customers customerid;

    public Transactions() {
    }

    public Transactions(Integer transactionid) {
        this.transactionid = transactionid;
    }

    public Transactions(Integer transactionid, String paymenttype, double price) {
        this.transactionid = transactionid;
        this.paymenttype = paymenttype;
        this.price = price;
    }

    public Integer getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(Integer transactionid) {
        this.transactionid = transactionid;
    }

    public String getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }

    public String getTransactionname() {
        return transactionname;
    }

    public void setTransactionname(String transactionname) {
        this.transactionname = transactionname;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(String expirydate) {
        this.expirydate = expirydate;
    }

    public Customers getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Customers customerid) {
        this.customerid = customerid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transactionid != null ? transactionid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transactions)) {
            return false;
        }
        Transactions other = (Transactions) object;
        if ((this.transactionid == null && other.transactionid != null) || (this.transactionid != null && !this.transactionid.equals(other.transactionid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Transactions[ transactionid=" + transactionid + " ]";
    }
    
}
