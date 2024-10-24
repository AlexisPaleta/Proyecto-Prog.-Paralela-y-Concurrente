public class Reloj extends Thread{
    
    public static String horaDelDia = "Manana"; //El reloj empieza siempre en la mañana

    @Override
    public void run(){
        while(true){
            try {
                System.out.println("Son las 5:00 AM, el horario corresponde a la Mañana");
                sleep(8000);
                cambiarHora("Manana");
                sleep(8000);
                System.out.println("Son las 12:00 PM, el horario corresponde a la Tarde");
                cambiarHora("Tarde");
                sleep(8000);
                System.out.println("Son las 7:00 PM, el horario corresponde a la Noche");
                cambiarHora("Noche");
                sleep(8000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static String comprobarHora(){
        return Reloj.horaDelDia;
    }

    public static void cambiarHora(String hora){
        Reloj.horaDelDia = hora;
    }

}
