/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Sibre.Cantina;

/**
 *
 * @author T69779848134
 */
public class vendaDTO {
    private int id;
    private String nome;
    private String descricao;
    private String qtd;
    private String total;
    private String cboCont;
    private String valorItem;

    public vendaDTO() {
    }

    public vendaDTO(int id, String nome, String descricao, String qtd, String total, String cboCont, String valorItem) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.qtd = qtd;
        this.total = total;
        this.cboCont = cboCont;
        this.valorItem = valorItem;
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
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
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
     * @return the qtd
     */
    public String getQtd() {
        return qtd;
    }

    /**
     * @param qtd the qtd to set
     */
    public void setQtd(String qtd) {
        this.qtd = qtd;
    }

    /**
     * @return the total
     */
    public String getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(String total) {
        this.total = total;
    }

    /**
     * @return the cboCont
     */
    public String getCboCont() {
        return cboCont;
    }

    /**
     * @param cboCont the cboCont to set
     */
    public void setCboCont(String cboCont) {
        this.cboCont = cboCont;
    }

    /**
     * @return the valorItem
     */
    public String getValorItem() {
        return valorItem;
    }

    /**
     * @param valorItem the valorItem to set
     */
    public void setValorItem(String valorItem) {
        this.valorItem = valorItem;
    }

   
}