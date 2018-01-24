/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Sibre.Cantina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import br.com.Sibre.Cantina.vendaDTO;
import br.com.Sibre.DAL.ModuloConexao;

public class VendaDao {

    ModuloConexao conexao = new ModuloConexao();
    PreparedStatement pst = null;
    ResultSet rs = null;

    public void salvarVenda(vendaDTO dto) {
        ModuloConexao conexao = new ModuloConexao();
        String sql = "insert into venda (nome, descricao, qtdp, valor, forma_pg )value (?,?,?,?,?)";
        conexao.conector();
        try {
            PreparedStatement pst = conexao.conn.prepareStatement(sql);
            pst.setString(1, dto.getNome());
            pst.setString(2, dto.getDescricao());
            pst.setString(3, dto.getQtd());
            pst.setString(4, dto.getTotal());
            pst.setString(5, dto.getCboCont());

            if ((dto.getNome().isEmpty()) || (dto.getDescricao().isEmpty()) || (dto.getTotal().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrgatórios");
            } else {
                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Venda finalizada com sucesso");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void ExcluirVenda(vendaDTO dto) {
        conexao.conector();
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from venda where id_v = ?";
            try {
                pst = conexao.conn.prepareStatement(sql);
                pst.setInt(1, dto.getId());

                int apagado = pst.executeUpdate();

                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário removido com sucesso");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "erro");

            }
        }
    }

    public void salvarCarrinho(vendaDTO dto) {
        ModuloConexao conexao = new ModuloConexao();
        String sql = "insert into carrinho (nome, descricao, quantidade, valor )value (?,?,?,?)";
        conexao.conector();
        try {
            PreparedStatement pst = conexao.conn.prepareStatement(sql);
            pst.setString(1, dto.getNome());
            pst.setString(2, dto.getDescricao());
            pst.setString(3, dto.getQtd());
            pst.setString(4, dto.getValorItem());
            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Dados do usuário alterados com sucesso");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
