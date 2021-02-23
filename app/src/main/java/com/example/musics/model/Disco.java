package com.example.musics.model;

import java.io.Serializable;

public class Disco implements Serializable {
    private  String imagen;
    private String artista;
    private String descripcion;
    private  int año;
    private double precio;
    private int imag;

    public int getImag() {
        return imag;
    }

    public void setImag(int imag) {
        this.imag = imag;
    }

    public Disco(String imagen, String artista, String descripcion, int año, double precio, int imag) {
        this.imagen = imagen;
        this.artista = artista;
        this.descripcion = descripcion;
        this.año = año;
        this.precio = precio;
        this.imag=imag;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
