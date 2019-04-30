package br.ufpa.configuracoes.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Servico database table.
 * 
 */
@Entity
@NamedQuery(name="Servico.findAll", query="SELECT s FROM Servico s")
public class Servico implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idServico;

	@Column(name="descricao", length=255, nullable=false)
	private String descricao;

	//bi-directional many-to-one association to LicoesAprendida
	@OneToMany(mappedBy="servico")
	private List<LicoesAprendida> licoesAprendidas;

	//bi-directional many-to-one association to TipoServico
	@ManyToOne
	@JoinColumn(name="idTipoServico")
	private TipoServico tipoServico;

	public Servico() {
	}

	public int getIdServico() {
		return this.idServico;
	}

	public void setIdServico(int idServico) {
		this.idServico = idServico;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<LicoesAprendida> getLicoesAprendidas() {
		return this.licoesAprendidas;
	}

	public void setLicoesAprendidas(List<LicoesAprendida> licoesAprendidas) {
		this.licoesAprendidas = licoesAprendidas;
	}

	public LicoesAprendida addLicoesAprendida(LicoesAprendida licoesAprendida) {
		getLicoesAprendidas().add(licoesAprendida);
		licoesAprendida.setServico(this);

		return licoesAprendida;
	}

	public LicoesAprendida removeLicoesAprendida(LicoesAprendida licoesAprendida) {
		getLicoesAprendidas().remove(licoesAprendida);
		licoesAprendida.setServico(null);

		return licoesAprendida;
	}

	public TipoServico getTipoServico() {
		return this.tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idServico;
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
		Servico other = (Servico) obj;
		if (idServico != other.idServico)
			return false;
		return true;
	}

}