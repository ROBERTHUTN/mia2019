package mia.core.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the opcionpregunta database table.
 * 
 */
@Entity
@NamedQuery(name="Opcionpregunta.findAll", query="SELECT o FROM Opcionpregunta o")
public class Opcionpregunta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="OPCIONPREGUNTA_IDOPCIONPREGUNTA_GENERATOR", sequenceName="SEQ_OPCIONPREGUNTA", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="OPCIONPREGUNTA_IDOPCIONPREGUNTA_GENERATOR")
	@Column(name="id_opcionpregunta")
	private long idOpcionpregunta;

	private String opcion;

	//bi-directional many-to-one association to Preguntamodulo
	@ManyToOne
	@JoinColumn(name="id_pregunta")
	private Preguntamodulo preguntamodulo;

	public Opcionpregunta() {
	}

	public long getIdOpcionpregunta() {
		return this.idOpcionpregunta;
	}

	public void setIdOpcionpregunta(long idOpcionpregunta) {
		this.idOpcionpregunta = idOpcionpregunta;
	}

	public String getOpcion() {
		return this.opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public Preguntamodulo getPreguntamodulo() {
		return this.preguntamodulo;
	}

	public void setPreguntamodulo(Preguntamodulo preguntamodulo) {
		this.preguntamodulo = preguntamodulo;
	}

}