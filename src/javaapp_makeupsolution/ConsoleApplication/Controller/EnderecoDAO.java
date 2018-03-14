/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapp_makeupsolution.ConsoleApplication.Controller;

import javaapp_makeupsolution.ConsoleApplication.Model.Endereco;
import javaapp_makeupsolution.ConsoleApplication.Model.CidadeEndereco;
import javaapp_makeupsolution.ConsoleApplication.Model.BairroEndereco;
import javaapp_makeupsolution.ConsoleApplication.Model.RuaEndereco;
import javaapp_makeupsolution.ConsoleApplication.Model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vdsbt33
 */
public class EnderecoDAO {
    
    public static void Adicionar(Endereco endereco) throws Exception{
        String query = "INSERT INTO Endereco ( codCliente, codCidadeEndereco, codBairroEndereco, codRuaEndereco, numeroEndereco ) VALUES ( ?, ?, ?, ?, ? );";
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(query);
            pstm.setInt(1, endereco.getCliente().getCod());
            pstm.setInt(2, endereco.getCidadeEndereco().getCodCidadeEndereco());
            pstm.setInt(3, endereco.getBairroEndereco().getCodBairroEndereco());
            pstm.setInt(4, endereco.getRuaEndereco().getCodRuaEndereco());
            pstm.setInt(5, endereco.getNumeroEndereco());
            pstm.execute();
            System.out.println("Endereço armazenado com sucesso.");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try{
                if (pstm != null){
                    pstm.close();
                }
                if (conn != null){
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println("Erro ao unir endereço e armazená-lo:");
                ex.printStackTrace();
            }
        }
    }
    
    public static void Atualizar(Endereco endereco){
        String query =  "UPDATE Endereco\n" +
                        "SET numeroEndereco = ?\n" +
                        "WHERE codCliente = ?;";
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(query);
            pstm.setInt(1, endereco.getNumeroEndereco());
            pstm.setInt(2, endereco.getCliente().getCod());
            pstm.execute();
            System.out.println("Número atualizado.");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try{
                if (pstm != null){
                    pstm.close();
                }
                if (conn != null){
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println("Erro ao atualizar Número:");
                ex.printStackTrace();
            }
        }
    }
    
    public static void Remover(Endereco endereco){
        String query =  "DELETE FROM Endereco\n" +
                        "WHERE codCliente = ?;";
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(query);
            pstm.setInt(1, endereco.getCliente().getCod());
            pstm.execute();
            System.out.println("Endereço removido.");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try{
                if (pstm != null){
                    pstm.close();
                }
                if (conn != null){
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println("Erro ao remover Endereço:");
                ex.printStackTrace();
            }
        }
        
        RuaEnderecoDAO.Remover(endereco.getRuaEndereco());
        BairroEnderecoDAO.Remover(endereco.getBairroEndereco());
        CidadeEnderecoDAO.Remover(endereco.getCidadeEndereco());
    }
    
}
