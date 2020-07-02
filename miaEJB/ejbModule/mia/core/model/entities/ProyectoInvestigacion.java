package mia.core.model.entities;


import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the proyecto_investigacion database table.
 * 
 */
@Entity
@Table(name="proyecto_investigacion")
@NamedQuery(name="ProyectoInvestigacion.findAll", query="SELECT p FROM ProyectoInvestigacion p")
public class ProyectoInvestigacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PROYECTO_INVESTIGACION_IDPROYECTOINVESTIGACION_GENERATOR", sequenceName="SEQ_PROYECTO_INVESTIGACION", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROYECTO_INVESTIGACION_IDPROYECTOINVESTIGACION_GENERATOR")
	@Column(name="id_proyecto_investigacion")
	private long idProyectoInvestigacion;

	@Column(name="estado_proyecto")
	private Boolean estadoProyecto;

	@Column(name="nombre_proyecto")
	private String nombreProyecto;

	@Column(name="proyecto_descripcion")
	private String proyectoDescripcion;

	//bi-directional many-to-one association to AreaInvestigacion
	@ManyToOne
	@JoinColumn(name="area_id")
	private AreaInvestigacion areaInvestigacion;

	//bi-directional many-to-one association to UsuarioProyecto
	@OneToMany(mappedBy="proyectoInvestigacion")
	private List<UsuarioProyecto> usuarioProyectos;

	public ProyectoInvestigacion() {
	}

	public long getIdProyectoInvestigacion() {
		return this.idProyectoInvestigacion;
	}

	public void setIdProyectoInvestigacion(long idProyectoInvestigacion) {
		this.idProyectoInvestigacion = idProyectoInvestigacion;
	}

	public Boolean getEstadoProyecto() {
		return this.estadoProyecto;
	}

	public void setEstadoProyecto(Boolean estadoProyecto) {
		this.estadoProyecto = estadoProyecto;
	}

	public String getNombreProyecto() {
		return this.nombreProyecto;
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	public String getProyectoDescripcion() {
		return this.proyectoDescripcion;
	}

	public void setProyectoDescripcion(String proyectoDescripcion) {
		this.proyectoDescripcion = proyectoDescripcion;
	}

	public AreaInvestigacion getAreaInvestigacion() {
		return this.areaInvestigacion;
	}

	public void setAreaInvestigacion(AreaInvestigacion areaInvestigacion) {
		this.areaInvestigacion = areaInvestigacion;
	}

	public List<UsuarioProyecto> getUsuarioProyectos() {
		return this.usuarioProyectos;
	}

	public void setUsuarioProyectos(List<UsuarioProyecto> usuarioProyectos) {
		this.usuarioProyectos = usuarioProyectos;
	}

	public UsuarioProyecto addUsuarioProyecto(UsuarioProyecto usuarioProyecto) {
		getUsuarioProyectos().add(usuarioProyecto);
		usuarioProyecto.setProyectoInvestigacion(this);

		return usuarioProyecto;
	}

	public UsuarioProyecto removeUsuarioProyecto(UsuarioProyecto usuarioProyecto) {
		getUsuarioProyectos().remove(usuarioProyecto);
		usuarioProyecto.setProyectoInvestigacion(null);

		return usuarioProyecto;
	}

}