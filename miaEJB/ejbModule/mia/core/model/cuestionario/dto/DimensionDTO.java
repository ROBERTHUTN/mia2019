package mia.core.model.cuestionario.dto;

import java.util.List;

import mia.core.model.entities.Cuestionario;
import mia.core.model.entities.Dimension;
import mia.core.model.entities.DimensionPregunta;
import mia.core.model.entities.Reporte;

public class DimensionDTO extends Dimension{
	
	private List<DimensionPreguntaDTO> listaDimensionesPreguntaDto;

	@Override
	public Integer getIdDimension() {		
		return super.getIdDimension();
	}
	@Override
	public void setIdDimension(Integer idDimension) {	
		super.setIdDimension(idDimension);
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
	public Cuestionario getCuestionario() {
		
		return super.getCuestionario();
	}

	@Override
	public void setCuestionario(Cuestionario cuestionario) {
		
		super.setCuestionario(cuestionario);
	}

	@Override
	public List<DimensionPregunta> getDimensionPreguntas() {
		
		return super.getDimensionPreguntas();
	}

	@Override
	public void setDimensionPreguntas(List<DimensionPregunta> dimensionPreguntas) {
		
		super.setDimensionPreguntas(dimensionPreguntas);
	}

	@Override
	public DimensionPregunta addDimensionPregunta(DimensionPregunta dimensionPregunta) {
		
		return super.addDimensionPregunta(dimensionPregunta);
	}

	@Override
	public DimensionPregunta removeDimensionPregunta(DimensionPregunta dimensionPregunta) {
		
		return super.removeDimensionPregunta(dimensionPregunta);
	}

	@Override
	public List<Reporte> getReportes() {
		
		return super.getReportes();
	}

	@Override
	public void setReportes(List<Reporte> reportes) {
		
		super.setReportes(reportes);
	}

	@Override
	public Reporte addReporte(Reporte reporte) {
		
		return super.addReporte(reporte);
	}

	@Override
	public Reporte removeReporte(Reporte reporte) {
		
		return super.removeReporte(reporte);
	}


	
	public List<DimensionPreguntaDTO> getListaDimensionesPreguntaDto() {
		return listaDimensionesPreguntaDto;
	}

	public void setListaDimensionesPreguntaDto(List<DimensionPreguntaDTO> listaDimensionesPreguntaDto) {
		this.listaDimensionesPreguntaDto = listaDimensionesPreguntaDto;
	}

}
