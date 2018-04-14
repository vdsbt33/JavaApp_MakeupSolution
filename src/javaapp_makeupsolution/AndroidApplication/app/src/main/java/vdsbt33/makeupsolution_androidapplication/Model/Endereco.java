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
public class Endereco {
    
    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    private CidadeEndereco cidadeEndereco;

    public CidadeEndereco getCidadeEndereco() {
        return cidadeEndereco;
    }

    public void setCidadeEndereco(CidadeEndereco cidadeEndereco) {
        this.cidadeEndereco = cidadeEndereco;
    }
    
    private BairroEndereco bairroEndereco;

    public BairroEndereco getBairroEndereco() {
        return bairroEndereco;
    }

    public void setBairroEndereco(BairroEndereco bairroEndereco) {
        this.bairroEndereco = bairroEndereco;
    }
    
    private RuaEndereco ruaEndereco;

    public RuaEndereco getRuaEndereco() {
        return ruaEndereco;
    }

    public void setRuaEndereco(RuaEndereco ruaEndereco) {
        this.ruaEndereco = ruaEndereco;
    }
    
    private int numeroEndereco;
    
    public int getNumeroEndereco() {
        return numeroEndereco;
    }

    public void setNumeroEndereco(int numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }
    
    // Constructor

    public Endereco(Cliente cliente, CidadeEndereco cidadeEndereco, BairroEndereco bairroEndereco, RuaEndereco ruaEndereco, int numeroEndereco) {
        this.cliente = cliente;
        this.cidadeEndereco = cidadeEndereco;
        this.bairroEndereco = bairroEndereco;
        this.ruaEndereco = ruaEndereco;
        this.numeroEndereco = numeroEndereco;
    }

    @Override
    public String toString(){
        return String.format(getCidadeEndereco().getNomeCidadeEndereco() + ", " + getBairroEndereco().getNomeBairroEndereco() + ", " + getRuaEndereco().getNomeRuaEndereco() + ", " + numeroEndereco);
    }
    
}
