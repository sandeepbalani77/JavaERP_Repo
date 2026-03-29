-- V2: Seed default admin user and roles (H2 compatible)
-- Default admin password: admin123 (BCrypt encoded)

INSERT INTO empresa (razao_social, fantasia, cnpj)
VALUES ('T2Ti Tecnologia da Informacao', 'T2Ti ERP', '00000000000000');

INSERT INTO usuario (login, senha, administrador, empresa_id)
VALUES ('admin', '$2a$10$.vQYZHYQr/ko5XWQIueE3u0d2n4q3dCZr3NCaUKKWkZX9ngmlQbV.', TRUE, 1);

INSERT INTO papel (nome, descricao)
VALUES ('ADMIN', 'Administrador do sistema com acesso total');

INSERT INTO papel (nome, descricao)
VALUES ('USUARIO', 'Usuario padrao com acesso limitado');

INSERT INTO papel_funcao (papel_id, funcao, pode_inserir, pode_alterar, pode_excluir, pode_consultar)
VALUES (1, 'CADASTROS', TRUE, TRUE, TRUE, TRUE);

INSERT INTO papel_funcao (papel_id, funcao, pode_inserir, pode_alterar, pode_excluir, pode_consultar)
VALUES (1, 'FINANCEIRO', TRUE, TRUE, TRUE, TRUE);

INSERT INTO papel_funcao (papel_id, funcao, pode_inserir, pode_alterar, pode_excluir, pode_consultar)
VALUES (1, 'ESTOQUE', TRUE, TRUE, TRUE, TRUE);

INSERT INTO papel_funcao (papel_id, funcao, pode_inserir, pode_alterar, pode_excluir, pode_consultar)
VALUES (2, 'CADASTROS', FALSE, FALSE, FALSE, TRUE);

INSERT INTO papel_funcao (papel_id, funcao, pode_inserir, pode_alterar, pode_excluir, pode_consultar)
VALUES (2, 'FINANCEIRO', FALSE, FALSE, FALSE, TRUE);

INSERT INTO usuario_papel (usuario_id, papel_id)
VALUES (1, 1);
