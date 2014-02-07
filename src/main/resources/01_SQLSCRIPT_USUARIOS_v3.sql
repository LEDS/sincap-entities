--
-- PostgreSQL database dump
--

-- Dumped from database version 9.1.11
-- Dumped by pg_dump version 9.2.4
-- Started on 2014-01-09 19:23:40

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
-- TOC entry 2246 (class 0 OID 0)
-- Dependencies: 217
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 163 (class 1259 OID 181578)
-- Name: bairro; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE bairro (
    id bigint NOT NULL,
    nome character varying(255)
);


ALTER TABLE public.bairro OWNER TO postgres;

--
-- TOC entry 162 (class 1259 OID 181576)
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
-- TOC entry 2247 (class 0 OID 0)
-- Dependencies: 162
-- Name: bairro_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE bairro_id_seq OWNED BY bairro.id;


--
-- TOC entry 165 (class 1259 OID 181586)
-- Name: captacao; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE captacao (
    id bigint NOT NULL,
    realizada boolean
);


ALTER TABLE public.captacao OWNER TO postgres;

--
-- TOC entry 164 (class 1259 OID 181584)
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
-- TOC entry 2248 (class 0 OID 0)
-- Dependencies: 164
-- Name: captacao_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE captacao_id_seq OWNED BY captacao.id;


--
-- TOC entry 167 (class 1259 OID 181594)
-- Name: causamortis; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE causamortis (
    id bigint NOT NULL,
    descricao character varying(255),
    causa_id bigint
);


ALTER TABLE public.causamortis OWNER TO postgres;

--
-- TOC entry 166 (class 1259 OID 181592)
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
-- TOC entry 2249 (class 0 OID 0)
-- Dependencies: 166
-- Name: causamortis_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE causamortis_id_seq OWNED BY causamortis.id;


--
-- TOC entry 169 (class 1259 OID 181602)
-- Name: cidade; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE cidade (
    id bigint NOT NULL,
    nome character varying(255)
);


ALTER TABLE public.cidade OWNER TO postgres;

--
-- TOC entry 170 (class 1259 OID 181608)
-- Name: cidade_bairro; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE cidade_bairro (
    cidade_id bigint NOT NULL,
    bairros_id bigint NOT NULL
);


ALTER TABLE public.cidade_bairro OWNER TO postgres;

--
-- TOC entry 168 (class 1259 OID 181600)
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
-- TOC entry 2250 (class 0 OID 0)
-- Dependencies: 168
-- Name: cidade_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cidade_id_seq OWNED BY cidade.id;


--
-- TOC entry 172 (class 1259 OID 181615)
-- Name: contraindicacaomedica; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE contraindicacaomedica (
    id bigint NOT NULL,
    nome character varying(255)
);


ALTER TABLE public.contraindicacaomedica OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 181613)
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
-- TOC entry 2251 (class 0 OID 0)
-- Dependencies: 171
-- Name: contraindicacaomedica_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE contraindicacaomedica_id_seq OWNED BY contraindicacaomedica.id;


--
-- TOC entry 174 (class 1259 OID 181623)
-- Name: doacao; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE doacao (
    id bigint NOT NULL,
    captacao bytea,
    dataentrevista date
);


ALTER TABLE public.doacao OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 181621)
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
-- TOC entry 2252 (class 0 OID 0)
-- Dependencies: 173
-- Name: doacao_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE doacao_id_seq OWNED BY doacao.id;


--
-- TOC entry 175 (class 1259 OID 181632)
-- Name: doacao_responsavel; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE doacao_responsavel (
    doacao_id bigint NOT NULL,
    responsaveis_id bigint NOT NULL
);


ALTER TABLE public.doacao_responsavel OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 181639)
-- Name: doacao_testemunha; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE doacao_testemunha (
    doacao_id bigint NOT NULL,
    testemunhas_id bigint NOT NULL
);


ALTER TABLE public.doacao_testemunha OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 181648)
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
-- TOC entry 177 (class 1259 OID 181646)
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
-- TOC entry 2253 (class 0 OID 0)
-- Dependencies: 177
-- Name: endereco_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE endereco_id_seq OWNED BY endereco.id;


--
-- TOC entry 180 (class 1259 OID 181656)
-- Name: estado; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE estado (
    id bigint NOT NULL,
    nome character varying(255),
    sigla character varying(255)
);


ALTER TABLE public.estado OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 181662)
-- Name: estado_cidade; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE estado_cidade (
    estado_id bigint NOT NULL,
    cidades_id bigint NOT NULL
);


ALTER TABLE public.estado_cidade OWNER TO postgres;

--
-- TOC entry 179 (class 1259 OID 181654)
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
-- TOC entry 2254 (class 0 OID 0)
-- Dependencies: 179
-- Name: estado_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE estado_id_seq OWNED BY estado.id;


--
-- TOC entry 183 (class 1259 OID 181669)
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
-- TOC entry 182 (class 1259 OID 181667)
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
-- TOC entry 2255 (class 0 OID 0)
-- Dependencies: 182
-- Name: hospital_instituicaoid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE hospital_instituicaoid_seq OWNED BY hospital.instituicaoid;


--
-- TOC entry 184 (class 1259 OID 181675)
-- Name: hospitalsetor; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE hospitalsetor (
    instituicaoid bigint NOT NULL,
    setorid bigint NOT NULL
);


ALTER TABLE public.hospitalsetor OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 181682)
-- Name: motivoinviabilidade; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE motivoinviabilidade (
    id bigint NOT NULL,
    nome character varying(255),
    tipomotivoinviabilidade_id bigint
);


ALTER TABLE public.motivoinviabilidade OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 181680)
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
-- TOC entry 2256 (class 0 OID 0)
-- Dependencies: 185
-- Name: motivoinviabilidade_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE motivoinviabilidade_id_seq OWNED BY motivoinviabilidade.id;


--
-- TOC entry 188 (class 1259 OID 181690)
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
-- TOC entry 187 (class 1259 OID 181688)
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
-- TOC entry 2257 (class 0 OID 0)
-- Dependencies: 187
-- Name: notificacao_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE notificacao_id_seq OWNED BY notificacao.id;


--
-- TOC entry 190 (class 1259 OID 181700)
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
-- TOC entry 191 (class 1259 OID 181706)
-- Name: notificador_hospital; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE notificador_hospital (
    notificador_id bigint NOT NULL,
    hospitais_instituicaoid bigint NOT NULL
);


ALTER TABLE public.notificador_hospital OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 181698)
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
-- TOC entry 2258 (class 0 OID 0)
-- Dependencies: 189
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
-- TOC entry 193 (class 1259 OID 181713)
-- Name: obito; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE obito (
    id bigint NOT NULL,
    dataobito date,
    encaminhamento integer,
    horarioobito timestamp without time zone,
    causaobito_id bigint,
    paciente_id bigint
);


ALTER TABLE public.obito OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 181711)
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
-- TOC entry 2259 (class 0 OID 0)
-- Dependencies: 192
-- Name: obito_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE obito_id_seq OWNED BY obito.id;


--
-- TOC entry 195 (class 1259 OID 181721)
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
-- TOC entry 194 (class 1259 OID 181719)
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
-- TOC entry 2260 (class 0 OID 0)
-- Dependencies: 194
-- Name: paciente_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE paciente_id_seq OWNED BY paciente.id;


--
-- TOC entry 197 (class 1259 OID 181732)
-- Name: pais; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pais (
    id bigint NOT NULL,
    nome character varying(255)
);


ALTER TABLE public.pais OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 181738)
-- Name: pais_estado; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pais_estado (
    pais_id bigint NOT NULL,
    estados_id bigint NOT NULL
);


ALTER TABLE public.pais_estado OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 181730)
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
-- TOC entry 2261 (class 0 OID 0)
-- Dependencies: 196
-- Name: pais_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pais_id_seq OWNED BY pais.id;


--
-- TOC entry 200 (class 1259 OID 181745)
-- Name: problemaestrutural; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE problemaestrutural (
    id bigint NOT NULL,
    nome character varying(255)
);


ALTER TABLE public.problemaestrutural OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 181743)
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
-- TOC entry 2262 (class 0 OID 0)
-- Dependencies: 199
-- Name: problemaestrutural_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE problemaestrutural_id_seq OWNED BY problemaestrutural.id;


--
-- TOC entry 202 (class 1259 OID 181753)
-- Name: problemalogistico; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE problemalogistico (
    id bigint NOT NULL,
    nome character varying(255)
);


ALTER TABLE public.problemalogistico OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 181751)
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
-- TOC entry 2263 (class 0 OID 0)
-- Dependencies: 201
-- Name: problemalogistico_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE problemalogistico_id_seq OWNED BY problemalogistico.id;


--
-- TOC entry 204 (class 1259 OID 181761)
-- Name: recusafamiliar; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE recusafamiliar (
    id bigint NOT NULL,
    nome character varying(255)
);


ALTER TABLE public.recusafamiliar OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 181759)
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
-- TOC entry 2264 (class 0 OID 0)
-- Dependencies: 203
-- Name: recusafamiliar_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE recusafamiliar_id_seq OWNED BY recusafamiliar.id;


--
-- TOC entry 206 (class 1259 OID 181769)
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
-- TOC entry 205 (class 1259 OID 181767)
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
-- TOC entry 2265 (class 0 OID 0)
-- Dependencies: 205
-- Name: responsavel_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE responsavel_id_seq OWNED BY responsavel.id;


--
-- TOC entry 208 (class 1259 OID 181777)
-- Name: setor; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE setor (
    setorid bigint NOT NULL,
    nome character varying(255)
);


ALTER TABLE public.setor OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 181775)
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
-- TOC entry 2266 (class 0 OID 0)
-- Dependencies: 207
-- Name: setor_setorid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE setor_setorid_seq OWNED BY setor.setorid;


--
-- TOC entry 210 (class 1259 OID 181785)
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
-- TOC entry 209 (class 1259 OID 181783)
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
-- TOC entry 2267 (class 0 OID 0)
-- Dependencies: 209
-- Name: telefone_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE telefone_id_seq OWNED BY telefone.id;


--
-- TOC entry 212 (class 1259 OID 181793)
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
-- TOC entry 211 (class 1259 OID 181791)
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
-- TOC entry 2268 (class 0 OID 0)
-- Dependencies: 211
-- Name: testemunha_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE testemunha_id_seq OWNED BY testemunha.id;


--
-- TOC entry 214 (class 1259 OID 181801)
-- Name: tipomotivoinviabilidade; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tipomotivoinviabilidade (
    id bigint NOT NULL,
    nome character varying(255)
);


ALTER TABLE public.tipomotivoinviabilidade OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 181799)
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
-- TOC entry 2269 (class 0 OID 0)
-- Dependencies: 213
-- Name: tipomotivoinviabilidade_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tipomotivoinviabilidade_id_seq OWNED BY tipomotivoinviabilidade.id;


--
-- TOC entry 216 (class 1259 OID 181809)
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
-- TOC entry 215 (class 1259 OID 181807)
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
-- TOC entry 2270 (class 0 OID 0)
-- Dependencies: 215
-- Name: usuario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE usuario_id_seq OWNED BY usuario.id;


--
-- TOC entry 2056 (class 2604 OID 181581)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY bairro ALTER COLUMN id SET DEFAULT nextval('bairro_id_seq'::regclass);


--
-- TOC entry 2057 (class 2604 OID 181589)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY captacao ALTER COLUMN id SET DEFAULT nextval('captacao_id_seq'::regclass);


--
-- TOC entry 2058 (class 2604 OID 181597)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY causamortis ALTER COLUMN id SET DEFAULT nextval('causamortis_id_seq'::regclass);


--
-- TOC entry 2059 (class 2604 OID 181605)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cidade ALTER COLUMN id SET DEFAULT nextval('cidade_id_seq'::regclass);


--
-- TOC entry 2060 (class 2604 OID 181618)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contraindicacaomedica ALTER COLUMN id SET DEFAULT nextval('contraindicacaomedica_id_seq'::regclass);


--
-- TOC entry 2061 (class 2604 OID 181626)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY doacao ALTER COLUMN id SET DEFAULT nextval('doacao_id_seq'::regclass);


--
-- TOC entry 2062 (class 2604 OID 181651)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY endereco ALTER COLUMN id SET DEFAULT nextval('endereco_id_seq'::regclass);


--
-- TOC entry 2063 (class 2604 OID 181659)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY estado ALTER COLUMN id SET DEFAULT nextval('estado_id_seq'::regclass);


--
-- TOC entry 2064 (class 2604 OID 181672)
-- Name: instituicaoid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY hospital ALTER COLUMN instituicaoid SET DEFAULT nextval('hospital_instituicaoid_seq'::regclass);


--
-- TOC entry 2065 (class 2604 OID 181685)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY motivoinviabilidade ALTER COLUMN id SET DEFAULT nextval('motivoinviabilidade_id_seq'::regclass);


--
-- TOC entry 2066 (class 2604 OID 181693)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificacao ALTER COLUMN id SET DEFAULT nextval('notificacao_id_seq'::regclass);


--
-- TOC entry 2067 (class 2604 OID 181703)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificador ALTER COLUMN id SET DEFAULT nextval('notificador_id_seq'::regclass);


--
-- TOC entry 2068 (class 2604 OID 181716)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY obito ALTER COLUMN id SET DEFAULT nextval('obito_id_seq'::regclass);


--
-- TOC entry 2069 (class 2604 OID 181724)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY paciente ALTER COLUMN id SET DEFAULT nextval('paciente_id_seq'::regclass);


--
-- TOC entry 2070 (class 2604 OID 181735)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pais ALTER COLUMN id SET DEFAULT nextval('pais_id_seq'::regclass);


--
-- TOC entry 2071 (class 2604 OID 181748)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY problemaestrutural ALTER COLUMN id SET DEFAULT nextval('problemaestrutural_id_seq'::regclass);


--
-- TOC entry 2072 (class 2604 OID 181756)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY problemalogistico ALTER COLUMN id SET DEFAULT nextval('problemalogistico_id_seq'::regclass);


--
-- TOC entry 2073 (class 2604 OID 181764)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY recusafamiliar ALTER COLUMN id SET DEFAULT nextval('recusafamiliar_id_seq'::regclass);


--
-- TOC entry 2074 (class 2604 OID 181772)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY responsavel ALTER COLUMN id SET DEFAULT nextval('responsavel_id_seq'::regclass);


--
-- TOC entry 2075 (class 2604 OID 181780)
-- Name: setorid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY setor ALTER COLUMN setorid SET DEFAULT nextval('setor_setorid_seq'::regclass);


--
-- TOC entry 2076 (class 2604 OID 181788)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY telefone ALTER COLUMN id SET DEFAULT nextval('telefone_id_seq'::regclass);


--
-- TOC entry 2077 (class 2604 OID 181796)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY testemunha ALTER COLUMN id SET DEFAULT nextval('testemunha_id_seq'::regclass);


--
-- TOC entry 2078 (class 2604 OID 181804)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipomotivoinviabilidade ALTER COLUMN id SET DEFAULT nextval('tipomotivoinviabilidade_id_seq'::regclass);


--
-- TOC entry 2079 (class 2604 OID 181812)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario ALTER COLUMN id SET DEFAULT nextval('usuario_id_seq'::regclass);


--
-- TOC entry 2185 (class 0 OID 181578)
-- Dependencies: 163
-- Data for Name: bairro; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY bairro (id, nome) FROM stdin;
\.


--
-- TOC entry 2271 (class 0 OID 0)
-- Dependencies: 162
-- Name: bairro_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('bairro_id_seq', 1, false);


--
-- TOC entry 2187 (class 0 OID 181586)
-- Dependencies: 165
-- Data for Name: captacao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY captacao (id, realizada) FROM stdin;
\.


--
-- TOC entry 2272 (class 0 OID 0)
-- Dependencies: 164
-- Name: captacao_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('captacao_id_seq', 1, false);


--
-- TOC entry 2189 (class 0 OID 181594)
-- Dependencies: 167
-- Data for Name: causamortis; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY causamortis (id, descricao, causa_id) FROM stdin;
\.


--
-- TOC entry 2273 (class 0 OID 0)
-- Dependencies: 166
-- Name: causamortis_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('causamortis_id_seq', 12, true);


--
-- TOC entry 2191 (class 0 OID 181602)
-- Dependencies: 169
-- Data for Name: cidade; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY cidade (id, nome) FROM stdin;
\.


--
-- TOC entry 2192 (class 0 OID 181608)
-- Dependencies: 170
-- Data for Name: cidade_bairro; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY cidade_bairro (cidade_id, bairros_id) FROM stdin;
\.


--
-- TOC entry 2274 (class 0 OID 0)
-- Dependencies: 168
-- Name: cidade_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cidade_id_seq', 1, false);


--
-- TOC entry 2194 (class 0 OID 181615)
-- Dependencies: 172
-- Data for Name: contraindicacaomedica; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY contraindicacaomedica (id, nome) FROM stdin;
\.


--
-- TOC entry 2275 (class 0 OID 0)
-- Dependencies: 171
-- Name: contraindicacaomedica_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('contraindicacaomedica_id_seq', 1, false);


--
-- TOC entry 2196 (class 0 OID 181623)
-- Dependencies: 174
-- Data for Name: doacao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY doacao (id, captacao, dataentrevista) FROM stdin;
\.


--
-- TOC entry 2276 (class 0 OID 0)
-- Dependencies: 173
-- Name: doacao_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('doacao_id_seq', 1, false);


--
-- TOC entry 2197 (class 0 OID 181632)
-- Dependencies: 175
-- Data for Name: doacao_responsavel; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY doacao_responsavel (doacao_id, responsaveis_id) FROM stdin;
\.


--
-- TOC entry 2198 (class 0 OID 181639)
-- Dependencies: 176
-- Data for Name: doacao_testemunha; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY doacao_testemunha (doacao_id, testemunhas_id) FROM stdin;
\.


--
-- TOC entry 2200 (class 0 OID 181648)
-- Dependencies: 178
-- Data for Name: endereco; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY endereco (id, cep, complemento, logradouro, numero, bairro_id, cidade_id, estado_id) FROM stdin;
\.


--
-- TOC entry 2277 (class 0 OID 0)
-- Dependencies: 177
-- Name: endereco_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('endereco_id_seq', 1, false);


--
-- TOC entry 2202 (class 0 OID 181656)
-- Dependencies: 180
-- Data for Name: estado; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY estado (id, nome, sigla) FROM stdin;
\.


--
-- TOC entry 2203 (class 0 OID 181662)
-- Dependencies: 181
-- Data for Name: estado_cidade; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY estado_cidade (estado_id, cidades_id) FROM stdin;
\.


--
-- TOC entry 2278 (class 0 OID 0)
-- Dependencies: 179
-- Name: estado_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('estado_id_seq', 1, false);


--
-- TOC entry 2205 (class 0 OID 181669)
-- Dependencies: 183
-- Data for Name: hospital; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY hospital (instituicaoid, cnes, nome, sigla, endereco_id) FROM stdin;
\.


--
-- TOC entry 2279 (class 0 OID 0)
-- Dependencies: 182
-- Name: hospital_instituicaoid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hospital_instituicaoid_seq', 1, false);


--
-- TOC entry 2206 (class 0 OID 181675)
-- Dependencies: 184
-- Data for Name: hospitalsetor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY hospitalsetor (instituicaoid, setorid) FROM stdin;
\.


--
-- TOC entry 2208 (class 0 OID 181682)
-- Dependencies: 186
-- Data for Name: motivoinviabilidade; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY motivoinviabilidade (id, nome, tipomotivoinviabilidade_id) FROM stdin;
\.


--
-- TOC entry 2280 (class 0 OID 0)
-- Dependencies: 185
-- Name: motivoinviabilidade_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('motivoinviabilidade_id_seq', 7, true);


--
-- TOC entry 2210 (class 0 OID 181690)
-- Dependencies: 188
-- Data for Name: notificacao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY notificacao (id, codigo, datanotificacao, notificador_id, obito_id, setor_setorid) FROM stdin;
\.


--
-- TOC entry 2281 (class 0 OID 0)
-- Dependencies: 187
-- Name: notificacao_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('notificacao_id_seq', 7, true);


--
-- TOC entry 2212 (class 0 OID 181700)
-- Dependencies: 190
-- Data for Name: notificador; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY notificador (id, nome, cpf, usuario_id) FROM stdin;
\.


--
-- TOC entry 2213 (class 0 OID 181706)
-- Dependencies: 191
-- Data for Name: notificador_hospital; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY notificador_hospital (notificador_id, hospitais_instituicaoid) FROM stdin;
\.


--
-- TOC entry 2282 (class 0 OID 0)
-- Dependencies: 189
-- Name: notificador_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('notificador_id_seq', 8, true);


--
-- TOC entry 2183 (class 0 OID 180464)
-- Dependencies: 161
-- Data for Name: notificador_telefone; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY notificador_telefone (notificador_id, telefones_id) FROM stdin;
\.


--
-- TOC entry 2215 (class 0 OID 181713)
-- Dependencies: 193
-- Data for Name: obito; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY obito (id, dataobito, encaminhamento, horarioobito, causaobito_id, paciente_id) FROM stdin;
\.


--
-- TOC entry 2283 (class 0 OID 0)
-- Dependencies: 192
-- Name: obito_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('obito_id_seq', 7, true);


--
-- TOC entry 2217 (class 0 OID 181721)
-- Dependencies: 195
-- Data for Name: paciente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY paciente (id, nome, estadocivil, datainternacao, datanascimento, nacionalidade, nomemae, nomepai, numeroprontuario, rg, tipopaciente, doacao_id, responsavel_id) FROM stdin;
\.


--
-- TOC entry 2284 (class 0 OID 0)
-- Dependencies: 194
-- Name: paciente_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('paciente_id_seq', 5, true);


--
-- TOC entry 2219 (class 0 OID 181732)
-- Dependencies: 197
-- Data for Name: pais; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY pais (id, nome) FROM stdin;
\.


--
-- TOC entry 2220 (class 0 OID 181738)
-- Dependencies: 198
-- Data for Name: pais_estado; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY pais_estado (pais_id, estados_id) FROM stdin;
\.


--
-- TOC entry 2285 (class 0 OID 0)
-- Dependencies: 196
-- Name: pais_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('pais_id_seq', 1, false);


--
-- TOC entry 2222 (class 0 OID 181745)
-- Dependencies: 200
-- Data for Name: problemaestrutural; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY problemaestrutural (id, nome) FROM stdin;
\.


--
-- TOC entry 2286 (class 0 OID 0)
-- Dependencies: 199
-- Name: problemaestrutural_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('problemaestrutural_id_seq', 1, false);


--
-- TOC entry 2224 (class 0 OID 181753)
-- Dependencies: 202
-- Data for Name: problemalogistico; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY problemalogistico (id, nome) FROM stdin;
\.


--
-- TOC entry 2287 (class 0 OID 0)
-- Dependencies: 201
-- Name: problemalogistico_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('problemalogistico_id_seq', 1, false);


--
-- TOC entry 2226 (class 0 OID 181761)
-- Dependencies: 204
-- Data for Name: recusafamiliar; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY recusafamiliar (id, nome) FROM stdin;
\.


--
-- TOC entry 2288 (class 0 OID 0)
-- Dependencies: 203
-- Name: recusafamiliar_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('recusafamiliar_id_seq', 1, false);


--
-- TOC entry 2228 (class 0 OID 181769)
-- Dependencies: 206
-- Data for Name: responsavel; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY responsavel (id, nome, estadocivil, parentesco, profissao, rg, sexo) FROM stdin;
\.


--
-- TOC entry 2289 (class 0 OID 0)
-- Dependencies: 205
-- Name: responsavel_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('responsavel_id_seq', 3, true);


--
-- TOC entry 2230 (class 0 OID 181777)
-- Dependencies: 208
-- Data for Name: setor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY setor (setorid, nome) FROM stdin;
\.


--
-- TOC entry 2290 (class 0 OID 0)
-- Dependencies: 207
-- Name: setor_setorid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('setor_setorid_seq', 1, false);


--
-- TOC entry 2232 (class 0 OID 181785)
-- Dependencies: 210
-- Data for Name: telefone; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY telefone (id, ddd, numero, tipo) FROM stdin;
\.


--
-- TOC entry 2291 (class 0 OID 0)
-- Dependencies: 209
-- Name: telefone_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('telefone_id_seq', 1, false);


--
-- TOC entry 2234 (class 0 OID 181793)
-- Dependencies: 212
-- Data for Name: testemunha; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY testemunha (id, nome, cpf, endereco_id) FROM stdin;
\.


--
-- TOC entry 2292 (class 0 OID 0)
-- Dependencies: 211
-- Name: testemunha_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('testemunha_id_seq', 1, false);


--
-- TOC entry 2236 (class 0 OID 181801)
-- Dependencies: 214
-- Data for Name: tipomotivoinviabilidade; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tipomotivoinviabilidade (id, nome) FROM stdin;
\.


--
-- TOC entry 2293 (class 0 OID 0)
-- Dependencies: 213
-- Name: tipomotivoinviabilidade_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tipomotivoinviabilidade_id_seq', 1, false);


--
-- TOC entry 2238 (class 0 OID 181809)
-- Dependencies: 216
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY usuario (id, active, email, password, username) FROM stdin;
\.


--
-- TOC entry 2294 (class 0 OID 0)
-- Dependencies: 215
-- Name: usuario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('usuario_id_seq', 1, false);


--
-- TOC entry 2085 (class 2606 OID 181583)
-- Name: bairro_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY bairro
    ADD CONSTRAINT bairro_pkey PRIMARY KEY (id);


--
-- TOC entry 2087 (class 2606 OID 181591)
-- Name: captacao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY captacao
    ADD CONSTRAINT captacao_pkey PRIMARY KEY (id);


--
-- TOC entry 2089 (class 2606 OID 181599)
-- Name: causamortis_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY causamortis
    ADD CONSTRAINT causamortis_pkey PRIMARY KEY (id);


--
-- TOC entry 2093 (class 2606 OID 181612)
-- Name: cidade_bairro_bairros_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY cidade_bairro
    ADD CONSTRAINT cidade_bairro_bairros_id_key UNIQUE (bairros_id);


--
-- TOC entry 2091 (class 2606 OID 181607)
-- Name: cidade_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY cidade
    ADD CONSTRAINT cidade_pkey PRIMARY KEY (id);


--
-- TOC entry 2095 (class 2606 OID 181620)
-- Name: contraindicacaomedica_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY contraindicacaomedica
    ADD CONSTRAINT contraindicacaomedica_pkey PRIMARY KEY (id);


--
-- TOC entry 2097 (class 2606 OID 181631)
-- Name: doacao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY doacao
    ADD CONSTRAINT doacao_pkey PRIMARY KEY (id);


--
-- TOC entry 2099 (class 2606 OID 181636)
-- Name: doacao_responsavel_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY doacao_responsavel
    ADD CONSTRAINT doacao_responsavel_pkey PRIMARY KEY (doacao_id, responsaveis_id);


--
-- TOC entry 2101 (class 2606 OID 181638)
-- Name: doacao_responsavel_responsaveis_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY doacao_responsavel
    ADD CONSTRAINT doacao_responsavel_responsaveis_id_key UNIQUE (responsaveis_id);


--
-- TOC entry 2103 (class 2606 OID 181643)
-- Name: doacao_testemunha_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY doacao_testemunha
    ADD CONSTRAINT doacao_testemunha_pkey PRIMARY KEY (doacao_id, testemunhas_id);


--
-- TOC entry 2105 (class 2606 OID 181645)
-- Name: doacao_testemunha_testemunhas_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY doacao_testemunha
    ADD CONSTRAINT doacao_testemunha_testemunhas_id_key UNIQUE (testemunhas_id);


--
-- TOC entry 2107 (class 2606 OID 181653)
-- Name: endereco_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY endereco
    ADD CONSTRAINT endereco_pkey PRIMARY KEY (id);


--
-- TOC entry 2111 (class 2606 OID 181666)
-- Name: estado_cidade_cidades_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY estado_cidade
    ADD CONSTRAINT estado_cidade_cidades_id_key UNIQUE (cidades_id);


--
-- TOC entry 2109 (class 2606 OID 181661)
-- Name: estado_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY estado
    ADD CONSTRAINT estado_pkey PRIMARY KEY (id);


--
-- TOC entry 2113 (class 2606 OID 181674)
-- Name: hospital_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY hospital
    ADD CONSTRAINT hospital_pkey PRIMARY KEY (instituicaoid);


--
-- TOC entry 2115 (class 2606 OID 181679)
-- Name: hospitalsetor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY hospitalsetor
    ADD CONSTRAINT hospitalsetor_pkey PRIMARY KEY (instituicaoid, setorid);


--
-- TOC entry 2117 (class 2606 OID 181687)
-- Name: motivoinviabilidade_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY motivoinviabilidade
    ADD CONSTRAINT motivoinviabilidade_pkey PRIMARY KEY (id);


--
-- TOC entry 2119 (class 2606 OID 181697)
-- Name: notificacao_codigo_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY notificacao
    ADD CONSTRAINT notificacao_codigo_key UNIQUE (codigo);


--
-- TOC entry 2121 (class 2606 OID 181695)
-- Name: notificacao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY notificacao
    ADD CONSTRAINT notificacao_pkey PRIMARY KEY (id);


--
-- TOC entry 2125 (class 2606 OID 181710)
-- Name: notificador_hospital_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY notificador_hospital
    ADD CONSTRAINT notificador_hospital_pkey PRIMARY KEY (notificador_id, hospitais_instituicaoid);


--
-- TOC entry 2123 (class 2606 OID 181705)
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
-- TOC entry 2127 (class 2606 OID 181718)
-- Name: obito_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY obito
    ADD CONSTRAINT obito_pkey PRIMARY KEY (id);


--
-- TOC entry 2129 (class 2606 OID 181729)
-- Name: paciente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY paciente
    ADD CONSTRAINT paciente_pkey PRIMARY KEY (id);


--
-- TOC entry 2133 (class 2606 OID 181742)
-- Name: pais_estado_estados_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pais_estado
    ADD CONSTRAINT pais_estado_estados_id_key UNIQUE (estados_id);


--
-- TOC entry 2131 (class 2606 OID 181737)
-- Name: pais_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pais
    ADD CONSTRAINT pais_pkey PRIMARY KEY (id);


--
-- TOC entry 2135 (class 2606 OID 181750)
-- Name: problemaestrutural_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY problemaestrutural
    ADD CONSTRAINT problemaestrutural_pkey PRIMARY KEY (id);


--
-- TOC entry 2137 (class 2606 OID 181758)
-- Name: problemalogistico_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY problemalogistico
    ADD CONSTRAINT problemalogistico_pkey PRIMARY KEY (id);


--
-- TOC entry 2139 (class 2606 OID 181766)
-- Name: recusafamiliar_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY recusafamiliar
    ADD CONSTRAINT recusafamiliar_pkey PRIMARY KEY (id);


--
-- TOC entry 2141 (class 2606 OID 181774)
-- Name: responsavel_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY responsavel
    ADD CONSTRAINT responsavel_pkey PRIMARY KEY (id);


--
-- TOC entry 2143 (class 2606 OID 181782)
-- Name: setor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY setor
    ADD CONSTRAINT setor_pkey PRIMARY KEY (setorid);


--
-- TOC entry 2145 (class 2606 OID 181790)
-- Name: telefone_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY telefone
    ADD CONSTRAINT telefone_pkey PRIMARY KEY (id);


--
-- TOC entry 2147 (class 2606 OID 181798)
-- Name: testemunha_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY testemunha
    ADD CONSTRAINT testemunha_pkey PRIMARY KEY (id);


--
-- TOC entry 2149 (class 2606 OID 181806)
-- Name: tipomotivoinviabilidade_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tipomotivoinviabilidade
    ADD CONSTRAINT tipomotivoinviabilidade_pkey PRIMARY KEY (id);


--
-- TOC entry 2151 (class 2606 OID 181814)
-- Name: usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- TOC entry 2153 (class 2606 OID 181816)
-- Name: usuario_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_username_key UNIQUE (username);


--
-- TOC entry 2158 (class 2606 OID 181837)
-- Name: fk14d3a6dc218fbe87; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY doacao_responsavel
    ADD CONSTRAINT fk14d3a6dc218fbe87 FOREIGN KEY (doacao_id) REFERENCES doacao(id);


--
-- TOC entry 2157 (class 2606 OID 181832)
-- Name: fk14d3a6dc22eed3d7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY doacao_responsavel
    ADD CONSTRAINT fk14d3a6dc22eed3d7 FOREIGN KEY (responsaveis_id) REFERENCES responsavel(id);


--
-- TOC entry 2178 (class 2606 OID 181937)
-- Name: fk30817791218fbe87; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY paciente
    ADD CONSTRAINT fk30817791218fbe87 FOREIGN KEY (doacao_id) REFERENCES doacao(id);


--
-- TOC entry 2179 (class 2606 OID 181942)
-- Name: fk308177915af86f2d; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY paciente
    ADD CONSTRAINT fk308177915af86f2d FOREIGN KEY (responsavel_id) REFERENCES responsavel(id);


--
-- TOC entry 2177 (class 2606 OID 181932)
-- Name: fk48765d13df7787; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY obito
    ADD CONSTRAINT fk48765d13df7787 FOREIGN KEY (paciente_id) REFERENCES paciente(id);


--
-- TOC entry 2176 (class 2606 OID 181927)
-- Name: fk48765d1c9642f54; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY obito
    ADD CONSTRAINT fk48765d1c9642f54 FOREIGN KEY (causaobito_id) REFERENCES causamortis(id);


--
-- TOC entry 2156 (class 2606 OID 181827)
-- Name: fk4b7b7b609ec2a543; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cidade_bairro
    ADD CONSTRAINT fk4b7b7b609ec2a543 FOREIGN KEY (cidade_id) REFERENCES cidade(id);


--
-- TOC entry 2155 (class 2606 OID 181822)
-- Name: fk4b7b7b60fa6f07ba; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cidade_bairro
    ADD CONSTRAINT fk4b7b7b60fa6f07ba FOREIGN KEY (bairros_id) REFERENCES bairro(id);


--
-- TOC entry 2163 (class 2606 OID 181862)
-- Name: fk6b07cbe9287d6603; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY endereco
    ADD CONSTRAINT fk6b07cbe9287d6603 FOREIGN KEY (estado_id) REFERENCES estado(id);


--
-- TOC entry 2162 (class 2606 OID 181857)
-- Name: fk6b07cbe99ec2a543; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY endereco
    ADD CONSTRAINT fk6b07cbe99ec2a543 FOREIGN KEY (cidade_id) REFERENCES cidade(id);


--
-- TOC entry 2161 (class 2606 OID 181852)
-- Name: fk6b07cbe9ef9d21a3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY endereco
    ADD CONSTRAINT fk6b07cbe9ef9d21a3 FOREIGN KEY (bairro_id) REFERENCES bairro(id);


--
-- TOC entry 2154 (class 2606 OID 181817)
-- Name: fk8a0fe833abe8ccfb; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY causamortis
    ADD CONSTRAINT fk8a0fe833abe8ccfb FOREIGN KEY (causa_id) REFERENCES causamortis(id);


--
-- TOC entry 2160 (class 2606 OID 181847)
-- Name: fk92f424f0218fbe87; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY doacao_testemunha
    ADD CONSTRAINT fk92f424f0218fbe87 FOREIGN KEY (doacao_id) REFERENCES doacao(id);


--
-- TOC entry 2159 (class 2606 OID 181842)
-- Name: fk92f424f0668c74ac; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY doacao_testemunha
    ADD CONSTRAINT fk92f424f0668c74ac FOREIGN KEY (testemunhas_id) REFERENCES testemunha(id);


--
-- TOC entry 2175 (class 2606 OID 181922)
-- Name: fkabe6b1494a0e2566; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificador_hospital
    ADD CONSTRAINT fkabe6b1494a0e2566 FOREIGN KEY (notificador_id) REFERENCES notificador(id);


--
-- TOC entry 2174 (class 2606 OID 181917)
-- Name: fkabe6b149a60ee9b2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificador_hospital
    ADD CONSTRAINT fkabe6b149a60ee9b2 FOREIGN KEY (hospitais_instituicaoid) REFERENCES hospital(instituicaoid);


--
-- TOC entry 2180 (class 2606 OID 181947)
-- Name: fkaf3d8b4a25bb3e3c; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pais_estado
    ADD CONSTRAINT fkaf3d8b4a25bb3e3c FOREIGN KEY (estados_id) REFERENCES estado(id);


--
-- TOC entry 2181 (class 2606 OID 181952)
-- Name: fkaf3d8b4adfcde423; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pais_estado
    ADD CONSTRAINT fkaf3d8b4adfcde423 FOREIGN KEY (pais_id) REFERENCES pais(id);


--
-- TOC entry 2169 (class 2606 OID 181892)
-- Name: fkba7a4bb68f5abc6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY motivoinviabilidade
    ADD CONSTRAINT fkba7a4bb68f5abc6 FOREIGN KEY (tipomotivoinviabilidade_id) REFERENCES tipomotivoinviabilidade(id);


--
-- TOC entry 2165 (class 2606 OID 181872)
-- Name: fkd4abf81d287d6603; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY estado_cidade
    ADD CONSTRAINT fkd4abf81d287d6603 FOREIGN KEY (estado_id) REFERENCES estado(id);


--
-- TOC entry 2164 (class 2606 OID 181867)
-- Name: fkd4abf81defde6ab8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY estado_cidade
    ADD CONSTRAINT fkd4abf81defde6ab8 FOREIGN KEY (cidades_id) REFERENCES cidade(id);


--
-- TOC entry 2167 (class 2606 OID 181882)
-- Name: fkd8214f8b10221211; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY hospitalsetor
    ADD CONSTRAINT fkd8214f8b10221211 FOREIGN KEY (setorid) REFERENCES setor(setorid);


--
-- TOC entry 2168 (class 2606 OID 181887)
-- Name: fkd8214f8b7d5c6f95; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY hospitalsetor
    ADD CONSTRAINT fkd8214f8b7d5c6f95 FOREIGN KEY (instituicaoid) REFERENCES hospital(instituicaoid);


--
-- TOC entry 2171 (class 2606 OID 181902)
-- Name: fke06d5afa219557cd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificacao
    ADD CONSTRAINT fke06d5afa219557cd FOREIGN KEY (obito_id) REFERENCES obito(id);


--
-- TOC entry 2172 (class 2606 OID 181907)
-- Name: fke06d5afa34965d97; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificacao
    ADD CONSTRAINT fke06d5afa34965d97 FOREIGN KEY (setor_setorid) REFERENCES setor(setorid);


--
-- TOC entry 2170 (class 2606 OID 181897)
-- Name: fke06d5afa4a0e2566; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificacao
    ADD CONSTRAINT fke06d5afa4a0e2566 FOREIGN KEY (notificador_id) REFERENCES notificador(id);


--
-- TOC entry 2173 (class 2606 OID 181912)
-- Name: fke06d60704ccd6ea6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificador
    ADD CONSTRAINT fke06d60704ccd6ea6 FOREIGN KEY (usuario_id) REFERENCES usuario(id);


--
-- TOC entry 2166 (class 2606 OID 181877)
-- Name: fkf1c1625a44be48e3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY hospital
    ADD CONSTRAINT fkf1c1625a44be48e3 FOREIGN KEY (endereco_id) REFERENCES endereco(id);


--
-- TOC entry 2182 (class 2606 OID 181957)
-- Name: fkfc233d2c44be48e3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY testemunha
    ADD CONSTRAINT fkfc233d2c44be48e3 FOREIGN KEY (endereco_id) REFERENCES endereco(id);


--
-- TOC entry 2245 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2014-01-09 19:23:41

--
-- PostgreSQL database dump complete
--

