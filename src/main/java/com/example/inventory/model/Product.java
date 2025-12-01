package com.example.inventory.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Product")
public class Product {
    @Id
    private Integer productId;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    private String productName;
    private String material;
    private BigDecimal price;
    private Integer quantity;
    private LocalDate releaseDate;

    public Product() {
    }

    public Product(Integer productId, Category category, String productName, String material, BigDecimal price,
            Integer quantity, LocalDate releaseDate) {
        this.productId = productId;
        this.category = category;
        this.productName = productName;
        this.material = material;
        this.price = price;
        this.quantity = quantity;
        this.releaseDate = releaseDate;
    }

    // getters/setters
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
