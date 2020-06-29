package mia.core.model.usuario;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mia.core.model.usuario.dto.UserCursoModuloDTO;
import mia.core.model.administrador.ManagerCurso;
import mia.core.model.cuestionario.dto.PreguntaModuloDTO;
import mia.core.model.entities.Curso;
import mia.core.model.entities.CursoModulo;
import mia.core.model.entities.Usuario;
import mia.core.model.entities.UsuarioCurso;

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

		Query q = em.createQuery("SELECT u FROM CursoModulo u where u.curso.idCurso=" + idCurso, CursoModulo.class);
		@SuppressWarnings("unchecked")
		List<CursoModulo> listaUsuarioCursos = q.getResultList();
		return listaUsuarioCursos;
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


	
	public UsuarioCurso findUsuarioCursoById(long id_user_curso) {
		UsuarioCurso curso = em.find(UsuarioCurso.class, id_user_curso);
		return curso;
	}

	public int obtenerResCorrectas(List<PreguntaModuloDTO> lp) {

		int respCorrectas = 0;

		for (PreguntaModuloDTO pm : lp) {
			System.out.println(pm.getRespuestacorrecta() + " /" + " " + pm.getRespuesta() + "");
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
		nusercurso.setModulorealizados("0");
		nusercurso.setAvance(num);
		em.persist(nusercurso);
	}

	@SuppressWarnings("unused")
	public String ConcatenarModulos(UsuarioCurso usuercurse, UserCursoModuloDTO userCursoDto) {
		String modulo = usuercurse.getModulorealizados();
		char[] caracteres;
		if (modulo.equals(" ") || modulo.length() == 0) {
			modulo = "";
			return modulo;
		} else {
			caracteres = modulo.toCharArray();
			int ultimo = Integer.parseInt(userCursoDto.getOrdenCurso() + "");

			modulo = modulo + "," + ultimo;
			return modulo;
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

	public void editarAvanceCurso(UserCursoModuloDTO userCursoDto) throws Exception {

		UsuarioCurso usercurseN = findUsuarioCursoById(userCursoDto.getUsuarioCurso().getIdUsuariocurso());
		if (usercurseN == null) {
			throw new Exception("Usuario Curso no existe en la bdd!.");
		}

		String modulo = ConcatenarModulos(usercurseN, userCursoDto);
		usercurseN.setModulorealizados(modulo);
		usercurseN.setAvance(porcentajeAvance(modulo, userCursoDto));
		BigDecimal d=new BigDecimal(100);
		if (Integer.parseInt(porcentajeAvance(modulo, userCursoDto)+"")>100) {
			usercurseN.setAvance(d);
		}
		em.merge(usercurseN);

	}

	public BigDecimal porcentajeAvance(String modulo, UserCursoModuloDTO userCursoDto) {
		BigDecimal bi;
		double a;
		List<CursoModulo> cursoModulo = findAllUsuarioCursoByIdCurso(userCursoDto.getCurso().getIdCurso());
		int numModulo = cursoModulo.size();
		UsuarioCurso usuaC = findUsuarioCursoById(userCursoDto.getUsuarioCurso().getIdUsuariocurso());
		int tama = usuaC.getModulorealizados().length();
		if (tama <= 1) {
			bi = new BigDecimal(0.00);
		} else {
			tama = tama - 1;
			tama = tama / 2;
			a = (tama * 100) / numModulo;
			bi = new BigDecimal(a);
		}

		return bi;
	}

	public void eliminarUsuarioCurso(int id_modulo) throws Exception {
		UsuarioCurso moduloN = findUsuarioCursoById(id_modulo);
		if (moduloN == null) {
			throw new Exception("Error al cargar el usuario.");
		}

		em.remove(moduloN);
	}

	// metodos de lista user curso DTO

	public List<UserCursoModuloDTO> cargarListaUserCurso(UsuarioCurso userCurso) {
		userCurso = findUsuarioCursoById(userCurso.getIdUsuariocurso());
		List<UserCursoModuloDTO> listaUserCursoDto = new ArrayList<>();
		List<CursoModulo> cursoModulo = findAllUsuarioCursoByIdCurso(userCurso.getCurso().getIdCurso());

		for (CursoModulo cM : cursoModulo) {
			UserCursoModuloDTO obj = new UserCursoModuloDTO();
			obj.setUsuarioCurso(userCurso);
			obj.setModulosrealizados(userCurso.getModulorealizados());
			obj.setAvance(userCurso.getAvance());
			obj.setCurso(cM.getCurso());
			obj.setOrdenCurso(cM.getOrdenCurso());
			obj.setIdCursoModulo(cM.getIdCursoModulo());
			obj.setModulo(cM.getModulo());
			listaUserCursoDto.add(obj);
		}
		return listaUserCursoDto;
	}
}
