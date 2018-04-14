package vdsbt33.makeupsolution_androidapplication.Lib;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vdsbt33.makeupsolution_androidapplication.Model.Contato;
import vdsbt33.makeupsolution_androidapplication.R;

public class Contato_Buscar_Component_Adapter extends ArrayAdapter<Contato> {

    private final Context context;
    private final List<Contato> itemList;

    public Contato_Buscar_Component_Adapter(Context context, List<Contato> itemList) {
        super(context, R.layout.cliente_contato_component, itemList);

        this.context = context;
        this.itemList = itemList;
    }

    /*
    // IMPORTANT NOTE:
    // This is called on every notifyDataSetChanged() call
    */
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.buscar_contato_component, parent, false);

        // 3. Get TextViews in it
        TextView contatoValorLbl = rowView.findViewById(R.id.contatoValorLbl);

        // 4. Setting values
        contatoValorLbl.setText(itemList.get(position).getValorContato());

        return rowView;
    }

}
