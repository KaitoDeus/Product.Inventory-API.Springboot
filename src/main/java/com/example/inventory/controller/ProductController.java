package com.example.inventory.controller;

import com.example.inventory.payload.ProductRequest;
import com.example.inventory.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Products", description = "Product management endpoints")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @Operation(summary = "List all products", description = "Get all products without authentication")
    public ResponseEntity<?> listAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID", description = "Retrieve a specific product by its ID")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return productService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @Operation(summary = "Create product", description = "Create a new product (requires ADMIN or MANAGER role)")
    @SecurityRequirement(name = "bearer-jwt")
    public ResponseEntity<?> create(@Valid @RequestBody ProductRequest req) {
        return productService.add(req);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @Operation(summary = "Update product", description = "Update an existing product (requires ADMIN or MANAGER role)")
    @SecurityRequirement(name = "bearer-jwt")
    public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody ProductRequest req) {
        return productService.update(id, req);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete product", description = "Delete a product (requires ADMIN role only)")
    @SecurityRequirement(name = "bearer-jwt")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return productService.delete(id);
    }

    @GetMapping("/search")
    @Operation(summary = "Search products", description = "Search products by name and/or category")
    public ResponseEntity<?> search(@RequestParam(required = false) String name,
            @RequestParam(required = false) String category) {
        return productService.search(name, category);
    }
}
