package de.leuphana.shop.component.structure.sales;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MappedOrder {
	
	private Integer orderId; 

	private Integer customerId;
	
	private List<MappedOrderPosition> orderPositions;
}
