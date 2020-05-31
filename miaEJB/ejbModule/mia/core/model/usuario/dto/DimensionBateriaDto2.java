package mia.core.model.usuario.dto;

import java.util.List;


public class DimensionBateriaDto2 {
	private int id_dimension_pregunta;
	private String nombre;
	private List<BateriaDto> listaPreguntas;
	private List<BateriaDto> listaRespuestas;
	
	public DimensionBateriaDto2(int id_dimension_pregunta, String nombre, List<BateriaDto> listaPreguntas, List<BateriaDto> listaRespuestas) {
		this.id_dimension_pregunta = id_dimension_pregunta;
		this.nombre = nombre;
		this.listaPreguntas = listaPreguntas;
		this.listaRespuestas = listaRespuestas;
	}
	
	public int getId_dimension_pregunta() {
		return id_dimension_pregunta;
	}
	public void setId_dimension_pregunta(int id_dimension_pregunta) {
		this.id_dimension_pregunta = id_dimension_pregunta;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<BateriaDto> getListaPreguntas() {
		return listaPreguntas;
	}
	public void setListaPreguntas(List<BateriaDto> listaPreguntas) {
		this.listaPreguntas = listaPreguntas;
	}

	public List<BateriaDto> getListaRespuestas() {
		return listaRespuestas;
	}

	public void setListaRespuestas(List<BateriaDto> listaRespuestas) {
		this.listaRespuestas = listaRespuestas;
	}
	
	
}
