package br.ufpa.configuracoes.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the LicoesAprendida database table.
 * 
 */
@Entity
@NamedQuery(name="LicoesAprendida.findAll", query="SELECT l FROM LicoesAprendida l")
public class LicoesAprendida implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idLicaoAprendida;

	private String assunto;

	private String numeroIncidente;
	@Column(name="numeroProblema", length=10, nullable=true)
	private String numeroProblema;

	private String problemaRelatado;
	@Column(columnDefinition = "TEXT", length=1000)
	private String solucaoContorno;
	@Column(columnDefinition = "TEXT", length=1000)
	private String solucaoRaiz;
	@Temporal(TemporalType.DATE)
	private java.util.Date data;

	//bi-directional many-to-one association to Sistema
	@ManyToOne
	@JoinColumn(name="idSistema")
	private Sistema sistema;

	//bi-directional many-to-one association to Servico
	@ManyToOne
	@JoinColumn(name="idServico")
	private Servico servico;
	
	@Lob
	@Column
	private byte[] anexo;

	public LicoesAprendida() {
	}

	public int getIdLicaoAprendida() {
		return this.idLicaoAprendida;
	}

	public void setIdLicaoAprendida(int idLicaoAprendida) {
		this.idLicaoAprendida = idLicaoAprendida;
	}

	public String getAssunto() {
		return this.assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getNumeroIncidente() {
		return this.numeroIncidente;
	}

	public void setNumeroIncidente(String numeroIncidente) {
		this.numeroIncidente = numeroIncidente;
	}

	public String getNumeroProblema() {
		return this.numeroProblema;
	}

	public void setNumeroProblema(String numeroProblema) {
		this.numeroProblema = numeroProblema;
	}

	public String getProblemaRelatado() {
		return this.problemaRelatado;
	}

	public void setProblemaRelatado(String problemaRelatado) {
		this.problemaRelatado = problemaRelatado;
	}

	public String getSolucaoContorno() {
		return this.solucaoContorno;
	}

	public void setSolucaoContorno(String solucaoContorno) {
		this.solucaoContorno = solucaoContorno;
	}

	public String getSolucaoRaiz() {
		return this.solucaoRaiz;
	}

	public void setSolucaoRaiz(String solucaoRaiz) {
		this.solucaoRaiz = solucaoRaiz;
	}

	public Sistema getSistema() {
		return this.sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public Servico getServico() {
		return this.servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}
	
	

	public byte[] getAnexo() {
		return anexo;
	}

	public void setAnexo(byte[] anexo) {
		this.anexo = anexo;
	}
	
	

	
	public java.util.Date getData() {
		return data;
	}

	public void setData(java.util.Date data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idLicaoAprendida;
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
		LicoesAprendida other = (LicoesAprendida) obj;
		if (idLicaoAprendida != other.idLicaoAprendida)
			return false;
		return true;
	}

}