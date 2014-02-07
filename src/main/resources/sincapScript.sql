--
-- PostgreSQL database dump
--

-- Dumped from database version 9.1.11
-- Dumped by pg_dump version 9.2.4
-- Started on 2014-01-09 18:06:05

SET statement_timeout = 0;
SET client_encoding = 'SQL_ASCII';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 217 (class 3079 OID 11677)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2247 (class 0 OID 0)
-- Dependencies: 217
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 163 (class 1259 OID 180800)
-- Name: bairro; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE bairro (
    id bigint NOT NULL,
    nome character varying(255)
);


ALTER TABLE public.bairro OWNER TO postgres;

--
-- TOC entry 162 (class 1259 OID 180798)
-- Name: bairro_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE bairro_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bairro_id_seq OWNER TO postgres;

--
-- TOC entry 2248 (class 0 OID 0)
-- Dependencies: 162
-- Name: bairro_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE bairro_id_seq OWNED BY bairro.id;


--
-- TOC entry 200 (class 1259 OID 181036)
-- Name: captacao; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE captacao (
    id bigint NOT NULL,
    realizada boolean
);


ALTER TABLE public.captacao OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 181034)
-- Name: captacao_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE captacao_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.captacao_id_seq OWNER TO postgres;

--
-- TOC entry 2249 (class 0 OID 0)
-- Dependencies: 199
-- Name: captacao_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE captacao_id_seq OWNED BY captacao.id;


--
-- TOC entry 202 (class 1259 OID 181044)
-- Name: causamortis; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE causamortis (
    id bigint NOT NULL,
    descricao character varying(255),
    causa_id bigint
);


ALTER TABLE public.causamortis OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 181042)
-- Name: causamortis_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE causamortis_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.causamortis_id_seq OWNER TO postgres;

--
-- TOC entry 2250 (class 0 OID 0)
-- Dependencies: 201
-- Name: causamortis_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE causamortis_id_seq OWNED BY causamortis.id;


--
-- TOC entry 165 (class 1259 OID 180808)
-- Name: cidade; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE cidade (
    id bigint NOT NULL,
    nome character varying(255)
);


ALTER TABLE public.cidade OWNER TO postgres;

--
-- TOC entry 166 (class 1259 OID 180814)
-- Name: cidade_bairro; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE cidade_bairro (
    cidade_id bigint NOT NULL,
    bairros_id bigint NOT NULL
);


ALTER TABLE public.cidade_bairro OWNER TO postgres;

--
-- TOC entry 164 (class 1259 OID 180806)
-- Name: cidade_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cidade_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cidade_id_seq OWNER TO postgres;

--
-- TOC entry 2251 (class 0 OID 0)
-- Dependencies: 164
-- Name: cidade_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cidade_id_seq OWNED BY cidade.id;


--
-- TOC entry 168 (class 1259 OID 180821)
-- Name: contraindicacaomedica; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE contraindicacaomedica (
    id bigint NOT NULL,
    nome character varying(255)
);


ALTER TABLE public.contraindicacaomedica OWNER TO postgres;

--
-- TOC entry 167 (class 1259 OID 180819)
-- Name: contraindicacaomedica_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE contraindicacaomedica_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contraindicacaomedica_id_seq OWNER TO postgres;

--
-- TOC entry 2252 (class 0 OID 0)
-- Dependencies: 167
-- Name: contraindicacaomedica_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE contraindicacaomedica_id_seq OWNED BY contraindicacaomedica.id;


--
-- TOC entry 204 (class 1259 OID 181052)
-- Name: doacao; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE doacao (
    id bigint NOT NULL,
    captacao bytea,
    dataentrevista date
);


ALTER TABLE public.doacao OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 181050)
-- Name: doacao_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE doacao_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.doacao_id_seq OWNER TO postgres;

--
-- TOC entry 2253 (class 0 OID 0)
-- Dependencies: 203
-- Name: doacao_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE doacao_id_seq OWNED BY doacao.id;


--
-- TOC entry 205 (class 1259 OID 181061)
-- Name: doacao_responsavel; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE doacao_responsavel (
    doacao_id bigint NOT NULL,
    responsaveis_id bigint NOT NULL
);


ALTER TABLE public.doacao_responsavel OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 181068)
-- Name: doacao_testemunha; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE doacao_testemunha (
    doacao_id bigint NOT NULL,
    testemunhas_id bigint NOT NULL
);


ALTER TABLE public.doacao_testemunha OWNER TO postgres;

--
-- TOC entry 170 (class 1259 OID 180829)
-- Name: endereco; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE endereco (
    id bigint NOT NULL,
    cep character varying(255),
    complemento character varying(255),
    logradouro character varying(255),
    numero character varying(255),
    bairro_id bigint,
    cidade_id bigint,
    estado_id bigint
);


ALTER TABLE public.endereco OWNER TO postgres;

--
-- TOC entry 169 (class 1259 OID 180827)
-- Name: endereco_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE endereco_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.endereco_id_seq OWNER TO postgres;

--
-- TOC entry 2254 (class 0 OID 0)
-- Dependencies: 169
-- Name: endereco_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE endereco_id_seq OWNED BY endereco.id;


--
-- TOC entry 172 (class 1259 OID 180837)
-- Name: estado; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE estado (
    id bigint NOT NULL,
    nome character varying(255),
    sigla character varying(255)
);


ALTER TABLE public.estado OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 180843)
-- Name: estado_cidade; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE estado_cidade (
    estado_id bigint NOT NULL,
    cidades_id bigint NOT NULL
);


ALTER TABLE public.estado_cidade OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 180835)
-- Name: estado_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE estado_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.estado_id_seq OWNER TO postgres;

--
-- TOC entry 2255 (class 0 OID 0)
-- Dependencies: 171
-- Name: estado_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE estado_id_seq OWNED BY estado.id;


--
-- TOC entry 175 (class 1259 OID 180850)
-- Name: hospital; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE hospital (
    instituicaoid bigint NOT NULL,
    cnes character varying(255),
    nome character varying(255),
    sigla character varying(255),
    endereco_id bigint
);


ALTER TABLE public.hospital OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 180848)
-- Name: hospital_instituicaoid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE hospital_instituicaoid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hospital_instituicaoid_seq OWNER TO postgres;

--
-- TOC entry 2256 (class 0 OID 0)
-- Dependencies: 174
-- Name: hospital_instituicaoid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE hospital_instituicaoid_seq OWNED BY hospital.instituicaoid;


--
-- TOC entry 176 (class 1259 OID 180856)
-- Name: hospitalsetor; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE hospitalsetor (
    instituicaoid bigint NOT NULL,
    setorid bigint NOT NULL
);


ALTER TABLE public.hospitalsetor OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 180863)
-- Name: motivoinviabilidade; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE motivoinviabilidade (
    id bigint NOT NULL,
    nome character varying(255),
    tipomotivoinviabilidade_id bigint
);


ALTER TABLE public.motivoinviabilidade OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 180861)
-- Name: motivoinviabilidade_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE motivoinviabilidade_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.motivoinviabilidade_id_seq OWNER TO postgres;

--
-- TOC entry 2257 (class 0 OID 0)
-- Dependencies: 177
-- Name: motivoinviabilidade_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE motivoinviabilidade_id_seq OWNED BY motivoinviabilidade.id;


--
-- TOC entry 208 (class 1259 OID 181077)
-- Name: notificacao; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE notificacao (
    id bigint NOT NULL,
    codigo character varying(255),
    datanotificacao timestamp without time zone,
    notificador_id bigint NOT NULL,
    obito_id bigint,
    setor_setorid bigint
);


ALTER TABLE public.notificacao OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 181075)
-- Name: notificacao_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE notificacao_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.notificacao_id_seq OWNER TO postgres;

--
-- TOC entry 2258 (class 0 OID 0)
-- Dependencies: 207
-- Name: notificacao_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE notificacao_id_seq OWNED BY notificacao.id;


--
-- TOC entry 180 (class 1259 OID 180871)
-- Name: notificador; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE notificador (
    id bigint NOT NULL,
    nome character varying(255),
    cpf character varying(255),
    usuario_id bigint
);


ALTER TABLE public.notificador OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 180877)
-- Name: notificador_hospital; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE notificador_hospital (
    notificador_id bigint NOT NULL,
    hospitais_instituicaoid bigint NOT NULL
);


ALTER TABLE public.notificador_hospital OWNER TO postgres;

--
-- TOC entry 179 (class 1259 OID 180869)
-- Name: notificador_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE notificador_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.notificador_id_seq OWNER TO postgres;

--
-- TOC entry 2259 (class 0 OID 0)
-- Dependencies: 179
-- Name: notificador_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE notificador_id_seq OWNED BY notificador.id;


--
-- TOC entry 161 (class 1259 OID 180464)
-- Name: notificador_telefone; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE notificador_telefone (
    notificador_id bigint NOT NULL,
    telefones_id bigint NOT NULL
);


ALTER TABLE public.notificador_telefone OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 181087)
-- Name: obito; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE obito (
    id bigint NOT NULL,
    dataobito date,
    encaminhamento integer,
    horarioobito timestamp without time zone,
    causaobito_id bigint,
    paciente_id bigint,
    setor_setorid bigint NOT NULL
);


ALTER TABLE public.obito OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 181085)
-- Name: obito_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE obito_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.obito_id_seq OWNER TO postgres;

--
-- TOC entry 2260 (class 0 OID 0)
-- Dependencies: 209
-- Name: obito_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE obito_id_seq OWNED BY obito.id;


--
-- TOC entry 212 (class 1259 OID 181095)
-- Name: paciente; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE paciente (
    id bigint NOT NULL,
    nome character varying(255),
    estadocivil character varying(255),
    datainternacao date,
    datanascimento date,
    nacionalidade character varying(255),
    nomemae character varying(255),
    nomepai character varying(255),
    numeroprontuario character varying(255),
    rg character varying(255),
    tipopaciente character varying(255),
    doacao_id bigint,
    responsavel_id bigint
);


ALTER TABLE public.paciente OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 181093)
-- Name: paciente_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE paciente_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.paciente_id_seq OWNER TO postgres;

--
-- TOC entry 2261 (class 0 OID 0)
-- Dependencies: 211
-- Name: paciente_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE paciente_id_seq OWNED BY paciente.id;


--
-- TOC entry 183 (class 1259 OID 180884)
-- Name: pais; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pais (
    id bigint NOT NULL,
    nome character varying(255)
);


ALTER TABLE public.pais OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 180890)
-- Name: pais_estado; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pais_estado (
    pais_id bigint NOT NULL,
    estados_id bigint NOT NULL
);


ALTER TABLE public.pais_estado OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 180882)
-- Name: pais_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pais_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pais_id_seq OWNER TO postgres;

--
-- TOC entry 2262 (class 0 OID 0)
-- Dependencies: 182
-- Name: pais_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pais_id_seq OWNED BY pais.id;


--
-- TOC entry 186 (class 1259 OID 180897)
-- Name: problemaestrutural; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE problemaestrutural (
    id bigint NOT NULL,
    nome character varying(255)
);


ALTER TABLE public.problemaestrutural OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 180895)
-- Name: problemaestrutural_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE problemaestrutural_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.problemaestrutural_id_seq OWNER TO postgres;

--
-- TOC entry 2263 (class 0 OID 0)
-- Dependencies: 185
-- Name: problemaestrutural_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE problemaestrutural_id_seq OWNED BY problemaestrutural.id;


--
-- TOC entry 188 (class 1259 OID 180905)
-- Name: problemalogistico; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE problemalogistico (
    id bigint NOT NULL,
    nome character varying(255)
);


ALTER TABLE public.problemalogistico OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 180903)
-- Name: problemalogistico_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE problemalogistico_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.problemalogistico_id_seq OWNER TO postgres;

--
-- TOC entry 2264 (class 0 OID 0)
-- Dependencies: 187
-- Name: problemalogistico_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE problemalogistico_id_seq OWNED BY problemalogistico.id;


--
-- TOC entry 190 (class 1259 OID 180913)
-- Name: recusafamiliar; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE recusafamiliar (
    id bigint NOT NULL,
    nome character varying(255)
);


ALTER TABLE public.recusafamiliar OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 180911)
-- Name: recusafamiliar_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE recusafamiliar_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.recusafamiliar_id_seq OWNER TO postgres;

--
-- TOC entry 2265 (class 0 OID 0)
-- Dependencies: 189
-- Name: recusafamiliar_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE recusafamiliar_id_seq OWNED BY recusafamiliar.id;


--
-- TOC entry 214 (class 1259 OID 181106)
-- Name: responsavel; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE responsavel (
    id bigint NOT NULL,
    nome character varying(255),
    estadocivil character varying(255),
    parentesco character varying(255),
    profissao character varying(255),
    rg character varying(255),
    sexo character varying(255)
);


ALTER TABLE public.responsavel OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 181104)
-- Name: responsavel_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE responsavel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.responsavel_id_seq OWNER TO postgres;

--
-- TOC entry 2266 (class 0 OID 0)
-- Dependencies: 213
-- Name: responsavel_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE responsavel_id_seq OWNED BY responsavel.id;


--
-- TOC entry 192 (class 1259 OID 180921)
-- Name: setor; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE setor (
    setorid bigint NOT NULL,
    nome character varying(255)
);


ALTER TABLE public.setor OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 180919)
-- Name: setor_setorid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE setor_setorid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.setor_setorid_seq OWNER TO postgres;

--
-- TOC entry 2267 (class 0 OID 0)
-- Dependencies: 191
-- Name: setor_setorid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE setor_setorid_seq OWNED BY setor.setorid;


--
-- TOC entry 194 (class 1259 OID 180929)
-- Name: telefone; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE telefone (
    id bigint NOT NULL,
    ddd character varying(255),
    numero character varying(255),
    tipo integer
);


ALTER TABLE public.telefone OWNER TO postgres;

--
-- TOC entry 193 (class 1259 OID 180927)
-- Name: telefone_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE telefone_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.telefone_id_seq OWNER TO postgres;

--
-- TOC entry 2268 (class 0 OID 0)
-- Dependencies: 193
-- Name: telefone_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE telefone_id_seq OWNED BY telefone.id;


--
-- TOC entry 216 (class 1259 OID 181114)
-- Name: testemunha; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE testemunha (
    id bigint NOT NULL,
    nome character varying(255),
    cpf character varying(255),
    endereco_id bigint
);


ALTER TABLE public.testemunha OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 181112)
-- Name: testemunha_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE testemunha_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.testemunha_id_seq OWNER TO postgres;

--
-- TOC entry 2269 (class 0 OID 0)
-- Dependencies: 215
-- Name: testemunha_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE testemunha_id_seq OWNED BY testemunha.id;


--
-- TOC entry 196 (class 1259 OID 180937)
-- Name: tipomotivoinviabilidade; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tipomotivoinviabilidade (
    id bigint NOT NULL,
    nome character varying(255)
);


ALTER TABLE public.tipomotivoinviabilidade OWNER TO postgres;

--
-- TOC entry 195 (class 1259 OID 180935)
-- Name: tipomotivoinviabilidade_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tipomotivoinviabilidade_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tipomotivoinviabilidade_id_seq OWNER TO postgres;

--
-- TOC entry 2270 (class 0 OID 0)
-- Dependencies: 195
-- Name: tipomotivoinviabilidade_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tipomotivoinviabilidade_id_seq OWNED BY tipomotivoinviabilidade.id;


--
-- TOC entry 198 (class 1259 OID 180945)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE usuario (
    id bigint NOT NULL,
    active boolean,
    email character varying(255),
    password character varying(255),
    username character varying(255)
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 180943)
-- Name: usuario_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE usuario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_id_seq OWNER TO postgres;

--
-- TOC entry 2271 (class 0 OID 0)
-- Dependencies: 197
-- Name: usuario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE usuario_id_seq OWNED BY usuario.id;


--
-- TOC entry 2056 (class 2604 OID 180803)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY bairro ALTER COLUMN id SET DEFAULT nextval('bairro_id_seq'::regclass);


--
-- TOC entry 2072 (class 2604 OID 181039)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY captacao ALTER COLUMN id SET DEFAULT nextval('captacao_id_seq'::regclass);


--
-- TOC entry 2073 (class 2604 OID 181047)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY causamortis ALTER COLUMN id SET DEFAULT nextval('causamortis_id_seq'::regclass);


--
-- TOC entry 2057 (class 2604 OID 180811)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cidade ALTER COLUMN id SET DEFAULT nextval('cidade_id_seq'::regclass);


--
-- TOC entry 2058 (class 2604 OID 180824)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contraindicacaomedica ALTER COLUMN id SET DEFAULT nextval('contraindicacaomedica_id_seq'::regclass);


--
-- TOC entry 2074 (class 2604 OID 181055)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY doacao ALTER COLUMN id SET DEFAULT nextval('doacao_id_seq'::regclass);


--
-- TOC entry 2059 (class 2604 OID 180832)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY endereco ALTER COLUMN id SET DEFAULT nextval('endereco_id_seq'::regclass);


--
-- TOC entry 2060 (class 2604 OID 180840)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY estado ALTER COLUMN id SET DEFAULT nextval('estado_id_seq'::regclass);


--
-- TOC entry 2061 (class 2604 OID 180853)
-- Name: instituicaoid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY hospital ALTER COLUMN instituicaoid SET DEFAULT nextval('hospital_instituicaoid_seq'::regclass);


--
-- TOC entry 2062 (class 2604 OID 180866)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY motivoinviabilidade ALTER COLUMN id SET DEFAULT nextval('motivoinviabilidade_id_seq'::regclass);


--
-- TOC entry 2075 (class 2604 OID 181080)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificacao ALTER COLUMN id SET DEFAULT nextval('notificacao_id_seq'::regclass);


--
-- TOC entry 2063 (class 2604 OID 180874)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificador ALTER COLUMN id SET DEFAULT nextval('notificador_id_seq'::regclass);


--
-- TOC entry 2076 (class 2604 OID 181090)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY obito ALTER COLUMN id SET DEFAULT nextval('obito_id_seq'::regclass);


--
-- TOC entry 2077 (class 2604 OID 181098)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY paciente ALTER COLUMN id SET DEFAULT nextval('paciente_id_seq'::regclass);


--
-- TOC entry 2064 (class 2604 OID 180887)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pais ALTER COLUMN id SET DEFAULT nextval('pais_id_seq'::regclass);


--
-- TOC entry 2065 (class 2604 OID 180900)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY problemaestrutural ALTER COLUMN id SET DEFAULT nextval('problemaestrutural_id_seq'::regclass);


--
-- TOC entry 2066 (class 2604 OID 180908)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY problemalogistico ALTER COLUMN id SET DEFAULT nextval('problemalogistico_id_seq'::regclass);


--
-- TOC entry 2067 (class 2604 OID 180916)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY recusafamiliar ALTER COLUMN id SET DEFAULT nextval('recusafamiliar_id_seq'::regclass);


--
-- TOC entry 2078 (class 2604 OID 181109)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY responsavel ALTER COLUMN id SET DEFAULT nextval('responsavel_id_seq'::regclass);


--
-- TOC entry 2068 (class 2604 OID 180924)
-- Name: setorid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY setor ALTER COLUMN setorid SET DEFAULT nextval('setor_setorid_seq'::regclass);


--
-- TOC entry 2069 (class 2604 OID 180932)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY telefone ALTER COLUMN id SET DEFAULT nextval('telefone_id_seq'::regclass);


--
-- TOC entry 2079 (class 2604 OID 181117)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY testemunha ALTER COLUMN id SET DEFAULT nextval('testemunha_id_seq'::regclass);


--
-- TOC entry 2070 (class 2604 OID 180940)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipomotivoinviabilidade ALTER COLUMN id SET DEFAULT nextval('tipomotivoinviabilidade_id_seq'::regclass);


--
-- TOC entry 2071 (class 2604 OID 180948)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario ALTER COLUMN id SET DEFAULT nextval('usuario_id_seq'::regclass);


--
-- TOC entry 2186 (class 0 OID 180800)
-- Dependencies: 163
-- Data for Name: bairro; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY bairro (id, nome) FROM stdin;
\.


--
-- TOC entry 2272 (class 0 OID 0)
-- Dependencies: 162
-- Name: bairro_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('bairro_id_seq', 1, false);


--
-- TOC entry 2223 (class 0 OID 181036)
-- Dependencies: 200
-- Data for Name: captacao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY captacao (id, realizada) FROM stdin;
\.


--
-- TOC entry 2273 (class 0 OID 0)
-- Dependencies: 199
-- Name: captacao_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('captacao_id_seq', 1, false);


--
-- TOC entry 2225 (class 0 OID 181044)
-- Dependencies: 202
-- Data for Name: causamortis; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY causamortis (id, descricao, causa_id) FROM stdin;
\.


--
-- TOC entry 2274 (class 0 OID 0)
-- Dependencies: 201
-- Name: causamortis_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('causamortis_id_seq', 1, false);


--
-- TOC entry 2188 (class 0 OID 180808)
-- Dependencies: 165
-- Data for Name: cidade; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY cidade (id, nome) FROM stdin;
\.


--
-- TOC entry 2189 (class 0 OID 180814)
-- Dependencies: 166
-- Data for Name: cidade_bairro; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY cidade_bairro (cidade_id, bairros_id) FROM stdin;
\.


--
-- TOC entry 2275 (class 0 OID 0)
-- Dependencies: 164
-- Name: cidade_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cidade_id_seq', 5, true);


--
-- TOC entry 2191 (class 0 OID 180821)
-- Dependencies: 168
-- Data for Name: contraindicacaomedica; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY contraindicacaomedica (id, nome) FROM stdin;
\.


--
-- TOC entry 2276 (class 0 OID 0)
-- Dependencies: 167
-- Name: contraindicacaomedica_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('contraindicacaomedica_id_seq', 1, false);


--
-- TOC entry 2227 (class 0 OID 181052)
-- Dependencies: 204
-- Data for Name: doacao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY doacao (id, captacao, dataentrevista) FROM stdin;
\.


--
-- TOC entry 2277 (class 0 OID 0)
-- Dependencies: 203
-- Name: doacao_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('doacao_id_seq', 1, false);


--
-- TOC entry 2228 (class 0 OID 181061)
-- Dependencies: 205
-- Data for Name: doacao_responsavel; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY doacao_responsavel (doacao_id, responsaveis_id) FROM stdin;
\.


--
-- TOC entry 2229 (class 0 OID 181068)
-- Dependencies: 206
-- Data for Name: doacao_testemunha; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY doacao_testemunha (doacao_id, testemunhas_id) FROM stdin;
\.


--
-- TOC entry 2193 (class 0 OID 180829)
-- Dependencies: 170
-- Data for Name: endereco; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY endereco (id, cep, complemento, logradouro, numero, bairro_id, cidade_id, estado_id) FROM stdin;
\.


--
-- TOC entry 2278 (class 0 OID 0)
-- Dependencies: 169
-- Name: endereco_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('endereco_id_seq', 1, false);


--
-- TOC entry 2195 (class 0 OID 180837)
-- Dependencies: 172
-- Data for Name: estado; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY estado (id, nome, sigla) FROM stdin;
\.


--
-- TOC entry 2196 (class 0 OID 180843)
-- Dependencies: 173
-- Data for Name: estado_cidade; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY estado_cidade (estado_id, cidades_id) FROM stdin;
\.


--
-- TOC entry 2279 (class 0 OID 0)
-- Dependencies: 171
-- Name: estado_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('estado_id_seq', 1, false);


--
-- TOC entry 2198 (class 0 OID 180850)
-- Dependencies: 175
-- Data for Name: hospital; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY hospital (instituicaoid, cnes, nome, sigla, endereco_id) FROM stdin;
\.


--
-- TOC entry 2280 (class 0 OID 0)
-- Dependencies: 174
-- Name: hospital_instituicaoid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hospital_instituicaoid_seq', 1, false);


--
-- TOC entry 2199 (class 0 OID 180856)
-- Dependencies: 176
-- Data for Name: hospitalsetor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY hospitalsetor (instituicaoid, setorid) FROM stdin;
\.


--
-- TOC entry 2201 (class 0 OID 180863)
-- Dependencies: 178
-- Data for Name: motivoinviabilidade; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY motivoinviabilidade (id, nome, tipomotivoinviabilidade_id) FROM stdin;
\.


--
-- TOC entry 2281 (class 0 OID 0)
-- Dependencies: 177
-- Name: motivoinviabilidade_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('motivoinviabilidade_id_seq', 6, true);


--
-- TOC entry 2231 (class 0 OID 181077)
-- Dependencies: 208
-- Data for Name: notificacao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY notificacao (id, codigo, datanotificacao, notificador_id, obito_id, setor_setorid) FROM stdin;
\.


--
-- TOC entry 2282 (class 0 OID 0)
-- Dependencies: 207
-- Name: notificacao_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('notificacao_id_seq', 1, false);


--
-- TOC entry 2203 (class 0 OID 180871)
-- Dependencies: 180
-- Data for Name: notificador; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY notificador (id, nome, cpf, usuario_id) FROM stdin;
\.


--
-- TOC entry 2204 (class 0 OID 180877)
-- Dependencies: 181
-- Data for Name: notificador_hospital; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY notificador_hospital (notificador_id, hospitais_instituicaoid) FROM stdin;
\.


--
-- TOC entry 2283 (class 0 OID 0)
-- Dependencies: 179
-- Name: notificador_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('notificador_id_seq', 1, false);


--
-- TOC entry 2184 (class 0 OID 180464)
-- Dependencies: 161
-- Data for Name: notificador_telefone; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY notificador_telefone (notificador_id, telefones_id) FROM stdin;
\.


--
-- TOC entry 2233 (class 0 OID 181087)
-- Dependencies: 210
-- Data for Name: obito; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY obito (id, dataobito, encaminhamento, horarioobito, causaobito_id, paciente_id, setor_setorid) FROM stdin;
\.


--
-- TOC entry 2284 (class 0 OID 0)
-- Dependencies: 209
-- Name: obito_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('obito_id_seq', 1, false);


--
-- TOC entry 2235 (class 0 OID 181095)
-- Dependencies: 212
-- Data for Name: paciente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY paciente (id, nome, estadocivil, datainternacao, datanascimento, nacionalidade, nomemae, nomepai, numeroprontuario, rg, tipopaciente, doacao_id, responsavel_id) FROM stdin;
\.


--
-- TOC entry 2285 (class 0 OID 0)
-- Dependencies: 211
-- Name: paciente_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('paciente_id_seq', 1, false);


--
-- TOC entry 2206 (class 0 OID 180884)
-- Dependencies: 183
-- Data for Name: pais; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY pais (id, nome) FROM stdin;
\.


--
-- TOC entry 2207 (class 0 OID 180890)
-- Dependencies: 184
-- Data for Name: pais_estado; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY pais_estado (pais_id, estados_id) FROM stdin;
\.


--
-- TOC entry 2286 (class 0 OID 0)
-- Dependencies: 182
-- Name: pais_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('pais_id_seq', 1, false);


--
-- TOC entry 2209 (class 0 OID 180897)
-- Dependencies: 186
-- Data for Name: problemaestrutural; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY problemaestrutural (id, nome) FROM stdin;
\.


--
-- TOC entry 2287 (class 0 OID 0)
-- Dependencies: 185
-- Name: problemaestrutural_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('problemaestrutural_id_seq', 1, false);


--
-- TOC entry 2211 (class 0 OID 180905)
-- Dependencies: 188
-- Data for Name: problemalogistico; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY problemalogistico (id, nome) FROM stdin;
\.


--
-- TOC entry 2288 (class 0 OID 0)
-- Dependencies: 187
-- Name: problemalogistico_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('problemalogistico_id_seq', 1, false);


--
-- TOC entry 2213 (class 0 OID 180913)
-- Dependencies: 190
-- Data for Name: recusafamiliar; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY recusafamiliar (id, nome) FROM stdin;
\.


--
-- TOC entry 2289 (class 0 OID 0)
-- Dependencies: 189
-- Name: recusafamiliar_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('recusafamiliar_id_seq', 1, false);


--
-- TOC entry 2237 (class 0 OID 181106)
-- Dependencies: 214
-- Data for Name: responsavel; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY responsavel (id, nome, estadocivil, parentesco, profissao, rg, sexo) FROM stdin;
\.


--
-- TOC entry 2290 (class 0 OID 0)
-- Dependencies: 213
-- Name: responsavel_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('responsavel_id_seq', 1, false);


--
-- TOC entry 2215 (class 0 OID 180921)
-- Dependencies: 192
-- Data for Name: setor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY setor (setorid, nome) FROM stdin;
\.


--
-- TOC entry 2291 (class 0 OID 0)
-- Dependencies: 191
-- Name: setor_setorid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('setor_setorid_seq', 1, false);


--
-- TOC entry 2217 (class 0 OID 180929)
-- Dependencies: 194
-- Data for Name: telefone; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY telefone (id, ddd, numero, tipo) FROM stdin;
\.


--
-- TOC entry 2292 (class 0 OID 0)
-- Dependencies: 193
-- Name: telefone_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('telefone_id_seq', 1, false);


--
-- TOC entry 2239 (class 0 OID 181114)
-- Dependencies: 216
-- Data for Name: testemunha; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY testemunha (id, nome, cpf, endereco_id) FROM stdin;
\.


--
-- TOC entry 2293 (class 0 OID 0)
-- Dependencies: 215
-- Name: testemunha_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('testemunha_id_seq', 1, false);


--
-- TOC entry 2219 (class 0 OID 180937)
-- Dependencies: 196
-- Data for Name: tipomotivoinviabilidade; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tipomotivoinviabilidade (id, nome) FROM stdin;
1	teste1
2	teste2
\.


--
-- TOC entry 2294 (class 0 OID 0)
-- Dependencies: 195
-- Name: tipomotivoinviabilidade_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tipomotivoinviabilidade_id_seq', 2, true);


--
-- TOC entry 2221 (class 0 OID 180945)
-- Dependencies: 198
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY usuario (id, active, email, password, username) FROM stdin;
\.


--
-- TOC entry 2295 (class 0 OID 0)
-- Dependencies: 197
-- Name: usuario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('usuario_id_seq', 1, false);


--
-- TOC entry 2085 (class 2606 OID 180805)
-- Name: bairro_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY bairro
    ADD CONSTRAINT bairro_pkey PRIMARY KEY (id);


--
-- TOC entry 2129 (class 2606 OID 181041)
-- Name: captacao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY captacao
    ADD CONSTRAINT captacao_pkey PRIMARY KEY (id);


--
-- TOC entry 2131 (class 2606 OID 181049)
-- Name: causamortis_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY causamortis
    ADD CONSTRAINT causamortis_pkey PRIMARY KEY (id);


--
-- TOC entry 2089 (class 2606 OID 180818)
-- Name: cidade_bairro_bairros_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY cidade_bairro
    ADD CONSTRAINT cidade_bairro_bairros_id_key UNIQUE (bairros_id);


--
-- TOC entry 2087 (class 2606 OID 180813)
-- Name: cidade_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY cidade
    ADD CONSTRAINT cidade_pkey PRIMARY KEY (id);


--
-- TOC entry 2091 (class 2606 OID 180826)
-- Name: contraindicacaomedica_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY contraindicacaomedica
    ADD CONSTRAINT contraindicacaomedica_pkey PRIMARY KEY (id);


--
-- TOC entry 2133 (class 2606 OID 181060)
-- Name: doacao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY doacao
    ADD CONSTRAINT doacao_pkey PRIMARY KEY (id);


--
-- TOC entry 2135 (class 2606 OID 181065)
-- Name: doacao_responsavel_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY doacao_responsavel
    ADD CONSTRAINT doacao_responsavel_pkey PRIMARY KEY (doacao_id, responsaveis_id);


--
-- TOC entry 2137 (class 2606 OID 181067)
-- Name: doacao_responsavel_responsaveis_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY doacao_responsavel
    ADD CONSTRAINT doacao_responsavel_responsaveis_id_key UNIQUE (responsaveis_id);


--
-- TOC entry 2139 (class 2606 OID 181072)
-- Name: doacao_testemunha_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY doacao_testemunha
    ADD CONSTRAINT doacao_testemunha_pkey PRIMARY KEY (doacao_id, testemunhas_id);


--
-- TOC entry 2141 (class 2606 OID 181074)
-- Name: doacao_testemunha_testemunhas_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY doacao_testemunha
    ADD CONSTRAINT doacao_testemunha_testemunhas_id_key UNIQUE (testemunhas_id);


--
-- TOC entry 2093 (class 2606 OID 180834)
-- Name: endereco_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY endereco
    ADD CONSTRAINT endereco_pkey PRIMARY KEY (id);


--
-- TOC entry 2097 (class 2606 OID 180847)
-- Name: estado_cidade_cidades_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY estado_cidade
    ADD CONSTRAINT estado_cidade_cidades_id_key UNIQUE (cidades_id);


--
-- TOC entry 2095 (class 2606 OID 180842)
-- Name: estado_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY estado
    ADD CONSTRAINT estado_pkey PRIMARY KEY (id);


--
-- TOC entry 2099 (class 2606 OID 180855)
-- Name: hospital_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY hospital
    ADD CONSTRAINT hospital_pkey PRIMARY KEY (instituicaoid);


--
-- TOC entry 2101 (class 2606 OID 180860)
-- Name: hospitalsetor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY hospitalsetor
    ADD CONSTRAINT hospitalsetor_pkey PRIMARY KEY (instituicaoid, setorid);


--
-- TOC entry 2103 (class 2606 OID 180868)
-- Name: motivoinviabilidade_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY motivoinviabilidade
    ADD CONSTRAINT motivoinviabilidade_pkey PRIMARY KEY (id);


--
-- TOC entry 2143 (class 2606 OID 181084)
-- Name: notificacao_codigo_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY notificacao
    ADD CONSTRAINT notificacao_codigo_key UNIQUE (codigo);


--
-- TOC entry 2145 (class 2606 OID 181082)
-- Name: notificacao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY notificacao
    ADD CONSTRAINT notificacao_pkey PRIMARY KEY (id);


--
-- TOC entry 2107 (class 2606 OID 180881)
-- Name: notificador_hospital_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY notificador_hospital
    ADD CONSTRAINT notificador_hospital_pkey PRIMARY KEY (notificador_id, hospitais_instituicaoid);


--
-- TOC entry 2105 (class 2606 OID 180876)
-- Name: notificador_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY notificador
    ADD CONSTRAINT notificador_pkey PRIMARY KEY (id);


--
-- TOC entry 2081 (class 2606 OID 180468)
-- Name: notificador_telefone_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY notificador_telefone
    ADD CONSTRAINT notificador_telefone_pkey PRIMARY KEY (notificador_id, telefones_id);


--
-- TOC entry 2083 (class 2606 OID 180470)
-- Name: notificador_telefone_telefones_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY notificador_telefone
    ADD CONSTRAINT notificador_telefone_telefones_id_key UNIQUE (telefones_id);


--
-- TOC entry 2147 (class 2606 OID 181092)
-- Name: obito_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY obito
    ADD CONSTRAINT obito_pkey PRIMARY KEY (id);


--
-- TOC entry 2149 (class 2606 OID 181103)
-- Name: paciente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY paciente
    ADD CONSTRAINT paciente_pkey PRIMARY KEY (id);


--
-- TOC entry 2111 (class 2606 OID 180894)
-- Name: pais_estado_estados_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pais_estado
    ADD CONSTRAINT pais_estado_estados_id_key UNIQUE (estados_id);


--
-- TOC entry 2109 (class 2606 OID 180889)
-- Name: pais_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pais
    ADD CONSTRAINT pais_pkey PRIMARY KEY (id);


--
-- TOC entry 2113 (class 2606 OID 180902)
-- Name: problemaestrutural_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY problemaestrutural
    ADD CONSTRAINT problemaestrutural_pkey PRIMARY KEY (id);


--
-- TOC entry 2115 (class 2606 OID 180910)
-- Name: problemalogistico_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY problemalogistico
    ADD CONSTRAINT problemalogistico_pkey PRIMARY KEY (id);


--
-- TOC entry 2117 (class 2606 OID 180918)
-- Name: recusafamiliar_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY recusafamiliar
    ADD CONSTRAINT recusafamiliar_pkey PRIMARY KEY (id);


--
-- TOC entry 2151 (class 2606 OID 181111)
-- Name: responsavel_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY responsavel
    ADD CONSTRAINT responsavel_pkey PRIMARY KEY (id);


--
-- TOC entry 2119 (class 2606 OID 180926)
-- Name: setor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY setor
    ADD CONSTRAINT setor_pkey PRIMARY KEY (setorid);


--
-- TOC entry 2121 (class 2606 OID 180934)
-- Name: telefone_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY telefone
    ADD CONSTRAINT telefone_pkey PRIMARY KEY (id);


--
-- TOC entry 2153 (class 2606 OID 181119)
-- Name: testemunha_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY testemunha
    ADD CONSTRAINT testemunha_pkey PRIMARY KEY (id);


--
-- TOC entry 2123 (class 2606 OID 180942)
-- Name: tipomotivoinviabilidade_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tipomotivoinviabilidade
    ADD CONSTRAINT tipomotivoinviabilidade_pkey PRIMARY KEY (id);


--
-- TOC entry 2125 (class 2606 OID 180950)
-- Name: usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- TOC entry 2127 (class 2606 OID 180952)
-- Name: usuario_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_username_key UNIQUE (username);


--
-- TOC entry 2172 (class 2606 OID 181130)
-- Name: fk14d3a6dc218fbe87; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY doacao_responsavel
    ADD CONSTRAINT fk14d3a6dc218fbe87 FOREIGN KEY (doacao_id) REFERENCES doacao(id);


--
-- TOC entry 2171 (class 2606 OID 181125)
-- Name: fk14d3a6dc22eed3d7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY doacao_responsavel
    ADD CONSTRAINT fk14d3a6dc22eed3d7 FOREIGN KEY (responsaveis_id) REFERENCES responsavel(id);


--
-- TOC entry 2181 (class 2606 OID 181175)
-- Name: fk30817791218fbe87; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY paciente
    ADD CONSTRAINT fk30817791218fbe87 FOREIGN KEY (doacao_id) REFERENCES doacao(id);


--
-- TOC entry 2182 (class 2606 OID 181180)
-- Name: fk308177915af86f2d; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY paciente
    ADD CONSTRAINT fk308177915af86f2d FOREIGN KEY (responsavel_id) REFERENCES responsavel(id);


--
-- TOC entry 2180 (class 2606 OID 181170)
-- Name: fk48765d134965d97; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY obito
    ADD CONSTRAINT fk48765d134965d97 FOREIGN KEY (setor_setorid) REFERENCES setor(setorid);


--
-- TOC entry 2179 (class 2606 OID 181165)
-- Name: fk48765d13df7787; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY obito
    ADD CONSTRAINT fk48765d13df7787 FOREIGN KEY (paciente_id) REFERENCES paciente(id);


--
-- TOC entry 2178 (class 2606 OID 181160)
-- Name: fk48765d1c9642f54; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY obito
    ADD CONSTRAINT fk48765d1c9642f54 FOREIGN KEY (causaobito_id) REFERENCES causamortis(id);


--
-- TOC entry 2155 (class 2606 OID 180958)
-- Name: fk4b7b7b609ec2a543; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cidade_bairro
    ADD CONSTRAINT fk4b7b7b609ec2a543 FOREIGN KEY (cidade_id) REFERENCES cidade(id);


--
-- TOC entry 2154 (class 2606 OID 180953)
-- Name: fk4b7b7b60fa6f07ba; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cidade_bairro
    ADD CONSTRAINT fk4b7b7b60fa6f07ba FOREIGN KEY (bairros_id) REFERENCES bairro(id);


--
-- TOC entry 2158 (class 2606 OID 180973)
-- Name: fk6b07cbe9287d6603; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY endereco
    ADD CONSTRAINT fk6b07cbe9287d6603 FOREIGN KEY (estado_id) REFERENCES estado(id);


--
-- TOC entry 2157 (class 2606 OID 180968)
-- Name: fk6b07cbe99ec2a543; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY endereco
    ADD CONSTRAINT fk6b07cbe99ec2a543 FOREIGN KEY (cidade_id) REFERENCES cidade(id);


--
-- TOC entry 2156 (class 2606 OID 180963)
-- Name: fk6b07cbe9ef9d21a3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY endereco
    ADD CONSTRAINT fk6b07cbe9ef9d21a3 FOREIGN KEY (bairro_id) REFERENCES bairro(id);


--
-- TOC entry 2170 (class 2606 OID 181120)
-- Name: fk8a0fe833abe8ccfb; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY causamortis
    ADD CONSTRAINT fk8a0fe833abe8ccfb FOREIGN KEY (causa_id) REFERENCES causamortis(id);


--
-- TOC entry 2174 (class 2606 OID 181140)
-- Name: fk92f424f0218fbe87; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY doacao_testemunha
    ADD CONSTRAINT fk92f424f0218fbe87 FOREIGN KEY (doacao_id) REFERENCES doacao(id);


--
-- TOC entry 2173 (class 2606 OID 181135)
-- Name: fk92f424f0668c74ac; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY doacao_testemunha
    ADD CONSTRAINT fk92f424f0668c74ac FOREIGN KEY (testemunhas_id) REFERENCES testemunha(id);


--
-- TOC entry 2167 (class 2606 OID 181018)
-- Name: fkabe6b1494a0e2566; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificador_hospital
    ADD CONSTRAINT fkabe6b1494a0e2566 FOREIGN KEY (notificador_id) REFERENCES notificador(id);


--
-- TOC entry 2166 (class 2606 OID 181013)
-- Name: fkabe6b149a60ee9b2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificador_hospital
    ADD CONSTRAINT fkabe6b149a60ee9b2 FOREIGN KEY (hospitais_instituicaoid) REFERENCES hospital(instituicaoid);


--
-- TOC entry 2168 (class 2606 OID 181023)
-- Name: fkaf3d8b4a25bb3e3c; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pais_estado
    ADD CONSTRAINT fkaf3d8b4a25bb3e3c FOREIGN KEY (estados_id) REFERENCES estado(id);


--
-- TOC entry 2169 (class 2606 OID 181028)
-- Name: fkaf3d8b4adfcde423; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pais_estado
    ADD CONSTRAINT fkaf3d8b4adfcde423 FOREIGN KEY (pais_id) REFERENCES pais(id);


--
-- TOC entry 2164 (class 2606 OID 181003)
-- Name: fkba7a4bb68f5abc6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY motivoinviabilidade
    ADD CONSTRAINT fkba7a4bb68f5abc6 FOREIGN KEY (tipomotivoinviabilidade_id) REFERENCES tipomotivoinviabilidade(id);


--
-- TOC entry 2160 (class 2606 OID 180983)
-- Name: fkd4abf81d287d6603; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY estado_cidade
    ADD CONSTRAINT fkd4abf81d287d6603 FOREIGN KEY (estado_id) REFERENCES estado(id);


--
-- TOC entry 2159 (class 2606 OID 180978)
-- Name: fkd4abf81defde6ab8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY estado_cidade
    ADD CONSTRAINT fkd4abf81defde6ab8 FOREIGN KEY (cidades_id) REFERENCES cidade(id);


--
-- TOC entry 2162 (class 2606 OID 180993)
-- Name: fkd8214f8b10221211; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY hospitalsetor
    ADD CONSTRAINT fkd8214f8b10221211 FOREIGN KEY (setorid) REFERENCES setor(setorid);


--
-- TOC entry 2163 (class 2606 OID 180998)
-- Name: fkd8214f8b7d5c6f95; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY hospitalsetor
    ADD CONSTRAINT fkd8214f8b7d5c6f95 FOREIGN KEY (instituicaoid) REFERENCES hospital(instituicaoid);


--
-- TOC entry 2176 (class 2606 OID 181150)
-- Name: fke06d5afa219557cd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificacao
    ADD CONSTRAINT fke06d5afa219557cd FOREIGN KEY (obito_id) REFERENCES obito(id);


--
-- TOC entry 2177 (class 2606 OID 181155)
-- Name: fke06d5afa34965d97; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificacao
    ADD CONSTRAINT fke06d5afa34965d97 FOREIGN KEY (setor_setorid) REFERENCES setor(setorid);


--
-- TOC entry 2175 (class 2606 OID 181145)
-- Name: fke06d5afa4a0e2566; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificacao
    ADD CONSTRAINT fke06d5afa4a0e2566 FOREIGN KEY (notificador_id) REFERENCES notificador(id);


--
-- TOC entry 2165 (class 2606 OID 181008)
-- Name: fke06d60704ccd6ea6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificador
    ADD CONSTRAINT fke06d60704ccd6ea6 FOREIGN KEY (usuario_id) REFERENCES usuario(id);


--
-- TOC entry 2161 (class 2606 OID 180988)
-- Name: fkf1c1625a44be48e3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY hospital
    ADD CONSTRAINT fkf1c1625a44be48e3 FOREIGN KEY (endereco_id) REFERENCES endereco(id);


--
-- TOC entry 2183 (class 2606 OID 181185)
-- Name: fkfc233d2c44be48e3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY testemunha
    ADD CONSTRAINT fkfc233d2c44be48e3 FOREIGN KEY (endereco_id) REFERENCES endereco(id);


--
-- TOC entry 2246 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2014-01-09 18:06:06

--
-- PostgreSQL database dump complete
--

