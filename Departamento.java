class Departamento{
    public final String[] colores = {
    /* BLACK =  */ "\u001B[30m",
    /* RED =  */ "\u001B[31m",
    /* GREEN =  */ "\u001B[32m",
    /* YELLOW = */  "\u001B[33m",
    /* BLUE =  */ "\u001B[34m",
    /* PURPLE = */ "\u001B[35m",
    /* CYAN =  */ "\u001B[36m",
    /* WHITE =  */ "\u001B[37m"};

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
            new Estudiante("Juan", appliances, colores[1]),
            new Estudiante("Pedro", appliances, colores[2]),
            new Estudiante("Elisa", appliances, colores[3])
        };
    }

    public void inicio(){
        //inicio de la ejecucion de todos los procesos
        for (int i = 0; i<residentes.length; i++){
            residentes[i].start();
        }

        appliances[0].start();
    }

    public Roomie[] getResidentes(){
        return this.residentes;
    }
}