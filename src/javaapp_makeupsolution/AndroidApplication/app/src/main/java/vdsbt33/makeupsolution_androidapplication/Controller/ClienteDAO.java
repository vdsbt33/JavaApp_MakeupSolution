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
    private Context context;

    public ClienteDAO(Context context) {
        initializer = new ClienteDAOInitializer(context);
        this.context = context;
    }

    public boolean Adicionar(Cliente cliente) {
        ContentValues valores;
        try {
            db = initializer.getWritableDatabase();
            valores = new ContentValues();
            valores.put("nomeCliente", cliente.getNome());
            valores.put("descricaoCliente", cliente.getDescricao());
            cliente.setCod((int) db.insert("Cliente", null, valores));

            db.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean Remover(Cliente cliente) {
        ContentValues valores;
        try {
            String where = "codCliente = ?";
            String[] params = { String.valueOf(cliente.getCod()) };

            db = initializer.getWritableDatabase();
            valores = new ContentValues();
            valores.put("codCliente", cliente.getCod());
            cliente.setCod((int) db.delete("Cliente", where, params));

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

    public Cliente getClienteByID(int id){
        Cliente cliente;

        String query = "SELECT codCliente, nomeCliente, descricaoCliente FROM Cliente WHERE codCliente = ?;";

        db = initializer.getReadableDatabase();
        Cursor cursor = db.rawQuery( query, new String[] { String.valueOf(id) } );
        cursor.moveToFirst();
        cliente = new Cliente(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        db.close();
        return cliente;
    }

    public boolean hasContato(Cliente cliente){

        String query = "SELECT COUNT(*)\n" +
                "        FROM Cliente cli\n" +
                "        LEFT JOIN Contato con On cli.codCliente = con.codCliente\n" +
                "        WHERE con.codCliente = ?;";

        db = initializer.getReadableDatabase();
        Cursor cursor = db.rawQuery( query, new String[] { String.valueOf(cliente.getCod()) } );
        ClienteDAO clienteDAO = new ClienteDAO(context);
        cursor.moveToFirst();
        int qtd = cursor.getInt(0);
        db.close();

        if (qtd > 0){
            return true;
        }
        return false;
    }

}
