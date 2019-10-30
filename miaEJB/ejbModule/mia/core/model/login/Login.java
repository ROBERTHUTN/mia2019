package mia.core.model.login;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class Login implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id_usuario;
	private String nombres;
	private String rol;
	private boolean isnuevo;
	private boolean tieneFicha;
	private boolean activo;
	
	public long getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public boolean isIsnuevo() {
		return isnuevo;
	}
	public void setIsnuevo(boolean isnuevo) {
		this.isnuevo = isnuevo;
	}
	public boolean isTieneFicha() {
		return tieneFicha;
	}
	public void setTieneFicha(boolean tieneFicha) {
		this.tieneFicha = tieneFicha;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	
	
	
	
	
	
	
	
	
	

	
}
