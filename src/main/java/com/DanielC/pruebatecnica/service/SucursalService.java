package com.DanielC.pruebatecnica.service;

import com.DanielC.pruebatecnica.dto.SucursalRequestDTO;
import com.DanielC.pruebatecnica.dto.SucursalResponseDTO;

import java.util.List;

public interface SucursalService {
    SucursalResponseDTO crearSucursal(SucursalRequestDTO requestDTO);
    SucursalResponseDTO obtenerSucursalPorId(Long id);
    List<SucursalResponseDTO> obtenerTodasLasSucursales();
    List<SucursalResponseDTO> obtenerSucursalesPorFranquicia(Long franquiciaId);
    SucursalResponseDTO actualizarNombreSucursal(Long id, String nombre);
    void eliminarSucursal(Long id);
}

