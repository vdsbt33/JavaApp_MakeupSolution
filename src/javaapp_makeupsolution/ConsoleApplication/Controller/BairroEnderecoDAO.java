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
    
    
}
