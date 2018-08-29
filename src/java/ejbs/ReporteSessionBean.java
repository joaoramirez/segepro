/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejbs;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import util.FormatoReporte;

/**
 *
 * @author Carlos y Jose
 */
@Stateless
public class ReporteSessionBean {

    /*@Resource(name = "jdbc/Aerolineas")
    private DataSource JDBCDatasource;*/ 

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public Boolean generarReporteSQLTitulo(FacesContext facesContext, HashMap<String, Object> parametros, String nombreArchivoReporte, String titulo) {
        JRExporter exporter = null;
        Connection conexion = null;
        String rutaReporte = null;
        HttpServletResponse response = null;
        Boolean resultado = null;
        List<Object> arr = null;
        try {

            DataSource dataSource = (DataSource) new InitialContext().lookup("jdbc/segepro");
            conexion = dataSource.getConnection();
            String r = ((ServletContext) facesContext.getExternalContext().getContext()).getRealPath("/");
            rutaReporte = r + "resources" + java.io.File.separator + "reportes" + java.io.File.separator + nombreArchivoReporte + ".jasper";
            parametros.put("SUBREPORT_DIR", r + "resources" + java.io.File.separator + "reportes" + java.io.File.separator);
            response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

            arr = new ArrayList<Object>();
            arr.add("Reporte SQL");
            arr.add(nombreArchivoReporte);
            arr.add(String.format("%1$tY-%<tm-%<td %<tT", Calendar.getInstance().getTime()));
            arr.add(rutaReporte);
            arr.add(parametros);
            arr.add(conexion);
            Logger.getLogger(ReporteSessionBean.class.getName()).log(Level.INFO, "[{0} : {1} : {2} : {3} : {4} : {5}]", arr.toArray());

            JasperPrint jrPrint = JasperFillManager.fillReport(rutaReporte, parametros, conexion);

            exporter = new JRPdfExporter();
            response.setContentType("application/pdf");

            response.setHeader("Content-Disposition", "attachment;filename=\"" + titulo + ".pdf\";");

            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jrPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
            exporter.exportReport();

            //response.getOutputStream().flush();           
            facesContext.responseComplete();

            resultado = Boolean.TRUE;
        } catch (Exception excpt) {
            Logger.getLogger(ReporteSessionBean.class.getName()).log(Level.SEVERE, null, excpt);
            resultado = Boolean.FALSE;
        } finally {
            try {
                if ((conexion != null) && !conexion.isClosed()) {
                    conexion.close();
                }
            } catch (Exception ex) {
                Logger.getLogger(ReporteSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            facesContext.responseComplete();
        }
        return resultado;
    }
    

    public byte[] generarDatosReporteSQL(FacesContext facesContext, HashMap<String, Object> parametros, String nombreArchivoReporte, FormatoReporte type) {
        Connection conexion = null;
        String rutaReporte;
        byte[] bytesReporte = null;
        List<Object> arr;

        try {
            DataSource dataSource = (DataSource) new InitialContext().lookup("jdbc/segepro");
            conexion = dataSource.getConnection();
            String r = ((ServletContext) facesContext.getExternalContext().getContext()).getRealPath("/");
            rutaReporte = r + "resources" + java.io.File.separator + "reportes" + java.io.File.separator + nombreArchivoReporte + ".jasper";
            parametros.put("SUBREPORT_DIR", r + "resources" + java.io.File.separator + "reportes" + java.io.File.separator);

            arr = new ArrayList<>();
            arr.add("Reporte SQL (Datos)");
            arr.add(nombreArchivoReporte);
            arr.add(String.format("%1$tY-%<tm-%<td %<tT", Calendar.getInstance().getTime()));
            arr.add(rutaReporte);
            arr.add(parametros);
            arr.add(conexion);
            Logger.getLogger(ReporteSessionBean.class.getName()).log(Level.INFO, "[{0} : {1} : {2} : {3} : {4} : {5}]", arr.toArray());

            JasperPrint jrPrint = JasperFillManager.fillReport(rutaReporte, parametros, conexion);
            bytesReporte = JasperExportManager.exportReportToPdf(jrPrint);
        } catch (Exception excpt) {
            bytesReporte = null;
            Logger.getLogger(ReporteSessionBean.class.getName()).log(Level.SEVERE, null, excpt);
        } finally {
            try {
                if ((conexion != null) && !conexion.isClosed()) {
                    conexion.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ReporteSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return bytesReporte;
    }
}

