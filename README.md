# API de Gestión de Franquicias - Prueba Técnica Backend

## Descripción del Proyecto

API REST desarrollada con Spring Boot que implementa un sistema completo de gestión de **Franquicias**, **Sucursales** y **Productos**. Permite realizar operaciones CRUD sobre una estructura jerárquica donde una franquicia contiene múltiples sucursales, y cada sucursal maneja su inventario de productos con control de stock.

## Arquitectura del Proyecto

### Estructura de Entidades

```
Franquicia
├── id: Long
├── nombre: String
└── sucursales: List<Sucursal>
    ├── id: Long
    ├── nombre: String
    ├── franquicia: Franquicia
    └── productos: List<Producto>
        ├── id: Long
        ├── nombre: String
        ├── stock: Integer
        └── sucursal: Sucursal
```

### Estructura de Capas

```
com.DanielC.pruebatecnica
├── model/           # Entidades JPA (Franquicia, Sucursal, Producto)
├── dto/             # Data Transfer Objects
├── repository/      # Capa de persistencia (JPA)
├── service/         # Lógica de negocio
├── controller/      # Endpoints REST
├── exception/       # Manejo de excepciones
└── config/          # Configuraciones (OpenAPI)
```

### Tecnologías Utilizadas

- **Java 17**: Versión LTS de Java
- **Spring Boot 4.0.1**: Framework principal
- **Spring Data JPA**: Persistencia de datos
- **H2 Database**: Base de datos en memoria
- **Lombok**: Reducción de código boilerplate
- **SpringDoc OpenAPI 2.3.0**: Documentación automática (Swagger)
- **Jakarta Validation**: Validaciones
- **SLF4J**: Logging

## Criterios de Aceptación Implementados

###  Requisitos Obligatorios

1. **Proyecto desarrollado en Spring Boot**
2. **Endpoint para agregar una nueva franquicia**
   - `POST /api/v1/franquicias`
3. **Endpoint para agregar una nueva sucursal a la franquicia**
   - `POST /api/v1/sucursales`
4. **Endpoint para agregar un nuevo producto a la sucursal**
   - `POST /api/v1/productos`
5. **Endpoint para eliminar un producto de una sucursal**
   - `DELETE /api/v1/productos/{id}`
6. **Endpoint para modificar stock de un producto**
   - `PUT /api/v1/productos/{id}/stock`
7. **Endpoint para mostrar producto con más stock por sucursal**
   - `GET /api/v1/franquicias/{id}/productos-mayor-stock`
8. **Sistema de persistencia de datos**
   - H2 Database (en memoria para desarrollo)

### Puntos Extra Implementados

- **Endpoint para actualizar nombre de franquicia**
  - `PUT /api/v1/franquicias/{id}`
- **Endpoint para actualizar nombre de sucursal**
  - `PUT /api/v1/sucursales/{id}/nombre`
- **Endpoint para actualizar nombre de producto**
  - `PUT /api/v1/productos/{id}/nombre`

## API Endpoints

### Franquicias

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/v1/franquicias` | Crear nueva franquicia |
| GET | `/api/v1/franquicias` | Obtener todas las franquicias |
| GET | `/api/v1/franquicias/{id}` | Obtener franquicia por ID |
| PUT | `/api/v1/franquicias/{id}` | Actualizar nombre de franquicia |
| DELETE | `/api/v1/franquicias/{id}` | Eliminar franquicia |
| GET | `/api/v1/franquicias/{id}/productos-mayor-stock` | Productos con mayor stock por sucursal |

### Sucursales

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/v1/sucursales` | Crear nueva sucursal |
| GET | `/api/v1/sucursales` | Obtener todas las sucursales |
| GET | `/api/v1/sucursales/{id}` | Obtener sucursal por ID |
| PUT | `/api/v1/sucursales/{id}/nombre` | Actualizar nombre de sucursal |
| DELETE | `/api/v1/sucursales/{id}` | Eliminar sucursal |

### Productos

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/v1/productos` | Crear nuevo producto |
| GET | `/api/v1/productos` | Obtener todos los productos |
| GET | `/api/v1/productos/{id}` | Obtener producto por ID |
| PUT | `/api/v1/productos/{id}/stock` | Actualizar stock del producto |
| PUT | `/api/v1/productos/{id}/nombre` | Actualizar nombre del producto |
| DELETE | `/api/v1/productos/{id}` | Eliminar producto |

## Cómo Ejecutar el Proyecto

### Requisitos Previos

- Java 17 o superior
- Gradle (incluido wrapper)

### Pasos para Ejecutar

1. **Compilar el proyecto**
```bash
./gradlew build
```

2. **Ejecutar la aplicación**
```bash
./gradlew bootRun
```

3. **Acceder a la aplicación**
   - API REST: `http://localhost:8080/api/v1`
   - Swagger UI: `http://localhost:8080/swagger-ui.html`
   - H2 Console: `http://localhost:8080/h2-console`
     - JDBC URL: `jdbc:h2:mem:testdb`
     - Username: `sa`
     - Password: *(vacío)*

## Ejemplos de Uso

### 1. Crear una Franquicia

```bash
curl -X POST http://localhost:8080/api/v1/franquicias \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Pizza Hut"
  }'
```

**Respuesta:**
```json
{
  "id": 4,
  "nombre": "Pizza Hut",
  "sucursales": [],
  "createdAt": "2025-12-23T20:00:00",
  "updatedAt": "2025-12-23T20:00:00"
}
```

### 2. Agregar una Sucursal a la Franquicia

```bash
curl -X POST http://localhost:8080/api/v1/sucursales \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Pizza Hut Centro",
    "franquiciaId": 4
  }'
```

### 3. Agregar un Producto a la Sucursal

```bash
curl -X POST http://localhost:8080/api/v1/productos \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Pizza Pepperoni Grande",
    "stock": 50,
    "sucursalId": 8
  }'
```

### 4. Actualizar Stock de un Producto

```bash
curl -X PUT http://localhost:8080/api/v1/productos/1/stock \
  -H "Content-Type: application/json" \
  -d '{
    "stock": 200
  }'
```

### 5. Eliminar un Producto

```bash
curl -X DELETE http://localhost:8080/api/v1/productos/1
```

### 6. Obtener Productos con Mayor Stock por Sucursal

```bash
curl http://localhost:8080/api/v1/franquicias/1/productos-mayor-stock
```

**Respuesta:**
```json
[
  {
    "productoId": 2,
    "productoNombre": "McNuggets 10 pzas",
    "stock": 200,
    "sucursalId": 1,
    "sucursalNombre": "McDonald's Centro"
  },
  {
    "productoId": 8,
    "productoNombre": "Coca-Cola Mediana",
    "stock": 250,
    "sucursalId": 2,
    "sucursalNombre": "McDonald's Norte"
  },
  {
    "productoId": 9,
    "productoNombre": "Happy Meal",
    "stock": 160,
    "sucursalId": 3,
    "sucursalNombre": "McDonald's Sur"
  }
]
```

### 7. Actualizar Nombre de Franquicia

```bash
curl -X PUT http://localhost:8080/api/v1/franquicias/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "McDonald'\''s International"
  }'
```

### 8. Actualizar Nombre de Sucursal

```bash
curl -X PUT http://localhost:8080/api/v1/sucursales/1/nombre \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "McDonald'\''s Centro Premium"
  }'
```

### 9. Actualizar Nombre de Producto

```bash
curl -X PUT http://localhost:8080/api/v1/productos/1/nombre \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Big Mac Premium"
  }'
```

## Validaciones Implementadas

### Franquicia
- `nombre`: No puede estar vacío, debe tener entre 3 y 100 caracteres

### Sucursal
- `nombre`: No puede estar vacío, debe tener entre 3 y 100 caracteres
- `franquiciaId`: Debe ser un ID válido de una franquicia existente

### Producto
- `nombre`: No puede estar vacío, debe tener entre 3 y 100 caracteres
- `stock`: No puede ser nulo, debe ser mayor o igual a 0
- `sucursalId`: Debe ser un ID válido de una sucursal existente

## Datos de Prueba Precargados

La aplicación inicia con datos de prueba que incluyen:

- **3 Franquicias**: McDonald's, Starbucks, Subway
- **7 Sucursales**: Distribuidas entre las franquicias
- **30+ Productos**: Con diferentes niveles de stock

Esto permite probar inmediatamente todas las funcionalidades sin necesidad de crear datos manualmente.

## 🔧 Características Técnicas

**Arquitectura en Capas**: Separación clara de responsabilidades  
**DTOs**: Objetos de transferencia para desacoplar entidades de la API  
**Validaciones**: Usando Jakarta Validation (@Valid, @NotNull, etc.)  
**Manejo Global de Excepciones**: Con @ControllerAdvice  
**Respuestas HTTP Apropiadas**: Códigos de estado correctos (200, 201, 404, 400)  
**Documentación Swagger**: Disponible en `/swagger-ui.html`  
**Logs Detallados**: SLF4J para trazabilidad  
**Relaciones JPA**: OneToMany y ManyToOne con cascada  
**Timestamps Automáticos**: @CreationTimestamp y @UpdateTimestamp  

## Documentación Swagger

Una vez la aplicación esté corriendo, accede a:

**http://localhost:8080/swagger-ui.html**

Aquí encontrarás:
- Documentación interactiva de todos los endpoints
- Posibilidad de probar los endpoints directamente desde el navegador
- Esquemas de los DTOs de request y response
- Códigos de respuesta HTTP con sus descripciones

## Consola H2

Para inspeccionar la base de datos en tiempo real:

1. Accede a: **http://localhost:8080/h2-console**
2. Configura la conexión:
   - JDBC URL: `jdbc:h2:mem:testdb`
   - Username: `sa`
   - Password: *(dejar vacío)*
3. Click en "Connect"

## Estructura del Proyecto

```
PruebaTecnica/
├── src/
│   ├── main/
│   │   ├── java/com/DanielC/pruebatecnica/
│   │   │   ├── PruebaTecnicaApplication.java
│   │   │   ├── config/
│   │   │   │   └── OpenApiConfig.java
│   │   │   ├── controller/
│   │   │   │   ├── FranquiciaController.java
│   │   │   │   ├── SucursalController.java
│   │   │   │   └── ProductoController.java
│   │   │   ├── dto/
│   │   │   │   ├── FranquiciaRequestDTO.java
│   │   │   │   ├── FranquiciaResponseDTO.java
│   │   │   │   ├── SucursalRequestDTO.java
│   │   │   │   ├── SucursalResponseDTO.java
│   │   │   │   ├── ProductoRequestDTO.java
│   │   │   │   ├── ProductoResponseDTO.java
│   │   │   │   └── ProductoMaxStockDTO.java
│   │   │   ├── exception/
│   │   │   │   ├── ErrorResponse.java
│   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   ├── FranquiciaNotFoundException.java
│   │   │   │   ├── SucursalNotFoundException.java
│   │   │   │   └── ProductoNotFoundException.java
│   │   │   ├── model/
│   │   │   │   ├── Franquicia.java
│   │   │   │   ├── Sucursal.java
│   │   │   │   └── Producto.java
│   │   │   ├── repository/
│   │   │   │   ├── FranquiciaRepository.java
│   │   │   │   ├── SucursalRepository.java
│   │   │   │   └── ProductoRepository.java
│   │   │   └── service/
│   │   │       ├── FranquiciaService.java
│   │   │       ├── FranquiciaServiceImpl.java
│   │   │       ├── SucursalService.java
│   │   │       ├── SucursalServiceImpl.java
│   │   │       ├── ProductoService.java
│   │   │       └── ProductoServiceImpl.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── data.sql
│   └── test/
├── build.gradle
├── settings.gradle
└── README.md
```

##  Características Adicionales Implementadas

1. **Relaciones Bidireccionales**: Las entidades mantienen referencias bidireccionales con JsonManagedReference y JsonBackReference para evitar recursión infinita
2. **Cascade Operations**: Las operaciones de eliminación se propagan automáticamente
3. **Lazy Loading**: Optimización de consultas con FetchType.LAZY
4. **Helper Methods**: Métodos auxiliares en entidades para mantener consistencia en relaciones
5. **Logging Completo**: Trazas detalladas en todas las operaciones
6. **Validación de Negocio**: Verificación de existencia de entidades relacionadas antes de operaciones

##  Autor

**DanielC**
- Email: botiacalle@gmail.com

## Licencia

Este proyecto fue desarrollado como parte de una prueba técnica backend.

