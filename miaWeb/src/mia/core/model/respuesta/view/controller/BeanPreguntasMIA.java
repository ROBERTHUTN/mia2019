package mia.core.model.respuesta.view.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.commons.collections.map.StaticBucketMap;

import java.awt.List;
import java.io.Serializable;

@Named
@ViewScoped
public class BeanPreguntasMIA implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String respuestaEstres;
	private String respuestaGlp;
	
	private int autoconciencia;
	private int automotivacion;
	private int controlEmociones;
	private int interpersonal;
	private int asesoriaEmocional;
	
	

    String [] inteligenciaEmocional= {
    		" Al momento, identifico cambios fisiológicos en mi cuerpo",
    		" Me relajo en situaciones de presión",
    		" Puedo actuar de modo productivo cuando me enfado",
    		" Si me siento ansioso actúo de modo productivo",
    		" Cuando estoy enfadado me tranquilizo rápido",
    		" Asocio mis diferentes estados físicos con mis emociones",
    		" Controlo mis estados emocionales a través de mi diálogo interior",
    		"Puedo comunicar mis sentimientos fácilmente",
    		"Tengo sentimientos negativos, sin angustiarme",
    		"Mantengo la calma cuando soy parte  del enfado de otros",
    		"Generalmente sé cuándo tengo pensamientos negativos",
    		" Casi siempre sé cuándo mi discurso interior es positivo",
    		" En general, sé cuando empiezo a enfadarme",
    		" Muchas veces sé cómo interpretar los acontecimientos",
    		" Usualmente conozco qué sentimientos tengo",
    		" Considero que me comunico adecuadamente con los demás",
    		" Puedo identificar la información que puede influir sobre mis interpretaciones",
    		" Identifico fácilmente mis cambios de humor",
    		" Conozco cuando debo ponerme a la defensiva",
    		" Medito el impacto de mi comportamiento sobre los demás",
    		" Cuando no me he comunicado bien, me doy cuenta",
    		" Puedo ponerme proseguir cuando desee",
    		" Me recupero rápido después de una situación problemática",
    		" Completo tareas difíciles dentro del tiempo previsto",
    		" Puedo generar  energía positiva cuando realizo un trabajo poco interesante",
    		" Soy Capaz de abandonar o cambiar hábitos inútiles",
    		" Puedo desarrollar pautas de conducta más productivas",
    		" Cumplo con mi palabra",
    		"Resuelvo conflictos con facilidad",
    		" Logro acuerdos con los demás con facilidad",
    		" Puedo mediar en los conflictos con los demás",
    		" Utilizo técnicas de comunicación eficaces",
    		" Expreso lo que siento en un grupo",
    		" Soy capaz de influir sobre los demás de forma directa o indirecta",
    		"Fomento la confianza con los demás",
    		"Construyo grupos de apoyo",
    		"Hago que los demás se sientan bien",
    		" Determino con facilidad  los sentimientos de los demás",
    		" Me doy cuenta de la angustia de los demás",
    		" Ayudo a los demás a controlar sus emociones",
    		" Trato de comprender a los demás",
    		" Entablo conversaciones íntimas con otras personas",
    		" Soy capaz de ayudar al grupo a controlar sus emociones",
    		" Soy capaz de detectar incongruencias entre las emociones o sentimientos de los demás y sus conductas."

    };
    
    String [] nivelEstres = {
    		"Al  menos cuatro noches a la semana duermo de siete a ocho horas.",
    		"En 50 kilómetros a la redonda poseo por lo menos una familia en que pueda confiar.",
    		"Por lo menos 2 veces por semana hago ejercicios hasta sudar.",
    		"Fumo menos de media cajetilla de cigarrillos al día.",
    		"Tomo menos de cinco tragos (de bebidas alcohólicas) a la semana.",
    		"Tengo el peso apropiado para mi estatura.",
    		"Mis ingresos satisfacen mis gastos fundamentales",
    		"Asisto regularmente a actividades sociales.",
    		"Tengo una red (grupo) de amigos conocidos.",
    		"Tengo uno o más amigos a quienes puedo confiarle, mis problemas personales.",
    		"Tengo buena salud (es decir, mi vista, oído, dentadura, están en buenas 	condiciones).",
    		"Converso regularmente sobre problemas domésticos(es decir, sobre tareas del hogar, dinero, problemas de la vida cotidiana) con las personas que conviven conmigo.",
    		"Por lo menos una vez a la semana hago algo para divertirme.",
    		"Soy capaz de organizar efectivamente  mi tiempo.",
    		"Tomo al menos tres tazas de café, té o refrescos al día.",
    		"Durante el día me dedico a mí mismo, al menos un rato de tranquilidad."
    };
    
    
    String [] glp = {
    		" Sugiero que nuestro equipo de trabajo se concentre en los objetivos y las metas.",
    		"Me aseguro que el equipo observe datos técnicos o información al respecto.",
    		"Me centro en el campo de acción.",
    		"Actúo sin pensar mucho",
    		"Busco que todos se involucren en la discusión para homologar estándares.",
    		"Veo las diferencias como la base de un posible cambio de dirección del equipo.",
    		"Lo dejo a la casualidad.",
    		"Ajustarse a su equipo.",
    		"Asegurarse de establecer una ética de represalias.",
    		"Tomar el control de las situaciones siempre.",
    		"Tolerar las diferencias individuales.",
    		"Asegurarse que todos se involucren.",
    		"Potenciar el crecimiento de los miembros de su equipo.",
    		"Valorar a los miembros según su mérito.",
    		"Comunico qué hacer a los demás.",
    		"Pierdo la paciencia y altero mi comportamiento.",
    		"Me centro en lo establecido y esperado.",
    		"Acepto solo las consideraciones de los expertos.",
    		"Me baso en los principios y el juicio.",
    		"Enfatizo en la escucha, la retroalimentación y la participación.",
    		"Rompo con los paradigmas del pensamiento y la acción.",
    		"Construyendo alternativas pero con tendencia a lo establecido",
    		"Sin respeto a las reglas.",
    		"Sigo fervientemente las reglas.",
    		"Desafío las normas del grupo.",
    		"Veo las reglas como pérdida de libertad.",
    		"Reconozco la importancia de los principios y el juicio.",
    		"Perturbo los paradigmas del pensamiento y la acción.",
    		"Colaborativo.",
    		"Impulsivo y dominante.",
    		"Con humor hostil y  manipulador.",
    		"Pacificador de conflictos tanto internos como externos.",
    		"Perfeccionista.",
    		"Consciente de mis virtudes y defectos.",
    		"Analítico, metafórico",
    		"Según el contexto actual y su historia.",
    		"Valorando la inmediatez.",
    		"De manera temporal estratégica.",
    		"Sin planificación.",
    		"Según el contexto y la contingencia.",
    		"Por el momento histórico particular.",
    		"Y al  tiempo y los acontecimientos como algo simbólico."    		
    };

	
	

} 
 