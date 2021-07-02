package br.com.rezende.dbs.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {
	@Autowired
	private ProductDAO repository;

	@Override
	public List<ProductEntity> findAll() {
		  return repository.getProducts();
	}

}
