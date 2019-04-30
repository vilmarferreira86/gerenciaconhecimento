package br.ufpa.configuracoes.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ufpa.configuracoes.dao.CoordenadoriaDao;
import br.ufpa.configuracoes.model.Coordenadoria;
import br.ufpa.configuracoes.model.Gerencia;

@FacesConverter(value = "convCoordenadoria", forClass = Coordenadoria.class)
public class CoordenadoriaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try {
			if (value == null && !"".equals(value)) {
				return null;
			}
			CoordenadoriaDao cDao = new CoordenadoriaDao();

			return cDao.getById(Integer.valueOf(value));
		} catch (NumberFormatException e) {
			System.out.println("erro------->" + e.getMessage());
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			System.out.println("Estou aqui para value = null -> getAsString");
			return null;
		}
		try {
			Coordenadoria c = (Coordenadoria) value;
			return Integer.toString(c.getIdCoordenadoria());
		} catch (Exception e) {
			return "erro: " + e;
		}
	}

}
