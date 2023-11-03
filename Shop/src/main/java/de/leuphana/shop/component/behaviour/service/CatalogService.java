package de.leuphana.shop.component.behaviour.service;

import java.util.List;

import de.leuphana.shop.component.structure.sales.CatalogEntity;
import de.leuphana.shop.component.structure.sales.MappedArticle;

public interface CatalogService {

	public CatalogEntity addArticleToCatalog(Integer articleId); 
	public Integer getArticleIdFromCatalog(Integer catalogPositionId); 
	public boolean removeArticleFromCatalog (Integer articleId);
	public List<MappedArticle> getArticlesFromCatalog();
	public MappedArticle getArticleById(int catalogPositionId);
}
