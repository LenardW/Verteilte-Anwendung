package de.leuphana.article.component.structure;

import de.leuphana.article.component.behaviour.ArticleImpl;

public class Book extends ArticleEntity {

	private String author;
	private BookCategory bookCategory;

	public Book() {
		super();
	}
	
	public void setArticleId(int articleId) {
		super.setArticleId(articleId);
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public BookCategory getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(BookCategory bookCategory) {
		this.bookCategory = bookCategory;
	}

}