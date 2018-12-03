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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import interfaces.AdminInterfaceRemote;
import entities.Admin;
import entities.Customers;
import entities.Reservation;
import entities.Rooms;
import entities.RoomTypes;
import entities.RoomStatus;

/**
 *
 * @author Sunil
 */

@ManagedBean
@SessionScoped
public class AdminHomeController implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @EJB
    private AdminInterfaceRemote adminSessionBean;
    
    
    private String adminname = "";
    private String errormsg = "";
    private String passmsg = "";
    
    
    private Admin admin = new Admin();
    private Reservation reservations = new Reservation();
    private Customers customers = new Customers();
    private Rooms rooms = new Rooms();
    private RoomTypes roomtypes = new RoomTypes();
    private RoomStatus roomstatus = new RoomStatus();
    
    public AdminHomeController(){
    }

    public AdminInterfaceRemote getAdminSessionBean() {
        return adminSessionBean;
    }
    

    public void setAdminSessionBean(AdminInterfaceRemote adminSessionBean) {
        this.adminSessionBean = adminSessionBean;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public Reservation getReservations() {
        return reservations;
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
    
    
    
    

    public void setReservations(Reservation reservations) {
        this.reservations = reservations;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
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

    public RoomStatus getRoomstatus() {
        return roomstatus;
    }

    public void setRoomstatus(RoomStatus roomstatus) {
        this.roomstatus = roomstatus;
    }
    
    
    
    
    
    public String login(){
        String response = "";
        int adminid = adminSessionBean.adminLogin(admin.getUsername(), admin.getPassword());
        if(adminid > 0){
            admin.setId(adminid);
            rooms.setRoomid(0);
            adminname = adminSessionBean.getAdminNameById(adminid);
            response = "adminhomepage?faces-redirect=true";
        }
        else{
            errormsg = "Invalid Credentials: Please Try Again";
            response = "adminloginpage?faces-redirect=true";
        }
        return response;    
    }
    
    public String logout(){
        reservations.setStartdate(null);
        reservations.setEnddate(null);
        roomtypes.setPrice(0);
        rooms.setFloorno(0);
        return "adminloginpage?faces-redirect=true";
    }
    
    
    
    //referred a part from sample codes from github
    public Collection viewAllReservations(){
        Date startdate = reservations.getStartdate();
        Date enddate = reservations.getEnddate();
        int floor = rooms.getFloorno();
        double price = roomtypes.getPrice();
        
        
        if(startdate==null && enddate==null && floor!=0 && price==0){
            return adminSessionBean.allReservationsByFloor(floor);
        }
        else if(startdate==null && enddate==null && floor==0 && price!=0){
            int room_type_id = adminSessionBean.roomIdFromPrice(price);
            return adminSessionBean.allReservationsByRoomTypeId(room_type_id);
        }
        else if(startdate!=null && enddate!=null && floor==0 && price==0){
            return adminSessionBean.allReservationsByDates(startdate, enddate);
        }
        else{
            return adminSessionBean.allReservations();
        }
    }
    
    public void reset1(){
        roomtypes.setPrice(0);
        rooms.setFloorno(0);
    }
    public void reset2(){
        reservations.setStartdate(null);
        reservations.setEnddate(null);
        roomtypes.setPrice(0);
    }
    public void reset3(){
        reservations.setStartdate(null);
        reservations.setEnddate(null);
        rooms.setFloorno(0);
    }
    
    
}
