# Resumen de Implementación - API de Gestión de Franquicias

## ✅ Cumplimiento de Requisitos

### Criterios de Aceptación Obligatorios

| # | Requisito | Estado | Endpoint/Implementación |
|---|-----------|--------|------------------------|
| 1 | Proyecto en Spring Boot | ✅ | Spring Boot 4.0.1 + Java 17 |
| 2 | Agregar nueva franquicia | ✅ | `POST /api/v1/franquicias` |
| 3 | Agregar sucursal a franquicia | ✅ | `POST /api/v1/sucursales` |
| 4 | Agregar producto a sucursal | ✅ | `POST /api/v1/productos` |
| 5 | Eliminar producto de sucursal | ✅ | `DELETE /api/v1/productos/{id}` |
| 6 | Modificar stock de producto | ✅ | `PUT /api/v1/productos/{id}/stock` |
| 7 | Producto con mayor stock por sucursal | ✅ | `GET /api/v1/franquicias/{id}/productos-mayor-stock` |
| 8 | Sistema de persistencia | ✅ | H2 Database (en memoria) |

### Puntos Extra Implementados

| Requisito | Estado | Endpoint |
|-----------|--------|----------|
| Actualizar nombre de franquicia | ✅ | `PUT /api/v1/franquicias/{id}` |
| Actualizar nombre de sucursal | ✅ | `PUT /api/v1/sucursales/{id}/nombre` |
| Actualizar nombre de producto | ✅ | `PUT /api/v1/productos/{id}/nombre` |
| Documentación con Docker | ⬜ | Pendiente (Plus opcional) |
| Programación reactiva | ⬜ | Pendiente (Plus opcional) |
| Infraestructura como código | ⬜ | Pendiente (Plus opcional) |
| Despliegue en la nube | ⬜ | Pendiente (Plus opcional) |

## 📊 Estructura de Datos Implementada

```
┌─────────────────────┐
│    FRANQUICIA       │
│  - id: Long         │
│  - nombre: String   │
│  - createdAt        │
│  - updatedAt        │
└──────────┬──────────┘
           │ 1:N
           ▼
┌─────────────────────┐
│     SUCURSAL        │
│  - id: Long         │
│  - nombre: String   │
│  - franquiciaId     │
│  - createdAt        │
│  - updatedAt        │
└──────────┬──────────┘
           │ 1:N
           ▼
┌─────────────────────┐
│     PRODUCTO        │
│  - id: Long         │
│  - nombre: String   │
│  - stock: Integer   │
│  - sucursalId       │
│  - createdAt        │
│  - updatedAt        │
└─────────────────────┘
```

## 🎯 Endpoints Detallados

### Franquicias

#### 1. Crear Franquicia
- **Método**: POST
- **URL**: `/api/v1/franquicias`
- **Body**:
```json
{
  "nombre": "Pizza Hut"
}
```
- **Response**: 201 Created
```json
{
  "id": 4,
  "nombre": "Pizza Hut",
  "sucursales": [],
  "createdAt": "2025-12-23T20:00:00",
  "updatedAt": "2025-12-23T20:00:00"
}
```

#### 2. Obtener Todas las Franquicias
- **Método**: GET
- **URL**: `/api/v1/franquicias`
- **Response**: 200 OK + Array de franquicias

#### 3. Obtener Franquicia por ID
- **Método**: GET
- **URL**: `/api/v1/franquicias/{id}`
- **Response**: 200 OK o 404 Not Found

#### 4. Actualizar Nombre de Franquicia
- **Método**: PUT
- **URL**: `/api/v1/franquicias/{id}`
- **Body**:
```json
{
  "nombre": "McDonald's International"
}
```
- **Response**: 200 OK

#### 5. Eliminar Franquicia
- **Método**: DELETE
- **URL**: `/api/v1/franquicias/{id}`
- **Response**: 204 No Content

#### 6. Obtener Productos con Mayor Stock por Sucursal
- **Método**: GET
- **URL**: `/api/v1/franquicias/{id}/productos-mayor-stock`
- **Response**: 200 OK
```json
[
  {
    "productoId": 2,
    "productoNombre": "McNuggets 10 pzas",
    "stock": 200,
    "sucursalId": 1,
    "sucursalNombre": "McDonald's Centro"
  }
]
```

### Sucursales

#### 1. Crear Sucursal
- **Método**: POST
- **URL**: `/api/v1/sucursales`
- **Body**:
```json
{
  "nombre": "Pizza Hut Centro",
  "franquiciaId": 4
}
```
- **Response**: 201 Created

#### 2. Obtener Todas las Sucursales
- **Método**: GET
- **URL**: `/api/v1/sucursales`
- **Response**: 200 OK

#### 3. Obtener Sucursal por ID
- **Método**: GET
- **URL**: `/api/v1/sucursales/{id}`
- **Response**: 200 OK o 404 Not Found

#### 4. Actualizar Nombre de Sucursal
- **Método**: PUT
- **URL**: `/api/v1/sucursales/{id}/nombre`
- **Body**:
```json
{
  "nombre": "Pizza Hut Centro Premium"
}
```
- **Response**: 200 OK

#### 5. Eliminar Sucursal
- **Método**: DELETE
- **URL**: `/api/v1/sucursales/{id}`
- **Response**: 204 No Content

### Productos

#### 1. Crear Producto
- **Método**: POST
- **URL**: `/api/v1/productos`
- **Body**:
```json
{
  "nombre": "Pizza Pepperoni Grande",
  "stock": 50,
  "sucursalId": 8
}
```
- **Response**: 201 Created

#### 2. Obtener Todos los Productos
- **Método**: GET
- **URL**: `/api/v1/productos`
- **Response**: 200 OK

#### 3. Obtener Producto por ID
- **Método**: GET
- **URL**: `/api/v1/productos/{id}`
- **Response**: 200 OK o 404 Not Found

#### 4. Actualizar Stock de Producto
- **Método**: PUT
- **URL**: `/api/v1/productos/{id}/stock`
- **Body**:
```json
{
  "stock": 200
}
```
- **Response**: 200 OK

#### 5. Actualizar Nombre de Producto
- **Método**: PUT
- **URL**: `/api/v1/productos/{id}/nombre`
- **Body**:
```json
{
  "nombre": "Pizza Pepperoni Extra Grande"
}
```
- **Response**: 200 OK

#### 6. Eliminar Producto
- **Método**: DELETE
- **URL**: `/api/v1/productos/{id}`
- **Response**: 204 No Content

## 🔐 Validaciones

### Franquicia
- `nombre`: NotBlank, Size(min=3, max=100)

### Sucursal
- `nombre`: NotBlank, Size(min=3, max=100)
- `franquiciaId`: NotNull, debe existir

### Producto
- `nombre`: NotBlank, Size(min=3, max=100)
- `stock`: NotNull, Min(0)
- `sucursalId`: NotNull, debe existir

## 📦 Tecnologías Utilizadas

| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| Java | 17 | Lenguaje base |
| Spring Boot | 4.0.1 | Framework principal |
| Spring Data JPA | 4.0.1 | ORM y persistencia |
| H2 Database | 2.4.240 | Base de datos en memoria |
| Lombok | 1.18.36 | Reducción de boilerplate |
| SpringDoc OpenAPI | 2.3.0 | Documentación Swagger |
| Jakarta Validation | 4.0.0 | Validaciones |
| SLF4J | 2.0.9 | Logging |
| Gradle | 9.2.1 | Build tool |

## 🚀 Acceso Rápido

Una vez la aplicación esté corriendo:

- **API Base**: http://localhost:8080/api/v1
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs JSON**: http://localhost:8080/api-docs
- **H2 Console**: http://localhost:8080/h2-console

## 📁 Datos de Prueba

La aplicación se inicia con:
- **3 Franquicias**: McDonald's, Starbucks, Subway
- **7 Sucursales**
- **30+ Productos** con diferentes stocks

Estos datos permiten probar inmediatamente la funcionalidad del endpoint:
`GET /api/v1/franquicias/1/productos-mayor-stock`

## ⚙️ Configuración

### application.properties

```properties
# Puerto del servidor
server.port=8080

# Base de datos H2
spring.datasource.url=jdbc:h2:mem:testdb
spring.h2.console.enabled=true

# JPA
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.defer-datasource-initialization=true

# Swagger
springdoc.swagger-ui.path=/swagger-ui.html
```

## 🧪 Testing

Para probar la aplicación puedes usar:

1. **Swagger UI**: Interfaz gráfica interactiva
2. **Postman**: Importar la colección desde el JSON de OpenAPI
3. **curl**: Comandos de terminal (ver ejemplos en README.md)
4. **H2 Console**: Para verificar datos directamente en la BD

## 📈 Mejoras Futuras (Plus)

- [ ] Contenedorización con Docker
- [ ] Implementación reactiva con WebFlux
- [ ] CI/CD con GitHub Actions
- [ ] Despliegue en AWS/Azure
- [ ] Infraestructura como código (Terraform)
- [ ] Tests unitarios e integración
- [ ] Base de datos MySQL/PostgreSQL
- [ ] Autenticación y autorización (Spring Security + JWT)
- [ ] Caché con Redis
- [ ] Métricas con Actuator

