/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entities.Supervisiones;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Boris Cornejo
 */
@Stateless
public class SupervisionesFacade extends AbstractFacade<Supervisiones> {

    @PersistenceContext(unitName = "SEGEPRO_V2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SupervisionesFacade() {
        super(Supervisiones.class);
    }
    
}
