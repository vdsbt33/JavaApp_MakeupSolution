package vdsbt33.makeupsolution_androidapplication.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vdsbt33.makeupsolution_androidapplication.Model.Endereco;

public class EnderecoDAO {


    private SQLiteDatabase db;
    private EnderecoDAOInitializer initializer;
    private Context context;

    public EnderecoDAO(Context context) {
        initializer = new EnderecoDAOInitializer(context);
        this.context = context;
    }

    public boolean Adicionar(Endereco endereco) {
        ContentValues valores;
        try {
            db = initializer.getWritableDatabase();
            valores = new ContentValues();
            valores.put("codCliente", endereco.getCliente().getCod());
            valores.put("codCidadeEndereco", endereco.getCidadeEndereco().getCodCidadeEndereco());
            valores.put("codBairroEndereco", endereco.getBairroEndereco().getCodBairroEndereco());
            valores.put("codRuaEndereco", endereco.getRuaEndereco().getCodRuaEndereco());
            valores.put("numeroEndereco", endereco.getNumeroEndereco());

            db.insert("Endereco", null, valores);

            db.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public List<Endereco> ListarTodos() {
        List enderecos = new ArrayList();

        String query = "SELECT codCliente, codCidadeEndereco, codBairroEndereco, codRuaEndereco, numeroEndereco FROM Endereco;";

        db = initializer.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            ClienteDAO clienteDAO = new ClienteDAO(context);
            CidadeEnderecoDAO cidadeEnderecoDAO = new CidadeEnderecoDAO(context);
            BairroEnderecoDAO bairroEnderecoDAO = new BairroEnderecoDAO(context);
            RuaEnderecoDAO ruaEnderecoDAO = new RuaEnderecoDAO(context);
            enderecos.add(new Endereco(clienteDAO.getClienteByID(cursor.getInt(0)), cidadeEnderecoDAO.getCidadeEnderecoByID(cursor.getInt(1)), bairroEnderecoDAO.getBairroEnderecoByID(cursor.getInt(2)), ruaEnderecoDAO.getRuaEnderecoByID(cursor.getInt(3)), cursor.getInt(4)));
        }
        db.close();

        return enderecos;
    }

    public Endereco getEnderecoByID(int id) {
        Endereco endereco = null;

        String query = "SELECT codCliente, codCidadeEndereco, codBairroEndereco, codRuaEndereco, numeroEndereco FROM Endereco WHERE codCliente = ?;";

        db = initializer.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[] { String.valueOf(id) });
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            ClienteDAO clienteDAO = new ClienteDAO(context);
            CidadeEnderecoDAO cidadeEnderecoDAO = new CidadeEnderecoDAO(context);
            BairroEnderecoDAO bairroEnderecoDAO = new BairroEnderecoDAO(context);
            RuaEnderecoDAO ruaEnderecoDAO = new RuaEnderecoDAO(context);
            endereco = new Endereco(clienteDAO.getClienteByID(cursor.getInt(0)), cidadeEnderecoDAO.getCidadeEnderecoByID(cursor.getInt(1)), bairroEnderecoDAO.getBairroEnderecoByID(cursor.getInt(2)), ruaEnderecoDAO.getRuaEnderecoByID(cursor.getInt(3)), cursor.getInt(4));
        }
        db.close();

        if (endereco != null) {
            return endereco;
        }
        return null;
    }
}
