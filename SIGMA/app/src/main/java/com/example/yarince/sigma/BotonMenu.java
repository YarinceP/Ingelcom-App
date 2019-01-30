package com.example.yarince.sigma;

public class BotonMenu {

    private String titulo;
    private String descripcion;
    private int imagen;
    private int background;

    public BotonMenu() {

    }

    public BotonMenu(String titulo, String descripcion, int imagen, int background) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagen = imagen;
       this.background = background;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public int getBackground() { return background; }

    public void setBackground(int background) { this.background = background; }

}
