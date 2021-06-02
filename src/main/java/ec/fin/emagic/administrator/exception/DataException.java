package ec.fin.emagic.administrator.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;

public class DataException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String userMessage;
	private HttpStatus status;

	
	
	public DataException(String debugMessage, String userMessage, HttpStatus status) {
		super(debugMessage);
		this.userMessage = userMessage;
		this.status = status;
	}

	public DataException(Class<?> _class, Object... params) {
		super(DataException.generateMessage(_class.getSimpleName(), toMap(String.class, String.class, params)));
	}

	public DataException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DataException(String debugMessage, String userMessage) {
		super(debugMessage);
		this.userMessage = userMessage;
	}

	private static String generateMessage(String entity, Map<String, String> searchParams) {
		return searchParams.toString();
	}

	private static <K, V> Map<K, V> toMap(Class<K> keyType, Class<V> valueType, Object... entries) {
		if (entries.length % 2 == 1)
			throw new IllegalArgumentException("Entradas invalidas");
		return IntStream.range(0, entries.length / 2).map(i -> i * 2).collect(HashMap::new,
				(m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1].toString())), Map::putAll);
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}


	public HttpStatus getStatus() {
		return status;
	}


	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	

	
}
