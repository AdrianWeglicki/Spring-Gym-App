package com.aweglicki.app.init;

import com.aweglicki.app.model.Customer;
import com.aweglicki.app.model.Product;
import com.aweglicki.app.repositories.CustomerRepository;
import com.aweglicki.app.repositories.ProductRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevInit implements ApplicationListener<ContextRefreshedEvent> {

    public ProductRepository productRepository;
    public CustomerRepository customerRepository;

    public DevInit(ProductRepository productRepository, CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    void initData() {

        Product singleSession = new Product();
        singleSession.setProductName("Single Session");
        singleSession.setProductPrice(5.00);

        Product oneMonth = new Product();
        oneMonth.setProductName("1 month");
        oneMonth.setProductPrice(30.00);

        Product proteinPortion = new Product();
        proteinPortion.setProductName("Protein portion");
        proteinPortion.setProductPrice(1.00);

        Customer customer = new Customer();
        customer.setFirstName("Ethan");
        customer.setLastName("Rist");

        productRepository.save(singleSession);
        productRepository.save(oneMonth);
        productRepository.save(proteinPortion);
        customerRepository.save(customer);
    }
}
