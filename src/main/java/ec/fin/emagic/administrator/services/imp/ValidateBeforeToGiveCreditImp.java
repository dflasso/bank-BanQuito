package ec.fin.emagic.administrator.services.imp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import org.springframework.stereotype.Service;

import ec.fin.emagic.administrator.exception.NotFoundException;
import ec.fin.emagic.administrator.model.DTO.ApiResponse;
import ec.fin.emagic.administrator.model.DTO.ClientCreditRequest;
import ec.fin.emagic.administrator.model.entities.Client;
import ec.fin.emagic.administrator.services.ICalculateMaxAmountCredit;
import ec.fin.emagic.administrator.services.ICheckClientStateCivil;
import ec.fin.emagic.administrator.services.IFindClientById;
import ec.fin.emagic.administrator.services.IFindTransactionByClientAndDates;
import ec.fin.emagic.administrator.services.IHasCreditClient;
import ec.fin.emagic.administrator.services.IValidateBeforeToGiveCredit;

@Service
@Primary
public class ValidateBeforeToGiveCreditImp  implements IValidateBeforeToGiveCredit {
	
	@Autowired
	private IFindClientById findClientById;

	@Autowired
	private IFindTransactionByClientAndDates findTransactions;
	
	@Autowired
	private ICheckClientStateCivil checkClientStateCivil;
	
	@Autowired
	private IHasCreditClient hasCreditClient;
	
	@Autowired
	private ICalculateMaxAmountCredit maxAmountCredit;
	
	@Override
	public ApiResponse validate(Long idClient, Double amount) {
		Client client = findClientById.find(idClient);
		return this.validate(client, amount);
	}
	
	@Override
	public ApiResponse validate(ClientCreditRequest request) {
		Client client = findClientById.find(request.getIdClient());
		return this.validate(client, request.getAmount());
	}

	@Override
	public ApiResponse validate(Client client, Double amount) {
		
		ApiResponse response = this.checkTransactions(client);
		
		if(!response.getSuccess()) {
			return response;
		}
		
		Boolean isValid =  checkClientStateCivil.validateAgeAndStateCivil(client);
		
		if(!isValid) {
			response.setMsg("El cliente está casado y tiene menos de 25 años. No es sejeto de crédito.");
			response.setSuccess(false);
			return response;
		}
		
		Boolean hasCredit = hasCreditClient.check(client);
		
		if(hasCredit) {
			response.setMsg("El cliente está casado y tiene menos de 25 años. No es sujeto de crédito.");
			response.setSuccess(false);
			return response;
		}
		
		Double maxAmount =  maxAmountCredit.calculate(client);
		
		if(amount > maxAmount) {
			response.setMsg("El monto máximo del crédito que puede acceder el cliente es de: $" + maxAmount);
			response.setSuccess(false);
			return response;
		}
		
		response.setMsg("El cliente es sujeto de crédito.");
		response.setSuccess(true);
		return response;
	}
	
	private ApiResponse checkTransactions(Client client) {
		
		Calendar cal = Calendar.getInstance();
		
		LocalDate now = LocalDateTime.ofInstant(cal.toInstant(), cal.getTimeZone().toZoneId()).toLocalDate();
		
		cal.add(Calendar.MONTH, -1);
		
		LocalDate lastMonth = LocalDateTime.ofInstant(cal.toInstant(), cal.getTimeZone().toZoneId()).toLocalDate();
		
		ApiResponse response = new ApiResponse();
		try {
			findTransactions.find(client, lastMonth, now);
			response.setSuccess(true);
		} catch (NotFoundException e) {
			response.setSuccess(false);
			response.setMsg(e.getMessage());
		}
		return response;
	}

	

}
