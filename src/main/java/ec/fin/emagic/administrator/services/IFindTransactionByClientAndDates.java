package ec.fin.emagic.administrator.services;

import java.time.LocalDate;
import java.util.List;

import ec.fin.emagic.administrator.model.entities.Client;
import ec.fin.emagic.administrator.model.entities.TransactioAccount;

public interface IFindTransactionByClientAndDates {

	/**
	 * 
	 * @param idClient
	 * @param atBegin - init range dates of transactions
	 * @param lastDate - end range dates of transactions
	 * @throws NotFoundException - If not found transactions or client (status 404)
	 * @return
	 */
	public List<TransactioAccount> find(Long idClient, LocalDate atBegin, LocalDate lastDate);
	
	
	/**
	 * 
	 * @param idClient
	 * @param atBegin - init range dates of transactions
	 * @param lastDate - end range dates of transactions
	 * @throws NotFoundException - If not found transactions or client (status 404)
	 * @return
	 */
	public List<TransactioAccount> find(Client client, LocalDate atBegin, LocalDate lastDate);
}
