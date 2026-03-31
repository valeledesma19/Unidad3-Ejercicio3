package com.programacion4.unidad3ej3.feature.producto.services.interfaces.domain;

import com.programacion4.unidad3ej3.feature.producto.dtos.request.ProductoPatchRequestDto;
import com.programacion4.unidad3ej3.feature.producto.dtos.request.ProductoUpdateRequestDto;
import com.programacion4.unidad3ej3.feature.producto.dtos.response.ProductoResponseDto;

import java.util.List;

public interface IProductoService {
    List<ProductoResponseDto> findAll();
    ProductoResponseDto findById(Long id);
    ProductoResponseDto update(Long id, ProductoUpdateRequestDto request);
    ProductoResponseDto patch(Long id, ProductoPatchRequestDto request);
}
