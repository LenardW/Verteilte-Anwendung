package de.leuphana.shop.connector;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.leuphana.shop.component.structure.billing.InvoiceEntity;

public interface InvoiceSpringDataConnectorRequester extends JpaRepository<InvoiceEntity, Integer> {

	Optional<InvoiceEntity> findById(Integer id); 
	
}
