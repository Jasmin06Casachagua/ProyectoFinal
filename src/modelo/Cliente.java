package modelo;

public class Cliente {

    private int id;
    private String nombre;

    // Constructor Vacío  
    public Cliente() {}

    // Constructor con parámetros 
    public Cliente(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y Setters 
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}