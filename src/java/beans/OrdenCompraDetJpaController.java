/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import beans.exceptions.NonexistentEntityException;
import beans.exceptions.PreexistingEntityException;
import beans.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.OrdenCompra;
import entities.OrdenCompraDet;
import entities.OrdenCompraDetPK;
import facade.OrdenCompraDetFacade;
import facade.OrdenCompraFacade;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Boris Cornejo
 */
@ManagedBean(name = "ordenCompraDetController")
@ViewScoped
public class OrdenCompraDetJpaController implements Serializable {

    @EJB
    private OrdenCompraDetFacade ordenCompraDetFacade;
    @EJB
    private OrdenCompraFacade ordenCompraFacade;
    private List<OrdenCompraDet> lstOrdenCompraDet;
    private List<OrdenCompra> lstOrdenCompra;
    private OrdenCompraDet ordenCompraDet;

    public List<OrdenCompraDet> getLstOrdenCompraDet() {
        return lstOrdenCompraDet;
    }

    public void setLstOrdenCompraDet(List<OrdenCompraDet> lstOrdenCompraDet) {
        this.lstOrdenCompraDet = lstOrdenCompraDet;
    }

    public List<OrdenCompra> getLstOrdenCompra() {
        return lstOrdenCompra;
    }

    public void setLstOrdenCompra(List<OrdenCompra> lstOrdenCompra) {
        this.lstOrdenCompra = lstOrdenCompra;
    }

    public OrdenCompraDet getOrdenCompraDet() {
        return ordenCompraDet;
    }

    public void setOrdenCompraDet(OrdenCompraDet ordenCompraDet) {
        this.ordenCompraDet = ordenCompraDet;
    }

    public OrdenCompraDetJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public OrdenCompraDetJpaController(){
    
    }

    @PostConstruct
    public void init() {
        lstOrdenCompraDet = new ArrayList<>();
        lstOrdenCompra = new ArrayList<>();
        ordenCompraDet = new  OrdenCompraDet();
        listarOrdenCompraDet();
    }

    public void create(OrdenCompraDet ordenCompraDet) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (ordenCompraDet.getOrdenCompraDetPK() == null) {
            ordenCompraDet.setOrdenCompraDetPK(new OrdenCompraDetPK());
        }
        ordenCompraDet.getOrdenCompraDetPK().setIdOrden(ordenCompraDet.getOrdenCompra().getIdOrden());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            OrdenCompra ordenCompra = ordenCompraDet.getOrdenCompra();
            if (ordenCompra != null) {
                ordenCompra = em.getReference(ordenCompra.getClass(), ordenCompra.getIdOrden());
                ordenCompraDet.setOrdenCompra(ordenCompra);
            }
            em.persist(ordenCompraDet);
            if (ordenCompra != null) {
                ordenCompra.getOrdenCompraDetList().add(ordenCompraDet);
                ordenCompra = em.merge(ordenCompra);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findOrdenCompraDet(ordenCompraDet.getOrdenCompraDetPK()) != null) {
                throw new PreexistingEntityException("OrdenCompraDet " + ordenCompraDet + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OrdenCompraDet ordenCompraDet) throws NonexistentEntityException, RollbackFailureException, Exception {
        ordenCompraDet.getOrdenCompraDetPK().setIdOrden(ordenCompraDet.getOrdenCompra().getIdOrden());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            OrdenCompraDet persistentOrdenCompraDet = em.find(OrdenCompraDet.class, ordenCompraDet.getOrdenCompraDetPK());
            OrdenCompra ordenCompraOld = persistentOrdenCompraDet.getOrdenCompra();
            OrdenCompra ordenCompraNew = ordenCompraDet.getOrdenCompra();
            if (ordenCompraNew != null) {
                ordenCompraNew = em.getReference(ordenCompraNew.getClass(), ordenCompraNew.getIdOrden());
                ordenCompraDet.setOrdenCompra(ordenCompraNew);
            }
            ordenCompraDet = em.merge(ordenCompraDet);
            if (ordenCompraOld != null && !ordenCompraOld.equals(ordenCompraNew)) {
                ordenCompraOld.getOrdenCompraDetList().remove(ordenCompraDet);
                ordenCompraOld = em.merge(ordenCompraOld);
            }
            if (ordenCompraNew != null && !ordenCompraNew.equals(ordenCompraOld)) {
                ordenCompraNew.getOrdenCompraDetList().add(ordenCompraDet);
                ordenCompraNew = em.merge(ordenCompraNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                OrdenCompraDetPK id = ordenCompraDet.getOrdenCompraDetPK();
                if (findOrdenCompraDet(id) == null) {
                    throw new NonexistentEntityException("The ordenCompraDet with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(OrdenCompraDetPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            OrdenCompraDet ordenCompraDet;
            try {
                ordenCompraDet = em.getReference(OrdenCompraDet.class, id);
                ordenCompraDet.getOrdenCompraDetPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ordenCompraDet with id " + id + " no longer exists.", enfe);
            }
            OrdenCompra ordenCompra = ordenCompraDet.getOrdenCompra();
            if (ordenCompra != null) {
                ordenCompra.getOrdenCompraDetList().remove(ordenCompraDet);
                ordenCompra = em.merge(ordenCompra);
            }
            em.remove(ordenCompraDet);
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

    public List<OrdenCompraDet> findOrdenCompraDetEntities() {
        return findOrdenCompraDetEntities(true, -1, -1);
    }

    public List<OrdenCompraDet> findOrdenCompraDetEntities(int maxResults, int firstResult) {
        return findOrdenCompraDetEntities(false, maxResults, firstResult);
    }

    private List<OrdenCompraDet> findOrdenCompraDetEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OrdenCompraDet.class));
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

    public OrdenCompraDet findOrdenCompraDet(OrdenCompraDetPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrdenCompraDet.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrdenCompraDetCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OrdenCompraDet> rt = cq.from(OrdenCompraDet.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public void listarOrdenCompraDet() {
        lstOrdenCompraDet = ordenCompraDetFacade.findAll();
    }

    public void filtrarOrdenCompraDet() {
        if (ordenCompraDet.getOrdenCompra().getIdOrden() != null) {
            lstOrdenCompraDet = ordenCompraDet.getOrdenCompra().getOrdenCompraDetList();
        } else {
            listarOrdenCompraDet();
        }

    }

}
