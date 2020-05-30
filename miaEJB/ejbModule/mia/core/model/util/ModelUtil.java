package mia.core.model.util;

public class ModelUtil {
	/**
	 * Verifica si una cadena es igual a null o tiene longitud igual a cero.
	 * @param cadena Cadena para validar.
	 * @return
	 */
	public static boolean isEmpty(String cadena){
		if(cadena==null||cadena.length()==0)
			return true;
		return false;
	}
	public static boolean IgualesString(String cadena,String cadena2){
		
		System.out.println("//"+cadena+" /n"+cadena2 );
		if (cadena.equals(cadena2)) 
			return true;
		else
		return false;
	}
	
	
	/*
	// VALIDAR CÉDULA
	public boolean validarCedula(String cedula) {
		boolean cedulaCorrecta = false;
		try {

			if (cedula.length() == 10) // ConstantesApp.LongitudCedula
			{
				int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
				if (tercerDigito < 6) {
					// Coeficientes de validación cédula
					// El décimo dígito se lo considera dígito verificador
					int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
					int verificador = Integer.parseInt(cedula.substring(9, 10));
					int suma = 0;
					int digito = 0;
					for (int i = 0; i < (cedula.length() - 1); i++) {
						digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
						suma += ((digito % 10) + (digito / 10));
					}

					if ((suma % 10 == 0) && (suma % 10 == verificador)) {
						cedulaCorrecta = true;
					} else if ((10 - (suma % 10)) == verificador) {
						cedulaCorrecta = true;
					} else {
						cedulaCorrecta = false;
					}
				} else {
					cedulaCorrecta = false;
				}
			} else {
				cedulaCorrecta = false;
			}
		} catch (NumberFormatException nfe) {
			cedulaCorrecta = false;
		} catch (Exception err) {
			JSFUtil.crearMensajeError(err.getMessage());
			err.printStackTrace();
			cedulaCorrecta = false;
		}

		if (!cedulaCorrecta) {
			JSFUtil.crearMensajeError("La cédula ingresada es incorrecta");
		}
		return cedulaCorrecta;
	}*/
	
	
}
