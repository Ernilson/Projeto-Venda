/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Sibre.Inventarios;

import java.sql.*;
import br.com.Sibre.DAL.ModuloConexao;
import br.com.Sibre.Inventarios.InventarioDTO;
import br.com.Sibre.Produtos.ProdutoDTO;
import javax.swing.JOptionPane;

/**
 *
 * @author T69779848134
 */
public class InventarioDAO {

    PreparedStatement pst = null;
    ResultSet rs = null;
    ModuloConexao conexao = new ModuloConexao();

    public void adicionar(InventarioDTO dto) {
        conexao.conector();
        String sql = "insert into inventario(descricao, quant, valorE) value(?,?,?)";

        try {
            pst = conexao.conn.prepareStatement(sql);
            pst.setString(1, dto.getDescricao());
            pst.setString(2, dto.getQuant());
            pst.setString(3, dto.getValor());

            if ((dto.getDescricao().isEmpty()) || (dto.getValor().isEmpty())) {
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
    }
    
    public void alterar(InventarioDTO dto) {
        String sql = "update inventario set descricao=?, quant=?, valorE=? where id_iv=?";
       conexao.conector();
        try {
            pst = conexao.conn.prepareStatement(sql);
            pst.setString(1, dto.getDescricao());
            pst.setString(2, dto.getQuant());
            pst.setString(3, dto.getValor());                    
            pst.setInt(4, dto.getId());

            if ((dto.getDescricao().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrgatórios");
            } else {
                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados alterados com sucesso");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        System.out.println(conexao);
    }
    
     public void Excluir(InventarioDTO dto) {
        conexao.conector();
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este dado?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from inventario where id_iv =?";
            try {
                PreparedStatement pst = conexao.conn.prepareStatement(sql);
                pst.setInt(1, dto.getId());

                int apagado = pst.executeUpdate();

                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Removido com sucesso");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "erro");

            }
        }
    }
}
