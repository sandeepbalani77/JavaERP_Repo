-- V5: Client, Supplier, Carrier, and Employee lookup tables

CREATE TABLE atividade_for_cli (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT
);

CREATE TABLE situacao_for_cli (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT
);

CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    id_pessoa INTEGER NOT NULL,
    desde DATE,
    data_cadastro DATE,
    observacao TEXT,
    conta_contabil VARCHAR(30),
    gera_financeiro VARCHAR(1),
    indicador_preco VARCHAR(1),
    porcento_desconto DECIMAL(18,6),
    forma_desconto VARCHAR(1),
    limite_credito DECIMAL(18,6),
    tipo_frete VARCHAR(1),
    id_situacao_for_cli INTEGER NOT NULL,
    id_atividade_for_cli INTEGER NOT NULL,
    CONSTRAINT fk_cliente_situacao FOREIGN KEY (id_situacao_for_cli) REFERENCES situacao_for_cli(id),
    CONSTRAINT fk_cliente_atividade FOREIGN KEY (id_atividade_for_cli) REFERENCES atividade_for_cli(id)
);

CREATE TABLE fornecedor (
    id SERIAL PRIMARY KEY,
    id_pessoa INTEGER NOT NULL,
    desde DATE,
    data_cadastro DATE,
    observacao TEXT,
    conta_contabil VARCHAR(30),
    gera_financeiro VARCHAR(1),
    id_situacao_for_cli INTEGER NOT NULL,
    id_atividade_for_cli INTEGER NOT NULL,
    CONSTRAINT fk_fornecedor_situacao FOREIGN KEY (id_situacao_for_cli) REFERENCES situacao_for_cli(id),
    CONSTRAINT fk_fornecedor_atividade FOREIGN KEY (id_atividade_for_cli) REFERENCES atividade_for_cli(id)
);

CREATE TABLE transportadora (
    id SERIAL PRIMARY KEY,
    id_pessoa INTEGER NOT NULL,
    observacao TEXT,
    conta_contabil VARCHAR(30)
);

-- Employee lookup tables
CREATE TABLE tipo_admissao (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT
);

CREATE TABLE tipo_relacionamento (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT
);

CREATE TABLE situacao_colaborador (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT
);

CREATE TABLE tipo_desligamento (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT
);

CREATE TABLE tipo_colaborador (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT
);

CREATE TABLE nivel_formacao (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT
);

CREATE TABLE setor (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT
);

CREATE TABLE cargo (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    salario DECIMAL(18,6),
    cbo_1994 VARCHAR(10),
    cbo_2002 VARCHAR(10)
);

CREATE TABLE colaborador (
    id SERIAL PRIMARY KEY,
    id_pessoa INTEGER NOT NULL,
    id_cargo INTEGER NOT NULL,
    id_setor INTEGER NOT NULL,
    id_tipo_admissao INTEGER,
    id_tipo_relacionamento INTEGER,
    id_situacao_colaborador INTEGER NOT NULL,
    id_tipo_desligamento INTEGER,
    id_tipo_colaborador INTEGER NOT NULL,
    id_nivel_formacao INTEGER NOT NULL,
    data_admissao DATE,
    data_demissao DATE,
    ctps_numero VARCHAR(20),
    ctps_serie VARCHAR(10),
    ctps_data_expedicao DATE,
    ctps_uf VARCHAR(2),
    observacao TEXT,
    pis_numero VARCHAR(20),
    CONSTRAINT fk_colaborador_cargo FOREIGN KEY (id_cargo) REFERENCES cargo(id),
    CONSTRAINT fk_colaborador_setor FOREIGN KEY (id_setor) REFERENCES setor(id),
    CONSTRAINT fk_colaborador_tipo_admissao FOREIGN KEY (id_tipo_admissao) REFERENCES tipo_admissao(id),
    CONSTRAINT fk_colaborador_tipo_relacionamento FOREIGN KEY (id_tipo_relacionamento) REFERENCES tipo_relacionamento(id),
    CONSTRAINT fk_colaborador_situacao FOREIGN KEY (id_situacao_colaborador) REFERENCES situacao_colaborador(id),
    CONSTRAINT fk_colaborador_tipo_desligamento FOREIGN KEY (id_tipo_desligamento) REFERENCES tipo_desligamento(id),
    CONSTRAINT fk_colaborador_tipo FOREIGN KEY (id_tipo_colaborador) REFERENCES tipo_colaborador(id),
    CONSTRAINT fk_colaborador_nivel_formacao FOREIGN KEY (id_nivel_formacao) REFERENCES nivel_formacao(id)
);
