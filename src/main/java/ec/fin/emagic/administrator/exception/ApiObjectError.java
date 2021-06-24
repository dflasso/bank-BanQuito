package ec.fin.emagic.administrator.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ApiObjectError<T> extends ApiSubError{
	private T detail;
	private String message;
	private String title;
}
