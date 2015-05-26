package br.com.homerbank.web.filters;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FilterContoller {

	private List filters = null;

	private static FilterContoller controller = null;

	private FilterContoller() {
		super();
		setFilters(new LinkedList());
	}	

	private List getFilters() {
		return filters;
	}
	
	private void setFilters(List filters) {
		this.filters = filters;
	}

	public static FilterContoller getInstance() {
		if (controller == null) {
			controller = new FilterContoller();
		}
		return controller;
	}

	public void addFilter(Filter filtro) {
		getFilters().add(filtro);
	}

	public void removeFilter(Filter filtro) {
		getFilters().remove(filtro);
	}

	public void initFilters(FilterConfig config) throws ServletException {
		for (int i = 0; i < getFilters().size(); i++) {
			Filter filter = (Filter) getFilters().get(i);
			filter.init(config);
		}
	}

	public boolean processFilters(HttpServletRequest request, HttpServletResponse response) throws ProcessFilterException {

		boolean toContinue = true;
		Iterator it = getFilters().iterator();
		while (it.hasNext() && toContinue) {
			if (toContinue) {
				Filter filter = (Filter) it.next();
				try {
					toContinue = filter.applyFiltro(request, response);
				} catch (ApplyFilterException e) {
					throw new ProcessFilterException("Erro ao aplicar filtro '" + filter.getName() + "': " + e.getMessage());
				}
			}
		}
		return toContinue;
	}
}
