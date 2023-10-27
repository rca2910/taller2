package gui;

import controlador.ControladorBase;
import controlador.IControladorBase;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import modelo.BaseDeDatos;
import modelo.Mensaje;
import modelo.Usuario;
import modelo.eRolUsuario;

/**
 *
 * @author rca29
 */
public class GestionBaseDeDatosPanel extends javax.swing.JFrame {

    private IControladorBase controladorBase;
    private Usuario usuarioLogueado;
    private BaseDeDatos baseSeleccionada;
    DefaultListModel modelBases = new DefaultListModel();

    //Constructor.
    public GestionBaseDeDatosPanel(Usuario usuarioLogueado) {
        initComponents();
        this.controladorBase = new ControladorBase();
        this.listarBases();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.usuarioLogueado = usuarioLogueado;
        if(usuarioLogueado.getRol() == eRolUsuario.COMUN)
        {
            CargarVistaComun();
        }
        else if(usuarioLogueado.getRol() == eRolUsuario.LECTOR)
        {
            CargarVistaLector();
        }
    }
    
    //Lista las bases de datos existentes en el sistema.
    public void listarBases()
    {
        this.modelBases = new DefaultListModel();
        this.listaBases.setModel(modelBases);
        
        ArrayList<BaseDeDatos> bases = controladorBase.obtenerBases();
        for(BaseDeDatos b : bases)
        {
            modelBases.addElement(b);
        }
    }

    //Carga la vista del usuario común.
    private void CargarVistaComun() {
        this.btnVolver.setVisible(false);
        this.txtCrear.setVisible(false);
        this.btnCrearBase.setVisible(false);
        this.btnEliminar.setVisible(false);
    }

    //Carga la vista del usuario lector.
    private void CargarVistaLector() {
        this.btnVolver.setVisible(false);
        this.txtCrear.setVisible(false);
        this.btnCrearBase.setVisible(false);
        this.btnCrearTabla.setVisible(false);
        this.btnModificar.setVisible(false);
        this.btnEliminar.setVisible(false);
    }
    
    //Habilita los botones para el ABM de base de datos.
    private void habilitarBotones() {
        this.btnModificar.setEnabled(true);
        this.btnCrearTabla.setEnabled(true);
        this.btnEjecutarQuery.setEnabled(true);
        this.btnEliminar.setEnabled(true);
    }

    //Deshabilita los botones ABM de base de datos.
    private void deshabilitarBotones() {
        this.btnModificar.setEnabled(false);
        this.btnCrearTabla.setEnabled(false);
        this.btnEjecutarQuery.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listaBases = new javax.swing.JList<>();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCrearBase = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnCrearTabla = new javax.swing.JButton();
        txtCrear = new java.awt.TextField();
        btnVolver = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();
        btnEjecutarQuery = new javax.swing.JButton();
        lblAcciones = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        listaBases.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaBasesMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                listaBasesMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(listaBases);

        btnModificar.setText("Renombrar Base de Datos");
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar Base de Datos");
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnCrearBase.setText("Crear Base de Datos");
        btnCrearBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearBaseActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("ROG Fonts", 1, 24)); // NOI18N
        jLabel1.setText("GESTION DE BASES DE DATOS");

        btnCrearTabla.setText("Crear tabla");
        btnCrearTabla.setEnabled(false);
        btnCrearTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearTablaActionPerformed(evt);
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

        btnCerrarSesion.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 12)); // NOI18N
        btnCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrar-sesion.png"))); // NOI18N
        btnCerrarSesion.setText("SALIR");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        btnEjecutarQuery.setText("Ejecutar query");
        btnEjecutarQuery.setEnabled(false);
        btnEjecutarQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecutarQueryActionPerformed(evt);
            }
        });

        lblAcciones.setFont(new java.awt.Font("ROG Fonts", 1, 24)); // NOI18N
        lblAcciones.setText("Acciones");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCrearTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCrear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCrearBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEjecutarQuery, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblAcciones)
                                .addGap(37, 37, 37))))
                    .addComponent(btnVolver))
                .addContainerGap(49, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(btnCerrarSesion))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel1))
                    .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAcciones)
                        .addGap(18, 18, 18)
                        .addComponent(btnCrearTabla)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEjecutarQuery)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCrearBase)))
                .addGap(18, 18, 18)
                .addComponent(btnModificar)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Crea la base de datos.
    private void btnCrearBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearBaseActionPerformed
        String nombre = this.txtCrear.getText();
        Mensaje respuestaAlta = controladorBase.altaBase(nombre);
        if(respuestaAlta.isExito())
        {
            this.txtCrear.setText("");
            this.listarBases();
            showMessageDialog(null, respuestaAlta.getMensaje(), "", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            showMessageDialog(null, respuestaAlta.getMensaje(), "", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCrearBaseActionPerformed

    //Elimina la base de datos.
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        controladorBase.bajaBase(baseSeleccionada.getId());
        deshabilitarBotones();
        this.modelBases.remove(this.listaBases.getSelectedIndex());
    }//GEN-LAST:event_btnEliminarActionPerformed

    //Vuelve al panel anterior.
    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        new AdministradorPanel(usuarioLogueado);
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    //Permite cerrar sesión.
    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed

        new Login();
        this.dispose();
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    //Permite modificar el nombre de la base de datos.
    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        this.setEnabled(false);
        new ModificarBase(this, baseSeleccionada);
    }//GEN-LAST:event_btnModificarActionPerformed

    //Permite crear una tabla en la base de datos.
    private void btnCrearTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearTablaActionPerformed
        this.setEnabled(false);
        new CrearTablaBase(this, baseSeleccionada, usuarioLogueado);
    }//GEN-LAST:event_btnCrearTablaActionPerformed

    //Permite listar las bases de datos que hay en el sistema.
    private void listaBasesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaBasesMouseClicked
        if(this.listaBases.getSelectedIndex() >= 0)
        {
            BaseDeDatos baseDeLista = (BaseDeDatos) this.modelBases.getElementAt(this.listaBases.getSelectedIndex());
            baseSeleccionada = controladorBase.obtenerBaseXId(baseDeLista.getId());
            habilitarBotones();
        }
    }//GEN-LAST:event_listaBasesMouseClicked

    //Abre el panel de ejecutar query.
    private void btnEjecutarQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutarQueryActionPerformed
        this.setEnabled(false);
        new EjecutarQueryBase(this, baseSeleccionada.getId(), usuarioLogueado);
    }//GEN-LAST:event_btnEjecutarQueryActionPerformed

    private void listaBasesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaBasesMousePressed
        if(this.listaBases.getSelectedIndex() >= 0)
        {
            BaseDeDatos baseDeLista = (BaseDeDatos) this.modelBases.getElementAt(this.listaBases.getSelectedIndex());
            baseSeleccionada = controladorBase.obtenerBaseXId(baseDeLista.getId());
            habilitarBotones();
        }
    }//GEN-LAST:event_listaBasesMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnCrearBase;
    private javax.swing.JButton btnCrearTabla;
    private javax.swing.JButton btnEjecutarQuery;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAcciones;
    private javax.swing.JList<String> listaBases;
    private java.awt.TextField txtCrear;
    // End of variables declaration//GEN-END:variables
}
