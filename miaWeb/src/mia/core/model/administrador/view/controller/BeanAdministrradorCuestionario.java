package mia.core.model.administrador.view.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.event.FlowEvent;

import mia.core.model.administrador.ManagerAdministrador;
import mia.core.model.cuestionario.ManagerCuestionario;
import mia.core.model.cuestionario.dto.CuestionarioDTO;
import mia.core.model.cuestionario.dto.DimensionDTO;
import mia.core.model.cuestionario.dto.InicioDTO;
import mia.core.model.cuestionario.dto.PreguntaDimensionDTO;
import mia.core.model.entities.Cuestionario;
import mia.core.model.entities.Dimension;
import mia.core.model.entities.DimensionPregunta;
import mia.core.model.entities.Modulo;
import mia.core.model.entities.Opcion;
import mia.core.model.entities.Opcionpregunta;
import mia.core.model.entities.Pregunta;
import mia.core.model.entities.Preguntamodulo;
import mia.core.model.entities.Respuestapregunta;
import mia.core.model.entities.Usuario;
import mia.core.model.reporte.ManagerReporte;
import mia.modulos.view.util.JSFUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class BeanAdministrradorCuestionario implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean atras;
	private boolean adelante;
	private boolean finalizarTest;
	private int contador;
private long id_modulo_fk;
private long id_pregunta_modulo_fk;
 private Preguntamodulo preguntaModuloE;
	private Opcionpregunta opcionPreIng= new Opcionpregunta();
	private Opcionpregunta opcionPreE= new Opcionpregunta();
	private Respuestapregunta respPregIng= new Respuestapregunta();
	private Respuestapregunta respPregE= new Respuestapregunta();
	private Preguntamodulo pregunModIng= new Preguntamodulo();
	private Preguntamodulo pregunModE= new Preguntamodulo();
	private List<Modulo>listaModulos;
//OPCIONPREGUNTA
private 	List<Respuestapregunta> listaRespPreg;
private 	List<Respuestapregunta> filteredlistaRespPreg;
private 	List<Preguntamodulo> listaPreMo;
 private 	List<Preguntamodulo> filteredlistaPreMo;
private 	List<Preguntamodulo> listaPreguntasSinRespuesta;
private 	List<Opcionpregunta> listaOpcPre;
private 	List<Opcionpregunta> filteredlistaOpcPre;
private 	List<Opcionpregunta> listaOpcPreE;

//FICHA_
	
	private Cuestionario cuestionario = new Cuestionario();
	private Dimension dimension = new Dimension();
	private Opcion opcion = new Opcion();
	private Pregunta pregunta = new Pregunta();
	private DimensionPregunta dimenpregunta = new DimensionPregunta();
	private Dimension dimensionR;
	private Usuario usuarioE;
	private Cuestionario cuestionarioE;
	private Dimension dimensionE;
	private Opcion opcionE;
	private Pregunta preguntaE;
	private DimensionPregunta dimenpreguntaE;
	private boolean skip;
	private List<CuestionarioDTO> cuestionarioDto = new ArrayList<>();
	private List<DimensionDTO> listaDimensionesDto = new ArrayList<>();
	private List<DimensionDTO> listaDimensionActualDto = new ArrayList<>();
	private DimensionDTO dimensionActDto;
//claves foraneas
	private int id_cuestionarioopc_fk;
	private int id_dimension_fk;
	private int id_dimensioncues_fk;
	private int id_pregunta_fk;
	private int id_rol_fk;
	@EJB
	private ManagerCuestionario managerCuestionario;
//ROLES
	private List<Cuestionario> cuestionarios;
	private List<Dimension> dimensiones;
	private List<Opcion> opciones;
	private List<Pregunta> preguntas;
	private List<DimensionPregunta> dimensionpreguntas;
	private List<DimensionPregunta> dimensionpreguntaID;
	private List<PreguntaDimensionDTO> dimensionpreguntaIDDto;
	private InicioDTO inicioDTO = new InicioDTO();

	@EJB
	private ManagerAdministrador managerAdministrador;

	@EJB
	private ManagerReporte managerReporte;

	private List<Usuario> usuarios;

	@PostConstruct
	public void init() {
		try {
			listaModulos=managerCuestionario.findAllModulos();
			listaOpcPre=managerCuestionario.findAllOpcionpregunta();
			listaRespPreg=managerCuestionario.findAllRespuestapregunta();
			listaPreMo=managerCuestionario.findAllPreguntamodulo();
		   listaPreguntasSinRespuesta=managerCuestionario.findAllPreguntamodulo();
			cuestionarios = managerCuestionario.findAllCuestionarioes();
			cuestionarioDto = managerCuestionario.cargarCuestionarios(cuestionarios);
			inicioDTO.setListaCuestionariosDto(cuestionarioDto);
			dimensiones = managerCuestionario.findAllDimensiones();
			opciones = managerCuestionario.findAllOpciones();
			preguntas = managerCuestionario.findAllPreguntaes();
			dimensionpreguntas = managerCuestionario.findAllDimensionPreguntaes();

		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}
public void actionListenercargarOpcionesPreguntas() {
	listaOpcPreE=managerCuestionario.findAllOpcionpreguntabypregunta(id_pregunta_modulo_fk)	;
	if (listaOpcPreE.isEmpty()) {
		JSFUtil.crearMensajeError("Las opciones de la pregunta no existen \n"
				+ " Cont�ctese con el administrador");
	}
}
	public void regresarAtras() {
		listaDimensionesDto.set(contador, listaDimensionActualDto.get(0));
		contador--;
		estadoActualContador(contador);
		dimensionActDto = listaDimensionesDto.get(contador);
		listaDimensionActualDto = new ArrayList<>();
		listaDimensionActualDto.add(dimensionActDto);
	}

	public void regresarSiguiente() {
		listaDimensionesDto.set(contador, listaDimensionActualDto.get(0));
		contador++;
		estadoActualContador(contador);

		dimensionActDto = listaDimensionesDto.get(contador);

		listaDimensionActualDto = new ArrayList<>();
		listaDimensionActualDto.add(dimensionActDto);

	}

	public String actionListenerIniciarTest() {
		try {
			cuestionarioDto = managerCuestionario.cargarCuestionarios(cuestionarios);
			inicioDTO.setListaCuestionariosDto(cuestionarioDto);
			return "test.xhtml?faces-redirect=true";
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			return "";
		}
	}

	// ** Lista d e Opciones segun el cuestionario */
	public void estadoActualContador(int contadorC) {
		if (listaDimensionesDto.isEmpty()) {
			atras = false;
			adelante = false;
			finalizarTest = false;

		} else {
			if (contadorC == 0 && contadorC == (listaDimensionesDto.size() - 1)) {
				adelante = false;
				atras = false;
				finalizarTest = true;
			} else {
				if (contadorC > 0 && contadorC < (listaDimensionesDto.size() - 1)) {
					adelante = true;
					atras = true;
					finalizarTest = false;
				} else {
					if (contadorC == (listaDimensionesDto.size() - 1)) {
						adelante = false;
						atras = true;
						finalizarTest = true;

					} else {
						adelante = true;
						atras = false;

					}
				}
			}

		}

	}

	public String actionDimensionesbyCuestionario(CuestionarioDTO cuest) {
		try {
			if (cuest.getListaDimensionesDto().isEmpty()) {
				JSFUtil.crearMensajeError(
						"El cuestionario " + cuest.getDescripcion() + " no tiene m�dulos disponibles");
				return "";
			} else {
				listaDimensionesDto = cuest.getListaDimensionesDto();
				dimensionActDto = listaDimensionesDto.get(0);
				listaDimensionActualDto.add(dimensionActDto);

				contador = 0;
				estadoActualContador(contador);

				JSFUtil.crearMensajeInfo("Lista de Dimensiones de Cuestionnarios");

				return "prediagnostico?faces-redirect=true";
			}
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			return "";
		}

	}

	public void actionListenerIngresarCuestionario() {
		try {
			managerCuestionario.ingresarCuestionario(cuestionario);
			cuestionarios = managerCuestionario.findAllCuestionarioes();
			JSFUtil.crearMensajeInfo("Cuestionario creada correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerEliminarCuestionario(int id_cuestionario) {
		try {
			managerCuestionario.eliminarCuestionario(id_cuestionario);
			cuestionarios = managerCuestionario.findAllCuestionarioes();
			JSFUtil.crearMensajeInfo("Cuestionario eliminado correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}
	public void actionListenerEliminarOpcionpregunta(long id_opcion_pregunta) {
		try {
			managerCuestionario.eliminarOpcionpregunta(id_opcion_pregunta);
			listaOpcPre=managerCuestionario.findAllOpcionpregunta();
			JSFUtil.crearMensajeInfo("Opci�n pregunta eliminada correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}
	public void actionListenerEliminarPreguntamodulo(int id_pregunta_modulo) {
		try {
			managerCuestionario.eliminarPreguntamodulo(id_pregunta_modulo);
			listaPreMo=managerCuestionario.findAllPreguntamodulo();
			JSFUtil.crearMensajeInfo("Pregunta m�dulo eliminada correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}
	public void actionListenerEliminarRespuestapregunta(long id_respuesta_pregunta) {
		try {
			managerCuestionario.eliminarRespuestapregunta(id_respuesta_pregunta);
			listaRespPreg=managerCuestionario.findAllRespuestapregunta();
			JSFUtil.crearMensajeInfo("Respuesta pregunta eliminada correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public void actionListenerCargarCuestionario(Cuestionario cuestC) {
		try {
			cuestionarioE = cuestC;
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}
	public void actionListenerCargarOpcionpregunta(Opcionpregunta opcionP) {
		try {
			opcionPreE = opcionP;
			id_pregunta_modulo_fk=opcionP.getPreguntamodulo().getIdPregunta();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}

	public void actionListenerEditarCuestionario() {
		try {
			managerCuestionario.editarCuestionario(cuestionarioE);
			JSFUtil.crearMensajeInfo("Cuestionario editado correctamente");
		} catch (Exception e) {
			cuestionarios = managerCuestionario.findAllCuestionarioes();
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}
	public void actionListenerCargarRespuestapregunta(Respuestapregunta resPreg) {
		
		respPregE=resPreg;
		preguntaModuloE=resPreg.getPreguntamodulo();
		id_pregunta_modulo_fk=resPreg.getPreguntamodulo().getIdPregunta();
		listaOpcPreE=managerCuestionario.findAllOpcionpreguntabypregunta(id_pregunta_modulo_fk)	;
	}
	public void actionListenerEditarPreguntamodulo() {
		try {
		managerCuestionario.editarPreguntamodulo(pregunModE, id_modulo_fk);
		listaPreMo=managerCuestionario.findAllPreguntamodulo();
		JSFUtil.crearMensajeInfo("Pregunta m�dulo editada correctamente.");
		} catch (Exception e) {
			listaPreMo=managerCuestionario.findAllPreguntamodulo();
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}
	public void actionListenerEditarRespuestaPregunta() {
		try {
			managerCuestionario.editarRespuestapregunta(respPregE, id_pregunta_modulo_fk);
			listaRespPreg=managerCuestionario.findAllRespuestapregunta();
			JSFUtil.crearMensajeInfo("Respuesta pregunta editada correctamente.");
			} catch (Exception e) {
				listaRespPreg=managerCuestionario.findAllRespuestapregunta();
				JSFUtil.crearMensajeError(e.getMessage());
			}
	}
	public void actionListenerEditarOpcionPregunta() {
		try {
			managerCuestionario.editarOpcionpregunta(opcionPreE, id_pregunta_modulo_fk);
			listaOpcPre=managerCuestionario.findAllOpcionpregunta();
			JSFUtil.crearMensajeInfo("Opci�n pregunta editada correctamente.");
			} catch (Exception e) {
				listaOpcPre=managerCuestionario.findAllOpcionpregunta();
				JSFUtil.crearMensajeError(e.getMessage());
			}
	}
	/**
	 * metodos de las opciones del Cuestionario
	 */

	public String actionIngresarOpcion() {
		try {

			managerCuestionario.ingresarOpcion(opcion, id_cuestionarioopc_fk);
			opciones = managerCuestionario.findAllOpciones();
			JSFUtil.crearMensajeInfo("Opcion creada correctamente");
			opcion = new Opcion();
			return "index.xhtml?faces-redirect=true";
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			return "";
		}

	}

	public void actionListenerCargarOpcion(Opcion opcionC) {
		try {
			opcionE = opcionC;
			id_cuestionarioopc_fk = opcionC.getCuestionario().getIdCuestionario();

		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}

	public void actionListenerEliminarOpcion(int id_opciones) {
		try {
			managerCuestionario.eliminarOpcion(id_opciones);
			opciones = managerCuestionario.findAllOpciones();
			JSFUtil.crearMensajeInfo("Opcion eliminado correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public void actionListenerEditarOpcion() {
		try {
			managerCuestionario.editarOpcion(opcionE, id_cuestionarioopc_fk);
			opciones = managerCuestionario.findAllOpciones();
			opcionE = new Opcion();
			id_cuestionarioopc_fk = 0;
			JSFUtil.crearMensajeInfo("Opcion editado correctamente");
		} catch (Exception e) {
			opciones = managerCuestionario.findAllOpciones();
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}

	/**
	 * public void actionListenerEditarDimensionPregunta() { try {
	 * managerCuestionario.editarDimensionPregunta(dimenpreguntaE, id_dimension_fk,
	 * id_pregunta_fk); dimensionpreguntas =
	 * managerCuestionario.findAllDimensionPreguntaes(); dimenpreguntaE= new
	 * DimensionPregunta(); id_dimension_fk=0; id_pregunta_fk=0;
	 * JSFUtil.crearMensajeInfo("DimensionPregunta editada correctamente"); } catch
	 * (Exception e) { dimensionpreguntas=
	 * managerCuestionario.findAllDimensionPreguntaes();
	 * JSFUtil.crearMensajeError(e.getMessage()); }
	 * 
	 * }
	 */

	/**
	 * metodos del crud de preguntas
	 */

	public void actionListenerIngresarPregunta() {
		try {
			managerCuestionario.ingresarPregunta(pregunta);
			preguntas = managerCuestionario.findAllPreguntaes();
			JSFUtil.crearMensajeInfo("Pregunta creada correctamente");

		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());

		}

	}

	public void actionListenerEliminarPregunta(int id_preguntaes) {
		try {
			managerCuestionario.eliminarPregunta(id_preguntaes);
			preguntas = managerCuestionario.findAllPreguntaes();
			JSFUtil.crearMensajeInfo("Pregunta eliminado correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public void actionListenerCargarPregunta(Pregunta pregC) {
		try {
			preguntaE = pregC;
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}
	public void actionListenerCargarPreguntamodulo(Preguntamodulo pregM) {
		try {
			pregunModE= pregM;
			id_modulo_fk=pregM.getModulo().getIdModulo();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}

	public void actionListenerEditarPregunta() {
		try {
			managerCuestionario.editarPregunta(preguntaE);
			JSFUtil.crearMensajeInfo("Pregunta editada correctamente");
		} catch (Exception e) {
			preguntas = managerCuestionario.findAllPreguntaes();
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}

	/**
	 * metodos del crud de dimensiones
	 */
	public String actionIngresarOpcionpregunta() {
		try {

			managerCuestionario.ingresarOpcionpregunta(opcionPreIng,id_pregunta_modulo_fk);
			listaOpcPre=managerCuestionario.findAllOpcionpregunta();
			JSFUtil.crearMensajeInfo("Opci�n pregunta creada correctamente");
			opcionPreIng = new Opcionpregunta();
			return "";
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			return "";
		}

	}
	public String actionIngresarPreguntamodulo() {
		try {
			managerCuestionario.ingresarPreguntamodulo(pregunModIng,id_modulo_fk);
listaPreMo=managerCuestionario.findAllPreguntamodulo();
			JSFUtil.crearMensajeInfo("Pregunta m�dulo creada correctamente");
			pregunModIng = new Preguntamodulo();
			return "";
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			return "";
		}

	}
	public String actionIngresarRespuestaPregunta() {
		try {
			managerCuestionario.ingresarRespuestapregunta(respPregIng,id_pregunta_modulo_fk);
			listaRespPreg=managerCuestionario.findAllRespuestapregunta();
			JSFUtil.crearMensajeInfo("Respuesta pregunta creada correctamente");
			respPregIng = new Respuestapregunta();
			return "";
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			return "";
		}

	}
	public String actionIngresarDimension() {
		try {

			managerCuestionario.ingresarDimension(dimension, id_cuestionarioopc_fk);
			dimensiones = managerCuestionario.findAllDimensiones();
			JSFUtil.crearMensajeInfo("Dimension creada correctamente");
			dimension = new Dimension();
			return "index.xhtml?faces-redirect=true";
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			return "";
		}

	}

	public void actionListenerEliminarDimension(int id_dimensiones) {
		try {
			managerCuestionario.eliminarDimension(id_dimensiones);
			dimensiones = managerCuestionario.findAllDimensiones();
			JSFUtil.crearMensajeInfo("Dimension eliminado correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public void actionListenerCargarDimension(Dimension dimenC) {
		try {
			dimensionE = dimenC;
			id_cuestionarioopc_fk = dimenC.getCuestionario().getIdCuestionario();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}

	public void actionListenerEditarDimension() {
		try {
			managerCuestionario.editarDimension(dimensionE, id_cuestionarioopc_fk);
			dimensiones = managerCuestionario.findAllDimensiones();
			dimensionE = new Dimension();
			id_dimension_fk = 0;
			id_cuestionarioopc_fk = 0;
			JSFUtil.crearMensajeInfo("Dimension editada correctamente");
		} catch (Exception e) {
			dimensiones = managerCuestionario.findAllDimensiones();
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}

	/**
	 * metodos del crud de dimensione_preguntas
	 */

	public String actionIngresarDimensionPregunta() {
		try {

			managerCuestionario.ingresarDimensionPregunta(id_dimension_fk, id_pregunta_fk);
			dimensionpreguntas = managerCuestionario.findAllDimensionPreguntaes();
			JSFUtil.crearMensajeInfo("Dimension y Pregunta creada correctamente");
			dimenpregunta = new DimensionPregunta();
			return "index.xhtml?faces-redirect=true";
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			return "";
		}

	}

	public void actionListenerEliminarDimensionPregunta(long id_dimenpregunta) {
		try {
			managerCuestionario.eliminarDimensionPregunta(id_dimenpregunta);
			dimensionpreguntas = managerCuestionario.findAllDimensionPreguntaes();
			JSFUtil.crearMensajeInfo("Dimension y Pregunta eliminado correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public String actionDimensionPreguntabyIdDimension(Dimension dimensionC) {
		try {
			dimensionR = dimensionC;
			dimensionpreguntaID = managerCuestionario.findAllDimensionByIdDimension(dimensionC.getIdDimension());
			dimensionpreguntaIDDto = managerCuestionario.cargarListaPreguntasRespuestas(dimensionpreguntaID);
			JSFUtil.crearMensajeInfo("lista de Preguntas por Dimension");

			return "prediagnostico?faces-redirect=true";
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			return "";
		}
	}

	public void actionListenerCargarDimensionPregunta(DimensionPregunta dimenpreguntaC) {
		try {
			dimenpreguntaE = dimenpreguntaC;
			id_dimension_fk = dimenpreguntaC.getDimension().getIdDimension();
			id_pregunta_fk = dimenpreguntaC.getPregunta().getIdPregunta();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}

	public void actionListenerEditarDimensionPregunta() {
		try {
			managerCuestionario.editarDimensionPregunta(dimenpreguntaE, id_dimension_fk, id_pregunta_fk);
			dimensionpreguntas = managerCuestionario.findAllDimensionPreguntaes();
			dimenpreguntaE = new DimensionPregunta();
			id_dimension_fk = 0;
			id_pregunta_fk = 0;
			JSFUtil.crearMensajeInfo("DimensionPregunta editada correctamente");
		} catch (Exception e) {
			dimensionpreguntas = managerCuestionario.findAllDimensionPreguntaes();
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}

//** Lista d e Opciones segun el cuestionario */	

	public String actionDimensionesbyCuestionario(Cuestionario cuest) {
		try {
			dimensiones = managerCuestionario.findalDimensionbyIdcuestionario(cuest.getIdCuestionario());
			opciones = managerCuestionario.findAllOpcionesByCuestionario(cuest.getIdCuestionario());
			JSFUtil.crearMensajeInfo("Lista de Dimensiones de Cuestionnarios");

			return "dimensiones?faces-redirect=true";
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			return "";
		}

	}
/*
	public String actionRetornarbyCuestiono() {
		try {

			System.out.println("tamano=" + listaDimensionesDto.size());

			respuesta = managerCuestionario.resultadoTest(listaDimensionesDto);
			managerAdministrador.ingresarRespuesta(login.getLogin().getId_usuario(), respuesta, fechaRealizacion);
			for (DimensionDTO dDto : listaDimensionesDto) {
				respuestaReporte = managerReporte.calcularRespuestaDimension(dDto.getListaDimensionesPreguntaDto(),
						dDto);
				String respuesta = respuestaReporte[0];
				double valor = 0.0;
				if (respuestaReporte[1].length() != 0) {
					valor = Double.parseDouble(respuestaReporte[1]);
				}
				managerCuestionario.ingresarReporte(dDto.getIdDimension(), respuesta, valor, fechaRealizacion,
						login.getLogin().getId_usuario());

			}
			DimensionDTO di = listaDimensionesDto.get(0);
			JSFUtil.crearMensajeInfo("Cuestionario " + di.getCuestionario().getDescripcion() + " "
					+ di.getCuestionario().getIdCuestionario() + "" + " " + " ha sido realizado correctamente");
			JSFUtil.crearMensajeFastFinal();
			respuesta = "";
			listaDimensionesDto = new ArrayList<>();
			listaDimensionActualDto = new ArrayList<>();

			return "test?faces-redirect=true";
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			return "";
		}

	}
*/
	/*
	 * public String ActionDimensionPreguntabyCuestionario(Cuestionario cuest) { try
	 * { dimensiones=
	 * managerCuestionario.findalDimensionbyIdcuestionario(cuest.getIdCuestionario()
	 * ); int [] dimensiones; opciones=
	 * managerCuestionario.findAllOpcionesByCuestionario(cuest.getIdCuestionario());
	 * JSFUtil.crearMensajeInfo("Lista de Preguntas por Cuestionnario.");
	 * 
	 * //return "prediagnostico?faces-redirect=true";
	 * 
	 * } catch (Exception e) { JSFUtil.crearMensajeError(e.getMessage()); return "";
	 * }
	 * 
	 * }
	 * 
	 */
	//
	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false; // reset in case user goes back
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}

	public Cuestionario getCuestionario() {
		return cuestionario;
	}

	public void setCuestionario(Cuestionario cuestionario) {
		this.cuestionario = cuestionario;
	}

	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	public Opcion getOpcion() {
		return opcion;
	}

	public void setOpcion(Opcion opcion) {
		this.opcion = opcion;
	}

	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

	public DimensionPregunta getDimenpregunta() {
		return dimenpregunta;
	}

	public void setDimenpregunta(DimensionPregunta dimenpregunta) {
		this.dimenpregunta = dimenpregunta;
	}

	public Dimension getDimensionR() {
		return dimensionR;
	}

	public void setDimensionR(Dimension dimensionR) {
		this.dimensionR = dimensionR;
	}

	public Usuario getUsuarioE() {
		return usuarioE;
	}

	public void setUsuarioE(Usuario usuarioE) {
		this.usuarioE = usuarioE;
	}

	public Cuestionario getCuestionarioE() {
		return cuestionarioE;
	}

	public void setCuestionarioE(Cuestionario cuestionarioE) {
		this.cuestionarioE = cuestionarioE;
	}

	public Dimension getDimensionE() {
		return dimensionE;
	}

	public void setDimensionE(Dimension dimensionE) {
		this.dimensionE = dimensionE;
	}

	public Opcion getOpcionE() {
		return opcionE;
	}

	public void setOpcionE(Opcion opcionE) {
		this.opcionE = opcionE;
	}

	public Pregunta getPreguntaE() {
		return preguntaE;
	}

	public void setPreguntaE(Pregunta preguntaE) {
		this.preguntaE = preguntaE;
	}

	public DimensionPregunta getDimenpreguntaE() {
		return dimenpreguntaE;
	}

	public void setDimenpreguntaE(DimensionPregunta dimenpreguntaE) {
		this.dimenpreguntaE = dimenpreguntaE;
	}

	public int getId_cuestionarioopc_fk() {
		return id_cuestionarioopc_fk;
	}

	public void setId_cuestionarioopc_fk(int id_cuestionarioopc_fk) {
		this.id_cuestionarioopc_fk = id_cuestionarioopc_fk;
	}

	public int getId_dimension_fk() {
		return id_dimension_fk;
	}

	public void setId_dimension_fk(int id_dimension_fk) {
		this.id_dimension_fk = id_dimension_fk;
	}

	public int getId_dimensioncues_fk() {
		return id_dimensioncues_fk;
	}

	public void setId_dimensioncues_fk(int id_dimensioncues_fk) {
		this.id_dimensioncues_fk = id_dimensioncues_fk;
	}

	public int getId_pregunta_fk() {
		return id_pregunta_fk;
	}

	public void setId_pregunta_fk(int id_pregunta_fk) {
		this.id_pregunta_fk = id_pregunta_fk;
	}

	public List<Cuestionario> getCuestionarios() {
		return cuestionarios;
	}

	public void setCuestionarios(List<Cuestionario> cuestionarios) {
		this.cuestionarios = cuestionarios;
	}

	public List<Dimension> getDimensiones() {
		return dimensiones;
	}

	public void setDimensiones(List<Dimension> dimensiones) {
		this.dimensiones = dimensiones;
	}

	public List<Opcion> getOpciones() {
		return opciones;
	}

	public void setOpciones(List<Opcion> opciones) {
		this.opciones = opciones;
	}

	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}

	public List<DimensionPregunta> getDimensionpreguntas() {
		return dimensionpreguntas;
	}

	public void setDimensionpreguntas(List<DimensionPregunta> dimensionpreguntas) {
		this.dimensionpreguntas = dimensionpreguntas;
	}

	public List<DimensionPregunta> getDimensionpreguntaID() {
		return dimensionpreguntaID;
	}

	public void setDimensionpreguntaID(List<DimensionPregunta> dimensionpreguntaID) {
		this.dimensionpreguntaID = dimensionpreguntaID;
	}

	public List<PreguntaDimensionDTO> getDimensionpreguntaIDDto() {
		return dimensionpreguntaIDDto;
	}

	public void setDimensionpreguntaIDDto(List<PreguntaDimensionDTO> dimensionpreguntaIDDto) {
		this.dimensionpreguntaIDDto = dimensionpreguntaIDDto;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public List<CuestionarioDTO> getCuestionarioDto() {
		return cuestionarioDto;
	}

	public void setCuestionarioDto(List<CuestionarioDTO> cuestionarioDto) {
		this.cuestionarioDto = cuestionarioDto;
	}

	public InicioDTO getInicioDTO() {
		return inicioDTO;
	}

	public void setInicioDTO(InicioDTO inicioDTO) {
		this.inicioDTO = inicioDTO;
	}

	public List<DimensionDTO> getListaDimensionesDto() {
		return listaDimensionesDto;
	}

	public void setListaDimensionesDto(List<DimensionDTO> listaDimensionesDto) {
		this.listaDimensionesDto = listaDimensionesDto;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public boolean isAtras() {
		return atras;
	}

	public void setAtras(boolean atras) {
		this.atras = atras;
	}

	public boolean isAdelante() {
		return adelante;
	}

	public void setAdelante(boolean adelante) {
		this.adelante = adelante;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public int getId_rol_fk() {
		return id_rol_fk;
	}

	public void setId_rol_fk(int id_rol_fk) {
		this.id_rol_fk = id_rol_fk;
	}

	public List<DimensionDTO> getListaDimensionActualDto() {
		return listaDimensionActualDto;
	}

	public void setListaDimensionActualDto(List<DimensionDTO> listaDimensionActualDto) {
		this.listaDimensionActualDto = listaDimensionActualDto;
	}

	public DimensionDTO getDimensionActDto() {
		return dimensionActDto;
	}

	public void setDimensionActDto(DimensionDTO dimensionActDto) {
		this.dimensionActDto = dimensionActDto;
	}

	public boolean isFinalizarTest() {
		return finalizarTest;
	}

	public void setFinalizarTest(boolean finalizarTest) {
		this.finalizarTest = finalizarTest;
	}
/*
	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String[] getRespuestaReporte() {
		return respuestaReporte;
	}

	public void setRespuestaReporte(String[] respuestaReporte) {
		this.respuestaReporte = respuestaReporte;
	}
*/
	public Opcionpregunta getOpcionPreIng() {
		return opcionPreIng;
	}

	public void setOpcionPreIng(Opcionpregunta opcionPreIng) {
		this.opcionPreIng = opcionPreIng;
	}

	public Opcionpregunta getOpcionPreE() {
		return opcionPreE;
	}

	public void setOpcionPreE(Opcionpregunta opcionPreE) {
		this.opcionPreE = opcionPreE;
	}

	public Respuestapregunta getRespPregIng() {
		return respPregIng;
	}

	public void setRespPregIng(Respuestapregunta respPregIng) {
		this.respPregIng = respPregIng;
	}

	public Respuestapregunta getRespPregE() {
		return respPregE;
	}

	public void setRespPregE(Respuestapregunta respPregE) {
		this.respPregE = respPregE;
	}

	public Preguntamodulo getPregunModIng() {
		return pregunModIng;
	}

	public void setPregunModIng(Preguntamodulo pregunModIng) {
		this.pregunModIng = pregunModIng;
	}

	public Preguntamodulo getPregunModE() {
		return pregunModE;
	}

	public void setPregunModE(Preguntamodulo pregunModE) {
		this.pregunModE = pregunModE;
	}

	public List<Respuestapregunta> getListaRespPreg() {
		return listaRespPreg;
	}

	public void setListaRespPreg(List<Respuestapregunta> listaRespPreg) {
		this.listaRespPreg = listaRespPreg;
	}

	public List<Preguntamodulo> getListaPreMo() {
		return listaPreMo;
	}

	public void setListaPreMo(List<Preguntamodulo> listaPreMo) {
		this.listaPreMo = listaPreMo;
	}

	public List<Opcionpregunta> getListaOpcPre() {
		return listaOpcPre;
	}

	public void setListaOpcPre(List<Opcionpregunta> listaOpcPre) {
		this.listaOpcPre = listaOpcPre;
	}

	public List<Modulo> getListaModulos() {
		return listaModulos;
	}

	public void setListaModulos(List<Modulo> listaModulos) {
		this.listaModulos = listaModulos;
	}

	public long getId_modulo_fk() {
		return id_modulo_fk;
	}

	public void setId_modulo_fk(long id_modulo_fk) {
		this.id_modulo_fk = id_modulo_fk;
	}

	public long getId_pregunta_modulo_fk() {
		return id_pregunta_modulo_fk;
	}

	public void setId_pregunta_modulo_fk(long id_pregunta_modulo_fk) {
		this.id_pregunta_modulo_fk = id_pregunta_modulo_fk;
	}

	public List<Preguntamodulo> getListaPreguntasSinRespuesta() {
		return listaPreguntasSinRespuesta;
	}

	public void setListaPreguntasSinRespuesta(List<Preguntamodulo> listaPreguntasSinRespuesta) {
		this.listaPreguntasSinRespuesta = listaPreguntasSinRespuesta;
	}

	public Preguntamodulo getPreguntaModuloE() {
		return preguntaModuloE;
	}

	public void setPreguntaModuloE(Preguntamodulo preguntaModuloE) {
		this.preguntaModuloE = preguntaModuloE;
	}
	public List<Opcionpregunta> getListaOpcPreE() {
		return listaOpcPreE;
	}
	public void setListaOpcPreE(List<Opcionpregunta> listaOpcPreE) {
		this.listaOpcPreE = listaOpcPreE;
	}
	public List<Preguntamodulo> getFilteredlistaPreMo() {
		return filteredlistaPreMo;
	}
	public void setFilteredlistaPreMo(List<Preguntamodulo> filteredlistaPreMo) {
		this.filteredlistaPreMo = filteredlistaPreMo;
	}
	public List<Respuestapregunta> getFilteredlistaRespPreg() {
		return filteredlistaRespPreg;
	}
	public void setFilteredlistaRespPreg(List<Respuestapregunta> filteredlistaRespPreg) {
		this.filteredlistaRespPreg = filteredlistaRespPreg;
	}
	public List<Opcionpregunta> getFilteredlistaOpcPre() {
		return filteredlistaOpcPre;
	}
	public void setFilteredlistaOpcPre(List<Opcionpregunta> filteredlistaOpcPre) {
		this.filteredlistaOpcPre = filteredlistaOpcPre;
	}
	

}