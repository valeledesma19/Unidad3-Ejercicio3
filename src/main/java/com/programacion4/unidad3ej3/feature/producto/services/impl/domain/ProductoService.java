package com.programacion4.unidad3ej3.feature.producto.services.impl.domain;

import com.programacion4.unidad3ej3.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej3.feature.producto.mappers.ProductoMapper;
import com.programacion4.unidad3ej3.feature.producto.repositories.IProductoRepository;
import com.programacion4.unidad3ej3.feature.producto.services.interfaces.domain.IProductoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductoService implements IProductoService {
    private final IProductoRepository productoRepository;

    @Override
    public List<ProductoResponseDto> findAll() {
        return productoRepository.findAllByEstaEliminadoFalse()
                .stream()
                .map(ProductoMapper::toResponseDto)
                .toList();
    }
}
