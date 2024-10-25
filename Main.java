public class Main {

    public static void main(String [] args){
        Departamento d = new Departamento();
        Reloj r = new Reloj(d.getResidentes());
        r.start();
        d.inicio();
        
    }
}