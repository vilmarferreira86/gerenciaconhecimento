package br.ufpa.configuracoes.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TipoServico database table.
 * 
 */
@Entity
@NamedQuery(name="TipoServico.findAll", query="SELECT t FROM TipoServico t")
public class TipoServico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idTipoServico;

	@Column(name="nome", nullable=false, length=255)
	private String nome;

	//bi-directional many-to-one association to Servico
	@OneToMany(mappedBy="tipoServico")
	private List<Servico> servicos;

	public TipoServico() {
	}

	public int getIdTipoServico() {
		return this.idTipoServico;
	}

	public void setIdTipoServico(int idTipoServico) {
		this.idTipoServico = idTipoServico;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Servico> getServicos() {
		return this.servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public Servico addServico(Servico servico) {
		getServicos().add(servico);
		servico.setTipoServico(this);

		return servico;
	}

	public Servico removeServico(Servico servico) {
		getServicos().remove(servico);
		servico.setTipoServico(null);

		return servico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idTipoServico;
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
		TipoServico other = (TipoServico) obj;
		if (idTipoServico != other.idTipoServico)
			return false;
		return true;
	}
	
	

}