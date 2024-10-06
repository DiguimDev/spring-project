package com.springproject.commerce.dto;

import com.springproject.commerce.entities.Product;
import jakarta.validation.constraints.*;

public class ProductDto {
    private Long id;
    @NotBlank(message = "Campo Requerido")
    @Size(min = 3, max = 80, message = "Nome vai de 3 a 80")
    private String name;
    @NotBlank
    @Size(min = 10, message = "A descrição deve ter no mínimo 10 Caracteres")
    private String description;
    @Positive(message = "O preço deve ser somente positivo")
    private Double price;
    private String imgUrl;

    public ProductDto(){
    }

    public ProductDto(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imgUrl = product.getImgUrl();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
