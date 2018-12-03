/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementation;

import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import entities.Rooms;
import entities.RoomTypes;
import interfaces.GuestInterfaceRemote;

/**
 *
 * @author Sunil
 */
//referred from various sources including weekly tasks and stackoverflow
@Stateless
@TransactionManagement(value=TransactionManagementType.CONTAINER)
public class GuestInterfaceImpl implements GuestInterfaceRemote {
    
    @javax.persistence.PersistenceContext(unitName="FinalAssignment_EjbPU")
    private EntityManager em;
    

    @Override
    public Collection getAvailableRooms() {
        Query query = em.createQuery("SELECT rm FROM Rooms rm JOIN rm.typeId rt WHERE rm.typeId = :type_id");
        RoomTypes roomtypes = em.find(RoomTypes.class, 1);
        query.setParameter("type_id", roomtypes);
        return (Collection<Rooms>) query.getResultList();
    }

    @Override
    public Collection getAllRoomTypes() {
        Query query = em.createQuery("SELECT rt FROM RoomTypes rt");
        return (Collection<RoomTypes>) query.getResultList();
    }

    @Override
    public Boolean checkAvailableRooms(Date startdate, Date enddate, int typeId, int qty) {
        
        boolean flag = false;
        Query query = em.createQuery("SELECT COUNT(rs) FROM Reservation rs JOIN rs.roomid rm WHERE "
                + "rm.typeId = :type_id AND ((:startdate BETWEEN rs.startdate AND rs.enddate) OR "
                + "(:enddate BETWEEN rs.startdate AND rs.enddate) OR (rs.startdate < :enddate AND rs.enddate > :startdate))");
        
        RoomTypes roomtypes = em.find(RoomTypes.class, typeId);
        query.setParameter("startdate", startdate);
        query.setParameter("enddate", enddate);
        query.setParameter("type_id", roomtypes);
        long count = (long) query.getSingleResult();
        
        
        Query query2 = em.createQuery("SELECT COUNT(r) FROM Rooms r WHERE r.typeId = :type_id");
        query2.setParameter("type_id", roomtypes);
        long total = (long) query2.getSingleResult();
        
        int available = (int) (total-count);
        
        if(qty<=available){
            flag = true;
        }
        return flag;
    }
      
}
