package mia.core.model.usuario;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.print.attribute.standard.Fidelity;

import mia.core.model.usuario.dto.UserCursoModuloDTO;
import mia.core.model.usuario.dto.UsuarioCursoDTO;
import mia.core.model.administrador.ManagerCurso;
import mia.core.model.cuestionario.dto.PreguntaModuloDTO;
import mia.core.model.entities.Curso;
import mia.core.model.entities.CursoModulo;
import mia.core.model.entities.Usuario;
import mia.core.model.entities.UsuarioCurso;
import mia.core.model.entities.UsuarioCursoModulo;

/**
 * Session Bean implementation class ManagerCuestionario
 */
@Stateless
@LocalBean
public class ManagerUserCurso {

	@PersistenceContext(unitName = "miaDS")
	private EntityManager em;

	@EJB
	private ManagerCurso managerCurso;

	@EJB
	private ManagerUsuario managerUsuario;

	@EJB
	private ManagerUserCurso managerUserCurso;

	public ManagerUserCurso() {

	}

	/*
	 * CARGAR LISTA DE USUARIOS CURSOS DTO
	 */
	public List<UsuarioCursoDTO> cargarListaUsuarioCursoDTOs(List<UsuarioCurso> listaUsuariosCursos)
			throws ParseException {
		List<UsuarioCursoDTO> listaNueva = new ArrayList<UsuarioCursoDTO>();
		if (listaUsuariosCursos.isEmpty()) {
			return listaNueva;
		} else {

			for (UsuarioCurso u : listaUsuariosCursos) {
				UsuarioCursoDTO uDTO = new UsuarioCursoDTO();
				uDTO.setAvance(u.getAvance());
				BigDecimal avancePla = calcularavanceplanificado(u.getFechaInicioProgramada(),
						u.getFechaFinProgramada());
				uDTO.setAvancePlanificado(avancePla);
				uDTO.setCurso(u.getCurso());
				uDTO.setFechaFinProgramada(u.getFechaFinProgramada());
				uDTO.setFechaFinReal(u.getFechaFinReal());
				uDTO.setFechaInicioProgramada(u.getFechaInicioProgramada());
				uDTO.setFechaInicioReal(u.getFechaInicioReal());
				uDTO.setIdUsuariocurso(u.getIdUsuariocurso());
				List<UsuarioCursoModulo> listaUcM = new ArrayList<UsuarioCursoModulo>();
				listaUcM = findAllUsuarioCursoModulobyUserCusro(u.getIdUsuariocurso());
				System.out.println("sss"+listaUcM.size());
				List<UserCursoModuloDTO> lista = cargarListaUsuarioCursoModulosDTOs(listaUcM);
				uDTO.setListaCursosModulosDTO(lista);
				uDTO.setModulorealizados(u.getModulorealizados());
				BigDecimal retraso = calcularaRetraso(uDTO.getAvance(), uDTO.getAvancePlanificado());
				uDTO.setRetrasoPlanificado(retraso);
				uDTO.setUsuario(u.getUsuario());
				uDTO.setUsuarioCurso(u.getUsuarioCurso());
				uDTO.setUsuarioCursoModulos(u.getUsuarioCursoModulos());
				uDTO.setUsuarioCursos(u.getUsuarioCursos());
				listaNueva.add(uDTO);
			}
			return listaNueva;
		}

	}

	public UsuarioCursoDTO cargarUsuarioCursoDTO(UsuarioCursoDTO user) throws ParseException {
		UsuarioCursoDTO uDTO = new UsuarioCursoDTO();
		UsuarioCurso u = findUsuarioCursoById(user.getIdUsuariocurso());
		uDTO.setAvance(u.getAvance());
		BigDecimal avancePla = calcularavanceplanificado(u.getFechaInicioProgramada(), u.getFechaFinProgramada());
		uDTO.setAvancePlanificado(avancePla);
		uDTO.setCurso(u.getCurso());
		uDTO.setFechaFinProgramada(u.getFechaFinProgramada());
		uDTO.setFechaFinReal(u.getFechaFinReal());
		uDTO.setFechaInicioProgramada(u.getFechaInicioProgramada());
		uDTO.setFechaInicioReal(u.getFechaInicioReal());
		uDTO.setIdUsuariocurso(u.getIdUsuariocurso());
		List<UsuarioCursoModulo> listaUcM = new ArrayList<UsuarioCursoModulo>();
		listaUcM = findAllUsuarioCursoModulobyUserCusro(u.getIdUsuariocurso());
		List<UserCursoModuloDTO> lista = cargarListaUsuarioCursoModulosDTOs(listaUcM);
		uDTO.setListaCursosModulosDTO(lista);
		uDTO.setModulorealizados(u.getModulorealizados());
		BigDecimal retraso = calcularaRetraso(uDTO.getAvance(), uDTO.getAvancePlanificado());
		uDTO.setRetrasoPlanificado(retraso);
		uDTO.setUsuario(u.getUsuario());
		uDTO.setUsuarioCurso(u.getUsuarioCurso());
		uDTO.setUsuarioCursoModulos(u.getUsuarioCursoModulos());
		uDTO.setUsuarioCursos(u.getUsuarioCursos());
		return uDTO;
	}

	public List<UserCursoModuloDTO> cargarListaUsuarioCursoModulosDTOs(
			List<UsuarioCursoModulo> listaUsuariosCursosModulos) throws ParseException {
		List<UserCursoModuloDTO> listaNueva = new ArrayList<UserCursoModuloDTO>();
		if (listaUsuariosCursosModulos.isEmpty()) {
			System.out.println("chale");
			return listaNueva;
		} else {
			System.out.println("pasa");
			for (UsuarioCursoModulo u : listaUsuariosCursosModulos) {
				UserCursoModuloDTO uDTO = new UserCursoModuloDTO();
				uDTO.setAciertos(u.getAciertos());
				uDTO.setAvance(u.getAvance());
				BigDecimal avancePla = calcularavanceplanificado(u.getFechaInicioProgramada(),
						u.getFechaFinProgramada());
				uDTO.setAvancePlanificado(avancePla);
				uDTO.setCursoModulo(u.getCursoModulo());
				uDTO.setErrores(u.getErrores());
				uDTO.setFechaFinProgramada(u.getFechaFinProgramada());
				uDTO.setFechaFinReal(u.getFechaFinReal());
				uDTO.setFechaInicioProgramada(u.getFechaInicioProgramada());
				uDTO.setFechaInicioReal(u.getFechaInicioReal());
				uDTO.setIdUsuarioCursoModulo(u.getIdUsuarioCursoModulo());
				BigDecimal retraso = calcularaRetraso(uDTO.getAvance(), uDTO.getAvancePlanificado());
				uDTO.setRetrasoPlanificado(retraso);
				uDTO.setUsuarioCurso(u.getUsuarioCurso());
				listaNueva.add(uDTO);
			}
			return listaNueva;
		}

	}

	public BigDecimal calcularavanceplanificado(Date fechaInicio, Date fechaFin) throws ParseException {
		Date fecha = new Date();
		long tiempoActualSistema, tiempoFin, tiempoInicio, diasTranscurrridos, diasProgramados;
		tiempoActualSistema = fecha.getTime();// milisegundos
		tiempoInicio = fechaInicio.getTime();// milisegundos
		tiempoFin = fechaFin.getTime();// milisegundos

		diasProgramados = (tiempoFin - tiempoInicio) / (1000 * 60 * 60 * 24);
		if (tiempoInicio > tiempoActualSistema) {
			return BigDecimal.valueOf(0.0);
		}
		if (diasProgramados < 0) {
			diasProgramados = 0;
		}
		if (tiempoFin < tiempoActualSistema) {
			diasTranscurrridos = diasProgramados;
		} else
			diasTranscurrridos = (diasProgramados - 1) - (tiempoFin - tiempoActualSistema) / (1000 * 60 * 60 * 24);

		BigDecimal porcentaje = calcularPorcentajeProgramado(diasProgramados, diasTranscurrridos);
		BigDecimal t = new BigDecimal(100.0);
		if (porcentaje.doubleValue() > 100.00) {
			return t;

		} else
			return porcentaje;
	}

	public BigDecimal calcularPorcentajeProgramado(long totalDias, long diasAvanzados) {
		double r;
		BigDecimal resultado;
		if (totalDias == 0 && diasAvanzados == 0) {
			resultado = new BigDecimal(100.0);
			resultado = resultado.setScale(2, BigDecimal.ROUND_HALF_UP);
		} else {
			r = (Double.parseDouble(diasAvanzados + "") * 100) / Double.parseDouble(totalDias + "");
			resultado = new BigDecimal(r);
			resultado = resultado.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		return resultado;
	}

	public BigDecimal calcularaRetraso(BigDecimal avanceEjecutado, BigDecimal avanceProgramado) throws ParseException {
		double a, b, r;
		if (avanceEjecutado == null) {
			avanceEjecutado = BigDecimal.valueOf(0.0);
		}
		b = Double.parseDouble(avanceEjecutado + "");// 5
		a = Double.parseDouble(avanceProgramado + "");// 10
		if (a > b) {
			r = a - b;
		} else
			r = 0.0;
		BigDecimal resultado = new BigDecimal(r);
		resultado = resultado.setScale(2, BigDecimal.ROUND_HALF_UP);

		return resultado;
	}

	/***
	 * mï¿½todos de usuario cursos
	 */
	// lista de usuarios y cursos
	public List<UsuarioCurso> findAllUsuarioCursoes() {

		Query q = em.createQuery("SELECT u FROM UsuarioCurso u", UsuarioCurso.class);
		@SuppressWarnings("unchecked")
		List<UsuarioCurso> listaUsuarioCursos = q.getResultList();
		return listaUsuarioCursos;
	}

	// lista de usuarios y cursos
	public List<UsuarioCurso> findAllUsuarioCursoes(long idUsuario) {

		Query q = em.createQuery("SELECT u FROM UsuarioCurso u where u.usuario.idUsuario=" + idUsuario,
				UsuarioCurso.class);
		@SuppressWarnings("unchecked")
		List<UsuarioCurso> listaUsuarioCursos = q.getResultList();
		return listaUsuarioCursos;
	}

	// lista de usuarios y cursos
	public List<CursoModulo> findAllUsuarioCursoByIdCurso(int idCurso) {
		Query q = em.createQuery(
				"SELECT u FROM CursoModulo u where u.curso.idCurso=" + idCurso + "" + " order by u.ordenCurso ",
				CursoModulo.class);
		@SuppressWarnings("unchecked")
		List<CursoModulo> listaUsuarioCursos = q.getResultList();
		return listaUsuarioCursos;
	}

	// lista de usuarios y cursos
	public CursoModulo findCursoModuloByCursoAndModulo(int idCurso, long idModulo) {
		CursoModulo cm = new CursoModulo();
		Query q = em.createQuery("SELECT u FROM CursoModulo u where u.curso.idCurso=?1" + " and u.modulo.idModulo=?2",
				CursoModulo.class);
		q.setParameter(1, idCurso);
		q.setParameter(2, idModulo);
		@SuppressWarnings("unchecked")
		List<CursoModulo> listaCursosModulos = q.getResultList();
		if (!listaCursosModulos.isEmpty()) {
			cm = listaCursosModulos.get(0);
			return cm;
		} else {
			return cm;
		}

	}

	// lista de usuario y cursos
	public List<UsuarioCurso> findAllUsuarioCursoesbyUser(long user) {

		String JPQL = "SELECT u FROM UsuarioCurso u WHERE u.usuario.idUsuario=?1";
		Query query = em.createQuery(JPQL, UsuarioCurso.class);
		query.setParameter(1, user);
		@SuppressWarnings("unchecked")
		List<UsuarioCurso> listaUsuarioCursos = query.getResultList();
		return listaUsuarioCursos;
	}

	// lista de usuario y cursos
	public List<UsuarioCurso> findAllUsuarioCursoPadre() {

		String JPQL = "SELECT u FROM UsuarioCurso u WHERE u.usuarioCurso=null";
		Query query = em.createQuery(JPQL, UsuarioCurso.class);
		@SuppressWarnings("unchecked")
		List<UsuarioCurso> listaUsuarioCursos = query.getResultList();
		return listaUsuarioCursos;
	}
	public List<UsuarioCurso> findAllUsuarioCursoHijos(long id_user_curso_pa) {

		String JPQL = "SELECT u FROM UsuarioCurso u WHERE u.usuarioCurso.idUsuariocurso=?1";
		Query query = em.createQuery(JPQL, UsuarioCurso.class);
		query.setParameter(1, id_user_curso_pa);
		@SuppressWarnings("unchecked")
		List<UsuarioCurso> listaUsuarioCursos = query.getResultList();
		return listaUsuarioCursos;
	}

	// lista de usuario y cursos
	public List<UsuarioCursoModulo> findAllUsuarioCursoModulobyUserCusro(long id_user_curso) {

		String JPQL = "SELECT u FROM UsuarioCursoModulo u WHERE u.usuarioCurso.idUsuariocurso=?1";
		Query query = em.createQuery(JPQL, UsuarioCursoModulo.class);
		query.setParameter(1, id_user_curso);
		@SuppressWarnings("unchecked")
		List<UsuarioCursoModulo> listaUsuarioCursosModulos = query.getResultList();
		return listaUsuarioCursosModulos;
	}

	public UsuarioCurso findUsuarioCursoById(long id_user_curso) {
		UsuarioCurso curso = em.find(UsuarioCurso.class, id_user_curso);
		return curso;
	}

	public UsuarioCursoModulo findUsuarioCursoModuloById(long id_user_curso_modulo) {
		UsuarioCursoModulo usuaCursoMod = em.find(UsuarioCursoModulo.class, id_user_curso_modulo);
		return usuaCursoMod;
	}

	public int obtenerResCorrectas(List<PreguntaModuloDTO> lp) throws Exception {

		int respCorrectas = 0;

		for (PreguntaModuloDTO pm : lp) {
			if (pm.getRespuestacorrecta() == null) {
				throw new Exception("El módulo " + pm.getModulo().getNombre()
						+ " no tiene cargada las respuestas correctas \n Contáctese con el administrador");
			}
			if (pm.getRespuestacorrecta().equals(pm.getRespuesta())) {
				respCorrectas++;
			}
		}

		return respCorrectas;
	}

	@SuppressWarnings("unchecked")
	public boolean existeNombreUsuariobyCurso(String nombre) {

		String JPQL = "SELECT u FROM UsuarioCurso u WHERE u.nombre=?1";
		Query query = em.createQuery(JPQL, UsuarioCurso.class);
		query.setParameter(1, nombre);
		List<UsuarioCurso> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}

	@SuppressWarnings("unchecked")
	public boolean existeUsuarioAndCursoenUserCur(int curso, long id_user) {

		String JPQL = "SELECT u FROM UsuarioCurso u WHERE u.curso.idCurso=?1 and u.usuario.idUsuario=" + id_user;
		Query query = em.createQuery(JPQL, UsuarioCurso.class);
		query.setParameter(1, curso);
		List<UsuarioCurso> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}

	@SuppressWarnings("unchecked")
	public boolean existeUsuarioInscritoCurso(long id_curso_usuario, long id_user) {

		String JPQL = "SELECT u FROM UsuarioCurso u WHERE u.usuarioCurso.idUsuariocurso=?1 and u.usuario.idUsuario="
				+ id_user;
		Query query = em.createQuery(JPQL, UsuarioCurso.class);
		query.setParameter(1, id_curso_usuario);
		List<UsuarioCurso> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}

	public UsuarioCurso ingresarUsuarioCursoInvestigador(long id_user, Integer id_curso, UsuarioCurso userCurso)
			throws Exception {

		if (userCurso.getFechaInicioProgramada().after(userCurso.getFechaFinProgramada())) {
			throw new Exception("Las fechas ingresadas son incorrectas: \n" + "Fecha final menor a la ficha inicial");
		}
		boolean existecurso = existeUsuarioAndCursoenUserCur(id_curso, id_user);
		if (id_user == 0) {
			throw new Exception("Usuario no logeado");
		}
		if (id_curso == 0) {
			throw new Exception("Curso no seleccionado");
		}
		if (existecurso) {
			throw new Exception("Ya está inscrito en este curso");
		}
		Curso curso = managerCurso.findCursoById(id_curso);
		Usuario user = managerUsuario.findUsuarioById(id_user);

		BigDecimal num = new BigDecimal(0);
		UsuarioCurso nusercurso = new UsuarioCurso();
		nusercurso.setCurso(curso);
		nusercurso.setUsuario(user);
		nusercurso.setFechaInicioProgramada(userCurso.getFechaInicioProgramada());
		nusercurso.setFechaFinProgramada(userCurso.getFechaFinProgramada());
		nusercurso.setModulorealizados("");
		nusercurso.setAvance(num);
		List<CursoModulo> listaCursosModulos = new ArrayList<CursoModulo>();
		listaCursosModulos = findAllUsuarioCursoByIdCurso(id_curso);
		int dias = calculardiasParaCadaModulo(nusercurso.getFechaInicioProgramada(), nusercurso.getFechaFinProgramada(),
				listaCursosModulos.size());
		em.persist(nusercurso);
		ingresarUsuarioCursoModulo(nusercurso, curso, dias);
		return nusercurso;
	}

	public UsuarioCurso inscripcionUsuarioCurso(long id_user_curso_pa, long id_user) throws Exception {
		UsuarioCurso userC = findUsuarioCursoById(id_user_curso_pa);
		Usuario u = managerUsuario.findUsuarioById(id_user);
		if (u == null) {
			throw new Exception("Error: Null en el usuario");
		}

		if (userC == null) {
			throw new Exception("Error: El curso no existe ");
		} else {

			boolean existeUsuarioCurso = existeUsuarioInscritoCurso(id_user_curso_pa, id_user);
			if (existeUsuarioCurso) {
				throw new Exception("Error: Ya se encuentra registrado en el curso " + userC.getCurso().getNombre()
						+ " del" + " investigador " + userC.getUsuario().getApellidos() + " "
						+ userC.getUsuario().getNombres());
			} else {

				UsuarioCurso useNCurso = new UsuarioCurso();
				useNCurso.setAvance(userC.getAvance());
				useNCurso.setCurso(userC.getCurso());
				useNCurso.setFechaFinProgramada(userC.getFechaFinProgramada());
				useNCurso.setFechaInicioProgramada(userC.getFechaInicioProgramada());
				useNCurso.setModulorealizados(userC.getModulorealizados());
				useNCurso.setUsuario(u);
				useNCurso.setUsuarioCurso(userC);
				em.persist(useNCurso);

				inscripcionModulosUsuario(id_user_curso_pa, useNCurso.getIdUsuariocurso());

			}

		}
		return userC;
	}

	public void inscripcionModulosUsuario(long id_user_curso_pa, long id_user_cur_hijo) throws Exception {

		if (id_user_curso_pa == 0) {
			throw new Exception("Error: Usuario curso padre no existe");
		}
		if (id_user_cur_hijo == 0) {
			throw new Exception("Error: Usuario curso hijo no existe");
		}
		UsuarioCurso usuHijo = findUsuarioCursoById(id_user_cur_hijo);
		List<UsuarioCursoModulo> listaUserCurMod = findAllUsuarioCursoModulobyUserCusro(id_user_curso_pa);

		for (UsuarioCursoModulo u : listaUserCurMod) {
			UsuarioCursoModulo uCH = new UsuarioCursoModulo();
			uCH.setAciertos(u.getAciertos());
			uCH.setAvance(u.getAvance());
			uCH.setCursoModulo(u.getCursoModulo());
			uCH.setErrores(u.getErrores());
			uCH.setFechaFinProgramada(u.getFechaFinProgramada());
			uCH.setFechaInicioProgramada(u.getFechaInicioProgramada());
			uCH.setUsuarioCurso(usuHijo);
			em.persist(uCH);
		}

	}

	public void ingresarUsuarioCurso(long id_user, Integer id_curso) throws Exception {

		boolean existecurso = existeUsuarioAndCursoenUserCur(id_curso, id_user);
		if (id_user == 0) {
			throw new Exception("Usuario no logeado");
		}
		if (id_curso == 0) {
			throw new Exception("Curso no seleccionado");
		}
		if (existecurso) {
			throw new Exception("Ya está inscrito en este curso");
		}
		Curso curso = managerCurso.findCursoById(id_curso);
		Usuario user = managerUsuario.findUsuarioById(id_user);

		BigDecimal num = new BigDecimal(0);
		UsuarioCurso nusercurso = new UsuarioCurso();
		nusercurso.setCurso(curso);
		nusercurso.setUsuario(user);
		nusercurso.setModulorealizados("");
		nusercurso.setAvance(num);
		em.persist(nusercurso);
	}

public void ingresarUsuarioCursoModulo(UsuarioCurso useC, Curso cur,int dias) throws Exception {
		
		if (useC==null) {
			throw new Exception("Usuario curso vacío");	
		}
		
		List<CursoModulo>listaCursosModulos=new ArrayList<CursoModulo>();
		listaCursosModulos=findAllUsuarioCursoByIdCurso(cur.getIdCurso());
		int tamanioL=0 ,aux=0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(useC.getFechaInicioProgramada());
		double d=dias/listaCursosModulos.size();
		dias=(int) Math.round(d);
		for (CursoModulo c : listaCursosModulos) {
			
			UsuarioCursoModulo uCMod=new UsuarioCursoModulo();
	        if (aux==0) {
	        	uCMod.setAciertos(0);
				BigDecimal r=new BigDecimal(0);
				
				uCMod.setFechaInicioProgramada(calendar.getTime());
			System.out.println(":"+uCMod.getFechaInicioProgramada()+" DIASS: "+dias);
			      calendar.add(Calendar.DAY_OF_YEAR, dias);  
			      if(calendar.getTime().compareTo(useC.getFechaFinProgramada())>0 || calendar.getTime().compareTo(useC.getFechaFinProgramada())==0  ) {
				    	
				    	 uCMod.setFechaFinProgramada(useC.getFechaFinProgramada()); 
				     }else {
				    	 uCMod.setFechaFinProgramada(calendar.getTime());  
				     }
			      
			    uCMod.setFechaFinProgramada(calendar.getTime()); 
				System.out.println(":"+calendar.getTime());
				uCMod.setAvance(r);
				uCMod.setCursoModulo(c);
				uCMod.setErrores(0);
				uCMod.setUsuarioCurso(useC);
				em.persist(uCMod);
			}else {
				uCMod.setAciertos(0);
				BigDecimal r=new BigDecimal(0);
				
				uCMod.setFechaInicioProgramada(calendar.getTime());
			System.out.println(":"+uCMod.getFechaInicioProgramada()+" DIASS: "+dias);
			calendar.add(Calendar.DAY_OF_YEAR, dias);
			 if(calendar.getTime().compareTo(useC.getFechaFinProgramada())>0 || calendar.getTime().compareTo(useC.getFechaFinProgramada())==0  ) {
		    	 uCMod.setFechaFinProgramada(useC.getFechaFinProgramada()); 
		     }else {
		    	 uCMod.setFechaFinProgramada(calendar.getTime());  
		     }
			    System.out.println(":"+calendar.getTime());
				uCMod.setAvance(r);
				uCMod.setCursoModulo(c);
				uCMod.setErrores(0);
				uCMod.setUsuarioCurso(useC);
				em.persist(uCMod);
	
			}
	        aux++;
		}
		
		
	}
	
	
	public int calculardiasParaCadaModulo(Date fechaInicio, Date fechaFin, int numModulos) throws Exception {
		int res = 0;
		double r = 0;
		Date fechaActual = new Date();
		long fechaInicioU, fechaFinU, diasTranscurridos;
		fechaInicioU = fechaInicio.getTime();
		fechaFinU = fechaFin.getTime();
		diasTranscurridos = (fechaFinU - fechaInicioU) / (1000 * 60 * 60 * 24);
		r = Double.parseDouble(diasTranscurridos + "");
		System.out.println(": " + r + " : " + numModulos);
		res = (int) diasTranscurridos;
		if (res < numModulos) {
			throw new Exception(
					"Error: almenos debe existir un día para cada módulo vuelva a seleccionar las fechas \n "
							+ " Existen " + numModulos + " módulos " + " para que sean realizados en " + res + " días");
		}
		return res;
	}

	@SuppressWarnings("unused")
	public String ConcatenarModulos(UsuarioCurso usuercurse, UsuarioCursoModulo usuarioCursoModulo) {
		// error
		String modulo = usuercurse.getModulorealizados();
		char[] caracteres;
		if (modulo.equals(" ") || modulo.length() == 0) {
			modulo = "";
			return modulo;
		} else {
			String[] parts = modulo.split(",");
			boolean existe = false;
			for (String s : parts) {
				if (s.equals(usuarioCursoModulo.getCursoModulo().getOrdenCurso() + "")) {
					existe = true;
				}
			}
			if (!existe) {
				caracteres = modulo.toCharArray();
				int ultimo = Integer.parseInt(usuarioCursoModulo.getCursoModulo().getOrdenCurso() + "");

				modulo = modulo + "," + ultimo;
				return modulo;
			} else {
				return modulo;
			}

		}
	}

	public void editarCurso(UsuarioCurso usercurseA, int id_curso, long id_user) throws Exception {
		UsuarioCurso usercurseN = findUsuarioCursoById(usercurseA.getIdUsuariocurso());
		if (usercurseN == null) {
			throw new Exception("Error al cargar el Usuario y Curso");
		}
		Curso curso = managerCurso.findCursoById(id_curso);
		Usuario user = managerUsuario.findUsuarioById(id_user);
		usercurseN.setCurso(curso);
		usercurseN.setUsuario(user);
		usercurseN.setAvance(usercurseA.getAvance());
		usercurseN.setModulorealizados(usercurseA.getModulorealizados());
		em.merge(usercurseN);
	}

	public String ConcatenarModulosRealizados(UserCursoModuloDTO userdto, CursoModulo curso) {
		String Respuesta = "";
		return Respuesta;

	}
	/*
	 * public boolean existeModuloRealizado( UserCursoModuloDTO userCursoDto) {
	 * UsuarioCurso
	 * usuaC=findUsuarioCursoById(userCursoDto.getUsuarioCurso().getIdUsuariocurso()
	 * ); int tama=usuaC.getModulorealizados().length(); return ; }
	 */

	public void editarAvanceCurso(UsuarioCursoDTO userCursoDto, UserCursoModuloDTO usuarioCursoModulo,
			List<PreguntaModuloDTO> preguntamoduloDTO) throws Exception {
		UsuarioCurso usercurseN = findUsuarioCursoById(userCursoDto.getIdUsuariocurso());
		if (usercurseN == null) {
			throw new Exception("Usuario Curso no existe en la base de datos!.");
		}

		porcentajeUsuarioCursoModulo(usuarioCursoModulo, preguntamoduloDTO);
		String modulo = ConcatenarModulos(usercurseN, usuarioCursoModulo);
		usercurseN.setModulorealizados(modulo);
		BigDecimal d = new BigDecimal(100);
		if (Double.parseDouble(porcentajeAvanceCursoUsuario(usercurseN) + "") > 100.00) {
			usercurseN.setAvance(d);
		} else {
			usercurseN.setAvance(porcentajeAvanceCursoUsuario(usercurseN));

		}
		em.merge(usercurseN);

	}

	public BigDecimal porcentajeUsuarioCursoModulo(UsuarioCursoModulo usuarioCursoModulo,
			List<PreguntaModuloDTO> preguntamoduloDTO) throws Exception {
		if (preguntamoduloDTO.isEmpty()) {
			throw new Exception("Las respuestas del módulo están vacías!.");
		} else {

			BigDecimal p = new BigDecimal(0);
			double respuesta = 0.0;
			int totalPreguntas = preguntamoduloDTO.size();

			int respuestasCorrecta = obtenerResCorrectas(preguntamoduloDTO);

			int respuestasIncorrectas = totalPreguntas - respuestasCorrecta;

			respuesta = Math.round((respuestasCorrecta * 100) / totalPreguntas);
			p = new BigDecimal(respuesta);
			UsuarioCursoModulo uCM = findUsuarioCursoModuloById(usuarioCursoModulo.getIdUsuarioCursoModulo());
			uCM.setAciertos(respuestasCorrecta);
			uCM.setErrores(respuestasIncorrectas);
			p = p.setScale(2, BigDecimal.ROUND_HALF_EVEN);
			uCM.setAvance(p);
			em.merge(uCM);
			return p;
		}

	}

	public BigDecimal porcentajeAvanceCursoUsuario(UsuarioCurso usercurseN) throws Exception {
		BigDecimal bi = new BigDecimal(0);
		List<UsuarioCursoModulo> lista = findAllUsuarioCursoModulobyUserCusro(usercurseN.getIdUsuariocurso());
		if (lista.isEmpty()) {
			throw new Exception("Contactese con el administrador el cuestionario no tiene módulos..");
		} else {
			double respuesta = 0.0;
			int numMod = lista.size();
			for (UsuarioCursoModulo u : lista) {
				respuesta = respuesta + Double.parseDouble(u.getAvance() + "");
			}
			if (respuesta > 0.0) {
				respuesta = respuesta / numMod;
				bi = new BigDecimal(respuesta);
				bi = bi.setScale(2, BigDecimal.ROUND_HALF_EVEN);
				return bi;
			}
			return bi;
		}

	}

	public void eliminarUsuarioCurso(int id_modulo) throws Exception {
		UsuarioCurso moduloN = findUsuarioCursoById(id_modulo);
		if (moduloN == null) {
			throw new Exception("Error al cargar el usuario.");
		}

		em.remove(moduloN);
	}

	// metodos de lista user curso DTO
	/*
	 * public UserCursoModuloDTO cargarListaUserCurso(UsuarioCurso userCurso) {
	 * userCurso = findUsuarioCursoById(userCurso.getIdUsuariocurso());
	 * UserCursoModuloDTO userCursoDto = new UserCursoModuloDTO();
	 * List<UsuarioCursoModulo> listaUsuarioCursoModulo =
	 * findAllUsuarioCursoModulobyUserCusro(userCurso.getIdUsuariocurso());
	 * userCursoDto.setIdUsuariocurso(userCurso.getIdUsuariocurso());
	 * userCursoDto.setAvance(userCurso.getAvance());
	 * userCursoDto.setCurso(userCurso.getCurso());
	 * userCursoDto.setModulorealizados(userCurso.getModulorealizados());
	 * userCursoDto.setUsuario(userCurso.getUsuario());
	 * userCursoDto.setListaUsuarioCurMod(listaUsuarioCursoModulo);
	 * 
	 * AQUI HAY QUE MODIFICAR
	 * 
	 * userCursoDto.setListaUsuarioCurMod(listaUsuarioCursoModulo); return
	 * userCursoDto; }
	 */
}
