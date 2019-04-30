package br.ufpa.configuracoes.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Coordenadoria database table.
 * 
 */
@Entity
@NamedQuery(name="Coordenadoria.findAll", query="SELECT c FROM Coordenadoria c")
public class Coordenadoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCoordenadoria;
	private String nome;
	private String sigla;
	private boolean situacao;
	//bi-directional many-to-one association to Gerencia
	@ManyToOne
	@JoinColumn(name="idGerencia")
	private Gerencia gerencia;

	//bi-directional many-to-one association to Sistema
	@OneToMany(mappedBy="coordenadoria")
	private List<Sistema> sistemas;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="coordenadoria")
	private List<Usuario> usuarios;

	public Coordenadoria() {
	}

	public int getIdCoordenadoria() {
		return this.idCoordenadoria;
	}

	public void setIdCoordenadoria(int idCoordenadoria) {
		this.idCoordenadoria = idCoordenadoria;
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

	public boolean getSituacao() {
		return this.situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}

	public Gerencia getGerencia() {
		return this.gerencia;
	}

	public void setGerencia(Gerencia gerencia) {
		this.gerencia = gerencia;
	}

	public List<Sistema> getSistemas() {
		return this.sistemas;
	}

	public void setSistemas(List<Sistema> sistemas) {
		this.sistemas = sistemas;
	}

	public Sistema addSistema(Sistema sistema) {
		getSistemas().add(sistema);
		sistema.setCoordenadoria(this);

		return sistema;
	}

	public Sistema removeSistema(Sistema sistema) {
		getSistemas().remove(sistema);
		sistema.setCoordenadoria(null);

		return sistema;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setCoordenadoria(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setCoordenadoria(null);

		return usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCoordenadoria;
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
		Coordenadoria other = (Coordenadoria) obj;
		if (idCoordenadoria != other.idCoordenadoria)
			return false;
		return true;
	}
	
	

}