package mia.core.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the organizacion_fichapersonal database table.
 * 
 */
@Entity
@Table(name="organizacion_fichapersonal")
@NamedQuery(name="OrganizacionFichapersonal.findAll", query="SELECT o FROM OrganizacionFichapersonal o")
public class OrganizacionFichapersonal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ORGANIZACION_FICHAPERSONAL_IDORGANIZACIONFICHA_GENERATOR", sequenceName="SEQ_ORGANIZACION_FICHAPERSONAL", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ORGANIZACION_FICHAPERSONAL_IDORGANIZACIONFICHA_GENERATOR")
	@Column(name="id_organizacion_ficha")
	private long idOrganizacionFicha;

	//bi-directional many-to-one association to FichaPersonal
	@ManyToOne
	@JoinColumn(name="id_ficha")
	private FichaPersonal fichaPersonal;

	//bi-directional many-to-one association to Organizacion
	@ManyToOne
	@JoinColumn(name="id_organizacion")
	private Organizacion organizacion;

	public OrganizacionFichapersonal() {
	}

	public long getIdOrganizacionFicha() {
		return this.idOrganizacionFicha;
	}

	public void setIdOrganizacionFicha(long idOrganizacionFicha) {
		this.idOrganizacionFicha = idOrganizacionFicha;
	}

	public FichaPersonal getFichaPersonal() {
		return this.fichaPersonal;
	}

	public void setFichaPersonal(FichaPersonal fichaPersonal) {
		this.fichaPersonal = fichaPersonal;
	}

	public Organizacion getOrganizacion() {
		return this.organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

}