package de.leuphana.article.component.behaviour;

import java.util.List;

import org.springframework.stereotype.Service;

import de.leuphana.article.component.structure.ArticleComplaintRequest;
import de.leuphana.article.component.structure.ArticleEntity;
@Service
public interface ArticleComponentService {
	
//	public ArticleEntity addArticle(Integer articleId, String manufactor, String name, float price) throws Exception;
	public ArticleEntity addArticle(ArticleEntity article) throws Exception;
	
	public ArticleEntity getArticle(Integer articleId);
	
	public List<ArticleEntity> getAllArticle();
	
	public 	Boolean updateArticle(Integer articleId, String name, String manufactor, Float price);	
	
	public boolean removeArticle(Integer articleId) throws Exception;

	public boolean addComplaint(ArticleComplaintRequest request);

}
