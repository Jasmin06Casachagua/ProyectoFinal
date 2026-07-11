package vista;

import controlador.ReporteController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class VentanaReportes extends JFrame {

    private JTable tabla;
    private DefaultTableModel modeloTabla;

    private ReporteController rc;

    public VentanaReportes() {

        setTitle("Reportes - Sistema NAMI");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        getContentPane().setBackground(
                Color.decode("#E3AAAA")
        );

        rc = new ReporteController();

        // ================= TABLA =================

        modeloTabla = new DefaultTableModel();

        modeloTabla.addColumn("ID Venta");
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Total");

        tabla = new JTable(modeloTabla);

        JScrollPane scroll =
                new JScrollPane(tabla);

        scroll.setBounds(
                30,
                40,
                520,
                220
        );

        add(scroll);

        // ================= BOTON VOLVER =================

        JButton btnVolver =
                new JButton("Volver");

        btnVolver.setBounds(
                230,
                300,
                120,
                30
        );

        add(btnVolver);

        cargarVentas();

        btnVolver.addActionListener(e -> {

            new MenuPrincipal();

            dispose();

        });

        setVisible(true);

    }

    private void cargarVentas() {

        modeloTabla.setRowCount(0);

        ArrayList<Object[]> lista =
                rc.listarVentas();

        for(Object[] fila : lista) {

            modeloTabla.addRow(fila);

        }

    }

}