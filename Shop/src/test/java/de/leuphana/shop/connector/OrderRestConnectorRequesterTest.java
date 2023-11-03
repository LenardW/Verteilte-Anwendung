package de.leuphana.shop.connector;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import de.leuphana.shop.component.structure.sales.MappedOrder;
import de.leuphana.shop.connector.OrderRestConnectorRequester;

@SpringBootTest
class OrderRestConnectorRequesterTest {
	
	@Autowired
	OrderRestConnectorRequester orderRestConnectorRequester;
	
	private Integer orderId;

	private MappedOrder mappedOrder;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	@Transactional
	@Rollback(false)
	void contextLoads() throws Exception  {
		//assertTrue(orderRestConnectorRequester.removeOrder(2));
		orderId = orderRestConnectorRequester.createOrder(12);
		assertThat(orderId).isNotNull();
		mappedOrder = orderRestConnectorRequester.getOrder(orderId);
		assertThat(mappedOrder).isNotNull();
		
	}

}
