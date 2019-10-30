package mia.core.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the organizacion database table.
 * 
 */
@Entity
@NamedQuery(name="Organizacion.findAll", query="SELECT o FROM Organizacion o")
public class Organizacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ORGANIZACION_IDORGANIZACION_GENERATOR", sequenceName="SEQ_ORGANIZACION", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ORGANIZACION_IDORGANIZACION_GENERATOR")
	@Column(name="id_organizacion")
	private Integer idOrganizacion;

	@Column(name="descripcion_organizacion")
	private String descripcionOrganizacion;

	@Column(name="nombre_organizacion")
	private String nombreOrganizacion;

	@Column(name="telefono_organizacion")
	private String telefonoOrganizacion;

	//bi-directional many-to-one association to OrganizacionFichapersonal
	@OneToMany(mappedBy="organizacion")
	private List<OrganizacionFichapersonal> organizacionFichapersonals;

	//bi-directional many-to-one association to UsuarioProyecto
	@OneToMany(mappedBy="organizacion")
	private List<UsuarioProyecto> usuarioProyectos;

	public Organizacion() {
	}

	public Integer getIdOrganizacion() {
		return this.idOrganizacion;
	}

	public void setIdOrganizacion(Integer idOrganizacion) {
		this.idOrganizacion = idOrganizacion;
	}

	public String getDescripcionOrganizacion() {
		return this.descripcionOrganizacion;
	}

	public void setDescripcionOrganizacion(String descripcionOrganizacion) {
		this.descripcionOrganizacion = descripcionOrganizacion;
	}

	public String getNombreOrganizacion() {
		return this.nombreOrganizacion;
	}

	public void setNombreOrganizacion(String nombreOrganizacion) {
		this.nombreOrganizacion = nombreOrganizacion;
	}

	public String getTelefonoOrganizacion() {
		return this.telefonoOrganizacion;
	}

	public void setTelefonoOrganizacion(String telefonoOrganizacion) {
		this.telefonoOrganizacion = telefonoOrganizacion;
	}

	public List<OrganizacionFichapersonal> getOrganizacionFichapersonals() {
		return this.organizacionFichapersonals;
	}

	public void setOrganizacionFichapersonals(List<OrganizacionFichapersonal> organizacionFichapersonals) {
		this.organizacionFichapersonals = organizacionFichapersonals;
	}

	public OrganizacionFichapersonal addOrganizacionFichapersonal(OrganizacionFichapersonal organizacionFichapersonal) {
		getOrganizacionFichapersonals().add(organizacionFichapersonal);
		organizacionFichapersonal.setOrganizacion(this);

		return organizacionFichapersonal;
	}

	public OrganizacionFichapersonal removeOrganizacionFichapersonal(OrganizacionFichapersonal organizacionFichapersonal) {
		getOrganizacionFichapersonals().remove(organizacionFichapersonal);
		organizacionFichapersonal.setOrganizacion(null);

		return organizacionFichapersonal;
	}

	public List<UsuarioProyecto> getUsuarioProyectos() {
		return this.usuarioProyectos;
	}

	public void setUsuarioProyectos(List<UsuarioProyecto> usuarioProyectos) {
		this.usuarioProyectos = usuarioProyectos;
	}

	public UsuarioProyecto addUsuarioProyecto(UsuarioProyecto usuarioProyecto) {
		getUsuarioProyectos().add(usuarioProyecto);
		usuarioProyecto.setOrganizacion(this);

		return usuarioProyecto;
	}

	public UsuarioProyecto removeUsuarioProyecto(UsuarioProyecto usuarioProyecto) {
		getUsuarioProyectos().remove(usuarioProyecto);
		usuarioProyecto.setOrganizacion(null);

		return usuarioProyecto;
	}

}