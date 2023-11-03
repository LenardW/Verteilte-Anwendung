package de.leuphana.order.connector;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.leuphana.order.component.structure.OrderEntity;

public interface OrderSpringDataConnectorRequester extends JpaRepository<OrderEntity, Integer> {

	Optional<OrderEntity> findById(Integer id);

}
