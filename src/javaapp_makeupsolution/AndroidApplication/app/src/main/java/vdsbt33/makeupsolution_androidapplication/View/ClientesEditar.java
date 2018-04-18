package vdsbt33.makeupsolution_androidapplication.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import vdsbt33.makeupsolution_androidapplication.Lib.Clientes_Buscar_Component;
import vdsbt33.makeupsolution_androidapplication.Lib.Contato_Adicionar_Component_Adapter;
import vdsbt33.makeupsolution_androidapplication.R;

public class ClientesEditar extends AppCompatActivity {

    private Clientes_Buscar_Component cliente;

    public Clientes_Buscar_Component getCliente() {
        return cliente;
    }

    public void setCliente(Clientes_Buscar_Component cliente) {
        this.cliente = cliente;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_clientes_editar);

        // Updating fields
        // Cliente
        ((EditText) findViewById(R.id.nomeTbox)).setText(cliente.getCliente().getNome());
        ((EditText) findViewById(R.id.descricaoTbox)).setText(cliente.getCliente().getDescricao());

        // Contato
        if (cliente.getContatos() != null){
            ((CheckBox) findViewById(R.id.cboxContato)).setChecked(true);
            containerContato_Toggle();

            ListView contatoListEditar = findViewById(R.id.contatoListEditar);
            Contato_Adicionar_Component_Adapter adapter = new Contato_Adicionar_Component_Adapter(getBaseContext(), cliente.getContatos());

            for (int i = 0; i < cliente.getContatos().size(); i++){

            }
        }

    }


    public void containerEndereco_Toggle(){
        CheckBox cboxEndereco = (CheckBox) findViewById(R.id.cboxEndereco);
        View containerEndereco = findViewById(R.id.containerEndereco);

        if (!cboxEndereco.isChecked()){
            containerEndereco.setVisibility(View.GONE);
        } else {
            containerEndereco.setVisibility(View.VISIBLE);
        }
    }

    public void containerContato_Toggle(){
        CheckBox cboxContato = (CheckBox) findViewById(R.id.cboxContato);
        View containerContato = findViewById(R.id.containerContato);

        if (!cboxContato.isChecked()){
            containerContato.setVisibility(View.GONE);
        } else {
            containerContato.setVisibility(View.VISIBLE);
        }
    }

}
