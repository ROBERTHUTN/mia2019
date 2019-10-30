package mia.core.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USUARIO_IDUSUARIO_GENERATOR", sequenceName="SEQ_USUARIO", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USUARIO_IDUSUARIO_GENERATOR")
	@Column(name="id_usuario")
	private long idUsuario;

	private Boolean activo;

	private String apellidos;

	private String ci;

	private String correo;

	private String nombres;

	private String password;

	//bi-directional many-to-one association to FichaPersonal
	@OneToMany(mappedBy="usuario")
	private List<FichaPersonal> fichaPersonals;

	//bi-directional many-to-one association to Reporte
	@OneToMany(mappedBy="usuario")
	private List<Reporte> reportes;

	//bi-directional many-to-one association to Respuesta
	@OneToMany(mappedBy="usuario")
	private List<Respuesta> respuestas;

	//bi-directional many-to-one association to Rol
	@ManyToOne
	@JoinColumn(name="id_rol")
	private Rol rol;

	//bi-directional many-to-one association to UsuarioCurso
	@OneToMany(mappedBy="usuario")
	private List<UsuarioCurso> usuarioCursos;

	public Usuario() {
	}

	public long getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCi() {
		return this.ci;
	}

	public void setCi(String ci) {
		this.ci = ci;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<FichaPersonal> getFichaPersonals() {
		return this.fichaPersonals;
	}

	public void setFichaPersonals(List<FichaPersonal> fichaPersonals) {
		this.fichaPersonals = fichaPersonals;
	}

	public FichaPersonal addFichaPersonal(FichaPersonal fichaPersonal) {
		getFichaPersonals().add(fichaPersonal);
		fichaPersonal.setUsuario(this);

		return fichaPersonal;
	}

	public FichaPersonal removeFichaPersonal(FichaPersonal fichaPersonal) {
		getFichaPersonals().remove(fichaPersonal);
		fichaPersonal.setUsuario(null);

		return fichaPersonal;
	}

	public List<Reporte> getReportes() {
		return this.reportes;
	}

	public void setReportes(List<Reporte> reportes) {
		this.reportes = reportes;
	}

	public Reporte addReporte(Reporte reporte) {
		getReportes().add(reporte);
		reporte.setUsuario(this);

		return reporte;
	}

	public Reporte removeReporte(Reporte reporte) {
		getReportes().remove(reporte);
		reporte.setUsuario(null);

		return reporte;
	}

	public List<Respuesta> getRespuestas() {
		return this.respuestas;
	}

	public void setRespuestas(List<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}

	public Respuesta addRespuesta(Respuesta respuesta) {
		getRespuestas().add(respuesta);
		respuesta.setUsuario(this);

		return respuesta;
	}

	public Respuesta removeRespuesta(Respuesta respuesta) {
		getRespuestas().remove(respuesta);
		respuesta.setUsuario(null);

		return respuesta;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public List<UsuarioCurso> getUsuarioCursos() {
		return this.usuarioCursos;
	}

	public void setUsuarioCursos(List<UsuarioCurso> usuarioCursos) {
		this.usuarioCursos = usuarioCursos;
	}

	public UsuarioCurso addUsuarioCurso(UsuarioCurso usuarioCurso) {
		getUsuarioCursos().add(usuarioCurso);
		usuarioCurso.setUsuario(this);

		return usuarioCurso;
	}

	public UsuarioCurso removeUsuarioCurso(UsuarioCurso usuarioCurso) {
		getUsuarioCursos().remove(usuarioCurso);
		usuarioCurso.setUsuario(null);

		return usuarioCurso;
	}

}