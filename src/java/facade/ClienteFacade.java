/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import entities.Cliente;
import entities.Profesiones;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author BorisECornejo
 */
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> {
    @PersistenceContext(unitName = "SEGEPRO_V2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }
    
    public List<Cliente> listaDatosCliente(String dui) {
            TypedQuery<Cliente> q=em.createNamedQuery("Cliente.findByDui", Cliente.class)
                    .setParameter("dui", dui);
            return q.getResultList();
      }
    
    public Object listaCliente() {
            Query q = em.createNativeQuery("SELECT MAX(COALESCE(ID_CLIENTE,0))+1 FROM Cliente");
            return q.getSingleResult();
      }
    
    public List<Profesiones> listadoProfesiones() {
            Query q = em.createNativeQuery("SELECT id_profesion, nombre_profesion FROM profesiones ORDER BY id_profesion ASC",Profesiones.class);
            return q.getResultList();
      }
}
