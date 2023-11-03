package de.leuphana.order.component.behaviour;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.leuphana.order.component.structure.ArticleComplaintRequest;
import de.leuphana.order.component.structure.OrderEntity;
import de.leuphana.order.component.structure.OrderPositionEntity;
import de.leuphana.order.connector.OrderRestConnectorProvider;
import de.leuphana.order.connector.OrderSpringDataConnectorRequester;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
@Transactional
public class OrderImpl implements OrderComponentService {
	private static final Logger log = LogManager.getLogger(OrderImpl.class);

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	private OrderSpringDataConnectorRequester orderSpringDataConnectorRequester;

	@Override
	public Integer createOrder(Integer customerId) {
		ArrayList<OrderPositionEntity> orderPositions = new ArrayList<OrderPositionEntity>();
		log.info("Saving new order in DB."); 
		OrderEntity orderEntity = orderSpringDataConnectorRequester
				.save(OrderEntity.builder()
						.orderPositions(orderPositions)
						.customerId(customerId)
						.build());
		return orderEntity.getOrderId();
	}

	@Override
	public Integer addOrderPositionToOrder(Integer orderId, Integer articleId, Float articlePrice,
			Integer articleQuantity) {
		OrderEntity orderEntity = orderSpringDataConnectorRequester.findById(orderId).orElse(null);
		//TODO Exception for null
		OrderPositionEntity orderPosition = OrderPositionEntity.builder()
				.articleId(articleId)
				.articlePrice(articlePrice)
				.articleQuantity(articleQuantity)
				.orderId(orderId)
				.build();

		List<OrderPositionEntity> orderPositions = orderEntity.getOrderPositions();
		orderPositions.add(orderPosition);
		orderEntity.setOrderPositions(orderPositions);
		entityManager.persist(orderPosition);
		entityManager.flush();
		return orderPosition.getPositionId();
	}

	@Override
	public OrderEntity getOrder(Integer orderId) {
		return orderSpringDataConnectorRequester.findById(orderId).orElse(null);
		//TODO Exception for null
	}

	@Override
	public OrderPositionEntity getOrderPosition(Integer orderId, Integer positionId) {
		OrderEntity orderEntity = orderSpringDataConnectorRequester.findById(orderId).orElse(null);
		//TODO Exception for null
		Optional<OrderPositionEntity> orderPositionOptional = orderEntity.getOrderPositions().stream()
				.filter(orderPosition -> orderPosition.getPositionId() == positionId).findFirst();
		if (orderPositionOptional.isPresent()) {
			return orderPositionOptional.get();
		}
		throw new RuntimeException("OrderPosition not found");

	}
	
	@Override
	public boolean removeOrder(Integer orderId) {
		OrderEntity orderEntity = orderSpringDataConnectorRequester.findById(orderId).orElse(null);
		//TODO Exception for null optinal
		if (orderEntity != null) {
	        orderSpringDataConnectorRequester.delete(orderEntity);
	        return true;
	    }
		return false;
	}

	@Override
	public boolean removeOrderPosition(Integer orderId, Integer positionId) {
		OrderEntity orderEntity = orderSpringDataConnectorRequester.findById(orderId).orElse(null);
		//TODO Exception for null
		List<OrderPositionEntity> orderPositions = orderEntity.getOrderPositions();
		Optional<OrderPositionEntity> orderPositionOptional = orderEntity.getOrderPositions().stream()
				.filter(orderPosition -> orderPosition.getPositionId() == positionId).findFirst();
		if (orderPositionOptional.isPresent()) {
			orderPositions.remove(orderPositionOptional.get());	
			return true;
		}
		return false;
	}

	@Override
	public boolean addComplaint(ArticleComplaintRequest complaint) {
		OrderEntity orderEntity = orderSpringDataConnectorRequester.findById(complaint.getOrderId()).orElse(null);
		orderEntity.setComplain(complaint.getReason());
		orderSpringDataConnectorRequester.save(orderEntity); 
//		update order
		return true;
	}
	
	//TODO getTotalPrice

}