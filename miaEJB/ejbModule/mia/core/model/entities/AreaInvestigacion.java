package mia.core.model.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the area_investigacion database table.
 * 
 */
@Entity
@Table(name="area_investigacion")
@NamedQuery(name="AreaInvestigacion.findAll", query="SELECT a FROM AreaInvestigacion a")
public class AreaInvestigacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="AREA_INVESTIGACION_AREAID_GENERATOR", sequenceName="SEQ_AREA_INVESTIGACION", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AREA_INVESTIGACION_AREAID_GENERATOR")
	@Column(name="area_id")
	private Integer areaId;

	@Column(name="descripcion_investigacion")
	private String descripcionInvestigacion;

	@Column(name="nombre_investigacion")
	private String nombreInvestigacion;

	//bi-directional many-to-one association to ProyectoInvestigacion
	@OneToMany(mappedBy="areaInvestigacion")
	private List<ProyectoInvestigacion> proyectoInvestigacions;

	//bi-directional many-to-one association to UsuarioInteresArea
	@OneToMany(mappedBy="areaInvestigacion")
	private List<UsuarioInteresArea> usuarioInteresAreas;

	public AreaInvestigacion() {
	}

	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getDescripcionInvestigacion() {
		return this.descripcionInvestigacion;
	}

	public void setDescripcionInvestigacion(String descripcionInvestigacion) {
		this.descripcionInvestigacion = descripcionInvestigacion;
	}

	public String getNombreInvestigacion() {
		return this.nombreInvestigacion;
	}

	public void setNombreInvestigacion(String nombreInvestigacion) {
		this.nombreInvestigacion = nombreInvestigacion;
	}

	public List<ProyectoInvestigacion> getProyectoInvestigacions() {
		return this.proyectoInvestigacions;
	}

	public void setProyectoInvestigacions(List<ProyectoInvestigacion> proyectoInvestigacions) {
		this.proyectoInvestigacions = proyectoInvestigacions;
	}

	public ProyectoInvestigacion addProyectoInvestigacion(ProyectoInvestigacion proyectoInvestigacion) {
		getProyectoInvestigacions().add(proyectoInvestigacion);
		proyectoInvestigacion.setAreaInvestigacion(this);

		return proyectoInvestigacion;
	}

	public ProyectoInvestigacion removeProyectoInvestigacion(ProyectoInvestigacion proyectoInvestigacion) {
		getProyectoInvestigacions().remove(proyectoInvestigacion);
		proyectoInvestigacion.setAreaInvestigacion(null);

		return proyectoInvestigacion;
	}

	public List<UsuarioInteresArea> getUsuarioInteresAreas() {
		return this.usuarioInteresAreas;
	}

	public void setUsuarioInteresAreas(List<UsuarioInteresArea> usuarioInteresAreas) {
		this.usuarioInteresAreas = usuarioInteresAreas;
	}

	public UsuarioInteresArea addUsuarioInteresArea(UsuarioInteresArea usuarioInteresArea) {
		getUsuarioInteresAreas().add(usuarioInteresArea);
		usuarioInteresArea.setAreaInvestigacion(this);

		return usuarioInteresArea;
	}

	public UsuarioInteresArea removeUsuarioInteresArea(UsuarioInteresArea usuarioInteresArea) {
		getUsuarioInteresAreas().remove(usuarioInteresArea);
		usuarioInteresArea.setAreaInvestigacion(null);

		return usuarioInteresArea;
	}

}