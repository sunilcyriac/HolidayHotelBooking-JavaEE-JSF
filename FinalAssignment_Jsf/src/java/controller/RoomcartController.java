/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Sunil
 */
//referred this part of the code from various website like github and stackoverflow

@ManagedBean
@SessionScoped
public class RoomcartController implements Serializable{
    
    private int roomid;
    private String roomtype;
    private int roomno;
    private Date startdate;
    private Date enddate;
    private double price;
    private String specialrequest;
    
    private RoomcartController rc;
    private List<RoomcartController> list = new ArrayList<RoomcartController>();
    
    private double total;
    
    public RoomcartController(){
        
    }

    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public int getRoomno() {
        return roomno;
    }

    public void setRoomno(int roomno) {
        this.roomno = roomno;
    }
    
    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public Date getStartdate() {
        return startdate;
    }
            
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSpecialrequest() {
        return specialrequest;
    }

    public void setSpecialrequest(String specialrequest) {
        this.specialrequest = specialrequest;
    }

    public RoomcartController getRc() {
        return rc;
    }

    public void setRc(RoomcartController rc) {
        this.rc = rc;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    public String frmtStartDate(){
        return new SimpleDateFormat("dd-MMM").format(startdate);
    } 
    public String frmtEndDate(){
        return new SimpleDateFormat("dd-MMM").format(enddate);
    } 

    public List<RoomcartController> getList() {
        return list;
    }

    public void setList(List<RoomcartController> list) {
        this.list = list;
    }
    
    //referred from github
    public void addToCart(int roomid, String roomtype, int roomno, 
            Date startdate, Date enddate, double price, String specialrequest){
        
            rc = new RoomcartController();
            rc.setRoomid(roomid);
            rc.setRoomtype(roomtype);
            rc.setRoomno(roomno);
            rc.setStartdate(startdate);
            rc.setEnddate(enddate);
            rc.setPrice(price);
            rc.setSpecialrequest(specialrequest);
            list.add(rc);  
        
    }
    public List<RoomcartController> viewCart(){
        return list;
    }
    public void removeFromCart(int index)
    {
        list.remove(index);
    }
    public double totalAmount(){
        total = 0;
        for (RoomcartController l : list) {
            total += l.getPrice();
        }
        total += total*0.10;
        return total;
    }
    public void emptyCart(){
        list = new ArrayList<RoomcartController>();
    }
}
