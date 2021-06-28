package ec.fin.emagic.administrator.services.imp;

import java.time.LocalDate;
import java.util.List;

import org.aspectj.apache.bcel.generic.InstructionConstants.Clinit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ec.fin.emagic.administrator.exception.DataException;
import ec.fin.emagic.administrator.model.DAO.CreditsClientDAO;
import ec.fin.emagic.administrator.model.DTO.ApiResponse;
import ec.fin.emagic.administrator.model.DTO.ClientCreditRequest;
import ec.fin.emagic.administrator.model.DTO.CreditReportResponse;
import ec.fin.emagic.administrator.model.DTO.TableAmortizationResponse;
import ec.fin.emagic.administrator.model.entities.Client;
import ec.fin.emagic.administrator.model.entities.CreditsClient;
import ec.fin.emagic.administrator.services.IFindClientById;
import ec.fin.emagic.administrator.services.IGenerateTableAmortization;
import ec.fin.emagic.administrator.services.ISaveCredit;
import ec.fin.emagic.administrator.services.IValidateBeforeToGiveCredit;
import lombok.extern.slf4j.Slf4j;

@Service
@Primary
@Slf4j
public class SaveCreditImp implements ISaveCredit {
	
	@Autowired
	private IValidateBeforeToGiveCredit validationCredit;
	
	@Autowired
	private IGenerateTableAmortization tableAmortization;

	@Autowired
	private IFindClientById findClientById;
	
	@Autowired
	private CreditsClientDAO creditDAO;
	
	
	@Override
	public ApiResponse<CreditsClient> trySaveAfterValidate(ClientCreditRequest request) {
		ApiResponse response = validationCredit.validate(request);
		
		if(!response.getSuccess()) {
			return response; 
		}
		
		return this.trySave(request);
	}

	@Override
	public ApiResponse<CreditsClient> trySaveAfterValidate(Long idClient, Double amount, Integer numFee, Double interestRateYear,
			String type, String reason) {
		
		ApiResponse<CreditsClient> response =  validationCredit.validate(idClient, amount);
		
		if(!response.getSuccess()) {
			return response; 
		}
		
		return this.trySave(idClient, amount, numFee, interestRateYear, type, reason);
	}

	@Override
	public ApiResponse<CreditsClient> trySave(ClientCreditRequest request) {
		return this.trySave(request.getIdClient(), request.getAmount(),request.getNumFee(), request.getInterestRateYear(), request.getType(), request.getReason());
	}

	@Override
	public ApiResponse<CreditsClient> trySave(Long idClient, Double amount, Integer numFee, Double interestRateYear, String type,
			String reason) {
		
		Double fee = tableAmortization.calculateFee(amount, numFee, interestRateYear);
		
		CreditsClient credit = new CreditsClient();
		credit.setCapitalBorrowed(amount);
		credit.setCapitalByReceive(amount);
		credit.setCapitalPaid(0D);
		
		Client client = findClientById.find(idClient);
		
		credit.setClient(client);
		credit.setFinishedAt(null);
		credit.setInterestPaid(0D);
		credit.setInterestRateYear(interestRateYear);
		credit.setMonthlyFee(fee);
		credit.setMonthlyFeeCanceled(0);
		credit.setMonthlyFeePending(numFee);
		credit.setReason(reason);
		credit.setStartedAt(LocalDate.now());
		credit.setState(true);
		credit.setTotalMonthlyFee(numFee);
		credit.setType(type);
		
		ApiResponse<CreditsClient> response = new ApiResponse<CreditsClient>();
		
		try {
			credit = creditDAO.save(credit);
			response.setData(credit);
			log.info("Credit Save to Client:  " + client.getName() + " " + client.getLastname());
		} catch (DataAccessException e) {
			throw new DataException("Data Access", "Error al intentar registrar el crédito.", HttpStatus.CONFLICT);
		}
		
		
		response.setMsg("El Crédito fue registrado exitosamente");
		response.setSuccess(true);
		
		return response;
	}

	@Override
	public CreditReportResponse trySaveAndReportered(ClientCreditRequest request) {
		return this.trySaveAndReportered(request.getIdClient(), request.getAmount(), request.getNumFee(), request.getInterestRateYear(), request.getType(), request.getReason());
	}

	@Override
	public CreditReportResponse trySaveAndReportered(Long idClient, Double amount, Integer numFee,
			Double interestRateYear, String type, String reason) {
		CreditReportResponse  response = new CreditReportResponse();
		
		ApiResponse<CreditsClient> apiResponse = this.trySaveAfterValidate(idClient, amount, numFee, interestRateYear, type, reason);
		List<TableAmortizationResponse> table = tableAmortization.generate(apiResponse.getData());
		
		response.setMsg(apiResponse.getMsg());
		response.setSuccess(true);
		response.setTableAmortization(table);		
		
		return response;
	}
	


}