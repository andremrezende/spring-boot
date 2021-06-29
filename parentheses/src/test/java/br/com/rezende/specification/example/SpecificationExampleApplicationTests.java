package br.com.rezende.specification.example;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.rezende.specification.example.controller.dto.UserListRequest;
import br.com.rezende.specification.example.controller.specification.UserListSpecification;
import br.com.rezende.specification.example.domain.User;
import br.com.rezende.specification.example.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpecificationExampleApplicationTests {

	@Autowired
	private UserListSpecification spec;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void contextLoads() {
		UserListRequest request = new UserListRequest();
		List<User> users = userRepository.findAll(spec.getFilter(request));
		assertNotNull(users);
		assertThat(users.isEmpty(), is(true));
	}

}
