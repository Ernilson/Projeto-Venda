/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Sibre.Telas;

import Utils.ModeloTabela;
import br.com.Sibre.DAL.ModuloConexao;
import br.com.Sibre.Cadastro.CadastroBLL;
import java.sql.*;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import br.com.Sibre.Telas.Menu;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.ListSelectionModel;

/**
 *
 * @author t69779848134
 */
public class TelaCadastro extends javax.swing.JInternalFrame {

    ModuloConexao conexao = new ModuloConexao();
    //Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public void pesquisa() {
        conexao.conector();
        String sql = "select id_cad as Id, nome as Nome, ender as Endereço, cep as CEP,fone as Telefone,email as Email, cpf as CPF, data_nasc as Data_de_Nasc, estatus as Estatus, perfil as Perfil,batismo as Batismo,genero as Genero from cadastro where id_cad like ?";

        try {
            pst = conexao.conn.prepareStatement(sql);
            pst.setString(1, CadPesq.getText() + "%");
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar
            CadTable.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
public void preencherTabela(String SQL) {
        ArrayList dados = new ArrayList();
        conexao.conector();
        String[] Colunas = new String[]{"NOME", "ENDEREÇO", "CEP","TELEFONE","EMAIL","CPF","NASCINT","STATUS","PERFIL","BATISMO"};

        conexao.executaSQL(SQL);
        try {
          // pst = conexao.conn.prepareStatement(sql);
            pst.setString(1, CadPesq.getText() + "%");
            rs = pst.executeQuery();
            //dados.add(new Object[]{conexao.rs.getInt("id_ped"), conexao.rs.getString("nome"), conexao.rs.getString("descricao"), conexao.rs.getString("datap")});
            do {
                dados.add(new Object[]{conexao.rs.getString("nome"), conexao.rs.getString("ender"), conexao.rs.getString("cep"), conexao.rs.getString("fone"), conexao.rs.getString("email"), conexao.rs.getString("cpf"), conexao.rs.getString("data_nasc"), conexao.rs.getString("estatus"), conexao.rs.getString("perfil"), conexao.rs.getString("batismo")});
            } while (conexao.rs.next());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher a tabela " + e);
        }
        ModeloTabela tabela = new ModeloTabela(dados, Colunas);
        CadTable.setModel(tabela);
        CadTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        CadTable.getColumnModel().getColumn(0).setResizable(false);
        CadTable.getColumnModel().getColumn(1).setPreferredWidth(180);
        CadTable.getColumnModel().getColumn(1).setResizable(false);
        CadTable.getColumnModel().getColumn(2).setPreferredWidth(40);
        CadTable.getColumnModel().getColumn(2).setResizable(false);
        CadTable.getColumnModel().getColumn(3).setPreferredWidth(50);
        CadTable.getColumnModel().getColumn(3).setResizable(false);
        CadTable.getColumnModel().getColumn(4).setPreferredWidth(90);
        CadTable.getColumnModel().getColumn(4).setResizable(false);
        CadTable.getColumnModel().getColumn(5).setPreferredWidth(80);
        CadTable.getColumnModel().getColumn(5).setResizable(false);
        CadTable.getColumnModel().getColumn(6).setPreferredWidth(50);
        CadTable.getColumnModel().getColumn(6).setResizable(false);
        CadTable.getColumnModel().getColumn(7).setPreferredWidth(50);
        CadTable.getColumnModel().getColumn(7).setResizable(false);
        CadTable.getColumnModel().getColumn(8).setPreferredWidth(70);
        CadTable.getColumnModel().getColumn(8).setResizable(false);
        CadTable.getColumnModel().getColumn(9).setPreferredWidth(40);
        CadTable.getColumnModel().getColumn(9).setResizable(false);
        CadTable.getTableHeader().setReorderingAllowed(false);
        CadTable.setAutoResizeMode(CadTable.AUTO_RESIZE_OFF);
        CadTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    private void setar() {
        conexao.conector();
        conexao.executaSQL("SELECT * FROM cadastro where id_cad like '%" + CadId.getText() + "%'");

        int setar = CadTable.getSelectedRow();
        CadId.setText(CadTable.getModel().getValueAt(setar, 0).toString());
        CadNome.setText(CadTable.getModel().getValueAt(setar, 1).toString());
        CadEnder.setText(CadTable.getModel().getValueAt(setar, 2).toString());
        CadCEP.setText(CadTable.getModel().getValueAt(setar, 3).toString());
        CadFone.setText(CadTable.getModel().getValueAt(setar, 4).toString());
        CadEmail.setText(CadTable.getModel().getValueAt(setar, 5).toString());
        CadCPF.setText(CadTable.getModel().getValueAt(setar, 6).toString());
        CadNasc.setText(CadTable.getModel().getValueAt(setar, 7).toString());
        CboPerfil.setSelectedItem(CadTable.getModel().getValueAt(setar,8).toString());
        CboEst.setSelectedItem(CadTable.getModel().getValueAt(setar,9).toString());
        CboBatismo.setSelectedItem(CadTable.getModel().getValueAt(setar,10).toString());
        try {
            conexao.rs.beforeFirst();
            //conexao.rs.afterLast();
            while (conexao.rs.next()) {
                byte[] imgBytes = ((byte[]) conexao.rs.getBytes("foto_img"));
                ImageIcon pic = null;
                pic = new ImageIcon(imgBytes);
                Image scaled = pic.getImage().getScaledInstance(LblCadIm.getWidth(), LblCadIm.getHeight(), Image.SCALE_DEFAULT);
                ImageIcon icon = new ImageIcon(scaled);
                LblCadIm.setIcon(icon);//Adapta o tamanho da img para ficar do tamanho da label
                //jl.setIcon(pic);
                FileOutputStream fos;
                String nome_foto = "teste";
            }
        } catch (Exception ex) {
            System.out.println("Erro " + ex);
        }

        // a linha a baixo desabilita o butao adicionar
       btnSalvar.setEnabled(false);
    }

    private void limpar() {
        CadTable.setVisible(false);
        CadId.setText(null);
        CadNome.setText(null);
        CadEnder.setText(null);
        CadFone.setText(null);
        CadNasc.setText(null);
        CadCPF.setText(null);
        CadEmail.setText(null);
        CadCEP.setText(null);
        CboPerfil.setSelectedItem(null);
        CboEst.setSelectedItem(null);
        CboBatismo.setSelectedItem(null);
        CadGen.setSelectedItem(null);
        LblCadIm.setIcon(new ImageIcon(""));

    }

    private void Gravi() {
        conexao.conector();
        String caminho = LblCadIm.getIcon().toString();
        InputStream fis;
        File file = new File(caminho);
        //file.getName();
        String sql = "INSERT INTO cadastro (nome,ender,cep,fone,email,cpf,data_nasc,estatus,perfil, foto_img,batismo,genero) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            fis = new FileInputStream(file);

            PreparedStatement ps = conexao.conn.prepareStatement(sql);
            ps.setString(1, CadNome.getText());
            ps.setString(2, CadEnder.getText());
            ps.setString(3, CadCEP.getText());
            ps.setString(4, CadFone.getText());
            ps.setString(5, CadEmail.getText());
            ps.setString(6, CadCPF.getText());
            ps.setString(7, CadNasc.getText());
            ps.setString(8, CboEst.getSelectedItem().toString());
            ps.setString(9, CboPerfil.getSelectedItem().toString());
            ps.setBinaryStream(10, fis, (int) file.length());
            ps.setString(11, CboBatismo.getSelectedItem().toString());
            ps.setString(12, CadGen.getSelectedItem().toString());
            
            
            
            if (LblCadIm == null) {
                JOptionPane.showMessageDialog(null, "Preenchimento obrgatório");
            } else {
                int adicionado = ps.executeUpdate();

                if (adicionado > 0) {
                    // JOptionPane.showMessageDialog(null, "Imagem inserida com sucesso");

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void BuscaNoBanco() {
        conexao.conector();
        try {
            conexao.executaSQL("SELECT * FROM cadastro where id_cad like '%" + CadId.getText() + "%'");
            conexao.rs.first();
            CadId.setText(String.valueOf(conexao.rs.getInt("id_cad")));
            CadNome.setText(conexao.rs.getString("nome"));
            CadEnder.setText(conexao.rs.getString("ender"));
            CadFone.setText(conexao.rs.getString("fone"));
            CadCPF.setText(conexao.rs.getString("cpf"));
            CadNasc.setText(conexao.rs.getString("data_nasc"));
            conexao.rs.beforeFirst();

            while (conexao.rs.next()) {
                byte[] imgBytes = ((byte[]) conexao.rs.getBytes("foto_img"));
                ImageIcon pic = null;
                pic = new ImageIcon(imgBytes);
                Image scaled = pic.getImage().getScaledInstance(LblCadIm.getWidth(), LblCadIm.getHeight(), Image.SCALE_DEFAULT);
                ImageIcon icon = new ImageIcon(scaled);
                LblCadIm.setIcon(icon);//Adapta o tamanho da img para ficar do tamanho da label
                //jl.setIcon(pic);
                FileOutputStream fos;
                String nome_foto = "teste";
            }
        } catch (Exception ex) {
            System.out.println("Erro " + ex);
        }

    }

    /**
     * Creates new form TelaCadastro
     */
    public TelaCadastro() {
        initComponents();
               
    }
    CadastroBLL bll = new CadastroBLL();
    Menu nem = new Menu();

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        CadCPF = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        CboPerfil = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        CadFone = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        CboEst = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        CadEnder = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        CadNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        CadNasc = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        CadId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        CadTable = new javax.swing.JTable();
        CadPesq = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        BtnNovo = new javax.swing.JButton();
        LblCadIm = new javax.swing.JLabel();
        BtnCarregaImagem = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        CadCEP = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        CadEmail = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        CboBatismo = new javax.swing.JComboBox<>();
        CadGen = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        setBackground(new java.awt.Color(172, 203, 223));
        setClosable(true);
        setIconifiable(true);
        setTitle("Sistema  de Cadastro");
        setToolTipText("");
        setMaximumSize(new java.awt.Dimension(1180, 680));
        setPreferredSize(new java.awt.Dimension(1130, 614));

        jPanel1.setBackground(new java.awt.Color(161, 185, 199));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnSalvar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnDel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDel.setText("Apagar");
        btnDel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        btnAlterar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAlterar.setText("Aterar");
        btnAlterar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        CadCPF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("CPF");

        CboPerfil.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CboPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Usuário" }));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Perfil");

        CadFone.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Telefone");

        CboEst.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CboEst.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Membro", "Congregado", "visitante" }));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Estatus");

        CadEnder.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Endereço");

        CadNome.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Nome");

        CadNasc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Data de Nascimento:");

        CadId.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CadId.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Id");

        CadTable.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        CadTable.setModel(new javax.swing.table.DefaultTableModel(
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
        CadTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CadTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(CadTable);

        CadPesq.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CadPesq.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CadPesqKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Pesquisar");

        jLabel10.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel10.setText("Até aqui o Senhor tem nos ajudado");

        jLabel11.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel11.setText("1ª Samuel 7:12");

        BtnNovo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BtnNovo.setText("Novo");
        BtnNovo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNovoActionPerformed(evt);
            }
        });

        LblCadIm.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        BtnCarregaImagem.setText("Buscar");
        BtnCarregaImagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCarregaImagemActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("CEP:");

        CadCEP.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Email");

        CadEmail.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Batizado");

        CboBatismo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CboBatismo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SIM", "NÃO" }));

        CadGen.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        CadGen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Feminino" }));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Genero");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblCadIm, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnCarregaImagem))
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(jLabel11)
                                .addGap(156, 156, 156))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(30, 30, 30))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addGap(14, 14, 14)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(CadPesq)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(CadId, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CadNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(81, 81, 81)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CadCEP))
                            .addComponent(CadEnder, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CadNome)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                                        .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(CadEmail)
                                    .addComponent(CadFone)
                                    .addComponent(CboPerfil, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(134, 134, 134)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel6)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel15)
                                        .addGap(9, 9, 9)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(CadGen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel8))
                                            .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(BtnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(CboBatismo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(CboEst, 0, 197, Short.MAX_VALUE)
                                        .addComponent(CadCPF)))))
                        .addGap(199, 199, 199))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(LblCadIm, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnCarregaImagem))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CadPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(CadNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CadId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel12)
                    .addComponent(CadCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CadNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CadEnder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(CadEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CadCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(CadGen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CboBatismo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(CadFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CboEst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addGap(51, 51, 51))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CboPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setBounds(0, 0, 1130, 614);
    }// </editor-fold>//GEN-END:initComponents

    private void CadPesqKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CadPesqKeyReleased
     
        pesquisa();        
        //preencherTabela("select nome, ender, cep, fone, email, cpf, data_nasc, estatus, perfil, batismo from cadastro");
        CadTable.setVisible(true);
    }//GEN-LAST:event_CadPesqKeyReleased

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        String nome = this.CadNome.getText();
        String ender = this.CadEnder.getText();
        String fone = this.CadFone.getText();
        String data_nasc = this.CadNasc.getText();
        String cpf = this.CadCPF.getText();
        String estatus = this.CboEst.getSelectedItem().toString();
        String perfil = this.CboPerfil.getSelectedItem().toString();
        String fotos = this.LblCadIm.getText();
      //  BinaryStream = this.BtnCarregaImagem.getText();

        // bll.inserir(nome, ender, fone, data_nasc, cpf, estatus, perfil, fotos);
        Gravi();
        limpar();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        int id = Integer.parseInt(CadId.getText());
        String nome = this.CadNome.getText();
        String ender = this.CadEnder.getText();
        String fone = this.CadFone.getText();
        String data_nasc = this.CadNasc.getText();
        String cpf = this.CadCPF.getText();
        String estatus = this.CboEst.getSelectedItem().toString();
        String perfil = this.CboPerfil.getSelectedItem().toString();
        String genero = this.CadGen.getSelectedItem().toString();

        bll.alterar(id, nome, ender, fone, cpf, data_nasc, estatus, perfil, genero);
        limpar();
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void CadTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CadTableMouseClicked
        setar();
       LblCadIm.setEnabled(true);
    }//GEN-LAST:event_CadTableMouseClicked

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        int id = Integer.parseInt(CadId.getText());
        String nome = this.CadNome.getText();
        String ender = this.CadEnder.getText();
        String fone = this.CadFone.getText();
        String estatus = this.CboEst.getSelectedItem().toString();
        String perfil = this.CboPerfil.getSelectedItem().toString();

        bll.remover(id, nome, ender, fone, fone, estatus, estatus, perfil);
        limpar();
    }//GEN-LAST:event_btnDelActionPerformed

    private void BtnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNovoActionPerformed
        limpar();
        btnSalvar.setEnabled(true);

    }//GEN-LAST:event_BtnNovoActionPerformed

    private void BtnCarregaImagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCarregaImagemActionPerformed
        try {
            JFileChooser file = new JFileChooser();
            file.setCurrentDirectory(new File("Bibliotecas\\Imagens"));
            file.setDialogTitle("Carregar Imagem da Pessoa");
            file.showOpenDialog(this);
            LblCadIm.setIcon(new ImageIcon(file.getSelectedFile().getPath()));
            //jl.setText(file.getSelectedFile().getPath());
        } catch (Exception e) {
            LblCadIm.setIcon(new ImageIcon("Bibliotecas\\Imagens\\X.png"));
        }

    }//GEN-LAST:event_BtnCarregaImagemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCarregaImagem;
    private javax.swing.JButton BtnNovo;
    private javax.swing.JTextField CadCEP;
    private javax.swing.JTextField CadCPF;
    private javax.swing.JTextField CadEmail;
    private javax.swing.JTextField CadEnder;
    private javax.swing.JTextField CadFone;
    private javax.swing.JComboBox<String> CadGen;
    private javax.swing.JTextField CadId;
    private javax.swing.JTextField CadNasc;
    private javax.swing.JTextField CadNome;
    private javax.swing.JTextField CadPesq;
    private javax.swing.JTable CadTable;
    private javax.swing.JComboBox<String> CboBatismo;
    private javax.swing.JComboBox<String> CboEst;
    private javax.swing.JComboBox<String> CboPerfil;
    private javax.swing.JLabel LblCadIm;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
