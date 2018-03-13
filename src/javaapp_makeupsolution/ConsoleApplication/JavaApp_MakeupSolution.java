/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapp_makeupsolution.ConsoleApplication;

import javaapp_makeupsolution.ConsoleApplication.lib.ConsoleText;
import javaapp_makeupsolution.ConsoleApplication.Controller.ConnectionFactory;
import java.sql.Connection;
import javaapp_makeupsolution.ConsoleApplication.Controller.*;
import javaapp_makeupsolution.ConsoleApplication.Model.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vdsbt33
 */
public class JavaApp_MakeupSolution {

    /**
     * @param args the command line arguments
     */
    
    public static ConsoleText ct = new ConsoleText();
    
    public static void main(String[] args) throws Exception {
        ct.setTitleMessage("MakeupSolution 0.1");
        menu(); // <<< this one should be a class, along with all others. Create object here then call it
        
        
        ct.println("\nMakeupSolution encerrado.");
    }
    
    public static void menu() throws Exception{
        String op = "";
        
        while (!op.equals("sair")){
            ct.showTitleMessage();
            ct.showSubtitleMessage("Menu");
            ct.println("[clientes] [produtos] [sair]\n");
            op = ct.printr("Escolha uma opção: ").toLowerCase();
            
            if (op.equals("clientes")){
                clientes();
            } else if (op.equals("produtos")) {
                produtos();
            } else if (op.equals("sair")) {
                 op = sair();
            }
            
            ct.br(11);
        }
    }
    
    public static void clientes() throws Exception{
        String op = "";
        
        while (!op.equals("voltar")){
            ct.br(11);
            ct.showTitleMessage();
            ct.showSubtitleMessage("Clientes");
            ct.println("[adicionar] [listar] [editar] [remover] [voltar]\n");
            op = ct.printr("Escolha uma opção: ").toLowerCase();
            
            if (op.equals("adicionar")){
                clientes_adicionar();
                
            } else if (op.equals("listar")){
                clientes_listar();
                
            } else if (op.equals("editar")){
                clientes_editar();
                
            } else if (op.equals("remover")){
                clientes_remover();
                
            }
        }
    }
    
    public static void clientes_adicionar() throws Exception{
        ct.br(11);
        ct.showTitleMessage();
        ct.showSubtitleMessage("Clientes - Adicionar");
        
        Cliente cliente = new Cliente(ct.printr("Nome: "), ct.printr("\nDescrição: "));
        ClienteDAO.Adicionar(cliente);
        
        String op = "";
        while (!op.equals("sim") && !op.equals("nao")){
            op = ct.printrln("\nDeseja adicionar um endereço? [sim] [nao]");
            
            ct.br(3);
            
            if (op.equals("sim")){
                
                CidadeEndereco cidadeEndereco = new CidadeEndereco(ct.printr("Cidade: "));
                BairroEndereco bairroEndereco = new BairroEndereco(cidadeEndereco, ct.printr("Bairro: "));
                RuaEndereco ruaEndereco = new RuaEndereco(bairroEndereco, ct.printr("Rua: "));
                Endereco endereco = new Endereco(cliente, cidadeEndereco, bairroEndereco, ruaEndereco, Integer.valueOf(ct.printr("Numero: ")));
                
                ct.br(3);
                CidadeEnderecoDAO.Adicionar(cidadeEndereco);
                BairroEnderecoDAO.Adicionar(bairroEndereco);
                RuaEnderecoDAO.Adicionar(ruaEndereco);
                EnderecoDAO.Adicionar(endereco);
                
                ct.pause();
            }
        }
        
        op = "";
    }
    
    public static void clientes_listar(){
        ct.br(11);
        ct.showTitleMessage();
        ct.showSubtitleMessage("Clientes - Listar");
        ct.println("\nid. Nome - Descrição @ Cidade, Bairro, Rua, Numero\n");
        
        List<Cliente> clientes = ClienteDAO.Listar();
        
        if (clientes.size() > 0){
            for (int i = 0; i < clientes.size(); i++){
                ct.print(String.valueOf(clientes.get(i).getCod()) + ". " + clientes.get(i).getNome() + " - " + clientes.get(i).getDescricao());
                ClienteDAO.getEnderecoCliente(clientes.get(i));
            }
        } else {
            ct.println("Não há clientes cadastrados.");
        }
        ct.pause();
    }
    
    public static void clientes_editar(){
        ct.br(11);
        ct.showTitleMessage();
        ct.showSubtitleMessage("Clientes - Editar");
        ct.pause();
    }
    
    public static void clientes_remover(){
        ct.br(11);
        ct.showTitleMessage();
        ct.showSubtitleMessage("Clientes - Remover");        
        ct.pause();
    }
    
    public static void produtos(){
        String op = "";
        
        while (!op.equals("voltar")){
            ct.br(11);
            ct.showTitleMessage();
            ct.showSubtitleMessage("Produtos");
            ct.println("[adicionar] [listar] [editar] [remover] [voltar]\n");
            op = ct.printr("Escolha uma opção: ").toLowerCase();
        }
        
    }
    
    public static String sair(){
        String op = "";
        
        while (!op.equals("sim") && !op.equals("nao")){
            ct.br(11);
            ct.showTitleMessage();
            ct.showSubtitleMessage("Sair");
            ct.println("Deseja realmente sair? [sim] [nao]");
            op = ct.read().toLowerCase();
        }
        if (op.equals("sim")){
            return "sair";
        }
        return "";
    }
    
}
