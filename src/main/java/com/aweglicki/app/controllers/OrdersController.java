package com.aweglicki.app.controllers;

import com.aweglicki.app.model.Customer;
import com.aweglicki.app.model.CustomerOrder;
import com.aweglicki.app.model.Product;
import com.aweglicki.app.repositories.CustomerOrderRepository;

import com.aweglicki.app.repositories.CustomerRepository;
import com.aweglicki.app.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
public class OrdersController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerOrderRepository customerOrderRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String productsList(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "orders";
    }

    @RequestMapping(value = "/listOfOrders", method = RequestMethod.GET)
    public String orderList(Model model){
        model.addAttribute("orders", customerOrderRepository.findAll());
        return "listOfOrders";
    }

    @RequestMapping(value = "/createorder", method = RequestMethod.POST)
    @ResponseBody
    public String saveOrder(@RequestParam String firstName, @RequestParam String lastName, @RequestParam(value = "productIds[]") Long[] productIds) {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customerRepository.save(customer);
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCustomer(customerRepository.findById(customer.getCustomerId()).orElse(null));
        Set<Product> productSet = new HashSet<>();
        for (Long productId : productIds) {
            productSet.add(productRepository.findById(productId).orElse(null));
        }
        customerOrder.setProducts(productSet);
        Double total = 0.0;
        for (Long productId : productIds) {
            Product product = productRepository.findById(productId).orElse(null);
            Double productPrice = 0.0;
            if(product != null){
                productPrice = product.getProductPrice();
            }
            total = total + productPrice;
        }
        customerOrder.setTotal(total);
        customerOrderRepository.save(customerOrder);

        return customerOrder.getOrderId().toString();
    }

    @RequestMapping(value = "/removeorder", method = RequestMethod.POST)
    @ResponseBody
    public String removeOrder(@RequestParam  Long id) {
        CustomerOrder customerOrder = customerOrderRepository.findById(id).get();
        customerOrderRepository.delete(customerOrder);
        return id.toString();
    }
}
