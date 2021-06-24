package ec.fin.emagic.administrator.model.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ClientCreditRequest {
	
	private Long idClient;
	
	private Double amount;
	
	@ApiModelProperty(notes = "numero de cuatos que quiere diferir el cliente")
	private Integer numFee;
	
	@ApiModelProperty(notes = "Tasa de Interes Anual")
	private Double interestRateYear;
	
	@ApiModelProperty(notes = "Tipo de Credito", example = "EMPRENDIENTO, AUTO, OTROS.")
	private String type;
	
	@ApiModelProperty(notes = "Nombre del Electrodomestico", example = "TV 14' ")
	private String reason;
}
