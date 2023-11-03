package de.leuphana.shop.component;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import de.leuphana.shop.component.behaviour.service.CartService;
import de.leuphana.shop.component.structure.sales.CartEntity;
import de.leuphana.shop.component.structure.sales.CartItemEntity;
import de.leuphana.shop.connector.CartSpringDataConnectorRequester;

@SpringBootTest
class CartTest {

	private static final Logger logger = LogManager.getLogger(CartTest.class);

	@Autowired
	CartSpringDataConnectorRequester cartSpringDataConnectorRequester;

	@Autowired
	CartService cartImpl;

	@Test
	@Transactional
	@Rollback(false)
	void contextLoads() throws Exception {
		Integer cartId = cartImpl.createCart(1);
		assertThat(cartId).isNotNull();
		logger.info("cart " + cartId + " created");

		Integer cartItemId = cartImpl.addCartItemToCart(cartId, 15, 240.0f, 30);
		assertThat(cartItemId).isNotNull();
		logger.info("cartItem " + cartItemId + " added to cart " + cartId);

		CartEntity cartEntity = cartImpl.getCart(cartId);
		assertThat(cartEntity).isNotNull();
		logger.info("cart " + cartId + " received");

		CartItemEntity cartItemEntity = cartImpl.getCartItem(cartId, cartItemId);
		assertThat(cartItemEntity).isNotNull();
		logger.info("cartItem " + cartItemId + " from cart " + cartId + " received");

		//remove last two tests to see a change in the database
		assertThat(cartImpl.removeCartItem(cartId, cartItemId)).isTrue();
		logger.info("cartItem " + cartItemId + " from cart " + cartId + " removed");

		assertThat(cartImpl.removeCart(cartId)).isTrue();
		logger.info("cart " + cartId + " removed");

	}
}
