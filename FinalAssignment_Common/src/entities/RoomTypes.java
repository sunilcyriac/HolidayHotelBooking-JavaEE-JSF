/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sunil
 * 
 * 
 */
@Entity
@Table(name = "ROOM_TYPES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RoomTypes.findAll", query = "SELECT r FROM RoomTypes r")
    , @NamedQuery(name = "RoomTypes.findByTypeId", query = "SELECT r FROM RoomTypes r WHERE r.typeId = :typeId")
    , @NamedQuery(name = "RoomTypes.findByRoomtype", query = "SELECT r FROM RoomTypes r WHERE r.roomtype = :roomtype")
    , @NamedQuery(name = "RoomTypes.findByRoomdetail", query = "SELECT r FROM RoomTypes r WHERE r.roomdetail = :roomdetail")
    , @NamedQuery(name = "RoomTypes.findByPrice", query = "SELECT r FROM RoomTypes r WHERE r.price = :price")})
public class RoomTypes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TYPE_ID")
    private Integer typeId;
    @Basic(optional = false)
    @Column(name = "ROOMTYPE")
    private String roomtype;
    @Basic(optional = false)
    @Column(name = "ROOMDETAIL")
    private String roomdetail;
    @Basic(optional = false)
    @Column(name = "PRICE")
    private double price;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeId", fetch = FetchType.EAGER)
    private Collection<Rooms> roomsCollection;

    public RoomTypes() {
    }

    public RoomTypes(Integer typeId) {
        this.typeId = typeId;
    }

    public RoomTypes(Integer typeId, String roomtype, String roomdetail, double price) {
        this.typeId = typeId;
        this.roomtype = roomtype;
        this.roomdetail = roomdetail;
        this.price = price;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public String getRoomdetail() {
        return roomdetail;
    }

    public void setRoomdetail(String roomdetail) {
        this.roomdetail = roomdetail;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @XmlTransient
    public Collection<Rooms> getRoomsCollection() {
        return roomsCollection;
    }

    public void setRoomsCollection(Collection<Rooms> roomsCollection) {
        this.roomsCollection = roomsCollection;
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
        if (!(object instanceof RoomTypes)) {
            return false;
        }
        RoomTypes other = (RoomTypes) object;
        if ((this.typeId == null && other.typeId != null) || (this.typeId != null && !this.typeId.equals(other.typeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.RoomTypes[ typeId=" + typeId + " ]";
    }
    
}
