package gui;

import controlador.ControladorBase;
import controlador.IControladorBase;
import java.awt.Checkbox;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.BaseDeDatos;
import modelo.Columna;
import modelo.Mensaje;
import modelo.Tabla;
import modelo.Usuario;
import modelo.eTipoColumna;

public class CrearTablaBase extends javax.swing.JFrame {
    
    private GestionBaseDeDatosPanel panelAnterior;
    private IControladorBase controladorBase;
    private BaseDeDatos baseAModificar;
    private Usuario usuarioLogueado;
    private Tabla tablaACrear = null;
    private ArrayList<Columna> columnasAAgregar = new ArrayList<Columna>();
    private DefaultTableModel modelTabla;

    public CrearTablaBase(GestionBaseDeDatosPanel panelAnterior, BaseDeDatos baseAModificar, Usuario usuarioLogueado) {
        initComponents();
        this.controladorBase = new ControladorBase();
        this.panelAnterior = panelAnterior;
        this.baseAModificar = baseAModificar;
        this.usuarioLogueado = usuarioLogueado;
        this.modelTabla = (DefaultTableModel) tablaBase.getModel();
        this.inicializarCamposColumnas();
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
        lblNombre = new javax.swing.JLabel();
        txtNombreTabla = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtNombreColumna1 = new javax.swing.JTextField();
        comboTipoColumna1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        chkNulleableColumna1 = new java.awt.Checkbox();
        btnCrear = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        comboTipoColumna2 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        chkNulleableColumna2 = new java.awt.Checkbox();
        jLabel4 = new javax.swing.JLabel();
        txtNombreColumna2 = new javax.swing.JTextField();
        comboTipoColumna3 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        chkNulleableColumna3 = new java.awt.Checkbox();
        jLabel6 = new javax.swing.JLabel();
        txtNombreColumna3 = new javax.swing.JTextField();
        comboTipoColumna4 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        chkNulleableColumna4 = new java.awt.Checkbox();
        jLabel8 = new javax.swing.JLabel();
        txtNombreColumna4 = new javax.swing.JTextField();
        txtNombreColumna5 = new javax.swing.JTextField();
        comboTipoColumna5 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        chkNulleableColumna5 = new java.awt.Checkbox();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaBase = new javax.swing.JTable();
        lblNombreTabla = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        lblTitulo.setFont(new java.awt.Font("ROG Fonts", 1, 24)); // NOI18N
        lblTitulo.setText("Crear tabla");

        lblNombre.setText("Tabla:");

        jLabel1.setText("Columna:");

        jLabel2.setText("Tipo:");

        chkNulleableColumna1.setLabel("Nulleable");

        btnCrear.setText("Crear");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel3.setText("Tipo:");

        chkNulleableColumna2.setLabel("Nulleable");

        jLabel4.setText("Columna:");

        jLabel5.setText("Tipo:");

        chkNulleableColumna3.setLabel("Nulleable");

        jLabel6.setText("Columna:");

        jLabel7.setText("Tipo:");

        chkNulleableColumna4.setLabel("Nulleable");

        jLabel8.setText("Columna:");

        jLabel9.setText("Tipo:");

        chkNulleableColumna5.setLabel("Nulleable");

        jLabel10.setText("Columna:");

        tablaBase.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tablaBase.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaBase.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaBase.setEnabled(false);
        jScrollPane1.setViewportView(tablaBase);

        lblNombreTabla.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNombreColumna2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboTipoColumna2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                                .addComponent(chkNulleableColumna2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNombreColumna3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboTipoColumna3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(chkNulleableColumna3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNombreColumna4, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboTipoColumna4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(chkNulleableColumna4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNombreColumna5, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboTipoColumna5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(chkNulleableColumna5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(lblNombre))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtNombreColumna1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(comboTipoColumna1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtNombreTabla))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(chkNulleableColumna1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(48, 48, 48)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(47, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblNombreTabla)
                .addGap(268, 268, 268))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitulo)
                    .addComponent(lblNombreTabla))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombre)
                            .addComponent(txtNombreTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(txtNombreColumna1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(comboTipoColumna1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addComponent(chkNulleableColumna1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(txtNombreColumna2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(comboTipoColumna2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addComponent(chkNulleableColumna2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtNombreColumna3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboTipoColumna3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addComponent(chkNulleableColumna3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtNombreColumna4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboTipoColumna4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(chkNulleableColumna4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txtNombreColumna5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboTipoColumna5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9))
                    .addComponent(chkNulleableColumna5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(btnCrear)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        String nombreTabla = this.txtNombreTabla.getText();
        if(nombreTabla.isEmpty())
        {
            showMessageDialog(null,"El nombre de la tabla no puede ser vacio", "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        columnasAAgregar = new ArrayList<Columna>();
        Mensaje respuestaCargarColumnas = cargarColumnas();
        
        if(!respuestaCargarColumnas.isExito())
        {
            showMessageDialog(null, respuestaCargarColumnas.getMensaje(), "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(tablaACrear == null)
        {
            if(columnasAAgregar.isEmpty())
            {
                tablaACrear = new Tabla(nombreTabla);
            }
            else
            {
                tablaACrear = new Tabla(nombreTabla, columnasAAgregar);
            }
            Mensaje respuestaAgregarTabla = controladorBase.agregarTabla(baseAModificar, tablaACrear);
            if(respuestaAgregarTabla.isExito())
            {
                showMessageDialog(null, respuestaAgregarTabla.getMensaje(), "", JOptionPane.INFORMATION_MESSAGE);
                this.lblNombreTabla.setText(nombreTabla);
                mostrarColumnas();
                inicializarCamposColumnas();
                this.txtNombreTabla.setEnabled(false);
                this.btnCrear.setText("Agregar columnas");
                this.btnCancelar.setText("Finalizar");
            }
            else
            {
                showMessageDialog(null, respuestaAgregarTabla.getMensaje(), "", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            Mensaje respuestaAgregarVariasColumnas = controladorBase.agregarVariasColumnas(tablaACrear, columnasAAgregar);
            if(respuestaAgregarVariasColumnas.isExito())
            {
                showMessageDialog(null, respuestaAgregarVariasColumnas.getMensaje(), "", JOptionPane.INFORMATION_MESSAGE);
                mostrarColumnas();
                inicializarCamposColumnas();
            }
            else
            {
                showMessageDialog(null, respuestaAgregarVariasColumnas.getMensaje(), "", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        panelAnterior.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void inicializarCamposColumnas(){
        this.txtNombreColumna1.setText("");
        this.txtNombreColumna2.setText("");
        this.txtNombreColumna3.setText("");
        this.txtNombreColumna4.setText("");
        this.txtNombreColumna5.setText("");
        listarTipoCombos();
        this.chkNulleableColumna1.setState(false);
        this.chkNulleableColumna2.setState(false);
        this.chkNulleableColumna3.setState(false);
        this.chkNulleableColumna4.setState(false);
        this.chkNulleableColumna5.setState(false);
    }
    
    private void listarTipoCombos() {
        DefaultComboBoxModel modelTipo1 = new DefaultComboBoxModel(eTipoColumna.values());
        this.comboTipoColumna1.setModel(modelTipo1);
        DefaultComboBoxModel modelTipo2 = new DefaultComboBoxModel(eTipoColumna.values());
        this.comboTipoColumna2.setModel(modelTipo2);
        DefaultComboBoxModel modelTipo3 = new DefaultComboBoxModel(eTipoColumna.values());
        this.comboTipoColumna3.setModel(modelTipo3);
        DefaultComboBoxModel modelTipo4 = new DefaultComboBoxModel(eTipoColumna.values());
        this.comboTipoColumna4.setModel(modelTipo4);
        DefaultComboBoxModel modelTipo5 = new DefaultComboBoxModel(eTipoColumna.values());
        this.comboTipoColumna5.setModel(modelTipo5);
    }
    
    private void mostrarColumnas(){
        for(Columna c : columnasAAgregar)
        {
            modelTabla.addColumn(c.getNombre()+ " (" + c.getTipo().name() + ")");
        }
    }
    
    /*Verifica que los nombres no esten repetidos ni se encuentren dentro de la tabla
    que se esta creando, en caso de que la tabla aun no se haya creado, solo verifica
    lo primero, los datos se almacenan en al variable columnasAAgregar*/
    private Mensaje cargarColumnas(){
        Mensaje respuesta = new Mensaje("", false);
        ArrayList<Columna> columnasAAgregarAux = new ArrayList<Columna>();
        
        Columna columna1 = generarColumna(this.txtNombreColumna1, this.comboTipoColumna1, this.chkNulleableColumna1);
        Columna columna2 = generarColumna(this.txtNombreColumna2, this.comboTipoColumna2, this.chkNulleableColumna2);
        Columna columna3 = generarColumna(this.txtNombreColumna3, this.comboTipoColumna3, this.chkNulleableColumna3);
        Columna columna4 = generarColumna(this.txtNombreColumna4, this.comboTipoColumna4, this.chkNulleableColumna4);
        Columna columna5 = generarColumna(this.txtNombreColumna5, this.comboTipoColumna5, this.chkNulleableColumna5);
        
        columnasAAgregarAux.add(columna1);
        columnasAAgregarAux.add(columna2);
        columnasAAgregarAux.add(columna3);
        columnasAAgregarAux.add(columna4);
        columnasAAgregarAux.add(columna5);
        
        for(Columna c : columnasAAgregarAux)
        {
            if(c == null)
            {
                continue;
            }
            if(nombreColumnaRepetido(c.getNombre()))
            {
                respuesta.setMensaje("Verifique que no hayan columnas repetidas en la tabla");
                return respuesta;
            }
            columnasAAgregar.add(c);
        }
        
        respuesta.setExito(true);
        return respuesta;
    }
    
    private Columna generarColumna(JTextField campoTexto, JComboBox<eTipoColumna> comboBox, Checkbox check)
    {
        String nombreColumna = campoTexto.getText().toUpperCase();
        if(nombreColumna.isEmpty())
        {
            return null;
        }
        eTipoColumna tipoColumna = comboBox.getModel().getElementAt(comboBox.getSelectedIndex());
        boolean nulleableColumna = check.getState();
        return new Columna(nombreColumna, tipoColumna, nulleableColumna);
    }
    
    private boolean nombreColumnaRepetido(String nombre)
    {
        for(Columna c : columnasAAgregar)
        {
            if(c.getNombre().toUpperCase().equals(nombre.toUpperCase()))
            {
                return true;
            }
        }
        return false;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCrear;
    private java.awt.Checkbox chkNulleableColumna1;
    private java.awt.Checkbox chkNulleableColumna2;
    private java.awt.Checkbox chkNulleableColumna3;
    private java.awt.Checkbox chkNulleableColumna4;
    private java.awt.Checkbox chkNulleableColumna5;
    private javax.swing.JComboBox<eTipoColumna> comboTipoColumna1;
    private javax.swing.JComboBox<eTipoColumna> comboTipoColumna2;
    private javax.swing.JComboBox<eTipoColumna> comboTipoColumna3;
    private javax.swing.JComboBox<eTipoColumna> comboTipoColumna4;
    private javax.swing.JComboBox<eTipoColumna> comboTipoColumna5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNombreTabla;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tablaBase;
    private javax.swing.JTextField txtNombreColumna1;
    private javax.swing.JTextField txtNombreColumna2;
    private javax.swing.JTextField txtNombreColumna3;
    private javax.swing.JTextField txtNombreColumna4;
    private javax.swing.JTextField txtNombreColumna5;
    private javax.swing.JTextField txtNombreTabla;
    // End of variables declaration//GEN-END:variables
}
