package ec.fin.emagic.administrator.services;

import java.util.List;

import ec.fin.emagic.administrator.model.DTO.TableAmortizationResponse;
import ec.fin.emagic.administrator.model.entities.Client;
import ec.fin.emagic.administrator.model.entities.CreditsClient;

public interface IGenerateTableAmortization {

	public Double calculateFee(Double amount, Integer numFee, Double interestRateYear);
	
	public List<TableAmortizationResponse> generateFromClient(Long idClient);
	
	public List<TableAmortizationResponse> generateFromClient(Client client);
	
	public List<TableAmortizationResponse> generate(Long idCredit);
	
	public List<TableAmortizationResponse> generate(CreditsClient credit);
	
}