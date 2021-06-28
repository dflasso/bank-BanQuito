package ec.fin.emagic.administrator.services.imp;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ec.fin.emagic.administrator.config.LoggerService;
import ec.fin.emagic.administrator.exception.NotFoundException;
import ec.fin.emagic.administrator.model.DAO.TransactioAccountDAO;
import ec.fin.emagic.administrator.model.entities.AccountsClient;
import ec.fin.emagic.administrator.model.entities.Client;
import ec.fin.emagic.administrator.model.entities.TransactioAccount;
import ec.fin.emagic.administrator.services.IFindAccountByClient;
import ec.fin.emagic.administrator.services.IFindClientById;
import ec.fin.emagic.administrator.services.IFindTransactionByClientAndDates;

@Service
@Primary
public class FindTransactionByClientAndDatesImp implements IFindTransactionByClientAndDates {

	@Autowired
	private IFindClientById findClientById;
	
	@Autowired
	private IFindAccountByClient findAccountsByClient;
	
	@Autowired
	private TransactioAccountDAO transationAccountDAO;
	
	@Autowired
	private LoggerService logger;
	
	@Override
	public List<TransactioAccount> find(Long idClient, LocalDate atBegin, LocalDate lastDate) {
		Client client = findClientById.find(idClient);
		return this.find(client, atBegin, lastDate);
	}

	@Override
	public List<TransactioAccount> find(Client client, LocalDate atBegin, LocalDate lastDate) {
		List<AccountsClient> accounts = findAccountsByClient.find(client);
		
		List<TransactioAccount>  transactions = transationAccountDAO.findByAccountIn(accounts);
		
		if(transactions.isEmpty()) {
			logger.msgInfo("FIND TRANSACTION IN RANGE OF TIME", "El cliente con identificación: " + client.getNumIdentification() + " no tiene transacciones en el rango de tiempo especificado.", null, 404);
			throw new NotFoundException("El cliente con identificación: " + client.getNumIdentification() + " no tiene transacciones en el rango de tiempo especificado.", "NOT FOUND TRANSACTIONS", HttpStatus.NOT_FOUND, null);
		}
		
		return transactions;
	}

}
