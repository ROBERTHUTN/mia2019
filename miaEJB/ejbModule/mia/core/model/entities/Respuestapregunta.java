package mia.core.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the respuestapregunta database table.
 * 
 */
@Entity
@NamedQuery(name="Respuestapregunta.findAll", query="SELECT r FROM Respuestapregunta r")
public class Respuestapregunta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RESPUESTAPREGUNTA_IDRESPUESTAPREGUNTA_GENERATOR", sequenceName="SEQ_RESPUESTAPREGUNTA", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RESPUESTAPREGUNTA_IDRESPUESTAPREGUNTA_GENERATOR")
	@Column(name="id_respuestapregunta")
	private long idRespuestapregunta;

	private String respuesta;

	//bi-directional many-to-one association to Preguntamodulo
	@ManyToOne
	@JoinColumn(name="id_pregunta")
	private Preguntamodulo preguntamodulo;

	public Respuestapregunta() {
	}

	public long getIdRespuestapregunta() {
		return this.idRespuestapregunta;
	}

	public void setIdRespuestapregunta(long idRespuestapregunta) {
		this.idRespuestapregunta = idRespuestapregunta;
	}

	public String getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public Preguntamodulo getPreguntamodulo() {
		return this.preguntamodulo;
	}

	public void setPreguntamodulo(Preguntamodulo preguntamodulo) {
		this.preguntamodulo = preguntamodulo;
	}

}