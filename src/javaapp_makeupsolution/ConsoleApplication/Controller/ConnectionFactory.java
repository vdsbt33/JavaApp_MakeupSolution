/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapp_makeupsolution.ConsoleApplication.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author vdsbt33
 */
public class ConnectionFactory {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";
    
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/makeupsolution?autoReconnect=true&useSSL=false";
    
    public static Connection createConnectionToMySQL() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        
        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        
        return connection;
    }
    
    public static int LastInsertID(Connection conn, PreparedStatement pstm) throws Exception{
        ResultSet rset = null;
        int lastid = 0;
        String query = "SELECT LAST_INSERT_ID();";
        try {
            pstm = conn.prepareStatement(query);
            rset = pstm.executeQuery();
            
            while (rset.next()){
                lastid = rset.getInt(1);
                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lastid;
    }
    
}