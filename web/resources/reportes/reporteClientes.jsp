<%-- 
    Document   : reporteClientes
    Created on : 03-19-2017, 07:43:49 PM
    Author     : Boris Cornejo
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
    File reportFile = new File(application.getRealPath("Reporteria/ReporteListado_Clientes.jasper"));
    System.out.println("llego al reporte.....");
    Map parameters = new HashMap();
  
//    List<String> sArray = new ArrayList<String>();
// 
//        if (!request.getParameter("terminoreporte").equals("")){
//                 sArray.add("(tb_pedido.num_factura::text like '%"+request.getParameter("terminoreporte").toUpperCase()+"%'"
//                  + " or tb_pedido.licitacion  like '%"+request.getParameter("terminoreporte").toUpperCase()+"%'"
//                  + " or tb_pedido.fech_factura like '%"+request.getParameter("terminoreporte").toUpperCase()+"%')");
//            }
//        
//       if (!request.getParameter("id").equals("")) {
//                String Idw = " tb_pedido.num_factura::text like '%" +request.getParameter("id").toUpperCase()+ "%'";
//                sArray.add(Idw);
//            }
//   if (!request.getParameter("licitacion").equals("")) {
//        String sBrowser = " tb_pedido.licitacion like '%" + request.getParameter("licitacion").toUpperCase()+ "%'";
//        sArray.add(sBrowser);
//    }
//       if (!request.getParameter("fecha").equals("")) {
//        String sVer = " tb_pedido.fech_factura like '%" + request.getParameter("fecha").toUpperCase()+ "%'";
//        sArray.add(sVer);
//            }
//    
//            
//           String individualSearch = "";
//            if(sArray.size()==1){
//                System.out.println("Arra==1");
//                individualSearch = sArray.get(0);
//                //individualSearch = " and "+ sArray.get(0);
//            }else if(sArray.size()>1){individualSearch +=" ";
//                System.out.println("Array >1");
//                for(int i=0;i<sArray.size()-1;i++){System.out.println("form:"+i);
//                    individualSearch += sArray.get(i)+ " and ";
//                }
//                individualSearch += sArray.get(sArray.size()-1);
//            }
//            String searchSQL="";
//            if(individualSearch!=""){System.out.println("!vacio");
//                searchSQL = " WHERE  " + individualSearch;
//               // searchSQL = "  " + individualSearch;System.out.println("searchSQL:"+searchSQL);
//            }else {System.out.println("vacio");
//                searchSQL="";
//            }
//            System.out.println("SQL:"+searchSQL);
//            
//            
//            System.out.println("cond: "+searchSQL);
//            parameters.put("condicion"," "+searchSQL );
              
              parameters.put("Id_Cliente", 0);

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