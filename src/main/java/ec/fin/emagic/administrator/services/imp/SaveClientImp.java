package ec.fin.emagic.administrator.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ec.fin.emagic.administrator.exception.DataException;
import ec.fin.emagic.administrator.model.DAO.ClientDAO;
import ec.fin.emagic.administrator.model.entities.Client;
import ec.fin.emagic.administrator.services.ISaveClient;

@Service
@Primary
public class SaveClientImp implements ISaveClient  {
	
	@Autowired
	private ClientDAO clientDAO;
	
	public void save(Client client) {
		
		try {
			clientDAO.save(client);
		} catch (DataAccessException e) {
			throw new DataException("Data Access", "No se pudo registrar el cliente.", HttpStatus.CONFLICT);
		}
		
	}
}
