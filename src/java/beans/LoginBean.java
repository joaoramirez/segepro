/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author BorisECornejo
 */
@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean {
    
    private String username;
    private String password;
    
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }
    
    public void login(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
       // session.setAttribute("SSCIAVAL", this.codCia1.getCodCia());
         pasarGarbageCollector();
        try{
            if (request.getUserPrincipal() != null) {

                FacesContext.getCurrentInstance().getExternalContext().redirect("Home/ModulosMenu.xhtml");
            }
           
            request.getSession().setMaxInactiveInterval(900);
            request.login(username.toUpperCase(), password);
            
            
        }catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Su usuario o password no son correctos " + e.getMessage(), null));
        }
    }
    
    
    public void logout() throws ServletException, IOException{
        System.gc();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (request.getSession() != null) {
            request.getSession().invalidate();
        }
           
        request.logout();
    }
    
     public void pasarGarbageCollector(){
 
        Runtime garbage = Runtime.getRuntime();
        System.out.println("Memoria libre antes de limpieza: "+ garbage.freeMemory() );
        System.out.println("Memoria libre antes de limpieza: "+ garbage.freeMemory() );
       
        garbage.gc();
 
        System.out.println("Memoria total: "+ garbage.totalMemory());
        System.out.println("Memoria libre antes de limpieza: "+ garbage.freeMemory() );
    } 
}
