/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import entities.AvanceConstru;
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
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author BorisECornejo
 */
@ManagedBean(name = "avanceConstruccionController")
@ViewScoped
public class AvanceConstruJpaController implements Serializable {
    
    @EJB
    private facade.AvanceConstruFacade avanceConstruFacade;
    
    private int idAvance;
    private String nombre;
    private String descripcion;
    
    private List<AvanceConstru> lstAvanceConstru = new ArrayList<AvanceConstru>();;
    private AvanceConstru selectedAvanceConstru;
    
    private int idAvance_modal;
    private String nombre_modal;
    private String descripcion_modal;

    public int getIdAvance() {
        return idAvance;
    }

    public void setIdAvance(int idAvance) {
        this.idAvance = idAvance;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<AvanceConstru> getLstAvanceConstru() {
        return lstAvanceConstru;
    }

    public AvanceConstru getSelectedAvanceConstru() {
        return selectedAvanceConstru;
    }

    public void setSelectedAvanceConstru(AvanceConstru selectedAvanceConstru) {
        this.selectedAvanceConstru = selectedAvanceConstru;
    }
    
    public void setLstAvanceConstru(List<AvanceConstru> lstAvanceConstru) {
        this.lstAvanceConstru = lstAvanceConstru;
    }

    public int getIdAvance_modal() {
        return idAvance_modal;
    }

    public void setIdAvance_modal(int idAvance_modal) {
        this.idAvance_modal = idAvance_modal;
    }

    public String getNombre_modal() {
        return nombre_modal;
    }

    public void setNombre_modal(String nombre_modal) {
        this.nombre_modal = nombre_modal;
    }

    public String getDescripcion_modal() {
        return descripcion_modal;
    }

    public void setDescripcion_modal(String descripcion_modal) {
        this.descripcion_modal = descripcion_modal;
    }
        
    public AvanceConstruJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public AvanceConstruJpaController(){
    
    }
    
    @PostConstruct
    public void init() {
        generar();
        llenarTabla();
    }

    public void create(AvanceConstru avanceConstru) throws Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(avanceConstru);
            utx.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException ex) {
            try {
                utx.rollback();
            } catch (IllegalStateException | SecurityException | SystemException re) {
                throw new Exception("An error occurred attempting to roll back the transaction.", re);
            }
            if (findAvanceConstru(avanceConstru.getIdAvanceConstru()) != null) {
                throw new Exception("AvanceConstru " + avanceConstru + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AvanceConstru avanceConstru) throws Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            avanceConstru = em.merge(avanceConstru);
            utx.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException ex) {
            try {
                utx.rollback();
            } catch (IllegalStateException | SecurityException | SystemException re) {
                throw new Exception("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = avanceConstru.getIdAvanceConstru();
                if (findAvanceConstru(id) == null) {
                    throw new Exception("The avanceConstru with id " + id + " no longer exists.");
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
            AvanceConstru avanceConstru;
            try {
                avanceConstru = em.getReference(AvanceConstru.class, id);
                avanceConstru.getIdAvanceConstru();
            } catch (EntityNotFoundException enfe) {
                throw new Exception("The avanceConstru with id " + id + " no longer exists.", enfe);
            }
            em.remove(avanceConstru);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (IllegalStateException | SecurityException | SystemException re) {
                throw new Exception("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AvanceConstru> findAvanceConstruEntities() {
        return findAvanceConstruEntities(true, -1, -1);
    }

    public List<AvanceConstru> findAvanceConstruEntities(int maxResults, int firstResult) {
        return findAvanceConstruEntities(false, maxResults, firstResult);
    }

    private List<AvanceConstru> findAvanceConstruEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AvanceConstru.class));
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

    public AvanceConstru findAvanceConstru(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AvanceConstru.class, id);
        } finally {
            em.close();
        }
    }

    public int getAvanceConstruCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AvanceConstru> rt = cq.from(AvanceConstru.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }  
    
    public void guardar() {
        try {
            AvanceConstru ac = new AvanceConstru();
            ac.setIdAvanceConstru(idAvance);
            ac.setNombre(nombre);
            ac.setDescripcion(descripcion);
            avanceConstruFacade.create(ac);
            FacesMessage msg = new FacesMessage("Se guardo el registro satisfactoriamente");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            limpiarForm();
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("No se pudo guardar el registro " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void limpiarForm() {
        idAvance = 0;
        nombre = null;
        descripcion = null;
        idAvance_modal = 0;
        nombre_modal = null;
        descripcion_modal = null;
        selectedAvanceConstru=new AvanceConstru();
        generar();
        llenarTabla();
    }

    public void generar() {
        try {
        
        }catch(Exception e) {
            
        }
    }
    
    public void llenarTabla() {
        lstAvanceConstru = avanceConstruFacade.findAll();
    }
    
    public void actualizarAvanceConstru() {
        try {
            selectedAvanceConstru.setNombre(nombre_modal);
            selectedAvanceConstru.setDescripcion(descripcion_modal);
            avanceConstruFacade.edit(selectedAvanceConstru);
            llenarTabla();
            FacesMessage msgInfo = new FacesMessage("Registro actualizar con exito!");
            FacesContext.getCurrentInstance().addMessage(null, msgInfo);
        } catch (Exception e) {
            FacesMessage msgError = new FacesMessage("No se pudo actualizar el registro " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msgError);
        }
    }
    
    public void onRowSelectAvanceConstru(SelectEvent event) {
        String codigo_avance = String.valueOf(selectedAvanceConstru.getIdAvanceConstru());
        FacesMessage msg = new FacesMessage("Codigo Marcado", codigo_avance);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        idAvance_modal = selectedAvanceConstru.getIdAvanceConstru();
        nombre_modal = selectedAvanceConstru.getNombre();
        descripcion_modal = selectedAvanceConstru.getDescripcion();
    }
    
}
