package com.maids.springbootquiz.controller;

import com.maids.springbootquiz.exception.ResourceNotFoundException;
import com.maids.springbootquiz.model.Product;
import com.maids.springbootquiz.repository.ProductRepository;
import com.maids.springbootquiz.response.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.maids.springbootquiz.response.CustomResponseHelper.failureResponse;
import static com.maids.springbootquiz.response.CustomResponseHelper.successResponse;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("")
    public CustomResponse<List<Product>> getAllProducts()
    {
        CustomResponse<List<Product>> listCustomResponse;
        try {
            listCustomResponse = successResponse(productRepository.findAll());
        }
        catch (Exception e){
            listCustomResponse = failureResponse(e.getMessage());
        }
        return listCustomResponse;
    }

    @GetMapping("/{id}")
    public CustomResponse<Product> getProductById(@PathVariable(value = "id") Long productId){
        CustomResponse<Product> customResponse;
        try {
            Product product =
                    productRepository
                            .findById(productId)
                            .orElseThrow(() -> new ResourceNotFoundException("Product not found on :: " + productId));
            customResponse = successResponse(product);
        }
        catch (Exception e){
            customResponse = failureResponse(e.getMessage());
        }
        return customResponse;
    }

    @PostMapping("")
    public CustomResponse<Product> createProduct(@Valid @RequestBody Product product) {
        CustomResponse<Product> customResponse;
        try {
            Product newProduct =
                    productRepository.save(product);
            customResponse = successResponse(newProduct);
        }
        catch (Exception e){
            customResponse = failureResponse(e.getMessage());
        }
        return customResponse;
    }

    @PutMapping("/{id}")
    public CustomResponse<Product> updateProduct(
            @PathVariable(value = "id") Long productId, @Valid @RequestBody Product productDetails){
        CustomResponse<Product> customResponse;
        try {
            Product newProduct =
                    productRepository
                            .findById(productId)
                            .orElseThrow(() -> new ResourceNotFoundException("Product not found on :: " + productId));
            newProduct.setName(productDetails.getName());
            newProduct.setDescription(productDetails.getDescription());
            newProduct.setPrice(productDetails.getPrice());
            newProduct.setCategory(productDetails.getCategory());
            final Product updatedProduct = productRepository.save(newProduct);
            customResponse = successResponse(updatedProduct);
        }
        catch (Exception e){
            customResponse = failureResponse(e.getMessage());
        }
        return customResponse;
    }
}
