-- V6: Product and related tables

CREATE TABLE produto_marca (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT
);

CREATE TABLE ncm (
    id SERIAL PRIMARY KEY,
    codigo VARCHAR(20),
    descricao TEXT,
    observacao TEXT
);

CREATE TABLE unidade_produto (
    id SERIAL PRIMARY KEY,
    sigla VARCHAR(10) NOT NULL,
    descricao VARCHAR(250),
    pode_fracionar VARCHAR(1)
);

CREATE TABLE produto_grupo (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT
);

CREATE TABLE produto_sub_grupo (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    id_produto_grupo INTEGER NOT NULL,
    CONSTRAINT fk_sub_grupo_grupo FOREIGN KEY (id_produto_grupo) REFERENCES produto_grupo(id)
);

CREATE TABLE produto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    descricao TEXT,
    gtin VARCHAR(14),
    codigo_interno VARCHAR(60),
    valor_compra DECIMAL(18,6),
    valor_venda DECIMAL(18,6),
    preco_venda_minimo DECIMAL(18,6),
    preco_sugerido DECIMAL(18,6),
    custo_medio_liquido DECIMAL(18,6),
    preco_lucro_zero DECIMAL(18,6),
    preco_lucro_minimo DECIMAL(18,6),
    preco_lucro_maximo DECIMAL(18,6),
    markup DECIMAL(18,6),
    quantidade_estoque DECIMAL(18,6),
    quantidade_estoque_anterior DECIMAL(18,6),
    estoque_minimo DECIMAL(18,6),
    estoque_maximo DECIMAL(18,6),
    estoque_ideal DECIMAL(18,6),
    id_ncm INTEGER,
    id_unidade_produto INTEGER NOT NULL,
    id_sub_grupo INTEGER NOT NULL,
    id_marca_produto INTEGER,
    foto_produto VARCHAR(250),
    data_cadastro DATE,
    data_alteracao DATE,
    iat VARCHAR(1),
    ippt VARCHAR(1),
    tipo_item_sped VARCHAR(2),
    CONSTRAINT fk_produto_ncm FOREIGN KEY (id_ncm) REFERENCES ncm(id),
    CONSTRAINT fk_produto_unidade FOREIGN KEY (id_unidade_produto) REFERENCES unidade_produto(id),
    CONSTRAINT fk_produto_sub_grupo FOREIGN KEY (id_sub_grupo) REFERENCES produto_sub_grupo(id),
    CONSTRAINT fk_produto_marca FOREIGN KEY (id_marca_produto) REFERENCES produto_marca(id)
);
