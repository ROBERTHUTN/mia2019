package mia.core.model.administrador.view.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import mia.core.model.administrador.ManagerAdministrador;
import mia.core.model.entities.AreaInvestigacion;
import mia.core.model.entities.Etnia;
import mia.core.model.entities.FichaPersonal;
import mia.core.model.entities.GradoEstudio;
import mia.core.model.entities.Organizacion;
import mia.core.model.entities.PaisEstado;
import mia.core.model.entities.Religion;
import mia.core.model.entities.Rol;
import mia.core.model.entities.Usuario;
import mia.core.model.entities.UsuarioProyecto;
import mia.core.model.login.view.controller.BeanLogin;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import mia.core.model.usuario.ManagerUsuario;
import mia.modulos.view.util.JSFUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
@Named
@ViewScoped
public class BeanAdministrador implements Serializable {

	private static final long serialVersionUID = 1L;
//FIC
	private FichaPersonal fichaPersonal = new FichaPersonal();
	private Religion religion = new Religion();
	private Etnia etnia = new Etnia();
	private Rol rol = new Rol();
	private Usuario usuario = new Usuario();
	private PaisEstado paisEstado = new PaisEstado();
	private GradoEstudio gradoEstudio = new GradoEstudio();
	private Organizacion organizacion = new Organizacion();
	private UsuarioProyecto usuarioproyecto = new UsuarioProyecto();

	private FichaPersonal fichaPersonalE;
	private Religion religionE;
	private Etnia etniaE;
	private Rol rolE;
	private GradoEstudio gradoE;
	private Organizacion organizacioE;
	private UsuarioProyecto usuarioproyectoE;
	private Usuario usuarioE;
	private AreaInvestigacion areaE;
	
//claves foraneas
	private Date fechaNacimiento;
	private Date fechaMinimaNacimiento;
	private Date fecha_de;
	private Date Fecha_hasta;
	private int id_user_fk;
	private int id_rol_fk;
	private int id_etnia_fk;
	private int id_religion_fk;
	private int id_pais_fk;
	private int id_estado_fk;
	private int id_grado_fk;
	
	
	/**
	 * revisar si se pueden llamar desde otra clase del investigador
	 * */
   private long id_ficha_fk;
   private long id_proyecto;
   private int id_organizacion;
   private int reporte;
   
   
	@EJB
	private ManagerAdministrador managerAdministrador;
//ROLES
	private List<Rol> roles;
	private List<Etnia> etnias;
	private List<Religion> religiones;
	private List<PaisEstado> paises;
	private List<PaisEstado> estados;
	private List<Usuario> usuarioes;
	private List<GradoEstudio> grados;
	private List<Organizacion> organizaciones;
	private List<UsuarioProyecto> usuarioproyectos;
	private List<AreaInvestigacion> areas;
	
	@EJB
	private ManagerUsuario managerUsuario;

	@Inject
	private BeanLogin beanLogin;

	private List<Usuario> usuarios;

	@PostConstruct
	public void init() {
		try {
		
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			fechaNacimiento = managerAdministrador.fechaActual();
			fechaMinimaNacimiento = managerAdministrador.fechadeNacimiento();
			roles = managerAdministrador.findAllRoles();
			etnias = managerAdministrador.findAllEtnia();
			religiones = managerAdministrador.findAllReligion();
			paises = managerAdministrador.findOnlyPais();
			usuarios = managerAdministrador.findAllUsuario();
			grados = managerUsuario.findAllGrado();
			organizaciones = managerAdministrador.findAllOrganizaciones();
			usuarioproyectos = managerAdministrador.findAllUsuarioProyectoes();
reporte=1;
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}
	
	
	public void actionListenerIngresarReligion() {
		try {
			managerAdministrador.ingresarReligion(religion);
			religiones = managerAdministrador.findAllReligion();
			JSFUtil.crearMensajeInfo("Religión creada correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actionListenerIngresarRol() {
		try {
			managerAdministrador.ingresarRol(rol);
			roles = managerAdministrador.findAllRoles();
			JSFUtil.crearMensajeInfo("Rol creada correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerEliminarRol(int id_rol) {
		try {
			managerAdministrador.eliminarRol(id_rol);
			roles = managerAdministrador.findAllRoles();
			JSFUtil.crearMensajeInfo("Rol eliminado correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public void actionListenerEliminarUsuario(long id_usuario) {
		try {
			managerAdministrador.eliminarUsuario(id_usuario);
			usuarios = managerAdministrador.findAllUsuario();
			JSFUtil.crearMensajeInfo("Usuario eliminado correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public void actionListenerEliminarReligion(int id_religion) {
		try {
			managerAdministrador.eliminarReligion(id_religion);
			religiones = managerAdministrador.findAllReligion();
			JSFUtil.crearMensajeInfo("Religión eliminado correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public void actionListenerEliminarEtnia(int id_etnia) {
		try {
			managerAdministrador.eliminarEtnia(id_etnia);
			etnias = managerAdministrador.findAllEtnia();
			JSFUtil.crearMensajeInfo("Etnia eliminada correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public void actionListenerIngresarEtnia() {
		try {
			managerAdministrador.ingresarEtnia(etnia);
			etnias = managerAdministrador.findAllEtnia();
			JSFUtil.crearMensajeInfo("Etnia creada correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());

		}
	}

	public String actionIngresarUsuario() {

		try {
			usuario.setActivo(true);
			managerAdministrador.ingresarUsuario(usuario, id_rol_fk);
			JSFUtil.crearMensajeInfo("Usuario creado correctamente");
			usuario = new Usuario();
			return "index.xhtml?faces-redirect=true";
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			return "";
		}

	}

	public void actionIngresarPaisEstado() {
		try {
			managerAdministrador.ingresarPais(paisEstado, 0);
			JSFUtil.crearMensajeInfo("Pais creado correctamente");
		} catch (Exception e) {

		}

	}

	public String actionIngresarFichaPersonal() {
		try {
			fichaPersonal.setFechaInscripcion(managerAdministrador.fechaActual());
			managerAdministrador.ingresarFichaPersonal(fichaPersonal, id_religion_fk, id_etnia_fk, id_pais_fk,
					id_estado_fk, beanLogin.getLogin().getId_usuario(), id_grado_fk);
			JSFUtil.crearMensajeInfo("Ficha personal creada correctamente");
			fichaPersonal = new FichaPersonal();
			return "menu?faces-redirect=true";
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			return "";
		}finally {
			JSFUtil.crearMensajeFastFinal();
		}

	}

//
	public void actionListenerCargarRol(Rol rolC) {
		try {
			rolE = rolC;
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}

	public void actionListenerCargarUsuario(Usuario usuarioC) {
		try {
			usuarioE = usuarioC;
			id_rol_fk = usuarioC.getRol().getIdRol();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}

	public void actionListenerCargarEtnia(Etnia etniaC) {
		try {
			etniaE = etniaC;
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}

	public void actionListenerCargarReligion(Religion religionC) {
		try {
			religionE = religionC;
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}

//
	public void actionListenerEditarRol() {
		try {
			managerAdministrador.editarRol(rolE);
			JSFUtil.crearMensajeInfo("Rol editado correctamente");
		} catch (Exception e) {
			roles = managerAdministrador.findAllRoles();
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}

	public void actionListenerEditarUsuario() {
		try {
			managerAdministrador.editarUsuario(usuarioE, id_rol_fk);
			usuarios = managerAdministrador.findAllUsuario();
			JSFUtil.crearMensajeInfo("Usuario editado correctamente");

		} catch (Exception e) {
			roles = managerAdministrador.findAllRoles();
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}

	public void actionListenerEditarReligion() {
		try {
			managerAdministrador.editarReligion(religionE);
			JSFUtil.crearMensajeInfo("Religión editada correctamente");
		} catch (Exception e) {
			religiones = managerAdministrador.findAllReligion();
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}

	public void actionListenerEditarEtnia() {
		try {
			managerAdministrador.editarEtnia(etniaE);
			JSFUtil.crearMensajeInfo("Etnia editada correctamente");
		} catch (Exception e) {
			etnias = managerAdministrador.findAllEtnia();
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}

	public void onCountryChange() {
		if (paises != null && !paises.equals("")) {
			estados = managerAdministrador.findPaisEstado(id_pais_fk);
		} else {
			JSFUtil.crearMensajeError("No se a selecionado un país.");
		}

	}

	public void actionListenerIngresarGradoEstudio() {
		try {

			managerUsuario.ingresarGradoEstudio(gradoEstudio);
			grados = managerUsuario.findAllGrado();
			JSFUtil.crearMensajeInfo("GradoEstudio creada correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerEliminarGradoEstudio(int id_grado) {
		try {
			managerUsuario.eliminarGrado(id_grado);
			grados = managerUsuario.findAllGrado();
			JSFUtil.crearMensajeInfo("Grado de Estudio eliminado correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public void actionListenerEditarGradoEstudio() {
		try {
			managerUsuario.editarGrado(gradoE);
			JSFUtil.crearMensajeInfo("Grado de Estudio editado correctamente");
		} catch (Exception e) {
			grados = managerUsuario.findAllGrado();
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}

	public void actionListenerCargarGradoEstudio(GradoEstudio gradoC) {
		try {
			gradoE = gradoC;
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}

	public void actionListenerIngresarOrganizacion() {
		try {
			System.out.println(organizacion.getNombreOrganizacion());
			managerAdministrador.ingresarOrganizacion(organizacion);
			organizaciones = managerAdministrador.findAllOrganizaciones();
			JSFUtil.crearMensajeInfo("Organizacion creada correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerEliminarOrganizacion(int id_organizacion) {
		try {
			managerAdministrador.eliminarOrganizacion(id_organizacion);
			organizaciones = managerAdministrador.findAllOrganizaciones();
			JSFUtil.crearMensajeInfo("Organizacion eliminado correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public void actionListenerEditarOrganizacion() {
		try {
			managerAdministrador.editarOrganizacion(organizacioE);
			JSFUtil.crearMensajeInfo("Organizacion editado correctamente");
		} catch (Exception e) {
			organizaciones = managerAdministrador.findAllOrganizaciones();
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}

	public void actionListenerCargarOrganizacion(Organizacion organizacionC) {
		try {
			organizacioE = organizacionC;
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}

	/**
	 * Métodos de usuario ,proyecto a cargo que se va a utilizar en la vista para
	 * realizar las respectivas transacciones.
	 */
	public void actionListenerIngresarUsuarioProyecto() {
		try {
			managerAdministrador.ingresarUsuarioProyecto( usuarioproyecto,id_ficha_fk, id_proyecto, id_organizacion);
			usuarioproyectos = managerAdministrador.findAllUsuarioProyectoes();
			JSFUtil.crearMensajeInfo("Usuario y Proyecto creada correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerEliminarUsuarioProyecto(int id_usuarioproyecto) {
		try {
			managerAdministrador.eliminarUsuarioProyecto(id_usuarioproyecto);
			usuarioproyectos = managerAdministrador.findAllUsuarioProyectoes();
			JSFUtil.crearMensajeInfo("Usuario del Proyecto eliminado correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public void actionListenerEditarUsuarioProyecto() {
		try {
			managerAdministrador.editarUsuarioProyecto(usuarioproyecto, id_ficha_fk, id_proyecto, id_organizacion);
			JSFUtil.crearMensajeInfo("UsuarioProyecto editado correctamente");
		} catch (Exception e) {
			usuarioproyectos = managerAdministrador.findAllUsuarioProyectoes();
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}
	public void actionListenerActulizarListaU() {
		if (reporte==1) {
			usuarios = managerAdministrador.findAllUsuario();
			JSFUtil.crearMensajeInfo("Lista actualizada.");
		}else {
				
				if (reporte==2) {
				
					usuarios=managerAdministrador.findAllUsuarioByInvestigador();
					JSFUtil.crearMensajeInfo("Lista actualizada.");
				}else {
					System.out.println("HOLA3");
				}
			}
	}
	
	public void generarReporte() {
		String pathR="", nombre=""; 
		if (reporte==1) {
		pathR="administrador/reporte/miaDS.jasper";
		nombre="miaDS.pdf";
		actionReporteAllUsuarios(pathR, nombre);
		
		}else {
			
			if (reporte==2) {
				pathR="administrador/reporte/investidores.jasper";
				nombre="investidores.pdf";
				 reporteInvestigadores( pathR, nombre);
			}else {
				System.out.println("HOLA3");
			}
		}
	    }
	public String actionReporteAllUsuarios(String pathR, String nombre){
		Map<String,Object> parametros=new HashMap<String,Object>();
		/*parametros.put("p_titulo_principal",p_titulo_principal);
		parametros.put("p_titulo",p_titulo);*/
		FacesContext context=FacesContext.getCurrentInstance();
		ServletContext servletContext=(ServletContext)context.getExternalContext().getContext();
		String ruta=servletContext.getRealPath(pathR);
		System.out.println(ruta);
		HttpServletResponse response=(HttpServletResponse)context.getExternalContext().getResponse();
		response.addHeader("Content-disposition", "attachment;filename="+nombre);
		response.setContentType("application/pdf");
		try {
		Class.forName("org.postgresql.Driver");
		Connection connection = null;
		connection = DriverManager.getConnection(
		 "jdbc:postgresql://localhost:5432/mia","postgres", "root");
		JasperPrint impresion=JasperFillManager.fillReport(ruta, parametros,connection);
		JasperExportManager.exportReportToPdfStream(impresion, response.getOutputStream());
		context.getApplication().getStateManager().saveView(context);
		System.out.println("reporte generado.");
		context.responseComplete();
		} catch (Exception e) {
		JSFUtil.crearMensajeError(e.getMessage());
		e.printStackTrace
		();
		}
		return "";
		}
	
	
	public String reporteInvestigadores( String pathR, String nombre){
		Map<String,Object> parametros=new HashMap<String,Object>();
		/*parametros.put("p_titulo_principal",p_titulo_principal);
		parametros.put("p_titulo",p_titulo);*/
		FacesContext context=FacesContext.getCurrentInstance();
		ServletContext servletContext=(ServletContext)context.getExternalContext().getContext();
		String ruta=servletContext.getRealPath(pathR);
		System.out.println(ruta);
		HttpServletResponse response=(HttpServletResponse)context.getExternalContext().getResponse();
		response.addHeader("Content-disposition", "attachment;filename="+nombre);
		response.setContentType("application/pdf");
		try {
		Class.forName("org.postgresql.Driver");
		Connection connection = null;
		connection = DriverManager.getConnection(
		 "jdbc:postgresql://localhost:5432/mia","postgres", "root");
		JasperPrint impresion=JasperFillManager.fillReport(ruta, parametros,connection);
		JasperExportManager.exportReportToPdfStream(impresion, response.getOutputStream());
		context.getApplication().getStateManager().saveView(context);
		System.out.println("reporte generado.");
		context.responseComplete();
		} catch (Exception e) {
		JSFUtil.crearMensajeError(e.getMessage());
		e.printStackTrace();
		}
		return "";
		}
	
	
	public void actionListenerCargarUsuarioProyecto(UsuarioProyecto usuarioproyectoC) {
		try {
			usuarioproyectoE = usuarioproyectoC;
			id_ficha_fk= usuarioproyectoC.getFichaPersonal().getIdFicha();
			id_organizacion = usuarioproyectoC.getOrganizacion().getIdOrganizacion();
			id_proyecto= usuarioproyectoC.getProyectoInvestigacion().getIdProyectoInvestigacion();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}


	public List<Etnia> getEtnias() {
		return etnias;
	}

	public void setEtnias(List<Etnia> etnias) {
		this.etnias = etnias;
	}

	public List<Religion> getReligiones() {
		return religiones;
	}

	public void setReligiones(List<Religion> religiones) {
		this.religiones = religiones;
	}

	public int getId_rol_fk() {
		return id_rol_fk;
	}

	public void setId_rol_fk(int id_rol_fk) {
		this.id_rol_fk = id_rol_fk;
	}

	public int getId_etnia_fk() {
		return id_etnia_fk;
	}

	public void setId_etnia_fk(int id_etnia_fk) {
		this.id_etnia_fk = id_etnia_fk;
	}

	public int getId_religion_fk() {
			return id_religion_fk;
	}

	public void setId_religion_fk(int id_religion_fk) {
		this.id_religion_fk = id_religion_fk;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public FichaPersonal getFichaPersonal() {
		return fichaPersonal;
	}

	public void setFichaPersonal(FichaPersonal fichaPersonal) {
		this.fichaPersonal = fichaPersonal;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Date getFechaMinimaNacimiento() {
		return fechaMinimaNacimiento;
	}

	public void setFechaMinimaNacimiento(Date fechaMinimaNacimiento) {
		this.fechaMinimaNacimiento = fechaMinimaNacimiento;
	}

	public Religion getReligion() {
		return religion;
	}

	public void setReligion(Religion religion) {
		this.religion = religion;
	}

	public Etnia getEtnia() {
		return etnia;
	}

	public void setEtnia(Etnia etnia) {
		this.etnia = etnia;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public FichaPersonal getFichaPersonalE() {
		return fichaPersonalE;
	}

	public void setFichaPersonalE(FichaPersonal fichaPersonalE) {
		this.fichaPersonalE = fichaPersonalE;
	}

	public Religion getReligionE() {
		return religionE;
	}

	public void setReligionE(Religion religionE) {
		this.religionE = religionE;
	}

	public Etnia getEtniaE() {
		return etniaE;
	}

	public void setEtniaE(Etnia etniaE) {
		this.etniaE = etniaE;
	}

	public Rol getRolE() {
		return rolE;
	}

	public void setRolE(Rol rolE) {
		this.rolE = rolE;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public int getId_pais_fk() {
		return id_pais_fk;
	}

	public void setId_pais_fk(int id_pais_fk) {
		this.id_pais_fk = id_pais_fk;
	}

	public PaisEstado getPaisEstado() {
		return paisEstado;
	}

	public void setPaisEstado(PaisEstado paisEstado) {
		this.paisEstado = paisEstado;
	}

	public List<PaisEstado> getPaises() {
		return paises;
	}

	public void setPaises(List<PaisEstado> paises) {
		this.paises = paises;
	}

	public List<PaisEstado> getEstados() {
		return estados;
	}

	public void setEstados(List<PaisEstado> estados) {
		this.estados = estados;
	}

	public int getId_estado_fk() {
		return id_estado_fk;
	}

	public void setId_estado_fk(int id_estado_fk) {
		this.id_estado_fk = id_estado_fk;
	}

	public int getId_user_fk() {
		return id_user_fk;
	}

	public void setId_user_fk(int id_user_fk) {
		this.id_user_fk = id_user_fk;
	}

	public List<Usuario> getUsuarioes() {
		return usuarioes;
	}

	public void setUsuarioes(List<Usuario> usuarioes) {
		this.usuarioes = usuarioes;
	}

	public BeanLogin getBeanLogin() {
		return beanLogin;
	}

	public void setBeanLogin(BeanLogin beanLogin) {
		this.beanLogin = beanLogin;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getUsuarioE() {
		return usuarioE;
	}

	public void setUsuarioE(Usuario usuarioE) {
		this.usuarioE = usuarioE;
	}

	public GradoEstudio getGradoEstudio() {
		return gradoEstudio;
	}

	public void setGradoEstudio(GradoEstudio gradoEstudio) {
		this.gradoEstudio = gradoEstudio;
	}

	public GradoEstudio getGradoE() {
		return gradoE;
	}

	public void setGradoE(GradoEstudio gradoE) {
		this.gradoE = gradoE;
	}

	public int getId_grado_fk() {
		return id_grado_fk;
	}

	public void setId_grado_fk(int id_grado_fk) {
		this.id_grado_fk = id_grado_fk;
	}

	public List<GradoEstudio> getGrados() {
		return grados;
	}

	public void setGrados(List<GradoEstudio> grados) {
		this.grados = grados;
	}

	public Organizacion getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

	public Organizacion getOrganizacioE() {
		return organizacioE;
	}

	public void setOrganizacioE(Organizacion organizacioE) {
		this.organizacioE = organizacioE;
	}

	public List<Organizacion> getOrganizaciones() {
		return organizaciones;
	}

	public void setOrganizaciones(List<Organizacion> organizaciones) {
		this.organizaciones = organizaciones;
	}

	public UsuarioProyecto getUsuarioproyecto() {
		return usuarioproyecto;
	}

	public void setUsuarioproyecto(UsuarioProyecto usuarioproyecto) {
		this.usuarioproyecto = usuarioproyecto;
	}

	public UsuarioProyecto getUsuarioproyectoE() {
		return usuarioproyectoE;
	}

	public void setUsuarioproyectoE(UsuarioProyecto usuarioproyectoE) {
		this.usuarioproyectoE = usuarioproyectoE;
	}

	public long getId_ficha_fk() {
		return id_ficha_fk;
	}

	public void setId_ficha_fk(long id_ficha_fk) {
		this.id_ficha_fk = id_ficha_fk;
	}

	public long getId_proyecto() {
		return id_proyecto;
	}

	public void setId_proyecto(long id_proyecto) {
		this.id_proyecto = id_proyecto;
	}

	public int getId_organizacion() {
		return id_organizacion;
	}

	public void setId_organizacion(int id_organizacion) {
		this.id_organizacion = id_organizacion;
	}

	public List<UsuarioProyecto> getUsuarioproyectos() {
		return usuarioproyectos;
	}

	public void setUsuarioproyectos(List<UsuarioProyecto> usuarioproyectos) {
		this.usuarioproyectos = usuarioproyectos;
	}

	public Date getFecha_de() {
		return fecha_de;
	}

	public void setFecha_de(Date fecha_de) {
		this.fecha_de = fecha_de;
	}

	public Date getFecha_hasta() {
		return Fecha_hasta;
	}

	public void setFecha_hasta(Date fecha_hasta) {
		Fecha_hasta = fecha_hasta;
	}
	public List<AreaInvestigacion> getAreas() {
		return areas;
	}

	public void setAreas(List<AreaInvestigacion> areas) {
		this.areas = areas;
	}
	public int getReporte() {
		return reporte;
	}
	public void setReporte(int reporte) {
		this.reporte = reporte;
	}


	
	
}
