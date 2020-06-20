package mia.core.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the preguntamodulo database table.
 * 
 */
@Entity
@NamedQuery(name="Preguntamodulo.findAll", query="SELECT p FROM Preguntamodulo p")
public class Preguntamodulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PREGUNTAMODULO_IDPREGUNTA_GENERATOR", sequenceName="SEQ_PREGUNTAMODULO", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PREGUNTAMODULO_IDPREGUNTA_GENERATOR")
	@Column(name="id_pregunta")
	private long idPregunta;

	private String pregunta;

	//bi-directional many-to-one association to Opcionpregunta
	@OneToMany(mappedBy="preguntamodulo")
	private List<Opcionpregunta> opcionpreguntas;

	//bi-directional many-to-one association to Modulo
	@ManyToOne
	@JoinColumn(name="id_modulo")
	private Modulo modulo;

	//bi-directional many-to-one association to Respuestapregunta
	@OneToMany(mappedBy="preguntamodulo")
	private List<Respuestapregunta> respuestapreguntas;

	public Preguntamodulo() {
	}

	public long getIdPregunta() {
		return this.idPregunta;
	}

	public void setIdPregunta(long idPregunta) {
		this.idPregunta = idPregunta;
	}

	public String getPregunta() {
		return this.pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public List<Opcionpregunta> getOpcionpreguntas() {
		return this.opcionpreguntas;
	}

	public void setOpcionpreguntas(List<Opcionpregunta> opcionpreguntas) {
		this.opcionpreguntas = opcionpreguntas;
	}

	public Opcionpregunta addOpcionpregunta(Opcionpregunta opcionpregunta) {
		getOpcionpreguntas().add(opcionpregunta);
		opcionpregunta.setPreguntamodulo(this);

		return opcionpregunta;
	}

	public Opcionpregunta removeOpcionpregunta(Opcionpregunta opcionpregunta) {
		getOpcionpreguntas().remove(opcionpregunta);
		opcionpregunta.setPreguntamodulo(null);

		return opcionpregunta;
	}

	public Modulo getModulo() {
		return this.modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public List<Respuestapregunta> getRespuestapreguntas() {
		return this.respuestapreguntas;
	}

	public void setRespuestapreguntas(List<Respuestapregunta> respuestapreguntas) {
		this.respuestapreguntas = respuestapreguntas;
	}

	public Respuestapregunta addRespuestapregunta(Respuestapregunta respuestapregunta) {
		getRespuestapreguntas().add(respuestapregunta);
		respuestapregunta.setPreguntamodulo(this);

		return respuestapregunta;
	}

	public Respuestapregunta removeRespuestapregunta(Respuestapregunta respuestapregunta) {
		getRespuestapreguntas().remove(respuestapregunta);
		respuestapregunta.setPreguntamodulo(null);

		return respuestapregunta;
	}

}