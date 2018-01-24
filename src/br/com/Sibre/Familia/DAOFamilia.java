/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Sibre.Familia;

import br.com.Sibre.Produtos.ProdutoDTO;
import java.sql.*;
import br.com.Sibre.DAL.ModuloConexao;
import br.com.Sibre.Familia.DTOFamilia;
import javax.swing.JOptionPane;

/**
 *
 * @author ernil
 */
public class DAOFamilia {

    ModuloConexao conexao = new ModuloConexao();

    public void AdicionaFamilia(DTOFamilia dto) {
        String sql = "insert into familia( estado_civil, posicao, profissao, conge, nome_filhos, cpff, funcao_minis, nive_escolar) values (?,?,?,?,?,?,?,?)";
        conexao.conector();

        try {
            PreparedStatement pst = conexao.conn.prepareStatement(sql);
            pst.setString(1, dto.getEstado());
            pst.setString(2, dto.getPosicao());
            pst.setString(3, dto.getProfissao());
            pst.setString(4, dto.getConge());
            pst.setString(5, dto.getFilhos());
            pst.setString(6, dto.getCpf());
            pst.setString(7, dto.getFuncao_minis());
            pst.setString(8, dto.getNive_esc());

            if ((dto.getEstado().isEmpty()) || (dto.getPosicao().isEmpty()) || (dto.getProfissao().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrgatórios");
            } else {
                int inclui = pst.executeUpdate();
                if (inclui > 0) {
                    JOptionPane.showMessageDialog(null, "Dados adicionados com sucesso!");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void alterar(DTOFamilia dto) {
        String sql = "update familia set estado_civil=?, posicao=?, profissao=?, conge=?, cpff=?, nome_filhos=?, funcao_minis=?, nive_escolar=? where id_fam=?";
        conexao.conector();
        try {
            PreparedStatement pst = conexao.conn.prepareStatement(sql);
            pst.setString(1, dto.getEstado());
            pst.setString(2, dto.getPosicao());
            pst.setString(3, dto.getProfissao());
            pst.setString(4, dto.getConge());
            pst.setString(5, dto.getCpf());
            pst.setString(6, dto.getFilhos());
            pst.setString(7, dto.getFuncao_minis());
            pst.setString(8, dto.getNive_esc());
            pst.setInt(9, dto.getId());

            if (dto.getCpf().isEmpty()) {
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

    public void Excluir(DTOFamilia dto) {
        conexao.conector();
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from familia where id_Fam = ?";
            try {
                PreparedStatement pst = conexao.conn.prepareStatement(sql);
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

}
