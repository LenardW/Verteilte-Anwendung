package de.leuphana.customer.component.structure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "customerOrders")
public class CustomerOrders {
	
	
	@Column
	private Integer customerId;
	
	@Id
	@Column
	private Integer orderId;

}
