package ec.fin.emagic.administrator.model.DTO;

import java.util.List;

import lombok.Data;

@Data
public class CreditReportResponse {

	private Boolean success;
	private String msg;
	private List<TableAmortizationResponse> tableAmortization;
}
