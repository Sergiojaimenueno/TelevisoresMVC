package com.sergiojn.televisoresmvc.base;

import java.time.LocalDate;

public class TelevisorQLED extends Televisor {

    private int horasVidaUtil;

    public TelevisorQLED() {
    }

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

    @Override
    public String toString() {
        return "QLED - Marca: "+ getMarca()+"Modelo: "+getModelo()+ "Precio: "+ getPrecio()+"Numero de serie "+getNumSerie();
    }
}
