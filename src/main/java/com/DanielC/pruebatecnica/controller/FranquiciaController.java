package com.viperexz.pruebatecnica.controller;

import com.viperexz.pruebatecnica.dto.FranquiciaRequestDTO;
import com.viperexz.pruebatecnica.dto.FranquiciaResponseDTO;
import com.viperexz.pruebatecnica.dto.ProductoMaxStockDTO;
import com.viperexz.pruebatecnica.service.FranquiciaService;
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

/**
 * Controlador REST para gestión de Franquicias
 */
@RestController
@RequestMapping("/api/v1/franquicias")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Franquicias", description = "API para gestión de franquicias")
public class FranquiciaController {

    private final FranquiciaService franquiciaService;

    @Operation(summary = "Crear una nueva franquicia", description = "Crea una nueva franquicia en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Franquicia creada exitosamente",
                    content = @Content(schema = @Schema(implementation = FranquiciaResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<FranquiciaResponseDTO> crearFranquicia(
            @Valid @RequestBody FranquiciaRequestDTO requestDTO) {
        log.info("POST /api/v1/franquicias - Crear franquicia: {}", requestDTO.getNombre());
        FranquiciaResponseDTO response = franquiciaService.crearFranquicia(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener franquicia por ID", description = "Obtiene los detalles de una franquicia específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Franquicia encontrada",
                    content = @Content(schema = @Schema(implementation = FranquiciaResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Franquicia no encontrada",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<FranquiciaResponseDTO> obtenerFranquiciaPorId(
            @Parameter(description = "ID de la franquicia", required = true)
            @PathVariable Long id) {
        log.info("GET /api/v1/franquicias/{} - Obtener franquicia por ID", id);
        FranquiciaResponseDTO response = franquiciaService.obtenerFranquiciaPorId(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Obtener todas las franquicias", description = "Obtiene la lista completa de franquicias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de franquicias obtenida exitosamente")
    })
    @GetMapping
    public ResponseEntity<List<FranquiciaResponseDTO>> obtenerTodasLasFranquicias() {
        log.info("GET /api/v1/franquicias - Obtener todas las franquicias");
        List<FranquiciaResponseDTO> response = franquiciaService.obtenerTodasLasFranquicias();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Actualizar nombre de franquicia", description = "Actualiza el nombre de una franquicia existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Franquicia actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Franquicia no encontrada"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<FranquiciaResponseDTO> actualizarNombreFranquicia(
            @Parameter(description = "ID de la franquicia", required = true)
            @PathVariable Long id,
            @Valid @RequestBody FranquiciaRequestDTO requestDTO) {
        log.info("PUT /api/v1/franquicias/{} - Actualizar nombre de franquicia", id);
        FranquiciaResponseDTO response = franquiciaService.actualizarNombreFranquicia(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Eliminar franquicia", description = "Elimina una franquicia del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Franquicia eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Franquicia no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFranquicia(
            @Parameter(description = "ID de la franquicia", required = true)
            @PathVariable Long id) {
        log.info("DELETE /api/v1/franquicias/{} - Eliminar franquicia", id);
        franquiciaService.eliminarFranquicia(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtener productos con mayor stock por sucursal",
            description = "Para una franquicia específica, retorna el producto con mayor stock de cada sucursal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido exitosamente"),
            @ApiResponse(responseCode = "404", description = "Franquicia no encontrada")
    })
    @GetMapping("/{id}/productos-mayor-stock")
    public ResponseEntity<List<ProductoMaxStockDTO>> obtenerProductosConMayorStockPorSucursal(
            @Parameter(description = "ID de la franquicia", required = true)
            @PathVariable Long id) {
        log.info("GET /api/v1/franquicias/{}/productos-mayor-stock", id);
        List<ProductoMaxStockDTO> response = franquiciaService.obtenerProductosConMayorStockPorSucursal(id);
        return ResponseEntity.ok(response);
    }
}

