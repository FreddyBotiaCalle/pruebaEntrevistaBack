package com.viperexz.pruebatecnica.service;

import com.viperexz.pruebatecnica.dto.FranquiciaRequestDTO;
import com.viperexz.pruebatecnica.dto.FranquiciaResponseDTO;
import com.viperexz.pruebatecnica.dto.ProductoMaxStockDTO;

import java.util.List;

/**
 * Interfaz del servicio de Franquicia
 */
public interface FranquiciaService {
    FranquiciaResponseDTO crearFranquicia(FranquiciaRequestDTO requestDTO);
    FranquiciaResponseDTO obtenerFranquiciaPorId(Long id);
    List<FranquiciaResponseDTO> obtenerTodasLasFranquicias();
    FranquiciaResponseDTO actualizarNombreFranquicia(Long id, FranquiciaRequestDTO requestDTO);
    void eliminarFranquicia(Long id);
    List<ProductoMaxStockDTO> obtenerProductosConMayorStockPorSucursal(Long franquiciaId);
}

