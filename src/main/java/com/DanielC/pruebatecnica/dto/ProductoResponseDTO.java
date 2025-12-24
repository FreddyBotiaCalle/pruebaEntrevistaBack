package com.DanielC.pruebatecnica.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO de respuesta para producto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoResponseDTO {
    private Long id;
    private String nombre;
    private Integer stock;
    private Long sucursalId;
    private String sucursalNombre;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

