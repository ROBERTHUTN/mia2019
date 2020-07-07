package mia.core.model.usuario.view.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import mia.core.model.administrador.ManagerCurso;
import mia.core.model.cuestionario.ManagerCuestionario;
import mia.core.model.cuestionario.dto.PreguntaModuloDTO;
import mia.core.model.entities.Curso;
import mia.core.model.entities.CursoModulo;
import mia.core.model.entities.Modulo;
import mia.core.model.entities.Preguntamodulo;
import mia.core.model.entities.Usuario;
import mia.core.model.entities.UsuarioCurso;
import mia.core.model.usuario.ManagerUserCurso;
import mia.core.model.usuario.dto.UserCursoModuloDTO;
import mia.core.model.usuario.dto.UsuarioCursoDTO;
import mia.modulos.view.util.JSFUtil;

@Named
@SessionScoped
public class BeanUserCursoModulo implements Serializable {

	private static final long serialVersionUID = 1L;
    private int numeroModulo;
	private CursoModulo cursoModulo = new CursoModulo();
	private Curso curso = new Curso();
	private Modulo modulo = new Modulo();
	private Usuario usuario = new Usuario();
	private String direccion;
	private String nombre;
	private List<UserCursoModuloDTO> userccursomoduloIdDto;
	private UserCursoModuloDTO usuarioCursoModuloDto;
	private List<PreguntaModuloDTO> preguntamoduloDTO;
	private UserCursoModuloDTO usuarioCursoDto;
	private String moduloRealizado;
	private CursoModulo cursoMod;
	@EJB
	private ManagerUserCurso managerUserCurso;
	@EJB
	private ManagerCurso managerCurso;
	@EJB
	private ManagerCuestionario managerCuestionario;
	private List<CursoModulo> cursomoduloID;
	private List<UsuarioCurso> usuariocursoID;
	private UsuarioCurso usuariocursoCar;
	private UsuarioCursoDTO usuariocursoCarDTO;
	private UserCursoModuloDTO usuarioCursoModuloC;
	@PostConstruct
	public void init() {

	}

	@SuppressWarnings("unused")
	public String actionContestarModulo() {
		try {

			PreguntaModuloDTO preguntaOpcionRespuestaDTO = new PreguntaModuloDTO();
			List<Preguntamodulo> preguntamodul = managerCuestionario
					.findAllPreguntamodulobymodulo(cursoMod.getModulo().getIdModulo());
			preguntamoduloDTO = managerCuestionario.otrometodo(preguntamodul);
			preguntamoduloDTO = managerCuestionario.cargarPreguntasModulodto(preguntamoduloDTO);
			return "preguntasModulo?faces-redirect=true";

		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
		return "";
	}

	public String cargarModulo(UserCursoModuloDTO m) {
		try {
			
			if(m.getCursoModulo().getOrdenCurso()==null)
			{
				JSFUtil.crearMensajeError("El módulo no tiene un orden \n Contáctese con el administrador ");
				return "";
			}
			usuarioCursoModuloC=m;
			numeroModulo=m.getCursoModulo().getOrdenCurso();
			cursoMod=m.getCursoModulo();
			JSFUtil.crearMensajeInfo("Módulo cargado correctamente.");
			return "contenidoModulo?faces-redirect=true";
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		} finally {
			JSFUtil.crearMensajeFastFinal();
		}
		return "";
	}

	public String actionIngresarCambio() {
		try {
			//usuarioCursoModuloDto;
			//usuarioCursoModuloC;usuarioCursoModuloC,usuarioCursoModuloDto,preguntamoduloDTO
			managerUserCurso.editarAvanceCurso(usuariocursoCarDTO, usuarioCursoModuloC, preguntamoduloDTO);
			usuariocursoCarDTO = managerUserCurso.cargarUsuarioCursoDTO(usuariocursoCarDTO);
			JSFUtil.crearMensajeInfo("Felicidades a finalizado el módulo");
			
			return "modulos?faces-redirect=true";

		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
			return "";
		} finally {
			JSFUtil.crearMensajeFastFinal();
		}
	}

	public List<UserCursoModuloDTO> getUserccursomoduloIdDto() {
		return userccursomoduloIdDto;
	}

	public void setUserccursomoduloIdDto(List<UserCursoModuloDTO> userccursomoduloIdDto) {
		this.userccursomoduloIdDto = userccursomoduloIdDto;
	}

	// ** Lista de Modulos segun el curso*/
	// esta sin terminar
	public String actionModulosbyCurso(CursoModulo curso) {
		try {

			return "modulos?faces-redirect=true";
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			return "";
		}
	}

	public String actionCursoModulosbyUserCurso(UsuarioCursoDTO userCursoDTO) {
	try {
			usuariocursoCarDTO = userCursoDTO;
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

	public UserCursoModuloDTO getUsuarioCursoDto() {
		return usuarioCursoDto;
	}

	public void setUsuarioCursoDto(UserCursoModuloDTO usuarioCursoDto) {
		this.usuarioCursoDto = usuarioCursoDto;
	}

	public List<PreguntaModuloDTO> getPreguntamoduloDTO() {
		return preguntamoduloDTO;
	}

	public void setPreguntamoduloDTO(List<PreguntaModuloDTO> preguntamoduloDTO) {
		this.preguntamoduloDTO = preguntamoduloDTO;
	}

	public int getNumeroModulo() {
		return numeroModulo;
	}

	public void setNumeroModulo(int numeroModulo) {
		this.numeroModulo = numeroModulo;
	}

	public UserCursoModuloDTO getUsuarioCursoModuloDto() {
		return usuarioCursoModuloDto;
	}

	public void setUsuarioCursoModuloDto(UserCursoModuloDTO usuarioCursoModuloDto) {
		this.usuarioCursoModuloDto = usuarioCursoModuloDto;
	}

	public CursoModulo getCursoMod() {
		return cursoMod;
	}

	public void setCursoMod(CursoModulo cursoMod) {
		this.cursoMod = cursoMod;
	}

	public UsuarioCursoDTO getUsuariocursoCarDTO() {
		return usuariocursoCarDTO;
	}

	public void setUsuariocursoCarDTO(UsuarioCursoDTO usuariocursoCarDTO) {
		this.usuariocursoCarDTO = usuariocursoCarDTO;
	}

	public UserCursoModuloDTO getUsuarioCursoModuloC() {
		return usuarioCursoModuloC;
	}

	public void setUsuarioCursoModuloC(UserCursoModuloDTO usuarioCursoModuloC) {
		this.usuarioCursoModuloC = usuarioCursoModuloC;
	}
	

}
