package controlador;

import conexion.Conexion;

import java.sql.*;
import java.util.ArrayList;

public class ReporteController {

    public ArrayList<Object[]> listarVentas() {

        ArrayList<Object[]> lista = new ArrayList<>();

        try(Connection con = Conexion.getConexion()) {

            String sql =
                    "SELECT idVenta, fecha, total FROM ventas";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                Object[] fila = {

                        rs.getInt("idVenta"),

                        rs.getDate("fecha"),

                        rs.getDouble("total")

                };

                lista.add(fila);

            }

        } catch(SQLException e) {

            System.out.println(e.getMessage());

        }

        return lista;

    }

}