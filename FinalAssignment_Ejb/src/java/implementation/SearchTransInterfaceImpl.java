/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.Query;
import entities.Transactions;
import interfaces.SearchTransInterfaceRemote;

/**
 *
 * @author Batman
 */
//referred from various sources including weekly tasks and stackoverflow
@Stateless
public class SearchTransInterfaceImpl implements SearchTransInterfaceRemote{
    
    @javax.persistence.PersistenceContext(unitName="FinalAssignment_EjbPU")
    private EntityManager entityManager;


    @Override
    public List<Transactions> searchTransactionById(int transactionid) throws Exception {
        Transactions transactions =entityManager.find(Transactions.class, transactionid);
        
        List<Transactions> result = new ArrayList<>();
        
        if (transactions != null) {
        result.add(transactions);
        }
        else{
         return null;
        }
        
        return  result;
        
       
    }

    @Override
    public List<Transactions> searchTransactionByName(String transactionname) {
        TypedQuery query = entityManager.createNamedQuery("Transactions.findByTransactionname", Transactions.class);
        
        try{
        
            
            query.setParameter("transactionname", transactionname);
            return (List<Transactions>) query.getResultList();
        
        }
        catch (Exception ex) {
            return new ArrayList<Transactions>();
        }
        
    }

    @Override
    public List<Transactions> searchTransactionByType(String paymenttype) {
        TypedQuery query = entityManager.createNamedQuery("Transactions.findByPaymenttype", Transactions.class);
        
        try{
        
            
            query.setParameter("paymenttype", paymenttype);
            return (List<Transactions>) query.getResultList();
        
        }
        catch (Exception ex) {
            return null;
        }
        
    }

    @Override
    public List<Transactions> getAllTransactions() throws Exception {
        return entityManager.createNamedQuery(Transactions.findAll).getResultList();
    }
    
}
