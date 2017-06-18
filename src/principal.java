///////-------------------------------------------------------------------------------
/////// Name:        Examen conocimiento general
/////// Copyright:   (c), Emmanuel Alcacio - 2017
///////-------------------------------------------------------------------------------

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Formatter;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.table.DefaultTableModel;

public class principal extends javax.swing.JFrame {

    String barra = File.separator;
    String ubicacion = System.getProperty("user.dir") + barra + "registros" + barra;

    File contenedor = new File(ubicacion);
    File[] registros = contenedor.listFiles();

    String[] titulos = {"ID", "Marca", "Linea", "Color", "Modelo"};
    DefaultTableModel dtm = new DefaultTableModel(null, titulos);

    public principal() {
        initComponents();
        //setIconImage(new ImageIcon(getClass().getResource("/imagenes/mario.png")).getImage());
        setLocationRelativeTo(null);
        mostrarcombo();
        regtabla();
    }

    private void actualizartabla() {
        registros = contenedor.listFiles();
        dtm.setRowCount(0);
        regtabla();
    }

    private void regtabla() {
        for (int i = 0; i < registros.length; i++) {
            File url = new File(ubicacion + registros[i].getName());
            try {
                FileInputStream fis = new FileInputStream(url);
                Properties mostrar = new Properties();
                mostrar.load(fis);
                String filas[] = {registros[i].getName().replace(".registros", ""),
                    mostrar.getProperty("Marca"),
                    mostrar.getProperty("Linea"),
                    mostrar.getProperty("Color"),
                    mostrar.getProperty("Modelo")
                };
                dtm.addRow(filas);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Error al mostrar tabla" + e);
            }
        }
        tblregistros.setModel(dtm);
    }

    private void mostrarcombo() {
        for (int i = 0; i < registros.length; i++) {
            comboregistros.addItem(registros[i].getName().replace(".registros", ""));
        }
    }

    private void Crear() {
        String archivo = txtid.getText() + ".registros";
        File crea_ubicacion = new File(ubicacion);
        File crea_archivo = new File(ubicacion + archivo);
        if (txtid.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "No Existe el ID");
        } else {
            try {
                if (crea_archivo.exists()) {
                    JOptionPane.showMessageDialog(rootPane, "El registro ya existe");
                } else {
                    crea_ubicacion.mkdirs();
                    Formatter crear = new Formatter(ubicacion + archivo);
                    crear.format("%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n",
                            "ID=" + txtid.getText(),
                            "Marca=" + txtmarca.getText(),
                            "Linea=" + txtlinea.getText(),
                            "Color=" + txtcolor.getText(),
                            "Modelo=" + txtmodelo.getText());
                    crear.close();
                    JOptionPane.showMessageDialog(rootPane, "El archivo se creo correctamente");
                    comboregistros.removeAllItems();
                    registros = contenedor.listFiles();
                    mostrarcombo();
                    actualizartabla();

                }
            } catch (Exception e) {
                //   JOptionPane.showMessageDialog(rootPane, "Error, El archivo no se genero" + e);
            }
        }
    }

    private void Mostrar() {
        File url = new File(ubicacion + txtid.getText() + ".registros");
        if (txtid.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Escribe un id");
        } else {
            if (url.exists()) {
                try {
                    FileInputStream fis = new FileInputStream(url);
                    Properties mostrar = new Properties();
                    mostrar.load(fis);
                    txtid.setText(mostrar.getProperty("ID"));
                    txtmarca.setText(mostrar.getProperty("Marca"));
                    txtlinea.setText(mostrar.getProperty("Linea"));
                    txtcolor.setText(mostrar.getProperty("Color"));
                    txtmodelo.setText(mostrar.getProperty("Modelo"));
                } catch (Exception e) {
                }
            }
        }
    }

    private void Editar() {
        File url = new File(ubicacion + txtid.getText() + ".registros");
        if (txtid.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Indique el registro a editar");
        } else {
            if (url.exists()) {
                try {
                    FileWriter permite_escrito = new FileWriter(ubicacion + txtid.getText() + (".registros"));
                    String id = "ID";
                    String marca = "Marca";
                    String linea = "Linea";
                    String color = "Color";
                    String modelo = "Modelo";
                    PrintWriter guardar = new PrintWriter(permite_escrito);
                    guardar.println(id + txtid.getText());
                    guardar.println(marca + txtmarca.getText());
                    guardar.println(linea + txtlinea.getText());
                    guardar.println(color + txtcolor.getText());
                    guardar.println(modelo + txtmodelo.getText());
                    permite_escrito.close();
                    JOptionPane.showMessageDialog(rootPane, "Registro editado correctamente");
                    actualizartabla();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(rootPane, "Error " + e);
                }
            }
        }
    }

    private void Eliminar() {
        File url = new File(ubicacion + txtid.getText() + ".registros");
        String btns[] = {"Eliminar", "Cancelar"};
        if (txtid.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Indique el registro a editar");
        } else {
            if (url.exists()) {
                try {
                    FileInputStream cerrar = new FileInputStream(url);
                    cerrar.close();
                    System.gc();
                    int seguro = JOptionPane.showOptionDialog(rootPane, "¿Estas seguro?", "Eliminar registro", 0, 0, null, btns, null);
                    if (seguro == JOptionPane.YES_OPTION) {
                        url.delete();
                        JOptionPane.showMessageDialog(rootPane, "Registro eliminado");
                        comboregistros.removeItem(txtid.getText());
                        actualizartabla();

                    }
                    if (seguro == JOptionPane.NO_OPTION) {
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(rootPane, "Error " + e);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lblid = new javax.swing.JLabel();
        lbllinea = new javax.swing.JLabel();
        lblmarca = new javax.swing.JLabel();
        lblmodelo = new javax.swing.JLabel();
        lblcolor = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        txtmarca = new javax.swing.JTextField();
        txtlinea = new javax.swing.JTextField();
        txtcolor = new javax.swing.JTextField();
        txtmodelo = new javax.swing.JTextField();
        btnregistrar = new javax.swing.JButton();
        btnmostrar = new javax.swing.JButton();
        btneditar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        comboregistros = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblregistros = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registro Vehicular");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setIconImages(null);
        setUndecorated(true);
        setResizable(false);

        lblid.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 11)); // NOI18N
        lblid.setForeground(new java.awt.Color(0, 0, 204));
        lblid.setText("ID");

        lbllinea.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 11)); // NOI18N
        lbllinea.setForeground(new java.awt.Color(0, 0, 204));
        lbllinea.setText("Linea");

        lblmarca.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 11)); // NOI18N
        lblmarca.setForeground(new java.awt.Color(0, 0, 204));
        lblmarca.setText("Marca");

        lblmodelo.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 11)); // NOI18N
        lblmodelo.setForeground(new java.awt.Color(0, 0, 204));
        lblmodelo.setText("Modelo");

        lblcolor.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 11)); // NOI18N
        lblcolor.setForeground(new java.awt.Color(0, 0, 204));
        lblcolor.setText("Color");

        btnregistrar.setBackground(new java.awt.Color(0, 0, 255));
        btnregistrar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnregistrar.setForeground(new java.awt.Color(0, 255, 0));
        btnregistrar.setText("Registrar");
        btnregistrar.setBorderPainted(false);
        btnregistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregistrarActionPerformed(evt);
            }
        });

        btnmostrar.setBackground(new java.awt.Color(0, 0, 204));
        btnmostrar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnmostrar.setForeground(new java.awt.Color(0, 255, 0));
        btnmostrar.setText("Mostrar");
        btnmostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmostrarActionPerformed(evt);
            }
        });

        btneditar.setBackground(new java.awt.Color(0, 0, 204));
        btneditar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btneditar.setForeground(new java.awt.Color(0, 255, 0));
        btneditar.setText("Editar");
        btneditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditarActionPerformed(evt);
            }
        });

        btneliminar.setBackground(new java.awt.Color(0, 0, 204));
        btneliminar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btneliminar.setForeground(new java.awt.Color(0, 255, 0));
        btneliminar.setText("Eliminar");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        btnsalir.setBackground(new java.awt.Color(0, 0, 153));
        btnsalir.setForeground(new java.awt.Color(0, 255, 0));
        btnsalir.setText("Salir");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        comboregistros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboregistrosActionPerformed(evt);
            }
        });

        tblregistros.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 11)); // NOI18N
        tblregistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblregistros.setGridColor(new java.awt.Color(0, 153, 153));
        tblregistros.setSelectionBackground(new java.awt.Color(0, 102, 153));
        jScrollPane2.setViewportView(tblregistros);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cesba.png"))); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/marioo.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblmodelo, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblcolor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbllinea, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblmarca, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtcolor, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtmodelo, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtmarca, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnmostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtlinea, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblid, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(comboregistros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnregistrar)))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboregistros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblid)
                    .addComponent(btnregistrar))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmarca)
                    .addComponent(txtmarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnmostrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btneditar)
                        .addComponent(txtlinea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbllinea, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btneliminar)
                    .addComponent(txtcolor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblcolor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmodelo)
                    .addComponent(txtmodelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsalir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("Registro de autos");
        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnregistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregistrarActionPerformed
        Crear();
    }//GEN-LAST:event_btnregistrarActionPerformed

    private void btnmostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmostrarActionPerformed
        Mostrar();
    }//GEN-LAST:event_btnmostrarActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
        Editar();
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        System.exit(0); //matar aplicacion
        // dispose(); salir unicamente de ventanas
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        Eliminar();
    }//GEN-LAST:event_btneliminarActionPerformed

    private void comboregistrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboregistrosActionPerformed
        String copiar = (String) comboregistros.getSelectedItem();
        txtid.setText(copiar);
        Mostrar();
    }//GEN-LAST:event_comboregistrosActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnmostrar;
    private javax.swing.JButton btnregistrar;
    private javax.swing.JButton btnsalir;
    private javax.swing.JComboBox<String> comboregistros;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblcolor;
    private javax.swing.JLabel lblid;
    private javax.swing.JLabel lbllinea;
    private javax.swing.JLabel lblmarca;
    private javax.swing.JLabel lblmodelo;
    private javax.swing.JTable tblregistros;
    private javax.swing.JTextField txtcolor;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtlinea;
    private javax.swing.JTextField txtmarca;
    private javax.swing.JTextField txtmodelo;
    // End of variables declaration//GEN-END:variables

    private void setIconImage(ImageIcon imageIcon) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
