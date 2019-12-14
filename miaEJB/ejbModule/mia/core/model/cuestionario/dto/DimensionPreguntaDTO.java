package mia.core.model.cuestionario.dto;

import mia.core.model.entities.Dimension;
import mia.core.model.entities.DimensionPregunta;
import mia.core.model.entities.Pregunta;

public class DimensionPreguntaDTO extends DimensionPregunta{
	private int valor ;
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

}
