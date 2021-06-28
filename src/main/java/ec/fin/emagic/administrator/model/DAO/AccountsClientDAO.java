package ec.fin.emagic.administrator.model.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ec.fin.emagic.administrator.model.entities.AccountsClient;
import ec.fin.emagic.administrator.model.entities.Client;

public interface AccountsClientDAO  extends JpaRepository<AccountsClient, Long>,  JpaSpecificationExecutor<AccountsClient>{

	public List<AccountsClient> findByClient(Client client);
	
}
