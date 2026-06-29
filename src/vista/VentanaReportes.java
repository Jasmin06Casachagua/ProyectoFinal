package vista;

import javax.swing.*;
import java.awt.*;

public class VentanaReportes extends JFrame {

    public VentanaReportes() {

        setTitle("Reportes");
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        getContentPane().setBackground(Color.decode("#E3AAAA"));

        JLabel lbl = new JLabel("MÓDULO DE REPORTES");
        lbl.setBounds(120, 100, 200, 30);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(140, 180, 120, 30);

        add(lbl);
        add(btnVolver);

        btnVolver.addActionListener(e -> {
            new MenuPrincipal();
            dispose();
        });

        setVisible(true);
    }
}