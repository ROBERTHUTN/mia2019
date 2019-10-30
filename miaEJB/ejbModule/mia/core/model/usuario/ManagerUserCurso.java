package mia.core.model.usuario;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mia.core.model.administrador.ManagerCurso;
import mia.core.model.entities.Curso;
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
	
	
	
	public ManagerUserCurso() {

	}
	
	
	
	/***
	 * m�todos de  usuario cuestionario
	 */
	public List<UsuarioCurso> findAllUsuarioCursoes() {

		Query q = em.createQuery("SELECT u FROM UsuarioCurso u", UsuarioCurso.class);
		@SuppressWarnings("unchecked")
		List<UsuarioCurso> listaUsuarioCursos = q.getResultList();
		return listaUsuarioCursos;
	}
	
	public UsuarioCurso findUsuarioCursoById(long id_user_curso) {
		UsuarioCurso curso = em.find(UsuarioCurso.class, id_user_curso);
		return curso;
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
	public boolean existeUsuariobyCurso(int curso) {

		String JPQL = "SELECT u FROM UsuarioCurso u WHERE u.curso.idCurso=?1";
		Query query = em.createQuery(JPQL, UsuarioCurso.class);
		query.setParameter(1, curso);
		List<UsuarioCurso> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}

	public void ingresarUsuarioCurso(UsuarioCurso usercurso, long id_user,  Integer id_curso )throws Exception {
		if (usercurso == null) {
			throw new Exception("No ha ingresado datos de usuario y el curso a ingresar");
		}
		boolean existecurso=existeUsuariobyCurso(id_curso);
		if (existecurso) {
			throw new Exception("Ya está inscrito en este curso");
		}
		String modulocurso= ConcatenarModulos(usercurso);
		Curso curso= managerCurso.findCursoById(id_curso);
		Usuario user = managerUsuario.findUsuarioById(id_user);
		
		BigDecimal num= new BigDecimal(0);
		System.out.print(num);
		System.out.print(modulocurso);
		UsuarioCurso nusercurso = new UsuarioCurso();
		nusercurso.setCurso(curso);
		nusercurso.setUsuario(user);
		nusercurso.setModulorealizados(modulocurso);
		nusercurso.setAvance(num);
		em.persist(nusercurso);
	}
	
	public String ConcatenarModulos(UsuarioCurso usuercurse)
	{
		String modulo;
		String usercurso= usuercurse.getModulorealizados();
		
		if(usuercurse.getModulorealizados()==null)
		{
			modulo="0,";	
			return modulo; 
		}else {
			List<String> modulos= new ArrayList<String>();
			 String [] modulosarray=  usercurso.split(",");
			
			int numModulos=modulosarray.length;
		    String insert= String.valueOf(numModulos+1);
		    modulos= Arrays.asList(modulosarray);
			modulos.add(insert);
			modulo= String.valueOf(modulos);
			return modulo;
		}
	}
	

	public void editarCurso(UsuarioCurso usercurseA, int id_curso, long id_user) throws Exception{
		UsuarioCurso usercurseN = findUsuarioCursoById(usercurseA.getIdUsuariocurso());
		if (usercurseN == null) {
			throw new Exception("Error al cargar el Usuario y Curso");
		}
		Curso curso= managerCurso.findCursoById(id_curso);
		Usuario user= managerUsuario.findUsuarioById(id_user);
		usercurseN.setCurso(curso);
		usercurseN.setUsuario(user);
		usercurseN.setAvance(usercurseA.getAvance());
		usercurseN.setModulorealizados(usercurseA.getModulorealizados());
		em.merge(usercurseN);
	}
	

	public void editarAvanceCurso( UsuarioCurso useravance)
	{
		UsuarioCurso usercurseN= findUsuarioCursoById(useravance.getIdUsuariocurso());
		String modulo= ConcatenarModulos(useravance);
		usercurseN.setModulorealizados(modulo);
		usercurseN.setAvance(porcentajeAvance(modulo, usercurseN.getCurso().getIdCurso()));
		em.merge(usercurseN);
	}
	
	
	
	public BigDecimal porcentajeAvance(String modulo, int id_curso)
	{
		String [] modulosarray=  modulo.split(",");
		int avanceModulos=modulosarray.length;
		int nummodulos=  managerCurso.findCursoById(id_curso).getAvance();
		double respuesta=(100*avanceModulos)/nummodulos;
		BigDecimal bi=BigDecimal.valueOf(respuesta).movePointLeft(2);
		return bi;
	}
	
	
	public void eliminarUsuarioCurso(int id_modulo) throws Exception {
		UsuarioCurso moduloN = findUsuarioCursoById(id_modulo);
		if (moduloN == null) {
			throw new Exception("Error al cargar el usuario.");
		}
		
		em.remove(moduloN);
	}
 
	public void ingresoCursoUser (UsuarioCurso ingreso ) throws Exception
	{
		if (ingreso == null) {
			throw new Exception("Usuario Curso no se ha cargado correctamente!.");
		}
		
		UsuarioCurso userCursoN= findUsuarioCursoById(ingreso.getIdUsuariocurso());
		System.out.println(" Ingreso correctamente"+ userCursoN.getUsuario().getApellidos());
	}
	
	
	
		
}
