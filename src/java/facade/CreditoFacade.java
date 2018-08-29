
package facade;

import entities.Credito;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author BorisECornejo
 */
@Stateless
public class CreditoFacade extends AbstractFacade<Credito> {
    @PersistenceContext(unitName = "SEGEPRO_V2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CreditoFacade() {
        super(Credito.class);
    }
    
    public List<Credito> listaDatosCredito(int idcliente) {
            TypedQuery<Credito> q=em.createNamedQuery("Credito.findAll", Credito.class)
                    .setParameter("idCliente", idcliente);
            return q.getResultList();
      }

    public List<Credito> listaDatosCreditoExp(int idcliente) {
            TypedQuery<Credito> q=em.createNamedQuery("Credito.findByIdCliente", Credito.class)
                    .setParameter("idCliente", idcliente);
            return q.getResultList();
      }
    
}
