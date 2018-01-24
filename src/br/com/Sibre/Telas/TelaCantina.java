/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Sibre.Telas;

import br.com.Sibre.DAL.ModuloConexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import br.com.Sibre.Cantina.vendaBLL;
import javax.swing.table.DefaultTableModel;
import br.com.Sibre.Produtos.ProdutoBLL;

/**
 *
 * @author T69779848134
 */
public class TelaCantina extends javax.swing.JInternalFrame {

    int flag = 1;
    float total;
    ModuloConexao conexao = new ModuloConexao();
    PreparedStatement pst = null;
    ResultSet rs = null;

    //Metodo para preencher com os dados do banco da tabela "cadastro" o Jtable - VendTbale;
    public void pesquisaCadastro() {
        conexao.conector();
        String sql = "select nome, estatus, perfil from cadastro where nome like ?";
        try {
            pst = conexao.conn.prepareStatement(sql);
            pst.setString(1, VendNome.getText() + "%");
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar                          
            VendTable.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Metodo para preencher com os dados do banco da tabela "cadastro" o Jtable - VendTbale;
    private void setarCadastro() {
        int setar = VendTable.getSelectedRow();
        //VendCod.setText(VendTable.getModel().getValueAt(setar, ).toString());
        VendNome.setText(VendTable.getModel().getValueAt(setar, 0).toString());
        //a linha a baixo desabilita o butao adicionar
        //   btnSalvar.setEnabled(false);       
    }

    //Metodo para preencher com os dados do banco da tabela "Produto" o Jtable - VendTbale2 na primeira Aba;
    public void pesquisaProdutos() {
        conexao.conector();
        String sql = "select descricao_p, qtd, preco, dataq from produtos where descricao_p like ?";
        try {
            pst = conexao.conn.prepareStatement(sql);
            pst.setString(1, VendProduto.getText() + "%");
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar
            VendTable2.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Metodo para preencher os campos de texto do formulario com os dados da tabela Produtos o JTable "VendTable2" na primeira Aba;
    private void setarProduto() {
        int setar = VendTable2.getSelectedRow();
        VendProduto.setText(VendTable2.getModel().getValueAt(setar, 0).toString());
        VendQtd.setText(VendTable2.getModel().getValueAt(setar, 1).toString());
        VendValor.setText(VendTable2.getModel().getValueAt(setar, 2).toString());
        VendData.setText(VendTable2.getModel().getValueAt(setar, 3).toString());
    }

    //Metodo para preencher com os dados do banco da tabela "venda" o Jtable - ReultTbale na segunda Aba;
    public void pesquisaVenda(String sql) {
        conexao.conector();
        try {
            pst = conexao.conn.prepareStatement(sql);
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar                           
            ResultTable.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void pesquisaCarrinho(String sql) {
        conexao.conector();
        try {
            pst = conexao.conn.prepareStatement(sql);
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar                           
            VendTable3.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Metodo para preencher os campos de texto do formulario com os dados do JTable "ResultTable" na segunda Aba;
    private void setarVenda() {
        int setar = ResultTable.getSelectedRow();
        ResultId.setText(ResultTable.getModel().getValueAt(setar, 0).toString());
        ResultPesq.setText(ResultTable.getModel().getValueAt(setar, 1).toString());
    }

    //Metodo para preencher com os dados do banco da tabela "produtos" o Jtable - ReultTbale na segunda Aba;
    public void pesquisaProduto(String sql) {
        conexao.conector();
        try {
            pst = conexao.conn.prepareStatement(sql);
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar                  
            ResultTable.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Metodo para preencher os campos de texto do formulario com os dados da tabela venda o JTable "ResultTable" na segunda Aba;
    private void setarReult() {
        int setar = ResultTable.getSelectedRow();
        ResultId.setText(ResultTable.getModel().getValueAt(setar, 0).toString());
        ResultPesq.setText(ResultTable.getModel().getValueAt(setar, 1).toString());
//        ProdQtd.setText(ResultTable.getModel().getValueAt(setar, 2).toString());
//        ProdPreco.setText(ResultTable.getModel().getValueAt(setar, 3).toString());
    }

    //Metodo para preencher os campos de texto do formulario com os dados da tabela Carrinho;
    private void avançar() {
        int setar = VendTable3.getSelectedRow();
        VendNome.setText(VendTable3.getModel().getValueAt(setar, 0).toString());
        VendProduto.setText(VendTable3.getModel().getValueAt(setar, 1).toString());
        VendQtd.setText(VendTable3.getModel().getValueAt(setar, 2).toString());
        VendValor.setText(VendTable3.getModel().getValueAt(setar, 3).toString());
    }

    // Metodo para somar o produto
    public void SomaProduto() {
        total = 0;
        int qtd = 0;
        float valor = 0;
        conexao.executaSQL("select Vend.nome,qtd, valor, Prod.descricao_p,preco from venda as Vend inner join produtos as Prod on (Prod.id_pro = Vend.id_v);" + ResultId);
        try {
            // conexao.rs.first();
            while (conexao.rs.next()) {
                qtd = conexao.rs.getInt("qtd");
                valor = conexao.rs.getFloat("preco");
                total = total + conexao.rs.getFloat("preco") * conexao.rs.getInt("qtd");
                JOptionPane.showMessageDialog(null, "qtd" + qtd + "pv=" + valor);
            }
            VendValorT.setText(String.valueOf(total));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher na soma total" + e);
        }
    }

//    Metodo para limpar a segunda Aba (resultado)
    private void LimparEstoque() {
        ResultId.setText(null);
        ResultPesq.setText(null);
        ResultTable.setVisible(false);
        ProdDesc.setText(null);
        ProdQtd.setText(null);
        ProdPreco.setText(null);
    }
//  Metodo para limpar a primeira Aba (venda)

    private void limpaTela() {
        VendNome.setText("");
        VendTable.setVisible(false);
        VendProduto.setText("");
        VendTable2.setVisible(false);
        VendQtd.setText("");
        VendValorT.setText("");                // Metodo para limapr campos;
        VendValor.setText("");
        VendData.setText("");
    }

    private void limpaProdutos() {
        ProdDesc.setText(null);
        ProdQtd.setText(null);
        ProdPreco.setText(null);
    }

    /**
     * Creates new form TelaCantina
     */
    public TelaCantina() {
        initComponents();
    }
    vendaBLL bll = new vendaBLL();
    ProdutoBLL bl2 = new ProdutoBLL();

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        ResultTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        ResultPesq = new javax.swing.JTextField();
        ProdDEL = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        ResultId = new javax.swing.JTextField();
        BtnVendido = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        ProdDesc = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        ProdPreco = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        ProdQtd = new javax.swing.JTextField();
        BtnAdic = new javax.swing.JButton();
        ResultEstoque = new javax.swing.JButton();
        BtnApagaP = new javax.swing.JButton();
        ResultNovo = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        VendTable = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        VendNome = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        VendProduto = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        VendTable2 = new javax.swing.JTable();
        VendQtd = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        VendValor = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        VendData = new javax.swing.JTextField();
        btnFinal = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        VendValorT = new javax.swing.JTextField();
        CboCont = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        VendTable3 = new javax.swing.JTable();
        VendAdd = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Tela Cantina");
        setToolTipText("Tela Cantina");
        setMaximumSize(new java.awt.Dimension(1130, 614));
        setPreferredSize(new java.awt.Dimension(1130, 614));

        jPanel2.setBackground(new java.awt.Color(180, 202, 197));
        jPanel2.setPreferredSize(new java.awt.Dimension(1205, 702));

        jPanel3.setBackground(new java.awt.Color(189, 206, 207));
        jPanel3.setPreferredSize(new java.awt.Dimension(1195, 695));

        ResultTable.setModel(new javax.swing.table.DefaultTableModel(
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
        ResultTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ResultTableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(ResultTable);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Pesquisa");

        ResultPesq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResultPesqActionPerformed(evt);
            }
        });
        ResultPesq.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ResultPesqKeyReleased(evt);
            }
        });

        ProdDEL.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ProdDEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/Sibre/Imagens/Cubiertos.png"))); // NOI18N
        ProdDEL.setText("Apaga Venda");
        ProdDEL.setToolTipText("Apagar Venda");
        ProdDEL.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ProdDEL.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ProdDEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProdDELActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Id");

        BtnVendido.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BtnVendido.setText("Vendidos");
        BtnVendido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVendidoActionPerformed(evt);
            }
        });
        BtnVendido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BtnVendidoKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Descrição do Produto:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Preço por unidade:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Quantidade Todal");

        BtnAdic.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BtnAdic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/Sibre/Imagens/Adicionar.jpg"))); // NOI18N
        BtnAdic.setText("Adicionar Produto");
        BtnAdic.setToolTipText("Adiciona Produto");
        BtnAdic.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnAdic.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnAdic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAdicActionPerformed(evt);
            }
        });

        ResultEstoque.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ResultEstoque.setText("Estoque");
        ResultEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResultEstoqueActionPerformed(evt);
            }
        });

        BtnApagaP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BtnApagaP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/Sibre/Imagens/images444.png"))); // NOI18N
        BtnApagaP.setText("Apaga Produto");
        BtnApagaP.setToolTipText("Apga produto");
        BtnApagaP.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnApagaP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnApagaP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnApagaPActionPerformed(evt);
            }
        });

        ResultNovo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ResultNovo.setText("Novo");
        ResultNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResultNovoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(BtnAdic, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91)
                        .addComponent(ProdDEL, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(BtnApagaP, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ProdPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addGap(133, 133, 133)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel14)
                                    .addComponent(ProdQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(ProdDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ResultNovo))
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 865, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(ResultId, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(35, 35, 35)
                                    .addComponent(BtnVendido)
                                    .addGap(36, 36, 36)
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(ResultPesq, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(ResultEstoque))))))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ResultPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnVendido)
                    .addComponent(ResultEstoque)
                    .addComponent(ResultId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProdDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ResultNovo))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14))
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProdPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ProdQtd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnAdic, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ProdDEL, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnApagaP, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(106, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 985, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Resultado", jPanel2);

        jPanel1.setBackground(new java.awt.Color(175, 205, 210));

        VendTable.setModel(new javax.swing.table.DefaultTableModel(
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
        VendTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VendTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(VendTable);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Nome");

        VendNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                VendNomeKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Produto");

        VendProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                VendProdutoKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Quantidade");

        VendTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        VendTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VendTable2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(VendTable2);

        VendQtd.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                VendQtdFocusGained(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Valor por Item:");

        VendValor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VendValorMouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Data:");

        btnFinal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnFinal.setText("Finalizar Venda");
        btnFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalActionPerformed(evt);
            }
        });

        btnDel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDel.setText("Nova Venda");
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Valor Total:");

        CboCont.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CboCont.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "pago á vista", "Fiado" }));
        CboCont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboContActionPerformed(evt);
            }
        });

        VendTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        VendTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VendTable3MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(VendTable3);

        VendAdd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        VendAdd.setText("Adcionar");
        VendAdd.setEnabled(false);
        VendAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VendAddMouseClicked(evt);
            }
        });
        VendAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VendAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(159, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(VendNome, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(118, 118, 118)
                                .addComponent(jLabel11)
                                .addGap(28, 28, 28)
                                .addComponent(VendData, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(VendProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(VendQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(VendValor, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 777, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnFinal)
                            .addComponent(CboCont, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(138, 138, 138)
                        .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(VendAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel2)
                                .addGap(26, 26, 26)
                                .addComponent(VendValorT, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(7, 7, 7)))
                .addContainerGap(158, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(VendNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(VendData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(VendProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VendValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(VendQtd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(VendValorT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CboCont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VendAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(151, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Vendas", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1104, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE))
        );

        setBounds(0, 0, 1130, 614);
    }// </editor-fold>//GEN-END:initComponents

    private void VendTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VendTableMouseClicked
        setarCadastro();
        VendTable.setVisible(false);
    }//GEN-LAST:event_VendTableMouseClicked

    private void VendNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_VendNomeKeyReleased
        pesquisaCadastro();
        VendTable.setVisible(true);
    }//GEN-LAST:event_VendNomeKeyReleased

    private void VendProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_VendProdutoKeyReleased
        pesquisaProdutos();
        VendTable2.setVisible(true);
    }//GEN-LAST:event_VendProdutoKeyReleased

    private void VendTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VendTable2MouseClicked
        setarProduto();
        VendQtd.setText("1");
        VendTable2.setVisible(false);
    }//GEN-LAST:event_VendTable2MouseClicked

    private void VendQtdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_VendQtdFocusGained
        float valorTotal;
        valorTotal = Float.parseFloat(VendValor.getText()) * Integer.parseInt(VendQtd.getText());
        VendValorT.setText(String.valueOf(valorTotal));
        //SomaProduto();
    }//GEN-LAST:event_VendQtdFocusGained

    private void VendValorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VendValorMouseClicked

    }//GEN-LAST:event_VendValorMouseClicked

    private void btnFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalActionPerformed
        String nome = this.VendNome.getText();
        String descrica = this.VendProduto.getText();
        String qtd = VendQtd.getText();
        String total = this.VendValorT.getText();
        String cboCont = this.CboCont.getSelectedItem().toString();
        bll.Salvar_venda(nome, descrica, qtd, total, cboCont);
        limpaTela();
    }//GEN-LAST:event_btnFinalActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        limpaTela();
    }//GEN-LAST:event_btnDelActionPerformed

    private void CboContActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboContActionPerformed
        //        // TODO add your handling code here: float valorTotal;
        //        valorTotal = Float.parseFloat(VendValor.getText()) * Integer.parseInt(VendQtd.getText());
        //        VendValorT.setText(String.valueOf(valorTotal));
        //        //
    }//GEN-LAST:event_CboContActionPerformed

    private void VendTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VendTable3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_VendTable3MouseClicked

    private void VendAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VendAddMouseClicked
        pesquisaCarrinho(" select Vend.nome, descricao,qtdp, Prod.preco from venda as Vend inner join produtos as Prod on (Prod.id_pro = Vend.id_v) where descricao like '%" + ResultPesq.getText() + "%'order by dataq desc;");// deve unir a tabela cadastro com produto
        //;nome, decricao, qtd, valor from produtos
    }//GEN-LAST:event_VendAddMouseClicked

    private void VendAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VendAddActionPerformed
//                String nome = this.VendNome.getText();
//                String descrica = this.VendProduto.getText();
//                String qtd = VendQtd.getText();
//                Float valorItem = Float.parseFloat(VendValor.getText());
//                bll.SalvaCarrinho(nome, descrica, qtd, valorItem);
//                //avancar3();
//               LimparEstoque();
//                //limpaTela();
    }//GEN-LAST:event_VendAddActionPerformed

    private void ResultTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResultTableMouseClicked
        if (flag == 1) {
            setarVenda();
        } else {
            setarReult();
        }
    }//GEN-LAST:event_ResultTableMouseClicked

    private void ResultPesqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResultPesqActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ResultPesqActionPerformed

    private void ResultPesqKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ResultPesqKeyReleased
        //pesquisaVenda();
    }//GEN-LAST:event_ResultPesqKeyReleased

    private void ProdDELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProdDELActionPerformed
        int id = Integer.parseInt(ResultId.getText());
        String nome = this.VendNome.getText();
        String descrica = this.VendProduto.getText();
        String qtd = this.VendQtd.getText();
        String total = this.VendValorT.getText();
        String cboCont = this.CboCont.getSelectedItem().toString();
        bll.ExcluirVenda(id, nome, descrica, qtd, total, cboCont);
        ResultTable.setEnabled(false);
        LimparEstoque();
    }//GEN-LAST:event_ProdDELActionPerformed

    private void BtnVendidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVendidoActionPerformed
        pesquisaProduto("select id_v, nome, descricao, qtdp, valor, forma_pg, dataq from venda where nome like '%" + ResultPesq.getText() + "%'");
        ProdDEL.setEnabled(true);
        BtnAdic.setEnabled(false);
        BtnApagaP.setEnabled(false);
        ProdDesc.setEnabled(false);
        ProdQtd.setEnabled(false);
        ProdPreco.setEnabled(false);
        ResultTable.setVisible(true);
        flag = 1;
    }//GEN-LAST:event_BtnVendidoActionPerformed

    private void BtnVendidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnVendidoKeyReleased

    }//GEN-LAST:event_BtnVendidoKeyReleased

    private void BtnAdicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAdicActionPerformed
        String descricao = this.ProdDesc.getText();
        String qtd = this.ProdQtd.getText();
        String preco = this.ProdPreco.getText();
        bl2.salvaProduto(descricao, qtd, preco);
        LimparEstoque();
    }//GEN-LAST:event_BtnAdicActionPerformed

    private void ResultEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResultEstoqueActionPerformed
        pesquisaVenda("select * from produtos where descricao_p like '%" + ResultPesq.getText() + "%'");
        ProdDesc.setEnabled(false);
        ProdPreco.setEnabled(false);
        ProdQtd.setEnabled(false);
        ProdDEL.setEnabled(false);
        BtnApagaP.setEnabled(true);
        ResultTable.setVisible(true);
        BtnAdic.setEnabled(false);
        flag = 2;
    }//GEN-LAST:event_ResultEstoqueActionPerformed

    private void BtnApagaPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnApagaPActionPerformed
        int id_pro = Integer.parseInt(ResultId.getText());
        String descrica = this.ProdDesc.getText();
        String qtd = this.ProdQtd.getText();
        String preco = this.ProdPreco.getText(); // preço por unidade da aba Result "valor Item".

        bl2.ExcluirProduto(id_pro, descrica, preco, preco);
        ResultTable.setVisible(false);
    }//GEN-LAST:event_BtnApagaPActionPerformed

    private void ResultNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResultNovoActionPerformed
        ProdDesc.setEnabled(true);
        ProdPreco.setEnabled(true);
        ProdQtd.setEnabled(true);
        ResultEstoque.setEnabled(true);
        ProdDEL.setEnabled(false);
        BtnAdic.setEnabled(true);
        BtnVendido.setEnabled(true);
        BtnApagaP.setEnabled(false);
        LimparEstoque();
    }//GEN-LAST:event_ResultNovoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAdic;
    private javax.swing.JButton BtnApagaP;
    private javax.swing.JButton BtnVendido;
    private javax.swing.JComboBox<String> CboCont;
    private javax.swing.JButton ProdDEL;
    private javax.swing.JTextField ProdDesc;
    private javax.swing.JTextField ProdPreco;
    private javax.swing.JTextField ProdQtd;
    private javax.swing.JButton ResultEstoque;
    private javax.swing.JTextField ResultId;
    private javax.swing.JButton ResultNovo;
    private javax.swing.JTextField ResultPesq;
    private javax.swing.JTable ResultTable;
    private javax.swing.JButton VendAdd;
    private javax.swing.JTextField VendData;
    private javax.swing.JTextField VendNome;
    private javax.swing.JTextField VendProduto;
    private javax.swing.JTextField VendQtd;
    private javax.swing.JTable VendTable;
    private javax.swing.JTable VendTable2;
    private javax.swing.JTable VendTable3;
    private javax.swing.JTextField VendValor;
    private javax.swing.JTextField VendValorT;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnFinal;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
