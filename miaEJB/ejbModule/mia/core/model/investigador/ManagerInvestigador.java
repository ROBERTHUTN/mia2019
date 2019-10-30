package mia.core.model.investigador;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mia.core.model.administrador.ManagerAdministrador;
import mia.core.model.entities.AreaInvestigacion;
import mia.core.model.entities.Etnia;
import mia.core.model.entities.FichaPersonal;
import mia.core.model.entities.Organizacion;
import mia.core.model.entities.OrganizacionFichapersonal;
import mia.core.model.entities.ProyectoInvestigacion;
import mia.core.model.entities.Rol;
import mia.core.model.entities.Usuario;
import mia.core.model.entities.UsuarioInteresArea;
import mia.core.model.entities.UsuarioProyecto;

@Stateless
@LocalBean
public class ManagerInvestigador {
	@PersistenceContext(unitName = "miaDS")
	private EntityManager em;

	@EJB
	private ManagerAdministrador managerAdministrador ;
	
	public ManagerInvestigador() {
	}


	// MÃ©todo que me devuelve la Lista de ROLES
	public List<AreaInvestigacion> findAllAreaInvestigaciones() {

		Query q = em.createQuery("SELECT a FROM AreaInvestigacion a", AreaInvestigacion.class);
		@SuppressWarnings("unchecked")
		List<AreaInvestigacion> listaAreaInvestigaciones = q.getResultList();
		return listaAreaInvestigaciones;
	}



	public AreaInvestigacion findAreaInvestigacionById(int id_area) {
		AreaInvestigacion area = em.find(AreaInvestigacion.class, id_area);
		return area;

	}

	@SuppressWarnings("unchecked")
	public boolean existeNombreAreaInvestigacion(String nombre) {

		String JPQL = "SELECT a FROM AreaInvestigacion a WHERE a.nombreInvestigacion=?1";
		Query query = em.createQuery(JPQL, AreaInvestigacion.class);
		query.setParameter(1, nombre);
		List<AreaInvestigacion> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}

	public void ingresarAreaInvestigacion(AreaInvestigacion areainvestigacion) throws Exception {
		if (areainvestigacion == null) {
			throw new Exception("Ingrese los datos del areainvestigacion");
		}
		boolean existeAreaInvestigacion = existeNombreAreaInvestigacion(areainvestigacion.getNombreInvestigacion());
		if (existeAreaInvestigacion) {
			throw new Exception("El areainvestigacion " + areainvestigacion.getNombreInvestigacion() + " ya existe");
		}
		AreaInvestigacion nareainvestigacion = new AreaInvestigacion();
		nareainvestigacion.setNombreInvestigacion(areainvestigacion.getNombreInvestigacion());
		nareainvestigacion.setDescripcionInvestigacion(areainvestigacion.getDescripcionInvestigacion());
		em.persist(nareainvestigacion);
	}

	public void editarAreaInvestigacion(AreaInvestigacion areainvestigacionA) throws Exception {
		AreaInvestigacion areainvestigacionN = findAreaInvestigacionById(areainvestigacionA.getAreaId());
		if (areainvestigacionN == null) {
			throw new Exception("Error al cargar el areainvestigacion");
		}
		if (!areainvestigacionA.getNombreInvestigacion().equals(areainvestigacionN.getNombreInvestigacion())) {
			boolean existeNombreAreaInvestigacion = existeNombreAreaInvestigacion(areainvestigacionA.getNombreInvestigacion());
			if (existeNombreAreaInvestigacion) {
				throw new Exception("Ya existe el area de investigacion con el nombre " + areainvestigacionA.getNombreInvestigacion());
			}
		}

		areainvestigacionN.setNombreInvestigacion(areainvestigacionA.getNombreInvestigacion());
		areainvestigacionN.setDescripcionInvestigacion(areainvestigacionA.getDescripcionInvestigacion());
		em.merge(areainvestigacionN);
	}
	

	public void eliminarAreaInvestigacion(int id_area) throws Exception {
		AreaInvestigacion areaN = findAreaInvestigacionById(id_area);
		if (areaN == null) {
			throw new Exception("Error al cargar el area");
		}
		boolean existeAreaInvestigacionUsuario = existeAreaInvestigacionenInteres(id_area);
		if (existeAreaInvestigacionUsuario) {
			throw new Exception("El area esta siendo utilizada en areas de interés..");
		}
		boolean existeAreaInvestigacionProyecto = existeAreaInvestigacionenProyecto(id_area);
		if (existeAreaInvestigacionProyecto) {
			throw new Exception("El area esta siendo utilizada en proyectos.");
		}
		
		em.remove(areaN);
	}

	 @SuppressWarnings("unchecked")
	public boolean existeAreaInvestigacionenInteres(int id_area) {
		String JPQL = "SELECT u FROM UsuarioInteresArea u WHERE u.areaInvestigacion.areaId=" + id_area;
		Query query = em.createQuery(JPQL, UsuarioInteresArea.class);
		List<UsuarioInteresArea> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}

	 @SuppressWarnings("unchecked")
	public boolean existeAreaInvestigacionenProyecto(int id_area) {
		String JPQL = "SELECT p FROM ProyectoInvestigacion p WHERE p.areaInvestigacion.areaId=" + id_area;
		Query query = em.createQuery(JPQL, ProyectoInvestigacion.class);
		List<ProyectoInvestigacion> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}


	 
	 /***
	  * métodos del proyecto de investigación
	  */
	 

		// MÃ©todo que me devuelve la Lista de ROLES
		public List<ProyectoInvestigacion> findAllProyectoInvestigaciones() {

			Query q = em.createQuery("SELECT p FROM ProyectoInvestigacion p", ProyectoInvestigacion.class);
			@SuppressWarnings("unchecked")
			List<ProyectoInvestigacion> listaProyectoInvestigaciones = q.getResultList();
			return listaProyectoInvestigaciones;
		}



		public ProyectoInvestigacion findProyectoInvestigacionById(long id_proyinvesti) {
			ProyectoInvestigacion proyinvesti = em.find(ProyectoInvestigacion.class, id_proyinvesti);
			return proyinvesti;

		}
		
		public void ingresarProyectoInvestigacion(ProyectoInvestigacion proyinvesti, int id_area) throws Exception {
			if (proyinvesti == null) {
				throw new Exception("Ingrese los datos del proyinvesti");
			}
			
			boolean existeProyectoNombreInvestigacion = existeNombreProyectoInvestigacion(proyinvesti.getNombreProyecto());
			if (existeProyectoNombreInvestigacion) {
				throw new Exception("El proyinvesti " + proyinvesti.getNombreProyecto() + " ya existe");
			}
			boolean existeProyectoInvestigacion = existeProyectoInvestigacionenUserproyecto(proyinvesti.getIdProyectoInvestigacion());
			if (existeProyectoInvestigacion) {
				throw new Exception("El " + proyinvesti.getNombreProyecto()+ " ya está siendo utilizado");
			}
			
			AreaInvestigacion area= findAreaInvestigacionById(id_area);
			ProyectoInvestigacion nproyinvesti = new ProyectoInvestigacion();
			nproyinvesti.setNombreProyecto(proyinvesti.getNombreProyecto());
			nproyinvesti.setProyectoDescripcion(proyinvesti.getProyectoDescripcion());
			nproyinvesti.setAreaInvestigacion(area);
			nproyinvesti.setEstadoProyecto(proyinvesti.getEstadoProyecto());
			em.persist(nproyinvesti);
		}
			public void editarProyectoInvestigacion(ProyectoInvestigacion proyinvestA, int id_area) throws Exception {
			ProyectoInvestigacion proyinvestN = findProyectoInvestigacionById(proyinvestA.getIdProyectoInvestigacion());
			if (proyinvestN == null) {
				throw new Exception("Error al cargar el proyinvest");
			}
			if (!proyinvestA.getNombreProyecto().equals(proyinvestN.getNombreProyecto())) {
				boolean existeNombreProyectoInvestigacion = existeNombreProyectoInvestigacion(proyinvestA.getNombreProyecto());
				if (existeNombreProyectoInvestigacion) {
					throw new Exception("Ya existe el proyinvest con el nombre " + proyinvestA.getNombreProyecto());
				}
			}
			AreaInvestigacion area= findAreaInvestigacionById(id_area);
			proyinvestN.setNombreProyecto(proyinvestA.getNombreProyecto());
			proyinvestN.setProyectoDescripcion(proyinvestA.getProyectoDescripcion());
			proyinvestN.setEstadoProyecto(proyinvestA.getEstadoProyecto());
			proyinvestN.setAreaInvestigacion(area);
			em.merge(proyinvestN);
		}
		

			


		@SuppressWarnings("unchecked")
		public boolean existeNombreProyectoInvestigacion(String nombre) {

			String JPQL = "SELECT p FROM ProyectoInvestigacion p WHERE p.nombreProyecto=?1";
			Query query = em.createQuery(JPQL, ProyectoInvestigacion.class);
			query.setParameter(1, nombre);
			List<ProyectoInvestigacion> lista;
			lista = query.getResultList();
			if (lista.isEmpty()) {
				return false;
			} else
				return true;
		}

		public void eliminarProyectoInvestigacion(int id_proyinvesti) throws Exception {
			ProyectoInvestigacion proyinvestiN = findProyectoInvestigacionById(id_proyinvesti);
			if (proyinvestiN == null) {
				throw new Exception("Error al cargar el proyinvesti");
			}
			boolean existeProyectoInvestigacionUsuario = existeProyectoInvestigacionenUserproyecto(id_proyinvesti);
			if (existeProyectoInvestigacionUsuario) {
				throw new Exception("El proyinvesti está siendo utilizada por usuarios en proyectos");
			}
			em.remove(proyinvestiN);
		}

		 @SuppressWarnings("unchecked")
		public boolean existeProyectoInvestigacionenUserproyecto(long id_proyinvesti) {
			String JPQL = "SELECT u FROM UsuarioProyecto u WHERE u.proyectoInvestigacion.idProyectoInvestigacion=" + id_proyinvesti;
			Query query = em.createQuery(JPQL, UsuarioProyecto.class);
			List<UsuarioProyecto> lista;
			lista = query.getResultList();
			if (lista.isEmpty()) {
				return false;
			} else
				return true;
		}

	 

			// Métodos de Organización ficha personal
			public List<OrganizacionFichapersonal> findAllOrganizacionFichapersonales() {

				Query q = em.createQuery("SELECT o FROM OrganizacionFichapersonal o", OrganizacionFichapersonal.class);
				@SuppressWarnings("unchecked")
				List<OrganizacionFichapersonal> listaOrganizacionFichapersonales = q.getResultList();
				return listaOrganizacionFichapersonales;
			}



			public OrganizacionFichapersonal findOrganizacionFichapersonalById(long id_organizacionfichapersonal) {
				OrganizacionFichapersonal organizacionfichapersonal = em.find(OrganizacionFichapersonal.class, id_organizacionfichapersonal);
				return organizacionfichapersonal;

			}
			
		
			public Organizacion findOrganizacionFichaByIdOrganizacion(int id_organizacion) {
				Organizacion organizacionficha = em.find(Organizacion.class, id_organizacion);
				return organizacionficha;

			}
			
		public boolean existeOrganizacionFicha(int id_organizacion,long id_ficha) {
			Query q = em.createQuery("SELECT o FROM OrganizacionFichapersonal o where o.organizacion.idOrganizacion="
			+id_organizacion+" and o.fichaPersonal.idFicha="+id_ficha, OrganizacionFichapersonal.class);
			@SuppressWarnings("unchecked")
			List<OrganizacionFichapersonal> listaOrganizacionFichapersonales = q.getResultList();
		if (listaOrganizacionFichapersonales.isEmpty())
			return false;
		else
			return true;
			
		}
			
			public void ingresarOrganizacionFichapersonal( long id_ficha_fk, int id_organizacion) throws Exception {
				if (id_ficha_fk == 0 || id_organizacion==0) {
					throw new Exception("Ingrese los datos del organizacion y la ficha personal.");
				}
				boolean existeOrganiFicha=existeOrganizacionFicha(id_organizacion, id_ficha_fk);
				if (existeOrganiFicha) {
					throw new Exception("Ya se encuentra la ficha registrada con esa organizacion");
				}
				
				OrganizacionFichapersonal norganizacionfichapersonal = new OrganizacionFichapersonal();
				FichaPersonal ficha= managerAdministrador.findFichaPersonalById(id_ficha_fk);
				Organizacion organizacion= managerAdministrador.findOrganizacionById(id_organizacion);
				norganizacionfichapersonal.setFichaPersonal(ficha);
				norganizacionfichapersonal.setOrganizacion(organizacion);
				em.persist(norganizacionfichapersonal);
			}

			public void editarOrganizacionFichapersonal(OrganizacionFichapersonal organizacionfichapersonalA , long id_ficha_fk, int id_organizacion) throws Exception {
				OrganizacionFichapersonal organizacionfichapersonalN = findOrganizacionFichapersonalById(organizacionfichapersonalA.getIdOrganizacionFicha());
				if (id_ficha_fk == 0 || id_organizacion==0) {
					throw new Exception("Ingrese los datos del organizacionfichapersonal");
				}
				
				FichaPersonal ficha= managerAdministrador.findFichaPersonalById(id_ficha_fk);
				Organizacion organizacion= managerAdministrador.findOrganizacionById(id_organizacion);
				organizacionfichapersonalN.setFichaPersonal(ficha);
				organizacionfichapersonalN.setOrganizacion(organizacion);

				em.merge(organizacionfichapersonalN);
			}
			
			public void eliminarOrganizacionFichapersonal(int id_organizacionfichapersonal) throws Exception {
				OrganizacionFichapersonal organizacionfichapersonalN = findOrganizacionFichapersonalById(id_organizacionfichapersonal);
				if (organizacionfichapersonalN == null) {
					throw new Exception("Error al cargar el organizacionfichapersonal");
				}
				
				em.remove(organizacionfichapersonalN);
			}
			


			
			
			
			
/**sin utilizar
			 @SuppressWarnings("unchecked")
			public boolean existeOrganizacionFichapersonalenUsuario(long id_organizacionfichapersonal) {
				String JPQL = "SELECT u FROM Usuario u WHERE u.organizacionfichapersonal.idOrganizacionFichapersonal=" + id_organizacionfichapersonal;
				Query query = em.createQuery(JPQL, Usuario.class);
				List<Usuario> lista;
				lista = query.getResultList();
				if (lista.isEmpty()) {
					return false;
				} else
					return true;
			}
		@SuppressWarnings("unchecked")
			public boolean existeNombreOrganizacionFichapersonal(String nombre) {

				String JPQL = "SELECT r FROM OrganizacionFichapersonal r WHERE r.nombreOrganizacionFichapersonal=?1";
				Query query = em.createQuery(JPQL, OrganizacionFichapersonal.class);
				query.setParameter(1, nombre);
				List<OrganizacionFichapersonal> lista;
				lista = query.getResultList();
				if (lista.isEmpty()) {
					return false;
				} else
					return true;
			}
	 */
		 
		 
			public List<UsuarioInteresArea> findAllUsuarioInteresAreaes() {

				Query q = em.createQuery("SELECT o FROM UsuarioInteresArea o", UsuarioInteresArea.class);
				@SuppressWarnings("unchecked")
				List<UsuarioInteresArea> listaUsuarioInteresAreaes = q.getResultList();
				return listaUsuarioInteresAreaes;
			}



			public UsuarioInteresArea findUsuarioInteresAreaById(long id_organizacionfichapersonal) {
				UsuarioInteresArea organizacionfichapersonal = em.find(UsuarioInteresArea.class, id_organizacionfichapersonal);
				return organizacionfichapersonal;

			}
			
		/**
			public Organizacion findOrganizacionFichaByIdOrganizacion(int id_organizacion) {
				Organizacion organizacionficha = em.find(Organizacion.class, id_organizacion);
				return organizacionficha;

			}
			
		
			**/
		
			public boolean existeAreaInteres(int id_area,long id_ficha) {
				Query q = em.createQuery("SELECT u FROM UsuarioInteresArea u where u.areaInvestigacion.areaId="
				+id_area+" and u.fichaPersonal.idFicha="+id_ficha, UsuarioInteresArea.class);
				@SuppressWarnings("unchecked")
				List<UsuarioInteresArea> listaUsuarioInteresAreaes = q.getResultList();
			if (listaUsuarioInteresAreaes.isEmpty())
				return false;
			else
				return true;
				
			}
			
			public void ingresarUsuarioInteresArea( long id_ficha_fk, int id_area) throws Exception {
				if (id_ficha_fk == 0 || id_area==0) {
					throw new Exception("Ingrese los datos del area de interes del usuario");
				}
				boolean existeAreaInteresFicha=existeOrganizacionFicha(id_area, id_ficha_fk);
				if (existeAreaInteresFicha) {
					throw new Exception("Ya se encuentra el área con el usuario");
				}
				
				UsuarioInteresArea nuserinteresarea = new UsuarioInteresArea();
				FichaPersonal ficha= managerAdministrador.findFichaPersonalById(id_ficha_fk);
				AreaInvestigacion area= findAreaInvestigacionById(id_area);
				nuserinteresarea.setFichaPersonal(ficha);
				nuserinteresarea.setAreaInvestigacion(area);
				em.persist(nuserinteresarea);
			}

			public void editarUsuarioInteresArea(UsuarioInteresArea userinteresareaA , long id_ficha_fk, int id_area) throws Exception {
				UsuarioInteresArea userinteresareaN = findUsuarioInteresAreaById(userinteresareaA.getIdInteresArea());
				if (id_ficha_fk == 0 || id_area==0) {
					throw new Exception("Ingrese los datos del interés del área de investigación.");
				}
				
				FichaPersonal ficha= managerAdministrador.findFichaPersonalById(id_ficha_fk);
				AreaInvestigacion area =findAreaInvestigacionById(id_area);
				userinteresareaN.setFichaPersonal(ficha);
				userinteresareaN.setAreaInvestigacion(area);

				em.merge(userinteresareaN);
			}
			
			public void eliminarUsuarioInteresArea(int id_userinteresarea) throws Exception {
				UsuarioInteresArea userinteresareaN = findUsuarioInteresAreaById(id_userinteresarea);
				if (userinteresareaN == null) {
					throw new Exception("Error al cargar el organizacionfichapersonal");
				}
				
				em.remove(userinteresareaN);
			}
			


			
			
	 
	 
}
