/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author BorisECornejo
 */
@ManagedBean
@ViewScoped
public class ModulosController {
    
    
    /**
     * Creates a new instance of ModulosController
     */
    public ModulosController() {
    }
    
    
    //modulos usuario
    /**
     * Muestra al usuario el modulo o los modulos que tiene acesso.
     * @return una lista de tipo modulo 
     * @throws java.lang.Exception excepcion generica 
     */
   /* public List<Modulo> modulosUsuarios() throws Exception {
        List<Modulo> lstMod = new ArrayList<>();
        try {
            LoginBean lg = new LoginBean();
            String user = lg.getLoggedUser();
            short codCia = lg.sscia();
            //System.out.println("CodCia--->"+codCia);
            BigDecimal codPuesto = this.ejbComparativo.buscarCodPuesto(user, codCia);
            //System.out.println("puesto-->"+codPuesto);
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.setAttribute("SSCODPUESTO", codPuesto);
            
            List<RolesXMenus> lstRolesXMenus = new ArrayList<>();
            List<Modulo> lstModulos = new ArrayList<>();
            String status = "A";
            lstRolesXMenus = ejbRoles.findCodPuesto(codCia, codPuesto, status);
            
            if(!lstMod.isEmpty()){
                lstMod.clear();
            }
            
               //System.out.println("lstRolesXMenus-->"+lstRolesXMenus);
              for (RolesXMenus rolesXMenus : lstRolesXMenus) {
                  // System.out.println("modulo--->"+rolesXMenus.getRolesXMenusPK().getCodModulo());
                   Modulo mm = ejbFacade.findCodModulo(codCia, rolesXMenus.getRolesXMenusPK().getCodModulo()).get(0);
                 //  System.out.println("mm--->"+mm);
                  if(lstModulos.contains(mm)==false){
                    lstModulos.add(mm);
                  }
            
                  
                  
              }
              
            for (Modulo mm : lstModulos) {
                
             
              //  System.out.println("modulo--->"+mm);
                if(lstMod.contains(mm)==true){
                //    System.out.println("ya existe1--->"+mm);
                
                }
                else
                { 
                    lstMod.add(mm);
                  
                }
            }
            
                    
                    
        } catch (NoResultException no) {
            FacesContext
                    .getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,"Error en modulosUsuarios: {0}" + no, null));
        }
        //System.out.println("modulos--->"+lstMod);
        return lstMod;
    }*/
}
