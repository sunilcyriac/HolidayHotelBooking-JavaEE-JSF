package controller;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import entities.Transactions;
import interfaces.SearchTransInterfaceRemote;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.ejb.EJB;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

/**
 *
 * @author Sunil
 */
@ManagedBean
@Named(value = "searchDetailResultsController")
@RequestScoped


public class SearchDetailResultsController implements Serializable{
	
	
    @EJB
    private SearchTransInterfaceRemote searchTransaction;
	
    private Transactions transactions;
	
    private String transactionId;
    
    private String pageTitle;
	
	
    public SearchDetailResultsController() {
            
    }
    
	
    
   @PostConstruct
   public void init(){
    
       //TODO : PUT TRY EXCEPT AND ACCESS CONTROL 
        transactionId = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("transactionid");
        
         transactions = getSearchDetail();
         
         if (transactions != null) {
         
             transactionId = String.valueOf(transactions.getTransactionid());
            
         }
        
         
         

        
    } 
    

    private Transactions getSearchDetail() {
    
        ArrayList<Transactions> transactions = new ArrayList<Transactions>();
        try {
            transactions =  (ArrayList<Transactions>) searchTransaction.searchTransactionById(Integer.parseInt( transactionId));
        } catch (Exception ex) {
            Logger.getLogger(SearchDetailResultsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (transactions.isEmpty()) {
        
            return null;
        }
        else {
        
            return transactions.get(0);
        }
        
    }
    
   
    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }


    public Transactions getTransactions() {
        return transactions;
    }

    public void setTransactions(Transactions transactions) {
        this.transactions = transactions;
    }

    
}


