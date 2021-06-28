package ec.fin.emagic.administrator.services.imp;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ec.fin.emagic.administrator.model.entities.Client;
import ec.fin.emagic.administrator.services.ICheckClientStateCivil;
import ec.fin.emagic.administrator.services.IFindClientById;

@Service
@Primary
public class CheckClientStateCivilImp implements ICheckClientStateCivil {

	@Autowired
	private IFindClientById findClientById;
	
	@Override
	public boolean validateAgeAndStateCivil(Long idClient) {
		Client client = findClientById.find(idClient);
		return this.validateAgeAndStateCivil(client);
	}

	@Override
	public boolean validateAgeAndStateCivil(Client client) {
		boolean isValid = true;
		if(client.getStateCivil().equals("c")) {
			int age = this.calculateAge(client.getBirthday());	
			if(age < 25) {
				isValid = false;
			}
		}
		return isValid;
	}
	
	private int calculateAge(LocalDate birtday) {
		Period period = Period.between(birtday, LocalDate.now());
		return period.getMonths();
	}

}
