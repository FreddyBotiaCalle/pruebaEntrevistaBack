package com.DanielC.pruebatecnica.repository;

import com.DanielC.pruebatecnica.model.Franquicia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para Franquicia
 */
@Repository
public interface FranquiciaRepository extends JpaRepository<Franquicia, Long> {
    Optional<Franquicia> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}

