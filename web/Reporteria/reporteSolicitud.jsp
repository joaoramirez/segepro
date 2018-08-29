<%-- 
    Document   : reporteSolicitud
    Created on : 08-26-2017, 06:25:26 PM
    Author     : BorisECornejo
--%>

<%@page import="net.sf.jasperreports.engine.JRException"%>
<%@page import="net.sf.jasperreports.engine.JasperRunManager"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.io.File"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Connection"%>
<%
    Connection conn;
    conn = Conexiones.conexionDatos.GetConnection();
    File reportFile = new File(application.getRealPath("Reporteria/detalle_solicitud.jasper"));
    Map parameters = new HashMap();
    
//    String hidcodigo = "1";
//    
//    int codCliente = Integer.parseInt(request.getParameter(hidcodigo));
              
              parameters.put("id_cliente", 1);

    try{
    byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath (),parameters ,conn);

    response.setContentType("application/pdf");

    response.setContentLength(bytes.length);
    ServletOutputStream ouputStream = response.getOutputStream();
    ouputStream.write(bytes, 0, bytes.length);
    ouputStream.flush();
    ouputStream.close();
   } catch (JRException e) {
            }
 //HttpSession sesion=request.getSession();
//sesion.invalidate();
                %>