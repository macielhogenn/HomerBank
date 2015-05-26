package br.com.homerbank.web.filters;



public class FilterManager extends BaseFilter { 
    
	public FilterManager() {
		super();
	}
        
        @Override
	public void loadFilters() {
		System.out.println("Filter Manager is Working");
		addFilter((Filter) new AuthenticationAccount());	
	}
        
        
}
