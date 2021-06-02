package ec.fin.emagic.administrator.exception;

import java.util.List;

import org.springframework.http.HttpStatus;


/**
 * @author Dany_Lasso
 * @apiNote Excepcion lanzada cuando las reglas de negocio se rompen.
 */
public class BusinessRulesException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String userMessage;
	private HttpStatus status;
	private List<ApiSubError> subErrors;
	
	public BusinessRulesException(String debugMessage, String userMessage, HttpStatus status, List<ApiSubError> subErrors) {
		super(debugMessage);
		this.userMessage = userMessage;
		this.status = status;
		this.subErrors = subErrors;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public List<ApiSubError> getSubErrors() {
		return subErrors;
	}
		
}
