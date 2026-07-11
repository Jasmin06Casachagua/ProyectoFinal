package modelo;

// Composición → depende de Venta
public class DetalleVenta {

    private int idDetalle;
    private Producto producto;
    private int cantidad;
    private double subtotal;

    // Constructor
    public DetalleVenta(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = producto.getPrecio() * cantidad;
    }

    // GETTERS

    public int getIdDetalle() {
        return idDetalle;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    // SETTERS

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.subtotal = producto.getPrecio() * cantidad; // recalcula
    }
}