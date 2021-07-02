package br.com.rezende.dbs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.rezende.dbs.product.IProductService;
import br.com.rezende.dbs.product.ProductEntity;
import br.com.rezende.dbs.service.IServiceService;
import br.com.rezende.dbs.service.ServiceEntity;

@Component	
public class MyRunner implements CommandLineRunner {

	@Autowired
	private IProductService productService;
	
	@Autowired
	private IServiceService serviceService;

	@Override
	public void run(String... args) {
		var products = productService.findAll();
		System.out.println("Products");
		for (ProductEntity product : products) {
			System.out.println(product);
		}
		System.out.println("______________________________________________________");
		System.out.println("Services");
		var services = serviceService.findAll();

		for (ServiceEntity service : services) {
			System.out.println(service);
		}
	}

}
