package mia.core.model.investigador.view.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import mia.core.model.administrador.ManagerAdministrador;
import mia.core.model.entities.AreaInvestigacion;

import mia.core.model.entities.FichaPersonal;
import mia.core.model.entities.Organizacion;
import mia.core.model.entities.OrganizacionFichapersonal;
import mia.core.model.entities.Usuario;
import mia.core.model.entities.UsuarioInteresArea;
import mia.core.model.entities.UsuarioProyecto;
import mia.core.model.investigador.ManagerInvestigador;
import mia.core.model.login.view.controller.BeanLogin;
import mia.modulos.view.util.JSFUtil;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
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
//ROLES
	private List<AreaInvestigacion> investigacionareas;
	private List<OrganizacionFichapersonal> organizacionFichapersonales;
	private List<FichaPersonal> listaFichaU;
	private List<FichaPersonal> ListaFichaIduser;
	private List<Usuario> listaUsuario;
	private List<FichaPersonal> listaFichaUvoluntariado;
	private List<UsuarioProyecto>listaOrganizaciones;
	private List<UsuarioInteresArea> areainteres;
	
	@Inject 
	private BeanLogin beanLogin;
	
	@PostConstruct
	public void init() {
		try {
			investigacionareas= managerInvestigador.findAllAreaInvestigaciones();
			listaFichaU=managerAdministrador.findAllFichaPersonalByRolUsuario();
			 listaUsuario=managerAdministrador.findAllUsuariosByRolUsuario();
			// ListaFichaIduser= managerAdministrador.findAllFichaPersonalByIdUsuario(id_usuario_f);
			listaFichaUvoluntariado= managerAdministrador.findAllFichaPersonalVoluntariado();
			listaOrganizaciones=managerAdministrador.findUsuarioProyectoByIdUsuario(beanLogin.getLogin().getId_usuario());
			// 
			organizacionFichapersonales=managerInvestigador.findAllOrganizacionFichapersonalesByOrganizacion(listaOrganizaciones);
			areainteres=managerInvestigador.findAllUsuarioInteresAreaes();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
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
	
	public void actionListenerIngresarOrganizacionFichapersonal() {
		try {
			managerInvestigador.ingresarOrganizacionFichapersonal(id_usuario_fk, id_organizacion);
			organizacionFichapersonales = managerInvestigador.findAllOrganizacionFichapersonales();
			JSFUtil.crearMensajeInfo("Usuario creado en la organización correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}
	
	public void actionListenerEliminarOrganizacionFichapersonal(int id_organizacion_ficha) {
		try {
			managerInvestigador.eliminarOrganizacionFichapersonal(id_organizacion_ficha);
			organizacionFichapersonales = managerInvestigador.findAllOrganizacionFichapersonales();
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
			
			organizacionFichapersonales = managerInvestigador.findAllOrganizacionFichapersonales();
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
	
	
	

}
