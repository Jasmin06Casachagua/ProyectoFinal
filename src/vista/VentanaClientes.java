package vista;

import controlador.ClienteController;
import modelo.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class VentanaClientes extends JFrame {

    private JTextField txtNombre;
    private JTextField txtTelefono;
    private JTextField txtDireccion;

    private JTable tablaClientes;
    private DefaultTableModel modeloTabla;

    private ClienteController cc;

    public VentanaClientes() {

        setTitle("Clientes - Sistema NAMI");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        getContentPane().setBackground(Color.decode("#E3AAAA"));

        cc = new ClienteController();

        // ================= CAMPOS =================

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 30, 80, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(100, 30, 150, 25);
        add(txtNombre);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(280, 30, 80, 25);
        add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(350, 30, 120, 25);
        add(txtTelefono);

        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setBounds(30, 70, 80, 25);
        add(lblDireccion);

        txtDireccion = new JTextField();
        txtDireccion.setBounds(100, 70, 370, 25);
        add(txtDireccion);

        // ================= TABLA =================

        modeloTabla = new DefaultTableModel();

        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Teléfono");
        modeloTabla.addColumn("Dirección");

        tablaClientes = new JTable(modeloTabla);

        JScrollPane scroll = new JScrollPane(tablaClientes);
        scroll.setBounds(30, 130, 620, 220);

        add(scroll);

        // ================= BOTONES =================

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(70, 390, 120, 30);
        add(btnGuardar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(220, 390, 120, 30);
        add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(370, 390, 120, 30);
        add(btnEliminar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(520, 390, 100, 30);
        add(btnVolver);

        cargarClientes();

        // ================= GUARDAR =================

        btnGuardar.addActionListener(e -> {

            Cliente c = new Cliente();

            c.setNombre(txtNombre.getText());

            c.setTelefono(txtTelefono.getText());

            c.setDireccion(txtDireccion.getText());

            if(cc.insertar(c)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Cliente guardado correctamente"
                );

                limpiarCampos();

                cargarClientes();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Error al guardar cliente"
                );

            }

        });

        // ================= SELECCIONAR TABLA =================

        tablaClientes.getSelectionModel()
                .addListSelectionListener(e -> {

                    int fila = tablaClientes.getSelectedRow();

                    if(fila >= 0) {

                        txtNombre.setText(
                                modeloTabla.getValueAt(fila,1).toString()
                        );

                        txtTelefono.setText(
                                modeloTabla.getValueAt(fila,2).toString()
                        );

                        txtDireccion.setText(
                                modeloTabla.getValueAt(fila,3).toString()
                        );

                    }

                });

        // ================= ACTUALIZAR =================

        btnActualizar.addActionListener(e -> {

            int fila = tablaClientes.getSelectedRow();

            if(fila >= 0) {

                Cliente c = new Cliente();

                c.setId(
                        Integer.parseInt(
                                modeloTabla.getValueAt(fila,0).toString()
                        )
                );

                c.setNombre(txtNombre.getText());

                c.setTelefono(txtTelefono.getText());

                c.setDireccion(txtDireccion.getText());

                if(cc.actualizarCliente(c)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Cliente actualizado"
                    );

                    cargarClientes();

                }

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Seleccione un cliente"
                );

            }

        });

        // ================= ELIMINAR =================

        btnEliminar.addActionListener(e -> {

            int fila = tablaClientes.getSelectedRow();

            if(fila >= 0) {

                int id = Integer.parseInt(
                        modeloTabla.getValueAt(fila,0).toString()
                );

                if(cc.eliminarCliente(id)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Cliente eliminado"
                    );

                    cargarClientes();

                    limpiarCampos();

                }

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Seleccione un cliente"
                );

            }

        });

        // ================= VOLVER =================

        btnVolver.addActionListener(e -> {

            new MenuPrincipal();

            dispose();

        });

        setVisible(true);

    }

    private void cargarClientes() {

        modeloTabla.setRowCount(0);

        ArrayList<Cliente> lista =
                cc.listarClientes();

        for(Cliente c : lista) {

            modeloTabla.addRow(
                    new Object[]{
                            c.getId(),
                            c.getNombre(),
                            c.getTelefono(),
                            c.getDireccion()
                    }
            );

        }

    }

    private void limpiarCampos() {

        txtNombre.setText("");

        txtTelefono.setText("");

        txtDireccion.setText("");

    }

}