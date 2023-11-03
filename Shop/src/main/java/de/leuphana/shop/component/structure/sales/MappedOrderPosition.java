package de.leuphana.shop.component.structure.sales;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MappedOrderPosition {
	
	private Integer positionId;
	
	private Integer articleId;
	
	private Float articlePrice;
	
	private Integer articleQuantity;
	
	private Integer orderId;

}
