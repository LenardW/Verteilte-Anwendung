package de.leuphana.shop.component.behaviour;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.leuphana.shop.component.behaviour.service.CartService;
import de.leuphana.shop.component.structure.sales.CartEntity;
import de.leuphana.shop.component.structure.sales.CartItemEntity;
import de.leuphana.shop.connector.CartSpringDataConnectorRequester;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Transactional
@Service
public class CartImpl implements CartService{

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	private CartSpringDataConnectorRequester cartSpringDataConnectorRequester;
	
	@Override
	public Integer createCart(Integer customerId) {
		ArrayList<CartItemEntity> cartItems = new ArrayList<CartItemEntity>();

		CartEntity cartEntity = cartSpringDataConnectorRequester
				.save(CartEntity.builder()
						.cartItems(cartItems)
						.customerId(customerId)
						.numberOfArticles(0)
						.build());

		return cartEntity.getCartId();
	}

	@Override
	public Integer addCartItemToCart(Integer cartId, Integer articleId,Float articlePrice, Integer articleQuantity) {
		CartEntity cartEntity = cartSpringDataConnectorRequester.findById(cartId).orElse(null);
		//TODO Exception for null
		CartItemEntity cartItemEntity = CartItemEntity.builder()
				.articleId(articleId)
				.articlePrice(articlePrice)
				.articleQuantity(articleQuantity)
				.cartId(cartId)
				.build();

		List<CartItemEntity> cartItems = cartEntity.getCartItems();
		cartItems.add(cartItemEntity);
		cartEntity.setCartItems(cartItems);
		entityManager.persist(cartItemEntity);
		entityManager.flush();
		cartEntity.setNumberOfArticles(cartEntity.getNumberOfArticles()+1);
		return cartItemEntity.getCartItemId();
	}

	@Override
	public CartEntity getCart(Integer cartId) {
		return cartSpringDataConnectorRequester.findById(cartId).orElse(null);
		//TODO Exception for null
	}
	
	@Override
	public CartItemEntity getCartItem(Integer cartId, Integer cartItemId) {
		CartEntity cartEntity = cartSpringDataConnectorRequester.findById(cartId).orElse(null);
		//TODO Exception for null
		Optional<CartItemEntity> cartItemOptional = cartEntity.getCartItems().stream()
				.filter(cartItemEntity -> cartItemEntity.getCartItemId() == cartItemId).findFirst();
		if (cartItemOptional.isPresent()) {
			return cartItemOptional.get();
		}
		throw new RuntimeException("CartItemEntity not found");

	}
	
	@Override
	public boolean removeCart(Integer cartId) {
		CartEntity cartEntity = cartSpringDataConnectorRequester.findById(cartId).orElse(null);
		//TODO Exception for null optinal
		if (cartEntity != null) {
	        cartSpringDataConnectorRequester.delete(cartEntity);
	        return true;
	    }
		return false;
	}

	@Override
	public boolean removeCartItem(Integer cartId, Integer cartItemId) {
		CartEntity cartEntity = cartSpringDataConnectorRequester.findById(cartId).orElse(null);
		//TODO Exception for null
		List<CartItemEntity> cartItems = cartEntity.getCartItems();
		Optional<CartItemEntity> cartItemOptional = cartEntity.getCartItems().stream()
				.filter(cartItemEntity -> cartItemEntity.getCartItemId() == cartItemId).findFirst();
		if (cartItemOptional.isPresent()) {
			cartItems.remove(cartItemOptional.get());	
			cartEntity.setNumberOfArticles(cartEntity.getNumberOfArticles()-1);
			return true;
		}
		return false;
	}

	@Override
	public boolean decrementArticleQuantityInCart(Integer cartId, Integer cartItemId) {
		CartEntity cartEntity = cartSpringDataConnectorRequester.findById(cartId).orElse(null);
		Optional<CartItemEntity> cartItemOptional = cartEntity.getCartItems().stream()
				.filter(cartItemEntity -> cartItemEntity.getCartItemId() == cartItemId).findFirst();
		if (cartItemOptional.isPresent()) {
			cartItemOptional.get().setArticleQuantity(cartItemOptional.get().getArticleQuantity()-1);
			return true;
		}
		return false;
	}

}