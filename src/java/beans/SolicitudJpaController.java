/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import beans.exceptions.NonexistentEntityException;
import beans.exceptions.PreexistingEntityException;
import beans.exceptions.RollbackFailureException;
import ejbs.ReporteSessionBean;
import entities.Cliente;
import entities.Solicitud;
import entities.SolicitudPK;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
import org.primefaces.event.SlideEndEvent;

/**
 *
 * @author Boris Cornejo
 */
@ManagedBean(name = "solicitudController")
@ViewScoped
public class SolicitudJpaController implements Serializable {

    @EJB
    private facade.SolicitudFacade solicitudFacade;
    @EJB
    private facade.ClienteFacade clienteFacade;
    @EJB
    private facade.ViviendaFacade viviendaFacade;
    @EJB
    private facade.MejoraFacade mejoraFacade;
    @EJB
    private ReporteSessionBean reporteSessionBean;
    
    private int idCliente, codAgencia, plazo, secuencia, numSolicitud,cantCuota;
    private Date fechaCambioEstado;
    private Date fechaEmision;
    private BigDecimal presupuesto, montoSolicitado, cuota;
    private String estado, municipio, comunidad, oficinaRegional, destinoCredito, duenioPropiedad, parentesco;
    private String detalleObra, modeloVivienda, formaPago, comentarioIniciativa, comentarioEvaluacion, comentarioGarantia;
    private String nomCliente,fechaPago;
    
    private List<Solicitud> listadoSolicitud = new ArrayList<Solicitud>();
    private List<Object[]> listado = new ArrayList<Object[]>();
    private List<Cliente> listadoClientes = new ArrayList<Cliente>();
    private Solicitud selectedSolicitud;
    private Cliente selectedCliente;

    public Cliente getSelectedCliente() {
        return selectedCliente;
    }

    public void setSelectedCliente(Cliente selectedCliente) {
        this.selectedCliente = selectedCliente;
    }

    public List<Solicitud> getListadoSolicitud() {
        return listadoSolicitud;
    }

    public void setListadoSolicitud(List<Solicitud> listadoSolicitud) {
        this.listadoSolicitud = listadoSolicitud;
    }
    
    public List<Cliente> getListadoClientes() {
        return listadoClientes;
    }

    public void setListadoClientes(List<Cliente> listadoClientes) {
        this.listadoClientes = listadoClientes;
    }
    
    public List<Object[]> getListado() {
        return listado;
    }

    public void setListado(List<Object[]> listado) {
        this.listado = listado;
    }

    public Solicitud getSelectedSolicitud() {
        return selectedSolicitud;
    }

    public void setSelectedSolicitud(Solicitud selectedSolicitud) {
        this.selectedSolicitud = selectedSolicitud;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getCodAgencia() {
        return codAgencia;
    }

    public void setCodAgencia(int codAgencia) {
        this.codAgencia = codAgencia;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }

    public int getNumSolicitud() {
        return numSolicitud;
    }

    public void setNumSolicitud(int numSolicitud) {
        this.numSolicitud = numSolicitud;
    }

    public Date getFechaCambioEstado() {
        return fechaCambioEstado;
    }

    public void setFechaCambioEstado(Date fechaCambioEstado) {
        this.fechaCambioEstado = fechaCambioEstado;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public BigDecimal getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(BigDecimal presupuesto) {
        this.presupuesto = presupuesto;
    }

    public BigDecimal getMontoSolicitado() {
        return montoSolicitado;
    }

    public void setMontoSolicitado(BigDecimal montoSolicitado) {
        this.montoSolicitado = montoSolicitado;
    }

    public BigDecimal getCuota() {
        return cuota;
    }

    public void setCuota(BigDecimal cuota) {
        this.cuota = cuota;
    }
    
    public int getCantCuota() {
        return cantCuota;
    }

    public void setCantCuota(int cantCuota) {
        this.cantCuota = cantCuota;
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

    public String getComunidad() {
        return comunidad;
    }

    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }

    public String getOficinaRegional() {
        return oficinaRegional;
    }

    public void setOficinaRegional(String oficinaRegional) {
        this.oficinaRegional = oficinaRegional;
    }

    public String getDestinoCredito() {
        return destinoCredito;
    }

    public void setDestinoCredito(String destinoCredito) {
        this.destinoCredito = destinoCredito;
    }

    public String getDuenioPropiedad() {
        return duenioPropiedad;
    }

    public void setDuenioPropiedad(String duenioPropiedad) {
        this.duenioPropiedad = duenioPropiedad;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getDetalleObra() {
        return detalleObra;
    }

    public void setDetalleObra(String detalleObra) {
        this.detalleObra = detalleObra;
    }

    public String getModeloVivienda() {
        return modeloVivienda;
    }

    public void setModeloVivienda(String modeloVivienda) {
        this.modeloVivienda = modeloVivienda;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getComentarioIniciativa() {
        return comentarioIniciativa;
    }

    public void setComentarioIniciativa(String comentarioIniciativa) {
        this.comentarioIniciativa = comentarioIniciativa;
    }

    public String getComentarioEvaluacion() {
        return comentarioEvaluacion;
    }

    public void setComentarioEvaluacion(String comentarioEvaluacion) {
        this.comentarioEvaluacion = comentarioEvaluacion;
    }

    public String getComentarioGarantia() {
        return comentarioGarantia;
    }

    public void setComentarioGarantia(String comentarioGarantia) {
        this.comentarioGarantia = comentarioGarantia;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }
    
    public SolicitudJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public SolicitudJpaController(){
    
    }
    
    @PostConstruct
    public void init() {
        generar();
        llenarTabla();
    }

    public void create(Solicitud solicitud) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (solicitud.getSolicitudPK() == null) {
            solicitud.setSolicitudPK(new SolicitudPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(solicitud);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findSolicitud(solicitud.getSolicitudPK()) != null) {
                throw new PreexistingEntityException("Solicitud " + solicitud + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Solicitud solicitud) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            solicitud = em.merge(solicitud);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                SolicitudPK id = solicitud.getSolicitudPK();
                if (findSolicitud(id) == null) {
                    throw new NonexistentEntityException("The solicitud with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(SolicitudPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Solicitud solicitud;
            try {
                solicitud = em.getReference(Solicitud.class, id);
                solicitud.getSolicitudPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The solicitud with id " + id + " no longer exists.", enfe);
            }
            em.remove(solicitud);
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

    public List<Solicitud> findSolicitudEntities() {
        return findSolicitudEntities(true, -1, -1);
    }

    public List<Solicitud> findSolicitudEntities(int maxResults, int firstResult) {
        return findSolicitudEntities(false, maxResults, firstResult);
    }

    private List<Solicitud> findSolicitudEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Solicitud.class));
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

    public Solicitud findSolicitud(SolicitudPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Solicitud.class, id);
        } finally {
            em.close();
        }
    }

    public int getSolicitudCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Solicitud> rt = cq.from(Solicitud.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

        public void guardar() {
        try {
        Solicitud s = new Solicitud();
        SolicitudPK spk = new SolicitudPK();
        spk.setNumSolicitud(numSolicitud);
        spk.setIdCliente(idCliente);
        s.setSolicitudPK(spk);
        s.setCodAgencia(codAgencia);
        s.setNomCliente(nomCliente);
        s.setOficinaRegional(oficinaRegional);
        s.setDestinoCredito(destinoCredito);
        s.setDetalleObra(detalleObra);
        s.setModeloVivienda(modeloVivienda);
        s.setDuenioPropiedad(duenioPropiedad);
        s.setEstado(estado);
        s.setFormaPago(formaPago);
        s.setCuota(cuota);
        s.setCantCuota(cantCuota);
        s.setComunidad(comunidad);
        s.setFechaCambioEstado(fechaCambioEstado);
        s.setFechaEmision(fechaEmision);
        s.setFechaPago(fechaPago);
        s.setMunicipio(municipio);
        s.setParentesco(parentesco);
        s.setMontoSolicitado(montoSolicitado);
        s.setPlazo(plazo);
//        s.setSecuencia(secuencia);
        s.setPresupuesto(presupuesto);
        s.setComentarioEvaluacion(comentarioEvaluacion);
        s.setComentarioGarantia(comentarioGarantia);
        s.setComentarioIniciativa(comentarioIniciativa);
        solicitudFacade.create(s);
        FacesMessage msgInfo = new FacesMessage("Registro guardado con exito!");
        FacesContext.getCurrentInstance().addMessage(null, msgInfo);
        limpiarForm();
        } catch(Exception e){
            FacesMessage msgError = new FacesMessage("No se pudo guardar el registro " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msgError);
        }
    }
    
    public void limpiarForm(){
        numSolicitud = 0;
        codAgencia = 0;
        idCliente = 0;
        nomCliente = null;
        oficinaRegional = null;
        destinoCredito = null;
        detalleObra = null;
        modeloVivienda = null;
        duenioPropiedad = null;
        estado = null;
        formaPago = null;
        cuota = BigDecimal.ZERO;
        cantCuota = 0;
        comunidad = null;
        fechaCambioEstado =  null;
        fechaEmision = null;
        fechaPago = null;
        municipio = null;
        parentesco = null;
        montoSolicitado = BigDecimal.ZERO;
        plazo = 0;
        secuencia = 0;
        presupuesto = BigDecimal.ZERO;
        comentarioEvaluacion = null;
        comentarioGarantia = null;
        comentarioIniciativa = null;
        generar();
        llenarTabla();
    }
    
    public void generar() {
        fechaEmision = Calendar.getInstance().getTime();
        fechaCambioEstado = Calendar.getInstance().getTime();
        codAgencia = 1;
        estado = "Grabada";
        municipio = "San Vicente";
        try {
        numSolicitud = (int) solicitudFacade.listaSolicitud();
        }catch(Exception e) {
            numSolicitud = 1;
        }
    }
    
    public void onChangeDestino() {
        switch (destinoCredito) {
            case "Compra de Vivienda Usada":
                
                break;
            case "Construc. de Vivienda + Compra de Terreno":
                listado = viviendaFacade.listadoViviendas();
                break;    
            case "Construcción de Vivienda":
                listado = viviendaFacade.listadoViviendas();
                break;
            case "Mejora de Vivienda":        
                listado = mejoraFacade.listadoMejoras();
                break;
        }
    }
    
    public void onChangeModelo() {
        switch (destinoCredito) {
            case "Compra de Vivienda Usada":
                
                break;
            case "Construc. de Vivienda + Compra de Terreno":
                presupuesto = (BigDecimal) viviendaFacade.listaViviendaCosto(Integer.parseInt(String.valueOf(modeloVivienda)));
                montoSolicitado = (BigDecimal) viviendaFacade.listaViviendaCosto(Integer.parseInt(String.valueOf(modeloVivienda)));
                break;    
            case "Construcción de Vivienda":
                presupuesto = (BigDecimal) viviendaFacade.listaViviendaCosto(Integer.parseInt(String.valueOf(modeloVivienda)));
                montoSolicitado = (BigDecimal) viviendaFacade.listaViviendaCosto(Integer.parseInt(String.valueOf(modeloVivienda)));
                break;
            case "Mejora de Vivienda":        
                presupuesto = (BigDecimal) mejoraFacade.listaMejorasCosto(Integer.parseInt(String.valueOf(modeloVivienda)));
                montoSolicitado = (BigDecimal) mejoraFacade.listaMejorasCosto(Integer.parseInt(String.valueOf(modeloVivienda)));
                break;
        }
    }
        
    public void llenarTabla() {
        listadoSolicitud = solicitudFacade.findAll();
        listadoClientes = clienteFacade.findAll();
    }
    
    public void onSlideEnd(SlideEndEvent event) {
        switch (fechaPago) {
            case "Semanal":
                cantCuota = event.getValue()*4;
                cuota = montoSolicitado.divide(BigDecimal.valueOf(Double.valueOf(String.valueOf(cantCuota))),2);
                break;
            case "Quincenal":
                cantCuota = event.getValue()*2;
                cuota = montoSolicitado.divide(BigDecimal.valueOf(Double.valueOf(String.valueOf(cantCuota))),2);
                break;
            case "Mensual":        
                cantCuota = event.getValue();
                cuota = montoSolicitado.divide(BigDecimal.valueOf(Double.valueOf(String.valueOf(cantCuota))),2);
                break;
        }
        FacesMessage message = new FacesMessage("Slide Ended", "Value: " + event.getValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void agregarModal(){
        idCliente = selectedCliente.getIdCliente();
        nomCliente = selectedCliente.getNombre().concat(" ").concat(selectedCliente.getApellido());
    }
    
    public String imprimirReporte() {

        HashMap<String, Object> parametros = new HashMap<>(); 
        parametros.put("id_cliente", selectedSolicitud.getSolicitudPK().getIdCliente());
        parametros.put("num_solicitud", selectedSolicitud.getSolicitudPK().getNumSolicitud());
//        parametros.put("COD_CIA", getMovimiento().getVtaMovmPK().getCodCia());
//        parametros.put("ID_SUCURSAL", getMovimiento().getVtaMovmPK().getIdSucursal());
//        parametros.put("ID_TIPO_DOCTO_VTA", getMovimiento().getVtaMovmPK().getIdTipoDoctoVta());
//        parametros.put("NUM_DOCTO", getMovimiento().getVtaMovmPK().getNumDocto());
        reporteSessionBean.generarReporteSQLTitulo(FacesContext.getCurrentInstance(), parametros, "detalle_solicitud", "SolicitudCredito"+selectedSolicitud.getSolicitudPK().getNumSolicitud());
        return null;
    }
    
    /*
    <p:commnadButton value="Imprimir Factura Fisica" icon="fa fa-print" ajax="false" immediate="true" action="#{facturaManagedBean.imprimirReporte()}" />
    */
    
    
    public String emitirSolicitud() {
        Date fecha = Calendar.getInstance().getTime();
        //Solicitud s;
        System.out.println("aaaaa");
        //selectedSolicitud.getSolicitudPK().getNumSolicitud();
        selectedSolicitud.setEstado("Emitida");
        selectedSolicitud.setFechaCambioEstado(fecha);
        solicitudFacade.edit(selectedSolicitud);
        return null;
    }
    
}
