/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapp_makeupsolution.ConsoleApplication.Controller;

import javaapp_makeupsolution.ConsoleApplication.Model.CidadeEndereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javaapp_makeupsolution.ConsoleApplication.Model.BairroEndereco;
import javaapp_makeupsolution.ConsoleApplication.Model.Endereco;
import javaapp_makeupsolution.ConsoleApplication.Model.RuaEndereco;

/**
 *
 * @author vdsbt33
 */
public class CidadeEnderecoDAO {
    
    public static int Adicionar(CidadeEndereco cidadeEndereco) throws Exception{
        String query = "INSERT INTO CidadeEndereco ( nomeCidadeEndereco ) VALUES ( ? );";
        Connection conn = null;
        PreparedStatement pstm = null;
        int lastid = 0;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(query);
            pstm.setString(1, cidadeEndereco.getNomeCidadeEndereco());
            pstm.execute();
            lastid = ConnectionFactory.LastInsertID(conn, pstm);
            System.out.println("Cidade armazenada.");
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
                System.out.println("Erro ao armazenar Cidade:");
                ex.printStackTrace();
            }
            cidadeEndereco.setCodCidadeEndereco(lastid);
            return lastid;
        }   
    }
    
    public static void Atualizar(CidadeEndereco cidadeEndereco){
        String query =  "UPDATE CidadeEndereco\n" +
                        "SET nomeCidadeEndereco = ?\n" +
                        "WHERE codCidadeEndereco = ?;";
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(query);
            pstm.setString(1, cidadeEndereco.getNomeCidadeEndereco());
            pstm.setInt(2, cidadeEndereco.getCodCidadeEndereco());
            pstm.execute();
            System.out.println("Cidade atualizada.");
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
                System.out.println("Erro ao atualizar Cidade:");
                ex.printStackTrace();
            }
            
        }
    }
    
    public static CidadeEndereco getCidadeEnderecoByID(int id){
        
        CidadeEndereco cidadeEndereco = null;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        String query =  "SELECT codCidadeEndereco, nomeCidadeEndereco\n" +
                        "FROM CidadeEndereco\n" +
                        "WHERE codCidadeEndereco = ?;";
        
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(query);
            pstm.setInt(1, id);
            rset = pstm.executeQuery();
            while (rset.next()){
                
                cidadeEndereco = new CidadeEndereco(rset.getInt("codCidadeEndereco"), rset.getString("nomeCidadeEndereco"));
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
        
        return cidadeEndereco;
    }
}
