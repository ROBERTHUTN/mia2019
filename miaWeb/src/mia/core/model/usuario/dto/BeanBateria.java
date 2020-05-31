package mia.core.model.usuario.dto;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.DragDropEvent;

import mia.core.model.cuestionario.dto.Car;
import mia.modulos.view.util.JSFUtil;
import java.io.Serializable;

@Named
@SessionScoped
public class BeanBateria implements Serializable {

	private static final long serialVersionUID = 1L;
private List<BateriaDto> bateriasPreguntas;
private List<BateriaDto> bateriasRespuestas;
private String nombre;
private List<DimensionBateriaDto2> listaDimensionPreguntas;
private List<DimensionBateriaDto2> listaDimensionRespuesta;
private List<DimensionBateriaDto2> listaDimensionRespuestaActual;
private DimensionBateriaDto2 dimensionActDto;
private boolean atras;
private boolean adelante;	
private boolean finalizarTest;
private int contador;
	@EJB
	BateriaServiceDto managerBateria;
	
	
public void atras() {
	listaDimensionRespuesta.set(contador, listaDimensionRespuestaActual.get(0));
	contador--;
	estadoActualContador(contador);
	dimensionActDto=listaDimensionRespuesta.get(contador);
	listaDimensionRespuestaActual=new ArrayList<>();
	listaDimensionRespuestaActual.add(dimensionActDto);
}
public void siguiente() {
	listaDimensionRespuesta.set(contador, listaDimensionRespuestaActual.get(0));
	contador++;
	estadoActualContador(contador);
	
	dimensionActDto=listaDimensionRespuesta.get(contador);
	
	listaDimensionRespuestaActual=new ArrayList<>();
	listaDimensionRespuestaActual.add(dimensionActDto);
	
}

public void estadoActualContador(int contadorC) {
	if (listaDimensionRespuesta.isEmpty()) {
		atras = false;
		adelante = false;
		finalizarTest = false;

	} else {
		if (contadorC == 0 && contadorC == (listaDimensionRespuesta.size() - 1)) {
			adelante = false;
			atras = false;
			finalizarTest = true;
		} else {
			if (contadorC > 0 && contadorC < (listaDimensionRespuesta.size() - 1)) {
				adelante = true;
				atras = true;
				finalizarTest = false;
			} else {
				if (contadorC == (listaDimensionRespuesta.size() - 1)) {
					adelante = false;
					atras = true;
					finalizarTest = true;

				} else {
					adelante = true;
					atras = false;

				}
			}
		}

	}

	
}
public void onCarDrop(DragDropEvent event) {
	
	DimensionBateriaDto2 prePact=listaDimensionRespuestaActual.get(contador);
	List<BateriaDto>listapreguntas=prePact.getListaPreguntas();
	List<BateriaDto>listarespuestas=prePact.getListaRespuestas();
	BateriaDto bat= (BateriaDto)event.getData();
	System.out.println(": "+bat.getPregunta());
	listarespuestas.add(bat);
	listapreguntas.remove(bat);
	listaDimensionRespuestaActual.get(contador).setListaPreguntas(listapreguntas);
	System.out.println(listarespuestas.size());
	listaDimensionRespuestaActual.get(contador).setListaRespuestas(listarespuestas);
}
public String PreguntasByDimension() {
	try {
		listaDimensionRespuesta=managerBateria.inicializarPreguntasPadres();
		System.out.println("1:"+listaDimensionRespuesta.size());
		listaDimensionRespuestaActual=new ArrayList<DimensionBateriaDto2>();
		listaDimensionRespuestaActual.add(listaDimensionRespuesta.get(0));
		System.out.println(":- "+listaDimensionRespuestaActual.get(0).getNombre());
		System.out.println(": "+listaDimensionRespuestaActual.get(0).getListaPreguntas().size());
		
		for (BateriaDto p : listaDimensionRespuestaActual.get(0).getListaPreguntas()) {
			System.out.println(p.getPregunta());
		}
		bateriasPreguntas =listaDimensionRespuesta.get(0).getListaPreguntas();
		System.out.println("3");
		contador = 0;
		System.out.println("4");
			estadoActualContador(contador);

			JSFUtil.crearMensajeInfo("Responder las siguientes preguntas");

			return "tipoLiderazgo2?faces-redirect=true";
	
	} catch (Exception e) {
		JSFUtil.crearMensajeError(e.getMessage());
		return "";
	}

}
	public List<BateriaDto> getBateriasRespuestas() {
		return bateriasRespuestas;
	}

	public void setBateriasRespuestas(List<BateriaDto> bateriasRespuestas) {
		this.bateriasRespuestas = bateriasRespuestas;
	}

	public List<DimensionBateriaDto2> getListaDimensionRespuesta() {
		return listaDimensionRespuesta;
	}

	public void setListaDimensionRespuesta(List<DimensionBateriaDto2> listaDimensionRespuesta) {
		this.listaDimensionRespuesta = listaDimensionRespuesta;
	}

	public List<DimensionBateriaDto2> getListaDimensionPreguntas() {
		return listaDimensionPreguntas;
	}

	public void setListaDimensionPreguntas(List<DimensionBateriaDto2> listaDimensionPreguntas) {
		this.listaDimensionPreguntas = listaDimensionPreguntas;
	}

	public List<BateriaDto> getBateriasPreguntas() {
		return bateriasPreguntas;
	}

	public void setBateriasPreguntas(List<BateriaDto> bateriasPreguntas) {
		this.bateriasPreguntas = bateriasPreguntas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isAtras() {
		return atras;
	}

	public void setAtras(boolean atras) {
		this.atras = atras;
	}

	public boolean isAdelante() {
		return adelante;
	}

	public void setAdelante(boolean adelante) {
		this.adelante = adelante;
	}

	public boolean isFinalizarTest() {
		return finalizarTest;
	}

	public void setFinalizarTest(boolean finalizarTest) {
		this.finalizarTest = finalizarTest;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public List<DimensionBateriaDto2> getListaDimensionRespuestaActual() {
		return listaDimensionRespuestaActual;
	}

	public void setListaDimensionRespuestaActual(List<DimensionBateriaDto2> listaDimensionRespuestaActual) {
		this.listaDimensionRespuestaActual = listaDimensionRespuestaActual;
	}
	
	
}
