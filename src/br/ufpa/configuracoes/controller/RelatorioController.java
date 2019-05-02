package br.ufpa.configuracoes.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;

import br.ufpa.configuracoes.model.LicoesAprendida;
import br.ufpa.configuracoes.model.Sistema;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author 7639
 *
 */
@ManagedBean
@SessionScoped
public class RelatorioController {
	private Sistema sistema = new Sistema();
	private LicoesAprendida licoes = new LicoesAprendida();
	
	public RelatorioController() {
	}

	public RelatorioController(Sistema sistema) {
		super();
		this.sistema= sistema;

	}
	
	public void pdf() throws IOException {
		try {

			List<LicoesAprendida> listHml = null;
			JRBeanCollectionDataSource beanCollectionDataSource;

			FacesContext context = FacesContext.getCurrentInstance();
			EntityManager em = JpaUtil.getEntityManager();
			
				Query q = em
						.createQuery("select l from LicoesAprendida l WHERE l.sistema.idSistema=:sistema)");
				q.setParameter("sistema", this.licoes.getSistema().getIdSistema());
				listHml = (List<LicoesAprendida>) q.getResultList();
				beanCollectionDataSource = new JRBeanCollectionDataSource(
						listHml);
		

			// q.executeUpdate();

			// compila jrxml em memoria
			// JasperReport jasper = JasperCompileManager.compileReport(jrxml);

			// caminho do relatorio ja compilado
			String reportPath = context.getExternalContext().getRealPath(
					"/relatorios/cobranca2.jasper");

			// preenche o relatorio
			JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath,
					new HashMap(), beanCollectionDataSource);
			// JasperExportManager.exportReportToPdfFile(jasperPrint,"C:/relatorios/relatorio.pdf");/
			// exportar relatorio to pdf

			HttpServletResponse response = (HttpServletResponse) context
					.getExternalContext().getResponse();

			// response.setHeader("Content-Disposition",
			// "attachment; filename=\"relatorio.pdf\"");
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition",
					"inline; filename=\"relatorio.pdf");

			JasperExportManager.exportReportToPdfStream(jasperPrint,
					response.getOutputStream());
			context.getApplication().getStateManager().saveView(context);
			context.renderResponse();
			context.responseComplete();
			em.close();

			// JasperViewer.viewReport(jasperPrint,false);

			/*
			 * byte[] bytes = null; bytes =
			 * JasperRunManager.runReportToPdf(reportPath, new HashMap(),
			 * beanCollectionDataSource); response.setHeader("Cache-Control",
			 * "no-store"); response.setHeader("Pragma", "no-cache");
			 * response.setDateHeader("Expires", 0);
			 * response.setContentType("application/pdf");
			 * response.setContentLength(bytes.length);
			 * 
			 * // ServletOutputStream servletOutputStream = //
			 * response.getOutputStream(); // servletOutputStream.write(bytes,
			 * 0, bytes.length); // servletOutputStream.flush();
			 * 
			 * // JasperExportManager.exportReportToPdfStream(jasperPrint, //
			 * servletOutputStream); // servletOutputStream.close(); //
			 * FacesContext.getCurrentInstance().responseComplete();
			 */

		} catch (JRException e) {
			System.out.println("Erro ao gerar relatorio->" + e.getMessage());
			e.printStackTrace();
		} // return "relatorio";
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public LicoesAprendida getLicoes() {
		return licoes;
	}

	public void setLicoes(LicoesAprendida licoes) {
		this.licoes = licoes;
	}
	
	

}
