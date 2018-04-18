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

public class Contato_Adicionar_Component_Adapter extends ArrayAdapter<Contato> implements View.OnClickListener, AdapterView.OnItemClickListener, TextWatcher, View.OnFocusChangeListener {

    private final Context context;
    private final List<Contato> itemList;

    public Contato_Adicionar_Component_Adapter(Context context, List<Contato> itemList) {
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
        View rowView = inflater.inflate(R.layout.cliente_contato_component, parent, false);

        // 3. Get TextViews in it
        TextView contatoTextoLbl = rowView.findViewById(R.id.contatoTextoLbl);
        EditText contatoValorTbox = rowView.findViewById(R.id.contatoValorTbox);
        Button removerContatoBtn = rowView.findViewById(R.id.contatoRemoverBtn);

        // 4. Setting values
        contatoTextoLbl.setText("Contato: ");
        contatoValorTbox.setText(itemList.get(position).getValorContato());

        contatoValorTbox.addTextChangedListener(this);
        contatoValorTbox.setOnFocusChangeListener(this);


        if (itemList.size() == 1) {
            removerContatoBtn.setVisibility(View.GONE);
        }

        removerContatoBtn.setOnClickListener(this);

        return rowView;
    }


    // Delete Contato
    public boolean deletarContato(View view){
        itemList.remove( ((ListView) view.getParent().getParent()).getPositionForView((View) view.getParent()) );
        selectedEditText = null;
        selectedContatoIndex = 0;

        notifyDataSetChanged();
        return true;
    }

    public void onClick(View view) {
        if (view == view.findViewById(R.id.contatoRemoverBtn)){
            deletarContato(view);
            setListViewHeightBasedOnChildren((ListView) view.getParent().getParent());
        } else if (view == view.findViewById(R.id.contatoValorTbox)){

        }
    }

    // Save Contato values in list when it changes
    private EditText selectedEditText;

    private int selectedContatoIndex;



    @Override
    public void onFocusChange(View view, boolean hasFocus) {

        if (!hasFocus){

        } else if (hasFocus) {

            selectedEditText = (EditText) view;
            selectedContatoIndex = ((ListView) view.getParent().getParent()).getPositionForView((View) view.getParent());
        }
    }


    public EditText getFocusedEditText() {
        return selectedEditText;
    }


    @Override
    public void afterTextChanged(Editable s) {}

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (selectedEditText != null){
            itemList.get(selectedContatoIndex).setValorContato(selectedEditText.getText().toString());
        }
    }

    // Listener for contatoList
    // This is needed to get the index of the Contato being edited.
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectedContatoIndex = position;
    }


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
