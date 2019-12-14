package mia.core.model.entities;

import java.io.Serializable;
import javax.persistence.*;

import mia.core.model.entities.Modulo;


/**
 * The persistent class for the curso_modulo database table.
 * 
 */
@Entity
@Table(name="curso_modulo")
@NamedQuery(name="CursoModulo.findAll", query="SELECT c FROM CursoModulo c")
public class CursoModulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CURSO_MODULO_IDCURSOMODULO_GENERATOR", sequenceName="SEQ_CURSO_MODULO", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CURSO_MODULO_IDCURSOMODULO_GENERATOR")
	@Column(name="id_curso_modulo")
	
	private long idCursoModulo;

	@Column(name="orden_curso")
	private Integer ordenCurso;

	//bi-directional many-to-one association to Curso
	@ManyToOne
	@JoinColumn(name="id_curso")
	private Curso curso;

	//bi-directional many-to-one association to Modulo
	@ManyToOne
	@JoinColumn(name="id_modulo")
	private Modulo modulo;

	public CursoModulo() {
	}

	public long getIdCursoModulo() {
		return this.idCursoModulo;
	}

	public void setIdCursoModulo(long idCursoModulo) {
		this.idCursoModulo = idCursoModulo;
	}

	public Integer getOrdenCurso() {
		return this.ordenCurso;
	}

	public void setOrdenCurso(Integer ordenCurso) {
		this.ordenCurso = ordenCurso;
	}

	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Modulo getModulo() {
		return this.modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

}