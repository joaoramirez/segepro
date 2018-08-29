/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import entities.Departamento;
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
public class DepartamentoFacade extends AbstractFacade<Departamento> {
    @PersistenceContext(unitName = "SEGEPRO_V2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepartamentoFacade() {
        super(Departamento.class);
    }
    
        public List<Departamento> listaDepartamentos() {
            List<Departamento> listDepartamentos;
            Query q = em.createNativeQuery("SELECT ID_DEPTO, NOMBRE FROM DEPARTAMENTO ORDER BY NOMBRE ASC", Departamento.class);
            return listDepartamentos = q.getResultList();
      }
}
