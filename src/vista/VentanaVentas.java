package vista;

import javax.swing.*;
import java.awt.*;

public class VentanaVentas extends JFrame {

    public VentanaVentas() {

        setTitle("Ventas");
        setSize(500, 350);
        setLayout(null);
        getContentPane().setBackground(Color.decode("#E3AAAA"));

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(180, 250, 120, 30);

        add(btnVolver);

        btnVolver.addActionListener(e -> {
            new MenuPrincipal();
            dispose();
        });

        setVisible(true);
    }
}
