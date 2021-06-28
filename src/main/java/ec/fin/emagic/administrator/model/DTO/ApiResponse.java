package ec.fin.emagic.administrator.model.DTO;


import lombok.Data;

@Data
public class ApiResponse<T> {

	private Boolean success;
	private String msg;
	private T data;
}
