package de.leuphana.article.connector;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import de.leuphana.article.component.behaviour.ArticleComponentService;
import de.leuphana.article.component.structure.ArticleEntity;
import de.leuphana.article.connector.ArticleSpringDataConnectorRequester;

@SpringBootTest
class ArticleDataConnectorRequesterTest {

	@Autowired
	ArticleSpringDataConnectorRequester articleSpringDataConnectorRequester;

	static ArticleEntity articleEntity;

	@BeforeAll
	static void createEntity() {
		articleEntity = ArticleEntity.builder().articleId(1).manufactor("nike").name("Superschuh").price(1.1f).build();
	}

	@Test
	@Order(1)
	@Transactional
	@Rollback(false)
	void saveArticle() throws Exception {
		assertThat(articleSpringDataConnectorRequester.save(articleEntity)).isNotNull(); 
	}

	@Test
	@Order(2)
	@Transactional
	@Rollback(false)
	void getAllArticles() throws Exception {
		articleSpringDataConnectorRequester.save(articleEntity); 
		assertThat(articleSpringDataConnectorRequester.findAll().get(0)).isNotNull();
	}
}
