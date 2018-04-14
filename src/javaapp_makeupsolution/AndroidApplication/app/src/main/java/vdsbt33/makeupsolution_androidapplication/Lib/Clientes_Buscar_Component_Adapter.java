package vdsbt33.makeupsolution_androidapplication.Lib;

import java.util.ArrayList;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import vdsbt33.makeupsolution_androidapplication.Controller.ClienteDAO;
import vdsbt33.makeupsolution_androidapplication.Model.Cliente;
import vdsbt33.makeupsolution_androidapplication.Model.Contato;
import vdsbt33.makeupsolution_androidapplication.R;
import vdsbt33.makeupsolution_androidapplication.View.ClientesAdicionar;

public class Clientes_Buscar_Component_Adapter extends ArrayAdapter<Clientes_Buscar_Component> implements View.OnClickListener {

    private final Context context;
    private final ArrayList<Clientes_Buscar_Component> itemList;

    public Clientes_Buscar_Component_Adapter(Context context, ArrayList<Clientes_Buscar_Component> itemList) {
        super(context, R.layout.cliente_buscar_component, itemList);

        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.cliente_buscar_component, parent, false);

        // 3. Get TextViews in it
        TextView nomeClienteLbl = rowView.findViewById(R.id.nomeClienteLbl);
        TextView descricaoClienteLbl = rowView.findViewById(R.id.descricaoClienteLbl);
        TextView enderecoLbl = rowView.findViewById(R.id.enderecoLbl);
        ListView contatoList = rowView.findViewById(R.id.contatoList);

        // 4. Setting values
        nomeClienteLbl.setText("");
        descricaoClienteLbl.setText("");
        enderecoLbl.setText("");

        nomeClienteLbl.setText(itemList.get(position).getCliente().getNome());

        if (itemList.get(position).getCliente().getDescricao().length() > 0) {
            descricaoClienteLbl.setText(itemList.get(position).getCliente().getDescricao());
        } else {
            descricaoClienteLbl.setText("[Não possui descrição]");
        }

        if (itemList.get(position).getEndereco() != null){
            enderecoLbl.setText(itemList.get(position).getEndereco().toString());
        } else {
            enderecoLbl.setText("");
            enderecoLbl.setVisibility(View.GONE);
        }

        Contato_Buscar_Component_Adapter contatos;
        if (itemList.get(position).getContatos() != null){
            contatos = new Contato_Buscar_Component_Adapter(context, itemList.get(position).getContatos());
            contatoList.setAdapter(contatos);

            // Resize ListView
            setListViewHeightBasedOnChildren(contatoList);


        } else {
            contatoList.setVisibility(View.GONE);
        }

        // This prevents the selectedContent from reseting when item is out of view
        if (selectedPosition != -1 && selectedPosition == position) {
            rowView.findViewById(R.id.selectedContent).setVisibility(View.VISIBLE);
        }

        contatoList.setEnabled(false);
        contatoList.setClickable(false);

        rowView.findViewById(R.id.excluirBtn).setOnClickListener(this);

        return rowView;
    }

    private int selectedPosition = -1;

    public void setSelectedView(int selectedPosition){
        this.selectedPosition = selectedPosition;
    }

    public boolean deletarCliente(){
        ClienteDAO clienteDAO = new ClienteDAO(context);
        if (clienteDAO.Remover(itemList.get(selectedPosition).getCliente())){
            itemList.remove(selectedPosition);
            notifyDataSetChanged();
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View view){

        if (view == view.findViewById(R.id.excluirBtn)){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Deseja realmente excluir o cliente " + itemList.get(selectedPosition).getCliente().getNome() +"?").setPositiveButton("Sim", dialogClickListener)
                    .setNegativeButton("Não", dialogClickListener).show();
        }

    }


    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    deletarCliente();
                    break;
            }
        }
    };


    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

}
