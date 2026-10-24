package com.sergiojn.televisoresmvc.base;

import java.time.LocalDate;

public class TelevisorOLED extends Televisor{

    private int aniosGarantiaPantalla;

    public TelevisorOLED() {
    }

    public TelevisorOLED(String numSerie, double precio, String marca, String modelo, int pulgadas, LocalDate fechaFabricacion, int aniosGarantiaPantalla) {
        super(numSerie, precio, marca, modelo, pulgadas, fechaFabricacion);
        this.aniosGarantiaPantalla = aniosGarantiaPantalla;
    }

    public int getAniosGarantiaPantalla() {
        return aniosGarantiaPantalla;
    }

    public void setAniosGarantiaPantalla(int aniosGarantiaPantalla) {
        this.aniosGarantiaPantalla = aniosGarantiaPantalla;
    }

    @Override
    public String toString() {
        return "OLED - Marca: "+ getMarca()+"Modelo: "+getModelo()+ "Precio: "+ getPrecio()+"Numero de serie "+getNumSerie();
    }
}
