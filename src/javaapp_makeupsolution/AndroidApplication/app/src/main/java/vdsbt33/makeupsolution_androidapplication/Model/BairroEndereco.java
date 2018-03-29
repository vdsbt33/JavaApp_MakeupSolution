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
public class BairroEndereco {
    
    private int codBairroEndereco;

    public int getCodBairroEndereco() {
        return codBairroEndereco;
    }

    public void setCodBairroEndereco(int codBairroEndereco) {
        this.codBairroEndereco = codBairroEndereco;
    }
    
    private CidadeEndereco cidadeEndereco;

    public CidadeEndereco getCidadeEndereco() {
        return cidadeEndereco;
    }

    public void setCidadeEndereco(CidadeEndereco cidadeEndereco) {
        this.cidadeEndereco = cidadeEndereco;
    }
    
    private String nomeBairroEndereco;

    public String getNomeBairroEndereco() {
        return nomeBairroEndereco;
    }

    public void setNomeBairroEndereco(String nomeBairroEndereco) {
        this.nomeBairroEndereco = nomeBairroEndereco;
    }
    
    // Constructors
    public BairroEndereco(CidadeEndereco cidadeEndereco, String nomeBairroEndereco){
        this.cidadeEndereco = cidadeEndereco;
        this.nomeBairroEndereco = nomeBairroEndereco;
    }
    
    public BairroEndereco(int codBairroEndereco, CidadeEndereco cidadeEndereco, String nomeBairroEndereco){
        this.codBairroEndereco = codBairroEndereco;
        this.cidadeEndereco = cidadeEndereco;
        this.nomeBairroEndereco = nomeBairroEndereco;
    }
}
