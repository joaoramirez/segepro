/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import beans.exceptions.NonexistentEntityException;
import beans.exceptions.PreexistingEntityException;
import beans.exceptions.RollbackFailureException;
import entities.SolicitudHist;
import entities.SolicitudHistPK;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author Boris Cornejo
 */
public class SolicitudHistJpaController implements Serializable {

    public SolicitudHistJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SolicitudHist solicitudHist) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (solicitudHist.getSolicitudHistPK() == null) {
            solicitudHist.setSolicitudHistPK(new SolicitudHistPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(solicitudHist);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findSolicitudHist(solicitudHist.getSolicitudHistPK()) != null) {
                throw new PreexistingEntityException("SolicitudHist " + solicitudHist + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SolicitudHist solicitudHist) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            solicitudHist = em.merge(solicitudHist);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                SolicitudHistPK id = solicitudHist.getSolicitudHistPK();
                if (findSolicitudHist(id) == null) {
                    throw new NonexistentEntityException("The solicitudHist with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(SolicitudHistPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            SolicitudHist solicitudHist;
            try {
                solicitudHist = em.getReference(SolicitudHist.class, id);
                solicitudHist.getSolicitudHistPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The solicitudHist with id " + id + " no longer exists.", enfe);
            }
            em.remove(solicitudHist);
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

    public List<SolicitudHist> findSolicitudHistEntities() {
        return findSolicitudHistEntities(true, -1, -1);
    }

    public List<SolicitudHist> findSolicitudHistEntities(int maxResults, int firstResult) {
        return findSolicitudHistEntities(false, maxResults, firstResult);
    }

    private List<SolicitudHist> findSolicitudHistEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SolicitudHist.class));
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

    public SolicitudHist findSolicitudHist(SolicitudHistPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SolicitudHist.class, id);
        } finally {
            em.close();
        }
    }

    public int getSolicitudHistCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SolicitudHist> rt = cq.from(SolicitudHist.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
