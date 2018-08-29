/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Expediente;
import entities.Presupuesto;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author BorisECornejo
 */
@ManagedBean(name = "presupuestoController")
@ViewScoped
public class PresupuestoJpaController implements Serializable {

    public PresupuestoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Presupuesto presupuesto) throws Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Expediente numExpediente = presupuesto.getNumExpediente();
            if (numExpediente != null) {
                numExpediente = em.getReference(numExpediente.getClass(), numExpediente.getNumExpediente());
                presupuesto.setNumExpediente(numExpediente);
            }
            em.persist(presupuesto);
            if (numExpediente != null) {
                numExpediente.getPresupuestoList().add(presupuesto);
                numExpediente = em.merge(numExpediente);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new Exception("An error occurred attempting to roll back the transaction.", re);
            }
            if (findPresupuesto(presupuesto.getIdPresupuesto()) != null) {
                throw new Exception("Presupuesto " + presupuesto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Presupuesto presupuesto) throws Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Presupuesto persistentPresupuesto = em.find(Presupuesto.class, presupuesto.getIdPresupuesto());
            Expediente numExpedienteOld = persistentPresupuesto.getNumExpediente();
            Expediente numExpedienteNew = presupuesto.getNumExpediente();
            if (numExpedienteNew != null) {
                numExpedienteNew = em.getReference(numExpedienteNew.getClass(), numExpedienteNew.getNumExpediente());
                presupuesto.setNumExpediente(numExpedienteNew);
            }
            presupuesto = em.merge(presupuesto);
            if (numExpedienteOld != null && !numExpedienteOld.equals(numExpedienteNew)) {
                numExpedienteOld.getPresupuestoList().remove(presupuesto);
                numExpedienteOld = em.merge(numExpedienteOld);
            }
            if (numExpedienteNew != null && !numExpedienteNew.equals(numExpedienteOld)) {
                numExpedienteNew.getPresupuestoList().add(presupuesto);
                numExpedienteNew = em.merge(numExpedienteNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new Exception("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = presupuesto.getIdPresupuesto();
                if (findPresupuesto(id) == null) {
                    throw new Exception("The presupuesto with id " + id + " no longer exists.");
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
            Presupuesto presupuesto;
            try {
                presupuesto = em.getReference(Presupuesto.class, id);
                presupuesto.getIdPresupuesto();
            } catch (EntityNotFoundException enfe) {
                throw new Exception("The presupuesto with id " + id + " no longer exists.", enfe);
            }
            Expediente numExpediente = presupuesto.getNumExpediente();
            if (numExpediente != null) {
                numExpediente.getPresupuestoList().remove(presupuesto);
                numExpediente = em.merge(numExpediente);
            }
            em.remove(presupuesto);
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

    public List<Presupuesto> findPresupuestoEntities() {
        return findPresupuestoEntities(true, -1, -1);
    }

    public List<Presupuesto> findPresupuestoEntities(int maxResults, int firstResult) {
        return findPresupuestoEntities(false, maxResults, firstResult);
    }

    private List<Presupuesto> findPresupuestoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Presupuesto.class));
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

    public Presupuesto findPresupuesto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Presupuesto.class, id);
        } finally {
            em.close();
        }
    }

    public int getPresupuestoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Presupuesto> rt = cq.from(Presupuesto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
