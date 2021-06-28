package ec.fin.emagic.administrator.services;

import java.util.List;

import ec.fin.emagic.administrator.model.entities.AccountsClient;
import ec.fin.emagic.administrator.model.entities.Client;

public interface IFindAccountByClient {

	public List<AccountsClient> find(Long idClient);
	
	public List<AccountsClient> find(Client client);
}
