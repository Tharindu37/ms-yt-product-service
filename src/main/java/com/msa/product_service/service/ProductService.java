package com.msa.product_service.service;

import com.msa.product_service.dto.ProductRequest;
import com.msa.product_service.dto.ProductResponse;
import com.msa.product_service.model.Product;
import com.msa.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
//@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
//        Product product = Product.builder()
//                .name(productRequest.name())
//                .description(productRequest.description())
//                .price(productRequest.price())
//                .build();
        Product product = new Product();
        product.setName(productRequest.name());
        product.setDescription(productRequest.description());
        product.setSkuCode(productRequest.skuCode());
        product.setPrice(productRequest.price());
        productRepository.save(product);
//        log.info("Product created: {}", product);
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(),product.getSkuCode(), product.getPrice());
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getDescription(),product.getSkuCode(), product.getPrice()))
                .toList();
    }
}
