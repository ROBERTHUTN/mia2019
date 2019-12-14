package mia.core.model.usuario.dto;

import java.math.BigDecimal;

import mia.core.model.entities.Curso;
import mia.core.model.entities.CursoModulo;
import mia.core.model.entities.Modulo;
import mia.core.model.entities.UsuarioCurso;

public class UserCursoModuloDTO extends CursoModulo {

	private static final long serialVersionUID = 1L;
	
	private UsuarioCurso usuarioCurso;
	private BigDecimal avance;
	private String modulosrealizados;
	
	public UsuarioCurso getUsuarioCurso() {
		return usuarioCurso;
	}

	public void setUsuarioCurso(UsuarioCurso usuarioCurso) {
		this.usuarioCurso = usuarioCurso;
	}

	@Override
	public long getIdCursoModulo() {
		
		return super.getIdCursoModulo();
	}

	@Override
	public void setIdCursoModulo(long idCursoModulo) {
		
		super.setIdCursoModulo(idCursoModulo);
	}

	@Override
	public Integer getOrdenCurso() {
		
		return super.getOrdenCurso();
	}

	@Override
	public void setOrdenCurso(Integer ordenCurso) {
		
		super.setOrdenCurso(ordenCurso);
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
	public Modulo getModulo() {
		
		return super.getModulo();
	}

	@Override
	public void setModulo(Modulo modulo) {
		
		super.setModulo(modulo);
	}



	public BigDecimal getAvance() {
		return avance;
	}

	public void setAvance(BigDecimal avance) {
		this.avance = avance;
	}

	public String getModulosrealizados() {
		return modulosrealizados;
	}

	public void setModulosrealizados(String modulosrealizados) {
		this.modulosrealizados = modulosrealizados;
	}

	
	
}
