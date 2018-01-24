/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Sibre.Familia;
import br.com.Sibre.DAL.ModuloConexao;
import br.com.Sibre.Familia.DTOFamilia;
import br.com.Sibre.Familia.DAOFamilia;
/**
 *
 * @author ernil
 */
public class BLLFamilia {
    DTOFamilia dto = new DTOFamilia();
    DAOFamilia dao = new DAOFamilia();
    
    public void AdicionaFamilia( String estdo, String posicao, String profissao, String conge, String filhos, String cpf, String funcao_minst, String nivel_esc){
        //dto.setId(id);
        dto.setEstado(estdo);
        dto.setPosicao(posicao);
        dto.setProfissao(profissao);
        dto.setConge(conge);
        dto.setFilhos(filhos);
        dto.setCpf(cpf);
        dto.setFuncao_minis(funcao_minst);
        dto.setNive_esc(nivel_esc);
        
        dao.AdicionaFamilia(dto);
    }
    public void Alterar(int id, String nome, String estdo, String posicao, String profissao, String conge, String filhos, String cpf,String funcao_minist, String nivel_esc){
        dto.setId(id);
        dto.setNome(nome);
        dto.setEstado(estdo);
        dto.setPosicao(posicao);
        dto.setProfissao(profissao);
        dto.setConge(conge);
        dto.setFilhos(filhos);
        dto.setCpf(cpf);
        dto.setFuncao_minis(funcao_minist);
        dto.setNive_esc(nivel_esc);
        
        dao.alterar(dto);
    }
    
    public void Excluir(int id, String estdo, String posicao, String profissao, String conge, String filhos, String cpf){
        dto.setId(id);
        dto.setEstado(estdo);
        dto.setPosicao(posicao);
        dto.setProfissao(profissao);
        dto.setConge(conge);
        dto.setFilhos(filhos);
        dto.setCpf(cpf);
       
        dao.Excluir(dto);
    }
}
