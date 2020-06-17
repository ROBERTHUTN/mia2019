package mia.core.model.investigador.view.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import mia.core.model.entities.AreaInvestigacion;
import mia.core.model.entities.ProyectoInvestigacion;
import mia.core.model.investigador.ManagerInvestigador;
import mia.core.model.login.view.controller.BeanLogin;
import mia.modulos.view.util.JSFUtil;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class BeanProyectoInvestigacion implements Serializable {

	private static final long serialVersionUID = 1L;
//FICHA_
	
	private ProyectoInvestigacion proyectoInvestigacion = new ProyectoInvestigacion(); 
	private AreaInvestigacion areaInvestigacion = new AreaInvestigacion();
	private ProyectoInvestigacion proyectoInvestigacionE;
	
//claves foraneas
	private int id_area_fk;
	
	@EJB
	private ManagerInvestigador managerInvestigador;

//ROLES
	private List<ProyectoInvestigacion> proyectoInvestigaciones;
	private List<AreaInvestigacion> areaInvestigaciones;
	@Inject
	private BeanLogin beanLogin;
	
	@PostConstruct
	public void init() {  
		try {
			proyectoInvestigaciones= managerInvestigador.findAllProyectoInvestigaciones();
			areaInvestigaciones= managerInvestigador.findAllAreaInvestigaciones();
			
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public void actionListenerIngresarProyectoInvestigacion() {
		try {
		
			managerInvestigador.ingresarProyectoInvestigacion(proyectoInvestigacion, id_area_fk);
		
			proyectoInvestigaciones = managerInvestigador.findAllProyectoInvestigaciones();
			JSFUtil.crearMensajeInfo("Proyecto de Investigación creada correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}
	
	public void actionListenerEliminarProyectoInvestigacion(int id_area) {
		try {
			managerInvestigador.eliminarProyectoInvestigacion(id_area);
			proyectoInvestigaciones = managerInvestigador.findAllProyectoInvestigaciones();
			JSFUtil.crearMensajeInfo("Proyecto de investigacion eliminado correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}//
	
	public void actionListenerCargar(ProyectoInvestigacion areaC) {
		try {
			proyectoInvestigacionE = areaC;
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}
	public void actionListenerEditarProyectoInvestigacion() {
		try {
			managerInvestigador.editarProyectoInvestigacion(proyectoInvestigacionE, id_area_fk);
			JSFUtil.crearMensajeInfo("Proyecto de Investigación editado correctamente");
		} catch (Exception e) {
			proyectoInvestigaciones = managerInvestigador.findAllProyectoInvestigaciones();
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}

	public ProyectoInvestigacion getProyectoInvestigacion() {
		return proyectoInvestigacion;
	}

	public void setProyectoInvestigacion(ProyectoInvestigacion proyectoInvestigacion) {
		this.proyectoInvestigacion = proyectoInvestigacion;
	}

	public ProyectoInvestigacion getProyectoInvestigacionE() {
		return proyectoInvestigacionE;
	}

	public void setProyectoInvestigacionE(ProyectoInvestigacion proyectoInvestigacionE) {
		this.proyectoInvestigacionE = proyectoInvestigacionE;
	}

	public int getId_area_fk() {
		return id_area_fk;
	}

	public void setId_area_fk(int id_area_fk) {
		this.id_area_fk = id_area_fk;
	}

	public List<ProyectoInvestigacion> getProyectoInvestigaciones() {
		return proyectoInvestigaciones;
	}

	public void setProyectoInvestigaciones(List<ProyectoInvestigacion> proyectoInvestigaciones) {
		this.proyectoInvestigaciones = proyectoInvestigaciones;
	}

	public BeanLogin getBeanLogin() {
		return beanLogin;
	}

	public void setBeanLogin(BeanLogin beanLogin) {
		this.beanLogin = beanLogin;
	}

	public AreaInvestigacion getAreaInvestigacion() {
		return areaInvestigacion;
	}

	public void setAreaInvestigacion(AreaInvestigacion areaInvestigacion) {
		this.areaInvestigacion = areaInvestigacion;
	}

	public List<AreaInvestigacion> getAreaInvestigaciones() {
		return areaInvestigaciones;
	}

	public void setAreaInvestigaciones(List<AreaInvestigacion> areaInvestigaciones) {
		this.areaInvestigaciones = areaInvestigaciones;
	}



}
