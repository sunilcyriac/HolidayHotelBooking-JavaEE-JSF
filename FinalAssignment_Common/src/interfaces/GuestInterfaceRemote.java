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
public interface GuestInterfaceRemote {
    
    public Boolean checkAvailableRooms(Date startdate, Date enddate, int typeId, int qty);

    public Collection getAvailableRooms();

    public Collection getAllRoomTypes();

      
}
