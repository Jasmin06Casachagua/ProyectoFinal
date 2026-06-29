package vista;

import controlador.ClienteController;
import modelo.Cliente;

import javax.swing.*;
import java.awt.*;

public class VentanaClientes extends JFrame {

    public VentanaClientes() {

        setTitle("Clientes");
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

        ClienteController cc = new ClienteController();

        btnGuardar.addActionListener(e -> {

            Cliente c = new Cliente();
            c.setNombre(txtNombre.getText());

            cc.insertar(c);

            JOptionPane.showMessageDialog(this, "Cliente guardado");
        });

        btnVolver.addActionListener(e -> {
            new MenuPrincipal();
            dispose();
        });

        setVisible(true);
    }
}