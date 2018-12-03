/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.Customers;
import java.util.List;
import javax.ejb.Remote;


/**
 *
 * @author Sunil Cyriac
 */

@Remote
public interface ManageCustInterfaceRemote{

    public int count();

    public void create(Customers entity);

    public void edit(Customers entity);

    public Customers find(Object id);

    public List<Customers> findAll();

    public List<Customers> findRange(int[] range);

    public void remove(Customers entity);
    
}

