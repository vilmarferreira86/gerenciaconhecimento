package br.ufpa.configuracoes.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Sistema database table.
 * 
 */
@Entity
@NamedQuery(name="Sistema.findAll", query="SELECT s FROM Sistema s")
public class Sistema implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSistema;

	private String descricao;

	private String nome;

	//bi-directional many-to-one association to LicoesAprendida
	@OneToMany(mappedBy="sistema")
	private List<LicoesAprendida> licoesAprendidas;

	//bi-directional many-to-one association to Coordenadoria
	@ManyToOne
	@JoinColumn(name="idCoordenadoria")
	private Coordenadoria coordenadoria;

	public Sistema() {
	}

	public int getIdSistema() {
		return this.idSistema;
	}

	public void setIdSistema(int idSistema) {
		this.idSistema = idSistema;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<LicoesAprendida> getLicoesAprendidas() {
		return this.licoesAprendidas;
	}

	public void setLicoesAprendidas(List<LicoesAprendida> licoesAprendidas) {
		this.licoesAprendidas = licoesAprendidas;
	}

	public LicoesAprendida addLicoesAprendida(LicoesAprendida licoesAprendida) {
		getLicoesAprendidas().add(licoesAprendida);
		licoesAprendida.setSistema(this);

		return licoesAprendida;
	}

	public LicoesAprendida removeLicoesAprendida(LicoesAprendida licoesAprendida) {
		getLicoesAprendidas().remove(licoesAprendida);
		licoesAprendida.setSistema(null);

		return licoesAprendida;
	}

	public Coordenadoria getCoordenadoria() {
		return this.coordenadoria;
	}

	public void setCoordenadoria(Coordenadoria coordenadoria) {
		this.coordenadoria = coordenadoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idSistema;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sistema other = (Sistema) obj;
		if (idSistema != other.idSistema)
			return false;
		return true;
	}
	
	

}