package de.leuphana.shop.connector;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.leuphana.shop.component.behaviour.service.CartService;
import de.leuphana.shop.component.behaviour.service.CustomerService;
import de.leuphana.shop.component.behaviour.service.SupplierService;
import de.leuphana.shop.component.structure.billing.InvoiceEntity;
import de.leuphana.shop.component.structure.sales.ArticleComplaintRequest;
import de.leuphana.shop.component.structure.sales.CartItemEntity;
import de.leuphana.shop.component.structure.sales.CatalogEntity;
import de.leuphana.shop.component.structure.sales.MappedArticle;
import de.leuphana.shop.component.structure.sales.MappedCustomer;
import de.leuphana.shop.component.structure.sales.MappedOrder;

@RestController
@RequestMapping("/shop")
public class ShopRestConnectorProvider {
	private static final Logger log = LogManager.getLogger(ShopRestConnectorProvider.class);

	@Autowired
	CustomerService customerService;

	@Autowired
	SupplierService supplierService;

	@Autowired
	CartService cartService;

	@Autowired
	ArticleComplaintJmsMessageSender sender;

	@Autowired
	ArticleRestConnectorRequester articleRestConnectorRequester;

	@GetMapping("/status")
	public String status() {
		return "Welcome to the shop!";
	}

	@PostMapping(path = "/complain", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ArticleComplaintRequest sendMessage(@RequestBody ArticleComplaintRequest message) {
		sender.sendMessage(message);
		return message;
	}

//	@GetMapping("/addArticle")
//	public String addArticle(Integer articleId, String name, String manufactor, Float price) {
//		supplierService.addArticle(articleId, name, manufactor, price); 
//		return "Article added!";
//	}

	@GetMapping("/article/status")
	public String statusArticle() {
		return supplierService.status();
	}

	@PostMapping(path = "/article/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public MappedArticle add(@RequestBody MappedArticle article) {
		log.info("Request to add Article with " + article.getName());
		return supplierService.addArticle(article);
	}

	@GetMapping("/article/find/{id}")
	public String getArticle(@PathVariable(required = true, name = "id") Integer id) {
		log.info("Getting Article with id " + id + " from DB."); 
		String s = customerService.showArticle(id);
		return s;
	}

	@GetMapping("/article/findAll")
	public List<MappedArticle> getAllArticle() {
		return supplierService.showAllArticle();
	}

	@GetMapping("/article/remove/{id}")
	public String removeArticle(@PathVariable(required = true, name = "id") Integer id) {
		System.out.println("ID: " + id);
		String s = supplierService.removeArticle(id);
		System.out.println(s);
		return s;
	}

	@GetMapping("/catalog/addArticleToCatalog/{id}")
	public CatalogEntity addArticleToCatalog(@PathVariable(required = true, name = "id") String id) {
		log.info("Id: " + id);
		Integer i = Integer.parseInt(id);
		CatalogEntity catalog = supplierService.addArticleToCatalog(i);
		return catalog;
	}

	@GetMapping("/catalog/showCatalog")
	public List<MappedArticle> getArticlesFromCatalog() {
		return customerService.getArticlesFromCatalog();
	}

	@GetMapping("/catalog/getArticle/{id}")
	public MappedArticle getArticleById(@PathVariable(required = true, name = "id") String id) {
		return customerService.getArticleFromCatalog(Integer.parseInt(id));
	}

	@GetMapping("/catalog/removeArticle/{id}")
	public Boolean removeArticleById(@PathVariable(required = true, name = "id") String id) {
		return supplierService.removeArticleFromCatalog(Integer.parseInt(id));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/invoice/create")
	public String works() {
		return "Works";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/invoice/createInvoice")
	public InvoiceEntity createInvoice(@RequestParam(value = "orderId") Integer orderId,
			@RequestParam(value = "customerId") Integer customerid) {
		return supplierService.createInvoice(orderId, customerid);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/cart/createCart")
	public Integer createCart(@RequestParam Integer customerId) {
		try {
			Integer cartId = cartService.createCart(customerId);
			log.info("Cart with ID " + cartId + " created and added to DB");

			return cartId;
		} catch (Exception e) {
			log.info(e);
			return null;
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/cart/checkoutCart")
	public MappedOrder checkoutCart(@RequestParam(value="customerId")  Integer customerId) {
		MappedOrder order = customerService.checkOutCart(customerId); 
		return order;
	}
  
	@RequestMapping(value = "/cart/addItemToCart", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Integer addCartItemToCart(@RequestBody CartItemEntity cartItem) {
			Integer cartItemId = customerService.addItemToCart(cartItem.getArticleId(), cartItem.getCartId(), cartItem.getArticleQuantity()); 
			log.info("Cartitem with ID " + cartItem.getArticleId() + " added to Cart "+ cartItem.getCartId());
			return cartItemId;
	}
	
	@PostMapping(value ="/customer/createNewCustomer", consumes=MediaType.APPLICATION_JSON_VALUE)
	public MappedCustomer createNewCustomer(@RequestBody MappedCustomer customer) {
		return supplierService.createCustomerWithCart(customer.getName(), customer.getAddress());
	}
}
