package ec.fin.emagic.administrator.services;

import ec.fin.emagic.administrator.model.DTO.ApiResponse;
import ec.fin.emagic.administrator.model.DTO.ClientCreditRequest;
import ec.fin.emagic.administrator.model.DTO.CreditReportResponse;
import ec.fin.emagic.administrator.model.entities.CreditsClient;

public interface ISaveCredit {

	public ApiResponse<CreditsClient> trySaveAfterValidate(ClientCreditRequest request);
	
	public ApiResponse<CreditsClient> trySaveAfterValidate(Long idClient, Double amount, Integer numFee, Double interestRateYear, String type, String reason);
	
	public ApiResponse<CreditsClient> trySave(ClientCreditRequest request);
	
	public ApiResponse<CreditsClient> trySave(Long idClient, Double amount, Integer numFee, Double interestRateYear, String type, String reason);
	
	public CreditReportResponse trySaveAndReportered(ClientCreditRequest request);
	
	public CreditReportResponse trySaveAndReportered(Long idClient, Double amount, Integer numFee, Double interestRateYear, String type, String reason);
}
