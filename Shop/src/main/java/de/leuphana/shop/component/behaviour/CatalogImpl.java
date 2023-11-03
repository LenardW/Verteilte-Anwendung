package de.leuphana.shop.component.behaviour;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.leuphana.shop.component.behaviour.service.CatalogService;
import de.leuphana.shop.component.structure.sales.CatalogEntity;
import de.leuphana.shop.component.structure.sales.MappedArticle;
import de.leuphana.shop.connector.ArticleRestConnectorRequester;
import de.leuphana.shop.connector.CatalogSpringDataConnectorRequester;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class CatalogImpl implements CatalogService{
	private static final Logger log = LogManager.getLogger(CatalogImpl.class);

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	private CatalogSpringDataConnectorRequester catalogSpringDataConnectorRequester;
	
	@Autowired
	private ArticleRestConnectorRequester articleRestConnectorRequester; 
	
	@Override
	public CatalogEntity addArticleToCatalog(Integer articleId) {
		CatalogEntity catalogEntity = catalogSpringDataConnectorRequester
				.save(CatalogEntity.builder()
				.articleId(articleId)
				.build());
		return catalogEntity;
	}
	
	@Override
	public Integer getArticleIdFromCatalog(Integer catalogPositionId) {
		return catalogSpringDataConnectorRequester.findById(catalogPositionId).orElse(null).getArticleId();
	}
	
	@Override
	public boolean removeArticleFromCatalog (Integer articleId) {
		int catalogPositionId = 0 ; 
		List<CatalogEntity> articles = catalogSpringDataConnectorRequester.findAll(); 
//		articles.forEach(n-> {if(n.getArticleId().equals(articleId)) catalogPositionId = n.getCatalogPositionId();});
	    CatalogEntity catalogEntity = articles.stream().filter(article -> article.getArticleId().equals(articleId)).findFirst().orElse(null);
		catalogSpringDataConnectorRequester.deleteById(catalogEntity.getCatalogPositionId());
		return true;
	}

	@Override
	public List<MappedArticle> getArticlesFromCatalog() {
			List<CatalogEntity> catalogEntities = catalogSpringDataConnectorRequester.findAll(); 
			List<MappedArticle> articles = new ArrayList(); 
			for(CatalogEntity e : catalogEntities) {
				log.info("Add article with id " + e.getArticleId());
				articles.add(articleRestConnectorRequester.getArticle(e.getArticleId())); 
			}
		return articles;
	}

	@Override
	public MappedArticle getArticleById(int catalogPostionId) {
		Optional<CatalogEntity> catalogEntity = catalogSpringDataConnectorRequester.findById(catalogPostionId); 
		log.info(catalogEntity.get().getArticleId().toString()); 
		MappedArticle article = articleRestConnectorRequester.getArticle(catalogEntity.get().getArticleId());
		log.info(article.getName()); 
		return articleRestConnectorRequester.getArticle(catalogEntity.get().getArticleId()); 
	}
	

	
}