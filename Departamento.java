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
            new Cafetera(5), // 0
            new Electrodomestico("Lavadora"), //1
            new Electrodomestico("Horno"),  //2
            new Electrodomestico("Secadora"),   //3
            new Electrodomestico("Licuadora"),  //4
            new Electrodomestico("Tostadora"),  //5
            new Electrodomestico("Lavavajillas"),   //6
            new Electrodomestico("Plancha"), //7
            new Electrodomestico("Estufa")  //8
        };

        // Creacion de los residentes de la vivienda
        residentes = new Roomie[] {
            new Estudiante("Juan", appliances, colores[1]),
            new Estudiante("Pedro", appliances, colores[2]),
            new Estudiante("Elisa", appliances, colores[3]),
            new Estudiante("Clara", appliances, colores[4]),
            new Trabajador("TRAB1", appliances, colores[5]),
            new Trabajador("TRAB2", appliances, colores[5]),
            new TrabajadorRemoto("HOMEOFF1", appliances, colores[6]),
            new TrabajadorRemoto("HOMEOFF2", appliances, colores[7])
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