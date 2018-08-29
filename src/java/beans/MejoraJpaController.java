/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Expediente;
import entities.Mejora;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author BorisECornejo
 */
@ManagedBean(name = "mejoraController")
@ViewScoped
public class MejoraJpaController implements Serializable {

    @EJB
    private facade.MejoraFacade mejoraFacade;

    private List<Mejora> listadoMejoras = new ArrayList<Mejora>();
    private Mejora selectedMejora;
    private int idMejora;
    private BigDecimal costo;
    private String nomMejora, incluye, area, descripcion;

    private int idMejora_modal;
    private BigDecimal costo_modal;
    private String nomMejora_modal, incluye_modal, area_modal, descripcion_modal;

    public int getIdMejora_modal() {
        return idMejora_modal;
    }

    public void setIdMejora_modal(int idMejora_modal) {
        this.idMejora_modal = idMejora_modal;
    }

    public BigDecimal getCosto_modal() {
        return costo_modal;
    }

    public void setCosto_modal(BigDecimal costo_modal) {
        this.costo_modal = costo_modal;
    }

    public String getNomMejora_modal() {
        return nomMejora_modal;
    }

    public void setNomMejora_modal(String nomMejora_modal) {
        this.nomMejora_modal = nomMejora_modal;
    }

    public String getIncluye_modal() {
        return incluye_modal;
    }

    public void setIncluye_modal(String incluye_modal) {
        this.incluye_modal = incluye_modal;
    }

    public String getArea_modal() {
        return area_modal;
    }

    public void setArea_modal(String area_modal) {
        this.area_modal = area_modal;
    }

    public String getDescripcion_modal() {
        return descripcion_modal;
    }

    public void setDescripcion_modal(String descripcion_modal) {
        this.descripcion_modal = descripcion_modal;
    }

    public int getIdMejora() {
        return idMejora;
    }

    public void setIdMejora(int idMejora) {
        this.idMejora = idMejora;
    }

    public BigDecimal getCosto() {
        costo = BigDecimal.ZERO;
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public String getNomMejora() {
        return nomMejora;
    }

    public void setNomMejora(String nomMejora) {
        this.nomMejora = nomMejora;
    }

    public String getIncluye() {
        return incluye;
    }

    public void setIncluye(String incluye) {
        this.incluye = incluye;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Mejora getSelectedMejora() {
        return selectedMejora;
    }

    public void setSelectedMejora(Mejora selectedMejora) {
        this.selectedMejora = selectedMejora;
    }

    public List<Mejora> getListadoMejoras() {
        return listadoMejoras;
    }

    public void setListadoMejoras(List<Mejora> listadoMejoras) {
        this.listadoMejoras = listadoMejoras;
    }

    public MejoraJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public MejoraJpaController() {

    }

    @PostConstruct
    public void init() {
        selectedMejora = new Mejora();
        generar();
        llenarTabla();
    }

    public void create(Mejora mejora) throws Exception {
        if (mejora.getExpedienteList() == null) {
            mejora.setExpedienteList(new ArrayList<Expediente>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Expediente> attachedExpedienteList = new ArrayList<Expediente>();
            for (Expediente expedienteListExpedienteToAttach : mejora.getExpedienteList()) {
                expedienteListExpedienteToAttach = em.getReference(expedienteListExpedienteToAttach.getClass(), expedienteListExpedienteToAttach.getNumExpediente());
                attachedExpedienteList.add(expedienteListExpedienteToAttach);
            }
            mejora.setExpedienteList(attachedExpedienteList);
            em.persist(mejora);
            for (Expediente expedienteListExpediente : mejora.getExpedienteList()) {
                Mejora oldIdMejoraOfExpedienteListExpediente = expedienteListExpediente.getIdMejora();
                expedienteListExpediente.setIdMejora(mejora);
                expedienteListExpediente = em.merge(expedienteListExpediente);
                if (oldIdMejoraOfExpedienteListExpediente != null) {
                    oldIdMejoraOfExpedienteListExpediente.getExpedienteList().remove(expedienteListExpediente);
                    oldIdMejoraOfExpedienteListExpediente = em.merge(oldIdMejoraOfExpedienteListExpediente);
                }
            }
            utx.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            try {
                utx.rollback();
            } catch (IllegalStateException | SecurityException | SystemException re) {
                throw new Exception("An error occurred attempting to roll back the transaction.", re);
            }
            if (findMejora(mejora.getIdMejora()) != null) {
                throw new Exception("Mejora " + mejora + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mejora mejora) throws Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Mejora persistentMejora = em.find(Mejora.class, mejora.getIdMejora());
            List<Expediente> expedienteListOld = persistentMejora.getExpedienteList();
            List<Expediente> expedienteListNew = mejora.getExpedienteList();
            List<String> illegalOrphanMessages = null;
            for (Expediente expedienteListOldExpediente : expedienteListOld) {
                if (!expedienteListNew.contains(expedienteListOldExpediente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Expediente " + expedienteListOldExpediente + " since its idMejora field is not nullable.");
                }
            }
            List<Expediente> attachedExpedienteListNew = new ArrayList<Expediente>();
            for (Expediente expedienteListNewExpedienteToAttach : expedienteListNew) {
                expedienteListNewExpedienteToAttach = em.getReference(expedienteListNewExpedienteToAttach.getClass(), expedienteListNewExpedienteToAttach.getNumExpediente());
                attachedExpedienteListNew.add(expedienteListNewExpedienteToAttach);
            }
            expedienteListNew = attachedExpedienteListNew;
            mejora.setExpedienteList(expedienteListNew);
            mejora = em.merge(mejora);
            for (Expediente expedienteListNewExpediente : expedienteListNew) {
                if (!expedienteListOld.contains(expedienteListNewExpediente)) {
                    Mejora oldIdMejoraOfExpedienteListNewExpediente = expedienteListNewExpediente.getIdMejora();
                    expedienteListNewExpediente.setIdMejora(mejora);
                    expedienteListNewExpediente = em.merge(expedienteListNewExpediente);
                    if (oldIdMejoraOfExpedienteListNewExpediente != null && !oldIdMejoraOfExpedienteListNewExpediente.equals(mejora)) {
                        oldIdMejoraOfExpedienteListNewExpediente.getExpedienteList().remove(expedienteListNewExpediente);
                        oldIdMejoraOfExpedienteListNewExpediente = em.merge(oldIdMejoraOfExpedienteListNewExpediente);
                    }
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (IllegalStateException | SecurityException | SystemException re) {
                throw new Exception("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = mejora.getIdMejora();
                if (findMejora(id) == null) {
                    throw new Exception("The mejora with id " + id + " no longer exists.");
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
            Mejora mejora;
            try {
                mejora = em.getReference(Mejora.class, id);
                mejora.getIdMejora();
            } catch (EntityNotFoundException enfe) {
                throw new Exception("The mejora with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Expediente> expedienteListOrphanCheck = mejora.getExpedienteList();
            for (Expediente expedienteListOrphanCheckExpediente : expedienteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Mejora (" + mejora + ") cannot be destroyed since the Expediente " + expedienteListOrphanCheckExpediente + " in its expedienteList field has a non-nullable idMejora field.");
            }
            em.remove(mejora);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (IllegalStateException | SecurityException | SystemException re) {
                throw new Exception("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mejora> findMejoraEntities() {
        return findMejoraEntities(true, -1, -1);
    }

    public List<Mejora> findMejoraEntities(int maxResults, int firstResult) {
        return findMejoraEntities(false, maxResults, firstResult);
    }

    private List<Mejora> findMejoraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mejora.class));
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

    public Mejora findMejora(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mejora.class, id);
        } finally {
            em.close();
        }
    }

    public int getMejoraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mejora> rt = cq.from(Mejora.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public void Mensaje() {
        addMessage("Informacion", "Actualizacion completada.");
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onRowSelectMejora(SelectEvent event) {
        String codigo_mejora = String.valueOf(selectedMejora.getIdMejora());
        FacesMessage msg = new FacesMessage("Codigo Marcado", codigo_mejora);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        idMejora_modal = selectedMejora.getIdMejora();
        nomMejora_modal = selectedMejora.getNombreMejora();
        descripcion_modal = selectedMejora.getDescripcion();
        incluye_modal = selectedMejora.getIncluye();
        area_modal = selectedMejora.getAreaConstruida();
        costo_modal = selectedMejora.getCosto();
    }

    public void generar() {
        try {
            idMejora = (int) mejoraFacade.listaIdMejoras();
        } catch (Exception e) {
            idMejora = 1;
        }
    }

    public void llenarTabla() {
        listadoMejoras = mejoraFacade.listaMejoras();
    }

    public void guardar() {
        try {
            Mejora m = new Mejora();
            m.setIdMejora(idMejora);
            m.setCosto(costo);
            m.setDescripcion(descripcion);
            m.setNombreMejora(nomMejora);
            m.setIncluye(incluye);
            m.setAreaConstruida(area);
            mejoraFacade.create(m);
            FacesMessage msgInfo = new FacesMessage("Registro guardado con exito!");
            FacesContext.getCurrentInstance().addMessage(null, msgInfo);
            limpiarForm();
        } catch (Exception e) {
            FacesMessage msgError = new FacesMessage("No se pudo guardar el registro " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msgError);
        }

    }

    public void limpiarForm() {
        idMejora = 0;
        costo = BigDecimal.ZERO;
        descripcion = null;
        nomMejora = null;
        incluye = null;
        area = null;
        generar();
        llenarTabla();
    }

    public void actualizaMejora() {
        try {
            selectedMejora.setCosto(costo_modal);
            selectedMejora.setDescripcion(descripcion_modal);
            selectedMejora.setNombreMejora(nomMejora_modal);
            selectedMejora.setIncluye(incluye_modal);
            selectedMejora.setAreaConstruida(area_modal);
            mejoraFacade.edit(selectedMejora);
            llenarTabla();
            FacesMessage msgInfo = new FacesMessage("Registro actualizar con exito!");
            FacesContext.getCurrentInstance().addMessage(null, msgInfo);
        } catch (Exception e) {
            FacesMessage msgError = new FacesMessage("No se pudo actualizar el registro " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msgError);
        }

    }

}
