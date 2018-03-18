/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapp_makeupsolution.ConsoleApplication.Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

/**
 *
 * @author vdsbt33
 */
public class Produto {

    public Produto(String nomeProduto, double precoProduto, int quantidadeProduto, LocalDateTime dataProduto) {
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
        this.quantidadeProduto = quantidadeProduto;
        this.dataProduto = dataProduto;
    }

    public Produto(int codProduto, String nomeProduto, double precoProduto, int quantidadeProduto, LocalDateTime dataProduto) {
        this.codProduto = codProduto;
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
        this.quantidadeProduto = quantidadeProduto;
        this.dataProduto = dataProduto;
    }

    
    
    private int codProduto;

    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }
    
    private String nomeProduto;

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
    
    private double precoProduto;

    public double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(double precoProduto) {
        this.precoProduto = precoProduto;
    }
    
    private int quantidadeProduto;

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }
    
    public LocalDateTime dataProduto;

    public LocalDateTime getDataProduto() {
        return dataProduto;
    }

    public void setDataProduto(LocalDateTime dataProduto) {
        this.dataProduto = dataProduto;
    }
    
    
    
    
}
