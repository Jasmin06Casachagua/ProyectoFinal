package modelo;

// Herencia → esta es la clase padre
public class Persona {

    protected String nombre;
    protected String telefono;

    // Constructor 
    public Persona(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    // Métodos Get y Set
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // Polimorfismo 
    public String mostrarDatos() {
        return nombre + " - " + telefono;
    }
}