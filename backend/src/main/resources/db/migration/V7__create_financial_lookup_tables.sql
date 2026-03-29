-- V7: Bank, Account, Cheque tables

CREATE TABLE banco (
    id SERIAL PRIMARY KEY,
    codigo VARCHAR(10),
    nome VARCHAR(100),
    url VARCHAR(250)
);

CREATE TABLE agencia_banco (
    id SERIAL PRIMARY KEY,
    id_banco INTEGER NOT NULL,
    numero VARCHAR(20),
    digito VARCHAR(2),
    nome VARCHAR(100),
    gerente VARCHAR(100),
    telefone VARCHAR(20),
    CONSTRAINT fk_agencia_banco FOREIGN KEY (id_banco) REFERENCES banco(id)
);

CREATE TABLE conta_caixa (
    id SERIAL PRIMARY KEY,
    id_agencia_banco INTEGER,
    codigo VARCHAR(20),
    nome VARCHAR(100),
    descricao TEXT,
    tipo VARCHAR(1),
    data_cadastro DATE,
    CONSTRAINT fk_conta_agencia FOREIGN KEY (id_agencia_banco) REFERENCES agencia_banco(id)
);

CREATE TABLE talonario_cheque (
    id SERIAL PRIMARY KEY,
    id_conta_caixa INTEGER NOT NULL,
    talao VARCHAR(20),
    numero INTEGER,
    status_talao VARCHAR(1),
    CONSTRAINT fk_talonario_conta FOREIGN KEY (id_conta_caixa) REFERENCES conta_caixa(id)
);

CREATE TABLE cheque (
    id SERIAL PRIMARY KEY,
    id_talonario_cheque INTEGER NOT NULL,
    numero INTEGER,
    status_cheque VARCHAR(1),
    data_cheque DATE,
    CONSTRAINT fk_cheque_talonario FOREIGN KEY (id_talonario_cheque) REFERENCES talonario_cheque(id)
);
