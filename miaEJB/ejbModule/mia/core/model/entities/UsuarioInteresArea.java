package mia.core.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuario_interes_area database table.
 * 
 */
@Entity
@Table(name="usuario_interes_area")
@NamedQuery(name="UsuarioInteresArea.findAll", query="SELECT u FROM UsuarioInteresArea u")
public class UsuarioInteresArea implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USUARIO_INTERES_AREA_IDINTERESAREA_GENERATOR", sequenceName="SEQ_USUARIO_INTERES_AREA", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USUARIO_INTERES_AREA_IDINTERESAREA_GENERATOR")
	@Column(name="id_interes_area")
	private long idInteresArea;

	//bi-directional many-to-one association to AreaInvestigacion
	@ManyToOne
	@JoinColumn(name="area_id")
	private AreaInvestigacion areaInvestigacion;

	//bi-directional many-to-one association to FichaPersonal
	@ManyToOne
	@JoinColumn(name="id_ficha")
	private FichaPersonal fichaPersonal;

	public UsuarioInteresArea() {
	}

	public long getIdInteresArea() {
		return this.idInteresArea;
	}

	public void setIdInteresArea(long idInteresArea) {
		this.idInteresArea = idInteresArea;
	}

	public AreaInvestigacion getAreaInvestigacion() {
		return this.areaInvestigacion;
	}

	public void setAreaInvestigacion(AreaInvestigacion areaInvestigacion) {
		this.areaInvestigacion = areaInvestigacion;
	}

	public FichaPersonal getFichaPersonal() {
		return this.fichaPersonal;
	}

	public void setFichaPersonal(FichaPersonal fichaPersonal) {
		this.fichaPersonal = fichaPersonal;
	}

}