package ProgramacionObject.Ejercicio4;

public class Conciones {
    private String titulo;
    private String artista;
    private Double duracion;

    public Conciones(String titulo, String artista, Double duracion) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracion = duracion;
    }

    
    @Override
    public String toString() {
        return String.format("Título: %-25s | Artista: %-15s | Duración: %.2f seg",
                titulo, artista, duracion);
    }

    // Getters and Setters
    public String getTitulo() {

        return titulo;

    }

    public void setTitulo(String titulo) {

        this.titulo = titulo;

    }

    public String getArtista() {
        return artista;

    }

    public void setArtista(String artista) {
        this.artista = artista;

    }

    public Double getDuracion() {
        return duracion;
    }

    public void setDuracion(Double duracion) {
        this.duracion = duracion;
    }
}