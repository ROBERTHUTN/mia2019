package mia.core.model.entities;


import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the religion database table.
 * 
 */
@Entity
@NamedQuery(name="Religion.findAll", query="SELECT r FROM Religion r")
public class Religion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RELIGION_IDRELIGION_GENERATOR", sequenceName="SEQ_RELIGION", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RELIGION_IDRELIGION_GENERATOR")
	@Column(name="id_religion")
	private Integer idReligion;

	private String descripcion;

	//bi-directional many-to-one association to FichaPersonal
	@OneToMany(mappedBy="religion")
	private List<FichaPersonal> fichaPersonals;

	public Religion() {
	}

	public Integer getIdReligion() {
		return this.idReligion;
	}

	public void setIdReligion(Integer idReligion) {
		this.idReligion = idReligion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<FichaPersonal> getFichaPersonals() {
		return this.fichaPersonals;
	}

	public void setFichaPersonals(List<FichaPersonal> fichaPersonals) {
		this.fichaPersonals = fichaPersonals;
	}

	public FichaPersonal addFichaPersonal(FichaPersonal fichaPersonal) {
		getFichaPersonals().add(fichaPersonal);
		fichaPersonal.setReligion(this);

		return fichaPersonal;
	}

	public FichaPersonal removeFichaPersonal(FichaPersonal fichaPersonal) {
		getFichaPersonals().remove(fichaPersonal);
		fichaPersonal.setReligion(null);

		return fichaPersonal;
	}

}