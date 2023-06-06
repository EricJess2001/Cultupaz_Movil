package com.example.cultupazmovil.model;

public class Pet {

    String Expresion, titulo;

    public Pet (){}

    public Pet(String expresion, String titulo) {
        Expresion = expresion;
        this.titulo = titulo;
    }

    public String getExpresion() {
        return Expresion;
    }

    public void setExpresion(String expresion) {
        Expresion = expresion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
