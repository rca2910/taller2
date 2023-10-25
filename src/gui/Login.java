package gui;

import controlador.Fachada;
import static javax.swing.JOptionPane.showMessageDialog;
import modelo.Usuario;

/**
 *
 * @author rca29
 */
public class Login extends javax.swing.JFrame {

    private Fachada fachada;

    //Constructor.
    public Login() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.fachada = new Fachada();;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLoginTitulo = new javax.swing.JLabel();
        lblLoginUsuario = new javax.swing.JLabel();
        lblLoginContrasena = new javax.swing.JLabel();
        txtusuario = new javax.swing.JTextField();
        txtcontrasena = new javax.swing.JTextField();
        btnlogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestor de Bases de datos");
        setResizable(false);

        lblLoginTitulo.setFont(new java.awt.Font("ROG Fonts", 1, 24)); // NOI18N
        lblLoginTitulo.setText("Ingrese al Sistema");

        lblLoginUsuario.setFont(new java.awt.Font("Rockwell Extra Bold", 1, 18)); // NOI18N
        lblLoginUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuario.png"))); // NOI18N
        lblLoginUsuario.setText("Usuario");

        lblLoginContrasena.setFont(new java.awt.Font("Rockwell Extra Bold", 1, 18)); // NOI18N
        lblLoginContrasena.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/password.png"))); // NOI18N
        lblLoginContrasena.setText("Contraseña");

        txtusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusuarioActionPerformed(evt);
            }
        });

        btnlogin.setFont(new java.awt.Font("Rockwell Extra Bold", 1, 18)); // NOI18N
        btnlogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ingresar.png"))); // NOI18N
        btnlogin.setText("INGRESAR");
        btnlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnloginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(252, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblLoginTitulo)
                        .addGap(214, 214, 214))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLoginContrasena)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtusuario)
                                .addComponent(txtcontrasena, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                                .addComponent(btnlogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lblLoginUsuario))
                        .addGap(264, 264, 264))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblLoginTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblLoginUsuario)
                .addGap(18, 18, 18)
                .addComponent(txtusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblLoginContrasena)
                .addGap(18, 18, 18)
                .addComponent(txtcontrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(btnlogin)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Permite loguearse a un usuario.
    private void btnloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnloginActionPerformed
        String cedula = txtusuario.getText();
        String contrasena = txtcontrasena.getText();

        Usuario usuario = fachada.getControladorUsuario().login(cedula, contrasena);

        if (usuario == null) {
            showMessageDialog(null, "Cédula o contraseña incorrecta");
            return;
        }
        switch (usuario.getRol()) {

            case ADMINISTRADOR:
                AdministradorPanel panelAdministrador = new AdministradorPanel(usuario);
                break;
            default:
                GestionBaseDeDatosPanel panelGestionBaseDeDatos = new GestionBaseDeDatosPanel(usuario);
                break;

        }
        this.dispose();

    }//GEN-LAST:event_btnloginActionPerformed

    private void txtusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusuarioActionPerformed

    }//GEN-LAST:event_txtusuarioActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnlogin;
    private javax.swing.JLabel lblLoginContrasena;
    private javax.swing.JLabel lblLoginTitulo;
    private javax.swing.JLabel lblLoginUsuario;
    private javax.swing.JTextField txtcontrasena;
    private javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables
}
