package mia.core.model.usuario.dto;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class BateriaServiceDto {
	
	public List<DimensionBateriaDto2>inicializarPreguntasPadres(){
		List<DimensionBateriaDto2> listaPadre=new ArrayList<DimensionBateriaDto2>();
		for (int i = 0; i < 6; i++) {
			List<BateriaDto>baterias=inicializarPreguntasBateria(i);
			List<BateriaDto> inicializarResp=new ArrayList<BateriaDto>();
			if (i==0) {
			
				listaPadre.add(new DimensionBateriaDto2(1, "a)	Para tomar decisiones", baterias,inicializarResp));
				}else{ 
					if(i==1){
						listaPadre.add(new DimensionBateriaDto2(2, "b)	Creo que la funci�n del l�der es", baterias,inicializarResp));
						
				}else {
					if(i==2){
						listaPadre.add(new DimensionBateriaDto2(3, "c)	Cuando las cosas no van bien", baterias,inicializarResp));
						
				}else {
					if(i==3){
						listaPadre.add(new DimensionBateriaDto2(4, "d)	Me gusta actuar", baterias,inicializarResp));
						
				}else {
					if(i==4){
						listaPadre.add(new DimensionBateriaDto2(5, "e)	Las personas me describen", baterias,inicializarResp));
						
				}else {
					listaPadre.add(new DimensionBateriaDto2(6, "f)	Visualizo el horizonte de trabajo", baterias,inicializarResp));
				}
		}
				}
				}
				}
		}
	return	listaPadre ;
	}
	public List<BateriaDto>inicializarPreguntasBateria(int  posicion) {
		
		List<BateriaDto>	listaPreguntas =new ArrayList<BateriaDto>();
		if (posicion==0) {
	
		listaPreguntas.add(new BateriaDto("Sugiero que nuestro equipo de trabajo se concentre en los objetivos y las metas",
				"a1", 0, "a"));
		listaPreguntas.add(new BateriaDto("Me aseguro que el equipo observe datos t�cnicos o informaci�n al respecto",
				"a2", 0, "a"));
		listaPreguntas.add(new BateriaDto("Me centro en el campo de acci�n",
				"a3", 0, "a"));
		listaPreguntas.add(new BateriaDto("Act�o sin pensar mucho",
				"a4", 0, "a"));
		listaPreguntas.add(new BateriaDto("Busco que todos se involucren en la discusi�n para homologar est�ndares",
				"a5", 0, "a"));
		listaPreguntas.add(new BateriaDto("Veo las diferencias como la base de un posible cambio de direcci�n del equipo",
				"a6", 0, "a"));
		listaPreguntas.add(new BateriaDto("Lo dejo a la casualidad",
				"a7", 0, "a"));	
		return listaPreguntas;
		}else{ 
			if(posicion==1){
				listaPreguntas.add(new BateriaDto("Ajustarse a su equipo",
						"b1", 0, "b"));
				listaPreguntas.add(new BateriaDto("Asegurarse de establecer una �tica de represalias",
						"b2", 0, "b"));
				listaPreguntas.add(new BateriaDto("Tomar el control de las situaciones siempre",
						"b3", 0, "b"));
				listaPreguntas.add(new BateriaDto("Tolerar las diferencias individuales",
						"b4", 0, "b"));
				listaPreguntas.add(new BateriaDto("Asegurarse que todos se involucren",
						"b5", 0, "b"));
				listaPreguntas.add(new BateriaDto("Potenciar el crecimiento de los miembros de su equipo.",
						"b6", 0, "b"));
				listaPreguntas.add(new BateriaDto("Valorar a los miembros seg�n su m�rito",
						"b7", 0, "b"));	
				return listaPreguntas;
		}else {
			if(posicion==2){
				listaPreguntas.add(new BateriaDto("Comunico qu� hacer a los dem�s",
						"c1", 0, "c"));
				listaPreguntas.add(new BateriaDto("Pierdo la paciencia y altero mi comportamiento",
						"c2", 0, "c"));
				listaPreguntas.add(new BateriaDto("Me centro en lo establecido y esperado",
						"c3", 0, "c"));
				listaPreguntas.add(new BateriaDto("Acepto solo las consideraciones de los expertos",
						"c4", 0, "c"));
				listaPreguntas.add(new BateriaDto("Me baso en los principios y el juicio",
						"c5", 0, "c"));
				listaPreguntas.add(new BateriaDto("Enfatizo en la escucha, la retroalimentaci�n y la participaci�n",
						"c6", 0, "c"));
				listaPreguntas.add(new BateriaDto("Rompo con los paradigmas del pensamiento y la acci�n",
						"c7", 0, "c"));	
				return listaPreguntas;
		}else {
			if(posicion==3){
				listaPreguntas.add(new BateriaDto("Construyendo alternativas pero con tendencia a lo establecido.",
						"d1", 0, "d"));
				listaPreguntas.add(new BateriaDto("Sin respeto a las reglas",
						"d2", 0, "d"));
				listaPreguntas.add(new BateriaDto("Sigo fervientemente las reglas ",
						"d3", 0, "d"));
				listaPreguntas.add(new BateriaDto("Desaf�o las normas del grupo",
						"d4", 0, "d"));
				listaPreguntas.add(new BateriaDto("Veo las reglas como p�rdida de libertad",
						"d5", 0, "d"));
				listaPreguntas.add(new BateriaDto("Reconozco la importancia de los principios y el juicio",
						"d6", 0, "d"));
				listaPreguntas.add(new BateriaDto("Perturbo los paradigmas del pensamiento y la acci�n",
						"d7", 0, "d"));	
				return listaPreguntas;
		}else {
			if(posicion==4){
				listaPreguntas.add(new BateriaDto("Colaborativo",
						"e1", 0, "e"));
				listaPreguntas.add(new BateriaDto("Impulsivo y dominante",
						"e2", 0, "e"));
				listaPreguntas.add(new BateriaDto("Con humor hostil y  manipulador ",
						"e3", 0, "e"));
				listaPreguntas.add(new BateriaDto("Pacificador de conflictos tanto internos como externos",
						"e4", 0, "e"));
				listaPreguntas.add(new BateriaDto("Perfeccionista",
						"e5", 0, "e"));
				listaPreguntas.add(new BateriaDto("Consciente de mis virtudes y defectos",
						"e6", 0, "e"));
				listaPreguntas.add(new BateriaDto("Anal�tico, metaf�rico ",
						"e7", 0, "e"));	
				return listaPreguntas;
		}else {
			listaPreguntas.add(new BateriaDto("Seg�n el contexto actual y su  historia.",
					"f1", 0, "f"));
			listaPreguntas.add(new BateriaDto("Valorando la inmediatez",
					"f2", 0, "f"));
			listaPreguntas.add(new BateriaDto("De manera temporal estrat�gica",
					"f3", 0, "f"));
			listaPreguntas.add(new BateriaDto("Sin planificaci�n ",
					"f4", 0, "f"));
			listaPreguntas.add(new BateriaDto("Seg�n el contexto y la contingencia.",
					"f5", 0, "f"));
			listaPreguntas.add(new BateriaDto("Por el momento hist�rico particular.",
					"f6", 0, "f"));
			listaPreguntas.add(new BateriaDto("Y al  tiempo y los acontecimientos como algo simb�lico. ",
					"f7", 0, "f"));	
		}
			
		}
		}
		}
			
		}
		
	return listaPreguntas;
	}
}
