PGDMP         :                x            mia    12.2    12.2               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                        0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            !           1262    16393    mia    DATABASE     �   CREATE DATABASE mia WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';
    DROP DATABASE mia;
                postgres    false            �            1259    16394    area_investigacion    TABLE     �   CREATE TABLE public.area_investigacion (
    area_id smallint NOT NULL,
    nombre_investigacion character varying(150) NOT NULL,
    descripcion_investigacion text NOT NULL
);
 &   DROP TABLE public.area_investigacion;
       public         heap    postgres    false            "           0    0    TABLE area_investigacion    COMMENT     Y   COMMENT ON TABLE public.area_investigacion IS 'area_investigacion en los proyectos MIA';
          public          postgres    false    202            #           0    0 !   COLUMN area_investigacion.area_id    COMMENT     h   COMMENT ON COLUMN public.area_investigacion.area_id IS 'Clave primaria de la tabla areainvestigación';
          public          postgres    false    202            $           0    0 .   COLUMN area_investigacion.nombre_investigacion    COMMENT     h   COMMENT ON COLUMN public.area_investigacion.nombre_investigacion IS 'nombre del area de investigacion';
          public          postgres    false    202            %           0    0 3   COLUMN area_investigacion.descripcion_investigacion    COMMENT     r   COMMENT ON COLUMN public.area_investigacion.descripcion_investigacion IS 'descripcion del area de investigacion';
          public          postgres    false    202            �            1259    16400    cuestionario    TABLE     }   CREATE TABLE public.cuestionario (
    id_cuestionario smallint NOT NULL,
    descripcion character varying(100) NOT NULL
);
     DROP TABLE public.cuestionario;
       public         heap    postgres    false            &           0    0    COLUMN cuestionario.descripcion    COMMENT     N   COMMENT ON COLUMN public.cuestionario.descripcion IS 'tipo de cuestionario ';
          public          postgres    false    203            �            1259    16403    curso    TABLE     �   CREATE TABLE public.curso (
    id_curso smallint NOT NULL,
    nombre character varying(100) NOT NULL,
    descripcion character varying(500) NOT NULL,
    avance smallint NOT NULL,
    secuencia boolean
);
    DROP TABLE public.curso;
       public         heap    postgres    false            '           0    0    TABLE curso    COMMENT     P   COMMENT ON TABLE public.curso IS 'tabla de cursos ofrecidos por MIA Institute';
          public          postgres    false    204            (           0    0    COLUMN curso.id_curso    COMMENT     T   COMMENT ON COLUMN public.curso.id_curso IS 'clave primaria de la tabla de cursos.';
          public          postgres    false    204            )           0    0    COLUMN curso.nombre    COMMENT     =   COMMENT ON COLUMN public.curso.nombre IS 'nombre del curso';
          public          postgres    false    204            *           0    0    COLUMN curso.descripcion    COMMENT     H   COMMENT ON COLUMN public.curso.descripcion IS 'Descripción del curso';
          public          postgres    false    204            +           0    0    COLUMN curso.avance    COMMENT     J   COMMENT ON COLUMN public.curso.avance IS 'cantidad de modulos del curso';
          public          postgres    false    204            ,           0    0    COLUMN curso.secuencia    COMMENT     V   COMMENT ON COLUMN public.curso.secuencia IS 'saber si el curso posee una secuencia.';
          public          postgres    false    204            �            1259    16409    curso_modulo    TABLE     �   CREATE TABLE public.curso_modulo (
    id_curso_modulo numeric(4,0) NOT NULL,
    id_modulo numeric(4,0) NOT NULL,
    id_curso smallint NOT NULL,
    orden_curso integer
);
     DROP TABLE public.curso_modulo;
       public         heap    postgres    false            -           0    0    TABLE curso_modulo    COMMENT     R   COMMENT ON TABLE public.curso_modulo IS 'realizamos la union de modulos y curso';
          public          postgres    false    205            .           0    0    COLUMN curso_modulo.id_modulo    COMMENT     P   COMMENT ON COLUMN public.curso_modulo.id_modulo IS 'clave foranea de la tabla';
          public          postgres    false    205            /           0    0    COLUMN curso_modulo.id_curso    COMMENT     U   COMMENT ON COLUMN public.curso_modulo.id_curso IS 'clave foranea de la tabla curso';
          public          postgres    false    205            0           0    0    COLUMN curso_modulo.orden_curso    COMMENT     o   COMMENT ON COLUMN public.curso_modulo.orden_curso IS 'la secuencia del curso, en que debe realizar el curso.';
          public          postgres    false    205            �            1259    16412 	   dimension    TABLE     �   CREATE TABLE public.dimension (
    id_dimension smallint NOT NULL,
    descripcion character varying(100) NOT NULL,
    id_cuestionario smallint NOT NULL
);
    DROP TABLE public.dimension;
       public         heap    postgres    false            1           0    0    COLUMN dimension.descripcion    COMMENT     V   COMMENT ON COLUMN public.dimension.descripcion IS 'Descricion de la tabla dimension';
          public          postgres    false    206            �            1259    16415    dimension_pregunta    TABLE     �   CREATE TABLE public.dimension_pregunta (
    id_pregunta_dimension numeric NOT NULL,
    id_dimension smallint,
    id_pregunta integer
);
 &   DROP TABLE public.dimension_pregunta;
       public         heap    postgres    false            �            1259    16421    etnia    TABLE     o   CREATE TABLE public.etnia (
    id_etnia smallint NOT NULL,
    descripcion character varying(100) NOT NULL
);
    DROP TABLE public.etnia;
       public         heap    postgres    false            �            1259    16424    ficha_personal    TABLE     �  CREATE TABLE public.ficha_personal (
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
 "   DROP TABLE public.ficha_personal;
       public         heap    postgres    false            2           0    0    COLUMN ficha_personal.id_ficha    COMMENT     [   COMMENT ON COLUMN public.ficha_personal.id_ficha IS 'clave primaria de la ficha personal';
          public          postgres    false    209            3           0    0    COLUMN ficha_personal.edad    COMMENT     a   COMMENT ON COLUMN public.ficha_personal.edad IS 'en este campo se guardara la edad del usuario';
          public          postgres    false    209            4           0    0    COLUMN ficha_personal.genero    COMMENT     J   COMMENT ON COLUMN public.ficha_personal.genero IS 'genero de la persona';
          public          postgres    false    209            5           0    0 &   COLUMN ficha_personal.fecha_nacimiento    COMMENT     a   COMMENT ON COLUMN public.ficha_personal.fecha_nacimiento IS 'fecha de nacimiento de la persona';
          public          postgres    false    209            6           0    0    COLUMN ficha_personal.ciudad    COMMENT     I   COMMENT ON COLUMN public.ficha_personal.ciudad IS 'nombre de la ciudad';
          public          postgres    false    209            7           0    0    COLUMN ficha_personal.direccion    COMMENT     P   COMMENT ON COLUMN public.ficha_personal.direccion IS 'direccion de la persona';
          public          postgres    false    209            8           0    0 "   COLUMN ficha_personal.estado_civil    COMMENT     P   COMMENT ON COLUMN public.ficha_personal.estado_civil IS 'estado de la persona';
          public          postgres    false    209            9           0    0    COLUMN ficha_personal.id_grado    COMMENT     X   COMMENT ON COLUMN public.ficha_personal.id_grado IS 'nivel de educacion de la persona';
          public          postgres    false    209            :           0    0 "   COLUMN ficha_personal.numero_hijos    COMMENT     Y   COMMENT ON COLUMN public.ficha_personal.numero_hijos IS 'numero de hijos de la persona';
          public          postgres    false    209            ;           0    0    COLUMN ficha_personal.id_etnia    COMMENT     W   COMMENT ON COLUMN public.ficha_personal.id_etnia IS 'clave foranea de la tabla etnia';
          public          postgres    false    209            <           0    0 !   COLUMN ficha_personal.id_religion    COMMENT     ]   COMMENT ON COLUMN public.ficha_personal.id_religion IS 'clave foranea de la tabla religion';
          public          postgres    false    209            =           0    0 "   COLUMN ficha_personal.voluntariado    COMMENT     e   COMMENT ON COLUMN public.ficha_personal.voluntariado IS 'personas que deseen realizar voluntariado';
          public          postgres    false    209            >           0    0 '   COLUMN ficha_personal.fecha_inscripcion    COMMENT     |   COMMENT ON COLUMN public.ficha_personal.fecha_inscripcion IS 'fecha de inscripcion del participante por parte del sistema';
          public          postgres    false    209            ?           0    0    COLUMN ficha_personal.trabajo    COMMENT     k   COMMENT ON COLUMN public.ficha_personal.trabajo IS 'personas que cuenta con trabajo en la tabla de ficha';
          public          postgres    false    209            @           0    0    COLUMN ficha_personal.id_pais    COMMENT     \   COMMENT ON COLUMN public.ficha_personal.id_pais IS 'clave foranea de la tabla pais_estado';
          public          postgres    false    209            A           0    0    COLUMN ficha_personal.id_estado    COMMENT     Q   COMMENT ON COLUMN public.ficha_personal.id_estado IS 'clave foranea del estado';
          public          postgres    false    209            B           0    0     COLUMN ficha_personal.id_usuario    COMMENT     [   COMMENT ON COLUMN public.ficha_personal.id_usuario IS 'clave foranea de la tabla Usuario';
          public          postgres    false    209            �            1259    16430    grado_estudio    TABLE     �   CREATE TABLE public.grado_estudio (
    id_grado smallint NOT NULL,
    nombre_grado character varying(150) NOT NULL,
    descripcion text NOT NULL
);
 !   DROP TABLE public.grado_estudio;
       public         heap    postgres    false            C           0    0    TABLE grado_estudio    COMMENT     S   COMMENT ON TABLE public.grado_estudio IS 'grados de estudio de la ficha personal';
          public          postgres    false    210            D           0    0     COLUMN grado_estudio.descripcion    COMMENT     O   COMMENT ON COLUMN public.grado_estudio.descripcion IS 'descripcion del grado';
          public          postgres    false    210            �            1259    16436    modulo    TABLE     �   CREATE TABLE public.modulo (
    id_modulo numeric(4,0) NOT NULL,
    nombre character varying(100) NOT NULL,
    direccion character varying(300) NOT NULL,
    descripcion character varying(500) NOT NULL
);
    DROP TABLE public.modulo;
       public         heap    postgres    false            E           0    0    COLUMN modulo.id_modulo    COMMENT     J   COMMENT ON COLUMN public.modulo.id_modulo IS 'clave primaria del modulo';
          public          postgres    false    211            F           0    0    COLUMN modulo.nombre    COMMENT     ?   COMMENT ON COLUMN public.modulo.nombre IS 'nombre del modulo';
          public          postgres    false    211            G           0    0    COLUMN modulo.descripcion    COMMENT     J   COMMENT ON COLUMN public.modulo.descripcion IS 'descripcion de la tabla';
          public          postgres    false    211            �            1259    16442    opcion    TABLE     �   CREATE TABLE public.opcion (
    id_opcion numeric NOT NULL,
    opc_descripcion character varying(100) NOT NULL,
    valor integer,
    id_cuestionario smallint NOT NULL
);
    DROP TABLE public.opcion;
       public         heap    postgres    false            H           0    0    COLUMN opcion.id_opcion    COMMENT     B   COMMENT ON COLUMN public.opcion.id_opcion IS 'PK_ tabla opción';
          public          postgres    false    212            I           0    0    COLUMN opcion.opc_descripcion    COMMENT     X   COMMENT ON COLUMN public.opcion.opc_descripcion IS 'variable de respuesta de pregunta';
          public          postgres    false    212            J           0    0    COLUMN opcion.id_cuestionario    COMMENT     d   COMMENT ON COLUMN public.opcion.id_cuestionario IS 'fk de la tabla cuestionario para las opciones';
          public          postgres    false    212            �            1259    16448    organizacion    TABLE     �   CREATE TABLE public.organizacion (
    id_organizacion smallint NOT NULL,
    nombre_organizacion character varying(150) NOT NULL,
    telefono_organizacion character varying(15) NOT NULL,
    descripcion_organizacion text NOT NULL
);
     DROP TABLE public.organizacion;
       public         heap    postgres    false            K           0    0    TABLE organizacion    COMMENT     j   COMMENT ON TABLE public.organizacion IS 'tablas de organizaciones que trabajan con la metodología MIA.';
          public          postgres    false    213            L           0    0 #   COLUMN organizacion.id_organizacion    COMMENT     d   COMMENT ON COLUMN public.organizacion.id_organizacion IS 'clave primaria de la tabla organizacion';
          public          postgres    false    213            M           0    0 '   COLUMN organizacion.nombre_organizacion    COMMENT     Z   COMMENT ON COLUMN public.organizacion.nombre_organizacion IS 'nombre de la organizacion';
          public          postgres    false    213            N           0    0 )   COLUMN organizacion.telefono_organizacion    COMMENT     i   COMMENT ON COLUMN public.organizacion.telefono_organizacion IS 'numero de contacto de la organizacion ';
          public          postgres    false    213            O           0    0 ,   COLUMN organizacion.descripcion_organizacion    COMMENT     e   COMMENT ON COLUMN public.organizacion.descripcion_organizacion IS 'descripcion de la organizacion ';
          public          postgres    false    213            �            1259    16454    organizacion_fichapersonal    TABLE     �   CREATE TABLE public.organizacion_fichapersonal (
    id_organizacion_ficha numeric NOT NULL,
    id_organizacion smallint NOT NULL,
    id_ficha numeric NOT NULL
);
 .   DROP TABLE public.organizacion_fichapersonal;
       public         heap    postgres    false            P           0    0     TABLE organizacion_fichapersonal    COMMENT     x   COMMENT ON TABLE public.organizacion_fichapersonal IS 'tabla intermedia de personas de la ficha personal del usuario.';
          public          postgres    false    214            Q           0    0 1   COLUMN organizacion_fichapersonal.id_organizacion    COMMENT        COMMENT ON COLUMN public.organizacion_fichapersonal.id_organizacion IS 'clave foranea de la tabla organizacion_fichapersonal';
          public          postgres    false    214            R           0    0 *   COLUMN organizacion_fichapersonal.id_ficha    COMMENT     p   COMMENT ON COLUMN public.organizacion_fichapersonal.id_ficha IS 'clave foranea de la tabla organizacion_ficha';
          public          postgres    false    214            �            1259    16460    pais_estado    TABLE     �   CREATE TABLE public.pais_estado (
    id_pais_estado integer NOT NULL,
    nombre character varying(100) NOT NULL,
    id_padre_pais integer
);
    DROP TABLE public.pais_estado;
       public         heap    postgres    false            S           0    0    TABLE pais_estado    COMMENT     ;   COMMENT ON TABLE public.pais_estado IS 'paises del mundo';
          public          postgres    false    215            T           0    0 !   COLUMN pais_estado.id_pais_estado    COMMENT     [   COMMENT ON COLUMN public.pais_estado.id_pais_estado IS 'clave perimaria de la tabla Pais';
          public          postgres    false    215            U           0    0     COLUMN pais_estado.id_padre_pais    COMMENT     {   COMMENT ON COLUMN public.pais_estado.id_padre_pais IS 'clave foranea de la tabla pais_estado que hace referencia al pais';
          public          postgres    false    215            �            1259    16463    pregunta    TABLE     t   CREATE TABLE public.pregunta (
    id_pregunta integer NOT NULL,
    descripcion character varying(250) NOT NULL
);
    DROP TABLE public.pregunta;
       public         heap    postgres    false            V           0    0    COLUMN pregunta.id_pregunta    COMMENT     L   COMMENT ON COLUMN public.pregunta.id_pregunta IS 'pk de la tabla pregunta';
          public          postgres    false    216            W           0    0    COLUMN pregunta.descripcion    COMMENT     n   COMMENT ON COLUMN public.pregunta.descripcion IS 'Descripcion de la pregunta formulada por el administrador';
          public          postgres    false    216            �            1259    16466    proyecto_investigacion    TABLE       CREATE TABLE public.proyecto_investigacion (
    id_proyecto_investigacion numeric NOT NULL,
    nombre_proyecto character varying(150) NOT NULL,
    proyecto_descripcion text NOT NULL,
    area_id smallint NOT NULL,
    estado_proyecto boolean NOT NULL
);
 *   DROP TABLE public.proyecto_investigacion;
       public         heap    postgres    false            X           0    0    TABLE proyecto_investigacion    COMMENT     k   COMMENT ON TABLE public.proyecto_investigacion IS 'tabla de proyectos realizados por la metodología MIA';
          public          postgres    false    217            Y           0    0 7   COLUMN proyecto_investigacion.id_proyecto_investigacion    COMMENT     �   COMMENT ON COLUMN public.proyecto_investigacion.id_proyecto_investigacion IS 'clave primaria de ta tablaproyecto de investigación.';
          public          postgres    false    217            Z           0    0 -   COLUMN proyecto_investigacion.nombre_proyecto    COMMENT     Z   COMMENT ON COLUMN public.proyecto_investigacion.nombre_proyecto IS 'nombre del proyecto';
          public          postgres    false    217            [           0    0 2   COLUMN proyecto_investigacion.proyecto_descripcion    COMMENT     v   COMMENT ON COLUMN public.proyecto_investigacion.proyecto_descripcion IS 'descripcion del proyecto de investigacion.';
          public          postgres    false    217            \           0    0 %   COLUMN proyecto_investigacion.area_id    COMMENT     o   COMMENT ON COLUMN public.proyecto_investigacion.area_id IS 'clave foranea de la tabla area de investigación';
          public          postgres    false    217            ]           0    0 -   COLUMN proyecto_investigacion.estado_proyecto    COMMENT     |   COMMENT ON COLUMN public.proyecto_investigacion.estado_proyecto IS 'estado del proyecto si se encuentra Activo o inactivo';
          public          postgres    false    217            �            1259    16472    religion    TABLE     u   CREATE TABLE public.religion (
    id_religion smallint NOT NULL,
    descripcion character varying(100) NOT NULL
);
    DROP TABLE public.religion;
       public         heap    postgres    false            ^           0    0    TABLE religion    COMMENT     >   COMMENT ON TABLE public.religion IS 'religiones en el mundo';
          public          postgres    false    218            _           0    0    COLUMN religion.id_religion    COMMENT     F   COMMENT ON COLUMN public.religion.id_religion IS 'id de la religion';
          public          postgres    false    218            `           0    0    COLUMN religion.descripcion    COMMENT     J   COMMENT ON COLUMN public.religion.descripcion IS 'nombre de la religion';
          public          postgres    false    218            �            1259    16475    reporte    TABLE     �   CREATE TABLE public.reporte (
    id_reporte numeric NOT NULL,
    resultado character varying(100) NOT NULL,
    id_dimension smallint NOT NULL,
    fecha date,
    id_usuario numeric NOT NULL
);
    DROP TABLE public.reporte;
       public         heap    postgres    false            �            1259    16481 	   respuesta    TABLE     �   CREATE TABLE public.respuesta (
    id_respuesta numeric NOT NULL,
    pregunta_respuesta text,
    id_usuario numeric NOT NULL,
    fecha date
);
    DROP TABLE public.respuesta;
       public         heap    postgres    false            �            1259    16487    rol    TABLE     x   CREATE TABLE public.rol (
    id_rol integer NOT NULL,
    nombre_rol character varying(100),
    activo_rol boolean
);
    DROP TABLE public.rol;
       public         heap    postgres    false            �            1259    16490    seq_area_investigacion    SEQUENCE     �   CREATE SEQUENCE public.seq_area_investigacion
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;
 -   DROP SEQUENCE public.seq_area_investigacion;
       public          postgres    false            �            1259    16492    seq_cuestionario    SEQUENCE     �   CREATE SEQUENCE public.seq_cuestionario
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;
 '   DROP SEQUENCE public.seq_cuestionario;
       public          postgres    false            �            1259    16494 	   seq_curso    SEQUENCE     y   CREATE SEQUENCE public.seq_curso
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;
     DROP SEQUENCE public.seq_curso;
       public          postgres    false            �            1259    16496    seq_curso_modulo    SEQUENCE     �   CREATE SEQUENCE public.seq_curso_modulo
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;
 '   DROP SEQUENCE public.seq_curso_modulo;
       public          postgres    false            �            1259    16498    seq_dimension    SEQUENCE     }   CREATE SEQUENCE public.seq_dimension
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;
 $   DROP SEQUENCE public.seq_dimension;
       public          postgres    false            �            1259    16500    seq_dimension_pregunta    SEQUENCE     �   CREATE SEQUENCE public.seq_dimension_pregunta
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;
 -   DROP SEQUENCE public.seq_dimension_pregunta;
       public          postgres    false            �            1259    16502 	   seq_etnia    SEQUENCE     y   CREATE SEQUENCE public.seq_etnia
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;
     DROP SEQUENCE public.seq_etnia;
       public          postgres    false            a           0    0    SEQUENCE seq_etnia    COMMENT     H   COMMENT ON SEQUENCE public.seq_etnia IS 'sequencia de la tabla etnia ';
          public          postgres    false    228            �            1259    16504    seq_ficha_personal    SEQUENCE     �   CREATE SEQUENCE public.seq_ficha_personal
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;
 )   DROP SEQUENCE public.seq_ficha_personal;
       public          postgres    false            b           0    0    SEQUENCE seq_ficha_personal    COMMENT     X   COMMENT ON SEQUENCE public.seq_ficha_personal IS 'sequencia de la tabla ficha persona';
          public          postgres    false    229            �            1259    16506    seq_grado_estudio    SEQUENCE     �   CREATE SEQUENCE public.seq_grado_estudio
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;
 (   DROP SEQUENCE public.seq_grado_estudio;
       public          postgres    false            �            1259    16508 
   seq_modulo    SEQUENCE     z   CREATE SEQUENCE public.seq_modulo
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;
 !   DROP SEQUENCE public.seq_modulo;
       public          postgres    false            �            1259    16510 
   seq_opcion    SEQUENCE     z   CREATE SEQUENCE public.seq_opcion
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;
 !   DROP SEQUENCE public.seq_opcion;
       public          postgres    false            �            1259    16512    seq_organizacion    SEQUENCE     �   CREATE SEQUENCE public.seq_organizacion
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;
 '   DROP SEQUENCE public.seq_organizacion;
       public          postgres    false            �            1259    16514    seq_organizacion_fichapersonal    SEQUENCE     �   CREATE SEQUENCE public.seq_organizacion_fichapersonal
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;
 5   DROP SEQUENCE public.seq_organizacion_fichapersonal;
       public          postgres    false            �            1259    16516    seq_pais_estado    SEQUENCE        CREATE SEQUENCE public.seq_pais_estado
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;
 &   DROP SEQUENCE public.seq_pais_estado;
       public          postgres    false            c           0    0    SEQUENCE seq_pais_estado    COMMENT     S   COMMENT ON SEQUENCE public.seq_pais_estado IS 'sequencia de la tabla pais estado';
          public          postgres    false    235            �            1259    16518    seq_pregunta    SEQUENCE     |   CREATE SEQUENCE public.seq_pregunta
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;
 #   DROP SEQUENCE public.seq_pregunta;
       public          postgres    false            �            1259    16520    seq_proyecto_investigacion    SEQUENCE     �   CREATE SEQUENCE public.seq_proyecto_investigacion
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;
 1   DROP SEQUENCE public.seq_proyecto_investigacion;
       public          postgres    false            �            1259    16522    seq_religion    SEQUENCE     |   CREATE SEQUENCE public.seq_religion
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;
 #   DROP SEQUENCE public.seq_religion;
       public          postgres    false            d           0    0    SEQUENCE seq_religion    COMMENT     M   COMMENT ON SEQUENCE public.seq_religion IS 'sequencia de la tabla religion';
          public          postgres    false    238            �            1259    16524    seq_reporte    SEQUENCE     {   CREATE SEQUENCE public.seq_reporte
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;
 "   DROP SEQUENCE public.seq_reporte;
       public          postgres    false            �            1259    16526    seq_respuesta    SEQUENCE     }   CREATE SEQUENCE public.seq_respuesta
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;
 $   DROP SEQUENCE public.seq_respuesta;
       public          postgres    false            �            1259    16528    seq_rol    SEQUENCE     w   CREATE SEQUENCE public.seq_rol
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;
    DROP SEQUENCE public.seq_rol;
       public          postgres    false            e           0    0    SEQUENCE seq_rol    COMMENT     C   COMMENT ON SEQUENCE public.seq_rol IS 'sequencia de la tabla rol';
          public          postgres    false    241            �            1259    16530    seq_usuario    SEQUENCE     {   CREATE SEQUENCE public.seq_usuario
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;
 "   DROP SEQUENCE public.seq_usuario;
       public          postgres    false            f           0    0    SEQUENCE seq_usuario    COMMENT     K   COMMENT ON SEQUENCE public.seq_usuario IS 'sequencia de la tabla usuario';
          public          postgres    false    242            �            1259    16532    seq_usuario_curso    SEQUENCE     �   CREATE SEQUENCE public.seq_usuario_curso
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;
 (   DROP SEQUENCE public.seq_usuario_curso;
       public          postgres    false            �            1259    16534    seq_usuario_interes_area    SEQUENCE     �   CREATE SEQUENCE public.seq_usuario_interes_area
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;
 /   DROP SEQUENCE public.seq_usuario_interes_area;
       public          postgres    false            �            1259    16536    seq_usuario_proyecto    SEQUENCE     �   CREATE SEQUENCE public.seq_usuario_proyecto
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;
 +   DROP SEQUENCE public.seq_usuario_proyecto;
       public          postgres    false            �            1259    16538    usuario    TABLE     X  CREATE TABLE public.usuario (
    id_usuario numeric NOT NULL,
    nombres character varying(100) NOT NULL,
    apellidos character varying(100) NOT NULL,
    password character varying(300) NOT NULL,
    id_rol integer NOT NULL,
    ci character varying(20) NOT NULL,
    correo character varying(150) NOT NULL,
    activo boolean NOT NULL
);
    DROP TABLE public.usuario;
       public         heap    postgres    false            g           0    0    TABLE usuario    COMMENT     A   COMMENT ON TABLE public.usuario IS 'Tabla usuario de registro.';
          public          postgres    false    246            h           0    0    COLUMN usuario.id_usuario    COMMENT     U   COMMENT ON COLUMN public.usuario.id_usuario IS 'clave primaria de la tabla usuario';
          public          postgres    false    246            i           0    0    COLUMN usuario.nombres    COMMENT     C   COMMENT ON COLUMN public.usuario.nombres IS 'nombres del usuario';
          public          postgres    false    246            j           0    0    COLUMN usuario.apellidos    COMMENT     K   COMMENT ON COLUMN public.usuario.apellidos IS 'apellidos de los usuarios';
          public          postgres    false    246            k           0    0    COLUMN usuario.password    COMMENT     M   COMMENT ON COLUMN public.usuario.password IS 'clave de ingreso del usuario';
          public          postgres    false    246            l           0    0    COLUMN usuario.id_rol    COMMENT     L   COMMENT ON COLUMN public.usuario.id_rol IS 'clave foranea de la tabla rol';
          public          postgres    false    246            m           0    0    COLUMN usuario.ci    COMMENT     Q   COMMENT ON COLUMN public.usuario.ci IS 'identificación del usuario son unicos';
          public          postgres    false    246            n           0    0    COLUMN usuario.correo    COMMENT     K   COMMENT ON COLUMN public.usuario.correo IS 'correo del usuario ser unico';
          public          postgres    false    246            o           0    0    COLUMN usuario.activo    COMMENT     P   COMMENT ON COLUMN public.usuario.activo IS 'si el usuario se encuentra activo';
          public          postgres    false    246            �            1259    16544    usuario_curso    TABLE     �   CREATE TABLE public.usuario_curso (
    id_usuariocurso numeric NOT NULL,
    id_usuario numeric NOT NULL,
    id_curso smallint NOT NULL,
    avance numeric(3,0) NOT NULL,
    modulorealizados character varying(50) NOT NULL
);
 !   DROP TABLE public.usuario_curso;
       public         heap    postgres    false            p           0    0    TABLE usuario_curso    COMMENT     ]   COMMENT ON TABLE public.usuario_curso IS 'Relacion de usuario y cursos asignados por este.';
          public          postgres    false    247            q           0    0 $   COLUMN usuario_curso.id_usuariocurso    COMMENT     X   COMMENT ON COLUMN public.usuario_curso.id_usuariocurso IS 'clave primaria de la tabla';
          public          postgres    false    247            r           0    0    COLUMN usuario_curso.id_usuario    COMMENT     Z   COMMENT ON COLUMN public.usuario_curso.id_usuario IS 'clave foranea de la tabla usuario';
          public          postgres    false    247            s           0    0    COLUMN usuario_curso.id_curso    COMMENT     W   COMMENT ON COLUMN public.usuario_curso.id_curso IS 'clave foranea de la tabla cursos';
          public          postgres    false    247            t           0    0    COLUMN usuario_curso.avance    COMMENT     E   COMMENT ON COLUMN public.usuario_curso.avance IS 'avance del curso';
          public          postgres    false    247            u           0    0 %   COLUMN usuario_curso.modulorealizados    COMMENT     ^   COMMENT ON COLUMN public.usuario_curso.modulorealizados IS 'cantidad de módulos terminados';
          public          postgres    false    247            �            1259    16550    usuario_interes_area    TABLE     �   CREATE TABLE public.usuario_interes_area (
    id_interes_area numeric NOT NULL,
    area_id smallint NOT NULL,
    id_ficha numeric NOT NULL
);
 (   DROP TABLE public.usuario_interes_area;
       public         heap    postgres    false            v           0    0 $   COLUMN usuario_interes_area.id_ficha    COMMENT     l   COMMENT ON COLUMN public.usuario_interes_area.id_ficha IS 'clave foranea de la tabla usuario_interes_area';
          public          postgres    false    248            �            1259    16556    usuario_proyecto    TABLE       CREATE TABLE public.usuario_proyecto (
    id_usuario_proyecto numeric NOT NULL,
    id_proyecto_investigacion numeric NOT NULL,
    id_ficha numeric NOT NULL,
    id_organizacion smallint NOT NULL,
    fecha_de date NOT NULL,
    fecha_hasta date NOT NULL
);
 $   DROP TABLE public.usuario_proyecto;
       public         heap    postgres    false            w           0    0    TABLE usuario_proyecto    COMMENT     f   COMMENT ON TABLE public.usuario_proyecto IS 'proyectos de los usuarios que se encuentren realizando';
          public          postgres    false    249            x           0    0 +   COLUMN usuario_proyecto.id_usuario_proyecto    COMMENT     p   COMMENT ON COLUMN public.usuario_proyecto.id_usuario_proyecto IS 'Id del proyecto y usuario realizando estos.';
          public          postgres    false    249            y           0    0 1   COLUMN usuario_proyecto.id_proyecto_investigacion    COMMENT     {   COMMENT ON COLUMN public.usuario_proyecto.id_proyecto_investigacion IS 'clave foranea de la tabla proyecto investigacion';
          public          postgres    false    249            z           0    0     COLUMN usuario_proyecto.id_ficha    COMMENT     d   COMMENT ON COLUMN public.usuario_proyecto.id_ficha IS 'clave foranea de la tabla usuario_proyecto';
          public          postgres    false    249            {           0    0 '   COLUMN usuario_proyecto.id_organizacion    COMMENT     c   COMMENT ON COLUMN public.usuario_proyecto.id_organizacion IS 'clave foranea del usuario_Proyecto';
          public          postgres    false    249            �          0    16394    area_investigacion 
   TABLE DATA           f   COPY public.area_investigacion (area_id, nombre_investigacion, descripcion_investigacion) FROM stdin;
    public          postgres    false    202   J      �          0    16400    cuestionario 
   TABLE DATA           D   COPY public.cuestionario (id_cuestionario, descripcion) FROM stdin;
    public          postgres    false    203   �      �          0    16403    curso 
   TABLE DATA           Q   COPY public.curso (id_curso, nombre, descripcion, avance, secuencia) FROM stdin;
    public          postgres    false    204   �      �          0    16409    curso_modulo 
   TABLE DATA           Y   COPY public.curso_modulo (id_curso_modulo, id_modulo, id_curso, orden_curso) FROM stdin;
    public          postgres    false    205   $!      �          0    16412 	   dimension 
   TABLE DATA           O   COPY public.dimension (id_dimension, descripcion, id_cuestionario) FROM stdin;
    public          postgres    false    206   l!      �          0    16415    dimension_pregunta 
   TABLE DATA           ^   COPY public.dimension_pregunta (id_pregunta_dimension, id_dimension, id_pregunta) FROM stdin;
    public          postgres    false    207   r"      �          0    16421    etnia 
   TABLE DATA           6   COPY public.etnia (id_etnia, descripcion) FROM stdin;
    public          postgres    false    208   $$      �          0    16424    ficha_personal 
   TABLE DATA           �   COPY public.ficha_personal (id_ficha, edad, genero, fecha_nacimiento, ciudad, direccion, telefono, celular, estado_civil, id_grado, numero_hijos, id_etnia, id_religion, voluntariado, fecha_inscripcion, trabajo, id_pais, id_estado, id_usuario) FROM stdin;
    public          postgres    false    209   ^$      �          0    16430    grado_estudio 
   TABLE DATA           L   COPY public.grado_estudio (id_grado, nombre_grado, descripcion) FROM stdin;
    public          postgres    false    210   C%      �          0    16436    modulo 
   TABLE DATA           K   COPY public.modulo (id_modulo, nombre, direccion, descripcion) FROM stdin;
    public          postgres    false    211   �%      �          0    16442    opcion 
   TABLE DATA           T   COPY public.opcion (id_opcion, opc_descripcion, valor, id_cuestionario) FROM stdin;
    public          postgres    false    212   g&      �          0    16448    organizacion 
   TABLE DATA           }   COPY public.organizacion (id_organizacion, nombre_organizacion, telefono_organizacion, descripcion_organizacion) FROM stdin;
    public          postgres    false    213   <'      �          0    16454    organizacion_fichapersonal 
   TABLE DATA           f   COPY public.organizacion_fichapersonal (id_organizacion_ficha, id_organizacion, id_ficha) FROM stdin;
    public          postgres    false    214   �'      �          0    16460    pais_estado 
   TABLE DATA           L   COPY public.pais_estado (id_pais_estado, nombre, id_padre_pais) FROM stdin;
    public          postgres    false    215   �'      �          0    16463    pregunta 
   TABLE DATA           <   COPY public.pregunta (id_pregunta, descripcion) FROM stdin;
    public          postgres    false    216   �/      �          0    16466    proyecto_investigacion 
   TABLE DATA           �   COPY public.proyecto_investigacion (id_proyecto_investigacion, nombre_proyecto, proyecto_descripcion, area_id, estado_proyecto) FROM stdin;
    public          postgres    false    217   �;      �          0    16472    religion 
   TABLE DATA           <   COPY public.religion (id_religion, descripcion) FROM stdin;
    public          postgres    false    218   ,<      �          0    16475    reporte 
   TABLE DATA           Y   COPY public.reporte (id_reporte, resultado, id_dimension, fecha, id_usuario) FROM stdin;
    public          postgres    false    219   U<      �          0    16481 	   respuesta 
   TABLE DATA           X   COPY public.respuesta (id_respuesta, pregunta_respuesta, id_usuario, fecha) FROM stdin;
    public          postgres    false    220   }=      �          0    16487    rol 
   TABLE DATA           =   COPY public.rol (id_rol, nombre_rol, activo_rol) FROM stdin;
    public          postgres    false    221   7@                0    16538    usuario 
   TABLE DATA           g   COPY public.usuario (id_usuario, nombres, apellidos, password, id_rol, ci, correo, activo) FROM stdin;
    public          postgres    false    246   |@                0    16544    usuario_curso 
   TABLE DATA           h   COPY public.usuario_curso (id_usuariocurso, id_usuario, id_curso, avance, modulorealizados) FROM stdin;
    public          postgres    false    247   |B                0    16550    usuario_interes_area 
   TABLE DATA           R   COPY public.usuario_interes_area (id_interes_area, area_id, id_ficha) FROM stdin;
    public          postgres    false    248   �B                0    16556    usuario_proyecto 
   TABLE DATA           �   COPY public.usuario_proyecto (id_usuario_proyecto, id_proyecto_investigacion, id_ficha, id_organizacion, fecha_de, fecha_hasta) FROM stdin;
    public          postgres    false    249   �B      |           0    0    seq_area_investigacion    SEQUENCE SET     E   SELECT pg_catalog.setval('public.seq_area_investigacion', 1, false);
          public          postgres    false    222            }           0    0    seq_cuestionario    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.seq_cuestionario', 1, false);
          public          postgres    false    223            ~           0    0 	   seq_curso    SEQUENCE SET     7   SELECT pg_catalog.setval('public.seq_curso', 2, true);
          public          postgres    false    224                       0    0    seq_curso_modulo    SEQUENCE SET     >   SELECT pg_catalog.setval('public.seq_curso_modulo', 8, true);
          public          postgres    false    225            �           0    0    seq_dimension    SEQUENCE SET     <   SELECT pg_catalog.setval('public.seq_dimension', 1, false);
          public          postgres    false    226            �           0    0    seq_dimension_pregunta    SEQUENCE SET     E   SELECT pg_catalog.setval('public.seq_dimension_pregunta', 1, false);
          public          postgres    false    227            �           0    0 	   seq_etnia    SEQUENCE SET     7   SELECT pg_catalog.setval('public.seq_etnia', 1, true);
          public          postgres    false    228            �           0    0    seq_ficha_personal    SEQUENCE SET     @   SELECT pg_catalog.setval('public.seq_ficha_personal', 6, true);
          public          postgres    false    229            �           0    0    seq_grado_estudio    SEQUENCE SET     @   SELECT pg_catalog.setval('public.seq_grado_estudio', 1, false);
          public          postgres    false    230            �           0    0 
   seq_modulo    SEQUENCE SET     8   SELECT pg_catalog.setval('public.seq_modulo', 1, true);
          public          postgres    false    231            �           0    0 
   seq_opcion    SEQUENCE SET     9   SELECT pg_catalog.setval('public.seq_opcion', 20, true);
          public          postgres    false    232            �           0    0    seq_organizacion    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.seq_organizacion', 1, false);
          public          postgres    false    233            �           0    0    seq_organizacion_fichapersonal    SEQUENCE SET     M   SELECT pg_catalog.setval('public.seq_organizacion_fichapersonal', 1, false);
          public          postgres    false    234            �           0    0    seq_pais_estado    SEQUENCE SET     >   SELECT pg_catalog.setval('public.seq_pais_estado', 1, false);
          public          postgres    false    235            �           0    0    seq_pregunta    SEQUENCE SET     <   SELECT pg_catalog.setval('public.seq_pregunta', 122, true);
          public          postgres    false    236            �           0    0    seq_proyecto_investigacion    SEQUENCE SET     I   SELECT pg_catalog.setval('public.seq_proyecto_investigacion', 1, false);
          public          postgres    false    237            �           0    0    seq_religion    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.seq_religion', 1, false);
          public          postgres    false    238            �           0    0    seq_reporte    SEQUENCE SET     :   SELECT pg_catalog.setval('public.seq_reporte', 26, true);
          public          postgres    false    239            �           0    0    seq_respuesta    SEQUENCE SET     <   SELECT pg_catalog.setval('public.seq_respuesta', 17, true);
          public          postgres    false    240            �           0    0    seq_rol    SEQUENCE SET     6   SELECT pg_catalog.setval('public.seq_rol', 1, false);
          public          postgres    false    241            �           0    0    seq_usuario    SEQUENCE SET     9   SELECT pg_catalog.setval('public.seq_usuario', 8, true);
          public          postgres    false    242            �           0    0    seq_usuario_curso    SEQUENCE SET     @   SELECT pg_catalog.setval('public.seq_usuario_curso', 19, true);
          public          postgres    false    243            �           0    0    seq_usuario_interes_area    SEQUENCE SET     G   SELECT pg_catalog.setval('public.seq_usuario_interes_area', 1, false);
          public          postgres    false    244            �           0    0    seq_usuario_proyecto    SEQUENCE SET     B   SELECT pg_catalog.setval('public.seq_usuario_proyecto', 1, true);
          public          postgres    false    245            F           2606    16563    rol auth_rol_pk 
   CONSTRAINT     Q   ALTER TABLE ONLY public.rol
    ADD CONSTRAINT auth_rol_pk PRIMARY KEY (id_rol);
 9   ALTER TABLE ONLY public.rol DROP CONSTRAINT auth_rol_pk;
       public            postgres    false    221                       2606    16565    cuestionario cuestionario_pk 
   CONSTRAINT     g   ALTER TABLE ONLY public.cuestionario
    ADD CONSTRAINT cuestionario_pk PRIMARY KEY (id_cuestionario);
 F   ALTER TABLE ONLY public.cuestionario DROP CONSTRAINT cuestionario_pk;
       public            postgres    false    203                        2606    16567    curso_modulo curso_modulo_pk 
   CONSTRAINT     g   ALTER TABLE ONLY public.curso_modulo
    ADD CONSTRAINT curso_modulo_pk PRIMARY KEY (id_curso_modulo);
 F   ALTER TABLE ONLY public.curso_modulo DROP CONSTRAINT curso_modulo_pk;
       public            postgres    false    205            �           0    0 *   CONSTRAINT curso_modulo_pk ON curso_modulo    COMMENT     R   COMMENT ON CONSTRAINT curso_modulo_pk ON public.curso_modulo IS 'clave primaria';
          public          postgres    false    2848                       2606    16569    curso curso_pk 
   CONSTRAINT     R   ALTER TABLE ONLY public.curso
    ADD CONSTRAINT curso_pk PRIMARY KEY (id_curso);
 8   ALTER TABLE ONLY public.curso DROP CONSTRAINT curso_pk;
       public            postgres    false    204            "           2606    16571    dimension dimension_pk 
   CONSTRAINT     ^   ALTER TABLE ONLY public.dimension
    ADD CONSTRAINT dimension_pk PRIMARY KEY (id_dimension);
 @   ALTER TABLE ONLY public.dimension DROP CONSTRAINT dimension_pk;
       public            postgres    false    206            $           2606    16573 (   dimension_pregunta dimension_pregunta_pk 
   CONSTRAINT     y   ALTER TABLE ONLY public.dimension_pregunta
    ADD CONSTRAINT dimension_pregunta_pk PRIMARY KEY (id_pregunta_dimension);
 R   ALTER TABLE ONLY public.dimension_pregunta DROP CONSTRAINT dimension_pregunta_pk;
       public            postgres    false    207            (           2606    16575    etnia etnia_pk 
   CONSTRAINT     R   ALTER TABLE ONLY public.etnia
    ADD CONSTRAINT etnia_pk PRIMARY KEY (id_etnia);
 8   ALTER TABLE ONLY public.etnia DROP CONSTRAINT etnia_pk;
       public            postgres    false    208            *           2606    16577     ficha_personal ficha_personal_pk 
   CONSTRAINT     d   ALTER TABLE ONLY public.ficha_personal
    ADD CONSTRAINT ficha_personal_pk PRIMARY KEY (id_ficha);
 J   ALTER TABLE ONLY public.ficha_personal DROP CONSTRAINT ficha_personal_pk;
       public            postgres    false    209            2           2606    16579    opcion opcion_pk 
   CONSTRAINT     U   ALTER TABLE ONLY public.opcion
    ADD CONSTRAINT opcion_pk PRIMARY KEY (id_opcion);
 :   ALTER TABLE ONLY public.opcion DROP CONSTRAINT opcion_pk;
       public            postgres    false    212            8           2606    16581    pais_estado pais_estado_pk 
   CONSTRAINT     d   ALTER TABLE ONLY public.pais_estado
    ADD CONSTRAINT pais_estado_pk PRIMARY KEY (id_pais_estado);
 D   ALTER TABLE ONLY public.pais_estado DROP CONSTRAINT pais_estado_pk;
       public            postgres    false    215                       2606    16583    area_investigacion pk_area 
   CONSTRAINT     ]   ALTER TABLE ONLY public.area_investigacion
    ADD CONSTRAINT pk_area PRIMARY KEY (area_id);
 D   ALTER TABLE ONLY public.area_investigacion DROP CONSTRAINT pk_area;
       public            postgres    false    202            �           0    0 (   CONSTRAINT pk_area ON area_investigacion    COMMENT     r   COMMENT ON CONSTRAINT pk_area ON public.area_investigacion IS 'clave primaria de tabla de Area de investigacion';
          public          postgres    false    2842            .           2606    16585    grado_estudio pk_id_grado 
   CONSTRAINT     ]   ALTER TABLE ONLY public.grado_estudio
    ADD CONSTRAINT pk_id_grado PRIMARY KEY (id_grado);
 C   ALTER TABLE ONLY public.grado_estudio DROP CONSTRAINT pk_id_grado;
       public            postgres    false    210            �           0    0 '   CONSTRAINT pk_id_grado ON grado_estudio    COMMENT     b   COMMENT ON CONSTRAINT pk_id_grado ON public.grado_estudio IS 'clave primaria de grad de estudio';
          public          postgres    false    2862            4           2606    16587    organizacion pk_id_organizacion 
   CONSTRAINT     j   ALTER TABLE ONLY public.organizacion
    ADD CONSTRAINT pk_id_organizacion PRIMARY KEY (id_organizacion);
 I   ALTER TABLE ONLY public.organizacion DROP CONSTRAINT pk_id_organizacion;
       public            postgres    false    213            �           0    0 -   CONSTRAINT pk_id_organizacion ON organizacion    COMMENT     o   COMMENT ON CONSTRAINT pk_id_organizacion ON public.organizacion IS 'clave primaria de la tabla organización';
          public          postgres    false    2868            >           2606    16589 %   proyecto_investigacion pk_id_proyecto 
   CONSTRAINT     z   ALTER TABLE ONLY public.proyecto_investigacion
    ADD CONSTRAINT pk_id_proyecto PRIMARY KEY (id_proyecto_investigacion);
 O   ALTER TABLE ONLY public.proyecto_investigacion DROP CONSTRAINT pk_id_proyecto;
       public            postgres    false    217            �           0    0 3   CONSTRAINT pk_id_proyecto ON proyecto_investigacion    COMMENT     �   COMMENT ON CONSTRAINT pk_id_proyecto ON public.proyecto_investigacion IS 'clave primaria de la tabla proyecto de investigacion';
          public          postgres    false    2878            P           2606    16591    usuario_interes_area pk_interes 
   CONSTRAINT     j   ALTER TABLE ONLY public.usuario_interes_area
    ADD CONSTRAINT pk_interes PRIMARY KEY (id_interes_area);
 I   ALTER TABLE ONLY public.usuario_interes_area DROP CONSTRAINT pk_interes;
       public            postgres    false    248            �           0    0 -   CONSTRAINT pk_interes ON usuario_interes_area    COMMENT     |   COMMENT ON CONSTRAINT pk_interes ON public.usuario_interes_area IS 'clave primaria de la tabla area_interes_investigacion';
          public          postgres    false    2896            0           2606    16593    modulo pk_modulo 
   CONSTRAINT     U   ALTER TABLE ONLY public.modulo
    ADD CONSTRAINT pk_modulo PRIMARY KEY (id_modulo);
 :   ALTER TABLE ONLY public.modulo DROP CONSTRAINT pk_modulo;
       public            postgres    false    211            �           0    0    CONSTRAINT pk_modulo ON modulo    COMMENT     Y   COMMENT ON CONSTRAINT pk_modulo ON public.modulo IS 'clave primaria de la tabla Modulo';
          public          postgres    false    2864            6           2606    16595 0   organizacion_fichapersonal pk_organizacion_ficha 
   CONSTRAINT     �   ALTER TABLE ONLY public.organizacion_fichapersonal
    ADD CONSTRAINT pk_organizacion_ficha PRIMARY KEY (id_organizacion_ficha);
 Z   ALTER TABLE ONLY public.organizacion_fichapersonal DROP CONSTRAINT pk_organizacion_ficha;
       public            postgres    false    214            �           0    0 >   CONSTRAINT pk_organizacion_ficha ON organizacion_fichapersonal    COMMENT     �   COMMENT ON CONSTRAINT pk_organizacion_ficha ON public.organizacion_fichapersonal IS 'clave primaria de la tabla organizacion_ficha';
          public          postgres    false    2870            R           2606    16597 $   usuario_proyecto pk_usuario_proyecto 
   CONSTRAINT     s   ALTER TABLE ONLY public.usuario_proyecto
    ADD CONSTRAINT pk_usuario_proyecto PRIMARY KEY (id_usuario_proyecto);
 N   ALTER TABLE ONLY public.usuario_proyecto DROP CONSTRAINT pk_usuario_proyecto;
       public            postgres    false    249            �           0    0 2   CONSTRAINT pk_usuario_proyecto ON usuario_proyecto    COMMENT     z   COMMENT ON CONSTRAINT pk_usuario_proyecto ON public.usuario_proyecto IS 'clave primaria de la tabla de usuario_proyecto';
          public          postgres    false    2898            <           2606    16599    pregunta pregunta_pk 
   CONSTRAINT     [   ALTER TABLE ONLY public.pregunta
    ADD CONSTRAINT pregunta_pk PRIMARY KEY (id_pregunta);
 >   ALTER TABLE ONLY public.pregunta DROP CONSTRAINT pregunta_pk;
       public            postgres    false    216            @           2606    16601    religion religion_pk 
   CONSTRAINT     [   ALTER TABLE ONLY public.religion
    ADD CONSTRAINT religion_pk PRIMARY KEY (id_religion);
 >   ALTER TABLE ONLY public.religion DROP CONSTRAINT religion_pk;
       public            postgres    false    218            B           2606    16603    reporte reporte_pk 
   CONSTRAINT     X   ALTER TABLE ONLY public.reporte
    ADD CONSTRAINT reporte_pk PRIMARY KEY (id_reporte);
 <   ALTER TABLE ONLY public.reporte DROP CONSTRAINT reporte_pk;
       public            postgres    false    219            D           2606    16605    respuesta respuesta_pk 
   CONSTRAINT     ^   ALTER TABLE ONLY public.respuesta
    ADD CONSTRAINT respuesta_pk PRIMARY KEY (id_respuesta);
 @   ALTER TABLE ONLY public.respuesta DROP CONSTRAINT respuesta_pk;
       public            postgres    false    220            H           2606    16607    usuario uk_ci 
   CONSTRAINT     F   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT uk_ci UNIQUE (ci);
 7   ALTER TABLE ONLY public.usuario DROP CONSTRAINT uk_ci;
       public            postgres    false    246            �           0    0    CONSTRAINT uk_ci ON usuario    COMMENT     [   COMMENT ON CONSTRAINT uk_ci ON public.usuario IS 'cedula de identidad del usuario unicos';
          public          postgres    false    2888            J           2606    16609    usuario uk_correo 
   CONSTRAINT     N   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT uk_correo UNIQUE (correo);
 ;   ALTER TABLE ONLY public.usuario DROP CONSTRAINT uk_correo;
       public            postgres    false    246            �           0    0    CONSTRAINT uk_correo ON usuario    COMMENT     ^   COMMENT ON CONSTRAINT uk_correo ON public.usuario IS 'correo debe ser unico para el usuario';
          public          postgres    false    2890            :           2606    16611    pais_estado uk_pais_estado 
   CONSTRAINT     f   ALTER TABLE ONLY public.pais_estado
    ADD CONSTRAINT uk_pais_estado UNIQUE (nombre, id_padre_pais);
 D   ALTER TABLE ONLY public.pais_estado DROP CONSTRAINT uk_pais_estado;
       public            postgres    false    215    215            �           0    0 (   CONSTRAINT uk_pais_estado ON pais_estado    COMMENT     u   COMMENT ON CONSTRAINT uk_pais_estado ON public.pais_estado IS 'no puede existir pais_estado con el nombre repetido';
          public          postgres    false    2874            &           2606    16613 (   dimension_pregunta uk_pregunta_dimension 
   CONSTRAINT     x   ALTER TABLE ONLY public.dimension_pregunta
    ADD CONSTRAINT uk_pregunta_dimension UNIQUE (id_dimension, id_pregunta);
 R   ALTER TABLE ONLY public.dimension_pregunta DROP CONSTRAINT uk_pregunta_dimension;
       public            postgres    false    207    207            ,           2606    16615    ficha_personal uk_usuario 
   CONSTRAINT     Z   ALTER TABLE ONLY public.ficha_personal
    ADD CONSTRAINT uk_usuario UNIQUE (id_usuario);
 C   ALTER TABLE ONLY public.ficha_personal DROP CONSTRAINT uk_usuario;
       public            postgres    false    209            N           2606    16617    usuario_curso usuario_curso_pk 
   CONSTRAINT     i   ALTER TABLE ONLY public.usuario_curso
    ADD CONSTRAINT usuario_curso_pk PRIMARY KEY (id_usuariocurso);
 H   ALTER TABLE ONLY public.usuario_curso DROP CONSTRAINT usuario_curso_pk;
       public            postgres    false    247            L           2606    16619    usuario usuario_pk 
   CONSTRAINT     X   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pk PRIMARY KEY (id_usuario);
 <   ALTER TABLE ONLY public.usuario DROP CONSTRAINT usuario_pk;
       public            postgres    false    246            b           2606    16620 !   proyecto_investigacion fk_area_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto_investigacion
    ADD CONSTRAINT fk_area_id FOREIGN KEY (area_id) REFERENCES public.area_investigacion(area_id) MATCH FULL;
 K   ALTER TABLE ONLY public.proyecto_investigacion DROP CONSTRAINT fk_area_id;
       public          postgres    false    2842    217    202            i           2606    16625    usuario_interes_area fk_area_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuario_interes_area
    ADD CONSTRAINT fk_area_id FOREIGN KEY (area_id) REFERENCES public.area_investigacion(area_id) MATCH FULL;
 I   ALTER TABLE ONLY public.usuario_interes_area DROP CONSTRAINT fk_area_id;
       public          postgres    false    248    202    2842            X           2606    16630    ficha_personal fk_estado    FK CONSTRAINT     �   ALTER TABLE ONLY public.ficha_personal
    ADD CONSTRAINT fk_estado FOREIGN KEY (id_estado) REFERENCES public.pais_estado(id_pais_estado) MATCH FULL;
 B   ALTER TABLE ONLY public.ficha_personal DROP CONSTRAINT fk_estado;
       public          postgres    false    209    215    2872            Y           2606    16635    ficha_personal fk_etnia    FK CONSTRAINT     �   ALTER TABLE ONLY public.ficha_personal
    ADD CONSTRAINT fk_etnia FOREIGN KEY (id_etnia) REFERENCES public.etnia(id_etnia) MATCH FULL;
 A   ALTER TABLE ONLY public.ficha_personal DROP CONSTRAINT fk_etnia;
       public          postgres    false    208    2856    209            U           2606    16640    dimension fk_id_cuestionario    FK CONSTRAINT     �   ALTER TABLE ONLY public.dimension
    ADD CONSTRAINT fk_id_cuestionario FOREIGN KEY (id_cuestionario) REFERENCES public.cuestionario(id_cuestionario) MATCH FULL;
 F   ALTER TABLE ONLY public.dimension DROP CONSTRAINT fk_id_cuestionario;
       public          postgres    false    203    206    2844            ^           2606    16645    opcion fk_id_cuestionario    FK CONSTRAINT     �   ALTER TABLE ONLY public.opcion
    ADD CONSTRAINT fk_id_cuestionario FOREIGN KEY (id_cuestionario) REFERENCES public.cuestionario(id_cuestionario) MATCH FULL;
 C   ALTER TABLE ONLY public.opcion DROP CONSTRAINT fk_id_cuestionario;
       public          postgres    false    212    203    2844            g           2606    16650    usuario_curso fk_id_curso    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuario_curso
    ADD CONSTRAINT fk_id_curso FOREIGN KEY (id_curso) REFERENCES public.curso(id_curso) MATCH FULL;
 C   ALTER TABLE ONLY public.usuario_curso DROP CONSTRAINT fk_id_curso;
       public          postgres    false    247    204    2846            S           2606    16655    curso_modulo fk_id_curso    FK CONSTRAINT     �   ALTER TABLE ONLY public.curso_modulo
    ADD CONSTRAINT fk_id_curso FOREIGN KEY (id_curso) REFERENCES public.curso(id_curso) MATCH FULL;
 B   ALTER TABLE ONLY public.curso_modulo DROP CONSTRAINT fk_id_curso;
       public          postgres    false    204    2846    205            V           2606    16660 "   dimension_pregunta fk_id_dimension    FK CONSTRAINT     �   ALTER TABLE ONLY public.dimension_pregunta
    ADD CONSTRAINT fk_id_dimension FOREIGN KEY (id_dimension) REFERENCES public.dimension(id_dimension) MATCH FULL;
 L   ALTER TABLE ONLY public.dimension_pregunta DROP CONSTRAINT fk_id_dimension;
       public          postgres    false    207    206    2850            c           2606    16665    reporte fk_id_dimension    FK CONSTRAINT     �   ALTER TABLE ONLY public.reporte
    ADD CONSTRAINT fk_id_dimension FOREIGN KEY (id_dimension) REFERENCES public.dimension(id_dimension) MATCH FULL;
 A   ALTER TABLE ONLY public.reporte DROP CONSTRAINT fk_id_dimension;
       public          postgres    false    206    219    2850            _           2606    16670 &   organizacion_fichapersonal fk_id_ficha    FK CONSTRAINT     �   ALTER TABLE ONLY public.organizacion_fichapersonal
    ADD CONSTRAINT fk_id_ficha FOREIGN KEY (id_ficha) REFERENCES public.ficha_personal(id_ficha) MATCH FULL;
 P   ALTER TABLE ONLY public.organizacion_fichapersonal DROP CONSTRAINT fk_id_ficha;
       public          postgres    false    209    214    2858            j           2606    16675     usuario_interes_area fk_id_ficha    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuario_interes_area
    ADD CONSTRAINT fk_id_ficha FOREIGN KEY (id_ficha) REFERENCES public.ficha_personal(id_ficha) MATCH FULL;
 J   ALTER TABLE ONLY public.usuario_interes_area DROP CONSTRAINT fk_id_ficha;
       public          postgres    false    209    248    2858            Z           2606    16680    ficha_personal fk_id_grado    FK CONSTRAINT     �   ALTER TABLE ONLY public.ficha_personal
    ADD CONSTRAINT fk_id_grado FOREIGN KEY (id_grado) REFERENCES public.grado_estudio(id_grado) MATCH FULL;
 D   ALTER TABLE ONLY public.ficha_personal DROP CONSTRAINT fk_id_grado;
       public          postgres    false    210    209    2862            T           2606    16685    curso_modulo fk_id_modulo    FK CONSTRAINT     �   ALTER TABLE ONLY public.curso_modulo
    ADD CONSTRAINT fk_id_modulo FOREIGN KEY (id_modulo) REFERENCES public.modulo(id_modulo) MATCH FULL;
 C   ALTER TABLE ONLY public.curso_modulo DROP CONSTRAINT fk_id_modulo;
       public          postgres    false    205    211    2864            `           2606    16690 -   organizacion_fichapersonal fk_id_organizacion    FK CONSTRAINT     �   ALTER TABLE ONLY public.organizacion_fichapersonal
    ADD CONSTRAINT fk_id_organizacion FOREIGN KEY (id_organizacion) REFERENCES public.organizacion(id_organizacion) MATCH FULL;
 W   ALTER TABLE ONLY public.organizacion_fichapersonal DROP CONSTRAINT fk_id_organizacion;
       public          postgres    false    214    213    2868            k           2606    16695 #   usuario_proyecto fk_id_organizacion    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuario_proyecto
    ADD CONSTRAINT fk_id_organizacion FOREIGN KEY (id_organizacion) REFERENCES public.organizacion(id_organizacion) MATCH FULL;
 M   ALTER TABLE ONLY public.usuario_proyecto DROP CONSTRAINT fk_id_organizacion;
       public          postgres    false    2868    249    213            W           2606    16700 !   dimension_pregunta fk_id_pregunta    FK CONSTRAINT     �   ALTER TABLE ONLY public.dimension_pregunta
    ADD CONSTRAINT fk_id_pregunta FOREIGN KEY (id_pregunta) REFERENCES public.pregunta(id_pregunta) MATCH FULL;
 K   ALTER TABLE ONLY public.dimension_pregunta DROP CONSTRAINT fk_id_pregunta;
       public          postgres    false    216    2876    207            [           2606    16705    ficha_personal fk_id_religion    FK CONSTRAINT     �   ALTER TABLE ONLY public.ficha_personal
    ADD CONSTRAINT fk_id_religion FOREIGN KEY (id_religion) REFERENCES public.religion(id_religion) MATCH FULL;
 G   ALTER TABLE ONLY public.ficha_personal DROP CONSTRAINT fk_id_religion;
       public          postgres    false    218    2880    209            d           2606    16710    reporte fk_id_usuario    FK CONSTRAINT     �   ALTER TABLE ONLY public.reporte
    ADD CONSTRAINT fk_id_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario) MATCH FULL;
 ?   ALTER TABLE ONLY public.reporte DROP CONSTRAINT fk_id_usuario;
       public          postgres    false    246    2892    219            e           2606    16715    respuesta fk_id_usuario    FK CONSTRAINT     �   ALTER TABLE ONLY public.respuesta
    ADD CONSTRAINT fk_id_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario) MATCH FULL;
 A   ALTER TABLE ONLY public.respuesta DROP CONSTRAINT fk_id_usuario;
       public          postgres    false    220    2892    246            h           2606    16720    usuario_curso fk_id_usuario    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuario_curso
    ADD CONSTRAINT fk_id_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario) MATCH FULL;
 E   ALTER TABLE ONLY public.usuario_curso DROP CONSTRAINT fk_id_usuario;
       public          postgres    false    2892    246    247            \           2606    16725    ficha_personal fk_pais    FK CONSTRAINT     �   ALTER TABLE ONLY public.ficha_personal
    ADD CONSTRAINT fk_pais FOREIGN KEY (id_pais) REFERENCES public.pais_estado(id_pais_estado) MATCH FULL;
 @   ALTER TABLE ONLY public.ficha_personal DROP CONSTRAINT fk_pais;
       public          postgres    false    2872    215    209            a           2606    16730    pais_estado fk_pais_estado    FK CONSTRAINT     �   ALTER TABLE ONLY public.pais_estado
    ADD CONSTRAINT fk_pais_estado FOREIGN KEY (id_padre_pais) REFERENCES public.pais_estado(id_pais_estado) MATCH FULL;
 D   ALTER TABLE ONLY public.pais_estado DROP CONSTRAINT fk_pais_estado;
       public          postgres    false    2872    215    215            l           2606    16735 *   usuario_proyecto fk_proyecto_investigacion    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuario_proyecto
    ADD CONSTRAINT fk_proyecto_investigacion FOREIGN KEY (id_proyecto_investigacion) REFERENCES public.proyecto_investigacion(id_proyecto_investigacion) MATCH FULL;
 T   ALTER TABLE ONLY public.usuario_proyecto DROP CONSTRAINT fk_proyecto_investigacion;
       public          postgres    false    2878    217    249            f           2606    16740    usuario fk_rol    FK CONSTRAINT     y   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT fk_rol FOREIGN KEY (id_rol) REFERENCES public.rol(id_rol) MATCH FULL;
 8   ALTER TABLE ONLY public.usuario DROP CONSTRAINT fk_rol;
       public          postgres    false    2886    221    246            ]           2606    16745    ficha_personal fk_usuario    FK CONSTRAINT     �   ALTER TABLE ONLY public.ficha_personal
    ADD CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario) MATCH FULL;
 C   ALTER TABLE ONLY public.ficha_personal DROP CONSTRAINT fk_usuario;
       public          postgres    false    246    2892    209            m           2606    16750 $   usuario_proyecto fk_usuario_proyecto    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuario_proyecto
    ADD CONSTRAINT fk_usuario_proyecto FOREIGN KEY (id_ficha) REFERENCES public.ficha_personal(id_ficha) MATCH FULL;
 N   ALTER TABLE ONLY public.usuario_proyecto DROP CONSTRAINT fk_usuario_proyecto;
       public          postgres    false    249    2858    209            �   -   x�3��O�L��,J�IL���KL�WH�QH�+�/������� ���      �   [   x�3���+I��LO�K�LTp��O���K��2���,K�QHIUH-.):���˘3 �(-,擙�Z�X�������To���������� (�C      �   "  x�U�=NA���S�A)Y	P$R��H��Hi�&���������@K5�y���<�]�Q8g�JQ�P	*�U@�&]R`��2���Lu�L�Y�z�� �_�Z+�sIf@"#�9U ׁTr��7��R�z�O&��悉 ���z��5F����~�TsAo{������Q�[�F��x�P��U�D�So��c���
�=&U��"Z��^��_/��FL8���`UCd�4�R�,��_�>֢ˌ�<q�V��ܘ*+g~5��:�mػ�9�]�� ���      �   8   x���  �Ώa�m�M��?���S���O9��/5zvg�����Y9� )`�      �   �   x�E�=n�0�g�<�Q'�#C��Pt�B�j�BSR��u��#�be�$�����dƖ��AS�fm�9�ȅ�'��c�p��c$���.p�j�kxL��('�&��F���gZ�I���pd�j��[aU����0�@b�]�ij]װ����Y.��V�ǰ�q<�f���J=O��݉�D	;[����
vߋfBr��,����8�W�y�������%h�����b/[��!Y���C_몪~��n�      �   �  x�%��q�0�3QL��������\� ����w�ׂ+?�0�p��H�h��+��cO=����i� fx)�d�,����ʁ��z�Gf�a���IX��YXk����oW+~O��?¯�$��˅��zJ�u޷|�_��y ��P�B}��� ��P_B}����ߓ|�����O�$?��62�)Y�߷\���z��C����wx"Q&�Q!�rQE�Zr�HC�t��|���D�/��l��r���E��.�=�=+s%g��l����]����S�0-3�=+{ea���5�X�ę���)[ұ-�!f��{���~_�L_SZN��/q�o|�#}y.�0��8�w�����%6�0�Y��I�Ŕ�)#��yZ��s��E���|��<6_d?O��������?���      �   *   x�3��M-.ɬ��2�tL+�WpM.M,�/�L������� ��
?      �   �   x����n�0���S���N�&y�����mE��w���)�����}�@pn�v�)9G��2Me}0�X�:
iI�~����{;�E3X��A쨶�%�Xy�C�5@L �З�i�,TJ�zǲ��n�~��>�gW�a:�׹S�Jߖ[�����C�h,b�13W�ƣ1��o���y��X�����z��)s�+�F+7�ce�y�2W>      �   p   x��;�@Dk�S�	q�4 �Kc�Œ�J|��(��ifތ^�<)�L�
F� ������4�V��eV'P���M�T�h���`Y��W�Ý!�]zw)�?x�)�      �   �   x�e�1�@ ����4!hGIq����r�A	�v/�GY�>���)M�t�Cxl���#O<loߵ����s�G�"���E�5�*��6��	C$�	AhƄ�2mV�Yq$x��H�oI�ɮ��t@�S_�+9ŝ���/O>�      �   �   x�e�;�0��9EO��g��H<6���MP��Gi�RFv��Ѷ(P�ଽ�0�9lu�
?#GpjQ�կ�Q�ɧ7mL�;;
���+8��z��F���oo�F��u"�D��>����E$ac.�
�X�	y:�{�$�B�"�$�?(j��+������{�����`%\�0�1�ؙ�1�8�G����"�ni      �   ?   x�3��+M-KT�LI�4�447�0�4�t,�O�LL���SHIU��WP(�LI�+I-����� �v      �      x������ � �      �     x�UWMs�8=#���ݪdK�$�:JN��ز�r�5���D��M
Hȱ���9��5�\���Jދ�n`���{�XLm������6���N�%���K�#1mJ���/��Ԕ��T��f-ޚ����6ƶ���LVj%��D����R��J<���mA�7c1�UI!�83C���1�R5:�ڨB{�H�ۦ4�W~{,������sR��ϙ8�TPe7>�8�J��'����l�=��}t�+j
N�@��
�řԦV">��5�L2�J��|p�H\����=����RqaBN���O��S����ؚ���/�p �Ў�.���ji���L`6T�_�5'k�1��Ñ��*w�;v�\�a*Nt�Qt*2�����y#���!OÉ�TK~���>��X\ڟ�^ZS��q���S���+]�o���wCaq,�Vn)�&��b���%[���U�(�e:[���h\�F�@́��q7�ŧ*ZP��B�I��Jo����cw<kT �x���[F��8wT<�Gd��������5������V�����L��?l�m:_���i,����X�4_W�Ot:��qy
�H�b1�3#���%�����䚦).�v����s�w�o:����Ț�	��3mBn3��h
���F�kdCq��2��?�9��F�bdc�Y���H��+B����TS�&|5G���2e�ې�siv��ۀ��Akt��k;\W�9��\�/G��|�� Z�{�i%�>���g@��T%����{fU#�M���KOvT�R��#���IBL �-=I�e��M �Zꀺ�^�o�7���|�	xO6rg%S�$�T���.�G��_)�N����%5̦$Sw-z:Ps���݁%��S�@<@�+�7V<@O(_�ǃ�8[�U��L�冻7�؇��ߙ��|^�eUɠ
$�Pi�m4��G�պ)u�/��n������.�\�ŴZ>���C.b��7��C1����?,KD��� L�0*���18@5�қ^��w�f�ck��Vj�����0[��ط�k��R�պ�M�A�ؙ9���	E%�s�Dy8YS��1Nk�G��7�tqM��B���+�p�Ԯ�K	}X P�_cHĝfV��h����7��Qת�C(:����%hX͸0���1hr���AA1����L-��>�	�1��<v`ݘ�`�	[2��*�.;��jʊ
ٮ��0��->�0��Sǣ@���8n& 8��8��������r0��ţSj9M�,:��܉�R?�9<KK�K�:>��H��x �մkj�ќ:�֏\��j�����{��@��R�N���TK�B�i�E�Faz�3E��;���q��0�Š�S	�y x�T=�ӝ2W�S~�eI��D}F� #1����k�c47�s솵�Y�5�𙕦i%�{��#�� b�>�Tےe���� �����A���ԒBJ@���\y)[ݭ���K<=E$ H�M��|�d�:�t��H��V�G@P����	Hp�e�õrP�q�
��a :��F	xq�/y���|�

c+q#�w]=F,u��뺟6Ay�nT�"e�3Ph��wk�(��%ѧJ6�5q��Ġ��*��\n�XѿiW���p#���#�* !K��{�4��:#���Ĺ�Z��F�m�\�d%`N����6�� F!���`�� ����ύ�]Jn���E��G�����	D�i�A��'ڽ���y���\eϝM�0&"tUP���>f�d��-~�x4yOZ ���/U�r3��lt�ؿ���z�Rš�p��(��A�t���5��t�+qҴ��d�� �	j���
��� .����r���������}m�u7d�
ZUe���A�S0���s���a�4���K��Cmt�=lw*րb^��������\���zp���63Q��Mu`�]oc�S�{�ZO6���`��8R���.��]��檿S������6�?aq˵�O�A��p�?�����ϻw�����      �   �  x��Zˎ��]���K�{��Td;1`���M5Y�S�"��"�Y�Wfc@�Y�y�?ɗ�[Ş�$C�Y�]��>��KM��K]�fg
����	��������<ty�i߸\��ԡUu�/'��ν��V�)�v�0��!/u�xL����_���:{�iܤ
\�yg�J��]��98h�j|4��T�^.k��*��7���gA�
�����x�m����P������3��+��hL9B�r�cp��#�N{(r��1H�W�V�ʥP��A#ح_��*{���;���<	M2���.�?*������jo��ܵ� �����^�&|�H������r9��G]���͵�+��)��B�W#�L��7,=ʲ�W[�cv���Qى�1�����a��i�g߫�<�!��̨I��~g���?�(��·��@>��@��ן���j����B��g����dP�E�Z�R�[]	�b���8����}�P�~����-���s+0�0�`����� �<:F�ꩇIY�z�|�NDIQ_��x��k�'�G����T�=�R��1 6�d�r�@#�z�e�ڡ��aD�VtiZ܋�^5�1>�6D�q�M�Ar���a��fӡ��|u����.�u	p��1V�R7hk�0;|�{�D�m��Qٳk�|���nhۼ�A�I*t5+�~�_GȚ��V�Ռ��Z��շ���l�D9,��	c�l�H��K}�<�������؉������t�n��"�jL��"��=��j�=]
ZC#�����pf��������d9�<H�����x�Q]+gXIU�zx"�jLm�Q�iDO�b����1%�����i{�;w�m�|;`�5 �#��U���{�M%l�_L�4E�j!:
yfї���Y���H6��ǂ���1*����?���o?p`r���*�����s��b���fx"�&�4H�|fݦ���Ⱦs�煷"PF�����i�	#���s�Ub�j�������}l�'.�@UK�z��O�Cw%�pQ��.V�-��Y��"vA 2c�d�h�qejw^���<���#B�� ��9i�I+�7'#��ra>�^�u�;������Cw2ߎ4��t`b�x������T_Ɣ�t9�����H{����R�0�}�@=w
�DG����xs���9 �v��tk�"��R5��C��\��*&���8�~9�/�o�|q���X�T��o|�Íu)dE��p�J�:\��Z#k�abp��ZP��'f��E(�$�n�V�����ߔ��:�C@z�����jt!�ya�`�D�m^�Y��M��;}�05������VoQ�|�p7�'�����.'���� l�b�Icdߤ�bw�<�v�������F�P� xе�5{�ŭI_Ϲ��,f٫@z	/�;��X�d\ܙa�����dq������+I��%�*����K����i�f�9�R�瑐��/�/DWX���z*�(}�����$e;h +��x>��*�������6�J8�"n�j&�<�[�>�r��6��|��f�*]������Dt<��<���CN@�MN��katk�L�����@R��aZ�Z>�f�����L�Y�}J�7��p�1Y�����������y���ds&���H�(�Ev���D�Ԯ�xA���q;�X����*��:~'C!�� U�#^�EG�*LY�u*I�d	�H�ZW�N�+	F�_��u��e﻽��F��	�5�n��d�	�ׂs��۲��ﻓ( }IK�
'�t-�s�F��`���Kd���|����΅+�s�W��qƞ��*�Z�u��ݣ�I�W��-��O](�2�+��C��ٮ�Z��=[�h�7�eB�2 �1痫�'���WF��4*\o����9��V#�Rxe��<zW�1H��[s�֩!|��׉�)�����c���^�vܓBQ�ׯ@$�� �A�ϲ��-߆�����!��\͘�4��o�F�o>SޣNV�9geV~�2joi5?����c��]+7D�q78P'|��>v�S���O�:WF�� ��M�� ��a~�V�F�J��ۅkqf��C����G�3C��-JI.BFTGq(۲�~�\�����sۺcl�I��Y�G�B7-�v�	p���Ȓ����z�둑n��ƣ���H�§��'1��;��{�����A.��� c��E#���N�*\ϳw��8氎J�����y�c��Ԕ�X��_���Ә�!�d�dϐ�҉��#e��z�Aܰ������6�W`��L�5�-"G��MWD�#�,�5����k�T�'Ψ����SdXɎŸ�-b�z�L�wz�Gsy3B�O�� mf�[�����߼���~8m��KB�T���kVm��w�b�}_5�|x��m��t#!�;^@$z���P���T���-Rb';M��Eæ��R�jL�/=�?�OxtMwIW��્�@�D�q�}0�Y֝�I�� ��$Ѹ����?y|����'#�z/�Kw�>'Θ^�=��DB>��bZ�V��ɟ7����D�e�u[}��;��Lj�q��B���A<�Ș���wÎ��/?��]Z(�`���<	�Ui�@��HI�xr����5�I�>��%�D�P0�V8�8p�.u����:���Ki���I�w�4��ӫ�[���U6v��BIY/J�F^�
iЇ�>P�AGY�JЧS^\)��e:�]�9��$Û��l�I�@�<>��8e�7���ɯ�4�fc��'Mɾ{�5�隊p. ����ɬ�<9���'+)��#O��p��D���NO.(#f�����8�wMr�R�Ԣ
:{��a���n\騭���|<A�!����ٖ̙�Dg�^��Ds���'qb�$�C��g1�kb<r�\=�R���-z�'��V^* ���j�o�Z&��
T,o�Y���#E!UCS�/��]��s��B����ٔ#w|+`/�MV�&h��F��W�d���^5�^���~b9r�xZ�~c@���ϗ���ߔ�N�      �   A   x�3�(ʯLM.�WHIU�+M-KT(�LI�H-*��K,V��	�敤���qr�p��qqq j1P      �      x�3�tN,9�9'39�+F��� 4�      �     x����N�0�g�)naLd�MB�b`@B����*W���Ӿ��Ñ�"��]<���;�?�Z�S��#��S��#@�@���R�J�KՈ�HÇ%?l�6;���������xHkO�M�i,�a�`��$ni8�֕�jߞ�׸������������ z�� ����nXp�y�Ʋ�8ZL@��K�{�7
�}��\��o��9��9����
�����C�����ǹ��-K�K]��Ћ\���cXf n�c�3@W�1����7�1b|VEQ� ��N�      �   �  x���Qv�0E�a-d�'Y
�e����7Mhc��̙�~X���u$=�v�Z^bn�Y0W����4��6��s��M�(�G�Qxh�咳;&0�)��ŵM�K��W��.˜}-}���a��#M�V���ᐳu��������A� J��8�8��
�p�f�!o�5�ާ:��6�'�9O�t'm�T"%�	L��I�ȅ�\�Ȋa������;Xߗ�|�������Na'� ��1D�.܅�ȡȡpאâ1�3��4:��L�3��4:��L�3��4:��L�3��4:��L��H	#a$���0F�H	#a$���0F���x�����x(,`�Qa#�mg�`�`�`�`�`�`�`�X�q ā8�@�8*�q ā8!����Y�ɛ%i@���$H;�;�pD����n^������������m=D[��mE[�ͽBu�h�I�+�6�6�������B��{�����_�	����o��3�>�p��v0��h��F;��}�g�k��n&n��}��a��P┳u��� b�!��Q� P%�h�k+���	�c���sM�������l��C����
*T*x� gA΂J�V��RE��(ܢR�U���+5s�p���V���8�,ܢR��.���.�My�&�zn��3��|�<��u>�� �;��      �   5   x�3�tL����,.)JL�/�,�2�-.M,����8=��R�K2ӡ�1z\\\ é         �  x����n�1�g�)��(���5H�ud�B�����ස����[7i��}w��-��4�~������h�a�4���W#4�XS�"Y�JTAK��p ���cC�FD���A�ze���PBN��El9�|��y���a�N+t��织o�k������f�K�)�N�Q
{�^�x-�X[)���̇�:��-[�ر����H����y�A�ת0�6[N/�۝��Ymq���ͻ��F�����I�2&�j�� ��T�Љ*���Ѱ\�x�9JN�峏���LN���+'���<�n���<\��C�0�v^d��{�V�6�Z���A�C��)h�`��jN"���p�b��NG������|e#��v<N����2��{���+�����1Ֆ0)���f������ɘ�g�Ɉ 1bc��[�|�:�2��O�w���p%]?�_x;�*b�)/J*B��I9W��袕 �Z����z^]�1�8=���ܬV�� ��         E   x�-���0Cѳ=K�␦e���U>�'���n�GM��"j�E��`�+ja6ǯ����{�� Eb�            x������ � �            x������ � �     