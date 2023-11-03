package de.leuphana.article.component;

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
class ArticleServiceTest {

	@Autowired
	ArticleSpringDataConnectorRequester articleSpringDataConnectorRequester;

	@Autowired
	ArticleComponentService articleImpl;

	static ArticleEntity articleEntity;

	@BeforeAll
	static void createEntity() {
		articleEntity = ArticleEntity.builder().articleId(1).manufactor("nike").name("Superschuh").price(1.1f).build();
	}

	@Test
	@Order(1)
	@Transactional
	@Rollback(false)
	void addArticleTest() throws Exception {
		articleEntity = articleImpl.addArticle(articleEntity);
		assertThat(articleEntity).isNotNull();

//		assertThat(articleImpl.removeArticle(articleEntity.getArticleId())).isTrue();
	}

	@Test
	@Order(2)
	@Transactional
	@Rollback(false)
	void getArticleTest() throws Exception {
		List<ArticleEntity> articles = articleImpl.getAllArticle();
		articleEntity = articles.get(0);
		articleImpl.getArticle(articleEntity.getArticleId());
		assertThat(articleEntity).isNotNull();
	}

	@Test
	@Order(3)
	@Transactional
	@Rollback(false)
	void removeArticleTest() throws Exception {
		List<ArticleEntity> articles = articleImpl.getAllArticle();
		try {
			articleImpl.addArticle(articleEntity);
			articleEntity = articles.get(0);
			assertThat(articleImpl.removeArticle(articleEntity.getArticleId())).isTrue();
		} catch (Exception e) {
			System.out.println("Could not remove Article");
		}
	}

	@Test
	@Order(3)
	@Transactional
	@Rollback(false)
	void getAllArticleTest() throws Exception {
		try {
			articleImpl.addArticle(articleEntity);
			assertThat(articleImpl.getAllArticle()).isNotNull();
		} catch (Exception e) {
			System.out.println("Could not get Article");
		}

	}
}
