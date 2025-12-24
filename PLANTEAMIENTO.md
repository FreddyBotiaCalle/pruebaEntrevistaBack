# Planteamiento de la Solución - Prueba Técnica Backend

## 📋 Resumen Ejecutivo

Este documento describe el planteamiento y las decisiones de diseño tomadas para la implementación de una API REST de gestión de productos utilizando Spring Boot.

## 🎯 Objetivos del Proyecto

1. Implementar una API REST completamente funcional con operaciones CRUD
2. Aplicar principios de arquitectura limpia y SOLID
3. Implementar validaciones y manejo de errores robusto
4. Proporcionar documentación automática con OpenAPI/Swagger
5. Garantizar calidad mediante testing exhaustivo

## 🏛️ Arquitectura Implementada

### Arquitectura en Capas (Layered Architecture)

```
┌─────────────────────────────────────┐
│         Controller Layer            │  ← Punto de entrada HTTP
│   (ProductController)               │
└────────────┬────────────────────────┘
             │
             ↓
┌─────────────────────────────────────┐
│         Service Layer               │  ← Lógica de negocio
│   (ProductService)                  │
└────────────┬────────────────────────┘
             │
             ↓
┌─────────────────────────────────────┐
│      Repository Layer               │  ← Persistencia
│   (ProductRepository)               │
└────────────┬────────────────────────┘
             │
             ↓
┌─────────────────────────────────────┐
│         Database (H2)               │  ← Almacenamiento
└─────────────────────────────────────┘
```

### Capas y Responsabilidades

#### 1. **Controller Layer** (Capa de Presentación)
- **Responsabilidad**: Manejo de peticiones HTTP y respuestas
- **Componentes**: `ProductController`
- **Características**:
  - Validación de entrada con `@Valid`
  - Códigos de estado HTTP apropiados
  - Documentación con anotaciones OpenAPI
  - Logging de solicitudes

#### 2. **Service Layer** (Capa de Negocio)
- **Responsabilidad**: Lógica de negocio y orquestación
- **Componentes**: `ProductService`, `ProductServiceImpl`
- **Características**:
  - Transaccionalidad con `@Transactional`
  - Conversión entre DTOs y entidades
  - Validaciones de negocio
  - Manejo de excepciones personalizadas

#### 3. **Repository Layer** (Capa de Persistencia)
- **Responsabilidad**: Acceso a datos
- **Componentes**: `ProductRepository`
- **Características**:
  - Extensión de JpaRepository
  - Métodos de búsqueda personalizados
  - Query methods de Spring Data

#### 4. **Model Layer** (Capa de Dominio)
- **Responsabilidad**: Representación de entidades
- **Componentes**: `Product`
- **Características**:
  - Anotaciones JPA
  - Validaciones Jakarta
  - Timestamps automáticos

## 🔄 Flujo de Datos

### Ejemplo: Crear un Producto

```
1. Cliente HTTP → POST /api/v1/products
                 Body: { "name": "Laptop", "price": 999.99, ... }

2. ProductController.createProduct()
   ├─→ Valida entrada con @Valid
   └─→ Llama a ProductService.createProduct()

3. ProductServiceImpl.createProduct()
   ├─→ Convierte DTO a entidad (ProductMapper)
   ├─→ Guarda en base de datos (ProductRepository)
   └─→ Convierte entidad a DTO de respuesta

4. Respuesta HTTP ← 201 Created
                   Body: { "id": 1, "name": "Laptop", ... }
```

## 📦 Componentes Principales

### 1. DTOs (Data Transfer Objects)

**Propósito**: Desacoplar la API de las entidades internas

- **ProductRequestDTO**: Datos de entrada para crear/actualizar
- **ProductResponseDTO**: Datos de salida hacia el cliente

**Ventajas**:
- Oculta detalles internos de implementación
- Permite validaciones específicas por operación
- Facilita versionado de API

### 2. Mapper Pattern

**Propósito**: Conversión entre entidades y DTOs

```java
ProductMapper
├─→ toEntity(): DTO → Entidad
├─→ toResponseDTO(): Entidad → DTO
└─→ updateEntityFromDTO(): Actualización parcial
```

### 3. Exception Handling

**Estrategia**: Manejo global con `@ControllerAdvice`

```
GlobalExceptionHandler
├─→ ProductNotFoundException → 404 Not Found
├─→ MethodArgumentNotValidException → 400 Bad Request
└─→ Exception → 500 Internal Server Error
```

**Respuesta de Error Estructurada**:
```json
{
  "timestamp": "2025-12-23T10:00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Producto no encontrado con id: 1",
  "path": "/api/v1/products/1"
}
```

## 🔒 Validaciones Implementadas

### Validaciones de Entrada (Jakarta Validation)

| Campo | Validaciones |
|-------|--------------|
| name | @NotBlank, @Size(min=3, max=100) |
| description | @Size(max=500) |
| price | @NotNull, @DecimalMin(0.01) |
| stock | @NotNull, @Min(0) |

### Validaciones de Negocio

- Existencia del producto antes de actualizar/eliminar
- Unicidad de identificadores
- Integridad referencial

## 📚 Documentación API (OpenAPI/Swagger)

### Configuración

```java
@OpenAPIDefinition(
    info = @Info(
        title = "API de Gestión de Productos",
        version = "1.0.0",
        description = "API REST para CRUD de productos"
    )
)
```

### Acceso a Documentación

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/api-docs

## 🧪 Estrategia de Testing

### Tests Unitarios (ProductServiceTest)

**Framework**: JUnit 5 + Mockito

**Cobertura**:
- ✅ Creación de producto
- ✅ Obtención de productos
- ✅ Actualización de producto
- ✅ Eliminación de producto
- ✅ Búsqueda por nombre
- ✅ Manejo de excepciones

**Patrón**: Given-When-Then (Arrange-Act-Assert)

### Tests de Integración (ProductControllerIntegrationTest)

**Framework**: Spring Boot Test + MockMvc

**Cobertura**:
- ✅ Endpoints completos
- ✅ Validación de respuestas HTTP
- ✅ Validación de JSON
- ✅ Casos de éxito y error
- ✅ Paginación

## 🗃️ Base de Datos

### Elección: H2 Database (In-Memory)

**Justificación**:
- Ideal para desarrollo y pruebas
- Sin necesidad de instalación externa
- Fácil de configurar y usar
- Incluye consola web

### Esquema de Datos

```sql
CREATE TABLE products (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    price DECIMAL(10,2) NOT NULL,
    stock INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);
```

### Datos Iniciales (data.sql)

Se incluyen 15 productos de ejemplo para facilitar la demostración.

## 🔧 Decisiones Técnicas

### 1. Lombok
**Razón**: Reduce código boilerplate significativamente
- `@Data`: Getters, setters, toString, equals, hashCode
- `@Builder`: Patrón builder para construcción de objetos
- `@Slf4j`: Logger automático
- `@RequiredArgsConstructor`: Inyección de dependencias por constructor

### 2. Spring Data JPA
**Razón**: Abstracción de persistencia potente
- Métodos CRUD sin implementación
- Query methods por convención
- Paginación integrada

### 3. Arquitectura en Capas
**Razón**: Separación de responsabilidades clara
- Facilita mantenimiento
- Permite testing aislado
- Promueve reusabilidad

### 4. DTOs vs Entidades
**Razón**: Desacoplamiento API-Modelo
- Mayor flexibilidad
- Seguridad (no exponer entidades)
- Versionado simplificado

## 📊 Principios SOLID Aplicados

### Single Responsibility Principle (SRP)
- Cada clase tiene una única responsabilidad
- Controller → HTTP, Service → Negocio, Repository → Datos

### Open/Closed Principle (OCP)
- Uso de interfaces (ProductService)
- Extensible sin modificar código existente

### Liskov Substitution Principle (LSP)
- Implementaciones intercambiables vía interfaces

### Interface Segregation Principle (ISP)
- Interfaces específicas y cohesivas

### Dependency Inversion Principle (DIP)
- Dependencias hacia abstracciones (interfaces)
- Inyección de dependencias con Spring

## 🚀 Mejores Prácticas Aplicadas

### Código Limpio
- ✅ Nombres descriptivos
- ✅ Métodos pequeños y enfocados
- ✅ Comentarios JavaDoc donde aportan valor
- ✅ Constantes en lugar de magic numbers

### Logging
- ✅ Niveles apropiados (INFO, DEBUG, ERROR)
- ✅ Mensajes contextuales
- ✅ SLF4J con Logback

### REST Best Practices
- ✅ Verbos HTTP correctos (GET, POST, PUT, DELETE)
- ✅ Códigos de estado apropiados
- ✅ Versionado de API (/api/v1)
- ✅ Nombres de recursos en plural

### Seguridad
- ✅ Validación de entrada
- ✅ Prevención de SQL Injection (JPA)
- ✅ No exposición de stack traces en producción

## 📈 Métricas de Calidad

### Cobertura de Tests
- Tests Unitarios: ~80%+ de servicios
- Tests de Integración: 100% de endpoints

### Complejidad Ciclomática
- Mantenida baja mediante métodos pequeños
- Máximo de 3-4 niveles de anidación

## 🔮 Escalabilidad Futura

### Posibles Extensiones

1. **Seguridad**
   - Spring Security + JWT
   - Autenticación y autorización

2. **Cache**
   - Spring Cache + Redis
   - Mejora de performance

3. **Búsqueda Avanzada**
   - Specifications de JPA
   - Filtros dinámicos

4. **Auditoría**
   - Spring Data JPA Auditing
   - Tracking de cambios

5. **Base de Datos Relacional**
   - PostgreSQL o MySQL
   - Configuración por profiles

6. **Monitoreo**
   - Spring Boot Actuator
   - Métricas y health checks

7. **Containerización**
   - Docker + Docker Compose
   - Despliegue facilitado

## 📝 Conclusiones

Esta implementación demuestra:

1. ✅ **Arquitectura Sólida**: Separación clara de capas
2. ✅ **Código Mantenible**: Principios SOLID y Clean Code
3. ✅ **Calidad Garantizada**: Testing exhaustivo
4. ✅ **Documentación Completa**: Swagger + JavaDoc
5. ✅ **Buenas Prácticas**: REST, validaciones, logging
6. ✅ **Preparación para Producción**: Manejo de errores robusto

El proyecto está listo para ser extendido y adaptado a necesidades empresariales reales.

