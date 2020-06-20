package mia.core.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the restablecer_contrasenia database table.
 * 
 */
@Entity
@Table(name="restablecer_contrasenia")
@NamedQuery(name="RestablecerContrasenia.findAll", query="SELECT r FROM RestablecerContrasenia r")
public class RestablecerContrasenia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RESTABLECER_CONTRASENIA_IDRESTABLECER_GENERATOR", sequenceName="SEQ_RESTABLECER_CONTRASENIA", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RESTABLECER_CONTRASENIA_IDRESTABLECER_GENERATOR")
	@Column(name="id_restablecer")
	private long idRestablecer;

	private Boolean activo;

	private String codigo;

	private String correo;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	public RestablecerContrasenia() {
	}

	public long getIdRestablecer() {
		return this.idRestablecer;
	}

	public void setIdRestablecer(long idRestablecer) {
		this.idRestablecer = idRestablecer;
	}

	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}