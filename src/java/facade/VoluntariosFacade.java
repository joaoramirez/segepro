/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import entities.Voluntarios;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author BorisECornejo
 */
@Stateless
public class VoluntariosFacade extends AbstractFacade<Voluntarios> {
    @PersistenceContext(unitName = "SEGEPRO_V2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VoluntariosFacade() {
        super(Voluntarios.class);
    }
    
    public Object listaVoluntarios() {
            Query q = em.createNativeQuery("SELECT MAX(COALESCE(ID_VOLUNTARIO,0))+1 FROM VOLUNTARIOS");
            return q.getSingleResult();
      }
    
}
