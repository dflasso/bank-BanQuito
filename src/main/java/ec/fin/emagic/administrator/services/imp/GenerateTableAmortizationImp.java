package ec.fin.emagic.administrator.services.imp;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ec.fin.emagic.administrator.exception.BusinessRulesException;
import ec.fin.emagic.administrator.exception.NotFoundException;
import ec.fin.emagic.administrator.model.DAO.CreditsClientDAO;
import ec.fin.emagic.administrator.model.DTO.TableAmortizationResponse;
import ec.fin.emagic.administrator.model.entities.Client;
import ec.fin.emagic.administrator.model.entities.CreditsClient;
import ec.fin.emagic.administrator.services.IFindClientById;
import ec.fin.emagic.administrator.services.IGenerateTableAmortization;

@Service
@Primary
public class GenerateTableAmortizationImp implements IGenerateTableAmortization {
	
	@Autowired
	private IFindClientById findClientById;
	
	@Autowired
	private CreditsClientDAO creditsDAO;

	@Override
	public Double calculateFee(Double amount, Integer numFee, Double interestRateYear) {
		interestRateYear = (interestRateYear/ 100D) / 12D; 
		
		//( 1 + interes_mensual)
		Double interest = 1D + interestRateYear;
		
		//( 1 + interes_mensual)^numero_cuotas
		interest = Math.pow(interest, numFee);

		Double fee = (interest * interestRateYear)/(interest - 1D);

		fee = amount * fee;
		
		return fee;
	}

	@Override
	public List<TableAmortizationResponse> generateFromClient(Long idClient) {
		Client client = findClientById.find(idClient);
		return this.generateFromClient(client);
	}

	@Override
	public List<TableAmortizationResponse> generateFromClient(Client client) {
		List<CreditsClient> credits = client.getCredits();
		
		CreditsClient creditEnable = new CreditsClient();
		if(credits != null && credits.size() > 0) {
			for(CreditsClient cr : credits) {
				if(cr.getState()) {
					creditEnable = cr;
				}
			}
		}else {
			throw new BusinessRulesException("NOT FOUND CREDIT", "El cliente no posee ningun crédito", HttpStatus.BAD_REQUEST, null);
		}
		
		return this.generate(creditEnable);
	}

	@Override
	public List<TableAmortizationResponse> generate(Long idCredit) {
		CreditsClient credit = creditsDAO.findById(idCredit).orElseThrow(() -> new NotFoundException("Crédito no encontrado", "Not Found Credit", HttpStatus.NOT_FOUND, null));
		return this.generate(credit);
	}

	@Override
	public List<TableAmortizationResponse> generate(CreditsClient credit) {
		List<TableAmortizationResponse>  table = new ArrayList<TableAmortizationResponse>();
		
		Double balance = credit.getCapitalBorrowed();
		Double interestRateYear = (credit.getInterestRateYear() / 100D) / 12D;
		Double interestPaid = 0D;
		Double capitalPaid = 0D;
		DecimalFormat df = new DecimalFormat("0.00");
		table.add(new TableAmortizationResponse(0, "0.00", "0.00", "0.00", df.format(balance)));
			
		for (int i = 1; i <= credit.getTotalMonthlyFee(); i++) {
			TableAmortizationResponse row = new TableAmortizationResponse();
			row.setIndex(i);
			row.setValueFee(df.format(credit.getMonthlyFee()));
			
			interestPaid =  interestRateYear * balance;
			row.setInterestPaid(df.format(interestPaid));
			capitalPaid =  credit.getMonthlyFee() - interestPaid; 
			row.setCapitalPaid(df.format(capitalPaid));
			
			
			balance = balance - capitalPaid;
			row.setBalance(df.format(balance));
			
			table.add(row);
		}
		
		return table;
	}

}
