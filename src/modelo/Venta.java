package modelo;

import java.util.ArrayList;

// Agregación → se obtiene de la lista de detalles
// Colecciones → ArrayList
public class Venta {

    private ArrayList<DetalleVenta> detalles;
    private double total;

    public Venta() {
        detalles = new ArrayList<>();
        total = 0;
    }

    public void agregarDetalle(DetalleVenta d) {
        detalles.add(d);
        total += d.getSubtotal();
    }

    public double getTotal() {
        return total;
    }
}