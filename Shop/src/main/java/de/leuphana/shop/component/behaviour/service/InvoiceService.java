package de.leuphana.shop.component.behaviour.service;

import de.leuphana.shop.component.structure.billing.InvoiceEntity;
import de.leuphana.shop.component.structure.billing.InvoicePositionEntity;

public interface InvoiceService {
	
	public InvoiceEntity createInvoice(Integer customerId);

	public InvoiceEntity getInvoice(Integer invoiceId);

	public InvoicePositionEntity getInvoicePosition(Integer invoiceId, Integer invoicePositionId);

	public boolean removeInvoice(Integer invoiceId);

	public boolean removeInvoicePosition(Integer invoiceId, Integer invoicePositionId);

	public InvoiceEntity addInvoicePositionToInvoice(InvoicePositionEntity invoicePostion);

}
