package com.programacion4.unidad3ej3.feature.producto.dtos.request;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoPatchRequestDto {

    private Double precio;
    private Integer stock;

}