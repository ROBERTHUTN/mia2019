package mia.core.model.usuario.view.controller;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import mia.core.model.administrador.ManagerAdministrador;
import mia.core.model.administrador.view.controller.BeanAdministrradorCuestionario;
import mia.core.model.cuestionario.dto.DimensionPreguntaDTO;
import mia.core.model.cuestionario.dto.PreguntaDimensionDTO;
import mia.core.model.entities.Dimension;
import mia.core.model.entities.Reporte;
import mia.core.model.entities.Usuario;
import mia.core.model.login.view.controller.BeanLogin;
import mia.core.model.reporte.ManagerReporte;
import mia.modulos.view.util.JSFUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Named
@ViewScoped
public class BeanUserCuestionario implements Serializable {

	private static final long serialVersionUID = 1L;
	private Date fechaRealizacion;
    
	
	private String respuesta;
	@Inject
	private BeanLogin login;
	@Inject
	private BeanAdministrradorCuestionario cuestionario;
	
	@EJB
	private ManagerReporte managerReporte; 
	
	@EJB
	private ManagerAdministrador managerAdministrador; 
	
/*
	public void actionIngresarReporte() {
		try {
			List<DimensionPreguntaDTO>lista=cuestionario.get
			Dimension dimension=cuestionario.getDimensionR();
			respuesta=managerReporte.calcularRespuestaDimension(lista, dimension);
			fechaRealizacion= managerAdministrador.fechaActual();
			managerReporte.ingresarReporte(respuesta, dimension.getIdDimension(),fechaRealizacion,login.getLogin().getId_usuario());
			JSFUtil.crearMensajeInfo("Reporte creada correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
*/	
} 
 