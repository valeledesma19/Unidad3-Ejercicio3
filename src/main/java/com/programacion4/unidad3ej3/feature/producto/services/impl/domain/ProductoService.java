package com.programacion4.unidad3ej3.feature.producto.services.impl.domain;

import com.programacion4.unidad3ej3.config.exceptions.ResourceNotFoundException;
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

    @Override
    public ProductoResponseDto findById(Long id) {
        var producto = productoRepository.findByIdAndEstaEliminadoFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto con id " + id + " no existe"));

        return ProductoMapper.toResponseDto(producto);
    }
}