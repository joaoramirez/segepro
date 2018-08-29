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
import ejbs.ReporteSessionBean;
import entities.Cliente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Mejora;
import entities.ConstruVivienda;
import entities.Credito;
import entities.Expediente;
import entities.Presupuesto;
import entities.Solicitud;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author BorisECornejo
 */
@ManagedBean(name = "expedienteController")
@ViewScoped
public class ExpedienteJpaController implements Serializable {

    @EJB
    private facade.ExpedienteFacade expedientefacade;
    @EJB
    private facade.ClienteFacade clienteFacade;
    @EJB
    private facade.SolicitudFacade solicitudFacade;
    @EJB
    private facade.CreditoFacade creditoFacade;
    @EJB
    private ReporteSessionBean reporteSessionBean;

    private Cliente selectedCliente;

    private Solicitud selectedNumSolicitud;

    private List<Cliente> listadoCliente = new ArrayList<Cliente>();

    private List<Cliente> listadoDatosCliente = new ArrayList<Cliente>();

    private List<Solicitud> listadoDatosSolicitud = new ArrayList<Solicitud>();

    private List<Credito> listadoDatosCredito = new ArrayList<Credito>();
    
    private List<Credito> listadoDatosCreditoExp = new ArrayList<Credito>();
    
    private List<Cliente> listadoDuiCliente = new ArrayList();
    
    private List<Cliente> listadoDuiClienteP = new ArrayList();   

    public List<Credito> getListadoDatosCredito() {
        return listadoDatosCredito;
    }

    public void setListadoDatosCredito(List<Credito> listadoDatosCredito) {
        this.listadoDatosCredito = listadoDatosCredito;
    }

    public List<Cliente> getListadoDuiCliente() {
        return listadoDuiCliente;
    }

    public void setListadoDuiCliente(List<Cliente> listadoDuiCliente) {
        this.listadoDuiCliente = listadoDuiCliente;
    }

    public List<Solicitud> getListadoDatosSolicitud() {
        return listadoDatosSolicitud;
    }

    public void setListadoDatosSolicitud(List<Solicitud> listadoDatosSolicitud) {
        this.listadoDatosSolicitud = listadoDatosSolicitud;
    }

    public List<Cliente> getListadoDatosCliente() {
        return listadoDatosCliente;
    }

    public void setListadoDatosCliente(List<Cliente> listadoDatosCliente) {
        this.listadoDatosCliente = listadoDatosCliente;
    }

    public Cliente getSelectedCliente() {
        return selectedCliente;
    }

    public void setSelectedCliente(Cliente selectedCliente) {
        this.selectedCliente = selectedCliente;
    }

    public Solicitud getSelectedNumSolicitud() {
        return selectedNumSolicitud;
    }

    public void setSelectedNumSolicitud(Solicitud selectedNumSolicitud) {
        this.selectedNumSolicitud = selectedNumSolicitud;
    }

    public List<Cliente> getListadoCliente() {
        return listadoCliente;
    }

    public void setListadoCliente(List<Cliente> listadoCliente) {
        this.listadoCliente = listadoCliente;
    }

    public List<Cliente> getListadoDuiClienteP() {
        return listadoDuiClienteP;
    }

    public void setListadoDuiClienteP(List<Cliente> listadoDuiClienteP) {
        this.listadoDuiClienteP = listadoDuiClienteP;
    }

    public List<Credito> getListadoDatosCreditoExp() {
        return listadoDatosCreditoExp;
    }

    public void setListadoDatosCreditoExp(List<Credito> listadoDatosCreditoExp) {
        this.listadoDatosCreditoExp = listadoDatosCreditoExp;
    }

    public ExpedienteJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public ExpedienteJpaController() {

    }

    @PostConstruct
    public void init() {
        listadoDuiCliente = solicitudFacade.listadoDuiClientes();
        listadoDuiClienteP = solicitudFacade.listadoDuiClientesP();
    }

    public void create(Expediente expediente) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (expediente.getPresupuestoList() == null) {
            expediente.setPresupuestoList(new ArrayList<Presupuesto>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Mejora idMejora = expediente.getIdMejora();
            if (idMejora != null) {
                idMejora = em.getReference(idMejora.getClass(), idMejora.getIdMejora());
                expediente.setIdMejora(idMejora);
            }
            ConstruVivienda idVivienda = expediente.getIdVivienda();
            if (idVivienda != null) {
                idVivienda = em.getReference(idVivienda.getClass(), idVivienda.getIdVivienda());
                expediente.setIdVivienda(idVivienda);
            }
            List<Presupuesto> attachedPresupuestoList = new ArrayList<Presupuesto>();
            for (Presupuesto presupuestoListPresupuestoToAttach : expediente.getPresupuestoList()) {
                presupuestoListPresupuestoToAttach = em.getReference(presupuestoListPresupuestoToAttach.getClass(), presupuestoListPresupuestoToAttach.getIdPresupuesto());
                attachedPresupuestoList.add(presupuestoListPresupuestoToAttach);
            }
            expediente.setPresupuestoList(attachedPresupuestoList);
            em.persist(expediente);
            if (idMejora != null) {
                idMejora.getExpedienteList().add(expediente);
                idMejora = em.merge(idMejora);
            }
            if (idVivienda != null) {
                idVivienda.getExpedienteList().add(expediente);
                idVivienda = em.merge(idVivienda);
            }
            for (Presupuesto presupuestoListPresupuesto : expediente.getPresupuestoList()) {
                Expediente oldNumExpedienteOfPresupuestoListPresupuesto = presupuestoListPresupuesto.getNumExpediente();
                presupuestoListPresupuesto.setNumExpediente(expediente);
                presupuestoListPresupuesto = em.merge(presupuestoListPresupuesto);
                if (oldNumExpedienteOfPresupuestoListPresupuesto != null) {
                    oldNumExpedienteOfPresupuestoListPresupuesto.getPresupuestoList().remove(presupuestoListPresupuesto);
                    oldNumExpedienteOfPresupuestoListPresupuesto = em.merge(oldNumExpedienteOfPresupuestoListPresupuesto);
                }
            }
            utx.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            try {
                utx.rollback();
            } catch (IllegalStateException | SecurityException | SystemException re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findExpediente(expediente.getNumExpediente()) != null) {
                throw new PreexistingEntityException("Expediente " + expediente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Expediente expediente) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Expediente persistentExpediente = em.find(Expediente.class, expediente.getNumExpediente());
            Mejora idMejoraOld = persistentExpediente.getIdMejora();
            Mejora idMejoraNew = expediente.getIdMejora();
            ConstruVivienda idViviendaOld = persistentExpediente.getIdVivienda();
            ConstruVivienda idViviendaNew = expediente.getIdVivienda();
            List<Presupuesto> presupuestoListOld = persistentExpediente.getPresupuestoList();
            List<Presupuesto> presupuestoListNew = expediente.getPresupuestoList();
            List<String> illegalOrphanMessages = null;
            for (Presupuesto presupuestoListOldPresupuesto : presupuestoListOld) {
                if (!presupuestoListNew.contains(presupuestoListOldPresupuesto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Presupuesto " + presupuestoListOldPresupuesto + " since its numExpediente field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idMejoraNew != null) {
                idMejoraNew = em.getReference(idMejoraNew.getClass(), idMejoraNew.getIdMejora());
                expediente.setIdMejora(idMejoraNew);
            }
            if (idViviendaNew != null) {
                idViviendaNew = em.getReference(idViviendaNew.getClass(), idViviendaNew.getIdVivienda());
                expediente.setIdVivienda(idViviendaNew);
            }
            List<Presupuesto> attachedPresupuestoListNew = new ArrayList<Presupuesto>();
            for (Presupuesto presupuestoListNewPresupuestoToAttach : presupuestoListNew) {
                presupuestoListNewPresupuestoToAttach = em.getReference(presupuestoListNewPresupuestoToAttach.getClass(), presupuestoListNewPresupuestoToAttach.getIdPresupuesto());
                attachedPresupuestoListNew.add(presupuestoListNewPresupuestoToAttach);
            }
            presupuestoListNew = attachedPresupuestoListNew;
            expediente.setPresupuestoList(presupuestoListNew);
            expediente = em.merge(expediente);
            if (idMejoraOld != null && !idMejoraOld.equals(idMejoraNew)) {
                idMejoraOld.getExpedienteList().remove(expediente);
                idMejoraOld = em.merge(idMejoraOld);
            }
            if (idMejoraNew != null && !idMejoraNew.equals(idMejoraOld)) {
                idMejoraNew.getExpedienteList().add(expediente);
                idMejoraNew = em.merge(idMejoraNew);
            }
            if (idViviendaOld != null && !idViviendaOld.equals(idViviendaNew)) {
                idViviendaOld.getExpedienteList().remove(expediente);
                idViviendaOld = em.merge(idViviendaOld);
            }
            if (idViviendaNew != null && !idViviendaNew.equals(idViviendaOld)) {
                idViviendaNew.getExpedienteList().add(expediente);
                idViviendaNew = em.merge(idViviendaNew);
            }
            for (Presupuesto presupuestoListNewPresupuesto : presupuestoListNew) {
                if (!presupuestoListOld.contains(presupuestoListNewPresupuesto)) {
                    Expediente oldNumExpedienteOfPresupuestoListNewPresupuesto = presupuestoListNewPresupuesto.getNumExpediente();
                    presupuestoListNewPresupuesto.setNumExpediente(expediente);
                    presupuestoListNewPresupuesto = em.merge(presupuestoListNewPresupuesto);
                    if (oldNumExpedienteOfPresupuestoListNewPresupuesto != null && !oldNumExpedienteOfPresupuestoListNewPresupuesto.equals(expediente)) {
                        oldNumExpedienteOfPresupuestoListNewPresupuesto.getPresupuestoList().remove(presupuestoListNewPresupuesto);
                        oldNumExpedienteOfPresupuestoListNewPresupuesto = em.merge(oldNumExpedienteOfPresupuestoListNewPresupuesto);
                    }
                }
            }
            utx.commit();
        } catch (NotSupportedException | SystemException | IllegalOrphanException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            try {
                utx.rollback();
            } catch (IllegalStateException | SecurityException | SystemException re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = expediente.getNumExpediente();
                if (findExpediente(id) == null) {
                    throw new NonexistentEntityException("The expediente with id " + id + " no longer exists.");
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
            Expediente expediente;
            try {
                expediente = em.getReference(Expediente.class, id);
                expediente.getNumExpediente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The expediente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Presupuesto> presupuestoListOrphanCheck = expediente.getPresupuestoList();
            for (Presupuesto presupuestoListOrphanCheckPresupuesto : presupuestoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Expediente (" + expediente + ") cannot be destroyed since the Presupuesto " + presupuestoListOrphanCheckPresupuesto + " in its presupuestoList field has a non-nullable numExpediente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Mejora idMejora = expediente.getIdMejora();
            if (idMejora != null) {
                idMejora.getExpedienteList().remove(expediente);
                idMejora = em.merge(idMejora);
            }
            ConstruVivienda idVivienda = expediente.getIdVivienda();
            if (idVivienda != null) {
                idVivienda.getExpedienteList().remove(expediente);
                idVivienda = em.merge(idVivienda);
            }
            em.remove(expediente);
            utx.commit();
        } catch (NotSupportedException | SystemException | NonexistentEntityException | IllegalOrphanException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            try {
                utx.rollback();
            } catch (IllegalStateException | SecurityException | SystemException re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Expediente> findExpedienteEntities() {
        return findExpedienteEntities(true, -1, -1);
    }

    public List<Expediente> findExpedienteEntities(int maxResults, int firstResult) {
        return findExpedienteEntities(false, maxResults, firstResult);
    }

    private List<Expediente> findExpedienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Expediente.class));
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

    public Expediente findExpediente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Expediente.class, id);
        } finally {
            em.close();
        }
    }

    public int getExpedienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Expediente> rt = cq.from(Expediente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public void onDuiChange() {
//      listadoDatosCliente = clienteFacade.listaDatosCliente(selectedDui);
        listadoDatosSolicitud = solicitudFacade.listaDatosSolicitud(selectedCliente.getIdCliente());
        listadoDatosCreditoExp = creditoFacade.listaDatosCreditoExp(selectedCliente.getIdCliente());
//        System.out.println("datos:" + listadoDatosSolicitud);
        String cod= String.valueOf(listadoDuiCliente.get(0));
        FacesMessage msg = new FacesMessage("Codigo Marcado", cod);
        FacesContext.getCurrentInstance().addMessage(null, msg);
//         listadoDatosCredito = creditoFacade.listaDatosCredito(selectedCliente.getIdCliente()) ;
    }
    
    public void onDuiChangePre() {
//      listadoDatosCliente = clienteFacade.listaDatosCliente(selectedDui);
        listadoDatosSolicitud = solicitudFacade.listaDatosSolicitud(selectedCliente.getIdCliente());
//        System.out.println("datos:" + listadoDatosSolicitud);
        String cod= String.valueOf(listadoDuiCliente.get(0));
        FacesMessage msg = new FacesMessage("Codigo Marcado", cod);
        FacesContext.getCurrentInstance().addMessage(null, msg);
//         listadoDatosCredito = creditoFacade.listaDatosCredito(selectedCliente.getIdCliente()) ;
    }

    public String aprobarSolicitud() throws SQLException {
        Date fecha = Calendar.getInstance().getTime();
        selectedNumSolicitud.setEstado("Aprobada");
        selectedNumSolicitud.setFechaCambioEstado(fecha);
        solicitudFacade.edit(selectedNumSolicitud);
        expedientefacade.creaCredito(selectedNumSolicitud.getCantCuota(), selectedNumSolicitud.getSolicitudPK().getNumSolicitud());
        expedientefacade.creaCuotaCredito(selectedNumSolicitud.getCantCuota(), selectedNumSolicitud.getSolicitudPK().getNumSolicitud());
        return null;
    }
    
    public void onRowSelected() {
        String cod= String.valueOf(selectedNumSolicitud.getSolicitudPK().getIdCliente()).concat(String.valueOf(selectedNumSolicitud.getSolicitudPK().getIdCliente()));
        FacesMessage msg = new FacesMessage("Registro Seleccionado :", cod);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String observarSolicitud() {
        Date fecha = Calendar.getInstance().getTime();
        selectedNumSolicitud.setEstado("Observada");
        selectedNumSolicitud.setFechaCambioEstado(fecha);
        solicitudFacade.edit(selectedNumSolicitud);
        
        return null;
    }

    public void denegarSolicitud() {
        Date fecha = Calendar.getInstance().getTime();
        selectedNumSolicitud.setEstado("Denegada");
        selectedNumSolicitud.setFechaCambioEstado(fecha);
        solicitudFacade.edit(selectedNumSolicitud);
    }

    public void cancelar() {

    }
    
    public String imprimirReporte() {

        HashMap<String, Object> parametros = new HashMap<>(); 
        parametros.put("id_cliente", selectedNumSolicitud.getSolicitudPK().getIdCliente());  
        parametros.put("num_solicitud", selectedNumSolicitud.getSolicitudPK().getNumSolicitud());
        
//        parametros.put("COD_CIA", getMovimiento().getVtaMovmPK().getCodCia());
//        parametros.put("ID_SUCURSAL", getMovimiento().getVtaMovmPK().getIdSucursal());
//        parametros.put("ID_TIPO_DOCTO_VTA", getMovimiento().getVtaMovmPK().getIdTipoDoctoVta());
//        parametros.put("NUM_DOCTO", getMovimiento().getVtaMovmPK().getNumDocto());
        reporteSessionBean.generarReporteSQLTitulo(FacesContext.getCurrentInstance(), parametros, "detalle_solicitud", "SolicitudCredito"+selectedNumSolicitud.getSolicitudPK().getNumSolicitud());
        return null;
    }

}
