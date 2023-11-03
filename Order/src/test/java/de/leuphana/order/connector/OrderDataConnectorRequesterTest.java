package de.leuphana.order.connector;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import de.leuphana.order.component.structure.OrderEntity;

@SpringBootTest
class OrderDataConnectorRequesterTest {

	@Autowired
	OrderSpringDataConnectorRequester orderSpringDataConnectorRequester;

	static OrderEntity orderEntity;

	@BeforeAll
	static void createEntity() {
		orderEntity = OrderEntity.builder().customerId(1).orderId(1).orderPositions(null).build();
	}

	@Test
	@Order(1)
	@Transactional
	@Rollback(true)
	void saveArticle() throws Exception {
		assertThat(orderSpringDataConnectorRequester.save(orderEntity)).isNotNull(); 
	}

	@Test
	@Order(2)
	@Transactional
	@Rollback(false)
	void getAllArticles() throws Exception {
		orderSpringDataConnectorRequester.save(orderEntity); 
		assertThat(orderSpringDataConnectorRequester.findAll().get(0)).isNotNull();
	}
}
