package mia.core.model.login;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mia.core.model.entities.RestablecerContrasenia;
import mia.core.model.entities.Usuario;
import mia.core.model.util.ModelUtil;

@Stateless
@LocalBean
public class ManagerRestablecerContrasenia {

	@PersistenceContext(unitName = "miaDS")
	private EntityManager em;
    public ManagerRestablecerContrasenia() {
 
    }
    @SuppressWarnings("unchecked")
	public boolean existeUsuario(String correo) {
		String JPQL = "SELECT u FROM Usuario u WHERE u.correo=?1";
		Query query = em.createQuery(JPQL, Usuario.class);
		query.setParameter(1, correo);
		List<Usuario> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		}else
		return true;	
    	
    }
	@SuppressWarnings("unchecked")
	public boolean intentosRestablecerContrasenia(String correo) {
		Date fecha=new Date();
		String JPQL = "SELECT r FROM RestablecerContrasenia r WHERE r.correo=?1 "
				+ " and r.fecha=?2" ;
		Query query = em.createQuery(JPQL, RestablecerContrasenia.class);
		query.setParameter(1, correo);
		query.setParameter(2, fecha);
		List<RestablecerContrasenia> lista;
	
		lista = query.getResultList();
		System.out.println("-----: "+lista.size());
		if (lista.isEmpty()||lista.size()<3) 
			return false;
		else
		return true;	
    	
    }

    @SuppressWarnings("unchecked")
	public void guardarNuevaContrasenia(String correoUsuario,String nuevaContrasenia) throws Exception{
    	boolean correo, nuevaC;
    	correo=ModelUtil.isEmpty(correoUsuario);
    	nuevaC=ModelUtil.isEmpty(nuevaContrasenia);
    	if (nuevaC) {
    		throw new Exception("La contraseña es null" );
		}
    	if (correo) {
    		throw new Exception("Ha ocurrido un error el correo es null." );
		}
    	Usuario usuario=new Usuario();
    	String JPQL = "SELECT u FROM Usuario u WHERE u.correo=?1";
		Query query = em.createQuery(JPQL, Usuario.class);
		query.setParameter(1, correoUsuario);
		List<Usuario> lista;
		lista = query.getResultList();
    	if (lista.isEmpty()) {
    		throw new Exception("Ha ocurrido un error al guardar la contraseña el correo no se ha encontrado." );
		}else {
			usuario=lista.get(0);
			usuario.setPassword(nuevaContrasenia);
		em.merge(usuario);
		}
    	
    }
    
    public RestablecerContrasenia RestablecerContrasenia(String correo, String codigo) throws Exception {
    	boolean a=ModelUtil.isEmpty(codigo);
    	boolean b=ModelUtil.isEmpty(correo);
    	if (a) {
    		throw new Exception("Código generado es null" );
		}
    	if (b) {
    		throw new Exception("El correo es null" );
		}
    	Date fechaHoy=new Date();
    	RestablecerContrasenia restablecer=new RestablecerContrasenia();
    	restablecer.setCodigo(codigo);
    	restablecer.setCorreo(correo);
    	restablecer.setActivo(true);
    	restablecer.setFecha(fechaHoy);
         em.persist(restablecer);
    	
    	return restablecer;
    }
    
	public String GenerarPalabra(){
        String palabra = ""; 
        int caracteres = (int)(Math.random()+2); 
        for (int i=0; i<caracteres; i++){ 
        int codigoAscii = (int)Math.floor(Math.random()*(122 -
        97)+97); 
        System.out.println(codigoAscii);
        palabra = palabra + (char)codigoAscii; 
        } 
        return palabra; 
    }

}
