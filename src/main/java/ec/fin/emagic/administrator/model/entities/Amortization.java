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
@Table(name = "Amortizacion")
@Data
public class Amortization {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_amortizacion")
	@JsonIgnore
	private Long id;
		
	@Column(name = "interes_mora", precision = 2, scale = 8)
	private Double interestForLate;
	
	@Column(length = 300, name = "observaciones")
	private String observations;
	
	@Column(name = "fecha_pago")
	private LocalDateTime datePaid;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_credit")
	private CreditsClient credit;
}
