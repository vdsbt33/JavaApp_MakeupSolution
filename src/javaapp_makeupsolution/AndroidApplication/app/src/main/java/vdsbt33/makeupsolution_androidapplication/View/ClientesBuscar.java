package vdsbt33.makeupsolution_androidapplication.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import vdsbt33.makeupsolution_androidapplication.Controller.ClienteDAO;
import vdsbt33.makeupsolution_androidapplication.Controller.ContatoDAO;
import vdsbt33.makeupsolution_androidapplication.Controller.EnderecoDAO;
import vdsbt33.makeupsolution_androidapplication.Lib.Clientes_Buscar_Component;
import vdsbt33.makeupsolution_androidapplication.Lib.Clientes_Buscar_Component_Adapter;
import vdsbt33.makeupsolution_androidapplication.Model.Cliente;
import vdsbt33.makeupsolution_androidapplication.R;

public class ClientesBuscar extends MainActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AppCompatCallback, NavigationView.OnNavigationItemSelectedListener {

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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_clientes_buscar_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finish();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        if (id == R.id.nav_clientes_adicionar) {
            Intent clientesAdicionar = new Intent(this, ClientesAdicionar.class);
            finish();
            startActivity(clientesAdicionar);
        } else if (id == R.id.nav_clientes_buscar) {
            // Nothing happens
        } else if (id == R.id.nav_agenda_adicionar) {

        } else if (id == R.id.nav_agenda_buscar) {

        } else if (id == R.id.nav_produto_adicionar) {

        } else if (id == R.id.nav_produto_buscar) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_clientes_buscar_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected Clientes_Buscar_Component_Adapter adapter;
    protected ArrayList<Clientes_Buscar_Component> lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes_buscar);

        delegate = AppCompatDelegate.create(this, this);
        delegate.onCreate(savedInstanceState);
        delegate.setContentView(R.layout.activity_clientes_buscar_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        delegate.setSupportActionBar(toolbar);
        toolbar.setTitle("Buscar Cliente");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_clientes_buscar_drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.activity_clientes_buscar_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        getLista();

        adapter = new Clientes_Buscar_Component_Adapter(this, lista);
        //listAdapter = new ArrayAdapter<Cliente>(this, R.layout.cliente_buscar_component, R.id.nomeClienteLbl, clientes);

        ListView clientesTable = findViewById(R.id.clientesTable);


        clientesTable.setAdapter(adapter);
        clientesTable.setOnItemClickListener(this);
        adapter.notifyDataSetChanged();
    }

    private void getLista(){
        ClienteDAO clienteDAO;
        ContatoDAO contatoDAO;
        EnderecoDAO enderecoDAO;
        List<Cliente> clientes;

        clienteDAO = new ClienteDAO(getBaseContext());
        contatoDAO = new ContatoDAO(getBaseContext());
        enderecoDAO = new EnderecoDAO(getBaseContext());

        clientes = clienteDAO.ListarTodos();

        lista = new ArrayList<Clientes_Buscar_Component>();

        for (int i = 0; i < clientes.size(); i++){
            Clientes_Buscar_Component component = new Clientes_Buscar_Component(clientes.get(i), contatoDAO.getContatosByID(clientes.get(i).getCod()), enderecoDAO.getEnderecoByID(clientes.get(i).getCod()));

            lista.add(component);
        }

    }

    public void onClick(View view){
        if (view == findViewById(R.id.fab)) {
            getLista();
        }
        android.util.Log.i("vdebug", "Clicked View: " + view.toString());
    }

    public static Cliente clienteEditar;

    public void openEditarCliente(Cliente cliente){
        Intent clientesEditar = new Intent(getBaseContext(), ClientesEditar.class);
        startActivity(clientesEditar);
        clienteEditar = cliente;
    }

    private View lastView = null;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (view != lastView){
            if (view.findViewById(R.id.selectedContent).getVisibility() == View.VISIBLE) {
                view.findViewById(R.id.selectedContent).setVisibility(View.GONE);
            } else {
                view.findViewById(R.id.selectedContent).setVisibility(View.VISIBLE);
            }

            if (lastView != null) {
                lastView.findViewById(R.id.selectedContent).setVisibility(View.GONE);
            }

            // View sem Contato pode ser clicada múltiplas vezes. Resolver.
            lastView = view;
            view.setEnabled(false);

            adapter.setSelectedView(position);
        }
    }

    //
    public void startEditarActivity(Clientes_Buscar_Component cliente) {
        
    }


}
