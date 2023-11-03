package de.leuphana.shop.component.behaviour.service;

import java.util.List;

import de.leuphana.shop.component.structure.sales.ArticleComplaintRequest;
import de.leuphana.shop.component.structure.sales.CartItemEntity;
import de.leuphana.shop.component.structure.sales.CatalogEntity;
import de.leuphana.shop.component.structure.sales.MappedArticle;
import de.leuphana.shop.component.structure.sales.MappedOrder;

public interface CustomerService {
	public List<MappedArticle> getArticlesFromCatalog();
	public Integer addItemToCart(Integer articleId, Integer customerId, Integer articleQuantity); 
	public boolean removeArticleFromCart(Integer customerId, Integer cartItemId); 
	public String showArticle(Integer articleId); 
	public String showCart(Integer customerId); 
	public boolean decrementArticleQuantityInCart(Integer customerId, Integer cartItemId); 
	public MappedOrder checkOutCart(Integer customerId); 
	public void complainArticle(ArticleComplaintRequest articleComplaintRequest);
	MappedArticle getArticleFromCatalog(int articleId);
}
