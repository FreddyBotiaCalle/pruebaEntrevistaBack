package com.viperexz.pruebatecnica.exception;

/**
 * Excepción cuando no se encuentra una franquicia
 */
public class FranquiciaNotFoundException extends RuntimeException {
    public FranquiciaNotFoundException(String message) {
        super(message);
    }

    public FranquiciaNotFoundException(Long id) {
        super("Franquicia no encontrada con id: " + id);
    }
}

