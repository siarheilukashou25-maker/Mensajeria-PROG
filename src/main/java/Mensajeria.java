
import java.util.GregorianCalendar;

/**
 *
 * @author Siarhei Lukashou
 * @author Elias Perez Arroyo
 */
public class Mensajeria {
    
    public static byte datoByte() {
        //Entorno:
            byte numeroByte;
            short numeroShort;
        //Algoritmo:
            numeroByte = 0;
            numeroShort = 0;

            do {
                numeroShort = Leer.datoShort();
                if (numeroShort < Byte.MIN_VALUE || numeroShort > Byte.MAX_VALUE) {
                    System.out.println("Ese dato no es válido. Teclee otro: ");
                }
            } while (numeroShort < Byte.MIN_VALUE || numeroShort > Byte.MAX_VALUE);

            numeroByte = (byte) numeroShort;

            return numeroByte;
    }//Fin Funcion

    public static void pintarMenu() {
        //Entorno:
        //Algoritmo:
            System.out.println("1. Envío instantáneo.");
            System.out.println("2. Enviar mensaje.");
            System.out.println("3. Recibir mensaje.");
            System.out.println("4. Salir");
    }//Fin Procedimiento  

    public static void envioInstanteo(Cola cola) {
        //Entorno:     
        //Algoritmo:       
            cola.meterEnCola(new Mensaje());
    }//Fin Procedimiento

    public static Mensaje pedirMensaje() {
        //Entorno:
            Mensaje mensaje;
        //Algoritmo:         
            System.out.println("Introduce datos del mensaje: "); 

            mensaje = new Mensaje(comprobarDatoVacio("Usuario"), comprobarDatoVacio("IP"), comprobarDatoVacio("Texto"));

            return mensaje;
    }//Fin Funcion
    
    public static String comprobarDatoVacio(String mensaje){
        //Entorno:
            String dato;
        //Algoritmo:
            dato = "";     
            
            do{                  
                System.out.println(mensaje + ": ");
                dato = Leer.dato();
                
                if(dato.isEmpty()){
                    System.out.println("Dato vacio. Introduzca dato: ");
                }
            }while(dato.isEmpty());
        
            return dato;
    }

    public static void enviarMensaje(Cola cola) {
        cola.meterEnCola(pedirMensaje());
    }//Fin Procedimiento

    public static String preguntaDeSalida() {
        //Entorno:   
            String confirmacion;
        //Algoritmo:
            confirmacion = "";

            do {
                System.out.println("Hay mensajes pendientes de recibir "
                    + "¿Salir de todas formas? (S/N)");
                confirmacion = Leer.dato();
            } while (!confirmacion.matches("^[SsNn]"));

            return confirmacion;
    }//Fin Funcion

    public static Mensaje recibirMensaje(Cola cola) {
        return (Mensaje) cola.sacarDeCola();
    }//Fin Funcion

    public static void mostrarMensaje(Mensaje msg) {
        //Entorno: 
        //Algoritmo:
            System.out.println("Usuario: " + msg.getUsuario());
            System.out.println("Fecha: " + formatearFecha(msg.getFecha()));
            System.out.println("IP: " + msg.getIp());
            System.out.println("Texto:");
            System.out.println(msg.getTexto());
    }//Fin Procedimiento

    public static String formatearFecha(GregorianCalendar fecha) {
        //Entorno:
            String fechaFormateada;
        //Algoritmo:
            fechaFormateada = "";

            fechaFormateada = fecha.get(GregorianCalendar.DAY_OF_MONTH) + "/"
                    + ((fecha.get(GregorianCalendar.MONTH)) + 1) + "/"
                    + fecha.get(GregorianCalendar.YEAR);
            return fechaFormateada;
    }//Fin Funcion

    public static void main(String[] args) {
        //Entorno:
            byte opcion;
            String confirmacion;
            Cola cola;
        //Algoritmo:
            opcion = 0;
            confirmacion = "";
            cola = new Cola();

            do{
                pintarMenu();
                opcion = datoByte();

                switch (opcion) {
                    case 1:
                        envioInstanteo(cola);
                        break;
                    case 2:
                        enviarMensaje(cola);
                        break;
                    case 3:
                        if (!cola.esColaVacia()) {
                            mostrarMensaje(recibirMensaje(cola));
                        } else {
                            System.out.println("No hay más mensajes");
                        }//Fin Si
                        break;
                    case 4:
                        if (!cola.esColaVacia()) {
                            confirmacion = preguntaDeSalida();
                        } else {
                            confirmacion = "S";
                        }//Fin Si
                        break;
                }//Fin Segun Sea
                
                System.out.println();
            } while (!confirmacion.matches("^[Ss]"));
    }//Fin Programa
}
