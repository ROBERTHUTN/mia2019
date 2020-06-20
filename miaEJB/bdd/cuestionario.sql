
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

CREATE TABLE public.preguntamodulo (
    id_pregunta numeric NOT NULL,
    pregunta character varying(90) NOT NULL, 
    id_modulo numeric NOT NULL
);

CREATE TABLE public.opcionpregunta (
    id_opcionpregunta numeric NOT NULL,
    opcion character varying(70) NOT NULL, 
    id_pregunta numeric NOT NULL
);

CREATE TABLE public.respuestapregunta (
    id_respuestapregunta numeric NOT NULL,
    respuesta character varying(70) NOT NULL, 
    id_pregunta numeric NOT NULL
);


ALTER TABLE public.preguntamodulo OWNER TO postgres;
ALTER TABLE public.opcionpregunta OWNER TO postgres;
ALTER TABLE public.respuestapregunta OWNER TO postgres;
--
-- TOC entry 2824 (class 2606 OID 16941)
-- Name: ficha_personal ficha_personal_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.preguntamodulo
    ADD CONSTRAINT preguntamodulo_pk PRIMARY KEY (id_pregunta);

ALTER TABLE ONLY public.opcionpregunta
    ADD CONSTRAINT opcionpregunta_pk PRIMARY KEY (id_opcionpregunta);

ALTER TABLE ONLY public.respuestapregunta
    ADD CONSTRAINT respuestapregunta_pk PRIMARY KEY (id_respuestapregunta);    

--
-- TOC entry 2826 (class 2606 OID 16979)
-- Name: ficha_personal uk_usuario; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.preguntamodulo
    ADD CONSTRAINT fk_modulo FOREIGN KEY (id_modulo) REFERENCES public.modulo(id_modulo) MATCH FULL;

ALTER TABLE ONLY public.opcionpregunta
    ADD CONSTRAINT fk_preguntamodulo FOREIGN KEY (id_pregunta) REFERENCES public.preguntamodulo(id_pregunta) MATCH FULL;

ALTER TABLE ONLY public.respuestapregunta
    ADD CONSTRAINT fk_preguntamodulo FOREIGN KEY (id_pregunta) REFERENCES public.preguntamodulo(id_pregunta) MATCH FULL;


-- Completed on 2020-06-13 10:00:50

--
-- PostgreSQL database dump complete
--



CREATE SEQUENCE public.seq_preguntamodulo
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.seq_preguntamodulo OWNER TO postgres;

CREATE SEQUENCE public.seq_opcionpregunta
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.seq_opcionpregunta OWNER TO postgres;


CREATE SEQUENCE public.seq_respuestapregunta
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.seq_respuestapregunta OWNER TO postgres;