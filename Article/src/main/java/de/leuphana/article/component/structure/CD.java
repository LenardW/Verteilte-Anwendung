package de.leuphana.article.component.structure;

import de.leuphana.article.component.behaviour.ArticleImpl;

public class CD extends ArticleImpl {

	private String artist;
	
	public CD() {
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

}