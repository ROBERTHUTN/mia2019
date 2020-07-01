package mia.core.model.usuario.dto;

import java.math.BigDecimal;
import java.util.List;

import mia.core.model.entities.Curso;
import mia.core.model.entities.Usuario;
import mia.core.model.entities.UsuarioCurso;
import mia.core.model.entities.UsuarioCursoModulo;

public class UserCursoModuloDTO extends UsuarioCurso {

	private static final long serialVersionUID = 1L;
	List<UsuarioCursoModulo> listaUsuarioCurMod;

	@Override
	public long getIdUsuariocurso() {

		return super.getIdUsuariocurso();
	}

	@Override
	public void setIdUsuariocurso(long idUsuariocurso) {

		super.setIdUsuariocurso(idUsuariocurso);
	}

	@Override
	public BigDecimal getAvance() {

		return super.getAvance();
	}

	@Override
	public void setAvance(BigDecimal avance) {

		super.setAvance(avance);
	}

	@Override
	public String getModulorealizados() {

		return super.getModulorealizados();
	}

	@Override
	public void setModulorealizados(String modulorealizados) {

		super.setModulorealizados(modulorealizados);
	}

	@Override
	public Curso getCurso() {

		return super.getCurso();
	}

	@Override
	public void setCurso(Curso curso) {

		super.setCurso(curso);
	}

	@Override
	public Usuario getUsuario() {

		return super.getUsuario();
	}

	@Override
	public void setUsuario(Usuario usuario) {

		super.setUsuario(usuario);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {

		return super.clone();
	}

	@Override
	public boolean equals(Object obj) {

		return super.equals(obj);
	}

	@Override
	protected void finalize() throws Throwable {

		super.finalize();
	}

	@Override
	public int hashCode() {

		return super.hashCode();
	}

	@Override
	public String toString() {

		return super.toString();
	}

	public List<UsuarioCursoModulo> getListaUsuarioCurMod() {
		return listaUsuarioCurMod;
	}

	public void setListaUsuarioCurMod(List<UsuarioCursoModulo> listaUsuarioCurMod) {
		this.listaUsuarioCurMod = listaUsuarioCurMod;
	}



}
