package de.leuphana.shop.component.structure.billing;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@Builder

@Entity
@Table(name = "invoicePositions")
public class InvoicePositionEntity {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer invoicePositionId;

	@Column
	private Integer articleId;

	@Column
	private Float articlePrice;

	@Column
	private Integer articleQuantity;
	
	@Column
	private Double totalPrice;

	@Column
	private Integer invoiceId;

}