package ec.fin.emagic.administrator.services;

import ec.fin.emagic.administrator.model.DTO.ApiResponse;
import ec.fin.emagic.administrator.model.DTO.ClientCreditRequest;
import ec.fin.emagic.administrator.model.entities.Client;

public interface IValidateBeforeToGiveCredit {

	public ApiResponse validate(Long idClient, Double amount);
	
	public ApiResponse validate(Client client, Double amount);
	
	public ApiResponse validate(ClientCreditRequest request);
}
