package de.leuphana.shop.connector;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.leuphana.shop.component.structure.sales.CatalogEntity;

public interface CatalogSpringDataConnectorRequester extends JpaRepository<CatalogEntity, Integer> {

	Optional<CatalogEntity> findById(Integer id); 
	
}
