package mia.core.model.cuestionario;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mia.core.model.administrador.ManagerAdministrador;
import mia.core.model.cuestionario.dto.CuestionarioDTO;
import mia.core.model.cuestionario.dto.DimensionDTO;
import mia.core.model.cuestionario.dto.DimensionPreguntaDTO;
import mia.core.model.cuestionario.dto.PreguntaDimensionDTO;
import mia.core.model.cuestionario.dto.PreguntaModuloDTO;
import mia.core.model.entities.Cuestionario;
import mia.core.model.entities.CursoModulo;
import mia.core.model.entities.Dimension;
import mia.core.model.entities.DimensionPregunta;
import mia.core.model.entities.Modulo;
import mia.core.model.entities.Opcion;
import mia.core.model.entities.Opcionpregunta;
import mia.core.model.entities.Pregunta;
import mia.core.model.entities.Preguntamodulo;
import mia.core.model.entities.Reporte;
import mia.core.model.entities.Reporteprepost;
import mia.core.model.entities.Respuestapregunta;
import mia.core.model.entities.Usuario;

/**
 * Session Bean implementation class ManagerCuestionario
 */
@Stateless
@LocalBean
public class ManagerCuestionario {

	@PersistenceContext(unitName = "miaDS")
	private EntityManager em;
	@EJB
	private ManagerAdministrador managerAdministrador;
	@EJB
	private ManagerCuestionario managerCuestionario;

	public ManagerCuestionario() {

	}

	/***
	 * mï¿½todos de las opciones del cuestionario
	 */

	@SuppressWarnings("unchecked")
	public List<Reporteprepost> ultimoReporte(long id_user)
	{
				Query q= em.createQuery(" SELECT r FROM Reporteprepost r "
						+ "WHERE r.usuario.idUsuario=?1 order by r.fechaInscripcion desc", Reporteprepost.class);
				q.setParameter(1, id_user);
			//	@SuppressWarnings("unchecked")
				List<Reporteprepost> reporteprepost= q.getResultList();
				return reporteprepost;
	}
	public int calcularDiasFaltantes(Date ultimoReporte) throws Exception {
		int d=0;
		if (ultimoReporte==null) {
			throw new Exception("La fecha está vacía");
		}else {
			Date fecha = new Date();
			System.out.println(fecha);
			long tiempoActualSistema, tiempoultimoReporte,diasTranscurrridos;
			
			tiempoActualSistema = fecha.getTime();// milisegundos
			tiempoultimoReporte = ultimoReporte.getTime();// milisegundos
			if (tiempoActualSistema>tiempoultimoReporte) {
				diasTranscurrridos=(tiempoActualSistema-tiempoultimoReporte)/(1000 * 60 * 60 * 24);
				d=Integer.parseInt(""+diasTranscurrridos);
				d=30-d;
			}

		}
		return d;
	}
	public List<Preguntamodulo> findAllPreguntamodulo() {

		Query q = em.createQuery("SELECT p FROM Preguntamodulo p", Preguntamodulo.class);
		@SuppressWarnings("unchecked")
		List<Preguntamodulo> listaPreguntamodulo = q.getResultList();
		return listaPreguntamodulo;
	}
	public List<Opcion> findAllOpciones() {

		Query q = em.createQuery("SELECT o FROM Opcion o", Opcion.class);
		@SuppressWarnings("unchecked")
		List<Opcion> listaOpcions = q.getResultList();
		return listaOpcions;
	}

	public List<Opcion> findAllOpcionesByCuestionario(int id_cuestionario) {

		Query q = em.createQuery("SELECT o FROM Opcion o where o.cuestionario.idCuestionario=" + id_cuestionario,
				Opcion.class);
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
		Cuestionario cuest = findCuestionarioById(id_cuestionario);
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
	 * mï¿½todos del cuestionario
	 */
	public List<Respuestapregunta> findAllRespuestapregunta() {

		Query q = em.createQuery("SELECT r FROM Respuestapregunta r", Respuestapregunta.class);
		@SuppressWarnings("unchecked")
		List<Respuestapregunta> listaRespuestapregunta = q.getResultList();
		return listaRespuestapregunta;
	}
	
	public List<Respuestapregunta> findAllRespuestapreguntabypregunta(long id_pregunta) {

		Query q = em.createQuery("SELECT r FROM Respuestapregunta r "
				+ "where r.preguntamodulo.idPregunta=?1", Respuestapregunta.class);
		q.setParameter(1, id_pregunta);
		@SuppressWarnings("unchecked")
		List<Respuestapregunta> listaRespuestapregunta = q.getResultList();
		return listaRespuestapregunta;
	}

	public List<Preguntamodulo> findAllPreguntamoduloSinResponder() {

		Query q = em.createQuery("SELECT p FROM Preguntamodulo p", Preguntamodulo.class);
		@SuppressWarnings("unchecked")
		List<Preguntamodulo> listaPreguntamodulo = q.getResultList();
		return listaPreguntamodulo;
	}
	
	public List<Preguntamodulo> findAllPreguntamodulobymodulo(long id_modulo) {

		Query q = em.createQuery("SELECT p FROM Preguntamodulo p "
				+ "where p.modulo.idModulo=?1", Preguntamodulo.class);
		q.setParameter(1, id_modulo);
		@SuppressWarnings("unchecked")
		List<Preguntamodulo> listaPreguntamodulo = q.getResultList();
		return listaPreguntamodulo;
	}

	public List<PreguntaModuloDTO> cargarPreguntasModulodto (List<PreguntaModuloDTO> lista) throws Exception {
		if(lista.isEmpty())
		{
			throw new Exception("Error al cargar las Preguntas");
		}
		
		List<PreguntaModuloDTO> pm= new ArrayList<>();
		List<Opcionpregunta> op = new ArrayList<>(); 
		List<Respuestapregunta> rp= new ArrayList<>();
		for (PreguntaModuloDTO p : lista) {
			op=findAllOpcionpreguntabypregunta(p.getIdPregunta());
			rp= findAllRespuestapreguntabypregunta(p.getIdPregunta());
			p.setOpcionpreguntas(op);
			p.setRespuestapreguntas(rp);
			
			pm.add(p);
		}
		return pm;
		
	}

	
	public List<PreguntaModuloDTO> otrometodo (List<Preguntamodulo> listapm) throws Exception {
		if(listapm.isEmpty())
		{
			throw new Exception("El módulo no tiene preguntas \n "
					+ "Contáctese con el administrador");
		}
		
		List<PreguntaModuloDTO> p= new ArrayList<>();
	
		for (Preguntamodulo pr : listapm) {
			PreguntaModuloDTO pmdto= new PreguntaModuloDTO();
			pmdto.setIdPregunta(pr.getIdPregunta());
			System.out.println("concatenar"+pr.getModulo());
			pmdto.setModulo(pr.getModulo());
			Respuestapregunta rp= new Respuestapregunta();
			rp= findRespuestapreguntaByIdPreguntaModulo(pr.getIdPregunta());
			
			pmdto.setRespuestacorrecta(rp.getRespuesta());
			
			pmdto.setOpcionpreguntas(pr.getOpcionpreguntas());	
			pmdto.setPregunta(pr.getPregunta());
			pmdto.setRespuestapreguntas(pr.getRespuestapreguntas());
			pmdto.setRespuesta(" ");
			p.add(pmdto);
		}
		
		return p;
		
	}
	


	

	
	
	public List<Opcionpregunta> findAllOpcionpregunta() {

		Query q = em.createQuery("SELECT o FROM Opcionpregunta o", Opcionpregunta.class);
		@SuppressWarnings("unchecked")
		List<Opcionpregunta> listaOpcionpregunta = q.getResultList();
		return listaOpcionpregunta;
	}
	
	public List<Opcionpregunta> findAllOpcionpreguntabypregunta(long id_pregunta) {

		Query q = em.createQuery("SELECT o FROM Opcionpregunta o "
				+ " where o.preguntamodulo.idPregunta=?1", Opcionpregunta.class);
		q.setParameter(1, id_pregunta);
		@SuppressWarnings("unchecked")
		List<Opcionpregunta> listaOpcionpregunta = q.getResultList();
		return listaOpcionpregunta;
	}
	
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

	public Opcionpregunta findOpcionpreguntaById(long id_opcion_pregunta) {
		Opcionpregunta opcPre = em.find(Opcionpregunta.class, id_opcion_pregunta);
		return opcPre;

	}

	public Preguntamodulo findPreguntamoduloById(long id_pregunta_modulo) {
		Preguntamodulo preMod = em.find(Preguntamodulo.class, id_pregunta_modulo);
		return preMod;

	}

	public Respuestapregunta findRespuestapreguntaById(long id_respuesta_pregunta) {
		Respuestapregunta respPre = em.find(Respuestapregunta.class, id_respuesta_pregunta);
		return respPre;

	}
	@SuppressWarnings("unchecked")
	public Respuestapregunta findRespuestapreguntaByIdPreguntaModulo(long id_pregunta_modulo) {
		
		String JPQL = "SELECT r FROM Respuestapregunta r"
				+ " WHERE preguntamodulo.idPregunta=?1";
		Query query = em.createQuery(JPQL, Respuestapregunta.class);
		query.setParameter(1, id_pregunta_modulo);
		List<Respuestapregunta> lista;
		lista = query.getResultList();
		Respuestapregunta r	=new Respuestapregunta();
		if (!lista.isEmpty()) {
			r=lista.get(0);
		} 
return r;
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
	
	@SuppressWarnings("unchecked")
	public boolean existePreguntamoduloenOpcionPregunta(long id_pregunta_modulo) {
		String JPQL = "SELECT o FROM Opcionpregunta o WHERE o.preguntamodulo.idPregunta=" + id_pregunta_modulo;
		Query query = em.createQuery(JPQL, Opcionpregunta.class);
		List<Opcionpregunta> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}
	@SuppressWarnings("unchecked")
	public boolean  existePreguntamoduloenRespuestapregunta(long id_pregunta_modulo) {
		String JPQL = "SELECT r FROM Respuestapregunta r WHERE  "
				+ " r.preguntamodulo.idPregunta=" + id_pregunta_modulo;
		Query query = em.createQuery(JPQL, Respuestapregunta.class);
		List<Respuestapregunta> lista;
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
			throw new Exception("La Cuestionario estï¿½ siendo utilizada en la tabla opciones");
		}
		em.remove(cuestN);
	}
	
	public void eliminarOpcionpregunta(long id_opcion_pregunta) throws Exception {
		Opcionpregunta opcPre=findOpcionpreguntaById(id_opcion_pregunta);
		if (opcPre== null) {
			throw new Exception("Error al cargar la opciòn pregunta.");
		}
		
		em.remove(opcPre);
	}
	public void eliminarPreguntamodulo(long id_pregunta_modulo) throws Exception {
		Preguntamodulo preM= findPreguntamoduloById(id_pregunta_modulo);
		if (preM== null) {
			throw new Exception("Error al cargar la pregunta mòdulo.");
		}
		boolean existePreguntamodulo= existePreguntamoduloenOpcionPregunta(id_pregunta_modulo);
		if (existePreguntamodulo) {
			throw new Exception("La pregunta mòdulo està siendo utilizada en opciòn pregunta.");
		}
		boolean existePreguntamodulo1= existePreguntamoduloenRespuestapregunta(id_pregunta_modulo);
		if (existePreguntamodulo1) {
			throw new Exception("La pregunta mòdulo està siendo utilizada en respuesta pregunta.");
		}
		em.remove(preM);
	}
	public void eliminarRespuestapregunta(long id_respuesta_pregunta) throws Exception {
		Respuestapregunta respP=findRespuestapreguntaById(id_respuesta_pregunta);
		if (respP== null) {
			throw new Exception("Error al cargar la respuesta pregunta.");
		}
		em.remove(respP);
	}

	/***
	 * mï¿½todos de als dimensiones a evaluar con respecto a la pregunta
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
	public Modulo findModuloById(long id_modulo) {
		Modulo mo= em.find(Modulo.class, id_modulo);
		return mo;

	}

	public List<Dimension> findalDimensionbyIdcuestionario(int id_cuestionario) {

		String JPQL = "SELECT d FROM Dimension d WHERE d.cuestionario.idCuestionario=?1";
		Query query = em.createQuery(JPQL, Dimension.class);
		query.setParameter(1, id_cuestionario);
		@SuppressWarnings("unchecked")
		List<Dimension> lista = query.getResultList();
		return lista;
	}

	@SuppressWarnings("unchecked")
	public boolean existeNombreOpcionPregunta(long idPregunta, String opcion) {

		String JPQL = "SELECT o FROM Opcionpregunta o WHERE o.opcion=?1 and " + "o.preguntamodulo.idPregunta=?2";
		Query query = em.createQuery(JPQL, Opcionpregunta.class);
		query.setParameter(1, opcion);
		query.setParameter(2, idPregunta);
		List<Opcionpregunta> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}
	@SuppressWarnings("unchecked")
	public boolean existeNombreRespuestaPregunta(long idPregunta, String respuesta) {

		String JPQL = "SELECT r FROM Respuestapregunta r "
				+ "WHERE r.respuesta=?1 and " + "r.preguntamodulo.idPregunta=?2";
		Query query = em.createQuery(JPQL, Respuestapregunta.class);
		query.setParameter(1, respuesta);
		query.setParameter(2, idPregunta);
		List<Respuestapregunta> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}
	@SuppressWarnings("unchecked")
	public boolean preguntaContestada(long idPregunta) {

		String JPQL = "SELECT r FROM Respuestapregunta r "
				+ "WHERE " + "r.preguntamodulo.idPregunta=?1";
		Query query = em.createQuery(JPQL, Respuestapregunta.class);
		query.setParameter(1, idPregunta);
		List<Respuestapregunta> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}
	
	@SuppressWarnings("unchecked")
	public boolean existeNombrePreguntaModulo(long idModulo, String pregunta) {

		String JPQL = "SELECT p FROM Preguntamodulo p "
				+ "WHERE p.pregunta=?1 and " + "p.modulo.idModulo=?2";
		Query query = em.createQuery(JPQL, Preguntamodulo.class);
		query.setParameter(1, pregunta);
		query.setParameter(2, idModulo);
		List<Preguntamodulo> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
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

	public void ingresarOpcionpregunta(Opcionpregunta opcionPregunta, long id_pregunta_modulo_fk) throws Exception {

		if (id_pregunta_modulo_fk == 0) {
			throw new Exception("Seleccione una Dimensiòn Pregunta");
		}
		boolean existeOpcionPregunta = existeNombreOpcionPregunta(id_pregunta_modulo_fk,
				opcionPregunta.getOpcion());
		if (existeOpcionPregunta) {
			throw new Exception("La opciòn pregunta" + opcionPregunta.getOpcion() + " ya existe");
		}

		Preguntamodulo preMo = managerCuestionario
				.findPreguntamoduloById(id_pregunta_modulo_fk);
		Opcionpregunta opcPre = new Opcionpregunta();
		opcPre.setOpcion(opcionPregunta.getOpcion());
		opcPre.setPreguntamodulo(preMo);
		em.persist(opcPre);
	}
	public void ingresarPreguntamodulo(Preguntamodulo preguntaModulo,long id_modulo_fk) throws Exception {
System.out.println("ENTRA");
		if (id_modulo_fk== 0) {
			throw new Exception("Seleccione un Módulo");
		}
		System.out.println("ENTRA2");
		boolean existePreguntaModulo= existeNombrePreguntaModulo(id_modulo_fk,preguntaModulo.getPregunta());
		if (existePreguntaModulo) {
			throw new Exception("La pregunta módulo " + preguntaModulo.getPregunta()+ " ya existe");
		}
		Modulo m=new Modulo();
		m=findModuloById(id_modulo_fk);
		Preguntamodulo preM=new Preguntamodulo();
		preM.setModulo(m);
		preM.setPregunta(preguntaModulo.getPregunta());

		System.out.println("ENTRA3");
		em.persist(preM);
	}
	public void ingresarRespuestapregunta(Respuestapregunta respuestaPregunta, long id_pregunta_modulo_fk) throws Exception {

		if (id_pregunta_modulo_fk== 0) {
			throw new Exception("Seleccione una Pregunta módulo");
		}
		boolean existeRespuestaPregunta= existeNombreRespuestaPregunta(id_pregunta_modulo_fk,respuestaPregunta.getRespuesta());
		if (existeRespuestaPregunta) {
			throw new Exception("La respuesta pregunta " + respuestaPregunta.getRespuesta()+ " ya existe");
		}
		boolean existeRespuesta=preguntaContestada(id_pregunta_modulo_fk);

		Preguntamodulo preMo = managerCuestionario
				.findPreguntamoduloById(id_pregunta_modulo_fk);
		if (existeRespuesta) {
			throw new Exception("La pregunta " + preMo.getPregunta()+ " ya tiene una respuesta");
		}

		Respuestapregunta resPreg= new Respuestapregunta();
		resPreg.setRespuesta(respuestaPregunta.getRespuesta());
		resPreg.setPreguntamodulo(preMo);
		em.persist(resPreg);
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
			throw new Exception("La Dimension estï¿½ siendo utilizada en dimension pregunta!");
		}
		boolean existeDimensionReporte = existeDimensionenReporte(id_dimen);
		if (existeDimensionReporte) {
			throw new Exception("La Dimension estï¿½ siendo utilizada en Reportes no se puede eliminar");
		}
		em.remove(dimenN);
	}

	/***
	 * mï¿½todos del ingreso de Preguntas
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

	public void editarPreguntamodulo(Preguntamodulo pregN,long id_modulo_fk) throws Exception {
		Preguntamodulo pregA = findPreguntamoduloById(pregN.getIdPregunta());
		if (pregA == null) {
			throw new Exception("Error al cargar la pregunta módulo");
		}
		if (id_modulo_fk==0) {
			throw new Exception("Error al cargar el módulo id 0");
		}
		Modulo m=new Modulo();
		
		m=managerCuestionario.findModuloById(id_modulo_fk);
		if (m==null) {
			throw new Exception("No existe el módulo");
		}
		if ((pregA.getPregunta().equals(pregN.getPregunta()))&&(pregA.getModulo().getIdModulo()==id_modulo_fk)) {
		return;
			}else {
			boolean existeNombrePreguntaModulo= existeNombrePreguntaModulo(id_modulo_fk, pregN.getPregunta());
			if (existeNombrePreguntaModulo) {
				throw new Exception("Ya existe la pregunta módulo : " + pregN.getPregunta());
			}else {
				pregA.setModulo(m);
				pregA.setPregunta(pregN.getPregunta());
				em.merge(pregA);
			}
			}
	}
	public void editarOpcionpregunta(Opcionpregunta opcN,long id_pregunta_modulo_fk) throws Exception {
		Opcionpregunta opcA = findOpcionpreguntaById(opcN.getIdOpcionpregunta());
		if (opcA == null) {
			throw new Exception("Error al cargar la opción pregunta");
		}
		if (id_pregunta_modulo_fk==0) {
			throw new Exception("Error al cargar la pregunta módulo = 0");
		}
		
		Preguntamodulo preM=new Preguntamodulo();
		
		preM=managerCuestionario.findPreguntamoduloById(id_pregunta_modulo_fk);
		if (preM==null) {
			throw new Exception("No existe la pregunta módulo");
		}
		if ((opcA.getOpcion().equals(opcN.getOpcion()))&&(opcA.getPreguntamodulo().getIdPregunta()==id_pregunta_modulo_fk)) {
		return;
			}else {
			boolean existeNombreOpcionPregunta= existeNombreOpcionPregunta(id_pregunta_modulo_fk, opcN.getOpcion());
			if (existeNombreOpcionPregunta) {
				throw new Exception("Ya existe la opción pregunta: " + opcN.getOpcion());
			}else {
				opcA.setPreguntamodulo(preM);
				opcA.setOpcion(opcN.getOpcion());
				em.merge(opcA);
			}
			}
	}
	
	public void editarRespuestapregunta(Respuestapregunta respN,long id_pregunta_modulo_fk) throws Exception {
		Respuestapregunta respA = findRespuestapreguntaById(respN.getIdRespuestapregunta());
		if (respA == null) {
			throw new Exception("Error al cargar la respuesta pregunta");
		}
		if (id_pregunta_modulo_fk==0) {
			throw new Exception("Error al cargar la pregunta módulo = 0");
		}
		Preguntamodulo preM=new Preguntamodulo();
		
		preM=managerCuestionario.findPreguntamoduloById(id_pregunta_modulo_fk);
		if (preM==null) {
			throw new Exception("No existe la pregunta módulo");
		}
		if ((respA.getRespuesta().equals(respN.getRespuesta()))&&(respA.getPreguntamodulo().getIdPregunta()==id_pregunta_modulo_fk)) {
		return;
			}else {
			boolean existeNombreRespuestaPregunta= existeNombreRespuestaPregunta(id_pregunta_modulo_fk, respN.getRespuesta());
			if (existeNombreRespuestaPregunta) {
				throw new Exception("Ya existe la respuesta pregunta: " + respN.getRespuesta());
			}else {
				respA.setPreguntamodulo(preM);
				respA.setRespuesta(respN.getRespuesta());
				em.merge(respA);
			}
			}
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
			throw new Exception("La Pregunta está siendo utilizada en dimensión");
		}
		em.remove(pregN);
	}

	/***
	 * mï¿½todos del ingreso de Dimen_Pregunta
	 */

	public List<DimensionPregunta> findAllDimensionPreguntaes() {

		Query q = em.createQuery("SELECT d FROM DimensionPregunta d", DimensionPregunta.class);
		@SuppressWarnings("unchecked")
		List<DimensionPregunta> listaDimensionPreguntas = q.getResultList();
		return listaDimensionPreguntas;
	}

	public List<DimensionPregunta> findAllDimensionByIdDimension(int id_dimen) {
		Query q = em.createQuery("SELECT d FROM DimensionPregunta d WHERE d.dimension.idDimension=" + id_dimen,
				DimensionPregunta.class);
		@SuppressWarnings("unchecked")
		List<DimensionPregunta> listaDimensionPreguntas = q.getResultList();
		return listaDimensionPreguntas;
	}

	public List<CursoModulo> findAllModulosByIdCurso(long id_curso) {
		Query q = em.createQuery("SELECT c FROM CursoModulo c WHERE c.curso.idCurso=" + id_curso+" order by c.ordenCurso ", CursoModulo.class);
		@SuppressWarnings("unchecked")
		List<CursoModulo> ListaCursoModulos = q.getResultList();

		return ListaCursoModulos;
	}
	public List<Modulo> findAllModulos() {
		Query q = em.createQuery("SELECT m FROM Modulo m" , Modulo.class);
		@SuppressWarnings("unchecked")
		List<Modulo> ListaModulos= q.getResultList();

		return ListaModulos;
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
		boolean dimensionPregunta = existeDimensionPregunta(id_dimen, id_preg);
		if (dimensionPregunta) {
			throw new Exception("Ya existe el cuestionario con la pregunta " + pregun.getDescripcion()
					+ " y dimension: " + dimen.getDescripcion());
		}

		DimensionPregunta ndimpreg = new DimensionPregunta();
		ndimpreg.setDimension(dimen);
		ndimpreg.setPregunta(pregun);

		em.persist(ndimpreg);
	}

	public void editarDimensionPregunta(DimensionPregunta pregA, int id_fk_dimension, int id_fk_pregunta)
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

	/* metodos de DTO */

	public List<PreguntaDimensionDTO> cargarListaPreguntasRespuestas(List<DimensionPregunta> listaDimenPregun) {

		List<PreguntaDimensionDTO> listaDto = new ArrayList<PreguntaDimensionDTO>();
		for (DimensionPregunta dimenPre : listaDimenPregun) {
			PreguntaDimensionDTO obj = new PreguntaDimensionDTO();
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

	public List<CuestionarioDTO> cargarCuestionarios(List<Cuestionario> listaCuestionarios) {
		List<CuestionarioDTO> cuestinariosDto = new ArrayList<>();

		for (Cuestionario cues : listaCuestionarios) {
			CuestionarioDTO cuesDto = new CuestionarioDTO();
			cuesDto.setIdCuestionario(cues.getIdCuestionario());
			cuesDto.setDescripcion(cues.getDescripcion());
			cuesDto.setDimensions(cues.getDimensions());
			cuesDto.setOpcions(cues.getOpcions());

			List<Dimension> dimensiones = findalDimensionbyIdcuestionario(cues.getIdCuestionario());
			cuesDto.setListaDimensionesDto(cargarDimensiones(dimensiones));
			cuestinariosDto.add(cuesDto);
		}
		return cuestinariosDto;
	}

	public List<DimensionDTO> cargarDimensiones(List<Dimension> listaDimensiones) {
		List<DimensionDTO> dimensionesDto = new ArrayList<>();

		for (Dimension dim : listaDimensiones) {
			DimensionDTO dimenDto = new DimensionDTO();
			dimenDto.setCuestionario(dim.getCuestionario());
			dimenDto.setDescripcion(dim.getDescripcion());
			dimenDto.setDimensionPreguntas(dim.getDimensionPreguntas());
			dimenDto.setIdDimension(dim.getIdDimension());
			List<DimensionPregunta> dimensionesPre = findAllDimensionByIdDimension(dim.getIdDimension());
			dimenDto.setListaDimensionesPreguntaDto(cargarDimensionesPreguntas(dimensionesPre));
			dimensionesDto.add(dimenDto);
		}
		return dimensionesDto;
	}

	public List<DimensionPreguntaDTO> cargarDimensionesPreguntas(List<DimensionPregunta> listaDimensionesPreguntas) {
		List<DimensionPreguntaDTO> dimensionesPreDto = new ArrayList<>();

		for (DimensionPregunta dimPre : listaDimensionesPreguntas) {
			DimensionPreguntaDTO dimenPreDto = new DimensionPreguntaDTO();
			dimenPreDto.setDimension(dimPre.getDimension());
			dimenPreDto.setIdPreguntaDimension(dimPre.getIdPreguntaDimension());
			dimenPreDto.setPregunta(dimPre.getPregunta());
			List<Opcion> opcionesByCuestionario = findAllOpcionesByCuestionario(
					dimPre.getDimension().getCuestionario().getIdCuestionario());
			dimenPreDto.setListaOpciones(opcionesByCuestionario);
			dimensionesPreDto.add(dimenPreDto);

		}
		return dimensionesPreDto;
	}

	public String resultadoTest(List<DimensionDTO> listaDimensionesDto) throws Exception {
		if (listaDimensionesDto.isEmpty()) {
			throw new Exception("Error lista vacï¿½a");
		} else {
			String respuesta = "";
			System.out.println("Dimensiones" + listaDimensionesDto.size());
			for (DimensionDTO dimensionDTO : listaDimensionesDto) {
				respuesta = respuesta + dimensionDTO.getCuestionario().getIdCuestionario() + "-";
				System.out.println("idCues=" + dimensionDTO.getCuestionario().getIdCuestionario());
				respuesta = respuesta + dimensionDTO.getIdDimension() + "|";
				System.out.println("idDim=" + dimensionDTO.getIdDimension());

				System.out.println("este es el tamano de" + dimensionDTO.getDescripcion() + "#"
						+ dimensionDTO.getListaDimensionesPreguntaDto().size());

				for (DimensionPreguntaDTO dimP : dimensionDTO.getListaDimensionesPreguntaDto()) {

					respuesta = respuesta + dimP.getPregunta().getIdPregunta() + ",";
					System.out.println("preg=" + dimP.getPregunta().getIdPregunta());
					respuesta = respuesta + dimP.getValor() + "/";
					System.out.println("resp=" + dimP.getValor());

				}

			}
			return respuesta;
		}
	}

// Metodo que me devuelve los reportes
	public List<Reporte> findAllReporte() {

		Query q = em.createQuery("SELECT r FROM Reporte r", Reporte.class);
		@SuppressWarnings("unchecked")
		List<Reporte> listaReporte = q.getResultList();
		return listaReporte;
	}

	public List<Reporte> findResultadosTestbyUsuario(long id_user) {
		String JPQL = "SELECT r FROM Reporte r WHERE r.usuario.idUsuario=?1";
		Query query = em.createQuery(JPQL, Reporte.class);
		query.setParameter(1, id_user);

		@SuppressWarnings("unchecked")
		List<Reporte> listaReporte = query.getResultList();
		return listaReporte;

	}

	/**
	 * Me devuelve la lista de Reportes de los usuarios Ingresados por su id de id
	 * de usuario.
	 */

// MÃ©todo que me devuelve Reportes por id usuario
	public Reporte findReporteByIdUsuario(long id_usuario) {
		String JPQL = "SELECT r FROM Reporte r WHERE r.usuario.idUsuario=?1";
		Query query = em.createQuery(JPQL, Reporte.class);
		query.setParameter(1, id_usuario);
		Reporte Reporte;
		Reporte = (Reporte) query.getSingleResult();
		return Reporte;
	}

	@SuppressWarnings("unused")
	public void ingresarReporte(int id_dimension, String respuesta, double valor, Date fecha_realizacion,
			long id_usuario) throws Exception {

		Reporte nreport = new Reporte();

		Usuario user = managerAdministrador.findUsuarioById(id_usuario);
		Dimension dime = managerCuestionario.findDimensionById(id_dimension);
		nreport.setResultado(respuesta);
		BigDecimal a = new BigDecimal(valor);

		nreport.setDimension(dime);
		nreport.setFecha(fecha_realizacion);
		nreport.setUsuario(user);
		em.persist(nreport);
	}

	public String reporteTest(List<DimensionDTO> listaDimensionesDto) throws Exception {
		if (listaDimensionesDto.isEmpty()) {
			throw new Exception("Error lista vacï¿½a");
		} else {
			String respuesta = "";
			for (DimensionDTO dimensionDTO : listaDimensionesDto) {

				respuesta = respuesta + "|" + dimensionDTO.getCuestionario().getIdCuestionario() + "-";
				respuesta = respuesta + "|" + dimensionDTO.getIdDimension() + "|";
				for (DimensionPreguntaDTO dimP : dimensionDTO.getListaDimensionesPreguntaDto()) {
					respuesta = respuesta + dimP.getPregunta().getIdPregunta() + ",";
					System.out.println("preg=" + dimP.getPregunta().getIdPregunta());
					respuesta = respuesta + dimP.getValor() + "/";
					System.out.println("resp=" + dimP.getValor());

				}

			}
			return respuesta;
		}
	}

	@SuppressWarnings("unused")
	public String calcularRespuestaCuestionario(List<DimensionDTO> listaDimensionesDto) throws Exception {
		String respuesta = "";
		int a = 0, b = 0;

		String[] modulosResp = new String[listaDimensionesDto.size()];

		if (listaDimensionesDto.isEmpty()) {
			throw new Exception("Error lista vacía");
		} else {
			for (DimensionDTO dimensionDTO : listaDimensionesDto) {
				if (dimensionDTO.getCuestionario().getIdCuestionario() == 1) {
					for (DimensionPreguntaDTO dimPDto : dimensionDTO.getListaDimensionesPreguntaDto()) {

						if (dimPDto.getValor() <= 3) {
							a++;
						} else {
							b++;
						}
					}
					double r;
					r = calcularPorcentaje(a, b, dimensionDTO.getListaDimensionesPreguntaDto().size());
					if (a > b) {
						respuesta = "Tiene bajo grado de capacidad en: " + dimensionDTO.getDescripcion()
								+ " con el porcentaje de " + r + "%";
						return respuesta;
					} else {
						respuesta = "Tiene alto grado de capacidad en: " + dimensionDTO.getDescripcion()
								+ " con el porcentaje de " + r + "%";
						return respuesta;
					}
				} else if (dimensionDTO.getCuestionario().getIdCuestionario() == 2) {
					int sumEstres = 0;
					for (DimensionPreguntaDTO dimPDto : dimensionDTO.getListaDimensionesPreguntaDto()) {
						sumEstres = sumEstres + dimPDto.getValor();
					}
					System.out.println("Estres sumatoria" + sumEstres);

					int menEstres = sumEstres - 16;

					if (menEstres > 24) {
						respuesta = "Vulnerable al estrés.";
					} else if (menEstres >= 40 || menEstres <= 60) {
						respuesta = "Seriamente vulnerable al ï¿½stres.";
					} else if (menEstres > 60) {
						respuesta = "Extremadamente vulnerable al ï¿½stres.";
					} else {
						respuesta = "Baja vulnerabilidad al ï¿½stres.";
					}
				} else if (dimensionDTO.getCuestionario().getIdCuestionario() == 3) {
					return respuesta = "respuesta se guardo";
				}
			}
		}

		return "";
	}

	public double calcularPorcentaje(int a, int b, int tamanio) {
		double r;
		if (a > b) {
			r = (a * 100) / tamanio;
			return r;
		} else {
			r = (b * 100) / tamanio;
			return r;
		}
	}

}
