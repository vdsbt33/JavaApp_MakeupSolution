package vdsbt33.makeupsolution_androidapplication.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vdsbt33.makeupsolution_androidapplication.Model.CidadeEndereco;

public class CidadeEnderecoDAO {


    private SQLiteDatabase db;
    private CidadeEnderecoDAOInitializer initializer;


    public CidadeEnderecoDAO(Context context) {
        initializer = new CidadeEnderecoDAOInitializer(context);
    }

    public boolean Adicionar(CidadeEndereco cidadeEndereco) {
        ContentValues valores;
        try {
            db = initializer.getWritableDatabase();
            valores = new ContentValues();
            valores.put("nomeCidadeEndereco", cidadeEndereco.getNomeCidadeEndereco());

            cidadeEndereco.setCodCidadeEndereco((int) db.insert("CidadeEndereco", null, valores));

            db.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public List<CidadeEndereco> ListarTodos() {
        List enderecos = new ArrayList();

        String query = "SELECT codCidadeEndereco, nomeCidadeEndereco FROM CidadeEndereco;";

        db = initializer.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            enderecos.add(new CidadeEndereco(cursor.getInt(0), cursor.getString(1)));
        }
        db.close();
        return enderecos;
    }

    public CidadeEndereco getCidadeEnderecoByID(int id){
        CidadeEndereco endereco;

        String query = "SELECT codCidadeEndereco, nomeCidadeEndereco FROM CidadeEndereco WHERE codCidadeEndereco = ?;";

        db = initializer.getReadableDatabase();
        Cursor cursor = db.rawQuery( query, new String[] { String.valueOf(id) } );
        cursor.moveToFirst();
        endereco = new CidadeEndereco(cursor.getInt(0), cursor.getString(1));
        db.close();
        return endereco;
    }


}
