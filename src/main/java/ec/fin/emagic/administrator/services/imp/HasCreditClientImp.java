package ec.fin.emagic.administrator.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ec.fin.emagic.administrator.model.DAO.CreditsClientDAO;
import ec.fin.emagic.administrator.model.entities.Client;
import ec.fin.emagic.administrator.model.entities.CreditsClient;
import ec.fin.emagic.administrator.services.IFindClientById;
import ec.fin.emagic.administrator.services.IHasCreditClient;

@Service
@Primary
public class HasCreditClientImp implements IHasCreditClient {
	
	@Autowired
	private IFindClientById findClientById;
	
	@Autowired
	private CreditsClientDAO creditsDAO;

	@Override
	public List<CreditsClient> find(Long idClient) {
		Client client = findClientById.find(idClient);
		return this.find(client);
	}

	@Override
	public List<CreditsClient> find(Client client) {
		return creditsDAO.findByClient(client);
	}

	@Override
	public Boolean check(Long idClient) {
		Client client = findClientById.find(idClient);
		return this.check(client);
	}

	@Override
	public Boolean check(Client client) {
		List<CreditsClient> credits = creditsDAO.findByClient(client);
		
		Boolean hasCredit = false;
		for(CreditsClient cr :  credits) {
			hasCredit = cr.getState();
		}
		
		return hasCredit;
	}

}
