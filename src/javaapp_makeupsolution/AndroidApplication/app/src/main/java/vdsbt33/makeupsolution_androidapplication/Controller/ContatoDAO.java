package vdsbt33.makeupsolution_androidapplication.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vdsbt33.makeupsolution_androidapplication.Model.Contato;

public class ContatoDAO {



    private SQLiteDatabase db;
    private ContatoDAOInitializer initializer;
    private Context context;

    public ContatoDAO(Context context) {
        initializer = new ContatoDAOInitializer(context);
        this.context = context;
    }

    public boolean Adicionar(Contato contato) {
        ContentValues valores;
        try {
            db = initializer.getWritableDatabase();
            valores = new ContentValues();
            valores.put("codCliente", contato.getCodCliente().getCod());
            valores.put("valorContato", contato.getValorContato());
            ClienteDAO clienteDAO = new ClienteDAO(context);
            db.insert("Contato", null, valores);

            db.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public List<Contato> getContatosByID(int id){
        List<Contato> contatos = new ArrayList<Contato>();

        String query = "SELECT codCliente, valorContato FROM Contato WHERE codCliente = ?;";

        db = initializer.getReadableDatabase();
        Cursor cursor = db.rawQuery( query, new String[] { String.valueOf(id) } );
        if (cursor.getCount() > 0) {
            ClienteDAO clienteDAO = new ClienteDAO(context);
            while (cursor.moveToNext()) {
                contatos.add(new Contato(clienteDAO.getClienteByID(cursor.getInt(0)), cursor.getString(1)));
            }
        }

        db.close();

        if (contatos.size() > 0){
            return contatos;
        }
        return null;
    }

    public boolean Remover(Contato contato){
        ContentValues valores;
        try {
            String where = "codCliente = ?";
            String[] params = { String.valueOf(contato.getCodCliente().getCod()) };

            db = initializer.getWritableDatabase();
            valores = new ContentValues();
            valores.put("codCliente", contato.getCodCliente().getCod());
            db.delete("Contato", where, params);

            db.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }



}
