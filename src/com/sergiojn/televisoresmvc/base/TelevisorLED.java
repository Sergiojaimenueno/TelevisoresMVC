package com.sergiojn.televisoresmvc.base;

import java.time.LocalDate;

/**
 * Televisor de tipo LED. Añade el dato consumo en vatios.
 * @author Sergio
 */
public class TelevisorLED extends Televisor {

    private int consumoVatios;

    /**
     * Constructor vacío.
     */
    public TelevisorLED() {
    }

    /**
     * Constructor con todos los datos.
     * @param numSerie número de serie
     * @param precio precio del tv
     * @param marca marca
     * @param modelo modelo
     * @param pulgadas tamaño en pulgadas
     * @param fechaFabricacion fecha de fabricación
     * @param consumoVatios consumo en vatios
     */
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

    /**
     * Devuelve el texto que se muestra en la lista.
     * @return los datos del televisor LED en una línea
     */
    @Override
    public String toString() {
        return "LED - Marca: "+ getMarca()+" Modelo: "+getModelo()+ " Precio: "+ getPrecio()+" Numero de serie "+getNumSerie();
    }
}
