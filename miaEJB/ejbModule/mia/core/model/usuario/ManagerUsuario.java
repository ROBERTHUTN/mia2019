package mia.core.model.usuario;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mia.core.model.administrador.ManagerAdministrador;
import mia.core.model.entities.Etnia;
import mia.core.model.entities.FichaPersonal;
import mia.core.model.entities.GradoEstudio;
import mia.core.model.entities.PaisEstado;
import mia.core.model.entities.Religion;
import mia.core.model.entities.Rol;
import mia.core.model.entities.Usuario;

@Stateless
@LocalBean
public class ManagerUsuario {
	@PersistenceContext(unitName = "miaDS")
	private EntityManager em;

	@EJB
	private ManagerAdministrador managerAdministrador;
	public ManagerUsuario() {
	}

	// MÃ©todo que me devuelve la Lista de ROLES
	public List<Rol> findAllRoles() {

		Query q = em.createQuery("SELECT r FROM Rol r", Rol.class);
		@SuppressWarnings("unchecked")
		List<Rol> listaRoles = q.getResultList();
		return listaRoles;
	}

	public List<GradoEstudio> findAllGrado() {

		Query q = em.createQuery("SELECT g FROM GradoEstudio g", GradoEstudio.class);
		@SuppressWarnings("unchecked")
		List<GradoEstudio> listaGrados = q.getResultList();
		return listaGrados;
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

		Query q = em.createQuery("SELECT p FROM PaisEstado p where p.id_padre_pais=null", PaisEstado.class);
		@SuppressWarnings("unchecked")
		List<PaisEstado> listaPaisEstado = q.getResultList();
		return listaPaisEstado;
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


	public Usuario findUsuarioById(long id_user) {
		Usuario user = em.find(Usuario.class, id_user);
		return user;

	}

	
	public Rol findRolById(int id_rol) {
		Rol rol = em.find(Rol.class, id_rol);
		return rol;

	}

	public GradoEstudio findGradoById(int id_grado) {
		GradoEstudio grado = em.find(GradoEstudio.class, id_grado);
		return grado;
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
	public boolean existeGradoEnFichaPersonal(int id_grado) {

		String JPQL = "SELECT f FROM FichaPersonal f WHERE f.gradoEstudio.idGrado=?1";
		Query query = em.createQuery(JPQL, FichaPersonal.class);
		query.setParameter(1, id_grado);
		List<FichaPersonal> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}

	@SuppressWarnings("unchecked")
	public boolean existeNombreGrado(String nombreGrado) {

		String JPQL = "SELECT g FROM GradoEstudio g WHERE g.nombreGrado=?1";
		Query query = em.createQuery(JPQL, GradoEstudio.class);
		query.setParameter(1, nombreGrado);
		List<GradoEstudio> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}
	@SuppressWarnings("unchecked")
	public boolean existeGrado(int id_grado) {

		String JPQL = "SELECT g FROM GradoEstudio g WHERE g.descripcion=?1";
		Query query = em.createQuery(JPQL, GradoEstudio.class);
		query.setParameter(1, id_grado);
		List<GradoEstudio> lista;
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
	public boolean existeGradoEstudio(String grado) {
		String JPQL = "SELECT g FROM GradoEstudio g WHERE g.descripcion=?1";
		Query query = em.createQuery(JPQL, GradoEstudio.class);
		query.setParameter(1, grado);
		List<GradoEstudio> lista;
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

			throw new Exception("Error ya existe un usario registrado con ese correo electrÃ³nico");
		}

		boolean ceula = existeCedulaUsuario(usuario.getCi());
		if (ceula) {
			throw new Exception("Error ya existe un usario registrado con ese nÃºmero de cÃ©dula");
		}

		Rol rol = findRolById(id_rol);
		Usuario usuarioNuevo = new Usuario();
		usuarioNuevo.setNombres(usuarioNuevo.getNombres());
		usuarioNuevo.setApellidos(usuarioNuevo.getApellidos());
		usuarioNuevo.setPassword(usuarioNuevo.getPassword());
		usuarioNuevo.setRol(rol);
		usuarioNuevo.setCi(usuarioNuevo.getCi());
		usuarioNuevo.setCorreo(usuarioNuevo.getCorreo());
		usuarioNuevo.setActivo(usuarioNuevo.getActivo());
		return;
	}

	public void ingresarPais(PaisEstado paisEstado, int id_padre_pais) throws Exception {
		if (paisEstado == null) {
			throw new Exception("No ha ingresado datos en el PaÃ­s");
		}

		if (id_padre_pais == 0) {
			throw new Exception("Error al seleccionar el pais");
		}
		boolean paisE = existePais(paisEstado.getNombre());
		if (paisE) {

			throw new Exception("Error ya existe un paÃ­s o estado registrado con ese nombre");
		}

		PaisEstado paisEstad = new PaisEstado();
		paisEstad.setNombre(paisEstad.getNombre());
		paisEstad.setPaisEstado(paisEstado.getPaisEstado());
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
			throw new Exception("Error al seleccionar el paï¿½s");
		}
		if (estado == 0) {
			throw new Exception("Error al seleccionar el estado");
		}
		if (id_grado == 0) {
			throw new Exception("Error al seleccionar el grado");
		}
		
		PaisEstado paisEst = findPaisEstadoById(id_pais);
		PaisEstado esta = findPaisEstadoById(estado);
		Usuario usuario = managerAdministrador.findUsuarioById(id_usuario);
		Etnia etnia = findEtniaById(id_etnia);
		Religion reli = findReligionById(id_religion);
		GradoEstudio grado= findGradoById(id_grado);
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

	public void ingresarGradoEstudio(GradoEstudio grado) throws Exception {
		if (grado == null) {
			throw new Exception("Ingrese los datos del grado de estudio.");
		}
		boolean existeGrado = existeGradoEstudio(grado.getNombreGrado());
		if (existeGrado) {
			throw new Exception("El grado  " + grado.getNombreGrado() + " ya existe");
		}
		GradoEstudio ngrado = new GradoEstudio();
		ngrado.setNombreGrado(grado.getNombreGrado());
		ngrado.setDescripcion(grado.getDescripcion());

		em.persist(ngrado);
	}

	public void ingresarEtnia(Etnia etnia) throws Exception {
		if (etnia == null) {
			throw new Exception("Ingrese los datos de la EtnÃ­a");
		} 
		boolean existeEtnia = existeEtnia(etnia.getDescripcion());
		if (existeEtnia) {
			throw new Exception("La etnia  " + etnia.getDescripcion() + " ya existe");
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

	public void editarGrado(GradoEstudio gradoA) throws Exception {
		GradoEstudio gradoN = findGradoById(gradoA.getIdGrado());
		if (gradoN == null) {
			throw new Exception("Error al cargar el grado");
		}
		if (!gradoA.getNombreGrado().equals(gradoN.getNombreGrado())) {
			boolean existeNombreGrado = existeNombreGrado(gradoA.getNombreGrado());
			if (existeNombreGrado) {
				throw new Exception("Ya existe el grado con el nombre " + gradoA.getNombreGrado());
			}
		}
		gradoN.setNombreGrado(gradoA.getNombreGrado());
		gradoN.setDescripcion(gradoA.getDescripcion());
		em.merge(gradoN);
	}

	public void editarReligion(Religion relA) throws Exception {
		Religion relN = findReligionById(relA.getIdReligion());
		if (relN == null) {
			throw new Exception("Error al cargar la religión");
		}
		if (!relA.getDescripcion().equals(relN.getDescripcion())) {
			boolean existeNombreReligion = existeNombreReligion(relA.getDescripcion());
			if (existeNombreReligion) {
				throw new Exception("Ya existe la religión con el nombre " + relA.getDescripcion());
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

	public void eliminarGrado(int id_grado) throws Exception {
		GradoEstudio gradoN = findGradoById(id_grado);
		if (gradoN == null) {
			throw new Exception("Error al cargar el grado");
		}
		boolean gradoEnFichaP=existeGradoEnFichaPersonal(id_grado);
	if (gradoEnFichaP) {
		throw new Exception("El grado no puede ser eliminado está siendo utilizado en la ficha personal no puede ser eliminado. !");
	}else{
	
		em.remove(gradoN);
	}
	}

	public void eliminarReligion(int id_religion) throws Exception {
		Religion reliN = findReligionById(id_religion);
		if (reliN == null) {
			throw new Exception("Error al cargar la religión");
		}
		boolean existeReligionFicha = existeReligionenFichaRegistro(id_religion);
		if (existeReligionFicha) {
			throw new Exception("La religión está siendo utilizada en la tabla ficha de registro");
		}
		em.remove(reliN);
	}

	@SuppressWarnings("unchecked")
	public boolean existeRolenUsuario(int id_rol) {
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

}
