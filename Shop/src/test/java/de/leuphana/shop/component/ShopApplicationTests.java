package de.leuphana.shop.component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.leuphana.shop.component.behaviour.service.CustomerService;
import de.leuphana.shop.component.behaviour.service.SupplierService;
import de.leuphana.shop.component.structure.sales.MappedArticle;
import de.leuphana.shop.component.structure.sales.MappedCustomer;
import de.leuphana.shop.component.structure.sales.MappedOrder;

@SpringBootTest
class ShopApplicationTests {
	
	private static final Logger logger = LogManager.getLogger(InvoiceTest.class);

	@Autowired
	CustomerService customerService;
	
	@Autowired
	SupplierService supplierService; 
	
	private MappedOrder order; 
	private MappedArticle article; 
	private MappedCustomer customer; 
			
		@Order(1)
		@Test
		void canCustomerbeCreated() {
//			customer = supplierService.createCustomerWithCart(0);
		}
			
		@Order(2)
		@Test
		void canOrderBeCreatedTest() {
//			order = customerService.checkOutCart(customer.getCustomerId());
//			Assertions.assertNotNull(order);
		}
		
		@Order(2)
		@Test
		void canInvoiceBeCreatedTest() {
//			Assertions.assertNotNull(supplierService.createInvoice(order.getOrderId(), customer.getCustomerId()));
		}
}
