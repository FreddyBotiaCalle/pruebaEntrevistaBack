package com.viperexz.pruebatecnica.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO de respuesta para sucursal
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SucursalResponseDTO {
    private Long id;
    private String nombre;
    private Long franquiciaId;
    private String franquiciaNombre;
    private List<ProductoResponseDTO> productos;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

