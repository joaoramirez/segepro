/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import beans.exceptions.NonexistentEntityException;
import beans.exceptions.PreexistingEntityException;
import beans.exceptions.RollbackFailureException;
import entities.Agencia;
import entities.Brigadas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author BorisECornejo
 */
@ManagedBean(name = "brigadasController")
@ViewScoped
public class BrigadasJpaController implements Serializable {
    
    @EJB
    private facade.BrigadasFacade brigadaFacade;
    @EJB
    private facade.AgenciaFacade agenciaFacade;
    
    private List<Brigadas> listadoBrigadas = new ArrayList<Brigadas>();
    private Brigadas selectedBrigadas;
    private int idbrigada,idagencia, cant_integrantes;
    private String nombre, tipo_brigada;
    private Date fecha_inicio, fecha_fin;
    private List<Agencia> listadoAgencia = new ArrayList<Agencia>();
    
    private int idbrigada_modal,idagencia_modal, cant_integrantes_modal;
    private String nombre_modal, tipo_brigada_modal;
    private Date fecha_inicio_modal, fecha_fin_modal;

    public List<Agencia> getListadoAgencia() {
        return listadoAgencia;
    }

    public void setListadoAgencia(List<Agencia> listadoAgencia) {
        this.listadoAgencia = listadoAgencia;
    }

    public List<Brigadas> getListadoBrigadas() {
        return listadoBrigadas;
    }

    public void setListadoBrigadas(List<Brigadas> listadoBrigadas) {
        this.listadoBrigadas = listadoBrigadas;
    }

    public Brigadas getSelectedBrigadas() {
        return selectedBrigadas;
    }

    public void setSelectedBrigadas(Brigadas selectedBrigadas) {
        this.selectedBrigadas = selectedBrigadas;
    }

    public int getIdbrigada() {
        return idbrigada;
    }

    public void setIdbrigada(int idbrigada) {
        this.idbrigada = idbrigada;
    }

    public int getIdagencia() {
        return idagencia;
    }

    public void setIdagencia(int idagencia) {
        this.idagencia = idagencia;
    }

    public int getCant_integrantes() {
        return cant_integrantes;
    }

    public void setCant_integrantes(int cant_integrantes) {
        this.cant_integrantes = cant_integrantes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo_brigada() {
        return tipo_brigada;
    }

    public void setTipo_brigada(String tipo_brigada) {
        this.tipo_brigada = tipo_brigada;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public int getIdbrigada_modal() {
        return idbrigada_modal;
    }

    public void setIdbrigada_modal(int idbrigada_modal) {
        this.idbrigada_modal = idbrigada_modal;
    }

    public int getIdagencia_modal() {
        return idagencia_modal;
    }

    public void setIdagencia_modal(int idagencia_modal) {
        this.idagencia_modal = idagencia_modal;
    }

    public int getCant_integrantes_modal() {
        return cant_integrantes_modal;
    }

    public void setCant_integrantes_modal(int cant_integrantes_modal) {
        this.cant_integrantes_modal = cant_integrantes_modal;
    }

    public String getNombre_modal() {
        return nombre_modal;
    }

    public void setNombre_modal(String nombre_modal) {
        this.nombre_modal = nombre_modal;
    }

    public String getTipo_brigada_modal() {
        return tipo_brigada_modal;
    }

    public void setTipo_brigada_modal(String tipo_brigada_modal) {
        this.tipo_brigada_modal = tipo_brigada_modal;
    }

    public Date getFecha_inicio_modal() {
        return fecha_inicio_modal;
    }

    public void setFecha_inicio_modal(Date fecha_inicio_modal) {
        this.fecha_inicio_modal = fecha_inicio_modal;
    }

    public Date getFecha_fin_modal() {
        return fecha_fin_modal;
    }

    public void setFecha_fin_modal(Date fecha_fin_modal) {
        this.fecha_fin_modal = fecha_fin_modal;
    }
    
    public BrigadasJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public BrigadasJpaController(){
    
    }
    
    @PostConstruct
    public void init() {
        listadoAgencia = agenciaFacade.listaAgencias();
        generar();
        llenarTabla();
    }

    public void create(Brigadas brigadas) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(brigadas);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findBrigadas(brigadas.getIdBrigada()) != null) {
                throw new PreexistingEntityException("Brigadas " + brigadas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Brigadas brigadas) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            brigadas = em.merge(brigadas);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = brigadas.getIdBrigada();
                if (findBrigadas(id) == null) {
                    throw new NonexistentEntityException("The brigadas with id " + id + " no longer exists.");
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
            Brigadas brigadas;
            try {
                brigadas = em.getReference(Brigadas.class, id);
                brigadas.getIdBrigada();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The brigadas with id " + id + " no longer exists.", enfe);
            }
            em.remove(brigadas);
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

    public List<Brigadas> findBrigadasEntities() {
        return findBrigadasEntities(true, -1, -1);
    }

    public List<Brigadas> findBrigadasEntities(int maxResults, int firstResult) {
        return findBrigadasEntities(false, maxResults, firstResult);
    }

    private List<Brigadas> findBrigadasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Brigadas.class));
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

    public Brigadas findBrigadas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Brigadas.class, id);
        } finally {
            em.close();
        }
    }

    public int getBrigadasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Brigadas> rt = cq.from(Brigadas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public void guardar() {
        try {
            Brigadas b = new Brigadas();
            b.setIdBrigada(idbrigada);
            b.setNomre(nombre);
            b.setIdAgencia(idagencia);
            b.setCantIntegrantes(cant_integrantes);
            b.setFechaInic(fecha_inicio);
            b.setFechaFin(fecha_fin);
            b.setTipoBrigada(tipo_brigada);
            brigadaFacade.create(b);
            FacesMessage msg = new FacesMessage("Se guardo el registro satisfactoriamente");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            limpiarForm();
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("No se pudo guardar el registro " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void limpiarForm() {
        idbrigada = 0;
        nombre = null;
        idagencia = 0;
        cant_integrantes = 0;
        fecha_inicio = null;
        fecha_fin = null;
        tipo_brigada = null;
        generar();
        llenarTabla();
    }

    public void generar() {
        try {
        idbrigada = (int) brigadaFacade.listaBrigada();
        }catch(Exception e) {
            idbrigada = 1;
        }
    }
    
    public void llenarTabla() {
        listadoBrigadas = brigadaFacade.findAll();
    }
    
    public void onRowSelectBrigada(SelectEvent event) {
        String codigo_brigada = String.valueOf(selectedBrigadas.getIdBrigada());
        FacesMessage msg = new FacesMessage("Codigo Marcado", codigo_brigada);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        idbrigada_modal = selectedBrigadas.getIdBrigada();
        idagencia_modal = selectedBrigadas.getIdAgencia();
        nombre_modal = selectedBrigadas.getNomre();
        cant_integrantes_modal = selectedBrigadas.getCantIntegrantes();
        tipo_brigada_modal = selectedBrigadas.getTipoBrigada();
        fecha_inicio_modal = selectedBrigadas.getFechaInic();
        fecha_fin_modal = selectedBrigadas.getFechaFin();
    }
    
    public void actualizaBrigada() {
        try {
            selectedBrigadas.setIdBrigada(idbrigada_modal);
            selectedBrigadas.setIdAgencia(idagencia_modal);
            selectedBrigadas.setNomre(nombre_modal);
            selectedBrigadas.setCantIntegrantes(cant_integrantes_modal);
            selectedBrigadas.setTipoBrigada(tipo_brigada_modal);
            selectedBrigadas.setFechaInic(fecha_inicio_modal);
            selectedBrigadas.setFechaFin(fecha_fin_modal);
            brigadaFacade.edit(selectedBrigadas);
            llenarTabla();
            FacesMessage msgInfo = new FacesMessage("Registro actualizar con exito!");
            FacesContext.getCurrentInstance().addMessage(null, msgInfo);
        } catch (Exception e) {
            FacesMessage msgError = new FacesMessage("No se pudo actualizar el registro " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msgError);
        }

    }
    
}
