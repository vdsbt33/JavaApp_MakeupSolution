/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vdsbt33.makeupsolution_androidapplication.Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import vdsbt33.makeupsolution_androidapplication.Model.Cliente;

/**
 *
 * @author vdsbt33
 */
public class Agenda {

    public Agenda(Cliente codCliente, double valorAgenda, LocalDateTime dataHoraAlvoAgenda) {
        this.codCliente = codCliente;
        this.valorAgenda = valorAgenda;
        this.dataHoraAlvoAgenda = dataHoraAlvoAgenda;
        dataHoraRegistradoAgenda = LocalDateTime.now();
        
    }
    
    public Agenda(Cliente codCliente, double valorAgenda, LocalDateTime dataHoraRegistradoAgenda, LocalDateTime dataHoraAlvoAgenda) {
        this.codCliente = codCliente;
        this.valorAgenda = valorAgenda;
        this.dataHoraRegistradoAgenda = dataHoraRegistradoAgenda;
        this.dataHoraAlvoAgenda = dataHoraAlvoAgenda;
    }

    public Agenda(int codAgenda, Cliente codCliente, double valorAgenda, LocalDateTime dataHoraRegistradoAgenda, LocalDateTime dataHoraAlvoAgenda) {
        this.codAgenda = codAgenda;
        this.codCliente = codCliente;
        this.valorAgenda = valorAgenda;
        this.dataHoraRegistradoAgenda = dataHoraRegistradoAgenda;
        this.dataHoraAlvoAgenda = dataHoraAlvoAgenda;
    }
    
    
    private int codAgenda;

    public int getCodAgenda() {
        return codAgenda;
    }

    public void setCodAgenda(int codAgenda) {
        this.codAgenda = codAgenda;
    }
    
    private Cliente codCliente;

    public Cliente getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Cliente codCliente) {
        this.codCliente = codCliente;
    }
    
    private double valorAgenda;

    public double getValorAgenda() {
        return valorAgenda;
    }

    public void setValorAgenda(double valorAgenda) {
        this.valorAgenda = valorAgenda;
    }
    
    private LocalDateTime dataHoraRegistradoAgenda;

    public LocalDateTime getDataHoraRegistradoAgenda() {
        return dataHoraRegistradoAgenda;
    }
    
    public String getStringDataHoraRegistradoAgenda() {
        java.text.SimpleDateFormat sdf = 
            new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(dataHoraRegistradoAgenda);
    }

    public void setDataHoraRegistradoAgenda(LocalDateTime dataHoraRegistradoAgenda) {
        this.dataHoraRegistradoAgenda = dataHoraRegistradoAgenda;
    }
    
    private LocalDateTime dataHoraAlvoAgenda;

    public LocalDateTime getDataHoraAlvoAgenda() {
        return dataHoraAlvoAgenda;
    }

    public void setDataHoraAlvoAgenda(LocalDateTime dataHoraAlvoAgenda) {
        this.dataHoraAlvoAgenda = dataHoraAlvoAgenda;
    }
    
}
