package de.leuphana.customer.component.behaviour;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.leuphana.customer.component.structure.CustomerEntity;
import de.leuphana.customer.component.structure.CustomerOrders;
import de.leuphana.customer.connector.CustomerOrdersSpringDataConnectorRequester;
import de.leuphana.customer.connector.CustomerSpringDataConnectorRequester;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Service
public class CustomerImpl implements CustomerComponentService {
    private static final Logger log = LogManager.getLogger(CustomerImpl.class);
    
	
	@Autowired
	private CustomerSpringDataConnectorRequester customerSpringDataConnectorRequester;
	
	@Autowired
	private CustomerOrdersSpringDataConnectorRequester customerOrdersSpringDataConnectorRequester;

	@Override
	public CustomerEntity addCustomer(String name, String address) throws Exception {

		ArrayList<CustomerOrders> customerOrders = new ArrayList<CustomerOrders>();
		return customerSpringDataConnectorRequester.save(CustomerEntity.builder()
                .name(name)
                .address(address)
                .cartId(0)
                .customerOrders(customerOrders)
                .build());
	}

	@Override
	public CustomerEntity getCustomer(Integer customerId) {
		CustomerEntity customerEntity = customerSpringDataConnectorRequester.findById(customerId).get();
		log.info("Received " + customerEntity.getName() + " from Database");
		return customerEntity; 
	}

	@Override
	public boolean removeCustomer(Integer customerId) throws Exception {
		if(!customerSpringDataConnectorRequester.existsById(customerId))
			throw new Exception("Customer can not be deleted because it does not exist");
		customerSpringDataConnectorRequester.deleteById(customerId);
		log.info("Customer with ID " + customerId  + " removed from Database");
		if(customerSpringDataConnectorRequester.existsById(customerId))
			{log.info("Customer with ID " + customerId  + " removed from Database");
			return false;
			}
		return true;
	}

	@Override
	public boolean addCartToCustomer(Integer customerId, Integer cartId) {
		CustomerEntity customerEntity = customerSpringDataConnectorRequester.findById(customerId).get();
		customerEntity.setCartId(cartId);
		customerSpringDataConnectorRequester.save(customerEntity);
		if (customerEntity.getCartId()==cartId) {
			return true;
		}
		return false;
		
	}

	@Override
	public boolean removeCartFromCustomer(Integer customerId) {
		CustomerEntity customerEntity = customerSpringDataConnectorRequester.findById(customerId).get();
		customerEntity.setCartId(0);
		customerSpringDataConnectorRequester.save(customerEntity);
		if (customerEntity.getCartId()==0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean addOrderToCustomer(Integer customerId, Integer orderId) {
		CustomerEntity customerEntity = customerSpringDataConnectorRequester.findById(customerId).get();
		CustomerOrders customerOrder = CustomerOrders.builder()
				.customerId(customerId)
				.orderId(orderId)
				.build();
		List<CustomerOrders> customerOrders = customerEntity.getCustomerOrders();
		customerOrders.add(customerOrder);
		customerEntity.setCustomerOrders(customerOrders);
		if (customerEntity.getCustomerOrders().stream().filter(customerOrderPositions -> customerOrderPositions.getOrderId() == orderId).findFirst().get() != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean removeOrderFromCustomer(Integer customerId, Integer orderId) {
		customerOrdersSpringDataConnectorRequester.deleteById(orderId);
		if(customerOrdersSpringDataConnectorRequester.existsById(orderId)){
			return true;
		}
		return false;
	}

}