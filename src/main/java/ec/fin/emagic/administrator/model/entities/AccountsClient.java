package ec.fin.emagic.administrator.model.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "CUENTAS_CLIENTE")
@Data
public class AccountsClient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cuenta")
	@JsonIgnore
	private Long id;
	
	@Column(length = 300, name = "num_cuenta")
	private String numberAccount;
	
	@Column(name = "saldo_cuenta", scale = 8, precision = 2)
	private Double balance;
	
	@Column(length = 10, name = "tipo_cuenta")
	private String type;
	
	@Column(name = "estado_cuenta")
	private Boolean enable;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente")
	private Client client;

	@JsonIgnore
	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
	private List<TransactioAccount> transacctions;
	
}
