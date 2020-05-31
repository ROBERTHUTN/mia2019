package mia.core.model.reporte;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mia.core.model.administrador.ManagerAdministrador;
import mia.core.model.cuestionario.ManagerCuestionario;
import mia.core.model.cuestionario.dto.DimensionPreguntaDTO;
import mia.core.model.cuestionario.dto.PreguntaDimensionDTO;
import mia.core.model.entities.Dimension;
import mia.core.model.entities.Reporte;
import mia.core.model.entities.Usuario;

@Stateless
@LocalBean
public class ManagerReporte {
	@PersistenceContext(unitName = "miaDS")
	private EntityManager em;
@EJB
	private ManagerAdministrador managerAdministrador;
@EJB
private ManagerCuestionario managerCuestionario;


	public ManagerReporte() {
	}

	// Metodo que me devuelve los reportes
	public List<Reporte> findAllReporte() {

		Query q = em.createQuery("SELECT r FROM Reporte r", Reporte.class);
		@SuppressWarnings("unchecked")
		List<Reporte> listaReporte = q.getResultList();
		return listaReporte;
	}	
	
	public List<Reporte> findResultadosTestbyUsuario(long id_user)
	{
		String JPQL = "SELECT r FROM Reporte r WHERE r.usuario.idUsuario=?1";
		Query query = em.createQuery(JPQL, Reporte.class);
		query.setParameter(1, id_user);
		@SuppressWarnings("unchecked")
		List<Reporte> listaReporte = query.getResultList();
		return listaReporte;
	}
	public List<Reporte> findResultadosTestbyUsuarioAndAnioAndMes(long id_user,int anio,int mes)
	{
		String JPQL = "SELECT r FROM Reporte r WHERE r.usuario.idUsuario=?1 "
				+ "and year(r.fecha)="+anio+" "
						+ " and month(r.fecha)="+mes;
		Query query = em.createQuery(JPQL, Reporte.class);
		query.setParameter(1, id_user);
		@SuppressWarnings("unchecked")
		List<Reporte> listaReporte = query.getResultList();
		return listaReporte;
	}
	public List<Integer> findResultadosTestbyUsuarioByAnio(long id_user)
	{
		List<Integer> publicationYears = em.createQuery(
				    "select distinct year(r.fecha) " +
				    " From Reporte r where r.usuario.idUsuario="+id_user+
				    "  order by year(r.fecha) desc", Integer.class)
				.getResultList();
		for (Integer integer : publicationYears) {
			System.out.println("A�O: "+integer.toString());
		}
		return publicationYears;
	}
	public List<Integer> findResultadosTestbyUsuarioByMes(int anio,long idUsuario)
	{
		List<Integer> listaMeses= em.createQuery(
				    "select distinct month(r.fecha) " +
				    " From Reporte r where r.usuario.idUsuario="+idUsuario
				    + " and year(r.fecha)="+anio +
				    "  order by month(r.fecha) desc", Integer.class)
				.getResultList();
		return listaMeses;
	}
	
	
	/**
	 * Me devuelve la lista de Reportes de los usuarios Ingresados por su id de 
	 * id de usuario.
	 * */
	

	
	// Método que me devuelve Reportes por id usuario
	public Reporte findReporteByIdUsuario(long id_usuario) {
		String JPQL = "SELECT r FROM Reporte r WHERE r.usuario.idUsuario=?1";
		Query query = em.createQuery(JPQL, Reporte.class);
		query.setParameter(1, id_usuario);
		Reporte Reporte;
		Reporte = (Reporte) query.getSingleResult();
		return Reporte;
	}

 

	public void ingresarReporte(String respuesta, int id_dimension,Date fecha_realizacion, long id_usuario) throws Exception {
		
		Reporte nreport = new Reporte();

		Usuario user = managerAdministrador.findUsuarioById(id_usuario);
		Dimension dime = managerCuestionario.findDimensionById(id_dimension);
		nreport.setResultado(respuesta);
		nreport.setDimension(dime);
		nreport.setFecha(fecha_realizacion);
		nreport.setUsuario(user);
		em.persist(nreport);
	}
	
	public String [] calcularRespuestaDimension(List<DimensionPreguntaDTO>listaDto,Dimension dimension)
	{
 String respuestaT[]=new String[2];
		String respuesta="";
		int a=0,b=0;
		if(dimension.getIdDimension()== 1|| dimension.getIdDimension()== 2 || dimension.getIdDimension()== 3|| dimension.getIdDimension()== 4 || dimension.getIdDimension()== 5)
		{
			for (DimensionPreguntaDTO dpDTO : listaDto) {
				if (dpDTO.getValor()<=3) {
					a++;
				}else {
					b++;
				}
				}
				double r;
				r=calcularPorcentaje(a, b, listaDto.size());
				if (a>b) {
					respuesta="Tiene bajo grado de capacidad en: "+dimension.getDescripcion()+" con el porcentaje de "+r+"%";
					respuestaT[0]=respuesta;
					respuestaT[1]=r+"";
					return respuestaT;
				}else {
					respuesta="Tiene alto grado de capacidad en: "+dimension.getDescripcion()+" con el porcentaje de "+r+"%";
					respuestaT[0]=respuesta;
					respuestaT[1]=r+"";
					return respuestaT;					
				}
		
				
		}else if(dimension.getIdDimension() == 6) 
		{
			int sumEstres = 0;
			for (DimensionPreguntaDTO dpDTO : listaDto) {
				sumEstres= sumEstres+dpDTO.getValor();
			}
			System.out.println("Estres sumatoria"+sumEstres);
			
			int menEstres= sumEstres-16;
			
			if(menEstres>24)
			{
				respuesta= "Vulnerable al �stres.";
				respuestaT[0]=respuesta;
				respuestaT[1]= menEstres+"";
			}else if(menEstres>=40 || menEstres<=60)
			{
				respuesta= "Seriamente vulnerable al estres.";
				respuestaT[0]=respuesta;
				respuestaT[1]= menEstres+"";
			}else if(menEstres>60)
			{
				respuesta= "Extremadamente vulnerable al estres.";
				respuestaT[0]=respuesta;
				respuestaT[1]= menEstres+"";
			}else {
				respuesta= "Baja vulnerabilidad al estres.";
				respuestaT[0]=respuesta;
				respuestaT[1]= menEstres+"";
			}
			
		}
		
		return respuestaT;	
	}
/**	
	public int[] respuestasIE(User user) {
	g	
	}*/
	
	public String [] calcularRespuestaDimension2( List<DimensionPreguntaDTO>listaDto,Dimension dimension)
	{
 String respuestaT[]=new String[2];
		String respuesta="";
		int a=0,b=0;
		if(dimension.getIdDimension()== 1|| dimension.getIdDimension()== 2 || dimension.getIdDimension()== 3|| dimension.getIdDimension()== 4 || dimension.getIdDimension()== 5)
		{
			for (DimensionPreguntaDTO dpDTO : listaDto) {
				if (dpDTO.getValor()<=3) {
					a++;
				}else {
					b++;
				}
				}
				double r;
				r=calcularPorcentaje(a, b, listaDto.size());
				if (a>b) {
					respuesta="Tiene bajo grado de capacidad en: "+dimension.getDescripcion()+" con el porcentaje de "+r+"%";
					respuestaT[0]=respuesta;
					respuestaT[1]=r+"";
					return respuestaT;
				}else {
					respuesta="Tiene alto grado de capacidad en: "+dimension.getDescripcion()+" con el porcentaje de "+r+"%";
					respuestaT[0]=respuesta;
					respuestaT[1]=r+"";
					return respuestaT;					
				}
		
				
		}else if(dimension.getIdDimension() == 6) 
		{
			int sumEstres = 0;
			for (DimensionPreguntaDTO dpDTO : listaDto) {
				sumEstres= sumEstres+dpDTO.getValor();
			}
			System.out.println("Estres sumatoria"+sumEstres);
			
			int menEstres= sumEstres-16;
			
			if(menEstres>24)
			{
				respuesta= "Vulnerable al �stres.";
				respuestaT[0]=respuesta;
				respuestaT[1]= menEstres+"";
			}else if(menEstres>=40 || menEstres<=60)
			{
				respuesta= "Seriamente vulnerable al estres.";
				respuestaT[0]=respuesta;
				respuestaT[1]= menEstres+"";
			}else if(menEstres>60)
			{
				respuesta= "Extremadamente vulnerable al estres.";
				respuestaT[0]=respuesta;
				respuestaT[1]= menEstres+"";
			}else {
				respuesta= "Baja vulnerabilidad al estres.";
				respuestaT[0]=respuesta;
				respuestaT[1]= menEstres+"";
			}
			
		}
		
		return respuestaT;	
	}
	
	
	
	
	public double calcularPorcentaje(int a,int b,int tamanio) {
		double r;
		if (a>b) {
			r=(a*100)/tamanio;
			return r;
		}else {
			r=(b*100)/tamanio;
		return r;
		}
	}


	
/*	
	public String nivelEstresAnsiedad(int sum)
	{
		String respuesta="";
		
	 if()	
	}*/
	
/**
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
*/
	/***
	 * Tres tipos de Cuestionarios A evaluar en la plataforma 
	 * creaci�n del Primer Cuestionario para la facilidad de creaci�n del 'ingreso de Reportes
	 * 
	 */
	
	
	
	
	
	
	
	

	
}
