/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import entities.Sav;
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
@ManagedBean(name = "savController")
@ViewScoped
public class SavJpaController implements Serializable {

    public SavJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sav sav) throws Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(sav);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new Exception("An error occurred attempting to roll back the transaction.", re);
            }
            if (findSav(sav.getIdSav()) != null) {
                throw new Exception("Sav " + sav + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sav sav) throws Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            sav = em.merge(sav);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new Exception("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sav.getIdSav();
                if (findSav(id) == null) {
                    throw new Exception("The sav with id " + id + " no longer exists.");
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
            Sav sav;
            try {
                sav = em.getReference(Sav.class, id);
                sav.getIdSav();
            } catch (EntityNotFoundException enfe) {
                throw new Exception("The sav with id " + id + " no longer exists.", enfe);
            }
            em.remove(sav);
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

    public List<Sav> findSavEntities() {
        return findSavEntities(true, -1, -1);
    }

    public List<Sav> findSavEntities(int maxResults, int firstResult) {
        return findSavEntities(false, maxResults, firstResult);
    }

    private List<Sav> findSavEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sav.class));
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

    public Sav findSav(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sav.class, id);
        } finally {
            em.close();
        }
    }

    public int getSavCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sav> rt = cq.from(Sav.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
