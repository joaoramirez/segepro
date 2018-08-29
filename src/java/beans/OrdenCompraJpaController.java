/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import beans.exceptions.IllegalOrphanException;
import beans.exceptions.NonexistentEntityException;
import beans.exceptions.PreexistingEntityException;
import beans.exceptions.RollbackFailureException;
import entities.Agencia;
import entities.OrdenCompra;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.OrdenCompraDet;
import entities.OrdenCompraDetPK;
import facade.AgenciaFacade;
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
@ManagedBean(name = "ordenCompraController")
@ViewScoped
public class OrdenCompraJpaController implements Serializable {

    @EJB
    private OrdenCompraFacade ordenCompraFacade;
    @EJB
    private AgenciaFacade agenciaFacade;
    private List<Agencia> lstAgencias;
    private OrdenCompra ordenCompra;
    private OrdenCompraDet ordenCompraDet;

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public OrdenCompraDet getOrdenCompraDet() {
        return ordenCompraDet;
    }

    public void setOrdenCompraDet(OrdenCompraDet ordenCompraDet) {
        this.ordenCompraDet = ordenCompraDet;
    }

    public List<Agencia> getLstAgencias() {
        return lstAgencias;
    }

    public void setLstAgencias(List<Agencia> lstAgencias) {
        this.lstAgencias = lstAgencias;
    }
        
    public OrdenCompraJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public OrdenCompraJpaController(){
    
    }
    
    @PostConstruct
    public void init() {
        lstAgencias = new ArrayList<>();
        ordenCompra = new OrdenCompra();
        ordenCompraDet = new OrdenCompraDet(new OrdenCompraDetPK());
        cargarAgencias();
    }

    public void create(OrdenCompra ordenCompra) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (ordenCompra.getOrdenCompraDetList() == null) {
            ordenCompra.setOrdenCompraDetList(new ArrayList<OrdenCompraDet>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<OrdenCompraDet> attachedOrdenCompraDetList = new ArrayList<OrdenCompraDet>();
            for (OrdenCompraDet ordenCompraDetListOrdenCompraDetToAttach : ordenCompra.getOrdenCompraDetList()) {
                ordenCompraDetListOrdenCompraDetToAttach = em.getReference(ordenCompraDetListOrdenCompraDetToAttach.getClass(), ordenCompraDetListOrdenCompraDetToAttach.getOrdenCompraDetPK());
                attachedOrdenCompraDetList.add(ordenCompraDetListOrdenCompraDetToAttach);
            }
            ordenCompra.setOrdenCompraDetList(attachedOrdenCompraDetList);
            em.persist(ordenCompra);
            for (OrdenCompraDet ordenCompraDetListOrdenCompraDet : ordenCompra.getOrdenCompraDetList()) {
                OrdenCompra oldOrdenCompraOfOrdenCompraDetListOrdenCompraDet = ordenCompraDetListOrdenCompraDet.getOrdenCompra();
                ordenCompraDetListOrdenCompraDet.setOrdenCompra(ordenCompra);
                ordenCompraDetListOrdenCompraDet = em.merge(ordenCompraDetListOrdenCompraDet);
                if (oldOrdenCompraOfOrdenCompraDetListOrdenCompraDet != null) {
                    oldOrdenCompraOfOrdenCompraDetListOrdenCompraDet.getOrdenCompraDetList().remove(ordenCompraDetListOrdenCompraDet);
                    oldOrdenCompraOfOrdenCompraDetListOrdenCompraDet = em.merge(oldOrdenCompraOfOrdenCompraDetListOrdenCompraDet);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findOrdenCompra(ordenCompra.getIdOrden()) != null) {
                throw new PreexistingEntityException("OrdenCompra " + ordenCompra + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OrdenCompra ordenCompra) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            OrdenCompra persistentOrdenCompra = em.find(OrdenCompra.class, ordenCompra.getIdOrden());
            List<OrdenCompraDet> ordenCompraDetListOld = persistentOrdenCompra.getOrdenCompraDetList();
            List<OrdenCompraDet> ordenCompraDetListNew = ordenCompra.getOrdenCompraDetList();
            List<String> illegalOrphanMessages = null;
            for (OrdenCompraDet ordenCompraDetListOldOrdenCompraDet : ordenCompraDetListOld) {
                if (!ordenCompraDetListNew.contains(ordenCompraDetListOldOrdenCompraDet)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OrdenCompraDet " + ordenCompraDetListOldOrdenCompraDet + " since its ordenCompra field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<OrdenCompraDet> attachedOrdenCompraDetListNew = new ArrayList<OrdenCompraDet>();
            for (OrdenCompraDet ordenCompraDetListNewOrdenCompraDetToAttach : ordenCompraDetListNew) {
                ordenCompraDetListNewOrdenCompraDetToAttach = em.getReference(ordenCompraDetListNewOrdenCompraDetToAttach.getClass(), ordenCompraDetListNewOrdenCompraDetToAttach.getOrdenCompraDetPK());
                attachedOrdenCompraDetListNew.add(ordenCompraDetListNewOrdenCompraDetToAttach);
            }
            ordenCompraDetListNew = attachedOrdenCompraDetListNew;
            ordenCompra.setOrdenCompraDetList(ordenCompraDetListNew);
            ordenCompra = em.merge(ordenCompra);
            for (OrdenCompraDet ordenCompraDetListNewOrdenCompraDet : ordenCompraDetListNew) {
                if (!ordenCompraDetListOld.contains(ordenCompraDetListNewOrdenCompraDet)) {
                    OrdenCompra oldOrdenCompraOfOrdenCompraDetListNewOrdenCompraDet = ordenCompraDetListNewOrdenCompraDet.getOrdenCompra();
                    ordenCompraDetListNewOrdenCompraDet.setOrdenCompra(ordenCompra);
                    ordenCompraDetListNewOrdenCompraDet = em.merge(ordenCompraDetListNewOrdenCompraDet);
                    if (oldOrdenCompraOfOrdenCompraDetListNewOrdenCompraDet != null && !oldOrdenCompraOfOrdenCompraDetListNewOrdenCompraDet.equals(ordenCompra)) {
                        oldOrdenCompraOfOrdenCompraDetListNewOrdenCompraDet.getOrdenCompraDetList().remove(ordenCompraDetListNewOrdenCompraDet);
                        oldOrdenCompraOfOrdenCompraDetListNewOrdenCompraDet = em.merge(oldOrdenCompraOfOrdenCompraDetListNewOrdenCompraDet);
                    }
                }
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
                Integer id = ordenCompra.getIdOrden();
                if (findOrdenCompra(id) == null) {
                    throw new NonexistentEntityException("The ordenCompra with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            OrdenCompra ordenCompra;
            try {
                ordenCompra = em.getReference(OrdenCompra.class, id);
                ordenCompra.getIdOrden();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ordenCompra with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<OrdenCompraDet> ordenCompraDetListOrphanCheck = ordenCompra.getOrdenCompraDetList();
            for (OrdenCompraDet ordenCompraDetListOrphanCheckOrdenCompraDet : ordenCompraDetListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OrdenCompra (" + ordenCompra + ") cannot be destroyed since the OrdenCompraDet " + ordenCompraDetListOrphanCheckOrdenCompraDet + " in its ordenCompraDetList field has a non-nullable ordenCompra field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(ordenCompra);
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

    public List<OrdenCompra> findOrdenCompraEntities() {
        return findOrdenCompraEntities(true, -1, -1);
    }

    public List<OrdenCompra> findOrdenCompraEntities(int maxResults, int firstResult) {
        return findOrdenCompraEntities(false, maxResults, firstResult);
    }

    private List<OrdenCompra> findOrdenCompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OrdenCompra.class));
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

    public OrdenCompra findOrdenCompra(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrdenCompra.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrdenCompraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OrdenCompra> rt = cq.from(OrdenCompra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public void guardarOrdenCompra() {
        try {
            ordenCompraFacade.create(ordenCompra);
        } catch (Exception e) {
            System.out.println("Error al guardar " + e);
        }
    }
    
    public void guardarOrdenCompraDet() {
        
        guardarOrdenCompra();
        
        if (ordenCompra != null) {
            ordenCompraDet.setOrdenCompra(ordenCompra);
            OrdenCompraDetPK od = new OrdenCompraDetPK();
            od.setIdOrden(ordenCompraDet.getOrdenCompraDetPK().getIdOrden());
            od.setSecuencia(1);
            ordenCompraDet.setOrdenCompraDetPK(od);
            ordenCompra.getOrdenCompraDetList().add(ordenCompraDet);
            ordenCompraDet = new OrdenCompraDet();
        }
    }
    
    public void cargarAgencias(){
        lstAgencias = agenciaFacade.findAll();
    }
    
}
