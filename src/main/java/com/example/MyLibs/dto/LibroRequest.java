package com.example.MyLibs.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
public class LibroRequest {
    @NotBlank(message = "El titulo es obligatorio")
    @Size(min = 2, max = 150, message = "El titulo debe tener entre 2 y 150 caracteres")
    private String titulo;
    @NotBlank(message = "El autor es obligatorio")
    @Size(max = 150, message = "El autor no debe superar 150 caracteres")
    private String autor;
    @Size(max = 30, message = "El ISBN no debe superar 30 caracteres")
    private String isbn;
    @PositiveOrZero
    private BigDecimal precio = BigDecimal.ZERO;
    private boolean leido = false;
    @jakarta.validation.constraints.Min(value = 0, message = "La puntuacion minima es 0")
    @jakarta.validation.constraints.Max(value = 5, message = "La puntuacion maxima es 5")
    private int puntuacion = 0;
    @Size(max = 3000, message = "La sinopsis no debe superar 3000 caracteres")
    private String sinopsis;
    @Size(max = 3000, message = "Los comentarios no deben superar 3000 caracteres")
    private String comentarios;
    @Size(max = 500, message = "La URL de portada no debe superar 500 caracteres")
    private String urlPortada;
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public BigDecimal getPrecio() {
        return precio;
    }
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    public boolean isLeido() {
        return leido;
    }
    public void setLeido(boolean leido) {
        this.leido = leido;
    }
    public int getPuntuacion() {
        return puntuacion;
    }
    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
    public String getSinopsis() {
        return sinopsis;
    }
    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
    public String getComentarios() {
        return comentarios;
    }
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    public String getUrlPortada() {
        return urlPortada;
    }
    public void setUrlPortada(String urlPortada) {
        this.urlPortada = urlPortada;
    }
}
