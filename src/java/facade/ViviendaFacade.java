/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import entities.Vivienda;
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
public class ViviendaFacade extends AbstractFacade<Vivienda> {
    @PersistenceContext(unitName = "SEGEPRO_V2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ViviendaFacade() {
        super(Vivienda.class);
    }
    
    public List<Vivienda> listaViviendas() {
            List<Vivienda> listViviendas;
            Query q = em.createNativeQuery("SELECT id_modelo,nombre_modelo,incluye,area_construida,descripcion,vivienda.costo FROM vivienda order by 1", Vivienda.class);
            return listViviendas = q.getResultList();
      }
    
    public Object listaVivienda() {
            Query q = em.createNativeQuery("SELECT MAX(COALESCE(ID_MODELO,0))+1 FROM VIVIENDA");
            return q.getSingleResult();
      }
    
    public List<Object[]> listadoViviendas() {
            Query q = em.createNativeQuery("SELECT id_modelo,nombre_modelo FROM vivienda order by 1");
            return q.getResultList();
      }
    
    public Object listaViviendaCosto(int idModelo) {
            Query q = em.createNativeQuery("SELECT costo FROM vivienda where id_modelo="+idModelo);
            return q.getSingleResult();
      }

    public BigDecimal listaViviendaCosto(String modeloVivienda) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
