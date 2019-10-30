package mia.core.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the grado_estudio database table.
 * 
 */
@Entity
@Table(name="grado_estudio")
@NamedQuery(name="GradoEstudio.findAll", query="SELECT g FROM GradoEstudio g")
public class GradoEstudio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GRADO_ESTUDIO_IDGRADO_GENERATOR", sequenceName="SEQ_GRADO_ESTUDIO", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GRADO_ESTUDIO_IDGRADO_GENERATOR")
	@Column(name="id_grado")
	private Integer idGrado;

	private String descripcion;

	@Column(name="nombre_grado")
	private String nombreGrado;

	//bi-directional many-to-one association to FichaPersonal
	@OneToMany(mappedBy="gradoEstudio")
	private List<FichaPersonal> fichaPersonals;

	public GradoEstudio() {
	}

	public Integer getIdGrado() {
		return this.idGrado;
	}

	public void setIdGrado(Integer idGrado) {
		this.idGrado = idGrado;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombreGrado() {
		return this.nombreGrado;
	}

	public void setNombreGrado(String nombreGrado) {
		this.nombreGrado = nombreGrado;
	}

	public List<FichaPersonal> getFichaPersonals() {
		return this.fichaPersonals;
	}

	public void setFichaPersonals(List<FichaPersonal> fichaPersonals) {
		this.fichaPersonals = fichaPersonals;
	}

	public FichaPersonal addFichaPersonal(FichaPersonal fichaPersonal) {
		getFichaPersonals().add(fichaPersonal);
		fichaPersonal.setGradoEstudio(this);

		return fichaPersonal;
	}

	public FichaPersonal removeFichaPersonal(FichaPersonal fichaPersonal) {
		getFichaPersonals().remove(fichaPersonal);
		fichaPersonal.setGradoEstudio(null);

		return fichaPersonal;
	}

}