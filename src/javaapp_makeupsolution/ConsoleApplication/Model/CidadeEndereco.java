/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapp_makeupsolution.ConsoleApplication.Model;

/**
 *
 * @author vdsbt33
 */
public class CidadeEndereco {
    
    private int codCidadeEndereco;

    public int getCodCidadeEndereco() {
        return codCidadeEndereco;
    }

    public void setCodCidadeEndereco(int codCidadeEndereco) {
        this.codCidadeEndereco = codCidadeEndereco;
    }
    
    private String nomeCidadeEndereco;

    public String getNomeCidadeEndereco() {
        return nomeCidadeEndereco;
    }

    public void setNomeCidadeEndereco(String nomeCidadeEndereco) {
        this.nomeCidadeEndereco = nomeCidadeEndereco;
    }
    
    // Constructors
    public CidadeEndereco(String nomeCidadeEndereco){
        this.nomeCidadeEndereco = nomeCidadeEndereco;
    }
    
    public CidadeEndereco(int codCidadeEndereco, String nomeCidadeEndereco){
        this.codCidadeEndereco = codCidadeEndereco;
        this.nomeCidadeEndereco = nomeCidadeEndereco;
    }
}
