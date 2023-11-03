package de.leuphana.order.connector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import de.leuphana.order.component.behaviour.OrderComponentService;
import de.leuphana.order.component.structure.OrderEntity;
import de.leuphana.order.component.structure.OrderPositionEntity;

@RestController
@RequestMapping("/order")
public class OrderRestConnectorProvider {
	private static final Logger log = LogManager.getLogger(OrderRestConnectorProvider.class);

	@Autowired
	private OrderComponentService orderComponentService;

	@GetMapping("/status/check")
	public String status() {
		return "Working Order";
	}

	@GetMapping("/createOrder/{customerid}")
	public Integer createOrder(@PathVariable Integer customerid) {
		Integer orderId = orderComponentService.createOrder(customerid);
		log.info("Order with CustomerID " + customerid + " added to DB");
		return orderId;
	}
	
	@GetMapping("/createOrderPosition/{orderId}/{articleId}/{articlePrice}/{articleQuantity}")
	public Integer addOrderPositionToOrder(@PathVariable Integer orderId, @PathVariable Integer articleId,
			@PathVariable Float articlePrice, @PathVariable Integer articleQuantity) {
		Integer orderPositionId = orderComponentService.addOrderPositionToOrder(orderId, articleId, articlePrice,
				articleQuantity);
		return orderPositionId;
	}

	@GetMapping(value = "/getOrder/{orderid}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public OrderEntity getOrderByID(@PathVariable Integer orderid) {
		OrderEntity order = orderComponentService.getOrder(orderid);
		log.info("Order with id " + order.getOrderId() + " received from DB"); 
		return order;
	}

	@GetMapping("/getOrderPosition/{orderId}/{positionId}")
	public OrderPositionEntity getOrderPositionById(@PathVariable Integer orderId,
			@PathVariable Integer positionId) {
		OrderPositionEntity orderPositionEntity = orderComponentService.getOrderPosition(orderId, positionId);
		return orderPositionEntity;
	}

	@GetMapping("/removeOrderPosition//{orderid}/{positionId}")
	public boolean removeOrderPosition(@PathVariable Integer orderid, @PathVariable Integer positionId) {
		boolean bool = orderComponentService.removeOrderPosition(orderid, positionId);
		return bool;
	}

	@GetMapping("/removeOrder/{orderid}")
	public boolean removeOrder(@PathVariable Integer orderid) {
		boolean bool = orderComponentService.removeOrder(orderid);
		return bool;
	}
}
