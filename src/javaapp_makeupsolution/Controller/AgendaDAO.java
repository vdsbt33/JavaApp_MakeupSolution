/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapp_makeupsolution.Controller;

import javaapp_makeupsolution.Model.Agenda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author vdsbt33
 */
public class AgendaDAO {
    
    public static int Adicionar(Agenda agenda){
        String query =  "INSERT INTO Agenda (\n" +
                        "  codCliente,\n" +
                        "  valorAgenda,\n" +
                        "  dataHoraRegistradoAgenda,\n" +
                        "  dataHoraAlvoAgenda\n" +
                        ") VALUES (\n" +
                        "  ?,\n" +
                        "  ?,\n" +
                        "  ?,\n" +
                        "  ?\n" +
                        ");";
        Connection conn = null;
        PreparedStatement pstm = null;
        int lastid = 0;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(query);
            pstm.setInt(1, agenda.getCodCliente().getCod());
            pstm.setDouble(2, agenda.getValorAgenda());
            pstm.setString(3, AgendaDAO.LocalDateTimeToString(agenda.getDataHoraRegistradoAgenda()));
            pstm.setString(4, AgendaDAO.LocalDateTimeToString(agenda.getDataHoraAlvoAgenda()));
            pstm.execute();
            lastid = ConnectionFactory.LastInsertID(conn, pstm);
            System.out.println("Agenda atualizada.");
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
                System.out.println("Erro ao atualizar Agenda:");
                ex.printStackTrace();
            }
            agenda.setCodAgenda(lastid);
            return lastid;
        }
    }
    
    public static void Atualizar(Agenda agenda) {
        String query =  "UPDATE Agenda\n" +
                        "SET codCliente = ?, valorAgenda = ?, dataHoraAlvoAgenda = ?\n" +
                        "WHERE codAgenda = ?;";
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(query);
            pstm.setInt(1, agenda.getCodCliente().getCod());
            pstm.setDouble(2, agenda.getValorAgenda());
            pstm.setString(3, AgendaDAO.LocalDateTimeToString(agenda.getDataHoraAlvoAgenda()));
            pstm.setInt(4, agenda.getCodAgenda());
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
                System.out.println("Agenda atualizada.");
                
            } catch (Exception ex) {
                System.out.println("Erro ao atualizar Agenda:");
                ex.printStackTrace();
            }
        }
    }
    
    public static void Remover(Agenda agenda){
        String query =  "DELETE FROM Agenda\n" +
                        "WHERE codAgenda = ?;";
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(query);
            pstm.setInt(1, agenda.getCodAgenda());
            pstm.execute();
            System.out.println("Relatório da Agenda removido.");
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
                System.out.println("Erro ao remover relatório da Agenda:");
                ex.printStackTrace();
            }
        }
    }
    
    public static List<Agenda> ListarAnteriores(){
        String query =  "SELECT age.codAgenda, age.codCliente, cli.nomeCliente, cli.descricaoCliente, age.dataHoraAlvoAgenda, age.valorAgenda, age.dataHoraRegistradoAgenda\n" +
                        "FROM Agenda age\n" +
                        "LEFT JOIN Cliente cli ON cli.codCliente = age.codCliente\n" +
                        "WHERE age.dataHoraAlvoAgenda < current_timestamp()\n" +
                        "ORDER BY age.dataHoraAlvoAgenda ASC;";
        return AgendaDAO.getListAgenda(query);
    }
    
    public static List<Agenda> ListarProximas(){
        String query =  "SELECT age.codAgenda, age.codCliente, cli.nomeCliente, cli.descricaoCliente, age.dataHoraAlvoAgenda, age.valorAgenda, age.dataHoraRegistradoAgenda\n" +
                        "FROM Agenda age\n" +
                        "LEFT JOIN Cliente cli ON cli.codCliente = age.codCliente\n" +
                        "WHERE age.dataHoraAlvoAgenda > current_timestamp()\n" +
                        "ORDER BY age.dataHoraAlvoAgenda ASC;";
        return AgendaDAO.getListAgenda(query);
    }
    
    public static List<Agenda> ListarPorId(int id){
        String query =  "SELECT age.codAgenda, age.codCliente, cli.nomeCliente, cli.descricaoCliente, age.dataHoraAlvoAgenda, age.valorAgenda, age.dataHoraRegistradoAgenda\n" +
                        "FROM Agenda age\n" +
                        "LEFT JOIN Cliente cli ON cli.codCliente = age.codCliente\n" +
                        "WHERE age.codAgenda = " + id + "\n" +
                        "ORDER BY age.dataHoraAlvoAgenda ASC;";
        return getListAgenda(query);
    }
    
    public static List<Agenda> getListAgenda(String query){
        List<Agenda> agenda = new ArrayList<Agenda>();
        
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(query);
            rset = pstm.executeQuery();
            
            while (rset.next()){
                agenda.add(new Agenda(rset.getInt("age.codAgenda"), ClienteDAO.getClienteByID(rset.getInt("age.codCliente")), rset.getDouble("age.valorAgenda"), rset.getTimestamp("age.dataHoraRegistradoAgenda").toLocalDateTime(), rset.getTimestamp("age.dataHoraAlvoAgenda").toLocalDateTime()));
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
        return agenda;
    }
    
    public static List<Agenda> getListAgendaBetween(LocalDateTime dataInicio, LocalDateTime dataFim) throws Exception{
        String query =  "SELECT age.codAgenda, age.codCliente, age.valorAgenda, age.dataHoraRegistradoAgenda, age.dataHoraAlvoAgenda\n" +
                        "FROM Agenda age\n" +
                        "LEFT JOIN Cliente cli ON cli.codCliente = age.codCliente\n" +
                        "WHERE age.dataHoraAlvoAgenda BETWEEN '" + AgendaDAO.LocalDateTimeToString(dataInicio) + "' AND '" + AgendaDAO.LocalDateTimeToString(dataFim) + "';";

        
        return AgendaDAO.getListAgenda(query);
    }
    
    public static Boolean exists(int id){
        Boolean exists = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        String query =  "SELECT codAgenda\n" +
                        "FROM Agenda\n"+
                        "WHERE codAgenda = ?";
        
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(query);
            pstm.setInt(1, id);
            rset = pstm.executeQuery();
            
            while (rset.next()){
                if (rset.getInt("codAgenda") == id){
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
    
    public static LocalDateTime StringToLocalDateTime(String date){
        String str = date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        
        return dateTime;
    }
    
    public static String LocalDateTimeToString(LocalDateTime ldt){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.of(ldt.getYear(), ldt.getMonthValue(), ldt.getDayOfMonth(), ldt.getHour(), ldt.getMinute(), ldt.getSecond());
        String formattedDateTime = dateTime.format(formatter);
        
        return formattedDateTime;
    }
    
    public static LocalDateTime DateToLocalDateTime(Date date) {
        
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
    
    public static Date LocalDateTimeToDate(LocalDateTime ldt){
        
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }
}
