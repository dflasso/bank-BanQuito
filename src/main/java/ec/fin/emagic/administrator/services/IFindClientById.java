package ec.fin.emagic.administrator.services;


import ec.fin.emagic.administrator.model.entities.Client;

public interface IFindClientById {
	
	/**
	 * @param id
	 * @throws NotFoundException - Client Not Found (status 404)
	 * @return Client
	 */
	public Client find(Long id);
}
