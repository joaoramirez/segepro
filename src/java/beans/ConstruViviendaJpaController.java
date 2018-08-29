/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import entities.ConstruVivienda;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Expediente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author BorisECornejo
 */
public class ConstruViviendaJpaController implements Serializable {

    public ConstruViviendaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ConstruVivienda construVivienda) throws Exception {
        if (construVivienda.getExpedienteList() == null) {
            construVivienda.setExpedienteList(new ArrayList<Expediente>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Expediente> attachedExpedienteList = new ArrayList<Expediente>();
            for (Expediente expedienteListExpedienteToAttach : construVivienda.getExpedienteList()) {
                expedienteListExpedienteToAttach = em.getReference(expedienteListExpedienteToAttach.getClass(), expedienteListExpedienteToAttach.getNumExpediente());
                attachedExpedienteList.add(expedienteListExpedienteToAttach);
            }
            construVivienda.setExpedienteList(attachedExpedienteList);
            em.persist(construVivienda);
            for (Expediente expedienteListExpediente : construVivienda.getExpedienteList()) {
                ConstruVivienda oldIdViviendaOfExpedienteListExpediente = expedienteListExpediente.getIdVivienda();
                expedienteListExpediente.setIdVivienda(construVivienda);
                expedienteListExpediente = em.merge(expedienteListExpediente);
                if (oldIdViviendaOfExpedienteListExpediente != null) {
                    oldIdViviendaOfExpedienteListExpediente.getExpedienteList().remove(expedienteListExpediente);
                    oldIdViviendaOfExpedienteListExpediente = em.merge(oldIdViviendaOfExpedienteListExpediente);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new Exception("An error occurred attempting to roll back the transaction.", re);
            }
            if (findConstruVivienda(construVivienda.getIdVivienda()) != null) {
                throw new Exception("ConstruVivienda " + construVivienda + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ConstruVivienda construVivienda) throws Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ConstruVivienda persistentConstruVivienda = em.find(ConstruVivienda.class, construVivienda.getIdVivienda());
            List<Expediente> expedienteListOld = persistentConstruVivienda.getExpedienteList();
            List<Expediente> expedienteListNew = construVivienda.getExpedienteList();
            List<String> illegalOrphanMessages = null;
            for (Expediente expedienteListOldExpediente : expedienteListOld) {
                if (!expedienteListNew.contains(expedienteListOldExpediente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Expediente " + expedienteListOldExpediente + " since its idVivienda field is not nullable.");
                }
            }
            List<Expediente> attachedExpedienteListNew = new ArrayList<Expediente>();
            for (Expediente expedienteListNewExpedienteToAttach : expedienteListNew) {
                expedienteListNewExpedienteToAttach = em.getReference(expedienteListNewExpedienteToAttach.getClass(), expedienteListNewExpedienteToAttach.getNumExpediente());
                attachedExpedienteListNew.add(expedienteListNewExpedienteToAttach);
            }
            expedienteListNew = attachedExpedienteListNew;
            construVivienda.setExpedienteList(expedienteListNew);
            construVivienda = em.merge(construVivienda);
            for (Expediente expedienteListNewExpediente : expedienteListNew) {
                if (!expedienteListOld.contains(expedienteListNewExpediente)) {
                    ConstruVivienda oldIdViviendaOfExpedienteListNewExpediente = expedienteListNewExpediente.getIdVivienda();
                    expedienteListNewExpediente.setIdVivienda(construVivienda);
                    expedienteListNewExpediente = em.merge(expedienteListNewExpediente);
                    if (oldIdViviendaOfExpedienteListNewExpediente != null && !oldIdViviendaOfExpedienteListNewExpediente.equals(construVivienda)) {
                        oldIdViviendaOfExpedienteListNewExpediente.getExpedienteList().remove(expedienteListNewExpediente);
                        oldIdViviendaOfExpedienteListNewExpediente = em.merge(oldIdViviendaOfExpedienteListNewExpediente);
                    }
                }
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
                Integer id = construVivienda.getIdVivienda();
                if (findConstruVivienda(id) == null) {
                    throw new Exception("The construVivienda with id " + id + " no longer exists.");
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
            ConstruVivienda construVivienda;
            try {
                construVivienda = em.getReference(ConstruVivienda.class, id);
                construVivienda.getIdVivienda();
            } catch (EntityNotFoundException enfe) {
                throw new Exception("The construVivienda with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Expediente> expedienteListOrphanCheck = construVivienda.getExpedienteList();
            for (Expediente expedienteListOrphanCheckExpediente : expedienteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ConstruVivienda (" + construVivienda + ") cannot be destroyed since the Expediente " + expedienteListOrphanCheckExpediente + " in its expedienteList field has a non-nullable idVivienda field.");
            }
            em.remove(construVivienda);
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

    public List<ConstruVivienda> findConstruViviendaEntities() {
        return findConstruViviendaEntities(true, -1, -1);
    }

    public List<ConstruVivienda> findConstruViviendaEntities(int maxResults, int firstResult) {
        return findConstruViviendaEntities(false, maxResults, firstResult);
    }

    private List<ConstruVivienda> findConstruViviendaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ConstruVivienda.class));
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

    public ConstruVivienda findConstruVivienda(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ConstruVivienda.class, id);
        } finally {
            em.close();
        }
    }

    public int getConstruViviendaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ConstruVivienda> rt = cq.from(ConstruVivienda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
