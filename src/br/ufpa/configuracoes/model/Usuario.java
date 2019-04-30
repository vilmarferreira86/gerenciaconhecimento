package br.ufpa.configuracoes.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idUsuario;

	private String dataNascimento;

	private String nome;

	//bi-directional many-to-one association to Coordenadoria
	@ManyToOne
	@JoinColumn(name="idCoordenadoria")
	private Coordenadoria coordenadoria;

	public Usuario() {
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Coordenadoria getCoordenadoria() {
		return this.coordenadoria;
	}

	public void setCoordenadoria(Coordenadoria coordenadoria) {
		this.coordenadoria = coordenadoria;
	}

}