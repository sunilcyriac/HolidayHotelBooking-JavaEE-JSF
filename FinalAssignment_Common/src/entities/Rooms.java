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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ROOMS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rooms.findAll", query = "SELECT r FROM Rooms r")
    , @NamedQuery(name = "Rooms.findByRoomid", query = "SELECT r FROM Rooms r WHERE r.roomid = :roomid")
    , @NamedQuery(name = "Rooms.findByRoomno", query = "SELECT r FROM Rooms r WHERE r.roomno = :roomno")
    , @NamedQuery(name = "Rooms.findByFloorno", query = "SELECT r FROM Rooms r WHERE r.floorno = :floorno")})
public class Rooms implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ROOMID")
    private Integer roomid;
    @Basic(optional = false)
    @Column(name = "ROOMNO")
    private int roomno;
    @Basic(optional = false)
    @Column(name = "FLOORNO")
    private int floorno;
    @JoinColumn(name = "ROOM_STATUS_ID", referencedColumnName = "ROOM_STATUS_ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private RoomStatus roomStatusId;
    @JoinColumn(name = "TYPE_ID", referencedColumnName = "TYPE_ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private RoomTypes typeId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomid", fetch = FetchType.EAGER)
    private Collection<Reservation> reservationCollection;

    public Rooms() {
    }

    public Rooms(Integer roomid) {
        this.roomid = roomid;
    }

    public Rooms(Integer roomid, int roomno, int floorno) {
        this.roomid = roomid;
        this.roomno = roomno;
        this.floorno = floorno;
    }

    public Integer getRoomid() {
        return roomid;
    }

    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
    }

    public int getRoomno() {
        return roomno;
    }

    public void setRoomno(int roomno) {
        this.roomno = roomno;
    }

    public int getFloorno() {
        return floorno;
    }

    public void setFloorno(int floorno) {
        this.floorno = floorno;
    }

    public RoomStatus getRoomStatusId() {
        return roomStatusId;
    }

    public void setRoomStatusId(RoomStatus roomStatusId) {
        this.roomStatusId = roomStatusId;
    }

    public RoomTypes getTypeId() {
        return typeId;
    }

    public void setTypeId(RoomTypes typeId) {
        this.typeId = typeId;
    }

    @XmlTransient
    public Collection<Reservation> getReservationCollection() {
        return reservationCollection;
    }

    public void setReservationCollection(Collection<Reservation> reservationCollection) {
        this.reservationCollection = reservationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomid != null ? roomid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rooms)) {
            return false;
        }
        Rooms other = (Rooms) object;
        if ((this.roomid == null && other.roomid != null) || (this.roomid != null && !this.roomid.equals(other.roomid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Rooms[ roomid=" + roomid + " ]";
    }
    
}
