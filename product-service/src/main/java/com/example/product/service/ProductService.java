package com.example.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.product.dto.ProductRequest;
import com.example.product.dto.ProductResponse;
import com.example.product.entity.Product;
import com.example.product.repo.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {
	
	@Autowired 
	private ProductRepository productRepo;
	
	public Product createProduct(ProductRequest productRequest)
	{
		Product product= Product.builder()
				.name(productRequest.getName())
				.description(productRequest.getDescription())
				.price(productRequest.getPrice())
				.build();
		
		Product saved= productRepo.save(product);
		log.info("Product is saved : "+ saved.getId());
		return saved;
				
	}

	public List<ProductResponse> getAllProducts() {
		
		List<Product> products= productRepo.findAll();
		
		return products.stream().map(prod-> getProductsInList(prod)).toList();
		
	}
	
	private ProductResponse getProductsInList(Product prod)
	{	
		return ProductResponse.builder()
				.id(prod.getId())
				.name(prod.getName())
				.description(prod.getDescription())
				.price(prod.getPrice()).build();
		
	}

}
