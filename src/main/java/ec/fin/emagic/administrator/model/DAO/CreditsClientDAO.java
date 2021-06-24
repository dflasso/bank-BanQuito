package ec.fin.emagic.administrator.model.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ec.fin.emagic.administrator.model.entities.CreditsClient;

public interface CreditsClientDAO extends JpaRepository<CreditsClient, Long>,  JpaSpecificationExecutor<CreditsClient>{

}
