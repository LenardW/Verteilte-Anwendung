package de.leuphana.shop.component.structure.sales;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MappedArticle {
	private Integer articleId; 
	private String manufactor; 
	private String name; 
	private Float price; 
	}