package mia.core.model.respuesta.view.controller;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import mia.core.model.administrador.ManagerAdministrador;
import mia.core.model.administrador.view.controller.BeanAdministrradorCuestionario;
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
public class BeanReporte implements Serializable {

	private static final long serialVersionUID = 1L;
	private Usuario user= new Usuario();
	private Dimension dimen= new Dimension();
	private Reporte repor= new Reporte();
	private Date fechaRealizacion;

	
	private int id_user_fk;
	private int id_dimen_fk;
	private int id_repor_fk;
	private String respuesta;
	@Inject
	private BeanLogin login;
	@Inject
	private BeanAdministrradorCuestionario cuestionario;
	
	@EJB
	private ManagerReporte managerReporte; 
	
	@EJB
	private ManagerAdministrador managerAdministrador; 
	

	public void actionIngresarReporte() {
		try {
			List<PreguntaDimensionDTO>lista=cuestionario.getDimensionpreguntaIDDto();
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
	
	
	

	
	
	public Usuario getUser() {
		return user;
	}
	public void setUser(Usuario user) {
		this.user = user;
	}
	public Dimension getDimen() {
		return dimen;
	}
	public void setDimen(Dimension dimen) {
		this.dimen = dimen;
	}
	public Reporte getRepor() {
		return repor;
	}
	public void setRepor(Reporte repor) {
		this.repor = repor;
	}
	public int getId_user_fk() {
		return id_user_fk;
	}
	public void setId_user_fk(int id_user_fk) {
		this.id_user_fk = id_user_fk;
	}
	public int getId_dimen_fk() {
		return id_dimen_fk;
	}
	public void setId_dimen_fk(int id_dimen_fk) {
		this.id_dimen_fk = id_dimen_fk;
	}
	public int getId_repor_fk() {
		return id_repor_fk;
	}
	public void setId_repor_fk(int id_repor_fk) {
		this.id_repor_fk = id_repor_fk;
	}



	public String getRespuesta() {
		return respuesta;
	}



	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}



	public BeanLogin getLogin() {
		return login;
	}



	public void setLogin(BeanLogin login) {
		this.login = login;
	}



	public BeanAdministrradorCuestionario getCuestionario() {
		return cuestionario;
	}



	public void setCuestionario(BeanAdministrradorCuestionario cuestionario) {
		this.cuestionario = cuestionario;
	}
	
	
	
	

} 
 