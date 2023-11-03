package de.leuphana.customer.component;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import de.leuphana.customer.component.behaviour.CustomerComponentService;
import de.leuphana.customer.component.structure.CustomerEntity;
import de.leuphana.customer.connector.CustomerSpringDataConnectorRequester;

@SpringBootTest
class CustomerApplicationTests {
	@Autowired
	CustomerSpringDataConnectorRequester customerSpringDataConnectorRequester;

	@Autowired
	CustomerComponentService customerImpl;

	static CustomerEntity customerEntity;

	@BeforeAll
	static void createEntity() {
		customerEntity = CustomerEntity.builder().customerId(1).name("König").address("Scharnhorststraße 20").build();
	}

	@Test
	@Order(1)
	@Transactional
	@Rollback(false)
	void addCustomerTest() throws Exception {
		assertThat(customerEntity).isNotNull();

		customerEntity = customerImpl.addCustomer( "Kafka", "Scharnhorststraße 16");
		assertThat(customerEntity).isNotNull();
	}

	@Test
	@Order(2)
	@Transactional
	@Rollback(false)
	void moreTest() throws Exception {

		customerEntity = customerImpl.getCustomer(customerEntity.getCustomerId());
		assertThat(customerEntity).isNotNull();

		assertThat(customerImpl.removeCustomer(customerEntity.getCustomerId())).isTrue();

//		assertThat(customerImpl.addCartToCustomer(1, 2)).isTrue();

	}
}
