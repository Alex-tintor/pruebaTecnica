# Prueba Técnica - Gestión de Tarjetas y Transacciones

## Descripción

Aplicación desarrollada como solución para una prueba técnica, compuesta por un backend en **Java con Spring Boot**, un frontend en **Angular** y persistencia en **MySQL**.

La solución permite gestionar el ciclo básico de una tarjeta y sus transacciones asociadas, incluyendo:

- creación de tarjeta
- consulta de tarjeta
- enrolamiento de tarjeta
- eliminación lógica de tarjeta
- creación de transacciones
- anulación de transacciones
- auditoría de cambios en base de datos

---

## Tecnologías utilizadas

### Backend
- Java 21
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Hibernate / Envers
- Bean Validation
- Spring Boot Actuator
- Swagger / OpenAPI
- Maven Wrapper

### Frontend
- Angular 21
- TypeScript
- Bootstrap

### Base de datos
- MySQL 8

### Infraestructura
- Docker / Docker Compose

---

## Arquitectura

La solución fue implementada como un **monolito modular**, priorizando simplicidad, mantenibilidad y facilidad de despliegue.

### Backend
Se organizó en capas:

- `controller`
- `service`
- `repository`
- `entity`
- `dto`
- `config`
- `exception`
- `utils`

### Frontend
Se organizó en vistas funcionales para:

- tarjetas
- transacciones

---

## Funcionalidades implementadas

### Tarjetas
- Crear tarjeta
- Consultar tarjeta por identificador
- Enrolar tarjeta mediante número de validación
- Eliminar tarjeta con borrado lógico
- Listar tarjetas

### Transacciones
- Crear transacción
- Anular transacción
- Listar transacciones

### Extras
- Validaciones con anotaciones
- Manejo global de excepciones
- Auditoría con Hibernate Envers
- Health check con Actuator
- Documentación de API con Swagger

---

## Reglas de negocio implementadas

### Tarjeta
- El PAN debe contener entre 16 y 19 dígitos
- El número de documento debe cumplir las restricciones definidas
- El teléfono debe contener exactamente 10 dígitos
- La tarjeta se crea con un identificador generado a partir de hash
- La tarjeta inicia en estado de creación
- El PAN se retorna enmascarado
- La eliminación de tarjeta se realiza mediante borrado lógico

### Transacción
- Solo se puede crear una transacción para una tarjeta enrolada
- La referencia de la transacción se genera automáticamente
- La anulación valida la tarjeta, referencia y valor
- Solo se permite anular dentro de la ventana de tiempo definida en la lógica del negocio

---

## Auditoría

La auditoría fue implementada con **Hibernate Envers** sobre las entidades principales del dominio.

Esto permite conservar historial de cambios en base de datos sin necesidad de implementar manualmente tablas de auditoría por cada operación.

Tablas auditadas:
- `tarjetas_AUD`
- `transacciones_AUD`

Tabla de revisiones:
- `revinfo`

---

## Estructura del proyecto

```text
/
├── backend
│   ├── src
│   ├── pom.xml
│   └── mvnw / mvnw.cmd
├── frontend
│   ├── src
│   ├── package.json
│   └── angular.json
├── docker-compose.yml
└── README.md
```

---

## Requisitos previos

Tener instalado:

- Java 21
- Node.js
- Docker Desktop
- MySQL Workbench opcional
- Git opcional

---

## Ejecución del proyecto

## 1. Base de datos con Docker

Desde la raíz del proyecto:

```bash
docker compose up -d
```

Esto levanta MySQL en el puerto configurado.

---

## 2. Ejecutar backend

Ubicarse en la carpeta `backend` y ejecutar:

### Windows
```bash
mvnw.cmd spring-boot:run
```

### Linux / Mac
```bash
./mvnw spring-boot:run
```

El backend queda disponible en:

```text
http://localhost:8080
```

---

## 3. Ejecutar frontend

Ubicarse en la carpeta `frontend` y ejecutar:

```bash
npm install
npm start
```

El frontend queda disponible en:

```text
http://localhost:4200
```

---

## Configuración del backend

Archivo principal:

```text
backend/src/main/resources/application.properties
```

Configuración esperada para la base de datos:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/pharos_bd?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.envers.audit_table_suffix=_AUD
spring.jpa.properties.hibernate.envers.store_data_at_delete=true

management.endpoints.web.exposure.include=health,info

springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

---

## Documentación de la API

Swagger disponible en:

```text
http://localhost:8080/swagger-ui.html
```

---

## Health Check

Endpoint de salud disponible en:

```text
http://localhost:8080/actuator/health
```

---

## Pantallas del frontend

El frontend incluye una interfaz básica para operar el sistema:

### Módulo de tarjetas
- crear tarjeta
- enrolar tarjeta
- listar tarjetas
- eliminar tarjeta

### Módulo de transacciones
- crear transacción
- anular transacción
- listar transacciones

---

## Manejo de errores

Se implementó manejo global de errores mediante un `ControllerAdvice`, retornando respuestas estructuradas para:

- errores de validación
- reglas de negocio
- recursos no encontrados
- errores inesperados

---

## Decisiones técnicas

Se optó por una arquitectura **monolítica modular** debido al alcance de la prueba y al tiempo disponible, priorizando:

- claridad del dominio
- mantenibilidad
- facilidad de despliegue
- facilidad de evaluación
- menor complejidad operativa

No se implementaron patrones como CQRS, eventos o arquitectura reactiva, ya que el alcance funcional no lo requería y habría introducido complejidad innecesaria.

La auditoría se resolvió con Envers por ser una solución robusta, limpia y persistida directamente en base de datos.

---

## Mejoras futuras

Algunas mejoras que podrían implementarse en una versión posterior:

- autenticación y autorización
- pruebas unitarias y de integración más amplias
- paginación y filtros en listados
- manejo más avanzado de mensajes de error en frontend
- dockerización completa de backend y frontend
- uso de migraciones con Flyway o Liquibase
- mejora visual del frontend
- separación de perfiles por entorno
- CI/CD

---

## Estado final

La solución permite ejecutar el flujo principal solicitado:

1. crear tarjeta
2. consultar tarjeta
3. enrolar tarjeta
4. crear transacción
5. anular transacción
6. eliminar tarjeta

Además, se entrega con:
- backend funcional
- frontend funcional
- auditoría en base de datos
- documentación Swagger
- health check
- despliegue de MySQL con Docker

---

## Autor

Desarrollado por: **Jaider Alexis Morales Bohorquez**
