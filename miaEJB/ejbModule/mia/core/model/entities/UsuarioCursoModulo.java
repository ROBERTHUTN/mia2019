package mia.core.model.entities;
import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the usuario_curso_modulo database table.
 * 
 */
@Entity
@Table(name="usuario_curso_modulo")
@NamedQuery(name="UsuarioCursoModulo.findAll", query="SELECT u FROM UsuarioCursoModulo u")
public class UsuarioCursoModulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USUARIO_CURSO_MODULO_IDUSUARIOCURSOMODULO_GENERATOR", sequenceName="SEQ_USUARIO_CURSO_MODULO", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USUARIO_CURSO_MODULO_IDUSUARIOCURSOMODULO_GENERATOR")
	@Column(name="id_usuario_curso_modulo")
	private long idUsuarioCursoModulo;

	private Integer aciertos;

	private BigDecimal avance;

	private Integer errores;

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

	//bi-directional many-to-one association to CursoModulo
	@ManyToOne
	@JoinColumn(name="id_curso_modulo")
	private CursoModulo cursoModulo;

	//bi-directional many-to-one association to UsuarioCurso
	@ManyToOne
	@JoinColumn(name="id_usuario_curso")
	private UsuarioCurso usuarioCurso;

	public UsuarioCursoModulo() {
	}

	public long getIdUsuarioCursoModulo() {
		return this.idUsuarioCursoModulo;
	}

	public void setIdUsuarioCursoModulo(long idUsuarioCursoModulo) {
		this.idUsuarioCursoModulo = idUsuarioCursoModulo;
	}

	public Integer getAciertos() {
		return this.aciertos;
	}

	public void setAciertos(Integer aciertos) {
		this.aciertos = aciertos;
	}

	public BigDecimal getAvance() {
		return this.avance;
	}

	public void setAvance(BigDecimal avance) {
		this.avance = avance;
	}

	public Integer getErrores() {
		return this.errores;
	}

	public void setErrores(Integer errores) {
		this.errores = errores;
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

	public CursoModulo getCursoModulo() {
		return this.cursoModulo;
	}

	public void setCursoModulo(CursoModulo cursoModulo) {
		this.cursoModulo = cursoModulo;
	}

	public UsuarioCurso getUsuarioCurso() {
		return this.usuarioCurso;
	}

	public void setUsuarioCurso(UsuarioCurso usuarioCurso) {
		this.usuarioCurso = usuarioCurso;
	}

}