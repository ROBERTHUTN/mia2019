package mia.core.model.cuestionario.dto;

import java.util.List;

import mia.core.model.entities.Cuestionario;
import mia.core.model.entities.Dimension;
import mia.core.model.entities.Opcion;

public class CuestionarioDTO extends Cuestionario{
private List<DimensionDTO>listaDimensionesDto;
	@Override
	public Integer getIdCuestionario() {
		
		return super.getIdCuestionario();
	}

	@Override
	public void setIdCuestionario(Integer idCuestionario) {
		
		super.setIdCuestionario(idCuestionario);
	}

	@Override
	public String getDescripcion() {
		
		return super.getDescripcion();
	}

	@Override
	public void setDescripcion(String descripcion) {
		
		super.setDescripcion(descripcion);
	}

	@Override
	public List<Dimension> getDimensions() {
		
		return super.getDimensions();
	}

	@Override
	public void setDimensions(List<Dimension> dimensions) {
		
		super.setDimensions(dimensions);
	}

	@Override
	public Dimension addDimension(Dimension dimension) {
		
		return super.addDimension(dimension);
	}

	@Override
	public Dimension removeDimension(Dimension dimension) {
		
		return super.removeDimension(dimension);
	}

	@Override
	public List<Opcion> getOpcions() {
		
		return super.getOpcions();
	}

	@Override
	public void setOpcions(List<Opcion> opcions) {
		
		super.setOpcions(opcions);
	}

	@Override
	public Opcion addOpcion(Opcion opcion) {
		
		return super.addOpcion(opcion);
	}

	@Override
	public Opcion removeOpcion(Opcion opcion) {
		
		return super.removeOpcion(opcion);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {	
		return super.clone();
	}
	@Override
	public boolean equals(Object obj) {
		
		return super.equals(obj);
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
	public List<DimensionDTO> getListaDimensionesDto() {
		return listaDimensionesDto;
	}
	public void setListaDimensionesDto(List<DimensionDTO> listaDimensionesDto) {
		this.listaDimensionesDto = listaDimensionesDto;
	}

}
