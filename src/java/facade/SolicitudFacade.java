/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entities.Cliente;
import entities.Solicitud;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Boris Cornejo
 */
@Stateless
public class SolicitudFacade extends AbstractFacade<Solicitud> {

    @PersistenceContext(unitName = "SEGEPRO_V2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SolicitudFacade() {
        super(Solicitud.class);
    }
    
        public List<Solicitud> listadoSolicitud() {
            Query q = em.createNativeQuery("SELECT * FROM solicitud ORDER BY num_solicitud ASC", Solicitud.class);
            return q.getResultList();
      }
    
    public List<Solicitud> listaDatosSolicitud(int idcliente) {
            TypedQuery<Solicitud> q=em.createNamedQuery("Solicitud.findByIdCliente", Solicitud.class)
                    .setParameter("idCliente", idcliente);
            return q.getResultList();
      }
    
    public Object listaSolicitud() {
            Query q = em.createNativeQuery("SELECT MAX(COALESCE(NUM_SOLICITUD,0))+1 FROM SOLICITUD");
            return q.getSingleResult();
      }
    
    public List<Object[]> listadoClientes() {
            Query q = em.createNativeQuery("SELECT id_cliente, nombre||apellido, dui FROM cliente ORDER BY id_cliente ASC");
            return q.getResultList();
      }
    
    public List<Cliente> listadoDuiClientes() {
            Query q = em.createNativeQuery("select c.* from cliente c, solicitud s where s.id_cliente = c.id_cliente", Cliente.class);
            return q.getResultList();
      }
    
    public List<Cliente> listadoDuiClientesP() {
            Query q = em.createNativeQuery("select c.* from cliente c, solicitud s where s.estado = 'Emitida' and s.id_cliente = c.id_cliente", Cliente.class);
            return q.getResultList();
      }
    
}
