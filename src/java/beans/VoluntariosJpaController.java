/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import beans.exceptions.NonexistentEntityException;
import beans.exceptions.PreexistingEntityException;
import beans.exceptions.RollbackFailureException;
import entities.Brigadas;
import entities.Voluntarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
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
@ManagedBean(name = "voluntariosController")
@ViewScoped
public class VoluntariosJpaController implements Serializable {

    @EJB
    private facade.VoluntariosFacade voluntariosFacade;
    @EJB
    private facade.BrigadasFacade brigadaFacade;
    
    private List<Voluntarios> listadoVoluntarios = new ArrayList<Voluntarios>();
    private List<Brigadas> listadoBrigadas = new ArrayList<Brigadas>();
    private Voluntarios selectedVoluntarios;
    private int idvoluntario, idbrigada;
    private String nombres, apellidos, dui, nit, nacionalidad, numpasaporte;
    private Date fecha_nacimiento;
    
    private int idvoluntario_modal, idbrigada_modal;
    private String nombres_modal, apellidos_modal, dui_modal, nit_modal, nacionalidad_modal, numpasaporte_modal;
    private Date fecha_nacimiento_modal;

    public List<Voluntarios> getListadoVoluntarios() {
        return listadoVoluntarios;
    }

    public void setListadoVoluntarios(List<Voluntarios> listadoVoluntarios) {
        this.listadoVoluntarios = listadoVoluntarios;
    }
    
    public List<Brigadas> getListadoBrigadas() {
        return listadoBrigadas;
    }

    public void setListadoBrigadas(List<Brigadas> listadoBrigadas) {
        this.listadoBrigadas = listadoBrigadas;
    }

    public Voluntarios getSelectedVoluntarios() {
        return selectedVoluntarios;
    }

    public void setSelectedVoluntarios(Voluntarios selectedVoluntarios) {
        this.selectedVoluntarios = selectedVoluntarios;
    }

    public int getIdvoluntario() {
        return idvoluntario;
    }

    public void setIdvoluntario(int idvoluntario) {
        this.idvoluntario = idvoluntario;
    }

    public int getIdbrigada() {
        return idbrigada;
    }

    public void setIdbrigada(int idbrigada) {
        this.idbrigada = idbrigada;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getNumpasaporte() {
        return numpasaporte;
    }

    public void setNumpasaporte(String numpasaporte) {
        this.numpasaporte = numpasaporte;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public int getIdvoluntario_modal() {
        return idvoluntario_modal;
    }

    public void setIdvoluntario_modal(int idvoluntario_modal) {
        this.idvoluntario_modal = idvoluntario_modal;
    }

    public int getIdbrigada_modal() {
        return idbrigada_modal;
    }

    public void setIdbrigada_modal(int idbrigada_modal) {
        this.idbrigada_modal = idbrigada_modal;
    }

    public String getNombres_modal() {
        return nombres_modal;
    }

    public void setNombres_modal(String nombres_modal) {
        this.nombres_modal = nombres_modal;
    }

    public String getApellidos_modal() {
        return apellidos_modal;
    }

    public void setApellidos_modal(String apellidos_modal) {
        this.apellidos_modal = apellidos_modal;
    }

    public String getDui_modal() {
        return dui_modal;
    }

    public void setDui_modal(String dui_modal) {
        this.dui_modal = dui_modal;
    }

    public String getNit_modal() {
        return nit_modal;
    }

    public void setNit_modal(String nit_modal) {
        this.nit_modal = nit_modal;
    }

    public String getNacionalidad_modal() {
        return nacionalidad_modal;
    }

    public void setNacionalidad_modal(String nacionalidad_modal) {
        this.nacionalidad_modal = nacionalidad_modal;
    }

    public String getNumpasaporte_modal() {
        return numpasaporte_modal;
    }

    public void setNumpasaporte_modal(String numpasaporte_modal) {
        this.numpasaporte_modal = numpasaporte_modal;
    }

    public Date getFecha_nacimiento_modal() {
        return fecha_nacimiento_modal;
    }

    public void setFecha_nacimiento_modal(Date fecha_nacimiento_modal) {
        this.fecha_nacimiento_modal = fecha_nacimiento_modal;
    }
    
    public VoluntariosJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public VoluntariosJpaController(){
    
    }
    
    @PostConstruct
    public void init() {
        listadoBrigadas = brigadaFacade.findAll();
        generar();
        llenarTabla();
    }

    public void create(Voluntarios voluntarios) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(voluntarios);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findVoluntarios(voluntarios.getIdVoluntario()) != null) {
                throw new PreexistingEntityException("Voluntarios " + voluntarios + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Voluntarios voluntarios) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            voluntarios = em.merge(voluntarios);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = voluntarios.getIdVoluntario();
                if (findVoluntarios(id) == null) {
                    throw new NonexistentEntityException("The voluntarios with id " + id + " no longer exists.");
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
            Voluntarios voluntarios;
            try {
                voluntarios = em.getReference(Voluntarios.class, id);
                voluntarios.getIdVoluntario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The voluntarios with id " + id + " no longer exists.", enfe);
            }
            em.remove(voluntarios);
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

    public List<Voluntarios> findVoluntariosEntities() {
        return findVoluntariosEntities(true, -1, -1);
    }

    public List<Voluntarios> findVoluntariosEntities(int maxResults, int firstResult) {
        return findVoluntariosEntities(false, maxResults, firstResult);
    }

    private List<Voluntarios> findVoluntariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Voluntarios.class));
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

    public Voluntarios findVoluntarios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Voluntarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getVoluntariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Voluntarios> rt = cq.from(Voluntarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public void guardar() {
        try {
            Voluntarios v = new Voluntarios();
            v.setIdVoluntario(idvoluntario);
            v.setIdBrigada(idbrigada);
            v.setNombres(nombres);
            v.setApellidos(apellidos);
            v.setDui(dui);
            v.setNit(nit);
            v.setNumPasaporte(numpasaporte);
            v.setFechaNacimiento(fecha_nacimiento);
            v.setNacionalidad(nacionalidad);
            voluntariosFacade.create(v);
            FacesMessage msg = new FacesMessage("Se guardo el registro satisfactoriamente");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            limpiarForm();
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("No se pudo guardar el registro " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void limpiarForm() {
        idvoluntario = 0;
        idbrigada = 0;
        nombres = null;
        apellidos = null;
        dui = null;
        nit = null;
        numpasaporte = null;
        nacionalidad = null;
        fecha_nacimiento = null;
        generar();
        llenarTabla();
    }

    public void generar() {
        try {
        idvoluntario = (int) voluntariosFacade.listaVoluntarios();
        }catch(Exception e) {
            idvoluntario = 1;
        }
    }
    
    public void llenarTabla() {
        listadoVoluntarios = voluntariosFacade.findAll();
    }
        
    public void onRowSelectVoluntarios(SelectEvent event) {
        String codigo_voluntario = String.valueOf(selectedVoluntarios.getIdVoluntario());
        FacesMessage msg = new FacesMessage("Codigo Marcado", codigo_voluntario);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        idbrigada_modal = selectedVoluntarios.getIdBrigada();
        idvoluntario_modal = selectedVoluntarios.getIdVoluntario();
        nombres_modal = selectedVoluntarios.getNombres();
        apellidos_modal = selectedVoluntarios.getApellidos();
        dui_modal = selectedVoluntarios.getDui();
        nit_modal = selectedVoluntarios.getNit();
        numpasaporte_modal = selectedVoluntarios.getNumPasaporte();
        nacionalidad_modal = selectedVoluntarios.getNacionalidad();
        fecha_nacimiento_modal = selectedVoluntarios.getFechaNacimiento();
    }
    
    public void actualizaVoluntarios() {
        try {
            selectedVoluntarios.setIdBrigada(idbrigada_modal);
            selectedVoluntarios.setIdVoluntario(idbrigada_modal);
            selectedVoluntarios.setNombres(nombres_modal);
            selectedVoluntarios.setApellidos(apellidos_modal);
            selectedVoluntarios.setDui(dui_modal);
            selectedVoluntarios.setNit(nit_modal);
            selectedVoluntarios.setNumPasaporte(numpasaporte_modal);
            selectedVoluntarios.setNacionalidad(nacionalidad_modal);
            selectedVoluntarios.setFechaNacimiento(fecha_nacimiento_modal);
            voluntariosFacade.edit(selectedVoluntarios);
            llenarTabla();
            FacesMessage msgInfo = new FacesMessage("Registro actualizar con exito!");
            FacesContext.getCurrentInstance().addMessage(null, msgInfo);
        } catch (Exception e) {
            FacesMessage msgError = new FacesMessage("No se pudo actualizar el registro " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msgError);
        }

    }
}
