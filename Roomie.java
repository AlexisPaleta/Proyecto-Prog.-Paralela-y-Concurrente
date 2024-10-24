import java.util.*;
import java.util.concurrent.Semaphore;

class Roomie extends Thread{
    public final String RESET = "\u001B[0m";
    String colorPropio;
    int [] horario = new int [10];
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
      int i = 0;
      while(true){
        try {
          System.out.println(colorPropio  + nombre + " trata de usar " + appliances[horario[i]].nombre + RESET);
          necesidades[i].acquire();
          System.out.println(colorPropio  + nombre + " esta usando "+ appliances[horario[i]].nombre +"\nNow sleep"  + RESET);
          sleep(2000);

          System.out.println(colorPropio  + nombre + " termina");
          if (horario[i] != 0){
          necesidades[i].release();}

          if (i==necesidades.length-1){
            i=0;
          }else{
            i++;
          }
          System.out.println(colorPropio  + nombre + " ahora va a usar " + appliances[horario[i]].nombre + RESET);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }

      }
      
    }

}