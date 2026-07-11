package controlador;

import conexion.Conexion;
import modelo.DetalleVenta;
import modelo.Venta;

import java.sql.*;
import java.time.LocalDate;

public class VentaController {

    public boolean registrarVenta(Venta venta) {

        Connection con = null;

        try {

            con = Conexion.getConexion();

            con.setAutoCommit(false);

            // REGISTRAR CABECERA VENTA

            String sqlVenta =
                    "INSERT INTO ventas(fecha,total) VALUES (?,?)";

            PreparedStatement psVenta =
                    con.prepareStatement(
                            sqlVenta,
                            Statement.RETURN_GENERATED_KEYS
                    );

            psVenta.setDate(
                    1,
                    Date.valueOf(LocalDate.now())
            );

            psVenta.setDouble(
                    2,
                    venta.getTotal()
            );

            psVenta.executeUpdate();

            ResultSet rs =
                    psVenta.getGeneratedKeys();

            int idVenta = 0;

            if(rs.next()) {

                idVenta = rs.getInt(1);

            }

            // REGISTRAR DETALLE

            String sqlDetalle =
                    "INSERT INTO detalle_venta" +
                    "(idVenta,idProducto,cantidad,subtotal)" +
                    " VALUES (?,?,?,?)";

            PreparedStatement psDetalle =
                    con.prepareStatement(sqlDetalle);

            // actualizar stock

            String sqlStock =
                    "UPDATE productos SET stock = stock - ? WHERE idProducto=?";

            PreparedStatement psStock =
                    con.prepareStatement(sqlStock);

            for(DetalleVenta d : venta.getDetalles()) {

                // Guardar detalle

                psDetalle.setInt(
                        1,
                        idVenta
                );

                psDetalle.setInt(
                        2,
                        d.getProducto().getId()
                );

                psDetalle.setInt(
                        3,
                        d.getCantidad()
                );

                psDetalle.setDouble(
                        4,
                        d.getSubtotal()
                );

                psDetalle.executeUpdate();

                // Descontar stock

                psStock.setInt(
                        1,
                        d.getCantidad()
                );

                psStock.setInt(
                        2,
                        d.getProducto().getId()
                );

                psStock.executeUpdate();

            }

            con.commit();

            return true;

        } catch(Exception e) {

            try {

                if(con != null) {

                    con.rollback();

                }

            } catch(SQLException ex) {

                System.out.println(ex.getMessage());

            }

            System.out.println(e.getMessage());

            return false;

        } finally {

            try {

                if(con != null) {

                    con.setAutoCommit(true);

                }

            } catch(SQLException e) {

                System.out.println(e.getMessage());

            }

        }

    }

}
