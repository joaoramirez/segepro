/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import beans.exceptions.NonexistentEntityException;
import beans.exceptions.PreexistingEntityException;
import beans.exceptions.RollbackFailureException;
import entities.CuotaCredito;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author BorisECornejo
 */
@ManagedBean(name = "cuotaCreditoController")
@ViewScoped
public class CuotaCreditoJpaController implements Serializable {
    
    @EJB 
    private facade.CuotaCreditoFacade cuotaCreditoFacade;
        
    private List<CuotaCredito> listadoCuotasCredito = new ArrayList<CuotaCredito>();
    private CuotaCredito selectedCuota;
    
    public CuotaCredito getSelectedCuota() {
        return selectedCuota;
    }

    public void setSelectedCuota(CuotaCredito selectedCuota) {
        this.selectedCuota = selectedCuota;
    }

    public List<CuotaCredito> getListadoCuotasCredito() {
        return listadoCuotasCredito;
    }

    public void setListadoCuotasCredito(List<CuotaCredito> listadoCuotasCredito) {
        this.listadoCuotasCredito = listadoCuotasCredito;
    }

    public CuotaCreditoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CuotaCreditoJpaController(){
    
    }
    
    @PostConstruct
    public void init() {
        llenarTabla();
    }

    public void create(CuotaCredito cuotaCredito) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(cuotaCredito);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCuotaCredito(cuotaCredito.getSecuencia()) != null) {
                throw new PreexistingEntityException("CuotaCredito " + cuotaCredito + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CuotaCredito cuotaCredito) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            cuotaCredito = em.merge(cuotaCredito);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cuotaCredito.getSecuencia();
                if (findCuotaCredito(id) == null) {
                    throw new NonexistentEntityException("The cuotaCredito with id " + id + " no longer exists.");
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
            CuotaCredito cuotaCredito;
            try {
                cuotaCredito = em.getReference(CuotaCredito.class, id);
                cuotaCredito.getSecuencia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cuotaCredito with id " + id + " no longer exists.", enfe);
            }
            em.remove(cuotaCredito);
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

    public List<CuotaCredito> findCuotaCreditoEntities() {
        return findCuotaCreditoEntities(true, -1, -1);
    }

    public List<CuotaCredito> findCuotaCreditoEntities(int maxResults, int firstResult) {
        return findCuotaCreditoEntities(false, maxResults, firstResult);
    }

    private List<CuotaCredito> findCuotaCreditoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CuotaCredito.class));
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

    public CuotaCredito findCuotaCredito(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CuotaCredito.class, id);
        } finally {
            em.close();
        }
    }

    public int getCuotaCreditoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CuotaCredito> rt = cq.from(CuotaCredito.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public void llenarTabla() {
        listadoCuotasCredito = cuotaCreditoFacade.findAll();
    }
    
}
