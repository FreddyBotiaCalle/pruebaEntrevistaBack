package com.viperexz.pruebatecnica.service;

import com.viperexz.pruebatecnica.dto.ProductoRequestDTO;
import com.viperexz.pruebatecnica.dto.ProductoResponseDTO;
import com.viperexz.pruebatecnica.exception.ProductoNotFoundException;
import com.viperexz.pruebatecnica.exception.SucursalNotFoundException;
import com.viperexz.pruebatecnica.model.Producto;
import com.viperexz.pruebatecnica.model.Sucursal;
import com.viperexz.pruebatecnica.repository.ProductoRepository;
import com.viperexz.pruebatecnica.repository.SucursalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final SucursalRepository sucursalRepository;

    @Override
    public ProductoResponseDTO crearProducto(ProductoRequestDTO requestDTO) {
        log.info("Creando nuevo producto: {} para sucursal ID: {}",
                requestDTO.getNombre(), requestDTO.getSucursalId());

        Sucursal sucursal = sucursalRepository.findById(requestDTO.getSucursalId())
                .orElseThrow(() -> new SucursalNotFoundException(requestDTO.getSucursalId()));

        Producto producto = Producto.builder()
                .nombre(requestDTO.getNombre())
                .stock(requestDTO.getStock())
                .sucursal(sucursal)
                .build();

        Producto saved = productoRepository.save(producto);
        log.info("Producto creado con ID: {}", saved.getId());

        return mapToResponseDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoResponseDTO obtenerProductoPorId(Long id) {
        log.info("Obteniendo producto con ID: {}", id);
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));
        return mapToResponseDTO(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> obtenerTodosLosProductos() {
        log.info("Obteniendo todos los productos");
        return productoRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> obtenerProductosPorSucursal(Long sucursalId) {
        log.info("Obteniendo productos de sucursal ID: {}", sucursalId);
        if (!sucursalRepository.existsById(sucursalId)) {
            throw new SucursalNotFoundException(sucursalId);
        }
        return productoRepository.findBySucursalId(sucursalId).stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoResponseDTO actualizarStockProducto(Long id, Integer nuevoStock) {
        log.info("Actualizando stock de producto ID: {} a {}", id, nuevoStock);
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));

        producto.setStock(nuevoStock);
        Producto updated = productoRepository.save(producto);
        log.info("Stock actualizado exitosamente");

        return mapToResponseDTO(updated);
    }

    @Override
    public ProductoResponseDTO actualizarNombreProducto(Long id, String nuevoNombre) {
        log.info("Actualizando nombre de producto ID: {}", id);
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));

        producto.setNombre(nuevoNombre);
        Producto updated = productoRepository.save(producto);
        log.info("Nombre de producto actualizado exitosamente");

        return mapToResponseDTO(updated);
    }

    @Override
    public void eliminarProducto(Long id) {
        log.info("Eliminando producto con ID: {}", id);
        if (!productoRepository.existsById(id)) {
            throw new ProductoNotFoundException(id);
        }
        productoRepository.deleteById(id);
        log.info("Producto eliminado exitosamente");
    }

    private ProductoResponseDTO mapToResponseDTO(Producto producto) {
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

