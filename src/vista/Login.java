package vista;

import controlador.UsuarioController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {

    private JTextField txtCorreo;
    private JPasswordField txtPass;

    public Login() {

        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        new MenuPrincipal();
        dispose();

        getContentPane().setBackground(Color.decode("#E3AAAA"));

        JLabel lbl1 = new JLabel("Usuario:");
        lbl1.setBounds(20, 30, 80, 20);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(100, 30, 150, 20);

        JLabel lbl2 = new JLabel("Contraseña:");
        lbl2.setBounds(20, 70, 80, 20);

        txtPass = new JPasswordField();
        txtPass.setBounds(100, 70, 150, 20);

        JButton btnLogin = new JButton("Ingresar");
        btnLogin.setBounds(100, 110, 100, 30);

        btnLogin.setBackground(Color.decode("#D3D3D3"));

        add(lbl1); add(txtCorreo);
        add(lbl2); add(txtPass);
        add(btnLogin);

        // Evento Botón
        btnLogin.addActionListener(e -> {

            UsuarioController uc = new UsuarioController();

            if (uc.login(txtCorreo.getText(), txtPass.getText())) {

                JOptionPane.showMessageDialog(null, "Bienvenido");

                new MenuPrincipal();
                dispose();

            } else {
                JOptionPane.showMessageDialog(null, "Datos incorrectos");
            }
        });

        setVisible(true);
    }
}