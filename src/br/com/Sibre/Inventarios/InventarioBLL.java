/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Sibre.Inventarios;
import br.com.Sibre.Inventarios.InventarioDTO;
import br.com.Sibre.Inventarios.InventarioDAO;
/**
 *
 * @author T69779848134
 */
public class InventarioBLL {
    InventarioDTO dto = new InventarioDTO();
    InventarioDAO dao = new InventarioDAO();
    
    public void adicionar(String descricao, String quant, String valor){
        dto.setDescricao(descricao);
        dto.setQuant(quant);
        dto.setValor(valor);
        
        dao.adicionar(dto);
    }
    
    public void Alterar(int id, String descricao, String quant, String valor){
        dto.setId(id);
        dto.setDescricao(descricao);
        dto.setQuant(quant);
        dto.setValor(valor);
        
        dao.alterar(dto);
    }
    
    public void Excluir(int id, String descricao, String quant, String valor){
        dto.setId(id);
        dto.setDescricao(descricao);
        dto.setQuant(quant);
        dto.setValor(valor);
        
        dao.Excluir(dto);
    }
}
