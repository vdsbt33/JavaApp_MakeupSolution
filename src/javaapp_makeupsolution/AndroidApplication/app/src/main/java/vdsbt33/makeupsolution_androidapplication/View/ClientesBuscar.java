package vdsbt33.makeupsolution_androidapplication.View;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

import vdsbt33.makeupsolution_androidapplication.Controller.ClienteDAO;
import vdsbt33.makeupsolution_androidapplication.Model.Cliente;
import vdsbt33.makeupsolution_androidapplication.R;

public class ClientesBuscar extends AppCompatActivity implements View.OnClickListener {

    protected ClienteDAO clienteDAO;
    protected List<Cliente> clientes;
    protected ArrayAdapter<Cliente> listAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes_buscar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        clienteDAO = new ClienteDAO(getBaseContext());
        clientes = clienteDAO.ListarTodos();
        listAdapter = new ArrayAdapter<Cliente>(this, android.R.layout.simple_list_item_1, clientes);

        ListView clientesTable = findViewById(R.id.clientesTable);
        clientesTable.setAdapter(listAdapter);


    }

    public void onClick(View view){
        if (view == findViewById(R.id.fab)) {
            clientes = clienteDAO.ListarTodos();

        }

    }

}
