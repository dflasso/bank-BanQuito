package ec.fin.emagic.administrator.services;

import java.util.List;

import ec.fin.emagic.administrator.model.entities.Client;
import ec.fin.emagic.administrator.model.entities.CreditsClient;

public interface IHasCreditClient {
	
	public List<CreditsClient> find(Long idClient);
	
	public List<CreditsClient> find(Client client);

	public Boolean check(Long idClient);
	
	public Boolean check(Client client);
	
}
