package de.leuphana.shop.component.behaviour;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.leuphana.shop.component.behaviour.service.CartService;
import de.leuphana.shop.component.behaviour.service.CatalogService;
import de.leuphana.shop.component.behaviour.service.CustomerService;
import de.leuphana.shop.component.behaviour.service.InvoiceService;
import de.leuphana.shop.component.behaviour.service.SupplierService;
import de.leuphana.shop.component.structure.billing.InvoiceEntity;
import de.leuphana.shop.component.structure.billing.InvoicePositionEntity;
import de.leuphana.shop.component.structure.sales.ArticleComplaintRequest;
import de.leuphana.shop.component.structure.sales.CartEntity;
import de.leuphana.shop.component.structure.sales.CartItemEntity;
import de.leuphana.shop.component.structure.sales.CatalogEntity;
import de.leuphana.shop.component.structure.sales.MappedArticle;
import de.leuphana.shop.component.structure.sales.MappedCustomer;
import de.leuphana.shop.component.structure.sales.MappedOrder;
import de.leuphana.shop.component.structure.sales.MappedOrderPosition;
import de.leuphana.shop.connector.ArticleComplaintJmsMessageSender;
import de.leuphana.shop.connector.ArticleRestConnectorRequester;
import de.leuphana.shop.connector.CartSpringDataConnectorRequester;
import de.leuphana.shop.connector.CatalogSpringDataConnectorRequester;
import de.leuphana.shop.connector.CustomerRestConnectorRequester;
import de.leuphana.shop.connector.OrderRestConnectorRequester;

@Service
public class ShopImpl implements CustomerService, SupplierService {
	private static final Logger log = LogManager.getLogger(ShopImpl.class);
	private CatalogEntity catalog;
	private CartEntity cart;
	private CartItemEntity cartItem;
	private MappedCustomer customer;
	private MappedOrder order;
	private MappedArticle article;
	private Map<Integer, MappedCustomer> customers;
  
	@Autowired
	ArticleRestConnectorRequester articleService;

	@Autowired
	InvoiceService invoiceService;

	@Autowired
	CatalogService catalogService;

	@Autowired
	OrderRestConnectorRequester orderService;
	
	@Autowired 
	CustomerRestConnectorRequester customerService; 
	
	@Autowired
	ArticleComplaintJmsMessageSender sender;
	
	@Autowired
	CartService cartService;

// Supplier Services  

	@Override
	public MappedArticle addArticle(MappedArticle article) {
		log.info("Article " + article.getName() + " to be created by articleRestConnectorRequester");
		MappedArticle articleReceived = articleService.addArticle(article);
		log.info(articleReceived.getArticleId());
		return articleReceived;
	}

	@Override
	public String removeArticle(Integer articleId) {
		log.info("remove article with Id "+ articleId);
		return articleService.remove(articleId);
	}

	@Override
	public CatalogEntity addArticleToCatalog(Integer articleId) {
		log.info("Add Article with Id " + articleId);
		return catalogService.addArticleToCatalog(articleId);

	}

	@Override
	public List<MappedArticle> getArticlesFromCatalog() {
		log.info("get all articles from catalog");
		return catalogService.getArticlesFromCatalog();
	}

	@Override
	public Boolean removeArticleFromCatalog(Integer articleId) {
		log.info("remove article from catalog with Id "+ articleId);
		return catalogService.removeArticleFromCatalog(articleId);
	}

	@Override
	public MappedCustomer createCustomerWithCart(String name, String address) {
		customer = new MappedCustomer();
		customer.setName(name);
		customer.setAddress(address);
		customer = customerService.addCustomer(customer);
//		Integer cartId = cartService.createCart(customer.getCustomerId());
		customerService.addCartToCustomer(customer.getCustomerId(), cartService.createCart(customer.getCustomerId()));
		customer = customerService.getCustomer(customer.getCustomerId());
		log.info("create customer and cart with Id "+ customer.getCustomerId());
		return customer;
	}

	@Override
	public MappedCustomer getCustomer(Integer customerId) {
		log.info("get customer with Id "+ customerId);
		return customerService.getCustomer(customerId);
	}

	@Override
	public boolean removeCustomer(Integer customerId) throws Exception {
		log.info("remove customer with Id "+ customerId);
		return customerService.removeCustomer(customerId);
	}

	@Override
	public CartEntity getCartForCustomer(Integer customerId) {
		log.info("get cart from customer with Id "+ customerId);
		customer = customerService.getCustomer(customerId);
		return cartService.getCart(customer.getCartId());
	}
	
	// =============== billing service interface =================

	@Override
	public InvoiceEntity createInvoice(Integer orderId, Integer customerId) {
		InvoiceEntity invoice = invoiceService.createInvoice(customerId);
		log.info("Create Invoice with orderId " + orderId + " and customerId: " + customerId);
    order = orderService.getOrder(orderId);
    for (MappedOrderPosition orderPosition : order.getOrderPositions()) {
			InvoicePositionEntity invoicePosition = new InvoicePositionEntity();
			invoicePosition.setArticleId(orderPosition.getArticleId());
			invoicePosition.setArticlePrice(orderPosition.getArticlePrice());
			invoicePosition.setArticleQuantity(orderPosition.getArticleQuantity());
			invoicePosition.setInvoiceId(invoice.getInvoiceId());
			log.info("add InvoicePosition " + invoicePosition);
			invoiceService.addInvoicePositionToInvoice(invoicePosition);
		}
		return invoice;
	}
	
	@Override
	public List<MappedArticle> showAllArticle() {
		List<MappedArticle> articles = articleService.findAllArticle();
		return articles;
	}
	
	
//******************************************************************************************************

//	Customer Services 

	@Override
	public MappedArticle getArticleFromCatalog(int articleId) {
		log.info("get article from catalog with Id "+articleId);
		return catalogService.getArticleById(articleId);
	}

	@Override
	public Integer addItemToCart(Integer articleId, Integer cartId, Integer articleQuantity) {
		log.info("add Item with Id "+articleId+" to cart with Id "+cartId);
		return cartService.addCartItemToCart(cartId, articleId, articleService.getArticle(articleId).getPrice(), articleQuantity);
	}

	@Override
	public boolean removeArticleFromCart(Integer customerId, Integer cartItemId) {
		log.info("remove article from customer "+customerId+" cart with Id "+cartItemId);
		customer = customerService.getCustomer(customerId);
		return cartService.removeCartItem(customer.getCartId(), cartItemId);
	}

	@Override
	public String showCart(Integer customerId) {
		log.info("show cart from cusotmer "+customerId);
		customer = customerService.getCustomer(customerId);
		cart = cartService.getCart(customer.getCartId());
		String showCartAsString = "Cart from: "+customer.getName()+"contains "+cart.getNumberOfArticles()+" articles \n";
		for (CartItemEntity item : cart.getCartItems()) {
			article = articleService.getArticle(item.getArticleId());
			showCartAsString += "-------------------------------- \n";
			showCartAsString += "Article: "+article.getName() +"\n"+"Price: "+ article.getPrice()+"\n"+" Quantity: "+ item.getArticleQuantity()+ "\n";
			showCartAsString += "-------------------------------- \n";
		}
		return showCartAsString;
	}

	@Override
	public boolean decrementArticleQuantityInCart(Integer customerId, Integer cartItemId) {
		log.info("decrement article "+cartItemId+" quantity in cart from customer with Id "+customerId);
		customer = customerService.getCustomer(customerId);
		return cartService.decrementArticleQuantityInCart(customer.getCartId(), cartItemId);
	}

	@Override
	public MappedOrder checkOutCart(Integer customerId) {
		log.info("check out cart from customer "+customerId);
		customer = customerService.getCustomer(customerId);
		log.info("Check out cart for cart with id "+ customer.getCartId());
		cart = cartService.getCart(customer.getCartId());		
		order = orderService.getOrder(orderService.createOrder(customerId));
		log.info("Create order with id "+ order.getOrderId());
		for(CartItemEntity cartItem : cart.getCartItems()) {
			log.info("Adding Orderposition for Article " + cartItem.getArticleId());
			orderService.addOrderPositionToOrder(order.getOrderId(), cartItem.getArticleId(), cartItem.getArticlePrice(), cartItem.getArticleQuantity());
		}
		log.info("Adding order " + order.getOrderId() + " to customer");
		customerService.addOrderToCustomer(customerId, order.getOrderId());
		order = orderService.getOrder(customerId); 
		log.info("Order: " + order.getOrderId() + " for Customer: " + order.getCustomerId() + " with order Positions: " + order.getOrderPositions()); 
		return order;
	}

	@Override
	public void complainArticle(ArticleComplaintRequest articleComplaintRequest) {
		sender.sendMessage(articleComplaintRequest);
	}

	@Override
	public String status() {
		return articleService.status();
	}

	@Override
	public String showArticle(Integer articleId) {
		log.info("show article with Id "+articleId);
		MappedArticle article = articleService.getArticle(articleId);
		return ("Artikel mit ID " + article.getArticleId() + ", Name: " + article.getName() + " und Preis: " + article.getPrice());
	}
	
//
//	public Order checkOutCart(int customerId) {
//
//		Customer customer = customers.get(customerId);
//		CartVOR cart = customer.getCart();
//
//		Order order = new Order();
//		order.setOrderId(1);
//
//
//		int i = 1;
//		
//		List<CartItemEntity> orderPositions = new ArrayList<CartItemEntity>();
//
//		for (CartItemVOR cartItem : cart.getCartItems()) {
//			CartItemEntity orderPosition = new CartItemEntity();
//			orderPosition.setPositionId(i++);
//			orderPosition.setArticleId(cartItem.getArticleId());
//			orderPosition.setArticleQuantity(cartItem.getQuantity());
//			orderPositions.add(orderPosition);
//		}
//		order.setOrderPositions(orderPositions);
//
//		customer.addOrder(order);
//
//		return order;
//	}

}