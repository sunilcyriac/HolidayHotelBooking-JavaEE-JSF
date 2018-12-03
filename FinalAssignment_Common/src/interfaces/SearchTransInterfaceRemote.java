/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;


import java.util.List;
import javax.ejb.Remote;
import entities.Transactions;

/**
 *
 * @author Sunil
 */

@Remote
public interface SearchTransInterfaceRemote {
    
    public List<Transactions> searchTransactionById(int transactionid) throws Exception;
    
    public List<Transactions> searchTransactionByName(String transactionname) throws Exception;
    
    public List<Transactions> searchTransactionByType(String paymenttype) throws Exception;
    
    public List<Transactions> getAllTransactions() throws Exception;
}
