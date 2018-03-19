/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapp_makeupsolution.Controller;

import javaapp_makeupsolution.Model.Cliente;
import javaapp_makeupsolution.Model.Endereco;
import javaapp_makeupsolution.Model.CidadeEndereco;
import javaapp_makeupsolution.Model.BairroEndereco;
import javaapp_makeupsolution.Model.RuaEndereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vdsbt33
 */
public class ClienteDAO {
    
    public static int Adicionar(Cliente cliente) throws Exception{
        String query = "INSERT INTO Cliente (nomeCliente, descricaoCliente ) VALUES ( ?, ? );";
        Connection conn = null;
        PreparedStatement pstm = null;
        int lastid = 0;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(query);
            pstm.setString(1, cliente.getNome());
            pstm.setString(2, cliente.getDescricao());
            pstm.execute();
            lastid = ConnectionFactory.LastInsertID(conn, pstm);
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
                System.out.println("Cliente " + cliente.getNome() + " adicionado com sucesso.");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            cliente.setCod(lastid);
            return lastid;
        }
        
    }
    
    public static List<Cliente> Listar(){
        String query = "SELECT codCliente, nomeCliente, descricaoCliente FROM Cliente;";
        return ClienteDAO.getListClientes(query);
    }
    
    public static List<Cliente> getListClientes(String query){
        List<Cliente> clientes = new ArrayList<Cliente>();
        
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(query);
            rset = pstm.executeQuery();
            
            while (rset.next()){
                clientes.add(new Cliente(rset.getInt("codCliente"), rset.getString("nomeCliente"), rset.getString("descricaoCliente")));
                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rset != null){
                rset.close();
                }
                if (pstm != null){
                pstm.close();
                }
                if (conn != null){
                conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
        }
        return clientes;
    }
    
    public static void Atualizar(Cliente cliente) {
        String query =  "UPDATE Cliente\n" +
                        "SET nomeCliente = ?, descricaoCliente = ?\n" +
                        "WHERE codCliente = ?;";
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(query);
            pstm.setString(1, cliente.getNome());
            pstm.setString(2, cliente.getDescricao());
            pstm.setInt(3, cliente.getCod());
            pstm.execute();
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
                System.out.println("Cliente atualizado.");
                
            } catch (Exception ex) {
                System.out.println("Erro ao atualizar Cliente:");
                ex.printStackTrace();
            }
        }
    }
    
    public static void Remover(Cliente cliente){
        String query =  "DELETE FROM Cliente\n" +
                        "WHERE codCliente = ?;";
        Connection conn = null;
        PreparedStatement pstm = null;
        if (ClienteDAO.hasEndereco(cliente)){
            EnderecoDAO.Remover(ClienteDAO.getEnderecoCliente(cliente));
        }
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(query);
            pstm.setInt(1, cliente.getCod());
            pstm.execute();
            System.out.println("Cliente removido.");
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
                System.out.println("Erro ao remover Cliente:");
                ex.printStackTrace();
            }
        }
    }
    
    public static Boolean exists(int id){
        Boolean exists = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        String query =  "SELECT codCliente\n" +
                        "FROM Cliente\n"+
                        "WHERE codCliente = ?";
        
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(query);
            pstm.setInt(1, id);
            rset = pstm.executeQuery();
            
            while (rset.next()){
                if (rset.getInt("codCliente") == id){
                    exists = true;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rset != null){
                rset.close();
                }
                if (pstm != null){
                pstm.close();
                }
                if (conn != null){
                conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }   
        }
        
        return exists;
    }
    
    public static Boolean hasEndereco(Cliente cliente){
        Boolean hasEndereco = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        String query =  "SELECT ende.codRuaEndereco\n" +
                        "FROM Cliente cli\n" +
                        "JOIN Endereco ende ON ende.codCliente = cli.codCliente\n" +
                        "WHERE cli.codCliente = ?;";
        
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(query);
            pstm.setInt(1, cliente.getCod());
            rset = pstm.executeQuery();
            
            while (rset.next()){
                if (rset.getInt("ende.codRuaEndereco") >= 0){
                    hasEndereco = true;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rset != null){
                rset.close();
                }
                if (pstm != null){
                pstm.close();
                }
                if (conn != null){
                conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }   
        }
        
        return hasEndereco;
    }
    
    public static Cliente getClienteByID(int id){
        Cliente cliente = null;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        String query =  "SELECT codCliente, nomeCliente, descricaoCliente\n" +
                        "FROM Cliente\n"+
                        "WHERE codCliente = ?";
        
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(query);
            pstm.setInt(1, id);
            rset = pstm.executeQuery();
            
            while (rset.next()){
                cliente = new Cliente(rset.getInt("codCliente"), rset.getString("nomeCliente"), rset.getString("descricaoCliente"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rset != null){
                rset.close();
                }
                if (pstm != null){
                pstm.close();
                }
                if (conn != null){
                conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }   
        }
        
        return cliente;
    }
    
    public static Endereco getEnderecoCliente(Cliente cliente){
        Endereco endereco = null;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        String query =  "SELECT cid.codCidadeEndereco, cid.nomeCidadeEndereco, bar.codBairroEndereco, bar.nomeBairroEndereco, rua.codRuaEndereco, rua.nomeRuaEndereco, ende.numeroEndereco\n" +
                        "FROM Cliente cli\n" +
                        "LEFT JOIN Endereco ende ON ende.codCliente = cli.codCliente\n" +
                        "LEFT JOIN CidadeEndereco cid ON cid.codCidadeEndereco = ende.codCidadeEndereco\n" +
                        "LEFT JOIN BairroEndereco bar ON bar.codBairroEndereco = cid.codCidadeEndereco\n" +
                        "LEFT JOIN RuaEndereco rua ON rua.codRuaEndereco = bar.codBairroEndereco\n" +
                        "WHERE cli.codCliente = ?;";
        
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(query);
            pstm.setInt(1, cliente.getCod());
            rset = pstm.executeQuery();
            CidadeEndereco cidadeEndereco = null;
            BairroEndereco bairroEndereco = null;
            RuaEndereco ruaEndereco = null;
            int count = 0;
            while (rset.next()){
                if (rset.getRow() == 0 && count == 0){
                    return null;
                }
                cidadeEndereco = CidadeEnderecoDAO.getCidadeEnderecoByID(rset.getInt("codCidadeEndereco"));
                bairroEndereco = BairroEnderecoDAO.getBairroEnderecoByID(rset.getInt("codBairroEndereco"));
                ruaEndereco = RuaEnderecoDAO.getRuaEnderecoByID(rset.getInt("codRuaEndereco"));
                
                endereco = new Endereco(cliente, cidadeEndereco, bairroEndereco, ruaEndereco, rset.getInt("ende.numeroEndereco"));
                count++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rset != null){
                rset.close();
                }
                if (pstm != null){
                pstm.close();
                }
                if (conn != null){
                conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }   
        }
        
        return endereco;
        
    }
    
}
