package vdsbt33.makeupsolution_androidapplication.Controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ClienteDAOInitializer extends SQLiteOpenHelper {
    public static final String TABELA = "Cliente";
    public static final String CODIGO = "codCliente";
    public static final String NOME = "nomeCliente";
    public static final String DESCRICAO = "descricaoCliente";
    public static final String NOME_BANCO = "MakeupSolution.db";
    public static final int VERSAO = 1;

    public ClienteDAOInitializer(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
        android.util.Log.i("vdjaja123", "-- ClienteDAOInitializer --");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS Cliente (\n" +
                "  codCliente INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  nomeCliente VARCHAR(100) NOT NULL,\n" +
                "  descricaoCliente VARCHAR(500) DEFAULT ''" +
                ");";
        android.util.Log.i("vdjaja123", "Banco de Dados iniciado!");
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Cliente;");
        onCreate(db);
    }

    /**
     * Check if the database exist and can be read.
     *
     * @return true if it exists and can be read, false if it doesn't
     */
    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase(TABELA, null,
                    SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
        } catch (Exception e) {
            // database doesn't exist yet.
        }
        return checkDB != null;
    }

}
