package mia.core.model.login.view.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import mia.core.model.entities.Usuario;
import mia.core.model.login.Login;
import mia.core.model.login.ManagerLogin;
import mia.model.seguridad.view.controller.Seguridad;
import mia.modulos.view.util.JSFUtil;

import java.io.IOException;
import java.io.Serializable;

@Named
@SessionScoped
public class BeanLogin implements Serializable {
	@EJB
	private ManagerLogin managerLogin;
	private static final long serialVersionUID = 1L;
	private String correo;
	private String contrasenia;
	private Login login;
	private Usuario registroUsuario=new Usuario();
    private boolean activoLogin;

public String  actionregistrarUsuario() {
try {
	registroUsuario.setPassword(Seguridad.encriptar(registroUsuario.getPassword()));
	login=managerLogin.registroUsuario(registroUsuario);
	JSFUtil.crearMensajeInfo("Se ha realizado el registro con éxito");
	return"usuario/fichaUsuario?faces-redirect=true";
} catch (Exception e) {
	JSFUtil.crearMensajeError(e.getMessage());
	e.printStackTrace();
	return"";
}finally {
	JSFUtil.crearMensajeFastFinal();
}
	
}

public String actionLogin() {
		try {
			contrasenia=Seguridad.encriptar(contrasenia);
			System.out.println("Contrasenia que ingresa "+contrasenia);
			login = managerLogin.comprobarCredenciales(correo, contrasenia);
			correo="";
			contrasenia="";
			if (!login.isActivo()) {
				JSFUtil.crearMensajeError("Cuenta inactiva contáctese con el administrador");
				return"";
			}
			System.out.println("Rol; "+login.getRol());
			if (login.getRol().equals("Administrador")) {
			
				if (login.isIsnuevo()) {
					return "administrador/seguridadAdministrador?faces-redirect=true";
				}
				if (!login.isTieneFicha()) {
					return"administrador/menu?faces-redirect=true";
				}
				return "administrador/menu?faces-redirect=true";
			} else {
				if (login.getRol().equals("Investigador")) {
					if (login.isIsnuevo()==true) {
						return "investigador/seguridadInvestigador?faces-redirect=true";
					
					}
					if (!login.isTieneFicha()) {
						return"investigador/fichaInvestigador?faces-redirect=true";
					}
					return "investigador/menu?faces-redirect=true";
				} else {
					System.out.println("Rol; "+login.getRol());
					if (login.isIsnuevo()==true) {
						System.out.println("Ronuevol; "+login.getRol());
						return "usuario/seguridadUsuario?faces-redirect=true";
					}
					if (!login.isTieneFicha()) {
						System.out.println("Ronuevfichaol; "+login.getRol());
						return"usuario/fichaUsuario?faces-redirect=true";
					}
					System.out.println("Rno; "+login.getRol());
					return "usuario/menu?faces-redirect=true";
				}
			}
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			return "";
		}
	}
	public String  actionCerrarSesion() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();		
		return "/login?faces-redirect=true";
	}
	
	public void actionComprobarSessionLogin(){
		try {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
    	String path=ec.getRequestPathInfo();
    	if(login==null) {
    		if(path.equals("/login.xhtml")) {
    	    	return;
    		}else {
    			ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
			return;
    		}
    	}
    	else {
    	activoLogin=true;
    	}
    	
    	if(!activoLogin){
    	ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
    	return;
    	}else{
    		if(path.equals("/login.xhtml")) {
    			if (login.getRol().equals("Administrador")) {
    				ec.redirect(ec.getRequestContextPath() +	 "/faces/administrador/menu.xhtml");
    			 	return;
				}else {
					if (login.getRol().equals("Investigador")) {
						ec.redirect(ec.getRequestContextPath() + "/faces/investigador/menu.xhtml");
					 	return;
							
					}else {
							ec.redirect(ec.getRequestContextPath() + "/faces/usuario/menu.xhtml");
						 	return;
					}
				}
    		}
        	//si hizo login, verificamos que acceda a paginas permitidas:
        
    	//si hizo login, verificamos que acceda a paginas permitidas:
    	if(login.getRol().equals("Administrador")){

    		if(!path.contains("/administrador/")) {
    	ec.redirect(ec.getRequestContextPath() +"/faces/login.xhtml");
    	return;
    		}else
    	return;
    	}
    	//si hizo login, verificamos que acceda a paginas permitidas:
    	if(login.getRol().equals("Investigador")){
    	if(!path.contains("/investigador/")) {
    	ec.redirect(ec.getRequestContextPath() +"/faces/login.xhtml");
    	return;
    	}
    	else
    	return;
    	}
    	if(login.getRol().equals("Usuario")){
        	if(!path.contains("/usuario/")) {
        	ec.redirect(ec.getRequestContextPath() +"/faces/login.xhtml");
        	return;
        	}else
        	return;
        	}
    	
    	}
    	} catch (IOException e) {
    	e.printStackTrace();
    	JSFUtil.crearMensajeError(""+e.getMessage());
    	}
    	return;
    	}          
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	public boolean isActivoLogin() {
		return activoLogin;
	}
	public void setActivoLogin(boolean activoLogin) {
		this.activoLogin = activoLogin;
	}
	public Usuario getRegistroUsuario() {
		return registroUsuario;
	}
	public void setRegistroUsuario(Usuario registroUsuario) {
		this.registroUsuario = registroUsuario;
	}
	

}
