package gui;

import controlador.Fachada;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import modelo.Mensaje;
import modelo.Usuario;
import modelo.eRolUsuario;
import modelo.eVersionUsuario;

public class ModificarUsuario extends javax.swing.JFrame {

    private Fachada fachada;
    private Usuario usuarioAModificar;
    private GestionUsuariosPanel gestionUsuariosPanel;

    public ModificarUsuario(Usuario usuarioAModificar, GestionUsuariosPanel gestionUsuariosPanel) {
        initComponents();
        listarComboRoles();
        listarComboVersiones();
        this.fachada = new Fachada();
        this.gestionUsuariosPanel = gestionUsuariosPanel;
        this.usuarioAModificar = usuarioAModificar;
        this.txtCedula.setText(usuarioAModificar.getCedula());
        this.txtNombre.setText(usuarioAModificar.getNombre());
        this.txtApellido.setText(usuarioAModificar.getApellido());
        this.txtContrasena.setText(usuarioAModificar.getContrasena());
        this.comboRol.setSelectedItem(usuarioAModificar.getRol());
        this.comboVersion.setSelectedItem(usuarioAModificar.getVersionUsuario());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboVersion = new javax.swing.JComboBox<>();
        lblRol1 = new javax.swing.JLabel();
        comboRol = new javax.swing.JComboBox<>();
        lblRol = new javax.swing.JLabel();
        lblContrasena = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        lblCedula = new javax.swing.JLabel();
        lblTituloGestionUsuario = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        chkCambiarContrasena = new javax.swing.JCheckBox();
        txtContrasena = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        lblRol1.setText("Version:");

        lblRol.setText("Rol:");

        lblContrasena.setText("Contraseña:");

        jLabel3.setText("Apellido:");

        lblNombre.setText("Nombre:");

        lblCedula.setText("Cedula:");

        lblTituloGestionUsuario.setFont(new java.awt.Font("ROG Fonts", 1, 24)); // NOI18N
        lblTituloGestionUsuario.setText("Modificar usuario");

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

        chkCambiarContrasena.setText("modificar");
        chkCambiarContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCambiarContrasenaActionPerformed(evt);
            }
        });

        txtContrasena.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(73, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombre)
                            .addComponent(lblCedula)
                            .addComponent(jLabel3)
                            .addComponent(lblContrasena)
                            .addComponent(lblRol)
                            .addComponent(lblRol1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboRol, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtApellido)
                            .addComponent(txtNombre)
                            .addComponent(txtCedula)
                            .addComponent(comboVersion, 0, 195, Short.MAX_VALUE)
                            .addComponent(txtContrasena)))
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkCambiarContrasena)
                .addGap(59, 59, 59))
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(lblTituloGestionUsuario)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblTituloGestionUsuario)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCedula))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContrasena)
                    .addComponent(chkCambiarContrasena)
                    .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRol))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboVersion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRol1))
                .addGap(47, 47, 47)
                .addComponent(btnModificar)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        String cedula = usuarioAModificar.getCedula();
        String nuevaCedula = this.txtCedula.getText();
        String nombre = this.txtNombre.getText();
        String apellido = this.txtApellido.getText();
        String contrasena = this.txtContrasena.getText();
        eRolUsuario rol = this.comboRol.getModel().getElementAt(this.comboRol.getSelectedIndex());
        eVersionUsuario version = this.comboVersion.getModel().getElementAt(this.comboVersion.getSelectedIndex());
        Mensaje modificacionUsuario = fachada.getControladorUsuario().modificarUsuario(cedula, nombre, apellido, contrasena, nuevaCedula, rol, version);
        if (modificacionUsuario.isExito()) {
            gestionUsuariosPanel.listarUsuarios();
            gestionUsuariosPanel.setEnabled(true);
            this.dispose();
            showMessageDialog(null, modificacionUsuario.getMensaje(), "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            showMessageDialog(null, modificacionUsuario.getMensaje(), "", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
        gestionUsuariosPanel.setEnabled(true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void chkCambiarContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCambiarContrasenaActionPerformed
        if (this.chkCambiarContrasena.isSelected()) {
            this.txtContrasena.setEnabled(true);
        } else {
            this.txtContrasena.setText(usuarioAModificar.getContrasena());
            this.txtContrasena.setEnabled(false);
        }
    }//GEN-LAST:event_chkCambiarContrasenaActionPerformed

    /*Para estos dos metodos de combobox, se puede apreciar en la pestaña de
    design del netbeans, al clickear en el combobox e ir a la pestaña code,
    el valor asignado en la propiedad typeParameter es el enum que se usa
    para el model*/
    private void listarComboRoles() {
        DefaultComboBoxModel modelRol = new DefaultComboBoxModel(eRolUsuario.values());
        this.comboRol.setModel(modelRol);
    }

    private void listarComboVersiones() {
        DefaultComboBoxModel modelVersion = new DefaultComboBoxModel(eVersionUsuario.values());
        this.comboVersion.setModel(modelVersion);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JCheckBox chkCambiarContrasena;
    private javax.swing.JComboBox<eRolUsuario> comboRol;
    private javax.swing.JComboBox<eVersionUsuario> comboVersion;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblContrasena;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblRol;
    private javax.swing.JLabel lblRol1;
    private javax.swing.JLabel lblTituloGestionUsuario;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
