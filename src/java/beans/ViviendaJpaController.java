/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import entities.Vivienda;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author BorisECornejo
 */
@ManagedBean(name = "viviendaController")
@ViewScoped
public class ViviendaJpaController implements Serializable {
    
    @EJB
    private facade.ViviendaFacade viviendaFacade;
    
    private List<Vivienda> listadoViviendas = new ArrayList<Vivienda>();
    private Vivienda selectedVivienda;
    private int idVivienda;
    private BigDecimal costo;
    private String nomVivienda, incluye, area, descripcion;
    
    private int idVivienda_modal;
    private BigDecimal costo_modal;
    private String nomVivienda_modal, incluye_modal, area_modal, descripcion_modal;

    public int getIdVivienda_modal() {
        return idVivienda_modal;
    }

    public void setIdVivienda_modal(int idVivienda_modal) {
        this.idVivienda_modal = idVivienda_modal;
    }

    public BigDecimal getCosto_modal() {
        return costo_modal;
    }

    public void setCosto_modal(BigDecimal costo_modal) {
        this.costo_modal = costo_modal;
    }

    public String getNomVivienda_modal() {
        return nomVivienda_modal;
    }

    public void setNomVivienda_modal(String nomVivienda_modal) {
        this.nomVivienda_modal = nomVivienda_modal;
    }

    public String getIncluye_modal() {
        return incluye_modal;
    }

    public void setIncluye_modal(String incluye_modal) {
        this.incluye_modal = incluye_modal;
    }

    public String getArea_modal() {
        return area_modal;
    }

    public void setArea_modal(String area_modal) {
        this.area_modal = area_modal;
    }

    public String getDescripcion_modal() {
        return descripcion_modal;
    }

    public void setDescripcion_modal(String descripcion_modal) {
        this.descripcion_modal = descripcion_modal;
    }
    
    public List<Vivienda> getListadoViviendas() {
        return listadoViviendas;
    }

    public void setListadoViviendas(List<Vivienda> listadoViviendas) {
        this.listadoViviendas = listadoViviendas;
    }

    public Vivienda getSelectedVivienda() {
        return selectedVivienda;
    }

    public void setSelectedVivienda(Vivienda selectedVivienda) {
        this.selectedVivienda = selectedVivienda;
    }

    public int getIdVivienda() {
        return idVivienda;
    }

    public void setIdVivienda(int idVivienda) {
        this.idVivienda = idVivienda;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public String getNomVivienda() {
        return nomVivienda;
    }

    public void setNomVivienda(String nomVivienda) {
        this.nomVivienda = nomVivienda;
    }

    public String getIncluye() {
        return incluye;
    }

    public void setIncluye(String incluye) {
        this.incluye = incluye;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ViviendaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public ViviendaJpaController(){
    
    }
    
    @PostConstruct
    public void init() {
       selectedVivienda = new Vivienda();
       generar();
       llenarTabla();
    }
    
    public void create(Vivienda vivienda) throws Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(vivienda);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new Exception("An error occurred attempting to roll back the transaction.", re);
            }
            if (findVivienda(vivienda.getIdModelo()) != null) {
                throw new Exception("Vivienda " + vivienda + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vivienda vivienda) throws Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            vivienda = em.merge(vivienda);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new Exception("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = vivienda.getIdModelo();
                if (findVivienda(id) == null) {
                    throw new Exception("The vivienda with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Vivienda vivienda;
            try {
                vivienda = em.getReference(Vivienda.class, id);
                vivienda.getIdModelo();
            } catch (EntityNotFoundException enfe) {
                throw new Exception("The vivienda with id " + id + " no longer exists.", enfe);
            }
            em.remove(vivienda);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new Exception("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vivienda> findViviendaEntities() {
        return findViviendaEntities(true, -1, -1);
    }

    public List<Vivienda> findViviendaEntities(int maxResults, int firstResult) {
        return findViviendaEntities(false, maxResults, firstResult);
    }

    private List<Vivienda> findViviendaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vivienda.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Vivienda findVivienda(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vivienda.class, id);
        } finally {
            em.close();
        }
    }

    public int getViviendaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vivienda> rt = cq.from(Vivienda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public void onRowSelectVivienda(SelectEvent event) {
        String codigo_mejora = String.valueOf(selectedVivienda.getIdModelo());
        FacesMessage msg = new FacesMessage("Codigo Marcado", codigo_mejora);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        idVivienda_modal = selectedVivienda.getIdModelo();
        nomVivienda_modal = selectedVivienda.getNombreModelo();
        descripcion_modal = selectedVivienda.getDescripcion();
        incluye_modal = selectedVivienda.getIncluye();
        costo_modal = selectedVivienda.getCosto();
        area_modal = selectedVivienda.getAreaConstruida();
    }
       
    public void guardar(){
        try{
        Vivienda v = new Vivienda();
        v.setIdModelo(idVivienda);
        v.setNombreModelo(nomVivienda);
        v.setDescripcion(descripcion);
        v.setIncluye(incluye);
        v.setCosto(costo);
        v.setAreaConstruida(area);
        viviendaFacade.create(v);
        FacesMessage msg = new FacesMessage("Se guardo el registro satisfactoriamente");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        limpiarForm();
        generar();
        llenarTabla();
        } catch(Exception e){
            FacesMessage msg = new FacesMessage("No se pudo guardar el registro " + e.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void limpiarForm() {
        idVivienda = 0;
        nomVivienda = null;
        descripcion = null;
        incluye = null;
        costo = BigDecimal.ZERO;
        area = null;
        generar();
        llenarTabla();
    }
    
    public void generar(){
        try {
        idVivienda = (int) viviendaFacade.listaVivienda();
        }catch (Exception e) {
            idVivienda = 1;
        }
    }
    
    public void llenarTabla(){
        listadoViviendas = viviendaFacade.listaViviendas();
    }
    
    public void actualizarVivienda() {
        try {
            selectedVivienda.setCosto(costo_modal);
            selectedVivienda.setDescripcion(descripcion_modal);
            selectedVivienda.setNombreModelo(nomVivienda_modal);
            selectedVivienda.setIncluye(incluye_modal);
            selectedVivienda.setAreaConstruida(area_modal);
            viviendaFacade.edit(selectedVivienda);
            llenarTabla();
            FacesMessage msgInfo = new FacesMessage("Registro actualizar con exito!");
            FacesContext.getCurrentInstance().addMessage(null, msgInfo);
        } catch (Exception e) {
            FacesMessage msgError = new FacesMessage("No se pudo actualizar el registro " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msgError);
        }
    }
    
}
