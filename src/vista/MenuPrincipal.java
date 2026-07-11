package vista;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    private JButton btnProductos;

    private JButton btnClientes;

    private JButton btnVentas;

    private JButton btnReportes;

    private JButton btnSalir;

    public MenuPrincipal() {

        setTitle("Menú Principal - Sistema NAMI");

        setSize(450,350);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        getContentPane().setBackground(Color.decode("#E3AAAA"));

        btnProductos = new JButton("Productos");

        btnClientes = new JButton("Clientes");

        btnVentas = new JButton("Ventas");

        btnReportes = new JButton("Reportes");

        btnSalir = new JButton("Cerrar sesión");

        btnProductos.setBounds(120,40,180,35);

        btnClientes.setBounds(120,80,180,35);

        btnVentas.setBounds(120,120,180,35);

        btnReportes.setBounds(120,160,180,35);

        btnSalir.setBounds(120,210,180,35);

        add(btnProductos);

        add(btnClientes);

        add(btnVentas);

        add(btnReportes);

        add(btnSalir);

        // PRODUCTOS

        btnProductos.addActionListener(e -> {

            new VentanaProductos();

            dispose();

        });

        // CLIENTES

        btnClientes.addActionListener(e -> {

            new VentanaClientes();

            dispose();

        });

        // VENTAS

        btnVentas.addActionListener(e -> {

            new VentanaVentas();

            dispose();

        });

        // REPORTES

        btnReportes.addActionListener(e -> {

            new VentanaReportes();

            dispose();

        });

        // CERRAR SESIÓN

        btnSalir.addActionListener(e -> {

            int opcion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Desea cerrar sesión?",
                    "Cerrar sesión",
                    JOptionPane.YES_NO_OPTION
            );

            if(opcion == JOptionPane.YES_OPTION) {

                dispose();

                new Login();

            }

        });

        setVisible(true);

    }

}