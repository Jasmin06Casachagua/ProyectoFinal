package vista;

import controlador.ProductoController;
import modelo.Producto;

import javax.swing.*;
import java.awt.*;

public class VentanaProductos extends JFrame {

    public VentanaProductos() {

        setTitle("Productos");
        setSize(400, 300);
        setLayout(null);
        getContentPane().setBackground(Color.decode("#E3AAAA"));

        JLabel lbl = new JLabel("Nombre:");
        lbl.setBounds(30, 30, 80, 25);

        JTextField txtNombre = new JTextField();
        txtNombre.setBounds(120, 30, 150, 25);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(120, 120, 120, 30);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(120, 160, 120, 30);

        add(lbl);
        add(txtNombre);
        add(btnGuardar);
        add(btnVolver);

        ProductoController pc = new ProductoController();

        btnGuardar.addActionListener(e -> {

            Producto p = new Producto();
            p.setNombre(txtNombre.getText());
            p.setPrecio(10); // temporal
            p.setStock(5);   // temporal

            pc.insertar(p);

            JOptionPane.showMessageDialog(this, "Producto guardado");
        });

        btnVolver.addActionListener(e -> {
            new MenuPrincipal();
            dispose();
        });

        setVisible(true);
    }
}