package vista;

import controlador.UsuarioController;
import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {

    private UsuarioController usuarioController;

    private JTextField txtUsuario;

    private JPasswordField txtPass;

    private JButton btnIngresar;

    public Login() {

        usuarioController = new UsuarioController();

        setTitle("Sistema NAMI");

        setSize(400,300);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        getContentPane().setBackground(Color.decode("#E3AAAA"));

        JLabel lblTitulo = new JLabel("Inicio de Sesión NAMI");

        lblTitulo.setBounds(110,30,200,30);

        add(lblTitulo);

        JLabel lblUsuario = new JLabel("Usuario:");

        lblUsuario.setBounds(50,80,80,25);

        add(lblUsuario);

        txtUsuario = new JTextField();

        txtUsuario.setBounds(130,80,180,25);

        add(txtUsuario);

        JLabel lblPass = new JLabel("Contraseña:");

        lblPass.setBounds(50,120,80,25);

        add(lblPass);

        txtPass = new JPasswordField();

        txtPass.setBounds(130,120,180,25);

        add(txtPass);

        btnIngresar = new JButton("Ingresar");

        btnIngresar.setBounds(130,170,120,35);

        add(btnIngresar);

        btnIngresar.addActionListener(e -> {

            String usuario = txtUsuario.getText();

            String contraseña =
                    String.valueOf(txtPass.getPassword());

            if(usuarioController.validarUsuario(usuario, contraseña)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Bienvenido al Sistema NAMI"
                );

                new MenuPrincipal();

                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Usuario o contraseña incorrectos"
                );

            }

        });

        setVisible(true);

    }

}