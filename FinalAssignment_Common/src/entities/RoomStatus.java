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
 * @author Batman
 */
@Entity
@Table(name = "ROOM_STATUS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RoomStatus.findAll", query = "SELECT r FROM RoomStatus r")
    , @NamedQuery(name = "RoomStatus.findByRoomStatusId", query = "SELECT r FROM RoomStatus r WHERE r.roomStatusId = :roomStatusId")
    , @NamedQuery(name = "RoomStatus.findByStatus", query = "SELECT r FROM RoomStatus r WHERE r.status = :status")})
public class RoomStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ROOM_STATUS_ID")
    private Integer roomStatusId;
    @Basic(optional = false)
    @Column(name = "STATUS")
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomStatusId", fetch = FetchType.EAGER)
    private Collection<Rooms> roomsCollection;

    public RoomStatus() {
    }

    public RoomStatus(Integer roomStatusId) {
        this.roomStatusId = roomStatusId;
    }

    public RoomStatus(Integer roomStatusId, String status) {
        this.roomStatusId = roomStatusId;
        this.status = status;
    }

    public Integer getRoomStatusId() {
        return roomStatusId;
    }

    public void setRoomStatusId(Integer roomStatusId) {
        this.roomStatusId = roomStatusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        hash += (roomStatusId != null ? roomStatusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoomStatus)) {
            return false;
        }
        RoomStatus other = (RoomStatus) object;
        if ((this.roomStatusId == null && other.roomStatusId != null) || (this.roomStatusId != null && !this.roomStatusId.equals(other.roomStatusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.RoomStatus[ roomStatusId=" + roomStatusId + " ]";
    }
    
}
