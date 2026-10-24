package com.sergiojn.televisoresmvc.base;

import java.time.LocalDate;

public abstract class Televisor {

    private String numSerie;
    private double precio;
    private String marca;
    private String modelo;
    private int pulgadas;
    private LocalDate fechaFabricacion;

    public Televisor() {
    }

    public Televisor(String numSerie, double precio, String marca, String modelo, int pulgadas, LocalDate fechaFabricacion) {
        this.numSerie = numSerie;
        this.precio = precio;
        this.marca = marca;
        this.modelo = modelo;
        this.pulgadas = pulgadas;
        this.fechaFabricacion = fechaFabricacion;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }
    public double getPrecio(){
        return precio;
    }
    public void setPrecio(double precio){
        this.precio = precio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getPulgadas() {
        return pulgadas;
    }

    public void setPulgadas(int pulgadas) {
        this.pulgadas = pulgadas;
    }

    public LocalDate getFechaFabricacion() {
        return fechaFabricacion;
    }

    public void setFechaFabricacion(LocalDate fechaFabricacion) {
        this.fechaFabricacion = fechaFabricacion;
    }
}
