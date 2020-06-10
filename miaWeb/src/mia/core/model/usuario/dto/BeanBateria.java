package mia.core.model.usuario.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.event.DragDropEvent;

import mia.modulos.view.util.JSFUtil;
import java.io.Serializable;

@Named
@SessionScoped
public class BeanBateria implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<BateriaDto> bateriasPreguntas;
	private List<BateriaDto> bateriasRespuestas;
	private String nombre;
	private List<DimensionBateriaDto2> listaDimensionPreguntas;
	private List<DimensionBateriaDto2> listaDimensionRespuesta;
	private List<DimensionBateriaDto2> listaDimensionRespuestaActual;
	private DimensionBateriaDto2 dimensionActDto;
	private boolean atras;
	private boolean adelante;
	private boolean finalizarTest;
	private int contador;
	private boolean iniciarTest;
	int cont = 1;
	@EJB
	BateriaServiceDto managerBateria;

	private String tlCentroGravedad;
	private String tlLogicaEmergente;
	private String tlLogicaretroceso;

	public void iniciarCuestionario() {
		iniciarTest = true;
		System.out.println("" + iniciarTest);
	}

	public void atras() {
		// listaDimensionRespuesta
		listaDimensionRespuesta.set(contador, listaDimensionRespuestaActual.get(0));
		contador--;
		estadoActualContador(contador);
		dimensionActDto = listaDimensionRespuesta.get(contador);
		listaDimensionRespuestaActual = new ArrayList<>();
		listaDimensionRespuestaActual.add(dimensionActDto);
	}

	public String siguiente() {
		try {
			DimensionBateriaDto2 prePact = listaDimensionRespuestaActual.get(0);

			List<BateriaDto> listapreguntas = prePact.getListaPreguntas();
			List<BateriaDto> listarespuestas = prePact.getListaRespuestas();
			System.out.println("" + listapreguntas.size());
			System.out.println("" + listarespuestas.size());
			if (listapreguntas.size() == 0 && listarespuestas.size() == 7) {
				listaDimensionRespuesta.set(contador, listaDimensionRespuestaActual.get(0));

				contador++;
				estadoActualContador(contador);
				dimensionActDto = new DimensionBateriaDto2();
				dimensionActDto = listaDimensionRespuesta.get(contador);
				if (dimensionActDto.getNombre().length() == 0) {
					System.out.println("3");
				}
				if (cont != 1) {
					cont = 1;
				}
				listaDimensionRespuestaActual = new ArrayList<>();
				listaDimensionRespuestaActual.add(dimensionActDto);

			} else {
				JSFUtil.crearMensajeError("Conteste todas las preguntas");
			}
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}

		return "";
	}

	public void finalizarTest() {

		// System.out.println(" cantidad de datos"+ respuesta.size());

		int[] oportunista = new int[6];
		int[] diplomatico = new int[6];
		int[] experto = new int[6];
		int[] redefiniendo = new int[6];
		int[] transformador = new int[6];
		int[] alquimico = new int[6];
		int[] impulsivo = new int[6];

		int o = 0, di = 0, e = 0, re = 0, t = 0, a = 0, i = 0;

		for (DimensionBateriaDto2 d : listaDimensionRespuesta) {
			System.out.println("Literal; " + d.getNombre());// literal
			for (BateriaDto r : d.getListaRespuestas()) {
				// System.out.println("Respuesta; "+r.getPregunta()+" Puntuacion;
				// "+r.getPosicion()+" "+ r.getLiteral());

				if (r.getLiteral() == "a7" || r.getLiteral() == "b2" || r.getLiteral() == "c1" || r.getLiteral() == "d5"
						|| r.getLiteral() == "e1" || r.getLiteral() == "f2") {

					oportunista[o] = r.getPosicion();
					o++;
					System.out.println("entra" + o);

				} else {
					if (r.getLiteral() == "a5" || r.getLiteral() == "b1" || r.getLiteral() == "c3"
							|| r.getLiteral() == "d3" || r.getLiteral() == "e4" || r.getLiteral() == "f1") {

						diplomatico[di] = r.getPosicion();
						di++;
						continue;
					} else {
						if (r.getLiteral() == "a2" || r.getLiteral() == "b7" || r.getLiteral() == "c4"
								|| r.getLiteral() == "d1" || r.getLiteral() == "e5" || r.getLiteral() == "f5") {
							experto[e] = r.getPosicion();
							e++;
							continue;
						} else {
							if (r.getLiteral() == "a6" || r.getLiteral() == "b4" || r.getLiteral() == "c6"
									|| r.getLiteral() == "d4" || r.getLiteral() == "e3" || r.getLiteral() == "f6") {
								redefiniendo[re] = r.getPosicion();
								re++;
								continue;
							} else {
								if (r.getLiteral() == "a1" || r.getLiteral() == "b6" || r.getLiteral() == "c5"
										|| r.getLiteral() == "d6" || r.getLiteral() == "e6" || r.getLiteral() == "f3") {

									transformador[t] = r.getPosicion();
									t++;
									continue;
								} else {
									if (r.getLiteral() == "a3" || r.getLiteral() == "b5" || r.getLiteral() == "c7"
											|| r.getLiteral() == "d7" || r.getLiteral() == "e7"
											|| r.getLiteral() == "f7") {

										alquimico[a] = r.getPosicion();
										a++;
										continue;
									} else {
										// a4, b3, c2,d2,e2,f4
										impulsivo[i] = r.getPosicion();
									//	System.out.println("impulsivo[i] " + i + " valor; " + r.getPosicion());
										i++;
									}
								}
							}
						}
					}
				}

			}
		}
		/**
		 * for (int j = 0; j < impulsivo.length; j++) { System.out.println("posV;
		 * "+j+"cali;"+impulsivo[j]);
		 * 
		 * }
		 */
		//tipoLiderazgo(oportunista, diplomatico, experto, redefiniendo, transformador, alquimico, impulsivo);
	 calculoTl(oportunista, diplomatico, experto, transformador, alquimico, redefiniendo, impulsivo);

	}



	
	
	public void calculoTl(int arrOp[], int arrDi[], int arrEx[],int arrTr[],int arrAl[],int arrRd[],int arrIm[] ) {
		
		
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
		
			System.out.println("al1"+al1);
			System.out.println("al2"+al2);
			System.out.println("al3"+al3);
			System.out.println("di1"+di1);
			System.out.println("di2"+di2);
			System.out.println("di3"+di3);
			System.out.println("op1"+op1);
			System.out.println("op2"+op2);
			System.out.println("op3"+op3);
			System.out.println("ex1"+ex1);
			System.out.println("ex2"+ex2);
			System.out.println("ex3"+ex3);
			System.out.println("re1"+re1);
			System.out.println("re2"+re2);
			System.out.println("re3"+re3);
			System.out.println("tr1"+tr1);
			System.out.println("tr2"+tr2);
			System.out.println("tr3"+tr3);
			System.out.println("im1"+im1);
			System.out.println("im2"+im2);
			System.out.println("im3"+im3);
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
	
	public  void compararTL (int al1, int al2, int al3, int di1, int di2, int di3,
			int op1, int op2, int op3, int ex1, int ex2, int ex3,
			int re1, int re2, int re3, int tr1, int tr2, int tr3, int im1, int im2, int im3)
	{
		System.out.println("al1"+al1);
		System.out.println("al2"+al2);
		System.out.println("al3"+al3);
		System.out.println("di1"+di1);
		System.out.println("di2"+di2);
		System.out.println("di3"+di3);
		System.out.println("op1"+op1);
		System.out.println("op2"+op2);
		System.out.println("op3"+op3);
		System.out.println("ex1"+ex1);
		System.out.println("ex2"+ex2);
		System.out.println("ex3"+ex3);
		System.out.println("re1"+re1);
		System.out.println("re2"+re2);
		System.out.println("re3"+re3);
		System.out.println("tr1"+tr1);
		System.out.println("tr2"+tr2);
		System.out.println("tr3"+tr3);
		System.out.println("im1"+im1);
		System.out.println("im2"+im2);
		System.out.println("im3"+im3);

		
	}
	
	
	public void estadoActualContador(int contadorC) {
		if (listaDimensionRespuesta.isEmpty()) {
			atras = false;
			adelante = false;
			finalizarTest = false;

		} else {
			if (contadorC == 0 && contadorC == (listaDimensionRespuesta.size() - 1)) {
				adelante = false;
				atras = false;
				finalizarTest = true;
			} else {
				if (contadorC > 0 && contadorC < (listaDimensionRespuesta.size() - 1)) {
					adelante = true;
					atras = true;
					finalizarTest = false;
				} else {
					if (contadorC == (listaDimensionRespuesta.size() - 1)) {
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

	public void onCarDrop(DragDropEvent event) {
		try {
			DimensionBateriaDto2 prePact = listaDimensionRespuestaActual.get(0);
			List<BateriaDto> listapreguntas = prePact.getListaPreguntas();
			List<BateriaDto> listarespuestas = prePact.getListaRespuestas();
			BateriaDto bat = (BateriaDto) event.getData();
			bat.setPosicion(cont++);
			listarespuestas.add(bat);
			listapreguntas.remove(bat);
			listaDimensionRespuestaActual.get(0).setListaPreguntas(listapreguntas);
			listaDimensionRespuestaActual.get(0).setListaRespuestas(listarespuestas);
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());

		}
	}

	public String PreguntasByDimension() {
		try {
			iniciarTest = false;
			listaDimensionRespuesta = managerBateria.inicializarPreguntasPadres();
			listaDimensionRespuestaActual = new ArrayList<DimensionBateriaDto2>();
			listaDimensionRespuestaActual.add(listaDimensionRespuesta.get(0));

			contador = 0;
			estadoActualContador(contador);

			JSFUtil.crearMensajeInfo("Responder las siguientes preguntas");

			return "tipoLiderazgo2?faces-redirect=true";

		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			return "";
		} finally {
			JSFUtil.crearMensajeFastFinal();
		}

	}

	public List<BateriaDto> getBateriasRespuestas() {
		return bateriasRespuestas;
	}

	public void setBateriasRespuestas(List<BateriaDto> bateriasRespuestas) {
		this.bateriasRespuestas = bateriasRespuestas;
	}

	public List<DimensionBateriaDto2> getListaDimensionRespuesta() {
		return listaDimensionRespuesta;
	}

	public void setListaDimensionRespuesta(List<DimensionBateriaDto2> listaDimensionRespuesta) {
		this.listaDimensionRespuesta = listaDimensionRespuesta;
	}

	public List<DimensionBateriaDto2> getListaDimensionPreguntas() {
		return listaDimensionPreguntas;
	}

	public void setListaDimensionPreguntas(List<DimensionBateriaDto2> listaDimensionPreguntas) {
		this.listaDimensionPreguntas = listaDimensionPreguntas;
	}

	public List<BateriaDto> getBateriasPreguntas() {
		return bateriasPreguntas;
	}

	public void setBateriasPreguntas(List<BateriaDto> bateriasPreguntas) {
		this.bateriasPreguntas = bateriasPreguntas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public boolean isFinalizarTest() {
		return finalizarTest;
	}

	public void setFinalizarTest(boolean finalizarTest) {

	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public List<DimensionBateriaDto2> getListaDimensionRespuestaActual() {
		return listaDimensionRespuestaActual;
	}

	public void setListaDimensionRespuestaActual(List<DimensionBateriaDto2> listaDimensionRespuestaActual) {
		this.listaDimensionRespuestaActual = listaDimensionRespuestaActual;
	}

	public boolean isIniciarTest() {
		return iniciarTest;
	}

	public void setIniciarTest(boolean iniciarTest) {
		this.iniciarTest = iniciarTest;
	}

}
