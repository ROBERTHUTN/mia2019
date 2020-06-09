package mia.core.model.usuario.dto;

import java.util.ArrayList;
import java.util.List;
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
int cont=1;
	@EJB
	BateriaServiceDto managerBateria;
	
private String tlCentroGravedad;
private String tlLogicaEmergente;
private String tlLogicaretroceso;

	public void iniciarCuestionario() {
		iniciarTest=true;
		System.out.println(""+iniciarTest);
	}
	
public void atras() {
	//listaDimensionRespuesta
	listaDimensionRespuesta.set(contador, listaDimensionRespuestaActual.get(0));
	contador--;
	estadoActualContador(contador);
	dimensionActDto=listaDimensionRespuesta.get(contador);
	listaDimensionRespuestaActual=new ArrayList<>();
	listaDimensionRespuestaActual.add(dimensionActDto);
}
public String siguiente() {
	try {
	DimensionBateriaDto2 prePact=listaDimensionRespuestaActual.get(0);
	
	List<BateriaDto>listapreguntas=prePact.getListaPreguntas();
	List<BateriaDto>listarespuestas=prePact.getListaRespuestas();
System.out.println(""+listapreguntas.size());
System.out.println(""+listarespuestas.size());
	if(listapreguntas.size()==0 && listarespuestas.size()==7) {
	listaDimensionRespuesta.set(contador, listaDimensionRespuestaActual.get(0));
	
	contador++;
	estadoActualContador(contador);
	dimensionActDto=new DimensionBateriaDto2();
	dimensionActDto=listaDimensionRespuesta.get(contador);
	if (dimensionActDto.getNombre().length()==0) {
		System.out.println("3");
	}
	if(cont!=1)
	{
		cont=1;
	}
	listaDimensionRespuestaActual=new ArrayList<>();
	listaDimensionRespuestaActual.add(dimensionActDto);
	
	}else {
		JSFUtil.crearMensajeError("Conteste todas las preguntas");
	}
	} catch (Exception e) {
		JSFUtil.crearMensajeError(e.getMessage());
	}
	
	return "";
}



public void finalizarTest() {

	//System.out.println(" cantidad de datos"+ respuesta.size());
	

	int []oportunista= new int[6];
	int []diplomatico= new int[6];
	int []experto= new int[6];
	int []redefiniendo= new int[6];
	int []transformador= new int[6];
	int []alquimico= new int[6];
	int []impulsivo= new int[6];
	
	int o=0, di=0,e=0,re=0,t=0,a=0,i=0;
	
	for (DimensionBateriaDto2 d : listaDimensionRespuesta) {
		System.out.println("Literal; "+d.getNombre());//literal
		for (BateriaDto  r: d.getListaRespuestas()) {
		//	System.out.println("Respuesta; "+r.getPregunta()+" Puntuacion; "+r.getPosicion()+" "+ r.getLiteral());
			
			if(r.getLiteral()=="a7"||r.getLiteral()=="b2"||r.getLiteral()=="c1"||r.getLiteral()=="d5"||r.getLiteral()=="e1"||r.getLiteral()=="f2")
			{
				
				oportunista[o]=r.getPosicion();
				o++;
				System.out.println("entra"+o);
				
			
				
			}else{
			if(r.getLiteral()=="a5"||r.getLiteral()=="b1"||r.getLiteral()=="c3"||r.getLiteral()=="d3"||r.getLiteral()=="e4"||r.getLiteral()=="f1")
			{
				
				diplomatico[di]=r.getPosicion();
				di++;
				continue;
			}else {
			if(r.getLiteral()=="a2"||r.getLiteral()=="b7"||r.getLiteral()=="c4"||r.getLiteral()=="d1"||r.getLiteral()=="e5"||r.getLiteral()=="f5")
			{
				experto[e]=r.getPosicion();
				e++;
				continue;
			}else {
			if(r.getLiteral()=="a6"||r.getLiteral()=="b4"||r.getLiteral()=="c6"||r.getLiteral()=="d4"||r.getLiteral()=="e3"||r.getLiteral()=="f6")
			{
				redefiniendo[re]=r.getPosicion();
				re++;
				continue;
			}else {
			if(r.getLiteral()=="a1"||r.getLiteral()=="b6"||r.getLiteral()=="c5"||r.getLiteral()=="d6"||r.getLiteral()=="e6"||r.getLiteral()=="f3")
			{
				
				transformador[t]=r.getPosicion();
				t++;
				continue;
			}else {
			if(r.getLiteral()=="a3"||r.getLiteral()=="b5"||r.getLiteral()=="c7"||r.getLiteral()=="d7"||r.getLiteral()=="e7"||r.getLiteral()=="f7")
			{
				
				alquimico[a]=r.getPosicion();
				a++;
				continue;
			}else {
				//a4, b3, c2,d2,e2,f4
				impulsivo[i]=r.getPosicion();
				System.out.println("impulsivo[i] "+i+" valor; "+r.getPosicion());
				i++;
			}
			}
			}
			}
			}
		}
			
		}
	}
	
for (int j = 0; j < impulsivo.length; j++) {
	System.out.println("posV; "+j+"cali;"+impulsivo[j]);

}
	
}

public void TipoLiderazgo(int[] oportunista ,int[] diplomatico, int[] experto, int[] redefiniendo,int[] transformador,int[]  alquimista, int[] impulsivo ) {
	
	
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
	DimensionBateriaDto2 prePact=listaDimensionRespuestaActual.get(0);
	List<BateriaDto>listapreguntas=prePact.getListaPreguntas();
	List<BateriaDto>listarespuestas=prePact.getListaRespuestas();
	BateriaDto bat= (BateriaDto)event.getData();
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
		iniciarTest=false;
		listaDimensionRespuesta=managerBateria.inicializarPreguntasPadres();
		listaDimensionRespuestaActual=new ArrayList<DimensionBateriaDto2>();
		listaDimensionRespuestaActual.add(listaDimensionRespuesta.get(0));
	
		contador = 0;
			estadoActualContador(contador);

			JSFUtil.crearMensajeInfo("Responder las siguientes preguntas");

			return "tipoLiderazgo2?faces-redirect=true";
	
	} catch (Exception e) {
		JSFUtil.crearMensajeError(e.getMessage());
		return "";
	}finally {
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
