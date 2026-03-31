package com.programacion4.unidad3ej3.feature.producto.controllers.patch;

import com.programacion4.unidad3ej3.config.BaseResponse;
import com.programacion4.unidad3ej3.feature.producto.dtos.request.ProductoPatchRequestDto;
import com.programacion4.unidad3ej3.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej3.feature.producto.services.interfaces.domain.IProductoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
@AllArgsConstructor
public class ProductoPatchController {

    private final IProductoService productoService;

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse<ProductoResponseDto>> patch(
            @PathVariable Long id,
            @RequestBody ProductoPatchRequestDto request) {

        ProductoResponseDto response = productoService.patch(id, request);

        return ResponseEntity.ok(
                BaseResponse.ok(response, "Producto actualizado parcialmente")
        );
    }
}
