/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entities.SolicitudHist;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Boris Cornejo
 */
@Stateless
public class SolicitudHistFacade extends AbstractFacade<SolicitudHist> {

    @PersistenceContext(unitName = "SEGEPRO_V2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SolicitudHistFacade() {
        super(SolicitudHist.class);
    }
    
}
