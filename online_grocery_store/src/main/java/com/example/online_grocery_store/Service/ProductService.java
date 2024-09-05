package com.example.online_grocery_store.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.online_grocery_store.Controller.ResourceNotFoundException;
import com.example.online_grocery_store.Entity.Category;
import com.example.online_grocery_store.Entity.Product;
import com.example.online_grocery_store.Repository.CategoryRepository;
import com.example.online_grocery_store.Repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Product getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new ResourceNotFoundException("Product not found");
        }
        return product;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Long categoryId, Product product) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null) {
            throw new ResourceNotFoundException("Category not found");
        }

        product.setCategory(category);

        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Long categoryId, Product product) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null) {
            throw new ResourceNotFoundException("Category not found");
        }

        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct == null) {
            throw new ResourceNotFoundException("Product not found");
        }

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategory(category);

        return productRepository.save(existingProduct);
    }
        
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
