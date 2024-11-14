
import java.util.*;
import java.util.concurrent.Semaphore;

class Roomie extends Thread {

    public final String RESET = "\u001B[0m";
    String colorPropio;
    int[] Morning; //Se definen los horarios que va a tener el roomie en cada fase del dia
    int[] Tarde;
    int[] Noche;
    int[] horario;
    int totalDeTareas, tareasRestantes;
    String FaseDelDia = "Manana";
    int k = 0; //Esta variable se define aqui para que se pueda utilizar en el metodo run, ya que como ahora el ciclo infinito esta
    //en la clase del esudiante, se define desde aqui el indice que se va a utilizar para recorrer el horario
    String nombre;
    Semaphore[] necesidades;

    //Horario que marca las tres horas del día
    Semaphore horariosProductivos;

    Electrodomestico[] appliances;

    Roomie(String nombre, Electrodomestico[] electrodomesticosDepa, String color) {
        this.nombre = nombre;
        this.appliances = electrodomesticosDepa;
        this.colorPropio = color;
        this.horariosProductivos = new Semaphore(0);

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
    public void run() {
        try {
            System.out.println(colorPropio + nombre + " trata de usar " + appliances[horario[k]].nombre + RESET);
            necesidades[k].acquire();//Se adquiere el semaforo del electrodomestico que se va a utilizar
            tareasRestantes--;
            System.out.println(colorPropio + "\n" + nombre + " esta usando " + appliances[horario[k]].nombre + "\nUsando..." + RESET);
            sleep(500);//se simula un tiempo de uso

            System.out.println(colorPropio + nombre + " termino de usar " + appliances[horario[k]].nombre + "Le restan " + tareasRestantes + RESET);

            if (horario[k] != 0) {//Si el electrodomestico que se uso no fue el cafe, entonces se libera el semaforo
                //la idea de que cuando sea 0, o sea el cafe no libere el semaforo directamente el roomie, es porque el cafe 
                //se va rellenando solo, por lo que no es necesario que el roomie lo libere, ademas de que este recurso puede ser adquirido
                //por varios roomies a la vez, en caso de que el recurso este disponible claro
                necesidades[k].release();
            }
            

            if (k >= necesidades.length - 1) { //Si ya se recorrio todo el horario, se reinicia el indice
                horariosProductivos.acquire();
            } else {
                k++;
            }
            
            //despues de esto iria lo del cambio de las necesidades, solo que se tendria que definir en la clase que herede de Roomie, debido
            //a las variables que defino en la clase que hereda, por ejemplo en el estudiante se define el horario de cada fase del dia
            //y es necesario que el resto del metodo se ejecute en el estudiante

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void establecerRutina(Electrodomestico[] todo, String color) {
        shuffleArray(Morning); //Se barajan los horarios de cada fase del dia
        shuffleArray(Tarde);
        shuffleArray(Noche);

        horario = Morning; //Al inicio se establece el horario de la mañana
        FaseDelDia = "Manana";

        necesidades = new Semaphore[horario.length];// En las necesidades se guardan los semaforos de los electrodomesticos que se van a utilizar
        //siendo que como en este momento se tienen 3 electrodomesticos, se tienen 3 semaforos, por lo que dentro se repiten los semaforos
        //en distintas ubicaciones del arreglo de necesidades, para poder ejemplificar que el estudiante necesita usar el electrodomestico una cierta
        //cantidad de veces dependiendo de la fase del dia
        for (int i = 0; i < horario.length; i++) {
            necesidades[i] = todo[Morning[i]].getSemaphore(); //Al inicio se establece el horario de la mañana
        }

        System.out.println(color + "El horario de " + nombre + " es : " + Arrays.toString(horario) + RESET + "\n"); //Se imprime el horario del estudiante
    }

    public void cambiarNecesidades() throws InterruptedException {//Se cambian las necesidades del estudiante dependiendo de la fase del dia, este metodo se ejecuta
        //cuando el estudiante termina de usar un electrodomestico, en caso de que la fase del dia sea la misma que la anterior, no se cambian las necesidades
        //pero si el reloj cambia de fase, entonces se cambian las necesidades del estudiante

        if (Reloj.comprobarHora().equals(FaseDelDia)) {
            System.out.println(colorPropio + "\nEl horario de " + nombre + " no ha cambiado, el horario del dia es: " + Reloj.comprobarHora() + RESET);
            return;
        } else {
            k = 0;
            int[] horaDelDia = new int[horario.length];
            switch (Reloj.comprobarHora()) {
                case "Manana":
                    tareasRestantes = totalDeTareas;
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
            for (int i = 0; i < horaDelDia.length; i++) {
                necesidades[i] = appliances[horaDelDia[i]].getSemaphore();
            }

            horario = horaDelDia;

            System.out.println(colorPropio + "El horario de " + nombre + " ahora es : " + Arrays.toString(horario) + RESET);
        }
    }

    public Semaphore getSemaforoDiario() {
        return this.horariosProductivos;
    }

    public void setTareasInt(int[] Morning, int[] Tarde, int[] Noche) {
        this.totalDeTareas = Morning.length + Tarde.length + Noche.length;
        this.tareasRestantes = totalDeTareas;
    }

    public void reporteDelDia() {
        System.err.println(colorPropio + "Soy " + nombre + " y no pude hacer " + tareasRestantes + " actividades el dia de hoy." + RESET);
    }
}
