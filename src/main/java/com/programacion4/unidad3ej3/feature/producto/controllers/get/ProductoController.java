package com.programacion4.unidad3ej3.feature.producto.controllers.get;

import com.programacion4.unidad3ej3.config.BaseResponse;
import com.programacion4.unidad3ej3.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej3.feature.producto.services.interfaces.domain.IProductoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/productos")
@AllArgsConstructor
public class ProductoController {
    private final IProductoService productoReadService;

    @GetMapping
    public ResponseEntity<BaseResponse<List<ProductoResponseDto>>> findAll() {
        var productos = productoReadService.findAll();
        return ResponseEntity.ok(
                BaseResponse.ok(productos, "Listado obtenido correctamente")
        );
    }
}
