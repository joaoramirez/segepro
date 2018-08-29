<%-- 
    Document   : menuReportes
    Created on : 03-04-2016, 11:06:02 PM
    Author     : Boris Cornejo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="es"><!-- InstanceBegin template="/Templates/AGESCO_MANTTO.dwt" codeOutsideHTMLIsLocked="false" -->
    <head>
        <!-- DNS prefetch -->
        <!-- Use the .htaccess and remove these lines to avoid edge case issues.
             More info: h5bp.com/b/378 -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>REPORTES :: SEGEPRO</title>
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- Mobile viewport optimized: j.mp/bplateviewport -->
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <!-- Place favicon.ico and apple-touch-icon.png in the root directory: mathiasbynens.be/notes/touch-icons -->
        <!-- CSS: implied media=all -->
        <!-- CSS concatenated and minified via ant build script-->
  <!-- Place favicon.ico and apple-touch-icon.png in the root directory: mathiasbynens.be/notes/touch-icons -->
 <link href="../imgmenu/logo_agesco.png" rel="shortcut icon"/>
  <!-- CSS: implied media=all -->
  <!-- CSS concatenated and minified via ant build script-->
  <link rel="stylesheet" href="../css/style.css"> <!-- Generic style (Boilerplate) -->
  <link rel="stylesheet" href="../css/960.fluid.css"> <!-- 960.gs Grid System -->
  <link rel="stylesheet" href="../css/main.css"> <!-- Complete Layout and main styles -->
  <link rel="stylesheet" href="../css/buttons.css"> <!-- Buttons, optional -->
  <link rel="stylesheet" href="../css/lists.css"> <!-- Lists, optional -->
  <link rel="stylesheet" href="../css/icons.css"> <!-- Icons, optional -->
  <link rel="stylesheet" href="../css/notifications.css"> <!-- Notifications, optional -->
  <link rel="stylesheet" href="../css/typography.css"> <!-- Typography -->
  <link rel="stylesheet" href="../css/forms.css"> <!-- Forms, optional -->
  <link rel="stylesheet" href="../css/tables.css"> <!-- Tables, optional -->
  <link rel="stylesheet" href="../css/charts.css"> <!-- Charts, optional -->
  <link rel="stylesheet" href="../css/jquery-ui-1.8.15.custom.css"> <!-- jQuery UI, optional -->

 <link href="../css/menuprincipal.css" media="screen" rel="stylesheet" type="text/css" />
  <script src="../js/libs/modernizr-2.0.6.min.js"></script>
   <!-- JavaScript at the bottom for fast page loading -->

  <!-- Grab Google CDN's jQuery, with a protocol relative URL; fall back to local if offline -->
  <script src="../js/jquery-1.7.2.js"></script><!-- esta libreria se cambio -->
	
<!-- scripts concatenated and minified via ant build script-->
  <script defer src="../js/plugins.js"></script> <!-- lightweight wrapper for consolelog, optional -->
  <script defer src="../js/mylibs/jquery-ui-1.8.15.custom.min.js"></script> <!-- jQuery UI -->
  <script defer src="../js/mylibs/jquery.notifications.js"></script> <!-- Notifications  -->
  <script defer src="../js/mylibs/jquery.uniform.min.js"></script> <!-- Uniform (Look & Feel from forms) -->
  <script defer src="../js/mylibs/jquery.validate.js"></script> <!-- Validation from forms -->
   <script defer src="../js/mylibs/messages_es.js"></script>
  <script defer src="../js/mylibs/jquery.dataTables.min.js"></script> <!-- Tables -->
  <script defer src="../js/mylibs/jquery.tipsy.js"></script> <!-- Tooltips -->
  <script defer src="../js/mylibs/excanvas.js"></script> <!-- Charts -->
  <script defer src="../js/mylibs/jquery.visualize.js"></script> <!-- Charts -->
  <script defer src="../js/mylibs/jquery.slidernav.min.js"></script> <!-- Contact List -->
  <script defer src="../js/common.js"></script> <!-- Generic functions -->
  <script defer src="../js/script.js"></script> <!-- Generic scripts -->
    <script defer src="../js/jquery.numericalpha.js"></script> <!-- Generic scripts -->
   <link href="../js/jQueryAlert/jquery.css" rel="stylesheet" type="text/css" media="screen">
  <script src="../js/jQueryAlert/jquery.js" type="text/javascript"></script>
  <script type="text/javascript" src="../js/mascara/jquery.mask.js"></script> 
 	

        <!-- InstanceBeginEditable name="head" -->
        <script type="text/javascript" charset="utf-8">
            var asInitVals = new Array();

            $(document).ready(function() {
                var oTable = $('#example').dataTable({
                    "bProcessing": false,
                    "bServerSide": true,
                    "bPaginate": true,
                    "bFilter": true,
                    "bInfo": false,
                    "bSortable": true,
                    "bSort": true,
                    "sPaginationType": "full_numbers",
                    "sAjaxSource": "<%=request.getContextPath()%>/ConsPedido",
                    "oLanguage": {
                        "sProcessing": "Procesando...",
                        "sLengthMenu": "Mostrar _MENU_ registros &nbsp;",
                        "sZeroRecords": "No se encontraron resultados",
                        "sEmptyTable": "Ning&uacute;n dato disponible en esta tabla",
                        "sInfo": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
                        "sInfoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
                        "sInfoFiltered": "(filtrado de un total de _MAX_ registros)",
                        "sInfoPostFix": "",
                        "sSearch": "Buscar:",
                        "sUrl": "",
                        "sInfoThousands": ",",
                        "sLoadingRecords": "Cargando..."
                        ,
                        "oPaginate": {
                            "sFirst": "Primero",
                            "sLast": "Ultimo",
                            "sNext": "Siguiente",
                            "sPrevious": "Anterior"
                        },
                        "oAria": {
                            "sSortAscending": ": Activar para ordenar la columna de manera ascendente",
                            "sSortDescending": ": Activar para ordenar la columna de manera descendente"
                        }
                    }
                });
                $('<div />').addClass('UnSelectAllButton').css({'float': 'right'}).attr({'id': 'UnSelectAllButtons'}).prependTo($('#example_length'));




                $('<button style="width: 80px;" value="&nbsp;&nbsp;&nbsp;&nbsp;Imprimir" onClick="$(\'#buscar_pedido\').dialog({ height: 510,width: 910,modal: true });" />').attr({
                    'id': 'imprimir'
                }).addClass('button ui-widget  ui-corner-all ui-button-text-icon-primary  i-16-imprimir').height('26px')
                        .html('&nbsp;&nbsp;&nbsp;&nbsp;Imprimir')
                        .appendTo($('#UnSelectAllButtons'));

                $('#imprimir').click(function() {
                    $('#mireporte').load("<%=request.getContextPath()%>/r_pedido");

                });

                $("thead input").keyup(function() {
                    /* Filter on the column (the index) of this element */
                    oTable.fnFilter(this.value, $("thead input").index(this));
                });



                /*
                 * Support functions to provide a little bit of 'user friendlyness' to the textboxes in 
                 * the footer
                 */
                $("thead input").each(function(i) {
                    asInitVals[i] = this.value;
                });

                $("thead input").focus(function() {
                    if (this.className == "search_init")
                    {
                        this.className = "";
                        this.value = "";
                    }
                });

                $("thead input").blur(function(i) {
                    if (this.value == "")
                    {
                        this.className = "search_init";
                        /*this.value = asInitVals[$("tfoot input").index(this)];*/
                    }
                });
            });
        </script>
        <!-- InstanceEndEditable -->

    </head>
    <body class="special-page">
        
  <!-- Begin of #container -->
  <div id="container">
  	       
    <!-- Begin of #main -->
  <div id="main" role="main">
 
		<!-- Begin of #main-content -->
		<div id="main-content">
			<div class="container_12"><!-- InstanceBeginEditable name="contenedor" -->
			<div class="grid_12">
            <div class="block-border">
              <div class="block-header">
                    <h1>Consulta  Pedido</h1>
              </div>
                  <form class="block-content" id="form_ConsPedido" onsubmit="return(false)"><br>
<table cellpadding="0" cellspacing="0" border="0"  id="example" class="table">
	<thead>
    <tr  id="buscar">
			<th><input type="text" name="search_engine" value="" class="search_init" id="id" /></th>
			<th><input type="text" name="search_browser" value="" class="search_init"  id="licitacion"/></th>
                        <th><input type="text" name="search_browser" value="" class="search_init"  id="fecha"/></th>
		</tr>
		<tr>
            <th>No. Factura</th>
            <th>No. Licitaci&oacute;n</th>
            <th>Fecha</th>
		</tr>
	</thead>
	<tbody>

        </tbody>
	<tfoot>
		
	</tfoot>
</table>
</form>
</div>
</div>
   			<div   title="Reporte de Pedidos" style="display: none; background-color:#c8e492; " id="buscar_pedido">
				
                            <div class="mireporte" id="mireporte" style="width: 895px; height: 470px; margin-top: -5px; margin-right: 0px; margin-bottom: -21px"> </div>
                                                </div>
<!-- InstanceEndEditable -->
            
			<div class="clear "></div>   
			  
              
			  <div class="clear height-fix"></div>

		</div></div> <!--! end of #main-content -->
  </div> <!--! end of #main -->

    
   <footer id="footer"><div class="container_12">
		<div class="grid_12">
    		<div class="footer-icon align-center">&copy; Todos los derechos reservados. 2015 UES FMP</div>
		</div>
    </div></footer>
  </div> <!--! end of #container --> 
 
  <!-- end scripts-->
    </body>
</html>