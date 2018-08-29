/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import entities.Proveedor;
import java.io.Serializable;
import java.util.ArrayList;
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
@ManagedBean(name = "proveedorController")
@ViewScoped
public class ProveedorJpaController implements Serializable {
    
    @EJB 
    facade.ProveedorFacade proveedorFacade;
    
    private List<Proveedor> listadoProvedores = new ArrayList<Proveedor>();
    private Proveedor selectedProveedor;
    private int idProveedor;
    private String nomProveedor, direccion, telefono, tipoProv, contacto;
    
    private int idProveedor_modal;
    private String nomProveedor_modal, direccion_modal, telefono_modal, tipoProv_modal, contacto_modal;

    public List<Proveedor> getListadoProveedores() {
        return listadoProvedores;
    }

    public void setListadoProveedores(List<Proveedor> listadoProvedores) {
        this.listadoProvedores = listadoProvedores;
    }

    public Proveedor getSelectedProveedor() {
        return selectedProveedor;
    }

    public void setSelectedProveedor(Proveedor selectedProveedor) {
        this.selectedProveedor = selectedProveedor;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNomProveedor() {
        return nomProveedor;
    }

    public void setNomProveedor(String nomProveedor) {
        this.nomProveedor = nomProveedor;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipoProv() {
        return tipoProv;
    }

    public void setTipoProv(String tipoProv) {
        this.tipoProv = tipoProv;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public int getIdProveedor_modal() {
        return idProveedor_modal;
    }

    public void setIdProveedor_modal(int idProveedor_modal) {
        this.idProveedor_modal = idProveedor_modal;
    }

    public String getNomProveedor_modal() {
        return nomProveedor_modal;
    }

    public void setNomProveedor_modal(String nomProveedor_modal) {
        this.nomProveedor_modal = nomProveedor_modal;
    }

    public String getDireccion_modal() {
        return direccion_modal;
    }

    public void setDireccion_modal(String direccion_modal) {
        this.direccion_modal = direccion_modal;
    }

    public String getTelefono_modal() {
        return telefono_modal;
    }

    public void setTelefono_modal(String telefono_modal) {
        this.telefono_modal = telefono_modal;
    }

    public String getContacto_modal() {
        return contacto_modal;
    }

    public void setContacto_modal(String contacto_modal) {
        this.contacto_modal = contacto_modal;
    }
    
    public ProveedorJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public ProveedorJpaController(){
    
    }
    
    @PostConstruct
    public void init() {
       generar();
       llenarTabla();
    }
    
    public void create(Proveedor proveedor) throws Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(proveedor);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new Exception("An error occurred attempting to roll back the transaction.", re);
            }
            if (findProveedor(proveedor.getIdProveedor()) != null) {
                throw new Exception("Proveedor " + proveedor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proveedor proveedor) throws Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            proveedor = em.merge(proveedor);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new Exception("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proveedor.getIdProveedor();
                if (findProveedor(id) == null) {
                    throw new Exception("The proveedor with id " + id + " no longer exists.");
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
            Proveedor proveedor;
            try {
                proveedor = em.getReference(Proveedor.class, id);
                proveedor.getIdProveedor();
            } catch (EntityNotFoundException enfe) {
                throw new Exception("The proveedor with id " + id + " no longer exists.", enfe);
            }
            em.remove(proveedor);
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

    public List<Proveedor> findProveedorEntities() {
        return findProveedorEntities(true, -1, -1);
    }

    public List<Proveedor> findProveedorEntities(int maxResults, int firstResult) {
        return findProveedorEntities(false, maxResults, firstResult);
    }

    private List<Proveedor> findProveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proveedor.class));
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

    public Proveedor findProveedor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proveedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getProveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proveedor> rt = cq.from(Proveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public void guardar(){
        try{
        Proveedor p = new Proveedor();
        p.setIdProveedor(idProveedor);
        p.setNombre(nomProveedor);
        p.setContacto(contacto);
        p.setTelefono(telefono);
        p.setTipoProv(tipoProv);
        p.setDireccion(direccion);
        proveedorFacade.create(p);
        FacesMessage msg = new FacesMessage("Se guardo el registro satisfactoriamente");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        limpiarForm();
        generar();
        llenarTabla();
        } catch(Exception e){
            FacesMessage msg = new FacesMessage("No se pudo guardar el registro " + e.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void limpiarForm() {
        idProveedor = 0;
        nomProveedor = null;
        contacto = null;
        telefono = null;
        tipoProv = null;
        direccion = null;
        generar();
        llenarTabla();
    }
    
    public void generar(){
        try {
            idProveedor = (int) proveedorFacade.listaProveedor();
        } catch (Exception e) {
            idProveedor = 1;
        }
    }
    
    public void llenarTabla(){
        listadoProvedores = proveedorFacade.findAll();
    }
    
    public void onRowSelectProveedor(SelectEvent event) {
        String codigo_proveedor = String.valueOf(selectedProveedor.getIdProveedor());
        FacesMessage msg = new FacesMessage("Codigo Marcado", codigo_proveedor);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        idProveedor_modal = selectedProveedor.getIdProveedor();
        nomProveedor_modal = selectedProveedor.getNombre();
        contacto_modal = selectedProveedor.getContacto();
        direccion_modal = selectedProveedor.getDireccion();
        tipoProv_modal = selectedProveedor.getTipoProv();
        telefono_modal = selectedProveedor.getTelefono();
    }
    
    public void actualizaProveedor() {
        try {
            selectedProveedor.setIdProveedor(idProveedor_modal);
            selectedProveedor.setNombre(nomProveedor_modal);
            selectedProveedor.setContacto(contacto_modal);
            selectedProveedor.setDireccion(direccion_modal);
            selectedProveedor.setTipoProv(tipoProv_modal);
            selectedProveedor.setTelefono(telefono_modal);
            proveedorFacade.edit(selectedProveedor);
            llenarTabla();
            FacesMessage msgInfo = new FacesMessage("Registro actualizar con exito!");
            FacesContext.getCurrentInstance().addMessage(null, msgInfo);
        } catch (Exception e) {
            FacesMessage msgError = new FacesMessage("No se pudo actualizar el registro " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msgError);
        }

    }
    
}
