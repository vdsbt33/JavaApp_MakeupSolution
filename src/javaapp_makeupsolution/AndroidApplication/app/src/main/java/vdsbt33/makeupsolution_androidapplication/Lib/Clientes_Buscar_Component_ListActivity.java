package vdsbt33.makeupsolution_androidapplication.Lib;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.ListActivity;
import android.widget.ListAdapter;

public class Clientes_Buscar_Component_ListActivity extends ListActivity {
    private ArrayList<Clientes_Buscar_Component> lista;

    public Clientes_Buscar_Component_ListActivity(ArrayList<Clientes_Buscar_Component> lista) {
        this.lista = lista;
    }

        public void onCreate(Bundle icicle){
        super.onCreate(icicle);

        // 1. Pass context and data to custom adapter
        Clientes_Buscar_Component_Adapter adapter = new Clientes_Buscar_Component_Adapter(this, lista);

        setListAdapter(adapter);
    }


}
