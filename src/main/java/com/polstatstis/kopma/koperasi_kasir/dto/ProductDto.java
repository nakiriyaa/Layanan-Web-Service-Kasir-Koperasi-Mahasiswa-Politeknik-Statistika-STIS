package com.polstatstis.kopma.koperasi_kasir.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
    private Long id;

    @NotBlank(message = "Product name is required")
    private String name;

    @NotNull(message = "Stock is required")
    @Positive(message = "Stock must be a positive number")
    private Integer stock;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be a positive number")
    private Double price;

    // Anda dapat menambahkan field atau metode tambahan jika diperlukan
}
