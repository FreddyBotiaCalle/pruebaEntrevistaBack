package com.viperexz.pruebatecnica.exception;

/**
 * Excepción cuando no se encuentra una sucursal
 */
public class SucursalNotFoundException extends RuntimeException {
    public SucursalNotFoundException(String message) {
        super(message);
    }

    public SucursalNotFoundException(Long id) {
        super("Sucursal no encontrada con id: " + id);
    }
}

