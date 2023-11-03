package de.leuphana.article.component.structure;

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
@Table(name="article")
public class ArticleEntity {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer articleId;
	
	@Column
	private String manufactor;
	
	@Column
	private String name;
	
	@Column
	private Integer nrComplaints; 
	
	@Column
	private float price;
}