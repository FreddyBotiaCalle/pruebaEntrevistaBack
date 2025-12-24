package com.DanielC.pruebatecnica.service;

import com.DanielC.pruebatecnica.dto.*;
import com.DanielC.pruebatecnica.exception.FranquiciaNotFoundException;
import com.DanielC.pruebatecnica.model.Franquicia;
import com.DanielC.pruebatecnica.model.Producto;
import com.DanielC.pruebatecnica.model.Sucursal;
import com.DanielC.pruebatecnica.repository.FranquiciaRepository;
import com.DanielC.pruebatecnica.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de Franquicia
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class FranquiciaServiceImpl implements FranquiciaService {

    private final FranquiciaRepository franquiciaRepository;
    private final ProductoRepository productoRepository;

    @Override
    public FranquiciaResponseDTO crearFranquicia(FranquiciaRequestDTO requestDTO) {
        log.info("Creando nueva franquicia: {}", requestDTO.getNombre());
        
        Franquicia franquicia = Franquicia.builder()
                .nombre(requestDTO.getNombre())
                .sucursales(new ArrayList<>())
                .build();
        
        Franquicia saved = franquiciaRepository.save(franquicia);
        log.info("Franquicia creada con ID: {}", saved.getId());
        
        return mapToResponseDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public FranquiciaResponseDTO obtenerFranquiciaPorId(Long id) {
        log.info("Obteniendo franquicia con ID: {}", id);
        Franquicia franquicia = franquiciaRepository.findById(id)
                .orElseThrow(() -> new FranquiciaNotFoundException(id));
        return mapToResponseDTO(franquicia);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FranquiciaResponseDTO> obtenerTodasLasFranquicias() {
        log.info("Obteniendo todas las franquicias");
        return franquiciaRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FranquiciaResponseDTO actualizarNombreFranquicia(Long id, FranquiciaRequestDTO requestDTO) {
        log.info("Actualizando nombre de franquicia ID: {}", id);
        Franquicia franquicia = franquiciaRepository.findById(id)
                .orElseThrow(() -> new FranquiciaNotFoundException(id));
        
        franquicia.setNombre(requestDTO.getNombre());
        Franquicia updated = franquiciaRepository.save(franquicia);
        log.info("Franquicia actualizada exitosamente");
        
        return mapToResponseDTO(updated);
    }

    @Override
    public void eliminarFranquicia(Long id) {
        log.info("Eliminando franquicia con ID: {}", id);
        if (!franquiciaRepository.existsById(id)) {
            throw new FranquiciaNotFoundException(id);
        }
        franquiciaRepository.deleteById(id);
        log.info("Franquicia eliminada exitosamente");
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoMaxStockDTO> obtenerProductosConMayorStockPorSucursal(Long franquiciaId) {
        log.info("Obteniendo productos con mayor stock para franquicia ID: {}", franquiciaId);
        
        Franquicia franquicia = franquiciaRepository.findById(franquiciaId)
                .orElseThrow(() -> new FranquiciaNotFoundException(franquiciaId));
        
        List<ProductoMaxStockDTO> resultado = new ArrayList<>();
        
        for (Sucursal sucursal : franquicia.getSucursales()) {
            List<Producto> productos = productoRepository.findTopBySucursalIdOrderByStockDesc(sucursal.getId());
            if (!productos.isEmpty()) {
                Producto productoMaxStock = productos.get(0);
                resultado.add(ProductoMaxStockDTO.builder()
                        .productoId(productoMaxStock.getId())
                        .productoNombre(productoMaxStock.getNombre())
                        .stock(productoMaxStock.getStock())
                        .sucursalId(sucursal.getId())
                        .sucursalNombre(sucursal.getNombre())
                        .build());
            }
        }
        
        log.info("Se encontraron {} productos con mayor stock", resultado.size());
        return resultado;
    }

    private FranquiciaResponseDTO mapToResponseDTO(Franquicia franquicia) {
        List<SucursalResponseDTO> sucursalesDTO = franquicia.getSucursales().stream()
                .map(this::mapSucursalToResponseDTO)
                .collect(Collectors.toList());
        
        return FranquiciaResponseDTO.builder()
                .id(franquicia.getId())
                .nombre(franquicia.getNombre())
                .sucursales(sucursalesDTO)
                .createdAt(franquicia.getCreatedAt())
                .updatedAt(franquicia.getUpdatedAt())
                .build();
    }

    private SucursalResponseDTO mapSucursalToResponseDTO(Sucursal sucursal) {
        List<ProductoResponseDTO> productosDTO = sucursal.getProductos().stream()
                .map(this::mapProductoToResponseDTO)
                .collect(Collectors.toList());
        
        return SucursalResponseDTO.builder()
                .id(sucursal.getId())
                .nombre(sucursal.getNombre())
                .franquiciaId(sucursal.getFranquicia().getId())
                .franquiciaNombre(sucursal.getFranquicia().getNombre())
                .productos(productosDTO)
                .createdAt(sucursal.getCreatedAt())
                .updatedAt(sucursal.getUpdatedAt())
                .build();
    }

    private ProductoResponseDTO mapProductoToResponseDTO(Producto producto) {
        return ProductoResponseDTO.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .stock(producto.getStock())
                .sucursalId(producto.getSucursal().getId())
                .sucursalNombre(producto.getSucursal().getNombre())
                .createdAt(producto.getCreatedAt())
                .updatedAt(producto.getUpdatedAt())
                .build();
    }
}

