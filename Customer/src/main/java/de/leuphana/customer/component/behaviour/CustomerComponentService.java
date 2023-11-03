package de.leuphana.customer.component.behaviour;

import org.springframework.stereotype.Service;

import de.leuphana.customer.component.structure.CustomerEntity;
@Service
public interface CustomerComponentService {
	
	public CustomerEntity addCustomer(String name, String address) throws Exception;
	
	public CustomerEntity getCustomer(Integer customerId);
	
	public boolean removeCustomer(Integer customerId) throws Exception;	
	
	public boolean addCartToCustomer(Integer customerId, Integer cartId);
	
	public boolean removeCartFromCustomer(Integer customerId);
	
	public boolean addOrderToCustomer(Integer customerId, Integer orderId);
	
	public boolean removeOrderFromCustomer(Integer customerId, Integer orderId);
}
