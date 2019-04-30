package br.ufpa.configuracoes.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ufpa.configuracoes.dao.ServicoDao;
import br.ufpa.configuracoes.model.Servico;

@FacesConverter(value="convServico", forClass=Servico.class)
public class ServicoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try{
			if(value == null && !"".equals(value)){
				return null;
			}
			ServicoDao sDao = new ServicoDao();
			
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
			Servico s = (Servico) value;
			return Integer.toString(s.getIdServico());
		}catch(Exception e){  
            return "erro: "+e;  
        }  
	}

}
