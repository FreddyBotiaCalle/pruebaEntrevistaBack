package com.DanielC.pruebatecnica.controller;

import com.DanielC.pruebatecnica.dto.SucursalRequestDTO;
import com.DanielC.pruebatecnica.dto.SucursalResponseDTO;
import com.DanielC.pruebatecnica.service.SucursalService;
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
 * Controlador REST para gestión de Sucursales
 */
@RestController
@RequestMapping("/api/v1/sucursales")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Sucursales", description = "API para gestión de sucursales")
public class SucursalController {

    private final SucursalService sucursalService;

    @Operation(summary = "Crear una nueva sucursal", description = "Agrega una nueva sucursal a una franquicia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sucursal creada exitosamente",
                    content = @Content(schema = @Schema(implementation = SucursalResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Franquicia no encontrada")
    })
    @PostMapping
    public ResponseEntity<SucursalResponseDTO> crearSucursal(
            @Valid @RequestBody SucursalRequestDTO requestDTO) {
        log.info("POST /api/v1/sucursales - Crear sucursal: {}", requestDTO.getNombre());
        SucursalResponseDTO response = sucursalService.crearSucursal(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener sucursal por ID", description = "Obtiene los detalles de una sucursal específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursal encontrada"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SucursalResponseDTO> obtenerSucursalPorId(
            @Parameter(description = "ID de la sucursal", required = true)
            @PathVariable Long id) {
        log.info("GET /api/v1/sucursales/{} - Obtener sucursal por ID", id);
        SucursalResponseDTO response = sucursalService.obtenerSucursalPorId(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Obtener todas las sucursales", description = "Obtiene la lista completa de sucursales")
    @GetMapping
    public ResponseEntity<List<SucursalResponseDTO>> obtenerTodasLasSucursales() {
        log.info("GET /api/v1/sucursales - Obtener todas las sucursales");
        List<SucursalResponseDTO> response = sucursalService.obtenerTodasLasSucursales();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Actualizar nombre de sucursal", description = "Actualiza el nombre de una sucursal existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursal actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @PutMapping("/{id}/nombre")
    public ResponseEntity<SucursalResponseDTO> actualizarNombreSucursal(
            @Parameter(description = "ID de la sucursal", required = true)
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        log.info("PUT /api/v1/sucursales/{}/nombre - Actualizar nombre", id);
        String nuevoNombre = body.get("nombre");
        SucursalResponseDTO response = sucursalService.actualizarNombreSucursal(id, nuevoNombre);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Eliminar sucursal", description = "Elimina una sucursal del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sucursal eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSucursal(
            @Parameter(description = "ID de la sucursal", required = true)
            @PathVariable Long id) {
        log.info("DELETE /api/v1/sucursales/{} - Eliminar sucursal", id);
        sucursalService.eliminarSucursal(id);
        return ResponseEntity.noContent().build();
    }
}

