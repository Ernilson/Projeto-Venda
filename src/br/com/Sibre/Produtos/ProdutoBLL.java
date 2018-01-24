/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Sibre.Produtos;
import br.com.Sibre.Produtos.ProdutoDTO;
import br.com.Sibre.Produtos.ProdutoDAO;

/**
 *
 * @author ernil
 */
public class ProdutoBLL {
    ProdutoDAO dao = new ProdutoDAO();
    ProdutoDTO dto = new ProdutoDTO();
    
    public void salvaProduto(String descricao, String qtd, String preco){
        dto.setDescricao(descricao);
        dto.setQtd(qtd);
        dto.setPreco(preco);
        
        dao.salvarProduto(dto);
    }
   
    public void ExcluirProduto(int id_pro, String descricao, String qtd, String preco){
        dto.setId_pro(id_pro);
        dto.setDescricao(descricao);
        dto.setQtd(qtd);
        dto.setPreco(preco);
        
        dao.ExcluirProduto(dto);
    }
    
}
