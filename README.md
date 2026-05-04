# MyLibs

Proyecto MyLibs Java con Spring Boot, JWT y Vaadin.

MyLibs es una aplicación de biblioteca personal online que permite guardar tus libros favoritos o pendientes, puntuarlos o añadir comentarios junto a otros lectores

## Inicio rapido
```powershell
# Requiere JDK 17
.\mvnw.cmd spring-boot:run
```

## Requisitos
- JDK 17
- Maven Wrapper incluido

## Ejecutar (HTTP)
```powershell
.\mvnw.cmd spring-boot:run
```

## Ejecutar (HTTPS/TLS)
1. Crear keystore:
```powershell
.\scripts\create-keystore.ps1
```
2. Iniciar con perfil SSL:
```powershell
.\mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=ssl
```

## Pruebas
```powershell
.\mvnw.cmd test
```

## Prueba de carga ligera (local)
```powershell
.\scripts\load-test.ps1 -BaseUrl "http://localhost:8081" -TotalRequests 100 -Concurrency 10
```

## Endpoints principales
- `GET /api/health`
- `POST /api/auth/register`
- `POST /api/auth/login`
- `POST /api/libros`
- `GET /api/libros/media`
- `GET /api/libros/mio`
