package com.DanielC.pruebatecnica.service;

import com.DanielC.pruebatecnica.dto.ProductoRequestDTO;
import com.DanielC.pruebatecnica.dto.ProductoResponseDTO;

import java.util.List;

public interface ProductoService {
    ProductoResponseDTO crearProducto(ProductoRequestDTO requestDTO);
    ProductoResponseDTO obtenerProductoPorId(Long id);
    List<ProductoResponseDTO> obtenerTodosLosProductos();
    List<ProductoResponseDTO> obtenerProductosPorSucursal(Long sucursalId);
    ProductoResponseDTO actualizarStockProducto(Long id, Integer nuevoStock);
    ProductoResponseDTO actualizarNombreProducto(Long id, String nuevoNombre);
    void eliminarProducto(Long id);
}

