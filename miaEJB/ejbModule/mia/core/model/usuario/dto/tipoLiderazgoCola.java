package mia.core.model.usuario.dto;



public class tipoLiderazgoCola implements Comparable< tipoLiderazgoCola> {

	 private String nombre;
	 private int tipo;
	 
	 
	public tipoLiderazgoCola(String nombre, int tipo) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	 

	 @Override
	    public int compareTo(tipoLiderazgoCola o) {
	        if (tipo < o.getTipo()) {
	            return 1;
	        } else if (tipo > o.getTipo()) {
	            return -1;
	        } else {
	            return 0;
	        }
	    }
}
