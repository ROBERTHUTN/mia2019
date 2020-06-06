package mia.core.model.administrador;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mia.core.model.entities.Curso;
import mia.core.model.entities.CursoModulo;
import mia.core.model.entities.Modulo;

/**
 * Session Bean implementation class ManagerCuestionario
 */
/**
 * @author MIA
 *
 */
@Stateless
@LocalBean
public class ManagerCurso {
	


	@PersistenceContext(unitName = "miaDS")
	private EntityManager em;

	public ManagerCurso() {

	}
	
	/***
	 * metodos de  curso
	 */

	public List<Curso> findAllCursoes() {

		Query q = em.createQuery("SELECT c FROM Curso c", Curso.class);
		@SuppressWarnings("unchecked")
		List<Curso> listaCursos = q.getResultList();
		return listaCursos;
	}
	/**
	 * 
	 public List<Curso> findAllCursoesByCuestionario(int id_curso) {

		Query q = em.createQuery("SELECT c FROM Curso c where c.id_curso="+id_curso, Curso.class);
		@SuppressWarnings("unchecked")
		List<Curso> listaCursos = q.getResultList();
		return listaCursos;
	}
	 */
	

	public Curso findCursoById( int id_curso) {
		Curso curso = em.find(Curso.class, id_curso);
		return curso;

	}

	@SuppressWarnings("unchecked")
	public boolean existeNombreCurso(String nombre) {

		String JPQL = "SELECT c FROM Curso c WHERE c.nombre=?1";
		Query query = em.createQuery(JPQL, Curso.class);
		query.setParameter(1, nombre);
		List<Curso> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}
/*
	@SuppressWarnings("unchecked")
	public boolean existeCuestionario(int id_cuest) {
		String JPQL = "SELECT o FROM Curso o WHERE o.cuestionario.idCuestionario=" + id_cuest;
		Query query = em.createQuery(JPQL, Curso.class);
		List<Curso> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}
*/
	public void ingresarCurso(Curso curso )throws Exception {
		if (curso == null) {
			throw new Exception("No ha ingresado datos en el curso");
		}
		boolean existeNombreCurso = existeNombreCurso(curso.getNombre());
		if (existeNombreCurso) {
			throw new Exception("Ya existe el curso con el nombre " + curso.getDescripcion());
		}
		Curso ncurso = new Curso();
		ncurso.setNombre(curso.getNombre());
		ncurso.setDescripcion(curso.getDescripcion());
		ncurso.setAvance(curso.getAvance());
		em.persist(ncurso);

	}

	public void editarCurso(Curso cursoA) throws Exception {
		Curso cursoN = findCursoById(cursoA.getIdCurso());
		if (cursoN == null) {
			throw new Exception("Error al cargar el Curso");
		}
		
		
		if (!cursoA.getNombre().equals(cursoN.getNombre())) {
			boolean existeNombreCurso = existeNombreCurso(cursoA.getNombre());
			if (existeNombreCurso) {
				throw new Exception("Ya existe el curso con el nombre " + cursoA.getDescripcion());
			}
		}
		cursoN.setNombre(cursoA.getNombre());
		cursoN.setDescripcion(cursoA.getDescripcion());
		cursoN.setAvance(cursoA.getAvance());
		em.merge(cursoN);
	}

	public void eliminarCurso(int id_curso) throws Exception {
		Curso cursoN = findCursoById(id_curso);
		if (cursoN == null) {
			throw new Exception("Error al cargar el cuestionario.");
		}
		em.remove(cursoN);
	}

	/***
	 * mï¿½todos de  modulo
	 */
	public List<Modulo> findAllModuloes() {

		Query q = em.createQuery("SELECT m FROM Modulo m", Modulo.class);
		@SuppressWarnings("unchecked")
		List<Modulo> listaModulos = q.getResultList();
		return listaModulos;
	}
	
	public Modulo findModuloById(long id_modulo) {
		Modulo curso = em.find(Modulo.class, id_modulo);
		return curso;
	}

	@SuppressWarnings("unchecked")
	public boolean existeNombreModulo(String nombre) {

		String JPQL = "SELECT m FROM Modulo m WHERE m.nombre=?1";
		Query query = em.createQuery(JPQL, Modulo.class);
		query.setParameter(1, nombre);
		List<Modulo> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}

	public void ingresarModulo(Modulo modulo )throws Exception {
		if (modulo == null) {
			throw new Exception("No ha ingresado datos en el modulo");
		}
		boolean existeNombreModulo = existeNombreModulo(modulo.getNombre());
			if (existeNombreModulo) {
				throw new Exception("Ya existe el modulo con el nombre " + modulo.getNombre());
			}
		
		
		Modulo ncurso = new Modulo();
		ncurso.setNombre(modulo.getNombre());
		ncurso.setDescripcion(modulo.getDescripcion());
		ncurso.setDireccion(modulo.getDireccion());
		em.persist(ncurso);

	}

	public void editarModulo(Modulo moduloA) throws Exception {
		Modulo moduloN = findModuloById(moduloA.getIdModulo());
		if (moduloN == null) {
			throw new Exception("Error al cargar el Modulo");
		}
		if (!moduloA.getNombre().equals(moduloN.getNombre())) {
			boolean existeNombreModulo = existeNombreModulo(moduloA.getNombre());
			if (existeNombreModulo) {
				throw new Exception("Ya existe el modulo con el nombre " + moduloA.getDescripcion());
			}
		}
		moduloN.setNombre(moduloA.getNombre());
		moduloN.setDescripcion(moduloA.getDescripcion());
		moduloN.setDireccion(moduloA.getDireccion());
		em.merge(moduloN);
	}

	public void eliminarModulo(int id_modulo) throws Exception {
		Modulo moduloN = findModuloById(id_modulo);
		if (moduloN == null) {
			throw new Exception("Error al cargar el mÃ³dulo.");
		}

		em.remove(moduloN);

	}
	
	/**
	 * metodos de la tabla cursos modulos
	 */

	public List<CursoModulo> findAllCursoModuloes() {

		Query q = em.createQuery("SELECT c FROM CursoModulo c", CursoModulo.class);
		@SuppressWarnings("unchecked")
		List<CursoModulo> listaCursoModulos = q.getResultList();
		return listaCursoModulos;
	}
	public List<CursoModulo> findAllModulosByCuros(long idCurso) {

		Query q = em.createQuery("SELECT c FROM CursoModulo c where c.curso.idCurso="+idCurso, CursoModulo.class);
		@SuppressWarnings("unchecked")
		List<CursoModulo> listaCursoModulos = q.getResultList();
		return listaCursoModulos;
	}
	
	public CursoModulo findCursoModuloById(long id_usercurso) {
		CursoModulo curso = em.find(CursoModulo.class, id_usercurso);
		return curso;
	}

	
	
	@SuppressWarnings("unchecked")
	public boolean existeCursoModulo(int curso) {
		String JPQL = "SELECT c FROM CursoModulo c WHERE c.idCurso=?1";
		Query query = em.createQuery(JPQL, CursoModulo.class);
		query.setParameter(1, curso);
		List<CursoModulo> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}
	
	
	@SuppressWarnings("unchecked")
	public boolean existeCursoModulo(int curso, long modulo) {
		String JPQL = "SELECT c FROM CursoModulo c WHERE c.curso.idCurso=?1 and c.modulo.idModulo=?2";
		Query query = em.createQuery(JPQL, CursoModulo.class);
		query.setParameter(1, curso);
		query.setParameter(2, modulo);
		List<CursoModulo> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}

	public void ingresarCursoModulo( long id_modulo, int id_curso, int orden)throws Exception {
		
		if (id_modulo == 0 && id_curso == 0) {
			throw new Exception("No ha ingresado datos para el usuario y curso.");
		}
		
		boolean existeNombreCursoModulo = existeCursoModulo(id_curso,id_modulo);
		
		if (existeNombreCursoModulo) {
				throw new Exception("Ya existe un Curso con el módulo asignado.");	
		}
		
		/**
		boolean existeCurso = existeCursoModulo(id_curso);
		if (existeCurso) {
			throw new Exception("Ya existe el Curso.");
		}*/

		Modulo modulo= findModuloById(id_modulo);
		
		Curso curso_id=findCursoById(id_curso);
		CursoModulo ncursomodulo = new CursoModulo();
		ncursomodulo.setCurso(curso_id);
		ncursomodulo.setModulo(modulo);
		ncursomodulo.setOrdenCurso(orden);
		em.persist(ncursomodulo);

	}

	public void editarCursoModulo(CursoModulo cursomoduloA,int id_curso,long id_modulo) throws Exception {
		CursoModulo cursomoduloN = findCursoModuloById(cursomoduloA.getIdCursoModulo());
		if (cursomoduloN == null) {
			throw new Exception("Error al cargar el Curso y Módulo  a editar");
		}
		
		boolean existeNombreCursoModulo = existeCursoModulo(cursomoduloA.getCurso().getIdCurso(), cursomoduloA.getModulo().getIdModulo());
		
		if (existeNombreCursoModulo) {
				throw new Exception("Ya existe un Curso con el módulo asignado.");
			
		}
		
		cursomoduloN.setCurso(cursomoduloA.getCurso());
		cursomoduloN.setModulo(cursomoduloA.getModulo());
		em.merge(cursomoduloN);
	}

	public void eliminarCursoModulo(int id_modulo) throws Exception {
		CursoModulo moduloN = findCursoModuloById(id_modulo);
		if (moduloN == null) {
			throw new Exception("Error al cargar el mÃ³dulo.");
		}

		em.remove(moduloN);

	}

	
	/**
	 * metodos de usuario y curso
	
	
	public List<UsuarioCurso> findAllUsuarioCursoes() {

		Query q = em.createQuery("SELECT u FROM UsuarioCurso u", UsuarioCurso.class);
		@SuppressWarnings("unchecked")
		List<UsuarioCurso> listaUsuarioCursos = q.getResultList();
		return listaUsuarioCursos;
	}
	
	public UsuarioCurso findUsuarioCursoById(long id_usercurso) {
		UsuarioCurso curso = em.find(UsuarioCurso.class, id_usercurso);
		return curso;
	}

	@SuppressWarnings("unchecked")
	public boolean existeNombreUsuarioCurso(String nombre) {
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

	public void ingresarUsuarioCurso(long id_modulo, Integer id_curso)throws Exception {
		if (id_modulo == 0 && id_curso == 0) {
			throw new Exception("No ha ingresado datos para el usuario y curso.");
		}
		Modulo modulo= findModuloById(id_modulo);
		Curso curso=findCursoById(id_curso);
		CursoModulo ncursomodulo = new CursoModulo();
		ncursomodulo.setCurso(curso);
		ncursomodulo.setModulo(modulo);
		em.persist(ncursomodulo);

	}

	public void editarUsuarioCurso(UsuarioCurso usercursoA) throws Exception {
		UsuarioCurso moduloN = findUsuarioCursoById(moduloA.getIdUsuarioCurso());
		if (moduloN == null) {
			throw new Exception("Error al cargar el UsuarioCurso");
		}
		if (!moduloA.getNombre().equals(moduloN.getNombre())) {
			boolean existeNombreUsuarioCurso = existeNombreUsuarioCurso(moduloA.getNombre());
			if (existeNombreUsuarioCurso) {
				throw new Exception("Ya existe el modulo con el nombre " + moduloA.getDescripcion());
			}
		}
		moduloN.setNombre(moduloA.getNombre());
		moduloN.setDescripcion(moduloA.getDescripcion());
		moduloN.setDireccion(moduloA.getDireccion());
		em.merge(moduloN);
	}

	public void eliminarUsuarioCurso(int id_modulo) throws Exception {
		UsuarioCurso moduloN = findUsuarioCursoById(id_modulo);
		if (moduloN == null) {
			throw new Exception("Error al cargar el mÃ³dulo.");
		}

		em.remove(moduloN);

	}
	 */
	
}
