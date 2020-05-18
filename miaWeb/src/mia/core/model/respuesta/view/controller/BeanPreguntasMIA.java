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
 