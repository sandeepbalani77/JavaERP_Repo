-- V1: Create authentication and authorization tables

CREATE TABLE empresa (
    id BIGSERIAL PRIMARY KEY,
    razao_social VARCHAR(250) NOT NULL,
    fantasia VARCHAR(250),
    cnpj VARCHAR(14) UNIQUE,
    inscricao_estadual VARCHAR(30),
    inscricao_municipal VARCHAR(30),
    tipo_regime VARCHAR(1),
    crt VARCHAR(1),
    endereco_logradouro VARCHAR(250),
    endereco_numero VARCHAR(10),
    endereco_complemento VARCHAR(100),
    endereco_bairro VARCHAR(100),
    endereco_cidade VARCHAR(100),
    endereco_uf VARCHAR(2),
    endereco_cep VARCHAR(8),
    contato_telefone VARCHAR(15),
    contato_email VARCHAR(250),
    logotipo TEXT,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE usuario (
    id BIGSERIAL PRIMARY KEY,
    login VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    colaborador_id BIGINT,
    administrador BOOLEAN NOT NULL DEFAULT FALSE,
    empresa_id BIGINT REFERENCES empresa(id),
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE papel (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    descricao VARCHAR(255)
);

CREATE TABLE papel_funcao (
    id BIGSERIAL PRIMARY KEY,
    papel_id BIGINT NOT NULL REFERENCES papel(id) ON DELETE CASCADE,
    funcao VARCHAR(100) NOT NULL,
    pode_inserir BOOLEAN NOT NULL DEFAULT FALSE,
    pode_alterar BOOLEAN NOT NULL DEFAULT FALSE,
    pode_excluir BOOLEAN NOT NULL DEFAULT FALSE,
    pode_consultar BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE usuario_papel (
    usuario_id BIGINT NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
    papel_id BIGINT NOT NULL REFERENCES papel(id) ON DELETE CASCADE,
    PRIMARY KEY (usuario_id, papel_id)
);

-- Indexes
CREATE INDEX idx_usuario_login ON usuario(login);
CREATE INDEX idx_usuario_empresa ON usuario(empresa_id);
CREATE INDEX idx_papel_funcao_papel ON papel_funcao(papel_id);
CREATE INDEX idx_papel_funcao_funcao ON papel_funcao(funcao);
