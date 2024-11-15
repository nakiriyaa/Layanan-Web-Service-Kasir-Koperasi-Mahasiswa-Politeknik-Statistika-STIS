package com.polstatstis.kopma.koperasi_kasir.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter



public class OrderDto {
    private Double totalPrice; // Tidak wajib diisi

    @NotNull(message = "Payment is required")
    @NotBlank
    private Double payment;

    private Double change;

    @NotNull(message = "Items cannot be null")
    private List<OrderItemDto> items;
}
