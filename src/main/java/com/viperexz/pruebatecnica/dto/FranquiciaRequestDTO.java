package com.viperexz.pruebatecnica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para crear/actualizar franquicia
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FranquiciaRequestDTO {

    @NotBlank(message = "El nombre de la franquicia es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;
}

