/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Sibre.Inventarios;

/**
 *
 * @author T69779848134
 */
public class InventarioDTO {
    private int id;
    private String descricao;
    private String quant;
    private String valor;

    public InventarioDTO() {
    }

    public InventarioDTO(int id, String descricao, String quant, String valor) {
        this.id = id;
        this.descricao = descricao;
        this.quant = quant;
        this.valor = valor;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the quant
     */
    public String getQuant() {
        return quant;
    }

    /**
     * @param quant the quant to set
     */
    public void setQuant(String quant) {
        this.quant = quant;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }
    
    
}
