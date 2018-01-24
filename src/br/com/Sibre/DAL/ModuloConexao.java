/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Sibre.DAL;
import java.sql.*;

import javax.swing.JOptionPane;

/**
 *
 * @author ernil
 */
public class ModuloConexao {

    public PreparedStatement pst;//responsavel por preparar e realizar pesquisas no banco de dados;
    public Statement stm;//responsavel por preparar e realizar pesquisas no banco de dados
    public ResultSet rs; //responsavel por armazenar o resultado de uma pesquisa passada para o statement
    public Connection conn;//responsavel por realizar a conexão com o banco de dados
    private String driver = "com.mysql.jdbc.Driver";//responsavel por realizar a conexão com o banco de dados
    private  String caminho = "jdbc:mysql://localhost:3306/RowDow";//responsavel por setar o local do banco de dados
    //responsavel por identificar o serviço de banco de dados
    private  String usuario = "root";
    private String senha = "";

//    public ModuloConexao() {
//        this.caminho = "jdbc:mysql://localhost:3306/Magaly";
//    }
    // Metodo para conexao com o banco
    public void conector() {
        try {
            System.setProperty("jdbc.Driver", driver);//seta a propriedade do driver de conexao
            conn = DriverManager.getConnection(caminho, usuario, senha);//realiza a conexao com o banco de dados

            //JOptionPane.showMessageDialog(null, "Conectado com sucesso!");//imprime um caixa de texto
        } catch (SQLException ex) {//excessão
            JOptionPane.showMessageDialog(null, "Erro de conexao!\n Erro:" + ex.getMessage());
        }
    }
    // Metodo para executar String sql;
    public void executaSQL(String sql){
            try {
                stm = conn.createStatement(rs.TYPE_SCROLL_INSENSITIVE,rs.CONCUR_READ_ONLY);
                rs = stm.executeQuery(sql);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro de conexao!\n Erro:" + ex.getMessage());
            }
    }
     // metodo para fechar a conexao com o banco de dados
    public void desconecata() {

        try {
            conn.close();// fecha a conexão
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de conexao!\n Erro:" + ex.getMessage());
        }

    }

}

