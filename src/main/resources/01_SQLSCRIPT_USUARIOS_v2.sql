-- ## TIPOS DE USUÁRIOS: ADMINISTRADOR, ALUNO, SECRETARIA, PEDAGOGO; ## --

-- # FUNÇÕES # --

CREATE OR REPLACE FUNCTION getIdUsuario(username text) RETURNS bigint AS 
	'SELECT id FROM usuario WHERE username = $1'
	LANGUAGE 'sql';

CREATE OR REPLACE FUNCTION getIdNotificador(nome text) RETURNS bigint AS 
	'SELECT id FROM notificador WHERE nome = $1'
	LANGUAGE 'sql';
	
CREATE OR REPLACE FUNCTION getIdHospital(nome text) RETURNS bigint AS 
	'SELECT instituicaoid FROM hospital WHERE nome = $1'
	LANGUAGE 'sql';

CREATE OR REPLACE FUNCTION getIdSetor(nome text) RETURNS bigint AS 
	'SELECT setorid FROM setor WHERE nome = $1'
	LANGUAGE 'sql';

CREATE OR REPLACE FUNCTION getIdEnderecoHospital(cep bigint, logradouro text, numero int) RETURNS bigint AS 
	'SELECT id FROM endereco WHERE cep = $1 and logradouro = $2 and numero = $3'
	LANGUAGE 'sql';
	
CREATE OR REPLACE FUNCTION getIdBairro(nbairro text, ncidade text, siglaestado text) RETURNS bigint AS 
	'SELECT b.id FROM bairro b JOIN municipio m ON (m.id = b.municipio_id) JOIN estado e ON (e.id = m.estado_id) WHERE b.nome = $1 and m.nome = $2 and e.sigla = $3'
	LANGUAGE 'sql';

	
-- ## USUARIOS ## --
INSERT INTO usuario(id, active, username, password, email) VALUES (nextval('usuario_id_seq'), true, '111.111.111-11', 'abc123', 'email@email.com');
INSERT INTO usuario(id, active, username, password, email) VALUES (nextval('usuario_id_seq'), true, '222.222.222-22', 'abc123', 'email@email.com');
INSERT INTO usuario(id, active, username, password, email) VALUES (nextval('usuario_id_seq'), true, '333.333.333-33', 'abc123', 'email@email.com');
INSERT INTO usuario(id, active, username, password, email) VALUES (nextval('usuario_id_seq'), true, '444.444.444-44', 'abc123', 'email@email.com');

-- ## ENDERECOS DOS HOSPITAIS## --
--INSERT INTO endereco(id, cep, complemento, logradouro, numero, bairro) VALUES (nextval('endereco_id_seq'), 'cep', 'complemento', 'logradouro', 0, getIdBairro('Bairro', 'cidade', 'siglaestado'));
INSERT INTO endereco(id, cep, complemento, logradouro, numero, bairro_id) VALUES (nextval('endereco_id_seq'), 29042715, null, 'AVENIDA MARECHAL CAMPOS', 1355, getIdBairro('Santos Dumont', 'Vitória', 'ES'));
INSERT INTO endereco(id, cep, complemento, logradouro, numero, bairro_id) VALUES (nextval('endereco_id_seq'), 29166828, null, 'AVENIDA PAULO PEREIRA GOMES', 1089, getIdBairro('Morada de Laranjeira', 'Serra', 'ES'));
INSERT INTO endereco(id, cep, complemento, logradouro, numero, bairro_id) VALUES (nextval('endereco_id_seq'), 29118060, null, 'RUA VENUS', 0, getIdBairro('Alecrim', 'Vila Velha', 'ES'));
INSERT INTO endereco(id, cep, complemento, logradouro, numero, bairro_id) VALUES (nextval('endereco_id_seq'), 29165680, null, 'RUA EUDES SCHERRER DE SOUZA', 0, getIdBairro('Parque Residencial L', 'Serra', 'ES'));

-- ## HOSPITAIS## --
INSERT INTO hospital(instituicaoid, nome, sigla, cnes, endereco_id) VALUES (nextval('hospital_instituicaoid_seq'), 'HOSPITAL DAS CLINICAS', 'HUCAM', '4044916', getIdEnderecoHospital(29042715, 'AVENIDA MARECHAL CAMPOS', 1355));
INSERT INTO hospital(instituicaoid, nome, sigla, cnes, endereco_id) VALUES (nextval('hospital_instituicaoid_seq'), 'HOSPITAL ESTADUAL DR JAYME SANTOS NEVES', 'HEJSN', '7257406',getIdEnderecoHospital(29166828, 'AVENIDA PAULO PEREIRA GOMES', 1089));
INSERT INTO hospital(instituicaoid, nome, sigla, cnes, endereco_id) VALUES (nextval('hospital_instituicaoid_seq'), 'HOSPITAL EVANGELICO DE VILA VELHA', 'HEVV', '2494442',getIdEnderecoHospital(29118060, 'RUA VENUS', 0));
INSERT INTO hospital(instituicaoid, nome, sigla, cnes, endereco_id) VALUES (nextval('hospital_instituicaoid_seq'), 'HOSPITAL DORIO SILVA', 'HDS', '2486199',getIdEnderecoHospital(29165680, 'RUA EUDES SCHERRER DE SOUZA', 0));

-- ## SETORES## --
INSERT INTO setor(setorid, nome) VALUES (nextval('setor_setorid_seq'), 'CLINICA MEDICA');
INSERT INTO setor(setorid, nome) VALUES (nextval('setor_setorid_seq'), 'CIRURGIA GERAL');
INSERT INTO setor(setorid, nome) VALUES (nextval('setor_setorid_seq'), 'CTI');
INSERT INTO setor(setorid, nome) VALUES (nextval('setor_setorid_seq'), 'PEDIATRIA');
INSERT INTO setor(setorid, nome) VALUES (nextval('setor_setorid_seq'), 'NEFROLOGIA');
INSERT INTO setor(setorid, nome) VALUES (nextval('setor_setorid_seq'), 'GINECOLOGIA');
INSERT INTO setor(setorid, nome) VALUES (nextval('setor_setorid_seq'), 'OBSTETRICIA');
INSERT INTO setor(setorid, nome) VALUES (nextval('setor_setorid_seq'), 'CTQ');
INSERT INTO setor(setorid, nome) VALUES (nextval('setor_setorid_seq'), 'CTI ADULTO');
INSERT INTO setor(setorid, nome) VALUES (nextval('setor_setorid_seq'), 'PRONTO SOCORRO');
INSERT INTO setor(setorid, nome) VALUES (nextval('setor_setorid_seq'), 'CENTRO CIRURGICO');
INSERT INTO setor(setorid, nome) VALUES (nextval('setor_setorid_seq'), 'UTIN');
INSERT INTO setor(setorid, nome) VALUES (nextval('setor_setorid_seq'), 'UADC');

-- ## NOTIFICADOR ## --
INSERT INTO notificador(usuario_id, nome, cpf, id) VALUES (getIdUsuario('111.111.111-11'), 'Notificador', '111.111.111-11',nextval('notificador_id_seq'));
INSERT INTO notificador(usuario_id, nome, cpf, id) VALUES (getIdUsuario('222.222.222-22'), 'Notificador1', '222.222.222-22',nextval('notificador_id_seq'));
INSERT INTO notificador(usuario_id, nome, cpf, id) VALUES (getIdUsuario('333.333.333-33'), 'Notificador2', '333.333.333-33',nextval('notificador_id_seq'));
INSERT INTO notificador(usuario_id, nome, cpf, id) VALUES (getIdUsuario('444.444.444-44'), 'Notificador3', '444.444.444-44',nextval('notificador_id_seq'));

-- ## NOTIFICADOR_HOPITAL ## --
INSERT INTO notificador_hospital(notificador_id, hospitais_instituicaoid) VALUES (getIdNotificador('Notificador'), getIdHospital('HOSPITAL DAS CLINICAS'));
INSERT INTO notificador_hospital(notificador_id, hospitais_instituicaoid) VALUES (getIdNotificador('Notificador'), getIdHospital('HOSPITAL ESTADUAL DR JAYME SANTOS NEVES'));
INSERT INTO notificador_hospital(notificador_id, hospitais_instituicaoid) VALUES (getIdNotificador('Notificador1'), getIdHospital('HOSPITAL DAS CLINICAS'));
INSERT INTO notificador_hospital(notificador_id, hospitais_instituicaoid) VALUES (getIdNotificador('Notificador2'), getIdHospital('HOSPITAL ESTADUAL DR JAYME SANTOS NEVES'));
INSERT INTO notificador_hospital(notificador_id, hospitais_instituicaoid) VALUES (getIdNotificador('Notificador3'), getIdHospital('HOSPITAL DAS CLINICAS'));
