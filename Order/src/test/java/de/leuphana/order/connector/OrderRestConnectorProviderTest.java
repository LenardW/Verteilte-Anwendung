package de.leuphana.order.connector;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import de.leuphana.order.component.structure.OrderEntity;

@SpringBootTest
class OrderRestConnectorProviderTest {

	@Autowired
	OrderRestConnectorProvider orderRestConnectorProvider;

	@Autowired
	WebApplicationContext webApplicationContext;

	static OrderEntity orderEntity;

	@BeforeAll
	static void createEntity() {
		orderEntity = OrderEntity.builder().customerId(1).orderId(1).orderPositions(null).build();}

	@Test
	@Order(1)
	@Transactional
	@Rollback(false)
	void addOrderPositionTest() throws Exception {
		assertThat(orderRestConnectorProvider.addOrderPositionToOrder(1,1,1f,1)).isNotNull(); 
	}

	@Test
	@Order(2)
	@Transactional
	@Rollback(false)
	void getOrderTest() throws Exception {
		assertThat(orderRestConnectorProvider.getOrderByID(orderEntity.getOrderId())).isNotNull();
	}

//	@Test
//	@Order(3)
//	@Transactional
//	@Rollback(false)
//	void addArticalToDBTest() throws Exception {
//		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//		String uri = "http://localhost:8010/article/add?name=Monstera&manufaktor=Annabel&price=1.5f";
//		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.ALL))
//				.andReturn();
//
//		int status = mvcResult.getResponse().getStatus();
//		assertEquals(200, status);
//		String content = mvcResult.getResponse().getContentAsString();
//		System.out.println(content);
//		ObjectMapper objectMapper = new ObjectMapper();
//		ArticleEntity[] articleList =  objectMapper.readValue(content, ArticleEntity[].class);
//		assertTrue(articleList.length > 0);
//	}

}
