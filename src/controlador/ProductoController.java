package controlador;

import conexion.Conexion;
import modelo.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductoController {

    public boolean insertar(Producto p) {

        try (Connection con = Conexion.getConexion()) {

            String sql = "INSERT INTO productos(nombre, precio, stock) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getStock());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // Entrada del Stock
    public void entradaStock(int id, int cant) {

        try (Connection con = Conexion.getConexion()) {

            String sql = "UPDATE productos SET stock = stock + ? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, cant);
            ps.setInt(2, id);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Salida de Stock
    public boolean salidaStock(int id, int cant) {

        try (Connection con = Conexion.getConexion()) {

            String sql = "UPDATE productos SET stock = stock - ? WHERE id=? AND stock>=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, cant);
            ps.setInt(2, id);
            ps.setInt(3, cant);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }
}