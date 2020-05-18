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
 