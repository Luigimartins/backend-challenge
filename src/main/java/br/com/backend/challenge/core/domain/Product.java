package br.com.backend.challenge.core.domain;

import java.math.BigDecimal;

public class Product {
    private Integer id;
    private String name;
    private Category category;
    private BigDecimal basePrice;
    private BigDecimal tariffedPrice;

    public Product(String name, Category category, BigDecimal basePrice) {
        this.name = name;
        this.category = category;
        this.basePrice = basePrice;
    }

    public Product(Integer id, String name, Category category, BigDecimal basePrice, BigDecimal tariffedPrice) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.basePrice = basePrice;
        this.tariffedPrice = tariffedPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public BigDecimal getTariffedPrice() {
        return tariffedPrice;
    }

    public void setTariffedPrice(BigDecimal tariffedPrice) {
        this.tariffedPrice = tariffedPrice;
    }

}
