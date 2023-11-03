package de.leuphana.customer.connector;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class CustomerRestConnectorProviderTests {

@Autowired
CustomerRestConnectorProvider restProvider; 

	@Test
	@Transactional
	@Rollback(false)
	void contextLoads() throws Exception {
		
		assertThat(restProvider.status()).isEqualTo("Customer Service is up!");  
	}
}
