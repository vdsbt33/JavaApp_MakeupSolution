/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapp_makeupsolution.Controller;

import javaapp_makeupsolution.Model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vdsbt33
 */
public class ProdutoDAO {
    
    public static int Adicionar(Produto produto){
        String query = "INSERT INTO Produto (nomeProduto, precoProduto, quantidadeProduto, dataProduto ) VALUES ( ?, ?, ?, ? );";
        Connection conn = null;
        PreparedStatement pstm = null;
        int lastid = 0;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(query);
            pstm.setString(1, produto.getNomeProduto());
            pstm.setDouble(2, produto.getPrecoProduto());
            pstm.setInt(3, produto.getQuantidadeProduto());
            pstm.setString(4, AgendaDAO.LocalDateTimeToString(produto.getDataProduto()));
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
                System.out.println("Produto " + produto.getNomeProduto() + " adicionado com sucesso.");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            produto.setCodProduto(lastid);
            return lastid;
        }
        
    }
    
    public static List<Produto> SemanaListar(){
        String query =  "SELECT *\n" +
                        "FROM Produto\n" +
                        "WHERE  YEARWEEK(`dataProduto`, 1) = YEARWEEK(CURDATE(), 1)\n" +
                        "GROUP BY dataProduto DESC;";
        List<Produto> produtos = new ArrayList<Produto>();
        
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(query);
            rset = pstm.executeQuery();
            
            while (rset.next()){
                produtos.add(new Produto(rset.getInt("codProduto"), rset.getString("nomeProduto"), rset.getDouble("precoProduto"), rset.getInt("quantidadeProduto"), rset.getTimestamp("dataProduto").toLocalDateTime()));
                
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
        return produtos;
    }
    
    public static List<Produto> MesListar(){
        String data1 = LocalDateTime.now().getYear() + "-" + LocalDateTime.now().getMonthValue() + "-" + "1";
        String data2 = LocalDateTime.now().getYear() + "-" + LocalDateTime.now().getMonthValue() + "-" + LocalDateTime.now().getMonth().maxLength();
        String query =  "SELECT codProduto, nomeProduto, precoProduto, quantidadeProduto, dataProduto FROM Produto \n" +
                        "WHERE dataProduto BETWEEN '" + data1 + "' AND '" + data2 + "'\n" +
                        "GROUP BY dataProduto DESC;";
        return ProdutoDAO.getListProdutos(query);
    }
    
    public static List<Produto> AnoListar(){
        String data1 = LocalDateTime.now().getYear() + "-01-" + "1";
        String data2 = LocalDateTime.now().getYear() + "-12-" + LocalDateTime.now().getMonth().maxLength();
        String query =  "SELECT codProduto, nomeProduto, precoProduto, quantidadeProduto, dataProduto FROM Produto \n" +
                        "WHERE dataProduto BETWEEN '" + data1 + "' AND '" + data2 + "'\n" +
                        "GROUP BY dataProduto DESC;";
        System.out.print(data1 + " - " + data2);
        return ProdutoDAO.getListProdutos(query);
    }
    
    public static List<Produto> TodosListar(){
        String query =  "SELECT codProduto, nomeProduto, precoProduto, quantidadeProduto, dataProduto FROM Produto \n" +
                        "GROUP BY dataProduto DESC;";
        return ProdutoDAO.getListProdutos(query);
    }
    
    public static Produto getProdutoByID(int id){
        String query = "SELECT codProduto, nomeProduto, precoProduto, quantidadeProduto, dataProduto FROM Produto \n" +
                        "WHERE codProduto = " + id + ";";
        return ProdutoDAO.getListProdutos(query).get(0);
    }
    
    
    public static List<Produto> getListProdutoBetween(LocalDateTime dataInicio, LocalDateTime dataFim) throws Exception{
        String query =  "SELECT codProduto, nomeProduto, precoProduto, quantidadeProduto, dataProduto FROM Produto \n" +
                        "WHERE dataProduto BETWEEN '" + AgendaDAO.LocalDateTimeToString(dataInicio) + "' AND '" + AgendaDAO.LocalDateTimeToString(dataFim.plusMinutes(1439)) + "';";
        return ProdutoDAO.getListProdutos(query);
    }
    
    public static List<Produto> getListProdutos(String query){
        List<Produto> produtos = new ArrayList<Produto>();
        
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(query);
            rset = pstm.executeQuery();
            
            while (rset.next()){
                produtos.add(new Produto(rset.getInt("codProduto"), rset.getString("nomeProduto"), rset.getDouble("precoProduto"), rset.getInt("quantidadeProduto"), rset.getTimestamp("dataProduto").toLocalDateTime()));
                
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
        return produtos;
    }
    
    public static void Atualizar(Produto produto) {
        String query =  "UPDATE Produto\n" +
                        "SET nomeProduto = ?, precoProduto = ?, quantidadeProduto = ?, dataProduto = ?\n" +
                        "WHERE codProduto = ?;";
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(query);
            pstm.setString(1, produto.getNomeProduto());
            pstm.setDouble(2, produto.getPrecoProduto());
            pstm.setInt(3, produto.getQuantidadeProduto());
            pstm.setString(4, AgendaDAO.LocalDateTimeToString(produto.getDataProduto()));
            pstm.setInt(5, produto.getCodProduto());
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
                System.out.println("Produto atualizado.");
                
            } catch (Exception ex) {
                System.out.println("Erro ao atualizar Produto:");
                ex.printStackTrace();
            }
        }
    }
    
    public static void Remover(Produto produto){
        String query =  "DELETE FROM Produto\n" +
                        "WHERE codProduto = ?;";
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(query);
            pstm.setInt(1, produto.getCodProduto());
            pstm.execute();
            System.out.println("Produto removido.");
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
                System.out.println("Erro ao remover Produto:");
                ex.printStackTrace();
            }
        }
    }
    
    public static Boolean exists(int id){
        Boolean exists = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        String query =  "SELECT codProduto\n" +
                        "FROM Produto\n"+
                        "WHERE codProduto = ?";
        
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(query);
            pstm.setInt(1, id);
            rset = pstm.executeQuery();
            
            while (rset.next()){
                if (rset.getInt("codProduto") == id){
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
    
    
}
