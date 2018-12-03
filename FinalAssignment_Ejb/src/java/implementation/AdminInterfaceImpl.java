/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import entities.Admin;
import entities.Reservation;
import entities.Rooms;
import entities.RoomStatus;
import entities.RoomTypes;
import interfaces.AdminInterfaceRemote;

/**
 *
 * @author Sunil
 */

//referred from various sources including weekly tasks and stackoverflow
@Stateless
@TransactionManagement(value=TransactionManagementType.CONTAINER)
public class AdminInterfaceImpl implements AdminInterfaceRemote {
    
    @javax.persistence.PersistenceContext(unitName="FinalAssignment_EjbPU")
    private EntityManager em;


    @Override
    public void createAdmin(String username, String password, String status) {
        Admin admin = new Admin();
        
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setStatus(status);
        
        em.merge(admin);
    }

    @Override
    public void editAdmin(int id, String username, String password, String status) {
        Admin admin = em.find(Admin.class, id);
        
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setStatus(status);
        
        em.merge(admin);
    }

    @Override
    public int adminLogin(String username, String password) {
        int adminid = 0;
        String select = "SELECT ua FROM Admin ua WHERE ua.username=:username and ua.password=:password";
        Query query = em.createQuery(select);
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<Admin> results = (List<Admin>)query.getResultList();
        
        if(!results.isEmpty()){
            for(Admin c: results){
                adminid = c.getId();
            }
        }
        return adminid;
    }

    @Override
    public String getAdminNameById(int adminid) {
        String adminname="";
        String select = "SELECT c FROM Admin c WHERE c.id = :adminid";
        Query query = em.createQuery(select);
        query.setParameter("adminid", adminid);
        List<Admin> results = (List<Admin>)query.getResultList();
        
        if(!results.isEmpty()){
            for(Admin cs: results){
                adminname = cs.getUsername();
            }
        }
        return adminname;
    }
    
    
    @Override
    public void addRoom(int typeId, int roomno, int floorno, int roomStatusId) {
        Rooms room = new Rooms();
        RoomTypes roomtype = em.find(RoomTypes.class, typeId);
        RoomStatus roomstatus = em.find(RoomStatus.class, roomStatusId);
        
        room.setTypeId(roomtype);
        room.setRoomno(roomno);
        room.setRoomno(floorno);
        room.setRoomStatusId(roomstatus);
        
        em.persist(room);
    }

    @Override
    public void editRoom(int roomid, int typeId, int roomno, int floorno, int roomStatusId) {
        Rooms room = em.find(Rooms.class, roomid);
        RoomTypes roomtype = em.find(RoomTypes.class, typeId);
        RoomStatus roomstatus = em.find(RoomStatus.class, roomStatusId);
        
        room.setTypeId(roomtype);
        room.setRoomno(roomno);
        room.setFloorno(floorno);
        room.setRoomStatusId(roomstatus);
        
        em.persist(room);
    }

    @Override
    public void deleteRoom(int roomid) {
        Rooms room = em.find(Rooms.class, roomid);
        em.remove(room);
    }

    

    @Override
    public Collection allReservations() {
        Query query = em.createQuery("SELECT cs.firstname,cs.lastname,rm.roomno,rs.startdate,rs.enddate,rs.specialrequest "
                + "FROM Reservation rs, Customers cs, Rooms rm WHERE rs.customerid = cs AND rs.roomid = rm");
        return (Collection) query.getResultList();
    }

    @Override
    public Collection allFloors() {
        Query query = em.createQuery("SELECT f.floorno FROM Rooms f GROUP BY f.floorno");
        return (Collection) query.getResultList();
    }
    
    @Override
    public Collection allReservationsByFloor(int floorno) {
        Query query = em.createQuery("SELECT cs.firstname,cs.lastname,rm.roomno,rs.startdate,rs.enddate,rs.specialrequest "
                + "FROM Reservation rs, Customers cs, Rooms rm WHERE rs.customerid = cs AND rs.roomid = rm AND rm.floorno=:floorno");
        query.setParameter("floorno", floorno);
        return (Collection) query.getResultList();
    }

    @Override
    public Collection allReservationsByRoomTypeId(int typeId) {
        Query query = em.createQuery("SELECT cs.firstname,cs.lastname,rm.roomno,rs.startdate,rs.enddate,rs.specialrequest "
                + "FROM Reservation rs, Customers cs, Rooms rm WHERE rs.customerid = cs AND rs.roomid = rm AND rm.typeId=:type_id");
        RoomTypes roomtypes = em.find(RoomTypes.class, typeId);
        query.setParameter("type_id", roomtypes);
        return (Collection) query.getResultList();
    }
    
    @Override
    public Collection allReservationsByDates(Date startdate, Date enddate) {
        Query query = em.createQuery("SELECT cs.firstname,cs.lastname,rm.roomno,rs.startdate,rs.enddate,rs.specialrequest "
                + "FROM Reservation rs, Customers cs, Rooms rm WHERE rs.customerid = cs AND rs.roomid = rm AND "
                + "rs.startdate>=:startdate AND rs.enddate<=:enddate");
        query.setParameter("startdate", startdate);
        query.setParameter("enddate", enddate);
        return (Collection) query.getResultList();
    }

    @Override
    public Collection allRoomTypes() {
        Query query = em.createQuery("SELECT rt FROM RoomTypes rt");
        return (Collection) query.getResultList();
    }

    @Override
    public Integer roomIdFromPrice(double price) {
        Query query = em.createQuery("SELECT rt.typeId FROM RoomTypes rt WHERE rt.price = :price");
        query.setParameter("price", price);
        return (int)query.getSingleResult();
    }

    

    @Override
    public Collection allRooms() {
        Query query = em.createQuery("SELECT rm.roomno, rt.roomtype, rm.floorno, rs.status, rm.roomid FROM Rooms rm, "
                + "RoomTypes rt, RoomStatus rs WHERE rm.typeId = rt AND rm.roomStatusId = rs");
        return (Collection) query.getResultList();
    }

    @Override
    public Collection allRoomStatus() {
        Query query = em.createQuery("SELECT rs FROM RoomStatus rs");
        return (Collection) query.getResultList();
    }

    @Override
    public Collection roomById(int roomid) {
        Query query = em.createQuery("SELECT rm FROM Rooms rm WHERE rm.roomid=:roomid");
        query.setParameter("roomid", roomid);
        return (Collection<Rooms>) query.getResultList();
    }
    
    

    @Override
    public List<Rooms> getAllRooms() {
        return em.createNamedQuery("Rooms.findAll").getResultList();
    }

    @Override
    public Rooms getRoomById(int roomid) {
//        return (Rooms)em.find(Rooms.class, roomid);
        return (Rooms) em.createNamedQuery("Rooms.findByRoomid").setParameter("roomid", roomid).getSingleResult();
    }

    @Override
    public List<RoomTypes> getAllRoomTypes() {
        return em.createNamedQuery("RoomTypes.findAll").getResultList();
    }

    @Override
    public List<RoomStatus> getAllRoomStatus() {
        return em.createNamedQuery("RoomStatus.findAll").getResultList();
    }

    @Override
    public RoomStatus getRoomstatusByStatus(String status) {
        return (RoomStatus) em.createNamedQuery("RoomStatus.findByStatus").setParameter("status", status).getSingleResult();
    }

    @Override
    public RoomTypes getRoomtypesByType(String type) {
        return (RoomTypes) em.createNamedQuery("RoomTypes.findByRoomtype").setParameter("roomtype", type).getSingleResult();
    }

    
    @Override
    public List<Reservation> getReservationByFloor(int floorno) {
        Query query = em.createNamedQuery("Reservation.findAll");
        List<Reservation> result = new ArrayList();
        List<Reservation> reservations = query.getResultList();
        for(Reservation reservation : reservations){
            if(reservation.getRoomid().getFloorno()== floorno){
                result.add(reservation);
            }
        }
        return result;
    }

    @Override
    public List<Reservation> getReservationByDate(Date startdate, Date enddate) {
        Query query =  em.createQuery("SELECT r FROM Reservation r WHERE r.startdate >= :startdate and r.enddate <= :enddate")
                    .setParameter("startdate", startdate)
                    .setParameter("enddate", enddate);
        List<Reservation> reservations = query.getResultList();
        List<Reservation> result = new ArrayList();
        for(Reservation reservation : reservations){
            result.add(reservation);
        }
        return result;
        
    }

    @Override
    public List<Reservation> getReservationByPrice(double startPrice, double endPrice) {
        Query query = em.createNamedQuery("Reservation.findAll");
        List<Reservation> result = new ArrayList();
        List<Reservation> reservations = query.getResultList();
        for(Reservation reservation : reservations){
            if(reservation.getRoomid().getTypeId().getPrice() >= startPrice 
                    && reservation.getRoomid().getTypeId().getPrice() <= endPrice){
                result.add(reservation);
            }
        }
        return result;
    }
    
}
