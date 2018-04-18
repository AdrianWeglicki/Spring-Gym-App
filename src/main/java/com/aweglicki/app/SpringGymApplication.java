package com.aweglicki.app;

import com.aweglicki.app.repository.CustomerOrderRepository;
import com.aweglicki.app.repository.CustomerRepository;
import com.aweglicki.app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringGymApplication  implements CommandLineRunner {

	@Autowired
	public ProductRepository productRepository;
	@Autowired
	public CustomerRepository customerRepository;
	@Autowired
	public CustomerOrderRepository customerOrderRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringGymApplication.class, args);
	}


	@Override
	public void run(String... strings) throws Exception {

	}

}
