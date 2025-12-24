package com.viperexz.pruebatecnica.service;

import com.viperexz.pruebatecnica.dto.ProductoResponseDTO;
import com.viperexz.pruebatecnica.dto.SucursalRequestDTO;
import com.viperexz.pruebatecnica.dto.SucursalResponseDTO;
import com.viperexz.pruebatecnica.exception.FranquiciaNotFoundException;
import com.viperexz.pruebatecnica.exception.SucursalNotFoundException;
import com.viperexz.pruebatecnica.model.Franquicia;
import com.viperexz.pruebatecnica.model.Sucursal;
import com.viperexz.pruebatecnica.repository.FranquiciaRepository;
import com.viperexz.pruebatecnica.repository.SucursalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SucursalServiceImpl implements SucursalService {

    private final SucursalRepository sucursalRepository;
    private final FranquiciaRepository franquiciaRepository;

    @Override
    public SucursalResponseDTO crearSucursal(SucursalRequestDTO requestDTO) {
        log.info("Creando nueva sucursal: {} para franquicia ID: {}",
                requestDTO.getNombre(), requestDTO.getFranquiciaId());

        Franquicia franquicia = franquiciaRepository.findById(requestDTO.getFranquiciaId())
                .orElseThrow(() -> new FranquiciaNotFoundException(requestDTO.getFranquiciaId()));

        Sucursal sucursal = Sucursal.builder()
                .nombre(requestDTO.getNombre())
                .franquicia(franquicia)
                .productos(new ArrayList<>())
                .build();

        Sucursal saved = sucursalRepository.save(sucursal);
        log.info("Sucursal creada con ID: {}", saved.getId());

        return mapToResponseDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public SucursalResponseDTO obtenerSucursalPorId(Long id) {
        log.info("Obteniendo sucursal con ID: {}", id);
        Sucursal sucursal = sucursalRepository.findById(id)
                .orElseThrow(() -> new SucursalNotFoundException(id));
        return mapToResponseDTO(sucursal);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SucursalResponseDTO> obtenerTodasLasSucursales() {
        log.info("Obteniendo todas las sucursales");
        return sucursalRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SucursalResponseDTO> obtenerSucursalesPorFranquicia(Long franquiciaId) {
        log.info("Obteniendo sucursales de franquicia ID: {}", franquiciaId);
        if (!franquiciaRepository.existsById(franquiciaId)) {
            throw new FranquiciaNotFoundException(franquiciaId);
        }
        return sucursalRepository.findByFranquiciaId(franquiciaId).stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SucursalResponseDTO actualizarNombreSucursal(Long id, String nombre) {
        log.info("Actualizando nombre de sucursal ID: {}", id);
        Sucursal sucursal = sucursalRepository.findById(id)
                .orElseThrow(() -> new SucursalNotFoundException(id));

        sucursal.setNombre(nombre);
        Sucursal updated = sucursalRepository.save(sucursal);
        log.info("Sucursal actualizada exitosamente");

        return mapToResponseDTO(updated);
    }

    @Override
    public void eliminarSucursal(Long id) {
        log.info("Eliminando sucursal con ID: {}", id);
        if (!sucursalRepository.existsById(id)) {
            throw new SucursalNotFoundException(id);
        }
        sucursalRepository.deleteById(id);
        log.info("Sucursal eliminada exitosamente");
    }

    private SucursalResponseDTO mapToResponseDTO(Sucursal sucursal) {
        List<ProductoResponseDTO> productosDTO = sucursal.getProductos().stream()
                .map(producto -> ProductoResponseDTO.builder()
                        .id(producto.getId())
                        .nombre(producto.getNombre())
                        .stock(producto.getStock())
                        .sucursalId(sucursal.getId())
                        .sucursalNombre(sucursal.getNombre())
                        .createdAt(producto.getCreatedAt())
                        .updatedAt(producto.getUpdatedAt())
                        .build())
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
}

