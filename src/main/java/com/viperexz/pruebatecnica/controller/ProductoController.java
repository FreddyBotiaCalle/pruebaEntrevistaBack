package com.viperexz.pruebatecnica.controller;

import com.viperexz.pruebatecnica.dto.ProductoRequestDTO;
import com.viperexz.pruebatecnica.dto.ProductoResponseDTO;
import com.viperexz.pruebatecnica.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controlador REST para gestión de Productos
 */
@RestController
@RequestMapping("/api/v1/productos")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Productos", description = "API para gestión de productos")
public class ProductoController {

    private final ProductoService productoService;

    @Operation(summary = "Crear un nuevo producto", description = "Agrega un nuevo producto a una sucursal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente",
                    content = @Content(schema = @Schema(implementation = ProductoResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crearProducto(
            @Valid @RequestBody ProductoRequestDTO requestDTO) {
        log.info("POST /api/v1/productos - Crear producto: {}", requestDTO.getNombre());
        ProductoResponseDTO response = productoService.crearProducto(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener producto por ID", description = "Obtiene los detalles de un producto específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> obtenerProductoPorId(
            @Parameter(description = "ID del producto", required = true)
            @PathVariable Long id) {
        log.info("GET /api/v1/productos/{} - Obtener producto por ID", id);
        ProductoResponseDTO response = productoService.obtenerProductoPorId(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Obtener todos los productos", description = "Obtiene la lista completa de productos")
    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> obtenerTodosLosProductos() {
        log.info("GET /api/v1/productos - Obtener todos los productos");
        List<ProductoResponseDTO> response = productoService.obtenerTodosLosProductos();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Actualizar stock de producto", description = "Modifica la cantidad de stock de un producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stock actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
            @ApiResponse(responseCode = "400", description = "Valor de stock inválido")
    })
    @PutMapping("/{id}/stock")
    public ResponseEntity<ProductoResponseDTO> actualizarStockProducto(
            @Parameter(description = "ID del producto", required = true)
            @PathVariable Long id,
            @RequestBody Map<String, Integer> body) {
        log.info("PUT /api/v1/productos/{}/stock - Actualizar stock", id);
        Integer nuevoStock = body.get("stock");
        ProductoResponseDTO response = productoService.actualizarStockProducto(id, nuevoStock);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Actualizar nombre de producto", description = "Actualiza el nombre de un producto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nombre actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PutMapping("/{id}/nombre")
    public ResponseEntity<ProductoResponseDTO> actualizarNombreProducto(
            @Parameter(description = "ID del producto", required = true)
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        log.info("PUT /api/v1/productos/{}/nombre - Actualizar nombre", id);
        String nuevoNombre = body.get("nombre");
        ProductoResponseDTO response = productoService.actualizarNombreProducto(id, nuevoNombre);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Eliminar producto", description = "Elimina un producto de una sucursal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(
            @Parameter(description = "ID del producto", required = true)
            @PathVariable Long id) {
        log.info("DELETE /api/v1/productos/{} - Eliminar producto", id);
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}

