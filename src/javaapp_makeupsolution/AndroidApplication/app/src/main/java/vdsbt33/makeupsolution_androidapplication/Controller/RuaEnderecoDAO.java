package vdsbt33.makeupsolution_androidapplication.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vdsbt33.makeupsolution_androidapplication.Model.RuaEndereco;
import vdsbt33.makeupsolution_androidapplication.Model.BairroEndereco;

public class RuaEnderecoDAO {
    private SQLiteDatabase db;
    private RuaEnderecoDAOInitializer initializer;
    private Context context;

    public RuaEnderecoDAO(Context context) {
        initializer = new RuaEnderecoDAOInitializer(context);
        this.context = context;
    }

    public boolean Adicionar(RuaEndereco ruaEndereco) {
        ContentValues valores;
        try {
            db = initializer.getWritableDatabase();
            valores = new ContentValues();
            valores.put("codBairroEndereco", ruaEndereco.getBairroEndereco().getCodBairroEndereco());
            valores.put("nomeRuaEndereco", ruaEndereco.getNomeRuaEndereco());

            ruaEndereco.setCodRuaEndereco((int) db.insert("RuaEndereco", null, valores));

            db.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public RuaEndereco getRuaEnderecoByID(int id){
        RuaEndereco endereco;

        String query = "SELECT codRuaEndereco, codBairroEndereco, nomeRuaEndereco FROM RuaEndereco WHERE codRuaEndereco = ?;";

        db = initializer.getReadableDatabase();
        Cursor cursor = db.rawQuery( query, new String[] { String.valueOf(id) } );
        cursor.moveToFirst();
        BairroEnderecoDAO bairroEnderecoDAO = new BairroEnderecoDAO(context);
        endereco = new RuaEndereco(cursor.getInt(0), bairroEnderecoDAO.getBairroEnderecoByID(cursor.getInt(1)), cursor.getString(2));
        db.close();
        return endereco;
    }

}
