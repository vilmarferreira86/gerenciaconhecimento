package br.ufpa.configuracoes.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ufpa.configuracoes.dao.ServicoDao;
import br.ufpa.configuracoes.dao.SistemaDao;
import br.ufpa.configuracoes.model.Servico;
import br.ufpa.configuracoes.model.Sistema;

@FacesConverter(value="convSistema", forClass=Sistema.class)
public class SistemaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try{
			if(value == null && !"".equals(value)){
				return null;
			}
			SistemaDao sDao = new SistemaDao();
			
			return sDao.getById(Integer.valueOf(value));
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
			Sistema s = (Sistema) value;
			return Integer.toString(s.getIdSistema());
		}catch(Exception e){  
            return "erro: "+e;  
        }  
	}

}
