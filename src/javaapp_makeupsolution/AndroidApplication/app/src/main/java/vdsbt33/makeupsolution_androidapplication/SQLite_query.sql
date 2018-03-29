CREATE TABLE IF NOT EXISTS CidadeEndereco(
  codCidadeEndereco INTEGER PRIMARY KEY AUTOINCREMENT,
  nomeCidadeEndereco VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS BairroEndereco(
  codBairroEndereco INTEGER PRIMARY KEY AUTOINCREMENT,
  codCidadeEndereco INTEGER NOT NULL,
  nomeBairroEndereco VARCHAR(100) NOT NULL,
  
  FOREIGN KEY(codCidadeEndereco) REFERENCES CidadeEndereco(codCidadeEndereco)
);

CREATE TABLE IF NOT EXISTS RuaEndereco(
  codRuaEndereco INTEGER PRIMARY KEY AUTOINCREMENT,
  codBairroEndereco INTEGER NOT NULL,
  nomeRuaEndereco VARCHAR(100) NOT NULL,
  
  FOREIGN KEY(codBairroEndereco) REFERENCES BairroEndereco(codBairroEndereco)
);



CREATE TABLE IF NOT EXISTS Cliente (
  codCliente INTEGER PRIMARY KEY AUTOINCREMENT,
  nomeCliente VARCHAR(100) NOT NULL,
  descricaoCliente VARCHAR(500) DEFAULT ''
);

CREATE TABLE IF NOT EXISTS Contato (
  codCliente INTEGER NOT NULL,
  valorContato VARCHAR(100),
  
  FOREIGN KEY (codCliente) REFERENCES Cliente(codCliente)
);

CREATE TABLE IF NOT EXISTS Endereco (
  codCliente INTEGER NOT NULL UNIQUE,
  codCidadeEndereco INTEGER NOT NULL,
  codBairroEndereco INTEGER NOT NULL,
  codRuaEndereco INTEGER NOT NULL,
  numeroEndereco INTEGER NOT NULL,
  
  FOREIGN KEY (codCliente) REFERENCES Cliente
  (codCliente),
  FOREIGN KEY (codCidadeEndereco) REFERENCES CidadeEndereco(codCidadeEndereco),
  FOREIGN KEY (codBairroEndereco) REFERENCES BairroEndereco(codBairroEndereco),
  FOREIGN KEY (codRuaEndereco) REFERENCES RuaEndereco(codRuaEndereco)
);

CREATE TABLE IF NOT EXISTS Agenda (
  codAgenda INTEGER PRIMARY KEY AUTOINCREMENT,
  codCliente INTEGER NOT NULL,
  valorAgenda DOUBLE(10,2) DEFAULT 0.0,
  dataHoraRegistradoAgenda DATETIME NOT NULL,
  dataHoraAlvoAgenda DATETIME NOT NULL,
  
  FOREIGN KEY(codCliente) REFERENCES Cliente(codCliente)
);

CREATE TABLE IF NOT EXISTS Produto (
  codProduto INTEGER PRIMARY KEY AUTOINCREMENT,
  nomeProduto VARCHAR(100) NOT NULL,
  precoProduto DOUBLE(10,2) DEFAULT 0.0,
  quantidadeProduto INTEGER DEFAULT 0,
  dataProduto DATETIME NOT NULL
);

/*
SELECT nomeCliente, descricaoCliente FROM Cliente;

*/