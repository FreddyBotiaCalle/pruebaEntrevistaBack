package com.DanielC.pruebatecnica.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para el producto con mayor stock por sucursal
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoMaxStockDTO {
    private Long productoId;
    private String productoNombre;
    private Integer stock;
    private Long sucursalId;
    private String sucursalNombre;
}

