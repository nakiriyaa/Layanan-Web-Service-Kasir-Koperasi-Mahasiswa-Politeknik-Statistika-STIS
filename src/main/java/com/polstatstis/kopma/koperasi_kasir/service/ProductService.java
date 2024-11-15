package com.polstatstis.kopma.koperasi_kasir.service;

import com.polstatstis.kopma.koperasi_kasir.dto.OrderItemDto;
import com.polstatstis.kopma.koperasi_kasir.dto.ProductDto;
import com.polstatstis.kopma.koperasi_kasir.entity.Product;
import com.polstatstis.kopma.koperasi_kasir.exception.CustomException;
import com.polstatstis.kopma.koperasi_kasir.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product getProductByNameOrId(String name) {
        if (name != null) {
            Optional<Product> product = productRepository.findByName(name);
            return product.orElse(null);
        }
        return null;
    }

    public Product saveProduct(Product product) {

//        ProductDto productDto = new ProductDto();
//
//        if (productDto.getName() == null) {
//            throw new CustomException("Nama produk tidak boleh null");
//        }

        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void updateStock(Long productId, Integer quantity) {
        Product product = getProductById(productId);
        if (product != null && product.getStock() >= quantity) {
            product.setStock(product.getStock() - quantity);
            productRepository.save(product);
        }
    }

    public void deleteProductById(Long id) {
        // Cek apakah produk dengan ID tersebut ada
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            // Jika produk ada, hapus produk tersebut
            productRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Product not found with ID: " + id);
        }
    }
}


