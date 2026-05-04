# Informe tecnico - MyLibs
Este documento se actualiza por fases segun el PDF del proyecto.
## Fase 1 - Arquitectura y entorno de red [cite: 5, 13]
- Estado: completado.
- Protocolo y arquitectura: servicio HTTP REST en Spring Boot.
- Puerto fijo: `server.port=8081` en `src/main/resources/application.properties`.
- Endpoint de prueba: `GET /api/health` en `src/main/java/com/example/MyLibs/controllers/HealthController.java`.
- Script de conectividad: `scripts/test-connect.ps1`.
## Fase 2 - Servicio en red y concurrencia [cite: 16, 17]
- Estado: completado.
- Concurrencia: `CompletableFuture` con `ThreadPoolTaskExecutor` en `src/main/java/com/example/MyLibs/config/AsyncConfig.java`.
- Registro de datos: `POST /api/libros` en `src/main/java/com/example/MyLibs/controllers/LibroApiController.java`.
- Consulta de datos: `GET /api/libros/media` y `GET /api/libros/mio` en el mismo controlador.
## Fase 3 - Gestion de usuarios y roles [cite: 20, 21]
- Estado: completado.
- Autenticacion con JWT: `src/main/java/com/example/MyLibs/config/JWTUtil.java` y `src/main/java/com/example/MyLibs/controllers/AuthController.java`.
- Hashing de password: `BCryptPasswordEncoder` en `src/main/java/com/example/MyLibs/config/SecurityConfig.java`.
- Registro REST: `POST /api/auth/register` en `src/main/java/com/example/MyLibs/controllers/AuthController.java`.
## Fase 4 - Cifrado y seguridad critica [cite: 3, 18, 19]
- Estado: completado.
- Headers de seguridad para `/api/**`: `src/main/java/com/example/MyLibs/config/ApiSecurityHeadersFilter.java`.
- Validacion estricta de entradas: `src/main/java/com/example/MyLibs/dto/LibroRequest.java`.
- Manejo de errores de validacion: `src/main/java/com/example/MyLibs/controllers/RestExceptionHandler.java`.
- TLS/SSL: perfil `ssl` en `src/main/resources/application-ssl.properties` y script `scripts/create-keystore.ps1`.

## Fase 5 - Testing, documentacion y entrega [cite: 22, 24]
- Estado: en progreso.
- Pruebas de seguridad y validacion: `src/test/java/com/example/MyLibs/controllers/ApiSecurityTest.java`.
- Pruebas de registro: `src/test/java/com/example/MyLibs/controllers/AuthRegisterTest.java`.
- Documentacion tecnica: `README.md`.
- Ejecucion de tests: `mvnw.cmd test` con JDK 17 (10 tests, 0 failures, 0 errors).
- Prueba de carga ligera: `scripts/load-test.ps1` (pendiente de ejecutar con servidor activo).
