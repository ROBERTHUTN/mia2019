package mia.core.model.investigador.view.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import mia.core.model.administrador.ManagerAdministrador;
import mia.core.model.entities.AreaInvestigacion;
import mia.core.model.entities.Curso;
import mia.core.model.entities.FichaPersonal;
import mia.core.model.entities.OrganizacionFichapersonal;
import mia.core.model.entities.Usuario;
import mia.core.model.entities.UsuarioCurso;
import mia.core.model.entities.UsuarioCursoModulo;
import mia.core.model.entities.UsuarioInteresArea;
import mia.core.model.entities.UsuarioProyecto;
import mia.core.model.investigador.ManagerInvestigador;
import mia.core.model.login.view.controller.BeanLogin;
import mia.core.model.usuario.ManagerUserCurso;
import mia.core.model.usuario.dto.UserCursoModuloDTO;
import mia.core.model.usuario.dto.UsuarioCursoDTO;
import mia.modulos.view.util.JSFUtil;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class BeanInvestigador implements Serializable {

	private static final long serialVersionUID = 1L;
//FICHA_
	private FichaPersonal fichaPersonal = new FichaPersonal();
	
	private AreaInvestigacion areaInvestigacion = new AreaInvestigacion(); 
	private OrganizacionFichapersonal organizacionFichapersonal= new OrganizacionFichapersonal();
	private UsuarioInteresArea usuarioInteresArea = new UsuarioInteresArea();

	private FichaPersonal fichaPersonalE;
	private AreaInvestigacion areaInvestigacionE;
	private OrganizacionFichapersonal organizacionFichapersonalE;
	private UsuarioInteresArea UsuarioInteresAreaE; 
	private UsuarioCurso usuarioCursoIng=new UsuarioCurso();
	private UsuarioCurso usuarioCursoAct=new UsuarioCurso();
	private UsuarioCurso usuarioCursoEdit=new UsuarioCurso();
	private List<UsuarioCurso> listaUsuariosCursos;
	private List<UsuarioCursoDTO> listaUsuariosCursosDTO;
	private List<UsuarioCursoModulo> listaUsuariosCursosModulos=new ArrayList<UsuarioCursoModulo>();;
	private boolean ingresadoModulos;
	private UsuarioCursoModulo usuarioCursoModuloE;
	private boolean error;
	private UserCursoModuloDTO useMDTO;
	private UsuarioCursoDTO  useCDTO;
//claves foraneas
	private int id_rol_fk;
	private long id_ficha_fk;
	private long id_usuario_fk;
	private int id_organizacion;
	private int id_area_fk;
	@EJB
	private ManagerAdministrador managerAdministrador ;	
	@EJB
	private ManagerInvestigador managerInvestigador;
	@EJB
	private ManagerUserCurso managerUserCurso; 
//ROLES
	private List<AreaInvestigacion> investigacionareas;
	private List<OrganizacionFichapersonal> organizacionFichapersonales;
	private List<OrganizacionFichapersonal> filteredorganizacionFichapersonales;
	private List<FichaPersonal> listaFichaU;
	private List<FichaPersonal> ListaFichaIduser;
	private List<Usuario> listaUsuario;
	private List<FichaPersonal> listaFichaUvoluntariado;
	private List<UsuarioProyecto>listaOrganizaciones;
	private List<UsuarioInteresArea> areainteres;
	private List<Curso>listaCursos;
	private List<UserCursoModuloDTO> listaUserCursoModuloDTOs;
	private List<UsuarioCursoDTO> listaUsuariosCursoDTO;
	private UsuarioCursoDTO userCursoDtoEdit;
	private String mensaje;
	private int fk_id_curso;
	@Inject 
	private BeanLogin beanLogin;
	
	@PostConstruct
	public void init() {
		try {
		    listaCursos=managerAdministrador.findAllCursos();
			investigacionareas= managerInvestigador.findAllAreaInvestigaciones();
			listaFichaU=managerAdministrador.findAllFichaPersonalByRolUsuario();
			 listaUsuario=managerAdministrador.findAllUsuariosByRolUsuario();
			// ListaFichaIduser= managerAdministrador.findAllFichaPersonalByIdUsuario(id_usuario_f);
			listaFichaUvoluntariado= managerAdministrador.findAllFichaPersonalVoluntariado();
			listaOrganizaciones=managerAdministrador.findUsuarioProyectoByIdUsuario(beanLogin.getLogin().getId_usuario());
			organizacionFichapersonales=managerInvestigador.findAllOrganizacionFichapersonalesByOrganizacion(listaOrganizaciones);
		
			areainteres=managerInvestigador.findAllUsuarioInteresAreaesByIdLogin(beanLogin.getLogin().getId_usuario());
			listaUsuariosCursos=managerInvestigador.findAllUsuariosCursosByIdUsuario(beanLogin.getLogin().getId_usuario());
			listaUsuariosCursosDTO=managerUserCurso.cargarListaUsuarioCursoDTOs(listaUsuariosCursos);
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}
	
	public String actionEditarTodosLosModulos() {
		try {
			
	System.out.println("BEAN");
	   managerInvestigador.editarFechasModulosByPadre(userCursoDtoEdit);
		listaUsuariosCursos=managerInvestigador.findAllUsuariosCursosByIdUsuario(beanLogin.getLogin().getId_usuario());
		listaUsuariosCursosDTO=managerUserCurso.cargarListaUsuarioCursoDTOs(listaUsuariosCursos);

	   JSFUtil.crearMensajeInfo("Módulos editados correctamente");
	    return "curso.xhtml?faces-redirect=true";
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			return"";
		}finally {
			JSFUtil.crearMensajeFastFinal();
		}
	}
	public String actionSeguimientoUsuariosCurso(UsuarioCursoDTO u) {
		try {
		List<UsuarioCurso>lista=new ArrayList<UsuarioCurso>();
		lista=managerUserCurso.findAllUsuarioCursoHijos(u.getIdUsuariocurso());
			listaUsuariosCursoDTO=managerUserCurso.cargarListaUsuarioCursoDTOs(lista);
		     return "seguimientoCursos.xhtml?faces-redirect=true";
		} catch (ParseException e) {
			e.printStackTrace();
			JSFUtil.crearMensajeError(e.getMessage());
			return"";
		}finally {
			JSFUtil.crearMensajeFastFinal();
		}
 
		
	}
	public void actionListenerCargarFechaInicioFechaFin(UsuarioCursoDTO u) {
		try {
			useCDTO=u;
		} catch (Exception e) {
		JSFUtil.crearMensajeError(e.getMessage());
		}

		
	}
	public String actionRedireccionarEditarModulos(UsuarioCursoDTO uDTO) {
		try {
			userCursoDtoEdit=uDTO;
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
		return "editarModulos.xhtml?faces-redirect=true";
		
	}

	public void onRowEditModulos(RowEditEvent event) {
        
     	try {
     		
     		UsuarioCursoModulo mo=(UserCursoModuloDTO)event.getObject();
            error=false;
        	UsuarioCursoModulo m=managerInvestigador.findAllUsuariosCursosModulosByIdUsuarioCursoModulo(mo.getIdUsuarioCursoModulo());
     		 managerInvestigador.editarfechaIniFin(m, mo.getFechaInicioProgramada(), mo.getFechaFinProgramada());
     		listaUsuariosCursos=managerInvestigador.findAllUsuariosCursosByIdUsuario(beanLogin.getLogin().getId_usuario());
     		listaUsuariosCursosDTO=managerUserCurso.cargarListaUsuarioCursoDTOs(listaUsuariosCursos);
     		userCursoDtoEdit=managerUserCurso.findUsuarioCursoDTO(listaUsuariosCursosDTO, userCursoDtoEdit);
     		mensaje=mo.getCursoModulo().getModulo().getNombre()+" ha sido modificado correctamente";
			JSFUtil.crearMensajeInfo(mo.getCursoModulo().getModulo().getNombre());
		} catch (Exception e) {
			error=true;
			listaUsuariosCursos=managerInvestigador.findAllUsuariosCursosByIdUsuario(beanLogin.getLogin().getId_usuario());
     		try {
			listaUsuariosCursosDTO=managerUserCurso.cargarListaUsuarioCursoDTOs(listaUsuariosCursos);
			userCursoDtoEdit=managerUserCurso.findUsuarioCursoDTO(listaUsuariosCursosDTO, userCursoDtoEdit);
     		
     		} catch (ParseException e1) {
	
				e1.printStackTrace();
			}
		     mensaje=e.getMessage();
		JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
    	
    }
	public void onRowEdit(RowEditEvent event) {
         
     	try {
     		
     		UsuarioCursoModulo mo=(UsuarioCursoModulo)event.getObject();
            error=false;
     	
     		 managerInvestigador.editarfechaIniFin(mo, mo.getFechaInicioProgramada(), mo.getFechaFinProgramada());
     		listaUsuariosCursosModulos=	managerInvestigador.findAllUsuariosCursosModulosByIdUsuarioCurso(usuarioCursoAct.getIdUsuariocurso());
			
     		// listaUsuariosCursosModulos= managerInvestigador.ListaUserCM(listaUsuariosCursosModulos, mo);
			mensaje=mo.getCursoModulo().getModulo().getNombre()+" ha sido modificado correctamente";
			JSFUtil.crearMensajeInfo(mo.getCursoModulo().getModulo().getNombre());
		} catch (Exception e) {
			error=true;
			listaUsuariosCursosModulos=	managerInvestigador.findAllUsuariosCursosModulosByIdUsuarioCurso(usuarioCursoAct.getIdUsuariocurso());
			mensaje=e.getMessage();
		JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
    	
    }
	public String actionListenerredireccionarCursoEditar() {
		try {
	listaUsuariosCursos= managerUserCurso.findAllUsuarioCursoesbyUser(beanLogin.getLogin().getId_usuario());
	listaUsuariosCursosDTO=managerUserCurso.cargarListaUsuarioCursoDTOs(listaUsuariosCursos);
		JSFUtil.crearMensajeInfo("Cursos realizados correctamente.");
		return"curso.xhtml?faces-redirect=true";
		} catch (Exception e) {
			
		}finally {
			JSFUtil.crearMensajeFastFinal();
		}
		return"";
	}
	public String actionListenerredireccionarCurso() {
		try {
	listaUsuariosCursos= managerUserCurso.findAllUsuarioCursoesbyUser(beanLogin.getLogin().getId_usuario());
	listaUsuariosCursosDTO=managerUserCurso.cargarListaUsuarioCursoDTOs(listaUsuariosCursos);
		JSFUtil.crearMensajeInfo("Cursos realizados correctamente.");
		return"curso.xhtml?faces-redirect=true";
		} catch (Exception e) {
			
		}finally {
			JSFUtil.crearMensajeFastFinal();
		}
		return"";
	}
	public void onRowCancel(RowEditEvent event) {
UsuarioCursoModulo mo=(UsuarioCursoModulo)event.getObject();
    	JSFUtil.crearMensajeInfo(mo.getCursoModulo().getModulo().getNombre());
    }

	public String actionListenerIngresarUsuarioCursoInvestigador() {
		try {
			usuarioCursoAct=managerUserCurso.ingresarUsuarioCursoInvestigador(beanLogin.getLogin().getId_usuario(), fk_id_curso,usuarioCursoIng);
			ingresadoModulos=true;
			listaUsuariosCursosModulos=new ArrayList<UsuarioCursoModulo>();
			listaUsuariosCursosModulos=	managerInvestigador.findAllUsuariosCursosModulosByIdUsuarioCurso(usuarioCursoAct.getIdUsuariocurso());
			listaUsuariosCursos= managerUserCurso.findAllUsuarioCursoesbyUser(beanLogin.getLogin().getId_usuario());
			JSFUtil.crearMensajeInfo("creada correctamente");
		return"nuevosModulos?faces-redirect=true";
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
			return"";
		}

	}
	public void actionListenerEditarFechasUsuarioCursoInvestigador() {
		try {
			//a
			usuarioCursoEdit=useCDTO;
			managerUserCurso.editarUsuarioCursoeHijos(useCDTO);
			listaUsuariosCursos=managerInvestigador.findAllUsuariosCursosByIdUsuario(beanLogin.getLogin().getId_usuario());
			listaUsuariosCursosDTO=managerUserCurso.cargarListaUsuarioCursoDTOs(listaUsuariosCursos);
			JSFUtil.crearMensajeInfo("Curso "+useCDTO.getCurso().getNombre()+" modificado correctamente");
		} catch (Exception e) {
			listaUsuariosCursos=managerInvestigador.findAllUsuariosCursosByIdUsuario(beanLogin.getLogin().getId_usuario());
			try {
				listaUsuariosCursosDTO=managerUserCurso.cargarListaUsuarioCursoDTOs(listaUsuariosCursos);
			} catch (ParseException e1) {		
				e1.printStackTrace();
			}
		
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actionListenerIngresarAreaInvestigacion() {
		try {
			managerInvestigador.ingresarAreaInvestigacion(areaInvestigacion);
			investigacionareas = managerInvestigador.findAllAreaInvestigaciones();
			JSFUtil.crearMensajeInfo("Are creada correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}
	
	public void actionListenerEliminarAreaInvestigacion(int id_area) {
		try {
			managerInvestigador.eliminarAreaInvestigacion(id_area);
			investigacionareas = managerInvestigador.findAllAreaInvestigaciones();
			JSFUtil.crearMensajeInfo("Area de investigacion eliminado correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}//
	public void actionListenerCargarOrganizacionFicha(OrganizacionFichapersonal organFichaC) {
		try {
			organizacionFichapersonalE = organFichaC;
			id_usuario_fk=organFichaC.getUsuario().getIdUsuario();
			id_organizacion=organFichaC.getOrganizacion().getIdOrganizacion();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}	

	public void actionListenerEditarAreaInvestigacion() {
		try {
			managerInvestigador.editarAreaInvestigacion(areaInvestigacionE);
			JSFUtil.crearMensajeInfo("Area Investigacion editado correctamente");
		} catch (Exception e) {
			investigacionareas = managerInvestigador.findAllAreaInvestigaciones();
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}
	public void actionListenerEditarFechaIniFin(UsuarioCursoModulo r) {
		try {
			usuarioCursoModuloE= managerInvestigador.findAllUsuariosCursosModulosByIdUsuarioCursoModulo(r.getIdUsuarioCursoModulo());
			managerInvestigador.editarfechaIniFin(usuarioCursoModuloE,r.getFechaInicioProgramada(), r.getFechaFinProgramada());
			JSFUtil.crearMensajeInfo("Fecha de inicio y fin módulo editado correctamente");
		} catch (Exception e) {
			//investigacionareas = managerInvestigador.findAllAreaInvestigaciones();
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}
	
	
	public void actionListenerIngresarOrganizacionFichapersonal() {
		try {
			managerInvestigador.ingresarOrganizacionFichapersonal(id_usuario_fk, id_organizacion);
			listaOrganizaciones=managerAdministrador.findUsuarioProyectoByIdUsuario(beanLogin.getLogin().getId_usuario());
			organizacionFichapersonales=managerInvestigador.findAllOrganizacionFichapersonalesByOrganizacion(listaOrganizaciones);
			JSFUtil.crearMensajeInfo("Usuario creado en la organización correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}
	
	public void actionListenerEliminarOrganizacionFichapersonal(int id_organizacion_ficha) {
		try {
			managerInvestigador.eliminarOrganizacionFichapersonal(id_organizacion_ficha);
			listaOrganizaciones=managerAdministrador.findUsuarioProyectoByIdUsuario(beanLogin.getLogin().getId_usuario());
			organizacionFichapersonales=managerInvestigador.findAllOrganizacionFichapersonalesByOrganizacion(listaOrganizaciones);
			JSFUtil.crearMensajeInfo("Usuario eliminado correctamente de la organización.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}//
	
	public void actionListenerCargar(OrganizacionFichapersonal organizacionFichaC) {
		try {
			organizacionFichapersonalE = organizacionFichaC;
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}
	public void actionListenerEditarOrganizacionFichapersonal() {
		try {
			managerInvestigador.editarOrganizacionFichapersonal(organizacionFichapersonalE, id_usuario_fk, id_organizacion);
			
			listaOrganizaciones=managerAdministrador.findUsuarioProyectoByIdUsuario(beanLogin.getLogin().getId_usuario());
			organizacionFichapersonales=managerInvestigador.findAllOrganizacionFichapersonalesByOrganizacion(listaOrganizaciones);
			JSFUtil.crearMensajeInfo("Usuario editado correctamente en la organización");
		} catch (Exception e) {
			
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}
	
	/**bean de área y interés**/
	

	public void actionListenerCargarAreaFicha(UsuarioInteresArea AreaFichaC) {
		try {
			usuarioInteresArea = AreaFichaC;
			id_area_fk=AreaFichaC.getAreaInvestigacion().getAreaId();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}	
	
	public void actionListenerIngresarUsuarioInteresArea() {
		try {
			managerInvestigador.ingresarUsuarioInteresArea(beanLogin.getLogin().getId_usuario(), id_area_fk);
			areainteres = managerInvestigador.findAllUsuarioInteresAreaes();
			JSFUtil.crearMensajeInfo("Usuario agregado área de Interes de investigación.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}
	
	public void actionListenerEliminarUsuarioInteresArea(int id_interes_area) {
		try {
			managerInvestigador.eliminarUsuarioInteresArea(id_interes_area);
			areainteres = managerInvestigador.findAllUsuarioInteresAreaes();
			JSFUtil.crearMensajeInfo("Usuario eliminado correctamente de los .");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}//
	
	
	public void actionListenerEditarUsuarioInteresArea() {
		try {
			managerInvestigador.editarUsuarioInteresArea(usuarioInteresArea, beanLogin.getLogin().getId_usuario(), id_area_fk);
			JSFUtil.crearMensajeInfo("Usuario editado correctamente en la área de investigación de interés");
		} catch (Exception e) {
			areainteres= managerInvestigador.findAllUsuarioInteresAreaes();
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}


	public FichaPersonal getFichaPersonal() {
		return fichaPersonal;
	}

	public void setFichaPersonal(FichaPersonal fichaPersonal) {
		this.fichaPersonal = fichaPersonal;
	}

	public AreaInvestigacion getAreaInvestigacion() {
		return areaInvestigacion;
	}

	public void setAreaInvestigacion(AreaInvestigacion areaInvestigacion) {
		this.areaInvestigacion = areaInvestigacion;
	}

	public FichaPersonal getFichaPersonalE() {
		return fichaPersonalE;
	}

	public void setFichaPersonalE(FichaPersonal fichaPersonalE) {
		this.fichaPersonalE = fichaPersonalE;
	}

	public AreaInvestigacion getAreaInvestigacionE() {
		return areaInvestigacionE;
	}

	public void setAreaInvestigacionE(AreaInvestigacion areaInvestigacionE) {
		this.areaInvestigacionE = areaInvestigacionE;
	}

	public int getId_rol_fk() {
		return id_rol_fk;
	}

	public void setId_rol_fk(int id_rol_fk) {
		this.id_rol_fk = id_rol_fk;
	}



	public void setId_ficha_fk(int id_ficha_fk) {
		this.id_ficha_fk = id_ficha_fk;
	}

	public List<AreaInvestigacion> getInvestigacionareas() {
		return investigacionareas;
	}

	public void setInvestigacionareas(List<AreaInvestigacion> investigacionareas) {
		this.investigacionareas = investigacionareas;
	}

	public BeanLogin getBeanLogin() {
		return beanLogin;
	}

	public void setBeanLogin(BeanLogin beanLogin) {
		this.beanLogin = beanLogin;
	}

	public OrganizacionFichapersonal getOrganizacionFichapersonal() {
		return organizacionFichapersonal;
	}

	public void setOrganizacionFichapersonal(OrganizacionFichapersonal organizacionFichapersonal) {
		this.organizacionFichapersonal = organizacionFichapersonal;
	}

	public OrganizacionFichapersonal getOrganizacionFichapersonalE() {
		return organizacionFichapersonalE;
	}

	public void setOrganizacionFichapersonalE(OrganizacionFichapersonal organizacionFichapersonalE) {
		this.organizacionFichapersonalE = organizacionFichapersonalE;
	}

	public int getId_organizacion() {
		return id_organizacion;
	}

	public void setId_organizacion(int id_organizacion) {
		this.id_organizacion = id_organizacion;
	}

	public List<OrganizacionFichapersonal> getOrganizacionFichapersonales() {
		return organizacionFichapersonales;
	}

	public void setOrganizacionFichapersonales(List<OrganizacionFichapersonal> organizacionFichapersonales) {
		this.organizacionFichapersonales = organizacionFichapersonales;
	}

	public List<FichaPersonal> getListaFichaU() {
		return listaFichaU;
	}

	public void setListaFichaU(List<FichaPersonal> listaFichaU) {
		this.listaFichaU = listaFichaU;
	}



	public long getId_ficha_fk() {
		return id_ficha_fk;
	}

	public void setId_ficha_fk(long id_ficha_fk) {
		this.id_ficha_fk = id_ficha_fk;
	}

	public UsuarioInteresArea getUsuarioInteresArea() {
		return usuarioInteresArea;
	}

	public void setUsuarioInteresArea(UsuarioInteresArea usuarioInteresArea) {
		this.usuarioInteresArea = usuarioInteresArea;
	}

	public UsuarioInteresArea getUsuarioInteresAreaE() {
		return UsuarioInteresAreaE;
	}

	public void setUsuarioInteresAreaE(UsuarioInteresArea usuarioInteresAreaE) {
		UsuarioInteresAreaE = usuarioInteresAreaE;
	}

	public int getId_area_fk() {
		return id_area_fk;
	}

	public void setId_area_fk(int id_area_fk) {
		this.id_area_fk = id_area_fk;
	}

	public List<UsuarioInteresArea> getAreainteres() {
		return areainteres;
	}

	public void setAreainteres(List<UsuarioInteresArea> areainteres) {
		this.areainteres = areainteres;
	}

	public List<FichaPersonal> getListaFichaUvoluntariado() {
		return listaFichaUvoluntariado;
	}

	public void setListaFichaUvoluntariado(List<FichaPersonal> listaFichaUvoluntariado) {
		this.listaFichaUvoluntariado = listaFichaUvoluntariado;
	}

	public long getId_usuario_fk() {
		return id_usuario_fk;
	}

	public void setId_usuario_fk(long id_usuario_fk) {
		this.id_usuario_fk = id_usuario_fk;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public List<UsuarioProyecto> getListaOrganizaciones() {
		return listaOrganizaciones;
	}

	public void setListaOrganizaciones(List<UsuarioProyecto> listaOrganizaciones) {
		this.listaOrganizaciones = listaOrganizaciones;
	}

	public List<Curso> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(List<Curso> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public int getFk_id_curso() {
		return fk_id_curso;
	}

	public void setFk_id_curso(int fk_id_curso) {
		this.fk_id_curso = fk_id_curso;
	}

	public UsuarioCurso getUsuarioCursoIng() {
		return usuarioCursoIng;
	}

	public void setUsuarioCursoIng(UsuarioCurso usuarioCursoIng) {
		this.usuarioCursoIng = usuarioCursoIng;
	}

	public UsuarioCurso getUsuarioCursoEdit() {
		return usuarioCursoEdit;
	}

	public void setUsuarioCursoEdit(UsuarioCurso usuarioCursoEdit) {
		this.usuarioCursoEdit = usuarioCursoEdit;
	}

	public List<FichaPersonal> getListaFichaIduser() {
		return ListaFichaIduser;
	}

	public void setListaFichaIduser(List<FichaPersonal> listaFichaIduser) {
		ListaFichaIduser = listaFichaIduser;
	}

	public List<UsuarioCurso> getListaUsuariosCursos() {
		return listaUsuariosCursos;
	}

	public void setListaUsuariosCursos(List<UsuarioCurso> listaUsuariosCursos) {
		this.listaUsuariosCursos = listaUsuariosCursos;
	}

	public UsuarioCurso getUsuarioCursoAct() {
		return usuarioCursoAct;
	}

	public void setUsuarioCursoAct(UsuarioCurso usuarioCursoAct) {
		this.usuarioCursoAct = usuarioCursoAct;
	}

	public boolean isIngresadoModulos() {
		return ingresadoModulos;
	}

	public void setIngresadoModulos(boolean ingresadoModulos) {
		this.ingresadoModulos = ingresadoModulos;
	}

	public List<UsuarioCursoModulo> getListaUsuariosCursosModulos() {
		return listaUsuariosCursosModulos;
	}

	public void setListaUsuariosCursosModulos(List<UsuarioCursoModulo> listaUsuariosCursosModulos) {
		this.listaUsuariosCursosModulos = listaUsuariosCursosModulos;
	}
	public UsuarioCursoModulo getUsuarioCursoModuloE() {
		return usuarioCursoModuloE;
	}
	public void setUsuarioCursoModuloE(UsuarioCursoModulo usuarioCursoModuloE) {
		this.usuarioCursoModuloE = usuarioCursoModuloE;
	}

	public List<UsuarioCursoDTO> getListaUsuariosCursosDTO() {
		return listaUsuariosCursosDTO;
	}

	public void setListaUsuariosCursosDTO(List<UsuarioCursoDTO> listaUsuariosCursosDTO) {
		this.listaUsuariosCursosDTO = listaUsuariosCursosDTO;
	}

	public List<UserCursoModuloDTO> getListaUserCursoModuloDTOs() {
		return listaUserCursoModuloDTOs;
	}

	public void setListaUserCursoModuloDTOs(List<UserCursoModuloDTO> listaUserCursoModuloDTOs) {
		this.listaUserCursoModuloDTOs = listaUserCursoModuloDTOs;
	}

	public List<UsuarioCursoDTO> getListaUsuariosCursoDTO() {
		return listaUsuariosCursoDTO;
	}

	public void setListaUsuariosCursoDTO(List<UsuarioCursoDTO> listaUsuariosCursoDTO) {
		this.listaUsuariosCursoDTO = listaUsuariosCursoDTO;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public UserCursoModuloDTO getUseMDTO() {
		return useMDTO;
	}

	public void setUseMDTO(UserCursoModuloDTO useMDTO) {
		this.useMDTO = useMDTO;
	}

	public UsuarioCursoDTO getUseCDTO() {
		return useCDTO;
	}

	public void setUseCDTO(UsuarioCursoDTO useCDTO) {
		this.useCDTO = useCDTO;
	}

	public UsuarioCursoDTO getUserCursoDtoEdit() {
		return userCursoDtoEdit;
	}

	public void setUserCursoDtoEdit(UsuarioCursoDTO userCursoDtoEdit) {
		this.userCursoDtoEdit = userCursoDtoEdit;
	}

	public List<OrganizacionFichapersonal> getFilteredorganizacionFichapersonales() {
		return filteredorganizacionFichapersonales;
	}

	public void setFilteredorganizacionFichapersonales(
			List<OrganizacionFichapersonal> filteredorganizacionFichapersonales) {
		this.filteredorganizacionFichapersonales = filteredorganizacionFichapersonales;
	}


}
