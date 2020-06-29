package mia.core.model.cuestionario.dto;

import java.util.List;

import mia.core.model.entities.Cuestionario;
import mia.core.model.entities.Dimension;
import mia.core.model.entities.Opcion;

public class TipoLiderazgo extends Cuestionario{
private String nombre;
private String respuesta;
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getRespuesta() {
	return respuesta;
}
public void setRespuesta(String respuesta) {
	this.respuesta = respuesta;
}
public TipoLiderazgo(String nombre, String respuesta) {
	this.nombre = nombre;
	this.respuesta = respuesta;
}


}
