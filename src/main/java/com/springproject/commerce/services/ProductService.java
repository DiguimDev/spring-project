package com.springproject.commerce.services;

import com.springproject.commerce.dto.ProductDto;
import com.springproject.commerce.entities.Product;
import com.springproject.commerce.repositories.ProductRepository;
import com.springproject.commerce.services.exceptions.DataBaseException;
import com.springproject.commerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new ProductDto(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDto> findAll(String name,Pageable pageable) {
        Page<Product> result = productRepository.searchByName(name,pageable);
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
        try {
            Product entity = productRepository.getReferenceById(id);
            copyDtoForEntity(productDto, entity);
            entity = productRepository.save(entity);
            return new ProductDto(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            productRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException("Falha de Integridade Referencial");
        }
    }

    private static void copyDtoForEntity(ProductDto productDto, Product entity) {
        entity.setName(productDto.getName());
        entity.setDescription(productDto.getDescription());
        entity.setImgUrl(productDto.getImgUrl());
        entity.setPrice(productDto.getPrice());
    }
}
