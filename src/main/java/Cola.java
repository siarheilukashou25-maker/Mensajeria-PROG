
import java.util.LinkedList;


/**
 *
 * @author Siarhei Lukashou
 * @author Elias Perez Arroyo
 */
public class Cola {
    //Atributos:
        private LinkedList tCola;
    //Constructores:
        public Cola(){
            this.tCola= new LinkedList();
        }
    //Métodos:
        public void meterEnCola(Object obj){
            this.tCola.addLast(obj);                  
        }
        
        public Object sacarDeCola(){      
            return this.tCola.getFirst();
        }
        
        public boolean esColaVacia(){            
            return this.tCola.isEmpty();
        }
        
}
