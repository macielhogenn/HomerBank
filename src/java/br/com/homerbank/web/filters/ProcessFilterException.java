package br.com.homerbank.web.filters;

public class ProcessFilterException extends Exception {

	public static final long serialVersionUID = 200512231412L;

	public ProcessFilterException() {
		super();
	}

	public ProcessFilterException(String message) {
		super(message);
	}

	public ProcessFilterException(Throwable cause) {
		super(cause);
	}

	public ProcessFilterException(String message, Throwable cause) {
		super(message, cause);
	}

}
