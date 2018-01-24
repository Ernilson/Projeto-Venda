/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Sibre.Familia;

/**
 *
 * @author ernil
 */
public class DTOFamilia {
    
    private int id;
    private String nome;
    private String estado;
    private String posicao;
    private String profissao;
    private String conge;
    private String filhos;
    private String cpf;
    private String nive_esc;
    private String funcao_minis;

    public DTOFamilia() {
    }

    public DTOFamilia(int id, String nome, String estado, String posicao, String profissao, String conge, String filhos, String cpf, String nive_esc, String funcao_minis) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
        this.posicao = posicao;
        this.profissao = profissao;
        this.conge = conge;
        this.filhos = filhos;
        this.cpf = cpf;
        this.nive_esc = nive_esc;
        this.funcao_minis = funcao_minis;
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
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the posicao
     */
    public String getPosicao() {
        return posicao;
    }

    /**
     * @param posicao the posicao to set
     */
    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    /**
     * @return the profissao
     */
    public String getProfissao() {
        return profissao;
    }

    /**
     * @param profissao the profissao to set
     */
    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    /**
     * @return the conge
     */
    public String getConge() {
        return conge;
    }

    /**
     * @param conge the conge to set
     */
    public void setConge(String conge) {
        this.conge = conge;
    }

    /**
     * @return the filhos
     */
    public String getFilhos() {
        return filhos;
    }

    /**
     * @param filhos the filhos to set
     */
    public void setFilhos(String filhos) {
        this.filhos = filhos;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the nive_esc
     */
    public String getNive_esc() {
        return nive_esc;
    }

    /**
     * @param nive_esc the nive_esc to set
     */
    public void setNive_esc(String nive_esc) {
        this.nive_esc = nive_esc;
    }

    /**
     * @return the funcao_minis
     */
    public String getFuncao_minis() {
        return funcao_minis;
    }

    /**
     * @param funcao_minis the funcao_minis to set
     */
    public void setFuncao_minis(String funcao_minis) {
        this.funcao_minis = funcao_minis;
    }

   
}
