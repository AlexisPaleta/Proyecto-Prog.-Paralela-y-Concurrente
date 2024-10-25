import java.util.Arrays;
import java.util.concurrent.Semaphore;

class Estudiante extends Roomie{

    //Las necesidades de un estudiante son las siguientes

    Estudiante(String nombre, Electrodomestico[] todo, String color){
        super(nombre, todo, color);
        Morning = new int[] {
            0, //Cafe
            1,1, //Lavadora  
            2,2,2 //Horno
        };

        Tarde = new int[] {
            0,0, //Cafe
            1,1,1, //Lavadora  
            2 //Horno
        };

        Noche = new int[] {
            0,0,0, //Cafe
            1,1, //Lavadora  
            2 //Horno
        };
        setTareasInt(this.Morning, this.Tarde, this.Noche);
        establecerRutina(todo, color);
    }
    //Se espera que un estudiante utilize una menor cantidad de electrodomesticos


    public void run(){//Se ejecuta el hilo del estudiante, se toma el metodo run de la clase Roomie y se le agrega la funcionalidad de cambiar las necesidades
        //por eso se quitó el while del roomie y se puso aqui
        while(true){
            if (horariosProductivos.availablePermits()>0){
            super.run(); //Se ejecuta el metodo run de la clase Roomie
            
            System.out.println(colorPropio +"Se va a comprobar la hora del dia por el estudiante " + nombre +" ,la fase del dia es: " + Reloj.comprobarHora() + RESET);
            try {
                this.cambiarNecesidades();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            
        }
        
        
    }
    
}