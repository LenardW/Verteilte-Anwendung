package de.leuphana.customer.connector;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import de.leuphana.customer.component.structure.CustomerEntity;

@SpringBootTest
class CustomerSpringDataConnectorRequesterTest {


@Autowired
CustomerSpringDataConnectorRequester customerSpringDataConnectorRequester;
	
	@Test
	@Transactional
	@Rollback(true)
	void contextLoads() throws Exception {
//		CustomerEntity customerEntity = CustomerEntity.builder().customerId(1).name("König").address("Universitätsallee 1").build(); 
//		customerEntity = customerSpringDataConnectorRequester.save(customerEntity); 
//		assertThat(customerEntity).isNotNull(); 
//		
//		Optional<CustomerEntity> customer = customerSpringDataConnectorRequester.findById(customerEntity.getCustomerId()); 
//		assertThat(customerEntity).isNotNull(); 
	}
}
