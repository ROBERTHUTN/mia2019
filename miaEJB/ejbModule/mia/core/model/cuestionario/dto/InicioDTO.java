package mia.core.model.cuestionario.dto;

import java.util.List;

public class InicioDTO {
	private int idCuestionario;
	private int idDimension;
	private long  idDimensionPregunta;
	private List<CuestionarioDTO>listaCuestionariosDto;
	public int getIdCuestionario() {
		return idCuestionario;
	}
	public void setIdCuestionario(int idCuestionario) {
		this.idCuestionario = idCuestionario;
	}
	public int getIdDimension() {
		return idDimension;
	}
	public void setIdDimension(int idDimension) {
		this.idDimension = idDimension;
	}
	public long getIdDimensionPregunta() {
		return idDimensionPregunta;
	}
	public void setIdDimensionPregunta(long idDimensionPregunta) {
		this.idDimensionPregunta = idDimensionPregunta;
	}
	public List<CuestionarioDTO> getListaCuestionariosDto() {
		return listaCuestionariosDto;
	}
	public void setListaCuestionariosDto(List<CuestionarioDTO> listaCuestionariosDto) {
		this.listaCuestionariosDto = listaCuestionariosDto;
	}
	
}
