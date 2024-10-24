import java.util.concurrent.Semaphore;

class Electrodomestico extends Thread{
    String nombre;
    Semaphore disponible;

    Electrodomestico(String nombre){
        this.nombre = nombre;
        disponible = new Semaphore(1, true);
    }

    public Semaphore getSemaphore(){
        return this.disponible;
    }

    
}