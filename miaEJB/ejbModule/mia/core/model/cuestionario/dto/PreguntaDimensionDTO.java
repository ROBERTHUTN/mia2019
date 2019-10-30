package mia.core.model.cuestionario.dto;

import mia.core.model.entities.Dimension;
import mia.core.model.entities.DimensionPregunta;
import mia.core.model.entities.Pregunta;

public class PreguntaDimensionDTO extends DimensionPregunta{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private int valor ;


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
@Override
protected Object clone() throws CloneNotSupportedException {
	
	return super.clone();
}
@Override
public boolean equals(Object arg0) {
	
	return super.equals(arg0);
}
@Override
protected void finalize() throws Throwable {
	
	super.finalize();
}
@Override
public int hashCode() {
	
	return super.hashCode();
}
@Override
public String toString() {
	
	return super.toString();
}
public int getValor() {
	return valor;
}
public void setValor(int valor) {
	this.valor = valor;
}

}
