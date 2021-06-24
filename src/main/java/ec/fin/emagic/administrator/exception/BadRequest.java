package ec.fin.emagic.administrator.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class BadRequest extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BadRequest(Class<?> _class, Object... params) {
		super(BadRequest.generateMessage(_class.getSimpleName(), toMap(String.class, String.class, params)));
	}

	public BadRequest(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public BadRequest(String arg0) {
		super(arg0);
	}

	private static String generateMessage(String entity, Map<String, String> searchParams) {
		return "Error en la entrada de datos: "+searchParams.toString();
	}

	private static <K, V> Map<K, V> toMap(Class<K> keyType, Class<V> valueType, Object... entries) {
		if (entries.length % 2 == 1)
			throw new IllegalArgumentException("Entradas inv�lidas");
		return IntStream.range(0, entries.length / 2).map(i -> i * 2).collect(HashMap::new,
				(m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1].toString())), Map::putAll);
	}
	
}
