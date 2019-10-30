package mia.core.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the opcion database table.
 * 
 */
@Entity
@NamedQuery(name="Opcion.findAll", query="SELECT o FROM Opcion o")
public class Opcion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="OPCION_IDOPCION_GENERATOR", sequenceName="SEQ_OPCION", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="OPCION_IDOPCION_GENERATOR")
	@Column(name="id_opcion")
	private long idOpcion;

	@Column(name="opc_descripcion")
	private String opcDescripcion;

	private Integer valor;

	//bi-directional many-to-one association to Cuestionario
	@ManyToOne
	@JoinColumn(name="id_cuestionario")
	private Cuestionario cuestionario;

	public Opcion() {
	}

	public long getIdOpcion() {
		return this.idOpcion;
	}

	public void setIdOpcion(long idOpcion) {
		this.idOpcion = idOpcion;
	}

	public String getOpcDescripcion() {
		return this.opcDescripcion;
	}

	public void setOpcDescripcion(String opcDescripcion) {
		this.opcDescripcion = opcDescripcion;
	}

	public Integer getValor() {
		return this.valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public Cuestionario getCuestionario() {
		return this.cuestionario;
	}

	public void setCuestionario(Cuestionario cuestionario) {
		this.cuestionario = cuestionario;
	}

}