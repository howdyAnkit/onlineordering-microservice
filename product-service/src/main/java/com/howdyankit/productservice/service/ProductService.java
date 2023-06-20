package com.howdyankit.productservice.service;

import com.howdyankit.productservice.dto.ProductRequest;
import com.howdyankit.productservice.dto.ProductResponse;
import com.howdyankit.productservice.model.Product;
import com.howdyankit.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);

        log.info("Product {} is Saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(this::mapToProductRepsonse).toList();
    }

    private ProductResponse mapToProductRepsonse(Product product) {
        return ProductResponse.builder().id(product.getId()).name(product.getName()).description(product.getDescription())
                .price(product.getPrice()).build();
    }
}
