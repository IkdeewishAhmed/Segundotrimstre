package Programacion.UD5.Relacion1;

public class Ejercicio5 {

    // Crea una clase Termómetro con un atributo temperatura. Agrega el método
    // convertirAFahrenheit().
    public static void main(String[] args) {
        Termómetro termómetro1 = new Termómetro(0);
        Termómetro termómetro2 = new Termómetro(37);

        System.out.println("Temperatura en Celsius: " + termómetro1.temperatura + "°C, en Fahrenheit: "
                + termómetro1.convertirAFahrenheit() + "°F");
        System.out.println("Temperatura en Celsius: " + termómetro2.temperatura + "°C, en Fahrenheit: "
                + termómetro2.convertirAFahrenheit() + "°F");
    }
}

// ====================================CLASSES==========================================//

class Termómetro {

    double temperatura;

    public Termómetro(double temperatura) {
        this.temperatura = temperatura;
    }

    public double convertirAFahrenheit() {
        return (this.temperatura * 9 / 5) + 32;
    }

}
