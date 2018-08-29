package util;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.List;
import java.util.Random;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItem;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


public class JsfUtil {
 private static Logger LOGGER ;

    public static void addErrorMessage(String msg) {
	FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
	FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	FacesContext.getCurrentInstance().validationFailed(); // Invalidate JSF page if we raise an error message

    }

   
    public static void addSuccessMessage(String msg) {
	FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg); 
	FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }


    public static Throwable getRootCause(Throwable cause) {
	if (cause != null) {
	    Throwable source = cause.getCause();
	    if (source != null) {
		return getRootCause(source);
	    } else {
		return cause;
	    }
	}
	return null;
    }

    public static boolean isValidationFailed() {
	return FacesContext.getCurrentInstance().isValidationFailed();
    }

    public static boolean isDummySelectItem(UIComponent component, String value) {
	for (UIComponent children : component.getChildren()) {
	    if (children instanceof UISelectItem) {
		UISelectItem item = (UISelectItem) children;
		if (item.getItemValue() == null && item.getItemLabel().equals(value)) {
		    return true;
		}
		break;
	    }
	}
	return false;
    }
    
public static int diasDelMes(int mes, int año){
    mes= mes-1;
    switch(mes){
	case 0:  // Enero
	case 2:  // Marzo
	case 4:  // Mayo
	case 6:  // Julio
	case 7:  // Agosto
	case 9:  // Octubre
	case 11: // Diciembre
	    return 31;
	case 3:  // Abril
	case 5:  // Junio
	case 8:  // Septiembre
	case 10: // Noviembre
	    return 30;
	case 1:  // Febrero
	    if ( ((año%100 == 0) && (año%400 == 0)) ||
		((año%100 != 0) && (año%  4 == 0))   )
		return 29;  // Año Bisiesto
	    else
		return 28;
	default:
	    throw new java.lang.IllegalArgumentException("El mes debe estar entre 0 y 11");
    }
}

public static double Redondear(double numero,int digitos)
{
      int cifras=(int) Math.pow(10,digitos);
      return Math.rint(numero*cifras)/cifras;
}

public static void logs(Exception ex,String summary , String detail, Class cl, String level){    
    if(level.equals("ERROR")){
        Logger.getLogger(cl.getName()).log(Level.SEVERE, detail, ex);                              
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL ,summary, cl+detail));                
    }
    if(level.equals("ALERT")){
        Logger.getLogger(cl.getName()).log(Level.WARNING, detail, ex);                                  
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,summary, cl+detail));
    }
    if(level.equals("INFO")){
        Logger.getLogger(cl.getName()).log(Level.INFO, detail, ex);                                          
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,summary, cl+detail));
    }    
                          
}

public static String truncate(String value, int length)
{
  if (value != null && value.length() > length)
    value = value.substring(0, length);
  return value;
}

public static double Redondear2(float numero,int digitos)
{
      int cifras=(int) Math.pow(10,digitos);
      return Math.rint(numero*cifras)/cifras;
}

    /**
     * Metodo que encripta una cadena utilizando el algoritmo MD5
     * @param txt cadena a encriptar
     * @return una cadena encriptada
     * @throws Exception error generico 
     */
    public static String EncriptadorMD5(String txt) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] b = md.digest(txt.getBytes());
        int size = b.length;
        StringBuffer h = new StringBuffer(size);
        // algoritmo y arreglo md5
        for (int i = 0; i < size; i++) {
            int u = b[i] & 255;
            if (u < 16) {
                h.append("0" + Integer.toHexString(u));
            } else {
                h.append(Integer.toHexString(u));
            }
        }
        // clave encriptada
        return h.toString();
    }
    
    public String  String_fecha(String aa){
	       /*SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es_ES"));
	        Date fechaDate = new Date();*/
                 String fecha="AA";
	      return fecha;
   }	
    
    public static String Meses(short mes){
        String vmes= "";
         switch (mes) {
               case 1:  vmes = "ENERO";
                     break;
               case 2:  vmes = "FEBRERO";
                     break;   
               case 3:  vmes = "MARZO";
                     break;   
               case 4:  vmes = "ABRIL";
                     break;   
               case 5:  vmes = "MAYO";
                     break;   
               case 6:  vmes = "JUNIO";
                     break;   
               case 7:  vmes = "JULIO";
                     break;   
               case 8:  vmes = "AGOSTO";
                     break;   
               case 9:  vmes = "SEPTIEMBRE";
                     break;   
               case 10:  vmes = "OCTUBRE";
                     break; 
               case 11:  vmes = "NOVIEMBRE";
                     break;   
               case 12:  vmes = "DICIEMBRE";
                     break;                       
             default: vmes = "error";
                     break;
         }
                
        return vmes;
    }
    
   public static enum PersistAction {

        CREATE,
        DELETE,
        UPDATE
    }  
    
   
    public String toString() {
      final StringBuilder objBuilder = new StringBuilder();

      // Inicio de cadena.
      objBuilder.append(getClass().getSimpleName());
      objBuilder.append(" {");

      try {
        // Comienza el análisis por la clase actual y recorre los elementos hasta
        // llegar a la clase base Object.
        Class<?> objClase = getClass();
        Field[] arCampos;
        String sTipo;

        while (null != objClase
               && !Object.class.getSimpleName().equals(objClase.getSimpleName())) {
          arCampos = objClase.getDeclaredFields();
          if (null != arCampos) {
            // Recorre los campos del objeto. Si es una constante omite su lectura.
            for (Field objCampo : arCampos) {
              sTipo = objCampo.getType().toString();
              if (!Modifier.isFinal(objCampo.getModifiers())
                  || (sTipo.startsWith("class")
                  && !sTipo.endsWith(String.class.getCanonicalName()))) {
                objCampo.setAccessible(true);
                objBuilder.append(objCampo.getName());
                objBuilder.append("->");
                objBuilder.append(objCampo.get(this));
                objBuilder.append("; ");
              }
            }
          }

          // Pasa a la siguiente clase padre.
          objClase = objClase.getSuperclass();
        }
      } catch (final IllegalArgumentException e) {
      } catch (final IllegalAccessException e) {
      }

      // Fin de cadena. Si hay datos elimina el último "; ".
      if (objBuilder.length() > getClass().getSimpleName().length() + 2) {
        objBuilder.setLength(objBuilder.length() - 2);
      }
      objBuilder.append('}');

      return objBuilder.toString();
    }       
    
    public static short seccionCia(){
    
     HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);                 
     short sspro  =  (short) session.getAttribute("SSCIAVAL") ;
     
     return sspro;
     
    }
    
public static BigDecimal round(BigDecimal d, int scale, boolean roundUp) {
  int mode = (roundUp) ? BigDecimal.ROUND_UP : BigDecimal.ROUND_DOWN;
  return d.setScale(scale, mode);
}

    /**
     * Genera una cadena alfanumerica aleatoria.
     * @param longitud longituda de la cadena deseada
     * @return el valor de la cadena aleatoria
     */
    public static String cadenaAlfaNumAleatoria(int longitud) {
        String cadenaAleatoria = "";
        long milis = new java.util.GregorianCalendar().getTimeInMillis();
        Random r = new Random(milis);
        int i = 0;
        while ( i < longitud){
            char c = (char)r.nextInt(255);
            if ( (c >= '0' && c <='9') || (c >='A' && c <='Z') ){
                cadenaAleatoria += c;
                i ++;
            }
        }
        return cadenaAleatoria;
    }
    
     public static boolean isInteger(String s) {
      boolean isValidInteger = false;
      try
      {
         Integer.parseInt(s);
 
         // s is a valid integer
 
         isValidInteger = true;
      }
      catch (NumberFormatException ex)
      {
         // s is not an integer
      }
 
      return isValidInteger;
   }
     
 private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
 
    /**
     * Validate given email with regular expression.
     * 
     * @param email
     *            email for validation
     * @return true valid email, otherwise false
     */
    public static boolean validateEmail(String email) {
 
        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
 
        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
 
    }     
    
     public static double redondearDecimales(double valorInicial, int numeroDecimales) {
        double parteEntera, resultado;
        resultado = valorInicial;
        parteEntera = Math.floor(resultado);
        resultado=(resultado-parteEntera)*Math.pow(10, numeroDecimales);
        resultado=Math.round(resultado);
        resultado=(resultado/Math.pow(10, numeroDecimales))+parteEntera;
        return resultado;
    }
     
        
    
}
