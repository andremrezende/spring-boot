package br.com.rezende.dbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceService implements IServiceService {
	@Autowired
	private ServiceDAO repository;

	@Override
	public List<ServiceEntity> findAll() {
		return repository.getServices();
	}

}
