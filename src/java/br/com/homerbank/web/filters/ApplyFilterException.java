package br.com.homerbank.web.filters;

public class ApplyFilterException extends Exception {

	public static final long serialVersionUID = 200512231412L;

	public ApplyFilterException() {
		super();
	}

	public ApplyFilterException(String message) {
		super(message);
	}

	public ApplyFilterException(Throwable cause) {
		super(cause);
	}

	public ApplyFilterException(String message, Throwable cause) {
		super(message, cause);
	}

}
