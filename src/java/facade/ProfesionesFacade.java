/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import entities.Profesiones;
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
public class ProfesionesFacade extends AbstractFacade<Profesiones> {
    @PersistenceContext(unitName = "SEGEPRO_V2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfesionesFacade() {
        super(Profesiones.class);
    }
    
    public Object listaProfesiones() {
            Query q = em.createNativeQuery("SELECT MAX(COALESCE(ID_PROFESION,0))+1 FROM PROFESIONES");
            return q.getSingleResult();
      }
    
    public List<Profesiones> listadoProfesiones() {
            Query q = em.createNativeQuery("SELECT id_profesion, nombre_profesion FROM PROFESIONES ORDER BY id_profesion ASC",Profesiones.class);
            return q.getResultList();
      }
    
}
