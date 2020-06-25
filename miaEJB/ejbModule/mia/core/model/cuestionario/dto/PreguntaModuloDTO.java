package mia.core.model.cuestionario.dto;


import java.util.ArrayList;
import java.util.List;

import mia.core.model.entities.Modulo;
import mia.core.model.entities.Opcionpregunta;
import mia.core.model.entities.Preguntamodulo;
import mia.core.model.entities.Respuestapregunta;



public class PreguntaModuloDTO extends Preguntamodulo{

	List<PreguntaOpcionDTO> preguntaOpcionDTO = new ArrayList<>();
	List<RespuestaPreguntaDTO> respuestaPreguntaDTO= new ArrayList<>();
	
	
	@Override
	public long getIdPregunta() {
		// TODO Auto-generated method stub
		return super.getIdPregunta();
	}

	@Override
	public void setIdPregunta(long idPregunta) {
		// TODO Auto-generated method stub
		super.setIdPregunta(idPregunta);
	}

	@Override
	public String getPregunta() {
		// TODO Auto-generated method stub
		return super.getPregunta();
	}

	@Override
	public void setPregunta(String pregunta) {
		// TODO Auto-generated method stub
		super.setPregunta(pregunta);
	}

	@Override
	public List<Opcionpregunta> getOpcionpreguntas() {
		// TODO Auto-generated method stub
		return super.getOpcionpreguntas();
	}

	@Override
	public void setOpcionpreguntas(List<Opcionpregunta> opcionpreguntas) {
		// TODO Auto-generated method stub
		super.setOpcionpreguntas(opcionpreguntas);
	}

	@Override
	public Opcionpregunta addOpcionpregunta(Opcionpregunta opcionpregunta) {
		// TODO Auto-generated method stub
		return super.addOpcionpregunta(opcionpregunta);
	}

	@Override
	public Opcionpregunta removeOpcionpregunta(Opcionpregunta opcionpregunta) {
		// TODO Auto-generated method stub
		return super.removeOpcionpregunta(opcionpregunta);
	}

	@Override
	public Modulo getModulo() {
		// TODO Auto-generated method stub
		return super.getModulo();
	}

	@Override
	public void setModulo(Modulo modulo) {
		// TODO Auto-generated method stub
		super.setModulo(modulo);
	}

	@Override
	public List<Respuestapregunta> getRespuestapreguntas() {
		// TODO Auto-generated method stub
		return super.getRespuestapreguntas();
	}

	@Override
	public void setRespuestapreguntas(List<Respuestapregunta> respuestapreguntas) {
		// TODO Auto-generated method stub
		super.setRespuestapreguntas(respuestapreguntas);
	}

	@Override
	public Respuestapregunta addRespuestapregunta(Respuestapregunta respuestapregunta) {
		// TODO Auto-generated method stub
		return super.addRespuestapregunta(respuestapregunta);
	}

	@Override
	public Respuestapregunta removeRespuestapregunta(Respuestapregunta respuestapregunta) {
		// TODO Auto-generated method stub
		return super.removeRespuestapregunta(respuestapregunta);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return super.equals(arg0);
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public List<PreguntaOpcionDTO> getPreguntaOpcionDTO() {
		return preguntaOpcionDTO;
	}

	public void setPreguntaOpcionDTO(List<PreguntaOpcionDTO> preguntaOpcionDTO) {
		this.preguntaOpcionDTO = preguntaOpcionDTO;
	}

	public List<RespuestaPreguntaDTO> getRespuestaPreguntaDTO() {
		return respuestaPreguntaDTO;
	}

	public void setRespuestaPreguntaDTO(List<RespuestaPreguntaDTO> respuestaPreguntaDTO) {
		this.respuestaPreguntaDTO = respuestaPreguntaDTO;
	}

	
	
	
	
}
