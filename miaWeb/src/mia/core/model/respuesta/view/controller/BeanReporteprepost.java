package mia.core.model.respuesta.view.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.charts.bar.BarChartModel;

import mia.core.model.administrador.ManagerAdministrador;
import mia.core.model.administrador.view.controller.BeanAdministrradorCuestionario;


import mia.core.model.entities.Reporteprepost;
import mia.core.model.entities.Usuario;
import mia.core.model.login.view.controller.BeanLogin;

import mia.core.model.reporte.ManagerReportePrePost;

import mia.core.model.usuario.view.controller.BeanReportePrePostEstadistico;
import mia.modulos.view.util.JSFUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Named
@ViewScoped
public class BeanReporteprepost implements Serializable {

	private static final long serialVersionUID = 1L;
	private Usuario user= new Usuario();
	private Reporteprepost repor= new Reporteprepost();
	private Date fechaRealizacion;

	private int anio;
	private int mes;
	private long id_user_fk;
	private long id_repor_fk;
	private String respuesta;
	List<Integer> listaanios=new ArrayList<Integer>();
	List<Integer>listaMeses=new ArrayList<Integer>();
	private BarChartModel mixedModelEjecValidProy;
	@Inject
	private BeanLogin login;
	@Inject
	private BeanAdministrradorCuestionario cuestionario;
	@Inject
	private BeanReportePrePostEstadistico beanReporteprepostEsta;

	@EJB
	private ManagerReportePrePost managerReporteprepost;
	
	@EJB
	private ManagerAdministrador managerAdministrador; 
	
	private List<Reporteprepost> reporteprepostTest;
	
	@PostConstruct
	public void init() {
		try {
		reporteprepostTest= new ArrayList<>();
		System.out.println("Si entra "+reporteprepostTest.size());
		listaanios= managerReporteprepost.findResultadosTestbyUsuarioByAnio(login.getLogin().getId_usuario());
		
		//mixedModelEjecValidProy=beanReporteprepostEsta.createMixedModelEjecProyVal(reporteprepostTest);
		} catch (Exception e) {
		JSFUtil.crearMensajeError(e.getMessage());
		}
		}	
	
	  public void seleccionarMes() {
	        if(anio ==0 )
	           JSFUtil.crearMensajeError("Seleccione un año");
	        else
	         listaMeses= managerReporteprepost.findResultadosTestbyUsuarioByMes(anio,login.getLogin().getId_usuario());
	    }
	  
	  public void reportebyMesAndAnio() {
		  reporteprepostTest= managerReporteprepost.findResultadosTestbyUsuarioAndAnioAndMes(login.getLogin().getId_usuario(),anio,mes);
		  JSFUtil.crearMensajeInfo("Consulta realizada correctamente!");
			mixedModelEjecValidProy=beanReporteprepostEsta.createMixedModelEjecProyVal(reporteprepostTest);
		  System.out.println("T: "+reporteprepostTest.size());
		  
		  
	  }
	 
	  public void itemSelect(ItemSelectEvent event) {
		  reporteprepostTest.get(event.getItemIndex()).getIdReportetest();
		  JSFUtil.crearMensajeInfo(reporteprepostTest.get(event.getItemIndex())+""); 
	    }
	
	public Usuario getUser() {
		return user;
	}
	public void setUser(Usuario user) {
		this.user = user;
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
	
	
	

	public long getId_user_fk() {
		return id_user_fk;
	}



	public void setId_user_fk(long id_user_fk) {
		this.id_user_fk = id_user_fk;
	}



	public long getId_repor_fk() {
		return id_repor_fk;
	}


	public void setId_repor_fk(long id_repor_fk) {
		this.id_repor_fk = id_repor_fk;
	}
	public List<Integer> getListaanios() {
		return listaanios;
	}

	public void setListaanios(List<Integer> listaanios) {
		this.listaanios = listaanios;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public List<Integer> getListaMeses() {
		return listaMeses;
	}
	public void setListaMeses(List<Integer> listaMeses) {
		this.listaMeses = listaMeses;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public BarChartModel getMixedModelEjecValidProy() {
		return mixedModelEjecValidProy;
	}
	public void setMixedModelEjecValidProy(BarChartModel mixedModelEjecValidProy) {
		this.mixedModelEjecValidProy = mixedModelEjecValidProy;
	}

	public Date getFechaRealizacion() {
		return fechaRealizacion;
	}

	public void setFechaRealizacion(Date fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}

	public List<Reporteprepost> getReporteprepostTest() {
		return reporteprepostTest;
	}

	public void setReporteprepostTest(List<Reporteprepost> reporteprepostTest) {
		this.reporteprepostTest = reporteprepostTest;
	}








	
	
	

} 
 