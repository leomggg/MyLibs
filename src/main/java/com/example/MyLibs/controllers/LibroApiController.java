package com.example.MyLibs.controllers;

import com.example.MyLibs.dto.LibroRequest;
import com.example.MyLibs.entities.Libro;
import com.example.MyLibs.entities.Usuario;
import com.example.MyLibs.services.LibroService;
import com.example.MyLibs.services.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@RestController
@RequestMapping("/api/libros")
@Validated
public class LibroApiController {

    private final LibroService libroService;
    private final UsuarioService usuarioService;
    private final Executor apiExecutor;

    public LibroApiController(LibroService libroService,
                              UsuarioService usuarioService,
                              @Qualifier("apiExecutor") Executor apiExecutor) {
        this.libroService = libroService;
        this.usuarioService = usuarioService;
        this.apiExecutor = apiExecutor;
    }

    @PostMapping
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    public CompletableFuture<ResponseEntity<Map<String, Object>>> registrarLibro(
            @Valid @RequestBody LibroRequest request,
            Authentication authentication) {

        return CompletableFuture.supplyAsync(() -> {
            Usuario usuario = usuarioService.buscarPorNombre(authentication.getName())
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

            Libro libro = new Libro();
            libro.setTitulo(request.getTitulo());
            libro.setAutor(request.getAutor());
            libro.setIsbn(request.getIsbn());
            libro.setPrecio(request.getPrecio());
            libro.setLeido(request.isLeido());
            libro.setPuntuacion(request.getPuntuacion());
            libro.setSinopsis(request.getSinopsis());
            libro.setComentarios(request.getComentarios());
            libro.setUrlPortada(request.getUrlPortada());
            libro.setUsuario(usuario);

            Libro guardado = libroService.guardarLibro(libro);

            return ResponseEntity.ok(Map.of(
                    "id", guardado.getId(),
                    "status", "created"
            ));
        }, apiExecutor);
    }

    @GetMapping("/media")
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    public CompletableFuture<ResponseEntity<Map<String, Object>>> obtenerMediaComunidad(
            @RequestParam @NotBlank String titulo,
            @RequestParam @NotBlank String autor) {

        return CompletableFuture.supplyAsync(() -> {
            int media = libroService.obtenerMediaComunidad(titulo, autor);
            return ResponseEntity.ok(Map.of(
                    "titulo", titulo,
                    "autor", autor,
                    "media", media
            ));
        }, apiExecutor);
    }

    @GetMapping("/mio")
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    public CompletableFuture<ResponseEntity<Map<String, Object>>> obtenerMiCopia(
            @RequestParam @NotBlank String titulo,
            @RequestParam @NotBlank String autor,
            Authentication authentication) {

        return CompletableFuture.supplyAsync(() -> {
            Usuario usuario = usuarioService.buscarPorNombre(authentication.getName())
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

            Optional<Libro> copia = libroService.buscarMiCopia(titulo, autor, usuario);

            if (copia.isEmpty()) {
                return ResponseEntity.ok(Map.of(
                        "found", false,
                        "titulo", titulo,
                        "autor", autor
                ));
            }

            Libro libro = copia.get();
            return ResponseEntity.ok(Map.of(
                    "found", true,
                    "id", libro.getId(),
                    "titulo", libro.getTitulo(),
                    "autor", libro.getAutor(),
                    "puntuacion", libro.getPuntuacion()
            ));
        }, apiExecutor);
    }
}

