package mia.core.model.login;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mia.core.model.administrador.ManagerAdministrador;
import mia.core.model.entities.FichaPersonal;
import mia.core.model.entities.Rol;
import mia.core.model.entities.Usuario;

@Stateless
@LocalBean
public class ManagerLogin {

	@PersistenceContext(unitName = "miaDS")
	private EntityManager em;
@EJB
	private ManagerAdministrador managerAdministrador;
    public ManagerLogin() {

    }
public Login comprobarCredenciales(String correo,String contrasenia) throws Exception {
	Login login=new Login();
	List<Usuario>usuarios=existeUsuario( correo, contrasenia);
Usuario usuario;
	if (usuarios.isEmpty()) {
		throw new Exception("Credenciales incorrectas intente otra vez");
	}else {
		usuario=usuarios.get(0);
	login.setId_usuario(usuario.getIdUsuario());
	login.setNombres(usuario.getApellidos()+" "+usuario.getNombres());
	login.setRol(usuario.getRol().getNombreRol());
	login.setIsnuevo(usuarioInseguro(usuario));
	login.setTieneFicha(usuarioTieneFicha(usuario.getIdUsuario()));
	login.setActivo(usuario.getActivo());
	}
	return login;
	
}
	public boolean usuarioInseguro(Usuario usuario) {
	if (usuario.getPassword().equals(usuario.getCi())) {
		return true;
	}else
		return false;
	}
	@SuppressWarnings("unchecked")
	public List<Usuario> existeUsuario(String correo,String contrasenia){
		String JPQL = "SELECT u FROM Usuario u WHERE u.correo=?1 and u.password=?2";
		Query query = em.createQuery(JPQL, Usuario.class);
		query.setParameter(1, correo);
		query.setParameter(2, contrasenia);
		List<Usuario> lista;
		lista = query.getResultList();
		return lista;	
	}
	
	public Login registroUsuario(Usuario nuevoUsuario) throws Exception {
		Login login=new Login();
		boolean correoRepetido, cedulaRepetida;
		cedulaRepetida=managerAdministrador.existeCedulaUsuario(nuevoUsuario.getCi());
		correoRepetido=managerAdministrador.existeCorreoUsuario(nuevoUsuario.getCorreo());
		if (cedulaRepetida) {
			throw new Exception("La cédula ya está registrada con otro usuario");	
		}
		if (correoRepetido) {
			throw new Exception("El correo ya se encuentra registrado con otro usuario");
		}
		Rol rol=new Rol();
		rol=managerAdministrador.findRolById(5);
		Usuario usuario=new Usuario();
		usuario.setActivo(true);
		usuario.setRol(rol);
		usuario.setApellidos(nuevoUsuario.getApellidos());
		usuario.setNombres(nuevoUsuario.getNombres());
		usuario.setCi(nuevoUsuario.getCi());
		usuario.setCorreo(nuevoUsuario.getCorreo());
		usuario.setPassword(nuevoUsuario.getPassword());
		em.persist(usuario);
		System.out.println("ENTRA");
		login.setId_usuario(usuario.getIdUsuario());
		System.out.println("NO ASE");
		login.setNombres(usuario.getApellidos()+" "+usuario.getNombres());
		login.setRol(usuario.getRol().getNombreRol());
		login.setIsnuevo(usuarioInseguro(usuario));
		login.setTieneFicha(usuarioTieneFicha(usuario.getIdUsuario()));
		login.setActivo(usuario.getActivo());
		
		return login;
		
	}

	@SuppressWarnings("unchecked")
	public boolean usuarioTieneFicha(long id_usuario) {
		String JPQL = "SELECT f FROM FichaPersonal f WHERE f.usuario.idUsuario="+id_usuario;
		Query query = em.createQuery(JPQL, FichaPersonal.class);
		List<FichaPersonal> lista;
		lista = query.getResultList();
		if (lista.isEmpty()) {
			return false;
		} else
			return true;
	}


}