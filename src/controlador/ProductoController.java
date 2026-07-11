package controlador;

import conexion.Conexion;
import modelo.Producto;

import java.sql.*;
import java.util.ArrayList;

public class ProductoController {

    // Registrar producto
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

    // Listar productos
    public ArrayList<Producto> listarProductos() {

        ArrayList<Producto> lista = new ArrayList<>();

        try (Connection con = Conexion.getConexion()) {

            String sql = "SELECT * FROM productos";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Producto p = new Producto();

                p.setId(rs.getInt("idProducto"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));

                lista.add(p);
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return lista;
    }

    // BUSCAR PRODUCTO POR ID 
    public Producto buscarPorId(int id) {

        Producto p = null;

        try (Connection con = Conexion.getConexion()) {

            String sql = "SELECT * FROM productos WHERE idProducto = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                p = new Producto();

                p.setId(rs.getInt("idProducto"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return p;
    }

    // Entrada de stock
    public void entradaStock(int id, int cant) {

        try (Connection con = Conexion.getConexion()) {

            String sql = "UPDATE productos SET stock = stock + ? WHERE idProducto=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, cant);
            ps.setInt(2, id);

            ps.executeUpdate();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    // Salida de stock
    public boolean salidaStock(int id, int cant) {

        try (Connection con = Conexion.getConexion()) {

            String sql = "UPDATE productos SET stock = stock - ? WHERE idProducto=? AND stock>=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, cant);
            ps.setInt(2, id);
            ps.setInt(3, cant);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            return false;
        }
    }

    // Actualizar producto
    public boolean actualizarProducto(Producto p) {

        try (Connection con = Conexion.getConexion()) {

            String sql = "UPDATE productos SET nombre=?, precio=?, stock=? WHERE idProducto=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getStock());
            ps.setInt(4, p.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return false;
        }
    }

    // Eliminar producto
    public boolean eliminarProducto(int id) {

        try (Connection con = Conexion.getConexion()) {

            String sql = "DELETE FROM productos WHERE idProducto=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return false;
        }
    }

}  