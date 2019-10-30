package mia.core.model.investigador.view.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import mia.core.model.administrador.ManagerAdministrador;
import mia.core.model.entities.AreaInvestigacion;
import mia.core.model.entities.Etnia;
import mia.core.model.entities.FichaPersonal;
import mia.core.model.entities.PaisEstado;
import mia.core.model.entities.Religion;
import mia.core.model.entities.Rol;
import mia.core.model.entities.Usuario;
import mia.core.model.investigador.ManagerInvestigador;
import mia.core.model.login.view.controller.BeanLogin;
import mia.modulos.view.util.JSFUtil;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class Bean implements Serializable {

	private static final long serialVersionUID = 1L;
//FICHA_
	private FichaPersonal fichaPersonal = new FichaPersonal();
	
	private AreaInvestigacion areaInvestigacion = new AreaInvestigacion(); 

	private FichaPersonal fichaPersonalE;
	private AreaInvestigacion areaInvestigacionE;
//claves foraneas
	private int id_rol_fk;
	
	@EJB
	private ManagerInvestigador managerInvestigador;
//ROLES
	private List<AreaInvestigacion> investigacionareas;
	
	@Inject
	private BeanLogin beanLogin;
	
	@PostConstruct
	public void init() {
		try {
			investigacionareas= managerInvestigador.findAllAreaInvestigaciones();
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
	
	public void actionListenerCargar(AreaInvestigacion areaC) {
		try {
			areaInvestigacionE = areaC;
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

	

}
