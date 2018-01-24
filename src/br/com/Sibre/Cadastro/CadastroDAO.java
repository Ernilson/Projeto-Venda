/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Sibre.Cadastro;

import java.sql.*;
import javax.swing.JOptionPane;
import br.com.Sibre.DAL.ModuloConexao;

/**
 *
 * @author t69779848134
 */
public class CadastroDAO {
ModuloConexao conexao = new ModuloConexao();
   // Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public void gravar(CadastroDTO dto) {
        String sql = "insert into cadastro(nome, ender, fone, cpf, data_nasc, estatus, perfil, foto_img )value (?,?,?,?,?,?,?,?)";
        conexao.conector();
        try {
            pst = conexao.conn.prepareStatement(sql);
            pst.setString(1, dto.getNome());
            pst.setString(2, dto.getEnder());
            pst.setString(3, dto.getFone());
            pst.setString(4, dto.getCpf());
            pst.setString(5, dto.getData_nasc());
            pst.setString(6, dto.getEstatus());
            pst.setString(7, dto.getPerfil());//pst.setString(5, dto.getPerfil();
            pst.setString(8, dto.getFotos());
                        
            if ((dto.getNome().isEmpty()) || (dto.getEnder().isEmpty()) || (dto.getFone().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrgatórios");
            } else {
                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do usuário alterados com sucesso");

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro"+e);
        }
    }
    public void alterar(CadastroDTO dto) {
        String sql = "update cadastro set nome=?, ender=?, fone=?, cpf=?, data_nasc=?, estatus=?, perfil=?, genero=? where id_cad=?";
       conexao.conector();
        try {
            pst = conexao.conn.prepareStatement(sql);
            pst.setString(1, dto.getNome());
            pst.setString(2, dto.getEnder());
            pst.setString(3, dto.getFone());
            pst.setString(4, dto.getCpf());
            pst.setString(5, dto.getData_nasc());
            pst.setString(6, dto.getEstatus());
            pst.setString(7, dto.getPerfil());
            pst.setString(8, dto.getGenero());
            pst.setInt(9, dto.getId());

            if ((dto.getNome().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrgatórios");
            } else {
                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do usuário alterados com sucesso");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        System.out.println(conexao);
    }
    public void remover(CadastroDTO dto) {
        conexao.conector();
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from cadastro where id_cad=?";
            try {
                pst = conexao.conn.prepareStatement(sql);
                pst.setInt(1, dto.getId());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário removido com sucesso");
                    
                    //ModuloC.fecharConexao();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "erro");

            }
        }
    }
}
