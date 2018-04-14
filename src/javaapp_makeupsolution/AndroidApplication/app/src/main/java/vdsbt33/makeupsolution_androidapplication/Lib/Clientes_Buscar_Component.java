package vdsbt33.makeupsolution_androidapplication.Lib;

import java.util.List;

import vdsbt33.makeupsolution_androidapplication.Model.Cliente;
import vdsbt33.makeupsolution_androidapplication.Model.Contato;
import vdsbt33.makeupsolution_androidapplication.Model.Endereco;

public class Clientes_Buscar_Component {

    private Cliente cliente;
    private List<Contato> contatos;
    private Endereco endereco;

    public Clientes_Buscar_Component(Cliente cliente, List<Contato> contatos, Endereco endereco) {
        this.cliente = cliente;
        this.contatos = contatos;
        this.endereco = endereco;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}