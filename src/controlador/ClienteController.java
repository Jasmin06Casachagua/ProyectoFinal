package controlador;

import conexion.Conexion;
import modelo.Cliente;

import java.sql.*;
import java.util.ArrayList;

public class ClienteController {

    // REGISTRAR CLIENTE

    public boolean insertar(Cliente c) {

        try(Connection con = Conexion.getConexion()) {

            String sql =
                    "INSERT INTO clientes(nombre,telefono,direccion) VALUES (?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, c.getNombre());

            ps.setString(2, c.getTelefono());

            ps.setString(3, c.getDireccion());

            ps.executeUpdate();

            return true;

        } catch(SQLException e) {

            System.out.println(e.getMessage());

            return false;

        }

    }

    // LISTAR CLIENTES

    public ArrayList<Cliente> listarClientes() {

        ArrayList<Cliente> lista = new ArrayList<>();

        try(Connection con = Conexion.getConexion()) {

            String sql =
                    "SELECT * FROM clientes";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                Cliente c = new Cliente();

                c.setId(
                        rs.getInt("idCliente")
                );

                c.setNombre(
                        rs.getString("nombre")
                );

                c.setTelefono(
                        rs.getString("telefono")
                );

                c.setDireccion(
                        rs.getString("direccion")
                );

                lista.add(c);

            }

        } catch(SQLException e) {

            System.out.println(e.getMessage());

        }

        return lista;

    }

    // ACTUALIZAR CLIENTE

    public boolean actualizarCliente(Cliente c) {

        try(Connection con = Conexion.getConexion()) {

            String sql =
                    "UPDATE clientes SET nombre=?, telefono=?, direccion=? WHERE idCliente=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, c.getNombre());

            ps.setString(2, c.getTelefono());

            ps.setString(3, c.getDireccion());

            ps.setInt(4, c.getId());

            return ps.executeUpdate() > 0;

        } catch(SQLException e) {

            System.out.println(e.getMessage());

            return false;

        }

    }

    // ELIMINAR CLIENTE

    public boolean eliminarCliente(int id) {

        try(Connection con = Conexion.getConexion()) {

            String sql =
                    "DELETE FROM clientes WHERE idCliente=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1,id);

            return ps.executeUpdate() > 0;

        } catch(SQLException e) {

            System.out.println(e.getMessage());

            return false;

        }

    }

}