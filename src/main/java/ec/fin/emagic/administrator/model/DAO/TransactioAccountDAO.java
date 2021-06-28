package ec.fin.emagic.administrator.model.DAO;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ec.fin.emagic.administrator.model.entities.AccountsClient;
import ec.fin.emagic.administrator.model.entities.TransactioAccount;

public interface TransactioAccountDAO  extends JpaRepository<TransactioAccount, Long>,  JpaSpecificationExecutor<TransactioAccount> {

	public List<TransactioAccount> findByAccountInAndAccountingDateBetween( List<AccountsClient> account, LocalDate atBegin, LocalDate lastDate);
	
	public List<TransactioAccount> findByAccountIn( List<AccountsClient> account);
}
