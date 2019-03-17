package com.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BulkOrder {
	
	@Id
	@GeneratedValue
	@Column(name = "bulkorderid")
	private Integer bulkorderid;
	

}
