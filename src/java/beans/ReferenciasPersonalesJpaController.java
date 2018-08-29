/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import entities.ReferenciasPersonales;
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
@ManagedBean(name = "referenciaController")
@ViewScoped
public class ReferenciasPersonalesJpaController implements Serializable {
    
    @EJB
    private facade.ReferenciasPersonalesFacade refPersonalesFacade;
    
    private List<ReferenciasPersonales> listadoReferencias = new ArrayList<ReferenciasPersonales>();
    private ReferenciasPersonales selectedReferenciasPersonales;
    private int idCliente, idReferenciaP;
    private String nombreCompletoRef, parentesco, telefono, direccion, lugarTrabajo, nomSolicitante, numSolicitud;

    public String getNumSolicitud() {
        return numSolicitud;
    }

    public void setNumSolicitud(String numSolicitud) {
        this.numSolicitud = numSolicitud;
    }

    public List<ReferenciasPersonales> getListadoReferencias() {
        return listadoReferencias;
    }

    public void setListadoReferencias(List<ReferenciasPersonales> listadoReferencias) {
        this.listadoReferencias = listadoReferencias;
    }

    public ReferenciasPersonales getSelectedReferenciasPersonales() {
        return selectedReferenciasPersonales;
    }

    public void setSelectedReferenciasPersonales(ReferenciasPersonales selectedReferenciasPersonales) {
        this.selectedReferenciasPersonales = selectedReferenciasPersonales;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdReferenciaP() {
        return idReferenciaP;
    }

    public void setIdReferenciaP(int idReferenciaP) {
        this.idReferenciaP = idReferenciaP;
    }

    public String getNombreCompletoRef() {
        return nombreCompletoRef;
    }

    public void setNombreCompletoRef(String nombreCompletoRef) {
        this.nombreCompletoRef = nombreCompletoRef;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
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

    public String getLugarTrabajo() {
        return lugarTrabajo;
    }

    public void setLugarTrabajo(String lugarTrabajo) {
        this.lugarTrabajo = lugarTrabajo;
    }

    public String getNomSolicitante() {
        return nomSolicitante;
    }

    public void setNomSolicitante(String nomSolicitante) {
        this.nomSolicitante = nomSolicitante;
    }
    
    public ReferenciasPersonalesJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public ReferenciasPersonalesJpaController(){
    
    }
    
    @PostConstruct
    public void init() {
       listadoReferencias = refPersonalesFacade.listaReferencias();
    }

    public void create(ReferenciasPersonales referenciasPersonales) throws Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(referenciasPersonales);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new Exception("An error occurred attempting to roll back the transaction.", re);
            }
            if (findReferenciasPersonales(referenciasPersonales.getIdReferenciaP()) != null) {
                throw new Exception("ReferenciasPersonales " + referenciasPersonales + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ReferenciasPersonales referenciasPersonales) throws Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            referenciasPersonales = em.merge(referenciasPersonales);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new Exception("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = referenciasPersonales.getIdReferenciaP();
                if (findReferenciasPersonales(id) == null) {
                    throw new Exception("The referenciasPersonales with id " + id + " no longer exists.");
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
            ReferenciasPersonales referenciasPersonales;
            try {
                referenciasPersonales = em.getReference(ReferenciasPersonales.class, id);
                referenciasPersonales.getIdReferenciaP();
            } catch (EntityNotFoundException enfe) {
                throw new Exception("The referenciasPersonales with id " + id + " no longer exists.", enfe);
            }
            em.remove(referenciasPersonales);
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

    public List<ReferenciasPersonales> findReferenciasPersonalesEntities() {
        return findReferenciasPersonalesEntities(true, -1, -1);
    }

    public List<ReferenciasPersonales> findReferenciasPersonalesEntities(int maxResults, int firstResult) {
        return findReferenciasPersonalesEntities(false, maxResults, firstResult);
    }

    private List<ReferenciasPersonales> findReferenciasPersonalesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ReferenciasPersonales.class));
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

    public ReferenciasPersonales findReferenciasPersonales(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ReferenciasPersonales.class, id);
        } finally {
            em.close();
        }
    }

    public int getReferenciasPersonalesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ReferenciasPersonales> rt = cq.from(ReferenciasPersonales.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public void onRowSelectVivienda(SelectEvent event) {
        String codigo_referencia = String.valueOf(selectedReferenciasPersonales.getIdReferenciaP());
        FacesMessage msg = new FacesMessage("Codigo Marcado", codigo_referencia);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
}
