package de.leuphana.article.connector;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.leuphana.article.component.behaviour.ArticleComponentService;
import de.leuphana.article.component.behaviour.ArticleImpl;
import de.leuphana.article.component.structure.ArticleComplaintRequest;
import de.leuphana.article.component.structure.ArticleEntity;

@RestController
@RequestMapping("/article")
public class ArticleRestConnectorProvider {
    private static final Logger log = LogManager.getLogger(ArticleRestConnectorProvider.class);
	
    @Autowired
	private ArticleComponentService articleService;
	
	@Autowired
	private ArticleJmsMessageSender sender; 
	
	@PostMapping(value="/send")
	public ResponseEntity<String> send(@RequestBody String message){
		sender.sendMessage(message);
		return new ResponseEntity<>("Message sent " + message, HttpStatus.OK);
	}
	
	@GetMapping("/status/check")
	public String status() {
		ArticleComplaintRequest message = new ArticleComplaintRequest(); 
		message.setArticleId(1); 
		message.setOrderId(2); 
		message.setReason("Defect"); 
//		sender.sendMessage(message);
		return "Working";
	}
	

	@RequestMapping(value = "add", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ArticleEntity addArticle(@RequestBody ArticleEntity article) {
		try {
			log.info("MappedArticle " + article.getName() + " received and mapped to Article Entity");
		ArticleEntity articleEntity =  articleService.addArticle(article.builder().articleId(article.getArticleId()).name(article.getName()).manufactor(article.getManufactor()).price(article.getPrice()).build()); 
		log.info("Article with ID " + articleEntity.getArticleId() + " added to DB"); 
		return articleEntity; 
		}
		catch(Exception e){
			log.info(e);
			return null; 
		}
	}

	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String updateArticle(@RequestParam String id, String name, String manufaktor, String price) {
		try {
		if(articleService.updateArticle(Integer.parseInt(id), name, manufaktor, Float.parseFloat(price))==true)
		return "updated Article";
		else
			return "no valid arguments"; }
		catch(Exception e) {
			return "No valid arguments"; 
		}
	}

	@GetMapping("/findAll")
	public List<ArticleEntity> findAll() throws Exception {
		return articleService.getAllArticle();
	}

	@GetMapping(path = "/find/{id}")
	public ArticleEntity findByID(@PathVariable(required = true, name = "id") String id) {
		try {
			log.info("Try to get article with id " + id);
			ArticleEntity article = articleService.getArticle(Integer.parseInt(id));
			log.info("Got Article with ID " + article.getArticleId()); 
			System.out.println("Article: " + article.getName());
			return article;
		} catch (Exception e) {
			log.info(e); 
		}
		return null; 
	}

	@GetMapping(path = "/remove/{id}")
	public String remove(@PathVariable(required = true, name = "id") String id) {
		try {
			if (articleService.removeArticle((Integer.parseInt(id))) == true)
				return "Removed article " + id + " from Database";
		} catch (Exception e) {
		}
		return "No article with ID " + id + "found";
	}
}
