package mia.core.model.administrador.view.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import mia.core.model.administrador.ManagerCurso;
import mia.core.model.entities.Curso;
import mia.core.model.entities.CursoModulo;
import mia.core.model.entities.Modulo;
import mia.core.model.login.view.controller.BeanLogin;
import mia.core.model.usuario.view.controller.BeanUserCurso;
import mia.modulos.view.util.JSFUtil;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanCurso implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id_modulo;
	private int id_curso;
	private long id_cursomodulo;
	private int orden;
	
	private Curso curso = new Curso();
	private Curso cursoE;
	private Modulo modulo = new Modulo();
	private Modulo moduloE= new Modulo();
	private CursoModulo cursomodulo= new CursoModulo();
	private CursoModulo cursomoduloE=new CursoModulo();
	
	@EJB
	private ManagerCurso managerCurso;
	
	
	private List<Curso> cursos;
	private List<Modulo> modulos;
	private List<CursoModulo> listaModCur;
	private List<CursoModulo> cursomodulos;

	
	
	
	@Inject
	private BeanLogin beanLogin;
	
	@Inject
	private BeanUserCurso beanUserCurso;
	
	@PostConstruct
	public void init() {  
		try {
			cursos= managerCurso.findAllCursoes();
			modulos= managerCurso.findAllModuloes();
			cursomodulos= managerCurso.findAllCursoModuloes();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}
	

	public String actionModulosByCurso(long idCurso) {
		try {
			listaModCur= managerCurso.findAllModulosByCuros(idCurso);
System.out.println(""+listaModCur.size());
	    JSFUtil.crearMensajeInfo("Curso creada correctamente");
	    return"usuariocurso1?faces-redirect=true";
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
			return "";
		}
	}
	
	

	/**
	 * metodos crud del curso 
	 */
	
	public void actionListenerIngresarCurso() {
		try {
			cursomodulos= managerCurso.findAllCursoModuloes();
			managerCurso.ingresarCurso(curso);
	    cursos= managerCurso.findAllCursoes();
	    JSFUtil.crearMensajeInfo("Curso creada correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}
	
	public void actionListenerEliminarCurso(int id_curso) {
		try {
			managerCurso.eliminarCurso(id_curso);
			cursos = managerCurso.findAllCursoes();
			JSFUtil.crearMensajeInfo("Cursos eliminado correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}//
	
	public void actionListenerCargar(Curso cursoC) {
		try {
			cursoE = cursoC;
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}
	public void actionListenerEditarCurso() {
		try {
			managerCurso.editarCurso(cursoE);
			JSFUtil.crearMensajeInfo("Curso editado correctamente");
		} catch (Exception e) {
			cursos = managerCurso.findAllCursoes();
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}

/*
 * metodos de la tabla modulo
 */
	public void actionListenerIngresarModulo() {
		try {
			managerCurso.ingresarModulo(modulo);
	    modulos= managerCurso.findAllModuloes();
	    JSFUtil.crearMensajeInfo("Modulo creada correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}
	
	public void actionListenerEliminarModulo(int id_modulo) {
		try {
			managerCurso.eliminarModulo(id_modulo);
			modulos = managerCurso.findAllModuloes();
			JSFUtil.crearMensajeInfo("Modulos eliminado correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}//
	
	public void actionListenerCargarModulo(Modulo moduloC) {
		try {
			moduloE = moduloC;
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}
	public void actionListenerEditarModulo() {
		try {
			managerCurso.editarModulo(moduloE);
			JSFUtil.crearMensajeInfo("Modulo editado correctamente");
		} catch (Exception e) {
			modulos = managerCurso.findAllModuloes();
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}
	
	/**
	 * metodo de la tabla curso modulo 
	 */
	
	public void actionListenerIngresarCursoModulo() {
		try {
			managerCurso.ingresarCursoModulo(id_modulo, id_curso, orden);
			cursomodulos= managerCurso.findAllCursoModuloes();
	    JSFUtil.crearMensajeInfo("CursoModulo creada correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}
	
	public void actionListenerEliminarCursoModulo(int id_cursomodulo) {
		try {
			managerCurso.eliminarCursoModulo(id_cursomodulo);
			cursomodulos = managerCurso.findAllCursoModuloes();
			JSFUtil.crearMensajeInfo("CursoModulos eliminado correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}
	
	public void actionListenerCargarCursoModulo(CursoModulo moduloC) {
		try {
			System.out.println("ID: "+moduloC.getIdCursoModulo());
			System.out.println("ID: "+moduloC.getIdCursoModulo());
			cursomoduloE=moduloC ;
			id_curso=moduloC.getCurso().getIdCurso();
			id_modulo=moduloC.getModulo().getIdModulo();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}
	public void actionListenerEditarCursoModulo() {
		try {
			managerCurso.editarCursoModulo(cursomoduloE,id_curso,id_modulo);
			JSFUtil.crearMensajeInfo("CursoModulo editado correctamente");
		} catch (Exception e) {
			cursomodulos = managerCurso.findAllCursoModuloes();
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}



	public long getId_modulo() {
		return id_modulo;
	}



	public void setId_modulo(long id_modulo) {
		this.id_modulo = id_modulo;
	}



	public Integer getId_curso() {
		return id_curso;
	}



	public void setId_curso(Integer id_curso) {
		this.id_curso = id_curso;
	}



	public Curso getCurso() {
		return curso;
	}



	public void setCurso(Curso curso) {
		this.curso = curso;
	}



	public Curso getCursoE() {
		return cursoE;
	}



	public void setCursoE(Curso cursoE) {
		this.cursoE = cursoE;
	}



	public Modulo getModulo() {
		return modulo;
	}



	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}



	public Modulo getModuloE() {
		return moduloE;
	}



	public void setModuloE(Modulo moduloE) {
		this.moduloE = moduloE;
	}



	public CursoModulo getCursomodulo() {
		return cursomodulo;
	}



	public void setCursomodulo(CursoModulo cursomodulo) {
		this.cursomodulo = cursomodulo;
	}



	public CursoModulo getCursomoduloE() {
		return cursomoduloE;
	}



	public void setCursomoduloE(CursoModulo cursomoduloE) {
		this.cursomoduloE = cursomoduloE;
	}



	public List<Curso> getCursos() {
		return cursos;
	}



	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}



	public List<Modulo> getModulos() {
		return modulos;
	}



	public void setModulos(List<Modulo> modulos) {
		this.modulos = modulos;
	}



	public List<CursoModulo> getCursomodulos() {
		return cursomodulos;
	}



	public void setCursomodulos(List<CursoModulo> cursomodulos) {
		this.cursomodulos = cursomodulos;
	}



	public BeanLogin getBeanLogin() {
		return beanLogin;
	}



	public void setBeanLogin(BeanLogin beanLogin) {
		this.beanLogin = beanLogin;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public long getId_cursomodulo() {
		return id_cursomodulo;
	}



	public void setId_cursomodulo(long id_cursomodulo) {
		this.id_cursomodulo = id_cursomodulo;
	}





	public List<CursoModulo> getListaModCur() {
		return listaModCur;
	}





	public void setListaModCur(List<CursoModulo> listaModCur) {
		this.listaModCur = listaModCur;
	}





	public BeanUserCurso getBeanUserCurso() {
		return beanUserCurso;
	}





	public void setBeanUserCurso(BeanUserCurso beanUserCurso) {
		this.beanUserCurso = beanUserCurso;
	}


	public int getOrden() {
		return orden;
	}


	public void setOrden(int orden) {
		this.orden = orden;
	}


	public void setId_curso(int id_curso) {
		this.id_curso = id_curso;
	}

	
}
