/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapp_makeupsolution.ConsoleApplication.Controller;

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
    
    public static void getEnderecoCliente(Cliente cliente){
                
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        String query = "SELECT cid.nomeCidadeEndereco, bar.nomeBairroEndereco, rua.nomeRuaEndereco, ende.numeroEndereco " +
                        "FROM Cliente cli " +
                        "LEFT JOIN Endereco ende ON ende.codCliente = cli.codCliente " +
                        "LEFT JOIN CidadeEndereco cid ON cid.codCidadeEndereco = ende.codCidadeEndereco " +
                        "LEFT JOIN BairroEndereco bar ON bar.codBairroEndereco = cid.codCidadeEndereco " +
                        "LEFT JOIN RuaEndereco rua ON rua.codRuaEndereco = bar.codBairroEndereco " +
                        "WHERE cli.codCliente = ?;";
        
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(query);
            pstm.setInt(1, cliente.getCod());
            rset = pstm.executeQuery();
            
            while (rset.next()){
                System.out.println(" @ " + rset.getString("cid.nomeCidadeEndereco") + ", " + rset.getString("bar.nomeBairroEndereco") + ", " + rset.getString("rua.nomeRuaEndereco") + ", " + rset.getInt("ende.numeroEndereco"));
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
        
    }
    
}
