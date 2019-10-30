package mia.core.model.usuario.dto;

import java.sql.Timestamp;
import java.util.Date;
import mia.core.model.entities.Etnia;
import mia.core.model.entities.FichaPersonal;
import mia.core.model.entities.GradoEstudio;
import mia.core.model.entities.PaisEstado;
import mia.core.model.entities.Religion;

public class UsuarioFichaDTO extends FichaPersonal{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	private long idUsuario;
	private Boolean activo;
	private String apellidos;
	private String ci;
	private String correo;
	private String nombres;
	private String password;
	private String celular;
	private String ciudad;
	private String direccion;
	private Integer edad;
	private String estadoCivil;
	private Timestamp fechaInscripcion;
	private Date fechaNacimiento;
	private String genero;
	private Integer numeroHijos;
	private String telefono;
	private Boolean trabajo;
	private Boolean voluntariado;
	private Etnia etnia;
	private GradoEstudio gradoEstudio;
	private PaisEstado paisEstado1;
	private PaisEstado paisEstado2;
	private Religion religion;

	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCi() {
		return ci;
	}
	public void setCi(String ci) {
		this.ci = ci;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public Timestamp getFechaInscripcion() {
		return fechaInscripcion;
	}
	public void setFechaInscripcion(Timestamp fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public Integer getNumeroHijos() {
		return numeroHijos;
	}
	public void setNumeroHijos(Integer numeroHijos) {
		this.numeroHijos = numeroHijos;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Boolean getTrabajo() {
		return trabajo;
	}
	public void setTrabajo(Boolean trabajo) {
		this.trabajo = trabajo;
	}
	public Boolean getVoluntariado() {
		return voluntariado;
	}
	public void setVoluntariado(Boolean voluntariado) {
		this.voluntariado = voluntariado;
	}
	public Etnia getEtnia() {
		return etnia;
	}
	public void setEtnia(Etnia etnia) {
		this.etnia = etnia;
	}
	public GradoEstudio getGradoEstudio() {
		return gradoEstudio;
	}
	public void setGradoEstudio(GradoEstudio gradoEstudio) {
		this.gradoEstudio = gradoEstudio;
	}
	public PaisEstado getPaisEstado1() {
		return paisEstado1;
	}
	public void setPaisEstado1(PaisEstado paisEstado1) {
		this.paisEstado1 = paisEstado1;
	}
	public PaisEstado getPaisEstado2() {
		return paisEstado2;
	}
	public void setPaisEstado2(PaisEstado paisEstado2) {
		this.paisEstado2 = paisEstado2;
	}
	public Religion getReligion() {
		return religion;
	}
	public void setReligion(Religion religion) {
		this.religion = religion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		
		return super.clone();
	}
	@Override
	public boolean equals(Object arg0) {
		
		return super.equals(arg0);
	}
	@Override
	protected void finalize() throws Throwable {
		
		super.finalize();
	}
	@Override
	public int hashCode() {
		
		return super.hashCode();
	}
	@Override
	public String toString() {
		
		return super.toString();
	}
	
	
	
	

}