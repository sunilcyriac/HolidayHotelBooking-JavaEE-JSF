/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import entities.Transactions;
import interfaces.SearchTransInterfaceRemote;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.ejb.EJB;
import java.util.HashMap;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Sunil
 */

@ManagedBean
@Named(value = "searchTransactionController")
@RequestScoped
public class SearchTransactionController implements Serializable{
    
    @EJB
    private SearchTransInterfaceRemote searchTransaction;
    
    private HashMap<String,String> newMap;
    
    private List<Transactions> SearchResults;

    private boolean resultAvailable;
    
    private boolean FirsTimeLoad;

    
    
      private String TextSelect;
      
      @NotNull
      private String SearchText;
      
      
      
      
    
    public SearchTransactionController() {
        
      newMap = new HashMap<>();
         resultAvailable = false;
        
      newMap.put("Search by id", "1");
      newMap.put("Search by name", "2");
      newMap.put("Search by type", "3");
      
     SearchResults = new ArrayList<Transactions>(); 
     
     FirsTimeLoad = true;
     
        
    }

    
    
    public HashMap<String,String> getMapValue()
    {
        return this.newMap;
    }
    
    public void setMapValue(HashMap<String,String> map)
    {
        this.newMap = map;
    }
    
    
    public List<Transactions> getSearchResults() {
        return SearchResults;
    }

    public void setSearchResults(List<Transactions> SearchResults) {
        this.SearchResults = SearchResults;
    }
    
    public String getTextSelect()
    {
        return TextSelect;
    }
    
    public void setTextSelect(String TextSelect)
    {
        this.TextSelect=TextSelect;
        
    }
    
    
    public String getSearchText()
    {
        return SearchText;
    }
    
    
    public void setSearchText(String SearchText)
    {
        this.SearchText=SearchText;
        
    }
    
   
    
    public void searches() throws Exception
    {
        FirsTimeLoad = false;
        resultAvailable = false;
        
        if (SearchText == null || SearchText.length() == 0) {
        
            System.out.println("Error");
        
        }
        
        if(TextSelect.equals("1")){
          SearchResults =   (List<Transactions>) searchTransaction.searchTransactionById((Integer.parseInt(SearchText)));
        
        }
        
        else if(TextSelect.equals("2")){
            
           SearchResults =   (List<Transactions>) searchTransaction.searchTransactionByName(SearchText);
           
        }
        
        else{
               
           SearchResults =   (List<Transactions>)  searchTransaction.searchTransactionByType(SearchText);
           
        }
        
        
        
        if ( SearchResults.size() >0) {
        
        
            resultAvailable = true;
        
        }
        
        
    }
    
      public SearchTransInterfaceRemote getSearchTransaction() {
        return searchTransaction;
    }

    public void setSearchTransaction(SearchTransInterfaceRemote searchTransaction) {
        this.searchTransaction = searchTransaction;
    }
    
    
     public boolean isResultAvailable() {
        return resultAvailable;
    }

    public void setResultAvailable(boolean resultAvailable) {
        this.resultAvailable = resultAvailable;
    }
    
    public boolean isFirsTimeLoad() {
        return FirsTimeLoad;
    }

    public void setFirsTimeLoad(boolean FirsTimeLoad) {
        this.FirsTimeLoad = FirsTimeLoad;
    }
    
}
