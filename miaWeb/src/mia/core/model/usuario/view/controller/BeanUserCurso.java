package mia.core.model.usuario.view.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import mia.core.model.administrador.ManagerCurso;
import mia.core.model.administrador.view.controller.BeanCurso;
import mia.core.model.cuestionario.ManagerCuestionario;
import mia.core.model.entities.CursoModulo;
import mia.core.model.entities.Reporteprepost;
import mia.core.model.entities.UsuarioCurso;
import mia.core.model.login.view.controller.BeanLogin;
import mia.core.model.usuario.ManagerUserCurso;
import mia.core.model.usuario.dto.UsuarioCursoDTO;
import mia.modulos.view.util.JSFUtil;
@Named
@ViewScoped
public class BeanUserCurso implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private BeanLogin login;
	@Inject
	private BeanCurso beanCurso;
	@EJB
	private ManagerCurso managerCurso;
	@EJB
	private ManagerUserCurso managerUserCurso; 
	@EJB
	private ManagerCuestionario managerCuestionario; 
	private List<UsuarioCursoDTO>listaUsuariosCursosDTO;
	//FICHA_
	  	private UsuarioCurso usuarioCurso = new UsuarioCurso();
	  	private UsuarioCurso usuarioCursoE;
	//claves foraneas
	  	private long id_usuario_curso_fk;
	  	private long id_usercurso;
	  	private int id_curso_fk;
	  	private long id_user_fk;
		private String modulos_res;
		private String avance_curso;
		private String mensajeDias;
		private UsuarioCurso usuarioCursoPa;
private UsuarioCurso usuarioFinalizado;
private List<Reporteprepost> listaReporteTest;
private List<UsuarioCurso>listaUsuariosCursos;
private int dias;
	  	private List<UsuarioCurso> usuarioCursos;
	 
		
		@PostConstruct
		public void init() {  
			try {
				listaUsuariosCursos=managerUserCurso.findAllUsuarioCursoPadre();
				usuarioCursos= managerUserCurso.findAllUsuarioCursoesbyUser(login.getLogin().getId_usuario());
				listaUsuariosCursosDTO=managerUserCurso.cargarListaUsuarioCursoDTOs(usuarioCursos);
				listaReporteTest=managerCuestionario.ultimoReporte(login.getLogin().getId_usuario());
			if (!listaReporteTest.isEmpty()) {
				Reporteprepost re=listaReporteTest.get(0);
				dias=managerCuestionario.calcularDiasFaltantes(re.getFechaInscripcion());
				mensajeDias="Faltan "+dias+" d�as para que se habilite el cuestionario."
						+ "Fecha �ltima que se realiz�: "+re.getFechaInscripcion();
			}else {
				dias=0;
			}
			
				System.out.println(dias);
			} catch (Exception e) {
				JSFUtil.crearMensajeError(e.getMessage());
			} 
		}
			
		public void actionListenerInscripcionCurso() {
			try {
				UsuarioCurso user=	managerUserCurso.inscripcionUsuarioCurso(id_usuario_curso_fk,login.getLogin().getId_usuario());
				usuarioCursos= managerUserCurso.findAllUsuarioCursoesbyUser(login.getLogin().getId_usuario());
				listaUsuariosCursosDTO=managerUserCurso.cargarListaUsuarioCursoDTOs(usuarioCursos);
				JSFUtil.crearMensajeInfo("Se ha inscrito en el curso "+user.getCurso().getNombre()+" "+" del investigador"+" "+user.getUsuario().getApellidos()+" "+user.getUsuario().getNombres());
			} catch (Exception e) {
				JSFUtil.crearMensajeError(e.getMessage());
			}
		}
		
		public void actionListenerIngresarUsuarioCurso() {
			try {
				managerUserCurso.ingresarUsuarioCurso(login.getLogin().getId_usuario(), id_curso_fk);
				usuarioCursos= managerUserCurso.findAllUsuarioCursoesbyUser(login.getLogin().getId_usuario());
				JSFUtil.crearMensajeInfo("creada correctamente");
			} catch (Exception e) {
				JSFUtil.crearMensajeError(e.getMessage());
				e.printStackTrace();
			}

		}
		
		public void actionListenerEliminarCurso(int id_curso_fk) {
			try {
				managerUserCurso.eliminarUsuarioCurso(id_curso_fk);
				usuarioCursos = managerUserCurso.findAllUsuarioCursoes();
				JSFUtil.crearMensajeInfo("Curso eliminado correctamente");
			} catch (Exception e) {
				JSFUtil.crearMensajeError(e.getMessage());
			}
		}//
		
		
		
		public void actionListenerCargaruserCurso(UsuarioCurso usercursoC) {
			try {
				usuarioCursoE = usercursoC;
			} catch (Exception e) {
				JSFUtil.crearMensajeError(e.getMessage());
			}
		}
		
		
		public void actionListenerEditarusurioCurso() {
			try {
				managerUserCurso.editarCurso(usuarioCursoE, id_curso_fk, id_user_fk);
				JSFUtil.crearMensajeInfo("Usuario y curso editado correctamente");
			} catch (Exception e) {
				usuarioCursos = managerUserCurso.findAllUsuarioCursoes();
				JSFUtil.crearMensajeError(e.getMessage());
			}

		}
 /**
		public void actionListenerEditarAvanceUsurioCurso() {
			try {
				managerUserCurso.editarAvanceCurso(usuarioCursoE);
				JSFUtil.crearMensajeInfo("Usuario finalizo el módulo correctamente");
			} catch (Exception e) {
				usuarioCursos = managerUserCurso.findAllUsuarioCursoes();
				JSFUtil.crearMensajeError(e.getMessage());
			}

		}*/
		
  
		/**
		 * 
		 * @return
		 * 
		 * revisar
		 */
		public String redirectCursoModulo(UsuarioCurso usuarioCurso) {
			  try {
			managerUserCurso.ingresarUsuarioCurso(login.getLogin().getId_usuario(), id_curso_fk);
				List<CursoModulo> lista=managerCurso.findAllModulosByCuros(usuarioCurso.getCurso().getIdCurso());
				usuarioFinalizado=usuarioCurso;
				beanCurso.setListaModCur(lista);
			} catch (Exception e) {
				JSFUtil.crearMensajeError(e.getMessage());
				e.printStackTrace();
				return "";
			}
			  return "moduloCurso.xhtml?faces-redirect=true";
			
			 }

		

		public BeanLogin getLogin() {
			return login;
		}



		public void setLogin(BeanLogin login) {
			this.login = login;
		}



		public UsuarioCurso getUsuarioCurso() {
			return usuarioCurso;
		}



		public void setUsuarioCurso(UsuarioCurso usuarioCurso) {
			this.usuarioCurso = usuarioCurso;
		}



		public UsuarioCurso getUsuarioCursoE() {
			return usuarioCursoE;
		}



		public void setUsuarioCursoE(UsuarioCurso usuarioCursoE) {
			this.usuarioCursoE = usuarioCursoE;
		}



		public int getId_curso_fk() {
			return id_curso_fk;
		}



		public void setId_curso_fk(int id_curso_fk) {
			this.id_curso_fk = id_curso_fk;
		}



		public long getId_user_fk() {
			return id_user_fk;
		}



		public void setId_user_fk(long id_user_fk) {
			this.id_user_fk = id_user_fk;
		}



		public List<UsuarioCurso> getUsuarioCursos() {
			return usuarioCursos;
		}



		public void setUsuarioCursos(List<UsuarioCurso> usuarioCursos) {
			this.usuarioCursos = usuarioCursos;
		}



		public static long getSerialversionuid() {
			return serialVersionUID;
		}



		public String getModulos_res() {
			return modulos_res;
		}



		public void setModulos_res(String modulos_res) {
			this.modulos_res = modulos_res;
		}



		public String getAvance_curso() {
			return avance_curso;
		}



		public void setAvance_curso(String avance_curso) {
			this.avance_curso = avance_curso;
		}



		public long getId_usercurso() {
			return id_usercurso;
		}



		public void setId_usercurso(long id_usercurso) {
			this.id_usercurso = id_usercurso;
		}



		public UsuarioCurso getUsuarioFinalizado() {
			return usuarioFinalizado;
		}



		public void setUsuarioFinalizado(UsuarioCurso usuarioFinalizado) {
			this.usuarioFinalizado = usuarioFinalizado;
		}



		public int getDias() {
			return dias;
		}



		public void setDias(int dias) {
			this.dias = dias;
		}



		public String getMensajeDias() {
			return mensajeDias;
		}



		public void setMensajeDias(String mensajeDias) {
			this.mensajeDias = mensajeDias;
		}



		public List<UsuarioCurso> getListaUsuariosCursos() {
			return listaUsuariosCursos;
		}



		public void setListaUsuariosCursos(List<UsuarioCurso> listaUsuariosCursos) {
			this.listaUsuariosCursos = listaUsuariosCursos;
		}

		public long getId_usuario_curso_fk() {
			return id_usuario_curso_fk;
		}

		public void setId_usuario_curso_fk(long id_usuario_curso_fk) {
			this.id_usuario_curso_fk = id_usuario_curso_fk;
		}

		public UsuarioCurso getUsuarioCursoPa() {
			return usuarioCursoPa;
		}

		public void setUsuarioCursoPa(UsuarioCurso usuarioCursoPa) {
			this.usuarioCursoPa = usuarioCursoPa;
		}

		public List<UsuarioCursoDTO> getListaUsuariosCursosDTO() {
			return listaUsuariosCursosDTO;
		}

		public void setListaUsuariosCursosDTO(List<UsuarioCursoDTO> listaUsuariosCursosDTO) {
			this.listaUsuariosCursosDTO = listaUsuariosCursosDTO;
		}


} 
 