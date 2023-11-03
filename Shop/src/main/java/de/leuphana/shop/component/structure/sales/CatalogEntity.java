package de.leuphana.shop.component.structure.sales;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;


@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@Builder

@Entity
@Table(name = "catalog")
public class CatalogEntity {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer catalogPositionId;
	
	@Column
	private Integer articleId;

//	public CatalogEntity() {
//		articles = new HashSet<Article>();
//		this.catalogId = 1;
//		
//		Book book = new Book();
//		book.setArticleId(1);
//		book.setName("Entwickeln von Web-Anwendungen");
//		book.setPrice(23.0f);
//		book.setBookCategory(BookCategory.POPULAR_SCIENCE);
//		
//		articles.add(book);
//		
//		book =  new Book();
//		book.setArticleId(2);
//		book.setName("Java in a nutshell");
//		book.setPrice(10.5f);
//		book.setBookCategory(BookCategory.POPULAR_SCIENCE);
//		
//		articles.add(book);
//		
//		book =  new Book();
//		book.setArticleId(3);
//		book.setName("Servlets");
//		book.setPrice(16.5f);
//		book.setBookCategory(BookCategory.POPULAR_SCIENCE);
//		
//		articles.add(book);
//	}


}
