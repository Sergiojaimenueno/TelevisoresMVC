package com.sergiojn.televisoresmvc.base;

import java.time.LocalDate;

public class TelevisorOLED extends Televisor{

    private int aniosGarantia;

    public TelevisorOLED() {
    }

    public TelevisorOLED(String numSerie, double precio, String marca, String modelo, int pulgadas, LocalDate fechaFabricacion, int aniosGarantia) {
        super(numSerie, precio, marca, modelo, pulgadas, fechaFabricacion);
        this.aniosGarantia = aniosGarantia;
    }

    public int getAniosGarantia() {
        return aniosGarantia;
    }

    public void setAniosGarantia(int aniosGarantia) {
        this.aniosGarantia = aniosGarantia;
    }

    @Override
    public String toString() {
        return "OLED - Marca: "+ getMarca()+" Modelo: "+getModelo()+ " Precio: "+ getPrecio()+" Numero de serie "+getNumSerie();
    }
}
