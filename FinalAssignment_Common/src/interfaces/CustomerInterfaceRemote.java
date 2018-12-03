/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.Collection;
import java.util.Date;
import javax.ejb.Remote;

/**
 *
 * @author Sunil
 */
@Remote
public interface CustomerInterfaceRemote {

    public void createCustomer(Object cust);

    public void editCustomer(int customerid, String firstname, String lastname, String email, String phone, String username, String password);

    public void deleteCustomer(int customerid);
    
    public int customerLogin(String username, String password);

    public String getCustname(int customerid);

    public void createReservation(int customerid, int roomid, Date startdate, Date enddate, String specialrequest);
    
    public Collection getReservationsByCustomer(int customerid);

    public void createTransaction(int customerid, String paymenttype, double price, String cardholder, String transactionname, String cardno, String expirydate);

    public boolean checkAvailabilityWithRoomNo(Date startdate, Date enddate, int roomno);

    public String getRoomTypeById(int typeId);
    
    public Collection getAllRooms();

    public Collection getRoomsByType(int typeId);

    public Double getPriceById(int typeId);

    public Integer getRoomIdByNo(int roomno);
    
}
