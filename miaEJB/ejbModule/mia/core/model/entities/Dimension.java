package mia.core.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the dimension database table.
 * 
 */
@Entity
@NamedQuery(name="Dimension.findAll", query="SELECT d FROM Dimension d")
public class Dimension implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DIMENSION_IDDIMENSION_GENERATOR", sequenceName="SEQ_DIMENSION", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DIMENSION_IDDIMENSION_GENERATOR")
	@Column(name="id_dimension")
	private Integer idDimension;

	private String descripcion;

	//bi-directional many-to-one association to Cuestionario
	@ManyToOne
	@JoinColumn(name="id_cuestionario")
	private Cuestionario cuestionario;

	//bi-directional many-to-one association to DimensionPregunta
	@OneToMany(mappedBy="dimension")
	private List<DimensionPregunta> dimensionPreguntas;

	//bi-directional many-to-one association to Reporte
	@OneToMany(mappedBy="dimension")
	private List<Reporte> reportes;

	public Dimension() {
	}

	public Integer getIdDimension() {
		return this.idDimension;
	}

	public void setIdDimension(Integer idDimension) {
		this.idDimension = idDimension;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Cuestionario getCuestionario() {
		return this.cuestionario;
	}

	public void setCuestionario(Cuestionario cuestionario) {
		this.cuestionario = cuestionario;
	}

	public List<DimensionPregunta> getDimensionPreguntas() {
		return this.dimensionPreguntas;
	}

	public void setDimensionPreguntas(List<DimensionPregunta> dimensionPreguntas) {
		this.dimensionPreguntas = dimensionPreguntas;
	}

	public DimensionPregunta addDimensionPregunta(DimensionPregunta dimensionPregunta) {
		getDimensionPreguntas().add(dimensionPregunta);
		dimensionPregunta.setDimension(this);

		return dimensionPregunta;
	}

	public DimensionPregunta removeDimensionPregunta(DimensionPregunta dimensionPregunta) {
		getDimensionPreguntas().remove(dimensionPregunta);
		dimensionPregunta.setDimension(null);

		return dimensionPregunta;
	}

	public List<Reporte> getReportes() {
		return this.reportes;
	}

	public void setReportes(List<Reporte> reportes) {
		this.reportes = reportes;
	}

	public Reporte addReporte(Reporte reporte) {
		getReportes().add(reporte);
		reporte.setDimension(this);

		return reporte;
	}

	public Reporte removeReporte(Reporte reporte) {
		getReportes().remove(reporte);
		reporte.setDimension(null);

		return reporte;
	}

}