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
int cont=1;
	@EJB
	BateriaServiceDto managerBateria;
	
private String tlCentroGravedad;
private String tlLogicaEmergente;
private String tlLogicaretroceso;

	
	
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
	//finalizarTest(listaDimensionRespuesta);
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
	
}
public void finalizarTest(List<DimensionBateriaDto2> respuesta ) {
	
	System.out.println(" cantidad de datos"+ respuesta.size());
	
	int [][] posTl = new int[6][5];
	for (DimensionBateriaDto2 d : respuesta) {
		System.out.println("Literal; "+d.getNombre());//literal
		for (BateriaDto  r: d.getListaRespuestas()) {
			System.out.println("Respuesta; "+r.getPregunta()+" Puntuacion; "+r.getPosicion()+" "+ r.getLiteral());
			
			if(r.getLiteral()=="a7"||r.getLiteral()=="b2"||r.getLiteral()=="c1"||r.getLiteral()=="d5"||r.getLiteral()=="e1"||r.getLiteral()=="f2")
			{
				int o=0;
				int []oportunista= new int[6];
				oportunista[o]=r.getPosicion();
				o++;
			}
			if(r.getLiteral()=="a5"||r.getLiteral()=="b1"||r.getLiteral()=="c3"||r.getLiteral()=="d3"||r.getLiteral()=="e4"||r.getLiteral()=="f1")
			{
				int o=0;
				int []diplomatico= new int[6];
				diplomatico[o]=r.getPosicion();
				o++;
			}
			if(r.getLiteral()=="a2"||r.getLiteral()=="b7"||r.getLiteral()=="c4"||r.getLiteral()=="d1"||r.getLiteral()=="e5"||r.getLiteral()=="f5")
			{
				int o=0;
				int []experto= new int[6];
				experto[o]=r.getPosicion();
				o++;
			}
			if(r.getLiteral()=="a6"||r.getLiteral()=="b4"||r.getLiteral()=="c6"||r.getLiteral()=="d4"||r.getLiteral()=="e3"||r.getLiteral()=="f6")
			{
				int o=0;
				int []redefiniendo= new int[6];
				redefiniendo[o]=r.getPosicion();
				o++;
			}
			if(r.getLiteral()=="a1"||r.getLiteral()=="b6"||r.getLiteral()=="c5"||r.getLiteral()=="d6"||r.getLiteral()=="e6"||r.getLiteral()=="f3")
			{
				int o=0;
				int []transformador= new int[6];
				transformador[o]=r.getPosicion();
				o++;
			}
			if(r.getLiteral()=="a3"||r.getLiteral()=="b5"||r.getLiteral()=="c7"||r.getLiteral()=="d7"||r.getLiteral()=="e7"||r.getLiteral()=="f7")
			{
				int o=0;
				int []alquimico= new int[6];
				alquimico[o]=r.getPosicion();
				o++;
			}
			if(r.getLiteral()=="a4"||r.getLiteral()=="b3"||r.getLiteral()=="c2"||r.getLiteral()=="d2"||r.getLiteral()=="e2"||r.getLiteral()=="f4")
			{
				int o=0;
				int []impulsivo= new int[6];
				impulsivo[o]=r.getPosicion();
				o++;
			}
		/**
		posTl [0][0]= r.getLiteral()=="a7"?r.getPosicion():0;
		posTl [1][0]= r.getLiteral()=="a5"?r.getPosicion():0;
		posTl [2][0]= r.getLiteral()=="a2"?r.getPosicion():0;
		posTl [3][0]= r.getLiteral()=="a6"?r.getPosicion():0;
		posTl [4][0]= r.getLiteral()=="a1"?r.getPosicion():0;
		posTl [5][0]= r.getLiteral()=="a3"?r.getPosicion():0;
		posTl [6][0]= r.getLiteral()=="a4"?r.getPosicion():0;
		
		posTl [0][1]= r.getLiteral()=="b2"?r.getPosicion():0;
		posTl [1][1]= r.getLiteral()=="b1"?r.getPosicion():0;
		posTl [2][1]= r.getLiteral()=="b7"?r.getPosicion():0;
		posTl [3][1]= r.getLiteral()=="b4"?r.getPosicion():0;
		posTl [4][1]= r.getLiteral()=="b6"?r.getPosicion():0;
		posTl [5][1]= r.getLiteral()=="b5"?r.getPosicion():0;
		posTl [6][1]= r.getLiteral()=="b3"?r.getPosicion():0;
		
		posTl [0][2]= r.getLiteral()=="c1"?r.getPosicion():0;
		posTl [1][2]= r.getLiteral()=="c3"?r.getPosicion():0;
		posTl [2][2]= r.getLiteral()=="c4"?r.getPosicion():0;
		posTl [3][2]= r.getLiteral()=="c6"?r.getPosicion():0;
		posTl [4][2]= r.getLiteral()=="c5"?r.getPosicion():0;
		posTl [5][2]= r.getLiteral()=="c7"?r.getPosicion():0;
		posTl [6][2]= r.getLiteral()=="c2"?r.getPosicion():0;
		
		posTl [0][3]= r.getLiteral()=="d5"?r.getPosicion():0;
		posTl [1][3]= r.getLiteral()=="d3"?r.getPosicion():0;
		posTl [2][3]= r.getLiteral()=="d1"?r.getPosicion():0;
		posTl [3][3]= r.getLiteral()=="d4"?r.getPosicion():0;
		posTl [4][3]= r.getLiteral()=="d6"?r.getPosicion():0;
		posTl [5][3]= r.getLiteral()=="d7"?r.getPosicion():0;
		posTl [6][3]= r.getLiteral()=="d2"?r.getPosicion():0;
		
		posTl [0][4]= r.getLiteral()=="e1"?r.getPosicion():0;
		posTl [1][4]= r.getLiteral()=="e4"?r.getPosicion():0;
		posTl [2][4]= r.getLiteral()=="e5"?r.getPosicion():0;
		posTl [3][4]= r.getLiteral()=="e3"?r.getPosicion():0;
		posTl [4][4]= r.getLiteral()=="e6"?r.getPosicion():0;
		posTl [5][4]= r.getLiteral()=="e7"?r.getPosicion():0;
		posTl [6][4]= r.getLiteral()=="e2"?r.getPosicion():0;
		
		posTl [0][5]= r.getLiteral()=="f2"?r.getPosicion():0;
		posTl [1][5]= r.getLiteral()=="f1"?r.getPosicion():0;
		posTl [2][5]= r.getLiteral()=="f5"?r.getPosicion():0;
		posTl [3][5]= r.getLiteral()=="f6"?r.getPosicion():0;
		posTl [4][5]= r.getLiteral()=="f3"?r.getPosicion():0;
		posTl [5][5]= r.getLiteral()=="f7"?r.getPosicion():0;
		posTl [6][5]= r.getLiteral()=="f4"?r.getPosicion():0;
		*/
		}
	}
	
    for(int i = 0; i < posTl.length; i++){
    	for(int j = 0; j < posTl[i].length; j++){
    		System.out.print(posTl[i][j] + " ");	// Imprime elemento
    	}
    	System.out.println();	// Imprime salto de línea
    }
	
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
	
	
}
