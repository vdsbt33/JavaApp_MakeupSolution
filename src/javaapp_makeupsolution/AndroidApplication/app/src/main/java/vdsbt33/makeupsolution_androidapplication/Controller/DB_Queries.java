package vdsbt33.makeupsolution_androidapplication.Controller;

public class DB_Queries {

    public static final String DATABASE_CREATE =
            "CREATE TABLE IF NOT EXISTS CidadeEndereco(\n" +
            "  codCidadeEndereco INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "  nomeCidadeEndereco VARCHAR(100) NOT NULL\n" +
            ");\n" +
            "\n" +
            "CREATE TABLE IF NOT EXISTS BairroEndereco(\n" +
            "  codBairroEndereco INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "  codCidadeEndereco INTEGER NOT NULL,\n" +
            "  nomeBairroEndereco VARCHAR(100) NOT NULL,\n" +
            "  \n" +
            "  FOREIGN KEY(codCidadeEndereco) REFERENCES CidadeEndereco(codCidadeEndereco)\n" +
            ");\n" +
            "\n" +
            "CREATE TABLE IF NOT EXISTS RuaEndereco(\n" +
            "  codRuaEndereco INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "  codBairroEndereco INTEGER NOT NULL,\n" +
            "  nomeRuaEndereco VARCHAR(100) NOT NULL,\n" +
            "  \n" +
            "  FOREIGN KEY(codBairroEndereco) REFERENCES BairroEndereco(codBairroEndereco)\n" +
            ");\n" +
            "\n" +
            "\n" +
            "\n" +
            "CREATE TABLE IF NOT EXISTS Cliente (\n" +
            "  codCliente INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "  nomeCliente VARCHAR(100) NOT NULL,\n" +
            "  descricaoCliente VARCHAR(500) DEFAULT \"\"\n" +
            ");\n" +
            "\n" +
            "CREATE TABLE IF NOT EXISTS Contato (\n" +
            "  codCliente INTEGER NOT NULL,\n" +
            "  valorContato VARCHAR(100),\n" +
            "  \n" +
            "  FOREIGN KEY (codCliente) REFERENCES Cliente(codCliente)\n" +
            ");\n" +
            "\n" +
            "CREATE TABLE IF NOT EXISTS Endereco (\n" +
            "  codCliente INTEGER NOT NULL UNIQUE,\n" +
            "  codCidadeEndereco INTEGER NOT NULL,\n" +
            "  codBairroEndereco INTEGER NOT NULL,\n" +
            "  codRuaEndereco INTEGER NOT NULL,\n" +
            "  numeroEndereco INTEGER NOT NULL,\n" +
            "  \n" +
            "  FOREIGN KEY (codCliente) REFERENCES Cliente\n" +
            "  (codCliente),\n" +
            "  FOREIGN KEY (codCidadeEndereco) REFERENCES CidadeEndereco(codCidadeEndereco),\n" +
            "  FOREIGN KEY (codBairroEndereco) REFERENCES BairroEndereco(codBairroEndereco),\n" +
            "  FOREIGN KEY (codRuaEndereco) REFERENCES RuaEndereco(codRuaEndereco)\n" +
            ");\n" +
            "\n" +
            "CREATE TABLE IF NOT EXISTS Agenda (\n" +
            "  codAgenda INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "  codCliente INTEGER NOT NULL,\n" +
            "  valorAgenda DOUBLE(10,2) DEFAULT 0.0,\n" +
            "  dataHoraRegistradoAgenda DATETIME NOT NULL,\n" +
            "  dataHoraAlvoAgenda DATETIME NOT NULL,\n" +
            "  \n" +
            "  FOREIGN KEY(codCliente) REFERENCES Cliente(codCliente)\n" +
            ");\n" +
            "\n" +
            "CREATE TABLE IF NOT EXISTS Produto (\n" +
            "  codProduto INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "  nomeProduto VARCHAR(100) NOT NULL,\n" +
            "  precoProduto DOUBLE(10,2) DEFAULT 0.0,\n" +
            "  quantidadeProduto INTEGER DEFAULT 0,\n" +
            "  dataProduto DATETIME NOT NULL\n" +
            ");";

    public static final String DATABASE_VERSIONUPDATE =
            "DROP TABLE IF EXISTS Produto;\n" +
            "DROP TABLE IF EXISTS Contato;\n" +
            "DROP TABLE IF EXISTS Agenda;\n" +
            "DROP TABLE IF EXISTS Endereco;\n" +
            "DROP TABLE IF EXISTS RuaEndereco;\n" +
            "DROP TABLE IF EXISTS BairroEndereco;\n" +
            "DROP TABLE IF EXISTS CidadeEndereco;\n" +
            "DROP TABLE IF EXISTS Cliente;\n";

    public static final String SELECT_CLIENTE_ALL =
            "SELECT nomeCliente, descricaoCliente FROM Cliente;";



}
