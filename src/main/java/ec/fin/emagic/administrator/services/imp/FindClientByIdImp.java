package ec.fin.emagic.administrator.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ec.fin.emagic.administrator.exception.NotFoundException;
import ec.fin.emagic.administrator.model.DAO.ClientDAO;
import ec.fin.emagic.administrator.model.entities.Client;
import ec.fin.emagic.administrator.services.IFindClientById;
import lombok.extern.slf4j.Slf4j;

@Service
@Primary
@Slf4j
public class FindClientByIdImp implements IFindClientById {

	@Autowired
	private ClientDAO clientDAO;

	@Override
	public Client find(Long id) {
		return clientDAO.findById(id).orElseThrow(() -> {
			log.info("[CLIENT NOT FOUND] id:" + id);
			return new NotFoundException("No existe un cliente registrado con el id: " + id, "CLIENT-ID NOT FOUND", HttpStatus.NOT_FOUND, null);
		});
	}
	
	
}
