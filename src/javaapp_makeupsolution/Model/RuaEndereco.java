/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapp_makeupsolution.Model;

/**
 *
 * @author vdsbt33
 */
public class RuaEndereco {
    
    private int codRuaEndereco;

    public int getCodRuaEndereco() {
        return codRuaEndereco;
    }

    public void setCodRuaEndereco(int codRuaEndereco) {
        this.codRuaEndereco = codRuaEndereco;
    }
    
    private BairroEndereco bairroEndereco;

    public BairroEndereco getBairroEndereco() {
        return bairroEndereco;
    }

    public void setBairroEndereco(BairroEndereco bairroEndereco) {
        this.bairroEndereco = bairroEndereco;
    }
    
    private String nomeRuaEndereco;

    public String getNomeRuaEndereco() {
        return nomeRuaEndereco;
    }

    public void setNomeRuaEndereco(String nomeRuaEndereco) {
        this.nomeRuaEndereco = nomeRuaEndereco;
    }
    
    // Constructors
    public RuaEndereco(BairroEndereco bairroEndereco, String nomeRuaEndereco) {
        this.bairroEndereco = bairroEndereco;
        this.nomeRuaEndereco = nomeRuaEndereco;
    }

    public RuaEndereco(int codRuaEndereco, BairroEndereco bairroEndereco, String nomeRuaEndereco) {
        this.codRuaEndereco = codRuaEndereco;
        this.bairroEndereco = bairroEndereco;
        this.nomeRuaEndereco = nomeRuaEndereco;
    }
    
}
