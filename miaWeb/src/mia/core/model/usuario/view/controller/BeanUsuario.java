package mia.core.model.usuario.view.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import mia.core.model.administrador.ManagerAdministrador;
import mia.core.model.entities.Etnia;
import mia.core.model.entities.FichaPersonal;
import mia.core.model.entities.GradoEstudio;
import mia.core.model.entities.PaisEstado;
import mia.core.model.entities.Religion;
import mia.core.model.entities.Rol;
import mia.core.model.entities.Usuario;
import mia.core.model.login.view.controller.BeanLogin;
import mia.core.model.usuario.ManagerUsuario;
import mia.modulos.view.util.JSFUtil;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped
public class BeanUsuario implements Serializable {

	private static final long serialVersionUID = 1L;
//FICHA_
	private FichaPersonal fichaPersonal =new FichaPersonal();
	private Religion religion = new Religion();
	private Etnia etnia = new Etnia();
	private Rol rol = new Rol();
	private Usuario usuario= new Usuario();
	private PaisEstado paisEstado= new PaisEstado();
	private GradoEstudio gradoEstudio = new GradoEstudio(); 
	private FichaPersonal fichaPersonalE =new FichaPersonal();;
	private Religion religionE;
	private Etnia etniaE;
	private Rol rolE;
	private Usuario usuarioE;
	
  private int id_pais_fk;
  private int id_estado_fk;
//claves foraneas
	private Date fechaNacimiento;
	private Date fechaMinimaNacimiento;
	private int id_rol_fk;
	private int id_etnia_fk;
	private int id_religion_fk;
	private int id_user_fk;
	private int id_grado_fk;
	@EJB
	private ManagerAdministrador managerAdministrador;
//ROLES
	private List<Rol> roles;
	private List<Etnia> etnias;
	private List<Religion> religiones;
	private List<PaisEstado> estados;
	private List<Usuario> usuarioes;
	private List<PaisEstado> paises;
	private List<GradoEstudio> grados;
	private String fechaRango;
	
	@EJB
	private ManagerUsuario managerUsuario;
	@Inject
	private BeanLogin beanLogin;
	
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	private List<Usuario> usuarios;

	@PostConstruct
	public void init() {
		try {
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            Date c=new Date();
			Calendar t=Calendar.getInstance();
			int a=t.get(Calendar.YEAR);
			int b=a-100;
			fechaRango=""+b+":"+a;
			System.out.println(fechaRango);
            
			fechaNacimiento = managerAdministrador.fechaActual();
			
			fechaMinimaNacimiento = managerAdministrador.fechadeNacimiento();
			roles = managerAdministrador.findAllRoles();
			etnias = managerAdministrador.findAllEtnia();
			religiones = managerAdministrador.findAllReligion();
			paises= managerAdministrador.findOnlyPais();		
			grados= managerUsuario.findAllGrado();
			
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}
	public String actionNavegarFichaUsuario() {
		try {
		fichaPersonalE=managerUsuario.findFichaPersonalByID(beanLogin.getLogin().getId_usuario());
		id_etnia_fk=fichaPersonalE.getEtnia().getIdEtnia();
		id_religion_fk=fichaPersonalE.getReligion().getIdReligion();
		 id_grado_fk=fichaPersonalE.getGradoEstudio().getIdGrado();
		 id_pais_fk=fichaPersonalE.getPaisEstado2().getIdPaisEstado();
		  estados=managerAdministrador.findPaisEstado(id_pais_fk);
		 id_estado_fk=fichaPersonalE.getPaisEstado1().getIdPaisEstado();
		
		return"editarUsuario.xhtml?faces-redirect=true";
		} catch (Exception e) {
		JSFUtil.crearMensajeError(e.getMessage());
		}
		return"";
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
	  public void onCountryChange() {
		    if(paises !=null && !paises.equals(""))
		    {
		    	estados = managerAdministrador.findPaisEstado(id_pais_fk);
		    	id_estado_fk=0;
		    }
		    else
		    {
		    	JSFUtil.crearMensajeError("No se a selecionado un país.");
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
			roles=managerAdministrador.findAllRoles();
			JSFUtil.crearMensajeInfo("Rol eliminado correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}
	public void actionListenerEliminarReligion(int id_religion) {
		try {
			managerAdministrador.eliminarReligion(id_religion);
			religiones=managerAdministrador.findAllReligion();
			JSFUtil.crearMensajeInfo("ReligiÃ³n eliminado correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}
	
	public void actionListenerEliminarEtnia(int id_etnia) {
		try {
			managerAdministrador.eliminarEtnia(id_etnia);
			etnias=managerAdministrador.findAllEtnia();
			JSFUtil.crearMensajeInfo("Etnia eliminada correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}
	
	public void actionListenerBuscarUsuario(int id_usuario) {
		try {
			managerAdministrador.findUsuarioById(id_usuario);
			JSFUtil.crearMensajeInfo("Usuario correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}
	
	public String actionBuscarUsuario() {
		
		try {
			managerAdministrador.findUsuarioById(id_user_fk);
			JSFUtil.crearMensajeInfo("Usuario encontrado correctamente");
			
			return "usuarioinformacion.xhtml?faces-redirect=true";
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			return "";
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
			managerAdministrador.ingresarPais(paisEstado,0);
			JSFUtil.crearMensajeInfo("Pais creado correctamente");
		} catch (Exception e) {
			
		}
		
		
	}
	
	public void actionListeneEditarFichaPersonal() {
		try {
			managerUsuario.editarFichaPersonalUsuario(fichaPersonalE, id_religion_fk, id_etnia_fk, id_pais_fk, id_estado_fk, beanLogin.getLogin().getId_usuario(), id_grado_fk);
		JSFUtil.crearMensajeInfo("Datos del usuario actualizado correctamente.!");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public String actionIngresarFichaPersonal() {
		try {
			fichaPersonal.setFechaInscripcion(managerAdministrador.fechaActual());
			managerAdministrador.ingresarFichaPersonal(fichaPersonal, id_religion_fk, id_etnia_fk,id_pais_fk ,id_estado_fk,beanLogin.getLogin().getId_usuario(), id_grado_fk);
			JSFUtil.crearMensajeInfo("Ficha creada correctamente");
			fichaPersonal = new FichaPersonal();
			return "menu?faces-redirect=true";
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			return "";
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
	
	public void actionListenerCargarUsuario(Usuario usuarioC) {
		try {
			usuarioE = usuarioC;
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

	public void actionListenerEditarReligion() {
		try {
			managerAdministrador.editarReligion(religionE);
			JSFUtil.crearMensajeInfo("ReligiÃ³n editada correctamente");
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
	
	public void actionListenerEditarUsuario() {
		try {
			managerAdministrador.editarUsuario(usuarioE);
			JSFUtil.crearMensajeInfo("Usuario editado correctamente");
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

	public BeanLogin getBeanLogin() {
		return beanLogin;
	}

	public void setBeanLogin(BeanLogin beanLogin) {
		this.beanLogin = beanLogin;
	}

	public PaisEstado getPaisEstado() {
		return paisEstado;
	}

	public void setPaisEstado(PaisEstado paisEstado) {
		this.paisEstado = paisEstado;
	}

	public int getId_pais_fk() {
		return id_pais_fk;
	}

	public void setId_pais_fk(int id_pais_fk) {
		this.id_pais_fk = id_pais_fk;
	}

	public int getId_estado_fk() {
		return id_estado_fk;
	}

	public void setId_estado_fk(int id_estado_fk) {
		this.id_estado_fk = id_estado_fk;
	}

	public List<PaisEstado> getEstados() {
		return estados;
	}

	public void setEstados(List<PaisEstado> estados) {
		this.estados = estados;
	}

	public List<PaisEstado> getPaises() {
		return paises;
	}

	public void setPaises(List<PaisEstado> paises) {
		this.paises = paises;
	}

	public List<Usuario> getUsuarioes() {
		return usuarioes;
	}

	public void setUsuarioes(List<Usuario> usuarioes) {
		this.usuarioes = usuarioes;
	}

	public Usuario getUsuarioE() {
		return usuarioE;
	}

	public void setUsuarioE(Usuario usuarioE) {
		this.usuarioE = usuarioE;
	}

	public int getId_user_fk() {
		return id_user_fk;
	}

	public void setId_user_fk(int id_user_fk) {
		this.id_user_fk = id_user_fk;
	}

	public GradoEstudio getGradoEstudio() {
		return gradoEstudio;
	}

	public void setGradoEstudio(GradoEstudio gradoEstudio) {
		this.gradoEstudio = gradoEstudio;
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
	public String getFechaRango() {
		return fechaRango;
	}
	public void setFechaRango(String fechaRango) {
		this.fechaRango = fechaRango;
	}	
	

}
