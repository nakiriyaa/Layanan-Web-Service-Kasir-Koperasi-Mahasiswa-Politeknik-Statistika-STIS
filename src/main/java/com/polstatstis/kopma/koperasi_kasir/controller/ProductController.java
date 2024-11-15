package com.polstatstis.kopma.koperasi_kasir.controller;

import com.polstatstis.kopma.koperasi_kasir.dto.ProductDto;
import com.polstatstis.kopma.koperasi_kasir.entity.Product;
import com.polstatstis.kopma.koperasi_kasir.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts().stream()
                .map(product -> new ProductDto(product.getId(), product.getName(), product.getStock(), product.getPrice()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        Optional<Product> productOptional = Optional.ofNullable(productService.getProductById(id));
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            return new ProductDto(product.getId(), product.getName(), product.getStock(), product.getPrice());
        } else {
            throw new IllegalArgumentException("Product not found with ID: " + id); // Bisa diganti dengan Exception Handler
        }
    }

    @PostMapping
    public ProductDto saveProduct(@Valid @RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setStock(productDto.getStock());
        product.setPrice(productDto.getPrice());

        Product savedProduct = productService.saveProduct(product);
        return new ProductDto(savedProduct.getId(), savedProduct.getName(), savedProduct.getStock(), savedProduct.getPrice());
    }

    // Endpoint untuk update produk berdasarkan ID
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDto productDto) {
        // Cek apakah produk dengan ID tersebut ada
        Optional<Product> existingProduct = Optional.ofNullable(productService.getProductById(id));
        if (existingProduct.isEmpty()) {
            return ResponseEntity.notFound().build(); // Jika produk tidak ditemukan, kembalikan status 404
        }

        // Mengambil produk yang sudah ada dan mengupdate nilainya
        Product product = existingProduct.get();
        product.setName(productDto.getName());
        product.setStock(productDto.getStock());
        product.setPrice(productDto.getPrice());

        Product updatedProduct = productService.saveProduct(product); // Simpan perubahan

        // Kembalikan response dengan data produk yang sudah diperbarui
        return ResponseEntity.ok(new ProductDto(updatedProduct.getId(), updatedProduct.getName(), updatedProduct.getStock(), updatedProduct.getPrice()));
    }

    // Endpoint untuk menghapus produk berdasarkan ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        // Cek apakah produk dengan ID tersebut ada
        Optional<Product> existingProduct = Optional.ofNullable(productService.getProductById(id));
        if (existingProduct.isEmpty()) {
            return ResponseEntity.notFound().build(); // Jika produk tidak ditemukan, kembalikan status 404
        }

        productService.deleteProductById(id); // Menghapus produk berdasarkan ID

        // Mengembalikan status 200 OK dengan pesan bahwa penghapusan berhasil
        return ResponseEntity.ok("Produk berhasil dihapus");
    }

}
