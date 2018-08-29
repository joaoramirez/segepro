/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import entities.RecordCred;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
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
@ManagedBean(name = "recordCredController")
@ViewScoped
public class RecordCredJpaController implements Serializable {

    public RecordCredJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RecordCred recordCred) throws Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(recordCred);
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

    public void edit(RecordCred recordCred) throws Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            recordCred = em.merge(recordCred);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new Exception("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = recordCred.getSecuencia();
                if (findRecordCred(id) == null) {
                    throw new Exception("The recordCred with id " + id + " no longer exists.");
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
            RecordCred recordCred;
            try {
                recordCred = em.getReference(RecordCred.class, id);
                recordCred.getSecuencia();
            } catch (EntityNotFoundException enfe) {
                throw new Exception("The recordCred with id " + id + " no longer exists.", enfe);
            }
            em.remove(recordCred);
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

    public List<RecordCred> findRecordCredEntities() {
        return findRecordCredEntities(true, -1, -1);
    }

    public List<RecordCred> findRecordCredEntities(int maxResults, int firstResult) {
        return findRecordCredEntities(false, maxResults, firstResult);
    }

    private List<RecordCred> findRecordCredEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RecordCred.class));
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

    public RecordCred findRecordCred(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RecordCred.class, id);
        } finally {
            em.close();
        }
    }

    public int getRecordCredCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RecordCred> rt = cq.from(RecordCred.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
