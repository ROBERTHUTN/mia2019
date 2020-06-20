--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2020-06-20 11:26:05

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
-- TOC entry 204 (class 1259 OID 16758)
-- Name: area_investigacion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.area_investigacion (
    area_id smallint NOT NULL,
    nombre_investigacion character varying(150) NOT NULL,
    descripcion_investigacion text NOT NULL
);


ALTER TABLE public.area_investigacion OWNER TO postgres;

--
-- TOC entry 2963 (class 0 OID 0)
-- Dependencies: 204
-- Name: TABLE area_investigacion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.area_investigacion IS 'area_investigacion en los proyectos MIA';


--
-- TOC entry 2964 (class 0 OID 0)
-- Dependencies: 204
-- Name: COLUMN area_investigacion.area_id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.area_investigacion.area_id IS 'Clave primaria de la tabla areainvestigación';


--
-- TOC entry 2965 (class 0 OID 0)
-- Dependencies: 204
-- Name: COLUMN area_investigacion.nombre_investigacion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.area_investigacion.nombre_investigacion IS 'nombre del area de investigacion';


--
-- TOC entry 2966 (class 0 OID 0)
-- Dependencies: 204
-- Name: COLUMN area_investigacion.descripcion_investigacion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.area_investigacion.descripcion_investigacion IS 'descripcion del area de investigacion';


--
-- TOC entry 2957 (class 0 OID 16758)
-- Dependencies: 204
-- Data for Name: area_investigacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.area_investigacion (area_id, nombre_investigacion, descripcion_investigacion) FROM stdin;
1	Social	relacionado al entorno
\.


--
-- TOC entry 2830 (class 2606 OID 16947)
-- Name: area_investigacion pk_area; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.area_investigacion
    ADD CONSTRAINT pk_area PRIMARY KEY (area_id);


--
-- TOC entry 2967 (class 0 OID 0)
-- Dependencies: 2830
-- Name: CONSTRAINT pk_area ON area_investigacion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON CONSTRAINT pk_area ON public.area_investigacion IS 'clave primaria de tabla de Area de investigacion';


-- Completed on 2020-06-20 11:26:05

--
-- PostgreSQL database dump complete
--

