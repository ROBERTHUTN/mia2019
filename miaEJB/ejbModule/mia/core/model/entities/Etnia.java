package mia.core.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the etnia database table.
 * 
 */
@Entity
@NamedQuery(name="Etnia.findAll", query="SELECT e FROM Etnia e")
public class Etnia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ETNIA_IDETNIA_GENERATOR", sequenceName="SEQ_ETNIA", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ETNIA_IDETNIA_GENERATOR")
	@Column(name="id_etnia")
	private Integer idEtnia;

	private String descripcion;

	//bi-directional many-to-one association to FichaPersonal
	@OneToMany(mappedBy="etnia")
	private List<FichaPersonal> fichaPersonals;

	public Etnia() {
	}

	public Integer getIdEtnia() {
		return this.idEtnia;
	}

	public void setIdEtnia(Integer idEtnia) {
		this.idEtnia = idEtnia;
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
		fichaPersonal.setEtnia(this);

		return fichaPersonal;
	}

	public FichaPersonal removeFichaPersonal(FichaPersonal fichaPersonal) {
		getFichaPersonals().remove(fichaPersonal);
		fichaPersonal.setEtnia(null);

		return fichaPersonal;
	}

}