package com.viperexz.pruebatecnica.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO de respuesta para franquicia
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FranquiciaResponseDTO {
    private Long id;
    private String nombre;
    private List<SucursalResponseDTO> sucursales;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

