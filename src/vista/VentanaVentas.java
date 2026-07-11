package vista;

import controlador.ProductoController;
import controlador.VentaController;

import modelo.Producto;
import modelo.DetalleVenta;
import modelo.Venta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaVentas extends JFrame {

    private JTextField txtIdProducto, txtCantidad;

    private JTable tabla;
    private DefaultTableModel modeloTabla;

    private JLabel lblTotal;

    private Venta venta;

    private ProductoController pc;
    private VentaController vc;

    public VentanaVentas() {

        setTitle("Ventas - Sistema NAMI");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setLayout(null);

        getContentPane().setBackground(Color.decode("#E3AAAA"));

        venta = new Venta();

        pc = new ProductoController();

        vc = new VentaController();

        // ================= CAMPOS =================

        JLabel lblProducto = new JLabel("ID Producto:");

        lblProducto.setBounds(30,30,100,25);

        add(lblProducto);

        txtIdProducto = new JTextField();

        txtIdProducto.setBounds(130,30,100,25);

        add(txtIdProducto);

        JLabel lblCantidad = new JLabel("Cantidad:");

        lblCantidad.setBounds(250,30,80,25);

        add(lblCantidad);

        txtCantidad = new JTextField();

        txtCantidad.setBounds(330,30,80,25);

        add(txtCantidad);

        JButton btnAgregar = new JButton("Agregar");

        btnAgregar.setBounds(430,30,100,25);

        add(btnAgregar);

        // ================= TABLA =================

        modeloTabla = new DefaultTableModel();

        modeloTabla.addColumn("Producto");

        modeloTabla.addColumn("Cantidad");

        modeloTabla.addColumn("Subtotal");

        tabla = new JTable(modeloTabla);

        JScrollPane scroll = new JScrollPane(tabla);

        scroll.setBounds(30,80,500,200);

        add(scroll);

        // ================= TOTAL =================

        lblTotal = new JLabel("Total: S/ 0.0");

        lblTotal.setBounds(30,300,200,25);

        add(lblTotal);

        // ================= BOTONES =================

        JButton btnQuitar = new JButton("Quitar");

        btnQuitar.setBounds(30,340,120,30);

        add(btnQuitar);

        JButton btnCancelar = new JButton("Cancelar");

        btnCancelar.setBounds(170,340,120,30);

        add(btnCancelar);

        JButton btnRegistrar = new JButton("Registrar Venta");

        btnRegistrar.setBounds(300,340,150,30);

        add(btnRegistrar);

        JButton btnVolver = new JButton("Volver");

        btnVolver.setBounds(470,340,90,30);

        add(btnVolver);

        // ================= AGREGAR PRODUCTO =================

        btnAgregar.addActionListener(e -> {

            try {

                int id =
                        Integer.parseInt(txtIdProducto.getText());

                int cantidad =
                        Integer.parseInt(txtCantidad.getText());

                Producto p =
                        pc.buscarPorId(id);

                if(p == null) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Producto no existe"
                    );

                    return;

                }

                if(cantidad <= 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Cantidad inválida"
                    );

                    return;

                }

                if(cantidad > p.getStock()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Stock insuficiente"
                    );

                    return;

                }

                DetalleVenta dv =
                        new DetalleVenta(p,cantidad);

                venta.agregarDetalle(dv);

                modeloTabla.addRow(new Object[]{

                        p.getNombre(),

                        cantidad,

                        dv.getSubtotal()

                });

                calcularTotal();

                txtIdProducto.setText("");

                txtCantidad.setText("");

            }catch(Exception ex){

                JOptionPane.showMessageDialog(
                        this,
                        "Datos inválidos"
                );

            }

        });

        // ================= QUITAR PRODUCTO =================

        btnQuitar.addActionListener(e -> {

            int fila =
                    tabla.getSelectedRow();

            if(fila >= 0) {

                // Eliminar de tabla

                modeloTabla.removeRow(fila);

                // Eliminar de la lista de venta

                venta.getDetalles().remove(fila);

                calcularTotal();

            }else{

                JOptionPane.showMessageDialog(
                        this,
                        "Seleccione un producto"
                );

            }

        });

        // ================= CANCELAR VENTA =================

        btnCancelar.addActionListener(e -> {

            modeloTabla.setRowCount(0);

            venta = new Venta();

            lblTotal.setText(
                    "Total: S/ 0.0"
            );

            txtIdProducto.setText("");

            txtCantidad.setText("");

        });

        // ================= REGISTRAR VENTA =================

        btnRegistrar.addActionListener(e -> {

            if(venta.getDetalles().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "No hay productos en la venta"
                );

                return;

            }

            if(vc.registrarVenta(venta)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Venta registrada correctamente\n"
                        + lblTotal.getText()
                );

                modeloTabla.setRowCount(0);

                lblTotal.setText(
                        "Total: S/ 0.0"
                );

                venta = new Venta();

            }else{

                JOptionPane.showMessageDialog(
                        this,
                        "Error al registrar venta"
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

    // ================= CALCULAR TOTAL =================

    private void calcularTotal() {

        double total = 0;

        for(int i=0;i<modeloTabla.getRowCount();i++){

            total += Double.parseDouble(
                    modeloTabla.getValueAt(i,2).toString()
            );

        }

        lblTotal.setText(
                "Total: S/ " + total
        );

    }

}