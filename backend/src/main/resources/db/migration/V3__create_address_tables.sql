-- V3: Address tables (Pais, Uf, Municipio, Cep)

CREATE TABLE pais (
    id BIGSERIAL PRIMARY KEY,
    nome_pt VARCHAR(100),
    nome_en VARCHAR(100),
    codigo_bacen INTEGER
);

CREATE TABLE uf (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100),
    sigla VARCHAR(2),
    codigo_ibge INTEGER,
    id_pais BIGINT REFERENCES pais(id)
);

CREATE TABLE municipio (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(200),
    codigo_ibge INTEGER,
    codigo_receita_federal INTEGER,
    codigo_estadual INTEGER,
    id_uf BIGINT REFERENCES uf(id)
);

CREATE TABLE cep (
    id BIGSERIAL PRIMARY KEY,
    numero VARCHAR(8),
    logradouro VARCHAR(200),
    complemento VARCHAR(200),
    bairro VARCHAR(100),
    id_municipio BIGINT REFERENCES municipio(id),
    id_uf BIGINT REFERENCES uf(id)
);

CREATE INDEX idx_pais_nome_pt ON pais(nome_pt);
CREATE INDEX idx_uf_sigla ON uf(sigla);
CREATE INDEX idx_uf_id_pais ON uf(id_pais);
CREATE INDEX idx_municipio_nome ON municipio(nome);
CREATE INDEX idx_municipio_codigo_ibge ON municipio(codigo_ibge);
CREATE INDEX idx_municipio_id_uf ON municipio(id_uf);
CREATE INDEX idx_cep_numero ON cep(numero);
CREATE INDEX idx_cep_id_municipio ON cep(id_municipio);
