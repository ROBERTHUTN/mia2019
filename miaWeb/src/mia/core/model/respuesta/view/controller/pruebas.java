package mia.core.model.respuesta.view.controller;

public class pruebas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BeanPreguntasMIA beanPreguntasMIA= new BeanPreguntasMIA();
		
		String[] estres = beanPreguntasMIA.nivelEstres;
		int i=0;
		for (String string : estres) {
			
			System.out.print(i+" "+string+" \n");
		i++;
		}

	}

}
