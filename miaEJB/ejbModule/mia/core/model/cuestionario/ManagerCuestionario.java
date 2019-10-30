package mia.core.model.cuestionario;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mia.core.model.cuestionario.dto.PreguntaDimensionDTO;
import mia.core.model.entities.Cuestionario;
import mia.core.model.entities.Dimension;
import mia.core.model.entities.DimensionPregunta;
import mia.core.model.entities.Opcion;
import mia.core.model.entities.Pregunta;

import mia.core.model.entities.Reporte;

/**
 * Session Bean implementation class ManagerCuestionario
 */
@Stateless
@LocalBean
public class ManagerCuestionario {
	


	@PersistenceContext(unitName = "miaDS")
	private EntityManager em;

	public ManagerCuestionario() {

	}

	/***
	 * m�todos de las opciones del cuestionario
	 */

	public List<Opcion> findAllOpciones() {

		Query q = em.createQuery("SELECT o FROM Opcion o", Opcion.class);
		@SuppressWarnings("unchecked")
		List<Opcion> listaOpcions = q.getResultList();
		return listaOpcions;
	}
	public List<Opcion> findAllOpcionesByCuestionario(int id_cuestionario) {

		Query q = em.createQuery("SELECT o FROM Opcion o where o.cuestionario.idCuestionario="+id_cuestionario, Opcion.class);
		@SuppressWarnings("unchecked")
		List<Opcion> listaOpcions = q.getResultList();
		return listaOpcions;
	}

	public Opcion findOpcionById(long id_opcion) {
		Opcion opcion = em.find(Opcion.class, id_opcion);
		return opcion;

	}

	@SuppressWarnings("unchecked")
	public boolean existeNombreOpcion(String nombre) {

		String JPQL = "SELECT o FROM Opcion o WHERE o.opcDescripcion=?1";
		Query query = em.createQuery(JPQL, Opcion.class);
		query.setParameter(1, nombre);
		List<Opcion> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}

	@SuppressWarnings("unchecked")
	public boolean existeCuestionario(int id_cuest) {
		String JPQL = "SELECT o FROM Opcion o WHERE o.cuestionario.idCuestionario=" + id_cuest;
		Query query = em.createQuery(JPQL, Opcion.class);
		List<Opcion> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}

	public void ingresarOpcion(Opcion opcio, int id_cuestionario) throws Exception {
		if (opcio == null) {
			throw new Exception("No ha ingresado datos en la ficha");
		}
		if (id_cuestionario == 0) {
			throw new Exception("Error al seleccionar el cuestionario");
		}
		System.out.println("Ingresa los dato" + opcio.getValor());
		Cuestionario cuest = findCuestionarioById(id_cuestionario);
		Opcion nopcio = new Opcion();
		nopcio.setOpcDescripcion(opcio.getOpcDescripcion());
		nopcio.setValor(opcio.getValor());
		
		nopcio.setCuestionario(cuest);
		em.persist(nopcio);

	}

	public void editarOpcion(Opcion opcioA, int id_cuestionario) throws Exception {
		Opcion cuestN = findOpcionById(opcioA.getIdOpcion());
		if (cuestN == null) {
			throw new Exception("Error al cargar el Cusetionario");
		}
		if (!opcioA.getOpcDescripcion().equals(cuestN.getOpcDescripcion())) {
			boolean existeNombreOpcion = existeNombreOpcion(opcioA.getOpcDescripcion());
			if (existeNombreOpcion) {
				throw new Exception("Ya existe el cuestionario con el nombre " + opcioA.getOpcDescripcion());
			}
		}
		System.out.println("esta es el id cuestionario"+ id_cuestionario);
		Cuestionario cuest= findCuestionarioById(id_cuestionario);
		cuestN.setOpcDescripcion(opcioA.getOpcDescripcion());
		cuestN.setValor(opcioA.getValor());
		cuestN.setCuestionario(cuest);
		em.merge(cuestN);
	}

	public void eliminarOpcion(int id_opcio) throws Exception {
		Opcion opcioN = findOpcionById(id_opcio);
		if (opcioN == null) {
			throw new Exception("Error al cargar el cuestionario.");
		}

		em.remove(opcioN);

	}

	/***
	 * m�todos del cuestionario
	 */

	public List<Cuestionario> findAllCuestionarioes() {

		Query q = em.createQuery("SELECT c FROM Cuestionario c", Cuestionario.class);
		@SuppressWarnings("unchecked")
		List<Cuestionario> listaCuestionarios = q.getResultList();
		return listaCuestionarios;
	}

	public Cuestionario findCuestionarioById(int id_cuest) {
		Cuestionario rol = em.find(Cuestionario.class, id_cuest);
		return rol;

	}

	@SuppressWarnings("unchecked")
	public boolean existeNombreCuestionario(String nombre) {

		String JPQL = "SELECT c FROM Cuestionario c WHERE c.descripcion=?1";
		Query query = em.createQuery(JPQL, Cuestionario.class);
		query.setParameter(1, nombre);
		List<Cuestionario> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}

	@SuppressWarnings("unchecked")
	public boolean existeCuestionarioenOpcion(int id_cuest) {
		String JPQL = "SELECT o FROM Opcion o WHERE o.cuestionario.idCuestionario=" + id_cuest;
		Query query = em.createQuery(JPQL, Opcion.class);
		List<Opcion> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}

	public void ingresarCuestionario(Cuestionario cuest) throws Exception {
		if (cuest == null) {
			throw new Exception("Ingrese los datos del cuestionario");
		}
		boolean existeCuestionario = existeNombreCuestionario(cuest.getDescripcion());
		if (existeCuestionario) {
			throw new Exception("El tipo de cuestionario " + cuest.getDescripcion() + " ya existe");
		}
		System.out.println("CUESTIONARIO; " + cuest.getDescripcion());
		Cuestionario ncuest = new Cuestionario();
		ncuest.setDescripcion(cuest.getDescripcion());
		em.persist(ncuest);
	}

	public void editarCuestionario(Cuestionario cuestA) throws Exception {
		Cuestionario cuestN = findCuestionarioById(cuestA.getIdCuestionario());
		if (cuestN == null) {
			throw new Exception("Error al cargar el Cusetionario");
		}
		if (!cuestA.getDescripcion().equals(cuestN.getDescripcion())) {
			boolean existeNombreCuestionario = existeNombreCuestionario(cuestA.getDescripcion());
			if (existeNombreCuestionario) {
				throw new Exception("Ya existe el cuestionario con el nombre " + cuestA.getDescripcion());
			}
		}
		cuestN.setDescripcion(cuestA.getDescripcion());
		em.merge(cuestN);
	}

	public void eliminarCuestionario(int id_cuest) throws Exception {
		Cuestionario cuestN = findCuestionarioById(id_cuest);
		if (cuestN == null) {
			throw new Exception("Error al cargar el cuestionario.");
		}
		boolean existeCuestionario = existeCuestionarioenOpcion(id_cuest);
		if (existeCuestionario) {
			throw new Exception("La Cuestionario est� siendo utilizada en la tabla opciones");
		}
		em.remove(cuestN);
	}

	/***
	 * m�todos de als dimensiones a evaluar con respecto a la pregunta
	 */

	public List<Dimension> findAllDimensiones() {

		Query q = em.createQuery("SELECT d FROM Dimension d", Dimension.class);
		@SuppressWarnings("unchecked")
		List<Dimension> listaDimensions = q.getResultList();
		return listaDimensions;
	}

	public Dimension findDimensionById(int id_dimen) {
		Dimension dimen = em.find(Dimension.class, id_dimen);
		return dimen;

	}

	
	public List<Dimension> findalDimensionbyIdcuestionario( int id_cuestionario) {

		String JPQL = "SELECT d FROM Dimension d WHERE d.cuestionario.idCuestionario=?1";
		Query query = em.createQuery(JPQL, Dimension.class);
		query.setParameter(1, id_cuestionario);
		@SuppressWarnings("unchecked")
		List<Dimension> lista= query.getResultList();
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public boolean existeNombreDimension(String nombre, int id_cuestionario) {

		String JPQL = "SELECT d FROM Dimension d WHERE d.descripcion=?1 and d.cuestionario.idCuestionario=?2";
		Query query = em.createQuery(JPQL, Dimension.class);
		query.setParameter(1, nombre);
		query.setParameter(2, id_cuestionario);
		List<Dimension> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}
	
	@SuppressWarnings("unchecked")
	public boolean existeDimensionenPre(int id_dimen) {
		String JPQL = "SELECT d FROM DimensionPregunta d WHERE d.dimension.idDimension=" + id_dimen;
		Query query = em.createQuery(JPQL, DimensionPregunta.class);
		List<DimensionPregunta> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}

	@SuppressWarnings("unchecked")
	public boolean existeDimensionenReporte(int id_dimen) {
		String JPQL = "SELECT r FROM Reporte r WHERE r.dimension.idDimension =" + id_dimen;
		Query query = em.createQuery(JPQL, Reporte.class);
		List<Reporte> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}

	public void ingresarDimension(Dimension dimen, int id_cuestionario) throws Exception {
		if (dimen == null) {
			throw new Exception("Ingrese los datos de las dimensiones de cuestionario");
		}
		boolean existeDimension = existeNombreDimension(dimen.getDescripcion(), id_cuestionario);
		if (existeDimension) {
			throw new Exception("El tipo de cuestionario " + dimen.getDescripcion() + " ya existe");
		}
		
		Cuestionario cuest = findCuestionarioById(id_cuestionario);
		Dimension ndimen = new Dimension();
		ndimen.setDescripcion(dimen.getDescripcion());
		ndimen.setCuestionario(cuest);
		em.persist(ndimen);
	}

	public void editarDimension(Dimension dimenA, int id_cuestionario) throws Exception {
		Dimension dimenN = findDimensionById(dimenA.getIdDimension());
		if (dimenN == null) {
			throw new Exception("Error al cargar el Cusetionario");
		}
		if (!dimenN.getDescripcion().equals(dimenN.getDescripcion())
				|| id_cuestionario != dimenA.getCuestionario().getIdCuestionario()) {
			System.out.println("SI ENTRA");
			boolean existeNombreDimension = existeNombreDimension(dimenA.getDescripcion(), id_cuestionario);
			if (existeNombreDimension) {
				throw new Exception("Ya existe la dimension con el nombre " + dimenA.getDescripcion());
			}
		}
		Cuestionario cuestio = findCuestionarioById(id_cuestionario);
		dimenN.setDescripcion(dimenA.getDescripcion());
		dimenN.setCuestionario(cuestio);
		em.merge(dimenN);
	}

	public void eliminarDimension(int id_dimen) throws Exception {
		Dimension dimenN = findDimensionById(id_dimen);
		if (dimenN == null) {
			throw new Exception("Error al cargar el cuestionario.");
		}
		boolean existeDimensionenPre = existeDimensionenPre(id_dimen);
		if (existeDimensionenPre) {
			throw new Exception("La Dimension est� siendo utilizada en dimension pregunta!");
		}
		boolean existeDimensionReporte = existeDimensionenReporte(id_dimen);
		if (existeDimensionReporte) {
			throw new Exception("La Dimension est� siendo utilizada en Reportes no se puede eliminar");
		}
		em.remove(dimenN);
	}

	/***
	 * m�todos del ingreso de Preguntas
	 */

	public List<Pregunta> findAllPreguntaes() {

		Query q = em.createQuery("SELECT p FROM Pregunta p", Pregunta.class);
		@SuppressWarnings("unchecked")
		List<Pregunta> listaPreguntas = q.getResultList();
		return listaPreguntas;
	}

	public Pregunta findPreguntaById(int id_pregunta) {
		Pregunta rol = em.find(Pregunta.class, id_pregunta);
		return rol;

	}

	@SuppressWarnings("unchecked")
	public boolean existeNombrePregunta(String nombre) {

		String JPQL = "SELECT p FROM Pregunta p WHERE p.descripcion=?1";
		Query query = em.createQuery(JPQL, Pregunta.class);
		query.setParameter(1, nombre);
		List<Pregunta> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}

	@SuppressWarnings("unchecked")
	public boolean existePreguntaenDimPre(int id_preg) {
		String JPQL = "SELECT d FROM DimensionPregunta d WHERE d.pregunta.idPregunta=" + id_preg;
		Query query = em.createQuery(JPQL, DimensionPregunta.class);
		List<DimensionPregunta> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}

	public void ingresarPregunta(Pregunta preg) throws Exception {
		if (preg == null) {
			throw new Exception("Ingrese los datos de la Pregunta");
		}
		boolean existePregunta = existeNombrePregunta(preg.getDescripcion());
		if (existePregunta) {
			throw new Exception("La pregunta: " + preg.getDescripcion() + " ya existe");
		}
		Pregunta npreg = new Pregunta();
		npreg.setDescripcion(preg.getDescripcion());
		
		em.persist(npreg);
	}
	

	public void editarPregunta(Pregunta pregA) throws Exception {
		Pregunta pregN = findPreguntaById(pregA.getIdPregunta());
		if (pregN == null) {
			throw new Exception("Error al cargar el Cusetionario");
		}
		if (!pregA.getDescripcion().equals(pregN.getDescripcion())) {
			boolean existeNombrePregunta = existeNombrePregunta(pregA.getDescripcion());
			if (existeNombrePregunta) {
				throw new Exception("Ya existe el cuestionario con la pregunta: " + pregA.getDescripcion());
			}
		}
		pregN.setDescripcion(pregA.getDescripcion());
		em.merge(pregN);
	}

	public void eliminarPregunta(int id_preg) throws Exception {
		Pregunta pregN = findPreguntaById(id_preg);
		if (pregN == null) {
			throw new Exception("Error al cargar el cuestionario.");
		}
		boolean existePregunta = existePreguntaenDimPre(id_preg);
		if (existePregunta) {
			throw new Exception("La Pregunta est� siendo utilizada en dimensi�n");
		}
		em.remove(pregN);
	}

	/***
	 * m�todos del ingreso de Dimen_Pregunta
	 */

	public List<DimensionPregunta> findAllDimensionPreguntaes() {

		Query q = em.createQuery("SELECT d FROM DimensionPregunta d", DimensionPregunta.class);
		@SuppressWarnings("unchecked")
		List<DimensionPregunta> listaDimensionPreguntas = q.getResultList();
		return listaDimensionPreguntas;
	}
 
	public List<DimensionPregunta> findAllDimensionByIdDimension(int id_dimen) {
      System.out.println("ID"+id_dimen);
		Query q = em.createQuery("SELECT d FROM DimensionPregunta d WHERE d.dimension.idDimension=" + id_dimen,
				DimensionPregunta.class);
		@SuppressWarnings("unchecked")
		List<DimensionPregunta> listaDimensionPreguntas = q.getResultList();
		System.out.println("LISTA "+listaDimensionPreguntas.size());
		return listaDimensionPreguntas;
	}
	
	
	public void ingresarDimensionPregunta(int id_dimen, int id_preg) throws Exception {
		if (id_dimen == 0) {
			throw new Exception("Seleccione la dimension");
		}
		if (id_dimen == 0) {
			throw new Exception("Seleccione la pregunta");
		}
		Dimension dimen = findDimensionById(id_dimen);
		Pregunta pregun = findPreguntaById(id_preg);
		boolean dimensionPregunta=existeDimensionPregunta(id_dimen,id_preg);
		if(dimensionPregunta) {
			throw new Exception("Ya existe el cuestionario con la pregunta " + pregun.getDescripcion()
					+ " y dimension: " + dimen.getDescripcion());
		}

		DimensionPregunta ndimpreg = new DimensionPregunta();
		ndimpreg.setDimension(dimen);
		ndimpreg.setPregunta(pregun);

		em.persist(ndimpreg);
	}

	public void editarDimensionPregunta(DimensionPregunta pregA, int id_fk_dimension,int id_fk_pregunta)
			throws Exception {
		DimensionPregunta pregN = findDimensionPreguntaById(pregA.getIdPreguntaDimension());
		if (pregN == null) {
			throw new Exception("Error al cargar el Cusetionario");
		}
		if (id_fk_dimension == 0) {
			throw new Exception("Error al seleccionar la dimension");
		}
		if (id_fk_pregunta == 0) {
			throw new Exception("Error al seleccionar la preggunta");
		}
		if ((id_fk_dimension != pregN.getDimension().getIdDimension())
				|| (id_fk_pregunta != pregN.getPregunta().getIdPregunta())) {
			boolean existeDimensionPregunta = existeDimensionPregunta(id_fk_dimension, id_fk_pregunta);
			if (existeDimensionPregunta) {
				throw new Exception("Ya existe el cuestionario con la pregunta " + pregA.getPregunta().getDescripcion()
						+ " y dimension: " + pregA.getDimension().getDescripcion());
			}
		}
		Pregunta pre = findPreguntaById(id_fk_pregunta);
		Dimension dime = findDimensionById(id_fk_dimension);
		pregN.setDimension(dime);
		pregN.setPregunta(pre);
		em.merge(pregN);
	}

	@SuppressWarnings("unchecked")
	public boolean existeDimensionPregunta(int id_fk_dimension, int id_fk_pregunta) {
		Query q = em.createQuery("SELECT d FROM DimensionPregunta d WHERE" + " d.dimension.idDimension="
				+ id_fk_dimension + " and d.pregunta.idPregunta=" + id_fk_pregunta, DimensionPregunta.class);
		List<DimensionPregunta> listaDimensionPreguntas = q.getResultList();
		if (listaDimensionPreguntas.isEmpty()) {
			return false;
		} else
			return true;
	}

	public void eliminarDimensionPregunta(long id_preg) throws Exception {
		DimensionPregunta pregN = findDimensionPreguntaById(id_preg);
		if (pregN == null) {
			throw new Exception("Error al cargar el cuestionario.");
		}
		em.remove(pregN);
	}
	public List<PreguntaDimensionDTO>cargarListaPreguntasRespuestas(List<DimensionPregunta>listaDimenPregun){
		
		List<PreguntaDimensionDTO> listaDto= new ArrayList<PreguntaDimensionDTO>();
		for (DimensionPregunta dimenPre : listaDimenPregun) {
			PreguntaDimensionDTO obj=new PreguntaDimensionDTO();
			obj.setDimension(dimenPre.getDimension());
			obj.setIdPreguntaDimension(dimenPre.getIdPreguntaDimension());
			obj.setPregunta(dimenPre.getPregunta());
			
			listaDto.add(obj);
		}
		for (PreguntaDimensionDTO preguntaDimensionDTO : listaDto) {
		System.out.println(preguntaDimensionDTO.getPregunta().getDescripcion());
		}
		return listaDto;
	}

	public DimensionPregunta findDimensionPreguntaById(long id_dimension_pregunta) {
		DimensionPregunta dimensionPregunta = em.find(DimensionPregunta.class, id_dimension_pregunta);
		return dimensionPregunta;

	}

	
	
}
