package com.springproject.commerce.services;

import com.springproject.commerce.dto.ProductDto;
import com.springproject.commerce.entities.Product;
import com.springproject.commerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDto findById(Long id){
        Optional<Product> result = productRepository.findById(id);
        Product product = result.get();
        return ProductDto.ProductDtoBuilder
                .builder()
                .name(product.getName())
                .id(product.getId())
                .price(product.getPrice())
                .imgUrl(product.getImgUrl())
                .description(product.getDescription())
                .build();
    }
}
