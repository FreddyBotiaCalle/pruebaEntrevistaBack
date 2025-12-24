package com.DanielC.pruebatecnica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para crear/actualizar sucursal
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SucursalRequestDTO {

    @NotBlank(message = "El nombre de la sucursal es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    @NotNull(message = "El ID de la franquicia es obligatorio")
    private Long franquiciaId;
}

