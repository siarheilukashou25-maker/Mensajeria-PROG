import java.util.GregorianCalendar;

/**
 *
 * @author Siarhei Lukashou
 * @author Elias Perez Arroyo
 */
public class Mensaje {
    //Atributos:
        private static final String APIPA = "169.254.0.1";
        private String usuario;
        private GregorianCalendar fecha;
        private String ip;
        private String texto;
    //Constructores:
        public Mensaje() {
            this.texto = encripta("POR DEFECTO").toUpperCase();
            this.ip = APIPA;
            this.fecha = new GregorianCalendar();
            this.usuario = "System";
        }  
        public Mensaje(String usuario, String ip, String texto) {
            this.usuario = usuario;

            if (esValida(ip)) {
                this.ip = ip;
            } else {
                this.ip = APIPA;
            }//Fin Si
            
            //this.ip = esValida(ip) ? ip : APIPA; --Operador ternario
        
            this.texto = encripta(texto).toUpperCase();
        }
    //Metodos:

    public GregorianCalendar getFecha() {
        return this.fecha;
    }

    public String getTexto() {
        return this.texto;
    }

    private static boolean esValida(String ip) {
        //Entorno:
            boolean esValida;
        //Algoritmo:
            esValida = false;

            esValida = ip.matches("^(?:(?:25[0-5]|2[0-4]\\d|1?\\d{1,2})(?:\\.(?!$)|$)){4}");
            return esValida;
    }

    private static String encripta(String msg) {
        //Entorno:
            String bufferInv, bufferEncript,mensajeEncript;
            String[] palabras;
            int i, j;
        //Algoritmo:
            i = 0;
            j = 0;
            mensajeEncript="";
            bufferInv = "";
            bufferEncript = "";
            
            bufferInv = reverse(msg);           
            palabras = bufferInv.toUpperCase().split("\\s+");
        
            for (i = 0; i < palabras.length; i++) {           
                bufferEncript="";
                for (j = 0; j < palabras[i].length(); j++) {               
                    if (palabras[i].charAt(j) + 3 > 90) {
                        bufferEncript+=(char)(((palabras[i].charAt(j) + 3)  - 'Z') + 'A' - 1);
                    } else {
                        bufferEncript += (char)(palabras[i].charAt(j) + 3);
                    }//Fin Si
                }//Fin Para
                mensajeEncript+=bufferEncript+" ";
            }//Fin Para
        
            return mensajeEncript.trim();
    }

    private static String desencripta(String msg) {
        //Entorno:
            String bufferInv, bufferEncript,mensajeEncript;
            String[] palabras;
            int i, j;
        //Algoritmo:
            i = 0;
            j = 0;
            bufferInv = "";
            bufferEncript = "";
            mensajeEncript = "";
            
            bufferInv = reverse(msg);
            palabras = bufferInv.toUpperCase().split("\\s+");
        
            for (i = 0; i < palabras.length; i++) {  
                
                bufferEncript="";
                
                for (j = 0; j < palabras[i].length(); j++) {    
                    
                    if (palabras[i].charAt(j) + 3 > 90) {
                        bufferEncript+=(char)(((palabras[i].charAt(j) -3)  - 'Z') + 'A' - 1);
                    } else {
                        bufferEncript += (char)(palabras[i].charAt(j) - 3);
                    }//Fin Si
                }//Fin Para
                
                mensajeEncript+=bufferEncript+" ";
            }//Fin Para
        
            return mensajeEncript.trim();        
    }

    private static String reverse(String cad) {
        //Entorno:
            String[] palabras;
            String palabraReverso, textoReverso;
            int i, j;
        //Algoritmo:     
            i = 0;
            j = 0;
            palabraReverso = "";
            textoReverso = "";
        
            palabras = cad.split("\\s+");
            
            for (i = 0; i < palabras.length; i++) {
                palabraReverso = "";
                
                for (j = palabras[i].length() - 1; j >= 0; j--) {
                    palabraReverso += palabras[i].charAt(j);
                }//Fin Para
                
                textoReverso += palabraReverso + " ";
            }//Fin Para
            
            return textoReverso.trim();
    }
}
