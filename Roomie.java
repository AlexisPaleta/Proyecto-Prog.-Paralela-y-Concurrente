import java.util.*;
import java.util.concurrent.Semaphore;

class Roomie extends Thread{
    public final String RESET = "\u001B[0m";
    String colorPropio;
    int [] Morning; //Se definen los horarios que va a tener el roomie en cada fase del dia
    int [] Tarde;
    int [] Noche;
    int [] horario;
    String FaseDelDia = "Manana";
    int k = 0; //Esta variable se define aqui para que se pueda utilizar en el metodo run, ya que como ahora el ciclo infinito esta
    //en la clase del esudiante, se define desde aqui el indice que se va a utilizar para recorrer el horario
    String nombre;
    Semaphore [] necesidades;
    Electrodomestico[] appliances;
    Roomie(String nombre, Electrodomestico[] electrodomesticosDepa, String color){
        this.nombre = nombre;
        this.appliances = electrodomesticosDepa;
        this.colorPropio = color;
    }


    //cambio de las necesidades para orden aleatorio. Se pretende tener horario unico para residente
    static void shuffleArray(int[] ar) {
      Random rnd = new Random();
      for (int i = ar.length - 1; i > 0; i--) {
        int index = rnd.nextInt(i + 1);
        
        // Simple swap
        int a = ar[index];
        ar[index] = ar[i];
        ar[i] = a;
      }
    }

    

    @Override
    public void run(){
      
      
        try {
          System.out.println(colorPropio  + nombre + " trata de usar " + appliances[horario[k]].nombre + RESET);
          necesidades[k].acquire();//Se adquiere el semaforo del electrodomestico que se va a utilizar
          System.out.println(colorPropio  + nombre + " esta usando "+ appliances[horario[k]].nombre +"\nNow sleep"  + RESET);
          sleep(2000);//se simula un tiempo de uso

          System.out.println(colorPropio  + nombre + " termina de usar " + appliances[horario[k]].nombre + RESET);
          if (horario[k] != 0){//Si el electrodomestico que se uso no fue el cafe, entonces se libera el semaforo
            //la idea de que cuando sea 0, o sea el cafe no libere el semaforo directamente el roomie, es porque el cafe 
            //se va rellenando solo, por lo que no es necesario que el roomie lo libere, ademas de que este recurso puede ser adquirido
            //por varios roomies a la vez, en caso de que el recurso este disponible claro
          necesidades[k].release();}

          if (k==necesidades.length-1){ //Si ya se recorrio todo el horario, se reinicia el indice
            k=0;
          }else{
            k++;
          }
          System.out.println(colorPropio  + nombre + " ahora va a usar " + appliances[horario[k]].nombre + RESET);
          //despues de esto iria lo del cambio de las necesidades, solo que se tendria que definir en la clase que herede de Roomie, debido
          //a las variables que defino en la clase que hereda, por ejemplo en el estudiante se define el horario de cada fase del dia
          //y es necesario que el resto del metodo se ejecute en el estudiante

        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }

      
      
    }

}