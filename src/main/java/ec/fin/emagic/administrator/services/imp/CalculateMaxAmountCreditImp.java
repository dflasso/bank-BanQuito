package ec.fin.emagic.administrator.services.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ec.fin.emagic.administrator.model.entities.AccountsClient;
import ec.fin.emagic.administrator.model.entities.Client;
import ec.fin.emagic.administrator.model.entities.TransactioAccount;
import ec.fin.emagic.administrator.services.ICalculateMaxAmountCredit;
import ec.fin.emagic.administrator.services.IFindClientById;

@Service
@Primary
public class CalculateMaxAmountCreditImp implements ICalculateMaxAmountCredit {

	@Autowired
	private IFindClientById findClientById;
	
	
	@Override
	public Double calculate(Long idClient) {
		Client client = findClientById.find(idClient);
		return this.calculate(client);
	}

	@Override
	public Double calculate(Client client) {
		List<AccountsClient> accounts = client.getAccounts();
		return this.calculate(accounts);
	}

	@Override
	public Double calculate(List<AccountsClient> accounts) {
		List<TransactioAccount> transactions = new ArrayList<TransactioAccount>();
		for(AccountsClient ac : accounts) {
			transactions.addAll(ac.getTransacctions());
		}
		return this.calculateFromTransactions(transactions);
	}

	@Override
	public Double calculateFromTransactions(List<TransactioAccount> transactions) {
		Double numTrxDeposit = 0D;
		Double numTrxRetiro = 0D;
		
		Double totalTrxDeposit = 0D;
		Double totalTrxRetiro = 0D;
		
		for(TransactioAccount trx : transactions) {
			if(trx.getTipo().equals("DEPOSITO")) {
				numTrxDeposit ++; 
				totalTrxDeposit = totalTrxDeposit + trx.getValue();
			} else if(trx.getTipo().equals("RETIRO")) {
				numTrxRetiro ++;
				totalTrxRetiro = totalTrxRetiro + trx.getValue();
			}
		}
		
		totalTrxDeposit = totalTrxDeposit / numTrxDeposit; 
		totalTrxRetiro = totalTrxRetiro / numTrxRetiro;

		Double prom = totalTrxDeposit - totalTrxRetiro;
		
		return ((prom * 0.6 ) * 9 );
	}

}
