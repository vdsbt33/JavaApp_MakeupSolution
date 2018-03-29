/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vdsbt33.makeupsolution_androidapplication.Model;

/**
 *
 * @author vdsbt33
 */
public class Cliente {

    public Cliente(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }
    
    public Cliente(int cod, String nome, String descricao) {
        this.cod = cod;
        this.nome = nome;
        this.descricao = descricao;
    }
    
    private int cod;

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
    
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    @Override
    public String toString() {
        return String.format(getCod() + ". " + getNome() + " - " + getDescricao());
    }

}
