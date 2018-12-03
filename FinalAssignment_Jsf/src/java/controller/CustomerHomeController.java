/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import interfaces.CustomerInterfaceRemote;
import interfaces.GuestInterfaceRemote;
import entities.Customers;
import entities.Reservation;
import entities.Rooms;
import entities.RoomTypes;
import entities.Transactions;

/**
 *
 * @author Sunil
 */
@ManagedBean
@SessionScoped
public class CustomerHomeController implements Serializable {
    

    private static final long serialVersionUID = 1L;
    
    @EJB
    private GuestInterfaceRemote guestSessionBean;
    @EJB
    private CustomerInterfaceRemote customerSessionBean;
    
    
    private String errormsg = "";
    private String passmsg = "";
    private Date curdate = new Date();
    
    private Customers customers = new Customers();
    private Rooms rooms = new Rooms();
    private RoomTypes roomtypes = new RoomTypes();
    private Reservation reservations = new Reservation();
    private Transactions transaction = new Transactions();
    
    private int qty = 0;
    private String custname = "";
    
    @ManagedProperty(value="#{roomcartController}")
    private RoomcartController roomcartController;
    
    
    public CustomerHomeController() {
    }
    
    public Rooms getRooms() {
        return rooms;
    }

    public void setRooms(Rooms rooms) {
        this.rooms = rooms;
    }

    public RoomTypes getRoomtypes() {
        return roomtypes;
    }

    public void setRoomtypes(RoomTypes roomtypes) {
        this.roomtypes = roomtypes;
    }
    
    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public Reservation getReservations() {
        return reservations;
    }

    public void setReservations(Reservation reservations) {
        this.reservations = reservations;
    }
    
     public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    public String getPassmsg() {
        return passmsg;
    }
    
    public void setPassmsg(String passmsg) {
        this.passmsg = passmsg;
    }
    
    public Date getCurdate() {
        return curdate;
    }

    public void setCurdate(Date curdate) {
        this.curdate = curdate;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public RoomcartController getRoomcartController() {
        return roomcartController;
    }

    public void setRoomcartController(RoomcartController roomcartController) {
        this.roomcartController = roomcartController;
    }

    public Transactions getTransaction() {
        return transaction;
    }

    public void setTransaction(Transactions transaction) {
        this.transaction = transaction;
    }

    public CustomerInterfaceRemote getCustomerSessionBean() {
        return customerSessionBean;
    }

    public void setCustomerSessionBean(CustomerInterfaceRemote customerSessionBean) {
        this.customerSessionBean = customerSessionBean;
    }

    public GuestInterfaceRemote getGuestSessionBean() {
        return guestSessionBean;
    }

    public void setGuestSessionBean(GuestInterfaceRemote guestSessionBean) {
        this.guestSessionBean = guestSessionBean;
    }
    
    
    
    
      
    public String addCustomer(){
        passmsg = "Signup is successful! You can login";
        customerSessionBean.createCustomer(customers);
        return "customerloginpage?faces-redirect=true";
    }
    
    public Collection allRoomDet(){
       return guestSessionBean.getAvailableRooms();
    }
    
    public Collection<RoomTypes> allRoomTypes(){
        return guestSessionBean.getAllRoomTypes();
    }
    
    public Collection<Rooms> allRooms(){
        return customerSessionBean.getAllRooms();
    }
    
    public Collection<Rooms> allRoomsByType(int typeId){
        return customerSessionBean.getRoomsByType(typeId);
    }
    
    //referred a part of the code from stackoverflow
    public String checkAvailabilty(int rid){
        Date startdate = reservations.getStartdate();
        Date enddate = reservations.getEnddate();
        int typeId = rid+1;
        int quantity = qty;
        
        if(quantity>0 && quantity<=10){
            boolean check = guestSessionBean.checkAvailableRooms(startdate, enddate, typeId, quantity);
            if(check == true){
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "This room booking is currently available. Login and book now", ""));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sorry currently this is full", ""));
            }
        }else{
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Kindly enter a quantity between 1 to 10", ""));
        }
        reservations.setStartdate(null);
        reservations.setEnddate(null);
        qty = 0;
        return "roombookingpage?faces-redirect=true";
    }
    
    public String login(){
        String response = "";
        int custid = customerSessionBean.customerLogin(customers.getUsername(), customers.getPassword());
        if(custid > 0){
            customers.setCustomerid(custid);
            custname = customerSessionBean.getCustname(custid);
            response = "customerhomepage?faces-redirect=true";
        }
        else{
            errormsg = "Invalid Credentials: Please Try Again";
            response = "customerloginpage?faces-redirect=true";
        }
        return response;
        
    }
    
    public String logout(){
        
        return "customerloginpage?faces-redirect=true";
    }
    
    public String checkAvailableByRoomNo(int typeId,int roomno){
        Date startdate = reservations.getStartdate();
        Date enddate = reservations.getEnddate();
        
        boolean check = customerSessionBean.checkAvailabilityWithRoomNo(startdate, enddate, roomno);
        if(check == true){    
            roomcartController.addToCart(customerSessionBean.getRoomIdByNo(roomno), 
                    customerSessionBean.getRoomTypeById(typeId),
                    roomno, startdate, enddate, customerSessionBean.getPriceById(typeId), 
                    reservations.getSpecialrequest());
            reservations.setSpecialrequest(null);
        }else{
            FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "This room is already booked in these dates", ""));
        }
        return "customerhomepage?faces-redirect=true";
    }
    
    public void save(){
        for(RoomcartController r : roomcartController.viewCart()){
            customerSessionBean.createReservation(customers.getCustomerid(), r.getRoomid(), r.getStartdate(), r.getEnddate(), r.getSpecialrequest()); 
        }
        customerSessionBean.createTransaction(customers.getCustomerid(), transaction.getPaymenttype(), transaction.getPrice(), 
                transaction.getCardholder(), transaction.getTransactionname(), transaction.getCardno(), transaction.getExpirydate());
        
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Congratulations,\n" + custname +"\nBooking is successful"));
        
        transaction.setPaymenttype("");
        transaction.setPrice(0);
        transaction.setCardholder("");
        transaction.setTransactionname("");
        transaction.setCardno("");
        transaction.setExpirydate("");
        
        roomcartController.emptyCart();
    }
    
    public Collection reservationByCust(){
        return customerSessionBean.getReservationsByCustomer(customers.getCustomerid());
    }
}
