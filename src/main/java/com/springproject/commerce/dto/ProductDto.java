package com.springproject.commerce.dto;

import com.springproject.commerce.entities.Product;

public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;


    public static final class ProductDtoBuilder {
        private Long id;
        private String name;
        private String description;
        private Double price;
        private String imgUrl;

        private ProductDtoBuilder() {
        }

        public static ProductDtoBuilder builder() {
            return new ProductDtoBuilder();
        }

        public ProductDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ProductDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProductDtoBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ProductDtoBuilder price(Double price) {
            this.price = price;
            return this;
        }

        public ProductDtoBuilder imgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
            return this;
        }

        public ProductDto build() {
            ProductDto productDto = new ProductDto();
            productDto.price = this.price;
            productDto.description = this.description;
            productDto.id = this.id;
            productDto.name = this.name;
            productDto.imgUrl = this.imgUrl;
            return productDto;
        }
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
