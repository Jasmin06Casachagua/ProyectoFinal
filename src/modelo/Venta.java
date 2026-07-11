package modelo;

import java.util.ArrayList;

// Agregación → lista de detalles
// Colecciones → ArrayList
public class Venta {

    private int idVenta;
    private String fecha;
    private int idCliente;

    private ArrayList<DetalleVenta> detalles;
    private double total;

    // Constructor
    public Venta() {
        detalles = new ArrayList<>();
        total = 0;
    }

    // Agregar productos a la venta
    public void agregarDetalle(DetalleVenta d) {
        detalles.add(d);
        total += d.getSubtotal();
    }

    // GETTERS Y SETTERS

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public ArrayList<DetalleVenta> getDetalles() {
        return detalles;
    }

    public double getTotal() {
        return total;
    }
}