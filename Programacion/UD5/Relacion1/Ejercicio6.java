package Programacion.UD5.Relacion1;

public class Ejercicio6 {
    // Crea una clase Reloj con atributos hora, minuto y segundo. Agrega un método
    // toString() que devuelva la hora en formato hh:mm:ss. Agrega el método
    // sumarMinutos(minutos) , restarMinutos(minutos) , sumarSegundos(segundos) y
    // restarSegundos(segundos)
    Reloj 

}

// =================================CLASSES=================================//

class Reloj {

    int hora;
    int minuto;
    int segundo;
    public Reloj (int hora, int minuto, int segundo) {
        this.hora = hora;
        this.minuto = minuto;
        this.segundo = segundo;
    }

    public String toString() {
        return String.format("%02d:%02d:%02d", hora, minuto, segundo);
    }
    public void sumarMinutos(int minutos) {
        int totalMinutos = this.minuto + minutos;
        this.hora = (this.hora + totalMinutos / 60) % 24;
        this.minuto = totalMinutos % 60;
    }
    public void restarMinutos(int minutos) {
        int totalMinutos = this.minuto - minutos;
        while (totalMinutos < 0) {
            totalMinutos += 60;
            this.hora = (this.hora - 1 + 24) % 24;
        }
        this.minuto = totalMinutos;
    }
    public void sumarSegundos(int segundos) {
        int totalSegundos = this.segundo + segundos;
        this.minuto = (this.minuto + totalSegundos / 60) % 60;
        this.segundo = totalSegundos % 60;
        this.hora = (this.hora + (this.minuto + totalSegundos / 60) / 60) % 24;
    }

}
