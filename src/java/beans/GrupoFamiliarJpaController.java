/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import entities.GrupoFamiliar;
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
public class GrupoFamiliarJpaController implements Serializable {

    public GrupoFamiliarJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(GrupoFamiliar grupoFamiliar) throws Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(grupoFamiliar);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new Exception("An error occurred attempting to roll back the transaction.", re);
            }
            if (findGrupoFamiliar(grupoFamiliar.getCodGrupo()) != null) {
                throw new Exception("GrupoFamiliar " + grupoFamiliar + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(GrupoFamiliar grupoFamiliar) throws Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            grupoFamiliar = em.merge(grupoFamiliar);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new Exception("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = grupoFamiliar.getCodGrupo();
                if (findGrupoFamiliar(id) == null) {
                    throw new Exception("The grupoFamiliar with id " + id + " no longer exists.");
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
            GrupoFamiliar grupoFamiliar;
            try {
                grupoFamiliar = em.getReference(GrupoFamiliar.class, id);
                grupoFamiliar.getCodGrupo();
            } catch (EntityNotFoundException enfe) {
                throw new Exception("The grupoFamiliar with id " + id + " no longer exists.", enfe);
            }
            em.remove(grupoFamiliar);
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

    public List<GrupoFamiliar> findGrupoFamiliarEntities() {
        return findGrupoFamiliarEntities(true, -1, -1);
    }

    public List<GrupoFamiliar> findGrupoFamiliarEntities(int maxResults, int firstResult) {
        return findGrupoFamiliarEntities(false, maxResults, firstResult);
    }

    private List<GrupoFamiliar> findGrupoFamiliarEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(GrupoFamiliar.class));
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

    public GrupoFamiliar findGrupoFamiliar(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GrupoFamiliar.class, id);
        } finally {
            em.close();
        }
    }

    public int getGrupoFamiliarCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<GrupoFamiliar> rt = cq.from(GrupoFamiliar.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
