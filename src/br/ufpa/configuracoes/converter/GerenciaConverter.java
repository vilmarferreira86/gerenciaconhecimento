package br.ufpa.configuracoes.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ufpa.configuracoes.dao.GerenciaDao;
import br.ufpa.configuracoes.model.Gerencia;


@FacesConverter(value = "convGerencia", forClass = Gerencia.class)
public class GerenciaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String value){
		try{
			if(value == null && !"".equals(value)){
				return null;
			}
			GerenciaDao gDao = new GerenciaDao();
			
			return gDao.getById(Integer.valueOf(value));
		}catch(NumberFormatException e){
			System.out.println("erro------->"+e.getMessage());
		}
		return null;
		
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent component,
			Object value) {
		if(value == null){
			System.out.println("Estou aqui para value = null -> getAsString");
			return null;
		}
		try{
			Gerencia g = (Gerencia) value;
			return Integer.toString(g.getIdGerencia());
		}catch(Exception e){  
            return "erro: "+e;  
        }  
	}

}