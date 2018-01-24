/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Sibre.Cadastro;

import java.sql.*;

/**
 *
 * @author t69779848134
 */
public class CadastroDTO {
    private int id;
    private String nome;
    private String ender;
    private String fone;
    private String data_nasc;
    private String cpf;
    private String estatus;
    private String perfil;
    private String fotos;
    private String genero;

    public CadastroDTO() {
    }

    public CadastroDTO(int id, String nome, String ender, String fone, String data_nasc, String cpf, String estatus, String perfil, String fotos, String genero) {
        this.id = id;
        this.nome = nome;
        this.ender = ender;
        this.fone = fone;
        this.data_nasc = data_nasc;
        this.cpf = cpf;
        this.estatus = estatus;
        this.perfil = perfil;
        this.fotos = fotos;
        this.genero = genero;
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
     * @return the ender
     */
    public String getEnder() {
        return ender;
    }

    /**
     * @param ender the ender to set
     */
    public void setEnder(String ender) {
        this.ender = ender;
    }

    /**
     * @return the fone
     */
    public String getFone() {
        return fone;
    }

    /**
     * @param fone the fone to set
     */
    public void setFone(String fone) {
        this.fone = fone;
    }

    /**
     * @return the data_nasc
     */
    public String getData_nasc() {
        return data_nasc;
    }

    /**
     * @param data_nasc the data_nasc to set
     */
    public void setData_nasc(String data_nasc) {
        this.data_nasc = data_nasc;
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
     * @return the estatus
     */
    public String getEstatus() {
        return estatus;
    }

    /**
     * @param estatus the estatus to set
     */
    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    /**
     * @return the perfil
     */
    public String getPerfil() {
        return perfil;
    }

    /**
     * @param perfil the perfil to set
     */
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    /**
     * @return the fotos
     */
    public String getFotos() {
        return fotos;
    }

    /**
     * @param fotos the fotos to set
     */
    public void setFotos(String fotos) {
        this.fotos = fotos;
    }

    /**
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    
    
   
}