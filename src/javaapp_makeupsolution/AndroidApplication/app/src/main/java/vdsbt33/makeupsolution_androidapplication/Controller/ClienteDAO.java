package vdsbt33.makeupsolution_androidapplication.Controller;

import vdsbt33.makeupsolution_androidapplication.Model.Cliente;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO  {



    private SQLiteDatabase db;
    private ClienteDAOInitializer initializer;

    public ClienteDAO(Context context) { initializer = new ClienteDAOInitializer(context); }

    public boolean Adicionar(Cliente cliente) {
        ContentValues valores;
        try {
            db = initializer.getWritableDatabase();
            valores = new ContentValues();
            valores.put("nomeCliente", cliente.getNome());
            valores.put("descricaoCliente", cliente.getDescricao());

            db.insert("Cliente", null, valores);
            db.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public List<Cliente> ListarTodos() {
        List clientes = new ArrayList();

        String query = "SELECT codCliente, nomeCliente, descricaoCliente FROM Cliente;";

        db = initializer.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {

            clientes.add(new Cliente(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
        }
        db.close();
        return clientes;
    }

}
