/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import entities.UnidadMedida;
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
@ManagedBean(name = "unidadMedidaController")
@ViewScoped
public class UnidadMedidaJpaController implements Serializable {

    @EJB
    private facade.UnidadMedidaFacade unidadMedidaFacade;
    
    private List<UnidadMedida> listadoUnidades = new ArrayList<UnidadMedida>();
    private UnidadMedida selectedUnidad;
    private int codUnidad;
    private String nomUnidad, abrevUnidad;
    
    private int codUnidad_modal;
    private String nomUnidad_modal, abrevUnidad_modal;

    public List<UnidadMedida> getListadoUnidades() {
        return listadoUnidades;
    }

    public void setListadoUnidades(List<UnidadMedida> listadoUnidades) {
        this.listadoUnidades = listadoUnidades;
    }

    public UnidadMedida getSelectedUnidad() {
        return selectedUnidad;
    }

    public void setSelectedUnidad(UnidadMedida selectedUnidad) {
        this.selectedUnidad = selectedUnidad;
    }

    public int getCodUnidad() {
        return codUnidad;
    }

    public void setCodUnidad(int codUnidad) {
        this.codUnidad = codUnidad;
    }

    public String getNomUnidad() {
        return nomUnidad;
    }

    public void setNomUnidad(String nomUnidad) {
        this.nomUnidad = nomUnidad;
    }

    public String getAbrevUnidad() {
        return abrevUnidad;
    }

    public void setAbrevUnidad(String abrevUnidad) {
        this.abrevUnidad = abrevUnidad;
    }

    public int getCodUnidad_modal() {
        return codUnidad_modal;
    }

    public void setCodUnidad_modal(int codUnidad_modal) {
        this.codUnidad_modal = codUnidad_modal;
    }

    public String getNomUnidad_modal() {
        return nomUnidad_modal;
    }

    public void setNomUnidad_modal(String nomUnidad_modal) {
        this.nomUnidad_modal = nomUnidad_modal;
    }

    public String getAbrevUnidad_modal() {
        return abrevUnidad_modal;
    }

    public void setAbrevUnidad_modal(String abrevUnidad_modal) {
        this.abrevUnidad_modal = abrevUnidad_modal;
    }
    
    public UnidadMedidaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public UnidadMedidaJpaController(){
    
    }
    
    @PostConstruct
    public void init() {
        generar();
        llenarTabla();
    }
    
    
    public void create(UnidadMedida unidadMedida) throws Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(unidadMedida);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new Exception("An error occurred attempting to roll back the transaction.", re);
            }
            if (findUnidadMedida(unidadMedida.getCodUnidad()) != null) {
                throw new Exception("UnidadMedida " + unidadMedida + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UnidadMedida unidadMedida) throws Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            unidadMedida = em.merge(unidadMedida);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new Exception("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = unidadMedida.getCodUnidad();
                if (findUnidadMedida(id) == null) {
                    throw new Exception("The unidadMedida with id " + id + " no longer exists.");
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
            UnidadMedida unidadMedida;
            try {
                unidadMedida = em.getReference(UnidadMedida.class, id);
                unidadMedida.getCodUnidad();
            } catch (EntityNotFoundException enfe) {
                throw new Exception("The unidadMedida with id " + id + " no longer exists.", enfe);
            }
            em.remove(unidadMedida);
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

    public List<UnidadMedida> findUnidadMedidaEntities() {
        return findUnidadMedidaEntities(true, -1, -1);
    }

    public List<UnidadMedida> findUnidadMedidaEntities(int maxResults, int firstResult) {
        return findUnidadMedidaEntities(false, maxResults, firstResult);
    }

    private List<UnidadMedida> findUnidadMedidaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UnidadMedida.class));
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

    public UnidadMedida findUnidadMedida(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UnidadMedida.class, id);
        } finally {
            em.close();
        }
    }

    public int getUnidadMedidaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UnidadMedida> rt = cq.from(UnidadMedida.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public void guardar(){
        try{
        UnidadMedida u = new UnidadMedida();
        u.setCodUnidad(codUnidad);
        u.setNomUnidad(nomUnidad);
        u.setAbreviatura(abrevUnidad);
        unidadMedidaFacade.create(u);
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
        codUnidad = 0;
        nomUnidad = null;
        abrevUnidad = null;
        generar();
        llenarTabla();
    }
    
    public void generar(){
        try {
            codUnidad = (int) unidadMedidaFacade.listaUnidad();
        } catch (Exception e) {
            codUnidad = 1;
        }
        
    }
    
    public void llenarTabla(){
        listadoUnidades = unidadMedidaFacade.findAll();
    }
    
    public void onRowSelectUnidades(SelectEvent event) {
        String codigo_codUnidad = String.valueOf(selectedUnidad.getCodUnidad());
        FacesMessage msg = new FacesMessage("Codigo Marcado", codigo_codUnidad);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        codUnidad_modal = selectedUnidad.getCodUnidad();
        nomUnidad_modal = selectedUnidad.getNomUnidad();
        abrevUnidad_modal = selectedUnidad.getAbreviatura();
    }
    
    public void actualizaUnidades() {
        try {
            selectedUnidad.setCodUnidad(codUnidad_modal);
            selectedUnidad.setNomUnidad(nomUnidad_modal);
            selectedUnidad.setAbreviatura(abrevUnidad_modal);
            unidadMedidaFacade.edit(selectedUnidad);
            llenarTabla();
            FacesMessage msgInfo = new FacesMessage("Registro actualizar con exito!");
            FacesContext.getCurrentInstance().addMessage(null, msgInfo);
        } catch (Exception e) {
            FacesMessage msgError = new FacesMessage("No se pudo actualizar el registro " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msgError);
        }

    }
    
}
