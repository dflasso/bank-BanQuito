package ec.fin.emagic.administrator.services.imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ec.fin.emagic.administrator.model.DAO.ClientDAO;
import ec.fin.emagic.administrator.model.entities.Client;

@Service
@Primary
public class FindClientById {

	@Autowired
	private ClientDAO clientDAO;
	
	public Optional<Client> find(Long id) {
		return clientDAO.findById(id);
	}
}
