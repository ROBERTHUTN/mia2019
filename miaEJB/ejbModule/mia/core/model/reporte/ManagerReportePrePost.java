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
import mia.core.model.entities.Reporteprepost;
import mia.core.model.entities.Usuario;
import mia.core.model.usuario.dto.tipoLiderazgoCola;

@Stateless
@LocalBean
public class ManagerReportePrePost {
	@PersistenceContext(unitName = "miaDS")
	private EntityManager em;
@EJB
	private ManagerAdministrador managerAdministrador;
@EJB
private ManagerCuestionario managerCuestionario;


	public ManagerReportePrePost() {
	}

	// Metodo que me devuelve los reportes
	
	public List<Reporteprepost> findAllReporteprepost() {

		Query q = em.createQuery("SELECT r FROM Reporteprepost r", Reporteprepost.class);
		@SuppressWarnings("unchecked")
		List<Reporteprepost> listaReporte = q.getResultList();
		return listaReporte;
	}	

	
	
	public List<Reporteprepost> findResultadosTestbyUsuario(long id_user)
	{
		String JPQL = "SELECT r FROM Reporteprepost r WHERE r.usuario.idUsuario=?1";
		Query query = em.createQuery(JPQL, Reporteprepost.class);
		query.setParameter(1, id_user);
		@SuppressWarnings("unchecked")
		List<Reporteprepost> listaReporte = query.getResultList();
		return listaReporte;
	}
	
	public List<Reporteprepost> findResultadosTestbyUsuarioAndAnioAndMes(long id_user,int anio,int mes)
	{
		String JPQL = "SELECT r FROM Reporteprepost r WHERE r.usuario.idUsuario=?1 "
				+ "and year(r.fecha)="+anio+" "
						+ " and month(r.fecha)="+mes;
		Query query = em.createQuery(JPQL, Reporteprepost.class);
		query.setParameter(1, id_user);
		@SuppressWarnings("unchecked")
		List<Reporteprepost> listaReporte = query.getResultList();
		return listaReporte;
	}
	
	
	public List<Integer> findResultadosTestbyUsuarioByAnio(long id_user)
	{
		List<Integer> publicationYears = em.createQuery(
				    "select distinct year(r.fecha) " +
				    " From Reporteprepost r where r.usuario.idUsuario="+id_user+
				    "  order by year(r.fecha) desc", Integer.class)
				.getResultList();
		for (Integer integer : publicationYears) {
			System.out.println("AÑO: "+integer.toString());
		}
		return publicationYears;
	}
	public List<Integer> findResultadosTestbyUsuarioByMes(int anio,long idUsuario)
	{
		List<Integer> listaMeses= em.createQuery(
				    "select distinct month(r.fecha) " +
				    " From Reporteprepost r where r.usuario.idUsuario="+idUsuario
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
	public Reporteprepost findReporteprepostByIdUsuario(long id_usuario) {
		String JPQL = "SELECT r FROM Reporteprepost r WHERE r.usuario.idUsuario=?1";
		Query query = em.createQuery(JPQL, Reporteprepost.class);
		query.setParameter(1, id_usuario);
		Reporteprepost Reporte;
		Reporte = (Reporteprepost) query.getSingleResult();
		return Reporte;
	}

 


	public void ingresarReporteprepost( int autoconciencia, int automotivacion, 
			int asesoriaEmocional, int controlEmocion, int interpersonal, 
			String centroGravedad, String logicaEmergente, String logicaRetroceso, 
			String nivelEstres, String resAutcon, String resAutomoti, 
			String resAse, String resControlEmocion, String resInterpersonal,
			String resCuestionario, Date fecha_realizacion, long id_usuario) throws Exception {
		 
		Reporteprepost nreport = new Reporteprepost();

		Usuario user = managerAdministrador.findUsuarioById(id_usuario);
		
		nreport.setAutoconciencia(autoconciencia);
		nreport.setAutomotivacion(automotivacion);
		nreport.setAsesoriaEmocional(asesoriaEmocional);
		nreport.setControlEmocion(controlEmocion);
		nreport.setInterpersonal(interpersonal);
		nreport.setCentrogravedad(centroGravedad);
		nreport.setLogicaEmergente(logicaEmergente);
		nreport.setLogicaRetroceso(logicaRetroceso);
		nreport.setNivelestres(nivelEstres);
		nreport.setResAutoconciencia(resAutcon);
		nreport.setResAutomotivacion(resAutomoti);
		nreport.setResAsesoriaEmocional(resAse);
		nreport.setResControlEmocion(resControlEmocion);
		nreport.setResInterpersonal(resInterpersonal);
		nreport.setRespuestaCuestionario(resCuestionario);
		nreport.setFechaInscripcion(fecha_realizacion);
		nreport.setUsuario(user);
		em.persist(nreport);
	}

	


	private String tlCentroGravedad;
	private String tlLogicaEmergente;
	private String tlLogicaretroceso;

	
	//
	public String [] calculoTl(int arrOp[], int arrDi[], int arrEx[],int arrTr[],int arrAl[],int arrRd[],int arrIm[] ) {
		
		String [] tipoLiderazgo= new String[3];
		
		int al1=0,al2=0,al3=0;
		int di1=0, di2=0, di3=0, ex1=0, ex2=0, ex3=0;
		int re1=0, re2=0, re3=0, tr1=0, tr2=0, tr3=0;
		int im1=0, im2=0, im3=0, op1=0, op2=0, op3=0;
		

	    
		// Traverse through map and print frequencies
		Map<Integer, Integer> mpAl = new HashMap<>();
		mpAl= countFreq(arrAl, arrAl.length);

			for (Map.Entry<Integer, Integer> entry : mpAl.entrySet()) {
				System.out.println("alquimista:" + entry.getKey() + " " + entry.getValue());

				if(entry.getKey()==1) {
					al1=entry.getValue();
					System.out.println("al1"+ al1);
					
				}
				if(entry.getKey()==2) {
					al2=entry.getValue();
					System.out.println("al2"+ al2);
					
				}
				if(entry.getKey()==3) {
					al3=entry.getValue();
					System.out.println("al3"+ al3);
					
				}
				
				
			}
		

			Map<Integer, Integer> mpDi = new HashMap<>();
			mpDi= countFreq(arrDi, arrDi.length);
			for (Map.Entry<Integer, Integer> entry : mpDi.entrySet()) {
				//System.out.println("diplomatico:" + entry.getKey() + " " + entry.getValue());
				if(entry.getKey()==1) {
					di1=entry.getValue();
				//	System.out.println("di1"+ di1);
				}
				if(entry.getKey()==2) {
					di2=entry.getValue();
					//System.out.println("d2"+ di2);
				}
				if(entry.getKey()==3) {
					di3=entry.getValue();
					//System.out.println("di3"+ di3);
				}

			}
		
			Map<Integer, Integer> mpOp = new HashMap<>();
			mpOp= countFreq(arrOp, arrOp.length);
		
			for (Map.Entry<Integer, Integer> entry : mpOp.entrySet()) {
			//	System.out.println("Oportunista:" + entry.getKey() + " " + entry.getValue());
				if(entry.getKey()==1) {
					op1=entry.getValue();
					//System.out.println("op1"+ op1);
				}
				if(entry.getKey()==2) {
					op2=entry.getValue();
					//System.out.println("op2"+ op2);
				}
				if(entry.getKey()==3) {
					op3=entry.getValue();
					//System.out.println("op3"+ op3);
				}
			}
		
			Map<Integer, Integer> mpEx = new HashMap<>();
			mpEx= countFreq(arrEx, arrEx.length);

			for (Map.Entry<Integer, Integer> entry : mpEx.entrySet()) {
				//System.out.println("Experto:" + entry.getKey() + " " + entry.getValue());
				if(entry.getKey()==1) {
					ex1=entry.getValue();
					//System.out.println("ex1"+ ex1);
				}
				if(entry.getKey()==2) {
					ex2=entry.getValue();
					//System.out.println("ex2"+ ex2);
				}
				if(entry.getKey()==3) {
					ex3=entry.getValue();
					//System.out.println("ex3"+ ex3);
				}
			}
		
			Map<Integer, Integer> mpRe = new HashMap<>();
			mpRe= countFreq(arrRd, arrRd.length);
			for (Map.Entry<Integer, Integer> entry : mpRe.entrySet()) {
				//System.out.println("Redefiniendo:" + entry.getKey() + " " + entry.getValue());
				if(entry.getKey()==1) {
					re1=entry.getValue();
				//	System.out.println("re1"+ re1);
				}
				if(entry.getKey()==2) {
					re2=entry.getValue();
					//System.out.println("re2"+ re2);
				}
				if(entry.getKey()==3) {
					re3=entry.getValue();
					//System.out.println("re3"+ re3);
				}
			}
		
			Map<Integer, Integer> mpTr = new HashMap<>();
			mpTr= countFreq(arrTr, arrTr.length);
			for (Map.Entry<Integer, Integer> entry : mpTr.entrySet()) {
				//System.out.println("Transformador:" + entry.getKey() + " " + entry.getValue());
				
				if(entry.getKey()==1) {
					tr1=entry.getValue();
					//System.out.println("tr1"+ tr1);
				}
				if(entry.getKey()==2) {
					tr2=entry.getValue();
					//System.out.println("tr2"+ tr2);
				}
				if(entry.getKey()==3) {
					tr3=entry.getValue();
					//System.out.println("tr3"+ tr3);
				}
				
			}

			Map<Integer, Integer> mpIm = new HashMap<>();
			mpIm= countFreq(arrIm, arrIm.length);
			for (Map.Entry<Integer, Integer> entry : mpIm.entrySet()) {
				//System.out.println("Impulsivo:" + entry.getKey() + " " + entry.getValue());
			
				if(entry.getKey()==1) {
					im1=entry.getValue();
					//System.out.println("im1"+ im1);
				}
				if(entry.getKey()==2) {
					im2=entry.getValue();
					//System.out.println("im2"+ im2);
				}
				if(entry.getKey()==3) {
					im3=entry.getValue();
					//System.out.println("im3"+ im3);
				}
			}
		
			
			
		/**
			Queue<tipoLiderazgoCola> tlCentral= new PriorityQueue<tipoLiderazgoCola>();
			tlCentral.add(new tipoLiderazgoCola("Alquimista", al1));  
			tlCentral.add(new tipoLiderazgoCola("Diplomatico", di1));
			tlCentral.add(new tipoLiderazgoCola("Experto", ex1));
			tlCentral.add(new tipoLiderazgoCola("Redefinido", re1));
			tlCentral.add(new tipoLiderazgoCola("Transformador", tr1));
			tlCentral.add(new tipoLiderazgoCola("Impulsivo", im1));
			tlCentral.add(new tipoLiderazgoCola("Oportunista", op1));
			System.out.println("Este es el primer"+tlCentral.peek().getNombre()+":"+tlCentral.peek().getTipo());
			
			while(!tlCentral.isEmpty()){
				tipoLiderazgoCola tl= tlCentral.remove();
						
				System.out.println(tl.getNombre()+ " "+ tl.getTipo());
			
			}
			*/
			tlCentroGravedad=respuestaLiderasgo(al1, di1, ex1, re1, tr1, im1, op1);
			System.out.println("Centro de Gavedad"+ tlCentroGravedad);
			
	
			tlLogicaEmergente= respuestaLiderasgo(al2, di2, ex2, re2, tr2, im2, op2);
			System.out.println("Lógica Emergente"+ tlLogicaEmergente);
			
			tlLogicaretroceso= respuestaLiderasgo(al3, di3, ex3, re3, tr3, im3, op3);
			System.out.println("Lógica de Retroceso"+ tlLogicaretroceso);
			
			
			tipoLiderazgo[0]=tlCentroGravedad;
			tipoLiderazgo[1]=tlLogicaEmergente;
			tipoLiderazgo[2]=tlLogicaretroceso;
			
			return tipoLiderazgo;
			
	}
	
	public String respuestaLiderasgo (int al, int di, int ex, int re, int tr, int im, int op ) {
		
		Queue<tipoLiderazgoCola> tlCentral= new PriorityQueue<tipoLiderazgoCola>();
		tlCentral.add(new tipoLiderazgoCola("Alquimista", al));  
		tlCentral.add(new tipoLiderazgoCola("Diplomatico", di));
		tlCentral.add(new tipoLiderazgoCola("Experto", ex));
		tlCentral.add(new tipoLiderazgoCola("Redefinido", re));
		tlCentral.add(new tipoLiderazgoCola("Transformador", tr));
		tlCentral.add(new tipoLiderazgoCola("Impulsivo", im));
		tlCentral.add(new tipoLiderazgoCola("Oportunista", op));
		
		
		int comparar=tlCentral.peek().getTipo();
		System.out.println( "Comparar "+ comparar);
		String tiposLiderazgos= "";
		while(!tlCentral.isEmpty()){
			tipoLiderazgoCola tl= tlCentral.remove();
			if(comparar==tl.getTipo())
			{
				tiposLiderazgos+= tl.getNombre()+ "/ ";
			}
			
			System.out.println(tl.getNombre()+ " "+ tl.getTipo());
		
		}
		
		return  tiposLiderazgos;
	}

	public static  Map<Integer, Integer> countFreq(int arr[], int n) {
		
		Map<Integer, Integer> mp = new HashMap<>();
		
	
		// Traverse through array elements and
		// count frequencies
		for (int i = 0; i < n; i++) {
			if (mp.containsKey(arr[i])) {
				mp.put(arr[i], mp.get(arr[i]) + 1);
			} else {
				mp.put(arr[i], 1);
			}
		}
		
		return mp;
	}


	/*
	
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
*/
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
	
   public String respuestascuestionario()
   {
	   StringBuffer cadena = new StringBuffer();
	   for (int x=0;x<res.length;x++){
		   cadena =cadena.append(res[x]);
		}
	   
	   return cadena.toString();
   }
	
	private int autoConciencia[] = {0,5,10,11,12,13,14,16,17,18,19,20};
	private int autoMotivacion[]= {6,21,22,24,25,26,27};
	private int controlEmociones[]= {0,1,2,3,4,6,8,9,12,26};
	private int interPersonal[]= {7,9,15,18,19,28,29,30,31,32,33,34,35,36,37,38,41,42,43,44};
	private int asesoriaEmocional[]= {7,9,15,17,33,34,36,37,38,39,40,43,44};
	private int nivelEstres[]= {45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60};

	
	
	
	public Object[] ingresarIEEL() {
		
		
		int porcentajeAE=0;
		
		int [] trabajarIE= res;
		
		// respuesta Asesoria Emocional
		int bajoAE=0;
		int altoAE=0;
		
		for (int i = 0; i < asesoriaEmocional.length; i++) {
		
			System.out.println(i+""+"AC"+ ""+trabajarIE[asesoriaEmocional[i]]);	
			if(trabajarIE[asesoriaEmocional[i]]<=3)
			{
				bajoAE++;
			}else {
				altoAE++;
			}
			
		}
			porcentajeAE= calcularPorcentaje(bajoAE, altoAE, asesoriaEmocional.length);
			String res_porcentajeAE="";
			if (bajoAE>altoAE) {
				System.out.println("Tiene bajo grado de capacidad en:  con el porcentaje de "+porcentajeAE+"%");
				res_porcentajeAE="Bajo";
			}else {
				res_porcentajeAE="Alto";
				System.out.println("Tiene alto grado de capacidad en:  con el porcentaje de "+porcentajeAE+"%");
			}
			
		
			int porcentajeIP=0;
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
				String res_porcentajeIP="";
				if (bajoIP>altoIP) {
					System.out.println("Tiene bajo grado de capacidad en:  con el porcentaje de "+porcentajeIP+"%");
					res_porcentajeIP="Bajo";
				}else {
					res_porcentajeIP="Alto";
					System.out.println("Tiene alto grado de capacidad en:  con el porcentaje de "+porcentajeIP+"%");
				}
				
				int porcentajeCE=0;
	
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
					String res_porcentajeCE="";
					if (bajoCE>altoCE) {
						System.out.println("Tiene bajo grado de capacidad en:  con el porcentaje de "+porcentajeCE+"%");
						res_porcentajeCE="Bajo";
					}else {
						res_porcentajeCE="Alto";
						System.out.println("Tiene alto grado de capacidad en:  con el porcentaje de "+porcentajeCE+"%");
					}
					
				
					int porcentajeAM=0;
				
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
						String res_pporcentajeAM="";
						if (bajoAM>altoAM) {
							System.out.println("Tiene bajo grado de capacidad en:  con el porcentaje de "+porcentajeAM+"%");
							res_pporcentajeAM="Bajo";
						}else {
							System.out.println("Tiene alto grado de capacidad en:  con el porcentaje de "+porcentajeAM+"%");
							res_pporcentajeAM="Alto";
						}
						
						int porcentajeAC=0; 
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
							String res_porcentajeAC="";
							if (bajoAC>altoAC) {
								System.out.println("Tiene bajo grado de capacidad en:  con el porcentaje de "+porcentajeAC+"%");
								res_porcentajeAC="Bajo";
							}else {
								res_porcentajeAC="Alto";
								System.out.println("Tiene alto grado de capacidad en:  con el porcentaje de "+porcentajeAC+"%");
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
					
							Object vector[] = new Object[11];
							
							vector[0]= porcentajeAC;
							vector[1]= res_porcentajeAC;
							vector[2]= porcentajeAE;
							vector[3]= res_porcentajeAE;
							vector[4]= porcentajeAM;
							vector[5]= res_pporcentajeAM;
							vector[6]= porcentajeCE;
							vector[7]= res_porcentajeCE;
							vector[8]= porcentajeIP;
							vector[9]= res_porcentajeIP;
							vector[10]= respuesta;
							
							return vector;
	
	}
	
	
	public int calcularPorcentaje(int a,int b,int tamanio) {
		int r;
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
