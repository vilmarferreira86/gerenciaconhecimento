package br.ufpa.configuracoes.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ufpa.configuracoes.dao.GerenciaDao;
import br.ufpa.configuracoes.dao.TipoServicoDao;
import br.ufpa.configuracoes.model.Gerencia;
import br.ufpa.configuracoes.model.TipoServico;

@FacesConverter(value="convTipoServico", forClass=TipoServico.class)
public class TipoServicoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try{
			if(value == null && !"".equals(value)){
				return null;
			}
			TipoServicoDao tpDao = new TipoServicoDao();
			
			return tpDao.getById(Integer.valueOf(value));
		}catch(NumberFormatException e){
			System.out.println("erro------->"+e.getMessage());
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value == null){
			System.out.println("Estou aqui para value = null -> getAsString");
			return null;
		}
		try{
			TipoServico tp = (TipoServico) value;
			return Integer.toString(tp.getIdTipoServico());
		}catch(Exception e){  
            return "erro: "+e;  
        }  
	}

}
