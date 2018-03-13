/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapp_makeupsolution.ConsoleApplication.Controller;

import javaapp_makeupsolution.ConsoleApplication.Model.RuaEndereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javaapp_makeupsolution.ConsoleApplication.Model.RuaEndereco;

/**
 *
 * @author vdsbt33
 */
public class RuaEnderecoDAO {
    
    public static int Adicionar(RuaEndereco ruaEndereco) throws Exception{
        String query = "INSERT INTO RuaEndereco ( codBairroEndereco, nomeRuaEndereco ) VALUES ( ?, ? );";
        Connection conn = null;
        PreparedStatement pstm = null;
        int lastid = 0;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(query);
            pstm.setInt(1, ruaEndereco.getBairroEndereco().getCodBairroEndereco());
            pstm.setString(2, ruaEndereco.getNomeRuaEndereco());
            pstm.execute();
            lastid = ConnectionFactory.LastInsertID(conn, pstm);
            System.out.println("Rua armazenada.");
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
                System.out.println("Erro ao armazenar Rua:");
                ex.printStackTrace();
            }
            ruaEndereco.setCodRuaEndereco(lastid);
            return lastid;
        }
    }
    
    public static RuaEndereco getRuaEnderecoByID(int id){
        
        RuaEndereco ruaEndereco = null;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        String query =  "SELECT codRuaEndereco, codBairroEndereco, nomeRuaEndereco\n" +
                        "FROM RuaEndereco\n" +
                        "WHERE codRuaEndereco = ?;";
        
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(query);
            pstm.setInt(1, id);
            rset = pstm.executeQuery();
            while (rset.next()){
                ruaEndereco = new RuaEndereco(rset.getInt("codRuaEndereco"), BairroEnderecoDAO.getBairroEnderecoByID(rset.getInt("codBairroEndereco")), rset.getString("nomeRuaEndereco"));
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
        
        return ruaEndereco;
    }
    
}
