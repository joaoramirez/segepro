/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import entities.Mejora;
import java.math.BigDecimal;
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
public class MejoraFacade extends AbstractFacade<Mejora> {
    @PersistenceContext(unitName = "SEGEPRO_V2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MejoraFacade() {
        super(Mejora.class);
    }
    
    public List<Mejora> listaMejoras() {
            List<Mejora> listMejoras;
            Query q = em.createNativeQuery("SELECT id_mejora, nombre_mejora, costo, incluye, area_construida, descripcion FROM mejora order by 1", Mejora.class);
            return listMejoras = q.getResultList();
      }
    
    public Object listaIdMejoras() {
            Query q = em.createNativeQuery("SELECT MAX(id_mejora)+1 FROM mejora");
            return q.getSingleResult();
      }
    
    public List<Object[]> listadoMejoras() {
            Query q = em.createNativeQuery("SELECT id_mejora, nombre_mejora FROM mejora order by 1");
            return q.getResultList();
      }
    
    public Object listaMejorasCosto(int idMejora) {
            Query q = em.createNativeQuery("SELECT costo FROM mejora where id_mejora ="+idMejora);
            return q.getSingleResult();
      }
}
