--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2020-03-06 11:23:18

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 202 (class 1259 OID 16394)
-- Name: area_investigacion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.area_investigacion (
    area_id smallint NOT NULL,
    nombre_investigacion character varying(150) NOT NULL,
    descripcion_investigacion text NOT NULL
);


ALTER TABLE public.area_investigacion OWNER TO postgres;

--
-- TOC entry 3105 (class 0 OID 0)
-- Dependencies: 202
-- Name: TABLE area_investigacion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.area_investigacion IS 'area_investigacion en los proyectos MIA';


--
-- TOC entry 3106 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN area_investigacion.area_id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.area_investigacion.area_id IS 'Clave primaria de la tabla areainvestigación';


--
-- TOC entry 3107 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN area_investigacion.nombre_investigacion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.area_investigacion.nombre_investigacion IS 'nombre del area de investigacion';


--
-- TOC entry 3108 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN area_investigacion.descripcion_investigacion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.area_investigacion.descripcion_investigacion IS 'descripcion del area de investigacion';


--
-- TOC entry 203 (class 1259 OID 16400)
-- Name: cuestionario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cuestionario (
    id_cuestionario smallint NOT NULL,
    descripcion character varying(100) NOT NULL
);


ALTER TABLE public.cuestionario OWNER TO postgres;

--
-- TOC entry 3109 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN cuestionario.descripcion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.cuestionario.descripcion IS 'tipo de cuestionario ';


--
-- TOC entry 204 (class 1259 OID 16403)
-- Name: curso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.curso (
    id_curso smallint NOT NULL,
    nombre character varying(100) NOT NULL,
    descripcion character varying(500) NOT NULL,
    avance smallint NOT NULL,
    secuencia boolean
);


ALTER TABLE public.curso OWNER TO postgres;

--
-- TOC entry 3110 (class 0 OID 0)
-- Dependencies: 204
-- Name: TABLE curso; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.curso IS 'tabla de cursos ofrecidos por MIA Institute';


--
-- TOC entry 3111 (class 0 OID 0)
-- Dependencies: 204
-- Name: COLUMN curso.id_curso; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.curso.id_curso IS 'clave primaria de la tabla de cursos.';


--
-- TOC entry 3112 (class 0 OID 0)
-- Dependencies: 204
-- Name: COLUMN curso.nombre; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.curso.nombre IS 'nombre del curso';


--
-- TOC entry 3113 (class 0 OID 0)
-- Dependencies: 204
-- Name: COLUMN curso.descripcion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.curso.descripcion IS 'Descripción del curso';


--
-- TOC entry 3114 (class 0 OID 0)
-- Dependencies: 204
-- Name: COLUMN curso.avance; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.curso.avance IS 'cantidad de modulos del curso';


--
-- TOC entry 3115 (class 0 OID 0)
-- Dependencies: 204
-- Name: COLUMN curso.secuencia; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.curso.secuencia IS 'saber si el curso posee una secuencia.';


--
-- TOC entry 205 (class 1259 OID 16409)
-- Name: curso_modulo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.curso_modulo (
    id_curso_modulo numeric(4,0) NOT NULL,
    id_modulo numeric(4,0) NOT NULL,
    id_curso smallint NOT NULL,
    orden_curso integer
);


ALTER TABLE public.curso_modulo OWNER TO postgres;

--
-- TOC entry 3116 (class 0 OID 0)
-- Dependencies: 205
-- Name: TABLE curso_modulo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.curso_modulo IS 'realizamos la union de modulos y curso';


--
-- TOC entry 3117 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN curso_modulo.id_modulo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.curso_modulo.id_modulo IS 'clave foranea de la tabla';


--
-- TOC entry 3118 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN curso_modulo.id_curso; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.curso_modulo.id_curso IS 'clave foranea de la tabla curso';


--
-- TOC entry 3119 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN curso_modulo.orden_curso; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.curso_modulo.orden_curso IS 'la secuencia del curso, en que debe realizar el curso.';


--
-- TOC entry 206 (class 1259 OID 16412)
-- Name: dimension; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.dimension (
    id_dimension smallint NOT NULL,
    descripcion character varying(100) NOT NULL,
    id_cuestionario smallint NOT NULL
);


ALTER TABLE public.dimension OWNER TO postgres;

--
-- TOC entry 3120 (class 0 OID 0)
-- Dependencies: 206
-- Name: COLUMN dimension.descripcion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.dimension.descripcion IS 'Descricion de la tabla dimension';


--
-- TOC entry 207 (class 1259 OID 16415)
-- Name: dimension_pregunta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.dimension_pregunta (
    id_pregunta_dimension numeric NOT NULL,
    id_dimension smallint,
    id_pregunta integer
);


ALTER TABLE public.dimension_pregunta OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 16421)
-- Name: etnia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.etnia (
    id_etnia smallint NOT NULL,
    descripcion character varying(100) NOT NULL
);


ALTER TABLE public.etnia OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 16424)
-- Name: ficha_personal; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ficha_personal (
    id_ficha numeric NOT NULL,
    edad integer,
    genero character varying(25) NOT NULL,
    fecha_nacimiento date NOT NULL,
    ciudad character varying(150) NOT NULL,
    direccion character varying(200) NOT NULL,
    telefono character varying(20),
    celular character varying(20) NOT NULL,
    estado_civil character varying(50) NOT NULL,
    id_grado smallint NOT NULL,
    numero_hijos smallint NOT NULL,
    id_etnia smallint NOT NULL,
    id_religion smallint NOT NULL,
    voluntariado boolean NOT NULL,
    fecha_inscripcion timestamp without time zone NOT NULL,
    trabajo boolean NOT NULL,
    id_pais integer NOT NULL,
    id_estado integer NOT NULL,
    id_usuario numeric NOT NULL
);


ALTER TABLE public.ficha_personal OWNER TO postgres;

--
-- TOC entry 3121 (class 0 OID 0)
-- Dependencies: 209
-- Name: COLUMN ficha_personal.id_ficha; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.ficha_personal.id_ficha IS 'clave primaria de la ficha personal';


--
-- TOC entry 3122 (class 0 OID 0)
-- Dependencies: 209
-- Name: COLUMN ficha_personal.edad; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.ficha_personal.edad IS 'en este campo se guardara la edad del usuario';


--
-- TOC entry 3123 (class 0 OID 0)
-- Dependencies: 209
-- Name: COLUMN ficha_personal.genero; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.ficha_personal.genero IS 'genero de la persona';


--
-- TOC entry 3124 (class 0 OID 0)
-- Dependencies: 209
-- Name: COLUMN ficha_personal.fecha_nacimiento; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.ficha_personal.fecha_nacimiento IS 'fecha de nacimiento de la persona';


--
-- TOC entry 3125 (class 0 OID 0)
-- Dependencies: 209
-- Name: COLUMN ficha_personal.ciudad; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.ficha_personal.ciudad IS 'nombre de la ciudad';


--
-- TOC entry 3126 (class 0 OID 0)
-- Dependencies: 209
-- Name: COLUMN ficha_personal.direccion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.ficha_personal.direccion IS 'direccion de la persona';


--
-- TOC entry 3127 (class 0 OID 0)
-- Dependencies: 209
-- Name: COLUMN ficha_personal.estado_civil; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.ficha_personal.estado_civil IS 'estado de la persona';


--
-- TOC entry 3128 (class 0 OID 0)
-- Dependencies: 209
-- Name: COLUMN ficha_personal.id_grado; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.ficha_personal.id_grado IS 'nivel de educacion de la persona';


--
-- TOC entry 3129 (class 0 OID 0)
-- Dependencies: 209
-- Name: COLUMN ficha_personal.numero_hijos; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.ficha_personal.numero_hijos IS 'numero de hijos de la persona';


--
-- TOC entry 3130 (class 0 OID 0)
-- Dependencies: 209
-- Name: COLUMN ficha_personal.id_etnia; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.ficha_personal.id_etnia IS 'clave foranea de la tabla etnia';


--
-- TOC entry 3131 (class 0 OID 0)
-- Dependencies: 209
-- Name: COLUMN ficha_personal.id_religion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.ficha_personal.id_religion IS 'clave foranea de la tabla religion';


--
-- TOC entry 3132 (class 0 OID 0)
-- Dependencies: 209
-- Name: COLUMN ficha_personal.voluntariado; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.ficha_personal.voluntariado IS 'personas que deseen realizar voluntariado';


--
-- TOC entry 3133 (class 0 OID 0)
-- Dependencies: 209
-- Name: COLUMN ficha_personal.fecha_inscripcion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.ficha_personal.fecha_inscripcion IS 'fecha de inscripcion del participante por parte del sistema';


--
-- TOC entry 3134 (class 0 OID 0)
-- Dependencies: 209
-- Name: COLUMN ficha_personal.trabajo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.ficha_personal.trabajo IS 'personas que cuenta con trabajo en la tabla de ficha';


--
-- TOC entry 3135 (class 0 OID 0)
-- Dependencies: 209
-- Name: COLUMN ficha_personal.id_pais; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.ficha_personal.id_pais IS 'clave foranea de la tabla pais_estado';


--
-- TOC entry 3136 (class 0 OID 0)
-- Dependencies: 209
-- Name: COLUMN ficha_personal.id_estado; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.ficha_personal.id_estado IS 'clave foranea del estado';


--
-- TOC entry 3137 (class 0 OID 0)
-- Dependencies: 209
-- Name: COLUMN ficha_personal.id_usuario; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.ficha_personal.id_usuario IS 'clave foranea de la tabla Usuario';


--
-- TOC entry 210 (class 1259 OID 16430)
-- Name: grado_estudio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.grado_estudio (
    id_grado smallint NOT NULL,
    nombre_grado character varying(150) NOT NULL,
    descripcion text NOT NULL
);


ALTER TABLE public.grado_estudio OWNER TO postgres;

--
-- TOC entry 3138 (class 0 OID 0)
-- Dependencies: 210
-- Name: TABLE grado_estudio; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.grado_estudio IS 'grados de estudio de la ficha personal';


--
-- TOC entry 3139 (class 0 OID 0)
-- Dependencies: 210
-- Name: COLUMN grado_estudio.descripcion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.grado_estudio.descripcion IS 'descripcion del grado';


--
-- TOC entry 211 (class 1259 OID 16436)
-- Name: modulo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.modulo (
    id_modulo numeric(4,0) NOT NULL,
    nombre character varying(100) NOT NULL,
    direccion character varying(300) NOT NULL,
    descripcion character varying(500) NOT NULL
);


ALTER TABLE public.modulo OWNER TO postgres;

--
-- TOC entry 3140 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN modulo.id_modulo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.modulo.id_modulo IS 'clave primaria del modulo';


--
-- TOC entry 3141 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN modulo.nombre; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.modulo.nombre IS 'nombre del modulo';


--
-- TOC entry 3142 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN modulo.descripcion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.modulo.descripcion IS 'descripcion de la tabla';


--
-- TOC entry 212 (class 1259 OID 16442)
-- Name: opcion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.opcion (
    id_opcion numeric NOT NULL,
    opc_descripcion character varying(100) NOT NULL,
    valor integer,
    id_cuestionario smallint NOT NULL
);


ALTER TABLE public.opcion OWNER TO postgres;

--
-- TOC entry 3143 (class 0 OID 0)
-- Dependencies: 212
-- Name: COLUMN opcion.id_opcion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.opcion.id_opcion IS 'PK_ tabla opción';


--
-- TOC entry 3144 (class 0 OID 0)
-- Dependencies: 212
-- Name: COLUMN opcion.opc_descripcion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.opcion.opc_descripcion IS 'variable de respuesta de pregunta';


--
-- TOC entry 3145 (class 0 OID 0)
-- Dependencies: 212
-- Name: COLUMN opcion.id_cuestionario; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.opcion.id_cuestionario IS 'fk de la tabla cuestionario para las opciones';


--
-- TOC entry 213 (class 1259 OID 16448)
-- Name: organizacion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.organizacion (
    id_organizacion smallint NOT NULL,
    nombre_organizacion character varying(150) NOT NULL,
    telefono_organizacion character varying(15) NOT NULL,
    descripcion_organizacion text NOT NULL
);


ALTER TABLE public.organizacion OWNER TO postgres;

--
-- TOC entry 3146 (class 0 OID 0)
-- Dependencies: 213
-- Name: TABLE organizacion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.organizacion IS 'tablas de organizaciones que trabajan con la metodología MIA.';


--
-- TOC entry 3147 (class 0 OID 0)
-- Dependencies: 213
-- Name: COLUMN organizacion.id_organizacion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.organizacion.id_organizacion IS 'clave primaria de la tabla organizacion';


--
-- TOC entry 3148 (class 0 OID 0)
-- Dependencies: 213
-- Name: COLUMN organizacion.nombre_organizacion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.organizacion.nombre_organizacion IS 'nombre de la organizacion';


--
-- TOC entry 3149 (class 0 OID 0)
-- Dependencies: 213
-- Name: COLUMN organizacion.telefono_organizacion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.organizacion.telefono_organizacion IS 'numero de contacto de la organizacion ';


--
-- TOC entry 3150 (class 0 OID 0)
-- Dependencies: 213
-- Name: COLUMN organizacion.descripcion_organizacion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.organizacion.descripcion_organizacion IS 'descripcion de la organizacion ';


--
-- TOC entry 214 (class 1259 OID 16454)
-- Name: organizacion_fichapersonal; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.organizacion_fichapersonal (
    id_organizacion_ficha numeric NOT NULL,
    id_organizacion smallint NOT NULL,
    id_ficha numeric NOT NULL
);


ALTER TABLE public.organizacion_fichapersonal OWNER TO postgres;

--
-- TOC entry 3151 (class 0 OID 0)
-- Dependencies: 214
-- Name: TABLE organizacion_fichapersonal; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.organizacion_fichapersonal IS 'tabla intermedia de personas de la ficha personal del usuario.';


--
-- TOC entry 3152 (class 0 OID 0)
-- Dependencies: 214
-- Name: COLUMN organizacion_fichapersonal.id_organizacion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.organizacion_fichapersonal.id_organizacion IS 'clave foranea de la tabla organizacion_fichapersonal';


--
-- TOC entry 3153 (class 0 OID 0)
-- Dependencies: 214
-- Name: COLUMN organizacion_fichapersonal.id_ficha; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.organizacion_fichapersonal.id_ficha IS 'clave foranea de la tabla organizacion_ficha';


--
-- TOC entry 215 (class 1259 OID 16460)
-- Name: pais_estado; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pais_estado (
    id_pais_estado integer NOT NULL,
    nombre character varying(100) NOT NULL,
    id_padre_pais integer
);


ALTER TABLE public.pais_estado OWNER TO postgres;

--
-- TOC entry 3154 (class 0 OID 0)
-- Dependencies: 215
-- Name: TABLE pais_estado; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.pais_estado IS 'paises del mundo';


--
-- TOC entry 3155 (class 0 OID 0)
-- Dependencies: 215
-- Name: COLUMN pais_estado.id_pais_estado; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.pais_estado.id_pais_estado IS 'clave perimaria de la tabla Pais';


--
-- TOC entry 3156 (class 0 OID 0)
-- Dependencies: 215
-- Name: COLUMN pais_estado.id_padre_pais; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.pais_estado.id_padre_pais IS 'clave foranea de la tabla pais_estado que hace referencia al pais';


--
-- TOC entry 216 (class 1259 OID 16463)
-- Name: pregunta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pregunta (
    id_pregunta integer NOT NULL,
    descripcion character varying(250) NOT NULL
);


ALTER TABLE public.pregunta OWNER TO postgres;

--
-- TOC entry 3157 (class 0 OID 0)
-- Dependencies: 216
-- Name: COLUMN pregunta.id_pregunta; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.pregunta.id_pregunta IS 'pk de la tabla pregunta';


--
-- TOC entry 3158 (class 0 OID 0)
-- Dependencies: 216
-- Name: COLUMN pregunta.descripcion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.pregunta.descripcion IS 'Descripcion de la pregunta formulada por el administrador';


--
-- TOC entry 217 (class 1259 OID 16466)
-- Name: proyecto_investigacion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.proyecto_investigacion (
    id_proyecto_investigacion numeric NOT NULL,
    nombre_proyecto character varying(150) NOT NULL,
    proyecto_descripcion text NOT NULL,
    area_id smallint NOT NULL,
    estado_proyecto boolean NOT NULL
);


ALTER TABLE public.proyecto_investigacion OWNER TO postgres;

--
-- TOC entry 3159 (class 0 OID 0)
-- Dependencies: 217
-- Name: TABLE proyecto_investigacion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.proyecto_investigacion IS 'tabla de proyectos realizados por la metodología MIA';


--
-- TOC entry 3160 (class 0 OID 0)
-- Dependencies: 217
-- Name: COLUMN proyecto_investigacion.id_proyecto_investigacion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.proyecto_investigacion.id_proyecto_investigacion IS 'clave primaria de ta tablaproyecto de investigación.';


--
-- TOC entry 3161 (class 0 OID 0)
-- Dependencies: 217
-- Name: COLUMN proyecto_investigacion.nombre_proyecto; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.proyecto_investigacion.nombre_proyecto IS 'nombre del proyecto';


--
-- TOC entry 3162 (class 0 OID 0)
-- Dependencies: 217
-- Name: COLUMN proyecto_investigacion.proyecto_descripcion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.proyecto_investigacion.proyecto_descripcion IS 'descripcion del proyecto de investigacion.';


--
-- TOC entry 3163 (class 0 OID 0)
-- Dependencies: 217
-- Name: COLUMN proyecto_investigacion.area_id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.proyecto_investigacion.area_id IS 'clave foranea de la tabla area de investigación';


--
-- TOC entry 3164 (class 0 OID 0)
-- Dependencies: 217
-- Name: COLUMN proyecto_investigacion.estado_proyecto; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.proyecto_investigacion.estado_proyecto IS 'estado del proyecto si se encuentra Activo o inactivo';


--
-- TOC entry 218 (class 1259 OID 16472)
-- Name: religion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.religion (
    id_religion smallint NOT NULL,
    descripcion character varying(100) NOT NULL
);


ALTER TABLE public.religion OWNER TO postgres;

--
-- TOC entry 3165 (class 0 OID 0)
-- Dependencies: 218
-- Name: TABLE religion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.religion IS 'religiones en el mundo';


--
-- TOC entry 3166 (class 0 OID 0)
-- Dependencies: 218
-- Name: COLUMN religion.id_religion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.religion.id_religion IS 'id de la religion';


--
-- TOC entry 3167 (class 0 OID 0)
-- Dependencies: 218
-- Name: COLUMN religion.descripcion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.religion.descripcion IS 'nombre de la religion';


--
-- TOC entry 219 (class 1259 OID 16475)
-- Name: reporte; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reporte (
    id_reporte numeric NOT NULL,
    resultado character varying(100) NOT NULL,
    id_dimension smallint NOT NULL,
    fecha date,
    id_usuario numeric NOT NULL
);


ALTER TABLE public.reporte OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16481)
-- Name: respuesta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.respuesta (
    id_respuesta numeric NOT NULL,
    pregunta_respuesta text,
    id_usuario numeric NOT NULL,
    fecha date
);


ALTER TABLE public.respuesta OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16487)
-- Name: rol; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rol (
    id_rol integer NOT NULL,
    nombre_rol character varying(100),
    activo_rol boolean
);


ALTER TABLE public.rol OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16490)
-- Name: seq_area_investigacion; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_area_investigacion
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.seq_area_investigacion OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16492)
-- Name: seq_cuestionario; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_cuestionario
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.seq_cuestionario OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16494)
-- Name: seq_curso; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_curso
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.seq_curso OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16496)
-- Name: seq_curso_modulo; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_curso_modulo
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.seq_curso_modulo OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 16498)
-- Name: seq_dimension; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_dimension
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.seq_dimension OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 16500)
-- Name: seq_dimension_pregunta; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_dimension_pregunta
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.seq_dimension_pregunta OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 16502)
-- Name: seq_etnia; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_etnia
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.seq_etnia OWNER TO postgres;

--
-- TOC entry 3168 (class 0 OID 0)
-- Dependencies: 228
-- Name: SEQUENCE seq_etnia; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON SEQUENCE public.seq_etnia IS 'sequencia de la tabla etnia ';


--
-- TOC entry 229 (class 1259 OID 16504)
-- Name: seq_ficha_personal; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_ficha_personal
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.seq_ficha_personal OWNER TO postgres;

--
-- TOC entry 3169 (class 0 OID 0)
-- Dependencies: 229
-- Name: SEQUENCE seq_ficha_personal; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON SEQUENCE public.seq_ficha_personal IS 'sequencia de la tabla ficha persona';


--
-- TOC entry 230 (class 1259 OID 16506)
-- Name: seq_grado_estudio; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_grado_estudio
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.seq_grado_estudio OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 16508)
-- Name: seq_modulo; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_modulo
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.seq_modulo OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 16510)
-- Name: seq_opcion; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_opcion
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.seq_opcion OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 16512)
-- Name: seq_organizacion; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_organizacion
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.seq_organizacion OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 16514)
-- Name: seq_organizacion_fichapersonal; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_organizacion_fichapersonal
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.seq_organizacion_fichapersonal OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 16516)
-- Name: seq_pais_estado; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_pais_estado
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.seq_pais_estado OWNER TO postgres;

--
-- TOC entry 3170 (class 0 OID 0)
-- Dependencies: 235
-- Name: SEQUENCE seq_pais_estado; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON SEQUENCE public.seq_pais_estado IS 'sequencia de la tabla pais estado';


--
-- TOC entry 236 (class 1259 OID 16518)
-- Name: seq_pregunta; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_pregunta
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.seq_pregunta OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 16520)
-- Name: seq_proyecto_investigacion; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_proyecto_investigacion
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.seq_proyecto_investigacion OWNER TO postgres;

--
-- TOC entry 238 (class 1259 OID 16522)
-- Name: seq_religion; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_religion
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.seq_religion OWNER TO postgres;

--
-- TOC entry 3171 (class 0 OID 0)
-- Dependencies: 238
-- Name: SEQUENCE seq_religion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON SEQUENCE public.seq_religion IS 'sequencia de la tabla religion';


--
-- TOC entry 239 (class 1259 OID 16524)
-- Name: seq_reporte; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_reporte
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.seq_reporte OWNER TO postgres;

--
-- TOC entry 240 (class 1259 OID 16526)
-- Name: seq_respuesta; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_respuesta
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.seq_respuesta OWNER TO postgres;

--
-- TOC entry 241 (class 1259 OID 16528)
-- Name: seq_rol; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_rol
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.seq_rol OWNER TO postgres;

--
-- TOC entry 3172 (class 0 OID 0)
-- Dependencies: 241
-- Name: SEQUENCE seq_rol; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON SEQUENCE public.seq_rol IS 'sequencia de la tabla rol';


--
-- TOC entry 242 (class 1259 OID 16530)
-- Name: seq_usuario; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_usuario
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.seq_usuario OWNER TO postgres;

--
-- TOC entry 3173 (class 0 OID 0)
-- Dependencies: 242
-- Name: SEQUENCE seq_usuario; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON SEQUENCE public.seq_usuario IS 'sequencia de la tabla usuario';


--
-- TOC entry 243 (class 1259 OID 16532)
-- Name: seq_usuario_curso; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_usuario_curso
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.seq_usuario_curso OWNER TO postgres;

--
-- TOC entry 244 (class 1259 OID 16534)
-- Name: seq_usuario_interes_area; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_usuario_interes_area
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.seq_usuario_interes_area OWNER TO postgres;

--
-- TOC entry 245 (class 1259 OID 16536)
-- Name: seq_usuario_proyecto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_usuario_proyecto
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.seq_usuario_proyecto OWNER TO postgres;

--
-- TOC entry 246 (class 1259 OID 16538)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    id_usuario numeric NOT NULL,
    nombres character varying(100) NOT NULL,
    apellidos character varying(100) NOT NULL,
    password character varying(300) NOT NULL,
    id_rol integer NOT NULL,
    ci character varying(20) NOT NULL,
    correo character varying(150) NOT NULL,
    activo boolean NOT NULL
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 3174 (class 0 OID 0)
-- Dependencies: 246
-- Name: TABLE usuario; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.usuario IS 'Tabla usuario de registro.';


--
-- TOC entry 3175 (class 0 OID 0)
-- Dependencies: 246
-- Name: COLUMN usuario.id_usuario; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.usuario.id_usuario IS 'clave primaria de la tabla usuario';


--
-- TOC entry 3176 (class 0 OID 0)
-- Dependencies: 246
-- Name: COLUMN usuario.nombres; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.usuario.nombres IS 'nombres del usuario';


--
-- TOC entry 3177 (class 0 OID 0)
-- Dependencies: 246
-- Name: COLUMN usuario.apellidos; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.usuario.apellidos IS 'apellidos de los usuarios';


--
-- TOC entry 3178 (class 0 OID 0)
-- Dependencies: 246
-- Name: COLUMN usuario.password; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.usuario.password IS 'clave de ingreso del usuario';


--
-- TOC entry 3179 (class 0 OID 0)
-- Dependencies: 246
-- Name: COLUMN usuario.id_rol; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.usuario.id_rol IS 'clave foranea de la tabla rol';


--
-- TOC entry 3180 (class 0 OID 0)
-- Dependencies: 246
-- Name: COLUMN usuario.ci; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.usuario.ci IS 'identificación del usuario son unicos';


--
-- TOC entry 3181 (class 0 OID 0)
-- Dependencies: 246
-- Name: COLUMN usuario.correo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.usuario.correo IS 'correo del usuario ser unico';


--
-- TOC entry 3182 (class 0 OID 0)
-- Dependencies: 246
-- Name: COLUMN usuario.activo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.usuario.activo IS 'si el usuario se encuentra activo';


--
-- TOC entry 247 (class 1259 OID 16544)
-- Name: usuario_curso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario_curso (
    id_usuariocurso numeric NOT NULL,
    id_usuario numeric NOT NULL,
    id_curso smallint NOT NULL,
    avance numeric(3,0) NOT NULL,
    modulorealizados character varying(50) NOT NULL
);


ALTER TABLE public.usuario_curso OWNER TO postgres;

--
-- TOC entry 3183 (class 0 OID 0)
-- Dependencies: 247
-- Name: TABLE usuario_curso; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.usuario_curso IS 'Relacion de usuario y cursos asignados por este.';


--
-- TOC entry 3184 (class 0 OID 0)
-- Dependencies: 247
-- Name: COLUMN usuario_curso.id_usuariocurso; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.usuario_curso.id_usuariocurso IS 'clave primaria de la tabla';


--
-- TOC entry 3185 (class 0 OID 0)
-- Dependencies: 247
-- Name: COLUMN usuario_curso.id_usuario; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.usuario_curso.id_usuario IS 'clave foranea de la tabla usuario';


--
-- TOC entry 3186 (class 0 OID 0)
-- Dependencies: 247
-- Name: COLUMN usuario_curso.id_curso; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.usuario_curso.id_curso IS 'clave foranea de la tabla cursos';


--
-- TOC entry 3187 (class 0 OID 0)
-- Dependencies: 247
-- Name: COLUMN usuario_curso.avance; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.usuario_curso.avance IS 'avance del curso';


--
-- TOC entry 3188 (class 0 OID 0)
-- Dependencies: 247
-- Name: COLUMN usuario_curso.modulorealizados; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.usuario_curso.modulorealizados IS 'cantidad de módulos terminados';


--
-- TOC entry 248 (class 1259 OID 16550)
-- Name: usuario_interes_area; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario_interes_area (
    id_interes_area numeric NOT NULL,
    area_id smallint NOT NULL,
    id_ficha numeric NOT NULL
);


ALTER TABLE public.usuario_interes_area OWNER TO postgres;

--
-- TOC entry 3189 (class 0 OID 0)
-- Dependencies: 248
-- Name: COLUMN usuario_interes_area.id_ficha; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.usuario_interes_area.id_ficha IS 'clave foranea de la tabla usuario_interes_area';


--
-- TOC entry 249 (class 1259 OID 16556)
-- Name: usuario_proyecto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario_proyecto (
    id_usuario_proyecto numeric NOT NULL,
    id_proyecto_investigacion numeric NOT NULL,
    id_ficha numeric NOT NULL,
    id_organizacion smallint NOT NULL,
    fecha_de date NOT NULL,
    fecha_hasta date NOT NULL
);


ALTER TABLE public.usuario_proyecto OWNER TO postgres;

--
-- TOC entry 3190 (class 0 OID 0)
-- Dependencies: 249
-- Name: TABLE usuario_proyecto; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.usuario_proyecto IS 'proyectos de los usuarios que se encuentren realizando';


--
-- TOC entry 3191 (class 0 OID 0)
-- Dependencies: 249
-- Name: COLUMN usuario_proyecto.id_usuario_proyecto; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.usuario_proyecto.id_usuario_proyecto IS 'Id del proyecto y usuario realizando estos.';


--
-- TOC entry 3192 (class 0 OID 0)
-- Dependencies: 249
-- Name: COLUMN usuario_proyecto.id_proyecto_investigacion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.usuario_proyecto.id_proyecto_investigacion IS 'clave foranea de la tabla proyecto investigacion';


--
-- TOC entry 3193 (class 0 OID 0)
-- Dependencies: 249
-- Name: COLUMN usuario_proyecto.id_ficha; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.usuario_proyecto.id_ficha IS 'clave foranea de la tabla usuario_proyecto';


--
-- TOC entry 3194 (class 0 OID 0)
-- Dependencies: 249
-- Name: COLUMN usuario_proyecto.id_organizacion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.usuario_proyecto.id_organizacion IS 'clave foranea del usuario_Proyecto';


--
-- TOC entry 3052 (class 0 OID 16394)
-- Dependencies: 202
-- Data for Name: area_investigacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.area_investigacion (area_id, nombre_investigacion, descripcion_investigacion) FROM stdin;
1	Social	relacionado al entorno
\.


--
-- TOC entry 3053 (class 0 OID 16400)
-- Dependencies: 203
-- Data for Name: cuestionario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cuestionario (id_cuestionario, descripcion) FROM stdin;
1	Inteligencia Emocional
2	Nivel de estrés
3	Perfil de Liderazgo global
4	CUSEOA
\.


--
-- TOC entry 3054 (class 0 OID 16403)
-- Dependencies: 204
-- Data for Name: curso; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.curso (id_curso, nombre, descripcion, avance, secuencia) FROM stdin;
1	Mindfulness Into Action	En neuro-ciencias se ha descubierto que de un 95 a 98% de nuestras acciones son subconscientes (desconocidas). Nuestros acciones desconocidas son nuestros modelos mentales que están incrustados en nuestro subconsciente, por lo que son difíciles de identificar. Y por ello, le estamos proponiendo la metodología MIA para identificar y cambiar estas conductas inconscientes de auto-sabotaje.	5	\N
2	prueba 5	esto es una prueba.	3	\N
\.


--
-- TOC entry 3055 (class 0 OID 16409)
-- Dependencies: 205
-- Data for Name: curso_modulo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.curso_modulo (id_curso_modulo, id_modulo, id_curso, orden_curso) FROM stdin;
1	0	1	1
2	1	1	2
3	1	1	3
4	1	2	4
5	1	1	5
6	1	1	6
7	0	2	\N
8	1	2	3
\.


--
-- TOC entry 3056 (class 0 OID 16412)
-- Dependencies: 206
-- Data for Name: dimension; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.dimension (id_dimension, descripcion, id_cuestionario) FROM stdin;
1	Auto Conciencia	1
2	Auto Motivación	1
3	Control de las Emociones	1
4	Inter Personal	1
5	Asesoría Emocional	1
6	Tipo Estrés	2
7	Para tomar decisiones...	3
8	Creo que la función del líder es...	3
9	Cuando las cosas no van bien....	3
10	Me gusta actuar....	3
11	Las personas me describen....	3
12	Visualizo el horizonte de trabajo....	3
\.


--
-- TOC entry 3057 (class 0 OID 16415)
-- Dependencies: 207
-- Data for Name: dimension_pregunta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.dimension_pregunta (id_pregunta_dimension, id_dimension, id_pregunta) FROM stdin;
1	1	1
2	1	6
3	1	11
4	1	12
5	1	13
6	1	14
7	1	15
8	1	17
9	1	18
10	1	19
11	1	20
12	1	21
13	2	7
14	2	22
15	2	23
16	2	25
17	2	26
18	2	27
19	2	28
20	3	1
21	3	2
22	3	3
23	3	4
24	3	5
25	3	7
26	3	9
27	3	10
28	3	13
29	3	27
30	4	8
31	4	10
32	4	16
33	4	19
34	4	20
35	4	29
36	4	30
37	4	31
38	4	32
39	4	33
40	4	34
41	4	35
42	4	36
43	4	37
44	4	38
45	4	39
46	4	42
47	4	43
48	4	44
49	5	8
50	5	10
51	5	16
52	5	18
53	5	34
54	5	35
55	5	37
56	5	38
57	5	39
58	5	40
59	5	41
60	5	44
61	6	45
62	6	46
63	6	47
64	6	48
65	6	49
66	6	50
67	6	51
68	6	52
69	6	53
70	6	54
71	6	55
72	6	56
73	6	57
74	6	58
75	6	59
76	6	60
77	7	62
78	7	63
79	7	64
80	7	65
81	7	66
82	7	67
83	7	68
84	8	70
85	8	71
86	8	72
87	8	73
88	8	74
89	8	75
90	8	76
91	9	78
92	9	79
93	9	80
94	9	81
95	9	82
96	9	83
97	9	84
98	10	86
99	10	87
100	10	88
101	10	89
102	10	90
103	10	91
104	10	92
106	11	94
107	11	95
108	11	96
109	11	97
110	11	98
111	11	99
112	11	100
113	12	102
114	12	103
115	12	104
116	12	105
117	12	106
118	12	107
119	12	108
\.


--
-- TOC entry 3058 (class 0 OID 16421)
-- Dependencies: 208
-- Data for Name: etnia; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.etnia (id_etnia, descripcion) FROM stdin;
2	Mestizo
1	Afro Ecuatoriano
\.


--
-- TOC entry 3059 (class 0 OID 16424)
-- Dependencies: 209
-- Data for Name: ficha_personal; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ficha_personal (id_ficha, edad, genero, fecha_nacimiento, ciudad, direccion, telefono, celular, estado_civil, id_grado, numero_hijos, id_etnia, id_religion, voluntariado, fecha_inscripcion, trabajo, id_pais, id_estado, id_usuario) FROM stdin;
3	1	femenino	2019-06-11	Ibarra	Ibarra	0958454861	0917584965	soltero/a	1	1	2	2	f	2019-12-14 12:06:38.333	f	103	261	6
5	28	masculino	1992-03-26	Ibarra	5 de Junio y Via Urcuqui	0991758496	0991758496	soltero/a	1	0	2	2	t	2020-02-17 00:40:22.716	f	103	261	7
6	1	masculino	2019-06-12	Ibarra	5 dejunio	0991758496	0991758496	soltero/a	1	1	2	2	f	2020-03-03 21:25:50.629	t	103	261	8
\.


--
-- TOC entry 3060 (class 0 OID 16430)
-- Dependencies: 210
-- Data for Name: grado_estudio; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.grado_estudio (id_grado, nombre_grado, descripcion) FROM stdin;
1	Bachillerato General Unificado	 Se lo denomina a los últimos 3 años de educación (desde 1º a 3º año).
\.


--
-- TOC entry 3061 (class 0 OID 16436)
-- Dependencies: 211
-- Data for Name: modulo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.modulo (id_modulo, nombre, direccion, descripcion) FROM stdin;
0	Introducción Metodología MIA	https://www.edjet.com/course/80310186	Proceso de una semana
1	Autosabotaje y Modelos Mentales	https://edjet.com/course/e2aaa9b6	Segunda semana
\.


--
-- TOC entry 3062 (class 0 OID 16442)
-- Dependencies: 212
-- Data for Name: opcion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.opcion (id_opcion, opc_descripcion, valor, id_cuestionario) FROM stdin;
1	Nunca	1	1
2	Raramente	2	1
3	Casi raramente	3	1
4	Ocasionalmente	4	1
5	Casi frecuentemente	5	1
6	Frecuentemente	6	1
7	Muy frecuentemente	7	1
8	Siempre	1	2
9	Casi Siempre	2	2
10	Frecuentemente	3	2
11	Casi Nunca	4	2
12	Nunca	5	2
13	Siempre	1	3
14	Casi siempre	2	3
15	Usualmente	3	3
16	Ocasionalmente	4	3
17	Usualmente no	5	3
18	Casi Nunca	6	3
19	Nunca	7	3
20	Totalmente en Desacuerdo	1	4
\.


--
-- TOC entry 3063 (class 0 OID 16448)
-- Dependencies: 213
-- Data for Name: organizacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.organizacion (id_organizacion, nombre_organizacion, telefono_organizacion, descripcion_organizacion) FROM stdin;
1	Nueva Vida	091758496	Asociacion de no  videntes
\.


--
-- TOC entry 3064 (class 0 OID 16454)
-- Dependencies: 214
-- Data for Name: organizacion_fichapersonal; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.organizacion_fichapersonal (id_organizacion_ficha, id_organizacion, id_ficha) FROM stdin;
\.


--
-- TOC entry 3065 (class 0 OID 16460)
-- Dependencies: 215
-- Data for Name: pais_estado; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pais_estado (id_pais_estado, nombre, id_padre_pais) FROM stdin;
1	Australia	\N
2	Austria	\N
3	Azerbaiyán	\N
4	Anguilla	\N
5	Argentina	\N
6	Armenia	\N
7	Bielorrusia	\N
8	Belice	\N
9	Bélgica	\N
10	Bermudas	\N
11	Bulgaria	\N
12	Brasil	\N
13	Reino Unido	\N
14	Hungría	\N
15	Vietnam	\N
16	Haiti	\N
17	Guadalupe	\N
18	Alemania	\N
19	Países Bajos, Holanda	\N
20	Grecia	\N
21	Georgia	\N
22	Dinamarca	\N
23	Egipto	\N
24	Israel	\N
25	India	\N
26	Irán	\N
27	Irlanda	\N
28	España	\N
29	Italia	\N
30	Kazajstán	\N
31	Camerún	\N
32	Canadá	\N
33	Chipre	\N
34	Kirguistán	\N
35	China	\N
36	Costa Rica	\N
37	Kuwait	\N
38	Letonia	\N
39	Libia	\N
40	Lituania	\N
41	Luxemburgo	\N
42	México	\N
43	Moldavia	\N
44	Mónaco	\N
45	Nueva Zelanda	\N
46	Noruega	\N
47	Polonia	\N
48	Portugal	\N
49	Reunión	\N
50	Rusia	\N
51	El Salvador	\N
52	Eslovaquia	\N
53	Eslovenia	\N
54	Surinam	\N
55	Estados Unidos	\N
56	Tadjikistan	\N
57	Turkmenistan	\N
58	Islas Turcas y Caicos	\N
59	Turquía	\N
60	Uganda	\N
61	Uzbekistán	\N
62	Ucrania	\N
63	Finlandia	\N
64	Francia	\N
65	República Checa	\N
66	Suiza	\N
67	Suecia	\N
68	Estonia	\N
69	Corea del Sur	\N
70	Japón	\N
71	Croacia	\N
72	Rumanía	\N
73	Hong Kong	\N
74	Indonesia	\N
75	Jordania	\N
76	Malasia	\N
77	Singapur	\N
78	Taiwan	\N
79	Bosnia y Herzegovina	\N
80	Bahamas	\N
81	Chile	\N
82	Colombia	\N
83	Islandia	\N
84	Corea del Norte	\N
85	Macedonia	\N
86	Malta	\N
87	Pakistán	\N
88	Papúa-Nueva Guinea	\N
89	Perú	\N
90	Filipinas	\N
91	Arabia Saudita	\N
92	Tailandia	\N
93	Emiratos árabes Unidos	\N
94	Groenlandia	\N
95	Venezuela	\N
96	Zimbabwe	\N
97	Kenia	\N
98	Algeria	\N
99	Líbano	\N
100	Botsuana	\N
101	Tanzania	\N
102	Namibia	\N
103	Ecuador	\N
104	Marruecos	\N
105	Ghana	\N
106	Siria	\N
107	Nepal	\N
108	Mauritania	\N
109	Seychelles	\N
110	Paraguay	\N
111	Uruguay	\N
112	Congo (Brazzaville)	\N
113	Cuba	\N
114	Albania	\N
115	Nigeria	\N
116	Zambia	\N
117	Mozambique	\N
119	Angola	\N
120	Sri Lanka	\N
121	Etiopía	\N
122	Túnez	\N
123	Bolivia	\N
124	Panamá	\N
125	Malawi	\N
126	Liechtenstein	\N
127	Bahrein	\N
128	Barbados	\N
130	Chad	\N
131	Man, Isla de	\N
132	Jamaica	\N
133	Malí	\N
134	Madagascar	\N
135	Senegal	\N
136	Togo	\N
137	Honduras	\N
138	República Dominicana	\N
139	Mongolia	\N
140	Irak	\N
141	Sudáfrica	\N
142	Aruba	\N
143	Gibraltar	\N
144	Afganistán	\N
145	Andorra	\N
147	Antigua y Barbuda	\N
149	Bangladesh	\N
151	Benín	\N
152	Bután	\N
154	Islas Virgenes Británicas	\N
155	Brunéi	\N
156	Burkina Faso	\N
157	Burundi	\N
158	Camboya	\N
159	Cabo Verde	\N
164	Comores	\N
165	Congo (Kinshasa)	\N
166	Cook, Islas	\N
168	Costa de Marfil	\N
169	Djibouti, Yibuti	\N
171	Timor Oriental	\N
172	Guinea Ecuatorial	\N
173	Eritrea	\N
175	Feroe, Islas	\N
176	Fiyi	\N
178	Polinesia Francesa	\N
180	Gabón	\N
181	Gambia	\N
184	Granada	\N
185	Guatemala	\N
186	Guernsey	\N
187	Guinea	\N
188	Guinea-Bissau	\N
189	Guyana	\N
193	Jersey	\N
195	Kiribati	\N
196	Laos	\N
197	Lesotho	\N
198	Liberia	\N
200	Maldivas	\N
201	Martinica	\N
202	Mauricio	\N
205	Myanmar	\N
206	Nauru	\N
207	Antillas Holandesas	\N
208	Nueva Caledonia	\N
209	Nicaragua	\N
210	Níger	\N
212	Norfolk Island	\N
213	Omán	\N
215	Isla Pitcairn	\N
216	Qatar	\N
217	Ruanda	\N
218	Santa Elena	\N
219	San Cristobal y Nevis	\N
220	Santa Lucía	\N
221	San Pedro y Miquelón	\N
222	San Vincente y Granadinas	\N
223	Samoa	\N
224	San Marino	\N
225	San Tomé y Príncipe	\N
226	Serbia y Montenegro	\N
227	Sierra Leona	\N
228	Islas Salomón	\N
229	Somalia	\N
232	Sudán	\N
234	Swazilandia	\N
235	Tokelau	\N
236	Tonga	\N
237	Trinidad y Tobago	\N
239	Tuvalu	\N
240	Vanuatu	\N
241	Wallis y Futuna	\N
242	Sáhara Occidental	\N
243	Yemen	\N
246	Puerto Rico	\N
251	Azuay	103
252	Bolívar	103
253	Cañar	103
254	Carchi	103
255	Chimborazo	103
256	Cotopaxi	103
257	El Oro	103
258	Esmeraldas	103
259	Galápagos	103
260	Guayas	103
261	Imbabura	103
262	Loja	103
263	Los Ríos	103
264	Manabí	103
265	Morona-Santiago	103
266	Napo	103
267	Orellana	103
268	Pastaza	103
269	Pichincha	103
270	Santa Elena	103
271	Santo Domingo de los Tsáchilas	103
272	Sucumbíos	103
273	Tungurahua	103
274	Zamora-Chinchipe	103
\.


--
-- TOC entry 3066 (class 0 OID 16463)
-- Dependencies: 216
-- Data for Name: pregunta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pregunta (id_pregunta, descripcion) FROM stdin;
1	Identifica cambios fisiológicos en su cuerpo al instante.
2	Se relaja en situaciones de presión.                                                        
3	Puede actuar de modo productivo cuando se enfada.                                                        
4	Actúa de modo productivo cuando se siente ansioso.                                                        
5	Cuando está enfadado puede tranquilizarse rápido.                                                        
6	Puede asociar diferentes estados físicos con las emociones que tiene.                                                        
7	Controlo mis estados emocionales usando mi diálogo interior.                                                        
8	Puedo comunicar mis sentimientos fácilmente.                                                        
9	Tengo sentimientos negativos sin angustiarme.                                                        
10	Mantengo la calma cuando soy parte  del enfado de otros.                                                        
11	Generalmente sé cuándo tengo pensamientos negativos.                                                        
12	Generalmente sé cuándo mi discurso interior es positivo.                                                        
13	Generalmente sé cuando empiezo a enfadarme.                                                        
14	Generalmente sé cómo interpretar los acontecimientos.                                                         
15	Generalmente conozco qué sentimientos tengo.                                                        
16	Generalmente comunico con precisión lo que experimento.                                                        
17	Identifico la información que puede influir sobre mis interpretaciones.                                                        
18	Identifico mis cambios de humor.                                                        
19	Sé cuando puedo estar a la defensiva.                                                        
20	Medito el impacto que mi comportamiento tiene sobre los demás.                                                        
21	Cuando no me he comunicado bien, me doy cuenta.                                                        
22	Puedo ponerme proseguir cuando desee.                                                        
23	Me recupero rápido después de un contratiempo.                                                        
24	Completo tareas complejas dentro del tiempo previsto.                                                        
25	Puedo generar  energía positiva cuando realizo un trabajo poco interesante.                                                         
26	Soy Capaz de abandonar o cambiar hábitos inútiles.                                                         
27	Puedo desarrollar nuevas pautas de conducta más productivas.                                                        
28	Cumplo con mi palabra.                                                        
29	Resuelvo conflictos con facilidad.                                                        
30	Logro consensos con los demás con facilidad.                                                        
31	Puedo mediar en los conflictos con los demás.                                                        
32	Utilizo técnicas de comunicación eficaces.                                                        
33	Expreso los pensamientos de mi grupo.                                                        
34	Soy capaz de influir sobre los demás de forma directa o indirecta.                                                        
35	Fomento la confianza con los demás.                                                        
36	Construyo grupos de apoyo.                                                        
37	Hago que los demás se sientan bien                                                        
38	Proporciono apoyo y consejos a los demás cuando es necesario.                                                        
39	Determino con facilidad  los sentimientos de las personas.                                                        
40	Me doy cuenta de la angustia de los demás.                                                        
41	Ayudo a los demás a controlar sus emociones.                                                        
42	Muestro comprensión hacia los demás.                                                        
43	Puedo  entablar conversaciones íntimas con otras personas.                                                        
44	Soy capaz de ayudar a un grupo a controlar sus emociones.                                                        
45	Al  menos cuatro noches a la semana duermo de siete a ocho horas.
46	En 50 kilómetros a la redonda poseo por lo menos una familia en que pueda confiar.
47	Por lo menos 2 veces por semana hago ejercicios hasta sudar.
48	Fumo menos de media cajetilla de cigarrillos al día.
49	Tomo menos de cinco tragos (de bebidas alcohólicas) a la semana.
50	Tengo el peso apropiado para mi estatura.
51	Mis ingresos satisfacen mis gastos fundamentales
52	Asisto regularmente a actividades sociales.
53	Tengo una red (grupo) de amigos conocidos.
54	Tengo uno o más amigos a quienes puedo confiarle, mis problemas personales.
55	Tengo buena salud (es decir, mi vista, oído, dentadura, están en buenas \tcondiciones.
56	Converso regularmente sobre problemas domésticos(es decir, sobre tareas del hogar, dinero, problemas de la vida cotidiana) con las personas que conviven conmigo.
57	Por lo menos una vez a la semana hago algo para divertirme.
58	Soy capaz de organizar efectivamente  mi tiempo.
59	Tomo al menos tres tazas de café, té o refrescos al día.
60	Durante el día me dedico a mí mismo, al menos un rato de tranquilidad.
61	Para tomar decisiones…
62	Sugiero que nuestro equipo de trabajo se concentre en los objetivos y las metas.
63	Me aseguro que el equipo observe datos técnicos o información al respecto.
64	Me centro en el campo de acción.
65	Actúo sin pensar mucho.
66	 Busco que todos se involucren en la discusión para homologar estándares.
67	Veo las diferencias como la base de un posible cambio de dirección del equipo.
68	Lo dejo a la casualidad.
69	Creo que la función del líder es…
70	Ajustarse a su equipo.
71	Asegurarse de establecer una ética de represalias.
72	Tomar el control de las situaciones siempre.
73	Tolerar las diferencias individuales.
74	Asegurarse que todos se involucren.
75	Potenciar el crecimiento de los miembros de su equipo.
76	Valorar a los miembros según su mérito.
77	Cuando las cosas no van bien….
78	Comunico qué hacer a los demás.
79	Pierdo la paciencia y altero mi comportamiento.
80	Me centro en lo establecido y esperado.
81	Acepto solo las consideraciones de los expertos.
82	Me baso en los principios y el juicio.
83	Enfatizo en la escucha, la retroalimentación y la participación.
84	Rompo con los paradigmas del pensamiento y la acción.
85	Me gusta actuar….
86	Construyendo alternativas pero con tendencia a lo establecido.
87	 Sin respeto a las reglas.
88	Sigo fervientemente las reglas.
89	 Desafío las normas del grupo.
90	Veo las reglas como pérdida de libertad.
91	Reconozco la importancia de los principios y el juicio.
92	Perturbo los paradigmas del pensamiento y la acción.
93	Las personas me describen….
94	Colaborativo.
95	Impulsivo y dominante.
96	Con humor hostil y manipulador.
97	Pacificador de conflictos tanto internos como externos.
98	Perfeccionista.
99	Consciente de mis virtudes y defectos.
100	Analítico, metafórico.
101	Visualizo el horizonte de trabajo….
102	Según el contexto actual y su historia.
103	Valorando la inmediatez.
104	De manera temporal estratégica.
105	Sin planificación.
106	Según el contexto y la contingencia.
107	Por el momento histórico particular.
108	Y al tiempo y los acontecimientos como algo simbólico.
109	Los objetivos indican lo que se espera que sea aprendido.
110	El nivel de dificultad de los contenidos fue elevado para mis conocimientos previos.
111	El material teórico me ayudó a comprender los conceptos.
112	Las actividades han sido claras y significativas para mi aprendizaje.
113	El sistema informa sobre mi progreso.
114	Las pistas sobre los errores cometidos son inútiles.
115	El texto es conciso y preciso.
116	Los títulos son inadecuados , no se sabe cual es la acción que se debe realizar.
117	Las imágenes empleadas me ayudaron a aclarar los contenidos.
118	Me encontré perdido cuando recorría el recurso, no sabía dónde me encontraba.
119	Los videos y las animaciones me ayudaron a aclarar los contenidos.
120	La información está mal organizada.
121	En general, los colores y el diseño de todo el recurso son adecuados.
122	Recomendaría este recurso a otra persona.
\.


--
-- TOC entry 3067 (class 0 OID 16466)
-- Dependencies: 217
-- Data for Name: proyecto_investigacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.proyecto_investigacion (id_proyecto_investigacion, nombre_proyecto, proyecto_descripcion, area_id, estado_proyecto) FROM stdin;
1	Proyecto de nueva vida	Personas no videntes\r\n	1	t
\.


--
-- TOC entry 3068 (class 0 OID 16472)
-- Dependencies: 218
-- Data for Name: religion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.religion (id_religion, descripcion) FROM stdin;
2	Católica
\.


--
-- TOC entry 3069 (class 0 OID 16475)
-- Dependencies: 219
-- Data for Name: reporte; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.reporte (id_reporte, resultado, id_dimension, fecha, id_usuario) FROM stdin;
15	Seriamente vulnerable al estres.	6	2019-12-16	6
16	Tiene alto grado de capacidad en: Auto Conciencia con el porcentaje de 75.0%	1	2019-12-16	6
17	Tiene bajo grado de capacidad en: Auto Motivación con el porcentaje de 100.0%	2	2019-12-16	6
18	Tiene bajo grado de capacidad en: Control de las Emociones con el porcentaje de 100.0%	3	2019-12-16	6
19	Tiene bajo grado de capacidad en: Inter Personal con el porcentaje de 100.0%	4	2019-12-16	6
20	Tiene bajo grado de capacidad en: Asesoría Emocional con el porcentaje de 100.0%	5	2019-12-16	6
21	Seriamente vulnerable al estres.	6	2019-12-16	6
22	Tiene bajo grado de capacidad en: Auto Conciencia con el porcentaje de 100.0%	1	2020-02-25	7
23	Tiene alto grado de capacidad en: Auto Motivación con el porcentaje de 100.0%	2	2020-02-25	7
24	Tiene alto grado de capacidad en: Control de las Emociones con el porcentaje de 100.0%	3	2020-02-25	7
25	Tiene alto grado de capacidad en: Inter Personal con el porcentaje de 100.0%	4	2020-02-25	7
26	Tiene alto grado de capacidad en: Asesoría Emocional con el porcentaje de 100.0%	5	2020-02-25	7
\.


--
-- TOC entry 3070 (class 0 OID 16481)
-- Dependencies: 220
-- Data for Name: respuesta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.respuesta (id_respuesta, pregunta_respuesta, id_usuario, fecha) FROM stdin;
4	2-645,3/46,3/47,3/48,3/49,4/50,4/51,3/52,2/53,2/54,3/55,4/56,4/57,3/58,3/59,4/60,4/1-27,5/22,5/23,5/25,5/26,5/27,5/28,5/1-31,6/2,6/3,6/4,7/5,7/7,7/9,7/10,7/13,7/27,7/1-48,7/10,7/16,7/19,7/20,7/29,7/30,7/31,7/32,7/33,7/34,7/35,7/36,7/37,7/38,7/39,7/42,7/43,7/44,7/1-58,7/10,7/16,7/18,6/34,7/35,7/37,7/38,7/39,7/40,7/41,7/44,7/	6	2019-12-15
5	1-1|1,3/6,3/11,4/12,4/13,4/14,5/15,5/17,5/18,5/19,5/20,5/21,5/1-2|7,5/22,5/23,5/25,5/26,5/27,5/28,5/1-3|1,7/2,7/3,7/4,7/5,7/7,7/9,7/10,7/13,7/27,7/1-4|8,7/10,7/16,7/19,7/20,7/29,7/30,7/31,7/32,7/33,7/34,7/35,7/36,6/37,7/38,7/39,7/42,7/43,7/44,7/1-5|8,1/10,1/16,1/18,1/34,1/35,1/37,1/38,1/39,1/40,1/41,1/44,1/	6	2019-12-15
6	2-6|45,0/46,0/47,0/48,0/49,0/50,0/51,0/52,0/53,0/54,0/55,0/56,0/57,0/58,0/59,0/60,0/	6	2019-12-15
7	2-6|45,5/46,5/47,5/48,5/49,5/50,5/51,5/52,5/53,5/54,5/55,5/56,5/57,5/58,5/59,5/60,5/	6	2019-12-15
8	2-6|45,3/46,3/47,3/48,3/49,3/50,3/51,3/52,3/53,3/54,3/55,3/56,3/57,3/58,3/59,3/60,3/	6	2019-12-15
9	2-6|45,1/46,1/47,1/48,1/49,1/50,1/51,1/52,1/53,1/54,1/55,1/56,1/57,1/58,1/59,1/60,1/	6	2019-12-15
10	2-6|45,2/46,2/47,2/48,2/49,2/50,2/51,2/52,2/53,2/54,2/55,2/56,2/57,2/58,2/59,2/60,2/	6	2019-12-15
11	2-6|45,0/46,0/47,0/48,0/49,0/50,0/51,0/52,0/53,0/54,0/55,0/56,0/57,0/58,0/59,0/60,0/	6	2019-12-16
12	2-6|45,2/46,2/47,2/48,3/49,3/50,3/51,4/52,4/53,4/54,3/55,3/56,3/57,4/58,4/59,4/60,4/1-2|7,7/22,7/23,7/25,6/26,6/27,5/28,5/1-3|1,7/2,6/3,5/4,4/5,5/7,6/9,6/10,7/13,7/27,7/1-4|8,7/10,7/16,6/19,7/20,6/29,6/30,6/31,7/32,7/33,6/34,7/35,7/36,7/37,7/38,6/39,6/42,6/43,7/44,7/1-5|8,6/10,6/16,6/18,6/34,6/35,6/37,6/38,6/39,7/40,6/41,6/44,6/	6	2019-12-16
13	2-6|45,0/46,0/47,0/48,0/49,0/50,0/51,0/52,0/53,0/54,0/55,0/56,0/57,0/58,0/59,0/60,0/	6	2019-12-16
14	2-6|45,0/46,0/47,0/48,0/49,0/50,0/51,0/52,0/53,0/54,0/55,0/56,0/57,0/58,0/59,0/60,0/	6	2019-12-16
15	1-1|1,3/6,3/11,3/12,4/13,4/14,4/15,5/17,5/18,4/19,5/20,6/21,5/1-2|7,0/22,0/23,0/25,0/26,0/27,0/28,0/1-3|1,0/2,0/3,0/4,0/5,0/7,0/9,0/10,0/13,0/27,0/1-4|8,0/10,0/16,0/19,0/20,0/29,0/30,0/31,0/32,0/33,0/34,0/35,0/36,0/37,0/38,0/39,0/42,0/43,0/44,0/1-5|8,0/10,0/16,0/18,0/34,0/35,0/37,0/38,0/39,0/40,0/41,0/44,0/	6	2019-12-16
16	2-6|45,0/46,0/47,0/48,0/49,0/50,0/51,0/52,0/53,0/54,0/55,0/56,0/57,0/58,0/59,0/60,0/	6	2019-12-16
17	1-1|1,2/6,3/11,3/12,3/13,3/14,3/15,3/17,3/18,3/19,3/20,3/21,3/1-2|7,6/22,6/23,6/25,6/26,6/27,6/28,6/1-3|1,6/2,6/3,6/4,6/5,6/7,6/9,6/10,6/13,6/27,6/1-4|8,6/10,6/16,6/19,6/20,6/29,6/30,6/31,6/32,6/33,6/34,6/35,6/36,6/37,6/38,6/39,6/42,6/43,6/44,6/1-5|8,6/10,6/16,6/18,6/34,6/35,6/37,6/38,6/39,6/40,6/41,6/44,6/	7	2020-02-25
\.


--
-- TOC entry 3071 (class 0 OID 16487)
-- Dependencies: 221
-- Data for Name: rol; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.rol (id_rol, nombre_rol, activo_rol) FROM stdin;
2	Administrador	t
5	Usuario	t
6	Investigador	t
\.


--
-- TOC entry 3096 (class 0 OID 16538)
-- Dependencies: 246
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario (id_usuario, nombres, apellidos, password, id_rol, ci, correo, activo) FROM stdin;
6	Jessica	Pinchao	956a3b578f43bf9d53c7a0adf84c0d7dd58b31c74b4f4e0224fa3f0e6e393f58	5	1001841863	jesicaM@gmail.com	t
7	edwin	pinchao	9d348b454d5bb16b9d651303dc016bcb841e0a009e99155352c2f7eac1b6bf16	5	1003863691	miguepinchao1992@gmail.com	t
8	Daniel 	Rosero	cd8c923f64270c71747c9e109c1da9da354d1f39b8b72cfeb21f5c282d2f61d8	2	1715841027	danielrosero@gmail.com	t
4	Cristian Ramiro	Rosero Pinchao	831b9a38461e3cc6dee73dc5e46957a219c335a5f5bce5cab999b84b01d5ef8a	5	1706172648	cristianrosero@gmail.com	t
\.


--
-- TOC entry 3097 (class 0 OID 16544)
-- Dependencies: 247
-- Data for Name: usuario_curso; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario_curso (id_usuariocurso, id_usuario, id_curso, avance, modulorealizados) FROM stdin;
16	4	2	100	0,4,4,4
17	6	1	40	0,1,2
15	4	1	100	0,1,2,3,5,6
18	7	1	20	0,1
19	7	2	0	0
\.


--
-- TOC entry 3098 (class 0 OID 16550)
-- Dependencies: 248
-- Data for Name: usuario_interes_area; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario_interes_area (id_interes_area, area_id, id_ficha) FROM stdin;
\.


--
-- TOC entry 3099 (class 0 OID 16556)
-- Dependencies: 249
-- Data for Name: usuario_proyecto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario_proyecto (id_usuario_proyecto, id_proyecto_investigacion, id_ficha, id_organizacion, fecha_de, fecha_hasta) FROM stdin;
\.


--
-- TOC entry 3195 (class 0 OID 0)
-- Dependencies: 222
-- Name: seq_area_investigacion; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_area_investigacion', 1, false);


--
-- TOC entry 3196 (class 0 OID 0)
-- Dependencies: 223
-- Name: seq_cuestionario; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_cuestionario', 1, false);


--
-- TOC entry 3197 (class 0 OID 0)
-- Dependencies: 224
-- Name: seq_curso; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_curso', 2, true);


--
-- TOC entry 3198 (class 0 OID 0)
-- Dependencies: 225
-- Name: seq_curso_modulo; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_curso_modulo', 8, true);


--
-- TOC entry 3199 (class 0 OID 0)
-- Dependencies: 226
-- Name: seq_dimension; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_dimension', 1, false);


--
-- TOC entry 3200 (class 0 OID 0)
-- Dependencies: 227
-- Name: seq_dimension_pregunta; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_dimension_pregunta', 1, false);


--
-- TOC entry 3201 (class 0 OID 0)
-- Dependencies: 228
-- Name: seq_etnia; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_etnia', 1, true);


--
-- TOC entry 3202 (class 0 OID 0)
-- Dependencies: 229
-- Name: seq_ficha_personal; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_ficha_personal', 6, true);


--
-- TOC entry 3203 (class 0 OID 0)
-- Dependencies: 230
-- Name: seq_grado_estudio; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_grado_estudio', 1, false);


--
-- TOC entry 3204 (class 0 OID 0)
-- Dependencies: 231
-- Name: seq_modulo; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_modulo', 1, true);


--
-- TOC entry 3205 (class 0 OID 0)
-- Dependencies: 232
-- Name: seq_opcion; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_opcion', 20, true);


--
-- TOC entry 3206 (class 0 OID 0)
-- Dependencies: 233
-- Name: seq_organizacion; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_organizacion', 1, false);


--
-- TOC entry 3207 (class 0 OID 0)
-- Dependencies: 234
-- Name: seq_organizacion_fichapersonal; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_organizacion_fichapersonal', 1, false);


--
-- TOC entry 3208 (class 0 OID 0)
-- Dependencies: 235
-- Name: seq_pais_estado; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_pais_estado', 1, false);


--
-- TOC entry 3209 (class 0 OID 0)
-- Dependencies: 236
-- Name: seq_pregunta; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_pregunta', 122, true);


--
-- TOC entry 3210 (class 0 OID 0)
-- Dependencies: 237
-- Name: seq_proyecto_investigacion; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_proyecto_investigacion', 1, false);


--
-- TOC entry 3211 (class 0 OID 0)
-- Dependencies: 238
-- Name: seq_religion; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_religion', 1, false);


--
-- TOC entry 3212 (class 0 OID 0)
-- Dependencies: 239
-- Name: seq_reporte; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_reporte', 26, true);


--
-- TOC entry 3213 (class 0 OID 0)
-- Dependencies: 240
-- Name: seq_respuesta; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_respuesta', 17, true);


--
-- TOC entry 3214 (class 0 OID 0)
-- Dependencies: 241
-- Name: seq_rol; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_rol', 1, false);


--
-- TOC entry 3215 (class 0 OID 0)
-- Dependencies: 242
-- Name: seq_usuario; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_usuario', 8, true);


--
-- TOC entry 3216 (class 0 OID 0)
-- Dependencies: 243
-- Name: seq_usuario_curso; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_usuario_curso', 19, true);


--
-- TOC entry 3217 (class 0 OID 0)
-- Dependencies: 244
-- Name: seq_usuario_interes_area; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_usuario_interes_area', 1, false);


--
-- TOC entry 3218 (class 0 OID 0)
-- Dependencies: 245
-- Name: seq_usuario_proyecto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_usuario_proyecto', 1, true);


--
-- TOC entry 2886 (class 2606 OID 16563)
-- Name: rol auth_rol_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rol
    ADD CONSTRAINT auth_rol_pk PRIMARY KEY (id_rol);


--
-- TOC entry 2844 (class 2606 OID 16565)
-- Name: cuestionario cuestionario_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cuestionario
    ADD CONSTRAINT cuestionario_pk PRIMARY KEY (id_cuestionario);


--
-- TOC entry 2848 (class 2606 OID 16567)
-- Name: curso_modulo curso_modulo_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.curso_modulo
    ADD CONSTRAINT curso_modulo_pk PRIMARY KEY (id_curso_modulo);


--
-- TOC entry 3219 (class 0 OID 0)
-- Dependencies: 2848
-- Name: CONSTRAINT curso_modulo_pk ON curso_modulo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON CONSTRAINT curso_modulo_pk ON public.curso_modulo IS 'clave primaria';


--
-- TOC entry 2846 (class 2606 OID 16569)
-- Name: curso curso_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.curso
    ADD CONSTRAINT curso_pk PRIMARY KEY (id_curso);


--
-- TOC entry 2850 (class 2606 OID 16571)
-- Name: dimension dimension_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dimension
    ADD CONSTRAINT dimension_pk PRIMARY KEY (id_dimension);


--
-- TOC entry 2852 (class 2606 OID 16573)
-- Name: dimension_pregunta dimension_pregunta_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dimension_pregunta
    ADD CONSTRAINT dimension_pregunta_pk PRIMARY KEY (id_pregunta_dimension);


--
-- TOC entry 2856 (class 2606 OID 16575)
-- Name: etnia etnia_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.etnia
    ADD CONSTRAINT etnia_pk PRIMARY KEY (id_etnia);


--
-- TOC entry 2858 (class 2606 OID 16577)
-- Name: ficha_personal ficha_personal_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ficha_personal
    ADD CONSTRAINT ficha_personal_pk PRIMARY KEY (id_ficha);


--
-- TOC entry 2866 (class 2606 OID 16579)
-- Name: opcion opcion_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.opcion
    ADD CONSTRAINT opcion_pk PRIMARY KEY (id_opcion);


--
-- TOC entry 2872 (class 2606 OID 16581)
-- Name: pais_estado pais_estado_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pais_estado
    ADD CONSTRAINT pais_estado_pk PRIMARY KEY (id_pais_estado);


--
-- TOC entry 2842 (class 2606 OID 16583)
-- Name: area_investigacion pk_area; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.area_investigacion
    ADD CONSTRAINT pk_area PRIMARY KEY (area_id);


--
-- TOC entry 3220 (class 0 OID 0)
-- Dependencies: 2842
-- Name: CONSTRAINT pk_area ON area_investigacion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON CONSTRAINT pk_area ON public.area_investigacion IS 'clave primaria de tabla de Area de investigacion';


--
-- TOC entry 2862 (class 2606 OID 16585)
-- Name: grado_estudio pk_id_grado; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.grado_estudio
    ADD CONSTRAINT pk_id_grado PRIMARY KEY (id_grado);


--
-- TOC entry 3221 (class 0 OID 0)
-- Dependencies: 2862
-- Name: CONSTRAINT pk_id_grado ON grado_estudio; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON CONSTRAINT pk_id_grado ON public.grado_estudio IS 'clave primaria de grad de estudio';


--
-- TOC entry 2868 (class 2606 OID 16587)
-- Name: organizacion pk_id_organizacion; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organizacion
    ADD CONSTRAINT pk_id_organizacion PRIMARY KEY (id_organizacion);


--
-- TOC entry 3222 (class 0 OID 0)
-- Dependencies: 2868
-- Name: CONSTRAINT pk_id_organizacion ON organizacion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON CONSTRAINT pk_id_organizacion ON public.organizacion IS 'clave primaria de la tabla organización';


--
-- TOC entry 2878 (class 2606 OID 16589)
-- Name: proyecto_investigacion pk_id_proyecto; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.proyecto_investigacion
    ADD CONSTRAINT pk_id_proyecto PRIMARY KEY (id_proyecto_investigacion);


--
-- TOC entry 3223 (class 0 OID 0)
-- Dependencies: 2878
-- Name: CONSTRAINT pk_id_proyecto ON proyecto_investigacion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON CONSTRAINT pk_id_proyecto ON public.proyecto_investigacion IS 'clave primaria de la tabla proyecto de investigacion';


--
-- TOC entry 2896 (class 2606 OID 16591)
-- Name: usuario_interes_area pk_interes; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_interes_area
    ADD CONSTRAINT pk_interes PRIMARY KEY (id_interes_area);


--
-- TOC entry 3224 (class 0 OID 0)
-- Dependencies: 2896
-- Name: CONSTRAINT pk_interes ON usuario_interes_area; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON CONSTRAINT pk_interes ON public.usuario_interes_area IS 'clave primaria de la tabla area_interes_investigacion';


--
-- TOC entry 2864 (class 2606 OID 16593)
-- Name: modulo pk_modulo; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.modulo
    ADD CONSTRAINT pk_modulo PRIMARY KEY (id_modulo);


--
-- TOC entry 3225 (class 0 OID 0)
-- Dependencies: 2864
-- Name: CONSTRAINT pk_modulo ON modulo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON CONSTRAINT pk_modulo ON public.modulo IS 'clave primaria de la tabla Modulo';


--
-- TOC entry 2870 (class 2606 OID 16595)
-- Name: organizacion_fichapersonal pk_organizacion_ficha; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organizacion_fichapersonal
    ADD CONSTRAINT pk_organizacion_ficha PRIMARY KEY (id_organizacion_ficha);


--
-- TOC entry 3226 (class 0 OID 0)
-- Dependencies: 2870
-- Name: CONSTRAINT pk_organizacion_ficha ON organizacion_fichapersonal; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON CONSTRAINT pk_organizacion_ficha ON public.organizacion_fichapersonal IS 'clave primaria de la tabla organizacion_ficha';


--
-- TOC entry 2898 (class 2606 OID 16597)
-- Name: usuario_proyecto pk_usuario_proyecto; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_proyecto
    ADD CONSTRAINT pk_usuario_proyecto PRIMARY KEY (id_usuario_proyecto);


--
-- TOC entry 3227 (class 0 OID 0)
-- Dependencies: 2898
-- Name: CONSTRAINT pk_usuario_proyecto ON usuario_proyecto; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON CONSTRAINT pk_usuario_proyecto ON public.usuario_proyecto IS 'clave primaria de la tabla de usuario_proyecto';


--
-- TOC entry 2876 (class 2606 OID 16599)
-- Name: pregunta pregunta_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pregunta
    ADD CONSTRAINT pregunta_pk PRIMARY KEY (id_pregunta);


--
-- TOC entry 2880 (class 2606 OID 16601)
-- Name: religion religion_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.religion
    ADD CONSTRAINT religion_pk PRIMARY KEY (id_religion);


--
-- TOC entry 2882 (class 2606 OID 16603)
-- Name: reporte reporte_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reporte
    ADD CONSTRAINT reporte_pk PRIMARY KEY (id_reporte);


--
-- TOC entry 2884 (class 2606 OID 16605)
-- Name: respuesta respuesta_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.respuesta
    ADD CONSTRAINT respuesta_pk PRIMARY KEY (id_respuesta);


--
-- TOC entry 2888 (class 2606 OID 16607)
-- Name: usuario uk_ci; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT uk_ci UNIQUE (ci);


--
-- TOC entry 3228 (class 0 OID 0)
-- Dependencies: 2888
-- Name: CONSTRAINT uk_ci ON usuario; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON CONSTRAINT uk_ci ON public.usuario IS 'cedula de identidad del usuario unicos';


--
-- TOC entry 2890 (class 2606 OID 16609)
-- Name: usuario uk_correo; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT uk_correo UNIQUE (correo);


--
-- TOC entry 3229 (class 0 OID 0)
-- Dependencies: 2890
-- Name: CONSTRAINT uk_correo ON usuario; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON CONSTRAINT uk_correo ON public.usuario IS 'correo debe ser unico para el usuario';


--
-- TOC entry 2874 (class 2606 OID 16611)
-- Name: pais_estado uk_pais_estado; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pais_estado
    ADD CONSTRAINT uk_pais_estado UNIQUE (nombre, id_padre_pais);


--
-- TOC entry 3230 (class 0 OID 0)
-- Dependencies: 2874
-- Name: CONSTRAINT uk_pais_estado ON pais_estado; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON CONSTRAINT uk_pais_estado ON public.pais_estado IS 'no puede existir pais_estado con el nombre repetido';


--
-- TOC entry 2854 (class 2606 OID 16613)
-- Name: dimension_pregunta uk_pregunta_dimension; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dimension_pregunta
    ADD CONSTRAINT uk_pregunta_dimension UNIQUE (id_dimension, id_pregunta);


--
-- TOC entry 2860 (class 2606 OID 16615)
-- Name: ficha_personal uk_usuario; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ficha_personal
    ADD CONSTRAINT uk_usuario UNIQUE (id_usuario);


--
-- TOC entry 2894 (class 2606 OID 16617)
-- Name: usuario_curso usuario_curso_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_curso
    ADD CONSTRAINT usuario_curso_pk PRIMARY KEY (id_usuariocurso);


--
-- TOC entry 2892 (class 2606 OID 16619)
-- Name: usuario usuario_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pk PRIMARY KEY (id_usuario);


--
-- TOC entry 2914 (class 2606 OID 16620)
-- Name: proyecto_investigacion fk_area_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.proyecto_investigacion
    ADD CONSTRAINT fk_area_id FOREIGN KEY (area_id) REFERENCES public.area_investigacion(area_id) MATCH FULL;


--
-- TOC entry 2921 (class 2606 OID 16625)
-- Name: usuario_interes_area fk_area_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_interes_area
    ADD CONSTRAINT fk_area_id FOREIGN KEY (area_id) REFERENCES public.area_investigacion(area_id) MATCH FULL;


--
-- TOC entry 2904 (class 2606 OID 16630)
-- Name: ficha_personal fk_estado; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ficha_personal
    ADD CONSTRAINT fk_estado FOREIGN KEY (id_estado) REFERENCES public.pais_estado(id_pais_estado) MATCH FULL;


--
-- TOC entry 2905 (class 2606 OID 16635)
-- Name: ficha_personal fk_etnia; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ficha_personal
    ADD CONSTRAINT fk_etnia FOREIGN KEY (id_etnia) REFERENCES public.etnia(id_etnia) MATCH FULL;


--
-- TOC entry 2901 (class 2606 OID 16640)
-- Name: dimension fk_id_cuestionario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dimension
    ADD CONSTRAINT fk_id_cuestionario FOREIGN KEY (id_cuestionario) REFERENCES public.cuestionario(id_cuestionario) MATCH FULL;


--
-- TOC entry 2910 (class 2606 OID 16645)
-- Name: opcion fk_id_cuestionario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.opcion
    ADD CONSTRAINT fk_id_cuestionario FOREIGN KEY (id_cuestionario) REFERENCES public.cuestionario(id_cuestionario) MATCH FULL;


--
-- TOC entry 2919 (class 2606 OID 16650)
-- Name: usuario_curso fk_id_curso; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_curso
    ADD CONSTRAINT fk_id_curso FOREIGN KEY (id_curso) REFERENCES public.curso(id_curso) MATCH FULL;


--
-- TOC entry 2899 (class 2606 OID 16655)
-- Name: curso_modulo fk_id_curso; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.curso_modulo
    ADD CONSTRAINT fk_id_curso FOREIGN KEY (id_curso) REFERENCES public.curso(id_curso) MATCH FULL;


--
-- TOC entry 2902 (class 2606 OID 16660)
-- Name: dimension_pregunta fk_id_dimension; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dimension_pregunta
    ADD CONSTRAINT fk_id_dimension FOREIGN KEY (id_dimension) REFERENCES public.dimension(id_dimension) MATCH FULL;


--
-- TOC entry 2915 (class 2606 OID 16665)
-- Name: reporte fk_id_dimension; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reporte
    ADD CONSTRAINT fk_id_dimension FOREIGN KEY (id_dimension) REFERENCES public.dimension(id_dimension) MATCH FULL;


--
-- TOC entry 2911 (class 2606 OID 16670)
-- Name: organizacion_fichapersonal fk_id_ficha; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organizacion_fichapersonal
    ADD CONSTRAINT fk_id_ficha FOREIGN KEY (id_ficha) REFERENCES public.ficha_personal(id_ficha) MATCH FULL;


--
-- TOC entry 2922 (class 2606 OID 16675)
-- Name: usuario_interes_area fk_id_ficha; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_interes_area
    ADD CONSTRAINT fk_id_ficha FOREIGN KEY (id_ficha) REFERENCES public.ficha_personal(id_ficha) MATCH FULL;


--
-- TOC entry 2906 (class 2606 OID 16680)
-- Name: ficha_personal fk_id_grado; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ficha_personal
    ADD CONSTRAINT fk_id_grado FOREIGN KEY (id_grado) REFERENCES public.grado_estudio(id_grado) MATCH FULL;


--
-- TOC entry 2900 (class 2606 OID 16685)
-- Name: curso_modulo fk_id_modulo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.curso_modulo
    ADD CONSTRAINT fk_id_modulo FOREIGN KEY (id_modulo) REFERENCES public.modulo(id_modulo) MATCH FULL;


--
-- TOC entry 2912 (class 2606 OID 16690)
-- Name: organizacion_fichapersonal fk_id_organizacion; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organizacion_fichapersonal
    ADD CONSTRAINT fk_id_organizacion FOREIGN KEY (id_organizacion) REFERENCES public.organizacion(id_organizacion) MATCH FULL;


--
-- TOC entry 2923 (class 2606 OID 16695)
-- Name: usuario_proyecto fk_id_organizacion; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_proyecto
    ADD CONSTRAINT fk_id_organizacion FOREIGN KEY (id_organizacion) REFERENCES public.organizacion(id_organizacion) MATCH FULL;


--
-- TOC entry 2903 (class 2606 OID 16700)
-- Name: dimension_pregunta fk_id_pregunta; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dimension_pregunta
    ADD CONSTRAINT fk_id_pregunta FOREIGN KEY (id_pregunta) REFERENCES public.pregunta(id_pregunta) MATCH FULL;


--
-- TOC entry 2907 (class 2606 OID 16705)
-- Name: ficha_personal fk_id_religion; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ficha_personal
    ADD CONSTRAINT fk_id_religion FOREIGN KEY (id_religion) REFERENCES public.religion(id_religion) MATCH FULL;


--
-- TOC entry 2916 (class 2606 OID 16710)
-- Name: reporte fk_id_usuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reporte
    ADD CONSTRAINT fk_id_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario) MATCH FULL;


--
-- TOC entry 2917 (class 2606 OID 16715)
-- Name: respuesta fk_id_usuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.respuesta
    ADD CONSTRAINT fk_id_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario) MATCH FULL;


--
-- TOC entry 2920 (class 2606 OID 16720)
-- Name: usuario_curso fk_id_usuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_curso
    ADD CONSTRAINT fk_id_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario) MATCH FULL;


--
-- TOC entry 2908 (class 2606 OID 16725)
-- Name: ficha_personal fk_pais; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ficha_personal
    ADD CONSTRAINT fk_pais FOREIGN KEY (id_pais) REFERENCES public.pais_estado(id_pais_estado) MATCH FULL;


--
-- TOC entry 2913 (class 2606 OID 16730)
-- Name: pais_estado fk_pais_estado; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pais_estado
    ADD CONSTRAINT fk_pais_estado FOREIGN KEY (id_padre_pais) REFERENCES public.pais_estado(id_pais_estado) MATCH FULL;


--
-- TOC entry 2924 (class 2606 OID 16735)
-- Name: usuario_proyecto fk_proyecto_investigacion; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_proyecto
    ADD CONSTRAINT fk_proyecto_investigacion FOREIGN KEY (id_proyecto_investigacion) REFERENCES public.proyecto_investigacion(id_proyecto_investigacion) MATCH FULL;


--
-- TOC entry 2918 (class 2606 OID 16740)
-- Name: usuario fk_rol; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT fk_rol FOREIGN KEY (id_rol) REFERENCES public.rol(id_rol) MATCH FULL;


--
-- TOC entry 2909 (class 2606 OID 16745)
-- Name: ficha_personal fk_usuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ficha_personal
    ADD CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario) MATCH FULL;


--
-- TOC entry 2925 (class 2606 OID 16750)
-- Name: usuario_proyecto fk_usuario_proyecto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_proyecto
    ADD CONSTRAINT fk_usuario_proyecto FOREIGN KEY (id_ficha) REFERENCES public.ficha_personal(id_ficha) MATCH FULL;


-- Completed on 2020-03-06 11:23:18

--
-- PostgreSQL database dump complete
--

