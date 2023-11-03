package de.leuphana.shop.connector;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import de.leuphana.shop.component.structure.sales.MappedOrder;
import de.leuphana.shop.component.structure.sales.MappedOrderPosition;

@FeignClient(name="OrderRequester", url="http://${ORDER:localhost}:8083/order")
public interface OrderRestConnectorRequester {
	
	@GetMapping("/createOrder/{customerid}")
	Integer createOrder(@PathVariable(required = true, name = "customerid") Integer customerid);
	
	@GetMapping("/createOrderPosition/{orderId}/{articleId}/{articlePrice}/{articleQuantity}")
	Integer addOrderPositionToOrder(@PathVariable("orderId") Integer orderId, @PathVariable("articleId") Integer articleId,
			@PathVariable("articlePrice") Float articlePrice, @PathVariable("articleQuantity") Integer articleQuantity);
	
	@GetMapping(value = "/getOrder/{orderid}", consumes=MediaType.APPLICATION_JSON_VALUE)
	MappedOrder getOrder(@PathVariable Integer orderid);
	
	@GetMapping(value = "/getOrderPosition/{orderId}/{positionId}", consumes=MediaType.APPLICATION_JSON_VALUE)
	MappedOrderPosition getOrderPosition(@PathVariable Integer orderId,
			@PathVariable Integer positionId);

	@GetMapping("/removeOrderPosition//{orderid}/{positionId}")
	boolean removeOrderPosition(@PathVariable Integer orderid, @PathVariable Integer positionId);
	
	@GetMapping(value = "/removeOrder/{orderid}")
	boolean removeOrder(@PathVariable Integer orderid);
	
}
