package com.viperexz.pruebatecnica.repository;

import com.viperexz.pruebatecnica.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para Producto
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findBySucursalId(Long sucursalId);

    @Query("SELECT p FROM Producto p WHERE p.sucursal.franquicia.id = :franquiciaId")
    List<Producto> findByFranquiciaId(@Param("franquiciaId") Long franquiciaId);

    @Query("SELECT p FROM Producto p WHERE p.sucursal.id = :sucursalId ORDER BY p.stock DESC")
    List<Producto> findTopBySucursalIdOrderByStockDesc(@Param("sucursalId") Long sucursalId);
}

