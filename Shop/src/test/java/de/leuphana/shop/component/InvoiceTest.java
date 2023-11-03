package de.leuphana.shop.component;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import de.leuphana.shop.component.behaviour.InvoiceImpl;
import de.leuphana.shop.component.structure.billing.InvoiceEntity;
import de.leuphana.shop.component.structure.billing.InvoicePositionEntity;
import de.leuphana.shop.connector.InvoiceSpringDataConnectorRequester;

@SpringBootTest
class InvoiceTest {

	private static final Logger logger = LogManager.getLogger(InvoiceTest.class);

	@Autowired
	InvoiceSpringDataConnectorRequester invoiceSpringDataConnectorRequester;

	@Autowired
	InvoiceImpl invoiceImpl;

	@Test
	@Transactional
	@Rollback(false)
	void contextLoads() throws Exception {
		InvoiceEntity invoice = invoiceImpl.createInvoice(1);
		assertThat(invoice).isNotNull();
		Integer invoiceId = invoice.getInvoiceId(); 
		logger.info("invoice " + invoice.getInvoiceId() + " created");

		InvoicePositionEntity invoicePosition = new InvoicePositionEntity(1, 15, 3f, 3, 240.0, invoiceId); 
		invoice = invoiceImpl.addInvoicePositionToInvoice(invoicePosition);
		assertThat(invoice).isNotNull();
		List<InvoicePositionEntity> positions = invoice.getInvoicePositions();
		Integer invoicePositionId = positions.get(positions.size()-1).getInvoicePositionId(); 
		logger.info("invoicePosition " + invoicePositionId + " added to invoice " + invoiceId);

		InvoiceEntity invoiceEntity = invoiceImpl.getInvoice(invoiceId);
		assertThat(invoiceEntity).isNotNull();
		logger.info("invoice " + invoiceEntity.getInvoiceId() + " received");

		InvoicePositionEntity invoicePositionEntity = invoiceImpl.getInvoicePosition(invoiceId, invoicePositionId);
		assertThat(invoicePositionEntity).isNotNull();
		logger.info("invoicePosition " + invoicePositionId + " from invoice " + invoiceId + " received");

		//remove last two tests to see a change in the database
//		assertThat(invoiceImpl.removeInvoicePosition(invoiceId, invoicePositionId)).isTrue();
//		logger.info("invoicePosition " + invoicePositionId + " from invoice " + invoiceId + " removed");

		assertThat(invoiceImpl.removeInvoice(invoiceId)).isTrue();
		logger.info("invoice " + invoiceId + " removed");

	}
}
