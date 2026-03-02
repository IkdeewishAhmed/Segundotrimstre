package ProgramacionObject.PT5;

import java.util.Random;

public class Faker {

    private static final Random random = new Random();

    private static final String[] nombresAnimales = {
            "León", "Tigre", "Elefante", "Jirafa", "Cebra",
            "Hipopótamo", "Rinoceronte", "Oso", "Lobo", "Zorro",
            "Panda", "Canguro", "Koala", "Mono", "Gorila",
            "Águila", "Pingüino", "Cocodrilo", "Serpiente", "Tortuga"
    };

    public static String generarNombre() {
        return nombresAnimales[random.nextInt(nombresAnimales.length)];
    }

    public static String generarCodigo() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numeros = "0123456789";

        return "" +
                letras.charAt(random.nextInt(letras.length())) +
                numeros.charAt(random.nextInt(numeros.length())) +
                letras.charAt(random.nextInt(letras.length())) +
                numeros.charAt(random.nextInt(numeros.length())) +
                letras.charAt(random.nextInt(letras.length())) +
                numeros.charAt(random.nextInt(numeros.length()));
    }

    public static int generarCantidad() {
        return random.nextInt(51);
    }

    public static double generarPrecio() {
        return 5 + (100 - 5) * random.nextDouble();
    }
}