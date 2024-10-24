import java.util.concurrent.Semaphore;

class Cafetera extends Electrodomestico{
    
    Cafetera(int max){
        super("Cafetera");
        disponible = new Semaphore(max, true);
        disponible.drainPermits();
    }

    public void llenadoAutomatico() throws InterruptedException{
        while(true){
            if (disponible.availablePermits() < 5){
                Thread.sleep(2000);
                System.out.println("~~~~~~ Cafetera llena ~~~~~ Ahora hay estas unidades de cafe " + disponible.availablePermits() );

                disponible.release();
            }
        }
    }

    @Override
    public void run(){
        try {
            llenadoAutomatico();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}