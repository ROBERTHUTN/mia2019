package mia.core.model.entities;
import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the pais_estado database table.
 * 
 */
@Entity
@Table(name="pais_estado")
@NamedQuery(name="PaisEstado.findAll", query="SELECT p FROM PaisEstado p")
public class PaisEstado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_pais_estado")
	private Integer idPaisEstado;

	private String nombre;

	//bi-directional many-to-one association to FichaPersonal
	@OneToMany(mappedBy="paisEstado1")
	private List<FichaPersonal> fichaPersonals1;

	//bi-directional many-to-one association to FichaPersonal
	@OneToMany(mappedBy="paisEstado2")
	private List<FichaPersonal> fichaPersonals2;

	//bi-directional many-to-one association to PaisEstado
	@ManyToOne
	@JoinColumn(name="id_padre_pais")
	private PaisEstado paisEstado;

	//bi-directional many-to-one association to PaisEstado
	@OneToMany(mappedBy="paisEstado")
	private List<PaisEstado> paisEstados;

	public PaisEstado() {
	}

	public Integer getIdPaisEstado() {
		return this.idPaisEstado;
	}

	public void setIdPaisEstado(Integer idPaisEstado) {
		this.idPaisEstado = idPaisEstado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<FichaPersonal> getFichaPersonals1() {
		return this.fichaPersonals1;
	}

	public void setFichaPersonals1(List<FichaPersonal> fichaPersonals1) {
		this.fichaPersonals1 = fichaPersonals1;
	}

	public FichaPersonal addFichaPersonals1(FichaPersonal fichaPersonals1) {
		getFichaPersonals1().add(fichaPersonals1);
		fichaPersonals1.setPaisEstado1(this);

		return fichaPersonals1;
	}

	public FichaPersonal removeFichaPersonals1(FichaPersonal fichaPersonals1) {
		getFichaPersonals1().remove(fichaPersonals1);
		fichaPersonals1.setPaisEstado1(null);

		return fichaPersonals1;
	}

	public List<FichaPersonal> getFichaPersonals2() {
		return this.fichaPersonals2;
	}

	public void setFichaPersonals2(List<FichaPersonal> fichaPersonals2) {
		this.fichaPersonals2 = fichaPersonals2;
	}

	public FichaPersonal addFichaPersonals2(FichaPersonal fichaPersonals2) {
		getFichaPersonals2().add(fichaPersonals2);
		fichaPersonals2.setPaisEstado2(this);

		return fichaPersonals2;
	}

	public FichaPersonal removeFichaPersonals2(FichaPersonal fichaPersonals2) {
		getFichaPersonals2().remove(fichaPersonals2);
		fichaPersonals2.setPaisEstado2(null);

		return fichaPersonals2;
	}

	public PaisEstado getPaisEstado() {
		return this.paisEstado;
	}

	public void setPaisEstado(PaisEstado paisEstado) {
		this.paisEstado = paisEstado;
	}

	public List<PaisEstado> getPaisEstados() {
		return this.paisEstados;
	}

	public void setPaisEstados(List<PaisEstado> paisEstados) {
		this.paisEstados = paisEstados;
	}

	public PaisEstado addPaisEstado(PaisEstado paisEstado) {
		getPaisEstados().add(paisEstado);
		paisEstado.setPaisEstado(this);

		return paisEstado;
	}

	public PaisEstado removePaisEstado(PaisEstado paisEstado) {
		getPaisEstados().remove(paisEstado);
		paisEstado.setPaisEstado(null);

		return paisEstado;
	}

}