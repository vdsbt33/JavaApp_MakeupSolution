package vdsbt33.makeupsolution_androidapplication.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vdsbt33.makeupsolution_androidapplication.Model.CidadeEndereco;
import vdsbt33.makeupsolution_androidapplication.Model.BairroEndereco;

public class BairroEnderecoDAO {

    private SQLiteDatabase db;
    private BairroEnderecoDAOInitializer initializer;
    private Context context;

    public BairroEnderecoDAO(Context context) {
        initializer = new BairroEnderecoDAOInitializer(context);
        this.context = context;
    }

    public boolean Adicionar(BairroEndereco bairroEndereco) {
        ContentValues valores;
        try {
            db = initializer.getWritableDatabase();
            valores = new ContentValues();
            valores.put("codCidadeEndereco", bairroEndereco.getCidadeEndereco().getCodCidadeEndereco());
            valores.put("nomeBairroEndereco", bairroEndereco.getNomeBairroEndereco());

            bairroEndereco.setCodBairroEndereco((int) db.insert("BairroEndereco", null, valores));

            db.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public BairroEndereco getBairroEnderecoByID(int id){
        BairroEndereco endereco;

        String query = "SELECT codBairroEndereco, codCidadeEndereco, nomeBairroEndereco FROM BairroEndereco WHERE codBairroEndereco = ?;";

        db = initializer.getReadableDatabase();
        Cursor cursor = db.rawQuery( query, new String[] { String.valueOf(id) } );
        cursor.moveToFirst();
        CidadeEnderecoDAO cidadeEnderecoDAO = new CidadeEnderecoDAO(context);
        endereco = new BairroEndereco(cursor.getInt(0), cidadeEnderecoDAO.getCidadeEnderecoByID(cursor.getInt(1)), cursor.getString(2));
        db.close();
        return endereco;
    }

}
