CREATE SCHEMA MakeupSolution;
USE MakeupSolution;


CREATE TABLE CidadeEndereco(
  codCidadeEndereco INT NOT NULL AUTO_INCREMENT,
  nomeCidadeEndereco VARCHAR(100) NOT NULL,
  
  PRIMARY KEY(codCidadeEndereco)
);

CREATE TABLE BairroEndereco(
  codBairroEndereco INT NOT NULL AUTO_INCREMENT,
  codCidadeEndereco INT NOT NULL,
  nomeBairroEndereco VARCHAR(100) NOT NULL,
  
  PRIMARY KEY(codBairroEndereco),
  FOREIGN KEY(codCidadeEndereco) REFERENCES CidadeEndereco(codCidadeEndereco)
);

CREATE TABLE RuaEndereco(
  codRuaEndereco INT NOT NULL AUTO_INCREMENT,
  codBairroEndereco INT NOT NULL,
  nomeRuaEndereco VARCHAR(100) NOT NULL,
  
  PRIMARY KEY(codRuaEndereco),
  FOREIGN KEY(codBairroEndereco) REFERENCES BairroEndereco(codBairroEndereco)
);

CREATE TABLE Cliente (
  codCliente INT NOT NULL AUTO_INCREMENT,
  nomeCliente VARCHAR(100),
  descricaoCliente VARCHAR(500),
  
  PRIMARY KEY(codCliente)
);

CREATE TABLE Contato (
  codCliente INT NOT NULL,
  valorContato VARCHAR(100),
  
  FOREIGN KEY(codCliente) REFERENCES Cliente(codCliente)
);

CREATE TABLE Endereco (
  codCliente INT NOT NULL,
  codCidadeEndereco INT NOT NULL,
  codBairroEndereco INT NOT NULL,
  codRuaEndereco INT NOT NULL,
  numeroEndereco INT NOT NULL,
  
  FOREIGN KEY (codCliente) REFERENCES Cliente
  (codCliente),
  FOREIGN KEY (codCidadeEndereco) REFERENCES CidadeEndereco(codCidadeEndereco),
  FOREIGN KEY (codBairroEndereco) REFERENCES BairroEndereco(codBairroEndereco),
  FOREIGN KEY (codRuaEndereco) REFERENCES RuaEndereco(codRuaEndereco)
);

/*

SELECT * FROM Cliente;
SELECT * FROM CidadeEndereco;
SELECT * FROM BairroEndereco;
SELECT * FROM RuaEndereco;
SELECT * FROM Endereco;
DROP SCHEMA makeupsolution;

SELECT cli.nomeCliente, cli.descricaoCliente, cid.nomeCidadeEndereco, bar.nomeBairroEndereco, rua.nomeRuaEndereco, ende.numeroEndereco
FROM Cliente cli
LEFT JOIN Endereco ende ON ende.codCliente = cli.codCliente
LEFT JOIN CidadeEndereco cid ON cid.codCidadeEndereco = ende.codCidadeEndereco
LEFT JOIN BairroEndereco bar ON bar.codBairroEndereco = cid.codCidadeEndereco
LEFT JOIN RuaEndereco rua ON rua.codRuaEndereco = bar.codBairroEndereco
WHERE cli.codCliente = 1;

INSERT INTO CidadeEndereco (
  nomeCidadeEndereco
) VALUES (
  "CidadeComIdEmVar"
);

INSERT INTO BairroEndereco ( codCidadeEndereco, nomeBairroEndereco ) VALUES ( 2, "TesteBairro" );

*/