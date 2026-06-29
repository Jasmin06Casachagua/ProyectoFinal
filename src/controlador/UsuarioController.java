package controlador;

import conexion.Conexion;
import java.sql.*;

public class UsuarioController {

    public boolean login(String correo, String pass) {

        try {
            Connection con = Conexion.getConexion();

            String sql = "SELECT * FROM usuarios WHERE correo=? AND contrasena=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, correo);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            return rs.next(); // Polimorfismo implícito (boolean)

        } catch (SQLException e) {
            System.out.println("Error login: " + e.getMessage());
            return false;
        }
    }
}
