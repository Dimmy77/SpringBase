package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.product.Product;
import ru.geekbrains.product.ProductRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String listPage(Model model) {
        logger.info("List page requested");

        model.addAttribute("products", productRepository.findAll());
        return "product";
    }

    @GetMapping("/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        logger.info("Edit page for id {} requested", id);

        model.addAttribute("product", productRepository.findById(id));
        return "product_form";
    }

    @PostMapping("/update")
    public String update(@Valid Product product, BindingResult result) {
        logger.info("Update endpoint requested");

        if (result.hasErrors()) {
            return "product_form";
        }

        if (product.getId() != null) {
            logger.info("Updating product with id {}", product.getId());
            productRepository.update(product);
        } else {
            logger.info("Creating new product");
            productRepository.insert(product);
        }

        if (product.getPrice() <= (float) 0) {
            result.rejectValue("price", "", "Price can't be negative or 0");
            return "product_form";
        }

        if (product.getProductName().equals("")) {
            result.rejectValue("productName", "", "Product name can't be empty");
            return "product_form";
        }
        return "redirect:/product";
    }

    @GetMapping("/new")
    public String create(Model model) {
        logger.info("Create new product request");

        model.addAttribute("product", new Product());
        return "product_form";
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") Long id) {
        logger.info("Product delete request");

        productRepository.delete(id);
        return "redirect:/product";
    }
}
