/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import entities.Expediente;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author BorisECornejo
 */
@Stateless
public class ExpedienteFacade extends AbstractFacade<Expediente> {
    @PersistenceContext(unitName = "SEGEPRO_V2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExpedienteFacade() {
        super(Expediente.class);
    }
    
    public void creaCredito(int cuota, int numSolicitud) throws SQLException {
        String sql = "SELECT crear_credito(?,?)";
        Connection conexion = null;
        CallableStatement stmt = null;
        try {
           // System.out.println(empresa + "|" + serie + "|" + numDocto + "|" + status);
            conexion = em.unwrap(Connection.class);
            stmt = conexion.prepareCall(sql);
            stmt.setInt(1, cuota);
            stmt.setInt(2, numSolicitud);
            //stmt.setString(3, numDocto);
            //stmt.setString(4, status);
            stmt.execute();
            stmt.close();
            conexion.close();
            System.out.println("finalizo proceso del encabezado");
        } catch (Exception e) {
            System.out.println("error " + e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException ex) {
                System.err.println("ouch!");
            }
        }
    }
    
    public void creaCuotaCredito(int cuota, int numSolicitud) throws SQLException {
        String sql2 = "SELECT crear_cuota_credito(?,?)";
        Connection conexion2 = null;
        CallableStatement stmt2 = null;
        try {
           // System.out.println(empresa + "|" + serie + "|" + numDocto + "|" + status);
            conexion2 = em.unwrap(Connection.class);
            stmt2 = conexion2.prepareCall(sql2);
            stmt2.setInt(1, cuota);
            stmt2.setInt(2, numSolicitud);
            //stmt.setString(3, numDocto);
            //stmt.setString(4, status);
            stmt2.execute();
            stmt2.close();
            conexion2.close();
            System.out.println("finalizo proceso del detalle");
        } catch (Exception e) {
            System.out.println("error " + e);
        } finally {
            try {
                if (stmt2 != null) {
                    stmt2.close();
                }
                if (conexion2 != null) {
                    conexion2.close();
                }
            } catch (SQLException ex) {
                System.err.println("ouch!");
            }
        }
    }
}
