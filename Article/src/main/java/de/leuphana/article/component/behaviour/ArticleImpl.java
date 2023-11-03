package de.leuphana.article.component.behaviour;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.leuphana.article.component.structure.ArticleComplaintRequest;
import de.leuphana.article.component.structure.ArticleEntity;
import de.leuphana.article.connector.ArticleSpringDataConnectorRequester;


@Service
public class ArticleImpl implements ArticleComponentService {
    private static final Logger log = LogManager.getLogger(ArticleImpl.class);
	
	@Autowired
	private ArticleSpringDataConnectorRequester articleSpringDataConnectorRequester;

	@Override
	public ArticleEntity addArticle(ArticleEntity article) throws Exception {
		log.info("Adding Article with name " + article.getName()  + " to Database");
		return articleSpringDataConnectorRequester.save(article);
	}

	@Override
	public ArticleEntity getArticle(Integer articleId) {
		log.info("Try to get Article with id " + articleId);
		ArticleEntity articleEntity = articleSpringDataConnectorRequester.findById(articleId).get();
		log.info("Received " + articleEntity.getName() + " from Database");
		return articleEntity; 
	}
	
	@Override
	public List<ArticleEntity> getAllArticle() {
		List<ArticleEntity> articleEntities = articleSpringDataConnectorRequester.findAll();
		log.info("Received all Articles from Database");
		return articleEntities; 
	}
	
	@Override
	public Boolean updateArticle(Integer articleId, String name, String manufactor, Float price) {
		try {
		ArticleEntity articleEntity = articleSpringDataConnectorRequester.findById(articleId).get();
		articleEntity.setName(name);
		articleEntity.setManufactor(manufactor);
		articleEntity.setPrice(price);
		articleSpringDataConnectorRequester.save(articleEntity);
		log.info("Updated " + articleEntity.getName() + " in Database");}
		catch(Exception e) {
			return false; 
		}
		return true; 
	}

	@Override
	public boolean removeArticle(Integer articleId) throws Exception {
		if(!articleSpringDataConnectorRequester.existsById(articleId))
			throw new Exception("Aricle can not be deleted because it does not exist");
		articleSpringDataConnectorRequester.deleteById(articleId);
		log.info("Article with ID " + articleId  + " removed from Database");
		if(articleSpringDataConnectorRequester.existsById(articleId))
			{log.info("Article with ID " + articleId  + " removed from Database");
			return false;
			}
		return true;
	}

	@Override
	public boolean addComplaint(ArticleComplaintRequest request) {
		Integer articleId = request.getArticleId(); 
		ArticleEntity article = articleSpringDataConnectorRequester.findById(articleId).get(); 
		Integer complaints = article.getNrComplaints(); 
		if(complaints==null) complaints = 1; 
		else complaints++; 
		article.setNrComplaints(complaints);
		articleSpringDataConnectorRequester.save(article); 
		log.info("Changed Number of Complaints for Article " + article.getName() + " to " + complaints);
		return true;
	}

}