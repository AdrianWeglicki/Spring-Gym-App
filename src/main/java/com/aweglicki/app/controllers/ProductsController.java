package com.aweglicki.app.controllers;

import com.aweglicki.app.model.Product;
import com.aweglicki.app.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductsController {

    @Autowired
    ProductRepository productRepository;

    @RequestMapping("/product/{id}")
    public String product(@PathVariable Long id, Model model){
        model.addAttribute("product", productRepository.findById(id).orElse(null));
        return "product";
    }

    @RequestMapping(value ={"", "/products"}, method = RequestMethod.GET)
    public String productsList(Model model){
        model.addAttribute("products",productRepository.findAll());
        return "products";
    }

    @RequestMapping(value = "/saveproduct", method=RequestMethod.POST)
    @ResponseBody
    public String saveProduct(@RequestBody Product product) {
        productRepository.save(product);
        return Long.toString(product.getProductId());
    }

    @RequestMapping(value = "/removeproduct", method=RequestMethod.POST)
    @ResponseBody
    public String removeProduct(@RequestParam(value = "id", required = false) Long id) {
        Product product = productRepository.findById(id).get();
        productRepository.delete(product);
        return id.toString();
    }
}
