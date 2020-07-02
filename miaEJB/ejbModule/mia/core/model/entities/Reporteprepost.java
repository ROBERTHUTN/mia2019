package mia.core.model.entities;


import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the reporteprepost database table.
 * 
 */
@Entity
@NamedQuery(name="Reporteprepost.findAll", query="SELECT r FROM Reporteprepost r")
public class Reporteprepost implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="REPORTEPREPOST_IDREPORTETEST_GENERATOR", sequenceName="SEQ_REPORTEPREPOST", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REPORTEPREPOST_IDREPORTETEST_GENERATOR")
	@Column(name="id_reportetest")
	private long idReportetest;

	@Column(name="asesoria_emocional")
	private Integer asesoriaEmocional;

	private Integer autoconciencia;

	private Integer automotivacion;

	private String centrogravedad;

	@Column(name="control_emocion")
	private Integer controlEmocion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inscripcion")
	private Date fechaInscripcion;

	private Integer interpersonal;

	@Column(name="logica_emergente")
	private String logicaEmergente;

	@Column(name="logica_retroceso")
	private String logicaRetroceso;

	private String nivelestres;

	@Column(name="por_estres")
	private Integer porEstres;

	@Column(name="res_asesoria_emocional")
	private String resAsesoriaEmocional;

	@Column(name="res_autoconciencia")
	private String resAutoconciencia;

	@Column(name="res_automotivacion")
	private String resAutomotivacion;

	@Column(name="res_control_emocion")
	private String resControlEmocion;

	@Column(name="res_interpersonal")
	private String resInterpersonal;

	@Column(name="respuesta_cuestionario")
	private String respuestaCuestionario;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public Reporteprepost() {
	}

	public long getIdReportetest() {
		return this.idReportetest;
	}

	public void setIdReportetest(long idReportetest) {
		this.idReportetest = idReportetest;
	}

	public Integer getAsesoriaEmocional() {
		return this.asesoriaEmocional;
	}

	public void setAsesoriaEmocional(Integer asesoriaEmocional) {
		this.asesoriaEmocional = asesoriaEmocional;
	}

	public Integer getAutoconciencia() {
		return this.autoconciencia;
	}

	public void setAutoconciencia(Integer autoconciencia) {
		this.autoconciencia = autoconciencia;
	}

	public Integer getAutomotivacion() {
		return this.automotivacion;
	}

	public void setAutomotivacion(Integer automotivacion) {
		this.automotivacion = automotivacion;
	}

	public String getCentrogravedad() {
		return this.centrogravedad;
	}

	public void setCentrogravedad(String centrogravedad) {
		this.centrogravedad = centrogravedad;
	}

	public Integer getControlEmocion() {
		return this.controlEmocion;
	}

	public void setControlEmocion(Integer controlEmocion) {
		this.controlEmocion = controlEmocion;
	}

	public Date getFechaInscripcion() {
		return this.fechaInscripcion;
	}

	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public Integer getInterpersonal() {
		return this.interpersonal;
	}

	public void setInterpersonal(Integer interpersonal) {
		this.interpersonal = interpersonal;
	}

	public String getLogicaEmergente() {
		return this.logicaEmergente;
	}

	public void setLogicaEmergente(String logicaEmergente) {
		this.logicaEmergente = logicaEmergente;
	}

	public String getLogicaRetroceso() {
		return this.logicaRetroceso;
	}

	public void setLogicaRetroceso(String logicaRetroceso) {
		this.logicaRetroceso = logicaRetroceso;
	}

	public String getNivelestres() {
		return this.nivelestres;
	}

	public void setNivelestres(String nivelestres) {
		this.nivelestres = nivelestres;
	}

	public Integer getPorEstres() {
		return this.porEstres;
	}

	public void setPorEstres(Integer porEstres) {
		this.porEstres = porEstres;
	}

	public String getResAsesoriaEmocional() {
		return this.resAsesoriaEmocional;
	}

	public void setResAsesoriaEmocional(String resAsesoriaEmocional) {
		this.resAsesoriaEmocional = resAsesoriaEmocional;
	}

	public String getResAutoconciencia() {
		return this.resAutoconciencia;
	}

	public void setResAutoconciencia(String resAutoconciencia) {
		this.resAutoconciencia = resAutoconciencia;
	}

	public String getResAutomotivacion() {
		return this.resAutomotivacion;
	}

	public void setResAutomotivacion(String resAutomotivacion) {
		this.resAutomotivacion = resAutomotivacion;
	}

	public String getResControlEmocion() {
		return this.resControlEmocion;
	}

	public void setResControlEmocion(String resControlEmocion) {
		this.resControlEmocion = resControlEmocion;
	}

	public String getResInterpersonal() {
		return this.resInterpersonal;
	}

	public void setResInterpersonal(String resInterpersonal) {
		this.resInterpersonal = resInterpersonal;
	}

	public String getRespuestaCuestionario() {
		return this.respuestaCuestionario;
	}

	public void setRespuestaCuestionario(String respuestaCuestionario) {
		this.respuestaCuestionario = respuestaCuestionario;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}