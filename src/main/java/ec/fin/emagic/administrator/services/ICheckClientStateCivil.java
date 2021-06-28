package ec.fin.emagic.administrator.services;

import ec.fin.emagic.administrator.model.entities.Client;

public interface ICheckClientStateCivil {


	public boolean validateAgeAndStateCivil(Long idClient);
	
	public boolean validateAgeAndStateCivil(Client client);
	
}
