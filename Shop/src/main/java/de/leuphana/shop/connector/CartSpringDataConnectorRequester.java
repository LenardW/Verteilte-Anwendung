package de.leuphana.shop.connector;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.leuphana.shop.component.structure.sales.CartEntity;

public interface CartSpringDataConnectorRequester extends JpaRepository<CartEntity, Integer> {

	Optional<CartEntity> findById(Integer id); 
	
}
