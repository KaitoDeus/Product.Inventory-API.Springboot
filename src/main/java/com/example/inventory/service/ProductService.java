package com.example.inventory.service;

import com.example.inventory.model.Category;
import com.example.inventory.model.Product;
import com.example.inventory.payload.ErrorResponse;
import com.example.inventory.payload.ProductRequest;
import com.example.inventory.repository.CategoryRepository;
import com.example.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private final Pattern namePattern = Pattern.compile("^[A-Z][a-zA-Z0-9\\s]{2,50}$");

    public ResponseEntity<?> getAll() {
        List<Product> list = productRepository.findAll();
        return ResponseEntity.ok(list);
    }

    public ResponseEntity<?> getById(Integer id) {
        Optional<Product> p = productRepository.findById(id);
        if (p.isEmpty())
            return ResponseEntity.status(404).body(new ErrorResponse("PR40401", "Resource not found"));
        return ResponseEntity.ok(p.get());
    }

    public ResponseEntity<?> add(ProductRequest req) {
        // validation
        if (req.getProductName() == null || !namePattern.matcher(req.getProductName()).matches()) {
            return ResponseEntity.badRequest().body(new ErrorResponse("PR40001", "Invalid product name"));
        }
        if (req.getPrice() == null || req.getPrice().doubleValue() <= 0) {
            return ResponseEntity.badRequest().body(new ErrorResponse("PR40001", "Price must be > 0"));
        }
        if (req.getQuantity() == null || req.getQuantity() < 0) {
            return ResponseEntity.badRequest().body(new ErrorResponse("PR40001", "Quantity must be >= 0"));
        }
        Optional<Category> cat = categoryRepository.findById(req.getCategoryId());
        if (cat.isEmpty())
            return ResponseEntity.status(404).body(new ErrorResponse("PR40401", "Category not found"));

        Product p = new Product();
        p.setProductId(null); // let JPA generate if configured; but our entities use manual ids so generate
                              // max+1
        // assign id as max+1
        Integer nextId = 1;
        List<Product> all = productRepository.findAll();
        for (Product x : all)
            if (x.getProductId() != null && x.getProductId() >= nextId)
                nextId = x.getProductId() + 1;
        p.setProductId(nextId);
        p.setProductName(req.getProductName());
        p.setMaterial(req.getMaterial());
        p.setPrice(req.getPrice());
        p.setQuantity(req.getQuantity());
        p.setCategory(cat.get());
        p.setReleaseDate(java.time.LocalDate.now());
        productRepository.save(p);
        return ResponseEntity.status(201).body(p);
    }

    public ResponseEntity<?> update(Integer id, ProductRequest req) {
        Optional<Product> opt = productRepository.findById(id);
        if (opt.isEmpty())
            return ResponseEntity.status(404).body(new ErrorResponse("PR40401", "Product not found"));

        if (req.getProductName() == null || !namePattern.matcher(req.getProductName()).matches()) {
            return ResponseEntity.badRequest().body(new ErrorResponse("PR40001", "Invalid product name"));
        }
        if (req.getPrice() == null || req.getPrice().doubleValue() <= 0) {
            return ResponseEntity.badRequest().body(new ErrorResponse("PR40001", "Price must be > 0"));
        }
        if (req.getQuantity() == null || req.getQuantity() < 0) {
            return ResponseEntity.badRequest().body(new ErrorResponse("PR40001", "Quantity must be >= 0"));
        }
        Optional<Category> cat = categoryRepository.findById(req.getCategoryId());
        if (cat.isEmpty())
            return ResponseEntity.status(404).body(new ErrorResponse("PR40401", "Category not found"));

        Product p = opt.get();
        p.setProductName(req.getProductName());
        p.setMaterial(req.getMaterial());
        p.setPrice(req.getPrice());
        p.setQuantity(req.getQuantity());
        p.setCategory(cat.get());
        productRepository.save(p);
        return ResponseEntity.ok(p);
    }

    public ResponseEntity<?> delete(Integer id) {
        Optional<Product> opt = productRepository.findById(id);
        if (opt.isEmpty())
            return ResponseEntity.status(404).body(new ErrorResponse("PR40401", "Product not found"));
        productRepository.delete(opt.get());
        return ResponseEntity.ok(Map.of("message", "Deleted"));
    }

    public ResponseEntity<?> search(String name, String category) {
        List<Product> results;
        if (name != null && !name.isBlank() && category != null && !category.isBlank()) {
            results = productRepository
                    .findByProductNameContainingIgnoreCaseAndCategoryCategoryNameContainingIgnoreCase(name, category);
        } else if (name != null && !name.isBlank()) {
            results = productRepository.findByProductNameContainingIgnoreCase(name);
        } else if (category != null && !category.isBlank()) {
            results = productRepository.findByCategoryCategoryNameContainingIgnoreCase(category);
        } else {
            results = productRepository.findAll();
        }
        // group by category
        Map<String, List<Product>> grouped = new LinkedHashMap<>();
        for (Product p : results) {
            String key = p.getCategory() != null ? p.getCategory().getCategoryName() : "Uncategorized";
            grouped.computeIfAbsent(key, k -> new ArrayList<>()).add(p);
        }
        return ResponseEntity.ok(grouped);
    }
}
