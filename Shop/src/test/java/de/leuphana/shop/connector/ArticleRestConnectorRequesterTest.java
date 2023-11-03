package de.leuphana.shop.connector;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import de.leuphana.shop.component.structure.sales.MappedArticle;

@SpringBootTest
class ArticleRestConnectorRequesterTest {
	
	@Autowired
	ArticleRestConnectorRequester articleRestConnectorRequester;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	@Transactional
	@Rollback(false)
	void contextLoads() throws Exception  {
		//assertTrue(orderRestConnectorRequester.removeOrder(2));
		MappedArticle article = new MappedArticle();
		article.setArticleId(1);
		article.setManufactor("TestManufactor");
		article.setName("Produkt");
		article.setPrice(1.10f);
		assertThat(articleRestConnectorRequester.addArticle(article)).isNotNull();	
	}

}
