package mia.core.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the ficha_personal database table.
 * 
 */
@Entity
@Table(name="ficha_personal")
@NamedQuery(name="FichaPersonal.findAll", query="SELECT f FROM FichaPersonal f")
public class FichaPersonal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FICHA_PERSONAL_IDFICHA_GENERATOR", sequenceName="SEQ_FICHA_PERSONAL", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FICHA_PERSONAL_IDFICHA_GENERATOR")
	@Column(name="id_ficha")
	private long idFicha;

	private String celular;

	private String ciudad;

	private String direccion;

	private Integer edad;

	@Column(name="estado_civil")
	private String estadoCivil;

	@Column(name="fecha_inscripcion")
	private Timestamp fechaInscripcion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento;

	private String genero;

	@Column(name="numero_hijos")
	private Integer numeroHijos;

	private String telefono;

	private Boolean trabajo;

	private Boolean voluntariado;

	//bi-directional many-to-one association to Etnia
	@ManyToOne
	@JoinColumn(name="id_etnia")
	private Etnia etnia;

	//bi-directional many-to-one association to GradoEstudio
	@ManyToOne
	@JoinColumn(name="id_grado")
	private GradoEstudio gradoEstudio;

	//bi-directional many-to-one association to PaisEstado
	@ManyToOne
	@JoinColumn(name="id_estado")
	private PaisEstado paisEstado1;

	//bi-directional many-to-one association to PaisEstado
	@ManyToOne
	@JoinColumn(name="id_pais")
	private PaisEstado paisEstado2;

	//bi-directional many-to-one association to Religion
	@ManyToOne
	@JoinColumn(name="id_religion")
	private Religion religion;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	//bi-directional many-to-one association to UsuarioInteresArea
	@OneToMany(mappedBy="fichaPersonal")
	private List<UsuarioInteresArea> usuarioInteresAreas;

	public FichaPersonal() {
	}

	public long getIdFicha() {
		return this.idFicha;
	}

	public void setIdFicha(long idFicha) {
		this.idFicha = idFicha;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getEdad() {
		return this.edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getEstadoCivil() {
		return this.estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Timestamp getFechaInscripcion() {
		return this.fechaInscripcion;
	}

	public void setFechaInscripcion(Timestamp fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Integer getNumeroHijos() {
		return this.numeroHijos;
	}

	public void setNumeroHijos(Integer numeroHijos) {
		this.numeroHijos = numeroHijos;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Boolean getTrabajo() {
		return this.trabajo;
	}

	public void setTrabajo(Boolean trabajo) {
		this.trabajo = trabajo;
	}

	public Boolean getVoluntariado() {
		return this.voluntariado;
	}

	public void setVoluntariado(Boolean voluntariado) {
		this.voluntariado = voluntariado;
	}

	public Etnia getEtnia() {
		return this.etnia;
	}

	public void setEtnia(Etnia etnia) {
		this.etnia = etnia;
	}

	public GradoEstudio getGradoEstudio() {
		return this.gradoEstudio;
	}

	public void setGradoEstudio(GradoEstudio gradoEstudio) {
		this.gradoEstudio = gradoEstudio;
	}

	public PaisEstado getPaisEstado1() {
		return this.paisEstado1;
	}

	public void setPaisEstado1(PaisEstado paisEstado1) {
		this.paisEstado1 = paisEstado1;
	}

	public PaisEstado getPaisEstado2() {
		return this.paisEstado2;
	}

	public void setPaisEstado2(PaisEstado paisEstado2) {
		this.paisEstado2 = paisEstado2;
	}

	public Religion getReligion() {
		return this.religion;
	}

	public void setReligion(Religion religion) {
		this.religion = religion;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<UsuarioInteresArea> getUsuarioInteresAreas() {
		return this.usuarioInteresAreas;
	}

	public void setUsuarioInteresAreas(List<UsuarioInteresArea> usuarioInteresAreas) {
		this.usuarioInteresAreas = usuarioInteresAreas;
	}

	public UsuarioInteresArea addUsuarioInteresArea(UsuarioInteresArea usuarioInteresArea) {
		getUsuarioInteresAreas().add(usuarioInteresArea);
		usuarioInteresArea.setFichaPersonal(this);

		return usuarioInteresArea;
	}

	public UsuarioInteresArea removeUsuarioInteresArea(UsuarioInteresArea usuarioInteresArea) {
		getUsuarioInteresAreas().remove(usuarioInteresArea);
		usuarioInteresArea.setFichaPersonal(null);

		return usuarioInteresArea;
	}

}