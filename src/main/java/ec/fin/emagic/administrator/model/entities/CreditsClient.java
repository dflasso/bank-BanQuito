package ec.fin.emagic.administrator.model.entities;

import java.time.LocalDate;
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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Table(name = "Credito")
@Data
public class CreditsClient {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_credito")
	@JsonIgnore
	private Long id;
	
	@Column(name = "capital_por_cobrar", scale = 8, precision = 2)
	@ApiModelProperty(notes = "Capital pendiente de cobrar")
	private Double capitalByReceive;
	
	@Column(name = "capital_pagado", scale = 8, precision = 2)
	@ApiModelProperty(notes = "Capital pagado")
	private Double capitalPaid;
	
	@Column(name = "capital_prestado", scale = 8, precision = 2)
	@ApiModelProperty(notes = "Capital prestado")
	private Double capitalBorrowed;
	
	@Column(name = "interes_pagado", scale = 8, precision = 2)
	@ApiModelProperty(notes = "Interes Pagado")
	private Double interestPaid;
	
	@Column(name = "tasa_interes_anual", scale = 8, precision = 2)
	@ApiModelProperty(notes = "Tasa de Interes Anual")
	private Double interestRateYear;
	
	@Column(name = "cuota_mensual", scale = 8, precision = 2)
	@ApiModelProperty(notes = "Valor cutoa mensual")
	private Double monthlyFeeTotal;
	
	@Column(name = "cuota_mensual_cancelada", scale = 8, precision = 2)
	@ApiModelProperty(notes = "Total cutoas mensuales canceladas")
	private Double monthlyFeeCanceled;
	
	@Column(name = "cuota_mensual_pending", scale = 8, precision = 2)
	@ApiModelProperty(notes = "Total cutoas mensuales pendientes")
	private Double monthlyFeePending;
	
	@Column(name = "estado")
	@ApiModelProperty(notes = "Total cutoas mensuales pendientes")
	private Boolean state;
	
	@Column(name = "fec_inicio")
	private LocalDate startedAt;
		
	@Column(name = "fec_fin")
	private LocalDate finishedAt;
	
	@Column(length = 100, name = "tipo_credito")
	private String type;
	
	@Column(length = 350, name = "razon_credito")
	private String reason;
	
	@Column(name = "total_cuotas")
	@ApiModelProperty(notes = "Total cutoas mensuales")
	private Integer totalMonthlyFee;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente")
	private Client client;
	
	@JsonIgnore
	@OneToMany(mappedBy = "credit", fetch = FetchType.LAZY)
	private List<Amortization> amortizations;
	
}
