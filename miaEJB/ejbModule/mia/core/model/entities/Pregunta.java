package mia.core.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the pregunta database table.
 * 
 */
@Entity
@NamedQuery(name="Pregunta.findAll", query="SELECT p FROM Pregunta p")
public class Pregunta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PREGUNTA_IDPREGUNTA_GENERATOR", sequenceName="SEQ_PREGUNTA", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PREGUNTA_IDPREGUNTA_GENERATOR")
	@Column(name="id_pregunta")
	private Integer idPregunta;

	private String descripcion;

	//bi-directional many-to-one association to DimensionPregunta
	@OneToMany(mappedBy="pregunta")
	private List<DimensionPregunta> dimensionPreguntas;

	public Pregunta() {
	}

	public Integer getIdPregunta() {
		return this.idPregunta;
	}

	public void setIdPregunta(Integer idPregunta) {
		this.idPregunta = idPregunta;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<DimensionPregunta> getDimensionPreguntas() {
		return this.dimensionPreguntas;
	}

	public void setDimensionPreguntas(List<DimensionPregunta> dimensionPreguntas) {
		this.dimensionPreguntas = dimensionPreguntas;
	}

	public DimensionPregunta addDimensionPregunta(DimensionPregunta dimensionPregunta) {
		getDimensionPreguntas().add(dimensionPregunta);
		dimensionPregunta.setPregunta(this);

		return dimensionPregunta;
	}

	public DimensionPregunta removeDimensionPregunta(DimensionPregunta dimensionPregunta) {
		getDimensionPreguntas().remove(dimensionPregunta);
		dimensionPregunta.setPregunta(null);

		return dimensionPregunta;
	}

}