/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import entities.ReferenciasPersonales;
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
public class ReferenciasPersonalesFacade extends AbstractFacade<ReferenciasPersonales> {
    @PersistenceContext(unitName = "SEGEPRO_V2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReferenciasPersonalesFacade() {
        super(ReferenciasPersonales.class);
    }
    
    public List<ReferenciasPersonales> listaReferencias() {
            List<ReferenciasPersonales> listReferencias;
            Query q = em.createNativeQuery("SELECT num_solicitud, id_cliente, nom_solicitante, id_referencia_p, nombre_completo_ref, parentesco, telefono, direccion, lugar_trabajo" +
                                           " FROM referencias_personales order by 1", ReferenciasPersonales.class);
            return listReferencias = q.getResultList();
      }
    
}