/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Sibre.Cadastro;

import java.sql.*;
import br.com.Sibre.Cadastro.CadastroDTO;
import br.com.Sibre.Cadastro.CadastroDAO;

/**
 *
 * @author t69779848134
 */
public class CadastroBLL {

    CadastroDTO dto = new CadastroDTO();
    CadastroDAO dao = new CadastroDAO();

    public void inserir(String nome, String ender, String fone, String data_nasc, String cpf, String estatus, String perfil, String fotos) {

        dto.setNome(nome);
        dto.setEnder(ender);
        dto.setFone(fone);
        dto.setData_nasc(data_nasc);
        dto.setCpf(cpf);
        dto.setEstatus(estatus);
        dto.setPerfil(perfil);
        dto.setFotos(fotos);

        dao.gravar(dto);
    }

    public void alterar(int id, String nome, String ender, String fone, String cpf, String data_nasc, String estatus, String perfil, String genero) {
        dto.setId(id);
        dto.setNome(nome);
        dto.setEnder(ender);
        dto.setFone(fone);
        dto.setCpf(cpf);
        dto.setData_nasc(data_nasc);
        dto.setEstatus(estatus);
        dto.setPerfil(perfil);
        dto.setGenero(genero);

        dao.alterar(dto);
    }

    public void remover(int id, String nome, String ender, String fone, String cpf, String data_nasc, String estatus, String perfil) {
       dto.setId(id);
        dto.setNome(nome);
        dto.setEnder(ender);
        dto.setFone(fone);
        dto.setCpf(cpf);
        dto.setData_nasc(data_nasc);
        dto.setEstatus(estatus);
        dto.setPerfil(perfil);

        dao.remover(dto);
    }

}
