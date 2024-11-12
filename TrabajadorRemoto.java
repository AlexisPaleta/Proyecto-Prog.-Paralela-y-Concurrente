
class TrabajadorRemoto extends Roomie {

    //Las necesidades de un estudiante son las siguientes
    TrabajadorRemoto(String nombre, Electrodomestico[] todo, String color) {
        super(nombre, todo, color);
        Morning = new int[]{
            0, //Cafe
            1,  //Lavadora  
            3,  //Secadora
            8, //Estufa
            5   //Tostadora 
        };

        Tarde = new int[]{
            6,  //Lavavajillas
            2 //Horno
        };

        Noche = new int[]{
            0, 0,   //Cafetera
            4,  //Licuadora
            8,  //Estufa
        };
        setTareasInt(this.Morning, this.Tarde, this.Noche);
        establecerRutina(todo, color);
    }

    //Se espera que un estudiante utilize una menor cantidad de electrodomesticos
    @Override
    public void run() {//Se ejecuta el hilo del estudiante, se toma el metodo run de la clase Roomie y se le agrega la funcionalidad de cambiar las necesidades
        //por eso se quitó el while del roomie y se puso aqui
        while (true) {
            if (horariosProductivos.availablePermits() > 0) {

                System.out.println(colorPropio + "Se va a comprobar la hora del dia por el trabajador remoto" + nombre + " ,la fase del dia es: " + Reloj.comprobarHora() + RESET);
                try {
                    this.cambiarNecesidades();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.run(); //Se ejecuta el metodo run de la clase Roomie

            }
        }
    }
}
