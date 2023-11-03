package de.leuphana.shop.component.behaviour.service;

import de.leuphana.shop.component.structure.sales.CartEntity;
import de.leuphana.shop.component.structure.sales.CartItemEntity;

public interface CartService {

	public Integer createCart(Integer customerId); 
	public Integer addCartItemToCart(Integer cartId, Integer articleId,Float articlePrice, Integer articleQuantity); 
	public CartEntity getCart(Integer cartId); 
	public CartItemEntity getCartItem(Integer cartId, Integer cartItemId);
	public boolean removeCart(Integer cartId);
	public boolean removeCartItem(Integer cartId, Integer cartItemId);
	public boolean decrementArticleQuantityInCart(Integer cartId, Integer cartItemId);
}
