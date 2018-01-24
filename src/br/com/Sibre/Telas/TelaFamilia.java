/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Sibre.Telas;

import java.sql.*;
import br.com.Sibre.DAL.ModuloConexao;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import br.com.Sibre.Familia.BLLFamilia;
import static br.com.Sibre.Telas.FichaFamiliar.FichaId;
import static br.com.Sibre.Telas.FichaFamiliar.FichaNome;
import static br.com.Sibre.Telas.FichaFamiliar.FichaEnder;
import static br.com.Sibre.Telas.FichaFamiliar.FichaFone;
import static br.com.Sibre.Telas.FichaFamiliar.FichaPosi;
import static br.com.Sibre.Telas.FichaFamiliar.FichaProf;
import static br.com.Sibre.Telas.FichaFamiliar.FichaConge;
import static br.com.Sibre.Telas.FichaFamiliar.FichaEst;
import static br.com.Sibre.Telas.FichaFamiliar.FichaEstatus;
import static br.com.Sibre.Telas.FichaFamiliar.FichaNdF;
import static br.com.Sibre.Telas.FichaFamiliar.FichaLbl;
import static br.com.Sibre.Telas.FichaFamiliar.FichaCEP;
import static br.com.Sibre.Telas.FichaFamiliar.FichaEmail;
import static br.com.Sibre.Telas.FichaFamiliar.FichaAcademica;
import static br.com.Sibre.Telas.FichaFamiliar.FichaNasc;
import static br.com.Sibre.Telas.FichaFamiliar.LblBatizado;

import java.awt.Image;
import java.io.FileOutputStream;
import javax.swing.ImageIcon;

/**
 *
 * @author ernil
 */
public class TelaFamilia extends javax.swing.JInternalFrame {

    ModuloConexao conexao = new ModuloConexao();
    PreparedStatement pst;
    ResultSet rs;

    int flag = 1;

//select Cad.id_cad,nome,ender, Fam.estado_civil,posicao,profissao,conge,cpff,nome_filhos,funcao_minis,nive_escolar from familia as Fam inner join cadastro as Cad on (Cad.cpf = Fam.cpff) where cpf like?
    public void pesquisaFamilia() {
        conexao.conector();
        String sql = "select id_cad,nome,ender,cep,fone,email, cpf from cadastro where nome like ?";
        try {
            pst = conexao.conn.prepareStatement(sql);
            pst.setString(1, FamPesq.getText() + "%");
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar
            FamTable.setModel(DbUtils.resultSetToTableModel(rs));            
       
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void setarFamilia() {
        int setar = FamTable.getSelectedRow();
        FamId.setText(FamTable.getModel().getValueAt(setar, 0).toString());
        FamNome.setText(FamTable.getModel().getValueAt(setar, 1).toString());
        FamCPF.setText(FamTable.getModel().getValueAt(setar, 6).toString());

    }

    public void pesquisaFamilia2() {
        conexao.conector();
        String sql = "select id_fam, estado_civil,posicao,profissao,conge,cpff,nome_filhos,funcao_minis,nive_escolar from familia where cpff like '%" + FamPesq.getText() + "%'";
        try {
            pst = conexao.conn.prepareStatement(sql);
           // pst.setString(1, FamPesq.getText() + "%");
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar
            FamTable.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void setarFamilia2() {
        int setar = FamTable.getSelectedRow();
        FamId.setText(FamTable.getModel().getValueAt(setar, 0).toString());
        FamPosi.setText(FamTable.getModel().getValueAt(setar, 2).toString());
        FamProf.setText(FamTable.getModel().getValueAt(setar, 3).toString());
        FamConge.setText(FamTable.getModel().getValueAt(setar, 1).toString());
        FamNomeFilhos.setText(FamTable.getModel().getValueAt(setar, 6).toString());
        FamCPF.setText(FamTable.getModel().getValueAt(setar, 5).toString());
        FamEsc.setText(FamTable.getModel().getValueAt(setar, 8).toString());
        FamMinist.setText(FamTable.getModel().getValueAt(setar, 7).toString());
       

    }
    

    public void pesquisaCPF(String sql) {
        conexao.conector();
        try {
            pst = conexao.conn.prepareStatement(sql);
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar                  
            FamTable.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //
    //select Cad.id_cad,nome,ender,cep,fone,email,estatus,foto_img, Fam.estado_civil,posicao,profissao,conge,nome_filhos from familia as Fam inner join cadastro as Cad on (Cad.cpf = Fam.cpff) where cpf like
    public void chamar() {
        FichaFamiliar ficha = new FichaFamiliar();
        ficha.setVisible(true);
        conexao.conector();
        try {
            conexao.executaSQL("select Cad.id_cad,nome,ender,cep,fone,email,data_nasc,estatus,foto_img, Batismo, Fam.estado_civil,posicao,profissao,conge,nome_filhos,funcao_minis,nive_escolar from familia as Fam inner join cadastro as Cad on (Cad.cpf = Fam.cpff) where cpf like '%" + FamCPF.getText() + "%'");
            conexao.rs.first();
            FichaId.setText(String.valueOf(conexao.rs.getInt("id_cad")));
            FichaNome.setText(conexao.rs.getString("nome"));
            FichaEnder.setText(conexao.rs.getString("ender"));
            FichaCEP.setText(conexao.rs.getString("cep"));
            FichaFone.setText(conexao.rs.getString("fone"));
            FichaEmail.setText(conexao.rs.getString("email"));
            FichaEstatus.setText(conexao.rs.getString("estatus"));
            FichaEst.setText(conexao.rs.getString("estado_civil"));
            FichaPosi.setText(conexao.rs.getString("posicao"));
            FichaProf.setText(conexao.rs.getString("profissao"));
            FichaConge.setText(conexao.rs.getString("conge"));
            FichaNdF.setText(conexao.rs.getString("nome_filhos"));
            FichaAcademica.setText(conexao.rs.getString("nive_escolar"));
            LblBatizado.setText(conexao.rs.getString("Batismo"));
            FichaLbl.setText(conexao.rs.getString("foto_img"));
            conexao.rs.beforeFirst();
            while (conexao.rs.next()) {
                byte[] imgBytes = ((byte[]) conexao.rs.getBytes("foto_img"));
                ImageIcon pic = null;
                pic = new ImageIcon(imgBytes);
                Image scaled = pic.getImage().getScaledInstance(FichaLbl.getWidth(), FichaLbl.getHeight(), Image.SCALE_DEFAULT);
                ImageIcon icon = new ImageIcon(scaled);
                FichaLbl.setIcon(icon);//Adapta o tamanho da img para ficar do tamanho da label
                //jl.setIcon(pic);
                FileOutputStream fos;
                String nome_foto = "teste";
            }
        } catch (Exception ex) {
            System.out.println("Erro " + ex);
        }

    }

    public void Limpar() {
        FamNome.setText(null);
        FamId.setText(null);
        FamPesq.setText(null);
        FamPosi.setText(null);
        FamProf.setText(null);
        FamConge.setText(null);
        FamMinist.setText(null);
        FamEsc.setText(null);
        FamCBO.setSelectedItem(null);
        FamNomeFilhos.setText(null);
        FamCPF.setText(null);
    }

    /**
     * Creates new form TelaFamilia
     */
    public TelaFamilia() {
        initComponents();
    }
    BLLFamilia bll = new BLLFamilia();

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        BtnFnov = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        FamNomeFilhos = new javax.swing.JTextArea();
        BtnAterarF = new javax.swing.JButton();
        BtnFdel = new javax.swing.JButton();
        FamConge = new javax.swing.JTextField();
        FamPosi = new javax.swing.JTextField();
        FamProf = new javax.swing.JTextField();
        FamCBO = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        BtnBusca = new javax.swing.JButton();
        FamCPF = new javax.swing.JTextField();
        FamId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        BtnFadic = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        FamTable = new javax.swing.JTable();
        FamPesq = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        FamNome = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        FamEsc = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        FamMinist = new javax.swing.JTextField();
        BtnSetarF2 = new javax.swing.JButton();
        BtnPesquisa = new javax.swing.JButton();

        setBackground(new java.awt.Color(227, 220, 220));
        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Tela Familia");
        setToolTipText("");
        setEnabled(false);
        setMaximumSize(new java.awt.Dimension(1130, 614));
        setPreferredSize(new java.awt.Dimension(1130, 614));

        jPanel1.setBackground(new java.awt.Color(136, 174, 193));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        BtnFnov.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BtnFnov.setText("NOVO");
        BtnFnov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnFnovActionPerformed(evt);
            }
        });

        FamNomeFilhos.setColumns(20);
        FamNomeFilhos.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        FamNomeFilhos.setRows(5);
        jScrollPane2.setViewportView(FamNomeFilhos);

        BtnAterarF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BtnAterarF.setText("Alterar");
        BtnAterarF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAterarFActionPerformed(evt);
            }
        });

        BtnFdel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BtnFdel.setText("Deletar");
        BtnFdel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnFdelActionPerformed(evt);
            }
        });

        FamConge.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        FamPosi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        FamProf.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        FamCBO.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        FamCBO.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Casado(a)", "Solteiro(a)", "Desquitado(a)", "União Estavel", "Outros" }));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Estado Civil:");

        BtnBusca.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BtnBusca.setText("Busca");
        BtnBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscaActionPerformed(evt);
            }
        });

        FamCPF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        FamId.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        FamId.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Id:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("CPF:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Nome dos Filhos:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Conge:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Posição Familiar:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Profissão:");

        BtnFadic.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BtnFadic.setText("Adionar");
        BtnFadic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnFadicActionPerformed(evt);
            }
        });

        FamTable.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        FamTable.setModel(new javax.swing.table.DefaultTableModel(
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
        FamTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FamTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(FamTable);

        FamPesq.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        FamPesq.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                FamPesqKeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Eu e minha casa Serviremos ao Senhor!");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Josué 24:15");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Nome:");

        FamNome.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Nivel Escolar:");

        FamEsc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Função Ministerial:");

        FamMinist.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        BtnSetarF2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BtnSetarF2.setText("Setar");
        BtnSetarF2.setToolTipText("Preenchemento dos campos");
        BtnSetarF2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnSetarF2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSetarF2ActionPerformed(evt);
            }
        });

        BtnPesquisa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BtnPesquisa.setText("Pesquisa");
        BtnPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPesquisaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(139, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel8))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BtnPesquisa)
                            .addComponent(jLabel1))
                        .addGap(8, 8, 8))
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BtnFadic)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(567, 567, 567)
                                .addComponent(jLabel10)
                                .addGap(92, 92, 92))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(BtnFdel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 397, Short.MAX_VALUE)
                                .addComponent(jLabel11))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(FamCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BtnBusca)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(FamEsc))
                            .addComponent(FamConge, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
                            .addComponent(FamProf)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                            .addComponent(FamPesq)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(FamId, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(134, 134, 134)
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(FamMinist))
                            .addComponent(FamNome)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(FamPosi, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addGap(36, 36, 36)
                                .addComponent(FamCBO, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(BtnAterarF)
                                    .addGap(37, 37, 37)
                                    .addComponent(BtnFnov))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(54, 54, 54)
                        .addComponent(BtnSetarF2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(FamPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnPesquisa))
                    .addComponent(BtnSetarF2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(FamId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(FamMinist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(FamNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(FamPosi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(FamCBO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(FamProf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(FamConge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(FamCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(BtnBusca)
                            .addComponent(jLabel13)
                            .addComponent(FamEsc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 9, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel8)
                                .addGap(8, 8, 8)
                                .addComponent(jLabel10)))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BtnFadic, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnFdel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnAterarF, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnFnov, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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

        setBounds(0, 0, 1130, 614);
    }// </editor-fold>//GEN-END:initComponents

    private void FamPesqKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FamPesqKeyReleased
        pesquisaFamilia();
        FamTable.setVisible(true);
        FamNome.setVisible(true);

    }//GEN-LAST:event_FamPesqKeyReleased

    private void FamTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FamTableMouseClicked
        if (flag == 1) {
            setarFamilia();
        } else {
            setarFamilia2();
        }

        //BtnFadic.setEnabled(false);
    }//GEN-LAST:event_FamTableMouseClicked

    private void BtnFadicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnFadicActionPerformed
        // int id = Integer.parseInt(FamId.getText());
        String estado = this.FamCBO.getSelectedItem().toString();
        String posicao = this.FamPosi.getText();
        String profissao = this.FamProf.getText();
        String conge = this.FamConge.getText();
        String filhos = this.FamNomeFilhos.getText();
        String cpf = this.FamCPF.getText();
        String funcao_minist = this.FamMinist.getText();
        String nivel_esc = this.FamEsc.getText();

        bll.AdicionaFamilia(estado, posicao, profissao, conge, filhos, cpf, funcao_minist, nivel_esc);
        Limpar();
        FamTable.setVisible(false);
    }//GEN-LAST:event_BtnFadicActionPerformed

    private void BtnFdelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnFdelActionPerformed
        int id = Integer.parseInt(FamId.getText());
        String estado = this.FamCBO.getSelectedItem().toString();
        String posicao = this.FamPosi.getText();
        String profissao = this.FamProf.getText();
        String conge = this.FamConge.getText();
        String filhos = this.FamNomeFilhos.getText();
        String cpf = this.FamCPF.getText();

        bll.Excluir(id, estado, posicao, profissao, conge, filhos, cpf);
        Limpar();
        FamTable.setVisible(false);
    }//GEN-LAST:event_BtnFdelActionPerformed

    private void BtnAterarFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAterarFActionPerformed
        int id = Integer.parseInt(FamId.getText());
        String nome = this.FamNome.getText();
        String estado = this.FamCBO.getSelectedItem().toString();
        String posicao = this.FamPosi.getText();
        String profissao = this.FamProf.getText();
        String conge = this.FamConge.getText();
        String filhos = this.FamNomeFilhos.getText();
        String cpf = this.FamCPF.getText();
        String funcao_minist = this.FamMinist.getText();
        String nivel_esc = this.FamEsc.getText();

        bll.Alterar(id, nome, estado, posicao, profissao, conge, filhos, cpf, funcao_minist, nivel_esc);
        Limpar();
        FamTable.setVisible(false);
    }//GEN-LAST:event_BtnAterarFActionPerformed

    private void BtnFnovActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnFnovActionPerformed
        Limpar();
        FamTable.setVisible(false);
        
        BtnFadic.setEnabled(true);
    }//GEN-LAST:event_BtnFnovActionPerformed

    private void BtnBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscaActionPerformed

        chamar();
        //pesquisaCPF("select Cad.nome,estatus, Fam.estado_civil,posicao,profissao,conge,filhos from familia as Fam inner join cadastro as Cad where Cad.id_cad = Fam.cpf like '%" + FamCPF.getText() + "%'");
    }//GEN-LAST:event_BtnBuscaActionPerformed

    private void BtnSetarF2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSetarF2ActionPerformed
        FamTable.setVisible(true);
        pesquisaFamilia2();
        BtnFadic.setEnabled(true);
        FamNome.setVisible(false);
        flag = 2;
    }//GEN-LAST:event_BtnSetarF2ActionPerformed

    private void BtnPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPesquisaActionPerformed
         FamTable.setVisible(true);
        pesquisaFamilia();
        FamNome.setVisible(true);
        flag = 1;
    }//GEN-LAST:event_BtnPesquisaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAterarF;
    private javax.swing.JButton BtnBusca;
    private javax.swing.JButton BtnFadic;
    private javax.swing.JButton BtnFdel;
    private javax.swing.JButton BtnFnov;
    private javax.swing.JButton BtnPesquisa;
    private javax.swing.JButton BtnSetarF2;
    private javax.swing.JComboBox<String> FamCBO;
    public static javax.swing.JTextField FamCPF;
    private javax.swing.JTextField FamConge;
    private javax.swing.JTextField FamEsc;
    private javax.swing.JTextField FamId;
    private javax.swing.JTextField FamMinist;
    private javax.swing.JTextField FamNome;
    private javax.swing.JTextArea FamNomeFilhos;
    private javax.swing.JTextField FamPesq;
    private javax.swing.JTextField FamPosi;
    private javax.swing.JTextField FamProf;
    public static javax.swing.JTable FamTable;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
