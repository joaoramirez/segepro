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
import entities.Departamento;
import entities.Municipio;
import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author BorisECornejo
 */
@ManagedBean(name = "agenciaController")
@ViewScoped
public class AgenciaJpaController implements Serializable {
    
    @EJB
    private facade.AgenciaFacade agenciaFacade;
    @EJB
    private facade.DepartamentoFacade deptoFacade;
    @EJB
    private facade.MunicipioFacade muniFacade;
    
    private List<Agencia> listadoAgencias = new ArrayList<Agencia>();
    private List<Departamento> listadoDeptos = new ArrayList<Departamento>();
    private List<Municipio> listadoMunicipios = new ArrayList<Municipio>();
    private Agencia selectedAgencia;
    private Departamento selectedDepto;
    private int idagencia, iddepto, id;
    private String centerGeoMap = "13.8462831, -88.805454";
    private MapModel geoModel;
    private Marker marker;
    private Object lstCantAgencias;
    private Object lstCantEmpleados;
    private Object lstTotCredito;

    public Object getLstTotCredito() {
        return lstTotCredito;
    }

    public void setLstTotCredito(Object lstTotCredito) {
        this.lstTotCredito = lstTotCredito;
    }

    public Object getLstCantEmpleados() {
        return lstCantEmpleados;
    }

    public void setLstCantEmpleados(Object lstCantEmpleados) {
        this.lstCantEmpleados = lstCantEmpleados;
    }

    public Object getLstCantAgencias() {
        return lstCantAgencias;
    }

    public void setLstCantAgencias(Object lstCantAgencias) {
        this.lstCantAgencias = lstCantAgencias;
    }
    
    public MapModel getGeoModel() {
        return geoModel;
    }

    public void setGeoModel(MapModel geoModel) {
        this.geoModel = geoModel;
    }

    public Marker getMarker() {
        return marker;
    }

    public String getCenterGeoMap() {
        return centerGeoMap;
    }

    public void setCenterGeoMap(String centerGeoMap) {
        this.centerGeoMap = centerGeoMap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIddepto() {
        return iddepto;
    }

    public void setIddepto(int iddepto) {
        this.iddepto = iddepto;
    }
    private BigDecimal longitud, latitud;
    private String nomAgencia, encargado, direccion, telefono, estado, nomdepto, nommuni;

    public List<Agencia> getListadoAgencias() {
        return listadoAgencias;
    }

    public void setListadoAgencias(List<Agencia> listadoAgencias) {
        this.listadoAgencias = listadoAgencias;
    }
    
    public List<Departamento> getListadoDeptos() {
        return listadoDeptos;
    }

    public void setListadoDeptos(List<Departamento> listadoDeptos) {
        this.listadoDeptos = listadoDeptos;
    }

    public List<Municipio> getListadoMunicipios() {
        return listadoMunicipios;
    }

    public void setListadoMunicipios(List<Municipio> listadoMunicipios) {
        this.listadoMunicipios = listadoMunicipios;
    }

    public Agencia getSelectedAgencia() {
        return selectedAgencia;
    }

    public void setSelectedAgencia(Agencia selectedAgencia) {
        this.selectedAgencia = selectedAgencia;
    }
    
    public Departamento getSelectedDepto() {
        return selectedDepto;
    }

    public void setSelectedDepto(Departamento selectedDepto) {
        this.selectedDepto = selectedDepto;
    }

    public int getIdagencia() {
        return idagencia;
    }

    public void setIdagencia(int idagencia) {
        this.idagencia = idagencia;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public String getNomAgencia() {
        return nomAgencia;
    }

    public void setNomAgencia(String nomAgencia) {
        this.nomAgencia = nomAgencia;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNomdepto() {
        return nomdepto;
    }

    public void setNomdepto(String nomdepto) {
        this.nomdepto = nomdepto;
    }

    public String getNommuni() {
        return nommuni;
    }

    public void setNommuni(String nommuni) {
        this.nommuni = nommuni;
    }

    public AgenciaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public AgenciaJpaController(){
    
    }
    
    @PostConstruct
    public void init() {
        selectedAgencia = new Agencia();
        generar();
        llenarTabla();
       listadoDeptos = deptoFacade.listaDepartamentos();
       geoPosicionar();
       cantEmpleados();
       cantAgencias();
       totCreditos();
    }

    public void create(Agencia agencia) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(agencia);
            utx.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findAgencia(agencia.getIdAgencia()) != null) {
                throw new PreexistingEntityException("Agencia " + agencia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Agencia agencia) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            agencia = em.merge(agencia);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = agencia.getIdAgencia();
                if (findAgencia(id) == null) {
                    throw new NonexistentEntityException("The agencia with id " + id + " no longer exists.");
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
            Agencia agencia;
            try {
                agencia = em.getReference(Agencia.class, id);
                agencia.getIdAgencia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The agencia with id " + id + " no longer exists.", enfe);
            }
            em.remove(agencia);
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

    public List<Agencia> findAgenciaEntities() {
        return findAgenciaEntities(true, -1, -1);
    }

    public List<Agencia> findAgenciaEntities(int maxResults, int firstResult) {
        return findAgenciaEntities(false, maxResults, firstResult);
    }

    private List<Agencia> findAgenciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Agencia.class));
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

    public Agencia findAgencia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Agencia.class, id);
        } finally {
            em.close();
        }
    }

    public int getAgenciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Agencia> rt = cq.from(Agencia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
        
    public void onRowSelectAgencia(SelectEvent event) {
        String codigo_agencia = String.valueOf(selectedAgencia.getIdAgencia());
        FacesMessage msg = new FacesMessage("Codigo Marcado", codigo_agencia);
        System.out.println("selected "+selectedAgencia.getTelefono());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onDeptoChange() {       
        listadoMunicipios = muniFacade.listaMunicipios(iddepto);
               
    }
    
    public void onDeptoChange2() {       
        listadoMunicipios = muniFacade.listaMunicipios(selectedDepto.getIdDepto());
               
    }
    
    public void limpiar(){
    selectedAgencia=new Agencia();
    }
    
    public void geoPosicionar(){
     geoModel= new DefaultMapModel();
    for(Agencia agencia: listadoAgencias){
       
       
        if(agencia.getLatitude()!=null&&agencia.getLongitud()!=null){
       Double lat=agencia.getLatitude().doubleValue();
       Double lgn=agencia.getLongitud().doubleValue();
       LatLng posicion = new LatLng(lat, lgn);
       Marker marker1 = new Marker(posicion, agencia.getNombre()+"<br/>"+agencia.getEncargado(), null, "../img/icono.png");
       geoModel.addOverlay(marker1);}
    }        
 }
    
    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
    }
    
    public void guardar() {
        try {
            Agencia v = new Agencia();
            v.setIdAgencia(idagencia);
            v.setNombre(nomAgencia);
            v.setEncargado(encargado);
            v.setTelefono(telefono);
            v.setDireccion(direccion);
            v.setIdDepto(iddepto);
            v.setNomMuni(nommuni);
            v.setLatitude(latitud);
            v.setLongitud(longitud);
            v.setEstado(estado);
            agenciaFacade.create(v);
            FacesMessage msg = new FacesMessage("Se guardo el registro satisfactoriamente");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            limpiarForm();
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("No se pudo guardar el registro " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void limpiarForm() {
        idagencia = 0;
        nomAgencia = null;
        encargado = null;
        telefono = null;
        direccion = null;
        nommuni = null;
        latitud = BigDecimal.ZERO;
        longitud = BigDecimal.ZERO;
        estado = null;
        generar();
        llenarTabla();
    }

    public void generar() {
        try {
        idagencia = (int) agenciaFacade.listaAgencia();
        }catch(Exception e) {
            idagencia = 1;
        }
    }
    
    public void llenarTabla() {
        listadoAgencias = agenciaFacade.listaAgencias();
    }
    
    public void cantAgencias() {       
        lstCantAgencias = agenciaFacade.lstaCantAgencias();
    }
    
    public void cantEmpleados() {       
        lstCantEmpleados = agenciaFacade.lstaCantEmpleados();
    }
    
    public void totCreditos() {       
        lstTotCredito = agenciaFacade.lstaTotCreditos();
    }
    
}
