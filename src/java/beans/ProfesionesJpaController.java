/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import entities.Profesiones;
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
@ManagedBean(name = "profesionesController")
@ViewScoped
public class ProfesionesJpaController implements Serializable {

    @EJB private facade.ProfesionesFacade profesionesFacade;
    
    private int idProfesion;
    private String nomProfesion;
    
    private int idProfesion_model;
    private String nomProfesion_model;
    
    private List<Profesiones> listadoProfesiones = new ArrayList<Profesiones>();
    private Profesiones selectedProfesiones;

    public List<Profesiones> getListadoProfesiones() {
        return listadoProfesiones;
    }

    public void setListadoProfesiones(List<Profesiones> listadoProfesiones) {
        this.listadoProfesiones = listadoProfesiones;
    }

    public Profesiones getSelectedProfesiones() {
        return selectedProfesiones;
    }

    public void setSelectedProfesiones(Profesiones selectedProfesiones) {
        this.selectedProfesiones = selectedProfesiones;
    }

    public int getIdProfesion() {
        return idProfesion;
    }

    public void setIdProfesion(int idProfesion) {
        this.idProfesion = idProfesion;
    }

    public String getNomProfesion() {
        return nomProfesion;
    }

    public void setNomProfesion(String nomProfesion) {
        this.nomProfesion = nomProfesion;
    }

    public int getIdProfesion_model() {
        return idProfesion_model;
    }

    public void setIdProfesion_model(int idProfesion_model) {
        this.idProfesion_model = idProfesion_model;
    }

    public String getNomProfesion_model() {
        return nomProfesion_model;
    }

    public void setNomProfesion_model(String nomProfesion_model) {
        this.nomProfesion_model = nomProfesion_model;
    }
        
    public ProfesionesJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public ProfesionesJpaController(){
    
    }
    
    @PostConstruct
    public void init() {
        generar();
        llenarTabla();
    }

    public void create(Profesiones profesiones) throws Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(profesiones);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new Exception("An error occurred attempting to roll back the transaction.", re);
            }
            if (findProfesiones(profesiones.getIdProfesion()) != null) {
                throw new Exception("Profesiones " + profesiones + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Profesiones profesiones) throws Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            profesiones = em.merge(profesiones);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new Exception("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = profesiones.getIdProfesion();
                if (findProfesiones(id) == null) {
                    throw new Exception("The profesiones with id " + id + " no longer exists.");
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
            Profesiones profesiones;
            try {
                profesiones = em.getReference(Profesiones.class, id);
                profesiones.getIdProfesion();
            } catch (EntityNotFoundException enfe) {
                throw new Exception("The profesiones with id " + id + " no longer exists.", enfe);
            }
            em.remove(profesiones);
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

    public List<Profesiones> findProfesionesEntities() {
        return findProfesionesEntities(true, -1, -1);
    }

    public List<Profesiones> findProfesionesEntities(int maxResults, int firstResult) {
        return findProfesionesEntities(false, maxResults, firstResult);
    }

    private List<Profesiones> findProfesionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Profesiones.class));
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

    public Profesiones findProfesiones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Profesiones.class, id);
        } finally {
            em.close();
        }
    }

    public int getProfesionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Profesiones> rt = cq.from(Profesiones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
   public void guardar() {
        try {
        Profesiones p = new Profesiones();
        p.setIdProfesion(idProfesion);
        p.setNombreProfesion(nomProfesion);
        profesionesFacade.create(p);
        FacesMessage msgInfo = new FacesMessage("Registro guardado con exito!");
        FacesContext.getCurrentInstance().addMessage(null, msgInfo);
        limpiarForm();
        } catch(Exception e){
            FacesMessage msgError = new FacesMessage("No se pudo guardar el registro " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msgError);
        }
    }
    
    public void limpiarForm(){
        idProfesion=0;
        nomProfesion=null;
        generar();
        llenarTabla();
    }
    
    public void generar() {
        try {
        idProfesion = (int) profesionesFacade.listaProfesiones();
        }catch(Exception e) {
            idProfesion = 1;
        }
    }
          
    public void llenarTabla() {
      listadoProfesiones = profesionesFacade.listadoProfesiones();
    }
    
    public void onRowSelectProfesiones(SelectEvent event) {
        String codigo_profesion = String.valueOf(selectedProfesiones.getIdProfesion());
        FacesMessage msg = new FacesMessage("Codigo Marcado", codigo_profesion);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        idProfesion_model = selectedProfesiones.getIdProfesion();
        nomProfesion_model = selectedProfesiones.getNombreProfesion();
    }
    
    public void actualizarProfesiones() {
        try {
            selectedProfesiones.setIdProfesion(idProfesion_model);
            selectedProfesiones.setNombreProfesion(nomProfesion_model);
            profesionesFacade.edit(selectedProfesiones);
            llenarTabla();
            FacesMessage msgInfo = new FacesMessage("Registro actualizar con exito!");
            FacesContext.getCurrentInstance().addMessage(null, msgInfo);
        } catch (Exception e) {
            FacesMessage msgError = new FacesMessage("No se pudo actualizar el registro " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msgError);
        }
    }
    
}
