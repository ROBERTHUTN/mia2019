package mia.core.model.administrador;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mia.core.model.cuestionario.dto.PreguntaDimensionDTO;
import mia.core.model.entities.AreaInvestigacion;
import mia.core.model.entities.Dimension;
import mia.core.model.entities.Etnia;
import mia.core.model.entities.FichaPersonal;
import mia.core.model.entities.GradoEstudio;
import mia.core.model.entities.Modulo;
import mia.core.model.entities.Organizacion;
import mia.core.model.entities.OrganizacionFichapersonal;
import mia.core.model.entities.PaisEstado;
import mia.core.model.entities.ProyectoInvestigacion;
import mia.core.model.entities.Religion;
import mia.core.model.entities.Reporte;
import mia.core.model.entities.Respuesta;
import mia.core.model.entities.Rol;
import mia.core.model.entities.Usuario;
import mia.core.model.entities.UsuarioProyecto;
import mia.core.model.investigador.ManagerInvestigador;
import mia.core.model.usuario.ManagerUsuario;

@Stateless
@LocalBean
public class ManagerAdministrador {
	@PersistenceContext(unitName = "miaDS")
	private EntityManager em;
	
	@EJB
	private ManagerUsuario managerUsuario;
	
	@EJB
	private ManagerInvestigador managerInvestigador;
 
	public ManagerAdministrador() {
	}

	// MÃ©todo que me devuelve la Lista de ROLES
	public List<Rol> findAllRoles() {

		Query q = em.createQuery("SELECT r FROM Rol r", Rol.class);
		@SuppressWarnings("unchecked")
		List<Rol> listaRoles = q.getResultList();
		return listaRoles;
	}

// MÃ©todo que me devuelve la Lista de ROLES
	public List<Etnia> findAllEtnia() {

		Query q = em.createQuery("SELECT e FROM Etnia e", Etnia.class);
		@SuppressWarnings("unchecked")
		List<Etnia> listaEtnia = q.getResultList();
		return listaEtnia;
	}

	// MÃ©todo que me devuelve la Lista de PAISES_ESTADO
	public List<PaisEstado> findOnlyPais() {

		Query q = em.createQuery("SELECT p FROM PaisEstado p where p.paisEstado=null", PaisEstado.class);
		@SuppressWarnings("unchecked")
		List<PaisEstado> listaPaisEstado = q.getResultList();
		return listaPaisEstado;
	}

	public List<PaisEstado> findPaisEstado(int pasiest) {

		String JPQL = "SELECT p FROM PaisEstado p WHERE p.paisEstado.idPaisEstado=" + pasiest;
		Query query = em.createQuery(JPQL, PaisEstado.class);
		@SuppressWarnings("unchecked")
		List<PaisEstado> lista = query.getResultList();
		return lista;
	}

// MÃ©todo que me devuelve la Lista de ROLES
	public List<Religion> findAllReligion() {

		Query q = em.createQuery("SELECT r FROM Religion r", Religion.class);
		@SuppressWarnings("unchecked")
		List<Religion> listaReligion = q.getResultList();
		return listaReligion;
	}

	public List<Usuario> findAllUsuario() {

		Query q = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
		@SuppressWarnings("unchecked")
		List<Usuario> listaUsuario = q.getResultList();
		return listaUsuario;
	}
	
	public List<FichaPersonal> findAllFichaPersonal() {

		Query q = em.createQuery("SELECT f FROM FichaPersonal f", FichaPersonal.class);
		@SuppressWarnings("unchecked")
		List<FichaPersonal> listaFichaPersonal = q.getResultList();
		return listaFichaPersonal;
	}
	
	public List<FichaPersonal> findAllFichaPersonalByRolUsuario() {

		Query q = em.createQuery("SELECT DISTINCT f FROM FichaPersonal f "
				+ " where f.usuario.rol.idRol=?1", FichaPersonal.class);
		q.setParameter(1, 5);
		@SuppressWarnings("unchecked")
		List<FichaPersonal> listaFichaPersonal = q.getResultList();
		return listaFichaPersonal;
	}
	
	public List<FichaPersonal> findAllFichaPersonalVoluntariado() {

		Query q = em.createQuery("SELECT f FROM FichaPersonal f where voluntariado='true' ", FichaPersonal.class);
		@SuppressWarnings("unchecked")
		List<FichaPersonal> listaFichaPersonal = q.getResultList();
		return listaFichaPersonal;
	}

	public List<FichaPersonal> findAllFichaPersonalByRol() {

		Query q = em.createQuery("SELECT f FROM FichaPersonal f WHERE f.usuario.rol. ", FichaPersonal.class);
		@SuppressWarnings("unchecked")
		List<FichaPersonal> listaFichaPersonal = q.getResultList();
		return listaFichaPersonal;
	}

	
	public Rol findRolById(int id_rol) {
		Rol rol = em.find(Rol.class, id_rol);
		return rol;

	}

	public PaisEstado findPaisEstadoById(int id_pais_estado) {
		PaisEstado paisEstado = em.find(PaisEstado.class, id_pais_estado);
		return paisEstado;

	}

	public Etnia findEtniaById(int id_etnia) {
		Etnia etnia = em.find(Etnia.class, id_etnia);
		return etnia;
	}

	public Religion findReligionById(int id_religion) {
		Religion reli = em.find(Religion.class, id_religion);
		return reli;
	}

	
	public Usuario findUsuarioById(long id_usuario) {
		Usuario usuario = em.find(Usuario.class, id_usuario);
		return usuario;

	}

	public FichaPersonal findFichaPersonalById(long id_ficha) {
		FichaPersonal ficha = em.find(FichaPersonal.class, id_ficha);
		return ficha;
	}


	//
	@SuppressWarnings("unchecked")
	public boolean existeCorreoUsuario(String correo) {
		String JPQL = "SELECT u FROM Usuario u WHERE u.correo=?1";
		Query query = em.createQuery(JPQL, Usuario.class);
		query.setParameter(1, correo);
		List<Usuario> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}

	@SuppressWarnings("unchecked")
	public boolean existeNombreRol(String nombre) {

		String JPQL = "SELECT r FROM Rol r WHERE r.nombreRol=?1";
		Query query = em.createQuery(JPQL, Rol.class);
		query.setParameter(1, nombre);
		List<Rol> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}
	
	@SuppressWarnings("unchecked")
	public boolean existeCIUsuario(String nombre) {

		String JPQL = "SELECT u FROM Usuario u WHERE u.ci=?1";
		Query query = em.createQuery(JPQL, Usuario.class);
		query.setParameter(1, nombre);
		List<Usuario> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}

	@SuppressWarnings("unchecked")
	public boolean existeNombreReligion(String descripcion) {

		String JPQL = "SELECT r FROM Religion r WHERE r.descripcion=?1";
		Query query = em.createQuery(JPQL, Religion.class);
		query.setParameter(1, descripcion);
		List<Religion> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}

	@SuppressWarnings("unchecked")
	public boolean existeNombreEtnia(String descripcion) {

		String JPQL = "SELECT e FROM Etnia e WHERE e.descripcion=?1";
		Query query = em.createQuery(JPQL, Etnia.class);
		query.setParameter(1, descripcion);
		List<Etnia> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}

	@SuppressWarnings("unchecked")
	public boolean existeCedulaUsuario(String cedula) {
		String JPQL = "SELECT u FROM Usuario u WHERE u.ci=?1";
		Query query = em.createQuery(JPQL, Usuario.class);
		query.setParameter(1, cedula);
		List<Usuario> lista;

		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}

	@SuppressWarnings("unchecked")
	public boolean existeRol(String rol) {
		String JPQL = "SELECT r FROM Rol r WHERE r.nombreRol=?1";
		Query query = em.createQuery(JPQL, Rol.class);
		query.setParameter(1, rol);
		List<Rol> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}

	@SuppressWarnings("unchecked")
	public boolean existeReligion(String religion) {
		String JPQL = "SELECT r FROM Religion r WHERE r.descripcion=?1";
		Query query = em.createQuery(JPQL, Religion.class);
		query.setParameter(1, religion);
		List<Religion> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}

	@SuppressWarnings("unchecked")
	public boolean existeEtnia(String etnia) {
		String JPQL = "SELECT e FROM Etnia e WHERE e.descripcion=?1";
		Query query = em.createQuery(JPQL, Etnia.class);
		query.setParameter(1, etnia);
		List<Etnia> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}

	@SuppressWarnings("unchecked")
	public boolean existePais(String paisestado) {
		String JPQL = "SELECT p FROM PaisEstado p WHERE p.nombre=?1";
		Query query = em.createQuery(JPQL, PaisEstado.class);
		query.setParameter(1, paisestado);
		List<PaisEstado> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}

//MÃ©todo que ingresa al usuario
	public void ingresarUsuario(Usuario usuario, int id_rol) throws Exception {
		if (usuario == null) {
			throw new Exception("No ha ingresado datos en la ficha");
		}

		if (id_rol == 0) {
			throw new Exception("Error al seleccionar el rol");
		}
		boolean correoE = existeCorreoUsuario(usuario.getCorreo());
		if (correoE) {

			throw new Exception("Error ya existe un usario registrado con ese correo electrónico");
		}

		boolean ceula = existeCedulaUsuario(usuario.getCi());
		if (ceula) {
			throw new Exception("Error ya existe un usario registrado con ese número de cédula");
		}

		Rol rol = findRolById(id_rol);
		Usuario usuarioNuevo = new Usuario();
		usuarioNuevo.setNombres(usuario.getNombres());
		usuarioNuevo.setApellidos(usuario.getApellidos());
		usuarioNuevo.setPassword(usuario.getPassword());
		usuarioNuevo.setRol(rol);
		usuarioNuevo.setCi(usuario.getCi());
		usuarioNuevo.setCorreo(usuario.getCorreo());
		usuarioNuevo.setActivo(usuario.getActivo());
		em.persist(usuarioNuevo);
		return;
	}

	public void ingresarPais(PaisEstado paisEstado, int id_padre_pais) throws Exception {
		if (paisEstado == null) {
			throw new Exception("No ha ingresado datos en el País");
		}

		if (id_padre_pais == 0) {
			throw new Exception("Error al seleccionar el pais");
		}
		boolean paisE = existePais(paisEstado.getNombre());
		if (paisE) {

			throw new Exception("Error ya existe un País o estado registrado con ese nombre");
		}

		PaisEstado paisEstad = new PaisEstado();
		paisEstad.setNombre(paisEstad.getNombre());
		paisEstad.setPaisEstado(paisEstado.getPaisEstado());
		em.persist(paisEstad);
		return;
	}

//MÃ©todo que me ingresa la ficha personal
	public void ingresarFichaPersonal(FichaPersonal fichaPersonal, int id_religion, int id_etnia, int id_pais,
			int estado, long id_usuario, int id_grado) throws Exception {
		if (id_usuario == 0) {
			throw new Exception("Error al cargar el usuario");
		}
		if (fichaPersonal == null) {
			throw new Exception("No ha ingresado datos en la ficha");
		}
		if (id_religion == 0) {
			throw new Exception("Error al seleccionar la religión");
		}
		if (id_etnia == 0) {
			throw new Exception("Error al seleccionar la etnia");
		}
		if (id_pais == 0) {
			throw new Exception("Error al seleccionar el país");
		}
		if (estado == 0) {
			throw new Exception("Error al seleccionar el estado");
		}
		if (id_grado == 0) {
			throw new Exception("Error al seleccionar el grado");
		}
		
		PaisEstado paisEst = findPaisEstadoById(id_pais);
		PaisEstado esta = findPaisEstadoById(estado);
		Usuario usuario = findUsuarioById(id_usuario);
		Etnia etnia = findEtniaById(id_etnia);
		Religion reli = findReligionById(id_religion);
		GradoEstudio grado= managerUsuario.findGradoById(id_grado);
		FichaPersonal fichaNueva = new FichaPersonal();
		fichaNueva.setPaisEstado1(esta);
		fichaNueva.setPaisEstado2(paisEst);
		fichaNueva.setCelular(fichaPersonal.getCelular());
		fichaNueva.setCiudad(fichaPersonal.getCiudad());
		fichaNueva.setDireccion(fichaPersonal.getDireccion());
		fichaNueva.setEdad(fichaPersonal.getEdad());
		fichaNueva.setEstadoCivil(fichaPersonal.getEstadoCivil());
		fichaNueva.setEstadoCivil(fichaPersonal.getEstadoCivil());
		fichaNueva.setFechaInscripcion(fichaPersonal.getFechaInscripcion());
		fichaNueva.setFechaNacimiento(fichaPersonal.getFechaNacimiento());
		fichaNueva.setGenero(fichaPersonal.getGenero());
		fichaNueva.setGradoEstudio(grado);
		fichaNueva.setNumeroHijos(fichaPersonal.getNumeroHijos());
		fichaNueva.setReligion(fichaPersonal.getReligion());
		fichaNueva.setTelefono(fichaPersonal.getTelefono());
		fichaNueva.setTrabajo(fichaPersonal.getTrabajo());
		fichaNueva.setVoluntariado(fichaPersonal.getVoluntariado());
		fichaNueva.setEtnia(etnia);
		fichaNueva.setReligion(reli);
		fichaNueva.setUsuario(usuario);
		em.persist(fichaNueva);
	}

	
	public Timestamp fechaActual() {
		Date today1 = new Date();
		Timestamp hoy = new Timestamp(today1.getTime());
		return hoy;
	}

	public void ingresarRol(Rol rol) throws Exception {
		if (rol == null) {
			throw new Exception("Ingrese los datos del rol");
		}
		boolean existeRol = existeRol(rol.getNombreRol());
		if (existeRol) {
			throw new Exception("El rol " + rol.getNombreRol() + " ya existe");
		}
		Rol nrol = new Rol();
		nrol.setActivoRol(rol.getActivoRol());
		nrol.setNombreRol(rol.getNombreRol());
		em.persist(nrol);
	}

	public void ingresarReligion(Religion reli) throws Exception {
		if (reli == null) {
			throw new Exception("Ingrese los datos de la religiÃ³n");
		}
		boolean existeReligion = existeReligion(reli.getDescripcion());
		if (existeReligion) {
			throw new Exception("La religiÃ³n  " + reli.getDescripcion() + " ya existe");
		}
		Religion nreli = new Religion();
		nreli.setDescripcion(reli.getDescripcion());

		em.persist(nreli);
	}

	public void ingresarEtnia(Etnia etnia) throws Exception {
		if (etnia == null) {
			throw new Exception("Ingrese los datos de la EtnÃ­a");
		}
		boolean existeEtnia = existeEtnia(etnia.getDescripcion());
		if (existeEtnia) {
			throw new Exception("La EtnÃ­a  " + etnia.getDescripcion() + " ya existe");
		}
		Etnia netni = new Etnia();
		netni.setDescripcion(etnia.getDescripcion());

		em.persist(netni);
	}

	public void editarRol(Rol rolA) throws Exception {
		Rol rolN = findRolById(rolA.getIdRol());
		if (rolN == null) {
			throw new Exception("Error al cargar el rol");
		}
		if (!rolA.getNombreRol().equals(rolN.getNombreRol())) {
			boolean existeNombreRol = existeNombreRol(rolA.getNombreRol());
			if (existeNombreRol) {
				throw new Exception("Ya existe el rol con el nombre " + rolA.getNombreRol());
			}
		}

		rolN.setActivoRol(rolA.getActivoRol());
		rolN.setNombreRol(rolA.getNombreRol());
		em.merge(rolN);
	}
	
	
	

	public void editarUsuario(Usuario usuarioA, int id_rol) throws Exception {
		Usuario userN = findUsuarioById(usuarioA.getIdUsuario());
		
		if (userN == null) {
			throw new Exception("Error al cargar el usuario");
			
		}
		if(!userN.getCi().equals(usuarioA.getCi())|| !userN.getCorreo().equals(usuarioA.getCorreo())) {
			
		
		boolean correoRepetido, cedulaRepetida;
		cedulaRepetida=existeCedulaUsuario(usuarioA.getCi());
		correoRepetido=existeCorreoUsuario(usuarioA.getCorreo());
		if (cedulaRepetida) {
			throw new Exception("La cédula ya está registrada con otro usuario");	
		}
		if (correoRepetido) {
			throw new Exception("El correo ya se encuentra registrado con otro usuario");
		} 
		
		}
		
		
		
		Rol rol= findRolById(id_rol);
		
		userN.setNombres(usuarioA.getNombres());
		userN.setApellidos(usuarioA.getApellidos());
		userN.setRol(rol);
		userN.setCi(usuarioA.getCi());
		userN.setCorreo(usuarioA.getCorreo());
		userN.setActivo(usuarioA.getActivo());
		em.merge(userN);
	}

	public void editarUsuario(Usuario usuarioA) throws Exception {
		Usuario userN = findUsuarioById(usuarioA.getIdUsuario());
		
		if (userN == null) {
			throw new Exception("Error al cargar el usuario");
			
		}
		if(!userN.getCi().equals(usuarioA.getCi())|| !userN.getCorreo().equals(usuarioA.getCorreo())) {
			
		
		boolean correoRepetido, cedulaRepetida;
		cedulaRepetida=existeCedulaUsuario(usuarioA.getCi());
		correoRepetido=existeCorreoUsuario(usuarioA.getCorreo());
		if (cedulaRepetida) {
			throw new Exception("La cédula ya está registrada con otro usuario");	
		}
		if (correoRepetido) {
			throw new Exception("El correo ya se encuentra registrado con otro usuario");
		} 
		
		}

		
		
		userN.setNombres(usuarioA.getNombres());
		userN.setApellidos(usuarioA.getApellidos());
		userN.setRol(usuarioA.getRol());
		userN.setCi(usuarioA.getCi());
		userN.setCorreo(usuarioA.getCorreo());
		userN.setActivo(usuarioA.getActivo());
		em.merge(userN);
	}

		
	public void editarReligion(Religion relA) throws Exception {
		Religion relN = findReligionById(relA.getIdReligion());
		if (relN == null) {
			throw new Exception("Error al cargar la religión");
		}
		if (!relA.getDescripcion().equals(relN.getDescripcion())) {
			boolean existeNombreReligion = existeNombreReligion(relA.getDescripcion());
			if (existeNombreReligion) {
				throw new Exception("Ya existe la religiÃ³n con el nombre " + relA.getDescripcion());
			}
		}

		relN.setDescripcion(relA.getDescripcion());
		em.merge(relN);
	}

	public void editarEtnia(Etnia etnA) throws Exception {
		Etnia etnN = findEtniaById(etnA.getIdEtnia());
		if (etnN == null) {
			throw new Exception("Error al cargar la etnia");
		}
		if (!etnA.getDescripcion().equals(etnN.getDescripcion())) {
			boolean existeNombreEtnia = existeNombreEtnia(etnA.getDescripcion());
			if (existeNombreEtnia) {
				throw new Exception("Ya existe la etnia con el nombre " + etnA.getDescripcion());
			}
		}

		etnN.setDescripcion(etnA.getDescripcion());
		em.merge(etnN);
	}

	public void eliminarEtnia(int id_etnia) throws Exception {
		Etnia etnN = findEtniaById(id_etnia);
		if (etnN == null) {
			throw new Exception("Error al cargar la etnia");
		}
		boolean existeEtniaFicha = existeEtniaenFichaRegistro(id_etnia);
		if (existeEtniaFicha) {
			throw new Exception("La etnia esa siendo utilizada en la tabla ficha de registro");
		}
		em.remove(etnN);
	}

	public void eliminarRol(int id_rol) throws Exception {
		Rol rolN = findRolById(id_rol);
		if (rolN == null) {
			throw new Exception("Error al cargar el rol");
		}
		boolean existeRolUsuario = existeRolenUsuario(id_rol);
		if (existeRolUsuario) {
			throw new Exception("El rol está siendo utilizada en la tabla usuario");
		}
		em.remove(rolN);
	}

	public void eliminarUsuario(long id_usuario) throws Exception {
		Usuario usuarioN = findUsuarioById(id_usuario);
		if (usuarioN == null) {
			throw new Exception("Error al cargar el usuario");
		}
		boolean existeUsuarioRespuesta = existeUsuarioRespuesta(id_usuario);
		if (existeUsuarioRespuesta) {
			throw new Exception("El usuario está siendo utilizado en la tabla de Respuestas no puede ser eliminado!");
		}
		boolean existeUsuarioReporte = existeUsuarioReporte(id_usuario);
		if (existeUsuarioReporte) {
			throw new Exception("El usuario está siendo utilizado en la tabla de Reporte no puede ser eliminado!");
		}
		boolean existeUsuarioFichaPersonal = existeUsuarioFichaPersonal(id_usuario);
		if (existeUsuarioFichaPersonal) {
			throw new Exception(
					"El usuario está siendo utilizado en la tabla de FichaPersonal no puede ser eliminado!");
		}
		em.remove(usuarioN);
	}

	public void eliminarReligion(int id_religion) throws Exception {
		Religion reliN = findReligionById(id_religion);
		if (reliN == null) {
			throw new Exception("Error al cargar la religiÃ³n");
		}
		boolean existeReligionFicha = existeReligionenFichaRegistro(id_religion);
		if (existeReligionFicha) {
			throw new Exception("La religiÃ³n estÃ¡ siendo utilizada en la tabla ficha de registro");
		}
		em.remove(reliN);
	}

	@SuppressWarnings("unchecked")
	public boolean existeRolenUsuario(long id_rol) {
		String JPQL = "SELECT u FROM Usuario u WHERE u.rol.idRol=" + id_rol;
		Query query = em.createQuery(JPQL, Usuario.class);
		List<Usuario> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}

	@SuppressWarnings("unchecked")
	public boolean existeUsuarioReporte(long id_usuario) {
		String JPQL = "SELECT r FROM Reporte r WHERE r.usuario.idUsuario=" + id_usuario;
		Query query = em.createQuery(JPQL, Reporte.class);
		List<Reporte> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}

	@SuppressWarnings("unchecked")
	public boolean existeUsuarioRespuesta(long id_usuario) {
		String JPQL = "SELECT r FROM Respuesta r WHERE r.usuario.idUsuario=" + id_usuario;
		Query query = em.createQuery(JPQL, Respuesta.class);
		List<Respuesta> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}

	@SuppressWarnings("unchecked")
	public boolean existeUsuarioFichaPersonal(long id_usuario) {
		String JPQL = "SELECT r FROM FichaPersonal r WHERE r.usuario.idUsuario=" + id_usuario;
		Query query = em.createQuery(JPQL, FichaPersonal.class);
		List<FichaPersonal> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}

	@SuppressWarnings("unchecked")
	public boolean existeReligionenFichaRegistro(int id_religion) {
		String JPQL = "SELECT f FROM FichaPersonal f WHERE f.religion.idReligion=" + id_religion;
		Query query = em.createQuery(JPQL, FichaPersonal.class);
		List<FichaPersonal> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}

	@SuppressWarnings("unchecked")
	public boolean existeEtniaenFichaRegistro(int id_etnia) {
		String JPQL = "SELECT f FROM FichaPersonal f WHERE f.etnia.idEtnia=" + id_etnia;
		Query query = em.createQuery(JPQL, FichaPersonal.class);
		List<FichaPersonal> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}
	

	// Método que devuelve la Organización
	public List<Organizacion> findAllOrganizaciones() {

		Query q = em.createQuery("SELECT o FROM Organizacion o", Organizacion.class);
		@SuppressWarnings("unchecked")
		List<Organizacion> listaOrganizaciones = q.getResultList();
		return listaOrganizaciones;
	}



	public Organizacion findOrganizacionById(int id_organizacion) {
		Organizacion organizacion = em.find(Organizacion.class, id_organizacion);
		return organizacion;

	}
	
	
	public void ingresarOrganizacion(Organizacion organizacion) throws Exception {
		System.out.println("si entra");
		if (organizacion == null) {
			throw new Exception("Ingrese los datos del organizacion");
		}
		boolean existeOrganizacion = existeNombreOrganizacion(organizacion.getNombreOrganizacion());
		if (existeOrganizacion) {
			throw new Exception("El organizacion " + organizacion.getNombreOrganizacion() + " ya existe");
		}
		System.out.println("esto es una prueba"+ organizacion.getNombreOrganizacion());

		Organizacion norganizacion = new Organizacion();
		norganizacion.setNombreOrganizacion(organizacion.getNombreOrganizacion());
		norganizacion.setDescripcionOrganizacion(organizacion.getDescripcionOrganizacion());
		norganizacion.setTelefonoOrganizacion(organizacion.getTelefonoOrganizacion());
		em.persist(norganizacion);
	}

	public void editarOrganizacion(Organizacion organizacionA) throws Exception {
		Organizacion organizacionN = findOrganizacionById(organizacionA.getIdOrganizacion());
		if (organizacionN == null) {
			throw new Exception("Error al cargar el organizacion");
		}
		if (!organizacionA.getNombreOrganizacion().equals(organizacionN.getNombreOrganizacion())) {
			boolean existeNombreOrganizacion = existeNombreOrganizacion(organizacionA.getNombreOrganizacion());
			if (existeNombreOrganizacion) {
				throw new Exception("Ya existe el organizacion con el nombre " + organizacionA.getNombreOrganizacion());
			}
		}
		organizacionN.setNombreOrganizacion(organizacionA.getNombreOrganizacion());
		organizacionN.setDescripcionOrganizacion(organizacionA.getDescripcionOrganizacion());
		organizacionN.setTelefonoOrganizacion(organizacionA.getTelefonoOrganizacion());

		em.merge(organizacionN);
	}
	
	
	


	public void eliminarOrganizacion(int id_organizacion) throws Exception {
		Organizacion organizacionN = findOrganizacionById(id_organizacion);
		if (organizacionN == null) {
			throw new Exception("Error al cargar el organizacion");
		}
		boolean existeOrganizacionenFicha = existeOrganizacionenFicha(organizacionN.getIdOrganizacion());
		if (existeOrganizacionenFicha) {
			throw new Exception("El organizacion " + organizacionN.getNombreOrganizacion() + " esta siendo utilizada por un usuario.");
		}
		em.remove(organizacionN);
	}

	 @SuppressWarnings("unchecked")
	public boolean existeOrganizacionenFicha(int id_organizacion) {
		String JPQL = "SELECT o FROM OrganizacionFichapersonal o WHERE o.organizacion.idOrganizacion=" + id_organizacion;
		Query query = em.createQuery(JPQL, OrganizacionFichapersonal.class);
		List<OrganizacionFichapersonal> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}
	 
		@SuppressWarnings("unchecked")
		public boolean existeNombreOrganizacion(String nombre) {

			String JPQL = "SELECT o FROM Organizacion o WHERE o.nombreOrganizacion=?1";
			Query query = em.createQuery(JPQL, Organizacion.class);
			query.setParameter(1, nombre);
			List<Organizacion> lista;
			lista = query.getResultList();
			if (lista.isEmpty()) {
				return false;
			} else
				return true;
		}

	
	public Date fechadeNacimiento()
	{
		Date t = new Date();
		Date m;
		m= new Date(t.getTime() - (365 * 24 * 60 * 1000));
		return m;
		
	}
		
		
	/**
	 * Métodos de usuarioproyecto
	 * Donde se registran los proyectos y ficha del usuario encargado al lugar que pertenecen.
	 */
	
	public List<UsuarioProyecto> findAllUsuarioProyectoes() {

		Query q = em.createQuery("SELECT o FROM UsuarioProyecto o", UsuarioProyecto.class);
		@SuppressWarnings("unchecked")
		List<UsuarioProyecto> listaUsuarioProyectoes = q.getResultList();
		return listaUsuarioProyectoes;
	}

	public UsuarioProyecto findUsuarioProyectoById(long id_organizacionfichapersonal) {
		UsuarioProyecto organizacionfichapersonal = em.find(UsuarioProyecto.class, id_organizacionfichapersonal);
		return organizacionfichapersonal;

	}
	
/**
	public Organizacion findOrganizacionFichaByIdOrganizacion(int id_organizacion) {
		Organizacion organizacionficha = em.find(Organizacion.class, id_organizacion);
		return organizacionficha;

	}
	

	**/
/**
	public boolean existeAreaInteres(int id_area,long id_ficha) {
		Query q = em.createQuery("SELECT u FROM UsuarioProyecto u where u.areaInvestigacion.areaId="
		+id_area+" and u.fichaPersonal.idFicha="+id_ficha, UsuarioProyecto.class);
		@SuppressWarnings("unchecked")
		List<UsuarioProyecto> listaUsuarioProyectoes = q.getResultList();
	if (listaUsuarioProyectoes.isEmpty())
		return false;
	else
		return true;
		
	}
	**/
	public void ingresarUsuarioProyecto( UsuarioProyecto usuarioproyecto ,long id_ficha_fk, long  id_proyecto, int id_organizacion) throws Exception {
	
		if (id_ficha_fk == 0 || id_proyecto==0 || id_organizacion==0) {
			throw new Exception("Ingrese los datos del proyecto, encargado y organización.");
		}
		/**
		boolean existeAreaInteresFicha=existeOrganizacionFicha(id_area, id_ficha_fk);
		if (existeAreaInteresFicha) {
			throw new Exception("Ya se encuentra el área con el usuario");
		}
		*/
		UsuarioProyecto nusuerproyecto = new UsuarioProyecto();
		FichaPersonal ficha= findFichaPersonalById(id_ficha_fk);
		Organizacion organizacion= findOrganizacionById(id_organizacion);
		ProyectoInvestigacion proyecto= managerInvestigador.findProyectoInvestigacionById(id_proyecto);
		nusuerproyecto.setFichaPersonal(ficha);
		nusuerproyecto.setOrganizacion(organizacion); 
		nusuerproyecto.setProyectoInvestigacion(proyecto);
		nusuerproyecto.setFechaDe(usuarioproyecto.getFechaDe());
		nusuerproyecto.setFechaHasta(usuarioproyecto.getFechaHasta());
		em.persist(nusuerproyecto);
	}

	public void editarUsuarioProyecto(UsuarioProyecto usuarioproyectoA , long id_ficha_fk,long id_proyect, int id_organizacion) throws Exception {
		UsuarioProyecto usuarioproyectoN = findUsuarioProyectoById(usuarioproyectoA.getIdUsuarioProyecto());
		if (id_ficha_fk == 0 || id_organizacion==0 || id_proyect==0 ) {
			throw new Exception("Ingrese los datos del usuario y proyectos a editar.");
		}
		
		FichaPersonal ficha= findFichaPersonalById(id_ficha_fk);
		Organizacion organizacion= findOrganizacionById(id_organizacion);
		ProyectoInvestigacion proyecto= managerInvestigador.findProyectoInvestigacionById(id_proyect);
		usuarioproyectoN.setOrganizacion(organizacion);
		usuarioproyectoN.setFichaPersonal(ficha);
		usuarioproyectoN.setProyectoInvestigacion(proyecto);
		usuarioproyectoN.setFechaDe(usuarioproyectoA.getFechaDe());
		usuarioproyectoN.setFechaHasta(usuarioproyectoA.getFechaHasta());
		em.merge(usuarioproyectoN);
	}
	
	public void eliminarUsuarioProyecto(int id_usuarioproyecto) throws Exception {
		UsuarioProyecto usuarioproyectoN = findUsuarioProyectoById(id_usuarioproyecto);
		if (usuarioproyectoN == null) {
			throw new Exception("Error al cargar el usuario y proyecto de encargado.");
		}
		
		em.remove(usuarioproyectoN);
	}
	
	/**
	 * metodos de AreaInvestigacion
	 */
  
	public List<AreaInvestigacion> findAllAreaInvestigaciones() {

		Query q = em.createQuery("SELECT a FROM AreaInvestigacion a", AreaInvestigacion.class);
		@SuppressWarnings("unchecked")
		List<AreaInvestigacion> listaAreaInvestigacions = q.getResultList();
		return listaAreaInvestigacions;
	}
	
	public AreaInvestigacion findAreaInvestigacionById(int id_AreaInvestigacion) {
		AreaInvestigacion areainvestigacion = em.find(AreaInvestigacion.class, id_AreaInvestigacion);
		return areainvestigacion;
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

	public void ingresarAreaInvestigacion(AreaInvestigacion areaInvestigacion )throws Exception {
		if (areaInvestigacion == null) {
			throw new Exception("No ha ingresado datos en el AreaInvestigacion");
		}
		boolean existeNombreAreaInvestigacion = existeNombreAreaInvestigacion(areaInvestigacion.getNombreInvestigacion());
			if (existeNombreAreaInvestigacion) {
				throw new Exception("Ya existe el AreaInvestigacion con el nombre " + areaInvestigacion.getNombreInvestigacion());
			}
			
		AreaInvestigacion narea = new AreaInvestigacion();
		narea.setNombreInvestigacion(areaInvestigacion.getNombreInvestigacion());
		narea.setDescripcionInvestigacion(areaInvestigacion.getDescripcionInvestigacion());
		em.persist(narea);

	}

	public void editarAreaInvestigacion(AreaInvestigacion areaInvestigacionA) throws Exception {
		AreaInvestigacion areaInvestigacionN = findAreaInvestigacionById(areaInvestigacionA.getAreaId());
		if (areaInvestigacionN == null) {
			throw new Exception("Error al cargar el AreaInvestigacion");
		}
		if (!areaInvestigacionA.getNombreInvestigacion().equals(areaInvestigacionN.getNombreInvestigacion())) {
			boolean existeNombreAreaInvestigacion = existeNombreAreaInvestigacion(areaInvestigacionA.getNombreInvestigacion());
			if (existeNombreAreaInvestigacion) {
				throw new Exception("Ya existe el AreaInvestigacion con el nombre " + areaInvestigacionA.getDescripcionInvestigacion());
			}
		}
		areaInvestigacionN.setNombreInvestigacion(areaInvestigacionA.getNombreInvestigacion());
		areaInvestigacionN.setDescripcionInvestigacion(areaInvestigacionA.getDescripcionInvestigacion());
	
		em.merge(areaInvestigacionN);
	}

	public void eliminarAreaInvestigacion(int id_AreaInvestigacion) throws Exception {
		AreaInvestigacion areaInvestigacionN = findAreaInvestigacionById(id_AreaInvestigacion);
		if (areaInvestigacionN == null) {
			throw new Exception("Error al cargar el área de investigación");
		}

		em.remove(areaInvestigacionN);

	}

	/*metodos de la tabla resultados*/

	public List<Respuesta> findAllRespuestaes() {

		Query q = em.createQuery("SELECT r FROM Respuesta r", Respuesta.class);
		@SuppressWarnings("unchecked")
		List<Respuesta> listaRespuestas = q.getResultList();
		return listaRespuestas;
	}
	
	public Respuesta findRespuestaById(long id_user) {
		Respuesta respuesta = em.find(Respuesta.class, id_user);
		return respuesta;
	}
/*
	@SuppressWarnings("unchecked")
	public boolean existeNombreRespuesta(String nombre) {

		String JPQL = "SELECT m FROM Respuesta m WHERE m.nombre=?1";
		Query query = em.createQuery(JPQL, Respuesta.class);
		query.setParameter(1, nombre);
		List<Respuesta> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}
*/
	public void ingresarRespuesta(long id_user, String resp, Date fecha)throws Exception {
		
		Respuesta nrespuesta = new Respuesta();
		Usuario user = findUsuarioById(id_user);
		nrespuesta.setUsuario(user);
		nrespuesta.setPreguntaRespuesta(resp);
		nrespuesta.setFecha(fecha);
		em.persist(nrespuesta);

	}

	
	// metodos de reporte





	
	
	

}
