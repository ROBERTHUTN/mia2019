package mia.core.model.entities;

import java.io.Serializable;
import javax.persistence.*;

import mia.core.model.entities.UsuarioCurso;

import java.util.List;


/**
 * The persistent class for the curso database table.
 * 
 */
@Entity
@NamedQuery(name="Curso.findAll", query="SELECT c FROM Curso c")
public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CURSO_IDCURSO_GENERATOR", sequenceName="SEQ_CURSO", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CURSO_IDCURSO_GENERATOR")
	@Column(name="id_curso")
	private Integer idCurso;

	private Integer avance;

	private String descripcion;

	private String nombre;

	private Boolean secuencia;

	//bi-directional many-to-one association to CursoModulo
	@OneToMany(mappedBy="curso")
	private List<CursoModulo> cursoModulos;

	//bi-directional many-to-one association to UsuarioCurso
	@OneToMany(mappedBy="curso")
	private List<UsuarioCurso> usuarioCursos;

	public Curso() {
	}

	public Integer getIdCurso() {
		return this.idCurso;
	}

	public void setIdCurso(Integer idCurso) {
		this.idCurso = idCurso;
	}

	public Integer getAvance() {
		return this.avance;
	}

	public void setAvance(Integer avance) {
		this.avance = avance;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getSecuencia() {
		return this.secuencia;
	}

	public void setSecuencia(Boolean secuencia) {
		this.secuencia = secuencia;
	}

	public List<CursoModulo> getCursoModulos() {
		return this.cursoModulos;
	}

	public void setCursoModulos(List<CursoModulo> cursoModulos) {
		this.cursoModulos = cursoModulos;
	}

	public CursoModulo addCursoModulo(CursoModulo cursoModulo) {
		getCursoModulos().add(cursoModulo);
		cursoModulo.setCurso(this);

		return cursoModulo;
	}

	public CursoModulo removeCursoModulo(CursoModulo cursoModulo) {
		getCursoModulos().remove(cursoModulo);
		cursoModulo.setCurso(null);

		return cursoModulo;
	}

	public List<UsuarioCurso> getUsuarioCursos() {
		return this.usuarioCursos;
	}

	public void setUsuarioCursos(List<UsuarioCurso> usuarioCursos) {
		this.usuarioCursos = usuarioCursos;
	}

	public UsuarioCurso addUsuarioCurso(UsuarioCurso usuarioCurso) {
		getUsuarioCursos().add(usuarioCurso);
		usuarioCurso.setCurso(this);

		return usuarioCurso;
	}

	public UsuarioCurso removeUsuarioCurso(UsuarioCurso usuarioCurso) {
		getUsuarioCursos().remove(usuarioCurso);
		usuarioCurso.setCurso(null);

		return usuarioCurso;
	}

}