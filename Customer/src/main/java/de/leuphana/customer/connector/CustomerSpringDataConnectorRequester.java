package de.leuphana.customer.connector;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.leuphana.customer.component.structure.CustomerEntity;

public interface CustomerSpringDataConnectorRequester extends JpaRepository<CustomerEntity, Integer> {

	Optional<CustomerEntity> findById(Integer id); 
	
}
