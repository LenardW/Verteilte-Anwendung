package de.leuphana.shop.component;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import de.leuphana.shop.component.behaviour.service.CatalogService;
import de.leuphana.shop.component.structure.sales.CatalogEntity;
import de.leuphana.shop.component.structure.sales.MappedArticle;
import de.leuphana.shop.connector.CatalogSpringDataConnectorRequester;

@SpringBootTest
class CatalogTest {

	private static final Logger logger = LogManager.getLogger(CatalogTest.class);

	@Autowired
	CatalogSpringDataConnectorRequester catalogSpringDataConnectorRequester;

	@Autowired
	CatalogService catalogService;

	@Test
	@Transactional
	@Rollback(false)
	void contextLoads() throws Exception {
		CatalogEntity catalogEntity = catalogService.addArticleToCatalog(8);
		assertThat(catalogEntity).isNotNull();
		logger.info("Catalog with id: " + catalogEntity.getCatalogPositionId() + " created");
		List<MappedArticle> articles = catalogService.getArticlesFromCatalog();
		logger.info(articles.get(0).getName()); 
		assertThat(articles).isNotEmpty();
		logger.info("Articles from Catalog received and ready to show customer");

		//remove last two tests to see a change in the database
		
		int id = catalogService.getArticlesFromCatalog().get(0).getArticleId();
		System.out.println(catalogEntity.getCatalogPositionId());
		
		assertThat(catalogService.getArticleById(catalogEntity.getCatalogPositionId())).isNotNull();
		logger.info(catalogService.getArticleById(catalogEntity.getCatalogPositionId()).getName() + " received from Catalog");
		
		assertThat(catalogService.removeArticleFromCatalog(id));
		logger.info("Article with id " + id + " removed from Catalog");

	}
}
