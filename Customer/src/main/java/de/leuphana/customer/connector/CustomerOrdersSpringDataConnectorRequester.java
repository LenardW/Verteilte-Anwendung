package de.leuphana.customer.connector;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.leuphana.customer.component.structure.CustomerEntity;
import de.leuphana.customer.component.structure.CustomerOrders;

public interface CustomerOrdersSpringDataConnectorRequester extends JpaRepository<CustomerOrders, Integer> {

	Optional<CustomerOrders> findById(Integer id); 
	
	void deleteById(Integer Id); 
	
}
