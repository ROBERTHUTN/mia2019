package mia.core.model.usuario.dto;

public class BateriaDto {
	private String pregunta;
	private String literal;
	private int posicion;
	private String tipo;
	public BateriaDto(String pregunta, String literal, int posicion, String tipo) {
	
		this.pregunta = pregunta;
		this.literal = literal;
		this.posicion = posicion;
		this.tipo= tipo;
	}
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public String getLiteral() {
		return literal;
	}
	public void setLiteral(String literal) {
		this.literal = literal;
	}
	public int getPosicion() {
		return posicion;
	}
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	
	
}
