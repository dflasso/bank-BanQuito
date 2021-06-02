package ec.fin.emagic.administrator.exception;

import org.springframework.http.HttpStatus;

public class UniqueDataException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String debugMessage;
	private HttpStatus status;
	
	
	public UniqueDataException(String debugMessage, HttpStatus status) {
		super();
		this.debugMessage = debugMessage;
		this.status = status;
	}
	

	public UniqueDataException(String message,String debugMessage, HttpStatus status) {
		super(message);
		this.debugMessage = debugMessage;
		this.status = status;
	}

	public String getDebugMessage() {
		return debugMessage;
	}


	public HttpStatus getStatus() {
		return status;
	}


	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}


	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	
}
