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
    		" Al momento, identifico cambios fisiol�gicos en mi cuerpo",
    		" Me relajo en situaciones de presi�n",
    		" Puedo actuar de modo productivo cuando me enfado",
    		" Si me siento ansioso act�o de modo productivo",
    		" Cuando estoy enfadado me tranquilizo r�pido",
    		" Asocio mis diferentes estados f�sicos con mis emociones",
    		" Controlo mis estados emocionales a trav�s de mi di�logo interior",
    		"Puedo comunicar mis sentimientos f�cilmente",
    		"Tengo sentimientos negativos, sin angustiarme",
    		"Mantengo la calma cuando soy parte  del enfado de otros",
    		"Generalmente s� cu�ndo tengo pensamientos negativos",
    		" Casi siempre s� cu�ndo mi discurso interior es positivo",
    		" En general, s� cuando empiezo a enfadarme",
    		" Muchas veces s� c�mo interpretar los acontecimientos",
    		" Usualmente conozco qu� sentimientos tengo",
    		" Considero que me comunico adecuadamente con los dem�s",
    		" Puedo identificar la informaci�n que puede influir sobre mis interpretaciones",
    		" Identifico f�cilmente mis cambios de humor",
    		" Conozco cuando debo ponerme a la defensiva",
    		" Medito el impacto de mi comportamiento sobre los dem�s",
    		" Cuando no me he comunicado bien, me doy cuenta",
    		" Puedo ponerme proseguir cuando desee",
    		" Me recupero r�pido despu�s de una situaci�n problem�tica",
    		" Completo tareas dif�ciles dentro del tiempo previsto",
    		" Puedo generar  energ�a positiva cuando realizo un trabajo poco interesante",
    		" Soy Capaz de abandonar o cambiar h�bitos in�tiles",
    		" Puedo desarrollar pautas de conducta m�s productivas",
    		" Cumplo con mi palabra",
    		"Resuelvo conflictos con facilidad",
    		" Logro acuerdos con los dem�s con facilidad",
    		" Puedo mediar en los conflictos con los dem�s",
    		" Utilizo t�cnicas de comunicaci�n eficaces",
    		" Expreso lo que siento en un grupo",
    		" Soy capaz de influir sobre los dem�s de forma directa o indirecta",
    		"Fomento la confianza con los dem�s",
    		"Construyo grupos de apoyo",
    		"Hago que los dem�s se sientan bien",
    		" Determino con facilidad  los sentimientos de los dem�s",
    		" Me doy cuenta de la angustia de los dem�s",
    		" Ayudo a los dem�s a controlar sus emociones",
    		" Trato de comprender a los dem�s",
    		" Entablo conversaciones �ntimas con otras personas",
    		" Soy capaz de ayudar al grupo a controlar sus emociones",
    		" Soy capaz de detectar incongruencias entre las emociones o sentimientos de los dem�s y sus conductas."

    };
    
    String [] nivelEstres = {
    		"Al  menos cuatro noches a la semana duermo de siete a ocho horas.",
    		"En 50 kil�metros a la redonda poseo por lo menos una familia en que pueda confiar.",
    		"Por lo menos 2 veces por semana hago ejercicios hasta sudar.",
    		"Fumo menos de media cajetilla de cigarrillos al d�a.",
    		"Tomo menos de cinco tragos (de bebidas alcoh�licas) a la semana.",
    		"Tengo el peso apropiado para mi estatura.",
    		"Mis ingresos satisfacen mis gastos fundamentales",
    		"Asisto regularmente a actividades sociales.",
    		"Tengo una red (grupo) de amigos conocidos.",
    		"Tengo uno o m�s amigos a quienes puedo confiarle, mis problemas personales.",
    		"Tengo buena salud (es decir, mi vista, o�do, dentadura, est�n en buenas 	condiciones).",
    		"Converso regularmente sobre problemas dom�sticos(es decir, sobre tareas del hogar, dinero, problemas de la vida cotidiana) con las personas que conviven conmigo.",
    		"Por lo menos una vez a la semana hago algo para divertirme.",
    		"Soy capaz de organizar efectivamente  mi tiempo.",
    		"Tomo al menos tres tazas de caf�, t� o refrescos al d�a.",
    		"Durante el d�a me dedico a m� mismo, al menos un rato de tranquilidad."
    };
    
    
    String [] glp = {
    		" Sugiero que nuestro equipo de trabajo se concentre en los objetivos y las metas.",
    		"Me aseguro que el equipo observe datos t�cnicos o informaci�n al respecto.",
    		"Me centro en el campo de acci�n.",
    		"Act�o sin pensar mucho",
    		"Busco que todos se involucren en la discusi�n para homologar est�ndares.",
    		"Veo las diferencias como la base de un posible cambio de direcci�n del equipo.",
    		"Lo dejo a la casualidad.",
    		"Ajustarse a su equipo.",
    		"Asegurarse de establecer una �tica de represalias.",
    		"Tomar el control de las situaciones siempre.",
    		"Tolerar las diferencias individuales.",
    		"Asegurarse que todos se involucren.",
    		"Potenciar el crecimiento de los miembros de su equipo.",
    		"Valorar a los miembros seg�n su m�rito.",
    		"Comunico qu� hacer a los dem�s.",
    		"Pierdo la paciencia y altero mi comportamiento.",
    		"Me centro en lo establecido y esperado.",
    		"Acepto solo las consideraciones de los expertos.",
    		"Me baso en los principios y el juicio.",
    		"Enfatizo en la escucha, la retroalimentaci�n y la participaci�n.",
    		"Rompo con los paradigmas del pensamiento y la acci�n.",
    		"Construyendo alternativas pero con tendencia a lo establecido",
    		"Sin respeto a las reglas.",
    		"Sigo fervientemente las reglas.",
    		"Desaf�o las normas del grupo.",
    		"Veo las reglas como p�rdida de libertad.",
    		"Reconozco la importancia de los principios y el juicio.",
    		"Perturbo los paradigmas del pensamiento y la acci�n.",
    		"Colaborativo.",
    		"Impulsivo y dominante.",
    		"Con humor hostil y  manipulador.",
    		"Pacificador de conflictos tanto internos como externos.",
    		"Perfeccionista.",
    		"Consciente de mis virtudes y defectos.",
    		"Anal�tico, metaf�rico",
    		"Seg�n el contexto actual y su historia.",
    		"Valorando la inmediatez.",
    		"De manera temporal estrat�gica.",
    		"Sin planificaci�n.",
    		"Seg�n el contexto y la contingencia.",
    		"Por el momento hist�rico particular.",
    		"Y al  tiempo y los acontecimientos como algo simb�lico."    		
    };

	
	

} 
 