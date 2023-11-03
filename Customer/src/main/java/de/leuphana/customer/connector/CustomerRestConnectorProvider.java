package de.leuphana.customer.connector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.leuphana.customer.component.behaviour.CustomerComponentService;
import de.leuphana.customer.component.structure.CustomerEntity;

@RestController
@RequestMapping("/customer")
public class CustomerRestConnectorProvider {
	private static final Logger log = LogManager.getLogger(CustomerRestConnectorProvider.class);

	@Autowired
	private CustomerComponentService customerService;
	
	@GetMapping("/status/check")
	public String status() {
		return "Customer Service is up!";
	}

	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public CustomerEntity addCustomer(@RequestBody CustomerEntity customer) throws Exception {
			log.info("MappedCustomer " + customer.getName() + " received and mapped to Customer Entity");
			log.info("Customer with ID " + customer.getCustomerId() + " added to DB");
			return customerService.addCustomer(customer.getName(),customer.getAddress());
	}
	
	@GetMapping("/get/{customerId}")
	public CustomerEntity getCustomer(@PathVariable(required=true, name="customerId") Integer customerId) {
		CustomerEntity customer = customerService.getCustomer(customerId); 
		return customer;
	}
	
	@RequestMapping(value = "addCart", method = RequestMethod.GET)
	public String addCart(@RequestParam Integer customerId, Integer cartId) {
		try {
		customerService.addCartToCustomer(customerId, cartId); 
			return "no valid arguments"; }
		catch(Exception e) {
			return "No valid arguments"; 
		}
	}
	
	@GetMapping(value= "/removeCustomer{customerId}")
	boolean removeCustomer (@PathVariable Integer customerId) throws Exception {
			return customerService.removeCustomer(customerId);

	}
	@GetMapping(value= "/addCartToCustomer/{customerId}/{cartId}")
	boolean addCartToCustomer(@PathVariable Integer customerId, @PathVariable Integer cartId) {
		return customerService.addCartToCustomer(customerId, cartId);
	}
	@GetMapping(value = "/addOrderToCustomer/{customerId}/{orderId}")
	boolean addOrderToCustomer(@PathVariable Integer customerId, @PathVariable Integer orderId) {
		return customerService.addOrderToCustomer(customerId, orderId);
	}
	
}
