package mia.core.model.cuestionario.dto;

import java.util.ArrayList;
import java.util.List;

import mia.core.model.entities.Dimension;
import mia.core.model.entities.DimensionPregunta;
import mia.core.model.entities.Opcion;
import mia.core.model.entities.Pregunta;

public class DimensionPreguntaDTO extends DimensionPregunta{
	private int valor ;
	private List<Opcion>listaOpciones=new ArrayList<>();
	
	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	@Override
	public long getIdPreguntaDimension() {
		
		return super.getIdPreguntaDimension();
	}

	@Override
	public void setIdPreguntaDimension(long idPreguntaDimension) {
		
		super.setIdPreguntaDimension(idPreguntaDimension);
	}

	@Override
	public Dimension getDimension() {
		
		return super.getDimension();
	}

	@Override
	public void setDimension(Dimension dimension) {
		
		super.setDimension(dimension);
	}

	@Override
	public Pregunta getPregunta() {
		
		return super.getPregunta();
	}

	@Override
	public void setPregunta(Pregunta pregunta) {
		
		super.setPregunta(pregunta);
	}

	public List<Opcion> getListaOpciones() {
		return listaOpciones;
	}

	public void setListaOpciones(List<Opcion> listaOpciones) {
		this.listaOpciones = listaOpciones;
	}

}
