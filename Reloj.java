
import java.util.concurrent.Semaphore;

public class Reloj extends Thread {

    public static String horaDelDia = "Manana"; //El reloj empieza siempre en la mañana
    public Roomie[] residentes;

    public Reloj(Roomie[] residentes) {
        this.residentes = residentes;
    }

    @Override
    public void run() {
        while (true) {
            try {

                System.out.println("INICIO DEL DIA\n\n");
                System.out.println("Son las 5:00 AM, el horario corresponde a la Mañana");
                cambiarHora("Manana");
                llenarSemaforos();
                sleep(15000);
                System.out.println("Son las 12:00 PM, el horario corresponde a la Tarde");
                cambiarHora("Tarde");
                llenarSemaforos();
                sleep(15000);
                System.out.println("Son las 7:00 PM, el horario corresponde a la Noche");
                cambiarHora("Noche");
                llenarSemaforos();
                sleep(15000);
                vaciarSemaforos();
                sleep(15000);
                System.out.println("TODOS SE VAN A DORMIR\n\n");
                terminoDia();
                sleep(15000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static String comprobarHora() {
        return Reloj.horaDelDia;
    }

    public static void cambiarHora(String hora) {
        Reloj.horaDelDia = hora;
    }

    public void llenarSemaforos() {
        //Inicio del dia
        for (int i = 0; i < residentes.length; i++) {
            Semaphore temp = residentes[i].getSemaforoDiario();
            temp.release(1);
        }
    }

    public void vaciarSemaforos() {
        //Inicio del dia
        for (int i = 0; i < residentes.length; i++) {
            Semaphore temp = residentes[i].getSemaforoDiario();
            temp.drainPermits();
        }
    }

    public void terminoDia() {
        for (int i = 0; i < residentes.length; i++) {
            residentes[i].reporteDelDia();

        }
    }
}
