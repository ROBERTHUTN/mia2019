package mia.core.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dimension_pregunta database table.
 * 
 */
@Entity
@Table(name="dimension_pregunta")
@NamedQuery(name="DimensionPregunta.findAll", query="SELECT d FROM DimensionPregunta d")
public class DimensionPregunta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DIMENSION_PREGUNTA_IDPREGUNTADIMENSION_GENERATOR", sequenceName="SEQ_DIMENSION_PREGUNTA", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DIMENSION_PREGUNTA_IDPREGUNTADIMENSION_GENERATOR")
	@Column(name="id_pregunta_dimension")
	private long idPreguntaDimension;

	//bi-directional many-to-one association to Dimension
	@ManyToOne
	@JoinColumn(name="id_dimension")
	private Dimension dimension;

	//bi-directional many-to-one association to Pregunta
	@ManyToOne
	@JoinColumn(name="id_pregunta")
	private Pregunta pregunta;

	public DimensionPregunta() {
	}

	public long getIdPreguntaDimension() {
		return this.idPreguntaDimension;
	}

	public void setIdPreguntaDimension(long idPreguntaDimension) {
		this.idPreguntaDimension = idPreguntaDimension;
	}

	public Dimension getDimension() {
		return this.dimension;
	}

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	public Pregunta getPregunta() {
		return this.pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

}