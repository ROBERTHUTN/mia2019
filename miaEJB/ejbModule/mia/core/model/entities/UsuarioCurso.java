package mia.core.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the usuario_curso database table.
 * 
 */
@Entity
@Table(name="usuario_curso")
@NamedQuery(name="UsuarioCurso.findAll", query="SELECT u FROM UsuarioCurso u")
public class UsuarioCurso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USUARIO_CURSO_IDUSUARIOCURSO_GENERATOR", sequenceName="SEQ_USUARIO_CURSO", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USUARIO_CURSO_IDUSUARIOCURSO_GENERATOR")
	@Column(name="id_usuariocurso")
	private long idUsuariocurso;

	private BigDecimal avance;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_fin_programada")
	private Date fechaFinProgramada;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_fin_real")
	private Date fechaFinReal;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicio_programada")
	private Date fechaInicioProgramada;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicio_real")
	private Date fechaInicioReal;

	private String modulorealizados;

	//bi-directional many-to-one association to Curso
	@ManyToOne
	@JoinColumn(name="id_curso")
	private Curso curso;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	//bi-directional many-to-one association to UsuarioCurso
	@ManyToOne
	@JoinColumn(name="id_padre_usuariocurso")
	private UsuarioCurso usuarioCurso;

	//bi-directional many-to-one association to UsuarioCurso
	@OneToMany(mappedBy="usuarioCurso")
	private List<UsuarioCurso> usuarioCursos;

	//bi-directional many-to-one association to UsuarioCursoModulo
	@OneToMany(mappedBy="usuarioCurso")
	private List<UsuarioCursoModulo> usuarioCursoModulos;

	public UsuarioCurso() {
	}

	public long getIdUsuariocurso() {
		return this.idUsuariocurso;
	}

	public void setIdUsuariocurso(long idUsuariocurso) {
		this.idUsuariocurso = idUsuariocurso;
	}

	public BigDecimal getAvance() {
		return this.avance;
	}

	public void setAvance(BigDecimal avance) {
		this.avance = avance;
	}

	public Date getFechaFinProgramada() {
		return this.fechaFinProgramada;
	}

	public void setFechaFinProgramada(Date fechaFinProgramada) {
		this.fechaFinProgramada = fechaFinProgramada;
	}

	public Date getFechaFinReal() {
		return this.fechaFinReal;
	}

	public void setFechaFinReal(Date fechaFinReal) {
		this.fechaFinReal = fechaFinReal;
	}

	public Date getFechaInicioProgramada() {
		return this.fechaInicioProgramada;
	}

	public void setFechaInicioProgramada(Date fechaInicioProgramada) {
		this.fechaInicioProgramada = fechaInicioProgramada;
	}

	public Date getFechaInicioReal() {
		return this.fechaInicioReal;
	}

	public void setFechaInicioReal(Date fechaInicioReal) {
		this.fechaInicioReal = fechaInicioReal;
	}

	public String getModulorealizados() {
		return this.modulorealizados;
	}

	public void setModulorealizados(String modulorealizados) {
		this.modulorealizados = modulorealizados;
	}

	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioCurso getUsuarioCurso() {
		return this.usuarioCurso;
	}

	public void setUsuarioCurso(UsuarioCurso usuarioCurso) {
		this.usuarioCurso = usuarioCurso;
	}

	public List<UsuarioCurso> getUsuarioCursos() {
		return this.usuarioCursos;
	}

	public void setUsuarioCursos(List<UsuarioCurso> usuarioCursos) {
		this.usuarioCursos = usuarioCursos;
	}

	public UsuarioCurso addUsuarioCurso(UsuarioCurso usuarioCurso) {
		getUsuarioCursos().add(usuarioCurso);
		usuarioCurso.setUsuarioCurso(this);

		return usuarioCurso;
	}

	public UsuarioCurso removeUsuarioCurso(UsuarioCurso usuarioCurso) {
		getUsuarioCursos().remove(usuarioCurso);
		usuarioCurso.setUsuarioCurso(null);

		return usuarioCurso;
	}

	public List<UsuarioCursoModulo> getUsuarioCursoModulos() {
		return this.usuarioCursoModulos;
	}

	public void setUsuarioCursoModulos(List<UsuarioCursoModulo> usuarioCursoModulos) {
		this.usuarioCursoModulos = usuarioCursoModulos;
	}

	public UsuarioCursoModulo addUsuarioCursoModulo(UsuarioCursoModulo usuarioCursoModulo) {
		getUsuarioCursoModulos().add(usuarioCursoModulo);
		usuarioCursoModulo.setUsuarioCurso(this);

		return usuarioCursoModulo;
	}

	public UsuarioCursoModulo removeUsuarioCursoModulo(UsuarioCursoModulo usuarioCursoModulo) {
		getUsuarioCursoModulos().remove(usuarioCursoModulo);
		usuarioCursoModulo.setUsuarioCurso(null);

		return usuarioCursoModulo;
	}

}