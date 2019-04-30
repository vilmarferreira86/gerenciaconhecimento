package br.ufpa.configuracoes.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Gerencia database table.
 * 
 */
@Entity
@NamedQuery(name="Gerencia.findAll", query="SELECT g FROM Gerencia g")
public class Gerencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idGerencia;

	@Column(name="nome", length=100)
	private String nome;
	
	@Column(name="sigla", length=10)
	private String sigla;

	//bi-directional many-to-one association to Coordenadoria
	@OneToMany(mappedBy="gerencia")
	private List<Coordenadoria> coordenadorias;

	public Gerencia() {
	}

	public int getIdGerencia() {
		return this.idGerencia;
	}

	public void setIdGerencia(int idGerencia) {
		this.idGerencia = idGerencia;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return this.sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public List<Coordenadoria> getCoordenadorias() {
		return this.coordenadorias;
	}

	public void setCoordenadorias(List<Coordenadoria> coordenadorias) {
		this.coordenadorias = coordenadorias;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idGerencia;
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
		Gerencia other = (Gerencia) obj;
		if (idGerencia != other.idGerencia)
			return false;
		return true;
	}

/*	public Coordenadoria addCoordenadoria(Coordenadoria coordenadoria) {
		getCoordenadorias().add(coordenadoria);
		coordenadoria.setGerencia(this);

		return coordenadoria;
	}

	public Coordenadoria removeCoordenadoria(Coordenadoria coordenadoria) {
		getCoordenadorias().remove(coordenadoria);
		coordenadoria.setGerencia(null);

		return coordenadoria;
	}*/
	
	

}