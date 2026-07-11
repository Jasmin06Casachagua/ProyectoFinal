package vista;

import controlador.ProductoController;
import modelo.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class VentanaProductos extends JFrame {

    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtStock;

    private JButton btnGuardar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnVolver;

    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;

    private ProductoController pc;

    private int idSeleccionado = 0;

    public VentanaProductos() {

        setTitle("Productos - Sistema NAMI");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        getContentPane().setBackground(Color.decode("#E3AAAA"));

        pc = new ProductoController();

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 30, 80, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(100, 30, 150, 25);
        add(txtNombre);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(280, 30, 80, 25);
        add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(340, 30, 100, 25);
        add(txtPrecio);

        JLabel lblStock = new JLabel("Stock:");
        lblStock.setBounds(460, 30, 60, 25);
        add(lblStock);

        txtStock = new JTextField();
        txtStock.setBounds(520, 30, 100, 25);
        add(txtStock);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(100, 75, 120, 35);
        add(btnGuardar);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(250, 75, 120, 35);
        add(btnActualizar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(400, 75, 120, 35);
        add(btnEliminar);

        modeloTabla = new DefaultTableModel();

        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Stock");

        tablaProductos = new JTable(modeloTabla);

        JScrollPane scroll = new JScrollPane(tablaProductos);
        scroll.setBounds(30, 130, 600, 220);

        add(scroll);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(280, 390, 120, 35);
        add(btnVolver);

        cargarProductos();

        // Seleccionar producto de la tabla
        tablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent e) {

                int fila = tablaProductos.getSelectedRow();

                idSeleccionado = Integer.parseInt(
                        modeloTabla.getValueAt(fila, 0).toString()
                );

                txtNombre.setText(
                        modeloTabla.getValueAt(fila, 1).toString()
                );

                txtPrecio.setText(
                        modeloTabla.getValueAt(fila, 2).toString()
                );

                txtStock.setText(
                        modeloTabla.getValueAt(fila, 3).toString()
                );

            }

        });

        // Guardar producto
        btnGuardar.addActionListener(e -> {

            try {

                Producto p = new Producto();

                p.setNombre(txtNombre.getText());
                p.setPrecio(Double.parseDouble(txtPrecio.getText()));
                p.setStock(Integer.parseInt(txtStock.getText()));

                if(pc.insertar(p)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Producto guardado correctamente"
                    );

                    limpiarCampos();
                    cargarProductos();

                }

            } catch(Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Ingrese datos válidos"
                );

            }

        });

        // Actualizar producto
        btnActualizar.addActionListener(e -> {

            if(idSeleccionado == 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Seleccione un producto"
                );

                return;
            }

            Producto p = new Producto();

            p.setId(idSeleccionado);
            p.setNombre(txtNombre.getText());
            p.setPrecio(Double.parseDouble(txtPrecio.getText()));
            p.setStock(Integer.parseInt(txtStock.getText()));

            if(pc.actualizarProducto(p)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Producto actualizado"
                );

                limpiarCampos();
                cargarProductos();

            }

        });

        // Eliminar producto
        btnEliminar.addActionListener(e -> {

            if(idSeleccionado == 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Seleccione un producto"
                );

                return;

            }

            int respuesta = JOptionPane.showConfirmDialog(
                    this,
                    "¿Desea eliminar el producto?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION
            );

            if(respuesta == JOptionPane.YES_OPTION) {

                if(pc.eliminarProducto(idSeleccionado)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Producto eliminado"
                    );

                    limpiarCampos();
                    cargarProductos();

                }

            }

        });

        btnVolver.addActionListener(e -> {

            MenuPrincipal menu = new MenuPrincipal();
            menu.setVisible(true);

            dispose();

        });

        setVisible(true);

    }

    private void cargarProductos() {

        modeloTabla.setRowCount(0);

        ArrayList<Producto> lista = pc.listarProductos();

        for(Producto p : lista) {

            Object[] fila = {

                    p.getId(),
                    p.getNombre(),
                    p.getPrecio(),
                    p.getStock()

            };

            modeloTabla.addRow(fila);

        }

    }

    private void limpiarCampos() {

        txtNombre.setText("");
        txtPrecio.setText("");
        txtStock.setText("");

        idSeleccionado = 0;

    }

}