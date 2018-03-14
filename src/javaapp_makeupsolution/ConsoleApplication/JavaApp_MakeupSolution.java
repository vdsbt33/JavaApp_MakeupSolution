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
        
    }
    
    public static void clientes_listar(){
        ct.br(11);
        ct.showTitleMessage();
        ct.showSubtitleMessage("Clientes - Listar");
        ct.println("\nid. Nome - Descrição @ Cidade, Bairro, Rua, Numero\n");
        
        List<Cliente> clientes = ClienteDAO.Listar();
        
        if (clientes.size() > 0){
            for (int i = 0; i < clientes.size(); i++){
                
                if (clientes.get(i).getDescricao().isEmpty()){
                    ct.print(String.valueOf(clientes.get(i).getCod()) + ". " + clientes.get(i).getNome() + " - " + "[SEM DESCRIÇÃO]" + " @ ");
                } else {
                    ct.print(String.valueOf(clientes.get(i).getCod()) + ". " + clientes.get(i).getNome() + " - " + clientes.get(i).getDescricao() + " @ ");
                }
                Endereco endereco = ClienteDAO.getEnderecoCliente(clientes.get(i));
                if (endereco.getRuaEndereco() != null){
                    ct.println(endereco.getCidadeEndereco().getNomeCidadeEndereco() + ", " + endereco.getBairroEndereco().getNomeBairroEndereco() + ", " + endereco.getRuaEndereco().getNomeRuaEndereco() + ", " + endereco.getNumeroEndereco());
                } else {
                    ct.println("[NENHUM ENDEREÇO CADASTRADO]");
                }
            }
        } else {
            ct.println("Não há clientes cadastrados.");
        }
        ct.pause();
    }
    
    public static void clientes_editar() throws Exception{
        
        String op1 = "";
        
        while (!op1.equals("voltar")){
            ct.br(11);
            ct.showTitleMessage();
            ct.showSubtitleMessage("Clientes - Editar");
            op1 = ct.printr("Insira o ID do cliente ou voltar: ").toLowerCase();
            
            int id = 0;
            
            if (!op1.equals("voltar") && op1.substring(0, 1).matches("[0-9]")){
                id = Integer.valueOf(op1);
                if (ClienteDAO.exists(id)){
                    Cliente cliente = ClienteDAO.getClienteByID(id);
                    Endereco endereco = ClienteDAO.getEnderecoCliente(cliente);
                    //ct.println("\n" + endereco.getRuaEndereco().getNomeRuaEndereco() + "\n");

                    String op2 = "";
                    while (!op2.equals("voltar")){
                        ct.br(11);
                        ct.println("\nid. Nome - Descrição @ Cidade, Bairro, Rua, Numero");

                        if (cliente.getDescricao().isEmpty()){
                            ct.print(String.valueOf(cliente.getCod()) + ". " + cliente.getNome() + " - " + "[SEM DESCRIÇÃO]" + " @ ");
                        } else {
                            ct.print(String.valueOf(cliente.getCod()) + ". " + cliente.getNome() + " - " + cliente.getDescricao() + " @ ");
                        }

                        if (endereco != null && endereco.getRuaEndereco() != null){
                            ct.println("[" + endereco.getCidadeEndereco().getNomeCidadeEndereco() + ", " + endereco.getBairroEndereco().getNomeBairroEndereco() + ", " + endereco.getRuaEndereco().getNomeRuaEndereco() + ", " + endereco.getNumeroEndereco() + "]");
                        } else {
                            ct.println("[NENHUM ENDEREÇO CADASTRADO]");
                        }
                        
                        ct.print("\nEscolha o que deseja editar:");

                        if (endereco != null && endereco.getRuaEndereco() == null){
                            op2 = ct.printrln("\n[nome] [descricao] [endereco] [voltar]").toLowerCase();
                        } else {
                            op2 = ct.printrln("\n[nome] [descricao] [cidade] [bairro] [rua] [numero] [voltar]").toLowerCase();
                        }

                        if (!op2.equals("voltar")){
                            
                            if (op2.equals("nome")){
                                cliente.setNome(ct.printr("Nome anterior: " + cliente.getNome() + ". Nome novo: "));
                                ClienteDAO.Atualizar(cliente);
                            } else if (op2.equals("descricao")){
                                if (cliente.getDescricao().isEmpty()){
                                    cliente.setDescricao(ct.printr("Não possuia descrição. Descricao nova: "));
                                } else {
                                    cliente.setDescricao(ct.printr("Descricao anterior: " + cliente.getDescricao() + ". Descricao nova: "));
                                }
                                ClienteDAO.Atualizar(cliente);
                            } else if (op2.equals("endereco") && endereco.getRuaEndereco() == null){
                                CidadeEndereco cidadeEndereco = new CidadeEndereco(ct.printr("Cidade: "));
                                BairroEndereco bairroEndereco = new BairroEndereco(cidadeEndereco, ct.printr("Bairro: "));
                                RuaEndereco ruaEndereco = new RuaEndereco(bairroEndereco, ct.printr("Rua: "));
                                Endereco novoEndereco = new Endereco(cliente, cidadeEndereco, bairroEndereco, ruaEndereco, Integer.valueOf(ct.printr("Numero: ")));

                                ct.br(3);
                                CidadeEnderecoDAO.Adicionar(cidadeEndereco);
                                BairroEnderecoDAO.Adicionar(bairroEndereco);
                                RuaEnderecoDAO.Adicionar(ruaEndereco);
                                EnderecoDAO.Adicionar(novoEndereco);
                                endereco = ClienteDAO.getEnderecoCliente(cliente);
                                ct.pause();
                            } else if (endereco.getRuaEndereco() != null){
                                if (op2.equals("cidade")){
                                    endereco.getCidadeEndereco().setNomeCidadeEndereco(ct.printr("Cidade anterior: " + endereco.getCidadeEndereco().getNomeCidadeEndereco() + ". Cidade nova: "));
                                    CidadeEnderecoDAO.Atualizar(endereco.getCidadeEndereco());
                                } else if (op2.equals("bairro")){
                                    endereco.getBairroEndereco().setNomeBairroEndereco(ct.printr("Bairro anterior: " + endereco.getBairroEndereco().getNomeBairroEndereco() + ". Bairro novo: "));
                                    BairroEnderecoDAO.Atualizar(endereco.getBairroEndereco());
                                } else if (op2.equals("rua")) {
                                    endereco.getRuaEndereco().setNomeRuaEndereco(ct.printr("Rua anterior: " + endereco.getRuaEndereco().getNomeRuaEndereco() + ". Rua novo: "));
                                    RuaEnderecoDAO.Atualizar(endereco.getRuaEndereco());
                                } else if (op2.equals("numero")){
                                    endereco.setNumeroEndereco(Integer.valueOf(ct.printr("Numero anterior: " + String.valueOf(endereco.getNumeroEndereco()) + ". Numero novo: ")));
                                    EnderecoDAO.Atualizar(endereco);
                                }
                            }
                            op2 = "";
                        }
                        
                    }
                } else {
                    ct.println("\nNão há clientes com o id inserido.");
                    ct.pause();
                }
            }
            
        }
        
    }
    
    public static void clientes_remover(){
        
        Cliente cliente = null;
        Endereco endereco = null;
        String op1 = "";
            
        while (!op1.equals("voltar")) {
            ct.br(11);
            ct.showTitleMessage();
            ct.showSubtitleMessage("Clientes - Remover");
            op1 = ct.printr("Insira o ID do cliente ou voltar: ").toLowerCase();
            
            int id = 0;
            
            if (!op1.equals("voltar") && op1.substring(0, 1).matches("[0-9]") && ClienteDAO.exists(Integer.valueOf(op1))){
                id = Integer.valueOf(op1);
                if (ClienteDAO.exists(id)){
                    cliente = ClienteDAO.getClienteByID(id);
                    endereco = ClienteDAO.getEnderecoCliente(cliente);
                    String op2 = "";
                    while (!op2.equals("sim") && !op2.equals("nao")){
                        ct.br(11);
                        ct.showTitleMessage();
                        ct.showSubtitleMessage("Clientes - Remover");
                        if (cliente.getDescricao().isEmpty()){
                            ct.print(String.valueOf(cliente.getCod()) + ". " + cliente.getNome() + " - " + "[SEM DESCRIÇÃO]" + " @ ");
                        } else {
                            ct.print(String.valueOf(cliente.getCod()) + ". " + cliente.getNome() + " - " + cliente.getDescricao() + " @ ");
                        }
                        if (endereco.getRuaEndereco() != null){
                            ct.println(endereco.getCidadeEndereco().getNomeCidadeEndereco() + ", " + endereco.getBairroEndereco().getNomeBairroEndereco() + ", " + endereco.getRuaEndereco().getNomeRuaEndereco() + ", " + endereco.getNumeroEndereco());
                        } else {
                            ct.println("[NENHUM ENDEREÇO CADASTRADO]");
                        }
                        
                        op2 = ct.printrln("\nDeseja realmente excluir esse cliente? [sim] [nao]");
                        
                        if (op2.equals("sim")){
                            ClienteDAO.Remover(cliente);
                            ct.pause();
                        }
                    }
                }
            }
        }
        
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
