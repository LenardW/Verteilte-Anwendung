package de.leuphana.order.component;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import de.leuphana.order.component.behaviour.OrderComponentService;
import de.leuphana.order.component.structure.OrderEntity;
import de.leuphana.order.component.structure.OrderPositionEntity;
import de.leuphana.order.connector.OrderSpringDataConnectorRequester;

@SpringBootTest
class OrderApplicationTests {

	private static final Logger logger = LogManager.getLogger(OrderApplicationTests.class);

	@Autowired
	OrderSpringDataConnectorRequester orderSpringDataConnectorRequester;

	@Autowired
	OrderComponentService orderImpl;

	@Test
	@Transactional
	@Rollback(false)
	void contextLoads() throws Exception {
		Integer orderId = orderImpl.createOrder(1);
		assertThat(orderId).isNotNull();
		logger.info("order " + orderId + " created");

		Integer positionId = orderImpl.addOrderPositionToOrder(orderId, 15, 240.0f, 30);
		assertThat(positionId).isNotNull();
		logger.info("orderPosition " + positionId + " added to order " + orderId);

		OrderEntity orderEntity = orderImpl.getOrder(orderId);
		assertThat(orderEntity).isNotNull();
		logger.info("order " + orderId + " received");

		OrderPositionEntity orderPositionEntity = orderImpl.getOrderPosition(orderId, positionId);
		assertThat(orderPositionEntity).isNotNull();
		logger.info("orderPosition " + positionId + " from order " + orderId + " received");

		//remove last two tests to see a change in the database
//		assertThat(orderImpl.removeOrderPosition(orderId, positionId)).isTrue();
		logger.info("orderPosition " + positionId + " from order " + orderId + " removed");

//		assertThat(orderImpl.removeOrder(orderId)).isTrue();
		logger.info("order " + orderId + " removed");

	}
}
