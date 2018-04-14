package vdsbt33.makeupsolution_androidapplication.Lib;

import android.app.ListActivity;
import android.os.Bundle;

import java.util.ArrayList;

import vdsbt33.makeupsolution_androidapplication.Model.Contato;

public class Contato_Buscar_Component_ListActivity extends ListActivity {
    private ArrayList<Contato> lista;

    public Contato_Buscar_Component_ListActivity(ArrayList<Contato> lista) {
        this.lista = lista;
    }

    public void onCreate(Bundle icicle){
        super.onCreate(icicle);

        // 1. Pass context and data to custom adapter
        Contato_Buscar_Component_Adapter adapter = new Contato_Buscar_Component_Adapter(this, lista);

        setListAdapter(adapter);
    }


}
