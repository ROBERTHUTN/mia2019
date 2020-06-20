--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2020-06-13 10:00:48

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
-- TOC entry 209 (class 1259 OID 16788)
-- Name: ficha_personal; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reporteprepost (
    id_reportetest numeric NOT NULL,
    autoconciencia integer,
    automotivacion integer,
    control_emocion integer,
    interpersonal integer,
    asesoria_emocional integer,
    nivelestres character varying(38) NOT NULL, 
    centrogravedad character varying(70) NOT NULL, 
    logica_emergente character varying(70) NOT NULL, 
    logica_retroceso character varying(70) NOT NULL, 
    respuesta_cuestionario text null
    fecha_inscripcion timestamp without time zone NOT NULL,
    id_usuario numeric NOT NULL
);


ALTER TABLE public.reporteprepost OWNER TO postgres;

--
-- TOC entry 2824 (class 2606 OID 16941)
-- Name: ficha_personal ficha_personal_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reporteprepost
    ADD CONSTRAINT reporteprepost_pk PRIMARY KEY (id_reportetest);


--
-- TOC entry 2826 (class 2606 OID 16979)
-- Name: ficha_personal uk_usuario; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reporteprepost
    ADD CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario) MATCH FULL;




CREATE SEQUENCE public.seq_reporteprepost
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.seq_cuestionario OWNER TO postgres;

-- Completed on 2020-06-13 10:00:50

--
-- PostgreSQL database dump complete
--

