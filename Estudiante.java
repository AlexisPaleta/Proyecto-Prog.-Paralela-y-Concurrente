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
        
        shuffleArray(Morning); //Se barajan los horarios de cada fase del dia
        shuffleArray(Tarde);
        shuffleArray(Noche);

        horario = Morning; //Al inicio se establece el horario de la mañana
        FaseDelDia = "Manana"; 

        necesidades = new Semaphore[horario.length];// En las necesidades se guardan los semaforos de los electrodomesticos que se van a utilizar
        //siendo que como en este momento se tienen 3 electrodomesticos, se tienen 3 semaforos, por lo que dentro se repiten los semaforos
        //en distintas ubicaciones del arreglo de necesidades, para poder ejemplificar que el estudiante necesita usar el electrodomestico una cierta
        //cantidad de veces dependiendo de la fase del dia
        for (int i = 0; i<horario.length; i++){
            necesidades[i] = todo[Morning[i]].getSemaphore(); //Al inicio se establece el horario de la mañana
        }

        System.out.println(color  + "El horario de " + nombre + "es : " + Arrays.toString(horario)+ RESET); //Se imprime el horario del estudiante
    }
    //Se espera que un estudiante utilize una menor cantidad de electrodomesticos

    public void cambiarNecesidades(){//Se cambian las necesidades del estudiante dependiendo de la fase del dia, este metodo se ejecuta
        //cuando el estudiante termina de usar un electrodomestico, en caso de que la fase del dia sea la misma que la anterior, no se cambian las necesidades
        //pero si el reloj cambia de fase, entonces se cambian las necesidades del estudiante
        if(Reloj.comprobarHora().equals(FaseDelDia)){
            System.out.println(colorPropio + "El horario de " + nombre + " no ha cambiado, el horario del dia es: " + Reloj.comprobarHora() + RESET);
            return;
        }
        int[] horaDelDia = new int[horario.length];
        switch(Reloj.comprobarHora()){
            case "Manana":
                horaDelDia = Morning; //Se cambian las necesidades del estudiante dependiendo de la fase del dia
                FaseDelDia = "Manana";//Esta cadena es para saber en que fase del dia se encuentra el estudiante
                break;
            case "Tarde":
                horaDelDia = Tarde;
                FaseDelDia = "Tarde";
                break;
            case "Noche":
                horaDelDia = Noche;
                FaseDelDia = "Noche";
                break;
        }

        necesidades = new Semaphore[horaDelDia.length];
        for (int i = 0; i<horaDelDia.length; i++){
            necesidades[i] = appliances[horaDelDia[i]].getSemaphore();
        }

        horario = horaDelDia;

        System.out.println(colorPropio  + "El horario de " + nombre + " ahora es : " + Arrays.toString(horario)+ RESET);
    }

    public void run(){//Se ejecuta el hilo del estudiante, se toma el metodo run de la clase Roomie y se le agrega la funcionalidad de cambiar las necesidades
        //por eso se quitó el while del roomie y se puso aqui
        while(true){
            super.run(); //Se ejecuta el metodo run de la clase Roomie
            System.out.println(colorPropio +"Se va a comprobar la hora del dia por el estudiante " + nombre +" ,la fase del dia es: " + Reloj.comprobarHora() + RESET);
            this.cambiarNecesidades();

        }
        
        
    }
    
}