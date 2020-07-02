package mia.core.model.entities;
import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cuestionario database table.
 * 
 */
@Entity
@NamedQuery(name="Cuestionario.findAll", query="SELECT c FROM Cuestionario c")
public class Cuestionario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CUESTIONARIO_IDCUESTIONARIO_GENERATOR", sequenceName="SEQ_CUESTIONARIO", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CUESTIONARIO_IDCUESTIONARIO_GENERATOR")
	@Column(name="id_cuestionario")
	private Integer idCuestionario;

	private String descripcion;

	//bi-directional many-to-one association to Dimension
	@OneToMany(mappedBy="cuestionario")
	private List<Dimension> dimensions;

	//bi-directional many-to-one association to Opcion
	@OneToMany(mappedBy="cuestionario")
	private List<Opcion> opcions;

	public Cuestionario() {
	}

	public Integer getIdCuestionario() {
		return this.idCuestionario;
	}

	public void setIdCuestionario(Integer idCuestionario) {
		this.idCuestionario = idCuestionario;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Dimension> getDimensions() {
		return this.dimensions;
	}

	public void setDimensions(List<Dimension> dimensions) {
		this.dimensions = dimensions;
	}

	public Dimension addDimension(Dimension dimension) {
		getDimensions().add(dimension);
		dimension.setCuestionario(this);

		return dimension;
	}

	public Dimension removeDimension(Dimension dimension) {
		getDimensions().remove(dimension);
		dimension.setCuestionario(null);

		return dimension;
	}

	public List<Opcion> getOpcions() {
		return this.opcions;
	}

	public void setOpcions(List<Opcion> opcions) {
		this.opcions = opcions;
	}

	public Opcion addOpcion(Opcion opcion) {
		getOpcions().add(opcion);
		opcion.setCuestionario(this);

		return opcion;
	}

	public Opcion removeOpcion(Opcion opcion) {
		getOpcions().remove(opcion);
		opcion.setCuestionario(null);

		return opcion;
	}

}