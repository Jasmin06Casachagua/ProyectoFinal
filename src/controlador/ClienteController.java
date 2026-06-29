package controlador;

import conexion.Conexion;
import modelo.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteController {

    public boolean insertar(Cliente c) {

        try (Connection con = Conexion.getConexion()) {

            String sql = "INSERT INTO clientes(nombre) VALUES (?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, c.getNombre());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}