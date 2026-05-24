/**
 *
 * @author Siarhei Lukashou
 * @author Elias Perez Arroyo
 */
public class Mensajeria {
    public static byte datoByte(){
        //Entorno:
            byte numeroByte;
            short numeroShort;
        //Algoritmo:
            numeroByte = 0;
            numeroShort = 0;
            
            do{
                numeroShort = Leer.datoShort();
                
                if(numeroShort < Byte.MIN_VALUE || numeroShort > Byte.MAX_VALUE){
                    System.out.println("Ese dato no es válido. Teclee otro: ");
                }
                
            }while (numeroShort < Byte.MIN_VALUE || numeroShort > Byte.MAX_VALUE);
            
            numeroByte = (byte)numeroShort;
            
            return numeroByte;
    }//Fin Funcion
    
    public static void pintarMenu(){
        //Entorno:
        //Algoritmo:
            System.out.println("1. Envío instantáneo.");
            System.out.println("2. Enviar mensaje.");
            System.out.println("3. Recibir mensaje.");
            System.out.println("4. Salir");
    }//Fin Procidimiento  
    
    public static void envioInstanteo(Cola cola){
        //Entorno:     
        //Algoritmo:       
            cola.meterEnCola(new Mensaje());      
    }
    
    public static Mensaje pedirMensaje(){
        //Entorno:
            String usuario, ip, texto;      
            Mensaje mensaje;
        //Algoritmo:
            usuario = "";
            ip = "";
            texto = "";
        
            System.out.println("Introduce datos del mensaje: ");
            System.out.println("Usuario: ");
            usuario = Leer.dato();
            System.out.println("IP: ");
            ip = Leer.dato();
            System.out.println("Texto: ");
            texto = Leer.dato();
            
            mensaje = new Mensaje(usuario, ip, texto);
            
            return mensaje;
    }
    
    public static void enviarMensaje(Cola cola){                         
        cola.meterEnCola(pedirMensaje());
    }
    
    public static String pregunta(){
        //Entorno:   
            String confirmacion;
        //Algoritmo:
            confirmacion = "";
        
            do{
                System.out.println("Hay mensajes pendientes de recibir "
                                + "¿Salir de todas formas? (S/N)");
                confirmacion = Leer.dato();
            }while(!confirmacion.matches("^[SsNn]"));
            
            return confirmacion;
        }
    
    public static void main(String[] args) {
        //Entorno:
            byte opcion;
            String confirmacion;
            Cola cola;
        //Algoritmo:
            opcion = 0;
            confirmacion = "S";
            cola = new Cola();                  
            
            do{
                opcion = datoByte();
                pintarMenu();
                
                switch(opcion){
                    case 1:
                        envioInstanteo(cola);
                        break;
                    case 2:
                        enviarMensaje(cola);                 
                        break;
                    case 3:
                        System.out.println("Recibir");
                        System.out.println("No hay más mensajes");
                        break; 
                    case 4:
                        confirmacion = pregunta();
                        break;
                }
            }while(!confirmacion.matches("^[Ss]"));
            
    }
}
