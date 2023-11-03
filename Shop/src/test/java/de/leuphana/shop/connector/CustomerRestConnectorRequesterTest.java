package de.leuphana.shop.connector;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.leuphana.shop.component.structure.sales.MappedCustomer;

@SpringBootTest
class CustomerRestConnectorRequesterTest {

	@Autowired 
	CustomerRestConnectorRequester customerService; 
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void test() {
		MappedCustomer customer = customerService.addCustomer(new MappedCustomer(1, "König", "Universitätsallee 1", 3, null));
		assertThat(customer).isNotNull(); 
	}

}
