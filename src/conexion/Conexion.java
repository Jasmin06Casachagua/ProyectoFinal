package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static Connection conexion;

    public static Connection getConexion() {

        try {

            if (conexion == null || conexion.isClosed()) {

                String url = "jdbc:mysql://localhost:3306/nami";
                String user = "root";
                String password = "yasmin";

                conexion = DriverManager.getConnection(url, user, password);

                System.out.println("Conexión exitosa");

            }

        } catch(SQLException e) {

            System.out.println("Error de conexión: " + e.getMessage());

        }

        return conexion;

    }

}