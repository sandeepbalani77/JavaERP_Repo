-- V2: Seed default admin user and roles
-- Default admin password: admin123 (BCrypt encoded)

INSERT INTO empresa (id, razao_social, fantasia, cnpj)
VALUES (1, 'T2Ti Tecnologia da Informação', 'T2Ti ERP', '00000000000000');

INSERT INTO usuario (id, login, senha, administrador, empresa_id)
VALUES (1, 'admin', '$2a$10$.vQYZHYQr/ko5XWQIueE3u0d2n4q3dCZr3NCaUKKWkZX9ngmlQbV.', TRUE, 1);

INSERT INTO papel (id, nome, descricao)
VALUES (1, 'ADMIN', 'Administrador do sistema com acesso total');

INSERT INTO papel (id, nome, descricao)
VALUES (2, 'USUARIO', 'Usuário padrão com acesso limitado');

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

-- Reset sequences
SELECT setval('empresa_id_seq', (SELECT MAX(id) FROM empresa));
SELECT setval('usuario_id_seq', (SELECT MAX(id) FROM usuario));
SELECT setval('papel_id_seq', (SELECT MAX(id) FROM papel));
SELECT setval('papel_funcao_id_seq', (SELECT MAX(id) FROM papel_funcao));
