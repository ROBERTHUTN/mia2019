package mia.core.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the reporte database table.
 * 
 */
@Entity
@NamedQuery(name="Reporte.findAll", query="SELECT r FROM Reporte r")
public class Reporte implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="REPORTE_IDREPORTE_GENERATOR", sequenceName="SEQ_REPORTE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REPORTE_IDREPORTE_GENERATOR")
	@Column(name="id_reporte")
	private long idReporte;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private String resultado;

	//bi-directional many-to-one association to Dimension
	@ManyToOne
	@JoinColumn(name="id_dimension")
	private Dimension dimension;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public Reporte() {
	}

	public long getIdReporte() {
		return this.idReporte;
	}

	public void setIdReporte(long idReporte) {
		this.idReporte = idReporte;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getResultado() {
		return this.resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public Dimension getDimension() {
		return this.dimension;
	}

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}