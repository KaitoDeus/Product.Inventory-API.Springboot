package com.example.inventory.payload;

import java.math.BigDecimal;
import jakarta.validation.constraints.*;

public class ProductRequest {
    @NotBlank(message = "Product name is required")
    private String productName;

    private String material;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be > 0")
    private BigDecimal price;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity must be >= 0")
    private Integer quantity;

    @NotNull(message = "CategoryId is required")
    private Integer categoryId;

    public ProductRequest() {
    }

    // getters/setters
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
