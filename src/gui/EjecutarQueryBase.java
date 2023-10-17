package gui;

import controlador.ControladorBase;
import controlador.IControladorBase;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.table.DefaultTableModel;
import modelo.BaseDeDatos;
import modelo.Columna;
import modelo.Mensaje;
import modelo.MensajeQuery;
import modelo.Tabla;
import modelo.Usuario;

public class EjecutarQueryBase extends javax.swing.JFrame {

    private GestionBaseDeDatosPanel panelAnterior;
    private IControladorBase controladorBase;
    private BaseDeDatos baseAModificar;
    private Usuario usuarioLogueado;
    private DefaultTableModel modelTablaResultado;

    public EjecutarQueryBase(GestionBaseDeDatosPanel panelAnterior, BaseDeDatos baseAModificar, Usuario usuarioLogueado) {
        initComponents();
        this.panelAnterior = panelAnterior;
        this.controladorBase = new ControladorBase();
        this.baseAModificar = baseAModificar;
        this.usuarioLogueado = usuarioLogueado;
        this.modelTablaResultado = (DefaultTableModel) tablaResultado.getModel();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        txtQuery = new java.awt.TextArea();
        btnEjecutarQuery = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaResultado = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("ROG Fonts", 1, 24)); // NOI18N
        lblTitulo.setText("EJECUTAR QUERY");

        btnEjecutarQuery.setText("Ejecutar query");
        btnEjecutarQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecutarQueryActionPerformed(evt);
            }
        });

        tablaResultado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tablaResultado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaResultado.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaResultado.setEnabled(false);
        jScrollPane1.setViewportView(tablaResultado);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(260, 260, 260)
                                .addComponent(lblTitulo))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(349, 349, 349)
                                .addComponent(btnEjecutarQuery)))
                        .addGap(0, 264, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtQuery, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addGap(57, 57, 57)
                .addComponent(txtQuery, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnEjecutarQuery)
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEjecutarQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutarQueryActionPerformed
        String query = this.txtQuery.getText();
        MensajeQuery ejecucion = controladorBase.ejecutarQuery(baseAModificar, query);
        
        if (ejecucion.isExito()) {
            showMessageDialog(null, ejecucion.getMensaje(), "", JOptionPane.INFORMATION_MESSAGE);
            mostrarColumnas(ejecucion.getColumnas());
        } else {
            showMessageDialog(null, ejecucion.getMensaje(), "", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEjecutarQueryActionPerformed

    private Mensaje ejecutarQuery(String[] sentencia) {
        if (sentencia.length >= 3) {
            switch (sentencia[0].toUpperCase()) {
                case "SELECT":
                case "CREATE":
                    break;
                case "DELETE":
                    break;
                case "INSERT":
                    break;
                case "UPDATE":
                    break;
                default:
                    return new Mensaje("Error en la query cerca de: " + sentencia[0], false);
            }
        }
        return new Mensaje("Verifique que la query sea válida", false);
    }

    private void mostrarColumnas(ArrayList<Columna> columnas) {
        modelTablaResultado = new DefaultTableModel();
        for(Columna c : columnas)
        {
            modelTablaResultado.addColumn(c.getNombre());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEjecutarQuery;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tablaResultado;
    private java.awt.TextArea txtQuery;
    // End of variables declaration//GEN-END:variables
}