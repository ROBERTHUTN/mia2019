package mia.core.model.usuario.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mia.core.model.entities.Curso;
import mia.core.model.entities.Usuario;
import mia.core.model.entities.UsuarioCurso;
import mia.core.model.entities.UsuarioCursoModulo;

public class UsuarioCursoDTO extends UsuarioCurso {

private static final long serialVersionUID = 1L;
private BigDecimal retrasoPlanificado;
private BigDecimal avancePlanificado;
private List<UserCursoModuloDTO>listaCursosModulosDTO;
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
public UsuarioCurso getUsuarioCurso() {
	 
	return super.getUsuarioCurso();
}
@Override
public void setUsuarioCurso(UsuarioCurso usuarioCurso) {
	 
	super.setUsuarioCurso(usuarioCurso);
}
@Override
public List<UsuarioCurso> getUsuarioCursos() {
	 
	return super.getUsuarioCursos();
}
@Override
public void setUsuarioCursos(List<UsuarioCurso> usuarioCursos) {
	 
	super.setUsuarioCursos(usuarioCursos);
}
@Override
public UsuarioCurso addUsuarioCurso(UsuarioCurso usuarioCurso) {
	 
	return super.addUsuarioCurso(usuarioCurso);
}
@Override
public UsuarioCurso removeUsuarioCurso(UsuarioCurso usuarioCurso) {
	 
	return super.removeUsuarioCurso(usuarioCurso);
}
@Override
public List<UsuarioCursoModulo> getUsuarioCursoModulos() {
	 
	return super.getUsuarioCursoModulos();
}
@Override
public void setUsuarioCursoModulos(List<UsuarioCursoModulo> usuarioCursoModulos) {
	 
	super.setUsuarioCursoModulos(usuarioCursoModulos);
}
@Override
public UsuarioCursoModulo addUsuarioCursoModulo(UsuarioCursoModulo usuarioCursoModulo) {
	 
	return super.addUsuarioCursoModulo(usuarioCursoModulo);
}
@Override
public UsuarioCursoModulo removeUsuarioCursoModulo(UsuarioCursoModulo usuarioCursoModulo) {
	 
	return super.removeUsuarioCursoModulo(usuarioCursoModulo);
}

public BigDecimal getAvancePlanificado() {
	return avancePlanificado;
}
public void setAvancePlanificado(BigDecimal avancePlanificado) {
	this.avancePlanificado = avancePlanificado;
}
public List<UserCursoModuloDTO> getListaCursosModulosDTO() {
	return listaCursosModulosDTO;
}
public void setListaCursosModulosDTO(List<UserCursoModuloDTO> listaCursosModulosDTO) {
	this.listaCursosModulosDTO = listaCursosModulosDTO;
}
public BigDecimal getRetrasoPlanificado() {
	return retrasoPlanificado;
}
public void setRetrasoPlanificado(BigDecimal retrasoPlanificado) {
	this.retrasoPlanificado = retrasoPlanificado;
}


}
