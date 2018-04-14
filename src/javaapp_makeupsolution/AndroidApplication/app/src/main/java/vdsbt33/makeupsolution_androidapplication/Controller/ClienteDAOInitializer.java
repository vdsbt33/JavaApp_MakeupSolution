package vdsbt33.makeupsolution_androidapplication.Controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ClienteDAOInitializer extends SQLiteOpenHelper {

    public static final String TABELA = "Cliente";
    public static final String NOME_BANCO = "MakeupSolution.db";
    public static final int VERSAO = 1;

    public ClienteDAOInitializer(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
        android.util.Log.i("vdebug", "ClienteDAOInitializer()");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Database_Create_Query.CidadeEndereco);
        db.execSQL(Database_Create_Query.BairroEndereco);
        db.execSQL(Database_Create_Query.RuaEndereco);
        db.execSQL(Database_Create_Query.Cliente);
        db.execSQL(Database_Create_Query.Contato);
        db.execSQL(Database_Create_Query.Endereco);
        db.execSQL(Database_Create_Query.Agenda);
        db.execSQL(Database_Create_Query.Produto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Cliente;");
        onCreate(db);
    }

}
