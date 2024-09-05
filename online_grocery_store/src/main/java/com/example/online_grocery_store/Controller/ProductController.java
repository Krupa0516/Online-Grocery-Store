package com.example.online_grocery_store.Controller;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.online_grocery_store.Entity.Product;
import com.example.online_grocery_store.Service.ProductService;

@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok().body(products);
    }

    @PostMapping("/{categoryId}")
    public ResponseEntity<Product> createProduct(@PathVariable Long categoryId, @RequestBody Product product) {
        Product createdProduct = productService.createProduct(categoryId, product);
        return ResponseEntity.created(URI.create("api/products/" + createdProduct.getId())).body(createdProduct);
    }

    @PutMapping("/{id}/{categoryId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @PathVariable Long categoryId, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, categoryId, product);
        return ResponseEntity.ok().body(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
