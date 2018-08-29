/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import entities.Brigadas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author BorisECornejo
 */
@Stateless
public class BrigadasFacade extends AbstractFacade<Brigadas> {
    @PersistenceContext(unitName = "SEGEPRO_V2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BrigadasFacade() {
        super(Brigadas.class);
    }
    
    public Object listaBrigada() {
            Query q = em.createNativeQuery("SELECT MAX(COALESCE(ID_BRIGADA,0))+1 FROM BRIGADAS");
            return q.getSingleResult();
      }
    
}
