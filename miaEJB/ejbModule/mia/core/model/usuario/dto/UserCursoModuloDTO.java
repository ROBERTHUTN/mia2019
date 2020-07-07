package mia.core.model.usuario.dto;

import java.math.BigDecimal;
import java.util.Date;

import mia.core.model.entities.CursoModulo;
import mia.core.model.entities.UsuarioCurso;
import mia.core.model.entities.UsuarioCursoModulo;

public class UserCursoModuloDTO extends UsuarioCursoModulo {

	private static final long serialVersionUID = 1L;
	private BigDecimal retrasoPlanificado;
	private BigDecimal avancePlanificado;
	@Override
	public long getIdUsuarioCursoModulo() {
		 
		return super.getIdUsuarioCursoModulo();
	}
	@Override
	public void setIdUsuarioCursoModulo(long idUsuarioCursoModulo) {
		 
		super.setIdUsuarioCursoModulo(idUsuarioCursoModulo);
	}
	@Override
	public Integer getAciertos() {
		 
		return super.getAciertos();
	}
	@Override
	public void setAciertos(Integer aciertos) {
		 
		super.setAciertos(aciertos);
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
	public Integer getErrores() {
		 
		return super.getErrores();
	}
	@Override
	public void setErrores(Integer errores) {
		 
		super.setErrores(errores);
	}
	@Override
	public Date getFechaFinProgramada() {
		 
		return super.getFechaFinProgramada();
	}
	@Override
	public void setFechaFinProgramada(Date fechaFinProgramada) {
		 
		super.setFechaFinProgramada(fechaFinProgramada);
	}
	@Override
	public Date getFechaFinReal() {
		 
		return super.getFechaFinReal();
	}
	@Override
	public void setFechaFinReal(Date fechaFinReal) {
		 
		super.setFechaFinReal(fechaFinReal);
	}
	@Override
	public Date getFechaInicioProgramada() {
		 
		return super.getFechaInicioProgramada();
	}
	@Override
	public void setFechaInicioProgramada(Date fechaInicioProgramada) {
		 
		super.setFechaInicioProgramada(fechaInicioProgramada);
	}
	@Override
	public Date getFechaInicioReal() {
		 
		return super.getFechaInicioReal();
	}
	@Override
	public void setFechaInicioReal(Date fechaInicioReal) {
		 
		super.setFechaInicioReal(fechaInicioReal);
	}
	@Override
	public CursoModulo getCursoModulo() {
		 
		return super.getCursoModulo();
	}
	@Override
	public void setCursoModulo(CursoModulo cursoModulo) {
		 
		super.setCursoModulo(cursoModulo);
	}
	@Override
	public UsuarioCurso getUsuarioCurso() {
		 
		return super.getUsuarioCurso();
	}
	@Override
	public void setUsuarioCurso(UsuarioCurso usuarioCurso) {
	 
		super.setUsuarioCurso(usuarioCurso);
	}

	public BigDecimal getRetrasoPlanificado() {
		return retrasoPlanificado;
	}
	public void setRetrasoPlanificado(BigDecimal retrasoPlanificado) {
		this.retrasoPlanificado = retrasoPlanificado;
	}
	public BigDecimal getAvancePlanificado() {
		return avancePlanificado;
	}
	public void setAvancePlanificado(BigDecimal avancePlanificado) {
		this.avancePlanificado = avancePlanificado;
	}
	

}
