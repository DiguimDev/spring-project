package com.springproject.commerce.services;

import com.springproject.commerce.dto.ProductDto;
import com.springproject.commerce.entities.Product;
import com.springproject.commerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        Optional<Product> result = productRepository.findById(id);
        Product product = result.get();
        return new ProductDto(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDto> findAll(Pageable pageable) {
        Page<Product> result = productRepository.findAll(pageable);
        return result.map(ProductDto::new);
    }

    @Transactional
    public ProductDto insert(ProductDto productDto) {
        Product entity = new Product();
        copyDtoForEntity(productDto, entity);
        entity = productRepository.save(entity);
        return new ProductDto(entity);
    }

    @Transactional
    public ProductDto update(Long id, ProductDto productDto) {
        Product entity = productRepository.getReferenceById(id);
        copyDtoForEntity(productDto, entity);
        entity = productRepository.save(entity);
        return new ProductDto(entity);
    }

    private static void copyDtoForEntity(ProductDto productDto, Product entity) {
        entity.setName(productDto.getName());
        entity.setDescription(productDto.getDescription());
        entity.setImgUrl(productDto.getImgUrl());
        entity.setPrice(productDto.getPrice());
    }
}
