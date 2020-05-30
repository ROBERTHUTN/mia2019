package mia.model.seguridad.view.controller;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.sun.xml.internal.fastinfoset.algorithm.IntEncodingAlgorithm;

import mia.core.model.entities.RestablecerContrasenia;
import mia.core.model.login.ManagerRestablecerContrasenia;
import mia.core.model.mail.ManagerMail;
import mia.core.model.util.ModelUtil;
import mia.modulos.view.util.JSFUtil;
import java.io.Serializable;

@Named
@ViewScoped
public class BeanRestablecerContrasenia implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	ManagerRestablecerContrasenia managerRestablecer;
	@EJB
	ManagerMail managerMail;
	
	private boolean restablecer;
	private boolean usuarioR;
	private boolean inicio;
	private String correoU;
	private String codigoVerificacion;
	private String password5;
	private String password4;
	private boolean activar;
	private boolean inicioA;
	private String codigoC;
	private String verificarCodigo;
	private boolean activarContrasenia;
	private long id_restablecer;

public void limpiarCredenciales() {
	activar=false;
	inicioA=true;
	activarContrasenia=false;
	restablecer=false;
	usuarioR=false;
	correoU="";
	codigoVerificacion="";
	password5="";
	password4="";
	codigoC="";
	verificarCodigo="";
	id_restablecer=0;
}
public void guardarNuevaContrasenia() {
	try {
		boolean iguales=ModelUtil.IgualesString(password4, password5);
		if (iguales) {
			managerRestablecer.guardarNuevaContrasenia(correoU, Seguridad.encriptar(password5));
			limpiarCredenciales();
			inicio=true;
			JSFUtil.crearMensajeInfo("Contraseña actualizada correctamente");
		}else {
			password4="";
			JSFUtil.crearMensajeError("Las contraseñas no son iguales");
		}

		
	} catch (Exception e) {
	JSFUtil.crearMensajeError(e.getMessage());
		e.printStackTrace();
	}
	
}
public String regresarInicio() {
	return"login.xhtml?faces-redirect=true";
}
	public void comprobarCorreo() {
		try {
		restablecer = managerRestablecer.existeUsuario(correoU);
	//3 intentos
		boolean intentos=managerRestablecer.intentosRestablecerContrasenia(correoU);
	
	if (intentos) {
	JSFUtil.crearMensajeError("Ha excedido el número de intentos permitidos (3), inténtelo de nuevo más tarde");	
		}else {
		if (restablecer) {
			usuarioR = true;
			activar=true;
			inicioA=true;
			String codigo=managerRestablecer.GenerarPalabra();
			System.out.println(": "+codigo);
		String cod=codigo;
			codigo=Seguridad.encriptar(codigo);
			
			RestablecerContrasenia cont=managerRestablecer.restablecerContrasenia(correoU, codigo);
			codigoC=cont.getCodigo();
			id_restablecer=cont.getIdRestablecer();
			boolean enviarCorreo=managerMail.enviarMensajesElectronicos("Restablecer contraseña", "Su código de confirmación es: "+cod, correoU);
		    
			JSFUtil.crearMensajeInfo("Revise su código de confirmación en su correo electrónico");
		} else {
			JSFUtil.crearMensajeError("Credencial incorrecta.");
		}
	}
		
		} catch (Exception e) {
		JSFUtil.crearMensajeError(e.getMessage());
		}

	}
	public void enviarCodigo() {
		try {
	boolean codigoCorrecto=ModelUtil.IgualesString(Seguridad.encriptar(codigoVerificacion), codigoC);
		if (codigoCorrecto) {
			activarContrasenia=true;
		activar=false;
		managerRestablecer.descativarRestablecer(id_restablecer);
	JSFUtil.crearMensajeInfo("Código correcto");
		} else {
			JSFUtil.crearMensajeError("Código incorrecto.");
		}
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}
	public void codigoVerificacion() {
		try {
			
		
		restablecer = managerRestablecer.existeUsuario(correoU);
		if (restablecer) {
			usuarioR = true;
			activar=true;
		String codigo=managerRestablecer.GenerarPalabra();
		System.out.println("codigo; "+codigo);
			codigo=Seguridad.encriptar(codigo);
			RestablecerContrasenia cont=managerRestablecer.restablecerContrasenia(correoU, codigo);
			codigoC=cont.getCodigo();
			id_restablecer=cont.getIdRestablecer();
			JSFUtil.crearMensajeInfo("Revise su código de confirmación en su correo electrónico");
		} else {
			JSFUtil.crearMensajeError("Credencial incorrecta.");
		}
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}


	public boolean isUsuarioR() {
		return usuarioR;
	}

	public void setUsuarioR(boolean usuarioR) {
		this.usuarioR = usuarioR;
	}

	public String getCorreoU() {
		return correoU;
	}

	public void setCorreoU(String correoU) {
		this.correoU = correoU;
	}

	public boolean isRestablecer() {
		return restablecer;
	}

	public void setRestablecer(boolean restablecer) {
		this.restablecer = restablecer;
	}

	public boolean isActivar() {
		return activar;
	}

	public void setActivar(boolean activar) {
		this.activar = activar;
	}

	public String getCodigoVerificacion() {
		return codigoVerificacion;
	}

	public void setCodigoVerificacion(String codigoVerificacion) {
		this.codigoVerificacion = codigoVerificacion;
	}
	

	public String getCodigoC() {
		return codigoC;
	}
	public void setCodigoC(String codigoC) {
		this.codigoC = codigoC;
	}
	public String getVerificarCodigo() {
		return verificarCodigo;
	}
	public void setVerificarCodigo(String verificarCodigo) {
		this.verificarCodigo = verificarCodigo;
	}
	public String getPassword5() {
		return password5;
	}
	public void setPassword5(String password5) {
		this.password5 = password5;
	}


	public boolean isActivarContrasenia() {
		return activarContrasenia;
	}


	public void setActivarContrasenia(boolean activarContrasenia) {
		this.activarContrasenia = activarContrasenia;
	}


	public boolean isInicioA() {
		return inicioA;
	}


	public void setInicioA(boolean inicioA) {
		this.inicioA = inicioA;
	}
	public String getPassword4() {
		return password4;
	}
	public void setPassword4(String password4) {
		this.password4 = password4;
	}
	public boolean isInicio() {
		return inicio;
	}
	public void setInicio(boolean inicio) {
		this.inicio = inicio;
	}
	public long getId_restablecer() {
		return id_restablecer;
	}
	public void setId_restablecer(long id_restablecer) {
		this.id_restablecer = id_restablecer;
	}

	
	
}
