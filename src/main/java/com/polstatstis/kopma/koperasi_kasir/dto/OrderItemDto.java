package com.polstatstis.kopma.koperasi_kasir.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class OrderItemDto {
    private String productName;
    private Integer quantity;
}
