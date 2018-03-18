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
import java.time.LocalDateTime;

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
        menu();
        
        
        ct.println("\nMakeupSolution encerrado.");
    }
    
    public static void menu() throws Exception{
        String op = "";
        
        while (!op.equals("sair")){
            ct.showTitleMessage();
            ct.showSubtitleMessage("Menu");
            ct.println("[clientes] [agenda] [produtos] [sair]\n");
            op = ct.printr("Escolha uma opção: ").toLowerCase();
            
            if (op.equals("clientes")){
                clientes();
            } else if (op.equals("agenda")) {
                agenda();
            } else if (op.equals("produtos")) {
                produtos();
            } else if (op.equals("sair")) {
                 op = sair();
            }
            
            ct.br(11);
        }
    }
    
    /* 
    CLIENTES
    */
    
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
    
    /* 
    AGENDA
    */
    
    public static void agenda() throws Exception{
        String op = "";
        
        while (!op.equals("voltar")){
            ct.br(11);
            ct.showTitleMessage();
            ct.showSubtitleMessage("Agenda");
            ct.println("[adicionar] [listar] [editar] [remover] [voltar]\n");
            op = ct.printr("Escolha uma opção: ").toLowerCase();
            
            if (op.equals("adicionar")){
                agenda_adicionar();
                
            } else if (op.equals("listar")){
                agenda_listar();
                
            } else if (op.equals("editar")){
                agenda_editar();
                
            } else if (op.equals("remover")){
                agenda_remover();
                
            }
        }
    }
    
    public static void agenda_adicionar() throws Exception{
        
        String op1 = "";
        
        while (!op1.equals("voltar")){
            
            ct.br(11);
            ct.showTitleMessage();
            ct.showSubtitleMessage("Agenda - Adicionar");
            
            int id = 0;
            
            op1 = ct.printr("Insira o ID do cliente ou voltar: ").toLowerCase();
            
            if (!op1.equals("voltar") && op1.substring(0, 1).matches("[0-9]")){
                if (ClienteDAO.exists(Integer.valueOf(op1))){
                    id = Integer.valueOf(op1);

                    LocalDateTime dataHoraAlvo = AgendaDAO.StringToLocalDateTime(ct.printr("Data e hora (Ex: " + AgendaDAO.LocalDateTimeToString(LocalDateTime.now()) + "): "));
                    double preco = Double.valueOf(ct.printr("Preço: "));

                    AgendaDAO.Adicionar(new Agenda(ClienteDAO.getClienteByID(id), preco, dataHoraAlvo));
                } else {
                    ct.println("\nNão há clientes com o id inserido.");
                }
                ct.pause();
            }
        }
    }
    
    public static void agenda_listar() {
        
        String op = "";
        
        while (!op.equals("voltar")){
            ct.br(11);
            ct.showTitleMessage();
            ct.showSubtitleMessage("Clientes - Listar");
            op = ct.printrln("\nDeseja exibir maquiagens ja feitas, ainda nao feitas ou voltar? [feitas] [agendadas] [voltar]");

            List<Agenda> agenda = new ArrayList<Agenda>();
            if (op.equals("feitas")){
                agenda = AgendaDAO.ListarAnteriores();
            } else if (op.equals("agendadas")){
                agenda = AgendaDAO.ListarProximas();
            }
            
            if (op.equals("feitas") || op.equals("agendadas")){
                ct.br();
                if (!agenda.isEmpty()){
                    ct.println("id. Nome : Custo | Data do registro / Data agendada \n");
                    for (int i = 0; i < agenda.size(); i++){
                        ct.println(agenda.get(i).getCodAgenda() + ". " + agenda.get(i).getCodCliente().getNome() + " : R$" + agenda.get(i).getValorAgenda() + " | " + AgendaDAO.LocalDateTimeToString(agenda.get(i).getDataHoraRegistradoAgenda()) + " / " + AgendaDAO.LocalDateTimeToString(agenda.get(i).getDataHoraAlvoAgenda()));
                    }
                } else {
                    ct.println("Não há registros na Agenda.");
                }
                ct.pause();
            }

        }
    }
    
    public static void agenda_editar() throws Exception{
        String op1 = "";
        
        while (!op1.equals("voltar")){
            ct.br(11);
            ct.showTitleMessage();
            ct.showSubtitleMessage("Clientes - Editar");
            op1 = ct.printr("Insira o ID do registro ou voltar: ").toLowerCase();
            
            int id = 0;
            
            if (!op1.equals("voltar") && op1.substring(0, 1).matches("[0-9]")){
                id = Integer.valueOf(op1);
                if (AgendaDAO.exists(id)){
                    List<Agenda> agenda = new ArrayList<Agenda>();
                    agenda = AgendaDAO.ListarPorId(id);
                    String op2 = "";
                    while (!op2.equals("voltar")){
                        ct.br(11);
                        ct.println("id. Nome : Custo | Data do registro / Data agendada");
                        ct.println(agenda.get(0).getCodAgenda() + ". " + agenda.get(0).getCodCliente().getNome() + " : R$" + agenda.get(0).getValorAgenda() + " | " + AgendaDAO.LocalDateTimeToString(agenda.get(0).getDataHoraRegistradoAgenda()) + " / " + AgendaDAO.LocalDateTimeToString(agenda.get(0).getDataHoraAlvoAgenda()));
                        
                        ct.println("\nEscolha o que deseja editar: ");
                        op2 = ct.printrln("[cliente] [custo] [data] [voltar]");

                        if (!op2.equals("voltar")){
                            
                            if (op2.equals("cliente")){
                                agenda.get(0).setCodCliente(ClienteDAO.getClienteByID(Integer.valueOf(ct.printr("ID Cliente: "))));
                                AgendaDAO.Atualizar(agenda.get(0));
                                
                            } else if (op2.equals("custo")){
                                agenda.get(0).setValorAgenda(Double.valueOf(ct.printr("Custo: ")));
                                AgendaDAO.Atualizar(agenda.get(0));
                                
                            } else if (op2.equals("data")){
                                agenda.get(0).setDataHoraAlvoAgenda(AgendaDAO.StringToLocalDateTime(ct.printr("Data e hora (Ex: " + AgendaDAO.LocalDateTimeToString(LocalDateTime.now()) + "): ")));
                                AgendaDAO.Atualizar(agenda.get(0));
                                
                            }
                            ct.pause();
                            op2 = "";
                        }
                        
                    }
                } else {
                    ct.println("\nNão há registros com o id inserido.");
                    ct.pause();
                }
            }
        }
        
    }
    
    public static void agenda_remover(){
        
        String op1 = "";
            
        while (!op1.equals("voltar")) {
            ct.br(11);
            ct.showTitleMessage();
            ct.showSubtitleMessage("Agenda - Remover");
            op1 = ct.printr("Insira o ID do registro ou voltar: ").toLowerCase();
            
            int id = 0;
            
            if (!op1.equals("voltar") && op1.substring(0, 1).matches("[0-9]")){
                if (AgendaDAO.exists(Integer.valueOf(op1))){
                    id = Integer.valueOf(op1);
                    List<Agenda> agenda = new ArrayList<Agenda>();
                    agenda = AgendaDAO.ListarPorId(id);
                    String op2 = "";

                    while (!op2.equals("sim") && !op2.equals("nao")){
                        ct.br(11);
                        ct.showTitleMessage();
                        ct.showSubtitleMessage("Agenda - Remover");
                        ct.println(agenda.get(0).getCodAgenda() + ". " + agenda.get(0).getCodCliente().getNome() + " : R$" + agenda.get(0).getValorAgenda() + " | " + AgendaDAO.LocalDateTimeToString(agenda.get(0).getDataHoraRegistradoAgenda()) + " / " + AgendaDAO.LocalDateTimeToString(agenda.get(0).getDataHoraAlvoAgenda()));

                        op2 = ct.printrln("\nDeseja realmente excluir esse relatório da agenda? [sim] [nao]");

                        if (op2.equals("sim")){
                            AgendaDAO.Remover(agenda.get(0));
                            ct.pause();
                        }
                    }
                } else {
                    ct.println("\nNão há registros com o id inserido.");
                    ct.pause();
                }
                
            }
        }
        
    }
    
    /* 
    PRODUTOS
    */
    
    public static void produtos(){
        String op = "";
        
        while (!op.equals("voltar")){
            ct.br(11);
            ct.showTitleMessage();
            ct.showSubtitleMessage("Relatórios de Produtos");
            ct.println("[adicionar] [listar] [editar] [remover] [voltar]\n");
            op = ct.printr("Escolha uma opção: ").toLowerCase();
            
            if (op.equals("adicionar")){
                produtos_adicionar();
            }else if (op.equals("listar")){
                produtos_listar();
            } else if (op.equals("editar")){
                produtos_editar();
            } else if(op.equals("remover")){
                produtos_remover();
            }
        }
        
    }
    
    public static void produtos_adicionar(){
        
        ct.br(11);
        ct.showTitleMessage();
        ct.showSubtitleMessage("Relatórios de Produtos - Adicionar");


        Produto produto = new Produto(ct.printr("Nome: "), Double.valueOf(ct.printr("\nPreço: ")), Integer.valueOf(ct.printr("\nQuantidade: ")), AgendaDAO.StringToLocalDateTime(ct.printr("Data e hora (Ex: " + AgendaDAO.LocalDateTimeToString(LocalDateTime.now()) + "): ")));
        ProdutoDAO.Adicionar(produto);
        
        ct.pause();
    }
    
    public static void produtos_listar(){
        String op = "";
        
        while (!op.equals("voltar")){
            ct.br(11);
            ct.showTitleMessage();
            ct.showSubtitleMessage("Relatórios de Produtos - Listar");
            op = ct.printrln("\nDeseja exibir relatórios de qual data? [semana] [mes] [ano] [todos] [voltar]");

            List<Produto> produtos = new ArrayList<Produto>();
            if (op.equals("semana")){
                produtos = ProdutoDAO.SemanaListar();
            } else if (op.equals("mes")){
                produtos = ProdutoDAO.MesListar();
            } else if (op.equals("ano")){
                produtos = ProdutoDAO.AnoListar();
            } else if (op.equals("todos")){
                produtos = ProdutoDAO.TodosListar();
            }
            
            if (op.equals("semana") || op.equals("mes") || op.equals("ano") || op.equals("todos")){
                ct.br();
                if (!produtos.isEmpty()){
                    ct.println("id. Nome : Preço - Quantidade | Data compra \n");
                    double total = 0.0;
                    for (int i = 0; i < produtos.size(); i++){
                        ct.println(produtos.get(i).getCodProduto() + ". " + produtos.get(i).getNomeProduto() + " : R$" + produtos.get(i).getPrecoProduto() + " - " + produtos.get(i).getQuantidadeProduto() + " | " + AgendaDAO.LocalDateTimeToString(produtos.get(i).getDataProduto()));
                        total += produtos.get(i).getPrecoProduto();
                    }
                    ct.println("Custo total: " + total + "\n");
                } else {
                    ct.println("Não há registros de compras para a opção selecionada.");
                }
                ct.pause();
            }

        }
    }
    
    public static void produtos_editar(){
        
        String op1 = "";
        
        while (!op1.equals("voltar")){
            ct.br(11);
            ct.showTitleMessage();
            ct.showSubtitleMessage("Relatórios de Produtos - Editar");
            op1 = ct.printr("Insira o ID do relatório ou voltar: ").toLowerCase();
            
            int id = 0;
            
            if (!op1.equals("voltar") && op1.substring(0, 1).matches("[0-9]")){
                id = Integer.valueOf(op1);
                if (ProdutoDAO.exists(id)){
                    Produto produto = ProdutoDAO.getProdutoByID(id);

                    String op2 = "";
                    while (!op2.equals("voltar")){
                        ct.br(11);
                        ct.println("\nid. Nome : Custo - Quantidade | Data compra");

                        ct.println(produto.getCodProduto() + ". " + produto.getNomeProduto() + " : R$" + produto.getPrecoProduto() + " - " + produto.getQuantidadeProduto() + " | " + AgendaDAO.LocalDateTimeToString(produto.getDataProduto()));
                        
                        ct.print("\nEscolha o que deseja editar:");

                        op2 = ct.printrln("\n[nome] [custo] [quantidade] [data] [voltar]").toLowerCase();

                        if (!op2.equals("voltar")){
                            
                            if (op2.equals("nome")){
                                produto.setNomeProduto(ct.printr("Nome anterior: " + produto.getNomeProduto() + "\nNome novo: "));
                            } else if (op2.equals("custo")){
                                produto.setPrecoProduto(Double.valueOf(ct.printr("Preço anterior: R$" + produto.getPrecoProduto() + "\nPreço novo: R$")));
                            } else if (op2.equals("quantidade")){
                                produto.setQuantidadeProduto(Integer.valueOf(ct.printr("Quantidade: " + produto.getQuantidadeProduto()+ "\nQuantidade nova: ")));
                            } else if (op2.equals("data")){
                                produto.setDataProduto(AgendaDAO.StringToLocalDateTime(ct.printr("Data de compra anterior: " + AgendaDAO.LocalDateTimeToString(produto.getDataProduto()) + "\nData de compra nova: ")));
                            }
                            ProdutoDAO.Atualizar(produto);
                            op2 = "";
                        }
                        
                    }
                } else {
                    ct.println("\nNão há relatórios com o id inserido.");
                    ct.pause();
                }
            }
            
        }
    }
    
    public static void produtos_remover(){
        Produto produto = null;
        String op1 = "";
            
        while (!op1.equals("voltar")) {
            ct.br(11);
            ct.showTitleMessage();
            ct.showSubtitleMessage("Relatórios de Produtos - Remover");
            op1 = ct.printr("Insira o ID do relatório ou voltar: ").toLowerCase();
            
            int id = 0;
            
            if (!op1.equals("voltar") && op1.substring(0, 1).matches("[0-9]")){
                id = Integer.valueOf(op1);
                if (ProdutoDAO.exists(id)){
                    produto = ProdutoDAO.getProdutoByID(id);
                    String op2 = "";
                    while (!op2.equals("sim") && !op2.equals("nao")){
                        ct.br(11);
                        ct.showTitleMessage();
                        ct.showSubtitleMessage("Relatórios de Produtos - Remover");
                        
                        ct.println("\nid. Nome : Custo - Quantidade | Data compra");
                        ct.println(produto.getCodProduto() + ". " + produto.getNomeProduto() + " : R$" + produto.getPrecoProduto() + " - " + produto.getQuantidadeProduto() + " | " + AgendaDAO.LocalDateTimeToString(produto.getDataProduto()));
                        
                        op2 = ct.printrln("\nDeseja realmente excluir esse relatório? [sim] [nao]");
                        
                        if (op2.equals("sim")){
                            ProdutoDAO.Remover(produto);
                            ct.pause();
                        }
                    }
                }
            }
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
