package de.leuphana.shop.component.structure.sales;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MappedCustomerOrders {

	private Integer customerId;
	private Integer orderId;
}
