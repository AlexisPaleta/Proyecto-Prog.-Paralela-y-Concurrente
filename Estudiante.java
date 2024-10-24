import java.util.Arrays;
import java.util.concurrent.Semaphore;

class Estudiante extends Roomie{

    //Las necesidades de un estudiante son las siguientes

    Estudiante(String nombre, Electrodomestico[] todo, String color){
        super(nombre, todo, color);
        horario = new int[] {
            0, //Cafe
            1,1, //Lavadora  
            2,2,2 //Horno
            ,2,2,2
        };
        //shuffleArray(horario);

        necesidades = new Semaphore[horario.length];
        for (int i = 0; i<horario.length; i++){
            necesidades[i] = todo[horario[i]].getSemaphore();
          }

          System.out.println(color  + "El horario de " + nombre + "es : " + Arrays.toString(horario)+ RESET);
    }
    //Se espera que un estudiante utilize una menor cantidad de electrodomesticos
    
}