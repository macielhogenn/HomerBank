CREATE TABLE client (
    client_id INTEGER,
    name VARCHAR(50) NOT NULL,
    CONSTRAINT PK_client_id PRIMARY KEY (client_id)
);

CREATE SEQUENCE SQ_ID_CLIENT MINVALUE 1 MAXVALUE 90000000 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER auto_increment_client_id
BEFORE INSERT ON client
FOR EACH ROW
BEGIN
  IF :NEW.client_id IS NULL THEN
    :NEW.client_id := SQ_ID_CLIENT.nextval;
  END IF;
END;

CREATE TABLE account (
		account_id INT,
		number_agency VARCHAR(10) NOT NULL,
		number_account VARCHAR(20) NOT NULL,
		password_account VARCHAR(50) NOT NULL,
		type_account INT NOT NULL,
		date_of_creation DATE NOT NULL,
		balance NUMERIC(18,2) NOT NULL,
		client_id INT NOT NULL,
    CONSTRAINT PK_account_id PRIMARY KEY (account_id),
    CONSTRAINT UQ_account_number_agency UNIQUE (number_agency),
    CONSTRAINT UQ_account_number_account UNIQUE (number_account),
    CONSTRAINT fk_account_client FOREIGN KEY (client_id) references client (client_id)
);

CREATE SEQUENCE SQ_ID_ACCOUNT MINVALUE 1 MAXVALUE 90000000 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER auto_increment_account_id
BEFORE INSERT ON account
FOR EACH ROW
BEGIN
  IF :NEW.account_id IS NULL THEN
    :NEW.account_id := SQ_ID_ACCOUNT.nextval;
  END IF;
END;

CREATE TABLE transaction (
  transaction_id INT,
  account_id_credit INT NOT NULL,
  account_id_debit INT NOT NULL,
  date_transaction DATE NOT NULL,
  amount NUMERIC(18,2) NOT NULL,
  CONSTRAINT PK_transaction_id PRIMARY KEY (transaction_id),
  CONSTRAINT FK_transaction_account_credit FOREIGN KEY (account_id_credit) REFERENCES account (account_id),
  CONSTRAINT FK_transaction_account_debit FOREIGN KEY (account_id_debit) REFERENCES account (account_id)
);

CREATE SEQUENCE SQ_ID_TRANSACTION MINVALUE 1 MAXVALUE 90000000 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER auto_increment_transaction_id
BEFORE INSERT ON transaction
FOR EACH ROW
BEGIN
  IF :NEW.transaction_id IS NULL THEN
    :NEW.transaction_id := SQ_ID_TRANSACTION.nextval;
  END IF;
END;