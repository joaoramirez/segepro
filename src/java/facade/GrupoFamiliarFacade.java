/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import entities.GrupoFamiliar;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author BorisECornejo
 */
@Stateless
public class GrupoFamiliarFacade extends AbstractFacade<GrupoFamiliar> {
    @PersistenceContext(unitName = "SEGEPRO_V2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupoFamiliarFacade() {
        super(GrupoFamiliar.class);
    }
    
}
