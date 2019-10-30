package mia.core.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

 
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

	private String modulorealizados;

	//bi-directional many-to-one association to Curso
	@ManyToOne
	@JoinColumn(name="id_curso")
	private Curso curso;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

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

}