package gui;

import controlador.ControladorBase;
import controlador.IControladorBase;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import modelo.BaseDeDatos;
import modelo.Mensaje;

public class ModificarBase extends javax.swing.JFrame {

    private IControladorBase controladorBase;
    private BaseDeDatos baseAModificar;
    private GestionBaseDeDatosPanel panelAnterior;
    
    public ModificarBase(GestionBaseDeDatosPanel panelAnterior, BaseDeDatos baseAModificar) {
        initComponents();
        this.controladorBase = new ControladorBase();
        this.baseAModificar = baseAModificar;
        this.panelAnterior = panelAnterior;
        this.lblNombreAModificar.setText(baseAModificar.getNombre());
        this.txtNuevoNombre.setText(baseAModificar.getNombre());
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

        lblNuevoNombre = new javax.swing.JLabel();
        txtNuevoNombre = new javax.swing.JTextField();
        lblTitulo = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblDescripcion = new javax.swing.JLabel();
        lblIdAModificar = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblNombreAModificar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        lblNuevoNombre.setText("Nuevo nombre:");

        lblTitulo.setFont(new java.awt.Font("ROG Fonts", 1, 24)); // NOI18N
        lblTitulo.setText("Modificar Base de datos");

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblDescripcion.setText("Base de datos:");

        lblNombre.setText("NOMBRE:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(lblTitulo)
                .addContainerGap(48, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNuevoNombre)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblDescripcion)
                                .addGap(26, 26, 26)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNuevoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNombre)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblIdAModificar)
                                    .addComponent(lblNombreAModificar))))))
                .addGap(79, 79, 79))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addComponent(lblIdAModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(lblNombreAModificar)
                    .addComponent(lblDescripcion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNuevoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNuevoNombre))
                .addGap(71, 71, 71)
                .addComponent(btnModificar)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar)
                .addGap(42, 42, 42))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed

        String nuevoNombre = this.txtNuevoNombre.getText();
        Mensaje modificarionBase = controladorBase.renombrarBase(baseAModificar.getId(), nuevoNombre);
        if(modificarionBase.isExito())
        {
            showMessageDialog(null, modificarionBase.getMensaje(), "", JOptionPane.INFORMATION_MESSAGE);
            panelAnterior.listarBases();
            panelAnterior.setEnabled(true);
            this.dispose();
        } else {
            showMessageDialog(null, modificarionBase.getMensaje(), "", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
        panelAnterior.setEnabled(true);
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblIdAModificar;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNombreAModificar;
    private javax.swing.JLabel lblNuevoNombre;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtNuevoNombre;
    // End of variables declaration//GEN-END:variables
}
