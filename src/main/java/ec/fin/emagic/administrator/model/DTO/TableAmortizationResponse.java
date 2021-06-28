package ec.fin.emagic.administrator.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TableAmortizationResponse {

	private Integer index;
	
	private String valueFee;
	
	private String interestPaid;
	
	private String capitalPaid;
	
	private String balance;
}
