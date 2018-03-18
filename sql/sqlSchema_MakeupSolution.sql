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
  nomeCliente VARCHAR(100) NOT NULL,
  descricaoCliente VARCHAR(500) DEFAULT "",
  
  PRIMARY KEY(codCliente)
);

CREATE TABLE Contato (
  codCliente INT NOT NULL,
  valorContato VARCHAR(100),
  
  FOREIGN KEY(codCliente) REFERENCES Cliente(codCliente)
);

CREATE TABLE Endereco (
  codCliente INT NOT NULL UNIQUE,
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

CREATE TABLE Agenda (
  codAgenda INT NOT NULL AUTO_INCREMENT,
  codCliente INT NOT NULL,
  valorAgenda DOUBLE(10,2) DEFAULT 0.0,
  dataHoraRegistradoAgenda DATETIME NOT NULL,
  dataHoraAlvoAgenda DATETIME NOT NULL,
  
  PRIMARY KEY(codAgenda),
  FOREIGN KEY(codCliente) REFERENCES Cliente(codCliente)
);

CREATE TABLE Produto (
  codProduto INT NOT NULL AUTO_INCREMENT,
  nomeProduto VARCHAR(100) NOT NULL,
  precoProduto DOUBLE(10,2) DEFAULT 0.0,
  quantidadeProduto INT DEFAULT 0,
  dataProduto DATETIME NOT NULL,
  
  PRIMARY KEY(codProduto)
);


/*
/ // // // // // //
  TESTING QUERIES  
// // // // // // /

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

SELECT * FROM Cliente;
SELECT * FROM Agenda;
UPDATE Agenda SET concluidoAgenda = 0 WHERE codCliente = 1;

INSERT INTO Agenda (
  codCliente,
  valorAgenda,
  dataHoraRegistradoAgenda,
  dataHoraAlvoAgenda
) VALUES (
  ?,
  ?,
  ?,
  ?
);

# Show all from table Agenda except if time passed in register date ascendant order

SELECT age.codAgenda, age.codCliente, cli.nomeCliente, cli.descricaoCliente, age.dataHoraAlvoAgenda, age.valorAgenda, age.dataHoraRegistradoAgenda
FROM Agenda age
LEFT JOIN Cliente cli ON cli.codCliente = age.codCliente
WHERE age.dataHoraAlvoAgenda < current_timestamp()
ORDER BY age.dataHoraAlvoAgenda ASC;

# Get week and between dates

SELECT *
FROM Produto
WHERE  YEARWEEK(`dataProduto`, 1) = YEARWEEK(CURDATE(), 1);

SELECT codProduto, nomeProduto, precoProduto, quantidadeProduto, dataProduto FROM Produto 
WHERE dataProduto BETWEEN '2018-01-01' AND '2018-06-01';

*/
