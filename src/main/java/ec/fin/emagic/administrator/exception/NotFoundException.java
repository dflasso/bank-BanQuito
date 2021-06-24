package ec.fin.emagic.administrator.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String debugMessage;
	private String userMessage;
	private HttpStatus status;
	

	public NotFoundException(Class<?> _class, Object... params) {
		super(NotFoundException.generateMessage(_class.getSimpleName(), toMap(String.class, String.class, params)));
	}

	public NotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public NotFoundException(String message,String debugMessage, HttpStatus status,String userMessage) {
		super(message);
		this.debugMessage = debugMessage;
		this.status = status;
		this.userMessage = userMessage;
	}

	private static String generateMessage(String entity, Map<String, String> searchParams) {
		return "No se encontro en la base de datos: "+searchParams.toString();
	}

	private static <K, V> Map<K, V> toMap(Class<K> keyType, Class<V> valueType, Object... entries) {
		if (entries.length % 2 == 1)
			throw new IllegalArgumentException("Entradas inv�lidas");
		return IntStream.range(0, entries.length / 2).map(i -> i * 2).collect(HashMap::new,
				(m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1].toString())), Map::putAll);
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

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}
	
	
}
