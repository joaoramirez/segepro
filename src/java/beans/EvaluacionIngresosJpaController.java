/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import beans.exceptions.NonexistentEntityException;
import beans.exceptions.RollbackFailureException;
import entities.EvaluacionIngresos;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@ManagedBean(name = "evaluacionIngresosController")
@ViewScoped
public class EvaluacionIngresosJpaController implements Serializable {

    @EJB
    private facade.EvaluacionIngresosFacade evaluacionIngresosFacade;

    private BigDecimal total1 = new BigDecimal(BigInteger.ZERO);
    private BigDecimal total2 = new BigDecimal(BigInteger.ZERO);
    private BigDecimal total = new BigDecimal(BigInteger.ZERO);
    private BigDecimal alimentacion = new BigDecimal(BigInteger.ZERO);
    private BigDecimal educacion = new BigDecimal(BigInteger.ZERO);
    private BigDecimal transporte = new BigDecimal(BigInteger.ZERO);
    private BigDecimal salud = new BigDecimal(BigInteger.ZERO);
    private BigDecimal servicios = new BigDecimal(BigInteger.ZERO);
    private BigDecimal alquiler = new BigDecimal(BigInteger.ZERO);
    private BigDecimal recreacion = new BigDecimal(BigInteger.ZERO);
    private BigDecimal creditos = new BigDecimal(BigInteger.ZERO);
    private BigDecimal descuentos = new BigDecimal(BigInteger.ZERO);
    private BigDecimal ingreso = new BigDecimal(BigInteger.ZERO);
    private BigDecimal otros = new BigDecimal(BigInteger.ZERO);
    private BigDecimal monto = new BigDecimal(BigInteger.ZERO);
    private BigDecimal supervabit = new BigDecimal(BigInteger.ZERO);
    private BigDecimal deficit = new BigDecimal(BigInteger.ZERO);

    public BigDecimal getTotal1() {
        return total1;
    }

    public void setTotal1(BigDecimal total1) {
        this.total1 = total1;
    }

    public BigDecimal getTotal2() {
        return total2;
    }

    public void setTotal2(BigDecimal total2) {
        this.total2 = total2;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getAlimentacion() {
        return alimentacion;
    }

    public void setAlimentacion(BigDecimal alimentacion) {
        this.alimentacion = alimentacion;
    }

    public BigDecimal getEducacion() {
        return educacion;
    }

    public void setEducacion(BigDecimal educacion) {
        this.educacion = educacion;
    }

    public BigDecimal getTransporte() {
        return transporte;
    }

    public void setTransporte(BigDecimal transporte) {
        this.transporte = transporte;
    }

    public BigDecimal getSalud() {
        return salud;
    }

    public void setSalud(BigDecimal salud) {
        this.salud = salud;
    }

    public BigDecimal getServicios() {
        return servicios;
    }

    public void setServicios(BigDecimal servicios) {
        this.servicios = servicios;
    }

    public BigDecimal getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(BigDecimal alquiler) {
        this.alquiler = alquiler;
    }

    public BigDecimal getRecreacion() {
        return recreacion;
    }

    public void setRecreacion(BigDecimal recreacion) {
        this.recreacion = recreacion;
    }

    public BigDecimal getCreditos() {
        return creditos;
    }

    public void setCreditos(BigDecimal creditos) {
        this.creditos = creditos;
    }

    public BigDecimal getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(BigDecimal descuentos) {
        this.descuentos = descuentos;
    }

    public BigDecimal getIngreso() {
        return ingreso;
    }

    public void setIngreso(BigDecimal ingreso) {
        this.ingreso = ingreso;
    }

    public BigDecimal getOtros() {
        return otros;
    }

    public void setOtros(BigDecimal otros) {
        this.otros = otros;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getSupervabit() {
        return supervabit;
    }

    public void setSupervabit(BigDecimal supervabit) {
        this.supervabit = supervabit;
    }

    public BigDecimal getDeficit() {
        return deficit;
    }

    public void setDeficit(BigDecimal deficit) {
        this.deficit = deficit;
    }
    
    public EvaluacionIngresosJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public EvaluacionIngresosJpaController() {

    }

    @PostConstruct
    public void init() {

    }

    public void create(EvaluacionIngresos evaluacionIngresos) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(evaluacionIngresos);
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

    public void edit(EvaluacionIngresos evaluacionIngresos) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            evaluacionIngresos = em.merge(evaluacionIngresos);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = evaluacionIngresos.getCodEvaluacion();
                if (findEvaluacionIngresos(id) == null) {
                    throw new NonexistentEntityException("The evaluacionIngresos with id " + id + " no longer exists.");
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
            EvaluacionIngresos evaluacionIngresos;
            try {
                evaluacionIngresos = em.getReference(EvaluacionIngresos.class, id);
                evaluacionIngresos.getCodEvaluacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The evaluacionIngresos with id " + id + " no longer exists.", enfe);
            }
            em.remove(evaluacionIngresos);
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

    public List<EvaluacionIngresos> findEvaluacionIngresosEntities() {
        return findEvaluacionIngresosEntities(true, -1, -1);
    }

    public List<EvaluacionIngresos> findEvaluacionIngresosEntities(int maxResults, int firstResult) {
        return findEvaluacionIngresosEntities(false, maxResults, firstResult);
    }

    private List<EvaluacionIngresos> findEvaluacionIngresosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EvaluacionIngresos.class));
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

    public EvaluacionIngresos findEvaluacionIngresos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EvaluacionIngresos.class, id);
        } finally {
            em.close();
        }
    }

    public int getEvaluacionIngresosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EvaluacionIngresos> rt = cq.from(EvaluacionIngresos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public void calcular() {
        total1 = total1.add(alimentacion).add(educacion).add(transporte).add(salud).add(servicios).add(alquiler).add(recreacion).add(creditos).add(descuentos);
        total2 = total2.add(ingreso).add(otros);
        total = total2.subtract(total1);
        total = total.subtract(monto);
        if (total.doubleValue() > 0) {
            supervabit = total;
        }   else {
                deficit = total;
                    }

    }

    public void limpiarForm() {
        total1 = BigDecimal.ZERO;
        total2 = BigDecimal.ZERO;
        total = BigDecimal.ZERO;
        alimentacion = BigDecimal.ZERO;
        educacion = BigDecimal.ZERO;
        transporte = BigDecimal.ZERO;
        salud = BigDecimal.ZERO;
        servicios = BigDecimal.ZERO;
        alquiler = BigDecimal.ZERO;
        recreacion = BigDecimal.ZERO;
        creditos = BigDecimal.ZERO;
        descuentos = BigDecimal.ZERO;
        ingreso = BigDecimal.ZERO;
        otros = BigDecimal.ZERO;
        monto = BigDecimal.ZERO;
        supervabit = BigDecimal.ZERO;
        deficit = BigDecimal.ZERO;
    }
    
}
