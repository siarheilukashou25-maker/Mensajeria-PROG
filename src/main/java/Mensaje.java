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
            this.fecha = new GregorianCalendar();
        }
    //Metodos:

        public GregorianCalendar getFecha() {
            return this.fecha;
        }

        public String getTexto() {
            return desencripta(this.texto);
        }
    
        public String getUsuario(){
            return this.usuario;
        }
    
        public String getIp(){
            return this.ip;
        }

        private boolean esValida(String ip) {
            //Entorno:
                boolean esValida;
            //Algoritmo:
                esValida = false;

                esValida = ip.matches("^(?:(?:25[0-5]|2[0-4]\\d|1?\\d{1,2})(?:\\.(?!$)|$)){4}");
                return esValida;
        }

        private String encripta(String msg) {
            //Entorno:
                String bufferInv, mensajeEncript;
                int i;
            //Algoritmo:
                i = 0;
                mensajeEncript = "";
                bufferInv = "";
    
                bufferInv = reverse(msg).toUpperCase();

                for (i = 0; i < bufferInv.length(); i++) {
                    
                    if (bufferInv.charAt(i) >= 'A' && bufferInv.charAt(i) <= 'Z') {
                        
                        if (bufferInv.charAt(i) + 3 > 'Z') {
                            mensajeEncript += (char)(((bufferInv.charAt(i) + 3) - 'Z') + 'A' - 1);
                        } else {
                            mensajeEncript += (char)(bufferInv.charAt(i) + 3);
                        }//Fin Si
                    } else {
                        mensajeEncript += bufferInv.charAt(i);
                    }//Fin Si
                }//Fin Para

                return mensajeEncript;
        }

        private String desencripta(String msg) {
            //Entorno:
                String mensajeDesencript;
                int i;
            //Algoritmo:
                i = 0;
                mensajeDesencript = "";
    
                msg = reverse(msg).toUpperCase();              
                
                for (i = 0; i < msg.length(); i++) {

                    if (msg.charAt(i) >= 'A' && msg.charAt(i) <= 'Z') {
                        
                        if (msg.charAt(i) - 3 < 'A') {
                            mensajeDesencript += (char)(((msg.charAt(i) - 3) - 'A') + 'Z' + 1);
                        } else {
                            mensajeDesencript += (char)(msg.charAt(i) - 3);
                        }//Fin Si                  
                    } else {
                        mensajeDesencript += msg.charAt(i);
                    }//Fin Si
                }//Fin Para

                return mensajeDesencript;
        }

        private String reverse(String cad) {
            //Entorno:
                String textoReverso;
                int i;
            //Algoritmo:
                i = 0;
                textoReverso = "";

                for (i = cad.length() - 1; i >= 0; i--) {
                    textoReverso += cad.charAt(i);
                }//Fin Para

                return textoReverso;
        }
}