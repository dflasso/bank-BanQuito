package ec.fin.emagic.administrator.services;

import java.util.List;

import ec.fin.emagic.administrator.model.entities.AccountsClient;
import ec.fin.emagic.administrator.model.entities.Client;
import ec.fin.emagic.administrator.model.entities.TransactioAccount;

public interface ICalculateMaxAmountCredit {
	
	public Double calculate(Long idClient);
	
	public Double calculate(Client client);
	
	public Double calculate(List<AccountsClient> accounts);
	
	public Double calculateFromTransactions(List<TransactioAccount> transactions);
}
