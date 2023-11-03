package de.leuphana.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.annotation.EnableJms;

@EnableJpaRepositories(basePackages = { "de.leuphana.article.connector" })
@EntityScan(basePackages = { "de.leuphana.article.component.structure" })
@SpringBootApplication
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableJms
public class ArticleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticleApplication.class, args);
	}

}
