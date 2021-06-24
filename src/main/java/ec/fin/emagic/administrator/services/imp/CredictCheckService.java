package ec.fin.emagic.administrator.services.imp;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import ec.fin.emagic.administrator.model.DAO.CreditsClientDAO;
import ec.fin.emagic.administrator.model.DTO.ClientCreditRequest;
import ec.fin.emagic.administrator.model.DTO.ClientCreditResponse;
import ec.fin.emagic.administrator.model.entities.AccountsClient;
import ec.fin.emagic.administrator.model.entities.Client;
import ec.fin.emagic.administrator.model.entities.CreditsClient;
import ec.fin.emagic.administrator.model.entities.TransactioAccount;
import lombok.extern.slf4j.Slf4j;

@Service
@Primary
@Slf4j
public class CredictCheckService {

	@Autowired
	private FindClientById findClientById;
	
	@Autowired
	private CreditsClientDAO creditsDAO;

	public ClientCreditResponse check(ClientCreditRequest request) {
		ClientCreditResponse response = this.check(request);
		Client client = findClientById.find(request.getIdClient()).orElse(null);
		
		Boolean saveSuccess = this.trySaveCredit(client, request);
		
		if(!response.getSuccess()) {
			return response;
		}
		
		if(!saveSuccess) {
			response.setSuccess(false);
			response.setMsg("No se pudo registrar el crédito. Error al comunicarse con la base de datos.");
			return response;
		}
		
		response.setMsg("El Crédito Directo fue registrado");
		response.setSuccess(true);

		return response;
	}

	private int calculateAge(LocalDate birtday) {
		Period period = Period.between(birtday, LocalDate.now());
		return period.getYears();
	}
	
	private boolean trySaveCredit(Client client,ClientCreditRequest request ) {
		Double tasaPeriodo = (0.16D)/12D;
		Double cuota = 1 - ((1 + tasaPeriodo) - request.getNumFee());
		cuota = request.getAmount() / cuota;
		
		CreditsClient credit = new CreditsClient();
		credit.setCapitalBorrowed(request.getAmount());
		credit.setCapitalByReceive(request.getAmount());
		credit.setCapitalPaid(0D);
		credit.setClient(client);
		credit.setInterestPaid(0D);
		credit.setInterestRateYear(0.16D);
		credit.setMonthlyFeeCanceled(0);
		credit.setMonthlyFeePending(request.getNumFee());
		credit.setMonthlyFee(cuota);
		credit.setReason(request.getReason());
		credit.setStartedAt(LocalDate.now());
		credit.setState(true);
		credit.setTotalMonthlyFee(request.getNumFee());
		credit.setType(request.getType());
		
		try {
			creditsDAO.save(credit);
		} catch (DataAccessException e) {
			return false;
		}
		
		return true;
	}
	
	public ClientCreditResponse validate(ClientCreditRequest request) {
		ClientCreditResponse response = new ClientCreditResponse();
		Client client = findClientById.find(request.getIdClient()).orElse(null);

		if (client == null) {
			response.setSuccess(false);
			response.setMsg("El Cliente no se encuentra registrado");
			return response;
		}

		List<AccountsClient> accounts = client.getAccounts();

		if (accounts.size() <= 0) {
			response.setSuccess(false);
			response.setMsg("El Cliente tiene cuentas registradas.");
			return response;
		}

		Boolean hasTrxsDeposit = false;
		for (AccountsClient acc : accounts) {
			List<TransactioAccount> transactions = acc.getTransacctions();
			for (TransactioAccount trxs : transactions) {
				if (trxs.getTipo().equals("DEPOSITO") ) {
					hasTrxsDeposit = true;
				}
			}
		}

		if (!hasTrxsDeposit) {
			response.setSuccess(false);
			response.setMsg("El Cliente no ha realizado Depósitos en el último mes.");
			return response;
		}

		int age = this.calculateAge(client.getBirthday());

		if (client.getStateCivil() == "c" && age < 25) {
			response.setSuccess(false);
			response.setMsg("El Cliente no puede adquirir el crédito, porque es casado y tiene menos de 25 años.");
			return response;
		}

		List<CreditsClient> credits = client.getCredits();

		Boolean hasCredit = false;
		for (CreditsClient cr : credits) {
			hasCredit = cr.getState();
		}
		
		if(hasCredit) {
			response.setSuccess(false);
			response.setMsg("El Cliente aún tiene un crédito pendiente.");
			return response;
		}
		
		
		int numDeposit = 0;
		int numRetiro = 0;
		double totalDeposit = 0D;
		double totalRetiro = 0D;
		for (AccountsClient acc : accounts) {
			List<TransactioAccount> transactions = acc.getTransacctions();
			for (TransactioAccount trxs : transactions) {
				if (trxs.getTipo().equals("DEPOSITO")) {
					numDeposit ++;
					totalDeposit = totalDeposit + trxs.getValue();
				}else if(trxs.getTipo().equals("RETIRO")) {
					numRetiro ++;
					totalRetiro = totalRetiro + trxs.getValue();
				}
			}
		}
		
		if(numDeposit == 0 || numRetiro == 0) {
			response.setSuccess(false);
			response.setMsg("El Cliente no ha realizado depósitos o retiros en los últimos tres meses. No se puede calcular el monto máximo.");
			return response;
		}
		
		//promedios
		totalDeposit = totalDeposit / numDeposit;
		totalRetiro = totalRetiro / numRetiro;
		
		log.info("promedio deposito;  " + totalDeposit);
		log.info("promedio deposito;  " + totalRetiro);
		
		Double maxAmount = ((totalDeposit - totalRetiro) * 0.6D) *9D;
		
		if(request.getAmount() > maxAmount) {
			response.setSuccess(false);
			response.setMsg("El monto máximo es de:  " + maxAmount + " no puede solicitar créditos superiores a ese monto.");
			return response;
		}
		
		response.setSuccess(true);
		response.setMsg("El cliente cumple con todos los requisitos para adquirir el crédito.");
		return response;
	}
	
	public ClientCreditResponse getInfo(Long idClient) {
		ClientCreditResponse response = new ClientCreditResponse();
		Client client = findClientById.find(idClient).orElse(null);

		if (client == null) {
			response.setSuccess(false);
			response.setMsg("El Cliente no se encuentra registrado");
			return response;
		}

		List<AccountsClient> accounts = client.getAccounts();

		if (accounts.size() <= 0) {
			response.setSuccess(false);
			response.setMsg("El Cliente tiene cuentas registradas.");
			return response;
		}

		Boolean hasTrxsDeposit = false;
		for (AccountsClient acc : accounts) {
			List<TransactioAccount> transactions = acc.getTransacctions();
			for (TransactioAccount trxs : transactions) {
				if (trxs.getTipo().equals("DEPOSITO") ) {
					hasTrxsDeposit = true;
				}
			}
		}

		if (!hasTrxsDeposit) {
			response.setSuccess(false);
			response.setMsg("El Cliente no ha realizado Depósitos en el último mes.");
			return response;
		}

		int age = this.calculateAge(client.getBirthday());

		if (client.getStateCivil() == "c" && age < 25) {
			response.setSuccess(false);
			response.setMsg("El Cliente no puede adquirir el crédito, porque es casado y tiene menos de 25 años.");
			return response;
		}

		List<CreditsClient> credits = client.getCredits();

		Boolean hasCredit = false;
		for (CreditsClient cr : credits) {
			hasCredit = cr.getState();
		}
		
		if(hasCredit) {
			response.setSuccess(false);
			response.setMsg("El Cliente aún tiene un crédito pendiente.");
			return response;
		}
		
		
		int numDeposit = 0;
		int numRetiro = 0;
		double totalDeposit = 0D;
		double totalRetiro = 0D;
		for (AccountsClient acc : accounts) {
			List<TransactioAccount> transactions = acc.getTransacctions();
			for (TransactioAccount trxs : transactions) {
				if (trxs.getTipo().equals("DEPOSITO")) {
					numDeposit ++;
					totalDeposit = totalDeposit + trxs.getValue();
				}else if(trxs.getTipo().equals("RETIRO")) {
					numRetiro ++;
					totalRetiro = totalRetiro + trxs.getValue();
				}
			}
		}
		
		if(numDeposit == 0 || numRetiro == 0) {
			response.setSuccess(false);
			response.setMsg("El Cliente no ha realizado depósitos o retiros en los últimos tres meses. No se puede calcular el monto máximo.");
			return response;
		}
		
		//promedios
		totalDeposit = totalDeposit / numDeposit;
		totalRetiro = totalRetiro / numRetiro;
		
		
		Double maxAmount = ((totalDeposit - totalRetiro) * 0.6D) *9D;
		
		
		if(maxAmount < 0) {
			response.setMsg("El monto máximo es cero, no puede solicitar créditos. El valor de los retiros supera el de los depósitos");
		}else {
			response.setMsg("El cliente puede solicitar créditos de hasta: "  + maxAmount);	
		}
		
		response.setSuccess(true);
		
		
		return response;
	}
};