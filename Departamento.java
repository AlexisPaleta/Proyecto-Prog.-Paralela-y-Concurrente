class Departamento{
    
    public final String BLACK = "\u001B[30m";
    public final String RED = "\u001B[31m";
    public final String GREEN = "\u001B[32m";
    public final String YELLOW = "\u001B[33m";
    public final String BLUE = "\u001B[34m";
    public final String PURPLE = "\u001B[35m";
    public final String CYAN = "\u001B[36m";
    public final String WHITE = "\u001B[37m";

    public Roomie[] residentes = new Roomie[3];
    public Electrodomestico[] appliances;
    public Departamento(){

        //Creacion de los electromesticos disponibles
        appliances = new Electrodomestico[]{
            new Cafetera(5),
            new Electrodomestico("Lavadora"),
            new Electrodomestico("Horno")
        };

        // Creacion de los residentes de la vivienda
        residentes = new Roomie[] {
            new Estudiante("Juan", appliances, YELLOW),
            new Estudiante("Pedro", appliances, RED),
            new Estudiante("Elisa", appliances, GREEN)
        };
    }

    public void inicio(){
        //inicio de la ejecucion de todos los procesos
        for (int i = 0; i<residentes.length; i++){
            residentes[i].start();
        }

        appliances[0].start();
    }

}