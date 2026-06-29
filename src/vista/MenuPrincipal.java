package vista;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {

        setTitle("Menú Principal");
        setSize(450, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.decode("#E3AAAA"));

        JButton btnProductos = new JButton("Productos");
        JButton btnClientes = new JButton("Clientes");
        JButton btnVentas = new JButton("Ventas");
        JButton btnReportes = new JButton("Reportes");
        JButton btnSalir = new JButton("Cerrar sesión");

        btnProductos.setBounds(120, 40, 180, 35);
        btnClientes.setBounds(120, 80, 180, 35);
        btnVentas.setBounds(120, 120, 180, 35);
        btnReportes.setBounds(120, 160, 180, 35);
        btnSalir.setBounds(120, 210, 180, 35);

        add(btnProductos);
        add(btnClientes);
        add(btnVentas);
        add(btnReportes);
        add(btnSalir);

        // Botones
        btnProductos.addActionListener(e -> {
            new VentanaProductos();
            dispose();
        });

        btnClientes.addActionListener(e -> {
            new VentanaClientes();
            dispose();
        });

        btnVentas.addActionListener(e -> {
            new VentanaVentas();
            dispose();
        });

        btnReportes.addActionListener(e -> {
            new VentanaReportes();
            dispose();
        });

        btnSalir.addActionListener(e -> {
            System.exit(0);
        });

        setVisible(true);
    }
}
