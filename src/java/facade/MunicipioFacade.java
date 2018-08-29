/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import entities.Municipio;
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
public class MunicipioFacade extends AbstractFacade<Municipio> {
    @PersistenceContext(unitName = "SEGEPRO_V2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MunicipioFacade() {
        super(Municipio.class);
    }
    
    public List<Municipio> listaMunicipios(int iddepto) {
            List<Municipio> listMunicipios;
            Query q = em.createNativeQuery("SELECT NOM_MUNICIPIO, ID FROM MUNICIPIO WHERE ID_DEPTO = "+iddepto+" ORDER BY NOM_MUNICIPIO ASC", Municipio.class);
            return listMunicipios = q.getResultList();
      }
}
