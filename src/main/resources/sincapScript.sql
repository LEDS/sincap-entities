CREATE TABLE analistacncdo
(
  id SERIAL PRIMARY KEY NOT NULL
);
CREATE TABLE atualizacaoestado
(
  id SERIAL PRIMARY KEY NOT NULL,
  dataatualizacaos TIMESTAMP,
  estadonotificacao VARCHAR(255),
  funcionario_id BIGINT
);
CREATE TABLE bairro
(
  id SERIAL PRIMARY KEY NOT NULL,
  nome VARCHAR(255)
);
CREATE TABLE bancoolhos
(
  instituicaoid SERIAL PRIMARY KEY NOT NULL,
  cnes VARCHAR(255),
  email VARCHAR(255),
  fantasia VARCHAR(255),
  nome VARCHAR(255),
  endereco_id BIGINT,
  telefone_id BIGINT
);
CREATE TABLE bancoolhos_funcionario
(
  bancoolhos_instituicaoid BIGINT NOT NULL,
  funcionarios_id BIGINT NOT NULL,
  PRIMARY KEY (bancoolhos_instituicaoid, funcionarios_id)
);
CREATE TABLE captacao
(
  id SERIAL PRIMARY KEY NOT NULL,
  captacaorealizada BOOL,
  comentario VARCHAR(255),
  datacadastro TIMESTAMP,
  datacaptacao TIMESTAMP,
  captador_id BIGINT
);
CREATE TABLE captador
(
  id SERIAL PRIMARY KEY NOT NULL,
  bancoolhos_instituicaoid BIGINT
);
CREATE TABLE causamortis
(
  id SERIAL PRIMARY KEY NOT NULL,
  nome VARCHAR(255)
);
CREATE TABLE causanaodoacao
(
  id SERIAL PRIMARY KEY NOT NULL,
  nome VARCHAR(255),
  tiponaodoacao VARCHAR(255)
);
CREATE TABLE cidade
(
  id SERIAL PRIMARY KEY NOT NULL,
  nome VARCHAR(255)
);
CREATE TABLE cidade_bairro
(
  cidade_id BIGINT NOT NULL,
  bairros_id BIGINT NOT NULL,
  PRIMARY KEY (cidade_id, bairros_id)
);
CREATE TABLE endereco
(
  id SERIAL PRIMARY KEY NOT NULL,
  cep VARCHAR(255),
  complemento VARCHAR(255),
  logradouro VARCHAR(255),
  numero VARCHAR(255),
  bairro_id BIGINT,
  cidade_id BIGINT,
  estado_id BIGINT
);
CREATE TABLE entrevista
(
  id SERIAL PRIMARY KEY NOT NULL,
  datacadastro TIMESTAMP,
  dataentrevista TIMESTAMP,
  doacaoautorizada BOOL,
  entrevistarealizada BOOL,
  funcionario_id BIGINT,
  responsavel_id BIGINT,
  testemunha1_id BIGINT,
  testemunha2_id BIGINT
);
CREATE TABLE estado
(
  id SERIAL PRIMARY KEY NOT NULL,
  nome VARCHAR(255),
  sigla VARCHAR(255)
);
CREATE TABLE estado_cidade
(
  estado_id BIGINT NOT NULL,
  cidades_id BIGINT NOT NULL,
  PRIMARY KEY (estado_id, cidades_id)
);
CREATE TABLE funcionario
(
  id SERIAL PRIMARY KEY NOT NULL,
  nome VARCHAR(255),
  ativo BOOL,
  cpf VARCHAR(255),
  documentosocial VARCHAR(255),
  email VARCHAR(255),
  senha VARCHAR(255),
  endereco_id BIGINT,
  telefone_id BIGINT
);
CREATE TABLE funcionario_permissao
(
  funcionario_id BIGINT NOT NULL,
  permissoes_id BIGINT NOT NULL
);
CREATE TABLE hospitalsetor
(
  instituicaoid BIGINT NOT NULL,
  setorid BIGINT NOT NULL,
  PRIMARY KEY (instituicaoid, setorid)
);
CREATE TABLE instituicaonotificadora
(
  dtype VARCHAR(31) NOT NULL,
  instituicaoid SERIAL PRIMARY KEY NOT NULL,
  cnes VARCHAR(255),
  email VARCHAR(255),
  fantasia VARCHAR(255),
  nome VARCHAR(255),
  sigla VARCHAR(255),
  endereco_id BIGINT,
  telefone_id BIGINT
);
CREATE TABLE instituicaonotificadora_funcionario
(
  instituicaonotificadora_instituicaoid BIGINT NOT NULL,
  funcionarios_id BIGINT NOT NULL,
  PRIMARY KEY (instituicaonotificadora_instituicaoid, funcionarios_id)
);
CREATE TABLE notificador
(
  id SERIAL PRIMARY KEY NOT NULL
);
CREATE TABLE notificador_instituicaonotificadora
(
  notificador_id BIGINT NOT NULL,
  instituicoesnotificadoras_instituicaoid BIGINT NOT NULL,
  PRIMARY KEY (notificador_id, instituicoesnotificadoras_instituicaoid)
);
CREATE TABLE obito
(
  id SERIAL PRIMARY KEY NOT NULL,
  aptodoacao BOOL,
  corpoencaminhamento VARCHAR(255),
  datacadastro TIMESTAMP,
  dataobito TIMESTAMP,
  tipoobito INT,
  hospital_instituicaoid BIGINT,
  paciente_id BIGINT NOT NULL,
  primeiracausamortis_id BIGINT,
  quartacausamortis_id BIGINT,
  segundacausamortis_id BIGINT,
  setor_setorid BIGINT,
  terceiracausamortis_id BIGINT
);
CREATE TABLE paciente
(
  id SERIAL PRIMARY KEY NOT NULL,
  nome VARCHAR(255),
  estadocivil VARCHAR(255),
  datainternacao TIMESTAMP,
  datanascimento TIMESTAMP,
  documentosocial VARCHAR(255),
  nacionalidade VARCHAR(255),
  nomemae VARCHAR(255),
  numeroprontuario VARCHAR(255),
  numerosus VARCHAR(255),
  profissao VARCHAR(255),
  sexo VARCHAR(255),
  endereco_id BIGINT,
  telefone_id BIGINT
);
CREATE TABLE pais
(
  id SERIAL PRIMARY KEY NOT NULL,
  nome VARCHAR(255)
);
CREATE TABLE pais_estado
(
  pais_id BIGINT NOT NULL,
  estados_id BIGINT NOT NULL,
  PRIMARY KEY (pais_id, estados_id)
);
CREATE TABLE permissao
(
  id SERIAL PRIMARY KEY NOT NULL,
  role VARCHAR(255) NOT NULL
);
CREATE TABLE processonotificacao
(
  id SERIAL PRIMARY KEY NOT NULL,
  arquivado BOOL,
  codigo VARCHAR(255) NOT NULL,
  dataabertura TIMESTAMP,
  dataarquivamento TIMESTAMP,
  captacao_id BIGINT,
  causanaodoacao_id BIGINT,
  entrevista_id BIGINT,
  notificador_id BIGINT NOT NULL,
  obito_id BIGINT NOT NULL,
  ultimoestado_id BIGINT
);
CREATE TABLE processonotificacao_atualizacaoestado
(
  processonotificacao_id BIGINT NOT NULL,
  historico_id BIGINT NOT NULL
);
CREATE TABLE responsavel
(
  id SERIAL PRIMARY KEY NOT NULL,
  nome VARCHAR(255),
  documentosocial VARCHAR(255),
  estadocivil VARCHAR(255),
  nacionalidade VARCHAR(255),
  parentesco VARCHAR(255),
  profissao VARCHAR(255),
  sexo VARCHAR(255),
  endereco_id BIGINT,
  telefone_id BIGINT,
  telefone2_id BIGINT
);
CREATE TABLE setor
(
  setorid SERIAL PRIMARY KEY NOT NULL,
  nome VARCHAR(255)
);
CREATE TABLE telefone
(
  id SERIAL PRIMARY KEY NOT NULL,
  numero VARCHAR(255)
);
CREATE TABLE testemunha
(
  id SERIAL PRIMARY KEY NOT NULL,
  nome VARCHAR(255),
  documentosocial VARCHAR(255),
  endereco_id BIGINT,
  telefone_id BIGINT
);
ALTER TABLE analistacncdo ADD FOREIGN KEY (id) REFERENCES funcionario (id);
ALTER TABLE atualizacaoestado ADD FOREIGN KEY (funcionario_id) REFERENCES funcionario (id);
ALTER TABLE bancoolhos ADD FOREIGN KEY (endereco_id) REFERENCES endereco (id);
ALTER TABLE bancoolhos ADD FOREIGN KEY (telefone_id) REFERENCES telefone (id);
CREATE UNIQUE INDEX bancoolhos_nome_key ON bancoolhos (nome);
ALTER TABLE bancoolhos_funcionario ADD FOREIGN KEY (bancoolhos_instituicaoid) REFERENCES bancoolhos (instituicaoid);
ALTER TABLE bancoolhos_funcionario ADD FOREIGN KEY (funcionarios_id) REFERENCES funcionario (id);
CREATE UNIQUE INDEX bancoolhos_funcionario_funcionarios_id_key ON bancoolhos_funcionario (funcionarios_id);
ALTER TABLE captacao ADD FOREIGN KEY (captador_id) REFERENCES captador (id);
ALTER TABLE captador ADD FOREIGN KEY (bancoolhos_instituicaoid) REFERENCES bancoolhos (instituicaoid);
ALTER TABLE captador ADD FOREIGN KEY (id) REFERENCES funcionario (id);
ALTER TABLE cidade_bairro ADD FOREIGN KEY (bairros_id) REFERENCES bairro (id);
ALTER TABLE cidade_bairro ADD FOREIGN KEY (cidade_id) REFERENCES cidade (id);
CREATE UNIQUE INDEX cidade_bairro_bairros_id_key ON cidade_bairro (bairros_id);
ALTER TABLE endereco ADD FOREIGN KEY (bairro_id) REFERENCES bairro (id);
ALTER TABLE endereco ADD FOREIGN KEY (cidade_id) REFERENCES cidade (id);
ALTER TABLE endereco ADD FOREIGN KEY (estado_id) REFERENCES estado (id);
ALTER TABLE entrevista ADD FOREIGN KEY (funcionario_id) REFERENCES funcionario (id);
ALTER TABLE entrevista ADD FOREIGN KEY (responsavel_id) REFERENCES responsavel (id);
ALTER TABLE entrevista ADD FOREIGN KEY (testemunha1_id) REFERENCES testemunha (id);
ALTER TABLE entrevista ADD FOREIGN KEY (testemunha2_id) REFERENCES testemunha (id);
ALTER TABLE estado_cidade ADD FOREIGN KEY (cidades_id) REFERENCES cidade (id);
ALTER TABLE estado_cidade ADD FOREIGN KEY (estado_id) REFERENCES estado (id);
CREATE UNIQUE INDEX estado_cidade_cidades_id_key ON estado_cidade (cidades_id);
ALTER TABLE funcionario ADD FOREIGN KEY (endereco_id) REFERENCES endereco (id);
ALTER TABLE funcionario ADD FOREIGN KEY (telefone_id) REFERENCES telefone (id);
ALTER TABLE funcionario_permissao ADD FOREIGN KEY (funcionario_id) REFERENCES funcionario (id);
ALTER TABLE funcionario_permissao ADD FOREIGN KEY (permissoes_id) REFERENCES permissao (id);
ALTER TABLE hospitalsetor ADD FOREIGN KEY (instituicaoid) REFERENCES instituicaonotificadora (instituicaoid);
ALTER TABLE hospitalsetor ADD FOREIGN KEY (setorid) REFERENCES setor (setorid);
ALTER TABLE instituicaonotificadora ADD FOREIGN KEY (endereco_id) REFERENCES endereco (id);
ALTER TABLE instituicaonotificadora ADD FOREIGN KEY (telefone_id) REFERENCES telefone (id);
CREATE UNIQUE INDEX instituicaonotificadora_nome_key ON instituicaonotificadora (nome);
ALTER TABLE instituicaonotificadora_funcionario ADD FOREIGN KEY (funcionarios_id) REFERENCES funcionario (id);
ALTER TABLE instituicaonotificadora_funcionario ADD FOREIGN KEY (instituicaonotificadora_instituicaoid) REFERENCES instituicaonotificadora (instituicaoid);
CREATE UNIQUE INDEX instituicaonotificadora_funcionario_funcionarios_id_key ON instituicaonotificadora_funcionario (funcionarios_id);
ALTER TABLE notificador ADD FOREIGN KEY (id) REFERENCES funcionario (id);
ALTER TABLE notificador_instituicaonotificadora ADD FOREIGN KEY (instituicoesnotificadoras_instituicaoid) REFERENCES instituicaonotificadora (instituicaoid);
ALTER TABLE notificador_instituicaonotificadora ADD FOREIGN KEY (notificador_id) REFERENCES notificador (id);
ALTER TABLE obito ADD FOREIGN KEY (segundacausamortis_id) REFERENCES causamortis (id);
ALTER TABLE obito ADD FOREIGN KEY (primeiracausamortis_id) REFERENCES causamortis (id);
ALTER TABLE obito ADD FOREIGN KEY (terceiracausamortis_id) REFERENCES causamortis (id);
ALTER TABLE obito ADD FOREIGN KEY (quartacausamortis_id) REFERENCES causamortis (id);
ALTER TABLE obito ADD FOREIGN KEY (hospital_instituicaoid) REFERENCES instituicaonotificadora (instituicaoid);
ALTER TABLE obito ADD FOREIGN KEY (paciente_id) REFERENCES paciente (id);
ALTER TABLE obito ADD FOREIGN KEY (setor_setorid) REFERENCES setor (setorid);
ALTER TABLE paciente ADD FOREIGN KEY (endereco_id) REFERENCES endereco (id);
ALTER TABLE paciente ADD FOREIGN KEY (telefone_id) REFERENCES telefone (id);
ALTER TABLE pais_estado ADD FOREIGN KEY (estados_id) REFERENCES estado (id);
ALTER TABLE pais_estado ADD FOREIGN KEY (pais_id) REFERENCES pais (id);
CREATE UNIQUE INDEX pais_estado_estados_id_key ON pais_estado (estados_id);
CREATE UNIQUE INDEX permissao_role_key ON permissao (role);
ALTER TABLE processonotificacao ADD FOREIGN KEY (ultimoestado_id) REFERENCES atualizacaoestado (id);
ALTER TABLE processonotificacao ADD FOREIGN KEY (captacao_id) REFERENCES captacao (id);
ALTER TABLE processonotificacao ADD FOREIGN KEY (causanaodoacao_id) REFERENCES causanaodoacao (id);
ALTER TABLE processonotificacao ADD FOREIGN KEY (entrevista_id) REFERENCES entrevista (id);
ALTER TABLE processonotificacao ADD FOREIGN KEY (notificador_id) REFERENCES notificador (id);
ALTER TABLE processonotificacao ADD FOREIGN KEY (obito_id) REFERENCES obito (id);
CREATE UNIQUE INDEX processonotificacao_codigo_key ON processonotificacao (codigo);
ALTER TABLE processonotificacao_atualizacaoestado ADD FOREIGN KEY (historico_id) REFERENCES atualizacaoestado (id);
ALTER TABLE processonotificacao_atualizacaoestado ADD FOREIGN KEY (processonotificacao_id) REFERENCES processonotificacao (id);
CREATE UNIQUE INDEX processonotificacao_atualizacaoestado_historico_id_key ON processonotificacao_atualizacaoestado (historico_id);
ALTER TABLE responsavel ADD FOREIGN KEY (endereco_id) REFERENCES endereco (id);
ALTER TABLE responsavel ADD FOREIGN KEY (telefone2_id) REFERENCES telefone (id);
ALTER TABLE responsavel ADD FOREIGN KEY (telefone_id) REFERENCES telefone (id);
ALTER TABLE testemunha ADD FOREIGN KEY (endereco_id) REFERENCES endereco (id);
ALTER TABLE testemunha ADD FOREIGN KEY (telefone_id) REFERENCES telefone (id);
