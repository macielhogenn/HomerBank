package br.com.homerbank.web.filters;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Filter {

	void init(FilterConfig config) throws ServletException;

	boolean applyFiltro(HttpServletRequest request, HttpServletResponse response) throws ApplyFilterException;

	String getName();

}
