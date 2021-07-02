package br.com.rezende.dbs.service;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceEntity {
	private UUID id;
	private String name;
	private Double price;
}
