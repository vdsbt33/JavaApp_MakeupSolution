package vdsbt33.makeupsolution_androidapplication.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import vdsbt33.makeupsolution_androidapplication.Controller.BairroEnderecoDAO;
import vdsbt33.makeupsolution_androidapplication.Controller.CidadeEnderecoDAO;
import vdsbt33.makeupsolution_androidapplication.Controller.ClienteDAO;
import vdsbt33.makeupsolution_androidapplication.Controller.ContatoDAO;
import vdsbt33.makeupsolution_androidapplication.Controller.EnderecoDAO;
import vdsbt33.makeupsolution_androidapplication.Controller.RuaEnderecoDAO;
import vdsbt33.makeupsolution_androidapplication.Lib.Contato_Adicionar_Component_Adapter;
import vdsbt33.makeupsolution_androidapplication.Model.BairroEndereco;
import vdsbt33.makeupsolution_androidapplication.Model.CidadeEndereco;
import vdsbt33.makeupsolution_androidapplication.Model.Cliente;
import vdsbt33.makeupsolution_androidapplication.Model.Contato;
import vdsbt33.makeupsolution_androidapplication.Model.Endereco;
import vdsbt33.makeupsolution_androidapplication.Model.RuaEndereco;
import vdsbt33.makeupsolution_androidapplication.R;

public class ClientesAdicionar extends MainActivity implements View.OnClickListener, AppCompatCallback, NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {

    private AppCompatDelegate delegate;
    @Override
    public void onSupportActionModeStarted(ActionMode mode) {
        super.onSupportActionModeStarted(mode);
    }

    @Override
    public void onSupportActionModeFinished(ActionMode mode) {
        super.onSupportActionModeFinished(mode);
    }

    @Nullable
    @Override
    public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
        return null;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_clientes_adicionar_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        if (id == R.id.nav_clientes_adicionar) {
            // Nothing happens
        } else if (id == R.id.nav_clientes_buscar) {
            Intent clientesBuscar = new Intent(this, ClientesBuscar.class);
            finish();
            startActivity(clientesBuscar);
        } else if (id == R.id.nav_agenda_adicionar) {

        } else if (id == R.id.nav_agenda_buscar) {

        } else if (id == R.id.nav_produto_adicionar) {

        } else if (id == R.id.nav_produto_buscar) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_clientes_adicionar_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected Contato_Adicionar_Component_Adapter adapter;
    protected ArrayList<Contato> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes_adicionar);

        delegate = AppCompatDelegate.create(this, this);
        delegate.onCreate(savedInstanceState);
        delegate.setContentView(R.layout.activity_clientes_adicionar_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        delegate.setSupportActionBar(toolbar);
        toolbar.setTitle("Adicionar Cliente");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_clientes_adicionar_drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.activity_clientes_adicionar_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);

        findViewById(R.id.cboxContato).setOnClickListener(this);
        findViewById(R.id.cboxEndereco).setOnClickListener(this);

        lista = new ArrayList<Contato>();
        adapter = new Contato_Adicionar_Component_Adapter(this, lista);

        ListView contatoListAdicionar = findViewById(R.id.contatoListAdicionar);

        contatoListAdicionar.setAdapter(adapter);
        contatoListAdicionar.setOnItemClickListener(this);

        findViewById(R.id.novoContatoBtnAdicionar).setOnClickListener(this);

        lista.add(new Contato(new Cliente("", ""), ""));

        adapter.notifyDataSetChanged();
    }

    public void onClick(View view){
        if (view == findViewById(R.id.cboxEndereco)){
            containerEndereco_Toggle();

        } else if (view == findViewById(R.id.cboxContato)) {
            containerContato_Toggle();

        } else if (view == findViewById(R.id.novoContatoBtnAdicionar)){

            lista.add(new Contato(new Cliente("", ""), ""));
            adapter.setListViewHeightBasedOnChildren((ListView) findViewById(R.id.contatoListAdicionar));

        } else if (view == findViewById(R.id.fab)){
            // Cliente
            ClienteDAO clienteDAO = new ClienteDAO(getBaseContext());
            EditText nomeTbox = findViewById(R.id.nomeTbox);
            EditText descricaoTbox = findViewById(R.id.descricaoTbox);

            // Endereco
            EditText cidadeTbox = findViewById(R.id.cidadeTbox);
            EditText bairroTbox = findViewById(R.id.bairroTbox);
            EditText ruaTbox = findViewById(R.id.ruaTbox);
            EditText numeroTboxnum = findViewById(R.id.numeroTboxnum);

            // CheckBoxes
            CheckBox cboxEndereco = findViewById(R.id.cboxEndereco);
            CheckBox cboxContato = findViewById(R.id.cboxContato);

            // Error checking and information variables
            boolean hasError = false;
            String errorMsg = "";

            // Error checking

            // Name of Cliente
            if (nomeTbox.getText().length() == 0){
                hasError = true;
                errorMsg = getString(R.string.cliente_adicionar_error_nome);
            }

            // Fields of Contato
            if (cboxContato.isChecked() && !hasError) {
                // Check if every Contato is has a value before saving
                for (int i = 0; i < lista.size() && !hasError; i++) {
                    if (lista.get(i).getValorContato().length() == 0) {
                        hasError = true;
                        errorMsg = getString(R.string.cliente_adicionar_error_contato);
                    }
                }
            }

            // Fields of Endereco
            if (cboxEndereco.isChecked() && !hasError) {
                if ( cidadeTbox.getText().toString().length() == 0 || bairroTbox.getText().toString().length() == 0 || ruaTbox.getText().toString().length() == 0 || numeroTboxnum.getText().toString().length() == 0 ) {
                    hasError = true;
                    errorMsg = getString(R.string.cliente_adicionar_error_endereco);
                }

            }

            // Data saving if no error ocurred
            if (hasError) {
                Snackbar.make(view, errorMsg, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            } else {
                // No error ocurred block


                // Adding to database: Cliente
                Cliente cliente = new Cliente(nomeTbox.getText().toString(), descricaoTbox.getText().toString());

                if (!clienteDAO.Adicionar(cliente)){
                    // If Cliente could not be added to database
                    hasError = true;
                }

                // Adding to database: all Contato values
                if (cboxContato.isChecked() && !hasError) {

                    ContatoDAO contatoDAO = new ContatoDAO(getBaseContext());
                    for (int i = 0; i < lista.size() && !hasError; i++){

                        // Sets the Cliente for Contato to the one created
                        lista.get(i).setCodCliente(cliente);

                        if (!contatoDAO.Adicionar(lista.get(i))) {
                            hasError = true;
                        }
                    }

                }

                // Adding to database: Endereco
                if (cboxEndereco.isChecked() && !hasError) {
                    // All database manipulator variables (by use order)
                    CidadeEnderecoDAO cidadeEnderecoDAO = new CidadeEnderecoDAO(getBaseContext());
                    BairroEnderecoDAO bairroEnderecoDAO = new BairroEnderecoDAO(getBaseContext());
                    RuaEnderecoDAO ruaEnderecoDAO = new RuaEnderecoDAO(getBaseContext());
                    EnderecoDAO enderecoDAO = new EnderecoDAO(getBaseContext());

                    // Creating and assigning variables and its foreign keys
                    CidadeEndereco cidadeEndereco = new CidadeEndereco(cidadeTbox.getText().toString());
                    BairroEndereco bairroEndereco = new BairroEndereco(cidadeEndereco, bairroTbox.getText().toString());
                    RuaEndereco ruaEndereco = new RuaEndereco(bairroEndereco, ruaTbox.getText().toString());
                    EditText numeroEndereco = findViewById(R.id.numeroTboxnum);
                    Endereco endereco = new Endereco(cliente, cidadeEndereco, bairroEndereco, ruaEndereco, Integer.valueOf(numeroEndereco.getText().toString()));

                    // Inserting all data to database
                    if (!cidadeEnderecoDAO.Adicionar(cidadeEndereco)){
                        hasError = true;
                    } else if (!bairroEnderecoDAO.Adicionar(bairroEndereco)){
                        hasError = true;
                    } else if (!ruaEnderecoDAO.Adicionar(ruaEndereco)){
                        hasError = true;
                    } else if (!enderecoDAO.Adicionar(endereco)){
                        hasError = true;
                    }
                }

                // Reseting all fields if there are no errors
                if (hasError) {
                    // Error here will always be caused by database access
                    errorMsg = getString(R.string.cliente_adicionar_error_database);
                    Snackbar.make(view, errorMsg, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {

                    // If there are no errors, show message and reset fields
                    errorMsg = getString(R.string.cliente_adicionar_success);
                    Snackbar.make(view, errorMsg, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    // Cliente fields
                    nomeTbox.setText("");
                    descricaoTbox.setText("");

                    // Contato fields
                    lista.clear();
                    // Adds the first item for the user to fill value
                    lista.add(new Contato(new Cliente("", ""), ""));
                    adapter.notifyDataSetChanged();

                    // Endereco fields
                    cidadeTbox.setText("");
                    bairroTbox.setText("");
                    ruaTbox.setText("");
                    numeroTboxnum.setText("");

                    // Toggles all checkboxes to off
                    if (cboxContato.isChecked()){
                        cboxContato.setChecked(false);
                        containerContato_Toggle();
                    }

                    if (cboxEndereco.isChecked()){
                        cboxEndereco.setChecked(false);
                        containerEndereco_Toggle();
                    }

                }

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
