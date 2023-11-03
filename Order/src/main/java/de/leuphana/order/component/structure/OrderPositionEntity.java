package de.leuphana.order.component.structure;

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
@Table(name = "orderPositions")
public class OrderPositionEntity {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer positionId;

	@Column
	private Integer articleId;

	@Column
	private Float articlePrice;

	@Column
	private Integer articleQuantity;

	@Column
	private Integer orderId;

}