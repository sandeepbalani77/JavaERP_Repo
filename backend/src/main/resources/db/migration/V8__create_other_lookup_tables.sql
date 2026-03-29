-- V8: CFOP, Feriados, Almoxarifado, OperadoraPlanoSaude, OperadoraCartao, Contador, Sindicato, Convenio

CREATE TABLE cfop (
    id SERIAL PRIMARY KEY,
    codigo INTEGER,
    descricao TEXT,
    aplicacao TEXT
);

CREATE TABLE feriados (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    data_feriado DATE,
    abrangencia VARCHAR(1),
    tipo VARCHAR(1),
    uf VARCHAR(2),
    municipio_ibge INTEGER
);

CREATE TABLE almoxarifado (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT
);

CREATE TABLE operadora_plano_saude (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    registro_ans VARCHAR(20),
    contato VARCHAR(100)
);

CREATE TABLE operadora_cartao (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    bandeira VARCHAR(50),
    taxa_adm DECIMAL(18,6),
    taxa_antecipacao DECIMAL(18,6),
    vencimento INTEGER
);

CREATE TABLE contador (
    id SERIAL PRIMARY KEY,
    id_pessoa INTEGER,
    crc_inscricao VARCHAR(20),
    crc_uf VARCHAR(2)
);

CREATE TABLE sindicato (
    id SERIAL PRIMARY KEY,
    id_pessoa INTEGER,
    nome VARCHAR(150),
    tipo VARCHAR(1),
    data_base DATE,
    piso_salarial DECIMAL(18,6)
);

CREATE TABLE convenio (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    descricao TEXT,
    logradouro VARCHAR(200),
    numero VARCHAR(10),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    uf VARCHAR(2),
    telefone VARCHAR(20),
    contato VARCHAR(100)
);
