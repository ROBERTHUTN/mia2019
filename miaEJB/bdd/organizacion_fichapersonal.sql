--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2020-06-20 11:47:35

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
-- TOC entry 216 (class 1259 OID 16818)
-- Name: organizacion_fichapersonal; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.organizacion_fichapersonal (
    id_organizacion_ficha numeric NOT NULL,
    id_organizacion smallint NOT NULL,
    id_ficha numeric NOT NULL
);


ALTER TABLE public.organizacion_fichapersonal OWNER TO postgres;

--
-- TOC entry 2965 (class 0 OID 0)
-- Dependencies: 216
-- Name: TABLE organizacion_fichapersonal; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.organizacion_fichapersonal IS 'tabla intermedia de personas de la ficha personal del usuario.';


--
-- TOC entry 2966 (class 0 OID 0)
-- Dependencies: 216
-- Name: COLUMN organizacion_fichapersonal.id_organizacion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.organizacion_fichapersonal.id_organizacion IS 'clave foranea de la tabla organizacion_fichapersonal';


--
-- TOC entry 2967 (class 0 OID 0)
-- Dependencies: 216
-- Name: COLUMN organizacion_fichapersonal.id_ficha; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.organizacion_fichapersonal.id_ficha IS 'clave foranea de la tabla organizacion_ficha';


--
-- TOC entry 2959 (class 0 OID 16818)
-- Dependencies: 216
-- Data for Name: organizacion_fichapersonal; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.organizacion_fichapersonal (id_organizacion_ficha, id_organizacion, id_ficha) FROM stdin;
\.


--
-- TOC entry 2830 (class 2606 OID 16959)
-- Name: organizacion_fichapersonal pk_organizacion_ficha; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organizacion_fichapersonal
    ADD CONSTRAINT pk_organizacion_ficha PRIMARY KEY (id_organizacion_ficha);


--
-- TOC entry 2968 (class 0 OID 0)
-- Dependencies: 2830
-- Name: CONSTRAINT pk_organizacion_ficha ON organizacion_fichapersonal; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON CONSTRAINT pk_organizacion_ficha ON public.organizacion_fichapersonal IS 'clave primaria de la tabla organizacion_ficha';


--
-- TOC entry 2831 (class 2606 OID 17034)
-- Name: organizacion_fichapersonal fk_id_ficha; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organizacion_fichapersonal
    ADD CONSTRAINT fk_id_ficha FOREIGN KEY (id_ficha) REFERENCES public.ficha_personal(id_ficha) MATCH FULL;


--
-- TOC entry 2832 (class 2606 OID 17054)
-- Name: organizacion_fichapersonal fk_id_organizacion; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organizacion_fichapersonal
    ADD CONSTRAINT fk_id_organizacion FOREIGN KEY (id_organizacion) REFERENCES public.organizacion(id_organizacion) MATCH FULL;


-- Completed on 2020-06-20 11:47:37

--
-- PostgreSQL database dump complete
--

