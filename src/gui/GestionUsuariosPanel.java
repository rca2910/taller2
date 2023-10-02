/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import controlador.ControladorUsuario;
import controlador.IControladorUsuario;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import modelo.Mensaje;
import modelo.Usuario;
import modelo.eRolUsuario;
import modelo.eVersionUsuario;

/**
 *
 * @author rca29
 */
public class GestionUsuariosPanel extends javax.swing.JFrame {

    private IControladorUsuario controladorUsuario;
    private Usuario usuarioLogueado;
    private Usuario usuarioSeleccionado;
    DefaultListModel modelUsuario = new DefaultListModel();

    public GestionUsuariosPanel(Usuario usuarioLogueado) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.usuarioLogueado = usuarioLogueado;
        this.controladorUsuario = new ControladorUsuario();
        listarUsuarios();
        listarComboRoles();
        listarComboVersiones();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTituloGestionUsuario = new javax.swing.JLabel();
        btnalta = new javax.swing.JButton();
        btnbaja = new javax.swing.JButton();
        btnmodificacion = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaUsuarios = new javax.swing.JList<>();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtContrasena = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        lblCedula = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblContrasena = new javax.swing.JLabel();
        comboRol = new javax.swing.JComboBox<>();
        lblRol = new javax.swing.JLabel();
        comboVersion = new javax.swing.JComboBox<>();
        lblRol1 = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTituloGestionUsuario.setFont(new java.awt.Font("ROG Fonts", 1, 24)); // NOI18N
        lblTituloGestionUsuario.setText("Gestion de usuarios");

        btnalta.setText("Alta");
        btnalta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaltaActionPerformed(evt);
            }
        });

        btnbaja.setText("Baja");
        btnbaja.setEnabled(false);
        btnbaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbajaActionPerformed(evt);
            }
        });

        btnmodificacion.setText("Modificación");
        btnmodificacion.setEnabled(false);
        btnmodificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificacionActionPerformed(evt);
            }
        });

        listaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listaUsuarios);

        lblCedula.setText("Cedula:");

        lblNombre.setText("Nombre:");

        jLabel3.setText("Apellido:");

        lblContrasena.setText("Contraseña:");

        lblRol.setText("Rol:");

        lblRol1.setText("Version:");

        btnCerrarSesion.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 12)); // NOI18N
        btnCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrar-sesion.png"))); // NOI18N
        btnCerrarSesion.setText("SALIR");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        btnVolver.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 12)); // NOI18N
        btnVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/volver.png"))); // NOI18N
        btnVolver.setText("VOLVER");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(220, 220, 220)
                .addComponent(lblTituloGestionUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCerrarSesion))
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnalta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                            .addComponent(txtContrasena, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                            .addComponent(txtApellido)
                            .addComponent(txtNombre)
                            .addComponent(txtCedula)
                            .addComponent(comboVersion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(btnmodificacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnbaja, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(107, 107, 107))
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnVolver)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(lblTituloGestionUsuario))
                    .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btnmodificacion)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
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
                            .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblContrasena))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRol))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboVersion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRol1))
                        .addGap(34, 34, 34)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnbaja)
                    .addComponent(btnalta))
                .addGap(46, 46, 46)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnaltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaltaActionPerformed

        String cedula = this.txtCedula.getText();
        String nombre = this.txtNombre.getText();
        String apellido = this.txtApellido.getText();
        String contrasena = this.txtContrasena.getText();
        eRolUsuario rol = this.comboRol.getModel().getElementAt(this.comboRol.getSelectedIndex());
        eVersionUsuario version = this.comboVersion.getModel().getElementAt(this.comboVersion.getSelectedIndex());
        Mensaje respuestaAlta = controladorUsuario.AltaUsuario(cedula, nombre, apellido, contrasena, rol, version);
        if (respuestaAlta.isExito()) {
            reiniciarCampos();
            listarUsuarios();
            showMessageDialog(null, respuestaAlta.getMensaje(), "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            showMessageDialog(null, respuestaAlta.getMensaje(), "", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnaltaActionPerformed

    private void btnbajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbajaActionPerformed
        Usuario aEliminar = (Usuario) this.modelUsuario.getElementAt(this.listaUsuarios.getSelectedIndex());
        controladorUsuario.BajaUsuario(aEliminar.getCedula());
        this.modelUsuario.remove(this.listaUsuarios.getSelectedIndex());
        deshabilitarBotones();
    }//GEN-LAST:event_btnbajaActionPerformed

    private void btnmodificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificacionActionPerformed
        this.setEnabled(false);
        new ModificarUsuario(usuarioSeleccionado, this);
    }//GEN-LAST:event_btnmodificacionActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed

        new Login();
        this.dispose();
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        new AdministradorPanel(usuarioLogueado);
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void listaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaUsuariosMouseClicked
        usuarioSeleccionado = (Usuario) this.modelUsuario.getElementAt(this.listaUsuarios.getSelectedIndex());
        if (usuarioSeleccionado.getCedula().equals(usuarioLogueado.getCedula())) {
            deshabilitarBotones();
        } else {
            habilitarBotones();
        }
    }//GEN-LAST:event_listaUsuariosMouseClicked

    private void reiniciarCampos() {
        this.txtCedula.setText("");
        this.txtNombre.setText("");
        this.txtApellido.setText("");
        this.txtContrasena.setText("");
        this.comboRol.setSelectedIndex(0);
        this.comboVersion.setSelectedIndex(0);
    }

    public void listarUsuarios() {
        this.modelUsuario = new DefaultListModel();
        this.listaUsuarios.setModel(modelUsuario);

        ArrayList<Usuario> usuarios = controladorUsuario.ObtenerUsuarios();
        for (Usuario u : usuarios) {
            modelUsuario.addElement(u);
        }
    }

    private void habilitarBotones() {
        this.btnmodificacion.setEnabled(true);
        this.btnbaja.setEnabled(true);
    }

    private void deshabilitarBotones() {
        this.btnmodificacion.setEnabled(false);
        this.btnbaja.setEnabled(false);
    }

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
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnVolver;
    private javax.swing.JButton btnalta;
    private javax.swing.JButton btnbaja;
    private javax.swing.JButton btnmodificacion;
    private javax.swing.JComboBox<eRolUsuario> comboRol;
    private javax.swing.JComboBox<eVersionUsuario> comboVersion;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblContrasena;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblRol;
    private javax.swing.JLabel lblRol1;
    private javax.swing.JLabel lblTituloGestionUsuario;
    private javax.swing.JList<String> listaUsuarios;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtContrasena;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
