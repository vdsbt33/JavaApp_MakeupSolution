/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapp_makeupsolution.ConsoleApplication.Controller;

import javaapp_makeupsolution.ConsoleApplication.Model.BairroEndereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javaapp_makeupsolution.ConsoleApplication.Model.BairroEndereco;

/**
 *
 * @author vdsbt33
 */
public class BairroEnderecoDAO {
    
    public static int Adicionar(BairroEndereco bairroEndereco) throws Exception{
        String query = "INSERT INTO BairroEndereco ( codCidadeEndereco, nomeBairroEndereco ) VALUES ( ?, ? );";
        Connection conn = null;
        PreparedStatement pstm = null;
        int lastid = 0;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(query);
            pstm.setInt(1, bairroEndereco.getCidadeEndereco().getCodCidadeEndereco());
            pstm.setString(2, bairroEndereco.getNomeBairroEndereco());
            pstm.execute();
            lastid = ConnectionFactory.LastInsertID(conn, pstm);
            System.out.println("Bairro armazenado.");
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
                System.out.println("Erro ao armazenar Bairro:");
                ex.printStackTrace();
            }
            bairroEndereco.setCodBairroEndereco(lastid);
            return lastid;
        }    
    }
    
    public static BairroEndereco getBairroEnderecoByID(int id){
        
        BairroEndereco bairroEndereco = null;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        String query =  "SELECT codBairroEndereco, codCidadeEndereco, nomeBairroEndereco\n" +
                        "FROM BairroEndereco\n" +
                        "WHERE codBairroEndereco = ?;";
        
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(query);
            pstm.setInt(1, id);
            rset = pstm.executeQuery();
            while (rset.next()){
                
                bairroEndereco = new BairroEndereco(rset.getInt("codBairroEndereco"), CidadeEnderecoDAO.getCidadeEnderecoByID(rset.getInt("codCidadeEndereco")), rset.getString("nomeBairroEndereco"));
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
        
        return bairroEndereco;
    }
    
}
