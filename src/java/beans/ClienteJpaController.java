/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import beans.exceptions.NonexistentEntityException;
import beans.exceptions.PreexistingEntityException;
import beans.exceptions.RollbackFailureException;
import entities.Cliente;
import entities.Profesiones;
import java.io.Serializable;
import java.math.BigDecimal;
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

/**
 *
 * @author BorisECornejo
 */
@ManagedBean(name = "clienteController")
@ViewScoped
public class ClienteJpaController implements Serializable {
    
    @EJB
    private facade.ClienteFacade clienteFacade;
    
    private int idCliente;
    private String nombre,apellido,dui,nit,estado_civil,sexo,lugarExp,lugarNac,telefono,profesion,direccion,punto_ref,reside_desde,condicionVivienda,lugarTrabajo,jefeInmediato,tiempoEmpleo,fiador;
    private Date fecExp, fecNac;
    private BigDecimal salario;
    
    private List<Cliente> listadoCliente = new ArrayList<Cliente>();
    private List<Profesiones> listadoProfesion = new ArrayList<Profesiones>();
    private Cliente seletedCliente;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getLugarExp() {
        return lugarExp;
    }

    public void setLugarExp(String lugarExp) {
        this.lugarExp = lugarExp;
    }

    public String getLugarNac() {
        return lugarNac;
    }

    public void setLugarNac(String lugarNac) {
        this.lugarNac = lugarNac;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPunto_ref() {
        return punto_ref;
    }

    public void setPunto_ref(String punto_ref) {
        this.punto_ref = punto_ref;
    }

    public String getReside_desde() {
        return reside_desde;
    }

    public void setReside_desde(String reside_desde) {
        this.reside_desde = reside_desde;
    }

    public String getCondicionVivienda() {
        return condicionVivienda;
    }

    public void setCondicionVivienda(String condicionVivienda) {
        this.condicionVivienda = condicionVivienda;
    }

    public String getLugarTrabajo() {
        return lugarTrabajo;
    }

    public void setLugarTrabajo(String lugarTrabajo) {
        this.lugarTrabajo = lugarTrabajo;
    }

    public String getJefeInmediato() {
        return jefeInmediato;
    }

    public void setJefeInmediato(String jefeInmediato) {
        this.jefeInmediato = jefeInmediato;
    }

    public String getTiempoEmpleo() {
        return tiempoEmpleo;
    }

    public void setTiempoEmpleo(String tiempoEmpleo) {
        this.tiempoEmpleo = tiempoEmpleo;
    }

    public String getFiador() {
        return fiador;
    }

    public void setFiador(String fiador) {
        this.fiador = fiador;
    }

    public Date getFecExp() {
        return fecExp;
    }

    public void setFecExp(Date fecExp) {
        this.fecExp = fecExp;
    }

    public Date getFecNac() {
        return fecNac;
    }

    public void setFecNac(Date fecNac) {
        this.fecNac = fecNac;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public List<Cliente> getListadoCliente() {
        return listadoCliente;
    }

    public void setListadoCliente(List<Cliente> listadoCliente) {
        this.listadoCliente = listadoCliente;
    }

    public List<Profesiones> getListadoProfesion() {
        return listadoProfesion;
    }

    public void setListadoProfesion(List<Profesiones> listadoProfesion) {
        this.listadoProfesion = listadoProfesion;
    }

    public Cliente getSeletedCliente() {
        return seletedCliente;
    }

    public void setSeletedCliente(Cliente seletedCliente) {
        this.seletedCliente = seletedCliente;
    }
    
    public ClienteJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public ClienteJpaController(){
    
    }
    
    @PostConstruct
    public void init() {
        generar();
        llenarTabla();
    }

    public void create(Cliente cliente) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(cliente);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCliente(cliente.getIdCliente()) != null) {
                throw new PreexistingEntityException("Cliente " + cliente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            cliente = em.merge(cliente);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cliente.getIdCliente();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getIdCliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            em.remove(cliente);
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

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public void guardar() {
        try {
        Cliente c = new Cliente();
        c.setIdCliente(idCliente);
        c.setNombre(nombre);
        c.setApellido(apellido);
        c.setDui(dui);
        c.setNit(nit);
        c.setEstadoCivil(estado_civil);
        c.setSexo(sexo);
        c.setLugarExp(lugarExp);
        c.setFecExp(fecExp);
        c.setLugarNac(lugarNac);
        c.setFecNac(fecNac);
        c.setTelefono(telefono);
        c.setProfesion(profesion);
        c.setDireccion(direccion);
        c.setPuntoRef(punto_ref);
        c.setResideDesde(reside_desde);
        c.setCondicionVivienda(condicionVivienda);
        c.setLugarTrabajo(lugarTrabajo);
        c.setJefeInmediato(jefeInmediato);
        c.setTiempoEmpleo(tiempoEmpleo);
        c.setFiador(fiador);
        c.setSalario(salario);
        clienteFacade.create(c);
        FacesMessage msgInfo = new FacesMessage("Registro guardado con exito!");
        FacesContext.getCurrentInstance().addMessage(null, msgInfo);
        limpiarForm();
        } catch(Exception e){
            FacesMessage msgError = new FacesMessage("No se pudo guardar el registro " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msgError);
        }
    }
    
    public void limpiarForm(){
        idCliente = 0;
        nombre = null;
        apellido = null;
        dui = null;
        nit = null;
        estado_civil = null;
        sexo = null;
        lugarExp = null;
        fecExp = null;
        lugarNac = null;
        fecNac = null;
        telefono = null;
        profesion = null;
        direccion = null;
        punto_ref = null;
        reside_desde = null;
        condicionVivienda = null;
        lugarTrabajo = null;
        jefeInmediato = null;
        tiempoEmpleo = null;
        fiador = null;
        salario = BigDecimal.ZERO;
        generar();
        llenarTabla();
    }
    
    public void generar() {
        try {
        idCliente = (int) clienteFacade.listaCliente();
        }catch(Exception e) {
            idCliente = 1;
        }
    }
          
    public void llenarTabla() {
      listadoCliente = clienteFacade.findAll();
      listadoProfesion = clienteFacade.listadoProfesiones();
    }
    
}
