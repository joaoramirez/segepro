/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import entities.MonitoreoGeoposicional;
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
 * @author BorisECornejo
 */
public class MonitoreoGeoposicionalJpaController implements Serializable {

    public MonitoreoGeoposicionalJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MonitoreoGeoposicional monitoreoGeoposicional) throws Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(monitoreoGeoposicional);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new Exception("An error occurred attempting to roll back the transaction.", re);
            }
            if (findMonitoreoGeoposicional(monitoreoGeoposicional.getIdPunto()) != null) {
                throw new Exception("MonitoreoGeoposicional " + monitoreoGeoposicional + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MonitoreoGeoposicional monitoreoGeoposicional) throws Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            monitoreoGeoposicional = em.merge(monitoreoGeoposicional);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new Exception("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = monitoreoGeoposicional.getIdPunto();
                if (findMonitoreoGeoposicional(id) == null) {
                    throw new Exception("The monitoreoGeoposicional with id " + id + " no longer exists.");
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
            MonitoreoGeoposicional monitoreoGeoposicional;
            try {
                monitoreoGeoposicional = em.getReference(MonitoreoGeoposicional.class, id);
                monitoreoGeoposicional.getIdPunto();
            } catch (EntityNotFoundException enfe) {
                throw new Exception("The monitoreoGeoposicional with id " + id + " no longer exists.", enfe);
            }
            em.remove(monitoreoGeoposicional);
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

    public List<MonitoreoGeoposicional> findMonitoreoGeoposicionalEntities() {
        return findMonitoreoGeoposicionalEntities(true, -1, -1);
    }

    public List<MonitoreoGeoposicional> findMonitoreoGeoposicionalEntities(int maxResults, int firstResult) {
        return findMonitoreoGeoposicionalEntities(false, maxResults, firstResult);
    }

    private List<MonitoreoGeoposicional> findMonitoreoGeoposicionalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MonitoreoGeoposicional.class));
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

    public MonitoreoGeoposicional findMonitoreoGeoposicional(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MonitoreoGeoposicional.class, id);
        } finally {
            em.close();
        }
    }

    public int getMonitoreoGeoposicionalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MonitoreoGeoposicional> rt = cq.from(MonitoreoGeoposicional.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
