package ec.fin.emagic.administrator.model.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "TRANSACCIONES_CUENTA")
@Data
public class TransactioAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_transaccion")
	@JsonIgnore
	private Long id;
	
	@Column(length = 10, name = "tipo_transaccion")
	private String tipo;
	
	@Column(name = "valor_transaccion", precision = 2, scale = 8)
	private Double value;
	
	@Column(length = 300, name = "descripcion_transaccion")
	private String description;
	
	@Column(name = "fecha_transaccion")
	private LocalDateTime accountingDate;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cuenta")
	private AccountsClient account;
	
}
