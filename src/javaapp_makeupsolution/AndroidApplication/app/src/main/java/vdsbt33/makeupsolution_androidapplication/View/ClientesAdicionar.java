package vdsbt33.makeupsolution_androidapplication.View;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import vdsbt33.makeupsolution_androidapplication.Controller.ClienteDAO;
import vdsbt33.makeupsolution_androidapplication.Model.Cliente;
import vdsbt33.makeupsolution_androidapplication.R;

public class ClientesAdicionar extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes_adicionar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        containerEndereco_Toggle();
        findViewById(R.id.cboxEndereco).setOnClickListener(this);

    }

    public void onClick(View view){
        if (view == findViewById(R.id.cboxEndereco)){
            containerEndereco_Toggle();
        } else if (view == findViewById(R.id.fab)){
            ClienteDAO clienteDAO = new ClienteDAO(getBaseContext());
            EditText nomeTbox = findViewById(R.id.nomeTbox);
            EditText descricaoTbox = findViewById(R.id.descricaoTbox);
            CheckBox cboxEndereco = findViewById(R.id.cboxEndereco);
            if (nomeTbox.getText().length() > 0) {
                if (clienteDAO.Adicionar(new Cliente(nomeTbox.getText().toString(), descricaoTbox.getText().toString()))){

                    nomeTbox.setText("");
                    descricaoTbox.setText("");

                    Snackbar.make(view, "Cliente adicionado com sucesso.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    Snackbar.make(view, "Não foi possível adicionar o cliente.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

                if (cboxEndereco.isChecked()){

                    cboxEndereco.setChecked(false);
                }
            } else {
                Snackbar.make(view, "Preencha os campos e tente novamente.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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

}
