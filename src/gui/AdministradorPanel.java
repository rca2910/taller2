/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import controlador.Fachada;
import java.io.IOException;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import modelo.Usuario;

/**
 *
 * @author rca29
 */
public class AdministradorPanel extends javax.swing.JFrame {

    private Usuario usuarioLogueado;
    private Fachada fachada;
    
    //Constructor.
    public AdministradorPanel(Usuario usuarioLogueado) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.usuarioLogueado = usuarioLogueado;
        this.fachada = new Fachada();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnAdmgestionusuario = new javax.swing.JButton();
        Admgestionbase = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();
        btnImportarBases = new javax.swing.JButton();
        btnExportarBases = new javax.swing.JButton();
        btnImportarUsuarios = new javax.swing.JButton();
        btnExportarUsuarios = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("ROG Fonts", 1, 24)); // NOI18N
        jLabel1.setText("Panel de Control");

        btnAdmgestionusuario.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 18)); // NOI18N
        btnAdmgestionusuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios.png"))); // NOI18N
        btnAdmgestionusuario.setText("Gestión de Usuarios");
        btnAdmgestionusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdmgestionusuarioActionPerformed(evt);
            }
        });

        Admgestionbase.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 18)); // NOI18N
        Admgestionbase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/gestion-base-de-datos.png"))); // NOI18N
        Admgestionbase.setText("Gestión Bases de Datos");
        Admgestionbase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdmgestionbaseActionPerformed(evt);
            }
        });

        btnCerrarSesion.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 12)); // NOI18N
        btnCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrar-sesion.png"))); // NOI18N
        btnCerrarSesion.setText("SALIR");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        btnImportarBases.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 18)); // NOI18N
        btnImportarBases.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/importar.png"))); // NOI18N
        btnImportarBases.setText("Importar Bases de Datos");
        btnImportarBases.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportarBasesActionPerformed(evt);
            }
        });

        btnExportarBases.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 18)); // NOI18N
        btnExportarBases.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/exportar.png"))); // NOI18N
        btnExportarBases.setText("Exportar Bases de Datos");
        btnExportarBases.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarBasesActionPerformed(evt);
            }
        });

        btnImportarUsuarios.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 18)); // NOI18N
        btnImportarUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/importar.png"))); // NOI18N
        btnImportarUsuarios.setText("Importar Usuarios");
        btnImportarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportarUsuariosActionPerformed(evt);
            }
        });

        btnExportarUsuarios.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 18)); // NOI18N
        btnExportarUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/exportar.png"))); // NOI18N
        btnExportarUsuarios.setText("Exportar Usuarios");
        btnExportarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarUsuariosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(242, 242, 242))
                            .addComponent(btnCerrarSesion, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnExportarBases, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Admgestionbase, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnImportarBases, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAdmgestionusuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnImportarUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnExportarUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel1))
                    .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(87, 87, 87)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Admgestionbase)
                    .addComponent(btnAdmgestionusuario))
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnImportarBases)
                    .addComponent(btnImportarUsuarios))
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExportarBases)
                    .addComponent(btnExportarUsuarios))
                .addContainerGap(100, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Muestra el panel de gestión de usuario.
    private void btnAdmgestionusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdmgestionusuarioActionPerformed

        new GestionUsuariosPanel(usuarioLogueado);
        this.dispose();
    }//GEN-LAST:event_btnAdmgestionusuarioActionPerformed

    //Muestra el panel de gestión de base de datos.
    private void AdmgestionbaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdmgestionbaseActionPerformed
        GestionBaseDeDatosPanel panelGestionBaseDeDatos = new GestionBaseDeDatosPanel(usuarioLogueado);
        this.dispose();
    }//GEN-LAST:event_AdmgestionbaseActionPerformed

    //Cierra la sesión.
    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed

        new Login();
        this.dispose();
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    //Carga las bases de datos.
    private void btnImportarBasesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportarBasesActionPerformed
        try {
            fachada.getControladorArchivo().CargarBasesDeDatos();
            showMessageDialog(null, "Las bases de datos fueron importadas exitosamente", "", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            showMessageDialog(null, "Error al importar las bases de datos", "", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            showMessageDialog(null, "Error al importar las bases de datos", "", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnImportarBasesActionPerformed

    //Permite guardar las bases de datos.
    private void btnExportarBasesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarBasesActionPerformed
        try{fachada.getControladorArchivo().GuardarBasesDeDatos();
        showMessageDialog(null, "Las bases de datos fueron exportadas exitosamente", "", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (IOException e) {
            showMessageDialog(null, "Error al exportar las bases de datos", "", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnExportarBasesActionPerformed

    //Carga los usuarios.
    private void btnImportarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportarUsuariosActionPerformed
        try {
            fachada.getControladorArchivo().CargarUsuarios();
            showMessageDialog(null, "Los usuarios fueron importados exitosamente", "", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            showMessageDialog(null, "Error al importar los usuarios", "", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            showMessageDialog(null, "Error al importar los usuarios", "", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnImportarUsuariosActionPerformed

    //Permite guardar los usuarios.
    private void btnExportarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarUsuariosActionPerformed
        try{fachada.getControladorArchivo().GuardarUsuarios();
        showMessageDialog(null, "Los usuarios fueron exportados exitosamente", "", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (IOException e) {
            showMessageDialog(null, "Error al exportar los usuarios", "", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnExportarUsuariosActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Admgestionbase;
    private javax.swing.JButton btnAdmgestionusuario;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnExportarBases;
    private javax.swing.JButton btnExportarUsuarios;
    private javax.swing.JButton btnImportarBases;
    private javax.swing.JButton btnImportarUsuarios;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
