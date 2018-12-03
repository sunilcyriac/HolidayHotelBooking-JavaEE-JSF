/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Sunil
 */

@ManagedBean
@Named(value = "navigationController")
@RequestScoped
public class NavigationController {
    
    
    public void showSearch() {
        
        String url = "http://localhost:40648/FinalAssignment_Jsf/faces/searchtransaction.xhtml";
        
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(NavigationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
