package de.leuphana.customer.component.structure;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
@Table(name = "customer")
public class CustomerEntity {
		@Id
		@Column
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer customerId;
		
		@Column
		private String name;
		
		@Column
		private String address;	
		
		@Column
		private Integer cartId;	
		
		@Column
		@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
		@JoinColumn(name = "customerId", referencedColumnName = "customerId")
		private List<CustomerOrders> customerOrders;	

}