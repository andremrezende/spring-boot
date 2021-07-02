package br.com.rezende.dbs.product;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
	private UUID id;
	private String name;
	private Double price;
	

}
