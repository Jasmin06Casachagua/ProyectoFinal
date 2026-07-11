package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioController {

    public boolean validarUsuario(String usuario, String contrasena) {

        boolean existe = false;

        try {

            Connection con = Conexion.getConexion();

            String sql = "SELECT * FROM usuarios WHERE usuario=? AND contrasena=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, usuario);
            ps.setString(2, contrasena);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                existe = true;

            }

        } catch(Exception e) {

            System.out.println("Error al validar usuario: " + e.getMessage());

        }

        return existe;

    }

}