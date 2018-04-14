package vdsbt33.makeupsolution_androidapplication.Model;


public class Contato {

    public Contato(String valorContato) {
        this.valorContato = valorContato;
    }

    public Contato(Cliente codCliente, String valorContato) {
        this.codCliente = codCliente;
        this.valorContato = valorContato;
    }

    private Cliente codCliente;

    public Cliente getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Cliente codCliente) {
        this.codCliente = codCliente;
    }

    private String valorContato;

    public String getValorContato() {
        return valorContato;
    }

    public void setValorContato(String valorContato) {
        this.valorContato = valorContato;
    }

    @Override
    public String toString() {
        return String.format(getValorContato());
    }

}
