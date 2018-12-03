/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementation;

import java.util.Date;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import entities.Customers;
import entities.RoomTypes;
import entities.Reservation;
import entities.Rooms;
import entities.Transactions;
import interfaces.CustomerInterfaceRemote;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Sunil
 */

//referred from various sources including weekly tasks and stackoverflow
@ManagedBean(name = "CustomerInterfaceImpl")
@Stateless
@TransactionManagement(value=TransactionManagementType.CONTAINER)
public class CustomerInterfaceImpl implements CustomerInterfaceRemote {
    
    @javax.persistence.PersistenceContext(unitName="FinalAssignment_EjbPU")
    private EntityManager em;
  

    @Override
    public void createCustomer(Object cust) {
        em.persist(cust);
    }

    @Override
    public void editCustomer(int customerid, String firstname, String lastname, String email, 
            String phone, String username, String password) {
        Customers cust = em.find(Customers.class, customerid);
        
        cust.setFirstname(firstname);
        cust.setLastname(lastname);
        cust.setEmail(email);
        cust.setPhone(phone);
        cust.setUsername(username);
        cust.setPassword(password);
        
        em.merge(cust);
    }

    @Override
    public void deleteCustomer(int customerid) {
        Customers cust = em.find(Customers.class, customerid);
        em.remove(cust);
    }

    @Override
    public void createReservation(int customerid, int roomid, Date startdate, Date enddate, String specialrequest) {
        
        Reservation reserve = new Reservation();
        Customers cust = em.find(Customers.class, customerid);
        Rooms room = em.find(Rooms.class, roomid);
        
        reserve.setCustomerid(cust);
        reserve.setRoomid(room);
        reserve.setStartdate(startdate);
        reserve.setEnddate(enddate);
        reserve.setSpecialrequest(specialrequest);
        
        em.persist(reserve);
    }    

    @Override
    public int customerLogin(String username, String password) {
        int custid = 0;
        String select = "SELECT ua FROM Customers ua WHERE ua.username=:username and ua.password=:password";
        Query query = em.createQuery(select);
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<Customers> results = (List<Customers>)query.getResultList();
        
        if(!results.isEmpty()){
            for(Customers c: results){
                custid = c.getCustomerid();
            }
        }
        return custid;
    }
     
    @Override
    public void createTransaction(int customerid, String paymenttype, double price, String cardholder, String transactionname, String cardno, String expirydate) {
        Transactions trans = new Transactions();
        Customers customers = em.find(Customers.class, customerid);
        
        trans.setCustomerid(customers);
        trans.setPaymenttype(paymenttype);
        trans.setPrice(price);
        trans.setCardholder(cardholder);
        trans.setTransactionname(transactionname);
        trans.setCardno(cardno);
        trans.setExpirydate(expirydate);
        
        em.persist(trans);
    }

    @Override
    public String getCustname(int customerid) {
        String custname="";
        String select = "SELECT c FROM Customers c WHERE c.customerid = :customerid";
        Query query = em.createQuery(select);
        query.setParameter("customerid", customerid);
        List<Customers> results = (List<Customers>)query.getResultList();
        
        if(!results.isEmpty()){
            for(Customers cs: results){
                custname = cs.getFirstname()+" "+cs.getLastname();
            }
        }
        return custname;
    }

    @Override
    public Collection getAllRooms() {
        Query query = em.createQuery("SELECT rm FROM Rooms rm");
        return (Collection<Rooms>) query.getResultList();
    }

    @Override
    public Collection getRoomsByType(int typeId) {
        Query query = em.createQuery("SELECT r FROM Rooms r JOIN r.roomStatusId rs WHERE r.typeId = :typeId AND rs.roomStatusId=1");
        RoomTypes roomtypes = em.find(RoomTypes.class, typeId);
        query.setParameter("typeId", roomtypes);
        return (Collection<Rooms>) query.getResultList();
    }

    @Override
    public boolean checkAvailabilityWithRoomNo(Date startdate, Date enddate, int roomno) {
        boolean flag = false;
        Query query = em.createQuery("SELECT COUNT(rs) FROM Reservation rs JOIN rs.roomid rm WHERE "
                + "rm.roomno = :roomno AND ((:startdate BETWEEN rs.startdate AND rs.enddate) OR "
                + "(:enddate BETWEEN rs.startdate AND rs.enddate) OR (rs.startdate < :enddate AND rs.enddate > :startdate))");
        
        query.setParameter("startdate", startdate);
        query.setParameter("enddate", enddate);
        query.setParameter("roomno", roomno);
        
        long count = (long) query.getSingleResult();
        
        if(count==0){
            flag = true;
        }
        return flag;
    }

    @Override
    public String getRoomTypeById(int typeId) {
        Query query = em.createQuery("SELECT rt.roomtype FROM RoomTypes rt WHERE rt.typeId=:typeId");
        query.setParameter("typeId", typeId);
        return (String)query.getSingleResult();
    }

    @Override
    public Double getPriceById(int typeId) {
        Query query = em.createQuery("SELECT rt.price FROM RoomTypes rt WHERE rt.typeId=:typeId");
        query.setParameter("typeId", typeId);
        return (double)query.getSingleResult();
    }

    @Override
    public Integer getRoomIdByNo(int roomno) {
        Query query = em.createQuery("SELECT rm.roomid FROM Rooms rm WHERE rm.roomno=:roomno");
        query.setParameter("roomno", roomno);
        return (int)query.getSingleResult();
    }

    @Override
    public Collection getReservationsByCustomer(int customerid) {
        Query query = em.createQuery("SELECT rt.roomtype, rm.roomno,rs.startdate,rs.enddate,rs.specialrequest "
                + "FROM Reservation rs, Rooms rm, RoomTypes rt WHERE rs.roomid = rm AND rm.typeId = rt AND rs.customerid = :customerid");
        Customers customers = em.find(Customers.class, customerid);
        query.setParameter("customerid", customers);
        return (Collection) query.getResultList();
    }
    
    
    
 
    
}
