/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import entities.UnidadMedida;
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
public class UnidadMedidaFacade extends AbstractFacade<UnidadMedida> {
    @PersistenceContext(unitName = "SEGEPRO_V2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UnidadMedidaFacade() {
        super(UnidadMedida.class);
    }
    
    public Object listaUnidad() {
            Query q = em.createNativeQuery("SELECT MAX(COALESCE(COD_UNIDAD,0))+1 FROM UNIDAD_MEDIDA");
            return q.getSingleResult();
      }
    
}
