/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import beans.exceptions.NonexistentEntityException;
import beans.exceptions.PreexistingEntityException;
import beans.exceptions.RollbackFailureException;
import entities.Credito;
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
@ManagedBean(name = "creditoController")
@ViewScoped
public class CreditoJpaController implements Serializable {

    @EJB
    private facade.CreditoFacade creditoFacade;
    
    private List<Credito> listadoCredito = new ArrayList<Credito>();
    private Credito selectedCredito;

    public Credito getSelectedCredito() {
        return selectedCredito;
    }

    public void setSelectedCredito(Credito selectedCredito) {
        this.selectedCredito = selectedCredito;
    }

    public List<Credito> getListadoCredito() {
        return listadoCredito;
    }

    public void setListadoCredito(List<Credito> listadoCredito) {
        this.listadoCredito = listadoCredito;
    }
    
    public CreditoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CreditoJpaController(){
    
    }
    
    @PostConstruct
    public void init() {
        llenarTabla();
    }

    public void create(Credito credito) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(credito);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCredito(credito.getNumCredito()) != null) {
                throw new PreexistingEntityException("Credito " + credito + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Credito credito) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            credito = em.merge(credito);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = credito.getNumCredito();
                if (findCredito(id) == null) {
                    throw new NonexistentEntityException("The credito with id " + id + " no longer exists.");
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
            Credito credito;
            try {
                credito = em.getReference(Credito.class, id);
                credito.getNumCredito();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The credito with id " + id + " no longer exists.", enfe);
            }
            em.remove(credito);
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

    public List<Credito> findCreditoEntities() {
        return findCreditoEntities(true, -1, -1);
    }

    public List<Credito> findCreditoEntities(int maxResults, int firstResult) {
        return findCreditoEntities(false, maxResults, firstResult);
    }

    private List<Credito> findCreditoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Credito.class));
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

    public Credito findCredito(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Credito.class, id);
        } finally {
            em.close();
        }
    }

    public int getCreditoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Credito> rt = cq.from(Credito.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public void llenarTabla() {
        listadoCredito = creditoFacade.findAll();
        //listadoCuotasCredito = cuotaCreditoFacade.findAll();
    }
    
}
