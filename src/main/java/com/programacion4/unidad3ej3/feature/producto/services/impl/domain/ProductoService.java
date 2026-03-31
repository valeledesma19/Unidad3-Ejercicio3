package com.programacion4.unidad3ej3.feature.producto.services.impl.domain;

import com.programacion4.unidad3ej3.config.exceptions.ResourceNotFoundException;
import com.programacion4.unidad3ej3.feature.producto.dtos.request.ProductoPatchRequestDto;
import com.programacion4.unidad3ej3.feature.producto.dtos.request.ProductoUpdateRequestDto;
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
    @Override
    public ProductoResponseDto update(Long id, ProductoUpdateRequestDto request) {
        var producto = productoRepository.findByIdAndEstaEliminadoFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto con id " + id + " no existe"));

        producto.setCodigo(request.getCodigo());
        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setPrecio(request.getPrecio());
        producto.setStock(request.getStock());

        var actualizado = productoRepository.save(producto);
        return ProductoMapper.toResponseDto(actualizado);
    }
    @Override
    public ProductoResponseDto patch(Long id, ProductoPatchRequestDto request) {

        var producto = productoRepository.findByIdAndEstaEliminadoFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto con id " + id + " no existe"));

        if (request.getPrecio() != null) {
            producto.setPrecio(request.getPrecio());
        }

        if (request.getStock() != null) {
            producto.setStock(request.getStock());
        }

        var productoActualizado = productoRepository.save(producto);

        return ProductoMapper.toResponseDto(productoActualizado);
    }
    @Override
    public void delete(Long id) {

        var producto = productoRepository.findByIdAndEstaEliminadoFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Producto con id " + id + " no existe"));

        producto.setEstaEliminado(true);
        productoRepository.save(producto);
    }
}