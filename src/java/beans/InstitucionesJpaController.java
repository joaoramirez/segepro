/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import beans.exceptions.NonexistentEntityException;
import beans.exceptions.PreexistingEntityException;
import beans.exceptions.RollbackFailureException;
import entities.Instituciones;
import java.io.Serializable;
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
@ManagedBean(name = "institucionesController")
@ViewScoped
public class InstitucionesJpaController implements Serializable {

    @EJB
    private facade.InstitucionesFacade institucionesFacade;
    
    private List<Instituciones> listadoInstituciones = new ArrayList<Instituciones>();
    private Instituciones selectedInstitucion;
    private int codinstitucion;
    private String nominstitucion, direccion, telefono;
    
    private int codinstitucion_modal;
    private String nominstitucion_modal, direccion_modal, telefono_modal;

    public List<Instituciones> getListadoInstituciones() {
        return listadoInstituciones;
    }

    public void setListadoInstituciones(List<Instituciones> listadoInstituciones) {
        this.listadoInstituciones = listadoInstituciones;
    }

    public Instituciones getSelectedInstitucion() {
        return selectedInstitucion;
    }

    public void setSelectedInstitucion(Instituciones selectedInstitucion) {
        this.selectedInstitucion = selectedInstitucion;
    }

    public int getCodinstitucion() {
        return codinstitucion;
    }

    public void setCodinstitucion(int codinstitucion) {
        this.codinstitucion = codinstitucion;
    }

    public String getNominstitucion() {
        return nominstitucion;
    }

    public void setNominstitucion(String nominstitucion) {
        this.nominstitucion = nominstitucion;
    }

    public String getDirecicon() {
        return direccion;
    }

    public void setDirecicon(String direcicon) {
        this.direccion = direcicon;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCodinstitucion_modal() {
        return codinstitucion_modal;
    }

    public void setCodinstitucion_modal(int codinstitucion_modal) {
        this.codinstitucion_modal = codinstitucion_modal;
    }

    public String getNominstitucion_modal() {
        return nominstitucion_modal;
    }

    public void setNominstitucion_modal(String nominstitucion_modal) {
        this.nominstitucion_modal = nominstitucion_modal;
    }

    public String getDireccion_modal() {
        return direccion_modal;
    }

    public void setDireccion_modal(String direccion_modal) {
        this.direccion_modal = direccion_modal;
    }

    public String getTelefono_modal() {
        return telefono_modal;
    }

    public void setTelefono_modal(String telefono_modal) {
        this.telefono_modal = telefono_modal;
    }
        
    public InstitucionesJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public InstitucionesJpaController(){
    
    }
    
    @PostConstruct
    public void init() {
        selectedInstitucion = new Instituciones();
        generar();
        llenarTabla();
    }

    public void create(Instituciones instituciones) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(instituciones);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findInstituciones(instituciones.getCodInstitucion()) != null) {
                throw new PreexistingEntityException("Instituciones " + instituciones + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Instituciones instituciones) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            instituciones = em.merge(instituciones);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = instituciones.getCodInstitucion();
                if (findInstituciones(id) == null) {
                    throw new NonexistentEntityException("The instituciones with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Instituciones instituciones;
            try {
                instituciones = em.getReference(Instituciones.class, id);
                instituciones.getCodInstitucion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The instituciones with id " + id + " no longer exists.", enfe);
            }
            em.remove(instituciones);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Instituciones> findInstitucionesEntities() {
        return findInstitucionesEntities(true, -1, -1);
    }

    public List<Instituciones> findInstitucionesEntities(int maxResults, int firstResult) {
        return findInstitucionesEntities(false, maxResults, firstResult);
    }

    private List<Instituciones> findInstitucionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Instituciones.class));
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

    public Instituciones findInstituciones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Instituciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getInstitucionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Instituciones> rt = cq.from(Instituciones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public void guardar(){
        try{
        Instituciones i = new Instituciones();
        i.setCodInstitucion(codinstitucion);
        i.setNomInstitucion(nominstitucion);
        i.setTelefonoInstitucion(telefono);
        i.setDireccionInstitucion(direccion);
        institucionesFacade.create(i);
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
        codinstitucion = 0;
        nominstitucion = null;
        direccion = null;
        telefono = null;
        generar();
        llenarTabla();
//        FacesMessage msg = new FacesMessage("Proceso cancelado con exito! ");
//        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void generar(){
        codinstitucion = (int) institucionesFacade.listaInstitucion();
    }
    
    public void llenarTabla(){
        listadoInstituciones = institucionesFacade.findAll();
    }
    
    public void onRowSelectInstituciones(SelectEvent event) {
        String codigo_institucion = String.valueOf(selectedInstitucion.getCodInstitucion());
        FacesMessage msg = new FacesMessage("Codigo Marcado", codigo_institucion);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        codinstitucion_modal = selectedInstitucion.getCodInstitucion();
        nominstitucion_modal = selectedInstitucion.getNomInstitucion();
        direccion_modal = selectedInstitucion.getDireccionInstitucion();
        telefono_modal = selectedInstitucion.getTelefonoInstitucion();
    }
    
    public void actualizarInstitucoines() {
        try {
            selectedInstitucion.setCodInstitucion(codinstitucion_modal);
            selectedInstitucion.setNomInstitucion(nominstitucion_modal);
            selectedInstitucion.setTelefonoInstitucion(telefono_modal);
            selectedInstitucion.setDireccionInstitucion(direccion_modal);
            institucionesFacade.edit(selectedInstitucion);
            llenarTabla();
            FacesMessage msgInfo = new FacesMessage("Registro actualizar con exito!");
            FacesContext.getCurrentInstance().addMessage(null, msgInfo);
        } catch (Exception e) {
            FacesMessage msgError = new FacesMessage("No se pudo actualizar el registro " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msgError);
        }
    }
    
}
