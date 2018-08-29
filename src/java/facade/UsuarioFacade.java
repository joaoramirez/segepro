/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import entities.Profesiones;
import entities.Usuario;
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
public class UsuarioFacade extends AbstractFacade<Usuario> {
    @PersistenceContext(unitName = "SEGEPRO_V2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public Object listaUsuario() {
            Query q = em.createNativeQuery("SELECT MAX(COALESCE(ID_USUARIO,0))+1 FROM Usuario");
            return q.getSingleResult();
      }
    
    public List<Profesiones> listadoProfesiones() {
            Query q = em.createNativeQuery("SELECT id_profesion, nombre_profesion FROM profesiones ORDER BY id_profesion ASC",Profesiones.class);
            return q.getResultList();
      }
    
    public List<Object[]> listaAgencia() {
            Query q = em.createNativeQuery("SELECT id_agencia, nombre FROM Agencia order by id_agencia asc");
            return q.getResultList();
      }
}
