package mia.core.model.reporte;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

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
import mia.core.model.usuario.dto.tipoLiderazgoCola;

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
	

	
	// MÃ©todo que me devuelve Reportes por id usuario
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
			
			
			int menEstres= sumEstres-16;
			
			if(menEstres>24)
			{
				respuesta= "Vulnerable al estres.";
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

	private int[] res = new int[61];
	
	
	public int[] respuestasIE(User user) {
		res[0]= user.getUno();
    	res[1]= user.getDos();
    	res[2]= user.getTres();
    	res[3]= user.getCuatro();
    	res[4]= user.getCinco();
    	res[5]= user.getSeis();
    	res[6]= user.getSiete();
    	res[7]= user.getOcho();
    	res[8]= user.getNueve();
    	res[9]= user.getDiez();
    	res[10]= user.getOnce();
    	res[11]= user.getDoce();
    	res[12]= user.getTrece();
    	res[13]= user.getCatorce();
    	res[14]= user.getQuince();
    	res[15]= user.getDiesiceis();
    	res[16]= user.getDiecisiete();
    	res[17]= user.getDieciocho();
    	res[18]= user.getDiecinueve();
    	res[19]= user.getVeinte();
    	res[20]= user.getVeintiuno();
    	res[21]= user.getVeintidos();
    	res[22]= user.getVeintitres();
    	res[23]= user.getVeinticuatro();
    	res[24]= user.getVeinticinco();
    	res[25]= user.getVeintiseis();
    	res[26]= user.getVeintisiete();
    	res[27]= user.getVeintiocho();
    	res[28]= user.getVeintinueve();
    	res[29]= user.getTreinta();
    	res[30]= user.getTreintayuno();
    	res[31]= user.getTreintaydos();
    	res[32]= user.getTreintaytres();
    	res[33]= user.getTreintaycuatro();
    	res[34]= user.getTreintaycinco();
    	res[35]= user.getTreintayseis();
    	res[36]= user.getTreintaysiete();
    	res[37]= user.getTreintayocho();
    	res[38]= user.getTreintaynueve();
    	res[39]= user.getCuarenta();
    	res[40]= user.getCuarentayuno();
    	res[41]= user.getCuarentaydos();
    	res[42]= user.getCuarentaytres();
    	res[43]= user.getCuarentaycuatro();
    	res[44]= user.getCuarentaycinco();
    	res[45]= user.getCuarentayseis();
    	res[46]= user.getCuarentaysiete();
    	res[47]= user.getCuarentayocho();
    	res[48]= user.getCuarentaynueve();
    	res[49]= user.getCincuenta();
    	res[50]= user.getCincuentayuno();
    	res[51]= user.getCincuentaydos();
    	res[52]= user.getCincuentaytres();
    	res[53]= user.getCincuentaycuatro();
    	res[54]= user.getCincuentaycinco();
    	res[55]= user.getCincuentayseis();
    	res[56]= user.getCincuentaysiete();
    	res[57]= user.getCincuentayocho();
    	res[58]= user.getCincuentaynueve();
    	res[59]= user.getSesenta();
    	res[60]= user.getSesentayuno();

    	

    	
    	/**System.out.println(res.length);
    	
    	for (int i = 0; i < res.length; i++) {
			System.out.println(i+"MR:"+res[i]);
		}
    	*/
    	return res;

	}
	
	private int autoConciencia[] = {0,5,10,11,12,13,14,16,17,18,19,20};
	private int autoMotivacion[]= {6,21,22,24,25,26,27};
	private int controlEmociones[]= {0,1,2,3,4,6,8,9,12,26};
	private int interPersonal[]= {7,9,15,18,19,28,29,30,31,32,33,34,35,36,37,38,41,42,43,44};
	private int asesoriaEmocional[]= {7,9,15,17,33,34,36,37,38,39,40,43,44};
	private int nivelEstres[]= {45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60};
	
	public void ingresarIEEL(User user) {
		
		
		double porcentajeAE=0.0;
		
		int [] trabajarIE= res;
		
		// respuesta Asesoria Emocional
		int bajoAE=0;
		int altoAE=0;
		
		for (int i = 0; i < asesoriaEmocional.length; i++) {
		
			if(trabajarIE[asesoriaEmocional[i]]<=3)
			{
				bajoAE++;
			}else {
				altoAE++;
			}
			
		}
			porcentajeAE= calcularPorcentaje(bajoAE, altoAE, asesoriaEmocional.length);
	
			if (bajoAE>altoAE) {
				System.out.println("Tiene bajo grado de capacidad en asesoriaEmocional:  con el porcentaje de "+porcentajeAE+"%");
				
			}else {
				System.out.println("Tiene alto grado de capacidad en asesoriaEmocional:  con el porcentaje de "+porcentajeAE+"%");
			}
			
		
			double porcentajeIP=0.0;
			// respuesta Inter Personal
			int bajoIP=0;
			int altoIP=0;
			
			for (int i = 0; i < interPersonal.length; i++) {
			
				System.out.println(i+""+"IP"+ ""+trabajarIE[interPersonal[i]]);	
				if(trabajarIE[interPersonal[i]]<=3)
				{
					bajoIP++;
				}else {
					altoIP++;
				}
				
			}
				porcentajeIP= calcularPorcentaje(bajoIP, altoIP, interPersonal.length);
		
				if (bajoIP>altoIP) {
					System.out.println("Tiene bajo grado de capacidad en interPersonal:  con el porcentaje de "+porcentajeIP+"%");
					
				}else {
					System.out.println("Tiene alto grado de capacidad en interPersonal:  con el porcentaje de "+porcentajeIP+"%");
				}
				
				double porcentajeCE=0.0;
				
				// respuesta control Emocional
				int bajoCE=0;
				int altoCE=0;
				
				for (int i = 0; i < controlEmociones.length; i++) {
				
					System.out.println(i+""+"CE"+ ""+trabajarIE[controlEmociones[i]]);	
					if(trabajarIE[controlEmociones[i]]<=3)
					{
						bajoCE++;
					}else {
						altoCE++;
					}
					
				}
					porcentajeCE= calcularPorcentaje(bajoCE, altoCE, controlEmociones.length);
			
					if (bajoCE>altoCE) {
						System.out.println("Tiene bajo grado de capacidad en controlEmociones:  con el porcentaje de "+porcentajeCE+"%");
						
					}else {
						System.out.println("Tiene alto grado de capacidad en controlEmociones:  con el porcentaje de "+porcentajeCE+"%");
					}
					
				
					double porcentajeAM=0.0;
				
					// respuesta autoMotivacion
					int bajoAM=0;
					int altoAM=0;
					
					for (int i = 0; i < autoMotivacion.length; i++) {
					
						System.out.println(i+""+"AM"+ ""+trabajarIE[autoMotivacion[i]]);	
						if(trabajarIE[autoMotivacion[i]]<=3)
						{
							bajoAM++;
						}else {
							altoAM++;
						}
						
					}
						porcentajeAM= calcularPorcentaje(bajoAM, altoAM, autoMotivacion.length);
				
						if (bajoAM>altoAM) {
							System.out.println("Tiene bajo grado de capacidad en autoMotivacion:  con el porcentaje de "+porcentajeAM+"%");
							
						}else {
							System.out.println("Tiene alto grado de capacidad en autoMotivacion:  con el porcentaje de "+porcentajeAM+"%");
						}
						
						double porcentajeAC=0.0; 
						// respuesta autoConciencia
						int bajoAC=0;
						int altoAC=0;
						
						for (int i = 0; i < autoConciencia.length; i++) {
						
							System.out.println(i+""+"AC"+ ""+trabajarIE[autoConciencia[i]]);	
							if(trabajarIE[autoConciencia[i]]<=3)
							{
								bajoAC++;
							}else {
								altoAC++;
							}
							
						}
							porcentajeAC= calcularPorcentaje(bajoAC, altoAC, autoConciencia.length);
					
							if (bajoAC>altoAC) {
								System.out.println("Tiene bajo grado de capacidad en Autoconciencia:  con el porcentaje de "+porcentajeAC+"%");
								
							}else {
								System.out.println("Tiene alto grado de capacidad en Autoconciencia:  con el porcentaje de "+porcentajeAC+"%");
							}
							
							
							// respuesta nivel de estres
							String respuesta="";
							int sumEstres=0;
							for (int i = 0; i < nivelEstres.length; i++) {
								
								System.out.println(i+""+"NE"+ ""+trabajarIE[nivelEstres[i]]);	
								sumEstres+=trabajarIE[nivelEstres[i]];
							}	
							
							System.out.println(" sumEstres="+ sumEstres);
							int menEstres= sumEstres-16;
							
							if(menEstres>24)
							{
								respuesta= "Vulnerable al estrés.";
							
							}else if(menEstres>=40 || menEstres<=60)
							{
								respuesta= "Seriamente vulnerable al estrés.";
					
							}else if(menEstres>60)
							{
								respuesta= "Extremadamente vulnerable al estrés.";
							
							}else {
								respuesta= "Baja vulnerabilidad al estres.";
							
							}
					
							System.out.println(" menEstres="+ respuesta);
							
							
							
	}
	
	
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
				respuesta= "Vulnerable al ï¿½stres.";
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

	public int[] getRes() {
		return res;
	}

	public void setRes(int[] res) {
		this.res = res;
	}

	public int[] getAutoConciencia() {
		return autoConciencia;
	}

	public void setAutoConciencia(int[] autoConciencia) {
		this.autoConciencia = autoConciencia;
	}

	public int[] getAutoMotivacion() {
		return autoMotivacion;
	}

	public void setAutoMotivacion(int[] autoMotivacion) {
		this.autoMotivacion = autoMotivacion;
	}

	public int[] getControlEmociones() {
		return controlEmociones;
	}

	public void setControlEmociones(int[] controlEmociones) {
		this.controlEmociones = controlEmociones;
	}

	public int[] getInterPersonal() {
		return interPersonal;
	}

	public void setInterPersonal(int[] interPersonal) {
		this.interPersonal = interPersonal;
	}

	public int[] getAsesoriaEmocional() {
		return asesoriaEmocional;
	}

	public void setAsesoriaEmocional(int[] asesoriaEmocional) {
		this.asesoriaEmocional = asesoriaEmocional;
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
	 * creaciï¿½n del Primer Cuestionario para la facilidad de creaciï¿½n del 'ingreso de Reportes
	 * 
	 */
	
	
		
	
	
	
	
	

	
}
