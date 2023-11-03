package de.leuphana.shop.component.behaviour.service;

import java.util.List;

import de.leuphana.shop.component.structure.billing.InvoiceEntity;
import de.leuphana.shop.component.structure.sales.CartEntity;
import de.leuphana.shop.component.structure.sales.CatalogEntity;
import de.leuphana.shop.component.structure.sales.MappedArticle;
import de.leuphana.shop.component.structure.sales.MappedCustomer;
import de.leuphana.shop.component.structure.sales.MappedOrder;

public interface SupplierService {
//	public Integer addArticle(Integer articleId, String name, String manufactor, String price);
	public MappedArticle addArticle(MappedArticle article); 
	public String removeArticle(Integer articleId); 
	public Boolean removeArticleFromCatalog(Integer articleId);
	public CatalogEntity addArticleToCatalog(Integer catalogPositionId);
	public CartEntity getCartForCustomer(Integer customerId); 
	public MappedCustomer createCustomerWithCart(String name, String address);	
	public MappedCustomer getCustomer(Integer customerId);
	public boolean removeCustomer(Integer customerId) throws Exception;	
	public String status(); 
	public InvoiceEntity createInvoice(Integer orderId, Integer customerId); 
	List<MappedArticle> showAllArticle(); 
}
