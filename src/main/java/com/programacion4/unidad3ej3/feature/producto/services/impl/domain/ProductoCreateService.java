package com.programacion4.unidad3ej3.feature.producto.services.impl.domain;

import com.programacion4.unidad3ej3.config.exceptions.BadRequestException;
import org.springframework.stereotype.Service;

import com.programacion4.unidad3ej3.feature.producto.services.interfaces.domain.IProductoCreateService;
import com.programacion4.unidad3ej3.feature.producto.dtos.request.ProductoCreateRequestDto;
import com.programacion4.unidad3ej3.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej3.feature.producto.models.Producto;
import com.programacion4.unidad3ej3.feature.producto.repositories.IProductoRepository;
import com.programacion4.unidad3ej3.feature.producto.mappers.ProductoMapper;
import com.programacion4.unidad3ej3.feature.producto.services.interfaces.commons.IProductoExistByNameService;

import lombok.AllArgsConstructor;

import java.util.Locale;


@Service
@AllArgsConstructor
public class ProductoCreateService implements IProductoCreateService {

    private final IProductoExistByNameService productoExistByNameService;

    private final IProductoRepository productoRepository;


    private String capitalizar(String texto) {
        texto = texto.toLowerCase();
        return texto.substring(0, 1).toUpperCase() + texto.substring(1);
    }

    @Override
    public ProductoResponseDto create(ProductoCreateRequestDto dto) {

        if (dto.getNombre() == null || dto.getCodigo() == null ||
                dto.getDescripcion() == null || dto.getPrecio() == null ||
                dto.getStock() == null) {

            throw new BadRequestException("Todos los campos son obligatorios");
        }

        String nombreFormateado = capitalizar(dto.getNombre());
        String descripcionFormateada = capitalizar(dto.getDescripcion());

        if (productoExistByNameService.existByName(nombreFormateado)) {
            throw new BadRequestException("El nombre del producto ya existe");
        }

        Producto productoAGuardar = ProductoMapper.toEntity(dto);
        productoAGuardar.setNombre(nombreFormateado);
        productoAGuardar.setDescripcion(descripcionFormateada);
        productoAGuardar.setEstaEliminado(false);
        Producto productoGuardado = productoRepository.save(productoAGuardar);

        return ProductoMapper.toResponseDto(productoGuardado);
    }
}