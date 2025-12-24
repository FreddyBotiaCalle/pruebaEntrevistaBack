package com.viperexz.pruebatecnica.repository;

import com.viperexz.pruebatecnica.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para Sucursal
 */
@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
    List<Sucursal> findByFranquiciaId(Long franquiciaId);
}

