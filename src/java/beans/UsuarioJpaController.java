/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import beans.exceptions.NonexistentEntityException;
import beans.exceptions.PreexistingEntityException;
import beans.exceptions.RollbackFailureException;
import entities.Profesiones;
import entities.Usuario;
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

/**
 *
 * @author BorisECornejo
 */
@ManagedBean(name = "usuarioController")
@ViewScoped
public class UsuarioJpaController implements Serializable {
    
    @EJB
    private facade.UsuarioFacade usuarioFacade;
    @EJB
    private facade.AgenciaFacade agenciaFacade;
    
    private int idUsuario, idAgencia, idDepto;
    private String nombre, apellido, dui, nit, estado_civil, direccion, sexo, cargo, telefono, clave, usuario, estado, municipio, profesion, usuario_creacion;
    private Date fecha_nacimiento, fecha_creacion;
    
    private List<Usuario> listadoUsuario = new ArrayList<Usuario>();
    private List<Profesiones> listadoProfesion = new ArrayList<Profesiones>();
    private List<Object[]> listadoAgencia = new ArrayList<Object[]>();
    private Usuario selectedUsuario;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdAgencia() {
        return idAgencia;
    }

    public void setIdAgencia(int idAgencia) {
        this.idAgencia = idAgencia;
    }

    public int getIdDepto() {
        return idDepto;
    }

    public void setIdDepto(int idDepto) {
        this.idDepto = idDepto;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getUsuario_creacion() {
        return usuario_creacion;
    }

    public void setUsuario_creacion(String usuario_creacion) {
        this.usuario_creacion = usuario_creacion;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public List<Usuario> getListadoUsuario() {
        return listadoUsuario;
    }
    
    public List<Profesiones> getListadoProfesion() {
        return listadoProfesion;
    }

    public void setListadoProfesion(List<Profesiones> listadoProfesion) {
        this.listadoProfesion = listadoProfesion;
    }

    public void setListadoUsuario(List<Usuario> listadoUsuario) {
        this.listadoUsuario = listadoUsuario;
    }

    public Usuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(Usuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }
    
    public List<Object[]> getListadoAgencia() {
        return listadoAgencia;
    }

    public void setListadoAgencia(List<Object[]> listadoAgencia) {
        this.listadoAgencia = listadoAgencia;
    }

    public UsuarioJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public UsuarioJpaController(){
    
    }
    
    @PostConstruct
    public void init() {
        generar();
        llenarTabla();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(usuario);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findUsuario(usuario.getIdUsuario()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            usuario = em.merge(usuario);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getIdUsuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            em.remove(usuario);
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

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public void guardar() {
        try {
        Usuario u = new Usuario();
        u.setIdAgencia(agenciaFacade.find(idAgencia));
        u.setIdUsuario(idUsuario);
        u.setNombre(nombre);
        u.setApellido(apellido);
        u.setDui(dui);
        u.setNit(nit);
        u.setDireccion(direccion);
        u.setProfesion(profesion);
        u.setSexo(sexo);
        u.setEstadoCivil(estado_civil);
        u.setEstado(estado);
        u.setCargo(cargo);
        u.setTelefono(telefono);
        u.setClave(clave);
        u.setUsuario(usuario);
        u.setFechaNacimiento(fecha_nacimiento);
        usuarioFacade.create(u);
        FacesMessage msgInfo = new FacesMessage("Registro guardado con exito!");
        FacesContext.getCurrentInstance().addMessage(null, msgInfo);
        limpiarForm();
        } catch(Exception e){
            FacesMessage msgError = new FacesMessage("No se pudo guardar el registro " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msgError);
        }
    }
    
    public void limpiarForm(){
        idAgencia=0;
        idUsuario=0;
        nombre=null;
        apellido=null;
        dui=null;
        nit=null;
        estado_civil=null;
        estado=null;
        direccion=null;
        sexo=null;
        cargo=null;
        telefono=null;
        clave=null;
        usuario=null;
        profesion=null;
        usuario=null;
        fecha_creacion=null;
        fecha_nacimiento=null;
        generar();
        llenarTabla();
    }
    
    public void generar() {
        try {
        idUsuario = (int) usuarioFacade.listaUsuario();
        }catch(Exception e) {
            idUsuario = 1;
        }
    }
          
    public void llenarTabla() {
      listadoUsuario = usuarioFacade.findAll();
      listadoAgencia = usuarioFacade.listaAgencia();
      listadoProfesion = usuarioFacade.listadoProfesiones();
    }
    
}
