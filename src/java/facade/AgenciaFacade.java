/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import entities.Agencia;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author BorisECornejo
 */
@Stateless
public class AgenciaFacade extends AbstractFacade<Agencia> {
    @PersistenceContext(unitName = "SEGEPRO_V2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AgenciaFacade() {
        super(Agencia.class);
    }
    
    public List<Agencia> listaAgencias() {
            List<Agencia> listAgencias;
            Query q = em.createNativeQuery("SELECT a.id_agencia, a.telefono, a.encargado, a.direccion, a.nombre, a.id_depto, a.nom_muni, a.estado, a.longitud, a.latitude FROM agencia a ORDER BY a.id_agencia ASC", Agencia.class);
            return listAgencias = q.getResultList();
      }
    
    public Object listaAgencia() {
            Query q = em.createNativeQuery("SELECT MAX(COALESCE(ID_AGENCIA,0))+1 FROM AGENCIA");
            return q.getSingleResult();
      }
    
    public Object lstaCantAgencias() {
            Query q = em.createNativeQuery("SELECT COUNT(*) FROM AGENCIA");
            return  q.getSingleResult();
      }
    
    public Object lstaCantEmpleados() {
            Query q = em.createNativeQuery("SELECT COUNT(*) FROM USUARIO");
            return  q.getSingleResult();
      }
    
    public Object lstaTotCreditos() {
            Query q = em.createNativeQuery("SELECT SUM(COALESCE(monto_solicitado,0)) FROM CREDITO");
            return  q.getSingleResult();
      }
    
}
