package vdsbt33.makeupsolution_androidapplication.Controller;

public class Database_Create_Query {

    public static final String CidadeEndereco = "CREATE TABLE IF NOT EXISTS CidadeEndereco(\n" +
            "  codCidadeEndereco INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "  nomeCidadeEndereco VARCHAR(100) NOT NULL\n" +
            ");";

    public static final String BairroEndereco = "CREATE TABLE IF NOT EXISTS BairroEndereco(\n" +
            "  codBairroEndereco INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "  codCidadeEndereco INTEGER NOT NULL,\n" +
            "  nomeBairroEndereco VARCHAR(100) NOT NULL,\n" +
            "  \n" +
            "  FOREIGN KEY(codCidadeEndereco) REFERENCES CidadeEndereco(codCidadeEndereco)\n" +
            ");";

    public static final String RuaEndereco = "CREATE TABLE IF NOT EXISTS RuaEndereco(\n" +
            "  codRuaEndereco INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "  codBairroEndereco INTEGER NOT NULL,\n" +
            "  nomeRuaEndereco VARCHAR(100) NOT NULL,\n" +
            "  \n" +
            "  FOREIGN KEY(codBairroEndereco) REFERENCES BairroEndereco(codBairroEndereco)\n" +
            ");";

    public static final String Cliente = "CREATE TABLE IF NOT EXISTS Cliente (\n" +
            "  codCliente INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "  nomeCliente VARCHAR(100) NOT NULL,\n" +
            "  descricaoCliente VARCHAR(500) DEFAULT ''\n" +
            ");";

    public static final String Contato = "CREATE TABLE IF NOT EXISTS Contato (\n" +
            "  codCliente INTEGER NOT NULL,\n" +
            "  valorContato VARCHAR(100),\n" +
            "  \n" +
            "  FOREIGN KEY (codCliente) REFERENCES Cliente(codCliente)\n" +
            ");";

    public static final String Endereco = "CREATE TABLE IF NOT EXISTS Endereco (\n" +
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
            ");";

    public static final String Agenda = "CREATE TABLE IF NOT EXISTS Agenda (\n" +
            "  codAgenda INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "  codCliente INTEGER NOT NULL,\n" +
            "  valorAgenda DOUBLE(10,2) DEFAULT 0.0,\n" +
            "  dataHoraRegistradoAgenda DATETIME NOT NULL,\n" +
            "  dataHoraAlvoAgenda DATETIME NOT NULL,\n" +
            "  \n" +
            "  FOREIGN KEY(codCliente) REFERENCES Cliente(codCliente)\n" +
            ");";

    public static final String Produto = "CREATE TABLE IF NOT EXISTS Produto (\n" +
            "  codProduto INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "  nomeProduto VARCHAR(100) NOT NULL,\n" +
            "  precoProduto DOUBLE(10,2) DEFAULT 0.0,\n" +
            "  quantidadeProduto INTEGER DEFAULT 0,\n" +
            "  dataProduto DATETIME NOT NULL\n" +
            ");";

}
