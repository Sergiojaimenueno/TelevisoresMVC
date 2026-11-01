package com.sergiojn.televisoresmvc.base;

import java.time.LocalDate;

public class TelevisorLED extends Televisor {

    private int consumoVatios;

    public TelevisorLED() {
    }

    public TelevisorLED(String numSerie, double precio, String marca, String modelo, int pulgadas, LocalDate fechaFabricacion, int consumoVatios) {
        super(numSerie, precio, marca, modelo, pulgadas, fechaFabricacion);
        this.consumoVatios = consumoVatios;
    }


    public int getConsumoVatios() {
        return consumoVatios;
    }

    public void setConsumoVatios(int consumoVatios) {
        this.consumoVatios = consumoVatios;
    }

    @Override
    public String toString() {
        return "LED - Marca: "+ getMarca()+" Modelo: "+getModelo()+ " Precio: "+ getPrecio()+" Numero de serie "+getNumSerie();
    }
}
