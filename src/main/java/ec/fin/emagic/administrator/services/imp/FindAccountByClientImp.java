package ec.fin.emagic.administrator.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ec.fin.emagic.administrator.config.LoggerService;
import ec.fin.emagic.administrator.exception.NotFoundException;
import ec.fin.emagic.administrator.model.DAO.AccountsClientDAO;
import ec.fin.emagic.administrator.model.entities.AccountsClient;
import ec.fin.emagic.administrator.model.entities.Client;
import ec.fin.emagic.administrator.services.IFindAccountByClient;
import ec.fin.emagic.administrator.services.IFindClientById;

@Service
@Primary
public class FindAccountByClientImp implements IFindAccountByClient {

	@Autowired
	private AccountsClientDAO accountClientDao;
	
	@Autowired
	private IFindClientById findClientById;
	
	@Autowired
	private LoggerService logger;
	
	@Override
	public List<AccountsClient> find(Long idClient) {
		Client client = findClientById.find(idClient);
		return this.find(client);
	}

	@Override
	public List<AccountsClient> find(Client client) {
		List<AccountsClient> accounts = accountClientDao.findByClient(client);
		
		logger.msgInfo("[FIND ACCOUNTS OF CLIENT]", "El usuario con la identificación:" + client.getNumIdentification() + " , tiene: " +accounts.size() + " cuentas.", null, 200);
		
		if(accounts.size() <= 0) {
			throw new NotFoundException("El usuario con la identificación:" + client.getNumIdentification() + " , tiene: " +accounts.size() + " cuentas.", "[NOT FOUNND ACCOUNTS OF CLIENT]", HttpStatus.NOT_FOUND, null);
		}
		
		return accounts;
	}

}
