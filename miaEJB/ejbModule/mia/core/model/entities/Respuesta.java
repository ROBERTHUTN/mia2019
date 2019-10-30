package mia.core.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the respuesta database table.
 * 
 */
@Entity
@NamedQuery(name="Respuesta.findAll", query="SELECT r FROM Respuesta r")
public class Respuesta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RESPUESTA_IDRESPUESTA_GENERATOR", sequenceName="SEQ_RESPUESTA", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RESPUESTA_IDRESPUESTA_GENERATOR")
	@Column(name="id_respuesta")
	private long idRespuesta;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name="pregunta_respuesta")
	private String preguntaRespuesta;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public Respuesta() {
	}

	public long getIdRespuesta() {
		return this.idRespuesta;
	}

	public void setIdRespuesta(long idRespuesta) {
		this.idRespuesta = idRespuesta;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getPreguntaRespuesta() {
		return this.preguntaRespuesta;
	}

	public void setPreguntaRespuesta(String preguntaRespuesta) {
		this.preguntaRespuesta = preguntaRespuesta;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}