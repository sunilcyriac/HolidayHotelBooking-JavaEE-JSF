/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import entities.Reservation;
import entities.Rooms;
import entities.RoomStatus;
import entities.RoomTypes;

/**
 *
 * @author Sunil
 */
@Remote
public interface AdminInterfaceRemote {

    public void createAdmin(String username, String password, String status);

    public void editAdmin(int id, String username, String password, String status);

    public int adminLogin(String username, String password);

    public String getAdminNameById(int adminid);
    
    public void addRoom(int typeId, int roomno, int floorno, int roomStatusId);

    public void editRoom(int roomid, int typeId, int roomno, int floorno, int roomStatusId);

    public void deleteRoom(int roomid);

    public Collection allReservations();

    public Collection allFloors();
    
    public Collection allReservationsByFloor(int floorno);

    public Collection allReservationsByRoomTypeId(int typeId);
    
    public Collection allReservationsByDates(Date startdate, Date enddate);

    public Collection allRoomTypes();

    public Integer roomIdFromPrice(double price);

    public Collection allRooms();

    public Collection allRoomStatus();

    public Collection roomById(int roomid);
    
    public List<Rooms> getAllRooms();

    public Rooms getRoomById(int roomid);

    public List<RoomTypes> getAllRoomTypes();

    public List<RoomStatus> getAllRoomStatus();

    public RoomStatus getRoomstatusByStatus(String status);

    public RoomTypes getRoomtypesByType(String type);
    
    public List<Reservation> getReservationByFloor(int floorno);

    public List<Reservation> getReservationByDate(Date startdate, Date enddate);

    public List<Reservation> getReservationByPrice(double startPrice, double endPrice);

    

}
