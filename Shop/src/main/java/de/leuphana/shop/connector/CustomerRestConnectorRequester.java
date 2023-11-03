package de.leuphana.shop.connector;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import de.leuphana.shop.component.structure.sales.MappedCustomer;

@SpringBootApplication
@FeignClient(name="CustomerRequester", url="http://${CUSTOMER:localhost}:8082/customer")
public interface CustomerRestConnectorRequester {

		@GetMapping("/addCart")
		Integer addCart(@RequestParam("customerId") Integer customerId, @RequestParam("cartId") Integer cartId);
		
		@GetMapping(value = "/get/{customerId}", consumes=MediaType.APPLICATION_JSON_VALUE)
		MappedCustomer getCustomer(@PathVariable("customerId") Integer customerId);
		
		@PostMapping(value = "/addCustomer", consumes=MediaType.APPLICATION_JSON_VALUE)
		MappedCustomer addCustomer(@RequestBody MappedCustomer customer);
		
		@GetMapping(value= "/removeCustomer/{customerId}")
		boolean removeCustomer(@PathVariable("customerId") Integer customerId);
		
		@GetMapping(value= "/addCartToCustomer/{customerId}/{cartId}")
		boolean addCartToCustomer (@PathVariable("customerId") Integer customerId, @PathVariable("cartId") Integer cartId);
		
		@GetMapping(value = "/addOrderToCustomer/{customerId}/{orderId}")
		boolean addOrderToCustomer(@PathVariable("customerId") Integer customerId, @PathVariable("orderId") Integer orderId);
}
