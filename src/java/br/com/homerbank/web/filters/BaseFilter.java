package br.com.homerbank.web.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseFilter implements javax.servlet.Filter {
	
	private FilterContoller controleFiltro = null;

	private FilterConfig config = null;

	public BaseFilter() {
		super();
		setFilterController(FilterContoller.getInstance());
	}

	private FilterConfig getConfig() {
		return config;
	}

	private void setConfig(FilterConfig config) {
		this.config = config;
	}

	private FilterContoller getControleFiltro() {
		return controleFiltro;
	}

	private void setFilterController(FilterContoller controleFiltro) {
		this.controleFiltro = controleFiltro;
	}

	public abstract void loadFilters();

        @Override
	public void init(FilterConfig config) throws ServletException {
		setConfig(config);
		loadFilters();
		getControleFiltro().initFilters(getConfig());
	}

        @Override
	public final void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		boolean toFilter = true;
		try {
			toFilter = getControleFiltro().processFilters((HttpServletRequest) request,(HttpServletResponse) response);
		} catch (ProcessFilterException e) {
			throw new ServletException(e);
		}
		if (toFilter) {
			chain.doFilter(request, response);
		}
	}

	public void addFilter(Filter filter) {
		getControleFiltro().addFilter(filter);
	}

	public void removeFilter(Filter filter) {
		getControleFiltro().removeFilter(filter);
	}

        @Override
	public final void destroy() {
		setConfig(null);
		setFilterController(null);
	}
        
        @Override
	public void finalize() {
		setConfig(null);
		setFilterController(null);
	}
}