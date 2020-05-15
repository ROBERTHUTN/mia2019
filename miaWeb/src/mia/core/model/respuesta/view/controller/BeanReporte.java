package mia.core.model.respuesta.view.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.charts.bar.BarChartModel;

import mia.core.model.administrador.ManagerAdministrador;
import mia.core.model.administrador.view.controller.BeanAdministrradorCuestionario;
import mia.core.model.cuestionario.dto.DimensionDTO;
import mia.core.model.cuestionario.dto.DimensionPreguntaDTO;
import mia.core.model.cuestionario.dto.PreguntaDimensionDTO;
import mia.core.model.entities.Dimension;
import mia.core.model.entities.Reporte;
import mia.core.model.entities.Usuario;
import mia.core.model.login.view.controller.BeanLogin;
import mia.core.model.reporte.ManagerReporte;
import mia.core.model.usuario.view.controller.BeanReporteEstadistico;
import mia.modulos.view.util.JSFUtil;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

	private int anio;
	private int mes;
	private long id_user_fk;
	private int id_dimen_fk;
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
	private BeanReporteEstadistico beanReporteEsta;
	@EJB
	private ManagerReporte managerReporte; 
	
	@EJB
	private ManagerAdministrador managerAdministrador; 
	
	private List<Reporte> reporteTest;
	
	@PostConstruct
	public void init() {
		try {
		reporteTest= managerReporte.findResultadosTestbyUsuario(login.getLogin().getId_usuario());
		listaanios= managerReporte.findResultadosTestbyUsuarioByAnio(login.getLogin().getId_usuario());
		mixedModelEjecValidProy=beanReporteEsta.createMixedModelEjecProyVal(reporteTest);
		} catch (Exception e) {
		JSFUtil.crearMensajeError(e.getMessage());
		}
		}	
	  public void seleccionarMes() {
	        if(anio ==0 )
	           JSFUtil.crearMensajeError("Seleccione un año");
	        else
	         listaMeses=managerReporte.findResultadosTestbyUsuarioByMes(anio,login.getLogin().getId_usuario());
	    }
	  
	  public void reportebyMesAndAnio() {
		  reporteTest= managerReporte.findResultadosTestbyUsuarioAndAnioAndMes(login.getLogin().getId_usuario(),anio,mes);
		  JSFUtil.crearMensajeInfo("Consulta realizada correctamente!");
			mixedModelEjecValidProy=beanReporteEsta.createMixedModelEjecProyVal(reporteTest);
		  System.out.println("T: "+reporteTest.size());
		  
		  
	  }
	/*
	
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
	
	
	*/
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
	public int getId_dimen_fk() {
		return id_dimen_fk;
	}
	public void setId_dimen_fk(int id_dimen_fk) {
		this.id_dimen_fk = id_dimen_fk;
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
	
	
	public List<Reporte> getReporteTest() {
		return reporteTest;
	}



	public void setReporteTest(List<Reporte> reporteTest) {
		this.reporteTest = reporteTest;
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








	
	
	

} 
 