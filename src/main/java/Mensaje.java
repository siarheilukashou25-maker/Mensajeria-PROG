
import java.util.GregorianCalendar;

/**
 *
 * @author Siarhei Lukashou
 * @author Elias Perez Arroyo
 */
public class Mensaje {
    //Entorno:
        private static final String APIPA = "169.254.0.1";
        private String usuario;
        private GregorianCalendar fecha;
        private String ip;
        private String texto;
    //Constructores:
        public Mensaje(){
            this.texto = encripta("POR DEFECTO").toUpperCase();
            this.ip = APIPA;
            this.fecha = new GregorianCalendar();
            this.usuario = "System";       
        };
        
        public Mensaje(String usuario, String ip, String texto){
            this.usuario = usuario;
            
            if(esValida(ip)){
                this.ip = ip;
            }else{
                this.ip = APIPA;
            }//Fin Si
            
            this.texto = encripta(texto).toUpperCase();
        }
    //Metodos:
        public GregorianCalendar getFecha(){
            return this.fecha;
        }
        
        public String getTexto(){
            return this.texto;
        }
        
        private boolean esValida(String ip){ 
            //Entorno:
                boolean esValida;
            //Algoritmo:
                esValida = false;
                
                esValida = ip.matches("^(?:(?:25[0-5]|2[0-4]\\d|1?\\d{1,2})(?:\\.(?!$)|$)){4}");
            return esValida;
        }
        
        private String encripta(String msg){  
            return "";
        }
        
        private String desencripta(String msg){
            return "";
        }
        
        private String reverse(String cad){
            return "";
        }
        
        
}
