package mia.core.model.usuario.view.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.wildfly.security.audit.SyslogAuditEndpoint;

import mia.core.model.administrador.ManagerCurso;
import mia.core.model.administrador.view.controller.BeanCurso;
import mia.core.model.cuestionario.ManagerCuestionario;
import mia.core.model.entities.Curso;
import mia.core.model.entities.CursoModulo;
import mia.core.model.entities.Modulo;
import mia.core.model.entities.Usuario;
import mia.core.model.entities.UsuarioCurso;
import mia.core.model.login.view.controller.BeanLogin;
import mia.core.model.usuario.ManagerUserCurso;
import mia.core.model.usuario.dto.UserCursoModuloDTO;
import mia.modulos.view.util.JSFUtil;
@Named
@SessionScoped
public class BeanUserCursoModulo implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private CursoModulo cursoModulo= new CursoModulo(); 
	private Curso curso= new Curso();
	private Modulo modulo= new Modulo();
	private Usuario usuario= new Usuario();
	private String direccion;
	private String nombre;

	private String moduloRealizado;
	
	@Inject
	private BeanLogin login;
	@Inject
	private BeanCurso beanCurso;

	@EJB
	private ManagerUserCurso managerUserCurso;
	
	@EJB 
	private ManagerCurso managerCurso;
	


	private List<CursoModulo> cursomoduloID;	
	private List<UsuarioCurso> usuariocursoID;
	private UsuarioCurso usuariocursoCar;

	
	@PostConstruct
	public void init() {
		
	}
	
	
	public void actionIngresarCambio(UserCursoModuloDTO userCursoDto) {
		try {
			managerUserCurso.editarAvanceCurso( userCursoDto);
			userccursomoduloIdDto= managerUserCurso.cargarListaUserCurso(usuariocursoCar);
			System.out.println("usuariocursoCar;"+usuariocursoCar.getIdUsuariocurso());
			JSFUtil.crearMensajeInfo("Felicidades a finalizado el módulo");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<UserCursoModuloDTO> getUserccursomoduloIdDto() {
		return userccursomoduloIdDto;
	}

	public void setUserccursomoduloIdDto(List<UserCursoModuloDTO> userccursomoduloIdDto) {
		this.userccursomoduloIdDto = userccursomoduloIdDto;
	}
	
	//** Lista de Modulos segun el curso*/
	// esta sin terminar
	public String actionModulosbyCurso( CursoModulo curso)
	{
		try { 
			
			return "modulos?faces-redirect=true";
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			return "";
		}
	}
	
	public String actionCursoModulosbyUserCurso( UsuarioCurso userCurso)
	{
		//
		try { 
            usuariocursoCar=userCurso;
			userccursomoduloIdDto= managerUserCurso.cargarListaUserCurso(userCurso);
			JSFUtil.crearMensajeInfo("Felicidades a finalizado el módulo");
			return "modulos?faces-redirect=true";
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			return "";
		}
	}


	public List<CursoModulo> getCursomoduloID() {
		return cursomoduloID;
	}


	public void setCursomoduloID(List<CursoModulo> cursomoduloID) {
		this.cursomoduloID = cursomoduloID;
	}


	public CursoModulo getCursoModulo() {
		return cursoModulo;
	}


	public void setCursoModulo(CursoModulo cursoModulo) {
		this.cursoModulo = cursoModulo;
	}


	public Curso getCurso() {
		return curso;
	}


	public void setCurso(Curso curso) {
		this.curso = curso;
	}


	public Modulo getModulo() {
		return modulo;
	}


	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public List<UsuarioCurso> getUsuariocursoID() {
		return usuariocursoID;
	}


	public void setUsuariocursoID(List<UsuarioCurso> usuariocursoID) {
		this.usuariocursoID = usuariocursoID;
	}


	public String getModuloRealizado() {
		return moduloRealizado;
	}


	public void setModuloRealizado(String moduloRealizado) {
		this.moduloRealizado = moduloRealizado;
	}

	public UsuarioCurso getUsuariocursoCar() {
		return usuariocursoCar;
	}


	public void setUsuariocursoCar(UsuarioCurso usuariocursoCar) {
		this.usuariocursoCar = usuariocursoCar;
	}

	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}





	private List<UserCursoModuloDTO> userccursomoduloIdDto;


} 
 