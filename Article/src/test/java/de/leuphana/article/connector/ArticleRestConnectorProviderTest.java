package de.leuphana.article.connector;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.leuphana.article.component.structure.ArticleEntity;

@SpringBootTest
class ArticleRestConnectorProviderTest {

	@Autowired
	ArticleRestConnectorProvider articleRestConnectorProvider;

	@Autowired
	WebApplicationContext webApplicationContext;

	static ArticleEntity articleEntity;
	
	protected MockMvc mvc;

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	@BeforeAll
	static void createEntity() {
		articleEntity = ArticleEntity.builder().articleId(1).manufactor("nike").name("Superschuh").price(1.1f).build();
	}

	@Test
	@Order(1)
	@Transactional
	@Rollback(false)
	void addArticleTest() throws Exception {
		assertThat(articleRestConnectorProvider.addArticle(ArticleEntity.builder().articleId(1).manufactor("Leuphana").name("Honig").price(2).build())).isNotNull(); 
//		isNotBlank();
	}

	@Test
	@Order(2)
	@Transactional
	@Rollback(false)
	void getArticleTest() throws Exception {
		articleEntity = articleRestConnectorProvider.findAll().get(0);
		assertThat(
				articleRestConnectorProvider.findByID(articleEntity.getArticleId().toString()).equals(articleEntity));
	}

//	@Test
//	@Order(3)
//	@Transactional
//	@Rollback(false)
//	void addArticalToDBTest() throws Exception {
//		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//		String uri = "http://localhost:8010/article/add?name=Monstera&manufaktor=Annabel&price=1.5f";
//		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.ALL))
//				.andReturn();
//
//		int status = mvcResult.getResponse().getStatus();
//		assertEquals(200, status);
//		String content = mvcResult.getResponse().getContentAsString();
//		System.out.println(content);
//		ObjectMapper objectMapper = new ObjectMapper();
//		ArticleEntity[] articleList =  objectMapper.readValue(content, ArticleEntity[].class);
//		assertTrue(articleList.length > 0);
//	}

}
