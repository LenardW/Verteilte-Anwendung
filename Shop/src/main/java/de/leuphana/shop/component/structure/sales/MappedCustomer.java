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
public class MappedCustomer {

	private Integer customerId;
	private String name;
	private String address;	
	private Integer cartId;	
	private List<MappedCustomerOrders> customerOrders;	
}
