package de.leuphana.article.connector;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.leuphana.article.component.structure.ArticleEntity;

public interface ArticleSpringDataConnectorRequester extends JpaRepository<ArticleEntity, Integer> {

	Optional<ArticleEntity> findById(Integer id); 
	
}
