package de.leuphana.shop.component.behaviour;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.leuphana.shop.component.behaviour.service.InvoiceService;
import de.leuphana.shop.component.structure.billing.InvoiceEntity;
import de.leuphana.shop.component.structure.billing.InvoicePositionEntity;
import de.leuphana.shop.connector.InvoiceSpringDataConnectorRequester;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class InvoiceImpl implements InvoiceService {
	private static final Logger log = LogManager.getLogger(InvoiceImpl.class);
	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	private InvoiceSpringDataConnectorRequester invoiceSpringDataConnectorRequester;

	@Override
	public InvoiceEntity createInvoice(Integer customerId) {
		ArrayList<InvoicePositionEntity> invoicePositions = new ArrayList<InvoicePositionEntity>();

		InvoiceEntity invoiceEntity = invoiceSpringDataConnectorRequester
				.save(InvoiceEntity.builder().invoicePositions(invoicePositions).customerId(customerId).build());

		return invoiceEntity;
	}

	@Override
	public InvoiceEntity addInvoicePositionToInvoice(InvoicePositionEntity invoicePosition) {
		log.info("Get invoice Entity for position " + invoicePosition);
		InvoiceEntity invoiceEntity = invoiceSpringDataConnectorRequester.findById(invoicePosition.getInvoiceId())
				.orElse(null);
		InvoicePositionEntity invoicePositionEntity = InvoicePositionEntity.builder()
				.articleId(invoicePosition.getArticleId()).articlePrice(invoicePosition.getArticlePrice())
				.articleQuantity(invoicePosition.getArticleQuantity())
				.totalPrice((double) (invoicePosition.getArticleQuantity() * invoicePosition.getArticlePrice()))
				.invoiceId(invoicePosition.getInvoiceId()).build();

		List<InvoicePositionEntity> invoicePositions = invoiceEntity.getInvoicePositions();
		invoicePositions.add(invoicePositionEntity);
		invoiceEntity.setInvoicePositions(invoicePositions);
		invoiceEntity = invoiceSpringDataConnectorRequester.save(invoiceEntity); 
		return invoiceEntity;
	}

	@Override
	public InvoiceEntity getInvoice(Integer invoiceId) {
		return invoiceSpringDataConnectorRequester.findById(invoiceId).orElse(null);
	}

	@Override
	public InvoicePositionEntity getInvoicePosition(Integer invoiceId, Integer invoicePositionId) {
		InvoiceEntity invoiceEntity = invoiceSpringDataConnectorRequester.findById(invoiceId).orElse(null);
		Optional<InvoicePositionEntity> invoicePositionOptional = invoiceEntity.getInvoicePositions().stream()
				.filter(invoicePositionEntity -> invoicePositionEntity.getInvoicePositionId() == invoicePositionId)
				.findFirst();
		if (invoicePositionOptional.isPresent()) {
			return invoicePositionOptional.get();
		}
		throw new RuntimeException("InvoicePositionEntity not found");

	}

	@Override
	public boolean removeInvoice(Integer invoiceId) {
		InvoiceEntity invoiceEntity = invoiceSpringDataConnectorRequester.findById(invoiceId).orElse(null);
		// TODO Exception for null optinal
		if (invoiceEntity != null) {
			invoiceSpringDataConnectorRequester.delete(invoiceEntity);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeInvoicePosition(Integer invoiceId, Integer invoicePositionId) {
		InvoiceEntity invoiceEntity = invoiceSpringDataConnectorRequester.findById(invoiceId).orElse(null);
		List<InvoicePositionEntity> invoicePositions = invoiceEntity.getInvoicePositions();
		Optional<InvoicePositionEntity> invoicePositionOptional = invoiceEntity.getInvoicePositions().stream()
				.filter(invoicePositionEntity -> invoicePositionEntity.getInvoicePositionId() == invoicePositionId)
				.findFirst();
		if (invoicePositionOptional.isPresent()) {
			invoicePositions.remove(invoicePositionOptional.get());
			return true;
		}
		return false;
	}

}