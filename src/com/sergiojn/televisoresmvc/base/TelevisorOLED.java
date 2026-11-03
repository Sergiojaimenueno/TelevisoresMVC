package com.sergiojn.televisoresmvc.base;

import java.time.LocalDate;

/**
 * Televisor de tipo OLED. Añade el dato años de garantía.
 * @author Sergio
 */
public class TelevisorOLED extends Televisor{

    private int aniosGarantia;

    /**
     * Constructor vacío.
     */
    public TelevisorOLED() {
    }

    /**
     * Constructor con todos los datos.
     * @param numSerie número de serie
     * @param precio precio del tv
     * @param marca marca
     * @param modelo modelo
     * @param pulgadas tamaño en pulgadas
     * @param fechaFabricacion fecha de fabricación
     * @param aniosGarantia años de garantía
     */
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

    /**
     * Devuelve el texto que se muestra en la lista.
     * @return los datos del televisor OLED en una línea
     */
    @Override
    public String toString() {
        return "OLED - Marca: "+ getMarca()+" Modelo: "+getModelo()+ " Precio: "+ getPrecio()+" Numero de serie "+getNumSerie();
    }
}
