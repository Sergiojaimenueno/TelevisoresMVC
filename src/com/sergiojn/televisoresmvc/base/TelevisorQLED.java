package com.sergiojn.televisoresmvc.base;

import java.time.LocalDate;

/**
 * Televisor de tipo QLED. Añade el dato horas de vida útil.
 * @author Sergio
 */
public class TelevisorQLED extends Televisor {

    private int horasVidaUtil;

    /**
     * Constructor vacío.
     */
    public TelevisorQLED() {
    }

    /**
     * Constructor con todos los datos.
     * @param numSerie número de serie
     * @param precio precio del tv
     * @param marca marca
     * @param modelo modelo
     * @param pulgadas tamaño en pulgadas
     * @param fechaFabricacion fecha de fabricación
     * @param horasVidaUtil horas de vida útil
     */
    public TelevisorQLED(String numSerie, double precio, String marca, String modelo, int pulgadas, LocalDate fechaFabricacion, int horasVidaUtil) {
        super(numSerie, precio, marca, modelo, pulgadas, fechaFabricacion);
        this.horasVidaUtil = horasVidaUtil;
    }

    public int getHorasVidaUtil() {
        return horasVidaUtil;
    }

    public void setHorasVidaUtil(int horasVidaUtil) {
        this.horasVidaUtil = horasVidaUtil;
    }

    /**
     * Devuelve el texto que se muestra en la lista.
     * @return los datos del televisor QLED en una línea
     */
    @Override
    public String toString() {
        return "QLED - Marca: "+ getMarca()+" Modelo: "+getModelo()+ " Precio: "+ getPrecio()+" Numero de serie "+getNumSerie();
    }
}
