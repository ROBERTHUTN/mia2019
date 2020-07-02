package mia.core.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the usuario_proyecto database table.
 * 
 */
@Entity
@Table(name="usuario_proyecto")
@NamedQuery(name="UsuarioProyecto.findAll", query="SELECT u FROM UsuarioProyecto u")
public class UsuarioProyecto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USUARIO_PROYECTO_IDUSUARIOPROYECTO_GENERATOR", sequenceName="SEQ_USUARIO_PROYECTO", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USUARIO_PROYECTO_IDUSUARIOPROYECTO_GENERATOR")
	@Column(name="id_usuario_proyecto")
	private long idUsuarioProyecto;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_de")
	private Date fechaDe;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_hasta")
	private Date fechaHasta;

	//bi-directional many-to-one association to Organizacion
	@ManyToOne
	@JoinColumn(name="id_organizacion")
	private Organizacion organizacion;

	//bi-directional many-to-one association to ProyectoInvestigacion
	@ManyToOne
	@JoinColumn(name="id_proyecto_investigacion")
	private ProyectoInvestigacion proyectoInvestigacion;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public UsuarioProyecto() {
	}

	public long getIdUsuarioProyecto() {
		return this.idUsuarioProyecto;
	}

	public void setIdUsuarioProyecto(long idUsuarioProyecto) {
		this.idUsuarioProyecto = idUsuarioProyecto;
	}

	public Date getFechaDe() {
		return this.fechaDe;
	}

	public void setFechaDe(Date fechaDe) {
		this.fechaDe = fechaDe;
	}

	public Date getFechaHasta() {
		return this.fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Organizacion getOrganizacion() {
		return this.organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

	public ProyectoInvestigacion getProyectoInvestigacion() {
		return this.proyectoInvestigacion;
	}

	public void setProyectoInvestigacion(ProyectoInvestigacion proyectoInvestigacion) {
		this.proyectoInvestigacion = proyectoInvestigacion;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}