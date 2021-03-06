/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Sibre.Telas;

import br.com.Sibre.DAL.ModuloConexao;
import br.com.Sibre.Telas.Menu;
import java.awt.Color;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author t69779848134
 */
public class TelaLogin extends javax.swing.JFrame {

    ModuloConexao conexao = new ModuloConexao();
    PreparedStatement pst;
    ResultSet rs;

    public void Logar() {
        String sql = "select * from acesso where login=? and senha=?";
        try {
            pst = conexao.conn.prepareStatement(sql);
            pst.setString(1, txtUsu.getText());
            pst.setString(2, txtSenha.getText());
            rs = pst.executeQuery();

            if (rs.next()) {
                String perfil = rs.getString(2);
                System.out.println(perfil);
                // a linha abaixo faz o tratamento do perfil do usuario   
                           
                if (perfil.equals("admin")) {
                    Menu cad = new Menu();
                    cad.setVisible(true);
                    Menu.MenCad.setEnabled(true);
                    Menu.MenRel.setEnabled(true);
                    Menu.MenFami.setEnabled(true);
                    Menu.MenInv.setEnabled(true);
                    Menu.MenuRelMem.setEnabled(true);
                    Menu.MenRelFamilia.setEnabled(true);
                    Menu.MenuRelInventario.setEnabled(true);
                    Menu.lblUsuario.setText(rs.getString(4));
                    Menu.lblUsuario.setForeground(Color.red);
                    this.dispose();
                    
                } else {
                    Menu cad = new Menu();
                    cad.setVisible(true);
                    Menu.MenCad.setEnabled(false);
                    Menu.MenRel.setEnabled(true);
                    Menu.lblUsuario.setText(rs.getString(4));
                    Menu.lblUsuario.setForeground(Color.black);  
                    this.dispose();
                }
            
            } else {
                JOptionPane.showMessageDialog(null, "Usuári e/ou senha invalida");
            }
        } catch (Exception e) {
           // JOptionPane.showMessageDialog(null, "O erro é aqui na tela Login - " + e);
        }
    }

//    private static String md5(String senha) {
//        String sen = "";
//        MessageDigest md = null;
//        try {
//            md = MessageDigest.getInstance("MD5");
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
//        sen = hash.toString(16);
//        return sen;
//    }

    /**
     * Creates new form TelaLogin
     */
    public TelaLogin() {
        initComponents();
        conexao.conector();
        if (conexao != null) {
            lblStatus.setText("Conectado");
        } else {
            lblStatus.setText("Não Conectado");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblStatus = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsu = new javax.swing.JTextField();
        txtSenha = new javax.swing.JPasswordField();
        btnLogar = new javax.swing.JButton();
        BtnLogCancelar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tela de Login");
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(177, 191, 177));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblStatus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblStatus.setText("Status");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Usuário");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Senha");

        txtUsu.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N

        txtSenha.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N

        btnLogar.setBackground(new java.awt.Color(204, 204, 204));
        btnLogar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLogar.setText("Logar");
        btnLogar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnLogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogarActionPerformed(evt);
            }
        });

        BtnLogCancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BtnLogCancelar.setText("Cancelar");
        BtnLogCancelar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnLogCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLogCancelarActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(123, 182, 240));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("SEGUNDA IGREJA BATISTA NO RECANTO DAS EMAS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 37, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 11, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addGap(0, 12, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtSenha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblStatus)
                                    .addComponent(jLabel2)
                                    .addComponent(txtUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(116, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnLogar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnLogCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(124, 124, 124))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnLogCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(578, 281));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogarActionPerformed
         Logar();
    }//GEN-LAST:event_btnLogarActionPerformed

    private void BtnLogCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLogCancelarActionPerformed
       System.exit(0);
    }//GEN-LAST:event_BtnLogCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnLogCancelar;
    private javax.swing.JButton btnLogar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtUsu;
    // End of variables declaration//GEN-END:variables
}
