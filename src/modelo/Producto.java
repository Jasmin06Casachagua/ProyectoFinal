package modelo;

public class Producto {

    private int id;
    private String nombre;
    private double precio;
    private int stock;

    // Constructor Vacío 
    public Producto() {
    }

    // Constructor
    public Producto(int id, String nombre, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // Setters 
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Getters 
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }

    // Sobrecarga
    public void actualizarStock(int cantidad) {
        this.stock += cantidad;
    }

    public void actualizarStock(int cantidad, boolean restar) {
        if (restar) {
            this.stock -= cantidad;
        } else {
            this.stock += cantidad;
        }
    }
}