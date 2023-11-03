package de.leuphana.shop.connector;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.leuphana.shop.component.structure.sales.MappedArticle;

@SpringBootApplication
@FeignClient(name="ArticleRequester", url="http://${ARTICLE:localhost}:8081/article")
public interface ArticleRestConnectorRequester {

	@GetMapping("/status/check")
	String status(); 

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	MappedArticle addArticle(@RequestBody MappedArticle mappedArticle);

	@GetMapping("/find/{id}")
	MappedArticle getArticle(@PathVariable("id") Integer articleId);
	
	@GetMapping("/findAll")
	List<MappedArticle> findAllArticle();

	@GetMapping("/remove/{id}")
	String remove(@PathVariable("id") Integer articleId);

}
