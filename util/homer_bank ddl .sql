DROP TABLE clients;
DROP TABLE agencies;
DROP TABLE accounts;
DROP TABLE transactions;

CREATE TABLE clients (
    client_id INTEGER,
    name VARCHAR(50) NOT NULL,
    CONSTRAINT PK_client_id PRIMARY KEY (client_id)
);

CREATE SEQUENCE SQ_ID_CLIENT MINVALUE 1 MAXVALUE 90000000 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER auto_increment_client_id
BEFORE INSERT ON clients
FOR EACH ROW
BEGIN
  IF :NEW.client_id IS NULL THEN
    :NEW.client_id := SQ_ID_CLIENT.nextval;
  END IF;
END;

CREATE TABLE agencies (
  agency_id INT,
  number_agency VARCHAR(10) NOT NULL,
  CONSTRAINT PK_agency_id PRIMARY KEY (agency_id),
  CONSTRAINT UK_number_agency UNIQUE (number_agency)
)

CREATE SEQUENCE SQ_ID_AGENCY MINVALUE 1 MAXVALUE 90000000 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER auto_increment_agency_id
BEFORE INSERT ON agencies
FOR EACH ROW
BEGIN
  IF :NEW.agency_id IS NULL THEN
    :NEW.agency_id := SQ_ID_AGENCY.nextval;
  END IF;
END;

CREATE TABLE accounts (
		account_id INT,
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
);

CREATE SEQUENCE SQ_ID_ACCOUNT MINVALUE 1 MAXVALUE 90000000 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER auto_increment_account_id
BEFORE INSERT ON accounts
FOR EACH ROW
BEGIN
  IF :NEW.account_id IS NULL THEN
    :NEW.account_id := SQ_ID_ACCOUNT.nextval;
  END IF;
END;

CREATE TABLE transactions (
  transaction_id INT,
  account_id_credit INT NOT NULL,
  account_id_debit INT NOT NULL,
  date_transaction DATE NOT NULL,
  amount NUMERIC(18,2) NOT NULL,
  CONSTRAINT PK_transaction_id PRIMARY KEY (transaction_id),
  CONSTRAINT FK_transaction_account_credit FOREIGN KEY (account_id_credit) REFERENCES accounts (account_id),
  CONSTRAINT FK_transaction_account_debit FOREIGN KEY (account_id_debit) REFERENCES accounts (account_id)
);

CREATE SEQUENCE SQ_ID_TRANSACTION MINVALUE 1 MAXVALUE 90000000 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER auto_increment_transaction_id
BEFORE INSERT ON transactions
FOR EACH ROW
BEGIN
  IF :NEW.transaction_id IS NULL THEN
    :NEW.transaction_id := SQ_ID_TRANSACTION.nextval;
  END IF;
END;


---------------------------
CREATE OR REPLACE TRIGGER balance_acaounts_transaction
AFTER INSERT ON transactions
FOR EACH ROW
DECLARE
  balance_debit_account accounts.balance%TYPE;
  balance_credit_account accounts.balance%TYPE;
BEGIN
  SELECT balance INTO balance_debit_account FROM accounts WHERE account_id = :NEW.account_id_debit;
  SELECT balance INTO balance_credit_account FROM accounts WHERE account_id = :NEW.account_id_credit;
  
  UPDATE accounts SET balance = (balance_debit_account - :NEW.amount) WHERE account_id = :NEW.account_id_debit;
  UPDATE accounts SET balance = (balance_credit_account + :NEW.amount) WHERE account_id = :NEW.account_id_credit;
END;