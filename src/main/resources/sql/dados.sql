-- ## ENDERECOS ## --
INSERT INTO endereco (cep, complemento, logradouro, numero, bairro_id, cidade_id, estado_id) VALUES ( '93.314-112', null, 'RUA NOVO MUNDO', '673', 3251, 2052, 8);
INSERT INTO endereco (cep, complemento, logradouro, numero, bairro_id, cidade_id, estado_id) VALUES ( '36.023-931', null, 'AVENIDA MACHADO', '76', 3251, 2052, 8);
INSERT INTO  endereco (cep, complemento, logradouro, numero, bairro_id, cidade_id, estado_id) VALUES ( '97.086-826', null, 'RUA CARDEAL DA SILVA', '83092', 3251, 2052, 8);
INSERT INTO endereco (cep, complemento, logradouro, numero, bairro_id, cidade_id, estado_id) VALUES ( '06.031-754', null, 'RUA TOMÉ-AÇU', '9722', 3270, 2052, 8);
INSERT INTO endereco (cep, complemento, logradouro, numero, bairro_id, cidade_id, estado_id) VALUES ( '50.221-076', null, 'RUA NAZARÉ', '96', 3516, 2072, 8);
INSERT INTO endereco (cep, complemento, logradouro, numero, bairro_id, cidade_id, estado_id) VALUES ( '28.422-571', null, 'AVENIDA FARIA LEMOS', '37110', 3418, 2068, 8);
INSERT INTO endereco (cep, complemento, logradouro, numero, bairro_id, cidade_id, estado_id) VALUES ( '94.433-468', null, 'RUA TOUROS', '99', 3365, 2055, 8);
INSERT INTO endereco (cep, complemento, logradouro, numero, bairro_id, cidade_id, estado_id) VALUES ( '67.376-783', null, 'AVENIDA OSVALDO CRUZ', '5491', 3113, 2017, 8);
INSERT INTO endereco (cep, complemento, logradouro, numero, bairro_id, cidade_id, estado_id) VALUES ( '93.314-112', null, 'RUA BARRA DO GUARITA', '673', 3517, 2072, 8);
INSERT INTO endereco (cep, complemento, logradouro, numero, bairro_id, cidade_id, estado_id) VALUES ( '36.023-931', null, 'AVENIDA SÃO LUÍS GONZAGA DO MARANHÃO', '76', 3251, 2052, 8);
INSERT INTO endereco (cep, complemento, logradouro, numero, bairro_id, cidade_id, estado_id) VALUES ( '97.086-826', null, 'RUA CURITIBANOS', '83092', 3251, 2052, 8);
INSERT INTO endereco (cep, complemento, logradouro, numero, bairro_id, cidade_id, estado_id) VALUES ( '06.031-754', null, 'TRAVESSIA SERRA PRETA', '9722', 3270, 2052, 8);
INSERT INTO endereco (cep, complemento, logradouro, numero, bairro_id, cidade_id, estado_id) VALUES ( '50.221-076', null, 'AVENIDA MASSAPÊ DO PIAUÍ', '96', 3516, 2072, 8);
INSERT INTO endereco (cep, complemento, logradouro, numero, bairro_id, cidade_id, estado_id) VALUES ( '28.422-571', null, 'AVENIDA CANABRAVA DO NORTE', '37110', 3418, 2068, 8);
INSERT INTO endereco (cep, complemento, logradouro, numero, bairro_id, cidade_id, estado_id) VALUES ( '94.433-468', null, 'RUA ÁGUA DOCE', '99', 3365, 2055, 8);
INSERT INTO endereco (cep, complemento, logradouro, numero, bairro_id, cidade_id, estado_id) VALUES ( '67.376-783', null, 'RUA MASSARANDUBA', '5491', 3113, 2017, 8);

-- ## TELEFONES ##--
INSERT INTO telefone (numero) VALUES ('27 32077244');
INSERT INTO telefone (numero) VALUES ('27 32581854');
INSERT INTO telefone (numero) VALUES ('27 32230755');
INSERT INTO telefone (numero) VALUES ('27 37714187');
INSERT INTO telefone (numero) VALUES ('27 38710259');
INSERT INTO telefone (numero) VALUES ('27 38416931');
INSERT INTO telefone (numero) VALUES ('27 35287175');
INSERT INTO telefone (numero) VALUES ('27 39675834');
INSERT INTO telefone (numero) VALUES ('27 30626982');
INSERT INTO telefone (numero) VALUES ('27 33977658');
INSERT INTO telefone (numero) VALUES ('27 33981210');
INSERT INTO telefone (numero) VALUES ('27 32249745');
INSERT INTO telefone (numero) VALUES ('27 37993968');
INSERT INTO telefone (numero) VALUES ('27 30836516');
INSERT INTO telefone (numero) VALUES ('27 34416945');
INSERT INTO telefone (numero) VALUES ('27 39774751');

-- ## BANCO DE OLHOS ## --
INSERT INTO bancoolhos (cnes, email, fantasia, nome, endereco_id,telefone_id) VALUES ('313334', 'BANCOOLHOS1@EMAIL.COM', 'FANTASIA BANCO DE OLHOS 1', 'BANCO DE OLHOS 1', 3, 3);
INSERT INTO bancoolhos (cnes, email, fantasia, nome, endereco_id,telefone_id) VALUES ('4456645', 'BANCOOLHOS2@EMAIL.COM', 'FANTASIA BANCO DE OLHOS 2', 'BANCO DE OLHOS 2', null, null);

-- ## HOSPITAIS ## --
INSERT INTO instituicaonotificadora (dtype, cnes, email, fantasia, nome, sigla, endereco_id, telefone_id, bancoolhos_id) VALUES ('Hospital', '4044916', 'HUCAM@email.com', 'HOSPITAL DAS CLINICAS FANTASIA', 'HOSPITAL DAS CLINICAS', 'HUCAM', 1, 1, 1);
INSERT INTO instituicaonotificadora (dtype, cnes, email, fantasia, nome, sigla, endereco_id, telefone_id, bancoolhos_id) VALUES ('Hospital', '4458179', 'HPM@email.com', 'HOSPITAL DA POLICIA MILITAR FANTASIA', 'HOSPITAL DA POLICIA MILITAR', 'HPM', 2, 2, 2);

-- ## FUNCIONARIOS ## --
INSERT INTO documentocomfoto (documento, tipodocumentocomfoto) VALUES ('111111111', 'RG');
INSERT INTO documentocomfoto (documento, tipodocumentocomfoto) VALUES ('222222222', 'RG');
INSERT INTO documentocomfoto (documento, tipodocumentocomfoto) VALUES ('333333333333', 'RG');
INSERT INTO documentocomfoto (documento, tipodocumentocomfoto) VALUES ('55555555555', 'RG');

INSERT INTO funcionario (nome,ativo,cpf,documentosocial_id,email,senha,endereco_id,telefone_id) VALUES ('Notificador 1', true, '111.111.111-11', 1, 'notificador1@email.com', '$2a$10$0QTW5I1JwlNKTT/fcBIYT.XeD9YMdgpkoBqia1Vcor5Ugb2CLhdLe', 1,1);
INSERT INTO funcionario (nome,ativo,cpf,documentosocial_id,email,senha,endereco_id,telefone_id) VALUES ('Captador 1', true, '222.222.222-22', 2, 'captador1@email.com', '$2a$10$lBmi0ZG/OkDueOQrOZNgzuEW4m.VG.l/sGfWFpQI/TJWedMsxmqy.', 2,2);
INSERT INTO funcionario (nome,ativo,cpf,documentosocial_id,email,senha) VALUES ('Analista 1', true, '333.333.333-33', 3, 'analista@email.com', '$2a$10$BjBCqVmiKLZ/zlu6nKF3MO.CpXKJeYo1rAbo.3APTX7n/HPpKqCly');
INSERT INTO funcionario (nome,ativo,cpf,documentosocial_id,email,senha) VALUES ('Sincap backdoor', true, '555.555.555-55', 4, 'sincap@email.com', '$2a$10$GlKSwYNkbp02ZnhWUAoAyuo2f.80lJLbn41hEvbh4Ttf1X9/WOkNC');
INSERT INTO notificador VALUES (1);
INSERT INTO captador VALUES (2,1);
INSERT INTO analistacncdo VALUES (3);
INSERT INTO notificador VALUES (4);

-- ## SETORES## --
INSERT INTO setor(nome) VALUES ('CLINICA MEDICA');
INSERT INTO setor(nome) VALUES ('CIRURGIA GERAL');
INSERT INTO setor(nome) VALUES ('CTI');
INSERT INTO setor(nome) VALUES ('PEDIATRIA');
INSERT INTO setor(nome) VALUES ('NEFROLOGIA');
INSERT INTO setor(nome) VALUES ('GINECOLOGIA');
INSERT INTO setor(nome) VALUES ('OBSTETRICIA');

-- ## HOSPITAL-SETORES ## --
INSERT INTO hospitalsetor(instituicaoid, setorid) VALUES (1, 1);
INSERT INTO hospitalsetor(instituicaoid, setorid) VALUES (1, 2);
INSERT INTO hospitalsetor(instituicaoid, setorid) VALUES (1, 3);
INSERT INTO hospitalsetor(instituicaoid, setorid) VALUES (1, 4);
INSERT INTO hospitalsetor(instituicaoid, setorid) VALUES (1, 5);
INSERT INTO hospitalsetor(instituicaoid, setorid) VALUES (1, 6);
INSERT INTO hospitalsetor(instituicaoid, setorid) VALUES (1, 7);

INSERT INTO hospitalsetor(instituicaoid, setorid) VALUES (2, 1);
INSERT INTO hospitalsetor(instituicaoid, setorid) VALUES (2, 2);
INSERT INTO hospitalsetor(instituicaoid, setorid) VALUES (2, 3);
INSERT INTO hospitalsetor(instituicaoid, setorid) VALUES (2, 4);
INSERT INTO hospitalsetor(instituicaoid, setorid) VALUES (2, 5);
INSERT INTO hospitalsetor(instituicaoid, setorid) VALUES (2, 6);
INSERT INTO hospitalsetor(instituicaoid, setorid) VALUES (2, 7);

-- ## NOTIFICADOR_INSTITUICAO_NOTIFICADORA ## --
INSERT INTO notificador_instituicaonotificadora VALUES (1, 1);
INSERT INTO notificador_instituicaonotificadora VALUES (4, 1);


INSERT INTO causanaodoacao (nome, tiponaodoacao) VALUES ('Desconhecimento do desejo do potencial doador', 'RECUSA_FAMILIAR');
INSERT INTO causanaodoacao (nome, tiponaodoacao) VALUES ('Doador contrário à doação em vida', 'RECUSA_FAMILIAR');
INSERT INTO causanaodoacao (nome, tiponaodoacao) VALUES ('Familiares indecisos', 'RECUSA_FAMILIAR');
INSERT INTO causanaodoacao (nome, tiponaodoacao) VALUES ('Familiares desejam o corpo íntegro', 'RECUSA_FAMILIAR');
INSERT INTO causanaodoacao (nome, tiponaodoacao) VALUES ('Familiares descontentes com o atendimento', 'RECUSA_FAMILIAR');
INSERT INTO causanaodoacao (nome, tiponaodoacao) VALUES ('Receio de demora na liberação do corpo', 'RECUSA_FAMILIAR');
INSERT INTO causanaodoacao (nome, tiponaodoacao) VALUES ('Convicções religiosas', 'RECUSA_FAMILIAR');
INSERT INTO causanaodoacao (nome, tiponaodoacao) VALUES ('Outros', 'RECUSA_FAMILIAR');
INSERT INTO causanaodoacao (nome, tiponaodoacao) VALUES ('Sorologia positiva HIV', 'CONTRAINDICACAO_MEDICA');
INSERT INTO causanaodoacao (nome, tiponaodoacao) VALUES ('Sorologia positiva HTLV', 'CONTRAINDICACAO_MEDICA');
INSERT INTO causanaodoacao (nome, tiponaodoacao) VALUES ('Sorologia positiva Hepatite B', 'CONTRAINDICACAO_MEDICA');
INSERT INTO causanaodoacao (nome, tiponaodoacao) VALUES ('Sorologia positiva Hepatite C', 'CONTRAINDICACAO_MEDICA');
INSERT INTO causanaodoacao (nome, tiponaodoacao) VALUES ('Acima do tempo máximo para retirada', 'CONTRAINDICACAO_MEDICA');
INSERT INTO causanaodoacao (nome, tiponaodoacao) VALUES ('Portador de infecção grave', 'CONTRAINDICACAO_MEDICA');
INSERT INTO causanaodoacao (nome, tiponaodoacao) VALUES ('Portador de neoplasia', 'CONTRAINDICACAO_MEDICA');
INSERT INTO causanaodoacao (nome, tiponaodoacao) VALUES ('Sem diagnóstico conhecido', 'CONTRAINDICACAO_MEDICA');
INSERT INTO causanaodoacao (nome, tiponaodoacao) VALUES ('Fora da faixa etária', 'CONTRAINDICACAO_MEDICA');
INSERT INTO causanaodoacao (nome, tiponaodoacao) VALUES ('Outras', 'CONTRAINDICACAO_MEDICA');
INSERT INTO causanaodoacao (nome, tiponaodoacao) VALUES ('Equipe de retirada não disponível', 'PROBLEMAS_ESTRUTURAIS');
INSERT INTO causanaodoacao (nome, tiponaodoacao) VALUES ('Família não localizada', 'PROBLEMAS_ESTRUTURAIS');
INSERT INTO causanaodoacao (nome, tiponaodoacao) VALUES ('Deficiência estrutural da instituição', 'PROBLEMAS_ESTRUTURAIS');
INSERT INTO causanaodoacao (nome, tiponaodoacao) VALUES ('Sem identificação', 'PROBLEMAS_ESTRUTURAIS');
INSERT INTO causanaodoacao (nome, tiponaodoacao) VALUES ('Outros', 'PROBLEMAS_ESTRUTURAIS');


INSERT INTO permissao (id, role) VALUES (1, 'ROLE_NOTIFICADOR');
INSERT INTO permissao (id, role) VALUES (2, 'ROLE_CAPTADOR');
INSERT INTO permissao (id, role) VALUES (3, 'ROLE_ANALISTA');
INSERT INTO permissao (id, role) VALUES (4, 'ROLE_ADMIN');

INSERT INTO funcionario_permissao (funcionario_id, permissoes_id) VALUES (1, 1);
INSERT INTO funcionario_permissao (funcionario_id, permissoes_id) VALUES (2, 2);
INSERT INTO funcionario_permissao (funcionario_id, permissoes_id) VALUES (3, 3);
INSERT INTO funcionario_permissao (funcionario_id, permissoes_id) VALUES (3, 4);
INSERT INTO funcionario_permissao (funcionario_id, permissoes_id) VALUES (4, 3);
INSERT INTO funcionario_permissao (funcionario_id, permissoes_id) VALUES (4, 4);
