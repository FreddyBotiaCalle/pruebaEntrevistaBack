package com.viperexz.pruebatecnica.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de OpenAPI/Swagger
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API de Gestión de Franquicias",
                version = "1.0.0",
                description = "API REST para la gestión de franquicias, sucursales y productos. " +
                        "Permite realizar operaciones CRUD completas sobre franquicias y sus sucursales, " +
                        "gestionar productos con control de stock, y obtener reportes de productos con mayor stock por sucursal.",
                contact = @Contact(
                        name = "ViperexZ",
                        email = "contacto@viperexz.com"
                )
        ),
        servers = {
                @Server(
                        description = "Servidor Local",
                        url = "http://localhost:8080"
                )
        }
)
public class OpenApiConfig {
}

