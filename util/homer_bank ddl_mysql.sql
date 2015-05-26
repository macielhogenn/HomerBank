UNLOCK TABLES;

DROP SCHEMA IF EXISTS homer_bank;
CREATE SCHEMA homer_bank;

USE homer_bank;

CREATE TABLE clients (
    client_id INT AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    CONSTRAINT PK_client_id PRIMARY KEY (client_id)
)ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,'João da Silva'),(2,'Maria Antonieta'),(3,'Nutella Corporation');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

CREATE TABLE agencies (
  agency_id INT AUTO_INCREMENT,
  number_agency VARCHAR(10) NOT NULL,
  CONSTRAINT PK_agency_id PRIMARY KEY (agency_id),
  CONSTRAINT UK_number_agency UNIQUE (number_agency)
)ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

LOCK TABLES `agencies` WRITE;
/*!40000 ALTER TABLE `agencies` DISABLE KEYS */;
INSERT INTO `agencies` VALUES (1,'0123-x'),(2,'0456-x');
/*!40000 ALTER TABLE `agencies` ENABLE KEYS */;
UNLOCK TABLES;

CREATE TABLE accounts (
	account_id INT AUTO_INCREMENT,
	number_account VARCHAR(20) NOT NULL,
	password_account VARCHAR(50) NOT NULL,
	type_account INT NOT NULL,
	date_of_creation DATE NOT NULL,
	balance NUMERIC(18,2) NOT NULL,
	client_id INT NOT NULL,
    agency_id INT NOT NULL,
    CONSTRAINT PK_account_id PRIMARY KEY (account_id),
    CONSTRAINT UQ_account_number_account UNIQUE (number_account),
    CONSTRAINT fk_account_clients FOREIGN KEY (client_id) references clients (client_id),
    CONSTRAINT fk_account_agencies FOREIGN KEY (agency_id) references agencies (agency_id)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (1,'123-3','202cb962ac59075b964b07152d234b70',1,'2015-05-25',1000.00,1,1),(2,'456-6','202cb962ac59075b964b07152d234b70',1,'2015-05-25',1500.00,2,2),(3,'789-9','202cb962ac59075b964b07152d234b70',1,'2015-05-25',10000.00,3,2);
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

CREATE TABLE transactions (
  transaction_id INT AUTO_INCREMENT,
  account_id_credit INT NOT NULL,
  account_id_debit INT NOT NULL,
  date_transaction DATE NOT NULL,
  amount NUMERIC(18,2) NOT NULL,
  CONSTRAINT PK_transaction_id PRIMARY KEY (transaction_id),
  CONSTRAINT FK_transaction_account_credit FOREIGN KEY (account_id_credit) REFERENCES accounts (account_id),
  CONSTRAINT FK_transaction_account_debit FOREIGN KEY (account_id_debit) REFERENCES accounts (account_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE slips (
    slip_id INT AUTO_INCREMENT,
    to_account INT NOT NULL,
    from_account INT,
    bar_code VARCHAR(100) NOT NULL,
    date_of_payment DATE,
    amount NUMERIC(18,2),
    CONSTRAINT PK_slip_id PRIMARY KEY (slip_id),
	CONSTRAINT UQ_bar_code UNIQUE (bar_code),
    CONSTRAINT FK_to_account FOREIGN KEY (to_account) REFERENCES accounts (account_id),
    CONSTRAINT FK_from_account FOREIGN KEY (from_account) REFERENCES accounts (account_id)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

LOCK TABLES `slips` WRITE;
/*!40000 ALTER TABLE `slips` DISABLE KEYS */;
INSERT INTO `slips` VALUES (1,3,NULL,'1111','2015-06-10',150.00),(2,3,NULL,'2222','2015-06-10',200.00);
/*!40000 ALTER TABLE `slips` ENABLE KEYS */;
UNLOCK TABLES;

---------------------------
DELIMITER //
CREATE TRIGGER balance_acaounts_transaction
AFTER INSERT ON transactions
FOR EACH ROW
BEGIN
	DECLARE balance_debit_account NUMERIC(18,2);
	DECLARE balance_credit_account NUMERIC(18,2);

  SELECT balance INTO balance_debit_account FROM accounts WHERE account_id = NEW.account_id_debit;
  SELECT balance INTO balance_credit_account FROM accounts WHERE account_id = NEW.account_id_credit;
  
  UPDATE accounts SET balance = (balance_debit_account - NEW.amount) WHERE account_id = NEW.account_id_debit;
  UPDATE accounts SET balance = (balance_credit_account + NEW.amount) WHERE account_id = NEW.account_id_credit;
END //
DELIMITER ;