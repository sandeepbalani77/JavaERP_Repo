-- V4: Person tables (EstadoCivil, Pessoa, PessoaFisica, PessoaJuridica, PessoaContato, PessoaEndereco)

CREATE TABLE estado_civil (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100),
    descricao VARCHAR(250)
);

CREATE TABLE pessoa (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    tipo VARCHAR(1),
    email VARCHAR(250),
    site VARCHAR(250),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE pessoa_fisica (
    id BIGSERIAL PRIMARY KEY,
    id_pessoa BIGINT NOT NULL REFERENCES pessoa(id) ON DELETE CASCADE,
    cpf VARCHAR(11),
    rg VARCHAR(20),
    orgao_rg VARCHAR(20),
    data_expedicao_rg DATE,
    data_nascimento DATE,
    sexo VARCHAR(1),
    raca VARCHAR(1),
    nacionalidade VARCHAR(100),
    naturalidade VARCHAR(100),
    nome_mae VARCHAR(150),
    nome_pai VARCHAR(150),
    id_estado_civil BIGINT REFERENCES estado_civil(id),
    CONSTRAINT uk_pessoa_fisica_pessoa UNIQUE (id_pessoa),
    CONSTRAINT uk_pessoa_fisica_cpf UNIQUE (cpf)
);

CREATE TABLE pessoa_juridica (
    id BIGSERIAL PRIMARY KEY,
    id_pessoa BIGINT NOT NULL REFERENCES pessoa(id) ON DELETE CASCADE,
    cnpj VARCHAR(14),
    nome_fantasia VARCHAR(150),
    inscricao_estadual VARCHAR(30),
    inscricao_municipal VARCHAR(30),
    data_cadastro DATE,
    data_constituicao DATE,
    tipo_regime VARCHAR(1),
    crt VARCHAR(1),
    suframa VARCHAR(20),
    CONSTRAINT uk_pessoa_juridica_pessoa UNIQUE (id_pessoa),
    CONSTRAINT uk_pessoa_juridica_cnpj UNIQUE (cnpj)
);

CREATE TABLE pessoa_contato (
    id BIGSERIAL PRIMARY KEY,
    id_pessoa BIGINT NOT NULL REFERENCES pessoa(id) ON DELETE CASCADE,
    nome VARCHAR(100),
    email VARCHAR(250),
    fone_comercial VARCHAR(20),
    fone_pessoal VARCHAR(20),
    celular VARCHAR(20)
);

CREATE TABLE pessoa_endereco (
    id BIGSERIAL PRIMARY KEY,
    id_pessoa BIGINT NOT NULL REFERENCES pessoa(id) ON DELETE CASCADE,
    logradouro VARCHAR(250),
    numero VARCHAR(10),
    complemento VARCHAR(100),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    cep VARCHAR(8),
    uf VARCHAR(2),
    pais VARCHAR(100),
    municipio_ibge INTEGER,
    principal VARCHAR(1)
);

CREATE INDEX idx_pessoa_nome ON pessoa(nome);
CREATE INDEX idx_pessoa_tipo ON pessoa(tipo);
CREATE INDEX idx_pessoa_fisica_cpf ON pessoa_fisica(cpf);
CREATE INDEX idx_pessoa_fisica_id_pessoa ON pessoa_fisica(id_pessoa);
CREATE INDEX idx_pessoa_juridica_cnpj ON pessoa_juridica(cnpj);
CREATE INDEX idx_pessoa_juridica_id_pessoa ON pessoa_juridica(id_pessoa);
CREATE INDEX idx_pessoa_contato_id_pessoa ON pessoa_contato(id_pessoa);
CREATE INDEX idx_pessoa_endereco_id_pessoa ON pessoa_endereco(id_pessoa);
