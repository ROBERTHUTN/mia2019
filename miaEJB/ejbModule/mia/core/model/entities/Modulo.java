package mia.core.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the modulo database table.
 * 
 */
@Entity
@NamedQuery(name="Modulo.findAll", query="SELECT m FROM Modulo m")
public class Modulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MODULO_IDMODULO_GENERATOR", sequenceName="SEQ_MODULO", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MODULO_IDMODULO_GENERATOR")
	@Column(name="id_modulo")
	private long idModulo;

	private String descripcion;

	private String direccion;

	private String nombre;

	//bi-directional many-to-one association to CursoModulo
	@OneToMany(mappedBy="modulo")
	private List<CursoModulo> cursoModulos;

	public Modulo() {
	}

	public long getIdModulo() {
		return this.idModulo;
	}

	public void setIdModulo(long idModulo) {
		this.idModulo = idModulo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<CursoModulo> getCursoModulos() {
		return this.cursoModulos;
	}

	public void setCursoModulos(List<CursoModulo> cursoModulos) {
		this.cursoModulos = cursoModulos;
	}

	public CursoModulo addCursoModulo(CursoModulo cursoModulo) {
		getCursoModulos().add(cursoModulo);
		cursoModulo.setModulo(this);

		return cursoModulo;
	}

	public CursoModulo removeCursoModulo(CursoModulo cursoModulo) {
		getCursoModulos().remove(cursoModulo);
		cursoModulo.setModulo(null);

		return cursoModulo;
	}

}